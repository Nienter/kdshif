package android.support.p004v7.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.support.annotation.RequiresApi;
import android.support.p001v4.app.NotificationBuilderWithBuilderAccessor;

@TargetApi(24)
@RequiresApi(24)
/* renamed from: android.support.v7.app.NotificationCompatImpl24 */
class NotificationCompatImpl24 {
    NotificationCompatImpl24() {
    }

    public static void addDecoratedCustomViewStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        notificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
    }

    public static void addDecoratedMediaCustomViewStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        notificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedMediaCustomViewStyle());
    }
}
