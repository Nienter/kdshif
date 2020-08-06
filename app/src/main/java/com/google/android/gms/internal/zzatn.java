package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.support.p001v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzaug;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.IOException;
import java.util.Map;

public class zzatn extends zzats {
    private final Map<String, Map<String, String>> zzbsH = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzbsI = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzbsJ = new ArrayMap();
    private final Map<String, zzaug.zzb> zzbsK = new ArrayMap();
    private final Map<String, String> zzbsL = new ArrayMap();

    zzatn(zzatp zzatp) {
        super(zzatp);
    }

    private Map<String, String> zza(zzaug.zzb zzb) {
        ArrayMap arrayMap = new ArrayMap();
        if (!(zzb == null || zzb.zzbvM == null)) {
            for (zzaug.zzc zzc : zzb.zzbvM) {
                if (zzc != null) {
                    arrayMap.put(zzc.zzaA, zzc.value);
                }
            }
        }
        return arrayMap;
    }

    private void zza(String str, zzaug.zzb zzb) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        if (!(zzb == null || zzb.zzbvN == null)) {
            for (zzaug.zza zza : zzb.zzbvN) {
                if (zza != null) {
                    String str2 = AppMeasurement.zza.zzbpx.get(zza.name);
                    if (str2 != null) {
                        zza.name = str2;
                    }
                    arrayMap.put(zza.name, zza.zzbvI);
                    arrayMap2.put(zza.name, zza.zzbvJ);
                }
            }
        }
        this.zzbsI.put(str, arrayMap);
        this.zzbsJ.put(str, arrayMap2);
    }

    @WorkerThread
    private zzaug.zzb zze(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzaug.zzb();
        }
        zzbul zzad = zzbul.zzad(bArr);
        zzaug.zzb zzb = new zzaug.zzb();
        try {
            zzb.zzb(zzad);
            zzJt().zzLg().zze("Parsed config. version, gmp_app_id", zzb.zzbvK, zzb.zzbqf);
            return zzb;
        } catch (IOException e) {
            zzJt().zzLc().zze("Unable to merge remote config. appId", zzati.zzfI(str), e);
            return null;
        }
    }

    @WorkerThread
    private void zzfN(String str) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        if (!this.zzbsK.containsKey(str)) {
            byte[] zzfA = zzJo().zzfA(str);
            if (zzfA == null) {
                this.zzbsH.put(str, null);
                this.zzbsI.put(str, null);
                this.zzbsJ.put(str, null);
                this.zzbsK.put(str, null);
                this.zzbsL.put(str, null);
                return;
            }
            zzaug.zzb zze = zze(str, zzfA);
            this.zzbsH.put(str, zza(zze));
            zza(str, zze);
            this.zzbsK.put(str, zze);
            this.zzbsL.put(str, null);
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJd() {
        super.zzJd();
    }

    public /* bridge */ /* synthetic */ void zzJe() {
        super.zzJe();
    }

    public /* bridge */ /* synthetic */ void zzJf() {
        super.zzJf();
    }

    public /* bridge */ /* synthetic */ zzaso zzJg() {
        return super.zzJg();
    }

    public /* bridge */ /* synthetic */ zzass zzJh() {
        return super.zzJh();
    }

    public /* bridge */ /* synthetic */ zzatu zzJi() {
        return super.zzJi();
    }

    public /* bridge */ /* synthetic */ zzatf zzJj() {
        return super.zzJj();
    }

    public /* bridge */ /* synthetic */ zzasw zzJk() {
        return super.zzJk();
    }

    public /* bridge */ /* synthetic */ zzatw zzJl() {
        return super.zzJl();
    }

    public /* bridge */ /* synthetic */ zzatv zzJm() {
        return super.zzJm();
    }

    public /* bridge */ /* synthetic */ zzatg zzJn() {
        return super.zzJn();
    }

    public /* bridge */ /* synthetic */ zzasu zzJo() {
        return super.zzJo();
    }

    public /* bridge */ /* synthetic */ zzaue zzJp() {
        return super.zzJp();
    }

    public /* bridge */ /* synthetic */ zzatn zzJq() {
        return super.zzJq();
    }

    public /* bridge */ /* synthetic */ zzaty zzJr() {
        return super.zzJr();
    }

    public /* bridge */ /* synthetic */ zzato zzJs() {
        return super.zzJs();
    }

    public /* bridge */ /* synthetic */ zzati zzJt() {
        return super.zzJt();
    }

    public /* bridge */ /* synthetic */ zzatl zzJu() {
        return super.zzJu();
    }

    public /* bridge */ /* synthetic */ zzast zzJv() {
        return super.zzJv();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzW(String str, String str2) {
        zzmq();
        zzfN(str);
        Map map = this.zzbsH.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzX(String str, String str2) {
        zzmq();
        zzfN(str);
        if (zzJp().zzgj(str) && zzaue.zzgg(str2)) {
            return true;
        }
        if (zzJp().zzgk(str) && zzaue.zzfW(str2)) {
            return true;
        }
        Map map = this.zzbsI.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzY(String str, String str2) {
        zzmq();
        zzfN(str);
        Map map = this.zzbsJ.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzb(String str, byte[] bArr, String str2) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzaug.zzb zze = zze(str, bArr);
        if (zze == null) {
            return false;
        }
        zza(str, zze);
        this.zzbsK.put(str, zze);
        this.zzbsL.put(str, str2);
        this.zzbsH.put(str, zza(zze));
        zzJh().zza(str, zze.zzbvO);
        try {
            zze.zzbvO = null;
            byte[] bArr2 = new byte[zze.zzacZ()];
            zze.zza(zzbum.zzae(bArr2));
            bArr = bArr2;
        } catch (IOException e) {
            zzJt().zzLc().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzati.zzfI(str), e);
        }
        zzJo().zzd(str, bArr);
        return true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public zzaug.zzb zzfO(String str) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzfN(str);
        return this.zzbsK.get(str);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public String zzfP(String str) {
        zzmq();
        return this.zzbsL.get(str);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void zzfQ(String str) {
        zzmq();
        this.zzbsL.put(str, null);
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }
}
