package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzabb extends zzzw {
    private TaskCompletionSource<Void> zzayo = new TaskCompletionSource<>();

    private zzabb(zzaax zzaax) {
        super(zzaax);
        this.zzaBs.zza("GmsAvailabilityHelper", (zzaaw) this);
    }

    public static zzabb zzu(Activity activity) {
        zzaax zzs = zzs(activity);
        zzabb zzabb = (zzabb) zzs.zza("GmsAvailabilityHelper", zzabb.class);
        if (zzabb == null) {
            return new zzabb(zzs);
        }
        if (!zzabb.zzayo.getTask().isComplete()) {
            return zzabb;
        }
        zzabb.zzayo = new TaskCompletionSource<>();
        return zzabb;
    }

    public Task<Void> getTask() {
        return this.zzayo.getTask();
    }

    public void onDestroy() {
        super.onDestroy();
        this.zzayo.setException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        this.zzayo.setException(zzb.zzl(connectionResult));
    }

    public void zzk(ConnectionResult connectionResult) {
        zzb(connectionResult, 0);
    }

    /* access modifiers changed from: protected */
    public void zzuW() {
        int isGooglePlayServicesAvailable = this.zzaxX.isGooglePlayServicesAvailable(this.zzaBs.zzwo());
        if (isGooglePlayServicesAvailable == 0) {
            this.zzayo.setResult(null);
        } else {
            zzk(new ConnectionResult(isGooglePlayServicesAvailable, null));
        }
    }
}
