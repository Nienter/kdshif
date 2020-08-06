package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zza;

public class zzaae extends zzzw {
    private zzaap zzaxK;
    private final zza<zzzs<?>> zzazH = new zza<>();

    private zzaae(zzaax zzaax) {
        super(zzaax);
        this.zzaBs.zza("ConnectionlessLifecycleHelper", (zzaaw) this);
    }

    public static void zza(Activity activity, zzaap zzaap, zzzs<?> zzzs) {
        zzaax zzs = zzs(activity);
        zzaae zzaae = (zzaae) zzs.zza("ConnectionlessLifecycleHelper", zzaae.class);
        if (zzaae == null) {
            zzaae = new zzaae(zzs);
        }
        zzaae.zzaxK = zzaap;
        zzaae.zza(zzzs);
        zzaap.zza(zzaae);
    }

    private void zza(zzzs<?> zzzs) {
        zzac.zzb(zzzs, (Object) "ApiKey cannot be null");
        this.zzazH.add(zzzs);
    }

    public void onStart() {
        super.onStart();
        if (!this.zzazH.isEmpty()) {
            this.zzaxK.zza(this);
        }
    }

    public void onStop() {
        super.onStop();
        this.zzaxK.zzb(this);
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        this.zzaxK.zza(connectionResult, i);
    }

    /* access modifiers changed from: protected */
    public void zzuW() {
        this.zzaxK.zzuW();
    }

    /* access modifiers changed from: package-private */
    public zza<zzzs<?>> zzvx() {
        return this.zzazH;
    }
}
