package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class zze {
    private static final Object zztU = new Object();
    private final zzh zzcjc;

    zze(zzh zzh) {
        this.zzcjc = zzh;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public String zzaai() {
        String str = null;
        synchronized (zztU) {
            String string = this.zzcjc.zzaam().getString("topic_operaion_queue", null);
            if (string != null) {
                String[] split = string.split(",");
                if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                    str = split[1];
                }
            }
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public void zzju(String str) {
        synchronized (zztU) {
            String string = this.zzcjc.zzaam().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            this.zzcjc.zzaam().edit().putString("topic_operaion_queue", new StringBuilder(String.valueOf(string).length() + 0 + String.valueOf(valueOf).length() + String.valueOf(str).length()).append(string).append(valueOf).append(str).toString()).apply();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzjy(String str) {
        boolean z;
        synchronized (zztU) {
            String string = this.zzcjc.zzaam().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                String valueOf3 = String.valueOf(",");
                String valueOf4 = String.valueOf(str);
                this.zzcjc.zzaam().edit().putString("topic_operaion_queue", string.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length())).apply();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
