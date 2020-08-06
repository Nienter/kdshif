package com.google.android.gms.internal;

import java.util.HashMap;

public class zzba extends zzak<Integer, Object> {
    public Long zzqm;
    public Boolean zzqn;
    public Boolean zzqo;

    public zzba() {
    }

    public zzba(String str) {
        zzk(str);
    }

    /* access modifiers changed from: protected */
    public HashMap<Integer, Object> zzQ() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(0, this.zzqm);
        hashMap.put(1, this.zzqn);
        hashMap.put(2, this.zzqo);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void zzk(String str) {
        HashMap zzl = zzl(str);
        if (zzl != null) {
            this.zzqm = (Long) zzl.get(0);
            this.zzqn = (Boolean) zzl.get(1);
            this.zzqo = (Boolean) zzl.get(2);
        }
    }
}
