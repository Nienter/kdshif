package com.google.android.gms.internal;

public interface zzad {

    public static final class zza extends zzbut {
        public zzb zzaJ;
        public zzc zzaK;

        public zza() {
            zzu();
        }

        public static zza zzc(byte[] bArr) {
            return (zza) zzbut.zza(new zza(), bArr);
        }

        /* renamed from: zza */
        public zza zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        if (this.zzaJ == null) {
                            this.zzaJ = new zzb();
                        }
                        zzbul.zza(this.zzaJ);
                        continue;
                    case 18:
                        if (this.zzaK == null) {
                            this.zzaK = new zzc();
                        }
                        zzbul.zza(this.zzaK);
                        continue;
                    default:
                        if (!zzbuw.zzb(zzbul, zzacu)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.zzaJ != null) {
                zzbum.zza(1, (zzbut) this.zzaJ);
            }
            if (this.zzaK != null) {
                zzbum.zza(2, (zzbut) this.zzaK);
            }
            super.zza(zzbum);
        }

        public zza zzu() {
            this.zzaJ = null;
            this.zzaK = null;
            this.zzcsg = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzaJ != null) {
                zzv += zzbum.zzc(1, (zzbut) this.zzaJ);
            }
            return this.zzaK != null ? zzv + zzbum.zzc(2, (zzbut) this.zzaK) : zzv;
        }
    }

    public static final class zzb extends zzbut {
        public Integer zzaL;

        public zzb() {
            zzw();
        }

        public void zza(zzbum zzbum) {
            if (this.zzaL != null) {
                zzbum.zzF(27, this.zzaL.intValue());
            }
            super.zza(zzbum);
        }

        /* renamed from: zzc */
        public zzb zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 216:
                        int zzacy = zzbul.zzacy();
                        switch (zzacy) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.zzaL = Integer.valueOf(zzacy);
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!zzbuw.zzb(zzbul, zzacu)) {
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
            return this.zzaL != null ? zzv + zzbum.zzH(27, this.zzaL.intValue()) : zzv;
        }

        public zzb zzw() {
            this.zzcsg = -1;
            return this;
        }
    }

    public static final class zzc extends zzbut {
        public String zzaM;
        public String zzaN;
        public String zzaO;
        public String zzaP;
        public String zzaQ;

        public zzc() {
            zzx();
        }

        public void zza(zzbum zzbum) {
            if (this.zzaM != null) {
                zzbum.zzq(1, this.zzaM);
            }
            if (this.zzaN != null) {
                zzbum.zzq(2, this.zzaN);
            }
            if (this.zzaO != null) {
                zzbum.zzq(3, this.zzaO);
            }
            if (this.zzaP != null) {
                zzbum.zzq(4, this.zzaP);
            }
            if (this.zzaQ != null) {
                zzbum.zzq(5, this.zzaQ);
            }
            super.zza(zzbum);
        }

        /* renamed from: zzd */
        public zzc zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.zzaM = zzbul.readString();
                        continue;
                    case 18:
                        this.zzaN = zzbul.readString();
                        continue;
                    case 26:
                        this.zzaO = zzbul.readString();
                        continue;
                    case 34:
                        this.zzaP = zzbul.readString();
                        continue;
                    case 42:
                        this.zzaQ = zzbul.readString();
                        continue;
                    default:
                        if (!zzbuw.zzb(zzbul, zzacu)) {
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
            if (this.zzaM != null) {
                zzv += zzbum.zzr(1, this.zzaM);
            }
            if (this.zzaN != null) {
                zzv += zzbum.zzr(2, this.zzaN);
            }
            if (this.zzaO != null) {
                zzv += zzbum.zzr(3, this.zzaO);
            }
            if (this.zzaP != null) {
                zzv += zzbum.zzr(4, this.zzaP);
            }
            return this.zzaQ != null ? zzv + zzbum.zzr(5, this.zzaQ) : zzv;
        }

        public zzc zzx() {
            this.zzaM = null;
            this.zzaN = null;
            this.zzaO = null;
            this.zzaP = null;
            this.zzaQ = null;
            this.zzcsg = -1;
            return this;
        }
    }
}
