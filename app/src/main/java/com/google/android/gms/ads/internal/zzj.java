package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.p001v4.util.SimpleArrayMap;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.internal.zzdy;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzgw;
import com.google.android.gms.internal.zzhj;
import com.google.android.gms.internal.zzhk;
import com.google.android.gms.internal.zzhl;
import com.google.android.gms.internal.zzhm;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzqa;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@zzmb
public class zzj extends zzem.zza {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();
    private final zzjs zzsD;
    private final zzd zzsz;
    /* access modifiers changed from: private */
    public final zzel zzti;
    /* access modifiers changed from: private */
    @Nullable
    public final zzhj zztj;
    /* access modifiers changed from: private */
    @Nullable
    public final zzhk zztk;
    /* access modifiers changed from: private */
    public final SimpleArrayMap<String, zzhm> zztl;
    /* access modifiers changed from: private */
    public final SimpleArrayMap<String, zzhl> zztm;
    /* access modifiers changed from: private */
    public final zzgw zztn;
    private final List<String> zzto;
    /* access modifiers changed from: private */
    public final zzet zztp;
    private final String zztq;
    private final zzqa zztr;
    /* access modifiers changed from: private */
    @Nullable
    public WeakReference<zzr> zzts;

    zzj(Context context, String str, zzjs zzjs, zzqa zzqa, zzel zzel, zzhj zzhj, zzhk zzhk, SimpleArrayMap<String, zzhm> simpleArrayMap, SimpleArrayMap<String, zzhl> simpleArrayMap2, zzgw zzgw, zzet zzet, zzd zzd) {
        this.mContext = context;
        this.zztq = str;
        this.zzsD = zzjs;
        this.zztr = zzqa;
        this.zzti = zzel;
        this.zztk = zzhk;
        this.zztj = zzhj;
        this.zztl = simpleArrayMap;
        this.zztm = simpleArrayMap2;
        this.zztn = zzgw;
        this.zzto = zzcg();
        this.zztp = zzet;
        this.zzsz = zzd;
    }

    /* access modifiers changed from: private */
    public List<String> zzcg() {
        ArrayList arrayList = new ArrayList();
        if (this.zztk != null) {
            arrayList.add(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (this.zztj != null) {
            arrayList.add("2");
        }
        if (this.zztl.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return r0;
     */
    @Nullable
    public String getMediationAdapterClassName() {
        synchronized (this.zzrN) {
            if (this.zzts == null) {
                return null;
            }
            zzr zzr = (zzr) this.zzts.get();
            String mediationAdapterClassName = zzr != null ? zzr.getMediationAdapterClassName() : null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return r0;
     */
    public boolean isLoading() {
        synchronized (this.zzrN) {
            if (this.zzts == null) {
                return false;
            }
            zzr zzr = (zzr) this.zzts.get();
            boolean isLoading = zzr != null ? zzr.isLoading() : false;
        }
    }

    /* access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        zzpi.zzWR.post(runnable);
    }

    /* access modifiers changed from: protected */
    public zzr zzch() {
        return new zzr(this.mContext, this.zzsz, zzec.zzj(this.mContext), this.zztq, this.zzsD, this.zztr);
    }

    public void zzf(final zzdy zzdy) {
        runOnUiThread(new Runnable() {
            public void run() {
                synchronized (zzj.this.zzrN) {
                    zzr zzch = zzj.this.zzch();
                    WeakReference unused = zzj.this.zzts = new WeakReference(zzch);
                    zzch.zzb(zzj.this.zztj);
                    zzch.zzb(zzj.this.zztk);
                    zzch.zza((SimpleArrayMap<String, zzhm>) zzj.this.zztl);
                    zzch.zza(zzj.this.zzti);
                    zzch.zzb((SimpleArrayMap<String, zzhl>) zzj.this.zztm);
                    zzch.zzb((List<String>) zzj.this.zzcg());
                    zzch.zzb(zzj.this.zztn);
                    zzch.zza(zzj.this.zztp);
                    zzch.zzb(zzdy);
                }
            }
        });
    }
}
