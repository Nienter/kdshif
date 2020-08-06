package com.snaperfect.style.daguerre.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.snaperfect.style.daguerre.p032b.MoveGestureDetector;

/* renamed from: com.snaperfect.style.daguerre.widget.b */
public class GridFreeLayerTouchListener implements View.OnTouchListener, MoveGestureDetector.C1519a {

    /* renamed from: a */
    private GridFreeLayer f2356a;

    /* renamed from: b */
    private MoveGestureDetector f2357b;

    /* renamed from: c */
    private C1602e f2358c;

    public GridFreeLayerTouchListener(Context context, GridFreeLayer gridFreeLayer) {
        this.f2357b = new MoveGestureDetector(context, 1, true, this);
        this.f2358c = new C1602e(context.getResources().getDisplayMetrics().density * 10.0f, "Move", 6);
        this.f2356a = gridFreeLayer;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1) {
            this.f2357b.mo17012a(motionEvent);
        }
        return true;
    }

    /* renamed from: b */
    public boolean mo17026b(MoveGestureDetector bVar) {
        this.f2358c.mo17415a();
        return this.f2356a.mo17308a((Object) this.f2357b);
    }

    /* renamed from: a */
    public boolean mo17025a(MoveGestureDetector bVar) {
        if (!this.f2356a.getSelected()) {
            this.f2356a.mo17306a(true);
        }
        float[] fArr = {bVar.mo17024g().x, bVar.mo17024g().y};
        float[] a = this.f2358c.mo17416a(this.f2356a.getNearestOffsets(), fArr);
        this.f2356a.setX(this.f2356a.getX() + a[0]);
        this.f2356a.setY(a[1] + this.f2356a.getY());
        return true;
    }

    /* renamed from: c */
    public void mo17027c(MoveGestureDetector bVar) {
        if (bVar.mo17021d()) {
            this.f2358c.mo17415a();
            this.f2356a.mo17311c();
            return;
        }
        this.f2356a.mo17306a(!this.f2356a.getSelected());
    }
}
