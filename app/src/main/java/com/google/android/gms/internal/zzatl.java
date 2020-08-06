package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.zzac;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Locale;

class zzatl extends zzats {
    static final Pair<String, Long> zzbse = new Pair<>("", 0L);
    /* access modifiers changed from: private */
    public SharedPreferences zzafC;
    public final zzc zzbsf = new zzc("health_monitor", zzJv().zzoZ());
    public final zzb zzbsg = new zzb("last_upload", 0);
    public final zzb zzbsh = new zzb("last_upload_attempt", 0);
    public final zzb zzbsi = new zzb("backoff", 0);
    public final zzb zzbsj = new zzb("last_delete_stale", 0);
    public final zzb zzbsk = new zzb("midnight_offset", 0);
    private String zzbsl;
    private boolean zzbsm;
    private long zzbsn;
    private SecureRandom zzbso;
    public final zzb zzbsp = new zzb("time_before_start", 10000);
    public final zzb zzbsq = new zzb("session_timeout", 1800000);
    public final zza zzbsr = new zza("start_new_session", true);
    public final zzb zzbss = new zzb("last_pause_time", 0);
    public final zzb zzbst = new zzb("time_active", 0);
    public boolean zzbsu;

    public final class zza {
        private final String zzAH;
        private boolean zzaxF;
        private final boolean zzbsv;
        private boolean zzbsw;

        public zza(String str, boolean z) {
            zzac.zzdv(str);
            this.zzAH = str;
            this.zzbsv = z;
        }

        @WorkerThread
        private void zzLq() {
            if (!this.zzbsw) {
                this.zzbsw = true;
                this.zzaxF = zzatl.this.zzafC.getBoolean(this.zzAH, this.zzbsv);
            }
        }

        @WorkerThread
        public boolean get() {
            zzLq();
            return this.zzaxF;
        }

        @WorkerThread
        public void set(boolean z) {
            SharedPreferences.Editor edit = zzatl.this.zzafC.edit();
            edit.putBoolean(this.zzAH, z);
            edit.apply();
            this.zzaxF = z;
        }
    }

    public final class zzb {
        private final String zzAH;
        private long zzacc;
        private boolean zzbsw;
        private final long zzbsy;

        public zzb(String str, long j) {
            zzac.zzdv(str);
            this.zzAH = str;
            this.zzbsy = j;
        }

        @WorkerThread
        private void zzLq() {
            if (!this.zzbsw) {
                this.zzbsw = true;
                this.zzacc = zzatl.this.zzafC.getLong(this.zzAH, this.zzbsy);
            }
        }

        @WorkerThread
        public long get() {
            zzLq();
            return this.zzacc;
        }

        @WorkerThread
        public void set(long j) {
            SharedPreferences.Editor edit = zzatl.this.zzafC.edit();
            edit.putLong(this.zzAH, j);
            edit.apply();
            this.zzacc = j;
        }
    }

    public final class zzc {
        private final long zzafG;
        private final String zzbsA;
        private final String zzbsB;
        final String zzbsz;

        private zzc(String str, long j) {
            zzac.zzdv(str);
            zzac.zzas(j > 0);
            this.zzbsz = String.valueOf(str).concat(":start");
            this.zzbsA = String.valueOf(str).concat(":count");
            this.zzbsB = String.valueOf(str).concat(":value");
            this.zzafG = j;
        }

        @WorkerThread
        private void zzpK() {
            zzatl.this.zzmq();
            long currentTimeMillis = zzatl.this.zznq().currentTimeMillis();
            SharedPreferences.Editor edit = zzatl.this.zzafC.edit();
            edit.remove(this.zzbsA);
            edit.remove(this.zzbsB);
            edit.putLong(this.zzbsz, currentTimeMillis);
            edit.apply();
        }

        @WorkerThread
        private long zzpL() {
            zzatl.this.zzmq();
            long zzpN = zzpN();
            if (zzpN != 0) {
                return Math.abs(zzpN - zzatl.this.zznq().currentTimeMillis());
            }
            zzpK();
            return 0;
        }

        @WorkerThread
        private long zzpN() {
            return zzatl.this.zzLl().getLong(this.zzbsz, 0);
        }

        @WorkerThread
        public void zzcb(String str) {
            zzi(str, 1);
        }

        @WorkerThread
        public void zzi(String str, long j) {
            zzatl.this.zzmq();
            if (zzpN() == 0) {
                zzpK();
            }
            if (str == null) {
                str = "";
            }
            long j2 = zzatl.this.zzafC.getLong(this.zzbsA, 0);
            if (j2 <= 0) {
                SharedPreferences.Editor edit = zzatl.this.zzafC.edit();
                edit.putString(this.zzbsB, str);
                edit.putLong(this.zzbsA, j);
                edit.apply();
                return;
            }
            boolean z = (zzatl.this.zzLi().nextLong() & Long.MAX_VALUE) < (Long.MAX_VALUE / (j2 + j)) * j;
            SharedPreferences.Editor edit2 = zzatl.this.zzafC.edit();
            if (z) {
                edit2.putString(this.zzbsB, str);
            }
            edit2.putLong(this.zzbsA, j2 + j);
            edit2.apply();
        }

        @WorkerThread
        public Pair<String, Long> zzpM() {
            zzatl.this.zzmq();
            long zzpL = zzpL();
            if (zzpL < this.zzafG) {
                return null;
            }
            if (zzpL > this.zzafG * 2) {
                zzpK();
                return null;
            }
            String string = zzatl.this.zzLl().getString(this.zzbsB, null);
            long j = zzatl.this.zzLl().getLong(this.zzbsA, 0);
            zzpK();
            return (string == null || j <= 0) ? zzatl.zzbse : new Pair<>(string, Long.valueOf(j));
        }
    }

    zzatl(zzatp zzatp) {
        super(zzatp);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public SecureRandom zzLi() {
        zzmq();
        if (this.zzbso == null) {
            this.zzbso = new SecureRandom();
        }
        return this.zzbso;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public SharedPreferences zzLl() {
        zzmq();
        zznA();
        return this.zzafC;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void setMeasurementEnabled(boolean z) {
        zzmq();
        zzJt().zzLg().zzj("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzLl().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzJy() {
        zzmq();
        try {
            return com.google.firebase.iid.zzc.zzaab().getId();
        } catch (IllegalStateException e) {
            zzJt().zzLc().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzLj() {
        byte[] bArr = new byte[16];
        zzLi().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public long zzLk() {
        zznA();
        zzmq();
        long j = this.zzbsk.get();
        if (j != 0) {
            return j;
        }
        long nextInt = (long) (zzLi().nextInt(86400000) + 1);
        this.zzbsk.set(nextInt);
        return nextInt;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzLm() {
        zzmq();
        return zzLl().getString("gmp_app_id", null);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public Boolean zzLn() {
        zzmq();
        if (!zzLl().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzLl().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzLo() {
        boolean z = true;
        zzmq();
        zzJt().zzLg().log("Clearing collection preferences.");
        boolean contains = zzLl().contains("measurement_enabled");
        if (contains) {
            z = zzaG(true);
        }
        SharedPreferences.Editor edit = zzLl().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public String zzLp() {
        zzmq();
        String string = zzLl().getString("previous_os_version", null);
        String zzKU = zzJk().zzKU();
        if (!TextUtils.isEmpty(zzKU) && !zzKU.equals(string)) {
            SharedPreferences.Editor edit = zzLl().edit();
            edit.putString("previous_os_version", zzKU);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzaF(boolean z) {
        zzmq();
        zzJt().zzLg().zzj("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzLl().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzaG(boolean z) {
        zzmq();
        return zzLl().getBoolean("measurement_enabled", z);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @NonNull
    public Pair<String, Boolean> zzfK(String str) {
        zzmq();
        long elapsedRealtime = zznq().elapsedRealtime();
        if (this.zzbsl != null && elapsedRealtime < this.zzbsn) {
            return new Pair<>(this.zzbsl, Boolean.valueOf(this.zzbsm));
        }
        this.zzbsn = elapsedRealtime + zzJv().zzfq(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            this.zzbsl = advertisingIdInfo.getId();
            if (this.zzbsl == null) {
                this.zzbsl = "";
            }
            this.zzbsm = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Throwable th) {
            zzJt().zzLf().zzj("Unable to get advertising id", th);
            this.zzbsl = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzbsl, Boolean.valueOf(this.zzbsm));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzfL(String str) {
        zzmq();
        String str2 = (String) zzfK(str).first;
        MessageDigest zzcg = zzaue.zzcg("MD5");
        if (zzcg == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzcg.digest(str2.getBytes()))});
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzfM(String str) {
        zzmq();
        SharedPreferences.Editor edit = zzLl().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
        this.zzafC = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzbsu = this.zzafC.getBoolean("has_been_opened", false);
        if (!this.zzbsu) {
            SharedPreferences.Editor edit = this.zzafC.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
    }
}
