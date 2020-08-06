package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@zzmb
public final class zzdz {
    private Bundle mExtras;
    private Location zzcU;
    private boolean zzsS;
    private long zzyV;
    private int zzyW;
    private List<String> zzyX;
    private boolean zzyY;
    private int zzyZ;
    private String zzza;
    private zzfj zzzb;
    private String zzzc;
    private Bundle zzzd;
    private Bundle zzze;
    private List<String> zzzf;
    private String zzzg;
    private String zzzh;
    private boolean zzzi;

    public zzdz() {
        this.zzyV = -1;
        this.mExtras = new Bundle();
        this.zzyW = -1;
        this.zzyX = new ArrayList();
        this.zzyY = false;
        this.zzyZ = -1;
        this.zzsS = false;
        this.zzza = null;
        this.zzzb = null;
        this.zzcU = null;
        this.zzzc = null;
        this.zzzd = new Bundle();
        this.zzze = new Bundle();
        this.zzzf = new ArrayList();
        this.zzzg = null;
        this.zzzh = null;
        this.zzzi = false;
    }

    public zzdz(zzdy zzdy) {
        this.zzyV = zzdy.zzyF;
        this.mExtras = zzdy.extras;
        this.zzyW = zzdy.zzyG;
        this.zzyX = zzdy.zzyH;
        this.zzyY = zzdy.zzyI;
        this.zzyZ = zzdy.zzyJ;
        this.zzsS = zzdy.zzyK;
        this.zzza = zzdy.zzyL;
        this.zzzb = zzdy.zzyM;
        this.zzcU = zzdy.zzyN;
        this.zzzc = zzdy.zzyO;
        this.zzzd = zzdy.zzyP;
        this.zzze = zzdy.zzyQ;
        this.zzzf = zzdy.zzyR;
        this.zzzg = zzdy.zzyS;
        this.zzzh = zzdy.zzyT;
    }

    public zzdz zza(@Nullable Location location) {
        this.zzcU = location;
        return this;
    }

    public zzdy zzex() {
        return new zzdy(7, this.zzyV, this.mExtras, this.zzyW, this.zzyX, this.zzyY, this.zzyZ, this.zzsS, this.zzza, this.zzzb, this.zzcU, this.zzzc, this.zzzd, this.zzze, this.zzzf, this.zzzg, this.zzzh, false);
    }
}
