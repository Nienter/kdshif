package com.snaperfect.style.daguerre.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.p004v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.snaperfect.style.daguerre.math.CGPoint;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.text.TextInfo;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import java.util.Timer;
import java.util.TimerTask;

public class GridText extends GridFreeLayer {

    /* renamed from: k */
    private float f2297k;

    /* renamed from: l */
    private TextInfo f2298l;

    private class KeyboardClosedReceiver extends ResultReceiver {

        /* renamed from: a */
        C1593a f2302a;

        public KeyboardClosedReceiver() {
            super(null);
        }

        public void onReceiveResult(int i, Bundle bundle) {
            if (this.f2302a != null) {
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        GridText.this.f2285g.mo16915d().runOnUiThread(new Runnable() {
                            public void run() {
                                KeyboardClosedReceiver.this.f2302a.mo17216a();
                                KeyboardClosedReceiver.this.f2302a = null;
                            }
                        });
                    }
                }, 200);
            }
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.widget.GridText$a */
    public interface C1593a {
        /* renamed from: a */
        void mo17216a();
    }

    /* renamed from: com.snaperfect.style.daguerre.widget.GridText$b */
    private class C1594b extends AppCompatEditText {

        /* renamed from: a */
        KeyboardClosedReceiver f2306a;

        C1594b(Context context) {
            super(context);
            this.f2306a = new KeyboardClosedReceiver();
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (isEnabled()) {
                return super.onTouchEvent(motionEvent);
            }
            return false;
        }

        public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() != 4) {
                return super.dispatchKeyEventPreIme(keyEvent);
            }
            GridText.this.f2285g.mo16913c(GridText.this);
            return true;
        }
    }

    public GridText(Context context) {
        this(context, null);
    }

    public GridText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GridText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2286h = true;
    }

    private C1594b getEditText() {
        return (C1594b) this.f2284f;
    }

    /* renamed from: a */
    public View mo17303a(CGSize cGSize) {
        final C1594b bVar = new C1594b(this.f2279a);
        bVar.setPadding(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= 16) {
            bVar.setBackground(null);
        } else {
            bVar.setBackgroundDrawable(null);
        }
        bVar.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                GridText.this.mo17327b(true);
            }
        });
        bVar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                InputMethodManager inputMethodManager = (InputMethodManager) bVar.getContext().getSystemService("input_method");
                if (z) {
                    inputMethodManager.showSoftInput(bVar, 1);
                    return;
                }
                inputMethodManager.hideSoftInputFromWindow(bVar.getWindowToken(), 0, bVar.f2306a);
                GridText.this.mo17327b(false);
                GridText.this.f2285g.mo16920e(GridText.this);
            }
        });
        return bVar;
    }

    /* renamed from: a */
    public boolean mo17307a() {
        return getEditText().isFocused();
    }

    public void setEditing(boolean z) {
        C1594b editText = getEditText();
        if (z) {
            editText.setEnabled(true);
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            return;
        }
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setEnabled(false);
        editText.setClickable(false);
    }

    /* renamed from: a */
    public void mo17304a(float f) {
        this.f2297k *= f;
        this.f2298l.mo17224a(this.f2298l.mo17230b() * f);
        getEditText().setTextSize(this.f2298l.mo17230b());
        mo17327b(false);
    }

    /* renamed from: a */
    public void mo17325a(C1593a aVar) {
        getEditText().f2306a.f2302a = aVar;
        setEditing(false);
    }

    /* renamed from: a */
    public void mo17326a(String str) {
        this.f2297k = ScreenInfo.m3112a(this.f2279a).f2102a;
        this.f2298l = TextInfo.m2978a(this.f2279a);
        super.mo17305a(this.f2279a, mo17324a(this.f2298l, str));
        C1594b editText = getEditText();
        editText.setTypeface(this.f2298l.mo17223a());
        editText.setTextSize(this.f2298l.mo17230b());
        editText.setTextColor(this.f2298l.mo17235e());
        setShadow(0.5f);
    }

    public void setFont(Typeface typeface) {
        this.f2298l.mo17227a(typeface);
        getEditText().setTypeface(typeface);
        mo17327b(false);
    }

    public void setColor(int i) {
        this.f2298l.mo17226a(i);
        getEditText().setTextColor(i);
    }

    public void setAlignment(Layout.Alignment alignment) {
        this.f2298l.mo17228a(alignment);
        if (alignment == Layout.Alignment.ALIGN_NORMAL) {
            getEditText().setGravity(3);
        } else if (alignment == Layout.Alignment.ALIGN_CENTER) {
            getEditText().setGravity(17);
        } else if (alignment == Layout.Alignment.ALIGN_OPPOSITE) {
            getEditText().setGravity(5);
        }
    }

    public float getShadow() {
        return this.f2298l.mo17237g() / ((getEditText().getTextSize() * 0.05f) * 4.0f);
    }

    public void setShadow(float f) {
        if (f == 0.0f) {
            getEditText().setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.f2298l.mo17231b(0.0f);
            this.f2298l.mo17225a(0.0f, 0.0f);
            return;
        }
        float textSize = getEditText().getTextSize() * 0.05f;
        float f2 = 4.0f * textSize * f;
        getEditText().setShadowLayer(f2, textSize, textSize, Integer.MIN_VALUE);
        this.f2298l.mo17231b(f2);
        this.f2298l.mo17225a(textSize, textSize);
    }

    public float getContentAlpha() {
        return getEditText().getAlpha();
    }

    public void setContentAlpha(float f) {
        getEditText().setAlpha(f);
        this.f2298l.mo17233c(f);
    }

    public int getColor() {
        return getEditText().getCurrentTextColor();
    }

    /* renamed from: d */
    public boolean mo17328d() {
        return getEditText().getText().length() == 0;
    }

    /* renamed from: b */
    public void mo17327b(boolean z) {
        CGSize b = mo17309b(mo17324a(this.f2298l, getEditText().getText()));
        b.f2102a += this.f2298l.mo17232c() / 4.0f;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.width = (int) b.f2102a;
        layoutParams.height = (int) b.f2103b;
        setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    public CGSize mo17324a(TextInfo cVar, CharSequence charSequence) {
        float f = 0.0f;
        StaticLayout staticLayout = new StaticLayout(charSequence, cVar.mo17245o(), (int) this.f2297k, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        for (int i = 0; i < staticLayout.getLineCount(); i++) {
            float lineWidth = staticLayout.getLineWidth(i);
            if (lineWidth > f) {
                f = lineWidth;
            }
        }
        return new CGSize(f, (float) staticLayout.getHeight());
    }

    public TextInfo getTextInfo() {
        return this.f2298l;
    }

    public CGPoint getCenter() {
        return new CGPoint(getX() + ((float) (getWidth() / 2)), getY() + ((float) (getHeight() / 2)));
    }

    public CGSize getSize() {
        return new CGSize((float) getEditText().getWidth(), (float) getEditText().getHeight());
    }

    public String getText() {
        return getEditText().getText().toString();
    }
}
