package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.VisibleForTesting;
import android.support.p001v4.content.WakefulBroadcastReceiver;
import android.support.p001v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

public class zzg {
    private static zzg zzcje;
    private final SimpleArrayMap<String, String> zzcjf = new SimpleArrayMap<>();
    private Boolean zzcjg = null;
    @VisibleForTesting
    final Queue<Intent> zzcjh = new LinkedList();
    @VisibleForTesting
    final Queue<Intent> zzcji = new LinkedList();

    private zzg() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return zza(context, i, "com.google.firebase.INSTANCE_ID_EVENT", intent, i2);
    }

    private static PendingIntent zza(Context context, int i, String str, Intent intent, int i2) {
        Intent intent2 = new Intent(context, FirebaseInstanceIdInternalReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return PendingIntent.getBroadcast(context, i, intent2, i2);
    }

    public static synchronized zzg zzaaj() {
        zzg zzg;
        synchronized (zzg.class) {
            if (zzcje == null) {
                zzcje = new zzg();
            }
            zzg = zzcje;
        }
        return zzg;
    }

    public static PendingIntent zzb(Context context, int i, Intent intent, int i2) {
        return zza(context, i, "com.google.firebase.MESSAGING_EVENT", intent, i2);
    }

    private boolean zzbY(Context context) {
        if (this.zzcjg == null) {
            this.zzcjg = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        return this.zzcjg.booleanValue();
    }

    private void zze(Context context, Intent intent) {
        String str;
        synchronized (this.zzcjf) {
            str = this.zzcjf.get(intent.getAction());
        }
        if (str == null) {
            ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null || resolveService.serviceInfo == null) {
                Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
                return;
            }
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
                String valueOf = String.valueOf(serviceInfo.packageName);
                String valueOf2 = String.valueOf(serviceInfo.name);
                Log.e("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 94 + String.valueOf(valueOf2).length()).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(valueOf).append("/").append(valueOf2).toString());
                return;
            }
            str = serviceInfo.name;
            if (str.startsWith(".")) {
                String valueOf3 = String.valueOf(context.getPackageName());
                String valueOf4 = String.valueOf(str);
                str = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
            }
            synchronized (this.zzcjf) {
                this.zzcjf.put(intent.getAction(), str);
            }
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf5 = String.valueOf(str);
            Log.d("FirebaseInstanceId", valueOf5.length() != 0 ? "Restricting intent to a specific service: ".concat(valueOf5) : new String("Restricting intent to a specific service: "));
        }
        intent.setClassName(context.getPackageName(), str);
    }

    private int zzg(Context context, Intent intent) {
        ComponentName startService;
        zze(context, intent);
        try {
            if (zzbY(context)) {
                startService = WakefulBroadcastReceiver.startWakefulService(context, intent);
            } else {
                startService = context.startService(intent);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }
            if (startService != null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (SecurityException e) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e);
            return 401;
        }
    }

    public Intent zzaak() {
        return this.zzcjh.poll();
    }

    public Intent zzaal() {
        return this.zzcji.poll();
    }

    public int zzb(Context context, String str, Intent intent) {
        char c = 65535;
        switch (str.hashCode()) {
            case -842411455:
                if (str.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
                    c = 0;
                    break;
                }
                break;
            case 41532704:
                if (str.equals("com.google.firebase.MESSAGING_EVENT")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.zzcjh.offer(intent);
                break;
            case 1:
                this.zzcji.offer(intent);
                break;
            default:
                String valueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", valueOf.length() != 0 ? "Unknown service action: ".concat(valueOf) : new String("Unknown service action: "));
                return 500;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzg(context, intent2);
    }

    public void zzf(Context context, Intent intent) {
        zzb(context, "com.google.firebase.INSTANCE_ID_EVENT", intent);
    }
}
