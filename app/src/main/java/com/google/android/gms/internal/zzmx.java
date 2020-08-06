package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zznj;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@zzmb
public class zzmx {
    @Nullable
    public String zzRf;
    @Nullable
    public Bundle zzRk;
    @Nullable
    public List<String> zzRs = new ArrayList();
    @Nullable
    public Bundle zzSC;
    @Nullable
    public zznj.zza zzSD;
    @Nullable
    public String zzSE;
    public zzmh zzSF;
    public zznf zzSG;
    public JSONObject zzSH = new JSONObject();
    @Nullable
    public Location zzyN;

    public zzmx zza(zznf zznf) {
        this.zzSG = zznf;
        return this;
    }

    public zzmx zza(zznj.zza zza) {
        this.zzSD = zza;
        return this;
    }

    public zzmx zzaJ(String str) {
        this.zzRf = str;
        return this;
    }

    public zzmx zzaK(String str) {
        this.zzSE = str;
        return this;
    }

    public zzmx zzc(Location location) {
        this.zzyN = location;
        return this;
    }

    public zzmx zze(Bundle bundle) {
        this.zzSC = bundle;
        return this;
    }

    public zzmx zzf(Bundle bundle) {
        this.zzRk = bundle;
        return this;
    }

    public zzmx zzf(zzmh zzmh) {
        this.zzSF = zzmh;
        return this;
    }

    public zzmx zzh(JSONObject jSONObject) {
        this.zzSH = jSONObject;
        return this;
    }

    public zzmx zzk(List<String> list) {
        if (list == null) {
            this.zzRs.clear();
        }
        this.zzRs = list;
        return this;
    }
}
