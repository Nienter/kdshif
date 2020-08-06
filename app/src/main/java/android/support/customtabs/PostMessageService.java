package android.support.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.customtabs.IPostMessageService;

public class PostMessageService extends Service {
    private IPostMessageService.Stub mBinder = new IPostMessageService.Stub() {
        public void onMessageChannelReady(ICustomTabsCallback iCustomTabsCallback, Bundle bundle) {
            iCustomTabsCallback.onMessageChannelReady(bundle);
        }

        public void onPostMessage(ICustomTabsCallback iCustomTabsCallback, String str, Bundle bundle) {
            iCustomTabsCallback.onPostMessage(str, bundle);
        }
    };

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
