package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.p001v4.internal.view.SupportMenu;
import android.support.p001v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import com.google.android.gms.internal.zzqp;
import java.util.HashMap;
import java.util.Map;

@zzmb
public class zzl extends FrameLayout implements zzi {
    private final zzqp zzGt;
    private String zzHV;
    private final FrameLayout zzNl;
    private final zzgf zzNm;
    private final zzaa zzNn;
    private final long zzNo;
    @Nullable
    private zzj zzNp;
    private boolean zzNq;
    private boolean zzNr;
    private boolean zzNs;
    private boolean zzNt;
    private long zzNu;
    private long zzNv;
    private Bitmap zzNw;
    private ImageView zzNx;
    private boolean zzNy;

    public zzl(Context context, zzqp zzqp, int i, boolean z, zzgf zzgf) {
        super(context);
        this.zzGt = zzqp;
        this.zzNm = zzgf;
        this.zzNl = new FrameLayout(context);
        addView(this.zzNl, new FrameLayout.LayoutParams(-1, -1));
        zzc.zzt(zzqp.zzbz());
        this.zzNp = zzqp.zzbz().zzsN.zza(context, zzqp, i, z, zzgf);
        if (this.zzNp != null) {
            this.zzNl.addView(this.zzNp, new FrameLayout.LayoutParams(-1, -1, 17));
            if (zzfx.zzBr.get().booleanValue()) {
                zzhG();
            }
        }
        this.zzNx = new ImageView(context);
        this.zzNo = zzfx.zzBv.get().longValue();
        this.zzNt = zzfx.zzBt.get().booleanValue();
        if (this.zzNm != null) {
            this.zzNm.zzg("spinner_used", this.zzNt ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.zzNn = new zzaa(this);
        this.zzNn.zzid();
        if (this.zzNp != null) {
            this.zzNp.zza(this);
        }
        if (this.zzNp == null) {
            zzk("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    /* access modifiers changed from: private */
    public void zza(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        int length = strArr.length;
        int i = 0;
        String str2 = null;
        while (i < length) {
            String str3 = strArr[i];
            if (str2 != null) {
                hashMap.put(str2, str3);
                str3 = null;
            }
            i++;
            str2 = str3;
        }
        this.zzGt.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    private void zzg(int i, int i2) {
        if (this.zzNt) {
            int max = Math.max(i / zzfx.zzBu.get().intValue(), 1);
            int max2 = Math.max(i2 / zzfx.zzBu.get().intValue(), 1);
            if (this.zzNw == null || this.zzNw.getWidth() != max || this.zzNw.getHeight() != max2) {
                this.zzNw = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
                this.zzNy = false;
            }
        }
    }

    @TargetApi(14)
    private void zzhI() {
        if (this.zzNw != null) {
            long elapsedRealtime = zzv.zzcP().elapsedRealtime();
            if (this.zzNp.getBitmap(this.zzNw) != null) {
                this.zzNy = true;
            }
            long elapsedRealtime2 = zzv.zzcP().elapsedRealtime() - elapsedRealtime;
            if (zzpe.zzkh()) {
                zzpe.m2431v(new StringBuilder(46).append("Spinner frame grab took ").append(elapsedRealtime2).append("ms").toString());
            }
            if (elapsedRealtime2 > this.zzNo) {
                zzpe.zzbe("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzNt = false;
                this.zzNw = null;
                if (this.zzNm != null) {
                    this.zzNm.zzg("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    private void zzhJ() {
        if (this.zzNy && this.zzNw != null && !zzhL()) {
            this.zzNx.setImageBitmap(this.zzNw);
            this.zzNx.invalidate();
            this.zzNl.addView(this.zzNx, new FrameLayout.LayoutParams(-1, -1));
            this.zzNl.bringChildToFront(this.zzNx);
        }
    }

    private void zzhK() {
        if (zzhL()) {
            this.zzNl.removeView(this.zzNx);
        }
    }

    private boolean zzhL() {
        return this.zzNx.getParent() != null;
    }

    private void zzhM() {
        if (this.zzGt.zzkR() != null && !this.zzNr) {
            this.zzNs = (this.zzGt.zzkR().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzNs) {
                this.zzGt.zzkR().getWindow().addFlags(128);
                this.zzNr = true;
            }
        }
    }

    private void zzhN() {
        if (this.zzGt.zzkR() != null && this.zzNr && !this.zzNs) {
            this.zzGt.zzkR().getWindow().clearFlags(128);
            this.zzNr = false;
        }
    }

    public static void zzi(zzqp zzqp) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        zzqp.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    public void destroy() {
        this.zzNn.cancel();
        if (this.zzNp != null) {
            this.zzNp.stop();
        }
        zzhN();
    }

    public void onPaused() {
        zza("pause", new String[0]);
        zzhN();
        this.zzNq = false;
    }

    public void pause() {
        if (this.zzNp != null) {
            this.zzNp.pause();
        }
    }

    public void play() {
        if (this.zzNp != null) {
            this.zzNp.play();
        }
    }

    public void seekTo(int i) {
        if (this.zzNp != null) {
            this.zzNp.seekTo(i);
        }
    }

    public void zza(float f, float f2) {
        if (this.zzNp != null) {
            this.zzNp.zza(f, f2);
        }
    }

    public void zzaB(String str) {
        this.zzHV = str;
    }

    public void zzb(float f) {
        if (this.zzNp != null) {
            this.zzNp.zzb(f);
        }
    }

    public void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzNl.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public void zzf(int i, int i2) {
        zzg(i, i2);
    }

    @TargetApi(14)
    public void zzf(MotionEvent motionEvent) {
        if (this.zzNp != null) {
            this.zzNp.dispatchTouchEvent(motionEvent);
        }
    }

    public void zzgq() {
        if (this.zzNp != null) {
            if (!TextUtils.isEmpty(this.zzHV)) {
                this.zzNp.setVideoPath(this.zzHV);
            } else {
                zza("no_src", new String[0]);
            }
        }
    }

    public void zzhA() {
        zzhM();
        this.zzNq = true;
    }

    public void zzhB() {
        zza("ended", new String[0]);
        zzhN();
    }

    public void zzhC() {
        zzhJ();
        this.zzNv = this.zzNu;
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                zzl.this.zza("surfaceDestroyed", new String[0]);
            }
        });
    }

    public void zzhD() {
        if (this.zzNq) {
            zzhK();
        }
        zzhI();
    }

    public void zzhE() {
        if (this.zzNp != null) {
            this.zzNp.zzhE();
        }
    }

    public void zzhF() {
        if (this.zzNp != null) {
            this.zzNp.zzhF();
        }
    }

    @TargetApi(14)
    public void zzhG() {
        if (this.zzNp != null) {
            TextView textView = new TextView(this.zzNp.getContext());
            String valueOf = String.valueOf(this.zzNp.zzhd());
            textView.setText(valueOf.length() != 0 ? "AdMob - ".concat(valueOf) : new String("AdMob - "));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzNl.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
            this.zzNl.bringChildToFront(textView);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzhH() {
        if (this.zzNp != null) {
            long currentPosition = (long) this.zzNp.getCurrentPosition();
            if (this.zzNu != currentPosition && currentPosition > 0) {
                zza("timeupdate", "time", String.valueOf(((float) currentPosition) / 1000.0f));
                this.zzNu = currentPosition;
            }
        }
    }

    public void zzhy() {
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                zzl.this.zza("surfaceCreated", new String[0]);
            }
        });
    }

    public void zzhz() {
        if (this.zzNp != null && this.zzNv == 0) {
            zza("canplaythrough", "duration", String.valueOf(((float) this.zzNp.getDuration()) / 1000.0f), "videoWidth", String.valueOf(this.zzNp.getVideoWidth()), "videoHeight", String.valueOf(this.zzNp.getVideoHeight()));
        }
    }

    public void zzk(String str, @Nullable String str2) {
        zza("error", "what", str, "extra", str2);
    }
}
