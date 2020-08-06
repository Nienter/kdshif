package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzbuy;
import com.google.android.gms.internal.zzzf;
import java.util.Arrays;

public class zzzh extends zza {
    public static final Parcelable.Creator<zzzh> CREATOR = new zzzi();
    public final int versionCode;
    public zzawe zzawE;
    public byte[] zzawF;
    public int[] zzawG;
    public String[] zzawH;
    public int[] zzawI;
    public byte[][] zzawJ;
    public boolean zzawK;
    public final zzbuy.zzc zzawL;
    public final zzzf.zzc zzawM;
    public final zzzf.zzc zzawN;

    zzzh(int i, zzawe zzawe, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z) {
        this.versionCode = i;
        this.zzawE = zzawe;
        this.zzawF = bArr;
        this.zzawG = iArr;
        this.zzawH = strArr;
        this.zzawL = null;
        this.zzawM = null;
        this.zzawN = null;
        this.zzawI = iArr2;
        this.zzawJ = bArr2;
        this.zzawK = z;
    }

    public zzzh(zzawe zzawe, zzbuy.zzc zzc, zzzf.zzc zzc2, zzzf.zzc zzc3, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, boolean z) {
        this.versionCode = 1;
        this.zzawE = zzawe;
        this.zzawL = zzc;
        this.zzawM = zzc2;
        this.zzawN = zzc3;
        this.zzawG = iArr;
        this.zzawH = strArr;
        this.zzawI = iArr2;
        this.zzawJ = bArr;
        this.zzawK = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzh)) {
            return false;
        }
        zzzh zzzh = (zzzh) obj;
        return this.versionCode == zzzh.versionCode && zzaa.equal(this.zzawE, zzzh.zzawE) && Arrays.equals(this.zzawF, zzzh.zzawF) && Arrays.equals(this.zzawG, zzzh.zzawG) && Arrays.equals(this.zzawH, zzzh.zzawH) && zzaa.equal(this.zzawL, zzzh.zzawL) && zzaa.equal(this.zzawM, zzzh.zzawM) && zzaa.equal(this.zzawN, zzzh.zzawN) && Arrays.equals(this.zzawI, zzzh.zzawI) && Arrays.deepEquals(this.zzawJ, zzzh.zzawJ) && this.zzawK == zzzh.zzawK;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.versionCode), this.zzawE, this.zzawF, this.zzawG, this.zzawH, this.zzawL, this.zzawM, this.zzawN, this.zzawI, this.zzawJ, Boolean.valueOf(this.zzawK));
    }

    public String toString() {
        return "LogEventParcelable[" + this.versionCode + ", " + this.zzawE + ", " + "LogEventBytes: " + (this.zzawF == null ? null : new String(this.zzawF)) + ", " + "TestCodes: " + Arrays.toString(this.zzawG) + ", " + "MendelPackages: " + Arrays.toString(this.zzawH) + ", " + "LogEvent: " + this.zzawL + ", " + "ExtensionProducer: " + this.zzawM + ", " + "VeProducer: " + this.zzawN + ", " + "ExperimentIDs: " + Arrays.toString(this.zzawI) + ", " + "ExperimentTokens: " + Arrays.toString(this.zzawJ) + ", " + "AddPhenotypeExperimentTokens: " + this.zzawK + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzzi.zza(this, parcel, i);
    }
}
