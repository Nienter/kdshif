package com.google.android.gms.internal;

import android.support.p004v7.widget.ActivityChooserView;

public final class zzbul {
    private final byte[] buffer;
    private int zzcrN;
    private int zzcrO;
    private int zzcrP;
    private int zzcrQ;
    private int zzcrR;
    private int zzcrS = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int zzcrT;
    private int zzcrU = 64;
    private int zzcrV = 67108864;

    private zzbul(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzcrN = i;
        this.zzcrO = i + i2;
        this.zzcrQ = i;
    }

    public static long zzaV(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private void zzacH() {
        this.zzcrO += this.zzcrP;
        int i = this.zzcrO;
        if (i > this.zzcrS) {
            this.zzcrP = i - this.zzcrS;
            this.zzcrO -= this.zzcrP;
            return;
        }
        this.zzcrP = 0;
    }

    public static zzbul zzad(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzbul zzb(byte[] bArr, int i, int i2) {
        return new zzbul(bArr, i, i2);
    }

    public static int zzqi(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public int getPosition() {
        return this.zzcrQ - this.zzcrN;
    }

    public byte[] readBytes() {
        int zzacD = zzacD();
        if (zzacD < 0) {
            throw zzbus.zzacS();
        } else if (zzacD == 0) {
            return zzbuw.zzcsp;
        } else {
            if (zzacD > this.zzcrO - this.zzcrQ) {
                throw zzbus.zzacR();
            }
            byte[] bArr = new byte[zzacD];
            System.arraycopy(this.buffer, this.zzcrQ, bArr, 0, zzacD);
            this.zzcrQ = zzacD + this.zzcrQ;
            return bArr;
        }
    }

    public double readDouble() {
        return Double.longBitsToDouble(zzacG());
    }

    public float readFloat() {
        return Float.intBitsToFloat(zzacF());
    }

    public String readString() {
        int zzacD = zzacD();
        if (zzacD < 0) {
            throw zzbus.zzacS();
        } else if (zzacD > this.zzcrO - this.zzcrQ) {
            throw zzbus.zzacR();
        } else {
            String str = new String(this.buffer, this.zzcrQ, zzacD, zzbur.UTF_8);
            this.zzcrQ = zzacD + this.zzcrQ;
            return str;
        }
    }

    public byte[] zzE(int i, int i2) {
        if (i2 == 0) {
            return zzbuw.zzcsp;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzcrN + i, bArr, 0, i2);
        return bArr;
    }

    public void zza(zzbut zzbut) {
        int zzacD = zzacD();
        if (this.zzcrT >= this.zzcrU) {
            throw zzbus.zzacX();
        }
        int zzqj = zzqj(zzacD);
        this.zzcrT++;
        zzbut.zzb(this);
        zzqg(0);
        this.zzcrT--;
        zzqk(zzqj);
    }

    public void zza(zzbut zzbut, int i) {
        if (this.zzcrT >= this.zzcrU) {
            throw zzbus.zzacX();
        }
        this.zzcrT++;
        zzbut.zzb(this);
        zzqg(zzbuw.zzK(i, 4));
        this.zzcrT--;
    }

    public boolean zzacA() {
        return zzacD() != 0;
    }

    public int zzacB() {
        return zzqi(zzacD());
    }

    public long zzacC() {
        return zzaV(zzacE());
    }

    public int zzacD() {
        byte zzacK = zzacK();
        if (zzacK >= 0) {
            return zzacK;
        }
        byte b = zzacK & Byte.MAX_VALUE;
        byte zzacK2 = zzacK();
        if (zzacK2 >= 0) {
            return b | (zzacK2 << 7);
        }
        byte b2 = b | ((zzacK2 & Byte.MAX_VALUE) << 7);
        byte zzacK3 = zzacK();
        if (zzacK3 >= 0) {
            return b2 | (zzacK3 << 14);
        }
        byte b3 = b2 | ((zzacK3 & Byte.MAX_VALUE) << 14);
        byte zzacK4 = zzacK();
        if (zzacK4 >= 0) {
            return b3 | (zzacK4 << 21);
        }
        byte b4 = b3 | ((zzacK4 & Byte.MAX_VALUE) << 21);
        byte zzacK5 = zzacK();
        byte b5 = b4 | (zzacK5 << 28);
        if (zzacK5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (zzacK() >= 0) {
                return b5;
            }
        }
        throw zzbus.zzacT();
    }

    public long zzacE() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzacK = zzacK();
            j |= ((long) (zzacK & Byte.MAX_VALUE)) << i;
            if ((zzacK & 128) == 0) {
                return j;
            }
        }
        throw zzbus.zzacT();
    }

    public int zzacF() {
        return (zzacK() & 255) | ((zzacK() & 255) << 8) | ((zzacK() & 255) << 16) | ((zzacK() & 255) << 24);
    }

    public long zzacG() {
        byte zzacK = zzacK();
        byte zzacK2 = zzacK();
        return ((((long) zzacK2) & 255) << 8) | (((long) zzacK) & 255) | ((((long) zzacK()) & 255) << 16) | ((((long) zzacK()) & 255) << 24) | ((((long) zzacK()) & 255) << 32) | ((((long) zzacK()) & 255) << 40) | ((((long) zzacK()) & 255) << 48) | ((((long) zzacK()) & 255) << 56);
    }

    public int zzacI() {
        if (this.zzcrS == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzcrS - this.zzcrQ;
    }

    public boolean zzacJ() {
        return this.zzcrQ == this.zzcrO;
    }

    public byte zzacK() {
        if (this.zzcrQ == this.zzcrO) {
            throw zzbus.zzacR();
        }
        byte[] bArr = this.buffer;
        int i = this.zzcrQ;
        this.zzcrQ = i + 1;
        return bArr[i];
    }

    public int zzacu() {
        if (zzacJ()) {
            this.zzcrR = 0;
            return 0;
        }
        this.zzcrR = zzacD();
        if (this.zzcrR != 0) {
            return this.zzcrR;
        }
        throw zzbus.zzacU();
    }

    public void zzacv() {
        int zzacu;
        do {
            zzacu = zzacu();
            if (zzacu == 0) {
                return;
            }
        } while (zzqh(zzacu));
    }

    public long zzacw() {
        return zzacE();
    }

    public long zzacx() {
        return zzacE();
    }

    public int zzacy() {
        return zzacD();
    }

    public long zzacz() {
        return zzacG();
    }

    public void zzqg(int i) {
        if (this.zzcrR != i) {
            throw zzbus.zzacV();
        }
    }

    public boolean zzqh(int i) {
        switch (zzbuw.zzqA(i)) {
            case 0:
                zzacy();
                return true;
            case 1:
                zzacG();
                return true;
            case 2:
                zzqm(zzacD());
                return true;
            case 3:
                zzacv();
                zzqg(zzbuw.zzK(zzbuw.zzqB(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                zzacF();
                return true;
            default:
                throw zzbus.zzacW();
        }
    }

    public int zzqj(int i) {
        if (i < 0) {
            throw zzbus.zzacS();
        }
        int i2 = this.zzcrQ + i;
        int i3 = this.zzcrS;
        if (i2 > i3) {
            throw zzbus.zzacR();
        }
        this.zzcrS = i2;
        zzacH();
        return i3;
    }

    public void zzqk(int i) {
        this.zzcrS = i;
        zzacH();
    }

    public void zzql(int i) {
        if (i > this.zzcrQ - this.zzcrN) {
            throw new IllegalArgumentException(new StringBuilder(50).append("Position ").append(i).append(" is beyond current ").append(this.zzcrQ - this.zzcrN).toString());
        } else if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(24).append("Bad position ").append(i).toString());
        } else {
            this.zzcrQ = this.zzcrN + i;
        }
    }

    public void zzqm(int i) {
        if (i < 0) {
            throw zzbus.zzacS();
        } else if (this.zzcrQ + i > this.zzcrS) {
            zzqm(this.zzcrS - this.zzcrQ);
            throw zzbus.zzacR();
        } else if (i <= this.zzcrO - this.zzcrQ) {
            this.zzcrQ += i;
        } else {
            throw zzbus.zzacR();
        }
    }
}
