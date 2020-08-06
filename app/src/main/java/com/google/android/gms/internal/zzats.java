package com.google.android.gms.internal;

abstract class zzats extends zzatr {
    private boolean zzacO;

    zzats(zzatp zzatp) {
        super(zzatp);
        this.zzbpw.zzb(this);
    }

    public final void initialize() {
        if (this.zzacO) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzmr();
        this.zzbpw.zzLK();
        this.zzacO = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.zzacO;
    }

    /* access modifiers changed from: protected */
    public abstract void zzmr();

    /* access modifiers changed from: protected */
    public void zznA() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
