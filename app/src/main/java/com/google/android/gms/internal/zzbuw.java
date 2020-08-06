package com.google.android.gms.internal;

public final class zzbuw {
    public static final int[] zzcsi = new int[0];
    public static final long[] zzcsj = new long[0];
    public static final float[] zzcsk = new float[0];
    public static final double[] zzcsl = new double[0];
    public static final boolean[] zzcsm = new boolean[0];
    public static final String[] zzcsn = new String[0];
    public static final byte[][] zzcso = new byte[0][];
    public static final byte[] zzcsp = new byte[0];

    public static int zzK(int i, int i2) {
        return (i << 3) | i2;
    }

    public static boolean zzb(zzbul zzbul, int i) {
        return zzbul.zzqh(i);
    }

    public static final int zzc(zzbul zzbul, int i) {
        int i2 = 1;
        int position = zzbul.getPosition();
        zzbul.zzqh(i);
        while (zzbul.zzacu() == i) {
            zzbul.zzqh(i);
            i2++;
        }
        zzbul.zzql(position);
        return i2;
    }

    static int zzqA(int i) {
        return i & 7;
    }

    public static int zzqB(int i) {
        return i >>> 3;
    }
}
