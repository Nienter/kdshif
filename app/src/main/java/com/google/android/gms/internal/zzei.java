package com.google.android.gms.internal;

import com.google.android.gms.internal.zzet;
import java.util.Random;

@zzmb
public class zzei extends zzet.zza {
    private Object zzrN = new Object();
    private final Random zzzI = new Random();
    private long zzzJ;

    public zzei() {
        zzeQ();
    }

    public long getValue() {
        return this.zzzJ;
    }

    public void zzeQ() {
        synchronized (this.zzrN) {
            int i = 3;
            long j = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                j = ((long) this.zzzI.nextInt()) + 2147483648L;
                if (j != this.zzzJ && j != 0) {
                    break;
                }
            }
            this.zzzJ = j;
        }
    }
}
