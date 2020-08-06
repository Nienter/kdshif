package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzzf;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class zzzo implements zzzf.zzb {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    static Boolean zzawQ = null;
    final zza zzawR;

    static class zza {
        final ContentResolver mContentResolver;

        zza(Context context) {
            if (context == null || !zzaj(context)) {
                this.mContentResolver = null;
                return;
            }
            this.mContentResolver = context.getContentResolver();
            zzbii.zzb(this.mContentResolver, "gms:playlog:service:sampling_");
        }

        private static boolean zzaj(Context context) {
            if (zzzo.zzawQ == null) {
                zzzo.zzawQ = Boolean.valueOf(context.checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
            }
            return zzzo.zzawQ.booleanValue();
        }

        /* access modifiers changed from: package-private */
        public String zzda(String str) {
            if (this.mContentResolver == null) {
                return null;
            }
            ContentResolver contentResolver = this.mContentResolver;
            String valueOf = String.valueOf("gms:playlog:service:sampling_");
            String valueOf2 = String.valueOf(str);
            return zzbii.zza(contentResolver, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), (String) null);
        }

        /* access modifiers changed from: package-private */
        public long zzux() {
            if (this.mContentResolver == null) {
                return 0;
            }
            return zzbii.getLong(this.mContentResolver, "android_id", 0);
        }
    }

    static class zzb {
        public final String zzawS;
        public final long zzawT;
        public final long zzawU;

        public zzb(String str, long j, long j2) {
            this.zzawS = str;
            this.zzawT = j;
            this.zzawU = j2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            return zzaa.equal(this.zzawS, zzb.zzawS) && zzaa.equal(Long.valueOf(this.zzawT), Long.valueOf(zzb.zzawT)) && zzaa.equal(Long.valueOf(this.zzawU), Long.valueOf(zzb.zzawU));
        }

        public int hashCode() {
            return zzaa.hashCode(this.zzawS, Long.valueOf(this.zzawT), Long.valueOf(this.zzawU));
        }
    }

    public zzzo() {
        this(new zza(null));
    }

    public zzzo(Context context) {
        this(new zza(context));
    }

    zzzo(zza zza2) {
        this.zzawR = (zza) zzac.zzw(zza2);
    }

    static long zzI(long j) {
        return zzzl.zzn(ByteBuffer.allocate(8).putLong(j).array());
    }

    static boolean zza(long j, long j2, long j3) {
        if (j2 >= 0 && j3 >= 0) {
            return j3 > 0 && zzzp.zzd(j, j3) < j2;
        }
        throw new IllegalArgumentException(new StringBuilder(72).append("negative values not supported: ").append(j2).append("/").append(j3).toString());
    }

    static zzb zzcZ(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            String valueOf = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf.length() != 0 ? "Failed to parse the rule: ".concat(valueOf) : new String("Failed to parse the rule: "));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzb(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", new StringBuilder(72).append("negative values not supported: ").append(parseLong).append("/").append(parseLong2).toString());
            return null;
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            String valueOf2 = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf2.length() != 0 ? "parseLong() failed while parsing: ".concat(valueOf2) : new String("parseLong() failed while parsing: "), numberFormatException);
            return null;
        }
    }

    static long zzd(String str, long j) {
        if (str == null || str.isEmpty()) {
            return zzI(j);
        }
        byte[] bytes = str.getBytes(UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
        allocate.put(bytes);
        allocate.putLong(j);
        return zzzl.zzn(allocate.array());
    }

    public boolean zzh(String str, int i) {
        if (str == null || str.isEmpty()) {
            str = i >= 0 ? String.valueOf(i) : null;
        }
        if (str == null) {
            return true;
        }
        long zzux = this.zzawR.zzux();
        zzb zzcZ = zzcZ(this.zzawR.zzda(str));
        if (zzcZ != null) {
            return zza(zzd(zzcZ.zzawS, zzux), zzcZ.zzawT, zzcZ.zzawU);
        }
        return true;
    }
}
