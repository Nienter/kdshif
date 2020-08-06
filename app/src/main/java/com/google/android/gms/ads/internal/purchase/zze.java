package com.google.android.gms.ads.internal.purchase;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.p001v4.view.PointerIconCompat;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.internal.zzky;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;

@zzmb
public class zze extends zzla.zza implements ServiceConnection {
    private final Activity mActivity;
    private zzb zzOR;
    zzh zzOS;
    private zzk zzOU;
    private Context zzOZ;
    private zzky zzPa;
    private zzf zzPb;
    private zzj zzPc;
    private String zzPd = null;

    public zze(Activity activity) {
        this.mActivity = activity;
        this.zzOS = zzh.zzq(this.mActivity.getApplicationContext());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001) {
            boolean z = false;
            try {
                int zzd = zzv.zzcX().zzd(intent);
                if (i2 == -1) {
                    zzv.zzcX();
                    if (zzd == 0) {
                        if (this.zzOU.zza(this.zzPd, i2, intent)) {
                            z = true;
                        }
                        this.zzPa.recordPlayBillingResolution(zzd);
                        this.mActivity.finish();
                        zza(this.zzPa.getProductId(), z, i2, intent);
                    }
                }
                this.zzOS.zza(this.zzPb);
                this.zzPa.recordPlayBillingResolution(zzd);
                this.mActivity.finish();
                zza(this.zzPa.getProductId(), z, i2, intent);
            } catch (RemoteException e) {
                zzpe.zzbe("Fail to process purchase result.");
                this.mActivity.finish();
            } finally {
                this.zzPd = null;
            }
        }
    }

    public void onCreate() {
        GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzPc = zzc.zzON;
        this.zzOU = zzc.zzvC;
        this.zzPa = zzc.zzOL;
        this.zzOR = new zzb(this.mActivity.getApplicationContext());
        this.zzOZ = zzc.zzOM;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            this.mActivity.setRequestedOrientation(zzv.zzcL().zzkp());
        } else {
            this.mActivity.setRequestedOrientation(zzv.zzcL().zzkq());
        }
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        zza.zzyc().zza((Context) this.mActivity, intent, (ServiceConnection) this, 1);
    }

    public void onDestroy() {
        zza.zzyc().zza(this.mActivity, this);
        this.zzOR.destroy();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zzOR.zzV(iBinder);
        try {
            this.zzPd = this.zzOU.zzis();
            Bundle zzb = this.zzOR.zzb(this.mActivity.getPackageName(), this.zzPa.getProductId(), this.zzPd);
            PendingIntent pendingIntent = (PendingIntent) zzb.getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                int zzd = zzv.zzcX().zzd(zzb);
                this.zzPa.recordPlayBillingResolution(zzd);
                zza(this.zzPa.getProductId(), false, zzd, null);
                this.mActivity.finish();
                return;
            }
            this.zzPb = new zzf(this.zzPa.getProductId(), this.zzPd);
            this.zzOS.zzb(this.zzPb);
            Integer num = 0;
            Integer num2 = 0;
            Integer num3 = 0;
            this.mActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), PointerIconCompat.TYPE_CONTEXT_MENU, new Intent(), num.intValue(), num2.intValue(), num3.intValue());
        } catch (IntentSender.SendIntentException | RemoteException e) {
            zzpe.zzc("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzpe.zzbd("In-app billing service disconnected.");
        this.zzOR.destroy();
    }

    /* access modifiers changed from: protected */
    public void zza(String str, boolean z, int i, Intent intent) {
        if (this.zzPc != null) {
            this.zzPc.zza(str, z, i, intent, this.zzPb);
        }
    }
}
