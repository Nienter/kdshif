package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@zzmb
public class zzfu {
    private final Collection<zzft> zzAJ = new ArrayList();
    private final Collection<zzft<String>> zzAK = new ArrayList();
    private final Collection<zzft<String>> zzAL = new ArrayList();

    public void zza(zzft zzft) {
        this.zzAJ.add(zzft);
    }

    public void zzb(zzft<String> zzft) {
        this.zzAK.add(zzft);
    }

    public void zzc(zzft<String> zzft) {
        this.zzAL.add(zzft);
    }

    public List<String> zzfn() {
        ArrayList arrayList = new ArrayList();
        for (zzft<String> zzft : this.zzAK) {
            String str = (String) zzft.get();
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public List<String> zzfo() {
        List<String> zzfn = zzfn();
        for (zzft<String> zzft : this.zzAL) {
            String str = (String) zzft.get();
            if (str != null) {
                zzfn.add(str);
            }
        }
        return zzfn;
    }
}
