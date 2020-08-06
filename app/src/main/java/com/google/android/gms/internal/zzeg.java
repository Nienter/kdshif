package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.widget.FrameLayout;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzes;
import com.google.firebase.analytics.FirebaseAnalytics;

@zzmb
public class zzeg {
    private final Object zzrN = new Object();
    private zzes zzzs;
    /* access modifiers changed from: private */
    public final zzdx zzzt;
    /* access modifiers changed from: private */
    public final zzdw zzzu;
    /* access modifiers changed from: private */
    public final zzfd zzzv;
    /* access modifiers changed from: private */
    public final zzhn zzzw;
    /* access modifiers changed from: private */
    public final zznv zzzx;
    /* access modifiers changed from: private */
    public final zzlf zzzy;
    /* access modifiers changed from: private */
    public final zzkq zzzz;

    @VisibleForTesting
    abstract class zza<T> {
        zza() {
        }

        /* access modifiers changed from: protected */
        @Nullable
        public abstract T zzb(zzes zzes);

        /* access modifiers changed from: protected */
        @Nullable
        public abstract T zzeE();

        /* access modifiers changed from: protected */
        @Nullable
        public final T zzeL() {
            T t = null;
            zzes zza = zzeg.this.zzeC();
            if (zza == null) {
                zzpy.zzbe("ClientApi class cannot be loaded.");
                return t;
            }
            try {
                return zzb(zza);
            } catch (RemoteException e) {
                zzpy.zzc("Cannot invoke local loader using ClientApi class", e);
                return t;
            }
        }

        /* access modifiers changed from: protected */
        @Nullable
        public final T zzeM() {
            try {
                return zzeE();
            } catch (RemoteException e) {
                zzpy.zzc("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zzeg(zzdx zzdx, zzdw zzdw, zzfd zzfd, zzhn zzhn, zznv zznv, zzlf zzlf, zzkq zzkq) {
        this.zzzt = zzdx;
        this.zzzu = zzdw;
        this.zzzv = zzfd;
        this.zzzw = zzhn;
        this.zzzx = zznv;
        this.zzzy = zzlf;
        this.zzzz = zzkq;
    }

    private static boolean zza(Activity activity, String str) {
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            return intent.getBooleanExtra(str, false);
        }
        zzpy.m2432e("useClientJar flag not found in activity intent extras.");
        return false;
    }

    /* access modifiers changed from: private */
    public void zzc(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "no_ads_fallback");
        bundle.putString("flow", str);
        zzeh.zzeO().zza(context, (String) null, "gmob-apps", bundle, true);
    }

    @Nullable
    private static zzes zzeB() {
        try {
            Object newInstance = zzeg.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzes.zza.asInterface((IBinder) newInstance);
            }
            zzpy.zzbe("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Exception e) {
            zzpy.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public zzes zzeC() {
        zzes zzes;
        synchronized (this.zzrN) {
            if (this.zzzs == null) {
                this.zzzs = zzeB();
            }
            zzes = this.zzzs;
        }
        return zzes;
    }

    public zzep zza(final Context context, final zzec zzec, final String str) {
        return (zzep) zza(context, false, new zza<zzep>() {
            /* renamed from: zza */
            public zzep zzb(zzes zzes) {
                return zzes.createSearchAdManager(zze.zzA(context), zzec, str, 10084000);
            }

            /* renamed from: zzeD */
            public zzep zzeE() {
                zzep zza = zzeg.this.zzzt.zza(context, zzec, str, null, 3);
                if (zza != null) {
                    return zza;
                }
                zzeg.this.zzc(context, FirebaseAnalytics.Event.SEARCH);
                return new zzff();
            }
        });
    }

    public zzep zza(Context context, zzec zzec, String str, zzjs zzjs) {
        final Context context2 = context;
        final zzec zzec2 = zzec;
        final String str2 = str;
        final zzjs zzjs2 = zzjs;
        return (zzep) zza(context, false, new zza<zzep>() {
            /* renamed from: zza */
            public zzep zzb(zzes zzes) {
                return zzes.createBannerAdManager(zze.zzA(context2), zzec2, str2, zzjs2, 10084000);
            }

            /* renamed from: zzeD */
            public zzep zzeE() {
                zzep zza = zzeg.this.zzzt.zza(context2, zzec2, str2, zzjs2, 1);
                if (zza != null) {
                    return zza;
                }
                zzeg.this.zzc(context2, "banner");
                return new zzff();
            }
        });
    }

    public zzhb zza(final Context context, final FrameLayout frameLayout, final FrameLayout frameLayout2) {
        return (zzhb) zza(context, false, new zza<zzhb>() {
            /* renamed from: zze */
            public zzhb zzb(zzes zzes) {
                return zzes.createNativeAdViewDelegate(zze.zzA(frameLayout), zze.zzA(frameLayout2));
            }

            /* renamed from: zzeH */
            public zzhb zzeE() {
                zzhb zzb = zzeg.this.zzzw.zzb(context, frameLayout, frameLayout2);
                if (zzb != null) {
                    return zzb;
                }
                zzeg.this.zzc(context, "native_ad_view_delegate");
                return new zzfh();
            }
        });
    }

    public zznr zza(final Context context, final zzjs zzjs) {
        return (zznr) zza(context, false, new zza<zznr>() {
            /* renamed from: zzeI */
            public zznr zzeE() {
                zznr zzb = zzeg.this.zzzx.zzb(context, zzjs);
                if (zzb != null) {
                    return zzb;
                }
                zzeg.this.zzc(context, "rewarded_video");
                return new zzfi();
            }

            /* renamed from: zzf */
            public zznr zzb(zzes zzes) {
                return zzes.createRewardedVideoAd(zze.zzA(context), zzjs, 10084000);
            }
        });
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public <T> T zza(Context context, boolean z, zza<T> zza2) {
        if (!z && !zzeh.zzeO().zzP(context)) {
            zzpy.zzbc("Google Play Services is not available");
            z = true;
        }
        if (z) {
            T zzeL = zza2.zzeL();
            return zzeL == null ? zza2.zzeM() : zzeL;
        }
        T zzeM = zza2.zzeM();
        return zzeM == null ? zza2.zzeL() : zzeM;
    }

    public zzen zzb(final Context context, final String str, final zzjs zzjs) {
        return (zzen) zza(context, false, new zza<zzen>() {
            /* renamed from: zzc */
            public zzen zzb(zzes zzes) {
                return zzes.createAdLoaderBuilder(zze.zzA(context), str, zzjs, 10084000);
            }

            /* renamed from: zzeF */
            public zzen zzeE() {
                zzen zza = zzeg.this.zzzu.zza(context, str, zzjs);
                if (zza != null) {
                    return zza;
                }
                zzeg.this.zzc(context, "native_ad");
                return new zzfe();
            }
        });
    }

    public zzep zzb(Context context, zzec zzec, String str, zzjs zzjs) {
        final Context context2 = context;
        final zzec zzec2 = zzec;
        final String str2 = str;
        final zzjs zzjs2 = zzjs;
        return (zzep) zza(context, false, new zza<zzep>() {
            /* renamed from: zza */
            public zzep zzb(zzes zzes) {
                return zzes.createInterstitialAdManager(zze.zzA(context2), zzec2, str2, zzjs2, 10084000);
            }

            /* renamed from: zzeD */
            public zzep zzeE() {
                zzep zza = zzeg.this.zzzt.zza(context2, zzec2, str2, zzjs2, 2);
                if (zza != null) {
                    return zza;
                }
                zzeg.this.zzc(context2, "interstitial");
                return new zzff();
            }
        });
    }

    @Nullable
    public zzla zzb(final Activity activity) {
        return (zzla) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.purchase.useClientJar"), new zza<zzla>() {
            /* renamed from: zzeJ */
            public zzla zzeE() {
                zzla zzg = zzeg.this.zzzy.zzg(activity);
                if (zzg != null) {
                    return zzg;
                }
                zzeg.this.zzc(activity, "iap");
                return null;
            }

            /* renamed from: zzg */
            public zzla zzb(zzes zzes) {
                return zzes.createInAppPurchaseManager(zze.zzA(activity));
            }
        });
    }

    @Nullable
    public zzkr zzc(final Activity activity) {
        return (zzkr) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.overlay.useClientJar"), new zza<zzkr>() {
            /* renamed from: zzeK */
            public zzkr zzeE() {
                zzkr zzf = zzeg.this.zzzz.zzf(activity);
                if (zzf != null) {
                    return zzf;
                }
                zzeg.this.zzc(activity, "ad_overlay");
                return null;
            }

            /* renamed from: zzh */
            public zzkr zzb(zzes zzes) {
                return zzes.createAdOverlay(zze.zzA(activity));
            }
        });
    }

    public zzeu zzk(final Context context) {
        return (zzeu) zza(context, false, new zza<zzeu>() {
            /* renamed from: zzd */
            public zzeu zzb(zzes zzes) {
                return zzes.getMobileAdsSettingsManagerWithClientJarVersion(zze.zzA(context), 10084000);
            }

            /* renamed from: zzeG */
            public zzeu zzeE() {
                zzeu zzl = zzeg.this.zzzv.zzl(context);
                if (zzl != null) {
                    return zzl;
                }
                zzeg.this.zzc(context, "mobile_ads_settings");
                return new zzfg();
            }
        });
    }
}
