package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.support.p001v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    @VisibleForTesting
    final ExecutorService zzbFy = Executors.newSingleThreadExecutor();
    private int zzbfI;
    private int zzbfJ = 0;
    MessengerCompat zzbhh = new MessengerCompat((Handler) new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            int zzc = MessengerCompat.zzc(message);
            zzf.zzbi(zzb.this);
            zzb.this.getPackageManager();
            if (zzc == zzf.zzbhs || zzc == zzf.zzbhr) {
                zzb.this.zzm((Intent) message.obj);
                return;
            }
            int i = zzf.zzbhr;
            Log.w("FirebaseInstanceId", new StringBuilder(77).append("Message from unexpected caller ").append(zzc).append(" mine=").append(i).append(" appid=").append(zzf.zzbhs).toString());
        }
    });
    private final Object zzrN = new Object();

    /* access modifiers changed from: private */
    public void zzG(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.zzrN) {
            this.zzbfJ--;
            if (this.zzbfJ == 0) {
                zzjr(this.zzbfI);
            }
        }
    }

    public final IBinder onBind(Intent intent) {
        if (intent == null || !"com.google.firebase.INSTANCE_ID_EVENT".equals(intent.getAction())) {
            return null;
        }
        return this.zzbhh.getBinder();
    }

    public final int onStartCommand(final Intent intent, int i, int i2) {
        synchronized (this.zzrN) {
            this.zzbfI = i2;
            this.zzbfJ++;
        }
        final Intent zzF = zzF(intent);
        if (zzF == null) {
            zzG(intent);
            return 2;
        } else if (zzH(zzF)) {
            zzG(intent);
            return 2;
        } else {
            this.zzbFy.execute(new Runnable() {
                public void run() {
                    zzb.this.zzm(zzF);
                    zzb.this.zzG(intent);
                }
            });
            return 3;
        }
    }

    /* access modifiers changed from: protected */
    public abstract Intent zzF(Intent intent);

    public boolean zzH(Intent intent) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean zzjr(int i) {
        return stopSelfResult(i);
    }

    public abstract void zzm(Intent intent);
}
