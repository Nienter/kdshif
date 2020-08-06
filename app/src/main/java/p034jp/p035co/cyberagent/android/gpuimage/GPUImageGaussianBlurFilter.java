package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.j */
public class GPUImageGaussianBlurFilter extends GPUImageTwoPassTextureSamplingFilter {

    /* renamed from: j */
    protected float f2804j;

    /* renamed from: k */
    protected boolean f2805k;

    /* renamed from: b */
    private static String m3806b(int i, float f) {
        float f2;
        if (i < 1) {
            return "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}";
        }
        float[] fArr = new float[(i + 1)];
        float f3 = 0.0f;
        int i2 = 0;
        while (i2 < i + 1) {
            fArr[i2] = (float) ((1.0d / Math.sqrt(6.283185307179586d * Math.pow((double) f, 2.0d))) * Math.exp((-Math.pow((double) i2, 2.0d)) / (2.0d * Math.pow((double) f, 2.0d))));
            if (i2 == 0) {
                f2 = fArr[i2] + f3;
            } else {
                f2 = (float) (((double) f3) + (2.0d * ((double) fArr[i2])));
            }
            i2++;
            f3 = f2;
        }
        for (int i3 = 0; i3 < i + 1; i3++) {
            fArr[i3] = fArr[i3] / f3;
        }
        int min = Math.min((i / 2) + (i % 2), 7);
        float[] fArr2 = new float[min];
        for (int i4 = 0; i4 < min; i4++) {
            float f4 = fArr[(i4 * 2) + 1];
            float f5 = fArr[(i4 * 2) + 2];
            fArr2[i4] = ((f4 * ((float) ((i4 * 2) + 1))) + (f5 * ((float) ((i4 * 2) + 2)))) / (f4 + f5);
        }
        String str = ("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform lowp float texelWidthOffset;\nuniform lowp float texelHeightOffset;\n\nvarying vec2 textureCoordinate;\nvarying vec2 blurCoordinates[" + ((long) ((min * 2) + 1)) + "];\n\nvoid main()\n{\n    gl_Position = position;\n   textureCoordinate = inputTextureCoordinate.xy;\n    \n    vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);\n") + "    blurCoordinates[0] = inputTextureCoordinate.xy;\n";
        for (int i5 = 0; i5 < min; i5++) {
            str = str + "    blurCoordinates[" + ((long) ((i5 * 2) + 1)) + "] = inputTextureCoordinate.xy + singleStepOffset * " + fArr2[i5] + ";\n    blurCoordinates[" + ((long) ((i5 * 2) + 2)) + "] = inputTextureCoordinate.xy - singleStepOffset * " + fArr2[i5] + ";\n";
        }
        return str + "}\n";
    }

    /* renamed from: c */
    private static String m3807c(int i, float f) {
        float f2;
        String str;
        int i2 = 0;
        if (i < 1) {
            return "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}";
        }
        float[] fArr = new float[(i + 1)];
        float f3 = 0.0f;
        int i3 = 0;
        while (true) {
            f2 = f3;
            if (i3 >= i + 1) {
                break;
            }
            fArr[i3] = (float) ((1.0d / Math.sqrt(6.283185307179586d * Math.pow((double) f, 2.0d))) * Math.exp((-Math.pow((double) i3, 2.0d)) / (Math.pow((double) f, 2.0d) * 2.0d)));
            if (i3 == 0) {
                f3 = fArr[i3] + f2;
            } else {
                f3 = (float) (((double) f2) + (((double) fArr[i3]) * 2.0d));
            }
            i3++;
        }
        for (int i4 = 0; i4 < i + 1; i4++) {
            fArr[i4] = fArr[i4] / f2;
        }
        int min = Math.min((i / 2) + (i % 2), 7);
        int i5 = (i / 2) + (i % 2);
        String str2 = ("uniform sampler2D inputImageTexture;\nuniform lowp float texelWidthOffset;\nuniform lowp float texelHeightOffset;\n\nvarying highp vec2 blurCoordinates[" + ((min * 2) + 1) + "];\nvarying highp vec2 textureCoordinate;\n\nvoid main()\n{\n   lowp vec4 sum = vec4(0.0);\n   lowp vec4 fragColor=texture2D(inputImageTexture,textureCoordinate);\n") + "    sum += texture2D(inputImageTexture, blurCoordinates[0]) * " + fArr[0] + ";\n";
        while (i2 < min) {
            float f4 = fArr[(i2 * 2) + 1] + fArr[(i2 * 2) + 2];
            String str3 = (str + "    sum += texture2D(inputImageTexture, blurCoordinates[" + ((i2 * 2) + 1) + "]) * " + f4 + ";\n") + "    sum += texture2D(inputImageTexture, blurCoordinates[" + ((i2 * 2) + 2) + "]) * " + f4 + ";\n";
            i2++;
            str2 = str3;
        }
        if (i5 > min) {
            str = str + "    highp vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);\n";
            while (min < i5) {
                float f5 = fArr[(min * 2) + 1];
                float f6 = fArr[(min * 2) + 2];
                float f7 = ((f5 * ((float) ((min * 2) + 1))) + (f6 * ((float) ((min * 2) + 2)))) / (f5 + f6);
                min++;
                str = (str + "    sum += texture2D(inputImageTexture, blurCoordinates[0] + singleStepOffset * " + f7 + ") * " + r6 + ";\n") + "    sum += texture2D(inputImageTexture, blurCoordinates[0] - singleStepOffset * " + f7 + ") * " + r6 + ";\n";
            }
        }
        return str + "    gl_FragColor = sum;\n}\n";
    }

    public GPUImageGaussianBlurFilter() {
        this(3.0f, true);
    }

    public GPUImageGaussianBlurFilter(float f) {
        this(f, false);
    }

    public GPUImageGaussianBlurFilter(float f, boolean z) {
        super(null, null, null, null);
        this.f2804j = 3.0f;
        this.f2805k = true;
        if (z) {
            mo17865b(f);
        } else {
            mo17864a(f);
        }
    }

    /* renamed from: a */
    public void mo17800a() {
        float round = (float) Math.round(this.f2804j);
        int i = 0;
        if (round >= 1.0f) {
            int floor = (int) Math.floor(Math.sqrt(-2.0d * Math.pow((double) round, 2.0d) * Math.log(((double) 0.00390625f) * Math.sqrt(6.283185307179586d * Math.pow((double) round, 2.0d)))));
            i = floor + (floor % 2);
        }
        String c = m3807c(i, round);
        String b = m3806b(i, round);
        mo17809a(b, c, b, c);
        super.mo17800a();
    }

    /* renamed from: a */
    public void mo17864a(float f) {
        this.f2804j = f;
        this.f2805k = false;
    }

    /* renamed from: b */
    public void mo17865b(float f) {
        this.f2804j = f;
        this.f2805k = true;
    }
}
