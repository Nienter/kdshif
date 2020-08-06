package com.google.android.gms.vision.face;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.zzbgt;
import com.google.android.gms.internal.zzbgw;
import com.google.android.gms.internal.zzbhd;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.zza;
import java.nio.ByteBuffer;
import java.util.HashSet;

public final class FaceDetector extends Detector<Face> {
    public static final int ACCURATE_MODE = 1;
    public static final int ALL_CLASSIFICATIONS = 1;
    public static final int ALL_LANDMARKS = 1;
    public static final int FAST_MODE = 0;
    public static final int NO_CLASSIFICATIONS = 0;
    public static final int NO_LANDMARKS = 0;
    private final zza zzbMT;
    private final zzbgt zzbMU;
    private boolean zzbMV;
    private final Object zzrN;

    public static class Builder {
        private final Context mContext;
        private int zzaJi = 0;
        private int zzbMW = 0;
        private boolean zzbMX = false;
        private int zzbMY = 0;
        private boolean zzbMZ = true;
        private float zzbNa = -1.0f;

        public Builder(Context context) {
            this.mContext = context;
        }

        public FaceDetector build() {
            zzbgw zzbgw = new zzbgw();
            zzbgw.mode = this.zzaJi;
            zzbgw.zzbNj = this.zzbMW;
            zzbgw.zzbNk = this.zzbMY;
            zzbgw.zzbNl = this.zzbMX;
            zzbgw.zzbNm = this.zzbMZ;
            zzbgw.zzbNn = this.zzbNa;
            return new FaceDetector(new zzbgt(this.mContext, zzbgw));
        }

        public Builder setClassificationType(int i) {
            if (i == 0 || i == 1) {
                this.zzbMY = i;
                return this;
            }
            throw new IllegalArgumentException(new StringBuilder(40).append("Invalid classification type: ").append(i).toString());
        }

        public Builder setLandmarkType(int i) {
            if (i == 0 || i == 1) {
                this.zzbMW = i;
                return this;
            }
            throw new IllegalArgumentException(new StringBuilder(34).append("Invalid landmark type: ").append(i).toString());
        }

        public Builder setMinFaceSize(float f) {
            if (f < 0.0f || f > 1.0f) {
                throw new IllegalArgumentException(new StringBuilder(47).append("Invalid proportional face size: ").append(f).toString());
            }
            this.zzbNa = f;
            return this;
        }

        public Builder setMode(int i) {
            switch (i) {
                case 0:
                case 1:
                    this.zzaJi = i;
                    return this;
                default:
                    throw new IllegalArgumentException(new StringBuilder(25).append("Invalid mode: ").append(i).toString());
            }
        }

        public Builder setProminentFaceOnly(boolean z) {
            this.zzbMX = z;
            return this;
        }

        public Builder setTrackingEnabled(boolean z) {
            this.zzbMZ = z;
            return this;
        }
    }

    private FaceDetector() {
        this.zzbMT = new zza();
        this.zzrN = new Object();
        this.zzbMV = true;
        throw new IllegalStateException("Default constructor called");
    }

    private FaceDetector(zzbgt zzbgt) {
        this.zzbMT = new zza();
        this.zzrN = new Object();
        this.zzbMV = true;
        this.zzbMU = zzbgt;
    }

    public SparseArray<Face> detect(Frame frame) {
        Face[] zzb;
        if (frame == null) {
            throw new IllegalArgumentException("No frame supplied.");
        }
        ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
        synchronized (this.zzrN) {
            if (!this.zzbMV) {
                throw new RuntimeException("Cannot use detector after release()");
            }
            zzb = this.zzbMU.zzb(grayscaleImageData, zzbhd.zzc(frame));
        }
        HashSet hashSet = new HashSet();
        SparseArray<Face> sparseArray = new SparseArray<>(zzb.length);
        int i = 0;
        for (Face face : zzb) {
            int id = face.getId();
            i = Math.max(i, id);
            if (hashSet.contains(Integer.valueOf(id))) {
                id = i + 1;
                i = id;
            }
            hashSet.add(Integer.valueOf(id));
            sparseArray.append(this.zzbMT.zzng(id), face);
        }
        return sparseArray;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            synchronized (this.zzrN) {
                if (this.zzbMV) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    release();
                }
            }
        } finally {
            super.finalize();
        }
    }

    public boolean isOperational() {
        return this.zzbMU.isOperational();
    }

    public void release() {
        super.release();
        synchronized (this.zzrN) {
            if (this.zzbMV) {
                this.zzbMU.zzSp();
                this.zzbMV = false;
            }
        }
    }

    public boolean setFocus(int i) {
        boolean zznw;
        int zznh = this.zzbMT.zznh(i);
        synchronized (this.zzrN) {
            if (!this.zzbMV) {
                throw new RuntimeException("Cannot use detector after release()");
            }
            zznw = this.zzbMU.zznw(zznh);
        }
        return zznw;
    }
}
