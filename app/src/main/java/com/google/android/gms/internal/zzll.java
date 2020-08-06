package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzqq;

@zzmb
public class zzll extends zzlj {
    private zzlk zzPD;

    zzll(Context context, zzov.zza zza, zzqp zzqp, zzln.zza zza2) {
        super(context, zza, zzqp, zza2);
    }

    /* access modifiers changed from: protected */
    public void zziu() {
        int i;
        int i2;
        zzec zzbD = this.zzGt.zzbD();
        if (zzbD.zzzl) {
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else {
            i = zzbD.widthPixels;
            i2 = zzbD.heightPixels;
        }
        this.zzPD = new zzlk(this, this.zzGt, i, i2);
        this.zzGt.zzkV().zza((zzqq.zza) this);
        this.zzPD.zza(this.zzPp);
    }

    /* access modifiers changed from: protected */
    public int zziv() {
        if (!this.zzPD.zziz()) {
            return !this.zzPD.zziA() ? 2 : -2;
        }
        zzpe.zzbc("Ad-Network indicated no fill with passback URL.");
        return 3;
    }
}
