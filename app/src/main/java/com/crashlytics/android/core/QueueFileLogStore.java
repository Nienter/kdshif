package com.crashlytics.android.core;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.QueueFile;

class QueueFileLogStore implements FileLogStore {
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    public QueueFileLogStore(File file, int i) {
        this.workingFile = file;
        this.maxLogSize = i;
    }

    public void writeToLog(long j, String str) {
        openLogFile();
        doWriteToLog(j, str);
    }

    public ByteString getLogAsByteString() {
        if (!this.workingFile.exists()) {
            return null;
        }
        openLogFile();
        if (this.logFile == null) {
            return null;
        }
        final int[] iArr = {0};
        final byte[] bArr = new byte[this.logFile.mo8306a()];
        try {
            this.logFile.mo8307a((QueueFile.C0435c) new QueueFile.C0435c() {
                public void read(InputStream inputStream, int i) {
                    try {
                        inputStream.read(bArr, iArr[0], i);
                        int[] iArr = iArr;
                        iArr[0] = iArr[0] + i;
                    } finally {
                        inputStream.close();
                    }
                }
            });
        } catch (IOException e) {
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "A problem occurred while reading the Crashlytics log file.", e);
        }
        return ByteString.copyFrom(bArr, 0, iArr[0]);
    }

    public void closeLogFile() {
        CommonUtils.m140a((Closeable) this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new QueueFile(this.workingFile);
            } catch (IOException e) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Could not open log file: " + this.workingFile, e);
            }
        }
    }

    private void doWriteToLog(long j, String str) {
        String str2;
        if (this.logFile != null) {
            if (str == null) {
                str2 = "null";
            } else {
                str2 = str;
            }
            try {
                if (str2.length() > this.maxLogSize / 4) {
                    str2 = "..." + str2.substring(str2.length() - r1);
                }
                this.logFile.mo8308a(String.format(Locale.US, "%d %s%n", new Object[]{Long.valueOf(j), str2.replaceAll("\r", " ").replaceAll("\n", " ")}).getBytes("UTF-8"));
                while (!this.logFile.mo8311b() && this.logFile.mo8306a() > this.maxLogSize) {
                    this.logFile.mo8312c();
                }
            } catch (IOException e) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "There was a problem writing to the Crashlytics log.", e);
            }
        }
    }
}
