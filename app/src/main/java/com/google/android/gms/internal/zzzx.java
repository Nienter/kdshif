package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.internal.zzabq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzzx<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zzayN = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        /* renamed from: zzvg */
        public Boolean initialValue() {
            return false;
        }
    };
    private boolean zzJ;
    private final Object zzayO;
    protected final zza<R> zzayP;
    protected final WeakReference<GoogleApiClient> zzayQ;
    private final ArrayList<PendingResult.zza> zzayR;
    private ResultCallback<? super R> zzayS;
    private final AtomicReference<zzabq.zzb> zzayT;
    private zzb zzayU;
    private volatile boolean zzayV;
    private boolean zzayW;
    private zzs zzayX;
    private volatile zzabp<R> zzayY;
    private boolean zzayZ;
    /* access modifiers changed from: private */
    public R zzayd;
    private final CountDownLatch zzth;

    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    zzb((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((zzzx) message.obj).zzB(Status.zzayk);
                    return;
                default:
                    Log.wtf("BasePendingResult", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
                    return;
            }
        }

        public void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void zza(zzzx<R> zzzx, long j) {
            sendMessageDelayed(obtainMessage(2, zzzx), j);
        }

        /* access modifiers changed from: protected */
        public void zzb(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                zzzx.zzd(r);
                throw e;
            }
        }

        public void zzvh() {
            removeMessages(2);
        }
    }

    private final class zzb {
        private zzb() {
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            zzzx.zzd(zzzx.this.zzayd);
            super.finalize();
        }
    }

    @Deprecated
    zzzx() {
        this.zzayO = new Object();
        this.zzth = new CountDownLatch(1);
        this.zzayR = new ArrayList<>();
        this.zzayT = new AtomicReference<>();
        this.zzayZ = false;
        this.zzayP = new zza<>(Looper.getMainLooper());
        this.zzayQ = new WeakReference<>(null);
    }

    @Deprecated
    protected zzzx(Looper looper) {
        this.zzayO = new Object();
        this.zzth = new CountDownLatch(1);
        this.zzayR = new ArrayList<>();
        this.zzayT = new AtomicReference<>();
        this.zzayZ = false;
        this.zzayP = new zza<>(looper);
        this.zzayQ = new WeakReference<>(null);
    }

    protected zzzx(GoogleApiClient googleApiClient) {
        this.zzayO = new Object();
        this.zzth = new CountDownLatch(1);
        this.zzayR = new ArrayList<>();
        this.zzayT = new AtomicReference<>();
        this.zzayZ = false;
        this.zzayP = new zza<>(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zzayQ = new WeakReference<>(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.zzayO) {
            if (this.zzayV) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed.");
            zzac.zza(isReady(), (Object) "Result is not ready.");
            r = this.zzayd;
            this.zzayd = null;
            this.zzayS = null;
            this.zzayV = true;
        }
        zzvd();
        return r;
    }

    private void zzc(R r) {
        this.zzayd = r;
        this.zzayX = null;
        this.zzth.countDown();
        Status status = this.zzayd.getStatus();
        if (this.zzJ) {
            this.zzayS = null;
        } else if (this.zzayS != null) {
            this.zzayP.zzvh();
            this.zzayP.zza(this.zzayS, get());
        } else if (this.zzayd instanceof Releasable) {
            this.zzayU = new zzb();
        }
        Iterator<PendingResult.zza> it = this.zzayR.iterator();
        while (it.hasNext()) {
            it.next().zzx(status);
        }
        this.zzayR.clear();
    }

    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private void zzvd() {
        zzabq.zzb andSet = this.zzayT.getAndSet(null);
        if (andSet != null) {
            andSet.zzc(this);
        }
    }

    public final R await() {
        boolean z = true;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzac.zza(!this.zzayV, (Object) "Result has already been consumed");
        if (this.zzayY != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zzth.await();
        } catch (InterruptedException e) {
            zzB(Status.zzayi);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        zzac.zza(j <= 0 || Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzac.zza(!this.zzayV, (Object) "Result has already been consumed.");
        if (this.zzayY != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zzth.await(j, timeUnit)) {
                zzB(Status.zzayk);
            }
        } catch (InterruptedException e) {
            zzB(Status.zzayi);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    public void cancel() {
        synchronized (this.zzayO) {
            if (!this.zzJ && !this.zzayV) {
                if (this.zzayX != null) {
                    try {
                        this.zzayX.cancel();
                    } catch (RemoteException e) {
                    }
                }
                zzd(this.zzayd);
                this.zzJ = true;
                zzc(zzc(Status.zzayl));
            }
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzayO) {
            z = this.zzJ;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzth.getCount() == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        boolean z = true;
        synchronized (this.zzayO) {
            if (resultCallback == null) {
                this.zzayS = null;
                return;
            }
            zzac.zza(!this.zzayV, (Object) "Result has already been consumed.");
            if (this.zzayY != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot set callbacks if then() has been called.");
            if (!isCanceled()) {
                if (isReady()) {
                    this.zzayP.zza(resultCallback, get());
                } else {
                    this.zzayS = resultCallback;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    public final void setResultCallback(ResultCallback<? super R> resultCallback, long j, TimeUnit timeUnit) {
        boolean z = true;
        synchronized (this.zzayO) {
            if (resultCallback == null) {
                this.zzayS = null;
                return;
            }
            zzac.zza(!this.zzayV, (Object) "Result has already been consumed.");
            if (this.zzayY != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot set callbacks if then() has been called.");
            if (!isCanceled()) {
                if (isReady()) {
                    this.zzayP.zza(resultCallback, get());
                } else {
                    this.zzayS = resultCallback;
                    this.zzayP.zza(this, timeUnit.toMillis(j));
                }
            }
        }
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzac.zza(!this.zzayV, (Object) "Result has already been consumed.");
        synchronized (this.zzayO) {
            zzac.zza(this.zzayY == null, (Object) "Cannot call then() twice.");
            if (this.zzayS != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() if callbacks are set.");
            this.zzayZ = true;
            this.zzayY = new zzabp<>(this.zzayQ);
            then = this.zzayY.then(resultTransform);
            if (isReady()) {
                this.zzayP.zza(this.zzayY, get());
            } else {
                this.zzayS = this.zzayY;
            }
        }
        return then;
    }

    public final void zzB(Status status) {
        synchronized (this.zzayO) {
            if (!isReady()) {
                zzb(zzc(status));
                this.zzayW = true;
            }
        }
    }

    public final void zza(PendingResult.zza zza2) {
        boolean z = true;
        zzac.zza(!this.zzayV, (Object) "Result has already been consumed.");
        if (zza2 == null) {
            z = false;
        }
        zzac.zzb(z, (Object) "Callback cannot be null.");
        synchronized (this.zzayO) {
            if (isReady()) {
                zza2.zzx(this.zzayd.getStatus());
            } else {
                this.zzayR.add(zza2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzs zzs) {
        synchronized (this.zzayO) {
            this.zzayX = zzs;
        }
    }

    public void zza(zzabq.zzb zzb2) {
        this.zzayT.set(zzb2);
    }

    public final void zzb(R r) {
        boolean z = true;
        synchronized (this.zzayO) {
            if (this.zzayW || this.zzJ) {
                zzd(r);
                return;
            }
            if (isReady()) {
            }
            zzac.zza(!isReady(), (Object) "Results have already been set");
            if (this.zzayV) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed");
            zzc(r);
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract R zzc(Status status);

    public Integer zzuR() {
        return null;
    }

    public boolean zzvc() {
        boolean isCanceled;
        synchronized (this.zzayO) {
            if (((GoogleApiClient) this.zzayQ.get()) == null || !this.zzayZ) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzve() {
        setResultCallback(null);
    }

    public void zzvf() {
        this.zzayZ = this.zzayZ || zzayN.get().booleanValue();
    }
}
