package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.zzzm;

public interface zzzn extends IInterface {

    public static abstract class zza extends Binder implements zzzn {

        /* renamed from: com.google.android.gms.internal.zzzn$zza$zza  reason: collision with other inner class name */
        private static class C1773zza implements zzzn {
            private IBinder zzrp;

            C1773zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public void zza(zzzm zzzm) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    if (zzzm != null) {
                        iBinder = zzzm.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrp.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void zza(zzzm zzzm, zzzh zzzh) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    if (zzzm != null) {
                        iBinder = zzzm.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (zzzh != null) {
                        obtain.writeInt(1);
                        zzzh.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static zzzn zzbo(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzzn)) ? new C1773zza(iBinder) : (zzzn) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    zza(zzzm.zza.zzbn(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzzh.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    zza(zzzm.zza.zzbn(parcel.readStrongBinder()));
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zza(zzzm zzzm);

    void zza(zzzm zzzm, zzzh zzzh);
}
