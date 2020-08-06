package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;

public class zzasx {
    final String mName;
    final String zzVQ;
    final long zzavX;
    final String zzbqG;
    final long zzbqH;
    final zzasz zzbqI;

    zzasx(zzatp zzatp, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzac.zzdv(str2);
        zzac.zzdv(str3);
        this.zzVQ = str2;
        this.mName = str3;
        this.zzbqG = TextUtils.isEmpty(str) ? null : str;
        this.zzavX = j;
        this.zzbqH = j2;
        if (this.zzbqH != 0 && this.zzbqH > this.zzavX) {
            zzatp.zzJt().zzLc().zzj("Event created with reverse previous/current timestamps. appId", zzati.zzfI(str2));
        }
        this.zzbqI = zza(zzatp, bundle);
    }

    private zzasx(zzatp zzatp, String str, String str2, String str3, long j, long j2, zzasz zzasz) {
        zzac.zzdv(str2);
        zzac.zzdv(str3);
        zzac.zzw(zzasz);
        this.zzVQ = str2;
        this.mName = str3;
        this.zzbqG = TextUtils.isEmpty(str) ? null : str;
        this.zzavX = j;
        this.zzbqH = j2;
        if (this.zzbqH != 0 && this.zzbqH > this.zzavX) {
            zzatp.zzJt().zzLc().zzj("Event created with reverse previous/current timestamps. appId", zzati.zzfI(str2));
        }
        this.zzbqI = zzasz;
    }

    static zzasz zza(zzatp zzatp, Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return new zzasz(new Bundle());
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                zzatp.zzJt().zzLa().log("Param name can't be null");
                it.remove();
            } else {
                Object zzl = zzatp.zzJp().zzl(str, bundle2.get(str));
                if (zzl == null) {
                    zzatp.zzJt().zzLc().zzj("Param value can't be null", str);
                    it.remove();
                } else {
                    zzatp.zzJp().zza(bundle2, str, zzl);
                }
            }
        }
        return new zzasz(bundle2);
    }

    public String toString() {
        String str = this.zzVQ;
        String str2 = this.mName;
        String valueOf = String.valueOf(this.zzbqI);
        return new StringBuilder(String.valueOf(str).length() + 33 + String.valueOf(str2).length() + String.valueOf(valueOf).length()).append("Event{appId='").append(str).append("'").append(", name='").append(str2).append("'").append(", params=").append(valueOf).append("}").toString();
    }

    /* access modifiers changed from: package-private */
    public zzasx zza(zzatp zzatp, long j) {
        return new zzasx(zzatp, this.zzbqG, this.zzVQ, this.mName, this.zzavX, j, this.zzbqI);
    }
}
