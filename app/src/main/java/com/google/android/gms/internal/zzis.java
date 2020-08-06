package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zznf;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;

@zzmb
public class zzis {
    private final LinkedList<zzit> zzIA = new LinkedList<>();
    @Nullable
    private zzip zzIB;
    private final Map<zzit, zziu> zzIz = new HashMap();

    private static void zza(String str, zzit zzit) {
        if (zzpe.zzai(2)) {
            zzpe.m2431v(String.format(str, new Object[]{zzit}));
        }
    }

    private String[] zzai(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    private boolean zzaj(String str) {
        try {
            return Pattern.matches(zzfx.zzCH.get(), str);
        } catch (RuntimeException e) {
            zzv.zzcN().zza((Throwable) e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    private static void zzc(Bundle bundle, String str) {
        String[] split = str.split("/", 2);
        if (split.length != 0) {
            String str2 = split[0];
            if (split.length == 1) {
                bundle.remove(str2);
                return;
            }
            Bundle bundle2 = bundle.getBundle(str2);
            if (bundle2 != null) {
                zzc(bundle2, split[1]);
            }
        }
    }

    private String zzgk() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.zzIA.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(((zzit) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    @Nullable
    static Bundle zzk(zzdy zzdy) {
        Bundle bundle = zzdy.zzyP;
        if (bundle == null) {
            return null;
        }
        return bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
    }

    static zzdy zzl(zzdy zzdy) {
        zzdy zzo = zzo(zzdy);
        Bundle zzk = zzk(zzo);
        if (zzk == null) {
            zzk = new Bundle();
            zzo.zzyP.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzk);
        }
        zzk.putBoolean("_skipMediation", true);
        return zzo;
    }

    static boolean zzm(zzdy zzdy) {
        Bundle bundle = zzdy.zzyP;
        if (bundle == null) {
            return false;
        }
        Bundle bundle2 = bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle2 != null && bundle2.containsKey("_skipMediation");
    }

    private static zzdy zzn(zzdy zzdy) {
        zzdy zzo = zzo(zzdy);
        for (String zzc : zzfx.zzCD.get().split(",")) {
            zzc(zzo.zzyP, zzc);
        }
        return zzo;
    }

    static zzdy zzo(zzdy zzdy) {
        Parcel obtain = Parcel.obtain();
        zzdy.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzdy createFromParcel = zzdy.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        zzdy.zzj(createFromParcel);
        return createFromParcel;
    }

    /* access modifiers changed from: package-private */
    public void flush() {
        while (this.zzIA.size() > 0) {
            zzit remove = this.zzIA.remove();
            zziu zziu = this.zzIz.get(remove);
            zza("Flushing interstitial queue for %s.", remove);
            while (zziu.size() > 0) {
                zziu.zzp(null).zzIG.zzck();
            }
            this.zzIz.remove(remove);
        }
    }

    /* access modifiers changed from: package-private */
    public void restore() {
        if (this.zzIB != null) {
            SharedPreferences sharedPreferences = this.zzIB.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            flush();
            try {
                HashMap hashMap = new HashMap();
                for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                    if (!((String) next.getKey()).equals("PoolKeys")) {
                        zziw zzak = zziw.zzak((String) next.getValue());
                        zzit zzit = new zzit(zzak.zzug, zzak.zztq, zzak.zzIE);
                        if (!this.zzIz.containsKey(zzit)) {
                            this.zzIz.put(zzit, new zziu(zzak.zzug, zzak.zztq, zzak.zzIE));
                            hashMap.put(zzit.toString(), zzit);
                            zza("Restored interstitial queue for %s.", zzit);
                        }
                    }
                }
                for (String str : zzai(sharedPreferences.getString("PoolKeys", ""))) {
                    zzit zzit2 = (zzit) hashMap.get(str);
                    if (this.zzIz.containsKey(zzit2)) {
                        this.zzIA.add(zzit2);
                    }
                }
            } catch (Throwable th) {
                zzv.zzcN().zza(th, "InterstitialAdPool.restore");
                zzpe.zzc("Malformed preferences value for InterstitialAdPool.", th);
                this.zzIz.clear();
                this.zzIA.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void save() {
        if (this.zzIB != null) {
            SharedPreferences.Editor edit = this.zzIB.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (Map.Entry next : this.zzIz.entrySet()) {
                zzit zzit = (zzit) next.getKey();
                zziu zziu = (zziu) next.getValue();
                if (zziu.zzgp()) {
                    edit.putString(zzit.toString(), new zziw(zziu).zzgs());
                    zza("Saved interstitial queue for %s.", zzit);
                }
            }
            edit.putString("PoolKeys", zzgk());
            edit.apply();
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zziu.zza zza(zzdy zzdy, String str) {
        zziu zziu;
        if (zzaj(str)) {
            return null;
        }
        int i = new zznf.zza(this.zzIB.getApplicationContext()).zzjn().zzUm;
        zzdy zzn = zzn(zzdy);
        zzit zzit = new zzit(zzn, str, i);
        zziu zziu2 = this.zzIz.get(zzit);
        if (zziu2 == null) {
            zza("Interstitial pool created at %s.", zzit);
            zziu zziu3 = new zziu(zzn, str, i);
            this.zzIz.put(zzit, zziu3);
            zziu = zziu3;
        } else {
            zziu = zziu2;
        }
        this.zzIA.remove(zzit);
        this.zzIA.add(zzit);
        zziu.zzgo();
        while (this.zzIA.size() > zzfx.zzCE.get().intValue()) {
            zzit remove = this.zzIA.remove();
            zziu zziu4 = this.zzIz.get(remove);
            zza("Evicting interstitial queue for %s.", remove);
            while (zziu4.size() > 0) {
                zziu4.zzp(null).zzIG.zzck();
            }
            this.zzIz.remove(remove);
        }
        while (zziu.size() > 0) {
            zziu.zza zzp = zziu.zzp(zzn);
            if (!zzp.zzIK || zzv.zzcP().currentTimeMillis() - zzp.zzIJ <= 1000 * ((long) zzfx.zzCG.get().intValue())) {
                String str2 = zzp.zzIH != null ? " (inline) " : " ";
                zza(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), zzit);
                return zzp;
            }
            zza("Expired interstitial at %s.", zzit);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void zza(zzip zzip) {
        if (this.zzIB == null) {
            this.zzIB = zzip.zzgi();
            restore();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzdy zzdy, String str) {
        if (this.zzIB != null) {
            int i = new zznf.zza(this.zzIB.getApplicationContext()).zzjn().zzUm;
            zzdy zzn = zzn(zzdy);
            zzit zzit = new zzit(zzn, str, i);
            zziu zziu = this.zzIz.get(zzit);
            if (zziu == null) {
                zza("Interstitial pool created at %s.", zzit);
                zziu = new zziu(zzn, str, i);
                this.zzIz.put(zzit, zziu);
            }
            zziu.zza(this.zzIB, zzdy);
            zziu.zzgo();
            zza("Inline entry added to the queue at %s.", zzit);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzgj() {
        if (this.zzIB != null) {
            for (Map.Entry next : this.zzIz.entrySet()) {
                zzit zzit = (zzit) next.getKey();
                zziu zziu = (zziu) next.getValue();
                if (zzpe.zzai(2)) {
                    int size = zziu.size();
                    int zzgm = zziu.zzgm();
                    if (zzgm < size) {
                        zzpe.m2431v(String.format("Loading %s/%s pooled interstitials for %s.", new Object[]{Integer.valueOf(size - zzgm), Integer.valueOf(size), zzit}));
                    }
                }
                zziu.zzgn();
                while (zziu.size() < zzfx.zzCF.get().intValue()) {
                    zza("Pooling and loading one new interstitial for %s.", zzit);
                    zziu.zzb(this.zzIB);
                }
            }
            save();
        }
    }
}
