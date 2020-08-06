package com.google.android.gms.ads.formats;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zzmb;

@zzmb
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzrX;
    private final int zzrY;
    private final boolean zzrZ;
    private final int zzsa;
    private final VideoOptions zzsb;

    public @interface AdChoicesPlacement {
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzrX = false;
        /* access modifiers changed from: private */
        public int zzrY = 0;
        /* access modifiers changed from: private */
        public boolean zzrZ = false;
        /* access modifiers changed from: private */
        public int zzsa = 1;
        /* access modifiers changed from: private */
        public VideoOptions zzsb;

        public NativeAdOptions build() {
            return new NativeAdOptions(this);
        }

        public Builder setAdChoicesPlacement(@AdChoicesPlacement int i) {
            this.zzsa = i;
            return this;
        }

        public Builder setImageOrientation(int i) {
            this.zzrY = i;
            return this;
        }

        public Builder setRequestMultipleImages(boolean z) {
            this.zzrZ = z;
            return this;
        }

        public Builder setReturnUrlsForImageAssets(boolean z) {
            this.zzrX = z;
            return this;
        }

        public Builder setVideoOptions(VideoOptions videoOptions) {
            this.zzsb = videoOptions;
            return this;
        }
    }

    private NativeAdOptions(Builder builder) {
        this.zzrX = builder.zzrX;
        this.zzrY = builder.zzrY;
        this.zzrZ = builder.zzrZ;
        this.zzsa = builder.zzsa;
        this.zzsb = builder.zzsb;
    }

    public int getAdChoicesPlacement() {
        return this.zzsa;
    }

    public int getImageOrientation() {
        return this.zzrY;
    }

    @Nullable
    public VideoOptions getVideoOptions() {
        return this.zzsb;
    }

    public boolean shouldRequestMultipleImages() {
        return this.zzrZ;
    }

    public boolean shouldReturnUrlsForImageAssets() {
        return this.zzrX;
    }
}
