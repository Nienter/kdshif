package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzf;
import java.util.Map;
import java.util.Set;

@zzmb
public class zzkj extends zzko {
    static final Set<String> zzLq = zzf.zzc("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final zzqp zzGt;
    private PopupWindow zzLA;
    private RelativeLayout zzLB;
    private ViewGroup zzLC;
    private final Activity zzLg;
    private String zzLr = "top-right";
    private boolean zzLs = true;
    private int zzLt = 0;
    private int zzLu = 0;
    private int zzLv = 0;
    private int zzLw = 0;
    private ImageView zzLx;
    private LinearLayout zzLy;
    private zzkp zzLz;
    private int zzrG = -1;
    private int zzrH = -1;
    private final Object zzrN = new Object();
    private zzec zzum;

    public zzkj(zzqp zzqp, zzkp zzkp) {
        super(zzqp, "resize");
        this.zzGt = zzqp;
        this.zzLg = zzqp.zzkR();
        this.zzLz = zzkp;
    }

    private int[] zzgQ() {
        if (!zzgS()) {
            return null;
        }
        if (this.zzLs) {
            return new int[]{this.zzLt + this.zzLv, this.zzLu + this.zzLw};
        }
        int[] zzi = zzv.zzcJ().zzi(this.zzLg);
        int[] zzk = zzv.zzcJ().zzk(this.zzLg);
        int i = zzi[0];
        int i2 = this.zzLt + this.zzLv;
        int i3 = this.zzLu + this.zzLw;
        if (i2 < 0) {
            i2 = 0;
        } else if (this.zzrG + i2 > i) {
            i2 = i - this.zzrG;
        }
        if (i3 < zzk[0]) {
            i3 = zzk[0];
        } else if (this.zzrH + i3 > zzk[1]) {
            i3 = zzk[1] - this.zzrH;
        }
        return new int[]{i2, i3};
    }

    private void zzj(Map<String, String> map) {
        if (!TextUtils.isEmpty(map.get("width"))) {
            this.zzrG = zzv.zzcJ().zzaW(map.get("width"));
        }
        if (!TextUtils.isEmpty(map.get("height"))) {
            this.zzrH = zzv.zzcJ().zzaW(map.get("height"));
        }
        if (!TextUtils.isEmpty(map.get("offsetX"))) {
            this.zzLv = zzv.zzcJ().zzaW(map.get("offsetX"));
        }
        if (!TextUtils.isEmpty(map.get("offsetY"))) {
            this.zzLw = zzv.zzcJ().zzaW(map.get("offsetY"));
        }
        if (!TextUtils.isEmpty(map.get("allowOffscreen"))) {
            this.zzLs = Boolean.parseBoolean(map.get("allowOffscreen"));
        }
        String str = map.get("customClosePosition");
        if (!TextUtils.isEmpty(str)) {
            this.zzLr = str;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void execute(Map<String, String> map) {
        char c;
        synchronized (this.zzrN) {
            if (this.zzLg == null) {
                zzay("Not an activity context. Cannot resize.");
            } else if (this.zzGt.zzbD() == null) {
                zzay("Webview is not yet available, size is not set.");
            } else if (this.zzGt.zzbD().zzzl) {
                zzay("Is interstitial. Cannot resize an interstitial.");
            } else if (this.zzGt.zzkZ()) {
                zzay("Cannot resize an expanded banner.");
            } else {
                zzj(map);
                if (!zzgP()) {
                    zzay("Invalid width and height options. Cannot resize.");
                    return;
                }
                Window window = this.zzLg.getWindow();
                if (window == null || window.getDecorView() == null) {
                    zzay("Activity context is not ready, cannot get window or decor view.");
                    return;
                }
                int[] zzgQ = zzgQ();
                if (zzgQ == null) {
                    zzay("Resize location out of screen or close button is not visible.");
                    return;
                }
                int zzb = zzeh.zzeO().zzb((Context) this.zzLg, this.zzrG);
                int zzb2 = zzeh.zzeO().zzb((Context) this.zzLg, this.zzrH);
                ViewParent parent = this.zzGt.getView().getParent();
                if (parent == null || !(parent instanceof ViewGroup)) {
                    zzay("Webview is detached, probably in the middle of a resize or expand.");
                    return;
                }
                ((ViewGroup) parent).removeView(this.zzGt.getView());
                if (this.zzLA == null) {
                    this.zzLC = (ViewGroup) parent;
                    Bitmap zzp = zzv.zzcJ().zzp(this.zzGt.getView());
                    this.zzLx = new ImageView(this.zzLg);
                    this.zzLx.setImageBitmap(zzp);
                    this.zzum = this.zzGt.zzbD();
                    this.zzLC.addView(this.zzLx);
                } else {
                    this.zzLA.dismiss();
                }
                this.zzLB = new RelativeLayout(this.zzLg);
                this.zzLB.setBackgroundColor(0);
                this.zzLB.setLayoutParams(new ViewGroup.LayoutParams(zzb, zzb2));
                this.zzLA = zzv.zzcJ().zza((View) this.zzLB, zzb, zzb2, false);
                this.zzLA.setOutsideTouchable(true);
                this.zzLA.setTouchable(true);
                this.zzLA.setClippingEnabled(!this.zzLs);
                this.zzLB.addView(this.zzGt.getView(), -1, -1);
                this.zzLy = new LinearLayout(this.zzLg);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(zzeh.zzeO().zzb((Context) this.zzLg, 50), zzeh.zzeO().zzb((Context) this.zzLg, 50));
                String str = this.zzLr;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            c = 2;
                            break;
                        }
                    case -1012429441:
                        if (str.equals("top-left")) {
                            c = 0;
                            break;
                        }
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            c = 3;
                            break;
                        }
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            c = 5;
                            break;
                        }
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            c = 4;
                            break;
                        }
                    case 1755462605:
                        if (str.equals("top-center")) {
                            c = 1;
                            break;
                        }
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        layoutParams.addRule(10);
                        layoutParams.addRule(9);
                        break;
                    case 1:
                        layoutParams.addRule(10);
                        layoutParams.addRule(14);
                        break;
                    case 2:
                        layoutParams.addRule(13);
                        break;
                    case 3:
                        layoutParams.addRule(12);
                        layoutParams.addRule(9);
                        break;
                    case 4:
                        layoutParams.addRule(12);
                        layoutParams.addRule(14);
                        break;
                    case 5:
                        layoutParams.addRule(12);
                        layoutParams.addRule(11);
                        break;
                    default:
                        layoutParams.addRule(10);
                        layoutParams.addRule(11);
                        break;
                }
                this.zzLy.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        zzkj.this.zzs(true);
                    }
                });
                this.zzLy.setContentDescription("Close button");
                this.zzLB.addView(this.zzLy, layoutParams);
                try {
                    this.zzLA.showAtLocation(window.getDecorView(), 0, zzeh.zzeO().zzb((Context) this.zzLg, zzgQ[0]), zzeh.zzeO().zzb((Context) this.zzLg, zzgQ[1]));
                    zzb(zzgQ[0], zzgQ[1]);
                    this.zzGt.zza(new zzec((Context) this.zzLg, new AdSize(this.zzrG, this.zzrH)));
                    zzc(zzgQ[0], zzgQ[1]);
                    zzaA("resized");
                } catch (RuntimeException e) {
                    String valueOf = String.valueOf(e.getMessage());
                    zzay(valueOf.length() != 0 ? "Cannot show popup window: ".concat(valueOf) : new String("Cannot show popup window: "));
                    this.zzLB.removeView(this.zzGt.getView());
                    if (this.zzLC != null) {
                        this.zzLC.removeView(this.zzLx);
                        this.zzLC.addView(this.zzGt.getView());
                        this.zzGt.zza(this.zzum);
                    }
                }
            }
        }
    }

    public void zza(int i, int i2, boolean z) {
        synchronized (this.zzrN) {
            this.zzLt = i;
            this.zzLu = i2;
            if (this.zzLA != null && z) {
                int[] zzgQ = zzgQ();
                if (zzgQ != null) {
                    this.zzLA.update(zzeh.zzeO().zzb((Context) this.zzLg, zzgQ[0]), zzeh.zzeO().zzb((Context) this.zzLg, zzgQ[1]), this.zzLA.getWidth(), this.zzLA.getHeight());
                    zzc(zzgQ[0], zzgQ[1]);
                } else {
                    zzs(true);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(int i, int i2) {
        if (this.zzLz != null) {
            this.zzLz.zza(i, i2, this.zzrG, this.zzrH);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzc(int i, int i2) {
        zzb(i, i2 - zzv.zzcJ().zzk(this.zzLg)[0], this.zzrG, this.zzrH);
    }

    public void zzd(int i, int i2) {
        this.zzLt = i;
        this.zzLu = i2;
    }

    /* access modifiers changed from: package-private */
    public boolean zzgP() {
        return this.zzrG > -1 && this.zzrH > -1;
    }

    public boolean zzgR() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzLA != null;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean zzgS() {
        int i;
        int i2;
        int[] zzi = zzv.zzcJ().zzi(this.zzLg);
        int[] zzk = zzv.zzcJ().zzk(this.zzLg);
        int i3 = zzi[0];
        int i4 = zzi[1];
        if (this.zzrG < 50 || this.zzrG > i3) {
            zzpe.zzbe("Width is too small or too large.");
            return false;
        } else if (this.zzrH < 50 || this.zzrH > i4) {
            zzpe.zzbe("Height is too small or too large.");
            return false;
        } else if (this.zzrH == i4 && this.zzrG == i3) {
            zzpe.zzbe("Cannot resize to a full-screen ad.");
            return false;
        } else {
            if (this.zzLs) {
                String str = this.zzLr;
                char c = 65535;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1012429441:
                        if (str.equals("top-left")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1755462605:
                        if (str.equals("top-center")) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        i = this.zzLv + this.zzLt;
                        i2 = this.zzLu + this.zzLw;
                        break;
                    case 1:
                        i = ((this.zzLt + this.zzLv) + (this.zzrG / 2)) - 25;
                        i2 = this.zzLu + this.zzLw;
                        break;
                    case 2:
                        i = ((this.zzLt + this.zzLv) + (this.zzrG / 2)) - 25;
                        i2 = ((this.zzLu + this.zzLw) + (this.zzrH / 2)) - 25;
                        break;
                    case 3:
                        i = this.zzLv + this.zzLt;
                        i2 = ((this.zzLu + this.zzLw) + this.zzrH) - 50;
                        break;
                    case 4:
                        i = ((this.zzLt + this.zzLv) + (this.zzrG / 2)) - 25;
                        i2 = ((this.zzLu + this.zzLw) + this.zzrH) - 50;
                        break;
                    case 5:
                        i = ((this.zzLt + this.zzLv) + this.zzrG) - 50;
                        i2 = ((this.zzLu + this.zzLw) + this.zzrH) - 50;
                        break;
                    default:
                        i = ((this.zzLt + this.zzLv) + this.zzrG) - 50;
                        i2 = this.zzLu + this.zzLw;
                        break;
                }
                if (i < 0 || i + 50 > i3 || i2 < zzk[0] || i2 + 50 > zzk[1]) {
                    return false;
                }
            }
            return true;
        }
    }

    public void zzs(boolean z) {
        synchronized (this.zzrN) {
            if (this.zzLA != null) {
                this.zzLA.dismiss();
                this.zzLB.removeView(this.zzGt.getView());
                if (this.zzLC != null) {
                    this.zzLC.removeView(this.zzLx);
                    this.zzLC.addView(this.zzGt.getView());
                    this.zzGt.zza(this.zzum);
                }
                if (z) {
                    zzaA("default");
                    if (this.zzLz != null) {
                        this.zzLz.zzbZ();
                    }
                }
                this.zzLA = null;
                this.zzLB = null;
                this.zzLC = null;
                this.zzLy = null;
            }
        }
    }
}
