package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.overlay.zzl;
import java.util.Map;
import org.json.JSONObject;

@zzmb
public final class zzih implements zzhx {
    private boolean zzHT;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            return zzeh.zzeO().zzb(context, Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            zzpe.zzbe(new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length()).append("Could not parse ").append(str).append(" in a video GMSG: ").append(str2).toString());
            return i;
        }
    }

    public void zza(zzqp zzqp, Map<String, String> map) {
        int i;
        int i2;
        String str = map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if (str == null) {
            zzpe.zzbe("Action missing from video GMSG.");
            return;
        }
        if (zzpe.zzai(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String valueOf = String.valueOf(jSONObject.toString());
            zzpe.zzbc(new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(valueOf).length()).append("Video GMSG: ").append(str).append(" ").append(valueOf).toString());
        }
        if ("background".equals(str)) {
            String str2 = map.get("color");
            if (TextUtils.isEmpty(str2)) {
                zzpe.zzbe("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzqp.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException e) {
                zzpe.zzbe("Invalid color parameter in video GMSG.");
            }
        } else {
            zzqo zzld = zzqp.zzld();
            if (zzld == null) {
                zzpe.zzbe("Could not get underlay container for a video GMSG.");
                return;
            }
            boolean equals = "new".equals(str);
            boolean equals2 = "position".equals(str);
            if (equals || equals2) {
                Context context = zzqp.getContext();
                int zza = zza(context, map, "x", 0);
                int zza2 = zza(context, map, "y", 0);
                int zza3 = zza(context, map, "w", -1);
                int zza4 = zza(context, map, "h", -1);
                if (zzfx.zzEb.get().booleanValue()) {
                    i = Math.min(zza3, zzqp.getMeasuredWidth() - zza);
                    zza4 = Math.min(zza4, zzqp.getMeasuredHeight() - zza2);
                } else {
                    i = zza3;
                }
                try {
                    i2 = Integer.parseInt(map.get("player"));
                } catch (NumberFormatException e2) {
                    i2 = 0;
                }
                boolean parseBoolean = Boolean.parseBoolean(map.get("spherical"));
                if (!equals || zzld.zzkO() != null) {
                    zzld.zze(zza, zza2, i, zza4);
                } else {
                    zzld.zza(zza, zza2, i, zza4, i2, parseBoolean);
                }
            } else {
                zzl zzkO = zzld.zzkO();
                if (zzkO == null) {
                    zzl.zzi(zzqp);
                } else if ("click".equals(str)) {
                    Context context2 = zzqp.getContext();
                    int zza5 = zza(context2, map, "x", 0);
                    int zza6 = zza(context2, map, "y", 0);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza5, (float) zza6, 0);
                    zzkO.zzf(obtain);
                    obtain.recycle();
                } else if ("currentTime".equals(str)) {
                    String str3 = map.get("time");
                    if (str3 == null) {
                        zzpe.zzbe("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        zzkO.seekTo((int) (Float.parseFloat(str3) * 1000.0f));
                    } catch (NumberFormatException e3) {
                        String valueOf2 = String.valueOf(str3);
                        zzpe.zzbe(valueOf2.length() != 0 ? "Could not parse time parameter from currentTime video GMSG: ".concat(valueOf2) : new String("Could not parse time parameter from currentTime video GMSG: "));
                    }
                } else if ("hide".equals(str)) {
                    zzkO.setVisibility(4);
                } else if ("load".equals(str)) {
                    zzkO.zzgq();
                } else if ("muted".equals(str)) {
                    if (Boolean.parseBoolean(map.get("muted"))) {
                        zzkO.zzhE();
                    } else {
                        zzkO.zzhF();
                    }
                } else if ("pause".equals(str)) {
                    zzkO.pause();
                } else if ("play".equals(str)) {
                    zzkO.play();
                } else if ("show".equals(str)) {
                    zzkO.setVisibility(0);
                } else if ("src".equals(str)) {
                    zzkO.zzaB(map.get("src"));
                } else if ("touchMove".equals(str)) {
                    Context context3 = zzqp.getContext();
                    zzkO.zza((float) zza(context3, map, "dx", 0), (float) zza(context3, map, "dy", 0));
                    if (!this.zzHT) {
                        zzqp.zzkT().zzhq();
                        this.zzHT = true;
                    }
                } else if ("volume".equals(str)) {
                    String str4 = map.get("volume");
                    if (str4 == null) {
                        zzpe.zzbe("Level parameter missing from volume video GMSG.");
                        return;
                    }
                    try {
                        zzkO.zzb(Float.parseFloat(str4));
                    } catch (NumberFormatException e4) {
                        String valueOf3 = String.valueOf(str4);
                        zzpe.zzbe(valueOf3.length() != 0 ? "Could not parse volume parameter from volume video GMSG: ".concat(valueOf3) : new String("Could not parse volume parameter from volume video GMSG: "));
                    }
                } else if ("watermark".equals(str)) {
                    zzkO.zzhG();
                } else {
                    String valueOf4 = String.valueOf(str);
                    zzpe.zzbe(valueOf4.length() != 0 ? "Unknown video action: ".concat(valueOf4) : new String("Unknown video action: "));
                }
            }
        }
    }
}
