package com.google.android.gms.common.api;

import android.support.p001v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzzs;
import java.util.ArrayList;

public class zzb extends Exception {
    private final ArrayMap<zzzs<?>, ConnectionResult> zzaxy;

    public zzb(ArrayMap<zzzs<?>, ConnectionResult> arrayMap) {
        this.zzaxy = arrayMap;
    }

    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzzs next : this.zzaxy.keySet()) {
            ConnectionResult connectionResult = this.zzaxy.get(next);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String valueOf = String.valueOf(next.zzuV());
            String valueOf2 = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(valueOf2).length()).append(valueOf).append(": ").append(valueOf2).toString());
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    public ArrayMap<zzzs<?>, ConnectionResult> zzuK() {
        return this.zzaxy;
    }
}
