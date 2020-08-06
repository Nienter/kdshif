package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                return z;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException e) {
                }
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder getBinder() {
            if (!this.consumed.compareAndSet(true, true)) {
                return this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
        if (androidIdViaService == null) {
            return new AttributionIdentifiers();
        }
        return androidIdViaService;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
            if (methodQuietly == null) {
                return null;
            }
            Object invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly, context);
            if (!(invokeMethodQuietly instanceof Integer) || ((Integer) invokeMethodQuietly).intValue() != 0) {
                return null;
            }
            Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
            if (methodQuietly2 == null) {
                return null;
            }
            Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly2, context);
            if (invokeMethodQuietly2 == null) {
                return null;
            }
            Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "getId", (Class<?>[]) new Class[0]);
            Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
            if (methodQuietly3 == null || methodQuietly4 == null) {
                return null;
            }
            AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
            attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly3, new Object[0]);
            attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly4, new Object[0])).booleanValue();
            return attributionIdentifiers;
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                Utility.logd("android_id", e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.facebook.internal.AttributionIdentifiers} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: android.net.Uri} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: android.net.Uri} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: android.net.Uri} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: android.net.Uri} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fe  */
    public static AttributionIdentifiers getAttributionIdentifiers(Context context) {
        Cursor cursor;
        Uri uri;
        Cursor cursor2 = null;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.e(TAG, "getAttributionIdentifiers should not be called from the main thread");
        }
        if (recentlyFetchedIdentifiers != null && System.currentTimeMillis() - recentlyFetchedIdentifiers.fetchTime < IDENTIFIER_REFRESH_INTERVAL_MILLIS) {
            return recentlyFetchedIdentifiers;
        }
        AttributionIdentifiers androidId = getAndroidId(context);
        try {
            String[] strArr = {ATTRIBUTION_ID_COLUMN_NAME, ANDROID_ID_COLUMN_NAME, LIMIT_TRACKING_COLUMN_NAME};
            if (context.getPackageManager().resolveContentProvider(ATTRIBUTION_ID_CONTENT_PROVIDER, 0) != null) {
                uri = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            } else if (context.getPackageManager().resolveContentProvider(ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI, 0) != null) {
                uri = Uri.parse("content://com.facebook.wakizashi.provider.AttributionIdProvider");
            } else {
                uri = cursor2;
            }
            String installerPackageName = getInstallerPackageName(context);
            if (installerPackageName != null) {
                androidId.androidInstallerPackage = installerPackageName;
            }
            if (uri == 0) {
                AttributionIdentifiers cacheAndReturnIdentifiers = cacheAndReturnIdentifiers(androidId);
                if (cursor2 == null) {
                    return cacheAndReturnIdentifiers;
                }
                cursor2.close();
                return cacheAndReturnIdentifiers;
            }
            cursor = context.getContentResolver().query(uri, strArr, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex(ATTRIBUTION_ID_COLUMN_NAME);
                        int columnIndex2 = cursor.getColumnIndex(ANDROID_ID_COLUMN_NAME);
                        int columnIndex3 = cursor.getColumnIndex(LIMIT_TRACKING_COLUMN_NAME);
                        androidId.attributionId = cursor.getString(columnIndex);
                        if (columnIndex2 > 0 && columnIndex3 > 0 && androidId.getAndroidAdvertiserId() == null) {
                            androidId.androidAdvertiserId = cursor.getString(columnIndex2);
                            androidId.limitTracking = Boolean.parseBoolean(cursor.getString(columnIndex3));
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return cacheAndReturnIdentifiers(androidId);
                    }
                } catch (Exception e) {
                    e = e;
                    try {
                        Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + e.toString());
                        if (cursor != null) {
                        }
                        return cursor2;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            AttributionIdentifiers cacheAndReturnIdentifiers2 = cacheAndReturnIdentifiers(androidId);
            if (cursor == null) {
                return cacheAndReturnIdentifiers2;
            }
            cursor.close();
            return cacheAndReturnIdentifiers2;
        } catch (Exception e2) {
            e = e2;
            cursor = cursor2;
            Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
            return cursor2;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
            if (cursor != null) {
            }
            throw th;
        }
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    @Nullable
    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
}
