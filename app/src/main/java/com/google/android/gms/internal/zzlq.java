package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzov;

@TargetApi(19)
@zzmb
public class zzlq extends zzlo {
    private Object zzPI = new Object();
    private PopupWindow zzPJ;
    private boolean zzPK = false;

    zzlq(Context context, zzov.zza zza, zzqp zzqp, zzln.zza zza2) {
        super(context, zza, zzqp, zza2);
    }

    private void zziC() {
        synchronized (this.zzPI) {
            this.zzPK = true;
            if ((this.mContext instanceof Activity) && ((Activity) this.mContext).isDestroyed()) {
                this.zzPJ = null;
            }
            if (this.zzPJ != null) {
                if (this.zzPJ.isShowing()) {
                    this.zzPJ.dismiss();
                }
                this.zzPJ = null;
            }
        }
    }

    public void cancel() {
        zziC();
        super.cancel();
    }

    /* access modifiers changed from: protected */
    public void zzO(int i) {
        zziC();
        super.zzO(i);
    }

    /* access modifiers changed from: protected */
    public void zziB() {
        Window window = this.mContext instanceof Activity ? ((Activity) this.mContext).getWindow() : null;
        if (window != null && window.getDecorView() != null && !((Activity) this.mContext).isDestroyed()) {
            FrameLayout frameLayout = new FrameLayout(this.mContext);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            frameLayout.addView(this.zzGt.getView(), -1, -1);
            synchronized (this.zzPI) {
                if (!this.zzPK) {
                    this.zzPJ = new PopupWindow(frameLayout, 1, 1, false);
                    this.zzPJ.setOutsideTouchable(true);
                    this.zzPJ.setClippingEnabled(false);
                    zzpe.zzbc("Displaying the 1x1 popup off the screen.");
                    try {
                        this.zzPJ.showAtLocation(window.getDecorView(), 0, -1, -1);
                    } catch (Exception e) {
                        this.zzPJ = null;
                    }
                }
            }
        }
    }
}
