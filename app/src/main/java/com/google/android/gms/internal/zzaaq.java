package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.internal.zzzv;

public class zzaaq<O extends Api.ApiOptions> extends zzaah {
    private final zzc<O> zzaBl;

    public zzaaq(zzc<O> zzc) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.zzaBl = zzc;
    }

    public Looper getLooper() {
        return this.zzaBl.getLooper();
    }

    public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(@NonNull T t) {
        return this.zzaBl.doRead(t);
    }

    public void zza(zzabp zzabp) {
    }

    public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(@NonNull T t) {
        return this.zzaBl.doWrite(t);
    }

    public void zzb(zzabp zzabp) {
    }
}
