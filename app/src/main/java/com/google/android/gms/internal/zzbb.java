package com.google.android.gms.internal;

import java.util.HashMap;

public class zzbb extends zzak<Integer, Long> {
    public Long zzqp;
    public Long zzqq;

    public zzbb() {
    }

    public zzbb(String str) {
        zzk(str);
    }

    /* access modifiers changed from: protected */
    public HashMap<Integer, Long> zzQ() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzqp);
        hashMap.put(1, this.zzqq);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void zzk(String str) {
        HashMap zzl = zzl(str);
        if (zzl != null) {
            this.zzqp = (Long) zzl.get(0);
            this.zzqq = (Long) zzl.get(1);
        }
    }
}
