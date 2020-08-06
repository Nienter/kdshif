package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new Parcelable.Creator<BinderWrapper>() {
        /* renamed from: zzaN */
        public BinderWrapper createFromParcel(Parcel parcel) {
            return new BinderWrapper(parcel);
        }

        /* renamed from: zzcN */
        public BinderWrapper[] newArray(int i) {
            return new BinderWrapper[i];
        }
    };
    private IBinder zzaEa;

    public BinderWrapper() {
        this.zzaEa = null;
    }

    public BinderWrapper(IBinder iBinder) {
        this.zzaEa = null;
        this.zzaEa = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.zzaEa = null;
        this.zzaEa = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zzaEa);
    }
}
