package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.internal.zzpp;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzmb
public class zzpm {
    private String zzXi = "";
    private String zzXj = "";
    private boolean zzXk = false;
    private final Object zzrN = new Object();

    private Uri zze(Context context, String str, String str2) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", zzM(context));
        buildUpon.appendQueryParameter("adSlotPath", str2);
        return buildUpon.build();
    }

    private void zzo(Context context, String str) {
        zzv.zzcJ().zza(context, zze(context, zzfx.zzES.get(), str));
    }

    public void zzI(boolean z) {
        synchronized (this.zzrN) {
            this.zzXk = z;
        }
    }

    public String zzM(Context context) {
        String str;
        synchronized (this.zzrN) {
            if (TextUtils.isEmpty(this.zzXi)) {
                this.zzXi = zzv.zzcJ().zzi(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty(this.zzXi)) {
                    this.zzXi = zzv.zzcJ().zzkk();
                    zzv.zzcJ().zzd(context, "debug_signals_id.txt", this.zzXi);
                }
            }
            str = this.zzXi;
        }
        return str;
    }

    public void zza(Context context, String str, String str2, String str3) {
        Uri.Builder buildUpon = zze(context, zzfx.zzEV.get(), str3).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        zzv.zzcJ().zzc(context, str, buildUpon.build().toString());
    }

    public void zzba(String str) {
        synchronized (this.zzrN) {
            this.zzXj = str;
        }
    }

    public void zzj(Context context, String str) {
        if (zzl(context, str)) {
            zzpe.zzbc("Device is linked for in app preview.");
        } else {
            zzo(context, str);
        }
    }

    public void zzk(Context context, String str) {
        if (zzm(context, str)) {
            zzpe.zzbc("Device is linked for debug signals.");
        } else {
            zzo(context, str);
        }
    }

    public String zzky() {
        String str;
        synchronized (this.zzrN) {
            str = this.zzXj;
        }
        return str;
    }

    public boolean zzkz() {
        boolean z;
        synchronized (this.zzrN) {
            z = this.zzXk;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean zzl(Context context, String str) {
        String zzn = zzn(context, zze(context, zzfx.zzET.get(), str).toString());
        if (TextUtils.isEmpty(zzn)) {
            zzpe.zzbc("Not linked for in app preview.");
            return false;
        }
        zzba(zzn.trim());
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean zzm(Context context, String str) {
        String zzn = zzn(context, zze(context, zzfx.zzEU.get(), str).toString());
        if (TextUtils.isEmpty(zzn)) {
            zzpe.zzbc("Not linked for debug signals.");
            return false;
        }
        boolean parseBoolean = Boolean.parseBoolean(zzn.trim());
        zzI(parseBoolean);
        return parseBoolean;
    }

    /* access modifiers changed from: protected */
    public String zzn(Context context, final String str) {
        zzqf zza = new zzpp(context).zza(str, new zzpp.zza<String>(this) {
            /* renamed from: zzi */
            public String zzh(InputStream inputStream) {
                try {
                    String str = new String(zzo.zza(inputStream, true), "UTF-8");
                    String str2 = str;
                    zzpe.zzbc(new StringBuilder(String.valueOf(str2).length() + 49 + String.valueOf(str).length()).append("Response received from server. \nURL: ").append(str2).append("\n Response: ").append(str).toString());
                    return str;
                } catch (IOException e) {
                    IOException iOException = e;
                    String valueOf = String.valueOf(str);
                    zzpe.zzc(valueOf.length() != 0 ? "Error connecting to url: ".concat(valueOf) : new String("Error connecting to url: "), iOException);
                    return null;
                }
            }

            /* renamed from: zzkA */
            public String zziT() {
                String valueOf = String.valueOf(str);
                zzpe.zzbe(valueOf.length() != 0 ? "Error getting a response from: ".concat(valueOf) : new String("Error getting a response from: "));
                return null;
            }
        });
        try {
            return (String) zza.get((long) zzfx.zzEW.get().intValue(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            TimeoutException timeoutException = e;
            String valueOf = String.valueOf(str);
            zzpe.zzb(valueOf.length() != 0 ? "Timeout while retriving a response from: ".concat(valueOf) : new String("Timeout while retriving a response from: "), timeoutException);
            zza.cancel(true);
        } catch (InterruptedException e2) {
            InterruptedException interruptedException = e2;
            String valueOf2 = String.valueOf(str);
            zzpe.zzb(valueOf2.length() != 0 ? "Interrupted while retriving a response from: ".concat(valueOf2) : new String("Interrupted while retriving a response from: "), interruptedException);
            zza.cancel(true);
        } catch (Exception e3) {
            Exception exc = e3;
            String valueOf3 = String.valueOf(str);
            zzpe.zzb(valueOf3.length() != 0 ? "Error retriving a response from: ".concat(valueOf3) : new String("Error retriving a response from: "), exc);
        }
        return null;
    }
}
