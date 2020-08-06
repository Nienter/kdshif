package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;

public interface zzbhi extends IInterface {

    public static abstract class zza extends Binder implements zzbhi {

        /* renamed from: com.google.android.gms.internal.zzbhi$zza$zza  reason: collision with other inner class name */
        private static class C1718zza implements zzbhi {
            private IBinder zzrp;

            C1718zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public void zzSu() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzbhk[] zza(zzd zzd, zzbhd zzbhd, zzbhm zzbhm) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzbhd != null) {
                        obtain.writeInt(1);
                        zzbhd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzbhm != null) {
                        obtain.writeInt(1);
                        zzbhm.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return (zzbhk[]) obtain2.createTypedArray(zzbhk.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzbhk[] zzd(zzd zzd, zzbhd zzbhd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzbhd != null) {
                        obtain.writeInt(1);
                        zzbhd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (zzbhk[]) obtain2.createTypedArray(zzbhk.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzbhi zzfk(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzbhi)) ? new C1718zza(iBinder) : (zzbhi) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
                    zzbhk[] zzd = zzd(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzbhd.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zzd, 1);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
                    zzSu();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
                    zzbhk[] zza = zza(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzbhd.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? zzbhm.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zza, 1);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zzSu();

    zzbhk[] zza(zzd zzd, zzbhd zzbhd, zzbhm zzbhm);

    zzbhk[] zzd(zzd zzd, zzbhd zzbhd);
}
