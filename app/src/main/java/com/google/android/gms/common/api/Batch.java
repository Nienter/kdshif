package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.zzzx;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzzx<BatchResult> {
    /* access modifiers changed from: private */
    public boolean zzaxA;
    /* access modifiers changed from: private */
    public boolean zzaxB;
    /* access modifiers changed from: private */
    public final PendingResult<?>[] zzaxC;
    /* access modifiers changed from: private */
    public int zzaxz;
    /* access modifiers changed from: private */
    public final Object zzrN;

    public static final class Builder {
        private GoogleApiClient zzamy;
        private List<PendingResult<?>> zzaxE = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.zzamy = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zzaxE.size());
            this.zzaxE.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.zzaxE, this.zzamy);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzrN = new Object();
        this.zzaxz = list.size();
        this.zzaxC = new PendingResult[this.zzaxz];
        if (list.isEmpty()) {
            zzb(new BatchResult(Status.zzayh, this.zzaxC));
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                PendingResult<?> pendingResult = list.get(i2);
                this.zzaxC[i2] = pendingResult;
                pendingResult.zza(new PendingResult.zza() {
                    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
                        return;
                     */
                    public void zzx(Status status) {
                        synchronized (Batch.this.zzrN) {
                            if (!Batch.this.isCanceled()) {
                                if (status.isCanceled()) {
                                    boolean unused = Batch.this.zzaxB = true;
                                } else if (!status.isSuccess()) {
                                    boolean unused2 = Batch.this.zzaxA = true;
                                }
                                Batch.zzb(Batch.this);
                                if (Batch.this.zzaxz == 0) {
                                    if (Batch.this.zzaxB) {
                                        Batch.super.cancel();
                                    } else {
                                        Batch.this.zzb(new BatchResult(Batch.this.zzaxA ? new Status(13) : Status.zzayh, Batch.this.zzaxC));
                                    }
                                }
                            }
                        }
                    }
                });
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    static /* synthetic */ int zzb(Batch batch) {
        int i = batch.zzaxz;
        batch.zzaxz = i - 1;
        return i;
    }

    public void cancel() {
        super.cancel();
        for (PendingResult<?> cancel : this.zzaxC) {
            cancel.cancel();
        }
    }

    /* renamed from: createFailedResult */
    public BatchResult zzc(Status status) {
        return new BatchResult(status, this.zzaxC);
    }
}
