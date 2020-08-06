package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.GPUImageNativeLibrary */
public class GPUImageNativeLibrary {
    public static native void YUVtoRBGA(byte[] bArr, int i, int i2, int[] iArr);

    static {
        System.loadLibrary("gpuimage-library");
    }
}
