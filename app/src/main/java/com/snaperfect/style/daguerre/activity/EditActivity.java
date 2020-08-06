package com.snaperfect.style.daguerre.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentTransaction;
import android.support.p001v4.content.ContextCompat;
import android.support.p001v4.internal.view.SupportMenu;
import android.support.p001v4.view.ViewCompat;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.vision.face.Face;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.p028a.p029a.MobclickAgent;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.crop.CropActivity;
import com.snaperfect.style.daguerre.filter.FilterAsyncTask;
import com.snaperfect.style.daguerre.filter.FilterObj;
import com.snaperfect.style.daguerre.filter.ImageFilterFragment;
import com.snaperfect.style.daguerre.frame.CollageFrame;
import com.snaperfect.style.daguerre.frame.CollageFrameTile;
import com.snaperfect.style.daguerre.frame.FrameFragment;
import com.snaperfect.style.daguerre.math.CGMatrix;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.math.CGRect;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.math.CGVector;
import com.snaperfect.style.daguerre.p031a.Directories;
import com.snaperfect.style.daguerre.retouch.RetouchFragment;
import com.snaperfect.style.daguerre.style.BackgroundInfo;
import com.snaperfect.style.daguerre.style.StyleFragment;
import com.snaperfect.style.daguerre.style.StyleObj;
import com.snaperfect.style.daguerre.text.AndroidBug5497Workaround;
import com.snaperfect.style.daguerre.text.TextFragment;
import com.snaperfect.style.daguerre.text.TextInfo;
import com.snaperfect.style.daguerre.utils.AppEvent;
import com.snaperfect.style.daguerre.utils.BitmapUtils;
import com.snaperfect.style.daguerre.utils.DisplayUtil;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.Localize;
import com.snaperfect.style.daguerre.utils.Pair;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import com.snaperfect.style.daguerre.utils.StoreUtils;
import com.snaperfect.style.daguerre.widget.CanvasLayout;
import com.snaperfect.style.daguerre.widget.GridFreeLayer;
import com.snaperfect.style.daguerre.widget.GridText;
import com.snaperfect.style.daguerre.widget.GridTile;
import com.snaperfect.style.daguerre.widget.GridTileTouchListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;

public class EditActivity extends FragmentActivity implements ImageFilterFragment.C1539a, FrameFragment.C1546a, RetouchFragment.C1552a, StyleFragment.C1556a, TextFragment.C1567a, GridFreeLayer.C1588a, GridTile.C1597a {

    /* renamed from: D */
    private static HashMap<Integer, String> f1743D = new HashMap<Integer, String>() {
        {
            put(Integer.valueOf(R.id.edit_toolbar_style), "Style");
            put(Integer.valueOf(R.id.edit_toolbar_bg), "Background");
            put(Integer.valueOf(R.id.edit_toolbar_filter), "Filter");
            put(Integer.valueOf(R.id.edit_toolbar_crop), "Crop");
            put(Integer.valueOf(R.id.edit_toolbar_text), "Text");
        }
    };

    /* renamed from: b */
    static final /* synthetic */ boolean f1744b;

    /* renamed from: A */
    private LinearLayout f1745A;

    /* renamed from: B */
    private AdView f1746B;
    /* access modifiers changed from: private */

    /* renamed from: C */
    public FirebaseAnalytics f1747C;

    /* renamed from: a */
    ProgressDialog f1748a;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public CanvasLayout canvasLayout;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public ImageView f1750d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public List<GridTile> f1751e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public List<GridFreeLayer> f1752f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public HorizontalScrollView f1753g;

    /* renamed from: h */
    private RelativeLayout f1754h;

    /* renamed from: i */
    private FragmentManager f1755i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public Context f1756j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public GPUImage f1757k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public CGSize f1758l;

    /* renamed from: m */
    private Intent f1759m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public PhotoAsset f1760n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public int f1761o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public StyleObj f1762p;
    /* access modifiers changed from: private */

    public HashMap<String, Object> f1763q;

    private BroadcastReceiver f1764r;

    private Runnable f1765s;

    private GridText f1766t;

    public int f1767u;

    public int f1768v;

    private boolean f1769w = false;

    private boolean f1770x = false;

    public boolean f1771y = false;

    /* renamed from: z */
    private Face[] f1772z = new Face[1];

    /* renamed from: com.snaperfect.style.daguerre.activity.EditActivity$a */
    private class C1496a extends AsyncTask<Object, Integer, PhotoAsset> {

        /* renamed from: b */
        private Throwable f1823b;

        private C1496a() {
        }

        /* renamed from: a */
        private PhotoAsset m2569a(CGSize cGSize, Object... objArr) {
            IOException iOException;
            PhotoAsset photoAsset;
            PhotoAsset photoAsset2 = null;
            StyleObj bVar = objArr[0];
            PhotoAsset[] photoAssetArr = objArr[1];
            CGSize[] a = EditActivity.this.m2477a(objArr[2]);
            Matrix[] a2 = EditActivity.this.m2482b(objArr[3]);
            Pair[] iVarArr = objArr[4];
            int[] iArr = objArr[5];
            TextInfo[] cVarArr = objArr[6];
            GPUImage aVar = objArr[7];
            Paint paint = new Paint(3);
            publishProgress(new Integer[]{1});
            float f = cGSize.f2102a / bVar.f2142e.f2102a;
            for (int i = 0; i < a.length; i++) {
                a[i].mo17152a(f).mo17155b();
                if (a[i].mo17165g()) {
                    this.f1823b = new Exception("invalid image size");
                    return null;
                }
                float[] c = CGMatrix.m2899c(a2[i]);
                CGVector.m2900a(c, f - 1.0f);
                a2[i].postTranslate(c[0], c[1]);
            }
            Bitmap createBitmap = Bitmap.createBitmap((int) cGSize.f2102a, (int) cGSize.f2103b, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            publishProgress(new Integer[]{3});
            StyleObj bVar2 = new StyleObj(bVar, cGSize);
            try {
                EditActivity.this.m2448a(canvas, paint, aVar, photoAssetArr[0], bVar2.f2143f, f);
                publishProgress(new Integer[]{10});
                CollageFrame b = bVar2.mo17203b();
                float a3 = (bVar2.f2144g > 0.0f ? b.mo17115a() : 0.0f) * bVar2.f2144g;
                int b2 = bVar2.mo17202b(false);
                for (int i2 = 0; i2 < photoAssetArr.length; i2++) {
                    EditActivity.this.m2447a(canvas, paint, aVar, photoAssetArr[i2], b.f2083b[i2], a[i2], a2[i2], iVarArr[i2], iArr[i2], b2, a3);
                    publishProgress(new Integer[]{Integer.valueOf((int) (10.0f + (70.0f * ((((float) i2) + 1.0f) / ((float) photoAssetArr.length)))))});
                }
                EditActivity.this.m2446a(canvas, paint, bVar2.mo17200a(false));
                publishProgress(new Integer[]{90});
                TextPaint textPaint = new TextPaint(7);
                for (TextInfo cVar : cVarArr) {
                    EditActivity.this.m2449a(canvas, textPaint, paint, new TextInfo(cVar, f));
                }
                String a4 = Localize.m3098a("output_%d.jpg", Long.valueOf(System.currentTimeMillis()));
                String a5 = Directories.m2434a("result", a4);
                File file = new File(a5);
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                createBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                PhotoAsset photoAsset3 = new PhotoAsset(String.valueOf(a5.hashCode()), a5, a4, new Date().toString());
                try {
                    photoAsset3.mo17256a(cGSize);
                    publishProgress(new Integer[]{100});
                    createBitmap.recycle();
                    return photoAsset3;
                } catch (IOException e) {
                    photoAsset2 = photoAsset3;
                    e = e;
                    iOException = e;
                    photoAsset = photoAsset2;
                    try {
                        this.f1823b = iOException;
                        return photoAsset;
                    } finally {
                        createBitmap.recycle();
                    }
                } catch (IllegalStateException e2) {
                    photoAsset2 = photoAsset3;
                    photoAsset = photoAsset2;
                    return photoAsset;
                }
            } catch (IOException e3) {
                photoAsset = photoAsset2;
                return photoAsset;
            } catch (IllegalStateException e4) {
                photoAsset = photoAsset2;
                return photoAsset;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public PhotoAsset doInBackground(Object... objArr) {
            CGSize a = EditActivity.this.m2519t().mo17151a();
            try {
                return m2569a(a, objArr);
            } catch (OutOfMemoryError e) {
                System.gc();
                System.gc();
                a.mo17156b(2.0f).mo17151a();
                return m2569a(a, objArr);
            } catch (Throwable th) {
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            EditActivity.this.m2498i();
            EditActivity.this.m2493g((int) R.string.loading);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onProgressUpdate(Integer... numArr) {
            if (EditActivity.this.f1748a != null && EditActivity.this.f1748a.isShowing()) {
                String string = EditActivity.this.f1756j.getString(R.string.loading);
                EditActivity.this.f1748a.setMessage(Localize.m3098a("%s %d%%", string, numArr[0]));
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(PhotoAsset photoAsset) {
            EditActivity.this.m2492g();
            Context a = EditActivity.this.f1756j;
            FirebaseAnalytics b = EditActivity.this.f1747C;
            String[] strArr = new String[2];
            strArr[0] = "result";
            strArr[1] = String.valueOf(photoAsset != null);
            AppEvent.m3081a(a, b, "SaveResult", strArr);
            if (photoAsset != null) {
                boolean unused = EditActivity.this.f1771y = true;
                EditActivity.this.m2524v();
                Intent intent = new Intent(EditActivity.this, ShareActivity.class);
                IntentUtils.m3095a(intent, "asset", photoAsset);
                EditActivity.this.startActivity(intent);
                return;
            }
            EditActivity.this.mo16901a((int) R.string.dialog_title_save_error, (int) R.string.dialog_msg_save_error, (DialogInterface.OnDismissListener) null);
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.activity.EditActivity$b */
    private interface C1497b {
        /* renamed from: a */
        void mo16932a();
    }

    static {
        boolean z;
        if (!EditActivity.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        f1744b = z;
    }

    /* renamed from: a */
    public GPUImage mo16899a() {
        return this.f1757k;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setSoftInputMode(16);
        this.f1747C = FirebaseAnalytics.getInstance(this);
        this.f1756j = getApplicationContext();
        this.f1758l = ScreenInfo.m3112a(this.f1756j);
        this.f1757k = new GPUImage(this.f1756j);
        this.f1751e = new ArrayList();
        this.f1752f = new ArrayList();
        this.f1763q = new HashMap<>();
        CollageFrame.m2812a(this.f1756j.getResources());
        mo16908b();
        setContentView(R.layout.activity_edit);
        this.canvasLayout = (CanvasLayout) findViewById(R.id.collage_canvas);
        ViewGroup.LayoutParams layoutParams = this.canvasLayout.getLayoutParams();
        this.canvasLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        layoutParams.width = -1;
        layoutParams.height = (int) this.f1758l.f2102a;
        this.f1745A = (LinearLayout) findViewById(R.id.edit_ad_panel);
        this.f1746B = (AdView) findViewById(R.id.ad_view);
        this.f1746B.loadAd(new AdRequest.Builder().build());
        this.f1746B.setAdListener(new AdListener() {
            public void onAdLoaded() {
                super.onAdLoaded();
                AppEvent.m3081a(EditActivity.this.f1756j, EditActivity.this.f1747C, "AdShow", "result", AppEventsConstants.EVENT_PARAM_VALUE_YES, ShareConstants.MEDIA_TYPE, "banner", "vender", "ADMob");
            }

            public void onAdFailedToLoad(int i) {
                AppEvent.m3081a(EditActivity.this.f1756j, EditActivity.this.f1747C, "AdShow", "result", AppEventsConstants.EVENT_PARAM_VALUE_NO, ShareConstants.MEDIA_TYPE, "banner", "vender", "ADMob");
            }
        });
        this.f1755i = getSupportFragmentManager();
        Log.d("EditActivity", "" + DisplayUtil.m3087a(1.0f));
        m2488f();
    }

    /* renamed from: e */
    private void m2486e() {
        this.canvasLayout = null;
        this.f1750d = null;
        this.f1751e = null;
        this.f1752f = null;
        if (this.f1753g != null) {
            this.f1753g.removeAllViews();
        }
        this.f1753g = null;
        this.f1754h = null;
        this.f1756j = null;
        this.f1757k = null;
        this.f1758l = null;
        this.f1759m = null;
        this.f1760n = null;
        this.f1762p = null;
        this.f1763q = null;
        this.f1766t = null;
        this.f1755i = null;
        this.f1748a = null;
        this.f1745A = null;
        this.f1746B = null;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.edit_top_scrollviw);
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
    }

    public void onStart() {
        super.onStart();
        if (this.f1771y) {
            this.f1771y = false;
            m2453a((C1497b) new C1497b() {
                /* renamed from: a */
                public void mo16932a() {
                    EditActivity.this.canvasLayout.setVisibility(0);
                }
            });
        }
    }

    /* renamed from: f */
    private void m2489f(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo16908b() {
        m2489f(ContextCompat.getColor(this.f1756j, R.color.mt_color));
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    /* renamed from: f */
    private void m2488f() {
        m2500j();
        m2502k();
        m2504l();
        m2465a(this.f1762p);
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m2493g(int i) {
        if (this.f1748a == null) {
            this.f1748a = new ProgressDialog(this);
            this.f1748a.setCancelable(false);
        }
        this.f1748a.setMessage(this.f1756j.getString(i));
        this.f1748a.show();
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m2492g() {
        if (this.f1748a != null) {
            this.f1748a.dismiss();
        }
    }

    /* renamed from: f */
    private void m2490f(GridFreeLayer gridFreeLayer) {
        Iterator<GridFreeLayer> it = this.f1752f.iterator();
        while (it.hasNext()) {
            GridFreeLayer next = it.next();
            next.setVisibility(next == gridFreeLayer ? 0 : 8);
        }
    }

    /* renamed from: h */
    private void m2494h() {
        for (GridFreeLayer visibility : this.f1752f) {
            visibility.setVisibility(0);
        }
    }

    /* renamed from: a */
    public void mo16901a(@StringRes int i, @StringRes int i2, DialogInterface.OnDismissListener onDismissListener) {
        AlertDialog create = new AlertDialog.Builder(this).setTitle(i).setMessage(i2).setNegativeButton(R.string.dialog_button_confirm, null).create();
        create.setOnDismissListener(onDismissListener);
        create.show();
    }

    /* renamed from: a */
    private void m2465a(StyleObj bVar) {
        m2493g((int) R.string.edit_process_msg);
        final CollageFrame b = this.f1762p.mo17203b();
        this.f1767u = 0;
        this.f1768v = 0;
        for (CollageFrameTile a : b.f2083b) {
            m2463a(a, this.f1760n, (GridTile.C1598b) new GridTile.C1598b() {
                /* renamed from: a */
                public void mo16930a(boolean z, GridTile gridTile) {
                    int unused = EditActivity.this.f1767u = EditActivity.this.f1767u + 1;
                    int unused2 = EditActivity.this.f1767u = (z ? 0 : 1) + EditActivity.this.f1767u;
                    if (EditActivity.this.f1767u != b.f2083b.length) {
                        return;
                    }
                    if (EditActivity.this.f1768v == 0) {
                        EditActivity.this.m2466a(EditActivity.this.f1762p, (C1497b) new C1497b() {
                            /* renamed from: a */
                            public void mo16932a() {
                                EditActivity.this.m2492g();
                            }
                        });
                        return;
                    }
                    EditActivity.this.m2492g();
                    EditActivity.this.mo16901a((int) R.string.dialog_title_image_load_error, (int) R.string.dialog_msg_image_load_error, (DialogInterface.OnDismissListener) new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialogInterface) {
                            EditActivity.this.finish();
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2467a(StyleObj bVar, PhotoAsset photoAsset, Bitmap bitmap) {
        int i = 1;
        final BackgroundInfo backgroundInfo = bVar.f2143f;
        this.canvasLayout.setTag(backgroundInfo);
        switch (bVar.f2143f.f2114b) {
            case 0:
                this.canvasLayout.setBackgroundColor(bVar.f2143f.mo17179c());
                return;
            case 1:
                m2450a((Drawable) new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{bVar.f2143f.mo17180d(), bVar.f2143f.mo17182e()}));
                return;
            case 2:
                final int h = bVar.f2143f.mo17186h();
                final CanvasLayout canvasLayout = this.canvasLayout;
                final GPUImage aVar = this.f1757k;
                float f = this.f1758l.f2102a;
                if (h != 0) {
                    i = 4;
                }
                int round = Math.round(f / ((float) i));
                int i2 = bVar.f2143f.mo17187i();
                this.f1760n.mo17257a(aVar, round, i2, 0, (PhotoAsset.C1581d) new PhotoAsset.C1581d() {
                    /* renamed from: a */
                    public void mo16936a(@Nullable Bitmap bitmap) {
                        if (bitmap == null || canvasLayout.getWidth() == 0 || canvasLayout.getHeight() == 0) {
                            canvasLayout.setBackgroundColor(-1);
                        } else if (h == 0) {
                            CGRect cGRect = new CGRect(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
                            cGRect.mo17134a((float) canvasLayout.getWidth(), (float) canvasLayout.getHeight()).mo17133a();
                            EditActivity.this.m2464a(backgroundInfo, BitmapUtils.m3083a(bitmap, cGRect));
                        } else {
                            GPUImageGaussianBlurFilter jVar = new GPUImageGaussianBlurFilter((float) h);
                            new FilterAsyncTask().execute(new Object[]{bitmap, jVar, aVar, new PhotoAsset.C1581d() {
                                /* renamed from: a */
                                public void mo16936a(@Nullable Bitmap bitmap) {
                                    if (bitmap == null || canvasLayout.getWidth() == 0 || canvasLayout.getHeight() == 0) {
                                        canvasLayout.setBackgroundColor(-1);
                                        return;
                                    }
                                    CGRect cGRect = new CGRect(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
                                    cGRect.mo17134a((float) canvasLayout.getWidth(), (float) canvasLayout.getHeight()).mo17133a();
                                    EditActivity.this.m2464a(backgroundInfo, BitmapUtils.m3083a(bitmap, cGRect));
                                }
                            }});
                        }
                    }
                });
                return;
            case 3:
                if (bitmap != null) {
                    m2464a(backgroundInfo, bitmap);
                    return;
                } else {
                    PhotoAsset.m3011a(this.f1756j, (int) this.f1758l.f2102a, bVar.f2143f.mo17184f(), (PhotoAsset.C1581d) new PhotoAsset.C1581d() {
                        /* renamed from: a */
                        public void mo16936a(@Nullable Bitmap bitmap) {
                            EditActivity.this.m2464a(backgroundInfo, bitmap);
                        }
                    });
                    return;
                }
            default:
                if (!f1744b) {
                    throw new AssertionError();
                }
                return;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2464a(BackgroundInfo backgroundInfo, Bitmap bitmap) {
        Drawable bitmapDrawable;
        if (this.canvasLayout != null && this.canvasLayout.getTag() == backgroundInfo) {
            if (bitmap == null) {
                bitmapDrawable = new ColorDrawable(-1);
            } else {
                bitmapDrawable = new BitmapDrawable(this.f1756j.getResources(), bitmap);
            }
            m2450a(bitmapDrawable);
        }
    }

    /* renamed from: a */
    private void m2450a(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.canvasLayout.setBackground(drawable);
        } else {
            this.canvasLayout.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    private void m2463a(CollageFrameTile dVar, PhotoAsset photoAsset, GridTile.C1598b bVar) {
        GridTile gridTile = new GridTile(this.f1756j);
        gridTile.setCallback(this);
        gridTile.setOnTouchListener(new GridTileTouchListener(this, gridTile));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) dVar.f2091f.f2100c, (int) dVar.f2091f.f2101d);
        layoutParams.setMargins((int) dVar.f2091f.f2098a, (int) dVar.f2091f.f2099b, 0, 0);
        gridTile.setLayoutParams(layoutParams);
        gridTile.setId(R.id.adjust_img);
        gridTile.setScaleType(ImageView.ScaleType.MATRIX);
        this.f1751e.add(gridTile);
        this.canvasLayout.addView(gridTile);
        gridTile.mo17352a(dVar, photoAsset, bVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2466a(StyleObj bVar, C1497b bVar2) {
        float f = 0.0f;
        boolean z = false;
        this.f1762p = bVar;
        boolean z2 = this.f1748a != null && this.f1748a.isShowing();
        if (!z2) {
            m2493g((int) R.string.edit_process_msg);
        }
        CollageFrame b = this.f1762p.mo17203b();
        if (this.f1762p.f2144g > 0.0f) {
            f = b.mo17115a();
        }
        float f2 = f * this.f1762p.f2144g;
        List<GridTile> list = this.f1751e;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                GridTile gridTile = list.get(i);
                CollageFrameTile dVar = b.f2083b[i];
                if (gridTile.getTile().f2092g == dVar.f2092g || (!gridTile.getTile().mo17118a() && !dVar.mo17118a())) {
                    gridTile.setTile(b.f2083b[i]);
                    gridTile.setCornerRadius(f2);
                } else {
                    gridTile.mo17352a(dVar, gridTile.getAsset(), null);
                }
            }
            if (!z2) {
                z = true;
            }
            m2468a(z, bVar2);
        }
    }

    /* renamed from: a */
    private void m2453a(final C1497b bVar) {
        int i = 0;
        m2493g((int) R.string.edit_process_msg);
        this.f1767u = 0;
        this.f1768v = 0;
        final List<GridTile> list = this.f1751e;
        if (list != null) {
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    list.get(i2).mo17353a((GridTile.C1598b) new GridTile.C1598b() {
                        /* renamed from: a */
                        public void mo16930a(boolean z, GridTile gridTile) {
                            int unused = EditActivity.this.f1767u = EditActivity.this.f1767u + 1;
                            if (!z) {
                                int unused2 = EditActivity.this.f1768v = EditActivity.this.f1768v + 1;
                            }
                            if (EditActivity.this.f1767u != list.size()) {
                                return;
                            }
                            if (EditActivity.this.f1768v == 0) {
                                EditActivity.this.m2468a(true, bVar);
                                return;
                            }
                            EditActivity.this.m2492g();
                            EditActivity.this.mo16901a((int) R.string.dialog_title_image_load_error, (int) R.string.dialog_msg_image_load_error, (DialogInterface.OnDismissListener) new DialogInterface.OnDismissListener() {
                                public void onDismiss(DialogInterface dialogInterface) {
                                    EditActivity.this.finish();
                                }
                            });
                        }
                    });
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2468a(final boolean z, final C1497b bVar) {
        PhotoAsset.m3012a(this.f1756j, (int) this.f1758l.f2102a, new int[]{this.f1762p.mo17200a(false), this.f1762p.mo17202b(false), this.f1762p.f2143f.mo17184f()}, (PhotoAsset.C1583f) new PhotoAsset.C1583f() {
            /* renamed from: a */
            public void mo16938a(Bitmap[] bitmapArr) {
                if (z && EditActivity.this.f1748a != null) {
                    EditActivity.this.m2492g();
                }
                Bitmap bitmap = bitmapArr[0];
                Bitmap bitmap2 = bitmapArr[1];
                Bitmap bitmap3 = bitmapArr[2];
                if (bitmap != null && !bitmap.isRecycled() && EditActivity.this.f1750d == null) {
                    ImageView unused = EditActivity.this.f1750d = new ImageView(EditActivity.this.f1756j);
                    EditActivity.this.f1750d.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                    EditActivity.this.f1750d.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    EditActivity.this.canvasLayout.addView(EditActivity.this.f1750d);
                }
                if (EditActivity.this.f1750d != null) {
                    EditActivity.this.f1750d.setImageBitmap(bitmap);
                }
                ((GridTile) EditActivity.this.f1751e.get(0)).setMaskBitmap(bitmap2);
                EditActivity.this.m2467a(EditActivity.this.f1762p, EditActivity.this.f1760n, bitmap3);
                if (bVar != null) {
                    bVar.mo16932a();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m2498i() {
        this.canvasLayout.setVisibility(8);
        m2450a((Drawable) null);
        if (this.f1750d != null) {
            this.f1750d.setImageDrawable(null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f1751e.size()) {
                this.f1751e.get(i2).mo17351a();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* renamed from: j */
    private void m2500j() {
        this.f1759m = getIntent();
        if (this.f1759m != null) {
            this.f1760n = (PhotoAsset) IntentUtils.m3093a(this.f1759m, "asset", PhotoAsset.CREATOR);
            this.f1761o = Math.min(IntentUtils.m3092a(this.f1759m, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, 0), StyleObj.f2134h.length - 1);
            this.f1762p = new StyleObj(StyleObj.f2134h[this.f1761o], new CGSize(this.f1758l.f2102a));
            this.f1759m = null;
        }
    }

    /* renamed from: k */
    private void m2502k() {
        findViewById(R.id.edit_title_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = EditActivity.this.m2517s();
            }
        });
        findViewById(R.id.edit_title_save).setOnClickListener(new View.OnClickListener() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.Object[]} */
            /* JADX WARNING: Multi-variable type inference failed */
            public void onClick(View view) {
                String str;
                Context a = EditActivity.this.f1756j;
                FirebaseAnalytics b = EditActivity.this.f1747C;
                String[] strArr = new String[12];
                strArr[0] = AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE;
                strArr[1] = String.valueOf(EditActivity.this.f1761o);
                strArr[2] = "background";
                strArr[3] = String.valueOf(EditActivity.this.f1762p.f2143f.f2114b);
                strArr[4] = "filter";
                strArr[5] = String.valueOf(((GridTile) EditActivity.this.f1751e.get(0)).getFilterIndex());
                strArr[6] = "crop";
                strArr[7] = String.valueOf(EditActivity.this.f1760n.mo17264e() != null);
                strArr[8] = "text";
                strArr[9] = String.valueOf(EditActivity.this.f1752f.size());
                strArr[10] = "retouch";
                if (EditActivity.this.f1760n.mo17262d() > 0.0f) {
                    str = ((GridTile) EditActivity.this.f1751e.get(0)).getRetouch() > 0 ? "2" : AppEventsConstants.EVENT_PARAM_VALUE_YES;
                } else {
                    str = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                strArr[11] = str;
                AppEvent.m3081a(a, b, "SaveImage", strArr);
                new C1496a().execute(new Object[]{EditActivity.this.f1762p, new PhotoAsset[]{EditActivity.this.f1760n}, EditActivity.this.m2506m(), EditActivity.this.m2508n(), EditActivity.this.m2510o(), EditActivity.this.m2513q(), EditActivity.this.m2512p(), EditActivity.this.f1757k});
            }
        });
    }

    /* renamed from: l */
    private void m2504l() {
        this.f1754h = (RelativeLayout) findViewById(R.id.edit_title_bar);
        this.f1753g = (HorizontalScrollView) findViewById(R.id.edit_scrollbar);
        this.f1764r = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                float floatExtra = intent.getFloatExtra("face", 0.0f);
                if (EditActivity.this.f1760n.mo17248a().equals(intent.getStringExtra("asset_id")) && floatExtra > 0.0f) {
                    EditActivity.this.f1760n.mo17249a(floatExtra);
                    LinearLayout linearLayout = (LinearLayout) EditActivity.this.f1753g.findViewById(R.id.edit_toolbar);
                    int round = (int) Math.round(((double) EditActivity.this.f1758l.f2102a) / 5.5d);
                    for (int i = 0; i < linearLayout.getChildCount(); i++) {
                        linearLayout.getChildAt(i).getLayoutParams().width = round;
                    }
                    linearLayout.setWeightSum(6.0f);
                    linearLayout.getLayoutParams().width = round * 6;
                    EditActivity.this.f1753g.getLayoutParams().width = round * 6;
                    linearLayout.requestLayout();
                    EditActivity.this.f1753g.requestLayout();
                    EditActivity.this.findViewById(R.id.edit_top_scrollviw).requestLayout();
                }
            }
        };
        registerReceiver(this.f1764r, new IntentFilter("action_face"));
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public CGSize[] m2506m() {
        CGSize[] cGSizeArr = new CGSize[this.f1751e.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f1751e.size()) {
                return cGSizeArr;
            }
            cGSizeArr[i2] = this.f1751e.get(i2).getActualImageSize();
            i = i2 + 1;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: n */
    public Matrix[] m2508n() {
        Matrix[] matrixArr = new Matrix[this.f1751e.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f1751e.size()) {
                return matrixArr;
            }
            matrixArr[i2] = new Matrix(this.f1751e.get(i2).getImageMatrix());
            i = i2 + 1;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2470a(Matrix[] matrixArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f1751e.size()) {
                this.f1751e.get(i2).setImageMatrix(matrixArr[i2]);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: o */
    public Pair<Integer, Float>[] m2510o() {
        Pair<Integer, Float>[] iVarArr = new Pair[this.f1751e.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f1751e.size()) {
                return iVarArr;
            }
            GridTile gridTile = this.f1751e.get(i2);
            iVarArr[i2] = new Pair<>(Integer.valueOf(gridTile.getFilterIndex()), Float.valueOf(gridTile.getFilterMix()));
            i = i2 + 1;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: p */
    public TextInfo[] m2512p() {
        ArrayList arrayList = new ArrayList();
        for (GridFreeLayer next : this.f1752f) {
            if (next instanceof GridText) {
                GridText gridText = (GridText) next;
                TextInfo cVar = new TextInfo(gridText.getTextInfo(), 1.0f);
                cVar.mo17229a(gridText.getCenter(), gridText.getSize(), gridText.getRotation(), gridText.getText());
                arrayList.add(cVar);
            }
        }
        return (TextInfo[]) arrayList.toArray(new TextInfo[0]);
    }

    /* access modifiers changed from: private */
    /* renamed from: q */
    public int[] m2513q() {
        int i = 0;
        List<GridTile> list = this.f1751e;
        if (list == null) {
            return new int[0];
        }
        int[] iArr = new int[list.size()];
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return iArr;
            }
            iArr[i2] = list.get(i2).getRetouch();
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private boolean m2474a(Pair<Integer, Float>[] iVarArr) {
        List<GridTile> list = this.f1751e;
        if (list == null) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            GridTile gridTile = list.get(i);
            if (gridTile.getFilterIndex() != ((Integer) iVarArr[i].f2264a).intValue() || gridTile.getFilterMix() != ((Float) iVarArr[i].f2265b).floatValue()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private boolean m2473a(int[] iArr) {
        List<GridTile> list = this.f1751e;
        if (list == null) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRetouch() != iArr[i]) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private void m2471a(Pair<Integer, Float>[] iVarArr, final GridTile.C1598b bVar) {
        List<GridTile> list = this.f1751e;
        if (list != null) {
            final ArrayList<GridTile> arrayList = new ArrayList<>(iVarArr.length);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= list.size()) {
                    break;
                }
                GridTile gridTile = list.get(i2);
                if (gridTile.getFilterIndex() != ((Integer) iVarArr[i2].f2264a).intValue() || gridTile.getFilterMix() != ((Float) iVarArr[i2].f2265b).floatValue()) {
                    gridTile.setFilterIndex(((Integer) iVarArr[i2].f2264a).intValue());
                    gridTile.setFilterMix(((Float) iVarArr[i2].f2265b).floatValue());
                    arrayList.add(gridTile);
                }
                i = i2 + 1;
            }
            final boolean[] zArr = new boolean[arrayList.size()];
            for (GridTile a : arrayList) {
                a.mo17353a((GridTile.C1598b) new GridTile.C1598b() {
                    /* renamed from: a */
                    public void mo16930a(boolean z, GridTile gridTile) {
                        zArr[arrayList.indexOf(gridTile)] = true;
                        boolean z2 = true;
                        for (boolean z3 : zArr) {
                            if (!z3) {
                                z2 = false;
                            }
                        }
                        if (z2) {
                            bVar.mo16930a(true, null);
                        }
                    }
                });
            }
        }
    }

    /* renamed from: a */
    private void m2469a(int[] iArr, final GridTile.C1598b bVar) {
        List<GridTile> list = this.f1751e;
        if (list != null) {
            final ArrayList<GridTile> arrayList = new ArrayList<>(iArr.length);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= list.size()) {
                    break;
                }
                GridTile gridTile = list.get(i2);
                if (gridTile.getRetouch() != iArr[i2]) {
                    gridTile.setRetouch(iArr[i2]);
                    arrayList.add(gridTile);
                }
                i = i2 + 1;
            }
            final boolean[] zArr = new boolean[arrayList.size()];
            for (GridTile a : arrayList) {
                a.mo17353a((GridTile.C1598b) new GridTile.C1598b() {
                    /* renamed from: a */
                    public void mo16930a(boolean z, GridTile gridTile) {
                        zArr[arrayList.indexOf(gridTile)] = true;
                        boolean z2 = true;
                        for (boolean z3 : zArr) {
                            if (!z3) {
                                z2 = false;
                            }
                        }
                        if (z2) {
                            bVar.mo16930a(true, null);
                        }
                    }
                });
            }
        }
    }

    /* renamed from: r */
    private void m2515r() {
        this.f1753g.setVisibility(8);
        this.f1754h.setVisibility(8);
        this.f1763q.clear();
    }

    /* renamed from: h */
    private void m2495h(int i) {
        AppEvent.m3081a(this.f1756j, this.f1747C, "MenuTap", "menu", f1743D.containsKey(Integer.valueOf(i)) ? f1743D.get(Integer.valueOf(i)) : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
    }

    public void onStyleButtonClick(View view) {
        m2515r();
        m2495h(view.getId());
        this.f1763q.put(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, this.f1762p);
        this.f1763q.put("matrix", m2508n());
        final StyleFragment styleFragment = new StyleFragment();
        Bundle bundle = new Bundle();
        IntentUtils.m3096a(bundle, "asset", this.f1760n, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, Integer.valueOf(this.f1761o));
        styleFragment.setArguments(bundle);
        this.f1765s = new Runnable() {
            public void run() {
                styleFragment.getView().setVisibility(8);
                EditActivity.this.mo16904a(true);
            }
        };
        m2451a((Fragment) styleFragment);
    }

    public void onBackgroundButtonClick(View view) {
        m2515r();
        m2495h(view.getId());
        this.f1763q.put("background", this.f1762p.f2143f);
        final FrameFragment frameFragment = new FrameFragment();
        Bundle bundle = new Bundle();
        IntentUtils.m3096a(bundle, "background", this.f1762p.f2143f);
        frameFragment.setArguments(bundle);
        this.f1765s = new Runnable() {
            public void run() {
                frameFragment.getView().setVisibility(8);
                EditActivity.this.mo16910b(true);
            }
        };
        m2451a((Fragment) frameFragment);
    }

    public void onFilterButtonClick(View view) {
        m2515r();
        m2495h(view.getId());
        this.f1763q.put("filter", m2510o());
        final ImageFilterFragment imageFilterFragment = new ImageFilterFragment();
        Bundle bundle = new Bundle();
        GridTile gridTile = this.f1751e.get(0);
        IntentUtils.m3096a(bundle, "asset", this.f1760n, "filter", Integer.valueOf(gridTile.getFilterIndex()), "mix", Float.valueOf(gridTile.getFilterMix()));
        imageFilterFragment.setArguments(bundle);
        this.f1765s = new Runnable() {
            public void run() {
                imageFilterFragment.getView().setVisibility(8);
                EditActivity.this.mo16914c(true);
            }
        };
        m2451a((Fragment) imageFilterFragment);
    }

    public void onCropButtonClick(View view) {
        m2495h(view.getId());
        Intent intent = new Intent(this, CropActivity.class);
        IntentUtils.m3095a(intent, "asset", this.f1760n);
        startActivityForResult(intent, 100);
    }

    public void onTextButtonClick(View view) {
        m2495h(view.getId());
        GridText gridText = new GridText(this.f1756j);
        gridText.setLayerCallback(this);
        gridText.mo17326a("W");
        this.f1752f.add(gridText);
        this.canvasLayout.addView(gridText);
        this.f1766t = gridText;
        AndroidBug5497Workaround.m2973a((Activity) this);
        getWindow().setSoftInputMode(16);
        mo16902a((GridFreeLayer) this.f1766t);
        this.f1766t.mo17310b();
    }

    public void onRetouchButtonClick(View view) {
        m2515r();
        m2495h(view.getId());
        this.f1763q.put("retouch", m2513q());
        final RetouchFragment retouchFragment = new RetouchFragment();
        Bundle bundle = new Bundle();
        GridTile gridTile = this.f1751e.get(0);
        if (gridTile.getRetouch() == 0) {
            mo16919e(50);
        }
        IntentUtils.m3096a(bundle, "asset", this.f1760n, "retouch", Integer.valueOf(gridTile.getRetouch()));
        retouchFragment.setArguments(bundle);
        this.f1765s = new Runnable() {
            public void run() {
                retouchFragment.getView().setVisibility(8);
                EditActivity.this.mo16921e(true);
            }
        };
        m2451a((Fragment) retouchFragment);
    }

    /* renamed from: a */
    private void m2451a(Fragment fragment) {
        FragmentTransaction beginTransaction = this.f1755i.beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
        beginTransaction.replace(R.id.edit_effect_frame, fragment);
        beginTransaction.commit();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == 101) {
            PhotoAsset photoAsset = (PhotoAsset) IntentUtils.m3093a(intent, "asset", PhotoAsset.CREATOR);
            if (photoAsset.mo17264e() != null && !photoAsset.mo17264e().equals(this.f1760n.mo17264e())) {
                this.f1769w = true;
                this.f1760n.mo17255a(photoAsset.mo17264e());
                this.f1760n.mo17250a(photoAsset.mo17261c());
                m2493g((int) R.string.loading);
                GridTile gridTile = this.f1751e.get(0);
                m2467a(this.f1762p, this.f1760n, (Bitmap) null);
                gridTile.mo17352a(gridTile.getTile(), gridTile.getAsset(), new GridTile.C1598b() {
                    /* renamed from: a */
                    public void mo16930a(boolean z, GridTile gridTile) {
                        EditActivity.this.m2492g();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("EditActivity", "on new intent");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f1746B != null) {
            this.f1746B.resume();
        }
        Log.i("EditActivity", "onResume");
        MobclickAgent.m2413b(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.f1746B != null) {
            this.f1746B.pause();
        }
        super.onPause();
        MobclickAgent.m2410a(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        m2498i();
        unregisterReceiver(this.f1764r);
        this.f1764r = null;
        if (this.f1746B != null) {
            this.f1746B.pause();
            this.f1746B.setAdListener(null);
            this.f1745A.removeAllViews();
            this.f1746B.destroy();
        }
        if (this.f1748a != null) {
            this.f1748a.dismiss();
        }
        m2486e();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.i("EditActivity", "save the state");
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        Log.i("EditActivity", "restore the state");
    }

    /* access modifiers changed from: private */
    /* renamed from: s */
    public boolean m2517s() {
        if (this.f1769w) {
            new AlertDialog.Builder(this).setTitle(this.f1756j.getString(R.string.edit_dialog_title)).setMessage(this.f1756j.getString(R.string.edit_dialog_msg)).setPositiveButton(R.string.dialog_button_confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditActivity.this.finish();
                }
            }).setNegativeButton(R.string.dialog_button_cancel, null).create().show();
        } else {
            finish();
        }
        return false;
    }

    /* renamed from: a */
    private void m2452a(View view) {
        this.f1753g.setVisibility(0);
        this.f1754h.setVisibility(0);
        this.f1765s = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: t */
    public CGSize m2519t() {
        return CGSize.m2874c(this.f1762p.f2142e, new CGSize(this.f1758l.f2102a > 720.0f ? 2048.0f : 1024.0f));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2448a(Canvas canvas, Paint paint, GPUImage aVar, PhotoAsset photoAsset, BackgroundInfo backgroundInfo, float f) {
        int saveCount = canvas.getSaveCount();
        canvas.save();
        switch (backgroundInfo.f2114b) {
            case 0:
                canvas.drawColor(backgroundInfo.mo17179c());
                break;
            case 1:
                RectF rectF = new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
                paint.setShader(new LinearGradient(0.0f, 0.0f, rectF.width(), rectF.height(), backgroundInfo.mo17180d(), backgroundInfo.mo17182e(), Shader.TileMode.CLAMP));
                canvas.drawRect(rectF, paint);
                paint.setShader(null);
                break;
            case 2:
                int round = Math.round(((float) backgroundInfo.mo17186h()) * f);
                int round2 = Math.round((float) (canvas.getWidth() / (round == 0 ? 1 : 4)));
                int i = backgroundInfo.mo17187i();
                Bitmap b = photoAsset.mo17258b(round2);
                if (b != null && (round > 1 || i > 0)) {
                    GPUImageFilterGroup hVar = new GPUImageFilterGroup();
                    if (i > 0) {
                        hVar.mo17859a((GPUImageFilter) FilterObj.f2048a[i].mo17103a(this.f1756j));
                    }
                    if (round > 1) {
                        hVar.mo17859a((GPUImageFilter) new GPUImageGaussianBlurFilter((float) round));
                    }
                    aVar.mo17789a((GPUImageFilter) hVar);
                    b = aVar.mo17790b(b);
                }
                if (b != null) {
                    RectF rectF2 = new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
                    CGRect cGRect = new CGRect(0.0f, 0.0f, (float) b.getWidth(), (float) b.getHeight());
                    cGRect.mo17134a((float) canvas.getWidth(), (float) canvas.getHeight()).mo17133a();
                    canvas.drawBitmap(b, cGRect.mo17144f(), rectF2, paint);
                    break;
                }
                break;
            case 3:
                Bitmap a = PhotoAsset.m3002a(this.f1756j, canvas.getWidth(), backgroundInfo.mo17184f());
                canvas.drawBitmap(a, new Rect(0, 0, a.getWidth(), a.getHeight()), new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), paint);
                break;
            default:
                if (!f1744b) {
                    throw new AssertionError();
                }
                break;
        }
        canvas.restoreToCount(saveCount);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2447a(Canvas canvas, Paint paint, GPUImage aVar, PhotoAsset photoAsset, CollageFrameTile dVar, CGSize cGSize, Matrix matrix, Pair<Integer, Float> iVar, int i, int i2, float f) {
        Bitmap bitmap;
        int saveCount = canvas.getSaveCount();
        canvas.save();
        canvas.translate(dVar.f2091f.f2098a, dVar.f2091f.f2099b);
        canvas.clipPath(dVar.mo17117a(f, 0));
        Bitmap b = photoAsset.mo17258b(Math.round(cGSize.mo17163e()));
        if (b == null) {
            throw new IllegalStateException("Can not read image");
        }
        float width = CGSize.m2874c(photoAsset.mo17266g(), new CGSize((float) canvas.getWidth(), (float) canvas.getHeight())).f2102a / ((float) b.getWidth());
        matrix.preScale(width, width);
        boolean z = dVar.f2092g == 2;
        if (((Integer) iVar.f2264a).intValue() > 0 || z || i > 0) {
            aVar.mo17789a((GPUImageFilter) PhotoAsset.m3008a(this.f1756j, cGSize, z, ((Integer) iVar.f2264a).intValue(), i, Math.round(((float) Math.min(b.getWidth(), b.getHeight())) * photoAsset.mo17262d() * 0.0667f)));
            bitmap = aVar.mo17790b(b);
            if (bitmap == null) {
                throw new IllegalStateException("Filted image can not be null");
            }
        } else {
            bitmap = b;
        }
        if (i2 != 0) {
            Bitmap a = PhotoAsset.m3002a(this.f1756j, canvas.getWidth(), i2);
            Rect rect = new Rect(0, 0, a.getWidth(), a.getHeight());
            RectF rectF = new RectF(0.0f, 0.0f, dVar.f2091f.f2100c, dVar.f2091f.f2101d);
            paint.setXfermode(GridTile.f2308a);
            canvas.drawBitmap(a, rect, rectF, paint);
            paint.setXfermode(GridTile.f2309b);
            canvas.drawBitmap(bitmap, matrix, paint);
            paint.setXfermode(null);
        } else {
            canvas.drawBitmap(bitmap, matrix, paint);
        }
        canvas.restoreToCount(saveCount);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2449a(Canvas canvas, TextPaint textPaint, Paint paint, TextInfo cVar) {
        int saveCount = canvas.getSaveCount();
        canvas.save();
        CGPoint b = new CGPoint(cVar.mo17242l()).mo17126b(-2.0f);
        canvas.translate(cVar.mo17241k().x, cVar.mo17241k().y);
        canvas.rotate(cVar.mo17243m());
        canvas.translate(b.x, b.y);
        textPaint.setTypeface(cVar.mo17223a());
        textPaint.setTextSize(cVar.mo17232c());
        textPaint.setColor(cVar.mo17235e());
        if (cVar.mo17237g() > 0.0f) {
            textPaint.setShadowLayer(cVar.mo17237g(), cVar.mo17238h(), cVar.mo17239i(), Integer.MIN_VALUE);
        } else {
            textPaint.clearShadowLayer();
        }
        int round = Math.round(cVar.mo17242l().f2102a);
        StaticLayout staticLayout = new StaticLayout(cVar.mo17244n(), textPaint, round, cVar.mo17234d(), 1.0f, 0.0f, true);
        if (cVar.mo17240j() == 1.0f) {
            staticLayout.draw(canvas);
        } else {
            Bitmap createBitmap = Bitmap.createBitmap(round, staticLayout.getHeight(), Bitmap.Config.ARGB_8888);
            staticLayout.draw(new Canvas(createBitmap));
            textPaint.clearShadowLayer();
            int alpha = paint.getAlpha();
            paint.setXfermode(null);
            paint.setAlpha((int) (255.0f * cVar.mo17240j()));
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
            createBitmap.recycle();
            paint.setAlpha(alpha);
        }
        canvas.restoreToCount(saveCount);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2446a(Canvas canvas, Paint paint, int i) {
        if (i != 0) {
            Bitmap a = PhotoAsset.m3002a(this.f1756j, canvas.getWidth(), i);
            canvas.drawBitmap(a, new Rect(0, 0, a.getWidth(), a.getHeight()), new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), paint);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public CGSize[] m2477a(CGSize[] cGSizeArr) {
        CGSize[] cGSizeArr2 = new CGSize[cGSizeArr.length];
        for (int i = 0; i < cGSizeArr.length; i++) {
            cGSizeArr2[i] = new CGSize(cGSizeArr[i].f2102a, cGSizeArr[i].f2103b);
        }
        return cGSizeArr2;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public Matrix[] m2482b(Matrix[] matrixArr) {
        Matrix[] matrixArr2 = new Matrix[matrixArr.length];
        for (int i = 0; i < matrixArr.length; i++) {
            matrixArr2[i] = new Matrix(matrixArr[i]);
        }
        return matrixArr2;
    }

    /* renamed from: a */
    public void mo16900a(int i) {
        this.f1761o = i;
        this.f1762p = new StyleObj(StyleObj.f2134h[i], new CGSize(this.f1758l.f2102a));
        m2466a(this.f1762p, (C1497b) null);
    }

    /* renamed from: a */
    public void mo16904a(boolean z) {
        m2452a((View) null);
        StyleObj bVar = (StyleObj) this.f1763q.get(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        if (!z) {
            StoreUtils.m3116a(this.f1756j, this.f1761o);
            if (this.f1762p != bVar) {
                this.f1769w = true;
            }
        } else if (bVar != null && bVar != this.f1762p) {
            m2493g((int) R.string.loading);
            m2466a(bVar, (C1497b) new C1497b() {
                /* renamed from: a */
                public void mo16932a() {
                    EditActivity.this.m2470a((Matrix[]) EditActivity.this.f1763q.get("matrix"));
                    EditActivity.this.m2492g();
                }
            });
        }
    }

    /* renamed from: u */
    private int m2521u() {
        boolean z = false;
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return z;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: v */
    public void m2524v() {
        int i;
        int c = StoreUtils.m3120c();
        int i2 = c >>> 24;
        int i3 = (c >> 8) & SupportMenu.USER_MASK;
        int i4 = c & 255;
        int u = m2521u();
        if (i3 == u) {
            i = i4 + 1;
        } else {
            i2 = 0;
            i = 1;
        }
        StoreUtils.m3121c(this.f1756j, (i & 255) | (i2 << 24) | (u << 8));
    }

    /* renamed from: b */
    public BackgroundInfo mo16907b(int i) {
        this.f1762p.f2143f = BackgroundInfo.m2907a(i);
        m2467a(this.f1762p, this.f1760n, (Bitmap) null);
        return this.f1762p.f2143f;
    }

    /* renamed from: a */
    public BackgroundInfo mo16898a(int i, int i2) {
        this.f1762p.f2143f = BackgroundInfo.m2908a(i, i2);
        m2467a(this.f1762p, this.f1760n, (Bitmap) null);
        return this.f1762p.f2143f;
    }

    /* renamed from: c */
    public BackgroundInfo mo16911c(int i) {
        GridTile gridTile = this.f1751e.get(0);
        this.f1762p.f2143f = BackgroundInfo.m2909a(i, gridTile.getFilterIndex(), gridTile.getFilterMix());
        m2467a(this.f1762p, this.f1760n, (Bitmap) null);
        return this.f1762p.f2143f;
    }

    /* renamed from: b */
    public void mo16910b(boolean z) {
        m2452a((View) null);
        BackgroundInfo backgroundInfo = (BackgroundInfo) this.f1763q.get("background");
        if (!z) {
            if (!this.f1762p.f2143f.equals(backgroundInfo)) {
                this.f1769w = true;
            }
            if (this.f1762p.f2143f.f2114b == 2) {
                StoreUtils.m3119b(this.f1756j, this.f1762p.f2143f.mo17186h());
            }
        } else if (backgroundInfo != null && !this.f1762p.f2143f.equals(backgroundInfo)) {
            this.f1762p.f2143f = backgroundInfo;
            m2467a(this.f1762p, this.f1760n, (Bitmap) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2481b(GridTile gridTile) {
        BackgroundInfo backgroundInfo = this.f1762p.f2143f;
        if (backgroundInfo.f2114b == 2) {
            this.f1762p.f2143f = BackgroundInfo.m2909a(backgroundInfo.mo17186h(), gridTile.getFilterIndex(), gridTile.getFilterMix());
            m2467a(this.f1762p, this.f1760n, (Bitmap) null);
        }
    }

    /* renamed from: d */
    public void mo16916d(int i) {
        GridTile gridTile = this.f1751e.get(0);
        gridTile.setFilterIndex(i);
        m2493g((int) R.string.loading);
        m2481b(gridTile);
        gridTile.mo17353a((GridTile.C1598b) new GridTile.C1598b() {
            /* renamed from: a */
            public void mo16930a(boolean z, GridTile gridTile) {
                EditActivity.this.m2492g();
            }
        });
    }

    /* renamed from: c */
    public void mo16914c(boolean z) {
        m2452a((View) null);
        Pair[] iVarArr = (Pair[]) this.f1763q.get("filter");
        if (z) {
            if (iVarArr != null && m2474a((Pair<Integer, Float>[]) iVarArr)) {
                m2493g((int) R.string.loading);
                m2471a((Pair<Integer, Float>[]) iVarArr, (GridTile.C1598b) new GridTile.C1598b() {
                    /* renamed from: a */
                    public void mo16930a(boolean z, GridTile gridTile) {
                        EditActivity.this.m2481b((GridTile) EditActivity.this.f1751e.get(0));
                        EditActivity.this.m2492g();
                    }
                });
            }
        } else if (iVarArr != null && m2474a((Pair<Integer, Float>[]) iVarArr)) {
            this.f1769w = true;
        }
    }

    /* renamed from: d */
    public void mo16918d(boolean z) {
        if (this.f1766t == null && this.f1752f != null && this.f1752f.size() > 0) {
            for (GridFreeLayer next : this.f1752f) {
                if (next instanceof GridText) {
                    GridText gridText = (GridText) next;
                    this.f1766t = gridText;
                    if (gridText.mo17307a()) {
                        break;
                    }
                }
            }
        }
        if (this.f1766t != null) {
            this.f1766t.setEditing(false);
            this.f1766t.setSelected(this.f1766t.getSelected());
        }
        this.f1770x = false;
        m2452a((View) null);
        m2494h();
        if (this.f1766t != null && this.f1766t.mo17328d()) {
            mo16909b((GridFreeLayer) this.f1766t);
        }
        if (this.f1752f.size() > 0) {
            this.f1769w = true;
        }
        this.f1766t = null;
    }

    /* renamed from: c */
    public GridText mo16912c() {
        if (this.f1766t == null && this.f1752f != null && this.f1752f.size() > 0) {
            for (GridFreeLayer next : this.f1752f) {
                if (next instanceof GridText) {
                    GridText gridText = (GridText) next;
                    this.f1766t = gridText;
                    if (gridText.mo17307a()) {
                        break;
                    }
                }
            }
        }
        return this.f1766t;
    }

    /* renamed from: e */
    public void mo16919e(int i) {
        GridTile gridTile = this.f1751e.get(0);
        gridTile.setRetouch(i);
        m2493g((int) R.string.loading);
        gridTile.mo17353a((GridTile.C1598b) new GridTile.C1598b() {
            /* renamed from: a */
            public void mo16930a(boolean z, GridTile gridTile) {
                EditActivity.this.m2492g();
            }
        });
    }

    /* renamed from: e */
    public void mo16921e(boolean z) {
        m2452a((View) null);
        if (z) {
            int[] iArr = (int[]) this.f1763q.get("retouch");
            if (iArr != null && m2473a(iArr)) {
                m2493g((int) R.string.loading);
                m2469a(iArr, (GridTile.C1598b) new GridTile.C1598b() {
                    /* renamed from: a */
                    public void mo16930a(boolean z, GridTile gridTile) {
                        EditActivity.this.m2481b((GridTile) EditActivity.this.f1751e.get(0));
                        EditActivity.this.m2492g();
                    }
                });
            }
        } else if (this.f1751e.get(0).getRetouch() != 0) {
            this.f1769w = true;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.f1765s == null) {
            return m2517s();
        }
        this.f1765s.run();
        return false;
    }

    /* renamed from: a */
    public boolean mo16906a(GridTile gridTile, Object obj) {
        boolean z = !this.f1770x;
        if (z) {
            mo16903a(gridTile);
        }
        return z;
    }

    /* renamed from: a */
    public void mo16903a(GridTile gridTile) {
        for (GridFreeLayer selected : this.f1752f) {
            selected.setSelected(false);
        }
    }

    /* renamed from: a */
    public void mo16902a(GridFreeLayer gridFreeLayer) {
        Iterator<GridFreeLayer> it = this.f1752f.iterator();
        while (it.hasNext()) {
            GridFreeLayer next = it.next();
            next.setSelected(next == gridFreeLayer);
        }
    }

    /* renamed from: b */
    public void mo16909b(GridFreeLayer gridFreeLayer) {
        if (this.f1770x) {
            mo16918d(false);
        }
        if (this.f1752f.contains(gridFreeLayer)) {
            this.f1752f.remove(gridFreeLayer);
            this.canvasLayout.removeView(gridFreeLayer);
        }
    }

    /* renamed from: c */
    public void mo16913c(GridFreeLayer gridFreeLayer) {
        if ((gridFreeLayer instanceof GridText) && this.f1770x) {
            mo16918d(true);
        }
    }

    /* renamed from: d */
    public void mo16917d(GridFreeLayer gridFreeLayer) {
        if (!this.f1770x) {
            m2490f(gridFreeLayer);
            this.f1766t = (GridText) gridFreeLayer;
            this.f1770x = true;
            m2515r();
            this.f1746B.setVisibility(8);
            final TextFragment textFragment = new TextFragment();
            this.f1765s = new Runnable() {
                public void run() {
                    textFragment.getView().setVisibility(8);
                    EditActivity.this.mo16918d(true);
                }
            };
            FragmentTransaction beginTransaction = this.f1755i.beginTransaction();
            beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            beginTransaction.replace(R.id.edit_effect_frame, textFragment);
            beginTransaction.commit();
        }
    }

    /* renamed from: e */
    public void mo16920e(GridFreeLayer gridFreeLayer) {
    }

    /* renamed from: a */
    public boolean mo16905a(GridFreeLayer gridFreeLayer, Object obj) {
        return true;
    }

    /* renamed from: d */
    public Activity mo16915d() {
        return this;
    }
}
