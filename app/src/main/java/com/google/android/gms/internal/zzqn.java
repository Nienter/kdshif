package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

@zzmb
abstract class zzqn {
    private final WeakReference<View> zzYy;

    public zzqn(View view) {
        this.zzYy = new WeakReference<>(view);
    }

    public final void detach() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            zzb(viewTreeObserver);
        }
    }

    /* access modifiers changed from: protected */
    public ViewTreeObserver getViewTreeObserver() {
        View view = (View) this.zzYy.get();
        if (view == null) {
            return null;
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return null;
        }
        return viewTreeObserver;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(ViewTreeObserver viewTreeObserver);

    /* access modifiers changed from: protected */
    public abstract void zzb(ViewTreeObserver viewTreeObserver);

    public final void zzkN() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            zza(viewTreeObserver);
        }
    }
}
