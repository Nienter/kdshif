package android.support.p004v7.widget;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.RequiresApi;
import android.support.p004v7.widget.RoundRectDrawableWithShadow;

@TargetApi(17)
@RequiresApi(17)
/* renamed from: android.support.v7.widget.CardViewJellybeanMr1 */
class CardViewJellybeanMr1 extends CardViewGingerbread {
    CardViewJellybeanMr1() {
    }

    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() {
            public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
                canvas.drawRoundRect(rectF, f, f, paint);
            }
        };
    }
}
