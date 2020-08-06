package com.google.android.gms.internal;

import android.support.p001v4.media.TransportMediator;
import java.util.Arrays;

public interface zzbuy {

    public static final class zza extends zzbun<zza> implements Cloneable {
        public String version;
        public int zzcss;
        public String zzcst;

        public zza() {
            zzadb();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.zzcss != zza.zzcss) {
                return false;
            }
            if (this.zzcst == null) {
                if (zza.zzcst != null) {
                    return false;
                }
            } else if (!this.zzcst.equals(zza.zzcst)) {
                return false;
            }
            if (this.version == null) {
                if (zza.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zza.version)) {
                return false;
            }
            return (this.zzcrX == null || this.zzcrX.isEmpty()) ? zza.zzcrX == null || zza.zzcrX.isEmpty() : this.zzcrX.equals(zza.zzcrX);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zzcst == null ? 0 : this.zzcst.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzcss) * 31)) * 31)) * 31;
            if (this.zzcrX != null && !this.zzcrX.isEmpty()) {
                i = this.zzcrX.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbum zzbum) {
            if (this.zzcss != 0) {
                zzbum.zzF(1, this.zzcss);
            }
            if (this.zzcst != null && !this.zzcst.equals("")) {
                zzbum.zzq(2, this.zzcst);
            }
            if (this.version != null && !this.version.equals("")) {
                zzbum.zzq(3, this.version);
            }
            super.zza(zzbum);
        }

        /* renamed from: zzaO */
        public zza zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzcss = zzbul.zzacy();
                        continue;
                    case 18:
                        this.zzcst = zzbul.readString();
                        continue;
                    case 26:
                        this.version = zzbul.readString();
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

        public /* synthetic */ zzbun zzacN() {
            return (zza) zzacO();
        }

        public /* synthetic */ zzbut zzacO() {
            return (zza) clone();
        }

        public zza zzadb() {
            this.zzcss = 0;
            this.zzcst = "";
            this.version = "";
            this.zzcrX = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzadc */
        public zza clone() {
            try {
                return (zza) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzcss != 0) {
                zzv += zzbum.zzH(1, this.zzcss);
            }
            if (this.zzcst != null && !this.zzcst.equals("")) {
                zzv += zzbum.zzr(2, this.zzcst);
            }
            return (this.version == null || this.version.equals("")) ? zzv : zzv + zzbum.zzr(3, this.version);
        }
    }

    public static final class zzb extends zzbun<zzb> implements Cloneable {
        public byte[] zzcsu;
        public String zzcsv;
        public byte[][] zzcsw;
        public boolean zzcsx;

        public zzb() {
            zzadd();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (!Arrays.equals(this.zzcsu, zzb.zzcsu)) {
                return false;
            }
            if (this.zzcsv == null) {
                if (zzb.zzcsv != null) {
                    return false;
                }
            } else if (!this.zzcsv.equals(zzb.zzcsv)) {
                return false;
            }
            if (!zzbur.zza(this.zzcsw, zzb.zzcsw) || this.zzcsx != zzb.zzcsx) {
                return false;
            }
            return (this.zzcrX == null || this.zzcrX.isEmpty()) ? zzb.zzcrX == null || zzb.zzcrX.isEmpty() : this.zzcrX.equals(zzb.zzcrX);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcsx ? 1231 : 1237) + (((((this.zzcsv == null ? 0 : this.zzcsv.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzcsu)) * 31)) * 31) + zzbur.zzb(this.zzcsw)) * 31)) * 31;
            if (this.zzcrX != null && !this.zzcrX.isEmpty()) {
                i = this.zzcrX.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbum zzbum) {
            if (!Arrays.equals(this.zzcsu, zzbuw.zzcsp)) {
                zzbum.zzb(1, this.zzcsu);
            }
            if (this.zzcsw != null && this.zzcsw.length > 0) {
                for (byte[] bArr : this.zzcsw) {
                    if (bArr != null) {
                        zzbum.zzb(2, bArr);
                    }
                }
            }
            if (this.zzcsx) {
                zzbum.zzg(3, this.zzcsx);
            }
            if (this.zzcsv != null && !this.zzcsv.equals("")) {
                zzbum.zzq(4, this.zzcsv);
            }
            super.zza(zzbum);
        }

        /* renamed from: zzaP */
        public zzb zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.zzcsu = zzbul.readBytes();
                        continue;
                    case 18:
                        int zzc = zzbuw.zzc(zzbul, 18);
                        int length = this.zzcsw == null ? 0 : this.zzcsw.length;
                        byte[][] bArr = new byte[(zzc + length)][];
                        if (length != 0) {
                            System.arraycopy(this.zzcsw, 0, bArr, 0, length);
                        }
                        while (length < bArr.length - 1) {
                            bArr[length] = zzbul.readBytes();
                            zzbul.zzacu();
                            length++;
                        }
                        bArr[length] = zzbul.readBytes();
                        this.zzcsw = bArr;
                        continue;
                    case 24:
                        this.zzcsx = zzbul.zzacA();
                        continue;
                    case 34:
                        this.zzcsv = zzbul.readString();
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

        public /* synthetic */ zzbun zzacN() {
            return (zzb) zzacO();
        }

        public /* synthetic */ zzbut zzacO() {
            return (zzb) clone();
        }

        public zzb zzadd() {
            this.zzcsu = zzbuw.zzcsp;
            this.zzcsv = "";
            this.zzcsw = zzbuw.zzcso;
            this.zzcsx = false;
            this.zzcrX = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzade */
        public zzb clone() {
            try {
                zzb zzb = (zzb) super.clone();
                if (this.zzcsw != null && this.zzcsw.length > 0) {
                    zzb.zzcsw = (byte[][]) this.zzcsw.clone();
                }
                return zzb;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (!Arrays.equals(this.zzcsu, zzbuw.zzcsp)) {
                zzv += zzbum.zzc(1, this.zzcsu);
            }
            if (this.zzcsw != null && this.zzcsw.length > 0) {
                int i = 0;
                int i2 = 0;
                for (byte[] bArr : this.zzcsw) {
                    if (bArr != null) {
                        i2++;
                        i += zzbum.zzag(bArr);
                    }
                }
                zzv = zzv + i + (i2 * 1);
            }
            if (this.zzcsx) {
                zzv += zzbum.zzh(3, this.zzcsx);
            }
            return (this.zzcsv == null || this.zzcsv.equals("")) ? zzv : zzv + zzbum.zzr(4, this.zzcsv);
        }
    }

    public static final class zzc extends zzbun<zzc> implements Cloneable {
        public String tag;
        public boolean zzcbV;
        public long zzcsA;
        public int zzcsB;
        public zzd[] zzcsC;
        public byte[] zzcsD;
        public zza zzcsE;
        public byte[] zzcsF;
        public String zzcsG;
        public String zzcsH;
        public String zzcsI;
        public long zzcsJ;
        public zzb zzcsK;
        public byte[] zzcsL;
        public String zzcsM;
        public int zzcsN;
        public int[] zzcsO;
        public long zzcsP;
        public zze zzcsQ;
        public long zzcsy;
        public long zzcsz;
        public int zzrn;

        public zzc() {
            zzadf();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzcsy != zzc.zzcsy || this.zzcsz != zzc.zzcsz || this.zzcsA != zzc.zzcsA) {
                return false;
            }
            if (this.tag == null) {
                if (zzc.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(zzc.tag)) {
                return false;
            }
            if (this.zzcsB != zzc.zzcsB || this.zzrn != zzc.zzrn || this.zzcbV != zzc.zzcbV || !zzbur.equals((Object[]) this.zzcsC, (Object[]) zzc.zzcsC) || !Arrays.equals(this.zzcsD, zzc.zzcsD)) {
                return false;
            }
            if (this.zzcsE == null) {
                if (zzc.zzcsE != null) {
                    return false;
                }
            } else if (!this.zzcsE.equals(zzc.zzcsE)) {
                return false;
            }
            if (!Arrays.equals(this.zzcsF, zzc.zzcsF)) {
                return false;
            }
            if (this.zzcsG == null) {
                if (zzc.zzcsG != null) {
                    return false;
                }
            } else if (!this.zzcsG.equals(zzc.zzcsG)) {
                return false;
            }
            if (this.zzcsH == null) {
                if (zzc.zzcsH != null) {
                    return false;
                }
            } else if (!this.zzcsH.equals(zzc.zzcsH)) {
                return false;
            }
            if (this.zzcsI == null) {
                if (zzc.zzcsI != null) {
                    return false;
                }
            } else if (!this.zzcsI.equals(zzc.zzcsI)) {
                return false;
            }
            if (this.zzcsJ != zzc.zzcsJ) {
                return false;
            }
            if (this.zzcsK == null) {
                if (zzc.zzcsK != null) {
                    return false;
                }
            } else if (!this.zzcsK.equals(zzc.zzcsK)) {
                return false;
            }
            if (!Arrays.equals(this.zzcsL, zzc.zzcsL)) {
                return false;
            }
            if (this.zzcsM == null) {
                if (zzc.zzcsM != null) {
                    return false;
                }
            } else if (!this.zzcsM.equals(zzc.zzcsM)) {
                return false;
            }
            if (this.zzcsN != zzc.zzcsN || !zzbur.equals(this.zzcsO, zzc.zzcsO) || this.zzcsP != zzc.zzcsP) {
                return false;
            }
            if (this.zzcsQ == null) {
                if (zzc.zzcsQ != null) {
                    return false;
                }
            } else if (!this.zzcsQ.equals(zzc.zzcsQ)) {
                return false;
            }
            return (this.zzcrX == null || this.zzcrX.isEmpty()) ? zzc.zzcrX == null || zzc.zzcrX.isEmpty() : this.zzcrX.equals(zzc.zzcrX);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcsQ == null ? 0 : this.zzcsQ.hashCode()) + (((((((((this.zzcsM == null ? 0 : this.zzcsM.hashCode()) + (((((this.zzcsK == null ? 0 : this.zzcsK.hashCode()) + (((((this.zzcsI == null ? 0 : this.zzcsI.hashCode()) + (((this.zzcsH == null ? 0 : this.zzcsH.hashCode()) + (((this.zzcsG == null ? 0 : this.zzcsG.hashCode()) + (((((this.zzcsE == null ? 0 : this.zzcsE.hashCode()) + (((((((this.zzcbV ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzcsy ^ (this.zzcsy >>> 32)))) * 31) + ((int) (this.zzcsz ^ (this.zzcsz >>> 32)))) * 31) + ((int) (this.zzcsA ^ (this.zzcsA >>> 32)))) * 31)) * 31) + this.zzcsB) * 31) + this.zzrn) * 31)) * 31) + zzbur.hashCode((Object[]) this.zzcsC)) * 31) + Arrays.hashCode(this.zzcsD)) * 31)) * 31) + Arrays.hashCode(this.zzcsF)) * 31)) * 31)) * 31)) * 31) + ((int) (this.zzcsJ ^ (this.zzcsJ >>> 32)))) * 31)) * 31) + Arrays.hashCode(this.zzcsL)) * 31)) * 31) + this.zzcsN) * 31) + zzbur.hashCode(this.zzcsO)) * 31) + ((int) (this.zzcsP ^ (this.zzcsP >>> 32)))) * 31)) * 31;
            if (this.zzcrX != null && !this.zzcrX.isEmpty()) {
                i = this.zzcrX.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbum zzbum) {
            if (this.zzcsy != 0) {
                zzbum.zzb(1, this.zzcsy);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzbum.zzq(2, this.tag);
            }
            if (this.zzcsC != null && this.zzcsC.length > 0) {
                for (zzd zzd : this.zzcsC) {
                    if (zzd != null) {
                        zzbum.zza(3, (zzbut) zzd);
                    }
                }
            }
            if (!Arrays.equals(this.zzcsD, zzbuw.zzcsp)) {
                zzbum.zzb(4, this.zzcsD);
            }
            if (!Arrays.equals(this.zzcsF, zzbuw.zzcsp)) {
                zzbum.zzb(6, this.zzcsF);
            }
            if (this.zzcsG != null && !this.zzcsG.equals("")) {
                zzbum.zzq(8, this.zzcsG);
            }
            if (this.zzcsE != null) {
                zzbum.zza(9, (zzbut) this.zzcsE);
            }
            if (this.zzcbV) {
                zzbum.zzg(10, this.zzcbV);
            }
            if (this.zzcsB != 0) {
                zzbum.zzF(11, this.zzcsB);
            }
            if (this.zzrn != 0) {
                zzbum.zzF(12, this.zzrn);
            }
            if (this.zzcsH != null && !this.zzcsH.equals("")) {
                zzbum.zzq(13, this.zzcsH);
            }
            if (this.zzcsI != null && !this.zzcsI.equals("")) {
                zzbum.zzq(14, this.zzcsI);
            }
            if (this.zzcsJ != 180000) {
                zzbum.zzd(15, this.zzcsJ);
            }
            if (this.zzcsK != null) {
                zzbum.zza(16, (zzbut) this.zzcsK);
            }
            if (this.zzcsz != 0) {
                zzbum.zzb(17, this.zzcsz);
            }
            if (!Arrays.equals(this.zzcsL, zzbuw.zzcsp)) {
                zzbum.zzb(18, this.zzcsL);
            }
            if (this.zzcsN != 0) {
                zzbum.zzF(19, this.zzcsN);
            }
            if (this.zzcsO != null && this.zzcsO.length > 0) {
                for (int zzF : this.zzcsO) {
                    zzbum.zzF(20, zzF);
                }
            }
            if (this.zzcsA != 0) {
                zzbum.zzb(21, this.zzcsA);
            }
            if (this.zzcsP != 0) {
                zzbum.zzb(22, this.zzcsP);
            }
            if (this.zzcsQ != null) {
                zzbum.zza(23, (zzbut) this.zzcsQ);
            }
            if (this.zzcsM != null && !this.zzcsM.equals("")) {
                zzbum.zzq(24, this.zzcsM);
            }
            super.zza(zzbum);
        }

        /* renamed from: zzaQ */
        public zzc zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzcsy = zzbul.zzacx();
                        continue;
                    case 18:
                        this.tag = zzbul.readString();
                        continue;
                    case 26:
                        int zzc = zzbuw.zzc(zzbul, 26);
                        int length = this.zzcsC == null ? 0 : this.zzcsC.length;
                        zzd[] zzdArr = new zzd[(zzc + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcsC, 0, zzdArr, 0, length);
                        }
                        while (length < zzdArr.length - 1) {
                            zzdArr[length] = new zzd();
                            zzbul.zza(zzdArr[length]);
                            zzbul.zzacu();
                            length++;
                        }
                        zzdArr[length] = new zzd();
                        zzbul.zza(zzdArr[length]);
                        this.zzcsC = zzdArr;
                        continue;
                    case 34:
                        this.zzcsD = zzbul.readBytes();
                        continue;
                    case 50:
                        this.zzcsF = zzbul.readBytes();
                        continue;
                    case 66:
                        this.zzcsG = zzbul.readString();
                        continue;
                    case 74:
                        if (this.zzcsE == null) {
                            this.zzcsE = new zza();
                        }
                        zzbul.zza(this.zzcsE);
                        continue;
                    case 80:
                        this.zzcbV = zzbul.zzacA();
                        continue;
                    case 88:
                        this.zzcsB = zzbul.zzacy();
                        continue;
                    case 96:
                        this.zzrn = zzbul.zzacy();
                        continue;
                    case 106:
                        this.zzcsH = zzbul.readString();
                        continue;
                    case 114:
                        this.zzcsI = zzbul.readString();
                        continue;
                    case 120:
                        this.zzcsJ = zzbul.zzacC();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD:
                        if (this.zzcsK == null) {
                            this.zzcsK = new zzb();
                        }
                        zzbul.zza(this.zzcsK);
                        continue;
                    case 136:
                        this.zzcsz = zzbul.zzacx();
                        continue;
                    case 146:
                        this.zzcsL = zzbul.readBytes();
                        continue;
                    case 152:
                        int zzacy = zzbul.zzacy();
                        switch (zzacy) {
                            case 0:
                            case 1:
                            case 2:
                                this.zzcsN = zzacy;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        int zzc2 = zzbuw.zzc(zzbul, 160);
                        int length2 = this.zzcsO == null ? 0 : this.zzcsO.length;
                        int[] iArr = new int[(zzc2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzcsO, 0, iArr, 0, length2);
                        }
                        while (length2 < iArr.length - 1) {
                            iArr[length2] = zzbul.zzacy();
                            zzbul.zzacu();
                            length2++;
                        }
                        iArr[length2] = zzbul.zzacy();
                        this.zzcsO = iArr;
                        continue;
                    case 162:
                        int zzqj = zzbul.zzqj(zzbul.zzacD());
                        int position = zzbul.getPosition();
                        int i = 0;
                        while (zzbul.zzacI() > 0) {
                            zzbul.zzacy();
                            i++;
                        }
                        zzbul.zzql(position);
                        int length3 = this.zzcsO == null ? 0 : this.zzcsO.length;
                        int[] iArr2 = new int[(i + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzcsO, 0, iArr2, 0, length3);
                        }
                        while (length3 < iArr2.length) {
                            iArr2[length3] = zzbul.zzacy();
                            length3++;
                        }
                        this.zzcsO = iArr2;
                        zzbul.zzqk(zzqj);
                        continue;
                    case 168:
                        this.zzcsA = zzbul.zzacx();
                        continue;
                    case 176:
                        this.zzcsP = zzbul.zzacx();
                        continue;
                    case 186:
                        if (this.zzcsQ == null) {
                            this.zzcsQ = new zze();
                        }
                        zzbul.zza(this.zzcsQ);
                        continue;
                    case 194:
                        this.zzcsM = zzbul.readString();
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

        public /* synthetic */ zzbun zzacN() {
            return (zzc) zzacO();
        }

        public /* synthetic */ zzbut zzacO() {
            return (zzc) clone();
        }

        public zzc zzadf() {
            this.zzcsy = 0;
            this.zzcsz = 0;
            this.zzcsA = 0;
            this.tag = "";
            this.zzcsB = 0;
            this.zzrn = 0;
            this.zzcbV = false;
            this.zzcsC = zzd.zzadh();
            this.zzcsD = zzbuw.zzcsp;
            this.zzcsE = null;
            this.zzcsF = zzbuw.zzcsp;
            this.zzcsG = "";
            this.zzcsH = "";
            this.zzcsI = "";
            this.zzcsJ = 180000;
            this.zzcsK = null;
            this.zzcsL = zzbuw.zzcsp;
            this.zzcsM = "";
            this.zzcsN = 0;
            this.zzcsO = zzbuw.zzcsi;
            this.zzcsP = 0;
            this.zzcsQ = null;
            this.zzcrX = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzadg */
        public zzc clone() {
            try {
                zzc zzc = (zzc) super.clone();
                if (this.zzcsC != null && this.zzcsC.length > 0) {
                    zzc.zzcsC = new zzd[this.zzcsC.length];
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= this.zzcsC.length) {
                            break;
                        }
                        if (this.zzcsC[i2] != null) {
                            zzc.zzcsC[i2] = (zzd) this.zzcsC[i2].zzacO();
                        }
                        i = i2 + 1;
                    }
                }
                if (this.zzcsE != null) {
                    zzc.zzcsE = (zza) this.zzcsE.zzacO();
                }
                if (this.zzcsK != null) {
                    zzc.zzcsK = (zzb) this.zzcsK.zzacO();
                }
                if (this.zzcsO != null && this.zzcsO.length > 0) {
                    zzc.zzcsO = (int[]) this.zzcsO.clone();
                }
                if (this.zzcsQ != null) {
                    zzc.zzcsQ = (zze) this.zzcsQ.zzacO();
                }
                return zzc;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzcsy != 0) {
                zzv += zzbum.zzf(1, this.zzcsy);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzv += zzbum.zzr(2, this.tag);
            }
            if (this.zzcsC != null && this.zzcsC.length > 0) {
                int i = zzv;
                for (zzd zzd : this.zzcsC) {
                    if (zzd != null) {
                        i += zzbum.zzc(3, (zzbut) zzd);
                    }
                }
                zzv = i;
            }
            if (!Arrays.equals(this.zzcsD, zzbuw.zzcsp)) {
                zzv += zzbum.zzc(4, this.zzcsD);
            }
            if (!Arrays.equals(this.zzcsF, zzbuw.zzcsp)) {
                zzv += zzbum.zzc(6, this.zzcsF);
            }
            if (this.zzcsG != null && !this.zzcsG.equals("")) {
                zzv += zzbum.zzr(8, this.zzcsG);
            }
            if (this.zzcsE != null) {
                zzv += zzbum.zzc(9, (zzbut) this.zzcsE);
            }
            if (this.zzcbV) {
                zzv += zzbum.zzh(10, this.zzcbV);
            }
            if (this.zzcsB != 0) {
                zzv += zzbum.zzH(11, this.zzcsB);
            }
            if (this.zzrn != 0) {
                zzv += zzbum.zzH(12, this.zzrn);
            }
            if (this.zzcsH != null && !this.zzcsH.equals("")) {
                zzv += zzbum.zzr(13, this.zzcsH);
            }
            if (this.zzcsI != null && !this.zzcsI.equals("")) {
                zzv += zzbum.zzr(14, this.zzcsI);
            }
            if (this.zzcsJ != 180000) {
                zzv += zzbum.zzh(15, this.zzcsJ);
            }
            if (this.zzcsK != null) {
                zzv += zzbum.zzc(16, (zzbut) this.zzcsK);
            }
            if (this.zzcsz != 0) {
                zzv += zzbum.zzf(17, this.zzcsz);
            }
            if (!Arrays.equals(this.zzcsL, zzbuw.zzcsp)) {
                zzv += zzbum.zzc(18, this.zzcsL);
            }
            if (this.zzcsN != 0) {
                zzv += zzbum.zzH(19, this.zzcsN);
            }
            if (this.zzcsO != null && this.zzcsO.length > 0) {
                int i2 = 0;
                for (int zzqp : this.zzcsO) {
                    i2 += zzbum.zzqp(zzqp);
                }
                zzv = zzv + i2 + (this.zzcsO.length * 2);
            }
            if (this.zzcsA != 0) {
                zzv += zzbum.zzf(21, this.zzcsA);
            }
            if (this.zzcsP != 0) {
                zzv += zzbum.zzf(22, this.zzcsP);
            }
            if (this.zzcsQ != null) {
                zzv += zzbum.zzc(23, (zzbut) this.zzcsQ);
            }
            return (this.zzcsM == null || this.zzcsM.equals("")) ? zzv : zzv + zzbum.zzr(24, this.zzcsM);
        }
    }

    public static final class zzd extends zzbun<zzd> implements Cloneable {
        private static volatile zzd[] zzcsR;
        public String value;
        public String zzaA;

        public zzd() {
            zzadi();
        }

        public static zzd[] zzadh() {
            if (zzcsR == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzcsR == null) {
                        zzcsR = new zzd[0];
                    }
                }
            }
            return zzcsR;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzaA == null) {
                if (zzd.zzaA != null) {
                    return false;
                }
            } else if (!this.zzaA.equals(zzd.zzaA)) {
                return false;
            }
            if (this.value == null) {
                if (zzd.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zzd.value)) {
                return false;
            }
            return (this.zzcrX == null || this.zzcrX.isEmpty()) ? zzd.zzcrX == null || zzd.zzcrX.isEmpty() : this.zzcrX.equals(zzd.zzcrX);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzaA == null ? 0 : this.zzaA.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzcrX != null && !this.zzcrX.isEmpty()) {
                i = this.zzcrX.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbum zzbum) {
            if (this.zzaA != null && !this.zzaA.equals("")) {
                zzbum.zzq(1, this.zzaA);
            }
            if (this.value != null && !this.value.equals("")) {
                zzbum.zzq(2, this.value);
            }
            super.zza(zzbum);
        }

        /* renamed from: zzaR */
        public zzd zzb(zzbul zzbul) {
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
                        if (!super.zza(zzbul, zzacu)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public /* synthetic */ zzbun zzacN() {
            return (zzd) zzacO();
        }

        public /* synthetic */ zzbut zzacO() {
            return (zzd) clone();
        }

        public zzd zzadi() {
            this.zzaA = "";
            this.value = "";
            this.zzcrX = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzadj */
        public zzd clone() {
            try {
                return (zzd) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzaA != null && !this.zzaA.equals("")) {
                zzv += zzbum.zzr(1, this.zzaA);
            }
            return (this.value == null || this.value.equals("")) ? zzv : zzv + zzbum.zzr(2, this.value);
        }
    }

    public static final class zze extends zzbun<zze> implements Cloneable {
        public int zzcsS;
        public int zzcsT;

        public zze() {
            zzadk();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzcsS == zze.zzcsS && this.zzcsT == zze.zzcsT) {
                return (this.zzcrX == null || this.zzcrX.isEmpty()) ? zze.zzcrX == null || zze.zzcrX.isEmpty() : this.zzcrX.equals(zze.zzcrX);
            }
            return false;
        }

        public int hashCode() {
            return ((this.zzcrX == null || this.zzcrX.isEmpty()) ? 0 : this.zzcrX.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.zzcsS) * 31) + this.zzcsT) * 31);
        }

        public void zza(zzbum zzbum) {
            if (this.zzcsS != -1) {
                zzbum.zzF(1, this.zzcsS);
            }
            if (this.zzcsT != 0) {
                zzbum.zzF(2, this.zzcsT);
            }
            super.zza(zzbum);
        }

        /* renamed from: zzaS */
        public zze zzb(zzbul zzbul) {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        int zzacy = zzbul.zzacy();
                        switch (zzacy) {
                            case -1:
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                                this.zzcsS = zzacy;
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        int zzacy2 = zzbul.zzacy();
                        switch (zzacy2) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 100:
                                this.zzcsT = zzacy2;
                                break;
                            default:
                                continue;
                        }
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

        public /* synthetic */ zzbun zzacN() {
            return (zze) zzacO();
        }

        public /* synthetic */ zzbut zzacO() {
            return (zze) clone();
        }

        public zze zzadk() {
            this.zzcsS = -1;
            this.zzcsT = 0;
            this.zzcrX = null;
            this.zzcsg = -1;
            return this;
        }

        /* renamed from: zzadl */
        public zze clone() {
            try {
                return (zze) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzv() {
            int zzv = super.zzv();
            if (this.zzcsS != -1) {
                zzv += zzbum.zzH(1, this.zzcsS);
            }
            return this.zzcsT != 0 ? zzv + zzbum.zzH(2, this.zzcsT) : zzv;
        }
    }
}
