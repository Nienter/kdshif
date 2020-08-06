package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.internal.zzpx;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzmb
public class zzpz implements zzpx.zza {
    @Nullable
    private final String zzHY;

    public zzpz() {
        this(null);
    }

    public zzpz(@Nullable String str) {
        this.zzHY = str;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @WorkerThread
    public void zzu(String str) {
        HttpURLConnection httpURLConnection;
        try {
            String valueOf = String.valueOf(str);
            zzpy.zzbc(valueOf.length() != 0 ? "Pinging URL: ".concat(valueOf) : new String("Pinging URL: "));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzeh.zzeO().zza(true, httpURLConnection, this.zzHY);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                zzpy.zzbe(new StringBuilder(String.valueOf(str).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(str).toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            String valueOf2 = String.valueOf(e.getMessage());
            zzpy.zzbe(new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf2).length()).append("Error while parsing ping URL: ").append(str).append(". ").append(valueOf2).toString());
        } catch (IOException e2) {
            String valueOf3 = String.valueOf(e2.getMessage());
            zzpy.zzbe(new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(valueOf3).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf3).toString());
        } catch (RuntimeException e3) {
            String valueOf4 = String.valueOf(e3.getMessage());
            zzpy.zzbe(new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(valueOf4).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf4).toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
