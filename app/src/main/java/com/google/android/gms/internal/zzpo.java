package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.List;

@zzmb
public class zzpo {
    private final String[] zzXl;
    private final double[] zzXm;
    private final double[] zzXn;
    private final int[] zzXo;
    private int zzXp;

    public static class zza {
        public final int count;
        public final String name;
        public final double zzXq;
        public final double zzXr;
        public final double zzXs;

        public zza(String str, double d, double d2, double d3, int i) {
            this.name = str;
            this.zzXr = d;
            this.zzXq = d2;
            this.zzXs = d3;
            this.count = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzaa.equal(this.name, zza.name) && this.zzXq == zza.zzXq && this.zzXr == zza.zzXr && this.count == zza.count && Double.compare(this.zzXs, zza.zzXs) == 0;
        }

        public int hashCode() {
            return zzaa.hashCode(this.name, Double.valueOf(this.zzXq), Double.valueOf(this.zzXr), Double.valueOf(this.zzXs), Integer.valueOf(this.count));
        }

        public String toString() {
            return zzaa.zzv(this).zzg("name", this.name).zzg("minBound", Double.valueOf(this.zzXr)).zzg("maxBound", Double.valueOf(this.zzXq)).zzg("percent", Double.valueOf(this.zzXs)).zzg("count", Integer.valueOf(this.count)).toString();
        }
    }

    public static class zzb {
        /* access modifiers changed from: private */
        public final List<String> zzXt = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Double> zzXu = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Double> zzXv = new ArrayList();

        public zzb zza(String str, double d, double d2) {
            int i;
            int i2 = 0;
            while (true) {
                i = i2;
                if (i >= this.zzXt.size()) {
                    break;
                }
                double doubleValue = this.zzXv.get(i).doubleValue();
                double doubleValue2 = this.zzXu.get(i).doubleValue();
                if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                    break;
                }
                i2 = i + 1;
            }
            this.zzXt.add(i, str);
            this.zzXv.add(i, Double.valueOf(d));
            this.zzXu.add(i, Double.valueOf(d2));
            return this;
        }

        public zzpo zzkB() {
            return new zzpo(this);
        }
    }

    private zzpo(zzb zzb2) {
        int size = zzb2.zzXu.size();
        this.zzXl = (String[]) zzb2.zzXt.toArray(new String[size]);
        this.zzXm = zzn(zzb2.zzXu);
        this.zzXn = zzn(zzb2.zzXv);
        this.zzXo = new int[size];
        this.zzXp = 0;
    }

    private double[] zzn(List<Double> list) {
        double[] dArr = new double[list.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= dArr.length) {
                return dArr;
            }
            dArr[i2] = list.get(i2).doubleValue();
            i = i2 + 1;
        }
    }

    public List<zza> getBuckets() {
        ArrayList arrayList = new ArrayList(this.zzXl.length);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zzXl.length) {
                return arrayList;
            }
            arrayList.add(new zza(this.zzXl[i2], this.zzXn[i2], this.zzXm[i2], ((double) this.zzXo[i2]) / ((double) this.zzXp), this.zzXo[i2]));
            i = i2 + 1;
        }
    }

    public void zza(double d) {
        this.zzXp++;
        int i = 0;
        while (i < this.zzXn.length) {
            if (this.zzXn[i] <= d && d < this.zzXm[i]) {
                int[] iArr = this.zzXo;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzXn[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
