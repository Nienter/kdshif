package com.google.android.gms.internal;

import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzv;

@zzmb
public abstract class zzft<T> {
    private final int zzAG;
    private final String zzAH;
    private final T zzAI;

    private zzft(int i, String str, T t) {
        this.zzAG = i;
        this.zzAH = str;
        this.zzAI = t;
        zzv.zzcU().zza(this);
    }

    public static zzft<String> zza(int i, String str) {
        zzft<String> zza = zza(i, str, (String) null);
        zzv.zzcU().zzb(zza);
        return zza;
    }

    public static zzft<Float> zza(int i, String str, float f) {
        return new zzft<Float>(i, str, Float.valueOf(f)) {
            /* renamed from: zze */
            public Float zza(SharedPreferences sharedPreferences) {
                return Float.valueOf(sharedPreferences.getFloat(getKey(), ((Float) zzfm()).floatValue()));
            }
        };
    }

    public static zzft<Integer> zza(int i, String str, int i2) {
        return new zzft<Integer>(i, str, Integer.valueOf(i2)) {
            /* renamed from: zzc */
            public Integer zza(SharedPreferences sharedPreferences) {
                return Integer.valueOf(sharedPreferences.getInt(getKey(), ((Integer) zzfm()).intValue()));
            }
        };
    }

    public static zzft<Long> zza(int i, String str, long j) {
        return new zzft<Long>(i, str, Long.valueOf(j)) {
            /* renamed from: zzd */
            public Long zza(SharedPreferences sharedPreferences) {
                return Long.valueOf(sharedPreferences.getLong(getKey(), ((Long) zzfm()).longValue()));
            }
        };
    }

    public static zzft<Boolean> zza(int i, String str, Boolean bool) {
        return new zzft<Boolean>(i, str, bool) {
            /* renamed from: zzb */
            public Boolean zza(SharedPreferences sharedPreferences) {
                return Boolean.valueOf(sharedPreferences.getBoolean(getKey(), ((Boolean) zzfm()).booleanValue()));
            }
        };
    }

    public static zzft<String> zza(int i, String str, String str2) {
        return new zzft<String>(i, str, str2) {
            /* renamed from: zzf */
            public String zza(SharedPreferences sharedPreferences) {
                return sharedPreferences.getString(getKey(), (String) zzfm());
            }
        };
    }

    public static zzft<String> zzb(int i, String str) {
        zzft<String> zza = zza(i, str, (String) null);
        zzv.zzcU().zzc(zza);
        return zza;
    }

    public T get() {
        return zzv.zzcV().zzd(this);
    }

    public String getKey() {
        return this.zzAH;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(SharedPreferences sharedPreferences);

    public T zzfm() {
        return this.zzAI;
    }
}
