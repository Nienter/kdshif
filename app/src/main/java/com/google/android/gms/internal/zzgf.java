package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzv;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzmb
public class zzgf {
    boolean zzFa;
    private final List<zzgd> zzFr = new LinkedList();
    private final Map<String, String> zzFs = new LinkedHashMap();
    private String zzFt;
    private zzgd zzFu;
    @Nullable
    private zzgf zzFv;
    private final Object zzrN = new Object();

    public zzgf(boolean z, String str, String str2) {
        this.zzFa = z;
        this.zzFs.put(NativeProtocol.WEB_DIALOG_ACTION, str);
        this.zzFs.put("ad_format", str2);
    }

    public void zzX(String str) {
        if (this.zzFa) {
            synchronized (this.zzrN) {
                this.zzFt = str;
            }
        }
    }

    public boolean zza(zzgd zzgd, long j, String... strArr) {
        synchronized (this.zzrN) {
            for (String zzgd2 : strArr) {
                this.zzFr.add(new zzgd(j, zzgd2, zzgd));
            }
        }
        return true;
    }

    public boolean zza(@Nullable zzgd zzgd, String... strArr) {
        if (!this.zzFa || zzgd == null) {
            return false;
        }
        return zza(zzgd, zzv.zzcP().elapsedRealtime(), strArr);
    }

    @Nullable
    public zzgd zzc(long j) {
        if (!this.zzFa) {
            return null;
        }
        return new zzgd(j, null, null);
    }

    public void zzc(@Nullable zzgf zzgf) {
        synchronized (this.zzrN) {
            this.zzFv = zzgf;
        }
    }

    public zzgd zzfA() {
        zzgd zzgd;
        synchronized (this.zzrN) {
            zzgd = this.zzFu;
        }
        return zzgd;
    }

    public zzgd zzfw() {
        return zzc(zzv.zzcP().elapsedRealtime());
    }

    public void zzfx() {
        synchronized (this.zzrN) {
            this.zzFu = zzfw();
        }
    }

    public String zzfy() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        synchronized (this.zzrN) {
            for (zzgd next : this.zzFr) {
                long time = next.getTime();
                String zzft = next.zzft();
                zzgd zzfu = next.zzfu();
                if (zzfu != null && time > 0) {
                    sb2.append(zzft).append('.').append(time - zzfu.getTime()).append(',');
                }
            }
            this.zzFr.clear();
            if (!TextUtils.isEmpty(this.zzFt)) {
                sb2.append(this.zzFt);
            } else if (sb2.length() > 0) {
                sb2.setLength(sb2.length() - 1);
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzfz() {
        Map<String, String> zza;
        synchronized (this.zzrN) {
            zzfz zzjN = zzv.zzcN().zzjN();
            zza = (zzjN == null || this.zzFv == null) ? this.zzFs : zzjN.zza(this.zzFs, this.zzFv.zzfz());
        }
        return zza;
    }

    public void zzg(String str, String str2) {
        if (this.zzFa && !TextUtils.isEmpty(str2)) {
            zzfz zzjN = zzv.zzcN().zzjN();
            if (zzjN != null) {
                synchronized (this.zzrN) {
                    zzjN.zzV(str).zza(this.zzFs, str, str2);
                }
            }
        }
    }
}
