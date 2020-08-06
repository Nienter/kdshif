package com.google.android.gms.internal;

public interface zzae {

    public static final class zza extends zzbun<zza> {
        public String stackTrace = null;
        public String zzaR = null;
        public Long zzaS = null;
        public String zzaT = null;
        public String zzaU = null;
        public Long zzaV = null;
        public Long zzaW = null;
        public String zzaX = null;
        public Long zzaY = null;
        public String zzaZ = null;

        public zza() {
            this.zzcsg = -1;
        }

        public void zza(zzbum zzbum) {
            if (this.zzaR != null) {
                zzbum.zzq(1, this.zzaR);
            }
            if (this.zzaS != null) {
                zzbum.zzb(2, this.zzaS.longValue());
            }
            if (this.stackTrace != null) {
                zzbum.zzq(3, this.stackTrace);
            }
            if (this.zzaT != null) {
                zzbum.zzq(4, this.zzaT);
            }
            if (this.zzaU != null) {
                zzbum.zzq(5, this.zzaU);
            }
            if (this.zzaV != null) {
                zzbum.zzb(6, this.zzaV.longValue());
            }
            if (this.zzaW != null) {
                zzbum.zzb(7, this.zzaW.longValue());
            }
            if (this.zzaX != null) {
                zzbum.zzq(8, this.zzaX);
            }
            if (this.zzaY != null) {
                zzbum.zzb(9, this.zzaY.longValue());
            }
            if (this.zzaZ != null) {
                zzbum.zzq(10, this.zzaZ);
            }
            super.zza(zzbum);
        }

        /* renamed from: zze */
        public zza zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.zzaR = zzbul.readString();
                        continue;
                    case 16:
                        this.zzaS = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 26:
                        this.stackTrace = zzbul.readString();
                        continue;
                    case 34:
                        this.zzaT = zzbul.readString();
                        continue;
                    case 42:
                        this.zzaU = zzbul.readString();
                        continue;
                    case 48:
                        this.zzaV = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 56:
                        this.zzaW = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 66:
                        this.zzaX = zzbul.readString();
                        continue;
                    case 72:
                        this.zzaY = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 82:
                        this.zzaZ = zzbul.readString();
                        continue;
                    default:
                        if (!super.zza(zzbul, zzacu)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzaR != null) {
                zzv += zzbum.zzr(1, this.zzaR);
            }
            if (this.zzaS != null) {
                zzv += zzbum.zzf(2, this.zzaS.longValue());
            }
            if (this.stackTrace != null) {
                zzv += zzbum.zzr(3, this.stackTrace);
            }
            if (this.zzaT != null) {
                zzv += zzbum.zzr(4, this.zzaT);
            }
            if (this.zzaU != null) {
                zzv += zzbum.zzr(5, this.zzaU);
            }
            if (this.zzaV != null) {
                zzv += zzbum.zzf(6, this.zzaV.longValue());
            }
            if (this.zzaW != null) {
                zzv += zzbum.zzf(7, this.zzaW.longValue());
            }
            if (this.zzaX != null) {
                zzv += zzbum.zzr(8, this.zzaX);
            }
            if (this.zzaY != null) {
                zzv += zzbum.zzf(9, this.zzaY.longValue());
            }
            return this.zzaZ != null ? zzv + zzbum.zzr(10, this.zzaZ) : zzv;
        }
    }
}
