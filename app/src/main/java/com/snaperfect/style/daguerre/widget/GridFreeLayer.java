package com.snaperfect.style.daguerre.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.utils.DisplayUtil;

public abstract class GridFreeLayer extends FrameLayout {

    /* renamed from: a */
    protected Context f2279a;

    /* renamed from: b */
    protected ImageView f2280b;

    /* renamed from: c */
    protected ImageView f2281c;

    /* renamed from: d */
    protected ImageView f2282d;

    /* renamed from: e */
    protected BorderView f2283e;

    /* renamed from: f */
    protected View f2284f;

    /* renamed from: g */
    protected C1588a f2285g;

    /* renamed from: h */
    protected boolean f2286h;

    /* renamed from: i */
    protected boolean f2287i;

    /* renamed from: j */
    protected boolean f2288j;

    protected class BorderView extends View {

        /* renamed from: b */
        private Rect f2295b;

        /* renamed from: c */
        private Paint f2296c;

        public BorderView(GridFreeLayer gridFreeLayer, Context context) {
            this(gridFreeLayer, context, null);
        }

        public BorderView(GridFreeLayer gridFreeLayer, Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public BorderView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.f2295b = new Rect();
            setEnabled(false);
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            this.f2295b.left = i - layoutParams.leftMargin;
            this.f2295b.top = i2 - layoutParams.topMargin;
            this.f2295b.right = i3 - layoutParams.rightMargin;
            this.f2295b.bottom = i4 - layoutParams.bottomMargin;
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(this.f2295b, this.f2296c);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        /* access modifiers changed from: private */
        public void setupBorderPaint(float f) {
            Paint paint = new Paint(7);
            paint.setStrokeWidth(f);
            paint.setColor(getResources().getColor(R.color.edit_border));
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setPathEffect(new CornerPathEffect(f));
            paint.setStyle(Paint.Style.STROKE);
            this.f2296c = paint;
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.widget.GridFreeLayer$a */
    public interface C1588a {
        /* renamed from: a */
        void mo16902a(GridFreeLayer gridFreeLayer);

        /* renamed from: a */
        boolean mo16905a(GridFreeLayer gridFreeLayer, Object obj);

        /* renamed from: b */
        void mo16909b(GridFreeLayer gridFreeLayer);

        /* renamed from: c */
        void mo16913c(GridFreeLayer gridFreeLayer);

        /* renamed from: d */
        Activity mo16915d();

        /* renamed from: d */
        void mo16917d(GridFreeLayer gridFreeLayer);

        /* renamed from: e */
        void mo16920e(GridFreeLayer gridFreeLayer);
    }

    /* renamed from: a */
    public abstract View mo17303a(CGSize cGSize);

    /* renamed from: a */
    public abstract void mo17304a(float f);

    /* renamed from: a */
    public abstract boolean mo17307a();

    public abstract void setEditing(boolean z);

    public GridFreeLayer(Context context) {
        this(context, null);
    }

    public GridFreeLayer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GridFreeLayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2279a = getContext();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17305a(Context context, CGSize cGSize) {
        if (!this.f2288j) {
            this.f2279a = context;
            this.f2283e = new BorderView(this, context);
            this.f2282d = new ImageView(context);
            this.f2280b = new ImageView(context);
            this.f2281c = new ImageView(context);
            this.f2282d.setImageResource(R.mipmap.edit_icon_free_scale);
            this.f2280b.setImageResource(R.mipmap.edit_icon_free_delete);
            this.f2281c.setImageResource(R.mipmap.edit_icon_free_edit);
            setTag("DraggableViewGroup");
            this.f2283e.setTag("iv_border");
            this.f2282d.setTag("iv_scale");
            this.f2280b.setTag("iv_delete");
            this.f2281c.setTag("iv_flip");
            float a = DisplayUtil.m3087a(24.0f);
            int round = Math.round(a);
            int round2 = Math.round(a / 2.0f);
            int round3 = Math.round(a * 0.75f);
            CGSize a2 = mo17309b(cGSize).mo17151a();
            this.f2283e.setupBorderPaint(DisplayUtil.m3087a(2.0f));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) a2.f2102a, (int) a2.f2103b);
            layoutParams.gravity = 17;
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
            layoutParams2.setMargins(round3, round3, round3, round3);
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
            layoutParams3.setMargins(round2, round2, round2, round2);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(round, round);
            layoutParams4.gravity = 51;
            FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(round, round);
            layoutParams5.gravity = 53;
            FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(round, round);
            layoutParams6.gravity = 85;
            setLayoutParams(layoutParams);
            this.f2284f = mo17303a(cGSize);
            addView(this.f2284f, layoutParams2);
            addView(this.f2283e, layoutParams3);
            addView(this.f2280b, layoutParams4);
            addView(this.f2281c, layoutParams5);
            addView(this.f2282d, layoutParams6);
            this.f2281c.setVisibility(8);
            setOnTouchListener(new GridFreeLayerTouchListener(this.f2279a, this));
            this.f2280b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new AlertDialog.Builder(GridFreeLayer.this.f2285g.mo16915d()).setTitle(GridFreeLayer.this.f2286h ? R.string.dialog_title_remove_text_box : R.string.dialog_title_remove_sticker_box).setNegativeButton(R.string.dialog_button_cancel, null).setPositiveButton(R.string.dialog_button_confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            GridFreeLayer.this.f2285g.mo16909b(this);
                        }
                    }).create().show();
                }
            });
            this.f2281c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (GridFreeLayer.this.f2286h && !GridFreeLayer.this.mo17307a()) {
                        GridFreeLayer.this.f2285g.mo16917d(this);
                        GridFreeLayer.this.f2281c.setVisibility(8);
                        GridFreeLayer.this.setEditing(true);
                    }
                }
            });
            this.f2282d.setOnTouchListener(new C1604h(this.f2279a, this));
            this.f2288j = true;
        }
    }

    public void setLayerCallback(C1588a aVar) {
        this.f2285g = aVar;
    }

    public void setEditable(boolean z) {
        this.f2286h = z;
        this.f2281c.setVisibility(z ? 8 : 0);
    }

    public boolean getSelected() {
        return this.f2287i;
    }

    public void setSelected(boolean z) {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        this.f2287i = z;
        ImageView imageView = this.f2280b;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        ImageView imageView2 = this.f2282d;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView2.setVisibility(i2);
        ImageView imageView3 = this.f2281c;
        if (!z || mo17307a()) {
            i3 = 8;
        } else {
            i3 = 0;
        }
        imageView3.setVisibility(i3);
        BorderView borderView = this.f2283e;
        if (!z) {
            i4 = 8;
        }
        borderView.setVisibility(i4);
    }

    /* renamed from: a */
    public void mo17306a(boolean z) {
        setSelected(this.f2287i);
        this.f2285g.mo16902a(this);
    }

    /* renamed from: b */
    public void mo17310b() {
        if (this.f2286h && !mo17307a()) {
            this.f2281c.performClick();
        }
    }

    public float[] getNearestOffsets() {
        return new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    /* renamed from: c */
    public void mo17311c() {
    }

    /* renamed from: a */
    public boolean mo17308a(Object obj) {
        return this.f2285g.mo16905a(this, obj);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public CGSize mo17309b(CGSize cGSize) {
        float a = ((float) DisplayUtil.m3088a(this.f2279a, 24.0f)) * 1.5f;
        return new CGSize(cGSize.f2102a + a, a + cGSize.f2103b);
    }
}
