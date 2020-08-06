package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzbgy;

public interface zzbgz extends IInterface {

    public static abstract class zza extends Binder implements zzbgz {

        /* renamed from: com.google.android.gms.internal.zzbgz$zza$zza  reason: collision with other inner class name */
        private static class C1717zza implements zzbgz {
            private IBinder zzrp;

            C1717zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public zzbgy zza(zzd zzd, zzbgw zzbgw) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzbgw != null) {
                        obtain.writeInt(1);
                        zzbgw.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzbgy.zza.zzfi(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzbgz zzfj(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzbgz)) ? new C1717zza(iBinder) : (zzbgz) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
                    zzbgy zza = zza(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzbgw.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (zza != null) {
                        iBinder = zza.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzbgy zza(zzd zzd, zzbgw zzbgw);
}
