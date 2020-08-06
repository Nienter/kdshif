package com.snaperfect.style.daguerre.style;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.math.CGRect;
import com.snaperfect.style.daguerre.math.CGSize;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageAlphaMaskGenFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageConstColorFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilterGraph;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFragScaleFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageGradientColorFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageGreyMaskFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageMixBlendFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageNormalBlendFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageTransformFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GraphNode;

/* renamed from: com.snaperfect.style.daguerre.style.a */
public class StyleBitmapTask extends AsyncTask<Object, Void, Bitmap> {

    /* renamed from: a */
    static final /* synthetic */ boolean f2129a = (!StyleBitmapTask.class.desiredAssertionStatus());

    /* renamed from: b */
    private final WeakReference<ImageView> f2130b;

    /* renamed from: c */
    private final int f2131c;

    /* renamed from: d */
    private final boolean f2132d;

    /* renamed from: e */
    private Throwable f2133e;

    public StyleBitmapTask(ImageView imageView, int i, boolean z) {
        imageView.setTag(-1430532899, Integer.valueOf(i));
        this.f2131c = i;
        this.f2130b = new WeakReference<>(imageView);
        this.f2132d = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap doInBackground(Object... objArr) {
        GPUImage aVar = objArr[0];
        Bitmap[] bitmapArr = objArr[1];
        CGSize cGSize = objArr[2];
        StyleObj bVar = objArr[3];
        String str = objArr[4];
        Bitmap bitmap = bitmapArr[0];
        Bitmap bitmap2 = bitmapArr[1];
        Bitmap bitmap3 = bitmapArr[2];
        if (bitmap == null || bitmap.isRecycled()) {
            this.f2133e = new NullPointerException(" Original Bitmap not exist");
            return null;
        } else if ((bVar.f2140c == 2 || bVar.f2140c == 3) && (bitmap2 == null || bitmap3 == null || bitmap2.isRecycled() || bitmap3.isRecycled())) {
            Log.d("StyleBitmapTask", "frame image or mask image not exist");
            this.f2133e = new NullPointerException("Frame image not exist");
            return null;
        } else {
            ArrayList arrayList = new ArrayList();
            switch (bVar.f2140c) {
                case 0:
                    m2937a((List<GraphNode>) arrayList, bitmapArr, cGSize, bVar);
                    break;
                case 1:
                    m2939b(arrayList, bitmapArr, cGSize, bVar);
                    break;
                case 2:
                    m2940c(arrayList, bitmapArr, cGSize, bVar);
                    break;
                case 3:
                    m2941d(arrayList, bitmapArr, cGSize, bVar);
                    break;
                default:
                    if (!f2129a) {
                        throw new AssertionError();
                    }
                    break;
            }
            Bitmap a = m2934a(aVar, bitmap, (List<GraphNode>) arrayList);
            if (a == null || !this.f2132d) {
                return a;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                a.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return a;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return a;
            } catch (IOException e2) {
                e2.printStackTrace();
                return a;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Bitmap bitmap) {
        ImageView imageView = (ImageView) this.f2130b.get();
        if (bitmap != null && imageView != null && ((Integer) imageView.getTag(-1430532899)).intValue() == this.f2131c) {
            imageView.setImageBitmap(bitmap);
        }
    }

    /* renamed from: a */
    private float[] m2938a(Matrix matrix) {
        float[] fArr = new float[16];
        float[] fArr2 = new float[9];
        matrix.getValues(fArr2);
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        fArr[4] = fArr2[3];
        fArr[5] = fArr2[4];
        fArr[10] = 1.0f;
        fArr[12] = fArr2[2];
        fArr[13] = fArr2[5];
        fArr[15] = 1.0f;
        return fArr;
    }

    /* renamed from: a */
    private GraphNode m2936a(BackgroundInfo backgroundInfo, List<GraphNode> list, CGSize cGSize, Bitmap bitmap) {
        if (backgroundInfo.f2114b == 0) {
            GraphNode adVar = new GraphNode(new GPUImageConstColorFilter(backgroundInfo.mo17179c()));
            adVar.mo17817a(GraphNode.f2737a);
            list.add(adVar);
            return adVar;
        } else if (backgroundInfo.f2114b == 1) {
            GraphNode adVar2 = new GraphNode(new GPUImageGradientColorFilter(backgroundInfo.mo17180d(), backgroundInfo.mo17182e()));
            adVar2.mo17817a(GraphNode.f2737a);
            list.add(adVar2);
            return adVar2;
        } else if (backgroundInfo.f2114b == 2) {
            float e = (float) (0.03125d * ((double) cGSize.mo17163e()));
            GraphNode adVar3 = new GraphNode(new GPUImageGaussianBlurFilter(e, true));
            adVar3.mo17817a(GraphNode.f2737a);
            GPUImageTransformFilter zVar = new GPUImageTransformFilter();
            GraphNode adVar4 = new GraphNode(zVar);
            float e2 = cGSize.mo17163e() / (cGSize.mo17164f() - (e * 2.0f));
            Matrix matrix = new Matrix();
            matrix.preScale(e2, e2);
            zVar.mo17912a(m2938a(matrix));
            adVar3.mo17817a(GraphNode.f2737a);
            adVar4.mo17817a(adVar3);
            list.add(adVar3);
            list.add(adVar4);
            return adVar4;
        } else if (backgroundInfo.f2114b == 3) {
            GraphNode adVar5 = new GraphNode(new GPUImageMixBlendFilter(1.0f));
            adVar5.mo17817a(GraphNode.f2737a);
            ((GPUImageTwoInputFilter) adVar5.mo17815a()).mo17803b(bitmap);
            list.add(adVar5);
            return adVar5;
        } else {
            Log.e("StyleBitmapTask", "Unknown background type");
            if (f2129a) {
                return null;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    private Bitmap m2934a(GPUImage aVar, Bitmap bitmap, List<GraphNode> list) {
        GPUImageFilterGraph gVar = new GPUImageFilterGraph();
        gVar.mo17856a(list);
        aVar.mo17789a((GPUImageFilter) gVar);
        Bitmap b = aVar.mo17790b(bitmap);
        Log.d("StyleBitmapTask", b.getWidth() + "," + b.getHeight());
        return b;
    }

    /* renamed from: a */
    private void m2937a(List<GraphNode> list, Bitmap[] bitmapArr, CGSize cGSize, StyleObj bVar) {
        GraphNode a = m2936a(bVar.f2143f, list, cGSize, bitmapArr[3]);
        GraphNode adVar = new GraphNode(new GPUImageNormalBlendFilter());
        adVar.mo17817a(a);
        if (Math.abs(cGSize.f2102a - cGSize.f2103b) < 5.0f) {
            GraphNode adVar2 = new GraphNode(new GPUImageFragScaleFilter(new PointF(0.1f, 0.1f), new PointF(0.8f, 0.8f)));
            adVar2.mo17817a(GraphNode.f2737a);
            adVar.mo17819b(adVar2);
            list.add(adVar2);
        } else {
            adVar.mo17819b(GraphNode.f2737a);
        }
        list.add(adVar);
    }

    /* renamed from: b */
    private void m2939b(List<GraphNode> list, Bitmap[] bitmapArr, CGSize cGSize, StyleObj bVar) {
        Bitmap bitmap = bitmapArr[0];
        GraphNode a = m2936a(bVar.f2143f, list, cGSize, bitmapArr[3]);
        GPUImageTransformFilter zVar = new GPUImageTransformFilter();
        float width = (float) (0.015625d * ((double) bitmap.getWidth()));
        GraphNode adVar = new GraphNode(zVar);
        GraphNode adVar2 = new GraphNode(new GPUImageAlphaMaskGenFilter());
        GraphNode adVar3 = new GraphNode(new GPUImageGaussianBlurFilter(width, true));
        GraphNode adVar4 = new GraphNode(new GPUImageConstColorFilter(0));
        GraphNode adVar5 = new GraphNode(new GPUImageNormalBlendFilter());
        GraphNode adVar6 = new GraphNode(new GPUImageNormalBlendFilter());
        GraphNode adVar7 = new GraphNode(new GPUImageNormalBlendFilter());
        adVar4.mo17817a(GraphNode.f2737a);
        adVar.mo17817a(GraphNode.f2737a);
        adVar2.mo17817a(adVar);
        adVar5.mo17817a(adVar4);
        adVar5.mo17819b(adVar2);
        adVar3.mo17817a(adVar5);
        adVar6.mo17817a(adVar3);
        adVar6.mo17819b(adVar);
        adVar7.mo17817a(a);
        adVar7.mo17819b(adVar6);
        Matrix matrix = new Matrix();
        matrix.preScale(0.85f, 0.85f);
        matrix.preRotate(5.0f);
        zVar.mo17912a(m2938a(matrix));
        list.addAll(Arrays.asList(new GraphNode[]{adVar4, adVar, adVar2, adVar5, adVar3, adVar6, adVar7}));
    }

    /* renamed from: a */
    private Matrix m2935a(CGSize cGSize, CGSize cGSize2, StyleObj bVar) {
        CGRect b = CGRect.m2856b(CGRect.m2857c(bVar.f2141d, CGSize.m2873b(cGSize, bVar.f2142e)), cGSize2);
        CGPoint a = CGPoint.m2834b(b.mo17137b(), new CGPoint(cGSize).mo17126b(2.0f)).mo17124a(cGSize).mo17122a(2.0f);
        CGSize b2 = CGSize.m2873b(new CGSize(b.f2100c, b.f2101d), cGSize2);
        Matrix matrix = new Matrix();
        matrix.preScale(b2.f2102a, b2.f2103b);
        matrix.postTranslate(a.x, a.y);
        return matrix;
    }

    /* renamed from: c */
    private void m2940c(List<GraphNode> list, Bitmap[] bitmapArr, CGSize cGSize, StyleObj bVar) {
        Bitmap bitmap = bitmapArr[0];
        Bitmap bitmap2 = bitmapArr[1];
        Bitmap bitmap3 = bitmapArr[2];
        bitmap.getWidth();
        GPUImageTransformFilter zVar = new GPUImageTransformFilter();
        GPUImageGreyMaskFilter mVar = new GPUImageGreyMaskFilter();
        GraphNode adVar = new GraphNode(zVar);
        GraphNode adVar2 = new GraphNode(mVar);
        adVar.mo17817a(GraphNode.f2737a);
        adVar2.mo17817a(adVar);
        mVar.mo17803b(bitmap2);
        mVar.mo17900a(bitmap3);
        zVar.mo17912a(m2938a(m2935a(new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight()), cGSize, bVar)));
        list.addAll(Arrays.asList(new GraphNode[]{adVar, adVar2}));
    }

    /* renamed from: d */
    private void m2941d(List<GraphNode> list, Bitmap[] bitmapArr, CGSize cGSize, StyleObj bVar) {
        Bitmap bitmap = bitmapArr[0];
        Bitmap bitmap2 = bitmapArr[1];
        Bitmap bitmap3 = bitmapArr[2];
        GraphNode a = m2936a(bVar.f2143f, list, cGSize, bitmapArr[3]);
        GPUImageTransformFilter zVar = new GPUImageTransformFilter();
        GPUImageGreyMaskFilter mVar = new GPUImageGreyMaskFilter();
        mVar.mo17803b(bitmap2);
        mVar.mo17900a(bitmap3);
        GraphNode adVar = new GraphNode(zVar);
        GraphNode adVar2 = new GraphNode(mVar);
        GraphNode adVar3 = new GraphNode(new GPUImageNormalBlendFilter());
        adVar.mo17817a(GraphNode.f2737a);
        adVar2.mo17817a(adVar);
        adVar3.mo17817a(a);
        adVar3.mo17819b(adVar2);
        zVar.mo17912a(m2938a(m2935a(new CGSize((float) bitmap.getWidth(), (float) bitmap.getHeight()), cGSize, bVar)));
        list.addAll(Arrays.asList(new GraphNode[]{adVar, adVar2, adVar3}));
    }
}
