package com.google.android.gms.internal;

public interface zzauf {

    public static final class zza extends zzbut {
        private static volatile zza[] zzbvg;
        public Integer zzbvh;
        public zze[] zzbvi;
        public zzb[] zzbvj;

        public zza() {
            zzMk();
        }

        public static zza[] zzMj() {
            if (zzbvg == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvg == null) {
                        zzbvg = new zza[0];
                    }
                }
            }
            return zzbvg;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.zzbvh == null) {
                if (zza.zzbvh != null) {
                    return false;
                }
            } else if (!this.zzbvh.equals(zza.zzbvh)) {
                return false;
            }
            if (!zzbur.equals((Object[]) this.zzbvi, (Object[]) zza.zzbvi)) {
                return false;
            }
            return zzbur.equals((Object[]) this.zzbvj, (Object[]) zza.zzbvj);
        }

        public int hashCode() {
            return (((((this.zzbvh == null ? 0 : this.zzbvh.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzbur.hashCode((Object[]) this.zzbvi)) * 31) + zzbur.hashCode((Object[]) this.zzbvj);
        }

        /* renamed from: zzG */
        public zza zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzbvh = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 18:
                        int zzc = zzbuw.zzc(zzbul, 18);
                        int length = this.zzbvi == null ? 0 : this.zzbvi.length;
                        zze[] zzeArr = new zze[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbvi, 0, zzeArr, 0, length);
                        }
                        while (length < zzeArr.length - 1) {
                            zzeArr[length] = new zze();
                            zzbul.zza(zzeArr[length]);
                            zzbul.zzacu();
                            length++;
                        }
                        zzeArr[length] = new zze();
                        zzbul.zza(zzeArr[length]);
                        this.zzbvi = zzeArr;
                        continue;
                    case 26:
                        int zzc2 = zzbuw.zzc(zzbul, 26);
                        int length2 = this.zzbvj == null ? 0 : this.zzbvj.length;
                        zzb[] zzbArr = new zzb[(zzc2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzbvj, 0, zzbArr, 0, length2);
                        }
                        while (length2 < zzbArr.length - 1) {
                            zzbArr[length2] = new zzb();
                            zzbul.zza(zzbArr[length2]);
                            zzbul.zzacu();
                            length2++;
                        }
                        zzbArr[length2] = new zzb();
                        zzbul.zza(zzbArr[length2]);
                        this.zzbvj = zzbArr;
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

        public zza zzMk() {
            this.zzbvh = null;
            this.zzbvi = zze.zzMq();
            this.zzbvj = zzb.zzMl();
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.zzbvh != null) {
                zzbum.zzF(1, this.zzbvh.intValue());
            }
            if (this.zzbvi != null && this.zzbvi.length > 0) {
                for (zze zze : this.zzbvi) {
                    if (zze != null) {
                        zzbum.zza(2, (zzbut) zze);
                    }
                }
            }
            if (this.zzbvj != null && this.zzbvj.length > 0) {
                for (zzb zzb : this.zzbvj) {
                    if (zzb != null) {
                        zzbum.zza(3, (zzbut) zzb);
                    }
                }
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvh != null) {
                zzv += zzbum.zzH(1, this.zzbvh.intValue());
            }
            if (this.zzbvi != null && this.zzbvi.length > 0) {
                int i = zzv;
                for (zze zze : this.zzbvi) {
                    if (zze != null) {
                        i += zzbum.zzc(2, (zzbut) zze);
                    }
                }
                zzv = i;
            }
            if (this.zzbvj != null && this.zzbvj.length > 0) {
                for (zzb zzb : this.zzbvj) {
                    if (zzb != null) {
                        zzv += zzbum.zzc(3, (zzbut) zzb);
                    }
                }
            }
            return zzv;
        }
    }

    public static final class zzb extends zzbut {
        private static volatile zzb[] zzbvk;
        public Integer zzbvl;
        public String zzbvm;
        public zzc[] zzbvn;
        public Boolean zzbvo;
        public zzd zzbvp;

        public zzb() {
            zzMm();
        }

        public static zzb[] zzMl() {
            if (zzbvk == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvk == null) {
                        zzbvk = new zzb[0];
                    }
                }
            }
            return zzbvk;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzbvl == null) {
                if (zzb.zzbvl != null) {
                    return false;
                }
            } else if (!this.zzbvl.equals(zzb.zzbvl)) {
                return false;
            }
            if (this.zzbvm == null) {
                if (zzb.zzbvm != null) {
                    return false;
                }
            } else if (!this.zzbvm.equals(zzb.zzbvm)) {
                return false;
            }
            if (!zzbur.equals((Object[]) this.zzbvn, (Object[]) zzb.zzbvn)) {
                return false;
            }
            if (this.zzbvo == null) {
                if (zzb.zzbvo != null) {
                    return false;
                }
            } else if (!this.zzbvo.equals(zzb.zzbvo)) {
                return false;
            }
            return this.zzbvp == null ? zzb.zzbvp == null : this.zzbvp.equals(zzb.zzbvp);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvo == null ? 0 : this.zzbvo.hashCode()) + (((((this.zzbvm == null ? 0 : this.zzbvm.hashCode()) + (((this.zzbvl == null ? 0 : this.zzbvl.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + zzbur.hashCode((Object[]) this.zzbvn)) * 31)) * 31;
            if (this.zzbvp != null) {
                i = this.zzbvp.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzH */
        public zzb zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzbvl = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 18:
                        this.zzbvm = zzbul.readString();
                        continue;
                    case 26:
                        int zzc = zzbuw.zzc(zzbul, 26);
                        int length = this.zzbvn == null ? 0 : this.zzbvn.length;
                        zzc[] zzcArr = new zzc[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbvn, 0, zzcArr, 0, length);
                        }
                        while (length < zzcArr.length - 1) {
                            zzcArr[length] = new zzc();
                            zzbul.zza(zzcArr[length]);
                            zzbul.zzacu();
                            length++;
                        }
                        zzcArr[length] = new zzc();
                        zzbul.zza(zzcArr[length]);
                        this.zzbvn = zzcArr;
                        continue;
                    case 32:
                        this.zzbvo = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 42:
                        if (this.zzbvp == null) {
                            this.zzbvp = new zzd();
                        }
                        zzbul.zza(this.zzbvp);
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

        public zzb zzMm() {
            this.zzbvl = null;
            this.zzbvm = null;
            this.zzbvn = zzc.zzMn();
            this.zzbvo = null;
            this.zzbvp = null;
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.zzbvl != null) {
                zzbum.zzF(1, this.zzbvl.intValue());
            }
            if (this.zzbvm != null) {
                zzbum.zzq(2, this.zzbvm);
            }
            if (this.zzbvn != null && this.zzbvn.length > 0) {
                for (zzc zzc : this.zzbvn) {
                    if (zzc != null) {
                        zzbum.zza(3, (zzbut) zzc);
                    }
                }
            }
            if (this.zzbvo != null) {
                zzbum.zzg(4, this.zzbvo.booleanValue());
            }
            if (this.zzbvp != null) {
                zzbum.zza(5, (zzbut) this.zzbvp);
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvl != null) {
                zzv += zzbum.zzH(1, this.zzbvl.intValue());
            }
            if (this.zzbvm != null) {
                zzv += zzbum.zzr(2, this.zzbvm);
            }
            if (this.zzbvn != null && this.zzbvn.length > 0) {
                int i = zzv;
                for (zzc zzc : this.zzbvn) {
                    if (zzc != null) {
                        i += zzbum.zzc(3, (zzbut) zzc);
                    }
                }
                zzv = i;
            }
            if (this.zzbvo != null) {
                zzv += zzbum.zzh(4, this.zzbvo.booleanValue());
            }
            return this.zzbvp != null ? zzv + zzbum.zzc(5, (zzbut) this.zzbvp) : zzv;
        }
    }

    public static final class zzc extends zzbut {
        private static volatile zzc[] zzbvq;
        public zzf zzbvr;
        public zzd zzbvs;
        public Boolean zzbvt;
        public String zzbvu;

        public zzc() {
            zzMo();
        }

        public static zzc[] zzMn() {
            if (zzbvq == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvq == null) {
                        zzbvq = new zzc[0];
                    }
                }
            }
            return zzbvq;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzbvr == null) {
                if (zzc.zzbvr != null) {
                    return false;
                }
            } else if (!this.zzbvr.equals(zzc.zzbvr)) {
                return false;
            }
            if (this.zzbvs == null) {
                if (zzc.zzbvs != null) {
                    return false;
                }
            } else if (!this.zzbvs.equals(zzc.zzbvs)) {
                return false;
            }
            if (this.zzbvt == null) {
                if (zzc.zzbvt != null) {
                    return false;
                }
            } else if (!this.zzbvt.equals(zzc.zzbvt)) {
                return false;
            }
            return this.zzbvu == null ? zzc.zzbvu == null : this.zzbvu.equals(zzc.zzbvu);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvt == null ? 0 : this.zzbvt.hashCode()) + (((this.zzbvs == null ? 0 : this.zzbvs.hashCode()) + (((this.zzbvr == null ? 0 : this.zzbvr.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
            if (this.zzbvu != null) {
                i = this.zzbvu.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzI */
        public zzc zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        if (this.zzbvr == null) {
                            this.zzbvr = new zzf();
                        }
                        zzbul.zza(this.zzbvr);
                        continue;
                    case 18:
                        if (this.zzbvs == null) {
                            this.zzbvs = new zzd();
                        }
                        zzbul.zza(this.zzbvs);
                        continue;
                    case 24:
                        this.zzbvt = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 34:
                        this.zzbvu = zzbul.readString();
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

        public zzc zzMo() {
            this.zzbvr = null;
            this.zzbvs = null;
            this.zzbvt = null;
            this.zzbvu = null;
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.zzbvr != null) {
                zzbum.zza(1, (zzbut) this.zzbvr);
            }
            if (this.zzbvs != null) {
                zzbum.zza(2, (zzbut) this.zzbvs);
            }
            if (this.zzbvt != null) {
                zzbum.zzg(3, this.zzbvt.booleanValue());
            }
            if (this.zzbvu != null) {
                zzbum.zzq(4, this.zzbvu);
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvr != null) {
                zzv += zzbum.zzc(1, (zzbut) this.zzbvr);
            }
            if (this.zzbvs != null) {
                zzv += zzbum.zzc(2, (zzbut) this.zzbvs);
            }
            if (this.zzbvt != null) {
                zzv += zzbum.zzh(3, this.zzbvt.booleanValue());
            }
            return this.zzbvu != null ? zzv + zzbum.zzr(4, this.zzbvu) : zzv;
        }
    }

    public static final class zzd extends zzbut {
        public Integer zzbvv;
        public Boolean zzbvw;
        public String zzbvx;
        public String zzbvy;
        public String zzbvz;

        public zzd() {
            zzMp();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzbvv == null) {
                if (zzd.zzbvv != null) {
                    return false;
                }
            } else if (!this.zzbvv.equals(zzd.zzbvv)) {
                return false;
            }
            if (this.zzbvw == null) {
                if (zzd.zzbvw != null) {
                    return false;
                }
            } else if (!this.zzbvw.equals(zzd.zzbvw)) {
                return false;
            }
            if (this.zzbvx == null) {
                if (zzd.zzbvx != null) {
                    return false;
                }
            } else if (!this.zzbvx.equals(zzd.zzbvx)) {
                return false;
            }
            if (this.zzbvy == null) {
                if (zzd.zzbvy != null) {
                    return false;
                }
            } else if (!this.zzbvy.equals(zzd.zzbvy)) {
                return false;
            }
            return this.zzbvz == null ? zzd.zzbvz == null : this.zzbvz.equals(zzd.zzbvz);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvy == null ? 0 : this.zzbvy.hashCode()) + (((this.zzbvx == null ? 0 : this.zzbvx.hashCode()) + (((this.zzbvw == null ? 0 : this.zzbvw.hashCode()) + (((this.zzbvv == null ? 0 : this.zzbvv.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzbvz != null) {
                i = this.zzbvz.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzJ */
        public zzd zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        int zzacy = zzbul.zzacy();
                        switch (zzacy) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.zzbvv = Integer.valueOf(zzacy);
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        this.zzbvw = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 26:
                        this.zzbvx = zzbul.readString();
                        continue;
                    case 34:
                        this.zzbvy = zzbul.readString();
                        continue;
                    case 42:
                        this.zzbvz = zzbul.readString();
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

        public zzd zzMp() {
            this.zzbvw = null;
            this.zzbvx = null;
            this.zzbvy = null;
            this.zzbvz = null;
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.zzbvv != null) {
                zzbum.zzF(1, this.zzbvv.intValue());
            }
            if (this.zzbvw != null) {
                zzbum.zzg(2, this.zzbvw.booleanValue());
            }
            if (this.zzbvx != null) {
                zzbum.zzq(3, this.zzbvx);
            }
            if (this.zzbvy != null) {
                zzbum.zzq(4, this.zzbvy);
            }
            if (this.zzbvz != null) {
                zzbum.zzq(5, this.zzbvz);
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvv != null) {
                zzv += zzbum.zzH(1, this.zzbvv.intValue());
            }
            if (this.zzbvw != null) {
                zzv += zzbum.zzh(2, this.zzbvw.booleanValue());
            }
            if (this.zzbvx != null) {
                zzv += zzbum.zzr(3, this.zzbvx);
            }
            if (this.zzbvy != null) {
                zzv += zzbum.zzr(4, this.zzbvy);
            }
            return this.zzbvz != null ? zzv + zzbum.zzr(5, this.zzbvz) : zzv;
        }
    }

    public static final class zze extends zzbut {
        private static volatile zze[] zzbvA;
        public String zzbvB;
        public zzc zzbvC;
        public Integer zzbvl;

        public zze() {
            zzMr();
        }

        public static zze[] zzMq() {
            if (zzbvA == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvA == null) {
                        zzbvA = new zze[0];
                    }
                }
            }
            return zzbvA;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzbvl == null) {
                if (zze.zzbvl != null) {
                    return false;
                }
            } else if (!this.zzbvl.equals(zze.zzbvl)) {
                return false;
            }
            if (this.zzbvB == null) {
                if (zze.zzbvB != null) {
                    return false;
                }
            } else if (!this.zzbvB.equals(zze.zzbvB)) {
                return false;
            }
            return this.zzbvC == null ? zze.zzbvC == null : this.zzbvC.equals(zze.zzbvC);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvB == null ? 0 : this.zzbvB.hashCode()) + (((this.zzbvl == null ? 0 : this.zzbvl.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbvC != null) {
                i = this.zzbvC.hashCode();
            }
            return hashCode + i;
        }

        /* renamed from: zzK */
        public zze zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzbvl = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 18:
                        this.zzbvB = zzbul.readString();
                        continue;
                    case 26:
                        if (this.zzbvC == null) {
                            this.zzbvC = new zzc();
                        }
                        zzbul.zza(this.zzbvC);
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

        public zze zzMr() {
            this.zzbvl = null;
            this.zzbvB = null;
            this.zzbvC = null;
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.zzbvl != null) {
                zzbum.zzF(1, this.zzbvl.intValue());
            }
            if (this.zzbvB != null) {
                zzbum.zzq(2, this.zzbvB);
            }
            if (this.zzbvC != null) {
                zzbum.zza(3, (zzbut) this.zzbvC);
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvl != null) {
                zzv += zzbum.zzH(1, this.zzbvl.intValue());
            }
            if (this.zzbvB != null) {
                zzv += zzbum.zzr(2, this.zzbvB);
            }
            return this.zzbvC != null ? zzv + zzbum.zzc(3, (zzbut) this.zzbvC) : zzv;
        }
    }

    public static final class zzf extends zzbut {
        public Integer zzbvD;
        public String zzbvE;
        public Boolean zzbvF;
        public String[] zzbvG;

        public zzf() {
            zzMs();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (this.zzbvD == null) {
                if (zzf.zzbvD != null) {
                    return false;
                }
            } else if (!this.zzbvD.equals(zzf.zzbvD)) {
                return false;
            }
            if (this.zzbvE == null) {
                if (zzf.zzbvE != null) {
                    return false;
                }
            } else if (!this.zzbvE.equals(zzf.zzbvE)) {
                return false;
            }
            if (this.zzbvF == null) {
                if (zzf.zzbvF != null) {
                    return false;
                }
            } else if (!this.zzbvF.equals(zzf.zzbvF)) {
                return false;
            }
            return zzbur.equals((Object[]) this.zzbvG, (Object[]) zzf.zzbvG);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvE == null ? 0 : this.zzbvE.hashCode()) + (((this.zzbvD == null ? 0 : this.zzbvD.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbvF != null) {
                i = this.zzbvF.hashCode();
            }
            return ((hashCode + i) * 31) + zzbur.hashCode((Object[]) this.zzbvG);
        }

        /* renamed from: zzL */
        public zzf zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        int zzacy = zzbul.zzacy();
                        switch (zzacy) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                this.zzbvD = Integer.valueOf(zzacy);
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.zzbvE = zzbul.readString();
                        continue;
                    case 24:
                        this.zzbvF = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 34:
                        int zzc = zzbuw.zzc(zzbul, 34);
                        int length = this.zzbvG == null ? 0 : this.zzbvG.length;
                        String[] strArr = new String[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbvG, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = zzbul.readString();
                            zzbul.zzacu();
                            length++;
                        }
                        strArr[length] = zzbul.readString();
                        this.zzbvG = strArr;
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

        public zzf zzMs() {
            this.zzbvE = null;
            this.zzbvF = null;
            this.zzbvG = zzbuw.zzcsn;
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) {
            if (this.zzbvD != null) {
                zzbum.zzF(1, this.zzbvD.intValue());
            }
            if (this.zzbvE != null) {
                zzbum.zzq(2, this.zzbvE);
            }
            if (this.zzbvF != null) {
                zzbum.zzg(3, this.zzbvF.booleanValue());
            }
            if (this.zzbvG != null && this.zzbvG.length > 0) {
                for (String str : this.zzbvG) {
                    if (str != null) {
                        zzbum.zzq(4, str);
                    }
                }
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvD != null) {
                zzv += zzbum.zzH(1, this.zzbvD.intValue());
            }
            if (this.zzbvE != null) {
                zzv += zzbum.zzr(2, this.zzbvE);
            }
            if (this.zzbvF != null) {
                zzv += zzbum.zzh(3, this.zzbvF.booleanValue());
            }
            if (this.zzbvG == null || this.zzbvG.length <= 0) {
                return zzv;
            }
            int i = 0;
            int i2 = 0;
            for (String str : this.zzbvG) {
                if (str != null) {
                    i2++;
                    i += zzbum.zzkc(str);
                }
            }
            return zzv + i + (i2 * 1);
        }
    }
}
