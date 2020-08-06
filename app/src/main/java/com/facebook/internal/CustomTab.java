package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import com.facebook.FacebookSdk;

public class CustomTab {
    private Uri uri;

    public CustomTab(String str, Bundle bundle) {
        this.uri = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + ServerProtocol.DIALOG_PATH + str, bundle == null ? new Bundle() : bundle);
    }

    public void openCustomTab(Activity activity, String str) {
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        build.intent.setPackage(str);
        build.intent.addFlags(1073741824);
        build.launchUrl(activity, this.uri);
    }
}
