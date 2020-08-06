package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.internal.zzack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class zzach extends com.google.android.gms.common.internal.safeparcel.zza implements zzack.zzb<String, Integer> {
    public static final Parcelable.Creator<zzach> CREATOR = new zzaci();
    final int mVersionCode;
    private final HashMap<String, Integer> zzaFv;
    private final SparseArray<String> zzaFw;
    private final ArrayList<zza> zzaFx;

    public static final class zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Parcelable.Creator<zza> CREATOR = new zzacj();
        final int versionCode;
        final String zzaFy;
        final int zzaFz;

        zza(int i, String str, int i2) {
            this.versionCode = i;
            this.zzaFy = str;
            this.zzaFz = i2;
        }

        zza(String str, int i) {
            this.versionCode = 1;
            this.zzaFy = str;
            this.zzaFz = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacj.zza(this, parcel, i);
        }
    }

    public zzach() {
        this.mVersionCode = 1;
        this.zzaFv = new HashMap<>();
        this.zzaFw = new SparseArray<>();
        this.zzaFx = null;
    }

    zzach(int i, ArrayList<zza> arrayList) {
        this.mVersionCode = i;
        this.zzaFv = new HashMap<>();
        this.zzaFw = new SparseArray<>();
        this.zzaFx = null;
        zzh(arrayList);
    }

    private void zzh(ArrayList<zza> arrayList) {
        Iterator<zza> it = arrayList.iterator();
        while (it.hasNext()) {
            zza next = it.next();
            zzj(next.zzaFy, next.zzaFz);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaci.zza(this, parcel, i);
    }

    /* renamed from: zzd */
    public String convertBack(Integer num) {
        String str = this.zzaFw.get(num.intValue());
        return (str != null || !this.zzaFv.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public zzach zzj(String str, int i) {
        this.zzaFv.put(str, Integer.valueOf(i));
        this.zzaFw.put(i, str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<zza> zzxJ() {
        ArrayList<zza> arrayList = new ArrayList<>();
        for (String next : this.zzaFv.keySet()) {
            arrayList.add(new zza(next, this.zzaFv.get(next).intValue()));
        }
        return arrayList;
    }
}
