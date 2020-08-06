package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import java.lang.ref.WeakReference;

public class zzabp<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */
    public ResultTransform<? super R, ? extends Result> zzaBM = null;
    /* access modifiers changed from: private */
    public zzabp<? extends Result> zzaBN = null;
    private volatile ResultCallbacks<? super R> zzaBO = null;
    private PendingResult<R> zzaBP = null;
    private Status zzaBQ = null;
    /* access modifiers changed from: private */
    public final zza zzaBR;
    private boolean zzaBS = false;
    /* access modifiers changed from: private */
    public final Object zzayO = new Object();
    /* access modifiers changed from: private */
    public final WeakReference<GoogleApiClient> zzayQ;

    private final class zza extends Handler {
        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    PendingResult pendingResult = (PendingResult) message.obj;
                    synchronized (zzabp.this.zzayO) {
                        if (pendingResult == null) {
                            zzabp.this.zzaBN.zzD(new Status(13, "Transform returned null"));
                        } else if (pendingResult instanceof zzabh) {
                            zzabp.this.zzaBN.zzD(((zzabh) pendingResult).getStatus());
                        } else {
                            zzabp.this.zzaBN.zza(pendingResult);
                        }
                    }
                    return;
                case 1:
                    RuntimeException runtimeException = (RuntimeException) message.obj;
                    String valueOf = String.valueOf(runtimeException.getMessage());
                    Log.e("TransformedResultImpl", valueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(valueOf) : new String("Runtime exception on the transformation worker thread: "));
                    throw runtimeException;
                default:
                    Log.e("TransformedResultImpl", new StringBuilder(70).append("TransformationResultHandler received unknown message type: ").append(message.what).toString());
                    return;
            }
        }
    }

    public zzabp(WeakReference<GoogleApiClient> weakReference) {
        zzac.zzb(weakReference, (Object) "GoogleApiClient reference must not be null");
        this.zzayQ = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zzayQ.get();
        this.zzaBR = new zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    /* access modifiers changed from: private */
    public void zzD(Status status) {
        synchronized (this.zzayO) {
            this.zzaBQ = status;
            zzE(this.zzaBQ);
        }
    }

    private void zzE(Status status) {
        synchronized (this.zzayO) {
            if (this.zzaBM != null) {
                Status onFailure = this.zzaBM.onFailure(status);
                zzac.zzb(onFailure, (Object) "onFailure must not return null");
                this.zzaBN.zzD(onFailure);
            } else if (zzwv()) {
                this.zzaBO.onFailure(status);
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private void zzwt() {
        if (this.zzaBM != null || this.zzaBO != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzayQ.get();
            if (!(this.zzaBS || this.zzaBM == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzaBS = true;
            }
            if (this.zzaBQ != null) {
                zzE(this.zzaBQ);
            } else if (this.zzaBP != null) {
                this.zzaBP.setResultCallback(this);
            }
        }
    }

    private boolean zzwv() {
        return (this.zzaBO == null || ((GoogleApiClient) this.zzayQ.get()) == null) ? false : true;
    }

    public void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        boolean z = true;
        synchronized (this.zzayO) {
            zzac.zza(this.zzaBO == null, (Object) "Cannot call andFinally() twice.");
            if (this.zzaBM != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzaBO = resultCallbacks;
            zzwt();
        }
    }

    public void onResult(final R r) {
        synchronized (this.zzayO) {
            if (!r.getStatus().isSuccess()) {
                zzD(r.getStatus());
                zzd((Result) r);
            } else if (this.zzaBM != null) {
                zzabg.zzvR().submit(new Runnable() {
                    @WorkerThread
                    public void run() {
                        try {
                            zzzx.zzayN.set(true);
                            zzabp.this.zzaBR.sendMessage(zzabp.this.zzaBR.obtainMessage(0, zzabp.this.zzaBM.onSuccess(r)));
                            zzzx.zzayN.set(false);
                            zzabp.this.zzd(r);
                            GoogleApiClient googleApiClient = (GoogleApiClient) zzabp.this.zzayQ.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(zzabp.this);
                            }
                        } catch (RuntimeException e) {
                            zzabp.this.zzaBR.sendMessage(zzabp.this.zzaBR.obtainMessage(1, e));
                            zzzx.zzayN.set(false);
                            zzabp.this.zzd(r);
                            GoogleApiClient googleApiClient2 = (GoogleApiClient) zzabp.this.zzayQ.get();
                            if (googleApiClient2 != null) {
                                googleApiClient2.zzb(zzabp.this);
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            zzzx.zzayN.set(false);
                            zzabp.this.zzd(r);
                            GoogleApiClient googleApiClient3 = (GoogleApiClient) zzabp.this.zzayQ.get();
                            if (googleApiClient3 != null) {
                                googleApiClient3.zzb(zzabp.this);
                            }
                            throw th2;
                        }
                    }
                });
            } else if (zzwv()) {
                this.zzaBO.onSuccess(r);
            }
        }
    }

    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zzabp<? extends Result> zzabp;
        boolean z = true;
        synchronized (this.zzayO) {
            zzac.zza(this.zzaBM == null, (Object) "Cannot call then() twice.");
            if (this.zzaBO != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzaBM = resultTransform;
            zzabp = new zzabp<>(this.zzayQ);
            this.zzaBN = zzabp;
            zzwt();
        }
        return zzabp;
    }

    public void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzayO) {
            this.zzaBP = pendingResult;
            zzwt();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzwu() {
        this.zzaBO = null;
    }
}
