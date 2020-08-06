package com.snaperfect.style.daguerre.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class RoundImageView extends AppCompatImageView {

    /* renamed from: a */
    public int f2333a = -1;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageBitmap(Bitmap bitmap) {
        RoundedBitmapDrawable fVar = new RoundedBitmapDrawable(getResources(), bitmap);
        fVar.mo17418a(this.f2333a < 0 ? (float) (bitmap.getWidth() / 2) : (float) this.f2333a);
        fVar.mo17420a(true);
        setImageDrawable(fVar);
    }

    public void setCornerRadius(int i) {
        this.f2333a = i;
    }
}
