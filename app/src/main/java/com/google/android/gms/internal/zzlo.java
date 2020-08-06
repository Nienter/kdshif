package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzqq;

@zzmb
public class zzlo extends zzlj implements zzqq.zza {
    zzlo(Context context, zzov.zza zza, zzqp zzqp, zzln.zza zza2) {
        super(context, zza, zzqp, zza2);
    }

    /* access modifiers changed from: protected */
    public void zziB() {
    }

    /* access modifiers changed from: protected */
    public void zziu() {
        if (this.zzPp.errorCode == -2) {
            this.zzGt.zzkV().zza((zzqq.zza) this);
            zziB();
            zzpe.zzbc("Loading HTML in WebView.");
            this.zzGt.loadDataWithBaseURL(this.zzPp.zzNb, this.zzPp.body, "text/html", "UTF-8", null);
        }
    }
}
