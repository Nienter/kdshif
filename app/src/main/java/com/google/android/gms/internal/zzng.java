package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zznf;
import java.util.WeakHashMap;

@zzmb
public final class zzng {
    private WeakHashMap<Context, zza> zzUx = new WeakHashMap<>();

    private class zza {
        public final long zzUy = zzv.zzcP().currentTimeMillis();
        public final zznf zzUz;

        public zza(zzng zzng, zznf zznf) {
            this.zzUz = zznf;
        }

        public boolean hasExpired() {
            return zzfx.zzCQ.get().longValue() + this.zzUy < zzv.zzcP().currentTimeMillis();
        }
    }

    public zznf zzv(Context context) {
        zza zza2 = this.zzUx.get(context);
        zznf zzjn = (zza2 == null || zza2.hasExpired() || !zzfx.zzCP.get().booleanValue()) ? new zznf.zza(context).zzjn() : new zznf.zza(context, zza2.zzUz).zzjn();
        this.zzUx.put(context, new zza(this, zzjn));
        return zzjn;
    }
}
