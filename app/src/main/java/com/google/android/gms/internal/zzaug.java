package com.google.android.gms.internal;

import com.google.android.gms.internal.zzauf;

public interface zzaug {

    public static final class zza extends zzbut {
        private static volatile zza[] zzbvH;
        public String name;
        public Boolean zzbvI;
        public Boolean zzbvJ;

        public zza() {
            zzMu();
        }

        public static zza[] zzMt() {
            if (zzbvH == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvH == null) {
                        zzbvH = new zza[0];
                    }
                }
            }
            return zzbvH;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.name == null) {
                if (zza.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zza.name)) {
                return false;
            }
            if (this.zzbvI == null) {
                if (zza.zzbvI != null) {
                    return false;
                }
            } else if (!this.zzbvI.equals(zza.zzbvI)) {
                return false;
            }
            return this.zzbvJ == null ? zza.zzbvJ == null : this.zzbvJ.equals(zza.zzbvJ);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvI == null ? 0 : this.zzbvI.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbvJ != null) {
                i = this.zzbvJ.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzM */
        public zza zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzbul.readString();
                        continue;
                    case 16:
                        this.zzbvI = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 24:
                        this.zzbvJ = Boolean.valueOf(zzbul.zzacA());
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

        public zza zzMu() {
            this.name = null;
            this.zzbvI = null;
            this.zzbvJ = null;
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.name != null) {
                zzbum.zzq(1, this.name);
            }
            if (this.zzbvI != null) {
                zzbum.zzg(2, this.zzbvI.booleanValue());
            }
            if (this.zzbvJ != null) {
                zzbum.zzg(3, this.zzbvJ.booleanValue());
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.name != null) {
                zzv += zzbum.zzr(1, this.name);
            }
            if (this.zzbvI != null) {
                zzv += zzbum.zzh(2, this.zzbvI.booleanValue());
            }
            return this.zzbvJ != null ? zzv + zzbum.zzh(3, this.zzbvJ.booleanValue()) : zzv;
        }
    }

    public static final class zzb extends zzbut {
        public String zzbqf;
        public Long zzbvK;
        public Integer zzbvL;
        public zzc[] zzbvM;
        public zza[] zzbvN;
        public zzauf.zza[] zzbvO;

        public zzb() {
            zzMv();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzbvK == null) {
                if (zzb.zzbvK != null) {
                    return false;
                }
            } else if (!this.zzbvK.equals(zzb.zzbvK)) {
                return false;
            }
            if (this.zzbqf == null) {
                if (zzb.zzbqf != null) {
                    return false;
                }
            } else if (!this.zzbqf.equals(zzb.zzbqf)) {
                return false;
            }
            if (this.zzbvL == null) {
                if (zzb.zzbvL != null) {
                    return false;
                }
            } else if (!this.zzbvL.equals(zzb.zzbvL)) {
                return false;
            }
            if (!zzbur.equals((Object[]) this.zzbvM, (Object[]) zzb.zzbvM)) {
                return false;
            }
            if (!zzbur.equals((Object[]) this.zzbvN, (Object[]) zzb.zzbvN)) {
                return false;
            }
            return zzbur.equals((Object[]) this.zzbvO, (Object[]) zzb.zzbvO);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbqf == null ? 0 : this.zzbqf.hashCode()) + (((this.zzbvK == null ? 0 : this.zzbvK.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbvL != null) {
                i = this.zzbvL.hashCode();
            }
            return ((((((hashCode + i) * 31) + zzbur.hashCode((Object[]) this.zzbvM)) * 31) + zzbur.hashCode((Object[]) this.zzbvN)) * 31) + zzbur.hashCode((Object[]) this.zzbvO);
        }

        public zzb zzMv() {
            this.zzbvK = null;
            this.zzbqf = null;
            this.zzbvL = null;
            this.zzbvM = zzc.zzMw();
            this.zzbvN = zza.zzMt();
            this.zzbvO = zzauf.zza.zzMj();
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzN */
        public zzb zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzbvK = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 18:
                        this.zzbqf = zzbul.readString();
                        continue;
                    case 24:
                        this.zzbvL = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 34:
                        int zzc = zzbuw.zzc(zzbul, 34);
                        int length = this.zzbvM == null ? 0 : this.zzbvM.length;
                        zzc[] zzcArr = new zzc[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbvM, 0, zzcArr, 0, length);
                        }
                        while (length < zzcArr.length - 1) {
                            zzcArr[length] = new zzc();
                            zzbul.zza(zzcArr[length]);
                            zzbul.zzacu();
                            length++;
                        }
                        zzcArr[length] = new zzc();
                        zzbul.zza(zzcArr[length]);
                        this.zzbvM = zzcArr;
                        continue;
                    case 42:
                        int zzc2 = zzbuw.zzc(zzbul, 42);
                        int length2 = this.zzbvN == null ? 0 : this.zzbvN.length;
                        zza[] zzaArr = new zza[(zzc2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzbvN, 0, zzaArr, 0, length2);
                        }
                        while (length2 < zzaArr.length - 1) {
                            zzaArr[length2] = new zza();
                            zzbul.zza(zzaArr[length2]);
                            zzbul.zzacu();
                            length2++;
                        }
                        zzaArr[length2] = new zza();
                        zzbul.zza(zzaArr[length2]);
                        this.zzbvN = zzaArr;
                        continue;
                    case 50:
                        int zzc3 = zzbuw.zzc(zzbul, 50);
                        int length3 = this.zzbvO == null ? 0 : this.zzbvO.length;
                        zzauf.zza[] zzaArr2 = new zzauf.zza[(zzc3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzbvO, 0, zzaArr2, 0, length3);
                        }
                        while (length3 < zzaArr2.length - 1) {
                            zzaArr2[length3] = new zzauf.zza();
                            zzbul.zza(zzaArr2[length3]);
                            zzbul.zzacu();
                            length3++;
                        }
                        zzaArr2[length3] = new zzauf.zza();
                        zzbul.zza(zzaArr2[length3]);
                        this.zzbvO = zzaArr2;
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
            if (this.zzbvK != null) {
                zzbum.zzb(1, this.zzbvK.longValue());
            }
            if (this.zzbqf != null) {
                zzbum.zzq(2, this.zzbqf);
            }
            if (this.zzbvL != null) {
                zzbum.zzF(3, this.zzbvL.intValue());
            }
            if (this.zzbvM != null && this.zzbvM.length > 0) {
                for (zzc zzc : this.zzbvM) {
                    if (zzc != null) {
                        zzbum.zza(4, (zzbut) zzc);
                    }
                }
            }
            if (this.zzbvN != null && this.zzbvN.length > 0) {
                for (zza zza : this.zzbvN) {
                    if (zza != null) {
                        zzbum.zza(5, (zzbut) zza);
                    }
                }
            }
            if (this.zzbvO != null && this.zzbvO.length > 0) {
                for (zzauf.zza zza2 : this.zzbvO) {
                    if (zza2 != null) {
                        zzbum.zza(6, (zzbut) zza2);
                    }
                }
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvK != null) {
                zzv += zzbum.zzf(1, this.zzbvK.longValue());
            }
            if (this.zzbqf != null) {
                zzv += zzbum.zzr(2, this.zzbqf);
            }
            if (this.zzbvL != null) {
                zzv += zzbum.zzH(3, this.zzbvL.intValue());
            }
            if (this.zzbvM != null && this.zzbvM.length > 0) {
                int i = zzv;
                for (zzc zzc : this.zzbvM) {
                    if (zzc != null) {
                        i += zzbum.zzc(4, (zzbut) zzc);
                    }
                }
                zzv = i;
            }
            if (this.zzbvN != null && this.zzbvN.length > 0) {
                int i2 = zzv;
                for (zza zza : this.zzbvN) {
                    if (zza != null) {
                        i2 += zzbum.zzc(5, (zzbut) zza);
                    }
                }
                zzv = i2;
            }
            if (this.zzbvO != null && this.zzbvO.length > 0) {
                for (zzauf.zza zza2 : this.zzbvO) {
                    if (zza2 != null) {
                        zzv += zzbum.zzc(6, (zzbut) zza2);
                    }
                }
            }
            return zzv;
        }
    }

    public static final class zzc extends zzbut {
        private static volatile zzc[] zzbvP;
        public String value;
        public String zzaA;

        public zzc() {
            zzMx();
        }

        public static zzc[] zzMw() {
            if (zzbvP == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvP == null) {
                        zzbvP = new zzc[0];
                    }
                }
            }
            return zzbvP;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzaA == null) {
                if (zzc.zzaA != null) {
                    return false;
                }
            } else if (!this.zzaA.equals(zzc.zzaA)) {
                return false;
            }
            return this.value == null ? zzc.value == null : this.value.equals(zzc.value);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzaA == null ? 0 : this.zzaA.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31;
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode + i;
        }

        public zzc zzMx() {
            this.zzaA = null;
            this.value = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzO */
        public zzc zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.zzaA = zzbul.readString();
                        continue;
                    case 18:
                        this.value = zzbul.readString();
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
            if (this.zzaA != null) {
                zzbum.zzq(1, this.zzaA);
            }
            if (this.value != null) {
                zzbum.zzq(2, this.value);
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzaA != null) {
                zzv += zzbum.zzr(1, this.zzaA);
            }
            return this.value != null ? zzv + zzbum.zzr(2, this.value) : zzv;
        }
    }
}
