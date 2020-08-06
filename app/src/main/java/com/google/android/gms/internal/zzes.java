package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzjs;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zznr;

public interface zzes extends IInterface {

    public static abstract class zza extends Binder implements zzes {

        /* renamed from: com.google.android.gms.internal.zzes$zza$zza  reason: collision with other inner class name */
        private static class C1732zza implements zzes {
            private IBinder zzrp;

            C1732zza(IBinder iBinder) {
                this.zzrp = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrp;
            }

            public zzen createAdLoaderBuilder(zzd zzd, String str, zzjs zzjs, int i) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    obtain.writeString(str);
                    if (zzjs != null) {
                        iBinder = zzjs.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzrp.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzen.zza.zzo(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzkr createAdOverlay(zzd zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    this.zzrp.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzkr.zza.zzT(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzep createBannerAdManager(zzd zzd, zzec zzec, String str, zzjs zzjs, int i) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
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
                    return zzep.zza.zzq(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzla createInAppPurchaseManager(zzd zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    this.zzrp.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzla.zza.zzY(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzep createInterstitialAdManager(zzd zzd, zzec zzec, String str, zzjs zzjs, int i) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
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
                    this.zzrp.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzep.zza.zzq(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzhb createNativeAdViewDelegate(zzd zzd, zzd zzd2) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzd2 != null) {
                        iBinder = zzd2.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrp.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzhb.zza.zzC(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zznr createRewardedVideoAd(zzd zzd, zzjs zzjs, int i) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzjs != null) {
                        iBinder = zzjs.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzrp.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return zznr.zza.zzah(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzep createSearchAdManager(zzd zzd, zzec zzec, String str, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.zzrp.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzep.zza.zzq(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzeu getMobileAdsSettingsManager(zzd zzd) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    this.zzrp.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzeu.zza.zzu(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzeu getMobileAdsSettingsManagerWithClientJarVersion(zzd zzd, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    obtain.writeInt(i);
                    this.zzrp.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzeu.zza.zzu(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.client.IClientApi");
        }

        public static zzes asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzes)) ? new C1732zza(iBinder) : (zzes) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzep createBannerAdManager = createBannerAdManager(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzec.CREATOR.createFromParcel(parcel) : null, parcel.readString(), zzjs.zza.zzM(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createBannerAdManager != null) {
                        iBinder = createBannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzep createInterstitialAdManager = createInterstitialAdManager(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzec.CREATOR.createFromParcel(parcel) : null, parcel.readString(), zzjs.zza.zzM(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createInterstitialAdManager != null) {
                        iBinder = createInterstitialAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzen createAdLoaderBuilder = createAdLoaderBuilder(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readString(), zzjs.zza.zzM(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createAdLoaderBuilder != null) {
                        iBinder = createAdLoaderBuilder.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzeu mobileAdsSettingsManager = getMobileAdsSettingsManager(zzd.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (mobileAdsSettingsManager != null) {
                        iBinder = mobileAdsSettingsManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzhb createNativeAdViewDelegate = createNativeAdViewDelegate(zzd.zza.zzcd(parcel.readStrongBinder()), zzd.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createNativeAdViewDelegate != null) {
                        iBinder = createNativeAdViewDelegate.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zznr createRewardedVideoAd = createRewardedVideoAd(zzd.zza.zzcd(parcel.readStrongBinder()), zzjs.zza.zzM(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createRewardedVideoAd != null) {
                        iBinder = createRewardedVideoAd.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzla createInAppPurchaseManager = createInAppPurchaseManager(zzd.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createInAppPurchaseManager != null) {
                        iBinder = createInAppPurchaseManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 8:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzkr createAdOverlay = createAdOverlay(zzd.zza.zzcd(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createAdOverlay != null) {
                        iBinder = createAdOverlay.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 9:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzeu mobileAdsSettingsManagerWithClientJarVersion = getMobileAdsSettingsManagerWithClientJarVersion(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (mobileAdsSettingsManagerWithClientJarVersion != null) {
                        iBinder = mobileAdsSettingsManagerWithClientJarVersion.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 10:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzep createSearchAdManager = createSearchAdManager(zzd.zza.zzcd(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzec.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (createSearchAdManager != null) {
                        iBinder = createSearchAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IClientApi");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzen createAdLoaderBuilder(zzd zzd, String str, zzjs zzjs, int i);

    zzkr createAdOverlay(zzd zzd);

    zzep createBannerAdManager(zzd zzd, zzec zzec, String str, zzjs zzjs, int i);

    zzla createInAppPurchaseManager(zzd zzd);

    zzep createInterstitialAdManager(zzd zzd, zzec zzec, String str, zzjs zzjs, int i);

    zzhb createNativeAdViewDelegate(zzd zzd, zzd zzd2);

    zznr createRewardedVideoAd(zzd zzd, zzjs zzjs, int i);

    zzep createSearchAdManager(zzd zzd, zzec zzec, String str, int i);

    zzeu getMobileAdsSettingsManager(zzd zzd);

    zzeu getMobileAdsSettingsManagerWithClientJarVersion(zzd zzd, int i);
}
