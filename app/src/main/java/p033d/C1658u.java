package p033d;

import java.nio.charset.Charset;

/* renamed from: d.u */
/* compiled from: Util */
final class C1658u {

    /* renamed from: a */
    public static final Charset f2697a = Charset.forName("UTF-8");

    /* renamed from: a */
    public static void m3672a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    /* renamed from: a */
    public static short m3671a(short s) {
        short s2 = 65535 & s;
        return (short) (((s2 & 255) << 8) | ((65280 & s2) >>> 8));
    }

    /* renamed from: a */
    public static int m3670a(int i) {
        return ((-16777216 & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8) | ((i & 255) << 24);
    }

    /* renamed from: a */
    public static void m3673a(Throwable th) {
        m3675b(th);
    }

    /* renamed from: b */
    private static <T extends Throwable> void m3675b(Throwable th) {
        throw th;
    }

    /* renamed from: a */
    public static boolean m3674a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }
}
