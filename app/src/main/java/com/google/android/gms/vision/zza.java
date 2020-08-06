package com.google.android.gms.vision;

import android.util.SparseArray;

public class zza {
    private static int zzbMB = 0;
    private static final Object zztU = new Object();
    private SparseArray<Integer> zzbMC = new SparseArray<>();
    private SparseArray<Integer> zzbMD = new SparseArray<>();

    public int zzng(int i) {
        int i2;
        synchronized (zztU) {
            Integer num = this.zzbMC.get(i);
            if (num != null) {
                i2 = num.intValue();
            } else {
                i2 = zzbMB;
                zzbMB++;
                this.zzbMC.append(i, Integer.valueOf(i2));
                this.zzbMD.append(i2, Integer.valueOf(i));
            }
        }
        return i2;
    }

    public int zznh(int i) {
        int intValue;
        synchronized (zztU) {
            intValue = this.zzbMD.get(i).intValue();
        }
        return intValue;
    }
}
