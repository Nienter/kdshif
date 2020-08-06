package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.internal.zzaar;

public abstract class zzzw extends zzaaw implements DialogInterface.OnCancelListener {
    protected boolean mStarted;
    protected final GoogleApiAvailability zzaxX;
    protected boolean zzayG;
    /* access modifiers changed from: private */
    public ConnectionResult zzayH;
    /* access modifiers changed from: private */
    public int zzayI;
    private final Handler zzayJ;

    private class zza implements Runnable {
        private zza() {
        }

        @MainThread
        public void run() {
            if (zzzw.this.mStarted) {
                if (zzzw.this.zzayH.hasResolution()) {
                    zzzw.this.zzaBs.startActivityForResult(GoogleApiActivity.zzb(zzzw.this.getActivity(), zzzw.this.zzayH.getResolution(), zzzw.this.zzayI, false), 1);
                } else if (zzzw.this.zzaxX.isUserResolvableError(zzzw.this.zzayH.getErrorCode())) {
                    zzzw.this.zzaxX.zza(zzzw.this.getActivity(), zzzw.this.zzaBs, zzzw.this.zzayH.getErrorCode(), 2, zzzw.this);
                } else if (zzzw.this.zzayH.getErrorCode() == 18) {
                    final Dialog zza = zzzw.this.zzaxX.zza(zzzw.this.getActivity(), (DialogInterface.OnCancelListener) zzzw.this);
                    zzzw.this.zzaxX.zza(zzzw.this.getActivity().getApplicationContext(), (zzaar.zza) new zzaar.zza() {
                        public void zzvb() {
                            zzzw.this.zzva();
                            if (zza.isShowing()) {
                                zza.dismiss();
                            }
                        }
                    });
                } else {
                    zzzw.this.zza(zzzw.this.zzayH, zzzw.this.zzayI);
                }
            }
        }
    }

    protected zzzw(zzaax zzaax) {
        this(zzaax, GoogleApiAvailability.getInstance());
    }

    zzzw(zzaax zzaax, GoogleApiAvailability googleApiAvailability) {
        super(zzaax);
        this.zzayI = -1;
        this.zzayJ = new Handler(Looper.getMainLooper());
        this.zzaxX = googleApiAvailability;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = true;
        switch (i) {
            case 1:
                if (i2 != -1) {
                    if (i2 == 0) {
                        this.zzayH = new ConnectionResult(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null);
                    }
                }
                break;
            case 2:
                int isGooglePlayServicesAvailable = this.zzaxX.isGooglePlayServicesAvailable(getActivity());
                if (isGooglePlayServicesAvailable != 0) {
                    z = false;
                }
                if (this.zzayH.getErrorCode() == 18 && isGooglePlayServicesAvailable == 18) {
                    return;
                }
            default:
                z = false;
                break;
        }
        if (z) {
            zzva();
        } else {
            zza(this.zzayH, this.zzayI);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), this.zzayI);
        zzva();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzayG = bundle.getBoolean("resolving_error", false);
            if (this.zzayG) {
                this.zzayI = bundle.getInt("failed_client_id", -1);
                this.zzayH = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.zzayG);
        if (this.zzayG) {
            bundle.putInt("failed_client_id", this.zzayI);
            bundle.putInt("failed_status", this.zzayH.getErrorCode());
            bundle.putParcelable("failed_resolution", this.zzayH.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(ConnectionResult connectionResult, int i);

    public void zzb(ConnectionResult connectionResult, int i) {
        if (!this.zzayG) {
            this.zzayG = true;
            this.zzayI = i;
            this.zzayH = connectionResult;
            this.zzayJ.post(new zza());
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzuW();

    /* access modifiers changed from: protected */
    public void zzva() {
        this.zzayI = -1;
        this.zzayG = false;
        this.zzayH = null;
        zzuW();
    }
}
