package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzbhd;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

public final class BarcodeDetector extends Detector<Barcode> {
    private final zzbgq zzbMJ;

    public static class Builder {
        private Context mContext;
        private zzbgo zzbMK = new zzbgo();

        public Builder(Context context) {
            this.mContext = context;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new zzbgq(this.mContext, this.zzbMK));
        }

        public Builder setBarcodeFormats(int i) {
            this.zzbMK.zzbML = i;
            return this;
        }
    }

    private BarcodeDetector() {
        throw new IllegalStateException("Default constructor called");
    }

    private BarcodeDetector(zzbgq zzbgq) {
        this.zzbMJ = zzbgq;
    }

    public SparseArray<Barcode> detect(Frame frame) {
        Barcode[] barcodeArr;
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        zzbhd zzc = zzbhd.zzc(frame);
        if (frame.getBitmap() != null) {
            barcodeArr = this.zzbMJ.zza(frame.getBitmap(), zzc);
            if (barcodeArr == null) {
                throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
        } else {
            barcodeArr = this.zzbMJ.zza(frame.getGrayscaleImageData(), zzc);
        }
        SparseArray<Barcode> sparseArray = new SparseArray<>(barcodeArr.length);
        for (Barcode barcode : barcodeArr) {
            sparseArray.append(barcode.rawValue.hashCode(), barcode);
        }
        return sparseArray;
    }

    public boolean isOperational() {
        return this.zzbMJ.isOperational();
    }

    public void release() {
        super.release();
        this.zzbMJ.zzSp();
    }
}
