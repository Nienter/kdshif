package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.internal.zzlu;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@zzmb
public class zzlx implements zzlu.zza<zzgq> {
    private final boolean zzQH;
    private final boolean zzQI;

    public zzlx(boolean z, boolean z2) {
        this.zzQH = z;
        this.zzQI = z2;
    }

    /* renamed from: zzc */
    public zzgq zza(zzlu zzlu, JSONObject jSONObject) {
        List<zzqf<zzgo>> zza = zzlu.zza(jSONObject, "images", true, this.zzQH, this.zzQI);
        zzqf<zzgo> zza2 = zzlu.zza(jSONObject, "secondary_image", false, this.zzQH);
        zzqf<zzgm> zze = zzlu.zze(jSONObject);
        ArrayList arrayList = new ArrayList();
        for (zzqf<zzgo> zzqf : zza) {
            arrayList.add((zzgo) zzqf.get());
        }
        return new zzgq(jSONObject.getString("headline"), arrayList, jSONObject.getString("body"), (zzgz) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.getString("advertiser"), (zzgm) zze.get(), new Bundle());
    }
}
