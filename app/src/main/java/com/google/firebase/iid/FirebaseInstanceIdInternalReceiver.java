package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.p001v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public final class FirebaseInstanceIdInternalReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (!(parcelableExtra instanceof Intent)) {
                Log.w("FirebaseInstanceId", "Missing or invalid wrapped intent");
            } else {
                zzg.zzaaj().zzb(context, intent.getAction(), (Intent) parcelableExtra);
            }
        }
    }
}
