package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class zzaco extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<zzaco> CREATOR = new zzacp();
    final int mVersionCode;
    private final HashMap<String, Map<String, zzack.zza<?, ?>>> zzaFK;
    private final ArrayList<zza> zzaFL = null;
    private final String zzaFM;

    public static class zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Parcelable.Creator<zza> CREATOR = new zzacq();
        final String className;
        final int versionCode;
        final ArrayList<zzb> zzaFN;

        zza(int i, String str, ArrayList<zzb> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.zzaFN = arrayList;
        }

        zza(String str, Map<String, zzack.zza<?, ?>> map) {
            this.versionCode = 1;
            this.className = str;
            this.zzaFN = zzW(map);
        }

        private static ArrayList<zzb> zzW(Map<String, zzack.zza<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList<zzb> arrayList = new ArrayList<>();
            for (String next : map.keySet()) {
                arrayList.add(new zzb(next, map.get(next)));
            }
            return arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacq.zza(this, parcel, i);
        }

        /* access modifiers changed from: package-private */
        public HashMap<String, zzack.zza<?, ?>> zzxZ() {
            HashMap<String, zzack.zza<?, ?>> hashMap = new HashMap<>();
            int size = this.zzaFN.size();
            for (int i = 0; i < size; i++) {
                zzb zzb = this.zzaFN.get(i);
                hashMap.put(zzb.zzaA, zzb.zzaFO);
            }
            return hashMap;
        }
    }

    public static class zzb extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Parcelable.Creator<zzb> CREATOR = new zzacn();
        final int versionCode;
        final String zzaA;
        final zzack.zza<?, ?> zzaFO;

        zzb(int i, String str, zzack.zza<?, ?> zza) {
            this.versionCode = i;
            this.zzaA = str;
            this.zzaFO = zza;
        }

        zzb(String str, zzack.zza<?, ?> zza) {
            this.versionCode = 1;
            this.zzaA = str;
            this.zzaFO = zza;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacn.zza(this, parcel, i);
        }
    }

    zzaco(int i, ArrayList<zza> arrayList, String str) {
        this.mVersionCode = i;
        this.zzaFK = zzi(arrayList);
        this.zzaFM = (String) zzac.zzw(str);
        zzxW();
    }

    private static HashMap<String, Map<String, zzack.zza<?, ?>>> zzi(ArrayList<zza> arrayList) {
        HashMap<String, Map<String, zzack.zza<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zza zza2 = arrayList.get(i);
            hashMap.put(zza2.className, zza2.zzxZ());
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String next : this.zzaFK.keySet()) {
            sb.append(next).append(":\n");
            Map map = this.zzaFK.get(next);
            for (String str : map.keySet()) {
                sb.append("  ").append(str).append(": ");
                sb.append(map.get(str));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzacp.zza(this, parcel, i);
    }

    public Map<String, zzack.zza<?, ?>> zzdA(String str) {
        return this.zzaFK.get(str);
    }

    public void zzxW() {
        for (String str : this.zzaFK.keySet()) {
            Map map = this.zzaFK.get(str);
            for (String str2 : map.keySet()) {
                ((zzack.zza) map.get(str2)).zza(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<zza> zzxX() {
        ArrayList<zza> arrayList = new ArrayList<>();
        for (String next : this.zzaFK.keySet()) {
            arrayList.add(new zza(next, this.zzaFK.get(next)));
        }
        return arrayList;
    }

    public String zzxY() {
        return this.zzaFM;
    }
}
