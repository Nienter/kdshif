package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzgb;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqa;
import java.util.concurrent.TimeUnit;

@zzmb
public class zzz {
    private final Context mContext;
    @Nullable
    private final zzgf zzNm;
    private boolean zzNq;
    private final String zzOn;
    @Nullable
    private final zzgd zzOo;
    private final zzpo zzOp = new zzpo.zzb().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzkB();
    private final long[] zzOq;
    private final String[] zzOr;
    private boolean zzOs = false;
    private boolean zzOt = false;
    private boolean zzOu = false;
    private boolean zzOv = false;
    private zzj zzOw;
    private boolean zzOx;
    private boolean zzOy;
    private long zzOz = -1;
    private final zzqa zztZ;

    public zzz(Context context, zzqa zzqa, String str, @Nullable zzgf zzgf, @Nullable zzgd zzgd) {
        this.mContext = context;
        this.zztZ = zzqa;
        this.zzOn = str;
        this.zzNm = zzgf;
        this.zzOo = zzgd;
        String str2 = zzfx.zzBp.get();
        if (str2 == null) {
            this.zzOr = new String[0];
            this.zzOq = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzOr = new String[split.length];
        this.zzOq = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzOq[i] = Long.parseLong(split[i]);
            } catch (NumberFormatException e) {
                zzpe.zzc("Unable to parse frame hash target time number.", e);
                this.zzOq[i] = -1;
            }
        }
    }

    private void zzc(zzj zzj) {
        long longValue = zzfx.zzBq.get().longValue();
        long currentPosition = (long) zzj.getCurrentPosition();
        for (int i = 0; i < this.zzOr.length; i++) {
            if (this.zzOr[i] == null && longValue > Math.abs(currentPosition - this.zzOq[i])) {
                this.zzOr[i] = zza((TextureView) zzj);
                return;
            }
        }
    }

    private void zzia() {
        if (this.zzOu && !this.zzOv) {
            zzgb.zza(this.zzNm, this.zzOo, "vff2");
            this.zzOv = true;
        }
        long nanoTime = zzv.zzcP().nanoTime();
        if (this.zzNq && this.zzOy && this.zzOz != -1) {
            this.zzOp.zza(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.zzOz)));
        }
        this.zzOy = this.zzNq;
        this.zzOz = nanoTime;
    }

    public void onStop() {
        if (zzfx.zzBo.get().booleanValue() && !this.zzOx) {
            Bundle bundle = new Bundle();
            bundle.putString(ShareConstants.MEDIA_TYPE, "native-player-metrics");
            bundle.putString(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, this.zzOn);
            bundle.putString("player", this.zzOw.zzhd());
            for (zzpo.zza next : this.zzOp.getBuckets()) {
                String valueOf = String.valueOf("fps_c_");
                String valueOf2 = String.valueOf(next.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Integer.toString(next.count));
                String valueOf3 = String.valueOf("fps_p_");
                String valueOf4 = String.valueOf(next.name);
                bundle.putString(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), Double.toString(next.zzXs));
            }
            for (int i = 0; i < this.zzOq.length; i++) {
                String str = this.zzOr[i];
                if (str != null) {
                    String valueOf5 = String.valueOf("fh_");
                    String valueOf6 = String.valueOf(Long.valueOf(this.zzOq[i]));
                    bundle.putString(new StringBuilder(String.valueOf(valueOf5).length() + 0 + String.valueOf(valueOf6).length()).append(valueOf5).append(valueOf6).toString(), str);
                }
            }
            zzv.zzcJ().zza(this.mContext, this.zztZ.zzaZ, "gmob-apps", bundle, true);
            this.zzOx = true;
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public String zza(TextureView textureView) {
        long j;
        Bitmap bitmap = textureView.getBitmap(8, 8);
        long j2 = 0;
        long j3 = 63;
        int i = 0;
        while (i < 8) {
            int i2 = 0;
            long j4 = j2;
            while (true) {
                j = j3;
                int i3 = i2;
                if (i3 >= 8) {
                    break;
                }
                int pixel = bitmap.getPixel(i3, i);
                j4 |= (Color.green(pixel) + (Color.blue(pixel) + Color.red(pixel)) > 128 ? 1 : 0) << ((int) j);
                i2 = i3 + 1;
                j3 = j - 1;
            }
            i++;
            j3 = j;
            j2 = j4;
        }
        return String.format("%016X", new Object[]{Long.valueOf(j2)});
    }

    public void zza(zzj zzj) {
        zzgb.zza(this.zzNm, this.zzOo, "vpc2");
        this.zzOs = true;
        if (this.zzNm != null) {
            this.zzNm.zzg("vpn", zzj.zzhd());
        }
        this.zzOw = zzj;
    }

    public void zzb(zzj zzj) {
        zzia();
        zzc(zzj);
    }

    public void zzhz() {
        if (this.zzOs && !this.zzOt) {
            zzgb.zza(this.zzNm, this.zzOo, "vfr2");
            this.zzOt = true;
        }
    }

    public void zzib() {
        this.zzNq = true;
        if (this.zzOt && !this.zzOu) {
            zzgb.zza(this.zzNm, this.zzOo, "vfp2");
            this.zzOu = true;
        }
    }

    public void zzic() {
        this.zzNq = false;
    }
}
