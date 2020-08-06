package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzaaz;

public abstract class zzaaf<L> implements zzaaz.zzc<L> {
    private final DataHolder zzazI;

    protected zzaaf(DataHolder dataHolder) {
        this.zzazI = dataHolder;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(L l, DataHolder dataHolder);

    public final void zzs(L l) {
        zza(l, this.zzazI);
    }

    public void zzvy() {
        if (this.zzazI != null) {
            this.zzazI.close();
        }
    }
}
