package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class zzabu extends ImageView {
    private Uri zzaDu;
    private int zzaDv;

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void zzcK(int i) {
        this.zzaDv = i;
    }

    public void zzr(Uri uri) {
        this.zzaDu = uri;
    }

    public int zzwO() {
        return this.zzaDv;
    }
}
