package p034jp.p035co.cyberagent.android.gpuimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import java.io.File;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;

/* renamed from: jp.co.cyberagent.android.gpuimage.GPUImageView */
public class GPUImageView extends FrameLayout {

    /* renamed from: a */
    public C1660b f2698a = null;

    /* renamed from: b */
    private GLSurfaceView f2699b;

    /* renamed from: c */
    private GPUImage f2700c;

    /* renamed from: d */
    private GPUImageFilter f2701d;

    /* renamed from: e */
    private float f2702e = 0.0f;

    /* renamed from: jp.co.cyberagent.android.gpuimage.GPUImageView$a */
    private class C1659a extends GLSurfaceView {
        public C1659a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            if (GPUImageView.this.f2698a != null) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(GPUImageView.this.f2698a.f2704a, 1073741824), View.MeasureSpec.makeMeasureSpec(GPUImageView.this.f2698a.f2705b, 1073741824));
            } else {
                super.onMeasure(i, i2);
            }
        }
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.GPUImageView$b */
    public static class C1660b {

        /* renamed from: a */
        int f2704a;

        /* renamed from: b */
        int f2705b;
    }

    public GPUImageView(Context context) {
        super(context);
        m3676a(context, null);
    }

    public GPUImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m3676a(context, attributeSet);
    }

    /* renamed from: a */
    private void m3676a(Context context, AttributeSet attributeSet) {
        this.f2699b = new C1659a(context, attributeSet);
        addView(this.f2699b);
        this.f2700c = new GPUImage(getContext());
        this.f2700c.mo17785a(this.f2699b);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.f2702e != 0.0f) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            if (((float) size) / this.f2702e < ((float) size2)) {
                size2 = Math.round(((float) size) / this.f2702e);
            } else {
                size = Math.round(((float) size2) * this.f2702e);
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
            return;
        }
        super.onMeasure(i, i2);
    }

    public GPUImage getGPUImage() {
        return this.f2700c;
    }

    public void setRatio(float f) {
        this.f2702e = f;
        this.f2699b.requestLayout();
        this.f2700c.mo17792c();
    }

    public void setScaleType(GPUImage.C1665d dVar) {
        this.f2700c.mo17787a(dVar);
    }

    public void setRotation(Rotation agVar) {
        this.f2700c.mo17788a(agVar);
        mo17770a();
    }

    public void setFilter(GPUImageFilter fVar) {
        this.f2701d = fVar;
        this.f2700c.mo17789a(fVar);
        mo17770a();
    }

    public GPUImageFilter getFilter() {
        return this.f2701d;
    }

    public void setImage(Bitmap bitmap) {
        this.f2700c.mo17783a(bitmap);
    }

    public void setImage(Uri uri) {
        this.f2700c.mo17784a(uri);
    }

    public void setImage(File file) {
        this.f2700c.mo17786a(file);
    }

    /* renamed from: a */
    public void mo17770a() {
        this.f2699b.requestRender();
    }
}
