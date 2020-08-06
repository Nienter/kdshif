package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class zzaad {
    /* access modifiers changed from: private */
    public final Map<zzzx<?>, Boolean> zzazC = Collections.synchronizedMap(new WeakHashMap());
    /* access modifiers changed from: private */
    public final Map<TaskCompletionSource<?>, Boolean> zzazD = Collections.synchronizedMap(new WeakHashMap());

    private void zza(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.zzazC) {
            hashMap = new HashMap(this.zzazC);
        }
        synchronized (this.zzazD) {
            hashMap2 = new HashMap(this.zzazD);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((zzzx) entry.getKey()).zzB(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new zza(status));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(final zzzx<? extends Result> zzzx, boolean z) {
        this.zzazC.put(zzzx, Boolean.valueOf(z));
        zzzx.zza((PendingResult.zza) new PendingResult.zza() {
            public void zzx(Status status) {
                zzaad.this.zzazC.remove(zzzx);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public <TResult> void zza(final TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.zzazD.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener<TResult>() {
            public void onComplete(@NonNull Task<TResult> task) {
                zzaad.this.zzazD.remove(taskCompletionSource);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean zzvu() {
        return !this.zzazC.isEmpty() || !this.zzazD.isEmpty();
    }

    public void zzvv() {
        zza(false, zzaap.zzaAO);
    }

    public void zzvw() {
        zza(true, zzabq.zzaBV);
    }
}
