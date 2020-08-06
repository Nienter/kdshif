package com.crashlytics.android.core;

import java.io.InputStream;
import p005b.p006a.p007a.p008a.p009a.p015e.PinningInfoProvider;

class CrashlyticsPinningInfoProvider implements PinningInfoProvider {
    private final PinningInfoProvider pinningInfo;

    public CrashlyticsPinningInfoProvider(PinningInfoProvider pinningInfoProvider) {
        this.pinningInfo = pinningInfoProvider;
    }

    public InputStream getKeyStoreStream() {
        return this.pinningInfo.getKeyStoreStream();
    }

    public String getKeyStorePassword() {
        return this.pinningInfo.getKeyStorePassword();
    }

    public String[] getPins() {
        return this.pinningInfo.getPins();
    }

    public long getPinCreationTimeInMillis() {
        return -1;
    }
}
