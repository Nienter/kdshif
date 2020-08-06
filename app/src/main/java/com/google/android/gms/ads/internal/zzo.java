package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzjn;
import com.google.android.gms.internal.zzjw;
import com.google.android.gms.internal.zzjx;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqq;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzo {
    private static zzgp zza(zzjw zzjw) {
        return new zzgp(zzjw.getHeadline(), zzjw.getImages(), zzjw.getBody(), zzjw.zzfL(), zzjw.getCallToAction(), zzjw.getStarRating(), zzjw.getStore(), zzjw.getPrice(), null, zzjw.getExtras(), null, null);
    }

    private static zzgq zza(zzjx zzjx) {
        return new zzgq(zzjx.getHeadline(), zzjx.getImages(), zzjx.getBody(), zzjx.zzfQ(), zzjx.getCallToAction(), zzjx.getAdvertiser(), null, zzjx.getExtras());
    }

    static zzhx zza(@Nullable final zzjw zzjw, @Nullable final zzjx zzjx, final zzf.zza zza) {
        return new zzhx() {
            public void zza(zzqp zzqp, Map<String, String> map) {
                View view = zzqp.getView();
                if (view != null) {
                    try {
                        if (zzjw.this != null) {
                            if (!zzjw.this.getOverrideClickHandling()) {
                                zzjw.this.zzk(zze.zzA(view));
                                zza.onClick();
                                return;
                            }
                            zzo.zza(zzqp);
                        } else if (zzjx == null) {
                        } else {
                            if (!zzjx.getOverrideClickHandling()) {
                                zzjx.zzk(zze.zzA(view));
                                zza.onClick();
                                return;
                            }
                            zzo.zza(zzqp);
                        }
                    } catch (RemoteException e) {
                        zzpe.zzc("Unable to call handleClick on mapper", e);
                    }
                }
            }
        };
    }

    static zzhx zza(final CountDownLatch countDownLatch) {
        return new zzhx() {
            public void zza(zzqp zzqp, Map<String, String> map) {
                countDownLatch.countDown();
                zzqp.getView().setVisibility(0);
            }
        };
    }

    private static String zza(@Nullable Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzpe.zzbe("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        String valueOf2 = String.valueOf(encodeToString);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    static String zza(@Nullable zzgz zzgz) {
        if (zzgz == null) {
            zzpe.zzbe("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzgz.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzpe.zzbe("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzgz);
    }

    /* access modifiers changed from: private */
    public static JSONObject zza(@Nullable Bundle bundle, String str) {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (bundle.containsKey(next)) {
                if ("image".equals(jSONObject2.getString(next))) {
                    Object obj = bundle.get(next);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(next, zza((Bitmap) obj));
                    } else {
                        zzpe.zzbe("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(next) instanceof Bitmap) {
                    zzpe.zzbe("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(next, String.valueOf(bundle.get(next)));
                }
            }
        }
        return jSONObject;
    }

    public static void zza(@Nullable zzov zzov, zzf.zza zza) {
        zzjx zzjx = null;
        if (zzov != null && zzh(zzov)) {
            zzqp zzqp = zzov.zzMZ;
            View view = zzqp != null ? zzqp.getView() : null;
            if (view == null) {
                zzpe.zzbe("AdWebView is null");
                return;
            }
            try {
                List<String> list = zzov.zzKA != null ? zzov.zzKA.zzJU : null;
                if (list == null || list.isEmpty()) {
                    zzpe.zzbe("No template ids present in mediation response");
                    return;
                }
                zzjw zzgJ = zzov.zzKB != null ? zzov.zzKB.zzgJ() : null;
                if (zzov.zzKB != null) {
                    zzjx = zzov.zzKB.zzgK();
                }
                if (list.contains("2") && zzgJ != null) {
                    zzgJ.zzl(zze.zzA(view));
                    if (!zzgJ.getOverrideImpressionRecording()) {
                        zzgJ.recordImpression();
                    }
                    zzqp.zzkV().zza("/nativeExpressViewClicked", zza(zzgJ, (zzjx) null, zza));
                } else if (!list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || zzjx == null) {
                    zzpe.zzbe("No matching template id and mapper");
                } else {
                    zzjx.zzl(zze.zzA(view));
                    if (!zzjx.getOverrideImpressionRecording()) {
                        zzjx.recordImpression();
                    }
                    zzqp.zzkV().zza("/nativeExpressViewClicked", zza((zzjw) null, zzjx, zza));
                }
            } catch (RemoteException e) {
                zzpe.zzc("Error occurred while recording impression and registering for clicks", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void zza(zzqp zzqp) {
        View.OnClickListener zzlk = zzqp.zzlk();
        if (zzlk != null) {
            zzlk.onClick(zzqp.getView());
        }
    }

    private static void zza(final zzqp zzqp, final zzgp zzgp, final String str) {
        zzqp.zzkV().zza((zzqq.zza) new zzqq.zza() {
            public void zza(zzqp zzqp, boolean z) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("headline", zzgp.this.getHeadline());
                    jSONObject.put("body", zzgp.this.getBody());
                    jSONObject.put("call_to_action", zzgp.this.getCallToAction());
                    jSONObject.put(FirebaseAnalytics.Param.PRICE, zzgp.this.getPrice());
                    jSONObject.put("star_rating", String.valueOf(zzgp.this.getStarRating()));
                    jSONObject.put("store", zzgp.this.getStore());
                    jSONObject.put("icon", zzo.zza(zzgp.this.zzfL()));
                    JSONArray jSONArray = new JSONArray();
                    List<Object> images = zzgp.this.getImages();
                    if (images != null) {
                        for (Object zzf : images) {
                            jSONArray.put(zzo.zza(zzo.zze(zzf)));
                        }
                    }
                    jSONObject.put("images", jSONArray);
                    jSONObject.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, zzo.zza(zzgp.this.getExtras(), str));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("assets", jSONObject);
                    jSONObject2.put("template_id", "2");
                    zzqp.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
                } catch (JSONException e) {
                    zzpe.zzc("Exception occurred when loading assets", e);
                }
            }
        });
    }

    private static void zza(final zzqp zzqp, final zzgq zzgq, final String str) {
        zzqp.zzkV().zza((zzqq.zza) new zzqq.zza() {
            public void zza(zzqp zzqp, boolean z) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("headline", zzgq.this.getHeadline());
                    jSONObject.put("body", zzgq.this.getBody());
                    jSONObject.put("call_to_action", zzgq.this.getCallToAction());
                    jSONObject.put("advertiser", zzgq.this.getAdvertiser());
                    jSONObject.put("logo", zzo.zza(zzgq.this.zzfQ()));
                    JSONArray jSONArray = new JSONArray();
                    List<Object> images = zzgq.this.getImages();
                    if (images != null) {
                        for (Object zzf : images) {
                            jSONArray.put(zzo.zza(zzo.zze(zzf)));
                        }
                    }
                    jSONObject.put("images", jSONArray);
                    jSONObject.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, zzo.zza(zzgq.this.getExtras(), str));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("assets", jSONObject);
                    jSONObject2.put("template_id", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    zzqp.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
                } catch (JSONException e) {
                    zzpe.zzc("Exception occurred when loading assets", e);
                }
            }
        });
    }

    private static void zza(zzqp zzqp, CountDownLatch countDownLatch) {
        zzqp.zzkV().zza("/nativeExpressAssetsLoaded", zza(countDownLatch));
        zzqp.zzkV().zza("/nativeExpressAssetsLoadingFailed", zzb(countDownLatch));
    }

    public static boolean zza(zzqp zzqp, zzjn zzjn, CountDownLatch countDownLatch) {
        boolean z = false;
        try {
            z = zzb(zzqp, zzjn, countDownLatch);
        } catch (RemoteException e) {
            zzpe.zzc("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    static zzhx zzb(final CountDownLatch countDownLatch) {
        return new zzhx() {
            public void zza(zzqp zzqp, Map<String, String> map) {
                zzpe.zzbe("Adapter returned an ad, but assets substitution failed");
                countDownLatch.countDown();
                zzqp.destroy();
            }
        };
    }

    private static String zzb(zzgz zzgz) {
        try {
            zzd zzfK = zzgz.zzfK();
            if (zzfK == null) {
                zzpe.zzbe("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) zze.zzE(zzfK);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzpe.zzbe("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzpe.zzbe("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    private static boolean zzb(zzqp zzqp, zzjn zzjn, CountDownLatch countDownLatch) {
        View view = zzqp.getView();
        if (view == null) {
            zzpe.zzbe("AdWebView is null");
            return false;
        }
        view.setVisibility(4);
        List<String> list = zzjn.zzKA.zzJU;
        if (list == null || list.isEmpty()) {
            zzpe.zzbe("No template ids present in mediation response");
            return false;
        }
        zza(zzqp, countDownLatch);
        zzjw zzgJ = zzjn.zzKB.zzgJ();
        zzjx zzgK = zzjn.zzKB.zzgK();
        if (list.contains("2") && zzgJ != null) {
            zza(zzqp, zza(zzgJ), zzjn.zzKA.zzJT);
        } else if (!list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || zzgK == null) {
            zzpe.zzbe("No matching template id and mapper");
            return false;
        } else {
            zza(zzqp, zza(zzgK), zzjn.zzKA.zzJT);
        }
        String str = zzjn.zzKA.zzJR;
        String str2 = zzjn.zzKA.zzJS;
        if (str2 != null) {
            zzqp.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", null);
        } else {
            zzqp.loadData(str, "text/html", "UTF-8");
        }
        return true;
    }

    /* access modifiers changed from: private */
    @Nullable
    public static zzgz zze(Object obj) {
        if (obj instanceof IBinder) {
            return zzgz.zza.zzB((IBinder) obj);
        }
        return null;
    }

    @Nullable
    public static View zzg(@Nullable zzov zzov) {
        if (zzov == null) {
            zzpe.m2432e("AdState is null");
            return null;
        } else if (zzh(zzov) && zzov.zzMZ != null) {
            return zzov.zzMZ.getView();
        } else {
            try {
                zzd view = zzov.zzKB != null ? zzov.zzKB.getView() : null;
                if (view != null) {
                    return (View) zze.zzE(view);
                }
                zzpe.zzbe("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                zzpe.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzh(@Nullable zzov zzov) {
        return (zzov == null || !zzov.zzRK || zzov.zzKA == null || zzov.zzKA.zzJR == null) ? false : true;
    }
}
