package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.api.Api;

public class zzal<T extends IInterface> extends zzl<T> {
    private final Api.zzg<T> zzaFm;

    /* access modifiers changed from: protected */
    public String zzeu() {
        return this.zzaFm.zzeu();
    }

    /* access modifiers changed from: protected */
    public String zzev() {
        return this.zzaFm.zzev();
    }

    /* access modifiers changed from: protected */
    public T zzh(IBinder iBinder) {
        return this.zzaFm.zzh(iBinder);
    }

    public Api.zzg<T> zzxG() {
        return this.zzaFm;
    }
}
