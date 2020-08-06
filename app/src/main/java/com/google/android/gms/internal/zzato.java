package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class zzato extends zzats {
    /* access modifiers changed from: private */
    public static final AtomicLong zzbsV = new AtomicLong(Long.MIN_VALUE);
    /* access modifiers changed from: private */
    public zzd zzbsM;
    /* access modifiers changed from: private */
    public zzd zzbsN;
    private final PriorityBlockingQueue<FutureTask<?>> zzbsO = new PriorityBlockingQueue<>();
    private final BlockingQueue<FutureTask<?>> zzbsP = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzbsQ = new zzb("Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzbsR = new zzb("Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzbsS = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzbsT = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzbsU;

    static class zza extends RuntimeException {
    }

    private final class zzb implements Thread.UncaughtExceptionHandler {
        private final String zzbsW;

        public zzb(String str) {
            zzac.zzw(str);
            this.zzbsW = str;
        }

        public synchronized void uncaughtException(Thread thread, Throwable th) {
            zzato.this.zzJt().zzLa().zzj(this.zzbsW, th);
        }
    }

    private final class zzc<V> extends FutureTask<V> implements Comparable<zzc> {
        private final String zzbsW;
        private final long zzbsY = zzato.zzbsV.getAndIncrement();
        private final boolean zzbsZ;

        zzc(Runnable runnable, boolean z, String str) {
            super(runnable, null);
            zzac.zzw(str);
            this.zzbsW = str;
            this.zzbsZ = z;
            if (this.zzbsY == Long.MAX_VALUE) {
                zzato.this.zzJt().zzLa().log("Tasks index overflow");
            }
        }

        zzc(Callable<V> callable, boolean z, String str) {
            super(callable);
            zzac.zzw(str);
            this.zzbsW = str;
            this.zzbsZ = z;
            if (this.zzbsY == Long.MAX_VALUE) {
                zzato.this.zzJt().zzLa().log("Tasks index overflow");
            }
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable th) {
            zzato.this.zzJt().zzLa().zzj(this.zzbsW, th);
            if (th instanceof zza) {
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
            }
            super.setException(th);
        }

        /* renamed from: zzb */
        public int compareTo(@NonNull zzc zzc) {
            if (this.zzbsZ != zzc.zzbsZ) {
                return this.zzbsZ ? -1 : 1;
            }
            if (this.zzbsY < zzc.zzbsY) {
                return -1;
            }
            if (this.zzbsY > zzc.zzbsY) {
                return 1;
            }
            zzato.this.zzJt().zzLb().zzj("Two tasks share the same index. index", Long.valueOf(this.zzbsY));
            return 0;
        }
    }

    private final class zzd extends Thread {
        private final Object zzbta = new Object();
        private final BlockingQueue<FutureTask<?>> zzbtb;

        public zzd(String str, BlockingQueue<FutureTask<?>> blockingQueue) {
            zzac.zzw(str);
            zzac.zzw(blockingQueue);
            this.zzbtb = blockingQueue;
            setName(str);
        }

        private void zza(InterruptedException interruptedException) {
            zzato.this.zzJt().zzLc().zzj(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0078, code lost:
            r1 = com.google.android.gms.internal.zzato.zzc(r4.zzbsX);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x007e, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
            com.google.android.gms.internal.zzato.zza(r4.zzbsX).release();
            com.google.android.gms.internal.zzato.zzc(r4.zzbsX).notifyAll();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0097, code lost:
            if (r4 != com.google.android.gms.internal.zzato.zzd(r4.zzbsX)) goto L_0x00a9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0099, code lost:
            com.google.android.gms.internal.zzato.zza(r4.zzbsX, null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x009f, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a0, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x00af, code lost:
            if (r4 != com.google.android.gms.internal.zzato.zze(r4.zzbsX)) goto L_0x00bb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b1, code lost:
            com.google.android.gms.internal.zzato.zzb(r4.zzbsX, null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
            r4.zzbsX.zzJt().zzLa().log("Current scheduler thread is neither worker nor network");
         */
        public void run() {
            boolean z = false;
            while (!z) {
                try {
                    zzato.this.zzbsT.acquire();
                    z = true;
                } catch (InterruptedException e) {
                    zza(e);
                }
            }
            while (true) {
                try {
                    FutureTask futureTask = (FutureTask) this.zzbtb.poll();
                    if (futureTask != null) {
                        futureTask.run();
                    } else {
                        synchronized (this.zzbta) {
                            if (this.zzbtb.peek() == null && !zzato.this.zzbsU) {
                                try {
                                    this.zzbta.wait(30000);
                                } catch (InterruptedException e2) {
                                    zza(e2);
                                }
                            }
                        }
                        synchronized (zzato.this.zzbsS) {
                            if (this.zzbtb.peek() == null) {
                            }
                        }
                    }
                } catch (Throwable th) {
                    synchronized (zzato.this.zzbsS) {
                        zzato.this.zzbsT.release();
                        zzato.this.zzbsS.notifyAll();
                        if (this == zzato.this.zzbsM) {
                            zzd unused = zzato.this.zzbsM = null;
                        } else if (this == zzato.this.zzbsN) {
                            zzd unused2 = zzato.this.zzbsN = null;
                        } else {
                            zzato.this.zzJt().zzLa().log("Current scheduler thread is neither worker nor network");
                        }
                        throw th;
                    }
                }
            }
        }

        public void zzhf() {
            synchronized (this.zzbta) {
                this.zzbta.notifyAll();
            }
        }
    }

    zzato(zzatp zzatp) {
        super(zzatp);
    }

    private void zza(zzc<?> zzc2) {
        synchronized (this.zzbsS) {
            this.zzbsO.add(zzc2);
            if (this.zzbsM == null) {
                this.zzbsM = new zzd("Measurement Worker", this.zzbsO);
                this.zzbsM.setUncaughtExceptionHandler(this.zzbsQ);
                this.zzbsM.start();
            } else {
                this.zzbsM.zzhf();
            }
        }
    }

    private void zza(FutureTask<?> futureTask) {
        synchronized (this.zzbsS) {
            this.zzbsP.add(futureTask);
            if (this.zzbsN == null) {
                this.zzbsN = new zzd("Measurement Network", this.zzbsP);
                this.zzbsN.setUncaughtExceptionHandler(this.zzbsR);
                this.zzbsN.start();
            } else {
                this.zzbsN.zzhf();
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzJd() {
        super.zzJd();
    }

    public /* bridge */ /* synthetic */ void zzJe() {
        super.zzJe();
    }

    public void zzJf() {
        if (Thread.currentThread() != this.zzbsN) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public /* bridge */ /* synthetic */ zzaso zzJg() {
        return super.zzJg();
    }

    public /* bridge */ /* synthetic */ zzass zzJh() {
        return super.zzJh();
    }

    public /* bridge */ /* synthetic */ zzatu zzJi() {
        return super.zzJi();
    }

    public /* bridge */ /* synthetic */ zzatf zzJj() {
        return super.zzJj();
    }

    public /* bridge */ /* synthetic */ zzasw zzJk() {
        return super.zzJk();
    }

    public /* bridge */ /* synthetic */ zzatw zzJl() {
        return super.zzJl();
    }

    public /* bridge */ /* synthetic */ zzatv zzJm() {
        return super.zzJm();
    }

    public /* bridge */ /* synthetic */ zzatg zzJn() {
        return super.zzJn();
    }

    public /* bridge */ /* synthetic */ zzasu zzJo() {
        return super.zzJo();
    }

    public /* bridge */ /* synthetic */ zzaue zzJp() {
        return super.zzJp();
    }

    public /* bridge */ /* synthetic */ zzatn zzJq() {
        return super.zzJq();
    }

    public /* bridge */ /* synthetic */ zzaty zzJr() {
        return super.zzJr();
    }

    public /* bridge */ /* synthetic */ zzato zzJs() {
        return super.zzJs();
    }

    public /* bridge */ /* synthetic */ zzati zzJt() {
        return super.zzJt();
    }

    public /* bridge */ /* synthetic */ zzatl zzJu() {
        return super.zzJu();
    }

    public /* bridge */ /* synthetic */ zzast zzJv() {
        return super.zzJv();
    }

    public boolean zzLr() {
        return Thread.currentThread() == this.zzbsM;
    }

    public boolean zzbd() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public <V> Future<V> zzd(Callable<V> callable) {
        zznA();
        zzac.zzw(callable);
        zzc zzc2 = new zzc(callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbsM) {
            zzc2.run();
        } else {
            zza((zzc<?>) zzc2);
        }
        return zzc2;
    }

    public <V> Future<V> zze(Callable<V> callable) {
        zznA();
        zzac.zzw(callable);
        zzc zzc2 = new zzc(callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbsM) {
            zzc2.run();
        } else {
            zza((zzc<?>) zzc2);
        }
        return zzc2;
    }

    public void zzm(Runnable runnable) {
        zznA();
        zzac.zzw(runnable);
        zza((zzc<?>) new zzc(runnable, false, "Task exception on worker thread"));
    }

    public void zzmq() {
        if (Thread.currentThread() != this.zzbsM) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    public void zzn(Runnable runnable) {
        zznA();
        zzac.zzw(runnable);
        zza((FutureTask<?>) new zzc(runnable, false, "Task exception on network thread"));
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }
}
