package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzov;

@zzmb
public class zzln {

    public interface zza {
        void zzb(zzov zzov);
    }

    public zzpk zza(Context context, com.google.android.gms.ads.internal.zza zza2, zzov.zza zza3, zzav zzav, @Nullable zzqp zzqp, zzjs zzjs, zza zza4, zzgf zzgf) {
        zzmk zzmk = zza3.zzVB;
        zzpk zzlr = zzmk.zzRK ? new zzlr(context, zza3, zzjs, zza4, zzgf, zzqp) : (zzmk.zzzn || (zza2 instanceof zzr)) ? (!zzmk.zzzn || !(zza2 instanceof zzr)) ? new zzlp(zza3, zza4) : new zzls(context, (zzr) zza2, zza3, zzav, zza4, zzgf) : (!zzfx.zzBN.get().booleanValue() || !zzmk.zzRQ) ? (!zzfx.zzCg.get().booleanValue() || !zzs.zzyF() || zzs.zzyH() || zzqp == null || !zzqp.zzbD().zzzl) ? new zzlo(context, zza3, zzqp, zza4) : new zzlq(context, zza3, zzqp, zza4) : new zzll(context, zza3, zzqp, zza4);
        String valueOf = String.valueOf(zzlr.getClass().getName());
        zzpe.zzbc(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzlr.zziw();
        return zzlr;
    }

    public zzpk zza(Context context, zzov.zza zza2, zznp zznp) {
        zzog zzog = new zzog(context, zza2, zznp);
        String valueOf = String.valueOf(zzog.getClass().getName());
        zzpe.zzbc(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzog.zziw();
        return zzog;
    }
}
