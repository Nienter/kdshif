package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import java.util.HashSet;

public class MultiProcessor<T> implements Detector.Processor<T> {
    /* access modifiers changed from: private */
    public Factory<T> zzbMG;
    private SparseArray<zza> zzbMH;
    /* access modifiers changed from: private */
    public int zzbMu;

    public static class Builder<T> {
        private MultiProcessor<T> zzbMI = new MultiProcessor<>();

        public Builder(Factory<T> factory) {
            if (factory == null) {
                throw new IllegalArgumentException("No factory supplied.");
            }
            Factory unused = this.zzbMI.zzbMG = factory;
        }

        public MultiProcessor<T> build() {
            return this.zzbMI;
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i < 0) {
                throw new IllegalArgumentException(new StringBuilder(28).append("Invalid max gap: ").append(i).toString());
            }
            int unused = this.zzbMI.zzbMu = i;
            return this;
        }
    }

    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    private class zza {
        /* access modifiers changed from: private */
        public Tracker<T> zzbMt;
        /* access modifiers changed from: private */
        public int zzbMx;

        private zza(MultiProcessor multiProcessor) {
            this.zzbMx = 0;
        }
    }

    private MultiProcessor() {
        this.zzbMH = new SparseArray<>();
        this.zzbMu = 3;
    }

    private void zza(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            T valueAt = detectedItems.valueAt(i);
            if (this.zzbMH.get(keyAt) == null) {
                zza zza2 = new zza();
                Tracker unused = zza2.zzbMt = this.zzbMG.create(valueAt);
                zza2.zzbMt.onNewItem(keyAt, valueAt);
                this.zzbMH.append(keyAt, zza2);
            }
        }
    }

    private void zzb(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet<>();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zzbMH.size()) {
                break;
            }
            int keyAt = this.zzbMH.keyAt(i2);
            if (detectedItems.get(keyAt) == null) {
                zza valueAt = this.zzbMH.valueAt(i2);
                int unused = valueAt.zzbMx = valueAt.zzbMx + 1;
                if (valueAt.zzbMx >= this.zzbMu) {
                    valueAt.zzbMt.onDone();
                    hashSet.add(Integer.valueOf(keyAt));
                } else {
                    valueAt.zzbMt.onMissing(detections);
                }
            }
            i = i2 + 1;
        }
        for (Integer intValue : hashSet) {
            this.zzbMH.delete(intValue.intValue());
        }
    }

    private void zzc(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            T valueAt = detectedItems.valueAt(i);
            zza zza2 = this.zzbMH.get(keyAt);
            int unused = zza2.zzbMx = 0;
            zza2.zzbMt.onUpdate(detections, valueAt);
        }
    }

    public void receiveDetections(Detector.Detections<T> detections) {
        zza(detections);
        zzb(detections);
        zzc(detections);
    }

    public void release() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzbMH.size()) {
                this.zzbMH.valueAt(i2).zzbMt.onDone();
                i = i2 + 1;
            } else {
                this.zzbMH.clear();
                return;
            }
        }
    }
}
