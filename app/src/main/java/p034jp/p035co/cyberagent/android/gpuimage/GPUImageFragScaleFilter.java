package p034jp.p035co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: jp.co.cyberagent.android.gpuimage.i */
public class GPUImageFragScaleFilter extends GPUImageFilter {

    /* renamed from: a */
    private PointF f2800a;

    /* renamed from: i */
    private PointF f2801i;

    /* renamed from: j */
    private int f2802j;

    /* renamed from: k */
    private int f2803k;

    public GPUImageFragScaleFilter() {
        this(new PointF(), new PointF(1.0f, 1.0f));
    }

    public GPUImageFragScaleFilter(PointF pointF, PointF pointF2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\nuniform highp vec2 origin;\nuniform highp vec2 size;\n \nvoid main()\n{\n    vec2 edge = smoothstep(origin, origin + size, textureCoordinate); \n    if (edge.x == 0.0 || edge.y == 0.0 || edge.x == 1.0 || edge.y == 1.0) { \n        gl_FragColor = vec4(0.0); \n    } else { \n        gl_FragColor = texture2D(inputImageTexture, (textureCoordinate - origin) / size); \n    } \n}");
        this.f2800a = pointF;
        this.f2801i = pointF2;
    }

    /* renamed from: a */
    public void mo17800a() {
        super.mo17800a();
        this.f2802j = GLES20.glGetUniformLocation(mo17850l(), FirebaseAnalytics.Param.ORIGIN);
        this.f2803k = GLES20.glGetUniformLocation(mo17850l(), "size");
    }

    /* renamed from: b */
    public void mo17832b() {
        super.mo17832b();
        mo17862a(this.f2800a);
        mo17863b(this.f2801i);
    }

    /* renamed from: a */
    public void mo17862a(PointF pointF) {
        this.f2800a = pointF;
        mo17836a(this.f2802j, this.f2800a);
    }

    /* renamed from: b */
    public void mo17863b(PointF pointF) {
        this.f2801i = pointF;
        mo17836a(this.f2803k, this.f2801i);
    }
}
