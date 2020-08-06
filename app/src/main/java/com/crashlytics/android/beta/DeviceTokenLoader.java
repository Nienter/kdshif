package com.crashlytics.android.beta;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p010a.ValueLoader;

public class DeviceTokenLoader implements ValueLoader<String> {
    private static final String BETA_APP_PACKAGE_NAME = "io.crash.air";
    private static final String DIRFACTOR_DEVICE_TOKEN_PREFIX = "assets/com.crashlytics.android.beta/dirfactor-device-token=";

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        p005b.p006a.p007a.p008a.Fabric.m456h().mo8506a(com.crashlytics.android.beta.Beta.TAG, "Beta by Crashlytics app is not installed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005e, code lost:
        if (r1 != null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0064, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0065, code lost:
        p005b.p006a.p007a.p008a.Fabric.m456h().mo8516e(com.crashlytics.android.beta.Beta.TAG, "Failed to close the APK file", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0071, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0072, code lost:
        r8 = r2;
        r2 = r1;
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0087, code lost:
        p005b.p006a.p007a.p008a.Fabric.m456h().mo8516e(com.crashlytics.android.beta.Beta.TAG, "Failed to close the APK file", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0093, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0094, code lost:
        r8 = r2;
        r2 = r1;
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00aa, code lost:
        p005b.p006a.p007a.p008a.Fabric.m456h().mo8516e(com.crashlytics.android.beta.Beta.TAG, "Failed to close the APK file", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b8, code lost:
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bf, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c0, code lost:
        p005b.p006a.p007a.p008a.Fabric.m456h().mo8516e(com.crashlytics.android.beta.Beta.TAG, "Failed to close the APK file", r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052 A[ExcHandler: NameNotFoundException (e android.content.pm.PackageManager$NameNotFoundException), Splitter:B:1:0x0009] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0082 A[SYNTHETIC, Splitter:B:25:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a4 A[SYNTHETIC, Splitter:B:34:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bb A[SYNTHETIC, Splitter:B:41:0x00bb] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0075=Splitter:B:22:0x0075, B:31:0x0097=Splitter:B:31:0x0097} */
    public String load(Context context) {
        ZipInputStream zipInputStream;
        ZipInputStream zipInputStreamOfApkFrom;
        long nanoTime = System.nanoTime();
        String str = "";
        ZipInputStream zipInputStream2 = null;
        try {
            zipInputStreamOfApkFrom = getZipInputStreamOfApkFrom(context, "io.crash.air");
            str = determineDeviceToken(zipInputStreamOfApkFrom);
            if (zipInputStreamOfApkFrom != null) {
                try {
                    zipInputStreamOfApkFrom.close();
                } catch (IOException e) {
                    Fabric.m456h().mo8516e(Beta.TAG, "Failed to close the APK file", e);
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
        } catch (FileNotFoundException e3) {
            FileNotFoundException fileNotFoundException = e3;
            zipInputStream = zipInputStreamOfApkFrom;
            FileNotFoundException fileNotFoundException2 = fileNotFoundException;
            try {
                Fabric.m456h().mo8516e(Beta.TAG, "Failed to find the APK file", fileNotFoundException2);
                if (zipInputStream != null) {
                }
            } catch (Throwable th) {
                th = th;
                if (zipInputStream != null) {
                }
                throw th;
            }
        } catch (IOException e4) {
            IOException iOException = e4;
            ZipInputStream zipInputStream3 = zipInputStreamOfApkFrom;
            IOException iOException2 = iOException;
            Fabric.m456h().mo8516e(Beta.TAG, "Failed to read the APK file", iOException2);
            if (zipInputStream != null) {
            }
        } catch (Throwable th2) {
            th = th2;
            zipInputStream = zipInputStream2;
            if (zipInputStream != null) {
            }
            throw th;
        }
        Fabric.m456h().mo8506a(Beta.TAG, "Beta device token load took " + (((double) (System.nanoTime() - nanoTime)) / 1000000.0d) + "ms");
        return str;
    }

    /* access modifiers changed from: package-private */
    public ZipInputStream getZipInputStreamOfApkFrom(Context context, String str) {
        return new ZipInputStream(new FileInputStream(context.getPackageManager().getApplicationInfo(str, 0).sourceDir));
    }

    /* access modifiers changed from: package-private */
    public String determineDeviceToken(ZipInputStream zipInputStream) {
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        if (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.startsWith(DIRFACTOR_DEVICE_TOKEN_PREFIX)) {
                return name.substring(DIRFACTOR_DEVICE_TOKEN_PREFIX.length(), name.length() - 1);
            }
        }
        return "";
    }
}
