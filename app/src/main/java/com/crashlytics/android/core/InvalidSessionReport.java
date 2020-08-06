package com.crashlytics.android.core;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import p005b.p006a.p007a.p008a.Fabric;

class InvalidSessionReport implements Report {
    private final Map<String, String> customHeaders = new HashMap(ReportUploader.HEADER_INVALID_CLS_FILE);
    private final File[] files;
    private final String identifier;

    public InvalidSessionReport(String str, File[] fileArr) {
        this.files = fileArr;
        this.identifier = str;
    }

    public String getFileName() {
        return this.files[0].getName();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public File getFile() {
        return this.files[0];
    }

    public File[] getFiles() {
        return this.files;
    }

    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    public void remove() {
        for (File path : this.files) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Removing invalid report file at " + path.getPath());
            r1[r0].delete();
        }
    }
}
