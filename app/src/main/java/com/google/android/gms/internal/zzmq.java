package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.zzmr;

public interface zzmq extends IInterface {

    public static abstract class zza extends Binder implements zzmq {

        /* renamed from: com.google.android.gms.internal.zzmq$zza$zza  reason: collision with other inner class name */
        private static class C1764zza implements zzmq {
            private IBinder zzrp;

            C1764zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public void zza(zzmh zzmh, zzmr zzmr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
                    if (zzmh != null) {
                        obtain.writeInt(1);
                        zzmh.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzmr != null ? zzmr.asBinder() : null);
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzmk zzd(zzmh zzmh) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
                    if (zzmh != null) {
                        obtain.writeInt(1);
                        zzmh.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? zzmk.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
        }

        public static zzmq zzae(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzmq)) ? new C1764zza(iBinder) : (zzmq) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            zzmh zzmh = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                    if (parcel.readInt() != 0) {
                        zzmh = zzmh.CREATOR.createFromParcel(parcel);
                    }
                    zzmk zzd = zzd(zzmh);
                    parcel2.writeNoException();
                    if (zzd != null) {
                        parcel2.writeInt(1);
                        zzd.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                    if (parcel.readInt() != 0) {
                        zzmh = zzmh.CREATOR.createFromParcel(parcel);
                    }
                    zza(zzmh, zzmr.zza.zzaf(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zza(zzmh zzmh, zzmr zzmr);

    zzmk zzd(zzmh zzmh);
}
