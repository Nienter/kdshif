package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzch;
import com.google.android.gms.internal.zzci;
import com.google.android.gms.internal.zzdy;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzfn;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzld;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zznt;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzph;
import com.google.android.gms.internal.zzqa;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzmb
public class zzu extends zzep.zza {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    @Nullable
    public zzel zzti;
    /* access modifiers changed from: private */
    public final zzqa zztr;
    private final zzec zzum;
    /* access modifiers changed from: private */
    public final Future<zzch> zzun = zzcz();
    private final zzb zzuo;
    /* access modifiers changed from: private */
    @Nullable
    public WebView zzup = new WebView(this.mContext);
    /* access modifiers changed from: private */
    @Nullable
    public zzch zzuq;
    private AsyncTask<Void, Void, String> zzur;

    private class zza extends AsyncTask<Void, Void, String> {
        private zza() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzD */
        public void onPostExecute(String str) {
            if (zzu.this.zzup != null && str != null) {
                zzu.this.zzup.loadUrl(str);
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public String doInBackground(Void... voidArr) {
            try {
                zzch unused = zzu.this.zzuq = (zzch) zzu.this.zzun.get(zzfx.zzEz.get().longValue(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException e) {
                zzpe.zzc("Failed to load ad data", e);
            } catch (TimeoutException e2) {
                zzpe.zzbe("Timed out waiting for ad data");
            }
            return zzu.this.zzcx();
        }
    }

    private static class zzb {
        private final String zzut;
        private final Map<String, String> zzuu = new TreeMap();
        private String zzuv;
        private String zzuw;

        public zzb(String str) {
            this.zzut = str;
        }

        public String getQuery() {
            return this.zzuv;
        }

        public String zzcB() {
            return this.zzuw;
        }

        public String zzcC() {
            return this.zzut;
        }

        public Map<String, String> zzcD() {
            return this.zzuu;
        }

        public void zzi(zzdy zzdy) {
            this.zzuv = zzdy.zzyM.zzAD;
            Bundle bundle = zzdy.zzyP != null ? zzdy.zzyP.getBundle(AdMobAdapter.class.getName()) : null;
            if (bundle != null) {
                String str = zzfx.zzEy.get();
                for (String str2 : bundle.keySet()) {
                    if (str.equals(str2)) {
                        this.zzuw = bundle.getString(str2);
                    } else if (str2.startsWith("csa_")) {
                        this.zzuu.put(str2.substring("csa_".length()), bundle.getString(str2));
                    }
                }
            }
        }
    }

    public zzu(Context context, zzec zzec, String str, zzqa zzqa) {
        this.mContext = context;
        this.zztr = zzqa;
        this.zzum = zzec;
        this.zzuo = new zzb(str);
        zzcw();
    }

    /* access modifiers changed from: private */
    public String zzB(String str) {
        if (this.zzuq == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzuq.zzd(parse, this.mContext);
        } catch (RemoteException e) {
            zzpe.zzc("Unable to process ad data", e);
        } catch (zzci e2) {
            zzpe.zzc("Unable to parse ad click url", e2);
        }
        return parse.toString();
    }

    /* access modifiers changed from: private */
    public void zzC(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    private void zzcw() {
        zzj(0);
        this.zzup.setVerticalScrollBarEnabled(false);
        this.zzup.getSettings().setJavaScriptEnabled(true);
        this.zzup.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                if (zzu.this.zzti != null) {
                    try {
                        zzu.this.zzti.onAdFailedToLoad(0);
                    } catch (RemoteException e) {
                        zzpe.zzc("Could not call AdListener.onAdFailedToLoad().", e);
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith(zzu.this.zzcy())) {
                    return false;
                }
                if (str.startsWith(zzfx.zzEu.get())) {
                    if (zzu.this.zzti != null) {
                        try {
                            zzu.this.zzti.onAdFailedToLoad(3);
                        } catch (RemoteException e) {
                            zzpe.zzc("Could not call AdListener.onAdFailedToLoad().", e);
                        }
                    }
                    zzu.this.zzj(0);
                    return true;
                } else if (str.startsWith(zzfx.zzEv.get())) {
                    if (zzu.this.zzti != null) {
                        try {
                            zzu.this.zzti.onAdFailedToLoad(0);
                        } catch (RemoteException e2) {
                            zzpe.zzc("Could not call AdListener.onAdFailedToLoad().", e2);
                        }
                    }
                    zzu.this.zzj(0);
                    return true;
                } else if (str.startsWith(zzfx.zzEw.get())) {
                    if (zzu.this.zzti != null) {
                        try {
                            zzu.this.zzti.onAdLoaded();
                        } catch (RemoteException e3) {
                            zzpe.zzc("Could not call AdListener.onAdLoaded().", e3);
                        }
                    }
                    zzu.this.zzj(zzu.this.zzA(str));
                    return true;
                } else if (str.startsWith("gmsg://")) {
                    return true;
                } else {
                    if (zzu.this.zzti != null) {
                        try {
                            zzu.this.zzti.onAdLeftApplication();
                        } catch (RemoteException e4) {
                            zzpe.zzc("Could not call AdListener.onAdLeftApplication().", e4);
                        }
                    }
                    zzu.this.zzC(zzu.this.zzB(str));
                    return true;
                }
            }
        });
        this.zzup.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (zzu.this.zzuq != null) {
                    try {
                        zzu.this.zzuq.zza(motionEvent);
                    } catch (RemoteException e) {
                        zzpe.zzc("Unable to process ad data", e);
                    }
                }
                return false;
            }
        });
    }

    private Future<zzch> zzcz() {
        return zzph.zza(new Callable<zzch>() {
            /* renamed from: zzcA */
            public zzch call() {
                return new zzch(zzu.this.zztr.zzaZ, zzu.this.mContext, false);
            }
        });
    }

    public void destroy() {
        zzac.zzdn("destroy must be called on the main UI thread.");
        this.zzur.cancel(true);
        this.zzun.cancel(true);
        this.zzup.destroy();
        this.zzup = null;
    }

    @Nullable
    public String getMediationAdapterClassName() {
        return null;
    }

    public boolean isLoading() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public void pause() {
        zzac.zzdn("pause must be called on the main UI thread.");
    }

    public void resume() {
        zzac.zzdn("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) {
    }

    public void setUserId(String str) {
        throw new IllegalStateException("Unused method");
    }

    public void showInterstitial() {
        throw new IllegalStateException("Unused method");
    }

    public void stopLoading() {
    }

    /* access modifiers changed from: package-private */
    public int zzA(String str) {
        int i = 0;
        String queryParameter = Uri.parse(str).getQueryParameter("height");
        if (TextUtils.isEmpty(queryParameter)) {
            return i;
        }
        try {
            return zzeh.zzeO().zzb(this.mContext, Integer.parseInt(queryParameter));
        } catch (NumberFormatException e) {
            return i;
        }
    }

    public void zza(zzec zzec) {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public void zza(zzek zzek) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzel zzel) {
        this.zzti = zzel;
    }

    public void zza(zzer zzer) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzet zzet) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzfn zzfn) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzgj zzgj) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzkz zzkz) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzld zzld, String str) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zznt zznt) {
        throw new IllegalStateException("Unused method");
    }

    public boolean zzb(zzdy zzdy) {
        zzac.zzb(this.zzup, (Object) "This Search Ad has already been torn down");
        this.zzuo.zzi(zzdy);
        this.zzur = new zza().execute(new Void[0]);
        return true;
    }

    public zzd zzbC() {
        zzac.zzdn("getAdFrame must be called on the main UI thread.");
        return zze.zzA(this.zzup);
    }

    public zzec zzbD() {
        return this.zzum;
    }

    public void zzbF() {
        throw new IllegalStateException("Unused method");
    }

    @Nullable
    public zzew zzbG() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public String zzcx() {
        Uri uri;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https://").appendEncodedPath(zzfx.zzEx.get());
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zzuo.getQuery());
        builder.appendQueryParameter("pubId", this.zzuo.zzcC());
        Map<String, String> zzcD = this.zzuo.zzcD();
        for (String next : zzcD.keySet()) {
            builder.appendQueryParameter(next, zzcD.get(next));
        }
        Uri build = builder.build();
        if (this.zzuq != null) {
            try {
                uri = this.zzuq.zzc(build, this.mContext);
            } catch (RemoteException | zzci e) {
                zzpe.zzc("Unable to process ad data", e);
            }
            String valueOf = String.valueOf(zzcy());
            String valueOf2 = String.valueOf(uri.getEncodedQuery());
            return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("#").append(valueOf2).toString();
        }
        uri = build;
        String valueOf3 = String.valueOf(zzcy());
        String valueOf22 = String.valueOf(uri.getEncodedQuery());
        return new StringBuilder(String.valueOf(valueOf3).length() + 1 + String.valueOf(valueOf22).length()).append(valueOf3).append("#").append(valueOf22).toString();
    }

    /* access modifiers changed from: package-private */
    public String zzcy() {
        String zzcB = this.zzuo.zzcB();
        String str = TextUtils.isEmpty(zzcB) ? "www.google.com" : zzcB;
        String valueOf = String.valueOf("https://");
        String str2 = zzfx.zzEx.get();
        return new StringBuilder(String.valueOf(valueOf).length() + 0 + String.valueOf(str).length() + String.valueOf(str2).length()).append(valueOf).append(str).append(str2).toString();
    }

    /* access modifiers changed from: package-private */
    public void zzj(int i) {
        if (this.zzup != null) {
            this.zzup.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
        }
    }
}
