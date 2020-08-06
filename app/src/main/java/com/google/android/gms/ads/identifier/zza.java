package com.google.android.gms.ads.identifier;

import android.support.annotation.WorkerThread;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class zza {
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @WorkerThread
    public void zzu(String str) {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                Log.w("HttpUrlPinger", new StringBuilder(String.valueOf(str).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(str).toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("HttpUrlPinger", new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf).length()).append("Error while parsing ping URL: ").append(str).append(". ").append(valueOf).toString(), e);
        } catch (IOException | RuntimeException e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.w("HttpUrlPinger", new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(valueOf2).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf2).toString(), e2);
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
