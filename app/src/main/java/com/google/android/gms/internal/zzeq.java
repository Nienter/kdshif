package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzjs;

public interface zzeq extends IInterface {

    public static abstract class zza extends Binder implements zzeq {

        /* renamed from: com.google.android.gms.internal.zzeq$zza$zza  reason: collision with other inner class name */
        private static class C1730zza implements zzeq {
            private IBinder zzrp;

            C1730zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public IBinder zza(zzd zzd, zzec zzec, String str, zzjs zzjs, int i) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzjs != null) {
                        iBinder = zzjs.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder zza(zzd zzd, zzec zzec, String str, zzjs zzjs, int i, int i2) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzjs != null) {
                        iBinder = zzjs.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzeq zzr(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzeq)) ? new C1730zza(iBinder) : (zzeq) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            zzec zzec = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    zzd zzcd = zzd.zza.zzcd(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        zzec = zzec.CREATOR.createFromParcel(parcel);
                    }
                    IBinder zza = zza(zzcd, zzec, parcel.readString(), zzjs.zza.zzM(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    zzd zzcd2 = zzd.zza.zzcd(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        zzec = zzec.CREATOR.createFromParcel(parcel);
                    }
                    IBinder zza2 = zza(zzcd2, zzec, parcel.readString(), zzjs.zza.zzM(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza2);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IBinder zza(zzd zzd, zzec zzec, String str, zzjs zzjs, int i);

    IBinder zza(zzd zzd, zzec zzec, String str, zzjs zzjs, int i, int i2);
}
