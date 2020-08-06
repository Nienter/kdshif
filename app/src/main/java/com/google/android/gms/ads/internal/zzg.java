package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzox;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzqa;
import com.google.android.gms.internal.zzqi;
import com.google.android.gms.internal.zzqp;
import java.util.Map;
import org.json.JSONObject;

@zzmb
public class zzg {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();
    public final zzhx zzsX = new zzhx() {
        public void zza(zzqp zzqp, Map<String, String> map) {
            zzqp.zzb("/appSettingsFetched", (zzhx) this);
            synchronized (zzg.this.zzrN) {
                if (map != null) {
                    if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(map.get("isSuccessful"))) {
                        zzv.zzcN().zzd(zzg.this.mContext, map.get("appSettingsJson"));
                    }
                }
            }
        }
    };

    private static boolean zza(@Nullable zzox zzox) {
        if (zzox == null) {
            return true;
        }
        return (((zzv.zzcP().currentTimeMillis() - zzox.zzjF()) > zzfx.zzEr.get().longValue() ? 1 : ((zzv.zzcP().currentTimeMillis() - zzox.zzjF()) == zzfx.zzEr.get().longValue() ? 0 : -1)) > 0) || !zzox.zzjG();
    }

    public void zza(Context context, zzqa zzqa, boolean z, @Nullable zzox zzox, String str, @Nullable String str2) {
        if (zza(zzox)) {
            if (context == null) {
                zzpe.zzbe("Context not provided to fetch application settings");
            } else if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                this.mContext = context;
                final zzja zzd = zzv.zzcJ().zzd(context, zzqa);
                final String str3 = str;
                final String str4 = str2;
                final boolean z2 = z;
                final Context context2 = context;
                zzpi.zzWR.post(new Runnable() {
                    public void run() {
                        zzd.zzgv().zza(new zzqi.zzc<zzjb>() {
                            /* renamed from: zzb */
                            public void zzd(zzjb zzjb) {
                                zzjb.zza("/appSettingsFetched", zzg.this.zzsX);
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    if (!TextUtils.isEmpty(str3)) {
                                        jSONObject.put("app_id", str3);
                                    } else if (!TextUtils.isEmpty(str4)) {
                                        jSONObject.put("ad_unit_id", str4);
                                    }
                                    jSONObject.put("is_init", z2);
                                    jSONObject.put("pn", context2.getPackageName());
                                    zzjb.zza("AFMA_fetchAppSettings", jSONObject);
                                } catch (Exception e) {
                                    zzjb.zzb("/appSettingsFetched", zzg.this.zzsX);
                                    zzpe.zzb("Error requesting application settings", e);
                                }
                            }
                        }, new zzqi.zzb());
                    }
                });
            } else {
                zzpe.zzbe("App settings could not be fetched. Required parameters missing");
            }
        }
    }
}
