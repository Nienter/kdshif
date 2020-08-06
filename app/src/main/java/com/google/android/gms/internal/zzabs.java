package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzabs<T> {
    private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static zza zzaCd = null;
    private static int zzaCe = 0;
    private static final Object zztU = new Object();
    protected final String zzAH;
    protected final T zzAI;
    private T zzaCf = null;

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zzb(String str, Float f);

        Integer zzb(String str, Integer num);
    }

    protected zzabs(String str, T t) {
        this.zzAH = str;
        this.zzAI = t;
    }

    public static zzabs<String> zzA(String str, String str2) {
        return new zzabs<String>(str, str2) {
            /* access modifiers changed from: protected */
            /* renamed from: zzdi */
            public String zzdd(String str) {
                zza zza = null;
                return zza.getString(this.zzAH, (String) this.zzAI);
            }
        };
    }

    public static zzabs<Float> zza(String str, Float f) {
        return new zzabs<Float>(str, f) {
            /* access modifiers changed from: protected */
            /* renamed from: zzdh */
            public Float zzdd(String str) {
                zza zza = null;
                return zza.zzb(this.zzAH, (Float) this.zzAI);
            }
        };
    }

    public static zzabs<Integer> zza(String str, Integer num) {
        return new zzabs<Integer>(str, num) {
            /* access modifiers changed from: protected */
            /* renamed from: zzdg */
            public Integer zzdd(String str) {
                zza zza = null;
                return zza.zzb(this.zzAH, (Integer) this.zzAI);
            }
        };
    }

    public static zzabs<Long> zza(String str, Long l) {
        return new zzabs<Long>(str, l) {
            /* access modifiers changed from: protected */
            /* renamed from: zzdf */
            public Long zzdd(String str) {
                zza zza = null;
                return zza.getLong(this.zzAH, (Long) this.zzAI);
            }
        };
    }

    public static zzabs<Boolean> zzj(String str, boolean z) {
        return new zzabs<Boolean>(str, Boolean.valueOf(z)) {
            /* access modifiers changed from: protected */
            /* renamed from: zzde */
            public Boolean zzdd(String str) {
                zza zza = null;
                return zza.zza(this.zzAH, (Boolean) this.zzAI);
            }
        };
    }

    public final T get() {
        long clearCallingIdentity;
        try {
            return zzdd(this.zzAH);
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            T zzdd = zzdd(this.zzAH);
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zzdd;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public abstract T zzdd(String str);
}
