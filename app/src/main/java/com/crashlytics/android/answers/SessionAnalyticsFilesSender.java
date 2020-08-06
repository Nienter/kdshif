package com.crashlytics.android.answers;

import java.io.File;
import java.util.List;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p005b.p006a.p007a.p008a.p009a.p011b.ResponseParser;
import p005b.p006a.p007a.p008a.p009a.p014d.FilesSender;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpMethod;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequest;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;

class SessionAnalyticsFilesSender extends AbstractSpiCall implements FilesSender {
    static final String FILE_CONTENT_TYPE = "application/vnd.crashlytics.android.events";
    static final String FILE_PARAM_NAME = "session_analytics_file_";
    private final String apiKey;

    public SessionAnalyticsFilesSender(Kit iVar, String str, String str2, HttpRequestFactory eVar, String str3) {
        super(iVar, str, str2, eVar, HttpMethod.POST);
        this.apiKey = str3;
    }

    public boolean send(List<File> list) {
        int b;
        HttpRequest a = getHttpRequest().mo8426a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).mo8426a(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion()).mo8426a(AbstractSpiCall.HEADER_API_KEY, this.apiKey);
        int i = 0;
        for (File next : list) {
            a.mo8429a(FILE_PARAM_NAME + i, next.getName(), FILE_CONTENT_TYPE, next);
            i++;
        }
        Fabric.m456h().mo8506a(Answers.TAG, "Sending " + list.size() + " analytics files to " + getUrl());
        Fabric.m456h().mo8506a(Answers.TAG, "Response code for analytics file send is " + b);
        if (ResponseParser.m228a(b) == 0) {
            return true;
        }
        return false;
    }
}
