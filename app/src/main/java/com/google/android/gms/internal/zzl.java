package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class zzl {
    private AtomicInteger zzW;
    private final Map<String, Queue<zzk<?>>> zzX;
    private final Set<zzk<?>> zzY;
    private final PriorityBlockingQueue<zzk<?>> zzZ;
    private final PriorityBlockingQueue<zzk<?>> zzaa;
    private zzg[] zzab;
    private zzc zzac;
    private List<Object> zzad;
    private final zzb zzi;
    private final zzn zzj;
    private final zzf zzx;

    public zzl(zzb zzb, zzf zzf) {
        this(zzb, zzf, 4);
    }

    public zzl(zzb zzb, zzf zzf, int i) {
        this(zzb, zzf, i, new zze(new Handler(Looper.getMainLooper())));
    }

    public zzl(zzb zzb, zzf zzf, int i, zzn zzn) {
        this.zzW = new AtomicInteger();
        this.zzX = new HashMap();
        this.zzY = new HashSet();
        this.zzZ = new PriorityBlockingQueue<>();
        this.zzaa = new PriorityBlockingQueue<>();
        this.zzad = new ArrayList();
        this.zzi = zzb;
        this.zzx = zzf;
        this.zzab = new zzg[i];
        this.zzj = zzn;
    }

    public int getSequenceNumber() {
        return this.zzW.incrementAndGet();
    }

    public void start() {
        stop();
        this.zzac = new zzc(this.zzZ, this.zzaa, this.zzi, this.zzj);
        this.zzac.start();
        for (int i = 0; i < this.zzab.length; i++) {
            zzg zzg = new zzg(this.zzaa, this.zzx, this.zzi, this.zzj);
            this.zzab[i] = zzg;
            zzg.start();
        }
    }

    public void stop() {
        if (this.zzac != null) {
            this.zzac.quit();
        }
        for (int i = 0; i < this.zzab.length; i++) {
            if (this.zzab[i] != null) {
                this.zzab[i].quit();
            }
        }
    }

    public <T> zzk<T> zze(zzk<T> zzk) {
        zzk.zza(this);
        synchronized (this.zzY) {
            this.zzY.add(zzk);
        }
        zzk.zza(getSequenceNumber());
        zzk.zzc("add-to-queue");
        if (!zzk.zzn()) {
            this.zzaa.add(zzk);
        } else {
            synchronized (this.zzX) {
                String zzg = zzk.zzg();
                if (this.zzX.containsKey(zzg)) {
                    Queue queue = this.zzX.get(zzg);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(zzk);
                    this.zzX.put(zzg, queue);
                    if (zzs.DEBUG) {
                        zzs.zza("Request for cacheKey=%s is in flight, putting on hold.", zzg);
                    }
                } else {
                    this.zzX.put(zzg, null);
                    this.zzZ.add(zzk);
                }
            }
        }
        return zzk;
    }

    /* access modifiers changed from: package-private */
    public <T> void zzf(zzk<T> zzk) {
        synchronized (this.zzY) {
            this.zzY.remove(zzk);
        }
        synchronized (this.zzad) {
            Iterator<Object> it = this.zzad.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        if (zzk.zzn()) {
            synchronized (this.zzX) {
                String zzg = zzk.zzg();
                Queue remove = this.zzX.remove(zzg);
                if (remove != null) {
                    if (zzs.DEBUG) {
                        zzs.zza("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), zzg);
                    }
                    this.zzZ.addAll(remove);
                }
            }
        }
    }
}
