package com.facebook.places.internal;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@TargetApi(21)
public class BleScannerImpl implements BleScanner {
    private static final String TAG = "BleScannerImpl";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private Context context;
    /* access modifiers changed from: private */
    public int errorCode;
    private boolean isScanInProgress;
    private LocationPackageRequestParams params;
    private ScanCallBackImpl scanCallBack;
    /* access modifiers changed from: private */
    public final List<BluetoothScanResult> scanResults = new ArrayList();

    private class ScanCallBackImpl extends ScanCallback {
        private ScanCallBackImpl() {
        }

        public void onScanFailed(int i) {
            super.onScanFailed(i);
            int unused = BleScannerImpl.this.errorCode = i;
        }

        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    for (ScanResult access$400 : list) {
                        BluetoothScanResult access$4002 = BleScannerImpl.newBluetoothScanResult(access$400);
                        if (access$4002 != null) {
                            BleScannerImpl.this.scanResults.add(access$4002);
                        }
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }

        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    BluetoothScanResult access$400 = BleScannerImpl.newBluetoothScanResult(scanResult);
                    if (access$400 != null) {
                        BleScannerImpl.this.scanResults.add(access$400);
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }
    }

    BleScannerImpl(Context context2, LocationPackageRequestParams locationPackageRequestParams) {
        this.context = context2;
        this.params = locationPackageRequestParams;
    }

    public synchronized void initAndCheckEligibility() {
        if (Build.VERSION.SDK_INT < 21) {
            throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
        } else if (!Validate.hasBluetoothPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        } else if (!Validate.hasLocationPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        } else {
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (this.bluetoothAdapter == null || !this.bluetoothAdapter.isEnabled()) {
                throw new ScannerException(ScannerException.Type.DISABLED);
            }
            this.bluetoothLeScanner = this.bluetoothAdapter.getBluetoothLeScanner();
            if (this.bluetoothLeScanner == null) {
                throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
            }
        }
    }

    public synchronized void startScanning() {
        if (this.isScanInProgress) {
            throw new ScannerException(ScannerException.Type.SCAN_ALREADY_IN_PROGRESS);
        }
        this.scanCallBack = new ScanCallBackImpl();
        this.isScanInProgress = true;
        this.errorCode = 0;
        synchronized (this.scanResults) {
            this.scanResults.clear();
        }
        if (this.bluetoothLeScanner == null) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
        }
        try {
            ScanSettings.Builder builder = new ScanSettings.Builder();
            builder.setScanMode(2);
            builder.setReportDelay(0);
            this.bluetoothLeScanner.startScan(null, builder.build(), this.scanCallBack);
            this.isScanInProgress = true;
        } catch (Exception e) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
        }
    }

    public synchronized void stopScanning() {
        this.bluetoothLeScanner.flushPendingScanResults(this.scanCallBack);
        this.bluetoothLeScanner.stopScan(this.scanCallBack);
        waitForMainLooper(this.params.getBluetoothFlushResultsTimeoutMs());
        this.isScanInProgress = false;
    }

    private void waitForMainLooper(long j) {
        try {
            final Object obj = new Object();
            synchronized (obj) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        try {
                            synchronized (obj) {
                                obj.notify();
                            }
                        } catch (Exception e) {
                            BleScannerImpl.logException("Exception waiting for main looper", e);
                        }
                    }
                });
                obj.wait(j);
            }
        } catch (Exception e) {
            logException("Exception waiting for main looper", e);
        }
    }

    public synchronized int getErrorCode() {
        return this.errorCode;
    }

    public synchronized List<BluetoothScanResult> getScanResults() {
        ArrayList arrayList;
        synchronized (this.scanResults) {
            int bluetoothMaxScanResults = this.params.getBluetoothMaxScanResults();
            if (this.scanResults.size() > bluetoothMaxScanResults) {
                arrayList = new ArrayList(bluetoothMaxScanResults);
                Collections.sort(this.scanResults, new Comparator<BluetoothScanResult>() {
                    public int compare(BluetoothScanResult bluetoothScanResult, BluetoothScanResult bluetoothScanResult2) {
                        return bluetoothScanResult2.rssi - bluetoothScanResult.rssi;
                    }
                });
                arrayList.addAll(this.scanResults.subList(0, bluetoothMaxScanResults));
            } else {
                arrayList = new ArrayList(this.scanResults.size());
                arrayList.addAll(this.scanResults);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static BluetoothScanResult newBluetoothScanResult(ScanResult scanResult) {
        return new BluetoothScanResult(formatPayload(scanResult.getScanRecord().getBytes()), scanResult.getRssi());
    }

    private static String formatPayload(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return toHexString(bArr, getPayloadLength(bArr));
    }

    private static int getPayloadLength(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == 0) {
                return i;
            }
            if (b < 0) {
                return bArr.length;
            }
            i += b + 1;
        }
        return bArr.length;
    }

    private static String toHexString(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i < 0 || i > bArr.length) {
            i = bArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i2])}));
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: private */
    public static void logException(String str, Exception exc) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, str, exc);
        }
    }
}
