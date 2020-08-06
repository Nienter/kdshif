package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzv;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzmb
public class zzcz {
    private final Object zzrN = new Object();
    private int zzxt;
    private List<zzcy> zzxu = new LinkedList();

    public boolean zza(zzcy zzcy) {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzxu.contains(zzcy);
        }
        return z;
    }

    public boolean zzb(zzcy zzcy) {
        synchronized (this.zzrN) {
            Iterator<zzcy> it = this.zzxu.iterator();
            while (it.hasNext()) {
                zzcy next = it.next();
                if (!zzfx.zzBU.get().booleanValue() || zzv.zzcN().zzjJ()) {
                    if (zzfx.zzBW.get().booleanValue() && !zzv.zzcN().zzjK() && zzcy != next && next.zzdX().equals(zzcy.zzdX())) {
                        it.remove();
                        return true;
                    }
                } else if (zzcy != next && next.zzdV().equals(zzcy.zzdV())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public void zzc(zzcy zzcy) {
        synchronized (this.zzrN) {
            if (this.zzxu.size() >= 10) {
                zzpe.zzbc(new StringBuilder(41).append("Queue is full, current size = ").append(this.zzxu.size()).toString());
                this.zzxu.remove(0);
            }
            int i = this.zzxt;
            this.zzxt = i + 1;
            zzcy.zzn(i);
            this.zzxu.add(zzcy);
        }
    }

    @Nullable
    public zzcy zzed() {
        int i;
        zzcy zzcy;
        int i2;
        zzcy zzcy2 = null;
        int i3 = 0;
        synchronized (this.zzrN) {
            if (this.zzxu.size() == 0) {
                zzpe.zzbc("Queue empty");
                return null;
            } else if (this.zzxu.size() >= 2) {
                int i4 = Integer.MIN_VALUE;
                int i5 = 0;
                for (zzcy next : this.zzxu) {
                    int score = next.getScore();
                    if (score > i4) {
                        i2 = score;
                        zzcy = next;
                        i = i5;
                    } else {
                        i = i3;
                        zzcy = zzcy2;
                        i2 = i4;
                    }
                    i5++;
                    i4 = i2;
                    zzcy2 = zzcy;
                    i3 = i;
                }
                this.zzxu.remove(i3);
                return zzcy2;
            } else {
                zzcy zzcy3 = this.zzxu.get(0);
                zzcy3.zzdY();
                return zzcy3;
            }
        }
    }
}
