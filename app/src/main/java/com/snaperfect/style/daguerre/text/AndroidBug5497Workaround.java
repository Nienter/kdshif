package com.snaperfect.style.daguerre.text;

import android.app.Activity;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;

/* renamed from: com.snaperfect.style.daguerre.text.a */
public class AndroidBug5497Workaround {

    /* renamed from: a */
    private static int f2176a;

    /* renamed from: b */
    private View f2177b;

    /* renamed from: c */
    private int f2178c;

    /* renamed from: d */
    private FrameLayout.LayoutParams f2179d = ((FrameLayout.LayoutParams) this.f2177b.getLayoutParams());

    /* renamed from: a */
    public static void m2973a(Activity activity) {
        new AndroidBug5497Workaround(activity);
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        f2176a = window.findViewById(16908290).getTop() - rect.top;
    }

    private AndroidBug5497Workaround(Activity activity) {
        this.f2177b = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
        this.f2177b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                Log.i("Bug5497", "start");
                AndroidBug5497Workaround.this.m2972a();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2972a() {
        int b = m2975b();
        if (b != this.f2178c) {
            int height = this.f2177b.getRootView().getHeight();
            Log.i("Bug5497", "here: " + height);
            int i = height - b;
            if (i > height / 4) {
                this.f2179d.height = height - i;
            } else {
                this.f2179d.height = b;
            }
            this.f2177b.requestLayout();
            this.f2178c = b;
        }
    }

    /* renamed from: b */
    private int m2975b() {
        Rect rect = new Rect();
        this.f2177b.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }
}
