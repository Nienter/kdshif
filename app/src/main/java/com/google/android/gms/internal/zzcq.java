package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.zzv;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzcq implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    protected final Object zzrN = new Object();
    private boolean zzui = false;
    private zzpt zzvK;
    private final Context zzvZ;
    private final WeakReference<zzov> zzwb;
    private WeakReference<ViewTreeObserver> zzwc;
    private final zzcx zzwd;
    protected final zzco zzwe;
    private final WindowManager zzwf;
    private final PowerManager zzwg;
    private final KeyguardManager zzwh;
    @Nullable
    private zzcr zzwi;
    private boolean zzwj;
    private boolean zzwk = false;
    private boolean zzwl;
    private boolean zzwm;
    private boolean zzwn;
    @Nullable
    BroadcastReceiver zzwo;
    private final HashSet<Object> zzwp = new HashSet<>();
    private final HashSet<zzcu> zzwq = new HashSet<>();

    public static class zza implements zzcx {
        private WeakReference<zzgu> zzws;

        public zza(zzgu zzgu) {
            this.zzws = new WeakReference<>(zzgu);
        }

        @Nullable
        public View zzdO() {
            zzgu zzgu = (zzgu) this.zzws.get();
            if (zzgu != null) {
                return zzgu.zzfV();
            }
            return null;
        }

        public boolean zzdP() {
            return this.zzws.get() == null;
        }

        public zzcx zzdQ() {
            return new zzb((zzgu) this.zzws.get());
        }
    }

    public static class zzb implements zzcx {
        private zzgu zzwt;

        public zzb(zzgu zzgu) {
            this.zzwt = zzgu;
        }

        public View zzdO() {
            if (this.zzwt != null) {
                return this.zzwt.zzfV();
            }
            return null;
        }

        public boolean zzdP() {
            return this.zzwt == null;
        }

        public zzcx zzdQ() {
            return this;
        }
    }

    public static class zzc implements zzcx {
        @Nullable
        private final View mView;
        @Nullable
        private final zzov zzwu;

        public zzc(View view, zzov zzov) {
            this.mView = view;
            this.zzwu = zzov;
        }

        public View zzdO() {
            return this.mView;
        }

        public boolean zzdP() {
            return this.zzwu == null || this.mView == null;
        }

        public zzcx zzdQ() {
            return this;
        }
    }

    public static class zzd implements zzcx {
        private final WeakReference<View> zzwv;
        private final WeakReference<zzov> zzww;

        public zzd(View view, zzov zzov) {
            this.zzwv = new WeakReference<>(view);
            this.zzww = new WeakReference<>(zzov);
        }

        public View zzdO() {
            return (View) this.zzwv.get();
        }

        public boolean zzdP() {
            return this.zzwv.get() == null || this.zzww.get() == null;
        }

        public zzcx zzdQ() {
            return new zzc((View) this.zzwv.get(), (zzov) this.zzww.get());
        }
    }

    public zzcq(Context context, zzec zzec, zzov zzov, zzqa zzqa, zzcx zzcx) {
        this.zzwb = new WeakReference<>(zzov);
        this.zzwd = zzcx;
        this.zzwc = new WeakReference<>(null);
        this.zzwl = true;
        this.zzwn = false;
        this.zzvK = new zzpt(200);
        this.zzwe = new zzco(UUID.randomUUID().toString(), zzqa, zzec.zzzk, zzov.zzVp, zzov.zzdz(), zzec.zzzn);
        this.zzwf = (WindowManager) context.getSystemService("window");
        this.zzwg = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzwh = (KeyguardManager) context.getSystemService("keyguard");
        this.zzvZ = context;
    }

    /* access modifiers changed from: protected */
    public void destroy() {
        synchronized (this.zzrN) {
            zzdH();
            zzdC();
            this.zzwl = false;
            zzdE();
            zzdJ();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isScreenOn() {
        return this.zzwg.isScreenOn();
    }

    public void onGlobalLayout() {
        zzk(2);
    }

    public void onScrollChanged() {
        zzk(1);
    }

    public void pause() {
        synchronized (this.zzrN) {
            this.zzui = true;
            zzk(3);
        }
    }

    public void resume() {
        synchronized (this.zzrN) {
            this.zzui = false;
            zzk(3);
        }
    }

    public void stop() {
        synchronized (this.zzrN) {
            this.zzwk = true;
            zzk(3);
        }
    }

    /* access modifiers changed from: protected */
    public int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    /* access modifiers changed from: package-private */
    public JSONObject zza(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    /* access modifiers changed from: protected */
    public void zza(View view, Map<String, String> map) {
        zzk(3);
    }

    public void zza(zzcr zzcr) {
        synchronized (this.zzrN) {
            this.zzwi = zzcr;
        }
    }

    public void zza(zzcu zzcu) {
        if (this.zzwq.isEmpty()) {
            zzdB();
            zzk(3);
        }
        this.zzwq.add(zzcu);
        try {
            zzcu.zzc(zza(zzd(this.zzwd.zzdO())), false);
        } catch (JSONException e) {
            zzpe.zzb("Skipping measurement update for new client.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzcu zzcu, Map<String, String> map) {
        String valueOf = String.valueOf(this.zzwe.zzdy());
        zzpe.zzbc(valueOf.length() != 0 ? "Received request to untrack: ".concat(valueOf) : new String("Received request to untrack: "));
        zzb(zzcu);
    }

    /* access modifiers changed from: protected */
    public void zza(JSONObject jSONObject, boolean z) {
        try {
            zzb(zza(jSONObject), z);
        } catch (Throwable th) {
            zzpe.zzb("Skipping active view message.", th);
        }
    }

    public void zzb(zzcu zzcu) {
        this.zzwq.remove(zzcu);
        zzcu.zzdS();
        if (this.zzwq.isEmpty()) {
            destroy();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzqp zzqp, Map<String, String> map) {
        zza(zzqp.getView(), map);
    }

    /* access modifiers changed from: protected */
    public void zzb(JSONObject jSONObject, boolean z) {
        Iterator it = new ArrayList(this.zzwq).iterator();
        while (it.hasNext()) {
            ((zzcu) it.next()).zzc(jSONObject, z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzb(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzwe.zzdy());
    }

    /* access modifiers changed from: package-private */
    public void zzc(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            zzj(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("isVisible")) || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(map.get("isVisible")));
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject zzd(@Nullable View view) {
        if (view == null) {
            return zzdL();
        }
        boolean isAttachedToWindow = zzv.zzcL().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzpe.zzb("Failure getting view location.", e);
        }
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.zzwf.getDefaultDisplay().getWidth();
        rect2.bottom = this.zzwf.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, null);
        Rect rect4 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect4);
        Rect rect5 = new Rect();
        view.getHitRect(rect5);
        JSONObject zzdI = zzdI();
        zzdI.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(rect2.top, displayMetrics)).put("bottom", zza(rect2.bottom, displayMetrics)).put("left", zza(rect2.left, displayMetrics)).put("right", zza(rect2.right, displayMetrics))).put("adBox", new JSONObject().put("top", zza(rect.top, displayMetrics)).put("bottom", zza(rect.bottom, displayMetrics)).put("left", zza(rect.left, displayMetrics)).put("right", zza(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put("top", zza(rect3.top, displayMetrics)).put("bottom", zza(rect3.bottom, displayMetrics)).put("left", zza(rect3.left, displayMetrics)).put("right", zza(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect4.top, displayMetrics)).put("bottom", zza(rect4.bottom, displayMetrics)).put("left", zza(rect4.left, displayMetrics)).put("right", zza(rect4.right, displayMetrics))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect5.top, displayMetrics)).put("bottom", zza(rect5.bottom, displayMetrics)).put("left", zza(rect5.left, displayMetrics)).put("right", zza(rect5.right, displayMetrics))).put("screenDensity", (double) displayMetrics.density).put("isVisible", zzv.zzcJ().zza(view, this.zzwg, this.zzwh));
        return zzdI;
    }

    /* access modifiers changed from: protected */
    public void zzdB() {
        synchronized (this.zzrN) {
            if (this.zzwo == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                this.zzwo = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        zzcq.this.zzk(3);
                    }
                };
                this.zzvZ.registerReceiver(this.zzwo, intentFilter);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzdC() {
        synchronized (this.zzrN) {
            if (this.zzwo != null) {
                try {
                    this.zzvZ.unregisterReceiver(this.zzwo);
                } catch (IllegalStateException e) {
                    zzpe.zzb("Failed trying to unregister the receiver", e);
                } catch (Exception e2) {
                    zzv.zzcN().zza((Throwable) e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                }
                this.zzwo = null;
            }
        }
        return;
    }

    public void zzdD() {
        synchronized (this.zzrN) {
            if (this.zzwl) {
                this.zzwm = true;
                try {
                    zza(zzdM(), true);
                } catch (JSONException e) {
                    zzpe.zzb("JSON failure while processing active view data.", e);
                } catch (RuntimeException e2) {
                    zzpe.zzb("Failure while processing active view data.", e2);
                }
                String valueOf = String.valueOf(this.zzwe.zzdy());
                zzpe.zzbc(valueOf.length() != 0 ? "Untracking ad unit: ".concat(valueOf) : new String("Untracking ad unit: "));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzdE() {
        if (this.zzwi != null) {
            this.zzwi.zza(this);
        }
    }

    public boolean zzdF() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzwl;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void zzdG() {
        View zzdO = this.zzwd.zzdQ().zzdO();
        if (zzdO != null) {
            ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzwc.get();
            ViewTreeObserver viewTreeObserver2 = zzdO.getViewTreeObserver();
            if (viewTreeObserver2 != viewTreeObserver) {
                zzdH();
                if (!this.zzwj || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                    this.zzwj = true;
                    viewTreeObserver2.addOnScrollChangedListener(this);
                    viewTreeObserver2.addOnGlobalLayoutListener(this);
                }
                this.zzwc = new WeakReference<>(viewTreeObserver2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzdH() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzwc.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject zzdI() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzwe.zzdw()).put("activeViewJSON", this.zzwe.zzdx()).put("timestamp", zzv.zzcP().elapsedRealtime()).put("adFormat", this.zzwe.zzdv()).put("hashCode", this.zzwe.zzdy()).put("isMraid", this.zzwe.zzdz()).put("isStopped", this.zzwk).put("isPaused", this.zzui).put("isScreenOn", isScreenOn()).put("isNative", this.zzwe.zzdA()).put("appMuted", zzv.zzcJ().zzcq()).put("appVolume", (double) zzv.zzcJ().zzco()).put("deviceVolume", (double) zzv.zzcJ().zzH(this.zzvZ));
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public void zzdJ() {
        Iterator it = new ArrayList(this.zzwq).iterator();
        while (it.hasNext()) {
            zzb((zzcu) it.next());
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzdK() {
        Iterator<zzcu> it = this.zzwq.iterator();
        while (it.hasNext()) {
            if (it.next().zzdR()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public JSONObject zzdL() {
        return zzdI().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
    }

    /* access modifiers changed from: protected */
    public JSONObject zzdM() {
        JSONObject zzdI = zzdI();
        zzdI.put("doneReasonCode", "u");
        return zzdI;
    }

    public zzco zzdN() {
        return this.zzwe;
    }

    /* access modifiers changed from: protected */
    public void zzj(boolean z) {
        Iterator<Object> it = this.zzwp.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0075, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0076, code lost:
        com.google.android.gms.internal.zzpe.zza("Active view update failed.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void zzk(int i) {
        boolean z = false;
        synchronized (this.zzrN) {
            if (zzdK() && this.zzwl) {
                View zzdO = this.zzwd.zzdO();
                boolean z2 = zzdO != null && zzv.zzcJ().zza(zzdO, this.zzwg, this.zzwh) && zzdO.getGlobalVisibleRect(new Rect(), null);
                if (this.zzwd.zzdP()) {
                    zzdD();
                    return;
                }
                if (i == 1) {
                    z = true;
                }
                if (z) {
                    if (!this.zzvK.tryAcquire() && z2 == this.zzwn) {
                        return;
                    }
                }
                if (z2 || this.zzwn || i != 1) {
                    zza(zzd(zzdO), false);
                    this.zzwn = z2;
                    zzdG();
                    zzdE();
                }
            }
        }
    }
}
