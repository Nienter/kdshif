package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.gms.ads.internal.zzv;
import java.lang.ref.WeakReference;

@zzmb
class zzql extends zzqn implements ViewTreeObserver.OnGlobalLayoutListener {
    private final WeakReference<ViewTreeObserver.OnGlobalLayoutListener> zzYx;

    public zzql(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.zzYx = new WeakReference<>(onGlobalLayoutListener);
    }

    public void onGlobalLayout() {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = (ViewTreeObserver.OnGlobalLayoutListener) this.zzYx.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            detach();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    /* access modifiers changed from: protected */
    public void zzb(ViewTreeObserver viewTreeObserver) {
        zzv.zzcL().zza(viewTreeObserver, (ViewTreeObserver.OnGlobalLayoutListener) this);
    }
}
