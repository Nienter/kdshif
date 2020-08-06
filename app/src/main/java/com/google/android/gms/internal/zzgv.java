package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzqq;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzgv implements zzgu {
    private final Context mContext;
    private final zzr zzGl;
    @Nullable
    private final JSONObject zzGo;
    @Nullable
    private final zzlt zzGp;
    @Nullable
    private final zzgu.zza zzGq;
    private final zzav zzGr;
    boolean zzGs;
    /* access modifiers changed from: private */
    public zzqp zzGt;
    /* access modifiers changed from: private */
    public String zzGu;
    @Nullable
    private String zzGv;
    private WeakReference<View> zzGw = null;
    private final Object zzrN = new Object();
    @Nullable
    private final zzqa zztr;

    public zzgv(Context context, zzr zzr, @Nullable zzlt zzlt, zzav zzav, @Nullable JSONObject jSONObject, @Nullable zzgu.zza zza, @Nullable zzqa zzqa, @Nullable String str) {
        this.mContext = context;
        this.zzGl = zzr;
        this.zzGp = zzlt;
        this.zzGr = zzav;
        this.zzGo = jSONObject;
        this.zzGq = zza;
        this.zztr = zzqa;
        this.zzGv = str;
    }

    private JSONObject zza(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject = new JSONObject();
        if (map == null || view == null) {
            return jSONObject;
        }
        try {
            int[] zzk = zzk(view);
            for (Map.Entry next : map.entrySet()) {
                View view2 = (View) ((WeakReference) next.getValue()).get();
                if (view2 != null) {
                    int[] zzk2 = zzk(view2);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("width", zzB(zzl(view2)));
                    jSONObject2.put("height", zzB(zzm(view2)));
                    jSONObject2.put("x", zzB(zzk2[0] - zzk[0]));
                    jSONObject2.put("y", zzB(zzk2[1] - zzk[1]));
                    jSONObject.put((String) next.getKey(), jSONObject2);
                }
            }
        } catch (JSONException e) {
            zzpe.zzbe("Unable to get all view rectangles");
        }
        return jSONObject;
    }

    private JSONObject zzb(Rect rect) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("x", zzB(rect.left));
        jSONObject.put("y", zzB(rect.top));
        jSONObject.put("width", zzB(rect.right - rect.left));
        jSONObject.put("height", zzB(rect.bottom - rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private JSONObject zzb(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (map == null || view == null) {
            return jSONObject2;
        }
        int[] zzk = zzk(view);
        for (Map.Entry next : map.entrySet()) {
            View view2 = (View) ((WeakReference) next.getValue()).get();
            if (view2 != null) {
                int[] zzk2 = zzk(view2);
                JSONObject jSONObject3 = new JSONObject();
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("width", zzB(zzl(view2)));
                    jSONObject4.put("height", zzB(zzm(view2)));
                    jSONObject4.put("x", zzB(zzk2[0] - zzk[0]));
                    jSONObject4.put("y", zzB(zzk2[1] - zzk[1]));
                    jSONObject4.put("relative_to", "ad_view");
                    jSONObject3.put("frame", jSONObject4);
                    Rect rect = new Rect();
                    if (view2.getLocalVisibleRect(rect)) {
                        jSONObject = zzb(rect);
                    } else {
                        jSONObject = new JSONObject();
                        jSONObject.put("x", zzB(zzk2[0] - zzk[0]));
                        jSONObject.put("y", zzB(zzk2[1] - zzk[1]));
                        jSONObject.put("width", 0);
                        jSONObject.put("height", 0);
                        jSONObject.put("relative_to", "ad_view");
                    }
                    jSONObject3.put("visible_bounds", jSONObject);
                    if (view2 instanceof TextView) {
                        TextView textView = (TextView) view2;
                        jSONObject3.put("text_color", textView.getCurrentTextColor());
                        jSONObject3.put("font_size", (double) textView.getTextSize());
                        jSONObject3.put("text", textView.getText());
                    }
                    jSONObject2.put((String) next.getKey(), jSONObject3);
                } catch (JSONException e) {
                    zzpe.zzbe("Unable to get asset views information");
                }
            }
        }
        return jSONObject2;
    }

    private JSONObject zzn(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view != null) {
            try {
                jSONObject.put("width", zzB(zzl(view)));
                jSONObject.put("height", zzB(zzm(view)));
            } catch (Exception e) {
                zzpe.zzbe("Unable to get native ad view bounding box");
            }
        }
        return jSONObject;
    }

    private JSONObject zzo(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (view != null) {
            try {
                int[] zzk = zzk(view);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("width", zzB(zzl(view)));
                jSONObject3.put("height", zzB(zzm(view)));
                jSONObject3.put("x", zzB(zzk[0]));
                jSONObject3.put("y", zzB(zzk[1]));
                jSONObject3.put("relative_to", "window");
                jSONObject2.put("frame", jSONObject3);
                Rect rect = new Rect();
                if (view.getGlobalVisibleRect(rect)) {
                    jSONObject = zzb(rect);
                } else {
                    jSONObject = new JSONObject();
                    jSONObject.put("x", zzB(zzk[0]));
                    jSONObject.put("y", zzB(zzk[1]));
                    jSONObject.put("width", 0);
                    jSONObject.put("height", 0);
                    jSONObject.put("relative_to", "window");
                }
                jSONObject2.put("visible_bounds", jSONObject);
            } catch (Exception e) {
                zzpe.zzbe("Unable to get native ad view bounding box");
            }
        }
        return jSONObject2;
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public int zzB(int i) {
        return zzeh.zzeO().zzc(this.mContext, i);
    }

    public zzgn zza(View.OnClickListener onClickListener) {
        zzgm zzfO = this.zzGq.zzfO();
        if (zzfO == null) {
            return null;
        }
        zzgn zzgn = new zzgn(this.mContext, zzfO);
        zzgn.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        zzgn.zzfJ().setOnClickListener(onClickListener);
        zzgn.zzfJ().setContentDescription(zzfx.zzEa.get());
        return zzgn;
    }

    public void zza(View view, zzgs zzgs) {
        if (this.zzGq instanceof zzgp) {
            zzgp zzgp = (zzgp) this.zzGq;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            if (zzgp.zzfP() != null) {
                ((FrameLayout) view).addView(zzgp.zzfP(), layoutParams);
                this.zzGl.zza(zzgs);
            } else if (zzgp.getImages() != null && zzgp.getImages().size() > 0) {
                zzgz zze = zze(zzgp.getImages().get(0));
                if (zze != null) {
                    try {
                        zzd zzfK = zze.zzfK();
                        if (zzfK != null) {
                            ImageView zzfY = zzfY();
                            zzfY.setImageDrawable((Drawable) zze.zzE(zzfK));
                            zzfY.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            ((FrameLayout) view).addView(zzfY, layoutParams);
                        }
                    } catch (RemoteException e) {
                        zzpe.zzbe("Could not get drawable from image");
                    }
                }
            }
        }
    }

    public void zza(View view, String str, @Nullable JSONObject jSONObject, Map<String, WeakReference<View>> map, View view2) {
        zzac.zzdn("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("asset", str);
            jSONObject2.put("template", this.zzGq.zzfN());
            final JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("ad", this.zzGo);
            jSONObject3.put("click", jSONObject2);
            jSONObject3.put("has_custom_click_handler", this.zzGl.zzz(this.zzGq.getCustomTemplateId()) != null);
            if (zzfx.zzEc.get().booleanValue()) {
                if (zzfx.zzEd.get().booleanValue()) {
                    jSONObject3.put("asset_view_signal", zzb(map, view2));
                    jSONObject3.put("ad_view_signal", zzo(view2));
                } else {
                    jSONObject3.put("view_rectangles", zza(map, view2));
                    jSONObject3.put("native_view_rectangle", zzn(view2));
                }
            }
            if (jSONObject != null) {
                jSONObject3.put("click_point", jSONObject);
            }
            try {
                JSONObject optJSONObject = this.zzGo.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                jSONObject2.put("click_signals", this.zzGr.zzW().zza(this.mContext, optJSONObject.optString("click_string"), view));
            } catch (Exception e) {
                zzpe.zzb("Exception obtaining click signals", e);
            }
            jSONObject3.put("ads_id", this.zzGv);
            this.zzGp.zza((zzlt.zza) new zzlt.zza(this) {
                public void zze(zzjb zzjb) {
                    zzjb.zza("google.afma.nativeAds.handleClickGmsg", jSONObject3);
                }
            });
        } catch (JSONException e2) {
            zzpe.zzb("Unable to create click JSON.", e2);
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        if (zzfx.zzDX.get().booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            if (map != null) {
                for (Map.Entry<String, WeakReference<View>> value : map.entrySet()) {
                    View view2 = (View) ((WeakReference) value.getValue()).get();
                    if (view2 != null) {
                        view2.setOnTouchListener(onTouchListener);
                        view2.setClickable(true);
                        view2.setOnClickListener(onClickListener);
                    }
                }
            }
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, View view2) {
        zzac.zzdn("performClick must be called on the main UI thread.");
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                if (view.equals((View) ((WeakReference) next.getValue()).get())) {
                    zza(view, (String) next.getKey(), jSONObject, map, view2);
                    return;
                }
            }
        }
        if ("2".equals(this.zzGq.zzfN())) {
            zza(view, "2099", jSONObject, map, view2);
        } else if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzGq.zzfN())) {
            zza(view, "1099", jSONObject, map, view2);
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        zzac.zzdn("recordImpression must be called on the main UI thread.");
        zzq(true);
        try {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.zzGo);
            jSONObject.put("ads_id", this.zzGv);
            if (zzfx.zzEc.get().booleanValue()) {
                if (zzfx.zzEd.get().booleanValue()) {
                    jSONObject.put("asset_view_signal", zzb(map, view));
                    jSONObject.put("ad_view_signal", zzo(view));
                } else {
                    jSONObject.put("view_rectangles", zza(map, view));
                    jSONObject.put("native_view_rectangle", zzn(view));
                }
            }
            this.zzGp.zza((zzlt.zza) new zzlt.zza(this) {
                public void zze(zzjb zzjb) {
                    zzjb.zza("google.afma.nativeAds.handleImpressionPing", jSONObject);
                }
            });
        } catch (JSONException e) {
            zzpe.zzb("Unable to create impression JSON.", e);
        }
        this.zzGl.zza((zzgu) this);
    }

    public void zzc(View view, Map<String, WeakReference<View>> map) {
        if (!zzfx.zzDW.get().booleanValue()) {
            view.setOnTouchListener(null);
            view.setClickable(false);
            view.setOnClickListener(null);
            if (map != null) {
                for (Map.Entry<String, WeakReference<View>> value : map.entrySet()) {
                    View view2 = (View) ((WeakReference) value.getValue()).get();
                    if (view2 != null) {
                        view2.setOnTouchListener(null);
                        view2.setClickable(false);
                        view2.setOnClickListener(null);
                    }
                }
            }
        }
    }

    public void zzd(MotionEvent motionEvent) {
        this.zzGr.zza(motionEvent);
    }

    public void zzd(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzrN) {
            if (!this.zzGs) {
                if (view.isShown()) {
                    if (view.getGlobalVisibleRect(new Rect(), null)) {
                        zzb(view, map);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzgz zze(Object obj) {
        if (obj instanceof IBinder) {
            return zzgz.zza.zzB((IBinder) obj);
        }
        return null;
    }

    public zzqp zzfU() {
        this.zzGt = zzfX();
        this.zzGt.getView().setVisibility(8);
        this.zzGp.zza((zzlt.zza) new zzlt.zza() {
            public void zze(final zzjb zzjb) {
                zzjb.zza("/loadHtml", (zzhx) new zzhx() {
                    public void zza(zzqp zzqp, final Map<String, String> map) {
                        zzgv.this.zzGt.zzkV().zza((zzqq.zza) new zzqq.zza() {
                            public void zza(zzqp zzqp, boolean z) {
                                String unused = zzgv.this.zzGu = (String) map.get("id");
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("messageType", "htmlLoaded");
                                    jSONObject.put("id", zzgv.this.zzGu);
                                    zzjb.zzb("sendMessageToNativeJs", jSONObject);
                                } catch (JSONException e) {
                                    zzpe.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                                }
                            }
                        });
                        String str = map.get("overlayHtml");
                        String str2 = map.get("baseUrl");
                        if (TextUtils.isEmpty(str2)) {
                            zzgv.this.zzGt.loadData(str, "text/html", "UTF-8");
                        } else {
                            zzgv.this.zzGt.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", null);
                        }
                    }
                });
                zzjb.zza("/showOverlay", (zzhx) new zzhx() {
                    public void zza(zzqp zzqp, Map<String, String> map) {
                        zzgv.this.zzGt.getView().setVisibility(0);
                    }
                });
                zzjb.zza("/hideOverlay", (zzhx) new zzhx() {
                    public void zza(zzqp zzqp, Map<String, String> map) {
                        zzgv.this.zzGt.getView().setVisibility(8);
                    }
                });
                zzgv.this.zzGt.zzkV().zza("/hideOverlay", (zzhx) new zzhx() {
                    public void zza(zzqp zzqp, Map<String, String> map) {
                        zzgv.this.zzGt.getView().setVisibility(8);
                    }
                });
                zzgv.this.zzGt.zzkV().zza("/sendMessageToSdk", (zzhx) new zzhx() {
                    public void zza(zzqp zzqp, Map<String, String> map) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            for (String next : map.keySet()) {
                                jSONObject.put(next, map.get(next));
                            }
                            jSONObject.put("id", zzgv.this.zzGu);
                            zzjb.zzb("sendMessageToNativeJs", jSONObject);
                        } catch (JSONException e) {
                            zzpe.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                        }
                    }
                });
            }
        });
        return this.zzGt;
    }

    public View zzfV() {
        if (this.zzGw != null) {
            return (View) this.zzGw.get();
        }
        return null;
    }

    public void zzfW() {
        if (this.zzGq instanceof zzgp) {
            this.zzGl.zzct();
        }
    }

    /* access modifiers changed from: package-private */
    public zzqp zzfX() {
        return zzv.zzcK().zza(this.mContext, zzec.zzj(this.mContext), false, false, this.zzGr, this.zztr);
    }

    /* access modifiers changed from: package-private */
    public ImageView zzfY() {
        return new ImageView(this.mContext);
    }

    public void zzj(View view) {
        this.zzGw = new WeakReference<>(view);
    }

    /* access modifiers changed from: package-private */
    public int[] zzk(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public int zzl(View view) {
        return view.getMeasuredWidth();
    }

    /* access modifiers changed from: package-private */
    public int zzm(View view) {
        return view.getMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public void zzq(boolean z) {
        this.zzGs = z;
    }
}
