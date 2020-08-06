package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzm;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

@zzmb
public class zzpp {
    private static zzl zzXw;
    private static final Object zzXx = new Object();
    public static final zza<Void> zzXy = new zza<Void>() {
        public /* bridge */ /* synthetic */ Object zzh(InputStream inputStream) {
            return null;
        }

        public /* bridge */ /* synthetic */ Object zziT() {
            return null;
        }
    };

    public interface zza<T> {
        T zzh(InputStream inputStream);

        T zziT();
    }

    private static class zzb<T> extends zzk<InputStream> {
        private final zza<T> zzXC;
        private final zzm.zzb<T> zzaF;

        public zzb(String str, final zza<T> zza, final zzm.zzb<T> zzb) {
            super(0, str, new zzm.zza() {
                public void zze(zzr zzr) {
                    zzm.zzb.this.zzb(zza.zziT());
                }
            });
            this.zzXC = zza;
            this.zzaF = zzb;
        }

        /* access modifiers changed from: protected */
        public zzm<InputStream> zza(zzi zzi) {
            return zzm.zza(new ByteArrayInputStream(zzi.data), zzx.zzb(zzi));
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzj */
        public void zza(InputStream inputStream) {
            this.zzaF.zzb(this.zzXC.zzh(inputStream));
        }
    }

    private class zzc<T> extends zzqc<T> implements zzm.zzb<T> {
        private zzc(zzpp zzpp) {
        }

        public void zzb(@Nullable T t) {
            super.zzh(t);
        }
    }

    public zzpp(Context context) {
        zzN(context);
    }

    private static zzl zzN(Context context) {
        zzl zzl;
        synchronized (zzXx) {
            if (zzXw == null) {
                zzXw = zzac.zza(context.getApplicationContext());
            }
            zzl = zzXw;
        }
        return zzl;
    }

    public zzqf<String> zza(int i, final String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        final zzc zzc2 = new zzc();
        final byte[] bArr2 = bArr;
        final Map<String, String> map2 = map;
        zzXw.zze(new zzab(this, i, str, zzc2, new zzm.zza(this) {
            public void zze(zzr zzr) {
                String str = str;
                String valueOf = String.valueOf(zzr.toString());
                zzpe.zzbe(new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(valueOf).length()).append("Failed to load URL: ").append(str).append("\n").append(valueOf).toString());
                zzc2.zzb(null);
            }
        }) {
            public Map<String, String> getHeaders() {
                return map2 == null ? super.getHeaders() : map2;
            }

            public byte[] zzm() {
                return bArr2 == null ? super.zzm() : bArr2;
            }
        });
        return zzc2;
    }

    public <T> zzqf<T> zza(String str, zza<T> zza2) {
        zzc zzc2 = new zzc();
        zzXw.zze(new zzb(str, zza2, zzc2));
        return zzc2;
    }

    public zzqf<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }
}
