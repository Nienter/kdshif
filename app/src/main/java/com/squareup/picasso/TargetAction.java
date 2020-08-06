package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;

/* renamed from: com.squareup.picasso.af */
final class TargetAction extends Action<Target> {
    TargetAction(Picasso vVar, Target aeVar, C1645y yVar, int i, int i2, Drawable drawable, String str, Object obj, int i3) {
        super(vVar, aeVar, yVar, i, i2, i3, drawable, str, obj, false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17463a(Bitmap bitmap, Picasso.C1640d dVar) {
        if (bitmap == null) {
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[]{this}));
        }
        Target aeVar = (Target) mo17466c();
        if (aeVar != null) {
            aeVar.mo17280a(bitmap, dVar);
            if (bitmap.isRecycled()) {
                throw new IllegalStateException("Target callback must not recycle bitmap!");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17464a(Exception exc) {
        Target aeVar = (Target) mo17466c();
        if (aeVar == null) {
            return;
        }
        if (this.f2420g != 0) {
            aeVar.mo17283a(exc, this.f2414a.f2551c.getResources().getDrawable(this.f2420g));
        } else {
            aeVar.mo17283a(exc, this.f2421h);
        }
    }
}
