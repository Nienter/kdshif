package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzjw;
import com.google.android.gms.internal.zzjx;
import com.google.android.gms.internal.zzoi;
import java.util.List;

public interface zzjt extends IInterface {

    public static abstract class zza extends Binder implements zzjt {

        /* renamed from: com.google.android.gms.internal.zzjt$zza$zza  reason: collision with other inner class name */
        private static class C1751zza implements zzjt {
            private IBinder zzrp;

            C1751zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public void destroy() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getInterstitialAdapterInfo() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd getView() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isInitialized() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(13, obtain, obtain2, 0);
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

            public void pause() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resume() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showInterstitial() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showVideo() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzdy zzdy, String str, zzju zzju) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzju != null) {
                        iBinder = zzju.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrp.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzdy zzdy, String str, zzoi zzoi, String str2) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzoi != null) {
                        iBinder = zzoi.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str2);
                    this.zzrp.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzju != null) {
                        iBinder = zzju.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrp.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju, zzgw zzgw, List<String> list) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzju != null) {
                        iBinder = zzju.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (zzgw != null) {
                        obtain.writeInt(1);
                        zzgw.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringList(list);
                    this.zzrp.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, zzju zzju) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzju != null) {
                        iBinder = zzju.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrp.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, String str2, zzju zzju) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzju != null) {
                        iBinder = zzju.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrp.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzdy zzdy, String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.zzrp.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzc(zzdy zzdy, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (zzdy != null) {
                        obtain.writeInt(1);
                        zzdy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.zzrp.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzjw zzgJ() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzjw.zza.zzQ(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzjx zzgK() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzjx.zza.zzR(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle zzgL() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle zzgM() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrp.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzj(zzd zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    this.zzrp.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }

        public static zzjt zzN(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzjt)) ? new C1751zza(iBinder) : (zzjt) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: com.google.android.gms.internal.zzgw} */
        /* JADX WARNING: type inference failed for: r6v0 */
        /* JADX WARNING: type inference failed for: r6v1, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v3, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v6 */
        /* JADX WARNING: type inference failed for: r6v8, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r6v10 */
        /* JADX WARNING: type inference failed for: r6v11 */
        /* JADX WARNING: type inference failed for: r6v12 */
        /* JADX WARNING: Multi-variable type inference failed */
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            ? r6 = 0;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zza(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzec.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null, parcel.readString(), zzju.zza.zzO(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zzd view = getView();
                    parcel2.writeNoException();
                    if (view != null) {
                        r6 = view.asBinder();
                    }
                    parcel2.writeStrongBinder(r6);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zza(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null, parcel.readString(), zzju.zza.zzO(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    showInterstitial();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    destroy();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zza(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzec.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), zzju.zza.zzO(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zza(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), zzju.zza.zzO(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    resume();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zza(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null, parcel.readString(), zzoi.zza.zzal(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zzc(parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    showVideo();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    boolean isInitialized = isInitialized();
                    parcel2.writeNoException();
                    if (isInitialized) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 14:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zzd zzcd = zzd.zza.zzcd(parcel.readStrongBinder());
                    zzdy createFromParcel = parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null;
                    String readString = parcel.readString();
                    String readString2 = parcel.readString();
                    zzju zzO = zzju.zza.zzO(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        r6 = zzgw.CREATOR.createFromParcel(parcel);
                    }
                    zza(zzcd, createFromParcel, readString, readString2, zzO, r6, parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zzjw zzgJ = zzgJ();
                    parcel2.writeNoException();
                    if (zzgJ != null) {
                        r6 = zzgJ.asBinder();
                    }
                    parcel2.writeStrongBinder(r6);
                    return true;
                case 16:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zzjx zzgK = zzgK();
                    parcel2.writeNoException();
                    if (zzgK != null) {
                        r6 = zzgK.asBinder();
                    }
                    parcel2.writeStrongBinder(r6);
                    return true;
                case 17:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    Bundle zzgL = zzgL();
                    parcel2.writeNoException();
                    if (zzgL != null) {
                        parcel2.writeInt(1);
                        zzgL.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 18:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    Bundle interstitialAdapterInfo = getInterstitialAdapterInfo();
                    parcel2.writeNoException();
                    if (interstitialAdapterInfo != null) {
                        parcel2.writeInt(1);
                        interstitialAdapterInfo.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 19:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    Bundle zzgM = zzgM();
                    parcel2.writeNoException();
                    if (zzgM != null) {
                        parcel2.writeInt(1);
                        zzgM.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 20:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zza(parcel.readInt() != 0 ? zzdy.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    zzj(zzd.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void destroy();

    Bundle getInterstitialAdapterInfo();

    zzd getView();

    boolean isInitialized();

    void pause();

    void resume();

    void showInterstitial();

    void showVideo();

    void zza(zzd zzd, zzdy zzdy, String str, zzju zzju);

    void zza(zzd zzd, zzdy zzdy, String str, zzoi zzoi, String str2);

    void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju);

    void zza(zzd zzd, zzdy zzdy, String str, String str2, zzju zzju, zzgw zzgw, List<String> list);

    void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, zzju zzju);

    void zza(zzd zzd, zzec zzec, zzdy zzdy, String str, String str2, zzju zzju);

    void zza(zzdy zzdy, String str, String str2);

    void zzc(zzdy zzdy, String str);

    zzjw zzgJ();

    zzjx zzgK();

    Bundle zzgL();

    Bundle zzgM();

    void zzj(zzd zzd);
}
