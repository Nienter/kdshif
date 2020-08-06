package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzpy;

public class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzkr zzrA;

    private void zzbp() {
        if (this.zzrA != null) {
            try {
                this.zzrA.zzbp();
            } catch (RemoteException e) {
                zzpy.zzc("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zzrA.onActivityResult(i, i2, intent);
        } catch (Exception e) {
            zzpy.zzc("Could not forward onActivityResult to ad overlay:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        boolean z = true;
        try {
            if (this.zzrA != null) {
                z = this.zzrA.zzhk();
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onBackPressed to ad overlay:", e);
        }
        if (z) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzrA.zzn(zze.zzA(configuration));
        } catch (RemoteException e) {
            zzpy.zzc("Failed to wrap configuration.", e);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzrA = zzeh.zzeP().zzc((Activity) this);
        if (this.zzrA == null) {
            zzpy.zzbe("Could not create ad overlay.");
            finish();
            return;
        }
        try {
            this.zzrA.onCreate(bundle);
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onCreate to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            if (this.zzrA != null) {
                this.zzrA.onDestroy();
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        try {
            if (this.zzrA != null) {
                this.zzrA.onPause();
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        try {
            if (this.zzrA != null) {
                this.zzrA.onRestart();
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            if (this.zzrA != null) {
                this.zzrA.onResume();
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.zzrA != null) {
                this.zzrA.onSaveInstanceState(bundle);
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            if (this.zzrA != null) {
                this.zzrA.onStart();
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        try {
            if (this.zzrA != null) {
                this.zzrA.onStop();
            }
        } catch (RemoteException e) {
            zzpy.zzc("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    public void setContentView(int i) {
        super.setContentView(i);
        zzbp();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        zzbp();
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zzbp();
    }
}
