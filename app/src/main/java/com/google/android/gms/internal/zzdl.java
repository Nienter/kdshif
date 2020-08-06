package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.io.InputStream;

@zzmb
public class zzdl extends zza {
    public static final Parcelable.Creator<zzdl> CREATOR = new zzdm();
    public final int version;
    @Nullable
    private ParcelFileDescriptor zzyw;

    public zzdl() {
        this(1, null);
    }

    zzdl(int i, @Nullable ParcelFileDescriptor parcelFileDescriptor) {
        this.version = i;
        this.zzyw = parcelFileDescriptor;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzdm.zza(this, parcel, i);
    }

    public synchronized boolean zzer() {
        return this.zzyw != null;
    }

    @Nullable
    public synchronized InputStream zzes() {
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = null;
        synchronized (this) {
            if (this.zzyw != null) {
                autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.zzyw);
                this.zzyw = null;
            }
        }
        return autoCloseInputStream;
    }

    /* access modifiers changed from: package-private */
    public synchronized ParcelFileDescriptor zzet() {
        return this.zzyw;
    }
}
