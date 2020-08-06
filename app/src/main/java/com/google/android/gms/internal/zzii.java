package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzv;
import java.util.Map;

@zzmb
class zzii implements zzhx {
    zzii() {
    }

    private int zzh(Map<String, String> map) {
        int parseInt = Integer.parseInt(map.get("playbackState"));
        if (parseInt < 0 || 3 < parseInt) {
            return 0;
        }
        return parseInt;
    }

    public void zza(zzqp zzqp, Map<String, String> map) {
        zzqu zzqu;
        if (zzfx.zzCY.get().booleanValue()) {
            zzqu zzlg = zzqp.zzlg();
            if (zzlg == null) {
                try {
                    zzqu zzqu2 = new zzqu(zzqp, Float.parseFloat(map.get("duration")));
                    zzqp.zza(zzqu2);
                    zzqu = zzqu2;
                } catch (NullPointerException | NumberFormatException e) {
                    zzpe.zzb("Unable to parse videoMeta message.", e);
                    zzv.zzcN().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            } else {
                zzqu = zzlg;
            }
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("muted"));
            float parseFloat = Float.parseFloat(map.get("currentTime"));
            int zzh = zzh(map);
            String str = map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzpe.zzai(3)) {
                zzpe.zzbc(new StringBuilder(String.valueOf(str).length() + 79).append("Video Meta GMSG: isMuted : ").append(equals).append(" , playbackState : ").append(zzh).append(" , aspectRatio : ").append(str).toString());
            }
            zzqu.zza(parseFloat, zzh, equals, parseFloat2);
        }
    }
}
