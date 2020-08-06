package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.internal.zzaan;
import com.google.android.gms.internal.zzzv;

public class zzaai implements zzaam {
    /* access modifiers changed from: private */
    public final zzaan zzazK;
    private boolean zzazL = false;

    public zzaai(zzaan zzaan) {
        this.zzazK = zzaan;
    }

    private <A extends Api.zzb> void zzd(zzzv.zza<? extends Result, A> zza) {
        this.zzazK.zzazd.zzaAx.zzb(zza);
        A zzb = this.zzazK.zzazd.zzb((Api.zzc<?>) zza.zzuH());
        if (zzb.isConnected() || !this.zzazK.zzaAG.containsKey(zza.zzuH())) {
            if (zzb instanceof zzal) {
                zzb = ((zzal) zzb).zzxG();
            }
            zza.zzb(zzb);
            return;
        }
        zza.zzA(new Status(17));
    }

    public void begin() {
    }

    public void connect() {
        if (this.zzazL) {
            this.zzazL = false;
            this.zzazK.zza((zzaan.zza) new zzaan.zza(this) {
                public void zzvA() {
                    zzaai.this.zzazK.zzaAK.zzo(null);
                }
            });
        }
    }

    public boolean disconnect() {
        if (this.zzazL) {
            return false;
        }
        if (this.zzazK.zzazd.zzvM()) {
            this.zzazL = true;
            for (zzabp zzwu : this.zzazK.zzazd.zzaAw) {
                zzwu.zzwu();
            }
            return false;
        }
        this.zzazK.zzh(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
        this.zzazK.zzh(null);
        this.zzazK.zzaAK.zzc(i, this.zzazL);
    }

    public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(T t) {
        return zzb(t);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(T t) {
        try {
            zzd(t);
        } catch (DeadObjectException e) {
            this.zzazK.zza((zzaan.zza) new zzaan.zza(this) {
                public void zzvA() {
                    zzaai.this.onConnectionSuspended(1);
                }
            });
        }
        return t;
    }

    /* access modifiers changed from: package-private */
    public void zzvz() {
        if (this.zzazL) {
            this.zzazL = false;
            this.zzazK.zzazd.zzaAx.release();
            disconnect();
        }
    }
}
