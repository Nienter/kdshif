package com.google.android.gms.internal;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzate;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class zzatq extends zzate.zza {
    /* access modifiers changed from: private */
    public final zzatp zzbpw;
    private Boolean zzbtL;
    @Nullable
    private String zzbtM;

    public zzatq(zzatp zzatp) {
        this(zzatp, null);
    }

    public zzatq(zzatp zzatp, @Nullable String str) {
        zzac.zzw(zzatp);
        this.zzbpw = zzatp;
        this.zzbtM = str;
    }

    @BinderThread
    private void zzb(zzasq zzasq, boolean z) {
        zzac.zzw(zzasq);
        zzm(zzasq.packageName, z);
        this.zzbpw.zzJp().zzgd(zzasq.zzbqf);
    }

    @BinderThread
    private void zzm(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.zzbpw.zzJt().zzLa().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        try {
            zzn(str, z);
        } catch (SecurityException e) {
            this.zzbpw.zzJt().zzLa().zzj("Measurement Service called with invalid calling package. appId", zzati.zzfI(str));
            throw e;
        }
    }

    @BinderThread
    public List<zzaub> zza(final zzasq zzasq, boolean z) {
        zzb(zzasq, false);
        try {
            List<zzaud> list = (List) this.zzbpw.zzJs().zzd(new Callable<List<zzaud>>() {
                /* renamed from: zzLO */
                public List<zzaud> call() {
                    zzatq.this.zzbpw.zzLL();
                    return zzatq.this.zzbpw.zzJo().zzfx(zzasq.packageName);
                }
            }).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzaud zzaud : list) {
                if (z || !zzaue.zzgg(zzaud.mName)) {
                    arrayList.add(new zzaub(zzaud));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbpw.zzJt().zzLa().zze("Failed to get user attributes. appId", zzati.zzfI(zzasq.packageName), e);
            return null;
        }
    }

    @BinderThread
    public void zza(long j, String str, String str2, String str3) {
        final String str4 = str2;
        final String str5 = str3;
        final String str6 = str;
        final long j2 = j;
        this.zzbpw.zzJs().zzm(new Runnable() {
            public void run() {
                if (str4 == null) {
                    zzatq.this.zzbpw.zzJm().zza(str5, (AppMeasurement.zzf) null);
                    return;
                }
                AppMeasurement.zzf zzf = new AppMeasurement.zzf();
                zzf.zzbpz = str6;
                zzf.zzbpA = str4;
                zzf.zzbpB = j2;
                zzatq.this.zzbpw.zzJm().zza(str5, zzf);
            }
        });
    }

    @BinderThread
    public void zza(final zzasq zzasq) {
        zzb(zzasq, false);
        this.zzbpw.zzJs().zzm(new Runnable() {
            public void run() {
                zzatq.this.zzbpw.zzLL();
                zzatq.this.zzbpw.zze(zzasq);
            }
        });
    }

    @BinderThread
    public void zza(final zzatb zzatb, final zzasq zzasq) {
        zzac.zzw(zzatb);
        zzb(zzasq, false);
        this.zzbpw.zzJs().zzm(new Runnable() {
            public void run() {
                zzatq.this.zzbpw.zzLL();
                zzatq.this.zzbpw.zzb(zzatb, zzasq);
            }
        });
    }

    @BinderThread
    public void zza(final zzatb zzatb, final String str, String str2) {
        zzac.zzw(zzatb);
        zzac.zzdv(str);
        zzm(str, true);
        this.zzbpw.zzJs().zzm(new Runnable() {
            public void run() {
                zzatq.this.zzbpw.zzLL();
                zzatq.this.zzbpw.zzb(zzatb, str);
            }
        });
    }

    @BinderThread
    public void zza(final zzaub zzaub, final zzasq zzasq) {
        zzac.zzw(zzaub);
        zzb(zzasq, false);
        if (zzaub.getValue() == null) {
            this.zzbpw.zzJs().zzm(new Runnable() {
                public void run() {
                    zzatq.this.zzbpw.zzLL();
                    zzatq.this.zzbpw.zzc(zzaub, zzasq);
                }
            });
        } else {
            this.zzbpw.zzJs().zzm(new Runnable() {
                public void run() {
                    zzatq.this.zzbpw.zzLL();
                    zzatq.this.zzbpw.zzb(zzaub, zzasq);
                }
            });
        }
    }

    @BinderThread
    public byte[] zza(final zzatb zzatb, final String str) {
        zzac.zzdv(str);
        zzac.zzw(zzatb);
        zzm(str, true);
        this.zzbpw.zzJt().zzLf().zzj("Log and bundle. event", zzatb.name);
        long nanoTime = this.zzbpw.zznq().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzbpw.zzJs().zze(new Callable<byte[]>() {
                /* renamed from: zzLN */
                public byte[] call() {
                    zzatq.this.zzbpw.zzLL();
                    return zzatq.this.zzbpw.zza(zzatb, str);
                }
            }).get();
            if (bArr == null) {
                this.zzbpw.zzJt().zzLa().zzj("Log and bundle returned null. appId", zzati.zzfI(str));
                bArr = new byte[0];
            }
            this.zzbpw.zzJt().zzLf().zzd("Log and bundle processed. event, size, time_ms", zzatb.name, Integer.valueOf(bArr.length), Long.valueOf((this.zzbpw.zznq().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbpw.zzJt().zzLa().zzd("Failed to log and bundle. appId, event, error", zzati.zzfI(str), zzatb.name, e);
            return null;
        }
    }

    @BinderThread
    public void zzb(final zzasq zzasq) {
        zzb(zzasq, false);
        this.zzbpw.zzJs().zzm(new Runnable() {
            public void run() {
                zzatq.this.zzbpw.zzLL();
                zzatq.this.zzbpw.zzd(zzasq);
            }
        });
    }

    @BinderThread
    public String zzc(zzasq zzasq) {
        zzb(zzasq, false);
        return this.zzbpw.zzfR(zzasq.packageName);
    }

    /* access modifiers changed from: protected */
    public void zzn(String str, boolean z) {
        if (z) {
            if (this.zzbtL == null) {
                this.zzbtL = Boolean.valueOf("com.google.android.gms".equals(this.zzbtM) || zzx.zzf(this.zzbpw.getContext(), Binder.getCallingUid()) || zzf.zzav(this.zzbpw.getContext()).zza(this.zzbpw.getContext().getPackageManager(), Binder.getCallingUid()));
            }
            if (this.zzbtL.booleanValue()) {
                return;
            }
        }
        if (this.zzbtM == null && zze.zzc(this.zzbpw.getContext(), Binder.getCallingUid(), str)) {
            this.zzbtM = str;
        }
        if (!str.equals(this.zzbtM)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }
}
