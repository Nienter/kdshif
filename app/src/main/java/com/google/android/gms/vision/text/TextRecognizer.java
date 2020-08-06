package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.zzbhd;
import com.google.android.gms.internal.zzbhf;
import com.google.android.gms.internal.zzbhk;
import com.google.android.gms.internal.zzbhm;
import com.google.android.gms.internal.zzbhq;
import com.google.android.gms.internal.zzbhr;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public final class TextRecognizer extends Detector<TextBlock> {
    private final zzbhq zzbNy;

    public static class Builder {
        private Context mContext;
        private zzbhr zzbNz = new zzbhr();

        public Builder(Context context) {
            this.mContext = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzbhq(this.mContext, this.zzbNz));
        }
    }

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzbhq zzbhq) {
        this.zzbNy = zzbhq;
    }

    private Bitmap zza(ByteBuffer byteBuffer, int i, int i2, int i3) {
        byte[] bArr;
        if (!byteBuffer.hasArray() || byteBuffer.arrayOffset() != 0) {
            bArr = new byte[byteBuffer.capacity()];
            byteBuffer.get(bArr);
        } else {
            bArr = byteBuffer.array();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new YuvImage(bArr, i, i2, i3, null).compressToJpeg(new Rect(0, 0, i2, i3), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    private Rect zza(Rect rect, int i, int i2, zzbhd zzbhd) {
        switch (zzbhd.rotation) {
            case 1:
                return new Rect(i2 - rect.bottom, rect.left, i2 - rect.top, rect.right);
            case 2:
                return new Rect(i - rect.right, i2 - rect.bottom, i - rect.left, i2 - rect.top);
            case 3:
                return new Rect(rect.top, i - rect.right, rect.bottom, i - rect.left);
            default:
                return rect;
        }
    }

    private SparseArray<TextBlock> zza(zzbhk[] zzbhkArr) {
        SparseArray sparseArray = new SparseArray();
        for (zzbhk zzbhk : zzbhkArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzbhk.zzbNJ);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzbhk.zzbNJ, sparseArray2);
            }
            sparseArray2.append(zzbhk.zzbNK, zzbhk);
        }
        SparseArray<TextBlock> sparseArray3 = new SparseArray<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray3.append(sparseArray.keyAt(i), new TextBlock((SparseArray) sparseArray.valueAt(i)));
        }
        return sparseArray3;
    }

    public SparseArray<TextBlock> detect(Frame frame) {
        return zza(frame, new zzbhm(1, new Rect()));
    }

    public boolean isOperational() {
        return this.zzbNy.isOperational();
    }

    public void release() {
        super.release();
        this.zzbNy.zzSp();
    }

    public SparseArray<TextBlock> zza(Frame frame, zzbhm zzbhm) {
        Bitmap zza;
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        zzbhd zzc = zzbhd.zzc(frame);
        if (frame.getBitmap() != null) {
            zza = frame.getBitmap();
        } else {
            zza = zza(frame.getGrayscaleImageData(), frame.getMetadata().getFormat(), zzc.width, zzc.height);
        }
        Bitmap zzb = zzbhf.zzb(zza, zzc);
        if (!zzbhm.zzbNL.isEmpty()) {
            zzbhm.zzbNL.set(zza(zzbhm.zzbNL, frame.getMetadata().getWidth(), frame.getMetadata().getHeight(), zzc));
        }
        zzc.rotation = 0;
        return zza(this.zzbNy.zza(zzb, zzc, zzbhm));
    }
}
