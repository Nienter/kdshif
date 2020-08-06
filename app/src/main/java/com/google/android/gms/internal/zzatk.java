package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;

class zzatk extends BroadcastReceiver {
    static final String zzafu = zzatk.class.getName();
    private boolean zzafv;
    private boolean zzafw;
    /* access modifiers changed from: private */
    public final zzatp zzbpw;

    zzatk(zzatp zzatp) {
        zzac.zzw(zzatp);
        this.zzbpw = zzatp;
    }

    private Context getContext() {
        return this.zzbpw.getContext();
    }

    private zzati zzJt() {
        return this.zzbpw.zzJt();
    }

    @WorkerThread
    public boolean isRegistered() {
        this.zzbpw.zzmq();
        return this.zzafv;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        this.zzbpw.zznA();
        String action = intent.getAction();
        zzJt().zzLg().zzj("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean zzpA = this.zzbpw.zzLy().zzpA();
            if (this.zzafw != zzpA) {
                this.zzafw = zzpA;
                this.zzbpw.zzJs().zzm(new Runnable() {
                    public void run() {
                        zzatk.this.zzbpw.zzV(zzpA);
                    }
                });
                return;
            }
            return;
        }
        zzJt().zzLc().zzj("NetworkBroadcastReceiver received unknown action", action);
    }

    @WorkerThread
    public void unregister() {
        this.zzbpw.zznA();
        this.zzbpw.zzmq();
        if (isRegistered()) {
            zzJt().zzLg().log("Unregistering connectivity change receiver");
            this.zzafv = false;
            this.zzafw = false;
            try {
                getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                zzJt().zzLa().zzj("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @WorkerThread
    public void zzpx() {
        this.zzbpw.zznA();
        this.zzbpw.zzmq();
        if (!this.zzafv) {
            getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.zzafw = this.zzbpw.zzLy().zzpA();
            zzJt().zzLg().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzafw));
            this.zzafv = true;
        }
    }
}
