package com.facebook.places.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WifiScannerImpl implements WifiScanner {
    private ScanResultBroadcastReceiver broadcastReceiver;
    private Context context;
    private final LocationPackageRequestParams params;
    /* access modifiers changed from: private */
    public final Object scanLock = new Object();
    private WifiManager wifiManager;

    private class ScanResultBroadcastReceiver extends BroadcastReceiver {
        private ScanResultBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                synchronized (WifiScannerImpl.this.scanLock) {
                    WifiScannerImpl.this.scanLock.notify();
                }
                WifiScannerImpl.this.unregisterBroadcastReceiver();
            }
        }
    }

    WifiScannerImpl(Context context2, LocationPackageRequestParams locationPackageRequestParams) {
        this.context = context2;
        this.params = locationPackageRequestParams;
    }

    public void initAndCheckEligibility() {
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.wifi")) {
            throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
        } else if (!Validate.hasWiFiPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        } else {
            if (this.wifiManager == null) {
                this.wifiManager = (WifiManager) this.context.getSystemService("wifi");
            }
            if (!isWifiScanningAlwaysOn() && !this.wifiManager.isWifiEnabled()) {
                throw new ScannerException(ScannerException.Type.DISABLED);
            }
        }
    }

    public WifiScanResult getConnectedWifi() {
        try {
            WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
            if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getBSSID()) || connectionInfo.getSupplicantState() != SupplicantState.COMPLETED) {
                return null;
            }
            WifiScanResult wifiScanResult = new WifiScanResult();
            wifiScanResult.bssid = connectionInfo.getBSSID();
            wifiScanResult.ssid = connectionInfo.getSSID();
            wifiScanResult.rssi = connectionInfo.getRssi();
            if (Build.VERSION.SDK_INT < 21) {
                return wifiScanResult;
            }
            wifiScanResult.frequency = connectionInfo.getFrequency();
            return wifiScanResult;
        } catch (Exception e) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, e);
        }
    }

    public boolean isWifiScanningEnabled() {
        try {
            initAndCheckEligibility();
            if (Validate.hasLocationPermission(this.context)) {
                return true;
            }
        } catch (ScannerException e) {
        }
        return false;
    }

    private boolean isWifiScanningAlwaysOn() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.wifiManager.isScanAlwaysAvailable();
        }
        return false;
    }

    private List<WifiScanResult> getCachedScanResults() {
        try {
            List<ScanResult> filterWifiScanResultsByMaxAge = filterWifiScanResultsByMaxAge(this.wifiManager.getScanResults(), this.params.getWifiScanMaxAgeMs());
            filterResults(filterWifiScanResultsByMaxAge, this.params.getWifiMaxScanResults());
            ArrayList arrayList = new ArrayList(filterWifiScanResultsByMaxAge.size());
            for (ScanResult next : filterWifiScanResultsByMaxAge) {
                WifiScanResult wifiScanResult = new WifiScanResult();
                wifiScanResult.bssid = next.BSSID;
                wifiScanResult.ssid = next.SSID;
                wifiScanResult.rssi = next.level;
                wifiScanResult.frequency = next.frequency;
                arrayList.add(wifiScanResult);
            }
            return arrayList;
        } catch (Exception e) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, e);
        }
    }

    private static void filterResults(List<ScanResult> list, int i) {
        if (list.size() > i) {
            Collections.sort(list, new Comparator<ScanResult>() {
                public int compare(ScanResult scanResult, ScanResult scanResult2) {
                    return scanResult2.level - scanResult.level;
                }
            });
            list.subList(i, list.size()).clear();
        }
    }

    private static List<ScanResult> filterWifiScanResultsByMaxAge(List<ScanResult> list, long j) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            if (Build.VERSION.SDK_INT < 17) {
                arrayList.addAll(list);
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                for (ScanResult next : list) {
                    long j2 = elapsedRealtime - (next.timestamp / 1000);
                    if (j2 < 0) {
                        j2 = System.currentTimeMillis() - next.timestamp;
                    }
                    if (j2 < j) {
                        arrayList.add(next);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized List<WifiScanResult> getWifiScans() {
        List<WifiScanResult> list;
        list = null;
        if (!this.params.isWifiActiveScanForced()) {
            list = getCachedScanResults();
        }
        boolean z = list == null || list.isEmpty();
        if (this.params.isWifiActiveScanForced() || (this.params.isWifiActiveScanAllowed() && z)) {
            list = getActiveScanResults();
        }
        return list;
    }

    private List<WifiScanResult> getActiveScanResults() {
        List<WifiScanResult> list = null;
        try {
            if (Validate.hasChangeWifiStatePermission(this.context)) {
                registerBroadcastReceiver();
                if (this.wifiManager.startScan()) {
                    try {
                        synchronized (this.scanLock) {
                            this.scanLock.wait(this.params.getWifiScanTimeoutMs());
                        }
                    } catch (InterruptedException e) {
                    }
                    list = getCachedScanResults();
                }
            }
        } catch (Exception e2) {
        } finally {
            unregisterBroadcastReceiver();
        }
        return list;
    }

    private void registerBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            unregisterBroadcastReceiver();
        }
        this.broadcastReceiver = new ScanResultBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.context.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    public void unregisterBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            try {
                this.context.unregisterReceiver(this.broadcastReceiver);
            } catch (Exception e) {
            }
            this.broadcastReceiver = null;
        }
    }
}
