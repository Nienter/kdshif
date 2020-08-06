package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.zzv;
import java.util.LinkedHashMap;
import java.util.Map;

@zzmb
public class zzfy {
    private Context mContext = null;
    private boolean zzFa;
    private String zzFb;
    private Map<String, String> zzFc;
    private String zzvU = null;

    public zzfy(Context context, String str) {
        this.mContext = context;
        this.zzvU = str;
        this.zzFa = zzfx.zzBK.get().booleanValue();
        this.zzFb = zzfx.zzBL.get();
        this.zzFc = new LinkedHashMap();
        this.zzFc.put("s", "gmob_sdk");
        this.zzFc.put("v", "3");
        this.zzFc.put("os", Build.VERSION.RELEASE);
        this.zzFc.put(ServerProtocol.DIALOG_PARAM_SDK_VERSION, Build.VERSION.SDK);
        this.zzFc.put("device", zzv.zzcJ().zzkm());
        this.zzFc.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        this.zzFc.put("is_lite_sdk", zzv.zzcJ().zzJ(context) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zznf zzv = zzv.zzcS().zzv(this.mContext);
        this.zzFc.put("network_coarse", Integer.toString(zzv.zzUm));
        this.zzFc.put("network_fine", Integer.toString(zzv.zzUn));
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public String zzdw() {
        return this.zzvU;
    }

    /* access modifiers changed from: package-private */
    public boolean zzfp() {
        return this.zzFa;
    }

    /* access modifiers changed from: package-private */
    public String zzfq() {
        return this.zzFb;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzfr() {
        return this.zzFc;
    }
}
