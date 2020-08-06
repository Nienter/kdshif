package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.internal.zzix;
import java.util.concurrent.Future;

@zzmb
public class zziy {

    private static class zza<JavascriptEngine> extends zzqc<JavascriptEngine> {
        JavascriptEngine zzIW;

        private zza() {
        }
    }

    /* access modifiers changed from: private */
    public zzix zza(Context context, zzqa zzqa, final zza<zzix> zza2, zzav zzav, zzd zzd) {
        JavascriptEngine zziz = new zziz(context, zzqa, zzav, zzd);
        zza2.zzIW = zziz;
        zziz.zza(new zzix.zza(this) {
            public void zzgu() {
                zza2.zzh((zzix) zza2.zzIW);
            }
        });
        return zziz;
    }

    public Future<zzix> zza(Context context, zzqa zzqa, String str, zzav zzav, zzd zzd) {
        final zza zza2 = new zza();
        final Context context2 = context;
        final zzqa zzqa2 = zzqa;
        final zzav zzav2 = zzav;
        final zzd zzd2 = zzd;
        final String str2 = str;
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                zziy.this.zza(context2, zzqa2, (zza<zzix>) zza2, zzav2, zzd2).zzam(str2);
            }
        });
        return zza2;
    }
}
