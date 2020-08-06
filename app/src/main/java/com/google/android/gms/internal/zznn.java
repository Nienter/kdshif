package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zznq;

@zzmb
public class zznn extends zznq.zza {
    private final String zzTW;
    private final int zzUD;

    public zznn(String str, int i) {
        this.zzTW = str;
        this.zzUD = i;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zznn)) {
            return false;
        }
        zznn zznn = (zznn) obj;
        return zzaa.equal(getType(), zznn.getType()) && zzaa.equal(Integer.valueOf(getAmount()), Integer.valueOf(zznn.getAmount()));
    }

    public int getAmount() {
        return this.zzUD;
    }

    public String getType() {
        return this.zzTW;
    }
}
