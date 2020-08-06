package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;

public class zzati extends zzats {
    private final String zzaEU = zzJv().zzJS();
    private final long zzbpS = zzJv().zzJD();
    private final char zzbrG;
    private final zza zzbrH;
    private final zza zzbrI;
    private final zza zzbrJ;
    private final zza zzbrK;
    private final zza zzbrL;
    private final zza zzbrM;
    private final zza zzbrN;
    private final zza zzbrO;
    private final zza zzbrP;

    public class zza {
        private final int mPriority;
        private final boolean zzbrS;
        private final boolean zzbrT;

        zza(int i, boolean z, boolean z2) {
            this.mPriority = i;
            this.zzbrS = z;
            this.zzbrT = z2;
        }

        public void log(String str) {
            zzati.this.zza(this.mPriority, this.zzbrS, this.zzbrT, str, null, null, null);
        }

        public void zzd(String str, Object obj, Object obj2, Object obj3) {
            zzati.this.zza(this.mPriority, this.zzbrS, this.zzbrT, str, obj, obj2, obj3);
        }

        public void zze(String str, Object obj, Object obj2) {
            zzati.this.zza(this.mPriority, this.zzbrS, this.zzbrT, str, obj, obj2, null);
        }

        public void zzj(String str, Object obj) {
            zzati.this.zza(this.mPriority, this.zzbrS, this.zzbrT, str, obj, null, null);
        }
    }

    private static class zzb {
        /* access modifiers changed from: private */
        public final String zzbrU;

        public zzb(@NonNull String str) {
            this.zzbrU = str;
        }
    }

    zzati(zzatp zzatp) {
        super(zzatp);
        if (zzJv().zzow()) {
            zzJv().zzKk();
            this.zzbrG = 'C';
        } else {
            zzJv().zzKk();
            this.zzbrG = 'c';
        }
        this.zzbrH = new zza(6, false, false);
        this.zzbrI = new zza(6, true, false);
        this.zzbrJ = new zza(6, false, true);
        this.zzbrK = new zza(5, false, false);
        this.zzbrL = new zza(5, true, false);
        this.zzbrM = new zza(5, false, true);
        this.zzbrN = new zza(4, false, false);
        this.zzbrO = new zza(3, false, false);
        this.zzbrP = new zza(2, false, false);
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zzc = zzc(z, obj);
        String zzc2 = zzc(z, obj2);
        String zzc3 = zzc(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzc)) {
            sb.append(str2);
            sb.append(zzc);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc2)) {
            sb.append(str2);
            sb.append(zzc2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc3)) {
            sb.append(str2);
            sb.append(zzc3);
        }
        return sb.toString();
    }

    static String zzc(boolean z, Object obj) {
        StackTraceElement stackTraceElement;
        if (obj == null) {
            return "";
        }
        Object valueOf = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (valueOf instanceof Long) {
            if (!z) {
                return String.valueOf(valueOf);
            }
            if (Math.abs(((Long) valueOf).longValue()) < 100) {
                return String.valueOf(valueOf);
            }
            String str = String.valueOf(valueOf).charAt(0) == '-' ? "-" : "";
            return new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (r1.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) String.valueOf(Math.abs(((Long) valueOf).longValue())).length()) - 1.0d)).toString();
        } else if (valueOf instanceof Boolean) {
            return String.valueOf(valueOf);
        } else {
            if (!(valueOf instanceof Throwable)) {
                return valueOf instanceof zzb ? ((zzb) valueOf).zzbrU : z ? "-" : String.valueOf(valueOf);
            }
            Throwable th = (Throwable) valueOf;
            StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String zzfJ = zzfJ(AppMeasurement.class.getCanonicalName());
            String zzfJ2 = zzfJ(zzatp.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                stackTraceElement = stackTrace[i];
                if (!stackTraceElement.isNativeMethod()) {
                    String className = stackTraceElement.getClassName();
                    if (className != null) {
                        String zzfJ3 = zzfJ(className);
                        if (zzfJ3.equals(zzfJ) || zzfJ3.equals(zzfJ2)) {
                            sb.append(": ");
                            sb.append(stackTraceElement);
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            sb.append(": ");
            sb.append(stackTraceElement);
            return sb.toString();
        }
    }

    protected static Object zzfI(String str) {
        if (str == null) {
            return null;
        }
        return new zzb(str);
    }

    private static String zzfJ(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
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

    public zza zzLa() {
        return this.zzbrH;
    }

    public zza zzLb() {
        return this.zzbrI;
    }

    public zza zzLc() {
        return this.zzbrK;
    }

    public zza zzLd() {
        return this.zzbrM;
    }

    public zza zzLe() {
        return this.zzbrN;
    }

    public zza zzLf() {
        return this.zzbrO;
    }

    public zza zzLg() {
        return this.zzbrP;
    }

    public String zzLh() {
        Pair<String, Long> zzpM = zzJu().zzbsf.zzpM();
        if (zzpM == null || zzpM == zzatl.zzbse) {
            return null;
        }
        String valueOf = String.valueOf(String.valueOf(zzpM.second));
        String str = (String) zzpM.first;
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
    }

    /* access modifiers changed from: protected */
    public void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zzai(i)) {
            zzn(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            zzb(i, str, obj, obj2, obj3);
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzai(int i) {
        return Log.isLoggable(this.zzaEU, i);
    }

    public void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        zzac.zzw(str);
        zzato zzLv = this.zzbpw.zzLv();
        if (zzLv == null) {
            zzn(6, "Scheduler not set. Not logging error/warn");
        } else if (!zzLv.isInitialized()) {
            zzn(6, "Scheduler not initialized. Not logging error/warn");
        } else {
            if (i < 0) {
                i = 0;
            }
            if (i >= "01VDIWEA?".length()) {
                i = "01VDIWEA?".length() - 1;
            }
            String valueOf = String.valueOf("2");
            char charAt = "01VDIWEA?".charAt(i);
            char c = this.zzbrG;
            long j = this.zzbpS;
            String valueOf2 = String.valueOf(zza(true, str, obj, obj2, obj3));
            final String sb = new StringBuilder(String.valueOf(valueOf).length() + 23 + String.valueOf(valueOf2).length()).append(valueOf).append(charAt).append(c).append(j).append(":").append(valueOf2).toString();
            if (sb.length() > 1024) {
                sb = str.substring(0, 1024);
            }
            zzLv.zzm(new Runnable() {
                public void run() {
                    zzatl zzJu = zzati.this.zzbpw.zzJu();
                    if (zzJu.isInitialized()) {
                        zzJu.zzbsf.zzcb(sb);
                    } else {
                        zzati.this.zzn(6, "Persisted config not initialized. Not logging error/warn");
                    }
                }
            });
        }
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    /* access modifiers changed from: protected */
    public void zzn(int i, String str) {
        Log.println(i, this.zzaEU, str);
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }
}
