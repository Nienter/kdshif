package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzauf;
import com.google.android.gms.internal.zzauh;
import com.google.android.gms.measurement.AppMeasurement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;
import p005b.p006a.p007a.p008a.p009a.p014d.EventsFilesManager;

public class zzaue extends zzats {
    private final AtomicLong zzbve = new AtomicLong(0);
    private int zzbvf;

    zzaue(zzatp zzatp) {
        super(zzatp);
    }

    private Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            return null;
        }
    }

    public static String zza(zzauf.zzb zzb) {
        if (zzb == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzb.zzbvl);
        zza(sb, 0, "event_name", (Object) zzb.zzbvm);
        zza(sb, 1, "event_count_filter", zzb.zzbvp);
        sb.append("  filters {\n");
        for (zzauf.zzc zza : zzb.zzbvn) {
            zza(sb, 2, zza);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    public static String zza(zzauf.zze zze) {
        if (zze == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", (Object) zze.zzbvl);
        zza(sb, 0, "property_name", (Object) zze.zzbvB);
        zza(sb, 1, zze.zzbvC);
        sb.append("}\n");
        return sb.toString();
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, zzauf.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", (Object) zzc.zzbvt);
            zza(sb, i, "param_name", (Object) zzc.zzbvu);
            zza(sb, i + 1, "string_filter", zzc.zzbvr);
            zza(sb, i + 1, "number_filter", zzc.zzbvs);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, zzauh.zze zze) {
        if (zze != null) {
            zza(sb, i);
            sb.append("bundle {\n");
            zza(sb, i, "protocol_version", (Object) zze.zzbwc);
            zza(sb, i, "platform", (Object) zze.zzbwk);
            zza(sb, i, "gmp_version", (Object) zze.zzbwo);
            zza(sb, i, "uploading_gmp_version", (Object) zze.zzbwp);
            zza(sb, i, "config_version", (Object) zze.zzbwB);
            zza(sb, i, "gmp_app_id", (Object) zze.zzbqf);
            zza(sb, i, "app_id", (Object) zze.zzaR);
            zza(sb, i, "app_version", (Object) zze.zzbhg);
            zza(sb, i, "app_version_major", (Object) zze.zzbwx);
            zza(sb, i, "firebase_instance_id", (Object) zze.zzbqn);
            zza(sb, i, "dev_cert_hash", (Object) zze.zzbwt);
            zza(sb, i, "app_store", (Object) zze.zzbqg);
            zza(sb, i, "upload_timestamp_millis", (Object) zze.zzbwf);
            zza(sb, i, "start_timestamp_millis", (Object) zze.zzbwg);
            zza(sb, i, "end_timestamp_millis", (Object) zze.zzbwh);
            zza(sb, i, "previous_bundle_start_timestamp_millis", (Object) zze.zzbwi);
            zza(sb, i, "previous_bundle_end_timestamp_millis", (Object) zze.zzbwj);
            zza(sb, i, "app_instance_id", (Object) zze.zzbws);
            zza(sb, i, "resettable_device_id", (Object) zze.zzbwq);
            zza(sb, i, "device_id", (Object) zze.zzbwA);
            zza(sb, i, "limited_ad_tracking", (Object) zze.zzbwr);
            zza(sb, i, "os_version", (Object) zze.zzba);
            zza(sb, i, "device_model", (Object) zze.zzbwl);
            zza(sb, i, "user_default_language", (Object) zze.zzbwm);
            zza(sb, i, "time_zone_offset_minutes", (Object) zze.zzbwn);
            zza(sb, i, "bundle_sequential_index", (Object) zze.zzbwu);
            zza(sb, i, "service_upload", (Object) zze.zzbwv);
            zza(sb, i, "health_monitor", (Object) zze.zzbqj);
            zza(sb, i, zze.zzbwe);
            zza(sb, i, zze.zzbww);
            zza(sb, i, zze.zzbwd);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzauf.zzd zzd) {
        if (zzd != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zzbvv != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (zzd.zzbvv.intValue()) {
                    case 1:
                        str2 = "LESS_THAN";
                        break;
                    case 2:
                        str2 = "GREATER_THAN";
                        break;
                    case 3:
                        str2 = "EQUAL";
                        break;
                    case 4:
                        str2 = "BETWEEN";
                        break;
                }
                zza(sb, i, "comparison_type", (Object) str2);
            }
            zza(sb, i, "match_as_float", (Object) zzd.zzbvw);
            zza(sb, i, "comparison_value", (Object) zzd.zzbvx);
            zza(sb, i, "min_comparison_value", (Object) zzd.zzbvy);
            zza(sb, i, "max_comparison_value", (Object) zzd.zzbvz);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzauf.zzf zzf) {
        if (zzf != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzf.zzbvD != null) {
                String str2 = "UNKNOWN_MATCH_TYPE";
                switch (zzf.zzbvD.intValue()) {
                    case 1:
                        str2 = "REGEXP";
                        break;
                    case 2:
                        str2 = "BEGINS_WITH";
                        break;
                    case 3:
                        str2 = "ENDS_WITH";
                        break;
                    case 4:
                        str2 = "PARTIAL";
                        break;
                    case 5:
                        str2 = "EXACT";
                        break;
                    case 6:
                        str2 = "IN_LIST";
                        break;
                }
                zza(sb, i, "match_type", (Object) str2);
            }
            zza(sb, i, "expression", (Object) zzf.zzbvE);
            zza(sb, i, "case_sensitive", (Object) zzf.zzbvF);
            if (zzf.zzbvG.length > 0) {
                zza(sb, i + 1);
                sb.append("expression_list {\n");
                for (String append : zzf.zzbvG) {
                    zza(sb, i + 2);
                    sb.append(append);
                    sb.append("\n");
                }
                sb.append("}\n");
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzauh.zzf zzf) {
        int i2 = 0;
        if (zzf != null) {
            int i3 = i + 1;
            zza(sb, i3);
            sb.append(str);
            sb.append(" {\n");
            if (zzf.zzbwD != null) {
                zza(sb, i3 + 1);
                sb.append("results: ");
                long[] jArr = zzf.zzbwD;
                int length = jArr.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length) {
                    Long valueOf = Long.valueOf(jArr[i4]);
                    int i6 = i5 + 1;
                    if (i5 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i4++;
                    i5 = i6;
                }
                sb.append(10);
            }
            if (zzf.zzbwC != null) {
                zza(sb, i3 + 1);
                sb.append("status: ");
                long[] jArr2 = zzf.zzbwC;
                int length2 = jArr2.length;
                int i7 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    int i8 = i7 + 1;
                    if (i7 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i2++;
                    i7 = i8;
                }
                sb.append(10);
            }
            zza(sb, i3);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    private static void zza(StringBuilder sb, int i, zzauh.zza[] zzaArr) {
        if (zzaArr != null) {
            int i2 = i + 1;
            for (zzauh.zza zza : zzaArr) {
                if (zza != null) {
                    zza(sb, i2);
                    sb.append("audience_membership {\n");
                    zza(sb, i2, "audience_id", (Object) zza.zzbvh);
                    zza(sb, i2, "new_audience", (Object) zza.zzbvT);
                    zza(sb, i2, "current_data", zza.zzbvR);
                    zza(sb, i2, "previous_data", zza.zzbvS);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauh.zzb[] zzbArr) {
        if (zzbArr != null) {
            int i2 = i + 1;
            for (zzauh.zzb zzb : zzbArr) {
                if (zzb != null) {
                    zza(sb, i2);
                    sb.append("event {\n");
                    zza(sb, i2, "name", (Object) zzb.name);
                    zza(sb, i2, "timestamp_millis", (Object) zzb.zzbvW);
                    zza(sb, i2, "previous_timestamp_millis", (Object) zzb.zzbvX);
                    zza(sb, i2, "count", (Object) zzb.count);
                    zza(sb, i2, zzb.zzbvV);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauh.zzc[] zzcArr) {
        if (zzcArr != null) {
            int i2 = i + 1;
            for (zzauh.zzc zzc : zzcArr) {
                if (zzc != null) {
                    zza(sb, i2);
                    sb.append("param {\n");
                    zza(sb, i2, "name", (Object) zzc.name);
                    zza(sb, i2, "string_value", (Object) zzc.zzaFy);
                    zza(sb, i2, "int_value", (Object) zzc.zzbvZ);
                    zza(sb, i2, "double_value", (Object) zzc.zzbvc);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauh.zzg[] zzgArr) {
        if (zzgArr != null) {
            int i2 = i + 1;
            for (zzauh.zzg zzg : zzgArr) {
                if (zzg != null) {
                    zza(sb, i2);
                    sb.append("user_property {\n");
                    zza(sb, i2, "set_timestamp_millis", (Object) zzg.zzbwF);
                    zza(sb, i2, "name", (Object) zzg.name);
                    zza(sb, i2, "string_value", (Object) zzg.zzaFy);
                    zza(sb, i2, "int_value", (Object) zzg.zzbvZ);
                    zza(sb, i2, "double_value", (Object) zzg.zzbvc);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    public static boolean zza(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, str), 2);
            if (receiverInfo == null || !receiverInfo.enabled) {
                return false;
            }
            return !z || receiverInfo.exported;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean zza(long[] jArr, int i) {
        return i < jArr.length * 64 && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i * 64) + i2 < bitSet.length()) {
                if (bitSet.get((i * 64) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    public static boolean zzab(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static String zzb(zzauh.zzd zzd) {
        if (zzd == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzd.zzbwa != null) {
            for (zzauh.zze zze : zzd.zzbwa) {
                if (zze != null) {
                    zza(sb, 1, zze);
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    @WorkerThread
    static boolean zzc(zzatb zzatb, zzasq zzasq) {
        zzac.zzw(zzatb);
        zzac.zzw(zzasq);
        return !TextUtils.isEmpty(zzasq.zzbqf) || "_in".equals(zzatb.name);
    }

    static MessageDigest zzcg(String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 2) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i = i2 + 1;
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    static boolean zzfW(String str) {
        zzac.zzdv(str);
        return str.charAt(0) != '_';
    }

    private int zzgf(String str) {
        return "_ldl".equals(str) ? zzJv().zzKa() : zzJv().zzJZ();
    }

    public static boolean zzgg(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
    }

    static boolean zzgi(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    public static boolean zzr(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 4);
            return serviceInfo != null && serviceInfo.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    static long zzy(byte[] bArr) {
        int i = 0;
        zzac.zzw(bArr);
        zzac.zzar(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
            length--;
        }
        return j;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public boolean zzD(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public long zzE(Context context, String str) {
        zzmq();
        zzac.zzw(context);
        zzac.zzdv(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzcg = zzcg("MD5");
        if (zzcg == null) {
            zzJt().zzLa().log("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzF(context, str)) {
                    PackageInfo packageInfo = zzacx.zzaQ(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zzy(zzcg.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzJt().zzLc().log("Could not get signatures");
                    return -1;
                }
            } catch (PackageManager.NameNotFoundException e) {
                zzJt().zzLa().zzj("Package name not found", e);
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean zzF(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = zzacx.zzaQ(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            zzJt().zzLa().zzj("Error obtaining certificate", e);
        } catch (PackageManager.NameNotFoundException e2) {
            zzJt().zzLa().zzj("Package name not found", e2);
        }
        return true;
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

    public long zzMi() {
        long andIncrement;
        if (this.zzbve.get() == 0) {
            synchronized (this.zzbve) {
                long nextLong = new Random(System.nanoTime() ^ zznq().currentTimeMillis()).nextLong();
                int i = this.zzbvf + 1;
                this.zzbvf = i;
                andIncrement = nextLong + ((long) i);
            }
        } else {
            synchronized (this.zzbve) {
                this.zzbve.compareAndSet(-1, 1);
                andIncrement = this.zzbve.getAndIncrement();
            }
        }
        return andIncrement;
    }

    /* access modifiers changed from: package-private */
    public boolean zzZ(String str, String str2) {
        if (str2 == null) {
            zzJt().zzLa().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzJt().zzLa().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzJt().zzLa().zze("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzJt().zzLa().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    public Bundle zza(String str, Bundle bundle, @Nullable List<String> list, boolean z) {
        int i;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        zzJv().zzJT();
        int i2 = 0;
        for (String str2 : bundle.keySet()) {
            if (list == null || !list.contains(str2)) {
                i = z ? zzgb(str2) : 0;
                if (i == 0) {
                    i = zzgc(str2);
                }
            } else {
                i = 0;
            }
            if (i != 0) {
                if (zzd(bundle2, i)) {
                    bundle2.putString("_ev", zza(str2, zzJv().zzJW(), true));
                    if (i == 3) {
                        zzb(bundle2, str2);
                    }
                }
                bundle2.remove(str2);
            } else if (zzk(str2, bundle.get(str2)) || "_ev".equals(str2)) {
                if (zzfW(str2)) {
                    i2++;
                    if (i2 > 25) {
                        zzJt().zzLa().zze(new StringBuilder(48).append("Event can't contain more then ").append(25).append(" params").toString(), str, bundle);
                        zzd(bundle2, 5);
                        bundle2.remove(str2);
                    }
                }
                i2 = i2;
            } else {
                if (zzd(bundle2, 4)) {
                    bundle2.putString("_ev", zza(str2, zzJv().zzJW(), true));
                    zzb(bundle2, bundle.get(str2));
                }
                bundle2.remove(str2);
            }
        }
        return bundle2;
    }

    public String zza(String str, int i, boolean z) {
        if (str.length() <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, i)).concat("...");
        }
        return null;
    }

    public void zza(int i, String str, String str2, int i2) {
        zza(null, i, str, str2, i2);
    }

    public void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzJt().zzLd().zze("Not putting event parameter. Invalid value type. name, type", str, obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public void zza(zzauh.zzc zzc, Object obj) {
        zzac.zzw(obj);
        zzc.zzaFy = null;
        zzc.zzbvZ = null;
        zzc.zzbvc = null;
        if (obj instanceof String) {
            zzc.zzaFy = (String) obj;
        } else if (obj instanceof Long) {
            zzc.zzbvZ = (Long) obj;
        } else if (obj instanceof Double) {
            zzc.zzbvc = (Double) obj;
        } else {
            zzJt().zzLa().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public void zza(zzauh.zzg zzg, Object obj) {
        zzac.zzw(obj);
        zzg.zzaFy = null;
        zzg.zzbvZ = null;
        zzg.zzbvc = null;
        if (obj instanceof String) {
            zzg.zzaFy = (String) obj;
        } else if (obj instanceof Long) {
            zzg.zzbvZ = (Long) obj;
        } else if (obj instanceof Double) {
            zzg.zzbvc = (Double) obj;
        } else {
            zzJt().zzLa().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzbpw.zzJv().zzKk();
        this.zzbpw.zzJi().zze("auto", "_err", bundle);
    }

    /* access modifiers changed from: package-private */
    public boolean zza(String str, String str2, int i, Object obj) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            return false;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.length() <= i) {
            return true;
        }
        zzJt().zzLc().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
        return false;
    }

    public byte[] zza(zzauh.zzd zzd) {
        try {
            byte[] bArr = new byte[zzd.zzacZ()];
            zzbum zzae = zzbum.zzae(bArr);
            zzd.zza(zzae);
            zzae.zzacM();
            return bArr;
        } catch (IOException e) {
            zzJt().zzLa().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzaa(String str, String str2) {
        if (str2 == null) {
            zzJt().zzLa().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzJt().zzLa().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzJt().zzLa().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzJt().zzLa().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    public void zzb(Bundle bundle, Object obj) {
        zzac.zzw(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    @WorkerThread
    public boolean zzbV(String str) {
        zzmq();
        if (zzacx.zzaQ(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzJt().zzLf().zzj("Permission not granted", str);
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(String str, int i, String str2) {
        if (str2 == null) {
            zzJt().zzLa().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() <= i) {
            return true;
        } else {
            zzJt().zzLa().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(String str, Map<String, String> map, String str2) {
        if (str2 == null) {
            zzJt().zzLa().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.startsWith("firebase_")) {
            zzJt().zzLa().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        } else if (map == null || !map.containsKey(str2)) {
            return true;
        } else {
            zzJt().zzLa().zze("Name is reserved. Type, name", str, str2);
            return false;
        }
    }

    public boolean zzd(Bundle bundle, int i) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    public boolean zzf(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zznq().currentTimeMillis() - j) > j2;
    }

    public int zzfX(String str) {
        if (!zzZ("event", str)) {
            return 2;
        }
        if (!zzc("event", AppMeasurement.zza.zzbpx, str)) {
            return 13;
        }
        return zzc("event", zzJv().zzJU(), str) ? 0 : 2;
    }

    public int zzfY(String str) {
        if (!zzaa("event", str)) {
            return 2;
        }
        if (!zzc("event", AppMeasurement.zza.zzbpx, str)) {
            return 13;
        }
        return zzc("event", zzJv().zzJU(), str) ? 0 : 2;
    }

    public int zzfZ(String str) {
        if (!zzZ("user property", str)) {
            return 6;
        }
        if (!zzc("user property", AppMeasurement.zzg.zzbpC, str)) {
            return 15;
        }
        return zzc("user property", zzJv().zzJV(), str) ? 0 : 6;
    }

    public int zzga(String str) {
        if (!zzaa("user property", str)) {
            return 6;
        }
        if (!zzc("user property", AppMeasurement.zzg.zzbpC, str)) {
            return 15;
        }
        return zzc("user property", zzJv().zzJV(), str) ? 0 : 6;
    }

    public int zzgb(String str) {
        if (!zzZ("event param", str)) {
            return 3;
        }
        if (!zzc("event param", (Map<String, String>) null, str)) {
            return 14;
        }
        return zzc("event param", zzJv().zzJW(), str) ? 0 : 3;
    }

    public int zzgc(String str) {
        if (!zzaa("event param", str)) {
            return 3;
        }
        if (!zzc("event param", (Map<String, String>) null, str)) {
            return 14;
        }
        return zzc("event param", zzJv().zzJW(), str) ? 0 : 3;
    }

    public boolean zzgd(String str) {
        if (TextUtils.isEmpty(str)) {
            zzJt().zzLa().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        } else if (zzge(str)) {
            return true;
        } else {
            zzJt().zzLa().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzge(String str) {
        zzac.zzw(str);
        return str.matches("^1:\\d+:android:[a-f0-9]+$");
    }

    public boolean zzgh(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzKF = zzJv().zzKF();
        zzJv().zzKk();
        return zzKF.equals(str);
    }

    /* access modifiers changed from: package-private */
    public boolean zzgj(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zzJq().zzW(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public boolean zzgk(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zzJq().zzW(str, "measurement.upload.blacklist_public"));
    }

    public boolean zzk(String str, Object obj) {
        return zzgg(str) ? zza("param", str, zzJv().zzJY(), obj) : zza("param", str, zzJv().zzJX(), obj);
    }

    public byte[] zzk(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzJt().zzLa().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public Object zzl(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(zzJv().zzJY(), obj, true);
        }
        return zza(zzgg(str) ? zzJv().zzJY() : zzJv().zzJX(), obj, false);
    }

    public int zzm(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzgf(str), obj) : zza("user property", str, zzgf(str), obj) ? 0 : 7;
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzJt().zzLc().log("Utils falling back to Random for random id");
            }
        }
        this.zzbve.set(nextLong);
    }

    public Object zzn(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzgf(str), obj, true) : zza(zzgf(str), obj, false);
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }

    public Bundle zzu(@NonNull Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        Bundle bundle = null;
        if (uri != null) {
            try {
                if (uri.isHierarchical()) {
                    str4 = uri.getQueryParameter("utm_campaign");
                    str3 = uri.getQueryParameter("utm_source");
                    str2 = uri.getQueryParameter("utm_medium");
                    str = uri.getQueryParameter("gclid");
                } else {
                    str = null;
                    str2 = null;
                    str3 = null;
                    str4 = null;
                }
                if (!TextUtils.isEmpty(str4) || !TextUtils.isEmpty(str3) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
                    bundle = new Bundle();
                    if (!TextUtils.isEmpty(str4)) {
                        bundle.putString("campaign", str4);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        bundle.putString(ShareConstants.FEED_SOURCE_PARAM, str3);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        bundle.putString("medium", str2);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        bundle.putString("gclid", str);
                    }
                    String queryParameter = uri.getQueryParameter("utm_term");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        bundle.putString("term", queryParameter);
                    }
                    String queryParameter2 = uri.getQueryParameter("utm_content");
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        bundle.putString("content", queryParameter2);
                    }
                    String queryParameter3 = uri.getQueryParameter("aclid");
                    if (!TextUtils.isEmpty(queryParameter3)) {
                        bundle.putString("aclid", queryParameter3);
                    }
                    String queryParameter4 = uri.getQueryParameter("cp1");
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString("cp1", queryParameter4);
                    }
                    String queryParameter5 = uri.getQueryParameter("anid");
                    if (!TextUtils.isEmpty(queryParameter5)) {
                        bundle.putString("anid", queryParameter5);
                    }
                }
            } catch (UnsupportedOperationException e) {
                zzJt().zzLc().zzj("Install referrer url isn't a hierarchical URI", e);
            }
        }
        return bundle;
    }

    public byte[] zzx(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read <= 0) {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (IOException e) {
            zzJt().zzLa().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    @WorkerThread
    public long zzz(byte[] bArr) {
        zzac.zzw(bArr);
        zzmq();
        MessageDigest zzcg = zzcg("MD5");
        if (zzcg != null) {
            return zzy(zzcg.digest(bArr));
        }
        zzJt().zzLa().log("Failed to get MD5");
        return 0;
    }
}
