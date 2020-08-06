package com.facebook.places.internal;

import com.facebook.places.internal.ScannerException;
import java.util.List;

public class BleScannerLegacy implements BleScanner {
    BleScannerLegacy() {
    }

    public void initAndCheckEligibility() {
        throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
    }

    public void startScanning() {
        throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
    }

    public void stopScanning() {
        throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
    }

    public int getErrorCode() {
        return -1;
    }

    public List<BluetoothScanResult> getScanResults() {
        return null;
    }
}
