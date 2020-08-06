package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;

public final class zzabt extends Drawable implements Drawable.Callback {
    private int mFrom;
    private boolean zzaCZ;
    private int zzaDe;
    private int zzaDf;
    private int zzaDg;
    private int zzaDh;
    private int zzaDi;
    private boolean zzaDj;
    private zzb zzaDk;
    private Drawable zzaDl;
    private Drawable zzaDm;
    private boolean zzaDn;
    private boolean zzaDo;
    private boolean zzaDp;
    private int zzaDq;
    private long zzaed;

    private static final class zza extends Drawable {
        /* access modifiers changed from: private */
        public static final zza zzaDr = new zza();
        private static final C1705zza zzaDs = new C1705zza();

        /* renamed from: com.google.android.gms.internal.zzabt$zza$zza  reason: collision with other inner class name */
        private static final class C1705zza extends Drawable.ConstantState {
            private C1705zza() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return zza.zzaDr;
            }
        }

        private zza() {
        }

        public void draw(Canvas canvas) {
        }

        public Drawable.ConstantState getConstantState() {
            return zzaDs;
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    static final class zzb extends Drawable.ConstantState {
        int mChangingConfigurations;
        int zzaDt;

        zzb(zzb zzb) {
            if (zzb != null) {
                this.mChangingConfigurations = zzb.mChangingConfigurations;
                this.zzaDt = zzb.zzaDt;
            }
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Drawable newDrawable() {
            return new zzabt(this);
        }
    }

    public zzabt(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zza.zzaDr : drawable;
        this.zzaDl = drawable;
        drawable.setCallback(this);
        this.zzaDk.zzaDt |= drawable.getChangingConfigurations();
        drawable2 = drawable2 == null ? zza.zzaDr : drawable2;
        this.zzaDm = drawable2;
        drawable2.setCallback(this);
        this.zzaDk.zzaDt |= drawable2.getChangingConfigurations();
    }

    zzabt(zzb zzb2) {
        this.zzaDe = 0;
        this.zzaDg = 255;
        this.zzaDi = 0;
        this.zzaCZ = true;
        this.zzaDk = new zzb(zzb2);
    }

    public boolean canConstantState() {
        if (!this.zzaDn) {
            this.zzaDo = (this.zzaDl.getConstantState() == null || this.zzaDm.getConstantState() == null) ? false : true;
            this.zzaDn = true;
        }
        return this.zzaDo;
    }

    public void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.zzaDe) {
            case 1:
                this.zzaed = SystemClock.uptimeMillis();
                this.zzaDe = 2;
                break;
            case 2:
                if (this.zzaed >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zzaed)) / ((float) this.zzaDh);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.zzaDe = 0;
                    }
                    this.zzaDi = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.zzaDf + 0))) + 0.0f);
                    break;
                }
                break;
        }
        z2 = z;
        int i = this.zzaDi;
        boolean z3 = this.zzaCZ;
        Drawable drawable = this.zzaDl;
        Drawable drawable2 = this.zzaDm;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zzaDg) {
                drawable2.setAlpha(this.zzaDg);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.zzaDg - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.zzaDg);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zzaDg);
        }
        invalidateSelf();
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzaDk.mChangingConfigurations | this.zzaDk.zzaDt;
    }

    public Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zzaDk.mChangingConfigurations = getChangingConfigurations();
        return this.zzaDk;
    }

    public int getIntrinsicHeight() {
        return Math.max(this.zzaDl.getIntrinsicHeight(), this.zzaDm.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(this.zzaDl.getIntrinsicWidth(), this.zzaDm.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!this.zzaDp) {
            this.zzaDq = Drawable.resolveOpacity(this.zzaDl.getOpacity(), this.zzaDm.getOpacity());
            this.zzaDp = true;
        }
        return this.zzaDq;
    }

    @TargetApi(11)
    public void invalidateDrawable(Drawable drawable) {
        if (zzs.zzyx()) {
            Drawable.Callback callback = getCallback();
            if (callback != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    public Drawable mutate() {
        if (!this.zzaDj && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zzaDl.mutate();
            this.zzaDm.mutate();
            this.zzaDj = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.zzaDl.setBounds(rect);
        this.zzaDm.setBounds(rect);
    }

    @TargetApi(11)
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (zzs.zzyx()) {
            Drawable.Callback callback = getCallback();
            if (callback != null) {
                callback.scheduleDrawable(this, runnable, j);
            }
        }
    }

    public void setAlpha(int i) {
        if (this.zzaDi == this.zzaDg) {
            this.zzaDi = i;
        }
        this.zzaDg = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.zzaDl.setColorFilter(colorFilter);
        this.zzaDm.setColorFilter(colorFilter);
    }

    public void startTransition(int i) {
        this.mFrom = 0;
        this.zzaDf = this.zzaDg;
        this.zzaDi = 0;
        this.zzaDh = i;
        this.zzaDe = 1;
        invalidateSelf();
    }

    @TargetApi(11)
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (zzs.zzyx()) {
            Drawable.Callback callback = getCallback();
            if (callback != null) {
                callback.unscheduleDrawable(this, runnable);
            }
        }
    }

    public Drawable zzwM() {
        return this.zzaDm;
    }
}
