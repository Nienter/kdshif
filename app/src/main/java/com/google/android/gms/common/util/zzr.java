package com.google.android.gms.common.util;

public class zzr {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x006b, code lost:
        r0 = (r0 | (r6[r2] & 255)) * -862048943;
        r0 = (((r0 >>> 17) | (r0 << 15)) * 461845907) ^ r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0047, code lost:
        r0 = r0 ^ r8;
        r0 = (r0 ^ (r0 >>> 16)) * -2048144789;
        r0 = (r0 ^ (r0 >>> 13)) * -1028477387;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0059, code lost:
        return r0 ^ (r0 >>> 16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0062, code lost:
        r0 = r0 | ((r6[r2 + 1] & 255) << 8);
     */
    public static int zza(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + (i2 & -4);
        int i5 = i3;
        while (i < i4) {
            int i6 = ((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | (bArr[i + 3] << 24)) * -862048943;
            int i7 = (((i6 >>> 17) | (i6 << 15)) * 461845907) ^ i5;
            i5 = -430675100 + (((i7 >>> 19) | (i7 << 13)) * 5);
            i += 4;
        }
        int i8 = 0;
        switch (i2 & 3) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                i8 = (bArr[i4 + 2] & 255) << 16;
                break;
            default:
                int i9 = i5;
                break;
        }
    }
}
