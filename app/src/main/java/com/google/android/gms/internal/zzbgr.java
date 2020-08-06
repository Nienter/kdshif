package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.vision.barcode.Barcode;

public interface zzbgr extends IInterface {

    public static abstract class zza extends Binder implements zzbgr {

        /* renamed from: com.google.android.gms.internal.zzbgr$zza$zza  reason: collision with other inner class name */
        private static class C1714zza implements zzbgr {
            private IBinder zzrp;

            C1714zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public void zzSo() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
                    this.zzrp.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Barcode[] zza(zzd zzd, zzbhd zzbhd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzbhd != null) {
                        obtain.writeInt(1);
                        zzbhd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Barcode[]) obtain2.createTypedArray(Barcode.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Barcode[] zzb(zzd zzd, zzbhd zzbhd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzbhd != null) {
                        obtain.writeInt(1);
                        zzbhd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Barcode[]) obtain2.createTypedArray(Barcode.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzbgr zzfg(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzbgr)) ? new C1714zza(iBinder) : (zzbgr) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            zzbhd zzbhd = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
                    zzd zzcd = zzd.zza.zzcd(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        zzbhd = zzbhd.CREATOR.createFromParcel(parcel);
                    }
                    Barcode[] zza = zza(zzcd, zzbhd);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zza, 1);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
                    zzd zzcd2 = zzd.zza.zzcd(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        zzbhd = zzbhd.CREATOR.createFromParcel(parcel);
                    }
                    Barcode[] zzb = zzb(zzcd2, zzbhd);
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zzb, 1);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
                    zzSo();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zzSo();

    Barcode[] zza(zzd zzd, zzbhd zzbhd);

    Barcode[] zzb(zzd zzd, zzbhd zzbhd);
}
