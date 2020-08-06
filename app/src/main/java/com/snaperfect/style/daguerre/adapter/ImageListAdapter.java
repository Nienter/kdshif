package com.snaperfect.style.daguerre.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.activity.EditActivity;
import com.snaperfect.style.daguerre.adapter.PreviewStyleAdapter;
import com.snaperfect.style.daguerre.style.StyleObj;
import com.snaperfect.style.daguerre.utils.AlbumManager;
import com.snaperfect.style.daguerre.utils.AppEvent;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.Pair;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import com.snaperfect.style.daguerre.utils.StoreUtils;
import com.snaperfect.style.daguerre.utils.ToastUtils;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;

/* renamed from: com.snaperfect.style.daguerre.adapter.a */
public class ImageListAdapter extends BaseAdapter implements PreviewStyleAdapter.C1513a {

    /* renamed from: a */
    private static final int[] f1882a = {R.id.preview_row_imageview1, R.id.preview_row_imageview2, R.id.preview_row_imageview3};

    /* renamed from: b */
    private static final Integer f1883b = 0;

    /* renamed from: c */
    private static final Integer f1884c = 1;

    /* renamed from: d */
    private int f1885d = 0;

    /* renamed from: e */
    private int f1886e = 0;

    /* renamed from: f */
    private LayoutInflater f1887f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Context f1888g;

    /* renamed from: h */
    private Activity f1889h;

    /* renamed from: i */
    private AlbumManager f1890i;

    /* renamed from: j */
    private GPUImage f1891j;

    /* renamed from: k */
    private final FirebaseAnalytics f1892k;

    /* renamed from: l */
    private View.OnClickListener f1893l;

    /* renamed from: m */
    private AbsListView.LayoutParams f1894m;

    /* renamed from: com.snaperfect.style.daguerre.adapter.a$a */
    /* compiled from: ImageListAdapter */
    private class C1517a extends AsyncTask<Bitmap, Void, Float> {

        /* renamed from: a */
        final String f1899a;

        C1517a(String str) {
            this.f1899a = str;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00bb  */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0108  */
        /* renamed from: a */
        public Float doInBackground(Bitmap... bitmapArr) {
            FaceDetector faceDetector;
            FaceDetector faceDetector2;
            boolean z;
            float f;
            PointF pointF;
            PointF pointF2;
            int i = 0;
            Bitmap bitmap = bitmapArr[0];
            if (bitmap == null || bitmap.isRecycled()) {
                return Float.valueOf(0.0f);
            }
            try {
                faceDetector = new FaceDetector.Builder(ImageListAdapter.this.f1888g).setTrackingEnabled(false).setLandmarkType(1).setMode(0).build();
                try {
                    faceDetector2 = faceDetector;
                    z = faceDetector.isOperational();
                } catch (Throwable th) {
                    faceDetector2 = faceDetector;
                    z = false;
                    if (faceDetector2 == null) {
                    }
                    if (bitmap.isRecycled()) {
                    }
                    float min = f / ((float) Math.min(bitmap.getWidth(), bitmap.getHeight()));
                    if (faceDetector2 != null) {
                    }
                    return Float.valueOf(min);
                }
            } catch (Throwable th2) {
                faceDetector = null;
            }
            if (faceDetector2 == null && z) {
                SparseArray<Face> detect = faceDetector2.detect(new Frame.Builder().setBitmap(bitmap).build());
                f = 0.0f;
                while (true) {
                    int i2 = i;
                    if (i2 >= detect.size()) {
                        break;
                    }
                    PointF pointF3 = null;
                    PointF pointF4 = null;
                    for (Landmark next : detect.valueAt(i2).getLandmarks()) {
                        if (next.getType() == 4) {
                            PointF pointF5 = pointF3;
                            pointF2 = next.getPosition();
                            pointF = pointF5;
                        } else if (next.getType() == 10) {
                            pointF = next.getPosition();
                            pointF2 = pointF4;
                        } else {
                            pointF = pointF3;
                            pointF2 = pointF4;
                        }
                        pointF4 = pointF2;
                        pointF3 = pointF;
                    }
                    if (!(pointF4 == null || pointF3 == null)) {
                        float length = new PointF(pointF4.x - pointF3.x, pointF4.y - pointF3.y).length();
                        if (length > f) {
                            f = length;
                        }
                    }
                    i = i2 + 1;
                }
            } else if (bitmap.isRecycled()) {
                Bitmap a = m2628a(bitmap);
                FaceDetector.Face[] faceArr = new FaceDetector.Face[3];
                int findFaces = new android.media.FaceDetector(a.getWidth(), a.getHeight(), 3).findFaces(a, faceArr);
                float f2 = 0.0f;
                for (int i3 = 0; i3 < findFaces; i3++) {
                    if (faceArr[i3].eyesDistance() > f2) {
                        f2 = faceArr[i3].eyesDistance();
                    }
                }
                f = f2;
            } else {
                f = 0.0f;
            }
            float min2 = f / ((float) Math.min(bitmap.getWidth(), bitmap.getHeight()));
            if (faceDetector2 != null) {
                faceDetector2.release();
            }
            return Float.valueOf(min2);
        }

        /* renamed from: a */
        private Bitmap m2628a(Bitmap bitmap) {
            Bitmap copy = bitmap.copy(Bitmap.Config.RGB_565, false);
            if (copy.getWidth() % 2 != 0) {
                return Bitmap.createBitmap(copy, 0, 0, copy.getWidth() - (copy.getWidth() % 2), copy.getHeight());
            }
            return copy;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Float f) {
            super.onPostExecute(f);
            Intent intent = new Intent("action_face");
            intent.putExtra("face", f);
            intent.putExtra("asset_id", this.f1899a);
            ImageListAdapter.this.f1888g.sendBroadcast(intent);
        }
    }

    /* renamed from: a */
    private float m2619a(int i) {
        return (this.f1888g.getResources().getDisplayMetrics().xdpi / 160.0f) * ((float) i);
    }

    public ImageListAdapter(Context context, Activity activity, FirebaseAnalytics firebaseAnalytics) {
        this.f1888g = context;
        this.f1891j = new GPUImage(context);
        this.f1889h = activity;
        this.f1892k = firebaseAnalytics;
        this.f1887f = LayoutInflater.from(activity);
        float f = ScreenInfo.m3112a(this.f1888g).f2102a;
        this.f1885d = (int) m2619a(140);
        this.f1886e = (int) ((f - m2619a(42)) / 3.0f);
        this.f1894m = new AbsListView.LayoutParams(-1, this.f1886e);
        this.f1890i = new AlbumManager(this.f1888g, this, 6, 2, 3);
        this.f1893l = new View.OnClickListener() {
            public void onClick(View view) {
                ImageListAdapter.this.m2623a((Pair<PhotoAsset, Integer>) (Pair) ((ImageView) view).getTag());
            }
        };
        if (this.f1890i.mo17286a() == 0) {
            mo16994a(activity);
        }
    }

    /* renamed from: a */
    public void mo16994a(final Activity activity) {
        new AlertDialog.Builder(activity).setTitle(R.string.preview_no_photo).setNegativeButton(R.string.dialog_button_cancel, null).setPositiveButton(R.string.dialog_button_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory("android.intent.category.DEFAULT");
                activity.startActivity(intent);
            }
        }).create().show();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2623a(Pair<PhotoAsset, Integer> iVar) {
        int i;
        boolean z;
        if (iVar != null && iVar.f2264a != null) {
            if (!((PhotoAsset) iVar.f2264a).mo17265f() || ((PhotoAsset) iVar.f2264a).mo17267h() <= 0.0f) {
                ToastUtils.m3122a(this.f1888g, (int) R.string.preview_toast_photo_not_exist);
                return;
            }
            int intValue = ((Integer) iVar.f2265b).intValue();
            int i2 = (-16777216 & intValue) >>> 24;
            int i3 = intValue & ViewCompat.MEASURED_SIZE_MASK;
            int i4 = i2 == 255 ? 0 : 1;
            if (i2 == 255) {
                i = StoreUtils.m3113a();
            } else {
                i = i2;
            }
            AppEvent.m3081a(this.f1888g, this.f1892k, "BeginEdit", "section", String.valueOf(i4), "index", String.valueOf(i3 / 10), AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, String.valueOf(i));
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.f1888g) == 0) {
                z = true;
            } else {
                z = false;
            }
            ((PhotoAsset) iVar.f2264a).mo17251a(z ? 256 : 320, (AsyncTask<Bitmap, Void, Float>) new C1517a(((PhotoAsset) iVar.f2264a).mo17248a()));
            Intent intent = new Intent(this.f1889h, EditActivity.class);
            IntentUtils.m3095a(intent, "asset", iVar.f2264a, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, Integer.valueOf(i));
            this.f1889h.startActivity(intent);
        }
    }

    /* renamed from: a */
    public void mo16993a() {
        this.f1890i.mo17293f();
    }

    public int getCount() {
        return this.f1890i.mo17286a();
    }

    public int getViewTypeCount() {
        return this.f1890i.mo17288b() > 0 ? 2 : 1;
    }

    public int getItemViewType(int i) {
        return (i < this.f1890i.mo17290c() ? f1883b : f1884c).intValue();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    /* renamed from: a */
    private void m2621a(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.style_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f1888g, 0, false));
        PreviewStyleAdapter previewStyleAdapter = new PreviewStyleAdapter(this.f1888g, this);
        previewStyleAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ImageListAdapter.this.m2623a((Pair<PhotoAsset, Integer>) (Pair) view.getTag());
            }
        });
        recyclerView.setAdapter(previewStyleAdapter);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (view == null) {
            view = this.f1887f.inflate(new int[]{R.layout.style_list_row, R.layout.preview_row}[itemViewType], viewGroup, false);
            view.setTag(f1884c);
            for (int findViewById : f1882a) {
                View findViewById2 = view.findViewById(findViewById);
                if (findViewById2 != null) {
                    findViewById2.setOnClickListener(this.f1893l);
                }
            }
            if (itemViewType == f1883b.intValue()) {
                m2621a(view);
            }
        } else if (itemViewType == f1883b.intValue() && this.f1890i.mo17291d()) {
            this.f1890i.mo17292e();
            ((RecyclerView) view.findViewById(R.id.style_list_view)).getAdapter().notifyDataSetChanged();
        }
        if (i >= this.f1890i.mo17290c()) {
            view.setLayoutParams(this.f1894m);
            int i2 = this.f1886e;
            PhotoAsset[] b = this.f1890i.mo17289b(i);
            for (int i3 = 0; i3 < b.length; i3++) {
                ImageView imageView = (ImageView) view.findViewById(f1882a[i3]);
                if (imageView != null) {
                    b[i3].mo17252a(this.f1886e, imageView);
                    imageView.setTag(new Pair(b[i3], Integer.valueOf(-16777216 | ((i * 3) + i3))));
                }
            }
        }
        return view;
    }

    /* renamed from: b */
    public int mo16992b() {
        return this.f1890i.mo17288b();
    }

    /* renamed from: a */
    public void mo16991a(PreviewStyleAdapter.PreviewStyleHolder previewStyleHolder, int i) {
        int i2 = new int[]{1, 4, 13, 21, 20, 19}[i];
        StyleObj bVar = StyleObj.f2134h[i2];
        PhotoAsset a = this.f1890i.mo17287a(i);
        previewStyleHolder.itemView.setTag(new Pair(a, Integer.valueOf((i2 << 24) | i)));
        previewStyleHolder.itemView.setId(bVar.f2138a);
        previewStyleHolder.f1880c = bVar.f2138a;
        previewStyleHolder.f1878a.setText(bVar.f2139b);
        previewStyleHolder.f1879b.setImageDrawable(null);
        a.mo17253a(i2, this.f1891j, this.f1885d, (int) R.drawable.sample_thumbnail, previewStyleHolder.f1879b);
    }
}
