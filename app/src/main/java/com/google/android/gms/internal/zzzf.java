package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzbuy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;

public final class zzzf {
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("ClearcutLogger.API", zzahd, zzahc);
    public static final Api.zzf<zzzk> zzahc = new Api.zzf<>();
    public static final Api.zza<zzzk, Api.ApiOptions.NoOptions> zzahd = new Api.zza<zzzk, Api.ApiOptions.NoOptions>() {
        /* renamed from: zze */
        public zzzk zza(Context context, Looper looper, zzg zzg, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzzk(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
    /* access modifiers changed from: private */
    public final String zzQL;
    /* access modifiers changed from: private */
    public final int zzawl;
    /* access modifiers changed from: private */
    public String zzawm;
    /* access modifiers changed from: private */
    public int zzawn;
    /* access modifiers changed from: private */
    public String zzawo;
    /* access modifiers changed from: private */
    public String zzawp;
    /* access modifiers changed from: private */
    public final boolean zzawq;
    private int zzawr;
    /* access modifiers changed from: private */
    public final zzzg zzaws;
    /* access modifiers changed from: private */
    public zzd zzawt;
    /* access modifiers changed from: private */
    public final zzb zzawu;
    /* access modifiers changed from: private */
    public final zze zzuI;

    public class zza {
        private boolean zzawA;
        private final zzbuy.zzc zzawB;
        private boolean zzawC;
        private String zzawm;
        private int zzawn;
        private String zzawo;
        private String zzawp;
        private int zzawr;
        private final zzc zzawv;
        private ArrayList<Integer> zzaww;
        private ArrayList<String> zzawx;
        private ArrayList<Integer> zzawy;
        private ArrayList<byte[]> zzawz;

        private zza(zzzf zzzf, byte[] bArr) {
            this(bArr, (zzc) null);
        }

        private zza(byte[] bArr, zzc zzc) {
            this.zzawn = zzzf.this.zzawn;
            this.zzawm = zzzf.this.zzawm;
            this.zzawo = zzzf.this.zzawo;
            this.zzawp = zzzf.this.zzawp;
            this.zzawr = zzzf.zze(zzzf.this);
            this.zzaww = null;
            this.zzawx = null;
            this.zzawy = null;
            this.zzawz = null;
            this.zzawA = true;
            this.zzawB = new zzbuy.zzc();
            this.zzawC = false;
            this.zzawo = zzzf.this.zzawo;
            this.zzawp = zzzf.this.zzawp;
            this.zzawB.zzcsy = zzzf.this.zzuI.currentTimeMillis();
            this.zzawB.zzcsz = zzzf.this.zzuI.elapsedRealtime();
            this.zzawB.zzcsJ = zzzf.this.zzawt.zzG(this.zzawB.zzcsy);
            if (bArr != null) {
                this.zzawB.zzcsF = bArr;
            }
            this.zzawv = zzc;
        }

        public zza zzcn(int i) {
            this.zzawB.zzcsB = i;
            return this;
        }

        public zza zzco(int i) {
            this.zzawB.zzrn = i;
            return this;
        }

        @Deprecated
        public PendingResult<Status> zze(GoogleApiClient googleApiClient) {
            return zzuv();
        }

        public zzzh zzuu() {
            zzzh zzzh = new zzzh(new zzawe(zzzf.this.zzQL, zzzf.this.zzawl, this.zzawn, this.zzawm, this.zzawo, this.zzawp, zzzf.this.zzawq, this.zzawr), this.zzawB, this.zzawv, null, zzzf.zzb((ArrayList<Integer>) null), zzzf.zzc((ArrayList<String>) null), zzzf.zzb((ArrayList<Integer>) null), zzzf.zzd((ArrayList<byte[]>) null), this.zzawA);
            return zzzh;
        }

        @Deprecated
        public PendingResult<Status> zzuv() {
            if (this.zzawC) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.zzawC = true;
            zzzh zzuu = zzuu();
            zzawe zzawe = zzuu.zzawE;
            return zzzf.this.zzawu.zzh(zzawe.zzbzD, zzawe.zzbzz) ? zzzf.this.zzaws.zza(zzuu) : PendingResults.immediatePendingResult(Status.zzayh);
        }
    }

    public interface zzb {
        boolean zzh(String str, int i);
    }

    public interface zzc {
        byte[] zzuw();
    }

    public static class zzd {
        public long zzG(long j) {
            return (long) (TimeZone.getDefault().getOffset(j) / 1000);
        }
    }

    public zzzf(Context context, int i, String str, String str2, String str3, boolean z, zzzg zzzg, zze zze, zzd zzd2, zzb zzb2) {
        boolean z2 = false;
        this.zzawn = -1;
        this.zzawr = 0;
        this.zzQL = context.getPackageName();
        this.zzawl = zzah(context);
        this.zzawn = i;
        this.zzawm = str;
        this.zzawo = str2;
        this.zzawp = str3;
        this.zzawq = z;
        this.zzaws = zzzg;
        this.zzuI = zze;
        this.zzawt = zzd2 == null ? new zzd() : zzd2;
        this.zzawr = 0;
        this.zzawu = zzb2;
        if (this.zzawq) {
            zzac.zzb(this.zzawo == null ? true : z2, (Object) "can't be anonymous with an upload account");
        }
    }

    public zzzf(Context context, String str, String str2) {
        this(context, -1, str, str2, null, false, zzzj.zzai(context), zzh.zzyv(), null, new zzzo(context));
    }

    private int zzah(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return z;
        }
    }

    /* access modifiers changed from: private */
    public static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        int i = 0;
        Iterator<Integer> it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return iArr;
            }
            i = i2 + 1;
            iArr[i2] = it.next().intValue();
        }
    }

    /* access modifiers changed from: private */
    public static String[] zzc(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* access modifiers changed from: private */
    public static byte[][] zzd(ArrayList<byte[]> arrayList) {
        if (arrayList == null) {
            return null;
        }
        return (byte[][]) arrayList.toArray(new byte[0][]);
    }

    static /* synthetic */ int zze(zzzf zzzf) {
        return 0;
    }

    public zza zzm(byte[] bArr) {
        return new zza(bArr);
    }
}
