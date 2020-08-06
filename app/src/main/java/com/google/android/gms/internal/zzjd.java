package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

@zzmb
public class zzjd implements zzjc {
    private final zzjb zzJE;
    private final HashSet<AbstractMap.SimpleEntry<String, zzhx>> zzJF = new HashSet<>();

    public zzjd(zzjb zzjb) {
        this.zzJE = zzjb;
    }

    public void zza(String str, zzhx zzhx) {
        this.zzJE.zza(str, zzhx);
        this.zzJF.add(new AbstractMap.SimpleEntry(str, zzhx));
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzJE.zza(str, jSONObject);
    }

    public void zzb(String str, zzhx zzhx) {
        this.zzJE.zzb(str, zzhx);
        this.zzJF.remove(new AbstractMap.SimpleEntry(str, zzhx));
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzJE.zzb(str, jSONObject);
    }

    public void zzgA() {
        Iterator<AbstractMap.SimpleEntry<String, zzhx>> it = this.zzJF.iterator();
        while (it.hasNext()) {
            AbstractMap.SimpleEntry next = it.next();
            String valueOf = String.valueOf(((zzhx) next.getValue()).toString());
            zzpe.m2431v(valueOf.length() != 0 ? "Unregistering eventhandler: ".concat(valueOf) : new String("Unregistering eventhandler: "));
            this.zzJE.zzb((String) next.getKey(), (zzhx) next.getValue());
        }
        this.zzJF.clear();
    }

    public void zzi(String str, String str2) {
        this.zzJE.zzi(str, str2);
    }
}
