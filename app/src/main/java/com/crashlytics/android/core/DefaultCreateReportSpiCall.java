package com.crashlytics.android.core;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p005b.p006a.p007a.p008a.p009a.p011b.ResponseParser;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpMethod;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequest;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;

class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    static final String FILE_CONTENT_TYPE = "application/octet-stream";
    static final String FILE_PARAM = "report[file]";
    static final String IDENTIFIER_PARAM = "report[identifier]";
    static final String MULTI_FILE_PARAM = "report[file";

    public DefaultCreateReportSpiCall(Kit iVar, String str, String str2, HttpRequestFactory eVar) {
        super(iVar, str, str2, eVar, HttpMethod.POST);
    }

    DefaultCreateReportSpiCall(Kit iVar, String str, String str2, HttpRequestFactory eVar, HttpMethod cVar) {
        super(iVar, str, str2, eVar, cVar);
    }

    public boolean invoke(CreateReportRequest createReportRequest) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest), createReportRequest.report);
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Sending report to: " + getUrl());
        int b = applyMultipartDataTo.mo8436b();
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Create report request ID: " + applyMultipartDataTo.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Result was: " + b);
        return ResponseParser.m228a(b) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest dVar, CreateReportRequest createReportRequest) {
        HttpRequest a = dVar.mo8426a(AbstractSpiCall.HEADER_API_KEY, createReportRequest.apiKey).mo8426a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).mo8426a(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
        Iterator<Map.Entry<String, String>> it = createReportRequest.report.getCustomHeaders().entrySet().iterator();
        while (true) {
            HttpRequest dVar2 = a;
            if (!it.hasNext()) {
                return dVar2;
            }
            a = dVar2.mo8432a(it.next());
        }
    }

    private HttpRequest applyMultipartDataTo(HttpRequest dVar, Report report) {
        dVar.mo8446e(IDENTIFIER_PARAM, report.getIdentifier());
        if (report.getFiles().length == 1) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Adding single file " + report.getFileName() + " to report " + report.getIdentifier());
            return dVar.mo8429a(FILE_PARAM, report.getFileName(), FILE_CONTENT_TYPE, report.getFile());
        }
        int i = 0;
        for (File file : report.getFiles()) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Adding file " + file.getName() + " to report " + report.getIdentifier());
            dVar.mo8429a(MULTI_FILE_PARAM + i + "]", file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        return dVar;
    }
}
