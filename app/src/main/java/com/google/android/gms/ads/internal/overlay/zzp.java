package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzmb;

@zzmb
public class zzp extends FrameLayout implements View.OnClickListener {
    private final ImageButton zzNA;
    private final zzv zzNB;

    public zzp(Context context, int i, zzv zzv) {
        super(context);
        this.zzNB = zzv;
        setOnClickListener(this);
        this.zzNA = new ImageButton(context);
        this.zzNA.setImageResource(17301527);
        this.zzNA.setBackgroundColor(0);
        this.zzNA.setOnClickListener(this);
        this.zzNA.setPadding(0, 0, 0, 0);
        this.zzNA.setContentDescription("Interstitial close button");
        int zzb = zzeh.zzeO().zzb(context, i);
        addView(this.zzNA, new FrameLayout.LayoutParams(zzb, zzb, 17));
    }

    public void onClick(View view) {
        if (this.zzNB != null) {
            this.zzNB.zzhj();
        }
    }

    public void zza(boolean z, boolean z2) {
        if (!z2) {
            this.zzNA.setVisibility(0);
        } else if (z) {
            this.zzNA.setVisibility(4);
        } else {
            this.zzNA.setVisibility(8);
        }
    }
}
