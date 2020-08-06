package p005b.p006a.p007a.p008a.p009a.p011b;

import android.content.Context;
import android.text.TextUtils;
import p005b.p006a.p007a.p008a.Fabric;

/* renamed from: b.a.a.a.a.b.o */
public class FirebaseInfo {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo8287a(Context context) {
        int a = CommonUtils.m122a(context, "google_app_id", "string");
        if (a == 0) {
            return null;
        }
        Fabric.m456h().mo8506a("Fabric", "Generating Crashlytics ApiKey from google_app_id in Strings");
        return mo8288a(context.getResources().getString(a));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo8288a(String str) {
        return CommonUtils.m149b(str).substring(0, 40);
    }

    /* renamed from: b */
    public boolean mo8289b(Context context) {
        boolean z;
        if (CommonUtils.m143a(context, "com.crashlytics.useFirebaseAppId", false)) {
            return true;
        }
        boolean z2 = CommonUtils.m122a(context, "google_app_id", "string") != 0;
        if (!TextUtils.isEmpty(new ApiKey().mo8274c(context)) || !TextUtils.isEmpty(new ApiKey().mo8275d(context))) {
            z = true;
        } else {
            z = false;
        }
        if (!z2 || z) {
            return false;
        }
        return true;
    }
}
