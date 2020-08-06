package com.crashlytics.android;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p011b.FirebaseInfo;

public class CrashlyticsInitProvider extends ContentProvider {
    private static final String TAG = "CrashlyticsInitProvider";

    interface EnabledCheckStrategy {
        boolean isCrashlyticsEnabled(Context context);
    }

    public boolean onCreate() {
        Context context = getContext();
        if (shouldInitializeFabric(context, new FirebaseInfo(), new ManifestEnabledCheckStrategy())) {
            try {
                Fabric.m446a(context, new Crashlytics());
                Fabric.m456h().mo8511c(TAG, "CrashlyticsInitProvider initialization successful");
            } catch (IllegalStateException e) {
                Fabric.m456h().mo8511c(TAG, "CrashlyticsInitProvider initialization unsuccessful");
                return false;
            }
        }
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldInitializeFabric(Context context, FirebaseInfo oVar, EnabledCheckStrategy enabledCheckStrategy) {
        return oVar.mo8289b(context) && enabledCheckStrategy.isCrashlyticsEnabled(context);
    }
}
