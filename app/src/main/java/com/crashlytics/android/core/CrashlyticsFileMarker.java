package com.crashlytics.android.core;

import java.io.File;
import java.io.IOException;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p016f.FileStore;

class CrashlyticsFileMarker {
    private final FileStore fileStore;
    private final String markerName;

    public CrashlyticsFileMarker(String str, FileStore aVar) {
        this.markerName = str;
        this.fileStore = aVar;
    }

    public boolean create() {
        boolean z = false;
        try {
            return getMarkerFile().createNewFile();
        } catch (IOException e) {
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error creating marker: " + this.markerName, e);
            return z;
        }
    }

    public boolean isPresent() {
        return getMarkerFile().exists();
    }

    public boolean remove() {
        return getMarkerFile().delete();
    }

    private File getMarkerFile() {
        return new File(this.fileStore.mo8479a(), this.markerName);
    }
}
