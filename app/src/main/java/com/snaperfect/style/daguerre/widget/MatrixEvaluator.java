package com.snaperfect.style.daguerre.widget;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.widget.ImageView;

/* renamed from: com.snaperfect.style.daguerre.widget.d */
public class MatrixEvaluator implements TypeEvaluator<Matrix> {

    /* renamed from: a */
    public static TypeEvaluator<Matrix> f2367a = new TypeEvaluator<Matrix>() {
        /* renamed from: a */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            return null;
        }
    };

    /* renamed from: b */
    public static final Property<ImageView, Matrix> f2368b = new Property<ImageView, Matrix>(Matrix.class, "animatedTransform") {
        /* renamed from: a */
        public void set(ImageView imageView, Matrix matrix) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                if (matrix == null) {
                    drawable.setBounds(0, 0, imageView.getWidth(), imageView.getHeight());
                } else {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    if (imageView.getImageMatrix() == null) {
                        imageView.setImageMatrix(new Matrix());
                    }
                    imageView.setImageMatrix(matrix);
                }
                imageView.invalidate();
            }
        }

        /* renamed from: a */
        public Matrix get(ImageView imageView) {
            return null;
        }
    };

    /* renamed from: f */
    private static final String f2369f = MatrixEvaluator.class.getSimpleName();

    /* renamed from: c */
    float[] f2370c = new float[9];

    /* renamed from: d */
    float[] f2371d = new float[9];

    /* renamed from: e */
    Matrix f2372e = new Matrix();

    /* renamed from: a */
    public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f2370c);
        matrix2.getValues(this.f2371d);
        for (int i = 0; i < 9; i++) {
            this.f2371d[i] = ((this.f2371d[i] - this.f2370c[i]) * f) + this.f2370c[i];
        }
        this.f2372e.setValues(this.f2371d);
        return this.f2372e;
    }
}
