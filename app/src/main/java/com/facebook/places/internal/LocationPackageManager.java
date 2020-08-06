package com.facebook.places.internal;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.places.internal.ScannerException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class LocationPackageManager {
    private static final String TAG = "LocationPackageManager";

    public interface Listener {
        void onLocationPackage(LocationPackage locationPackage);
    }

    public static void requestLocationPackage(final LocationPackageRequestParams locationPackageRequestParams, final Listener listener) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x008d, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception scanning for bluetooth beacons", r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x0094, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x0095, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception scanning for locations", r0);
                r3.locationError = r0.type;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception scanning for wifi access points", r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a6, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a7, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception requesting a location package", r0);
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:26:0x0094 A[ExcHandler: ScannerException (r0v2 'e' com.facebook.places.internal.ScannerException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
            public void run() {
                FutureTask futureTask;
                FutureTask futureTask2;
                FutureTask futureTask3 = null;
                LocationPackage locationPackage = new LocationPackage();
                try {
                    if (locationPackageRequestParams.isLocationScanEnabled()) {
                        LocationScanner newLocationScanner = ScannerFactory.newLocationScanner(FacebookSdk.getApplicationContext(), locationPackageRequestParams);
                        newLocationScanner.initAndCheckEligibility();
                        FutureTask access$000 = LocationPackageManager.newLocationScanFuture(newLocationScanner, locationPackageRequestParams);
                        FacebookSdk.getExecutor().execute(access$000);
                        futureTask = access$000;
                    } else {
                        futureTask = null;
                    }
                    if (locationPackageRequestParams.isWifiScanEnabled()) {
                        futureTask2 = LocationPackageManager.newWifiScanFuture(locationPackageRequestParams);
                        FacebookSdk.getExecutor().execute(futureTask2);
                    } else {
                        futureTask2 = null;
                    }
                    if (locationPackageRequestParams.isBluetoothScanEnabled()) {
                        futureTask3 = LocationPackageManager.newBluetoothScanFuture(locationPackageRequestParams);
                        FacebookSdk.getExecutor().execute(futureTask3);
                    }
                    if (futureTask3 != null) {
                        LocationPackage locationPackage2 = (LocationPackage) futureTask3.get();
                        locationPackage.ambientBluetoothLe = locationPackage2.ambientBluetoothLe;
                        locationPackage.isBluetoothScanningEnabled = locationPackage2.isBluetoothScanningEnabled;
                    }
                    if (futureTask2 != null) {
                        LocationPackage locationPackage3 = (LocationPackage) futureTask2.get();
                        locationPackage.isWifiScanningEnabled = locationPackage3.isWifiScanningEnabled;
                        locationPackage.connectedWifi = locationPackage3.connectedWifi;
                        locationPackage.ambientWifi = locationPackage3.ambientWifi;
                    }
                    if (futureTask != null) {
                        LocationPackage locationPackage4 = (LocationPackage) futureTask.get();
                        locationPackage.locationError = locationPackage4.locationError;
                        locationPackage.location = locationPackage4.location;
                    }
                } catch (Exception e) {
                    LocationPackageManager.logException("Exception getting location", e);
                } catch (ScannerException e2) {
                }
                listener.onLocationPackage(locationPackage);
            }
        });
    }

    /* access modifiers changed from: private */
    public static FutureTask<LocationPackage> newLocationScanFuture(final LocationScanner locationScanner, LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() {
            public LocationPackage call() {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    locationPackage.location = locationScanner.getLocation();
                } catch (ScannerException e) {
                    locationPackage.locationError = e.type;
                    LocationPackageManager.logException("Exception while getting location", e);
                } catch (Exception e2) {
                    locationPackage.locationError = ScannerException.Type.UNKNOWN_ERROR;
                }
                return locationPackage;
            }
        });
    }

    /* access modifiers changed from: private */
    public static FutureTask<LocationPackage> newBluetoothScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() {
            public LocationPackage call() {
                BleScanner newBleScanner;
                LocationPackage locationPackage = new LocationPackage();
                try {
                    newBleScanner = ScannerFactory.newBleScanner(FacebookSdk.getApplicationContext(), locationPackageRequestParams);
                    newBleScanner.initAndCheckEligibility();
                    newBleScanner.startScanning();
                    try {
                        Thread.sleep(locationPackageRequestParams.getBluetoothScanDurationMs());
                    } catch (Exception e) {
                    }
                    newBleScanner.stopScanning();
                    int errorCode = newBleScanner.getErrorCode();
                    if (errorCode == 0) {
                        locationPackage.ambientBluetoothLe = newBleScanner.getScanResults();
                        locationPackage.isBluetoothScanningEnabled = true;
                    } else {
                        if (FacebookSdk.isDebugEnabled()) {
                            Log.d(LocationPackageManager.TAG, String.format("Bluetooth LE scan failed with error: %d", new Object[]{Integer.valueOf(errorCode)}));
                        }
                        locationPackage.isBluetoothScanningEnabled = false;
                    }
                } catch (Exception e2) {
                    LocationPackageManager.logException("Exception scanning for bluetooth beacons", e2);
                    locationPackage.isBluetoothScanningEnabled = false;
                } catch (Throwable th) {
                    newBleScanner.stopScanning();
                    throw th;
                }
                return locationPackage;
            }
        });
    }

    /* access modifiers changed from: private */
    public static FutureTask<LocationPackage> newWifiScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() {
            public LocationPackage call() {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    WifiScanner newWifiScanner = ScannerFactory.newWifiScanner(FacebookSdk.getApplicationContext(), locationPackageRequestParams);
                    newWifiScanner.initAndCheckEligibility();
                    locationPackage.connectedWifi = newWifiScanner.getConnectedWifi();
                    locationPackage.isWifiScanningEnabled = newWifiScanner.isWifiScanningEnabled();
                    if (locationPackage.isWifiScanningEnabled) {
                        locationPackage.ambientWifi = newWifiScanner.getWifiScans();
                    }
                } catch (Exception e) {
                    LocationPackageManager.logException("Exception scanning for wifi access points", e);
                    locationPackage.isWifiScanningEnabled = false;
                }
                return locationPackage;
            }
        });
    }

    /* access modifiers changed from: private */
    public static void logException(String str, Throwable th) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, str, th);
        }
    }
}
