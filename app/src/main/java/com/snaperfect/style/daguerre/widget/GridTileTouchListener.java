package com.snaperfect.style.daguerre.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.p028a.p029a.MobclickAgent;
import com.snaperfect.style.daguerre.math.CGFloat;
import com.snaperfect.style.daguerre.math.CGMatrix;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.p032b.MoveGestureDetector;
import com.snaperfect.style.daguerre.p032b.RotateGestureDetector;

/* renamed from: com.snaperfect.style.daguerre.widget.c */
public class GridTileTouchListener implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, MoveGestureDetector.C1519a, RotateGestureDetector.C1520a {

    /* renamed from: a */
    private GridTile f2359a;

    /* renamed from: b */
    private MoveGestureDetector f2360b;

    /* renamed from: c */
    private ScaleGestureDetector f2361c;

    /* renamed from: d */
    private RotateGestureDetector f2362d;

    /* renamed from: e */
    private C1602e f2363e;

    /* renamed from: f */
    private C1605i f2364f = new C1605i(0.03f, "Scale");

    /* renamed from: g */
    private C1605i f2365g = new C1605i(0.08726646f, "Rotate");

    /* renamed from: h */
    private CGPoint f2366h;

    public GridTileTouchListener(Context context, GridTile gridTile) {
        this.f2360b = new MoveGestureDetector(context, 1, false, this);
        this.f2361c = new ScaleGestureDetector(context, this);
        this.f2362d = new RotateGestureDetector(context, this);
        this.f2363e = new C1602e(context.getResources().getDisplayMetrics().density * 10.0f, "Move", 6);
        this.f2359a = gridTile;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1) {
            this.f2360b.mo17012a(motionEvent);
        } else {
            this.f2362d.mo17012a(motionEvent);
            this.f2361c.onTouchEvent(motionEvent);
        }
        return true;
    }

    /* renamed from: b */
    public boolean mo17026b(MoveGestureDetector bVar) {
        this.f2363e.mo17415a();
        return this.f2359a.mo17355a((Object) this.f2360b);
    }

    /* renamed from: a */
    public boolean mo17025a(MoveGestureDetector bVar) {
        float[] fArr = {bVar.mo17024g().x, bVar.mo17024g().y};
        float[] a = this.f2363e.mo17416a(this.f2359a.getNearestOffsets(), fArr);
        Matrix matrix = new Matrix(this.f2359a.getImageMatrix());
        matrix.postTranslate(a[0], a[1]);
        this.f2359a.setImageMatrix(matrix);
        return true;
    }

    /* renamed from: c */
    public void mo17027c(MoveGestureDetector bVar) {
        this.f2363e.mo17415a();
        if (bVar.mo17021d()) {
            this.f2359a.mo17356b();
            MobclickAgent.m2411a(this.f2359a.getContext(), "Move");
            return;
        }
        this.f2359a.mo17354a(false);
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        this.f2364f.mo17453a();
        return this.f2359a.mo17355a((Object) this.f2361c);
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
            return false;
        }
        Matrix matrix = new Matrix(this.f2359a.getImageMatrix());
        float exp = (float) Math.exp((double) this.f2364f.mo17452a(this.f2359a.mo17350a((float) Math.log((double) CGMatrix.m2897a(matrix))), (float) Math.log((double) scaleFactor)));
        this.f2366h = new CGPoint(scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        CGPoint a = CGPoint.m2831a(this.f2366h, 1.0f - exp);
        matrix.postScale(exp, exp);
        matrix.postTranslate(a.x, a.y);
        this.f2359a.setImageMatrix(matrix);
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        boolean z;
        float f;
        Matrix matrix = new Matrix(this.f2359a.getImageMatrix());
        float a = CGMatrix.m2897a(matrix);
        if (a > 1.5f) {
            f = 1.5f / a;
            z = true;
        } else if (a < 0.1f) {
            f = 0.1f / a;
            z = true;
        } else {
            z = false;
            f = 1.0f;
        }
        if (z && this.f2366h != null) {
            CGPoint a2 = CGPoint.m2831a(this.f2366h, 1.0f - f);
            matrix.postScale(f, f);
            matrix.postTranslate(a2.x, a2.y);
            ObjectAnimator ofObject = ObjectAnimator.ofObject(this.f2359a, MatrixEvaluator.f2368b, new MatrixEvaluator(), new Matrix[]{this.f2359a.getImageMatrix(), matrix});
            ofObject.setDuration(300);
            ofObject.start();
        }
        MobclickAgent.m2411a(this.f2359a.getContext(), "Scale");
    }

    /* renamed from: b */
    public boolean mo17030b(RotateGestureDetector cVar) {
        this.f2365g.mo17453a();
        return this.f2359a.mo17355a((Object) this.f2362d);
    }

    /* renamed from: a */
    public boolean mo17029a(RotateGestureDetector cVar) {
        float d = cVar.mo17028d();
        Matrix matrix = new Matrix(this.f2359a.getImageMatrix());
        float a = this.f2365g.mo17452a(CGFloat.m2894a(CGMatrix.m2898b(matrix)), d);
        CGPoint cGPoint = new CGPoint(cVar.mo17032e(), cVar.mo17033f());
        CGPoint b = new CGPoint(cGPoint.x, cGPoint.y).mo17130c(a).mo17127b(cGPoint);
        matrix.postRotate((float) (((double) a) * 57.29577951308232d));
        matrix.postTranslate(b.x, b.y);
        this.f2359a.setImageMatrix(matrix);
        return true;
    }

    /* renamed from: c */
    public void mo17031c(RotateGestureDetector cVar) {
        MobclickAgent.m2411a(this.f2359a.getContext(), "Rotate");
    }
}
