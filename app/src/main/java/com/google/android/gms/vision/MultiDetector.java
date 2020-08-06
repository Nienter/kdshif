package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.ArrayList;
import java.util.List;

public class MultiDetector extends Detector<Object> {
    /* access modifiers changed from: private */
    public List<Detector<? extends Object>> zzbME;

    public static class Builder {
        private MultiDetector zzbMF = new MultiDetector();

        public Builder add(Detector<? extends Object> detector) {
            this.zzbMF.zzbME.add(detector);
            return this;
        }

        public MultiDetector build() {
            if (this.zzbMF.zzbME.size() != 0) {
                return this.zzbMF;
            }
            throw new RuntimeException("No underlying detectors added to MultiDetector.");
        }
    }

    private MultiDetector() {
        this.zzbME = new ArrayList();
    }

    public SparseArray<Object> detect(Frame frame) {
        SparseArray<Object> sparseArray = new SparseArray<>();
        for (Detector<? extends Object> detect : this.zzbME) {
            SparseArray detect2 = detect.detect(frame);
            int i = 0;
            while (true) {
                if (i < detect2.size()) {
                    int keyAt = detect2.keyAt(i);
                    if (sparseArray.get(keyAt) != null) {
                        throw new IllegalStateException(new StringBuilder(105).append("Detection ID overlap for id = ").append(keyAt).append(".  This means that one of the detectors is not using global IDs.").toString());
                    }
                    sparseArray.append(keyAt, detect2.valueAt(i));
                    i++;
                }
            }
        }
        return sparseArray;
    }

    public boolean isOperational() {
        for (Detector<? extends Object> isOperational : this.zzbME) {
            if (!isOperational.isOperational()) {
                return false;
            }
        }
        return true;
    }

    public void receiveFrame(Frame frame) {
        for (Detector<? extends Object> receiveFrame : this.zzbME) {
            receiveFrame.receiveFrame(frame);
        }
    }

    public void release() {
        for (Detector<? extends Object> release : this.zzbME) {
            release.release();
        }
        this.zzbME.clear();
    }

    public void setProcessor(Detector.Processor<Object> processor) {
        throw new UnsupportedOperationException("MultiDetector.setProcessor is not supported.  You should set a processor instance on each underlying detector instead.");
    }
}
