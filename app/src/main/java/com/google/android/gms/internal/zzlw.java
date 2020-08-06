package com.google.android.gms.internal;

import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.internal.zzlu;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

@zzmb
public class zzlw implements zzlu.zza<zzgp> {
    private final boolean zzQH;
    private final boolean zzQI;

    public zzlw(boolean z, boolean z2) {
        this.zzQH = z;
        this.zzQI = z2;
    }

    private zzqp zzb(zzqf<zzqp> zzqf) {
        try {
            return (zzqp) zzqf.get((long) zzfx.zzDY.get().intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            zzpe.zzc("InterruptedException occurred while waiting for video to load", e);
            Thread.currentThread().interrupt();
        } catch (CancellationException | ExecutionException | TimeoutException e2) {
            zzpe.zzc("Exception occurred while waiting for video to load", e2);
        }
        return null;
    }

    /* renamed from: zzb */
    public zzgp zza(zzlu zzlu, JSONObject jSONObject) {
        List<zzqf<zzgo>> zza = zzlu.zza(jSONObject, "images", true, this.zzQH, this.zzQI);
        zzqf<zzgo> zza2 = zzlu.zza(jSONObject, "app_icon", true, this.zzQH);
        zzqf<zzqp> zzc = zzlu.zzc(jSONObject, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
        zzqf<zzgm> zze = zzlu.zze(jSONObject);
        ArrayList arrayList = new ArrayList();
        for (zzqf<zzgo> zzqf : zza) {
            arrayList.add((zzgo) zzqf.get());
        }
        zzqp zzb = zzb(zzc);
        return new zzgp(jSONObject.getString("headline"), arrayList, jSONObject.getString("body"), (zzgz) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString("store"), jSONObject.optString(FirebaseAnalytics.Param.PRICE), (zzgm) zze.get(), new Bundle(), zzb != null ? zzb.zzlg() : null, zzb != null ? zzb.getView() : null);
    }
}
