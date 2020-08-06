package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.zzda;
import com.google.android.gms.internal.zzpg;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Future;

@zzmb
public class zzoy implements zzda.zzb, zzpg.zzb {
    private Context mContext;
    private String zzHY;
    private boolean zzTB = true;
    private boolean zzTC = true;
    private boolean zzTD = true;
    private boolean zzTL = false;
    private final String zzVW;
    private final zzoz zzVX;
    private BigInteger zzVY = BigInteger.ONE;
    private final HashSet<zzow> zzVZ = new HashSet<>();
    private final HashMap<String, zzpb> zzWa = new HashMap<>();
    private boolean zzWb = false;
    private int zzWc = 0;
    private zzfz zzWd = null;
    private zzdb zzWe = null;
    private String zzWf;
    private String zzWg;
    private Boolean zzWh = null;
    private boolean zzWi = false;
    private boolean zzWj = false;
    private boolean zzWk = false;
    private String zzWl = "";
    private long zzWm = 0;
    private long zzWn = 0;
    private int zzWo = -1;
    private final Object zzrN = new Object();
    private zzcp zzsy;
    private boolean zztW = false;
    private zzqa zztr;
    private zzcz zzxE = null;

    public zzoy(zzpi zzpi) {
        this.zzVW = zzpi.zzkl();
        this.zzVX = new zzoz(this.zzVW);
    }

    public Resources getResources() {
        if (this.zztr.zzYd) {
            return this.mContext.getResources();
        }
        try {
            DynamiteModule zza = DynamiteModule.zza(this.mContext, DynamiteModule.zzaQw, ModuleDescriptor.MODULE_ID);
            if (zza != null) {
                return zza.zzBd().getResources();
            }
            return null;
        } catch (DynamiteModule.zza e) {
            zzpe.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public String getSessionId() {
        return this.zzVW;
    }

    public void zzE(boolean z) {
        synchronized (this.zzrN) {
            if (this.zzTC != z) {
                zzpg.zze(this.mContext, z);
            }
            this.zzTC = z;
            zzdb zzw = zzw(this.mContext);
            if (zzw != null && !zzw.isAlive()) {
                zzpe.zzbd("start fetching content...");
                zzw.zzee();
            }
        }
    }

    public void zzF(boolean z) {
        synchronized (this.zzrN) {
            if (this.zzTD != z) {
                zzpg.zze(this.mContext, z);
            }
            zzpg.zze(this.mContext, z);
            this.zzTD = z;
            zzdb zzw = zzw(this.mContext);
            if (zzw != null && !zzw.isAlive()) {
                zzpe.zzbd("start fetching content...");
                zzw.zzee();
            }
        }
    }

    public void zzG(boolean z) {
        this.zzWk = z;
    }

    public void zzH(boolean z) {
        synchronized (this.zzrN) {
            this.zzWi = z;
        }
    }

    public Bundle zza(Context context, zzpa zzpa, String str) {
        Bundle bundle;
        synchronized (this.zzrN) {
            bundle = new Bundle();
            bundle.putBundle("app", this.zzVX.zze(context, str));
            Bundle bundle2 = new Bundle();
            for (String next : this.zzWa.keySet()) {
                bundle2.putBundle(next, this.zzWa.get(next).toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator<zzow> it = this.zzVZ.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            zzpa.zza(this.zzVZ);
            this.zzVZ.clear();
        }
        return bundle;
    }

    public void zza(zzow zzow) {
        synchronized (this.zzrN) {
            this.zzVZ.add(zzow);
        }
    }

    public void zza(String str, zzpb zzpb) {
        synchronized (this.zzrN) {
            this.zzWa.put(str, zzpb);
        }
    }

    public void zza(Throwable th, String str) {
        zzlz.zzb(this.mContext, this.zztr).zza(th, str);
    }

    public Future zzaS(String str) {
        Future future;
        synchronized (this.zzrN) {
            if (str != null) {
                if (!str.equals(this.zzWf)) {
                    this.zzWf = str;
                    future = zzpg.zzf(this.mContext, str);
                }
            }
            future = null;
        }
        return future;
    }

    public Future zzaT(String str) {
        Future future;
        synchronized (this.zzrN) {
            if (str != null) {
                if (!str.equals(this.zzWg)) {
                    this.zzWg = str;
                    future = zzpg.zzg(this.mContext, str);
                }
            }
            future = null;
        }
        return future;
    }

    public Future zzaf(int i) {
        Future zza;
        synchronized (this.zzrN) {
            this.zzWo = i;
            zza = zzpg.zza(this.mContext, i);
        }
        return zza;
    }

    public void zzb(Boolean bool) {
        synchronized (this.zzrN) {
            this.zzWh = bool;
        }
    }

    public void zzb(HashSet<zzow> hashSet) {
        synchronized (this.zzrN) {
            this.zzVZ.addAll(hashSet);
        }
    }

    public Future zzc(Context context, boolean z) {
        Future future;
        synchronized (this.zzrN) {
            if (z != this.zzTB) {
                this.zzTB = z;
                future = zzpg.zzc(context, z);
            } else {
                future = null;
            }
        }
        return future;
    }

    @TargetApi(23)
    public void zzc(Context context, zzqa zzqa) {
        synchronized (this.zzrN) {
            if (!this.zztW) {
                this.mContext = context.getApplicationContext();
                this.zztr = zzqa;
                zzv.zzcM().zza(this);
                zzpg.zza(context, (zzpg.zzb) this);
                zzpg.zzb(context, this);
                zzpg.zzc(context, (zzpg.zzb) this);
                zzpg.zzd(context, this);
                zzpg.zze(context, (zzpg.zzb) this);
                zzpg.zzf(context, (zzpg.zzb) this);
                zzpg.zzg(context, (zzpg.zzb) this);
                zzka();
                this.zzHY = zzv.zzcJ().zzh(context, zzqa.zzaZ);
                if (zzs.zzyJ() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                    this.zzWj = true;
                }
                this.zzsy = new zzcp(context.getApplicationContext(), this.zztr, zzv.zzcJ().zzd(context, zzqa));
                zzkc();
                zzv.zzcX().zzr(this.mContext);
                this.zztW = true;
            }
        }
    }

    public Future zzd(Context context, String str) {
        Future future;
        this.zzWm = zzv.zzcP().currentTimeMillis();
        synchronized (this.zzrN) {
            if (str != null) {
                if (!str.equals(this.zzWl)) {
                    this.zzWl = str;
                    future = zzpg.zza(context, str, this.zzWm);
                }
            }
            future = null;
        }
        return future;
    }

    public Future zzd(Context context, boolean z) {
        Future future;
        synchronized (this.zzrN) {
            if (z != this.zzTL) {
                this.zzTL = z;
                future = zzpg.zzf(context, z);
            } else {
                future = null;
            }
        }
        return future;
    }

    public void zzh(Bundle bundle) {
        synchronized (this.zzrN) {
            this.zzTB = bundle.getBoolean("use_https", this.zzTB);
            this.zzWc = bundle.getInt("webview_cache_version", this.zzWc);
            if (bundle.containsKey("content_url_opted_out")) {
                zzE(bundle.getBoolean("content_url_opted_out"));
            }
            if (bundle.containsKey("content_url_hashes")) {
                this.zzWf = bundle.getString("content_url_hashes");
            }
            this.zzTL = bundle.getBoolean("auto_collect_location", this.zzTL);
            if (bundle.containsKey("content_vertical_opted_out")) {
                zzF(bundle.getBoolean("content_vertical_opted_out"));
            }
            if (bundle.containsKey("content_vertical_hashes")) {
                this.zzWg = bundle.getString("content_vertical_hashes");
            }
            this.zzWl = bundle.containsKey("app_settings_json") ? bundle.getString("app_settings_json") : this.zzWl;
            this.zzWm = bundle.getLong("app_settings_last_update_ms", this.zzWm);
            this.zzWn = bundle.getLong("app_last_background_time_ms", this.zzWn);
            this.zzWo = bundle.getInt("request_in_session_count", this.zzWo);
        }
    }

    public boolean zzjJ() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzTC;
        }
        return z;
    }

    public boolean zzjK() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzTD;
        }
        return z;
    }

    public String zzjL() {
        String bigInteger;
        synchronized (this.zzrN) {
            bigInteger = this.zzVY.toString();
            this.zzVY = this.zzVY.add(BigInteger.ONE);
        }
        return bigInteger;
    }

    public zzoz zzjM() {
        zzoz zzoz;
        synchronized (this.zzrN) {
            zzoz = this.zzVX;
        }
        return zzoz;
    }

    public zzfz zzjN() {
        zzfz zzfz;
        synchronized (this.zzrN) {
            zzfz = this.zzWd;
        }
        return zzfz;
    }

    public boolean zzjO() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzWb;
            this.zzWb = true;
        }
        return z;
    }

    public boolean zzjP() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzTB || this.zzWj;
        }
        return z;
    }

    public String zzjQ() {
        String str;
        synchronized (this.zzrN) {
            str = this.zzHY;
        }
        return str;
    }

    public String zzjR() {
        String str;
        synchronized (this.zzrN) {
            str = this.zzWf;
        }
        return str;
    }

    public String zzjS() {
        String str;
        synchronized (this.zzrN) {
            str = this.zzWg;
        }
        return str;
    }

    public Boolean zzjT() {
        Boolean bool;
        synchronized (this.zzrN) {
            bool = this.zzWh;
        }
        return bool;
    }

    public boolean zzjU() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzTL;
        }
        return z;
    }

    public long zzjV() {
        long j;
        synchronized (this.zzrN) {
            j = this.zzWn;
        }
        return j;
    }

    public int zzjW() {
        int i;
        synchronized (this.zzrN) {
            i = this.zzWo;
        }
        return i;
    }

    public boolean zzjX() {
        return this.zzWk;
    }

    public zzox zzjY() {
        zzox zzox;
        synchronized (this.zzrN) {
            zzox = new zzox(this.zzWl, this.zzWm);
        }
        return zzox;
    }

    public zzcp zzjZ() {
        return this.zzsy;
    }

    public void zzk(boolean z) {
        if (!z) {
            zzo(zzv.zzcP().currentTimeMillis());
            zzaf(this.zzVX.zzjW());
        } else if (zzv.zzcP().currentTimeMillis() - this.zzWn > zzfx.zzCv.get().longValue()) {
            this.zzVX.zzag(-1);
        } else {
            this.zzVX.zzag(this.zzWo);
        }
    }

    public void zzka() {
        zzlz.zzb(this.mContext, this.zztr);
    }

    public boolean zzkb() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzWi;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void zzkc() {
        try {
            this.zzWd = zzv.zzcQ().zza(new zzfy(this.mContext, this.zztr.zzaZ));
        } catch (IllegalArgumentException e) {
            zzpe.zzc("Cannot initialize CSI reporter.", e);
        }
    }

    public Future zzo(long j) {
        Future future;
        synchronized (this.zzrN) {
            if (this.zzWn < j) {
                this.zzWn = j;
                future = zzpg.zza(this.mContext, j);
            } else {
                future = null;
            }
        }
        return future;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return null;
     */
    public zzdb zzw(Context context) {
        if (!zzfx.zzBO.get().booleanValue()) {
            return null;
        }
        if (!zzs.zzyA()) {
            return null;
        }
        if (!zzfx.zzBW.get().booleanValue() && !zzfx.zzBU.get().booleanValue()) {
            return null;
        }
        if (zzjJ() && zzjK()) {
            return null;
        }
        synchronized (this.zzrN) {
            if (Looper.getMainLooper() != null && context != null) {
                if (this.zzxE == null) {
                    this.zzxE = new zzcz();
                }
                if (this.zzWe == null) {
                    this.zzWe = new zzdb(this.zzxE, zzlz.zzb(this.mContext, this.zztr));
                }
                this.zzWe.zzee();
                zzdb zzdb = this.zzWe;
                return zzdb;
            }
        }
    }
}
