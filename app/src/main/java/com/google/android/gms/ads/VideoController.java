package com.google.android.gms.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzfm;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpy;

@zzmb
public final class VideoController {
    private final Object zzrN = new Object();
    @Nullable
    private zzew zzrO;
    @Nullable
    private VideoLifecycleCallbacks zzrP;

    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }
    }

    public float getAspectRatio() {
        float f = 0.0f;
        synchronized (this.zzrN) {
            if (this.zzrO != null) {
                try {
                    f = this.zzrO.getAspectRatio();
                } catch (RemoteException e) {
                    zzpy.zzb("Unable to call getAspectRatio on video controller.", e);
                }
            }
        }
        return f;
    }

    @Nullable
    public VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.zzrN) {
            videoLifecycleCallbacks = this.zzrP;
        }
        return videoLifecycleCallbacks;
    }

    public boolean hasVideoContent() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzrO != null;
        }
        return z;
    }

    public void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        zzac.zzb(videoLifecycleCallbacks, (Object) "VideoLifecycleCallbacks may not be null.");
        synchronized (this.zzrN) {
            this.zzrP = videoLifecycleCallbacks;
            if (this.zzrO != null) {
                try {
                    this.zzrO.zza(new zzfm(videoLifecycleCallbacks));
                } catch (RemoteException e) {
                    zzpy.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
                }
                return;
            }
            return;
        }
    }

    public void zza(zzew zzew) {
        synchronized (this.zzrN) {
            this.zzrO = zzew;
            if (this.zzrP != null) {
                setVideoLifecycleCallbacks(this.zzrP);
            }
        }
    }

    public zzew zzbt() {
        zzew zzew;
        synchronized (this.zzrN) {
            zzew = this.zzrO;
        }
        return zzew;
    }
}
