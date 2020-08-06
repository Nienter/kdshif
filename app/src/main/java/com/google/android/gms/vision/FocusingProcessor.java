package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;

public abstract class FocusingProcessor<T> implements Detector.Processor<T> {
    private Detector<T> zzbMd;
    private Tracker<T> zzbMt;
    private int zzbMu = 3;
    private boolean zzbMv = false;
    private int zzbMw;
    private int zzbMx = 0;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.zzbMd = detector;
        this.zzbMt = tracker;
    }

    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.zzbMx == this.zzbMu) {
                this.zzbMt.onDone();
                this.zzbMv = false;
            } else {
                this.zzbMt.onMissing(detections);
            }
            this.zzbMx++;
            return;
        }
        this.zzbMx = 0;
        if (this.zzbMv) {
            T t = detectedItems.get(this.zzbMw);
            if (t != null) {
                this.zzbMt.onUpdate(detections, t);
                return;
            } else {
                this.zzbMt.onDone();
                this.zzbMv = false;
            }
        }
        int selectFocus = selectFocus(detections);
        T t2 = detectedItems.get(selectFocus);
        if (t2 == null) {
            Log.w("FocusingProcessor", new StringBuilder(35).append("Invalid focus selected: ").append(selectFocus).toString());
            return;
        }
        this.zzbMv = true;
        this.zzbMw = selectFocus;
        this.zzbMd.setFocus(this.zzbMw);
        this.zzbMt.onNewItem(this.zzbMw, t2);
        this.zzbMt.onUpdate(detections, t2);
    }

    public void release() {
        this.zzbMt.onDone();
    }

    public abstract int selectFocus(Detector.Detections<T> detections);

    /* access modifiers changed from: protected */
    public void zznf(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(28).append("Invalid max gap: ").append(i).toString());
        }
        this.zzbMu = i;
    }
}
