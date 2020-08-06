package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzew;
import java.util.HashMap;
import java.util.Map;

@zzmb
public class zzqu extends zzew.zza {
    /* access modifiers changed from: private */
    public final zzqp zzGt;
    private boolean zzZA;
    private float zzZB;
    private float zzZC;
    private final float zzZw;
    private int zzZx;
    /* access modifiers changed from: private */
    public zzex zzZy;
    /* access modifiers changed from: private */
    public boolean zzZz;
    /* access modifiers changed from: private */
    public final Object zzrN = new Object();
    private boolean zzrQ = true;

    public zzqu(zzqp zzqp, float f) {
        this.zzGt = zzqp;
        this.zzZw = f;
    }

    private void zzbk(String str) {
        zzd(str, null);
    }

    private void zzd(String str, @Nullable Map<String, String> map) {
        final HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put(NativeProtocol.WEB_DIALOG_ACTION, str);
        zzv.zzcJ().runOnUiThread(new Runnable() {
            public void run() {
                zzqu.this.zzGt.zza("pubVideoCmd", (Map<String, ?>) hashMap);
            }
        });
    }

    private void zzk(final int i, final int i2) {
        zzv.zzcJ().runOnUiThread(new Runnable() {
            public void run() {
                boolean z = false;
                synchronized (zzqu.this.zzrN) {
                    boolean z2 = i != i2;
                    boolean z3 = !zzqu.this.zzZz && i2 == 1;
                    boolean z4 = z2 && i2 == 1;
                    boolean z5 = z2 && i2 == 2;
                    boolean z6 = z2 && i2 == 3;
                    zzqu zzqu = zzqu.this;
                    if (zzqu.this.zzZz || z3) {
                        z = true;
                    }
                    boolean unused = zzqu.zzZz = z;
                    if (zzqu.this.zzZy != null) {
                        if (z3) {
                            try {
                                zzqu.this.zzZy.zzeT();
                            } catch (RemoteException e) {
                                zzpe.zzc("Unable to call onVideoStart()", e);
                            }
                        }
                        if (z4) {
                            try {
                                zzqu.this.zzZy.zzeU();
                            } catch (RemoteException e2) {
                                zzpe.zzc("Unable to call onVideoPlay()", e2);
                            }
                        }
                        if (z5) {
                            try {
                                zzqu.this.zzZy.zzeV();
                            } catch (RemoteException e3) {
                                zzpe.zzc("Unable to call onVideoPause()", e3);
                            }
                        }
                        if (z6) {
                            try {
                                zzqu.this.zzZy.onVideoEnd();
                            } catch (RemoteException e4) {
                                zzpe.zzc("Unable to call onVideoEnd()", e4);
                            }
                        }
                    }
                }
            }
        });
    }

    public float getAspectRatio() {
        float f;
        synchronized (this.zzrN) {
            f = this.zzZC;
        }
        return f;
    }

    public int getPlaybackState() {
        int i;
        synchronized (this.zzrN) {
            i = this.zzZx;
        }
        return i;
    }

    public boolean isMuted() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzZA;
        }
        return z;
    }

    public void pause() {
        zzbk("pause");
    }

    public void play() {
        zzbk("play");
    }

    public void zzP(boolean z) {
        synchronized (this.zzrN) {
            this.zzrQ = z;
        }
        zzd("initialState", zzf.zze("muteStart", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    public void zza(float f, int i, boolean z, float f2) {
        int i2;
        synchronized (this.zzrN) {
            this.zzZB = f;
            this.zzZA = z;
            i2 = this.zzZx;
            this.zzZx = i;
            this.zzZC = f2;
        }
        zzk(i2, i);
    }

    public void zza(zzex zzex) {
        synchronized (this.zzrN) {
            this.zzZy = zzex;
        }
    }

    public float zzeR() {
        return this.zzZw;
    }

    public float zzeS() {
        float f;
        synchronized (this.zzrN) {
            f = this.zzZB;
        }
        return f;
    }

    public void zzn(boolean z) {
        zzbk(z ? "mute" : "unmute");
    }
}
