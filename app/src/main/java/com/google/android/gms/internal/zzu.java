package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class zzu {
    protected static final Comparator<byte[]> zzau = new Comparator<byte[]>() {
        /* renamed from: zza */
        public int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    };
    private List<byte[]> zzaq = new LinkedList();
    private List<byte[]> zzar = new ArrayList(64);
    private int zzas = 0;
    private final int zzat;

    public zzu(int i) {
        this.zzat = i;
    }

    private synchronized void zzt() {
        while (this.zzas > this.zzat) {
            byte[] remove = this.zzaq.remove(0);
            this.zzar.remove(remove);
            this.zzas -= remove.length;
        }
    }

    public synchronized void zza(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.zzat) {
                this.zzaq.add(bArr);
                int binarySearch = Collections.binarySearch(this.zzar, bArr, zzau);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.zzar.add(binarySearch, bArr);
                this.zzas += bArr.length;
                zzt();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = new byte[r5];
     */
    public synchronized byte[] zzb(int i) {
        byte[] bArr;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.zzar.size()) {
                break;
            }
            bArr = this.zzar.get(i3);
            if (bArr.length >= i) {
                this.zzas -= bArr.length;
                this.zzar.remove(i3);
                this.zzaq.remove(bArr);
                break;
            }
            i2 = i3 + 1;
        }
        return bArr;
    }
}
