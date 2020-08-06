package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzack;

public class zzacf extends zza {
    public static final Parcelable.Creator<zzacf> CREATOR = new zzacg();
    final int mVersionCode;
    private final zzach zzaFu;

    zzacf(int i, zzach zzach) {
        this.mVersionCode = i;
        this.zzaFu = zzach;
    }

    private zzacf(zzach zzach) {
        this.mVersionCode = 1;
        this.zzaFu = zzach;
    }

    public static zzacf zza(zzack.zzb<?, ?> zzb) {
        if (zzb instanceof zzach) {
            return new zzacf((zzach) zzb);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzacg.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public zzach zzxH() {
        return this.zzaFu;
    }

    public zzack.zzb<?, ?> zzxI() {
        if (this.zzaFu != null) {
            return this.zzaFu;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
