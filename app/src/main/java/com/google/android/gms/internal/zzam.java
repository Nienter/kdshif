package com.google.android.gms.internal;

import java.util.HashMap;

public class zzam extends zzak<Integer, Object> {
    public String zzaM;
    public String zzaO;
    public String zzaP;
    public String zzaQ;
    public long zzlH;

    public zzam() {
        this.zzaM = "E";
        this.zzlH = -1;
        this.zzaO = "E";
        this.zzaP = "E";
        this.zzaQ = "E";
    }

    public zzam(String str) {
        this();
        zzk(str);
    }

    /* access modifiers changed from: protected */
    public HashMap<Integer, Object> zzQ() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(0, this.zzaM);
        hashMap.put(4, this.zzaQ);
        hashMap.put(3, this.zzaP);
        hashMap.put(2, this.zzaO);
        hashMap.put(1, Long.valueOf(this.zzlH));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void zzk(String str) {
        HashMap zzl = zzl(str);
        if (zzl != null) {
            this.zzaM = zzl.get(0) == null ? "E" : (String) zzl.get(0);
            this.zzlH = zzl.get(1) == null ? -1 : ((Long) zzl.get(1)).longValue();
            this.zzaO = zzl.get(2) == null ? "E" : (String) zzl.get(2);
            this.zzaP = zzl.get(3) == null ? "E" : (String) zzl.get(3);
            this.zzaQ = zzl.get(4) == null ? "E" : (String) zzl.get(4);
        }
    }
}
