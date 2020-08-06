package android.support.p001v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.net.ConnectivityManagerCompat */
public final class ConnectivityManagerCompat {
    private static final ConnectivityManagerCompatImpl IMPL;
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$Api24ConnectivityManagerCompatImpl */
    static class Api24ConnectivityManagerCompatImpl extends JellyBeanConnectivityManagerCompatImpl {
        Api24ConnectivityManagerCompatImpl() {
        }

        public int getRestrictBackgroundStatus(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatApi24.getRestrictBackgroundStatus(connectivityManager);
        }
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$BaseConnectivityManagerCompatImpl */
    static class BaseConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl {
        BaseConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return true;
            }
            switch (activeNetworkInfo.getType()) {
                case 1:
                    return false;
                default:
                    return true;
            }
        }

        public int getRestrictBackgroundStatus(ConnectivityManager connectivityManager) {
            return 3;
        }
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$ConnectivityManagerCompatImpl */
    interface ConnectivityManagerCompatImpl {
        int getRestrictBackgroundStatus(ConnectivityManager connectivityManager);

        boolean isActiveNetworkMetered(ConnectivityManager connectivityManager);
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$HoneycombMR2ConnectivityManagerCompatImpl */
    static class HoneycombMR2ConnectivityManagerCompatImpl extends BaseConnectivityManagerCompatImpl {
        HoneycombMR2ConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(connectivityManager);
        }
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$JellyBeanConnectivityManagerCompatImpl */
    static class JellyBeanConnectivityManagerCompatImpl extends HoneycombMR2ConnectivityManagerCompatImpl {
        JellyBeanConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(connectivityManager);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$RestrictBackgroundStatus */
    public @interface RestrictBackgroundStatus {
    }

    static {
        if (Build.VERSION.SDK_INT >= 24) {
            IMPL = new Api24ConnectivityManagerCompatImpl();
        } else if (Build.VERSION.SDK_INT >= 16) {
            IMPL = new JellyBeanConnectivityManagerCompatImpl();
        } else if (Build.VERSION.SDK_INT >= 13) {
            IMPL = new HoneycombMR2ConnectivityManagerCompatImpl();
        } else {
            IMPL = new BaseConnectivityManagerCompatImpl();
        }
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        return IMPL.isActiveNetworkMetered(connectivityManager);
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivityManager, Intent intent) {
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (networkInfo != null) {
            return connectivityManager.getNetworkInfo(networkInfo.getType());
        }
        return null;
    }

    public static int getRestrictBackgroundStatus(ConnectivityManager connectivityManager) {
        return IMPL.getRestrictBackgroundStatus(connectivityManager);
    }

    private ConnectivityManagerCompat() {
    }
}
