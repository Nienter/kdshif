package com.google.firebase.iid;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzh;
import java.io.IOException;

public class FirebaseInstanceIdService extends zzb {
    private static BroadcastReceiver zzciU;
    @VisibleForTesting
    static final Object zzciV = new Object();
    @VisibleForTesting
    static boolean zzciW = false;
    /* access modifiers changed from: private */
    public boolean zzciX = false;

    private String zzJ(Intent intent) {
        String stringExtra = intent.getStringExtra("subtype");
        return stringExtra == null ? "" : stringExtra;
    }

    private int zza(Intent intent, boolean z) {
        int intExtra = intent == null ? 10 : intent.getIntExtra("next_retry_delay_in_seconds", 0);
        if (intExtra < 10 && !z) {
            return 30;
        }
        if (intExtra < 10) {
            return 10;
        }
        if (intExtra > 28800) {
            return 28800;
        }
        return intExtra;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r0.zzjC(com.google.firebase.iid.zzd.zzbhg) != false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r3.zzaaf().zzaai() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        zzbV(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000a, code lost:
        r0 = r3.zzaad();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        if (r0 == null) goto L_0x0022;
     */
    static void zza(Context context, FirebaseInstanceId firebaseInstanceId) {
        synchronized (zzciV) {
            if (zzciW) {
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a4 A[Catch:{ IOException -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c1 A[SYNTHETIC, Splitter:B:59:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0087 A[SYNTHETIC] */
    private void zza(Intent intent, boolean z, boolean z2) {
        synchronized (zzciV) {
            zzciW = false;
        }
        if (zzf.zzbi(this) != null) {
            FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
            zzh.zza zzaad = instance.zzaad();
            if (zzaad == null || zzaad.zzjC(zzd.zzbhg)) {
                try {
                    String zzaae = instance.zzaae();
                    if (zzaae != null) {
                        if (this.zzciX) {
                            Log.d("FirebaseInstanceId", "get master token succeeded");
                        }
                        zza((Context) this, instance);
                        if (z2 || zzaad == null || (zzaad != null && !zzaae.equals(zzaad.zzbwP))) {
                            onTokenRefresh();
                            return;
                        }
                        return;
                    }
                    zzd(intent, "returned token is null");
                } catch (IOException e) {
                    zzd(intent, e.getMessage());
                } catch (SecurityException e2) {
                    Log.e("FirebaseInstanceId", "Unable to get master token", e2);
                }
            } else {
                zze zzaaf = instance.zzaaf();
                for (String zzaai = zzaaf.zzaai(); zzaai != null; zzaai = zzaaf.zzaai()) {
                    String[] split = zzaai.split("!");
                    if (split.length == 2) {
                        String str = split[0];
                        String str2 = split[1];
                        char c = 65535;
                        try {
                            switch (str.hashCode()) {
                                case 83:
                                    if (str.equals("S")) {
                                        c = 0;
                                    }
                                case 85:
                                    if (str.equals("U")) {
                                        c = 1;
                                    }
                                    switch (c) {
                                        case 0:
                                            FirebaseInstanceId.getInstance().zzjv(str2);
                                            if (!this.zzciX) {
                                                break;
                                            } else {
                                                Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                                                break;
                                            }
                                        case 1:
                                            FirebaseInstanceId.getInstance().zzjw(str2);
                                            if (!this.zzciX) {
                                                break;
                                            } else {
                                                Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                                                break;
                                            }
                                    }
                            }
                            switch (c) {
                                case 0:
                                    break;
                                case 1:
                                    break;
                            }
                        } catch (IOException e3) {
                            zzd(intent, e3.getMessage());
                            return;
                        }
                    }
                    zzaaf.zzjy(zzaai);
                }
                Log.d("FirebaseInstanceId", "topic sync succeeded");
            }
        }
    }

    private void zza(zzf zzf, Bundle bundle) {
        String zzbi = zzf.zzbi(this);
        if (zzbi == null) {
            Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
            return;
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        intent.setPackage(zzbi);
        intent.putExtras(bundle);
        zzf.zzs(intent);
        intent.putExtra("google.to", "google.com/iid");
        intent.putExtra("google.message_id", zzf.zzGz());
        sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    static void zzbV(Context context) {
        if (zzf.zzbi(context) != null) {
            synchronized (zzciV) {
                if (!zzciW) {
                    zzg.zzaaj().zzf(context, zzpR(0));
                    zzciW = true;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean zzbW(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void zzd(Intent intent, String str) {
        boolean zzbW = zzbW(this);
        final int zza = zza(intent, zzbW);
        Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(str).length() + 47).append("background sync failed: ").append(str).append(", retry in ").append(zza).append("s").toString());
        synchronized (zzciV) {
            zzpS(zza);
            zzciW = true;
        }
        if (!zzbW) {
            if (this.zzciX) {
                Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
            }
            if (zzciU == null) {
                zzciU = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        if (FirebaseInstanceIdService.zzbW(context)) {
                            if (FirebaseInstanceIdService.this.zzciX) {
                                Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
                            }
                            FirebaseInstanceIdService.this.getApplicationContext().unregisterReceiver(this);
                            zzg.zzaaj().zzf(context, FirebaseInstanceIdService.zzpR(zza));
                        }
                    }
                };
            }
            getApplicationContext().registerReceiver(zzciU, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    private zzd zzjx(String str) {
        if (str == null) {
            return zzd.zzb(this, null);
        }
        Bundle bundle = new Bundle();
        bundle.putString("subtype", str);
        return zzd.zzb(this, bundle);
    }

    /* access modifiers changed from: private */
    public static Intent zzpR(int i) {
        Intent intent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
        intent.putExtra("next_retry_delay_in_seconds", i);
        return intent;
    }

    private void zzpS(int i) {
        ((AlarmManager) getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + ((long) (i * 1000)), zzg.zza(this, 0, zzpR(i * 2), 134217728));
    }

    @WorkerThread
    public void onTokenRefresh() {
    }

    /* access modifiers changed from: protected */
    public Intent zzF(Intent intent) {
        return zzg.zzaaj().zzaak();
    }

    public boolean zzH(Intent intent) {
        this.zzciX = Log.isLoggable("FirebaseInstanceId", 3);
        if (intent.getStringExtra("error") == null && intent.getStringExtra("registration_id") == null) {
            return false;
        }
        String zzJ = zzJ(intent);
        if (this.zzciX) {
            String valueOf = String.valueOf(zzJ);
            Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Register result in service ".concat(valueOf) : new String("Register result in service "));
        }
        zzjx(zzJ).zzaah().zzv(intent);
        return true;
    }

    public void zzI(Intent intent) {
        String zzJ = zzJ(intent);
        zzd zzjx = zzjx(zzJ);
        String stringExtra = intent.getStringExtra("CMD");
        if (this.zzciX) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(zzJ).length() + 18 + String.valueOf(stringExtra).length() + String.valueOf(valueOf).length()).append("Service command ").append(zzJ).append(" ").append(stringExtra).append(" ").append(valueOf).toString());
        }
        if (intent.getStringExtra("unregistered") != null) {
            zzh zzaag = zzjx.zzaag();
            if (zzJ == null) {
                zzJ = "";
            }
            zzaag.zzeO(zzJ);
            zzjx.zzaah().zzv(intent);
        } else if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
            zzjx.zzaag().zzeO(zzJ);
            zza(intent, false, true);
        } else if ("RST".equals(stringExtra)) {
            zzjx.zzGu();
            zza(intent, true, true);
        } else if ("RST_FULL".equals(stringExtra)) {
            if (!zzjx.zzaag().isEmpty()) {
                zzjx.zzGu();
                zzjx.zzaag().zzGA();
                zza(intent, true, true);
            }
        } else if ("SYNC".equals(stringExtra)) {
            zzjx.zzaag().zzeO(zzJ);
            zza(intent, false, true);
        } else if ("PING".equals(stringExtra)) {
            zza(zzjx.zzaah(), intent.getExtras());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    public void zzm(Intent intent) {
        boolean z;
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        switch (action.hashCode()) {
            case -1737547627:
                if (action.equals("ACTION_TOKEN_REFRESH_RETRY")) {
                    z = false;
                    break;
                }
            default:
                z = true;
                break;
        }
        switch (z) {
            case false:
                zza(intent, false, false);
                return;
            default:
                zzI(intent);
                return;
        }
    }
}
