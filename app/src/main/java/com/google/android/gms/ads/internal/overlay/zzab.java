package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import com.google.android.gms.internal.zzmb;

@TargetApi(14)
@zzmb
public class zzab implements AudioManager.OnAudioFocusChangeListener {
    private final AudioManager mAudioManager;
    private boolean zzNq;
    private final zza zzOB;
    private boolean zzOC;
    private boolean zzOD;
    private float zzOE = 1.0f;

    interface zza {
        void zzhh();
    }

    public zzab(Context context, zza zza2) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.zzOB = zza2;
    }

    private void zzif() {
        boolean z = this.zzNq && !this.zzOD && this.zzOE > 0.0f;
        if (z && !this.zzOC) {
            zzig();
            this.zzOB.zzhh();
        } else if (!z && this.zzOC) {
            zzih();
            this.zzOB.zzhh();
        }
    }

    private void zzig() {
        boolean z = true;
        if (this.mAudioManager != null && !this.zzOC) {
            if (this.mAudioManager.requestAudioFocus(this, 3, 2) != 1) {
                z = false;
            }
            this.zzOC = z;
        }
    }

    private void zzih() {
        if (this.mAudioManager != null && this.zzOC) {
            this.zzOC = this.mAudioManager.abandonAudioFocus(this) == 0;
        }
    }

    public void onAudioFocusChange(int i) {
        this.zzOC = i > 0;
        this.zzOB.zzhh();
    }

    public void setMuted(boolean z) {
        this.zzOD = z;
        zzif();
    }

    public void zzb(float f) {
        this.zzOE = f;
        zzif();
    }

    public void zzib() {
        this.zzNq = true;
        zzif();
    }

    public void zzic() {
        this.zzNq = false;
        zzif();
    }

    public float zzie() {
        float f = this.zzOD ? 0.0f : this.zzOE;
        if (this.zzOC) {
            return f;
        }
        return 0.0f;
    }
}
