package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zza implements ServiceConnection {
    boolean zzawV = false;
    private final BlockingQueue<IBinder> zzawW = new LinkedBlockingQueue();

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zzawW.add(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public IBinder zza(long j, TimeUnit timeUnit) {
        zzac.zzdo("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.zzawV) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zzawV = true;
        IBinder poll = this.zzawW.poll(j, timeUnit);
        if (poll != null) {
            return poll;
        }
        throw new TimeoutException("Timed out waiting for the service connection");
    }

    public IBinder zzuy() {
        zzac.zzdo("BlockingServiceConnection.getService() called on main thread");
        if (this.zzawV) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zzawV = true;
        return this.zzawW.take();
    }
}
