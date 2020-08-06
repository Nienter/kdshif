package p005b.p006a.p007a.p008a.p009a.p011b;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import p005b.p006a.p007a.p008a.Fabric;

/* renamed from: b.a.a.a.a.b.g */
public class ApiKey {
    /* renamed from: a */
    public String mo8272a(Context context) {
        String c = mo8274c(context);
        if (TextUtils.isEmpty(c)) {
            c = mo8275d(context);
        }
        if (TextUtils.isEmpty(c)) {
            c = mo8273b(context);
        }
        if (TextUtils.isEmpty(c)) {
            mo8276e(context);
        }
        return c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public String mo8273b(Context context) {
        return new FirebaseInfo().mo8287a(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public String mo8274c(Context context) {
        String str = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            String string = bundle.getString("io.fabric.ApiKey");
            try {
                if ("@string/twitter_consumer_secret".equals(string)) {
                    Fabric.m456h().mo8506a("Fabric", "Ignoring bad default value for Fabric ApiKey set by FirebaseUI-Auth");
                } else {
                    str = string;
                }
                if (str != null) {
                    return str;
                }
                Fabric.m456h().mo8506a("Fabric", "Falling back to Crashlytics key lookup from Manifest");
                return bundle.getString("com.crashlytics.ApiKey");
            } catch (Exception e) {
                Exception exc = e;
                str = string;
                e = exc;
                Fabric.m456h().mo8506a("Fabric", "Caught non-fatal exception while retrieving apiKey: " + e);
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public String mo8275d(Context context) {
        int a = CommonUtils.m122a(context, "io.fabric.ApiKey", "string");
        if (a == 0) {
            Fabric.m456h().mo8506a("Fabric", "Falling back to Crashlytics key lookup from Strings");
            a = CommonUtils.m122a(context, "com.crashlytics.ApiKey", "string");
        }
        if (a != 0) {
            return context.getResources().getString(a);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo8276e(Context context) {
        if (Fabric.m457i() || CommonUtils.m160i(context)) {
            throw new IllegalArgumentException(mo8271a());
        }
        Fabric.m456h().mo8515e("Fabric", mo8271a());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo8271a() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }
}
