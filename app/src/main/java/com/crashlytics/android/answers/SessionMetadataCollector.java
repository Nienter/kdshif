package com.crashlytics.android.answers;

import android.content.Context;
import java.util.Map;
import java.util.UUID;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;

class SessionMetadataCollector {
    private final Context context;
    private final IdManager idManager;
    private final String versionCode;
    private final String versionName;

    public SessionMetadataCollector(Context context2, IdManager pVar, String str, String str2) {
        this.context = context2;
        this.idManager = pVar;
        this.versionCode = str;
        this.versionName = str2;
    }

    public SessionEventMetadata getMetadata() {
        Map<IdManager.C0430a, String> h = this.idManager.mo8297h();
        String m = CommonUtils.m164m(this.context);
        String d = this.idManager.mo8293d();
        String g = this.idManager.mo8296g();
        return new SessionEventMetadata(this.idManager.mo8292c(), UUID.randomUUID().toString(), this.idManager.mo8291b(), h.get(IdManager.C0430a.ANDROID_ID), h.get(IdManager.C0430a.ANDROID_ADVERTISING_ID), this.idManager.mo8299j(), h.get(IdManager.C0430a.FONT_TOKEN), m, d, g, this.versionCode, this.versionName);
    }
}
