package com.google.android.gms.internal;

@zzmb
public class zzob {
    public final int errorCode;
    public final String zzJJ;
    public final long zzKF;
    public final String zzUV;

    public static final class zza {
        /* access modifiers changed from: private */
        public String zzKo;
        /* access modifiers changed from: private */
        public int zzPF;
        /* access modifiers changed from: private */
        public String zzUW;
        /* access modifiers changed from: private */
        public long zzUX;

        public zza zzaO(String str) {
            this.zzKo = str;
            return this;
        }

        public zza zzaP(String str) {
            this.zzUW = str;
            return this;
        }

        public zza zzac(int i) {
            this.zzPF = i;
            return this;
        }

        public zzob zzjt() {
            return new zzob(this);
        }

        public zza zzl(long j) {
            this.zzUX = j;
            return this;
        }
    }

    private zzob(zza zza2) {
        this.zzUV = zza2.zzKo;
        this.zzJJ = zza2.zzUW;
        this.errorCode = zza2.zzPF;
        this.zzKF = zza2.zzUX;
    }
}
