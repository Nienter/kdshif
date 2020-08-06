package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzm;
import com.google.android.gms.internal.zzs;
import java.util.Collections;
import java.util.Map;

public abstract class zzk<T> implements Comparable<zzk<T>> {
    /* access modifiers changed from: private */
    public final zzs.zza zzB;
    private final int zzC;
    private final String zzD;
    private final int zzE;
    private final zzm.zza zzF;
    private Integer zzG;
    private zzl zzH;
    private boolean zzI;
    private boolean zzJ;
    private boolean zzK;
    private long zzL;
    private zzo zzM;
    private zzb.zza zzN;

    public enum zza {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public zzk(int i, String str, zzm.zza zza2) {
        this.zzB = zzs.zza.zzai ? new zzs.zza() : null;
        this.zzI = true;
        this.zzJ = false;
        this.zzK = false;
        this.zzL = 0;
        this.zzN = null;
        this.zzC = i;
        this.zzD = str;
        this.zzF = zza2;
        zza((zzo) new zzd());
        this.zzE = zzb(str);
    }

    private static int zzb(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    public int getMethod() {
        return this.zzC;
    }

    public String getUrl() {
        return this.zzD;
    }

    public String toString() {
        String valueOf = String.valueOf(Integer.toHexString(zzf()));
        String concat = valueOf.length() != 0 ? "0x".concat(valueOf) : new String("0x");
        String valueOf2 = String.valueOf(getUrl());
        String valueOf3 = String.valueOf(zzo());
        String valueOf4 = String.valueOf(this.zzG);
        return new StringBuilder(String.valueOf("[ ] ").length() + 3 + String.valueOf(valueOf2).length() + String.valueOf(concat).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length()).append("[ ] ").append(valueOf2).append(" ").append(concat).append(" ").append(valueOf3).append(" ").append(valueOf4).toString();
    }

    public final zzk<?> zza(int i) {
        this.zzG = Integer.valueOf(i);
        return this;
    }

    public zzk<?> zza(zzb.zza zza2) {
        this.zzN = zza2;
        return this;
    }

    public zzk<?> zza(zzl zzl) {
        this.zzH = zzl;
        return this;
    }

    public zzk<?> zza(zzo zzo) {
        this.zzM = zzo;
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract zzm<T> zza(zzi zzi);

    /* access modifiers changed from: protected */
    public abstract void zza(T t);

    /* access modifiers changed from: protected */
    public zzr zzb(zzr zzr) {
        return zzr;
    }

    /* renamed from: zzc */
    public int compareTo(zzk<T> zzk) {
        zza zzo = zzo();
        zza zzo2 = zzk.zzo();
        return zzo == zzo2 ? this.zzG.intValue() - zzk.zzG.intValue() : zzo2.ordinal() - zzo.ordinal();
    }

    public void zzc(zzr zzr) {
        if (this.zzF != null) {
            this.zzF.zze(zzr);
        }
    }

    public void zzc(String str) {
        if (zzs.zza.zzai) {
            this.zzB.zza(str, Thread.currentThread().getId());
        } else if (this.zzL == 0) {
            this.zzL = SystemClock.elapsedRealtime();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(final String str) {
        if (this.zzH != null) {
            this.zzH.zzf(this);
        }
        if (zzs.zza.zzai) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        zzk.this.zzB.zza(str, id);
                        zzk.this.zzB.zzd(toString());
                    }
                });
                return;
            }
            this.zzB.zza(str, id);
            this.zzB.zzd(toString());
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzL;
        if (elapsedRealtime >= 3000) {
            zzs.zzb("%d ms: %s", Long.valueOf(elapsedRealtime), toString());
        }
    }

    public int zzf() {
        return this.zzE;
    }

    public String zzg() {
        return getUrl();
    }

    public zzb.zza zzh() {
        return this.zzN;
    }

    @Deprecated
    public String zzi() {
        return zzl();
    }

    @Deprecated
    public byte[] zzj() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String zzk() {
        return "UTF-8";
    }

    public String zzl() {
        String valueOf = String.valueOf(zzk());
        return valueOf.length() != 0 ? "application/x-www-form-urlencoded; charset=".concat(valueOf) : new String("application/x-www-form-urlencoded; charset=");
    }

    public byte[] zzm() {
        return null;
    }

    public final boolean zzn() {
        return this.zzI;
    }

    public zza zzo() {
        return zza.NORMAL;
    }

    public final int zzp() {
        return this.zzM.zzc();
    }

    public zzo zzq() {
        return this.zzM;
    }

    public void zzr() {
        this.zzK = true;
    }

    public boolean zzs() {
        return this.zzK;
    }
}
