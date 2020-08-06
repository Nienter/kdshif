package com.google.android.gms.internal;

import java.util.HashMap;

public class zzbf extends zzak<Integer, Long> {
    public Long zzqU;

    public zzbf() {
    }

    public zzbf(String str) {
        zzk(str);
    }

    /* access modifiers changed from: protected */
    public HashMap<Integer, Long> zzQ() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzqU);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void zzk(String str) {
        HashMap zzl = zzl(str);
        if (zzl != null) {
            this.zzqU = (Long) zzl.get(0);
        }
    }
}
