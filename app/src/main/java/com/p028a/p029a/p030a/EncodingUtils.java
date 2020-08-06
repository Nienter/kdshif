package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.bc */
public class EncodingUtils {
    /* renamed from: a */
    public static final boolean m1910a(byte b, int i) {
        return m1911a((int) b, i);
    }

    /* renamed from: a */
    public static final boolean m1911a(int i, int i2) {
        return ((1 << i2) & i) != 0;
    }

    /* renamed from: b */
    public static final byte m1912b(byte b, int i) {
        return (byte) m1913b((int) b, i);
    }

    /* renamed from: b */
    public static final int m1913b(int i, int i2) {
        return ((1 << i2) ^ -1) & i;
    }

    /* renamed from: a */
    public static final byte m1908a(byte b, int i, boolean z) {
        return (byte) m1909a((int) b, i, z);
    }

    /* renamed from: a */
    public static final int m1909a(int i, int i2, boolean z) {
        if (z) {
            return (1 << i2) | i;
        }
        return m1913b(i, i2);
    }
}
