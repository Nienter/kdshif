package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzaxv;

public class zzaxy extends zzl<zzaxv> implements zzaxn {
    private Integer zzaEe;
    private final zzg zzazs;
    private final Bundle zzbCf;
    private final boolean zzbCq;

    public zzaxy(Context context, Looper looper, boolean z, zzg zzg, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzbCq = z;
        this.zzazs = zzg;
        this.zzbCf = bundle;
        this.zzaEe = zzg.zzxl();
    }

    public zzaxy(Context context, Looper looper, boolean z, zzg zzg, zzaxo zzaxo, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, z, zzg, zza(zzg), connectionCallbacks, onConnectionFailedListener);
    }

    private zzad zzOn() {
        Account zzwU = this.zzazs.zzwU();
        GoogleSignInAccount googleSignInAccount = null;
        if ("<<default account>>".equals(zzwU.name)) {
            googleSignInAccount = com.google.android.gms.auth.api.signin.internal.zzl.zzaa(getContext()).zzrc();
        }
        return new zzad(zzwU, this.zzaEe.intValue(), googleSignInAccount);
    }

    public static Bundle zza(zzg zzg) {
        zzaxo zzxk = zzg.zzxk();
        Integer zzxl = zzg.zzxl();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", zzg.getAccount());
        if (zzxl != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zzxl.intValue());
        }
        if (zzxk != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzxk.zzOf());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzxk.zzqK());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzxk.zzqN());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzxk.zzqM());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzxk.zzqO());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzxk.zzOg());
            if (zzxk.zzOh() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzxk.zzOh().longValue());
            }
            if (zzxk.zzOi() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzxk.zzOi().longValue());
            }
        }
        return bundle;
    }

    public void connect() {
        zza((zzf.C1686zzf) new zzf.zzi());
    }

    public void zzOe() {
        try {
            ((zzaxv) zzwW()).zzmK(this.zzaEe.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public void zza(zzr zzr, boolean z) {
        try {
            ((zzaxv) zzwW()).zza(zzr, this.zzaEe.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void zza(zzaxu zzaxu) {
        zzac.zzb(zzaxu, (Object) "Expecting a valid ISignInCallbacks");
        try {
            ((zzaxv) zzwW()).zza(new zzaxz(zzOn()), zzaxu);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzaxu.zzb(new zzayb(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzeZ */
    public zzaxv zzh(IBinder iBinder) {
        return zzaxv.zza.zzeY(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeu() {
        return "com.google.android.gms.signin.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzev() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    public boolean zzqD() {
        return this.zzbCq;
    }

    /* access modifiers changed from: protected */
    public Bundle zzql() {
        if (!getContext().getPackageName().equals(this.zzazs.zzxh())) {
            this.zzbCf.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzazs.zzxh());
        }
        return this.zzbCf;
    }
}
