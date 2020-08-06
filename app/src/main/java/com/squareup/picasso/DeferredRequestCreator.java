package com.squareup.picasso;

import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* renamed from: com.squareup.picasso.h */
class DeferredRequestCreator implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
    @VisibleForTesting

    /* renamed from: a */
    final WeakReference<ImageView> f2502a;
    @VisibleForTesting

    /* renamed from: b */
    C1626e f2503b;

    /* renamed from: c */
    private final RequestCreator f2504c;

    DeferredRequestCreator(RequestCreator zVar, ImageView imageView, C1626e eVar) {
        this.f2504c = zVar;
        this.f2502a = new WeakReference<>(imageView);
        this.f2503b = eVar;
        imageView.addOnAttachStateChangeListener(this);
        if (imageView.getWindowToken() != null) {
            onViewAttachedToWindow(imageView);
        }
    }

    public void onViewAttachedToWindow(View view) {
        view.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public void onViewDetachedFromWindow(View view) {
        view.getViewTreeObserver().removeOnPreDrawListener(this);
    }

    public boolean onPreDraw() {
        ImageView imageView = (ImageView) this.f2502a.get();
        if (imageView != null) {
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                if (width > 0 && height > 0 && !imageView.isLayoutRequested()) {
                    imageView.removeOnAttachStateChangeListener(this);
                    viewTreeObserver.removeOnPreDrawListener(this);
                    this.f2502a.clear();
                    this.f2504c.mo17613a().mo17615a(width, height).mo17618a(imageView, this.f2503b);
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17531a() {
        this.f2504c.mo17620b();
        this.f2503b = null;
        ImageView imageView = (ImageView) this.f2502a.get();
        if (imageView != null) {
            this.f2502a.clear();
            imageView.removeOnAttachStateChangeListener(this);
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this);
            }
        }
    }
}
