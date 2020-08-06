package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;

public interface zzcm extends IInterface {

    public static abstract class zza extends Binder implements zzcm {

        /* renamed from: com.google.android.gms.internal.zzcm$zza$zza  reason: collision with other inner class name */
        private static class C1721zza implements zzcm {
            private IBinder zzrp;

            C1721zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public IBinder zza(String str, zzd zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder zzb(String str, zzd zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzcm zze(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzcm)) ? new C1721zza(iBinder) : (zzcm) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
                    IBinder zza = zza(parcel.readString(), zzd.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
                    IBinder zzb = zzb(parcel.readString(), zzd.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zzb);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IBinder zza(String str, zzd zzd);

    IBinder zzb(String str, zzd zzd);
}
