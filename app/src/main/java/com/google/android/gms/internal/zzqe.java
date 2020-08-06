package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@zzmb
public class zzqe {

    public interface zza<D, R> {
        R apply(D d);
    }

    public static <A, B> zzqf<B> zza(final zzqf<A> zzqf, final zza<A, B> zza2) {
        final zzqc zzqc = new zzqc();
        zzqf.zzc(new Runnable() {
            public void run() {
                try {
                    zzqc.this.zzh(zza2.apply(zzqf.get()));
                } catch (InterruptedException | CancellationException | ExecutionException e) {
                    zzqc.this.cancel(true);
                }
            }
        });
        return zzqc;
    }

    public static <V> zzqf<List<V>> zzo(final List<zzqf<V>> list) {
        final zzqc zzqc = new zzqc();
        final int size = list.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzqf<V> zzc : list) {
            zzc.zzc(new Runnable() {
                public void run() {
                    if (atomicInteger.incrementAndGet() >= size) {
                        try {
                            zzqc.zzh(zzqe.zzp(list));
                        } catch (InterruptedException | ExecutionException e) {
                            zzpe.zzc("Unable to convert list of futures to a future of list", e);
                        }
                    }
                }
            });
        }
        return zzqc;
    }

    /* access modifiers changed from: private */
    public static <V> List<V> zzp(List<zzqf<V>> list) {
        ArrayList arrayList = new ArrayList();
        for (zzqf<V> zzqf : list) {
            Object obj = zzqf.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
