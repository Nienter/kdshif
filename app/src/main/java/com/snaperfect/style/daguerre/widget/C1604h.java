package com.snaperfect.style.daguerre.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.snaperfect.style.daguerre.math.CGFloat;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.p032b.MoveGestureDetector;
import com.snaperfect.style.daguerre.utils.Localize;

/* renamed from: com.snaperfect.style.daguerre.widget.h */
/* compiled from: GridFreeLayer */
class C1604h implements View.OnTouchListener, MoveGestureDetector.C1519a {

    /* renamed from: a */
    private GridFreeLayer f2407a;

    /* renamed from: b */
    private MoveGestureDetector f2408b;

    /* renamed from: c */
    private C1605i f2409c = new C1605i(0.08726646f, "Rotate");

    /* renamed from: d */
    private CGPoint f2410d;

    C1604h(Context context, GridFreeLayer gridFreeLayer) {
        this.f2408b = new MoveGestureDetector(context, 1, true, this);
        float f = context.getResources().getDisplayMetrics().density;
        this.f2407a = gridFreeLayer;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1) {
            this.f2408b.mo17012a(motionEvent);
        }
        return true;
    }

    /* renamed from: b */
    public boolean mo17026b(MoveGestureDetector bVar) {
        this.f2409c.mo17453a();
        this.f2410d = new CGPoint(bVar.mo17022e() - (this.f2407a.getX() + ((float) (this.f2407a.getWidth() / 2))), bVar.mo17023f() - (this.f2407a.getY() + ((float) (this.f2407a.getHeight() / 2))));
        return true;
    }

    /* renamed from: a */
    public boolean mo17025a(MoveGestureDetector bVar) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f2407a.getLayoutParams();
        CGPoint cGPoint = new CGPoint(bVar.mo17022e() - (this.f2407a.getX() + ((float) (this.f2407a.getWidth() / 2))), bVar.mo17023f() - (this.f2407a.getY() + ((float) (this.f2407a.getHeight() / 2))));
        float length = cGPoint.length() / this.f2410d.length();
        this.f2410d = cGPoint;
        float a = CGFloat.m2894a((float) Math.toRadians((double) this.f2407a.getRotation())) * -1.0f;
        float a2 = this.f2409c.mo17452a(a, (float) Math.asin((double) (this.f2410d.mo17129c(cGPoint) / (this.f2410d.length() * cGPoint.length()))));
        Log.d("Scale Button", Localize.m3098a("curr:%g scale:%g, rotate:%g", Float.valueOf(a), Float.valueOf(length), Float.valueOf(a2)));
        this.f2407a.setRotation(((float) Math.toDegrees((double) a2)) + this.f2407a.getRotation());
        ViewGroup.LayoutParams layoutParams2 = this.f2407a.getLayoutParams();
        layoutParams2.width = (int) (((float) layoutParams2.width) * length);
        ViewGroup.LayoutParams layoutParams3 = this.f2407a.getLayoutParams();
        layoutParams3.height = (int) (((float) layoutParams3.height) * length);
        this.f2407a.setLayoutParams(this.f2407a.getLayoutParams());
        this.f2407a.mo17304a(length);
        return true;
    }

    /* renamed from: c */
    public void mo17027c(MoveGestureDetector bVar) {
        this.f2409c.mo17453a();
    }
}
