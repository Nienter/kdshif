package com.google.android.gms.internal;

import android.support.p001v4.media.TransportMediator;

public interface zzauh {

    public static final class zza extends zzbut {
        private static volatile zza[] zzbvQ;
        public zzf zzbvR;
        public zzf zzbvS;
        public Boolean zzbvT;
        public Integer zzbvh;

        public zza() {
            zzMz();
        }

        public static zza[] zzMy() {
            if (zzbvQ == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvQ == null) {
                        zzbvQ = new zza[0];
                    }
                }
            }
            return zzbvQ;
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
            if (this.zzbvR == null) {
                if (zza.zzbvR != null) {
                    return false;
                }
            } else if (!this.zzbvR.equals(zza.zzbvR)) {
                return false;
            }
            if (this.zzbvS == null) {
                if (zza.zzbvS != null) {
                    return false;
                }
            } else if (!this.zzbvS.equals(zza.zzbvS)) {
                return false;
            }
            return this.zzbvT == null ? zza.zzbvT == null : this.zzbvT.equals(zza.zzbvT);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvS == null ? 0 : this.zzbvS.hashCode()) + (((this.zzbvR == null ? 0 : this.zzbvR.hashCode()) + (((this.zzbvh == null ? 0 : this.zzbvh.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
            if (this.zzbvT != null) {
                i = this.zzbvT.hashCode();
            }
            return hashCode + i;
        }

        public zza zzMz() {
            this.zzbvh = null;
            this.zzbvR = null;
            this.zzbvS = null;
            this.zzbvT = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzP */
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
                        if (this.zzbvR == null) {
                            this.zzbvR = new zzf();
                        }
                        zzbul.zza(this.zzbvR);
                        continue;
                    case 26:
                        if (this.zzbvS == null) {
                            this.zzbvS = new zzf();
                        }
                        zzbul.zza(this.zzbvS);
                        continue;
                    case 32:
                        this.zzbvT = Boolean.valueOf(zzbul.zzacA());
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
            if (this.zzbvh != null) {
                zzbum.zzF(1, this.zzbvh.intValue());
            }
            if (this.zzbvR != null) {
                zzbum.zza(2, (zzbut) this.zzbvR);
            }
            if (this.zzbvS != null) {
                zzbum.zza(3, (zzbut) this.zzbvS);
            }
            if (this.zzbvT != null) {
                zzbum.zzg(4, this.zzbvT.booleanValue());
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvh != null) {
                zzv += zzbum.zzH(1, this.zzbvh.intValue());
            }
            if (this.zzbvR != null) {
                zzv += zzbum.zzc(2, (zzbut) this.zzbvR);
            }
            if (this.zzbvS != null) {
                zzv += zzbum.zzc(3, (zzbut) this.zzbvS);
            }
            return this.zzbvT != null ? zzv + zzbum.zzh(4, this.zzbvT.booleanValue()) : zzv;
        }
    }

    public static final class zzb extends zzbut {
        private static volatile zzb[] zzbvU;
        public Integer count;
        public String name;
        public zzc[] zzbvV;
        public Long zzbvW;
        public Long zzbvX;

        public zzb() {
            zzMB();
        }

        public static zzb[] zzMA() {
            if (zzbvU == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvU == null) {
                        zzbvU = new zzb[0];
                    }
                }
            }
            return zzbvU;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (!zzbur.equals((Object[]) this.zzbvV, (Object[]) zzb.zzbvV)) {
                return false;
            }
            if (this.name == null) {
                if (zzb.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzb.name)) {
                return false;
            }
            if (this.zzbvW == null) {
                if (zzb.zzbvW != null) {
                    return false;
                }
            } else if (!this.zzbvW.equals(zzb.zzbvW)) {
                return false;
            }
            if (this.zzbvX == null) {
                if (zzb.zzbvX != null) {
                    return false;
                }
            } else if (!this.zzbvX.equals(zzb.zzbvX)) {
                return false;
            }
            return this.count == null ? zzb.count == null : this.count.equals(zzb.count);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvX == null ? 0 : this.zzbvX.hashCode()) + (((this.zzbvW == null ? 0 : this.zzbvW.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzbur.hashCode((Object[]) this.zzbvV)) * 31)) * 31)) * 31)) * 31;
            if (this.count != null) {
                i = this.count.hashCode();
            }
            return hashCode + i;
        }

        public zzb zzMB() {
            this.zzbvV = zzc.zzMC();
            this.name = null;
            this.zzbvW = null;
            this.zzbvX = null;
            this.count = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzQ */
        public zzb zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        int zzc = zzbuw.zzc(zzbul, 10);
                        int length = this.zzbvV == null ? 0 : this.zzbvV.length;
                        zzc[] zzcArr = new zzc[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbvV, 0, zzcArr, 0, length);
                        }
                        while (length < zzcArr.length - 1) {
                            zzcArr[length] = new zzc();
                            zzbul.zza(zzcArr[length]);
                            zzbul.zzacu();
                            length++;
                        }
                        zzcArr[length] = new zzc();
                        zzbul.zza(zzcArr[length]);
                        this.zzbvV = zzcArr;
                        continue;
                    case 18:
                        this.name = zzbul.readString();
                        continue;
                    case 24:
                        this.zzbvW = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 32:
                        this.zzbvX = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 40:
                        this.count = Integer.valueOf(zzbul.zzacy());
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
            if (this.zzbvV != null && this.zzbvV.length > 0) {
                for (zzc zzc : this.zzbvV) {
                    if (zzc != null) {
                        zzbum.zza(1, (zzbut) zzc);
                    }
                }
            }
            if (this.name != null) {
                zzbum.zzq(2, this.name);
            }
            if (this.zzbvW != null) {
                zzbum.zzb(3, this.zzbvW.longValue());
            }
            if (this.zzbvX != null) {
                zzbum.zzb(4, this.zzbvX.longValue());
            }
            if (this.count != null) {
                zzbum.zzF(5, this.count.intValue());
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbvV != null && this.zzbvV.length > 0) {
                for (zzc zzc : this.zzbvV) {
                    if (zzc != null) {
                        zzv += zzbum.zzc(1, (zzbut) zzc);
                    }
                }
            }
            if (this.name != null) {
                zzv += zzbum.zzr(2, this.name);
            }
            if (this.zzbvW != null) {
                zzv += zzbum.zzf(3, this.zzbvW.longValue());
            }
            if (this.zzbvX != null) {
                zzv += zzbum.zzf(4, this.zzbvX.longValue());
            }
            return this.count != null ? zzv + zzbum.zzH(5, this.count.intValue()) : zzv;
        }
    }

    public static final class zzc extends zzbut {
        private static volatile zzc[] zzbvY;
        public String name;
        public String zzaFy;
        public Long zzbvZ;
        public Float zzbvb;
        public Double zzbvc;

        public zzc() {
            zzMD();
        }

        public static zzc[] zzMC() {
            if (zzbvY == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvY == null) {
                        zzbvY = new zzc[0];
                    }
                }
            }
            return zzbvY;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.name == null) {
                if (zzc.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzc.name)) {
                return false;
            }
            if (this.zzaFy == null) {
                if (zzc.zzaFy != null) {
                    return false;
                }
            } else if (!this.zzaFy.equals(zzc.zzaFy)) {
                return false;
            }
            if (this.zzbvZ == null) {
                if (zzc.zzbvZ != null) {
                    return false;
                }
            } else if (!this.zzbvZ.equals(zzc.zzbvZ)) {
                return false;
            }
            if (this.zzbvb == null) {
                if (zzc.zzbvb != null) {
                    return false;
                }
            } else if (!this.zzbvb.equals(zzc.zzbvb)) {
                return false;
            }
            return this.zzbvc == null ? zzc.zzbvc == null : this.zzbvc.equals(zzc.zzbvc);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvb == null ? 0 : this.zzbvb.hashCode()) + (((this.zzbvZ == null ? 0 : this.zzbvZ.hashCode()) + (((this.zzaFy == null ? 0 : this.zzaFy.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzbvc != null) {
                i = this.zzbvc.hashCode();
            }
            return hashCode + i;
        }

        public zzc zzMD() {
            this.name = null;
            this.zzaFy = null;
            this.zzbvZ = null;
            this.zzbvb = null;
            this.zzbvc = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzR */
        public zzc zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzbul.readString();
                        continue;
                    case 18:
                        this.zzaFy = zzbul.readString();
                        continue;
                    case 24:
                        this.zzbvZ = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 37:
                        this.zzbvb = Float.valueOf(zzbul.readFloat());
                        continue;
                    case 41:
                        this.zzbvc = Double.valueOf(zzbul.readDouble());
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
            if (this.name != null) {
                zzbum.zzq(1, this.name);
            }
            if (this.zzaFy != null) {
                zzbum.zzq(2, this.zzaFy);
            }
            if (this.zzbvZ != null) {
                zzbum.zzb(3, this.zzbvZ.longValue());
            }
            if (this.zzbvb != null) {
                zzbum.zzc(4, this.zzbvb.floatValue());
            }
            if (this.zzbvc != null) {
                zzbum.zza(5, this.zzbvc.doubleValue());
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.name != null) {
                zzv += zzbum.zzr(1, this.name);
            }
            if (this.zzaFy != null) {
                zzv += zzbum.zzr(2, this.zzaFy);
            }
            if (this.zzbvZ != null) {
                zzv += zzbum.zzf(3, this.zzbvZ.longValue());
            }
            if (this.zzbvb != null) {
                zzv += zzbum.zzd(4, this.zzbvb.floatValue());
            }
            return this.zzbvc != null ? zzv + zzbum.zzb(5, this.zzbvc.doubleValue()) : zzv;
        }
    }

    public static final class zzd extends zzbut {
        public zze[] zzbwa;

        public zzd() {
            zzME();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            return zzbur.equals((Object[]) this.zzbwa, (Object[]) ((zzd) obj).zzbwa);
        }

        public int hashCode() {
            return ((getClass().getName().hashCode() + 527) * 31) + zzbur.hashCode((Object[]) this.zzbwa);
        }

        public zzd zzME() {
            this.zzbwa = zze.zzMF();
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzS */
        public zzd zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        int zzc = zzbuw.zzc(zzbul, 10);
                        int length = this.zzbwa == null ? 0 : this.zzbwa.length;
                        zze[] zzeArr = new zze[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbwa, 0, zzeArr, 0, length);
                        }
                        while (length < zzeArr.length - 1) {
                            zzeArr[length] = new zze();
                            zzbul.zza(zzeArr[length]);
                            zzbul.zzacu();
                            length++;
                        }
                        zzeArr[length] = new zze();
                        zzbul.zza(zzeArr[length]);
                        this.zzbwa = zzeArr;
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
            if (this.zzbwa != null && this.zzbwa.length > 0) {
                for (zze zze : this.zzbwa) {
                    if (zze != null) {
                        zzbum.zza(1, (zzbut) zze);
                    }
                }
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbwa != null && this.zzbwa.length > 0) {
                for (zze zze : this.zzbwa) {
                    if (zze != null) {
                        zzv += zzbum.zzc(1, (zzbut) zze);
                    }
                }
            }
            return zzv;
        }
    }

    public static final class zze extends zzbut {
        private static volatile zze[] zzbwb;
        public String zzaR;
        public String zzba;
        public String zzbhg;
        public String zzbqf;
        public String zzbqg;
        public String zzbqj;
        public String zzbqn;
        public String zzbwA;
        public Long zzbwB;
        public Integer zzbwc;
        public zzb[] zzbwd;
        public zzg[] zzbwe;
        public Long zzbwf;
        public Long zzbwg;
        public Long zzbwh;
        public Long zzbwi;
        public Long zzbwj;
        public String zzbwk;
        public String zzbwl;
        public String zzbwm;
        public Integer zzbwn;
        public Long zzbwo;
        public Long zzbwp;
        public String zzbwq;
        public Boolean zzbwr;
        public String zzbws;
        public Long zzbwt;
        public Integer zzbwu;
        public Boolean zzbwv;
        public zza[] zzbww;
        public Integer zzbwx;
        public Integer zzbwy;
        public Integer zzbwz;

        public zze() {
            zzMG();
        }

        public static zze[] zzMF() {
            if (zzbwb == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbwb == null) {
                        zzbwb = new zze[0];
                    }
                }
            }
            return zzbwb;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzbwc == null) {
                if (zze.zzbwc != null) {
                    return false;
                }
            } else if (!this.zzbwc.equals(zze.zzbwc)) {
                return false;
            }
            if (!zzbur.equals((Object[]) this.zzbwd, (Object[]) zze.zzbwd)) {
                return false;
            }
            if (!zzbur.equals((Object[]) this.zzbwe, (Object[]) zze.zzbwe)) {
                return false;
            }
            if (this.zzbwf == null) {
                if (zze.zzbwf != null) {
                    return false;
                }
            } else if (!this.zzbwf.equals(zze.zzbwf)) {
                return false;
            }
            if (this.zzbwg == null) {
                if (zze.zzbwg != null) {
                    return false;
                }
            } else if (!this.zzbwg.equals(zze.zzbwg)) {
                return false;
            }
            if (this.zzbwh == null) {
                if (zze.zzbwh != null) {
                    return false;
                }
            } else if (!this.zzbwh.equals(zze.zzbwh)) {
                return false;
            }
            if (this.zzbwi == null) {
                if (zze.zzbwi != null) {
                    return false;
                }
            } else if (!this.zzbwi.equals(zze.zzbwi)) {
                return false;
            }
            if (this.zzbwj == null) {
                if (zze.zzbwj != null) {
                    return false;
                }
            } else if (!this.zzbwj.equals(zze.zzbwj)) {
                return false;
            }
            if (this.zzbwk == null) {
                if (zze.zzbwk != null) {
                    return false;
                }
            } else if (!this.zzbwk.equals(zze.zzbwk)) {
                return false;
            }
            if (this.zzba == null) {
                if (zze.zzba != null) {
                    return false;
                }
            } else if (!this.zzba.equals(zze.zzba)) {
                return false;
            }
            if (this.zzbwl == null) {
                if (zze.zzbwl != null) {
                    return false;
                }
            } else if (!this.zzbwl.equals(zze.zzbwl)) {
                return false;
            }
            if (this.zzbwm == null) {
                if (zze.zzbwm != null) {
                    return false;
                }
            } else if (!this.zzbwm.equals(zze.zzbwm)) {
                return false;
            }
            if (this.zzbwn == null) {
                if (zze.zzbwn != null) {
                    return false;
                }
            } else if (!this.zzbwn.equals(zze.zzbwn)) {
                return false;
            }
            if (this.zzbqg == null) {
                if (zze.zzbqg != null) {
                    return false;
                }
            } else if (!this.zzbqg.equals(zze.zzbqg)) {
                return false;
            }
            if (this.zzaR == null) {
                if (zze.zzaR != null) {
                    return false;
                }
            } else if (!this.zzaR.equals(zze.zzaR)) {
                return false;
            }
            if (this.zzbhg == null) {
                if (zze.zzbhg != null) {
                    return false;
                }
            } else if (!this.zzbhg.equals(zze.zzbhg)) {
                return false;
            }
            if (this.zzbwo == null) {
                if (zze.zzbwo != null) {
                    return false;
                }
            } else if (!this.zzbwo.equals(zze.zzbwo)) {
                return false;
            }
            if (this.zzbwp == null) {
                if (zze.zzbwp != null) {
                    return false;
                }
            } else if (!this.zzbwp.equals(zze.zzbwp)) {
                return false;
            }
            if (this.zzbwq == null) {
                if (zze.zzbwq != null) {
                    return false;
                }
            } else if (!this.zzbwq.equals(zze.zzbwq)) {
                return false;
            }
            if (this.zzbwr == null) {
                if (zze.zzbwr != null) {
                    return false;
                }
            } else if (!this.zzbwr.equals(zze.zzbwr)) {
                return false;
            }
            if (this.zzbws == null) {
                if (zze.zzbws != null) {
                    return false;
                }
            } else if (!this.zzbws.equals(zze.zzbws)) {
                return false;
            }
            if (this.zzbwt == null) {
                if (zze.zzbwt != null) {
                    return false;
                }
            } else if (!this.zzbwt.equals(zze.zzbwt)) {
                return false;
            }
            if (this.zzbwu == null) {
                if (zze.zzbwu != null) {
                    return false;
                }
            } else if (!this.zzbwu.equals(zze.zzbwu)) {
                return false;
            }
            if (this.zzbqj == null) {
                if (zze.zzbqj != null) {
                    return false;
                }
            } else if (!this.zzbqj.equals(zze.zzbqj)) {
                return false;
            }
            if (this.zzbqf == null) {
                if (zze.zzbqf != null) {
                    return false;
                }
            } else if (!this.zzbqf.equals(zze.zzbqf)) {
                return false;
            }
            if (this.zzbwv == null) {
                if (zze.zzbwv != null) {
                    return false;
                }
            } else if (!this.zzbwv.equals(zze.zzbwv)) {
                return false;
            }
            if (!zzbur.equals((Object[]) this.zzbww, (Object[]) zze.zzbww)) {
                return false;
            }
            if (this.zzbqn == null) {
                if (zze.zzbqn != null) {
                    return false;
                }
            } else if (!this.zzbqn.equals(zze.zzbqn)) {
                return false;
            }
            if (this.zzbwx == null) {
                if (zze.zzbwx != null) {
                    return false;
                }
            } else if (!this.zzbwx.equals(zze.zzbwx)) {
                return false;
            }
            if (this.zzbwy == null) {
                if (zze.zzbwy != null) {
                    return false;
                }
            } else if (!this.zzbwy.equals(zze.zzbwy)) {
                return false;
            }
            if (this.zzbwz == null) {
                if (zze.zzbwz != null) {
                    return false;
                }
            } else if (!this.zzbwz.equals(zze.zzbwz)) {
                return false;
            }
            if (this.zzbwA == null) {
                if (zze.zzbwA != null) {
                    return false;
                }
            } else if (!this.zzbwA.equals(zze.zzbwA)) {
                return false;
            }
            return this.zzbwB == null ? zze.zzbwB == null : this.zzbwB.equals(zze.zzbwB);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbwA == null ? 0 : this.zzbwA.hashCode()) + (((this.zzbwz == null ? 0 : this.zzbwz.hashCode()) + (((this.zzbwy == null ? 0 : this.zzbwy.hashCode()) + (((this.zzbwx == null ? 0 : this.zzbwx.hashCode()) + (((this.zzbqn == null ? 0 : this.zzbqn.hashCode()) + (((((this.zzbwv == null ? 0 : this.zzbwv.hashCode()) + (((this.zzbqf == null ? 0 : this.zzbqf.hashCode()) + (((this.zzbqj == null ? 0 : this.zzbqj.hashCode()) + (((this.zzbwu == null ? 0 : this.zzbwu.hashCode()) + (((this.zzbwt == null ? 0 : this.zzbwt.hashCode()) + (((this.zzbws == null ? 0 : this.zzbws.hashCode()) + (((this.zzbwr == null ? 0 : this.zzbwr.hashCode()) + (((this.zzbwq == null ? 0 : this.zzbwq.hashCode()) + (((this.zzbwp == null ? 0 : this.zzbwp.hashCode()) + (((this.zzbwo == null ? 0 : this.zzbwo.hashCode()) + (((this.zzbhg == null ? 0 : this.zzbhg.hashCode()) + (((this.zzaR == null ? 0 : this.zzaR.hashCode()) + (((this.zzbqg == null ? 0 : this.zzbqg.hashCode()) + (((this.zzbwn == null ? 0 : this.zzbwn.hashCode()) + (((this.zzbwm == null ? 0 : this.zzbwm.hashCode()) + (((this.zzbwl == null ? 0 : this.zzbwl.hashCode()) + (((this.zzba == null ? 0 : this.zzba.hashCode()) + (((this.zzbwk == null ? 0 : this.zzbwk.hashCode()) + (((this.zzbwj == null ? 0 : this.zzbwj.hashCode()) + (((this.zzbwi == null ? 0 : this.zzbwi.hashCode()) + (((this.zzbwh == null ? 0 : this.zzbwh.hashCode()) + (((this.zzbwg == null ? 0 : this.zzbwg.hashCode()) + (((this.zzbwf == null ? 0 : this.zzbwf.hashCode()) + (((((((this.zzbwc == null ? 0 : this.zzbwc.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzbur.hashCode((Object[]) this.zzbwd)) * 31) + zzbur.hashCode((Object[]) this.zzbwe)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + zzbur.hashCode((Object[]) this.zzbww)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzbwB != null) {
                i = this.zzbwB.hashCode();
            }
            return hashCode + i;
        }

        public zze zzMG() {
            this.zzbwc = null;
            this.zzbwd = zzb.zzMA();
            this.zzbwe = zzg.zzMI();
            this.zzbwf = null;
            this.zzbwg = null;
            this.zzbwh = null;
            this.zzbwi = null;
            this.zzbwj = null;
            this.zzbwk = null;
            this.zzba = null;
            this.zzbwl = null;
            this.zzbwm = null;
            this.zzbwn = null;
            this.zzbqg = null;
            this.zzaR = null;
            this.zzbhg = null;
            this.zzbwo = null;
            this.zzbwp = null;
            this.zzbwq = null;
            this.zzbwr = null;
            this.zzbws = null;
            this.zzbwt = null;
            this.zzbwu = null;
            this.zzbqj = null;
            this.zzbqf = null;
            this.zzbwv = null;
            this.zzbww = zza.zzMy();
            this.zzbqn = null;
            this.zzbwx = null;
            this.zzbwy = null;
            this.zzbwz = null;
            this.zzbwA = null;
            this.zzbwB = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzT */
        public zze zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzbwc = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 18:
                        int zzc = zzbuw.zzc(zzbul, 18);
                        int length = this.zzbwd == null ? 0 : this.zzbwd.length;
                        zzb[] zzbArr = new zzb[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbwd, 0, zzbArr, 0, length);
                        }
                        while (length < zzbArr.length - 1) {
                            zzbArr[length] = new zzb();
                            zzbul.zza(zzbArr[length]);
                            zzbul.zzacu();
                            length++;
                        }
                        zzbArr[length] = new zzb();
                        zzbul.zza(zzbArr[length]);
                        this.zzbwd = zzbArr;
                        continue;
                    case 26:
                        int zzc2 = zzbuw.zzc(zzbul, 26);
                        int length2 = this.zzbwe == null ? 0 : this.zzbwe.length;
                        zzg[] zzgArr = new zzg[(zzc2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzbwe, 0, zzgArr, 0, length2);
                        }
                        while (length2 < zzgArr.length - 1) {
                            zzgArr[length2] = new zzg();
                            zzbul.zza(zzgArr[length2]);
                            zzbul.zzacu();
                            length2++;
                        }
                        zzgArr[length2] = new zzg();
                        zzbul.zza(zzgArr[length2]);
                        this.zzbwe = zzgArr;
                        continue;
                    case 32:
                        this.zzbwf = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 40:
                        this.zzbwg = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 48:
                        this.zzbwh = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 56:
                        this.zzbwj = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 66:
                        this.zzbwk = zzbul.readString();
                        continue;
                    case 74:
                        this.zzba = zzbul.readString();
                        continue;
                    case 82:
                        this.zzbwl = zzbul.readString();
                        continue;
                    case 90:
                        this.zzbwm = zzbul.readString();
                        continue;
                    case 96:
                        this.zzbwn = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 106:
                        this.zzbqg = zzbul.readString();
                        continue;
                    case 114:
                        this.zzaR = zzbul.readString();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD:
                        this.zzbhg = zzbul.readString();
                        continue;
                    case 136:
                        this.zzbwo = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 144:
                        this.zzbwp = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 154:
                        this.zzbwq = zzbul.readString();
                        continue;
                    case 160:
                        this.zzbwr = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 170:
                        this.zzbws = zzbul.readString();
                        continue;
                    case 176:
                        this.zzbwt = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 184:
                        this.zzbwu = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 194:
                        this.zzbqj = zzbul.readString();
                        continue;
                    case 202:
                        this.zzbqf = zzbul.readString();
                        continue;
                    case 208:
                        this.zzbwi = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 224:
                        this.zzbwv = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 234:
                        int zzc3 = zzbuw.zzc(zzbul, 234);
                        int length3 = this.zzbww == null ? 0 : this.zzbww.length;
                        zza[] zzaArr = new zza[(zzc3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzbww, 0, zzaArr, 0, length3);
                        }
                        while (length3 < zzaArr.length - 1) {
                            zzaArr[length3] = new zza();
                            zzbul.zza(zzaArr[length3]);
                            zzbul.zzacu();
                            length3++;
                        }
                        zzaArr[length3] = new zza();
                        zzbul.zza(zzaArr[length3]);
                        this.zzbww = zzaArr;
                        continue;
                    case 242:
                        this.zzbqn = zzbul.readString();
                        continue;
                    case 248:
                        this.zzbwx = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 256:
                        this.zzbwy = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 264:
                        this.zzbwz = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 274:
                        this.zzbwA = zzbul.readString();
                        continue;
                    case 280:
                        this.zzbwB = Long.valueOf(zzbul.zzacx());
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
            if (this.zzbwc != null) {
                zzbum.zzF(1, this.zzbwc.intValue());
            }
            if (this.zzbwd != null && this.zzbwd.length > 0) {
                for (zzb zzb : this.zzbwd) {
                    if (zzb != null) {
                        zzbum.zza(2, (zzbut) zzb);
                    }
                }
            }
            if (this.zzbwe != null && this.zzbwe.length > 0) {
                for (zzg zzg : this.zzbwe) {
                    if (zzg != null) {
                        zzbum.zza(3, (zzbut) zzg);
                    }
                }
            }
            if (this.zzbwf != null) {
                zzbum.zzb(4, this.zzbwf.longValue());
            }
            if (this.zzbwg != null) {
                zzbum.zzb(5, this.zzbwg.longValue());
            }
            if (this.zzbwh != null) {
                zzbum.zzb(6, this.zzbwh.longValue());
            }
            if (this.zzbwj != null) {
                zzbum.zzb(7, this.zzbwj.longValue());
            }
            if (this.zzbwk != null) {
                zzbum.zzq(8, this.zzbwk);
            }
            if (this.zzba != null) {
                zzbum.zzq(9, this.zzba);
            }
            if (this.zzbwl != null) {
                zzbum.zzq(10, this.zzbwl);
            }
            if (this.zzbwm != null) {
                zzbum.zzq(11, this.zzbwm);
            }
            if (this.zzbwn != null) {
                zzbum.zzF(12, this.zzbwn.intValue());
            }
            if (this.zzbqg != null) {
                zzbum.zzq(13, this.zzbqg);
            }
            if (this.zzaR != null) {
                zzbum.zzq(14, this.zzaR);
            }
            if (this.zzbhg != null) {
                zzbum.zzq(16, this.zzbhg);
            }
            if (this.zzbwo != null) {
                zzbum.zzb(17, this.zzbwo.longValue());
            }
            if (this.zzbwp != null) {
                zzbum.zzb(18, this.zzbwp.longValue());
            }
            if (this.zzbwq != null) {
                zzbum.zzq(19, this.zzbwq);
            }
            if (this.zzbwr != null) {
                zzbum.zzg(20, this.zzbwr.booleanValue());
            }
            if (this.zzbws != null) {
                zzbum.zzq(21, this.zzbws);
            }
            if (this.zzbwt != null) {
                zzbum.zzb(22, this.zzbwt.longValue());
            }
            if (this.zzbwu != null) {
                zzbum.zzF(23, this.zzbwu.intValue());
            }
            if (this.zzbqj != null) {
                zzbum.zzq(24, this.zzbqj);
            }
            if (this.zzbqf != null) {
                zzbum.zzq(25, this.zzbqf);
            }
            if (this.zzbwi != null) {
                zzbum.zzb(26, this.zzbwi.longValue());
            }
            if (this.zzbwv != null) {
                zzbum.zzg(28, this.zzbwv.booleanValue());
            }
            if (this.zzbww != null && this.zzbww.length > 0) {
                for (zza zza : this.zzbww) {
                    if (zza != null) {
                        zzbum.zza(29, (zzbut) zza);
                    }
                }
            }
            if (this.zzbqn != null) {
                zzbum.zzq(30, this.zzbqn);
            }
            if (this.zzbwx != null) {
                zzbum.zzF(31, this.zzbwx.intValue());
            }
            if (this.zzbwy != null) {
                zzbum.zzF(32, this.zzbwy.intValue());
            }
            if (this.zzbwz != null) {
                zzbum.zzF(33, this.zzbwz.intValue());
            }
            if (this.zzbwA != null) {
                zzbum.zzq(34, this.zzbwA);
            }
            if (this.zzbwB != null) {
                zzbum.zzb(35, this.zzbwB.longValue());
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbwc != null) {
                zzv += zzbum.zzH(1, this.zzbwc.intValue());
            }
            if (this.zzbwd != null && this.zzbwd.length > 0) {
                int i = zzv;
                for (zzb zzb : this.zzbwd) {
                    if (zzb != null) {
                        i += zzbum.zzc(2, (zzbut) zzb);
                    }
                }
                zzv = i;
            }
            if (this.zzbwe != null && this.zzbwe.length > 0) {
                int i2 = zzv;
                for (zzg zzg : this.zzbwe) {
                    if (zzg != null) {
                        i2 += zzbum.zzc(3, (zzbut) zzg);
                    }
                }
                zzv = i2;
            }
            if (this.zzbwf != null) {
                zzv += zzbum.zzf(4, this.zzbwf.longValue());
            }
            if (this.zzbwg != null) {
                zzv += zzbum.zzf(5, this.zzbwg.longValue());
            }
            if (this.zzbwh != null) {
                zzv += zzbum.zzf(6, this.zzbwh.longValue());
            }
            if (this.zzbwj != null) {
                zzv += zzbum.zzf(7, this.zzbwj.longValue());
            }
            if (this.zzbwk != null) {
                zzv += zzbum.zzr(8, this.zzbwk);
            }
            if (this.zzba != null) {
                zzv += zzbum.zzr(9, this.zzba);
            }
            if (this.zzbwl != null) {
                zzv += zzbum.zzr(10, this.zzbwl);
            }
            if (this.zzbwm != null) {
                zzv += zzbum.zzr(11, this.zzbwm);
            }
            if (this.zzbwn != null) {
                zzv += zzbum.zzH(12, this.zzbwn.intValue());
            }
            if (this.zzbqg != null) {
                zzv += zzbum.zzr(13, this.zzbqg);
            }
            if (this.zzaR != null) {
                zzv += zzbum.zzr(14, this.zzaR);
            }
            if (this.zzbhg != null) {
                zzv += zzbum.zzr(16, this.zzbhg);
            }
            if (this.zzbwo != null) {
                zzv += zzbum.zzf(17, this.zzbwo.longValue());
            }
            if (this.zzbwp != null) {
                zzv += zzbum.zzf(18, this.zzbwp.longValue());
            }
            if (this.zzbwq != null) {
                zzv += zzbum.zzr(19, this.zzbwq);
            }
            if (this.zzbwr != null) {
                zzv += zzbum.zzh(20, this.zzbwr.booleanValue());
            }
            if (this.zzbws != null) {
                zzv += zzbum.zzr(21, this.zzbws);
            }
            if (this.zzbwt != null) {
                zzv += zzbum.zzf(22, this.zzbwt.longValue());
            }
            if (this.zzbwu != null) {
                zzv += zzbum.zzH(23, this.zzbwu.intValue());
            }
            if (this.zzbqj != null) {
                zzv += zzbum.zzr(24, this.zzbqj);
            }
            if (this.zzbqf != null) {
                zzv += zzbum.zzr(25, this.zzbqf);
            }
            if (this.zzbwi != null) {
                zzv += zzbum.zzf(26, this.zzbwi.longValue());
            }
            if (this.zzbwv != null) {
                zzv += zzbum.zzh(28, this.zzbwv.booleanValue());
            }
            if (this.zzbww != null && this.zzbww.length > 0) {
                for (zza zza : this.zzbww) {
                    if (zza != null) {
                        zzv += zzbum.zzc(29, (zzbut) zza);
                    }
                }
            }
            if (this.zzbqn != null) {
                zzv += zzbum.zzr(30, this.zzbqn);
            }
            if (this.zzbwx != null) {
                zzv += zzbum.zzH(31, this.zzbwx.intValue());
            }
            if (this.zzbwy != null) {
                zzv += zzbum.zzH(32, this.zzbwy.intValue());
            }
            if (this.zzbwz != null) {
                zzv += zzbum.zzH(33, this.zzbwz.intValue());
            }
            if (this.zzbwA != null) {
                zzv += zzbum.zzr(34, this.zzbwA);
            }
            return this.zzbwB != null ? zzv + zzbum.zzf(35, this.zzbwB.longValue()) : zzv;
        }
    }

    public static final class zzf extends zzbut {
        public long[] zzbwC;
        public long[] zzbwD;

        public zzf() {
            zzMH();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (!zzbur.equals(this.zzbwC, zzf.zzbwC)) {
                return false;
            }
            return zzbur.equals(this.zzbwD, zzf.zzbwD);
        }

        public int hashCode() {
            return ((((getClass().getName().hashCode() + 527) * 31) + zzbur.hashCode(this.zzbwC)) * 31) + zzbur.hashCode(this.zzbwD);
        }

        public zzf zzMH() {
            this.zzbwC = zzbuw.zzcsj;
            this.zzbwD = zzbuw.zzcsj;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzU */
        public zzf zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        int zzc = zzbuw.zzc(zzbul, 8);
                        int length = this.zzbwC == null ? 0 : this.zzbwC.length;
                        long[] jArr = new long[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzbwC, 0, jArr, 0, length);
                        }
                        while (length < jArr.length - 1) {
                            jArr[length] = zzbul.zzacw();
                            zzbul.zzacu();
                            length++;
                        }
                        jArr[length] = zzbul.zzacw();
                        this.zzbwC = jArr;
                        continue;
                    case 10:
                        int zzqj = zzbul.zzqj(zzbul.zzacD());
                        int position = zzbul.getPosition();
                        int i = 0;
                        while (zzbul.zzacI() > 0) {
                            zzbul.zzacw();
                            i++;
                        }
                        zzbul.zzql(position);
                        int length2 = this.zzbwC == null ? 0 : this.zzbwC.length;
                        long[] jArr2 = new long[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzbwC, 0, jArr2, 0, length2);
                        }
                        while (length2 < jArr2.length) {
                            jArr2[length2] = zzbul.zzacw();
                            length2++;
                        }
                        this.zzbwC = jArr2;
                        zzbul.zzqk(zzqj);
                        continue;
                    case 16:
                        int zzc2 = zzbuw.zzc(zzbul, 16);
                        int length3 = this.zzbwD == null ? 0 : this.zzbwD.length;
                        long[] jArr3 = new long[(zzc2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzbwD, 0, jArr3, 0, length3);
                        }
                        while (length3 < jArr3.length - 1) {
                            jArr3[length3] = zzbul.zzacw();
                            zzbul.zzacu();
                            length3++;
                        }
                        jArr3[length3] = zzbul.zzacw();
                        this.zzbwD = jArr3;
                        continue;
                    case 18:
                        int zzqj2 = zzbul.zzqj(zzbul.zzacD());
                        int position2 = zzbul.getPosition();
                        int i2 = 0;
                        while (zzbul.zzacI() > 0) {
                            zzbul.zzacw();
                            i2++;
                        }
                        zzbul.zzql(position2);
                        int length4 = this.zzbwD == null ? 0 : this.zzbwD.length;
                        long[] jArr4 = new long[(i2 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzbwD, 0, jArr4, 0, length4);
                        }
                        while (length4 < jArr4.length) {
                            jArr4[length4] = zzbul.zzacw();
                            length4++;
                        }
                        this.zzbwD = jArr4;
                        zzbul.zzqk(zzqj2);
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
            if (this.zzbwC != null && this.zzbwC.length > 0) {
                for (long zza : this.zzbwC) {
                    zzbum.zza(1, zza);
                }
            }
            if (this.zzbwD != null && this.zzbwD.length > 0) {
                for (long zza2 : this.zzbwD) {
                    zzbum.zza(2, zza2);
                }
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int i;
            int zzv = super.zzv();
            if (this.zzbwC == null || this.zzbwC.length <= 0) {
                i = zzv;
            } else {
                int i2 = 0;
                for (long zzba : this.zzbwC) {
                    i2 += zzbum.zzba(zzba);
                }
                i = zzv + i2 + (this.zzbwC.length * 1);
            }
            if (this.zzbwD == null || this.zzbwD.length <= 0) {
                return i;
            }
            int i3 = 0;
            for (long zzba2 : this.zzbwD) {
                i3 += zzbum.zzba(zzba2);
            }
            return i + i3 + (this.zzbwD.length * 1);
        }
    }

    public static final class zzg extends zzbut {
        private static volatile zzg[] zzbwE;
        public String name;
        public String zzaFy;
        public Long zzbvZ;
        public Float zzbvb;
        public Double zzbvc;
        public Long zzbwF;

        public zzg() {
            zzMJ();
        }

        public static zzg[] zzMI() {
            if (zzbwE == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbwE == null) {
                        zzbwE = new zzg[0];
                    }
                }
            }
            return zzbwE;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzg)) {
                return false;
            }
            zzg zzg = (zzg) obj;
            if (this.zzbwF == null) {
                if (zzg.zzbwF != null) {
                    return false;
                }
            } else if (!this.zzbwF.equals(zzg.zzbwF)) {
                return false;
            }
            if (this.name == null) {
                if (zzg.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzg.name)) {
                return false;
            }
            if (this.zzaFy == null) {
                if (zzg.zzaFy != null) {
                    return false;
                }
            } else if (!this.zzaFy.equals(zzg.zzaFy)) {
                return false;
            }
            if (this.zzbvZ == null) {
                if (zzg.zzbvZ != null) {
                    return false;
                }
            } else if (!this.zzbvZ.equals(zzg.zzbvZ)) {
                return false;
            }
            if (this.zzbvb == null) {
                if (zzg.zzbvb != null) {
                    return false;
                }
            } else if (!this.zzbvb.equals(zzg.zzbvb)) {
                return false;
            }
            return this.zzbvc == null ? zzg.zzbvc == null : this.zzbvc.equals(zzg.zzbvc);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvb == null ? 0 : this.zzbvb.hashCode()) + (((this.zzbvZ == null ? 0 : this.zzbvZ.hashCode()) + (((this.zzaFy == null ? 0 : this.zzaFy.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + (((this.zzbwF == null ? 0 : this.zzbwF.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzbvc != null) {
                i = this.zzbvc.hashCode();
            }
            return hashCode + i;
        }

        public zzg zzMJ() {
            this.zzbwF = null;
            this.name = null;
            this.zzaFy = null;
            this.zzbvZ = null;
            this.zzbvb = null;
            this.zzbvc = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzV */
        public zzg zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzbwF = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 18:
                        this.name = zzbul.readString();
                        continue;
                    case 26:
                        this.zzaFy = zzbul.readString();
                        continue;
                    case 32:
                        this.zzbvZ = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 45:
                        this.zzbvb = Float.valueOf(zzbul.readFloat());
                        continue;
                    case 49:
                        this.zzbvc = Double.valueOf(zzbul.readDouble());
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
            if (this.zzbwF != null) {
                zzbum.zzb(1, this.zzbwF.longValue());
            }
            if (this.name != null) {
                zzbum.zzq(2, this.name);
            }
            if (this.zzaFy != null) {
                zzbum.zzq(3, this.zzaFy);
            }
            if (this.zzbvZ != null) {
                zzbum.zzb(4, this.zzbvZ.longValue());
            }
            if (this.zzbvb != null) {
                zzbum.zzc(5, this.zzbvb.floatValue());
            }
            if (this.zzbvc != null) {
                zzbum.zza(6, this.zzbvc.doubleValue());
            }
            super.zza(zzbum);
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzbwF != null) {
                zzv += zzbum.zzf(1, this.zzbwF.longValue());
            }
            if (this.name != null) {
                zzv += zzbum.zzr(2, this.name);
            }
            if (this.zzaFy != null) {
                zzv += zzbum.zzr(3, this.zzaFy);
            }
            if (this.zzbvZ != null) {
                zzv += zzbum.zzf(4, this.zzbvZ.longValue());
            }
            if (this.zzbvb != null) {
                zzv += zzbum.zzd(5, this.zzbvb.floatValue());
            }
            return this.zzbvc != null ? zzv + zzbum.zzb(6, this.zzbvc.doubleValue()) : zzv;
        }
    }
}
