package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzs;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(14)
@zzmb
public class zzdb extends Thread {
    private boolean mStarted = false;
    private boolean zzak = false;
    private final Object zzrN;
    private boolean zzxD = false;
    private final zzcz zzxE;
    private final zzma zzxF;
    private final int zzxG;
    private final int zzxH;
    private final int zzxI;
    private final int zzxJ;
    private final int zzxK;
    private final int zzxL;
    private final String zzxM;
    private final int zzxe;
    private final int zzxg;

    @zzmb
    class zza {
        final int zzxU;
        final int zzxV;

        zza(zzdb zzdb, int i, int i2) {
            this.zzxU = i;
            this.zzxV = i2;
        }
    }

    public zzdb(zzcz zzcz, zzma zzma) {
        this.zzxE = zzcz;
        this.zzxF = zzma;
        this.zzrN = new Object();
        this.zzxe = zzfx.zzBP.get().intValue();
        this.zzxH = zzfx.zzBQ.get().intValue();
        this.zzxg = zzfx.zzBR.get().intValue();
        this.zzxI = zzfx.zzBS.get().intValue();
        this.zzxJ = zzfx.zzBV.get().intValue();
        this.zzxK = zzfx.zzBX.get().intValue();
        this.zzxL = zzfx.zzBY.get().intValue();
        this.zzxG = zzfx.zzBT.get().intValue();
        this.zzxM = zzfx.zzCa.get();
        setName("ContentFetchTask");
    }

    public void run() {
        while (true) {
            try {
                if (zzef()) {
                    Activity activity = zzv.zzcM().getActivity();
                    if (activity == null) {
                        zzpe.zzbc("ContentFetchThread: no activity. Sleeping.");
                        zzeh();
                    } else {
                        zza(activity);
                    }
                } else {
                    zzpe.zzbc("ContentFetchTask: sleeping");
                    zzeh();
                }
                Thread.sleep((long) (this.zzxG * 1000));
            } catch (InterruptedException e) {
                zzpe.zzb("Error in ContentFetchTask", e);
            } catch (Throwable th) {
                zzpe.zzb("Error in ContentFetchTask", th);
                this.zzxF.zza(th, "ContentFetchTask.run");
            }
            synchronized (this.zzrN) {
                while (this.zzxD) {
                    try {
                        zzpe.zzbc("ContentFetchTask: waiting");
                        this.zzrN.wait();
                    } catch (InterruptedException e2) {
                    }
                }
            }
        }
    }

    public void wakeup() {
        synchronized (this.zzrN) {
            this.zzxD = false;
            this.zzrN.notifyAll();
            zzpe.zzbc("ContentFetchThread: wakeup");
        }
    }

    /* access modifiers changed from: package-private */
    public zza zza(@Nullable View view, zzcy zzcy) {
        int i = 0;
        if (view == null) {
            return new zza(this, 0, 0);
        }
        Context context = zzv.zzcM().getContext();
        if (context != null) {
            String str = (String) view.getTag(context.getResources().getIdentifier(zzfx.zzBZ.get(), "id", context.getPackageName()));
            if (!TextUtils.isEmpty(this.zzxM) && str != null && str.equals(this.zzxM)) {
                return new zza(this, 0, 0);
            }
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zza(this, 0, 0);
            }
            zzcy.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zza(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzqp)) {
            zzcy.zzea();
            return zza((WebView) view, zzcy, globalVisibleRect) ? new zza(this, 0, 1) : new zza(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zza(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zza zza2 = zza(viewGroup.getChildAt(i3), zzcy);
                i2 += zza2.zzxU;
                i += zza2.zzxV;
            }
            return new zza(this, i2, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(@Nullable Activity activity) {
        if (activity != null) {
            View view = null;
            try {
                if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                    view = activity.getWindow().getDecorView().findViewById(16908290);
                }
            } catch (Throwable th) {
                zzpe.zzbc("Failed getting root view of activity. Content not extracted.");
            }
            if (view != null) {
                zzh(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzcy zzcy, WebView webView, String str, boolean z) {
        zzcy.zzdZ();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (!TextUtils.isEmpty(webView.getTitle())) {
                    String valueOf = String.valueOf(webView.getTitle());
                    zzcy.zza(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(optString).length()).append(valueOf).append("\n").append(optString).toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    zzcy.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzcy.zzdU()) {
                this.zzxE.zzb(zzcy);
            }
        } catch (JSONException e) {
            zzpe.zzbc("Json string may be malformed.");
        } catch (Throwable th) {
            zzpe.zza("Failed to get webview content.", th);
            this.zzxF.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zza(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
        return runningAppProcessInfo.importance == 100;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(19)
    public boolean zza(final WebView webView, final zzcy zzcy, final boolean z) {
        if (!zzs.zzyF()) {
            return false;
        }
        zzcy.zzea();
        webView.post(new Runnable() {
            ValueCallback<String> zzxP = new ValueCallback<String>() {
                /* renamed from: zzE */
                public void onReceiveValue(String str) {
                    zzdb.this.zza(zzcy, webView, str, z);
                }
            };

            public void run() {
                if (webView.getSettings().getJavaScriptEnabled()) {
                    try {
                        webView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzxP);
                    } catch (Throwable th) {
                        this.zzxP.onReceiveValue("");
                    }
                }
            }
        });
        return true;
    }

    public void zzee() {
        synchronized (this.zzrN) {
            if (this.mStarted) {
                zzpe.zzbc("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzef() {
        try {
            Context context = zzv.zzcM().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (Process.myPid() == next.pid) {
                    if (zza(next) && !keyguardManager.inKeyguardRestrictedInputMode() && zzh(context)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public zzcy zzeg() {
        return this.zzxE.zzed();
    }

    public void zzeh() {
        synchronized (this.zzrN) {
            this.zzxD = true;
            zzpe.zzbc(new StringBuilder(42).append("ContentFetchThread: paused, mPause = ").append(this.zzxD).toString());
        }
    }

    public boolean zzei() {
        return this.zzxD;
    }

    /* access modifiers changed from: package-private */
    public boolean zzh(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return false;
        }
        return powerManager.isScreenOn();
    }

    /* access modifiers changed from: package-private */
    public boolean zzh(@Nullable final View view) {
        if (view == null) {
            return false;
        }
        view.post(new Runnable() {
            public void run() {
                zzdb.this.zzi(view);
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void zzi(View view) {
        try {
            zzcy zzcy = new zzcy(this.zzxe, this.zzxH, this.zzxg, this.zzxI, this.zzxJ, this.zzxK, this.zzxL);
            zza zza2 = zza(view, zzcy);
            zzcy.zzeb();
            if (zza2.zzxU != 0 || zza2.zzxV != 0) {
                if (zza2.zzxV != 0 || zzcy.zzec() != 0) {
                    if (zza2.zzxV != 0 || !this.zzxE.zza(zzcy)) {
                        this.zzxE.zzc(zzcy);
                    }
                }
            }
        } catch (Exception e) {
            zzpe.zzb("Exception in fetchContentOnUIThread", e);
            this.zzxF.zza(e, "ContentFetchTask.fetchContent");
        }
    }
}
