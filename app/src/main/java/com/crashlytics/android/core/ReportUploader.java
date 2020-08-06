package com.crashlytics.android.core;

import com.facebook.appevents.AppEventsConstants;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p011b.BackgroundPriorityRunnable;

class ReportUploader {
    static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    /* access modifiers changed from: private */
    public static final short[] RETRY_INTERVALS = {10, 20, 30, 60, 120, 300};
    private final String apiKey;
    private final CreateReportSpiCall createReportCall;
    private final Object fileAccessLock = new Object();
    /* access modifiers changed from: private */
    public final HandlingExceptionCheck handlingExceptionCheck;
    private final ReportFilesProvider reportFilesProvider;
    /* access modifiers changed from: private */
    public Thread uploadThread;

    static final class AlwaysSendCheck implements SendCheck {
        AlwaysSendCheck() {
        }

        public boolean canSendReports() {
            return true;
        }
    }

    interface HandlingExceptionCheck {
        boolean isHandlingException();
    }

    interface ReportFilesProvider {
        File[] getCompleteSessionFiles();

        File[] getInvalidSessionFiles();
    }

    interface SendCheck {
        boolean canSendReports();
    }

    private class Worker extends BackgroundPriorityRunnable {
        private final float delay;
        private final SendCheck sendCheck;

        Worker(float f, SendCheck sendCheck2) {
            this.delay = f;
            this.sendCheck = sendCheck2;
        }

        public void onRun() {
            try {
                attemptUploadWithRetry();
            } catch (Exception e) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            Thread unused = ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry() {
            long j;
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Starting report processing in " + this.delay + " second(s)...");
            if (this.delay > 0.0f) {
                try {
                    Thread.sleep((long) (this.delay * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            List<Report> findReports = ReportUploader.this.findReports();
            if (!ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                if (findReports.isEmpty() || this.sendCheck.canSendReports()) {
                    List<Report> list = findReports;
                    int i = 0;
                    while (!list.isEmpty() && !ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Attempting to send " + list.size() + " report(s)");
                        for (Report forceUpload : list) {
                            ReportUploader.this.forceUpload(forceUpload);
                        }
                        List<Report> findReports2 = ReportUploader.this.findReports();
                        if (!findReports2.isEmpty()) {
                            int i2 = i + 1;
                            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Report submisson: scheduling delayed retry in " + j + " seconds");
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                                list = findReports2;
                            } catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        } else {
                            list = findReports2;
                        }
                    }
                    return;
                }
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "User declined to send. Removing " + findReports.size() + " Report(s).");
                for (Report remove : findReports) {
                    remove.remove();
                }
            }
        }
    }

    public ReportUploader(String str, CreateReportSpiCall createReportSpiCall, ReportFilesProvider reportFilesProvider2, HandlingExceptionCheck handlingExceptionCheck2) {
        if (createReportSpiCall == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.createReportCall = createReportSpiCall;
        this.apiKey = str;
        this.reportFilesProvider = reportFilesProvider2;
        this.handlingExceptionCheck = handlingExceptionCheck2;
    }

    public synchronized void uploadReports(float f, SendCheck sendCheck) {
        if (this.uploadThread != null) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Report upload has already been started.");
        } else {
            this.uploadThread = new Thread(new Worker(f, sendCheck), "Crashlytics Report Uploader");
            this.uploadThread.start();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isUploading() {
        return this.uploadThread != null;
    }

    /* access modifiers changed from: package-private */
    public boolean forceUpload(Report report) {
        boolean z = false;
        synchronized (this.fileAccessLock) {
            try {
                boolean invoke = this.createReportCall.invoke(new CreateReportRequest(this.apiKey, report));
                Fabric.m456h().mo8511c(CrashlyticsCore.TAG, "Crashlytics report upload " + (invoke ? "complete: " : "FAILED: ") + report.getIdentifier());
                if (invoke) {
                    report.remove();
                    z = true;
                }
            } catch (Exception e) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error occurred sending report " + report, e);
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public List<Report> findReports() {
        File[] completeSessionFiles;
        File[] invalidSessionFiles;
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Checking for crash reports...");
        synchronized (this.fileAccessLock) {
            completeSessionFiles = this.reportFilesProvider.getCompleteSessionFiles();
            invalidSessionFiles = this.reportFilesProvider.getInvalidSessionFiles();
        }
        LinkedList linkedList = new LinkedList();
        if (completeSessionFiles != null) {
            for (File path : completeSessionFiles) {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Found crash report " + path.getPath());
                linkedList.add(new SessionReport(path));
            }
        }
        HashMap hashMap = new HashMap();
        if (invalidSessionFiles != null) {
            for (File file : invalidSessionFiles) {
                String sessionIdFromSessionFile = CrashlyticsController.getSessionIdFromSessionFile(file);
                if (!hashMap.containsKey(sessionIdFromSessionFile)) {
                    hashMap.put(sessionIdFromSessionFile, new LinkedList());
                }
                ((List) hashMap.get(sessionIdFromSessionFile)).add(file);
            }
        }
        for (String str : hashMap.keySet()) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Found invalid session: " + str);
            List list = (List) hashMap.get(str);
            linkedList.add(new InvalidSessionReport(str, (File[]) list.toArray(new File[list.size()])));
        }
        if (linkedList.isEmpty()) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "No reports found.");
        }
        return linkedList;
    }
}
