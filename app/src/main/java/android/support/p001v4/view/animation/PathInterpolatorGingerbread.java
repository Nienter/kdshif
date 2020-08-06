package android.support.p001v4.view.animation;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RequiresApi;
import android.view.animation.Interpolator;

@TargetApi(9)
@RequiresApi(9)
/* renamed from: android.support.v4.view.animation.PathInterpolatorGingerbread */
class PathInterpolatorGingerbread implements Interpolator {
    private static final float PRECISION = 0.002f;

    /* renamed from: mX */
    private final float[] f82mX;

    /* renamed from: mY */
    private final float[] f83mY;

    public PathInterpolatorGingerbread(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int i = ((int) (length / PRECISION)) + 1;
        this.f82mX = new float[i];
        this.f83mY = new float[i];
        float[] fArr = new float[2];
        for (int i2 = 0; i2 < i; i2++) {
            pathMeasure.getPosTan((((float) i2) * length) / ((float) (i - 1)), fArr, null);
            this.f82mX[i2] = fArr[0];
            this.f83mY[i2] = fArr[1];
        }
    }

    public PathInterpolatorGingerbread(float f, float f2) {
        this(createQuad(f, f2));
    }

    public PathInterpolatorGingerbread(float f, float f2, float f3, float f4) {
        this(createCubic(f, f2, f3, f4));
    }

    public float getInterpolation(float f) {
        int i;
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int i2 = 0;
        int length = this.f82mX.length - 1;
        while (length - i2 > 1) {
            int i3 = (i2 + length) / 2;
            if (f < this.f82mX[i3]) {
                i = i2;
            } else {
                int i4 = length;
                i = i3;
                i3 = i4;
            }
            i2 = i;
            length = i3;
        }
        float f2 = this.f82mX[length] - this.f82mX[i2];
        if (f2 == 0.0f) {
            return this.f83mY[i2];
        }
        float f3 = (f - this.f82mX[i2]) / f2;
        float f4 = this.f83mY[i2];
        return (f3 * (this.f83mY[length] - f4)) + f4;
    }

    private static Path createQuad(float f, float f2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f, f2, 1.0f, 1.0f);
        return path;
    }

    private static Path createCubic(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        return path;
    }
}
