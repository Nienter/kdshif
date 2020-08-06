package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.p001v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.zzh;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> zzbha = new ArrayMap();
    private static zze zzciQ;
    private final FirebaseApp zzciR;
    private final zzd zzciS;
    private final String zzciT = zzaac();

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzd zzd) {
        this.zzciR = firebaseApp;
        this.zzciS = zzd;
        if (this.zzciT == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        FirebaseInstanceIdService.zza(this.zzciR.getApplicationContext(), this);
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = zzbha.get(firebaseApp.getOptions().getApplicationId());
            if (firebaseInstanceId == null) {
                zzd zzb = zzd.zzb(firebaseApp.getApplicationContext(), null);
                if (zzciQ == null) {
                    zzciQ = new zze(zzb.zzaag());
                }
                firebaseInstanceId = new FirebaseInstanceId(firebaseApp, zzb);
                zzbha.put(firebaseApp.getOptions().getApplicationId(), firebaseInstanceId);
            }
        }
        return firebaseInstanceId;
    }

    static int zzK(Context context, String str) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to find package ").append(valueOf).toString());
            return z;
        }
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) (((digest[0] & 15) + 112) & 255);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static void zza(Context context, zzh zzh) {
        zzh.zzGA();
        Intent intent = new Intent();
        intent.putExtra("CMD", "RST");
        zzg.zzaaj().zzf(context, intent);
    }

    static String zzbT(Context context) {
        return getInstance().zzciR.getOptions().getApplicationId();
    }

    static int zzbU(Context context) {
        return zzK(context, context.getPackageName());
    }

    static String zzbg(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static void zzbh(Context context) {
        Intent intent = new Intent();
        intent.putExtra("CMD", "SYNC");
        zzg.zzaaj().zzf(context, intent);
    }

    static String zzv(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public void deleteInstanceId() {
        this.zzciS.zzb("*", "*", null);
        this.zzciS.zzGu();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) {
        this.zzciS.zzb(str, str2, null);
    }

    public long getCreationTime() {
        return this.zzciS.getCreationTime();
    }

    public String getId() {
        return zza(this.zzciS.zzGt());
    }

    @Nullable
    public String getToken() {
        zzh.zza zzaad = zzaad();
        if (zzaad == null || zzaad.zzjC(zzd.zzbhg)) {
            FirebaseInstanceIdService.zzbV(this.zzciR.getApplicationContext());
        }
        if (zzaad != null) {
            return zzaad.zzbwP;
        }
        return null;
    }

    @WorkerThread
    public String getToken(String str, String str2) {
        return this.zzciS.getToken(str, str2, null);
    }

    /* access modifiers changed from: package-private */
    public String zzaac() {
        String gcmSenderId = this.zzciR.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = this.zzciR.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            return applicationId;
        }
        String[] split = applicationId.split(":");
        if (split.length < 2) {
            return null;
        }
        String str = split[1];
        if (str.isEmpty()) {
            return null;
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzh.zza zzaad() {
        return this.zzciS.zzaag().zzq("", this.zzciT, "*");
    }

    /* access modifiers changed from: package-private */
    public String zzaae() {
        return getToken(this.zzciT, "*");
    }

    /* access modifiers changed from: package-private */
    public zze zzaaf() {
        return zzciQ;
    }

    public void zzju(String str) {
        zzciQ.zzju(str);
        FirebaseInstanceIdService.zzbV(this.zzciR.getApplicationContext());
    }

    /* access modifiers changed from: package-private */
    public void zzjv(String str) {
        zzh.zza zzaad = zzaad();
        if (zzaad == null || zzaad.zzjC(zzd.zzbhg)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzd zzd = this.zzciS;
        String str2 = zzaad.zzbwP;
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str);
        zzd.getToken(str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle);
    }

    /* access modifiers changed from: package-private */
    public void zzjw(String str) {
        zzh.zza zzaad = zzaad();
        if (zzaad == null || zzaad.zzjC(zzd.zzbhg)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzd zzd = this.zzciS;
        String str2 = zzaad.zzbwP;
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str);
        zzd.zzb(str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle);
    }
}
