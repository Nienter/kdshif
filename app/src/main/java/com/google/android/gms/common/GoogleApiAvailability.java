package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.app.NotificationCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import com.google.android.gms.C0955R;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzaar;
import com.google.android.gms.internal.zzaax;
import com.google.android.gms.internal.zzabb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class GoogleApiAvailability extends zzc {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailability zzaxa = new GoogleApiAvailability();

    @SuppressLint({"HandlerLeak"})
    private class zza extends Handler {
        private final Context zzvZ;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zza(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.zzvZ = context.getApplicationContext();
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    int isGooglePlayServicesAvailable = GoogleApiAvailability.this.isGooglePlayServicesAvailable(this.zzvZ);
                    if (GoogleApiAvailability.this.isUserResolvableError(isGooglePlayServicesAvailable)) {
                        GoogleApiAvailability.this.showErrorNotification(this.zzvZ, isGooglePlayServicesAvailable);
                        return;
                    }
                    return;
                default:
                    Log.w("GoogleApiAvailability", new StringBuilder(50).append("Don't know how to handle this message: ").append(message.what).toString());
                    return;
            }
        }
    }

    GoogleApiAvailability() {
    }

    public static GoogleApiAvailability getInstance() {
        return zzaxa;
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2) {
        return getErrorDialog(activity, i, i2, null);
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        return zza((Context) activity, i, zzi.zza(activity, zzb(activity, i, "d"), i2), onCancelListener);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return super.getErrorResolutionPendingIntent(context, i, i2);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, ConnectionResult connectionResult) {
        return connectionResult.hasResolution() ? connectionResult.getResolution() : getErrorResolutionPendingIntent(context, connectionResult.getErrorCode(), 0);
    }

    public final String getErrorString(int i) {
        return super.getErrorString(i);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return super.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    public final boolean isUserResolvableError(int i) {
        return super.isUserResolvableError(i);
    }

    @MainThread
    public Task<Void> makeGooglePlayServicesAvailable(Activity activity) {
        zzac.zzdn("makeGooglePlayServicesAvailable must be called from the main thread");
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable == 0) {
            return Tasks.forResult(null);
        }
        zzabb zzu = zzabb.zzu(activity);
        zzu.zzk(new ConnectionResult(isGooglePlayServicesAvailable, null));
        return zzu.getTask();
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2) {
        return showErrorDialogFragment(activity, i, i2, null);
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog errorDialog = getErrorDialog(activity, i, i2, onCancelListener);
        if (errorDialog == null) {
            return false;
        }
        zza(activity, errorDialog, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }

    public void showErrorNotification(Context context, int i) {
        zza(context, i, (String) null);
    }

    public void showErrorNotification(Context context, ConnectionResult connectionResult) {
        zza(context, connectionResult.getErrorCode(), (String) null, getErrorResolutionPendingIntent(context, connectionResult));
    }

    public Dialog zza(Activity activity, DialogInterface.OnCancelListener onCancelListener) {
        ProgressBar progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(progressBar);
        builder.setMessage(zzh.zzi(activity, 18));
        builder.setPositiveButton("", null);
        AlertDialog create = builder.create();
        zza(activity, (Dialog) create, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return create;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public Dialog zza(Context context, int i, zzi zzi, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder = null;
        if (i == 0) {
            return null;
        }
        if (zzs.zzyA()) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16843529, typedValue, true);
            if ("Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new AlertDialog.Builder(context, 5);
            }
        }
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(zzh.zzi(context, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String zzk = zzh.zzk(context, i);
        if (zzk != null) {
            builder.setPositiveButton(zzk, zzi);
        }
        String zzg = zzh.zzg(context, i);
        if (zzg != null) {
            builder.setTitle(zzg);
        }
        return builder.create();
    }

    @Nullable
    public PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        return super.zza(context, i, i2, str);
    }

    @Nullable
    public zzaar zza(Context context, zzaar.zza zza2) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        zzaar zzaar = new zzaar(zza2);
        context.registerReceiver(zzaar, intentFilter);
        zzaar.setContext(context);
        if (zzs(context, "com.google.android.gms")) {
            return zzaar;
        }
        zza2.zzvb();
        zzaar.unregister();
        return null;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(11)
    public void zza(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        boolean z;
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
            z = false;
        }
        if (z) {
            SupportErrorDialogFragment.newInstance(dialog, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), str);
        } else if (zzs.zzyx()) {
            ErrorDialogFragment.newInstance(dialog, onCancelListener).show(activity.getFragmentManager(), str);
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
    }

    public void zza(Context context, int i, String str) {
        zza(context, i, str, zza(context, i, 0, "n"));
    }

    /* access modifiers changed from: package-private */
    @TargetApi(20)
    public void zza(Context context, int i, String str, PendingIntent pendingIntent) {
        Notification build;
        int i2;
        if (i == 18) {
            zzal(context);
        } else if (pendingIntent != null) {
            String zzh = zzh.zzh(context, i);
            String zzj = zzh.zzj(context, i);
            Resources resources = context.getResources();
            if (com.google.android.gms.common.util.zzi.zzaJ(context)) {
                zzac.zzar(zzs.zzyG());
                build = new Notification.Builder(context).setSmallIcon(context.getApplicationInfo().icon).setPriority(2).setAutoCancel(true).setContentTitle(zzh).setStyle(new Notification.BigTextStyle().bigText(zzj)).addAction(C0955R.C0956drawable.common_full_open_on_phone, resources.getString(C0955R.string.common_open_on_phone), pendingIntent).build();
            } else {
                build = new NotificationCompat.Builder(context).setSmallIcon(17301642).setTicker(resources.getString(C0955R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(zzh).setContentText(zzj).setLocalOnly(true).setStyle(new NotificationCompat.BigTextStyle().bigText(zzj)).build();
            }
            switch (i) {
                case 1:
                case 2:
                case 3:
                    zze.zzaxp.set(false);
                    i2 = 10436;
                    break;
                default:
                    i2 = 39789;
                    break;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (str == null) {
                notificationManager.notify(i2, build);
            } else {
                notificationManager.notify(str, i2, build);
            }
        } else if (i == 6) {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        }
    }

    public void zza(Context context, ConnectionResult connectionResult, int i) {
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            zza(context, connectionResult.getErrorCode(), (String) null, GoogleApiActivity.zza(context, errorResolutionPendingIntent, i));
        }
    }

    public boolean zza(Activity activity, @NonNull zzaax zzaax, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog zza2 = zza((Context) activity, i, zzi.zza(zzaax, zzb(activity, i, "d"), i2), onCancelListener);
        if (zza2 == null) {
            return false;
        }
        zza(activity, zza2, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }

    public int zzak(Context context) {
        return super.zzak(context);
    }

    /* access modifiers changed from: package-private */
    public void zzal(Context context) {
        new zza(context).sendEmptyMessageDelayed(1, 120000);
    }

    @Nullable
    public Intent zzb(Context context, int i, @Nullable String str) {
        return super.zzb(context, i, str);
    }

    @Nullable
    @Deprecated
    public Intent zzcr(int i) {
        return super.zzcr(i);
    }

    public boolean zzd(Context context, int i) {
        return super.zzd(context, i);
    }
}
