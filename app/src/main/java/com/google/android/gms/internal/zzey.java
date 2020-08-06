package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@zzmb
public final class zzey {
    public static final String DEVICE_ID_EMULATOR = zzeh.zzeO().zzbb("emulator");
    private final Date zzcQ;
    private final Set<String> zzcS;
    private final Location zzcU;
    private final boolean zzsS;
    private final int zzyW;
    private final int zzyZ;
    private final Bundle zzzL;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzzM;
    private final SearchAdRequest zzzN;
    private final Set<String> zzzO;
    private final Set<String> zzzP;
    private final String zzza;
    private final String zzzc;
    private final Bundle zzze;
    private final String zzzg;
    private final boolean zzzi;

    public static final class zza {
        /* access modifiers changed from: private */
        public Date zzcQ;
        /* access modifiers changed from: private */
        public Location zzcU;
        /* access modifiers changed from: private */
        public boolean zzsS = false;
        /* access modifiers changed from: private */
        public int zzyW = -1;
        /* access modifiers changed from: private */
        public int zzyZ = -1;
        /* access modifiers changed from: private */
        public final Bundle zzzL = new Bundle();
        /* access modifiers changed from: private */
        public final HashSet<String> zzzQ = new HashSet<>();
        /* access modifiers changed from: private */
        public final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzzR = new HashMap<>();
        /* access modifiers changed from: private */
        public final HashSet<String> zzzS = new HashSet<>();
        /* access modifiers changed from: private */
        public final HashSet<String> zzzT = new HashSet<>();
        /* access modifiers changed from: private */
        public String zzza;
        /* access modifiers changed from: private */
        public String zzzc;
        /* access modifiers changed from: private */
        public final Bundle zzze = new Bundle();
        /* access modifiers changed from: private */
        public String zzzg;
        /* access modifiers changed from: private */
        public boolean zzzi;

        public void setManualImpressionsEnabled(boolean z) {
            this.zzsS = z;
        }

        public void zzL(String str) {
            this.zzzQ.add(str);
        }

        public void zzM(String str) {
            this.zzzS.add(str);
        }

        public void zzN(String str) {
            this.zzzS.remove(str);
        }

        public void zzO(String str) {
            this.zzzc = str;
        }

        public void zzP(String str) {
            this.zzza = str;
        }

        public void zzQ(String str) {
            this.zzzg = str;
        }

        public void zzR(String str) {
            this.zzzT.add(str);
        }

        @Deprecated
        public void zza(NetworkExtras networkExtras) {
            if (networkExtras instanceof AdMobExtras) {
                zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
            } else {
                this.zzzR.put(networkExtras.getClass(), networkExtras);
            }
        }

        public void zza(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzzL.putBundle(cls.getName(), bundle);
        }

        public void zza(Date date) {
            this.zzcQ = date;
        }

        public void zzb(Location location) {
            this.zzcU = location;
        }

        public void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
            if (this.zzzL.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
                this.zzzL.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
            }
            this.zzzL.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
        }

        public void zze(String str, String str2) {
            this.zzze.putString(str, str2);
        }

        public void zzo(boolean z) {
            this.zzyZ = z ? 1 : 0;
        }

        public void zzp(boolean z) {
            this.zzzi = z;
        }

        public void zzx(int i) {
            this.zzyW = i;
        }
    }

    public zzey(zza zza2) {
        this(zza2, null);
    }

    public zzey(zza zza2, SearchAdRequest searchAdRequest) {
        this.zzcQ = zza2.zzcQ;
        this.zzzc = zza2.zzzc;
        this.zzyW = zza2.zzyW;
        this.zzcS = Collections.unmodifiableSet(zza2.zzzQ);
        this.zzcU = zza2.zzcU;
        this.zzsS = zza2.zzsS;
        this.zzzL = zza2.zzzL;
        this.zzzM = Collections.unmodifiableMap(zza2.zzzR);
        this.zzza = zza2.zzza;
        this.zzzg = zza2.zzzg;
        this.zzzN = searchAdRequest;
        this.zzyZ = zza2.zzyZ;
        this.zzzO = Collections.unmodifiableSet(zza2.zzzS);
        this.zzze = zza2.zzze;
        this.zzzP = Collections.unmodifiableSet(zza2.zzzT);
        this.zzzi = zza2.zzzi;
    }

    public Date getBirthday() {
        return this.zzcQ;
    }

    public String getContentUrl() {
        return this.zzzc;
    }

    public Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzzL.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(cls.getName());
        }
        return null;
    }

    public Bundle getCustomTargeting() {
        return this.zzze;
    }

    public int getGender() {
        return this.zzyW;
    }

    public Set<String> getKeywords() {
        return this.zzcS;
    }

    public Location getLocation() {
        return this.zzcU;
    }

    public boolean getManualImpressionsEnabled() {
        return this.zzsS;
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (NetworkExtras) this.zzzM.get(cls);
    }

    public Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> cls) {
        return this.zzzL.getBundle(cls.getName());
    }

    public String getPublisherProvidedId() {
        return this.zzza;
    }

    public boolean isDesignedForFamilies() {
        return this.zzzi;
    }

    public boolean isTestDevice(Context context) {
        return this.zzzO.contains(zzeh.zzeO().zzO(context));
    }

    public String zzeW() {
        return this.zzzg;
    }

    public SearchAdRequest zzeX() {
        return this.zzzN;
    }

    public Map<Class<? extends NetworkExtras>, NetworkExtras> zzeY() {
        return this.zzzM;
    }

    public Bundle zzeZ() {
        return this.zzzL;
    }

    public int zzfa() {
        return this.zzyZ;
    }

    public Set<String> zzfb() {
        return this.zzzP;
    }
}
