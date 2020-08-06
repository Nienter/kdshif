package p034jp.p035co.cyberagent.android.gpuimage.p036a;

import p034jp.p035co.cyberagent.android.gpuimage.Rotation;

/* renamed from: jp.co.cyberagent.android.gpuimage.a.a */
public class TextureRotationUtil {

    /* renamed from: a */
    public static final float[] f2724a = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};

    /* renamed from: b */
    public static final float[] f2725b = {1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    /* renamed from: c */
    public static final float[] f2726c = {1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f};

    /* renamed from: d */
    public static final float[] f2727d = {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f};

    /* renamed from: a */
    public static float[] m3713a(Rotation agVar, boolean z, boolean z2) {
        float[] fArr;
        switch (agVar) {
            case ROTATION_90:
                fArr = f2725b;
                break;
            case ROTATION_180:
                fArr = f2726c;
                break;
            case ROTATION_270:
                fArr = f2727d;
                break;
            default:
                fArr = f2724a;
                break;
        }
        float[] fArr2 = z ? new float[]{m3712a(fArr[0]), fArr[1], m3712a(fArr[2]), fArr[3], m3712a(fArr[4]), fArr[5], m3712a(fArr[6]), fArr[7]} : fArr;
        if (!z2) {
            return fArr2;
        }
        return new float[]{fArr2[0], m3712a(fArr2[1]), fArr2[2], m3712a(fArr2[3]), fArr2[4], m3712a(fArr2[5]), fArr2[6], m3712a(fArr2[7])};
    }

    /* renamed from: a */
    private static float m3712a(float f) {
        if (f == 0.0f) {
            return 1.0f;
        }
        return 0.0f;
    }
}
