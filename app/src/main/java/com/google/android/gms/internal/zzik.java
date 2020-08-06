package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzv;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzmb
public class zzik implements Iterable<zzij> {
    private final List<zzij> zzHX = new LinkedList();

    private zzij zzg(zzqp zzqp) {
        Iterator<zzij> it = zzv.zzdg().iterator();
        while (it.hasNext()) {
            zzij next = it.next();
            if (next.zzGt == zzqp) {
                return next;
            }
        }
        return null;
    }

    public Iterator<zzij> iterator() {
        return this.zzHX.iterator();
    }

    public void zza(zzij zzij) {
        this.zzHX.add(zzij);
    }

    public void zzb(zzij zzij) {
        this.zzHX.remove(zzij);
    }

    public boolean zze(zzqp zzqp) {
        zzij zzg = zzg(zzqp);
        if (zzg == null) {
            return false;
        }
        zzg.zzHU.abort();
        return true;
    }

    public boolean zzf(zzqp zzqp) {
        return zzg(zzqp) != null;
    }

    public int zzgf() {
        return this.zzHX.size();
    }
}
