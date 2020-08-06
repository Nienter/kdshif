package p000a;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.applinks.AppLinkData;

/* renamed from: a.c */
public final class AppLinks {
    /* renamed from: a */
    public static Bundle m3a(Intent intent) {
        return intent.getBundleExtra("al_applink_data");
    }

    /* renamed from: b */
    public static Bundle m4b(Intent intent) {
        Bundle a = m3a(intent);
        if (a == null) {
            return null;
        }
        return a.getBundle(AppLinkData.ARGUMENTS_EXTRAS_KEY);
    }
}
