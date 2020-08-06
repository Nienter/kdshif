package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;

public abstract class zzbhc<T> {
    private final Context mContext;
    private final String mTag;
    private boolean zzbNo = false;
    private T zzbNp;
    private final Object zzrN = new Object();

    public zzbhc(Context context, String str) {
        this.mContext = context;
        this.mTag = str;
    }

    public boolean isOperational() {
        return zzSq() != null;
    }

    /* access modifiers changed from: protected */
    public abstract void zzSn();

    public void zzSp() {
        synchronized (this.zzrN) {
            if (this.zzbNp != null) {
                try {
                    zzSn();
                } catch (RemoteException e) {
                    Log.e(this.mTag, "Could not finalize native handle", e);
                }
                return;
            }
            return;
        }
    }

    /* access modifiers changed from: protected */
    public T zzSq() {
        T t;
        synchronized (this.zzrN) {
            if (this.zzbNp != null) {
                t = this.zzbNp;
            } else {
                try {
                    this.zzbNp = zzb(DynamiteModule.zza(this.mContext, DynamiteModule.zzaQz, "com.google.android.gms.vision.dynamite"), this.mContext);
                } catch (RemoteException | DynamiteModule.zza e) {
                    Log.e(this.mTag, "Error creating remote native handle", e);
                }
                if (!this.zzbNo && this.zzbNp == null) {
                    Log.w(this.mTag, "Native handle not yet available. Reverting to no-op handle.");
                    this.zzbNo = true;
                } else if (this.zzbNo && this.zzbNp != null) {
                    Log.w(this.mTag, "Native handle is now available.");
                }
                t = this.zzbNp;
            }
        }
        return t;
    }

    /* access modifiers changed from: protected */
    public abstract T zzb(DynamiteModule dynamiteModule, Context context);
}
