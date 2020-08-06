package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import java.util.Map;

@zzmb
public class zzig implements zzhx {
    private final zza zzHS;

    public interface zza {
        void zzb(zzok zzok);

        void zzcl();
    }

    public zzig(zza zza2) {
        this.zzHS = zza2;
    }

    public static void zza(zzqp zzqp, zza zza2) {
        zzqp.zzkV().zza("/reward", (zzhx) new zzig(zza2));
    }

    private void zzf(Map<String, String> map) {
        zzok zzok;
        try {
            int parseInt = Integer.parseInt(map.get("amount"));
            String str = map.get(ShareConstants.MEDIA_TYPE);
            if (!TextUtils.isEmpty(str)) {
                zzok = new zzok(str, parseInt);
                this.zzHS.zzb(zzok);
            }
        } catch (NumberFormatException e) {
            zzpe.zzc("Unable to parse reward amount.", e);
        }
        zzok = null;
        this.zzHS.zzb(zzok);
    }

    private void zzg(Map<String, String> map) {
        this.zzHS.zzcl();
    }

    public void zza(zzqp zzqp, Map<String, String> map) {
        String str = map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if ("grant".equals(str)) {
            zzf(map);
        } else if ("video_start".equals(str)) {
            zzg(map);
        }
    }
}
