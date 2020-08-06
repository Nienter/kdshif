package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;

public interface zzbgy extends IInterface {

    public static abstract class zza extends Binder implements zzbgy {

        /* renamed from: com.google.android.gms.internal.zzbgy$zza$zza  reason: collision with other inner class name */
        private static class C1716zza implements zzbgy {
            private IBinder zzrp;

            C1716zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public void zzSo() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
                    this.zzrp.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzbgu[] zzc(zzd zzd, zzbhd zzbhd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzbhd != null) {
                        obtain.writeInt(1);
                        zzbhd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (zzbgu[]) obtain2.createTypedArray(zzbgu.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zznw(int i) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
                    obtain.writeInt(i);
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzbgy zzfi(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzbgy)) ? new C1716zza(iBinder) : (zzbgy) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
                    zzbgu[] zzc = zzc(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzbhd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zzc, 1);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
                    boolean zznw = zznw(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zznw ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
                    zzSo();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zzSo();

    zzbgu[] zzc(zzd zzd, zzbhd zzbhd);

    boolean zznw(int i);
}
