package p005b.p006a.p007a.p008a.p009a.p017g;

import android.content.res.Resources;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Locale;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.KitInfo;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.ResponseParser;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpMethod;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequest;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;

/* renamed from: b.a.a.a.a.g.a */
abstract class AbstractAppSpiCall extends AbstractSpiCall {
    public AbstractAppSpiCall(Kit iVar, String str, String str2, HttpRequestFactory eVar, HttpMethod cVar) {
        super(iVar, str, str2, eVar, cVar);
    }

    /* renamed from: a */
    public boolean mo8485a(AppRequestData dVar) {
        HttpRequest b = m385b(m384a(getHttpRequest(), dVar), dVar);
        Fabric.m456h().mo8506a("Fabric", "Sending app info to " + getUrl());
        if (dVar.f291j != null) {
            Fabric.m456h().mo8506a("Fabric", "App icon hash is " + dVar.f291j.f314a);
            Fabric.m456h().mo8506a("Fabric", "App icon size is " + dVar.f291j.f316c + "x" + dVar.f291j.f317d);
        }
        int b2 = b.mo8436b();
        Fabric.m456h().mo8506a("Fabric", ("POST".equals(b.mo8460p()) ? "Create" : "Update") + " app request ID: " + b.mo8438b(AbstractSpiCall.HEADER_REQUEST_ID));
        Fabric.m456h().mo8506a("Fabric", "Result was " + b2);
        if (ResponseParser.m228a(b2) == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private HttpRequest m384a(HttpRequest dVar, AppRequestData dVar2) {
        return dVar.mo8426a(AbstractSpiCall.HEADER_API_KEY, dVar2.f282a).mo8426a(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).mo8426a(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
    }

    /* renamed from: b */
    private HttpRequest m385b(HttpRequest dVar, AppRequestData dVar2) {
        String str;
        HttpRequest e = dVar.mo8446e("app[identifier]", dVar2.f283b).mo8446e("app[name]", dVar2.f287f).mo8446e("app[display_version]", dVar2.f284c).mo8446e("app[build_version]", dVar2.f285d).mo8425a("app[source]", (Number) Integer.valueOf(dVar2.f288g)).mo8446e("app[minimum_sdk_version]", dVar2.f289h).mo8446e("app[built_sdk_version]", dVar2.f290i);
        if (!CommonUtils.m155d(dVar2.f286e)) {
            e.mo8446e("app[instance_identifier]", dVar2.f286e);
        }
        if (dVar2.f291j != null) {
            InputStream inputStream = null;
            try {
                inputStream = this.kit.getContext().getResources().openRawResource(dVar2.f291j.f315b);
                e.mo8446e("app[icon][hash]", dVar2.f291j.f314a).mo8430a("app[icon][data]", "icon.png", "application/octet-stream", inputStream).mo8425a("app[icon][width]", (Number) Integer.valueOf(dVar2.f291j.f316c)).mo8425a("app[icon][height]", (Number) Integer.valueOf(dVar2.f291j.f317d));
            } catch (Resources.NotFoundException e2) {
                Fabric.m456h().mo8516e("Fabric", "Failed to find app icon with resource ID: " + dVar2.f291j.f315b, e2);
            } finally {
                str = "Failed to close app icon InputStream.";
                CommonUtils.m140a((Closeable) inputStream, str);
            }
        }
        if (dVar2.f292k != null) {
            for (KitInfo next : dVar2.f292k) {
                e.mo8446e(mo8484a(next), next.mo8563b());
                e.mo8446e(mo8486b(next), next.mo8564c());
            }
        }
        return e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo8484a(KitInfo kVar) {
        return String.format(Locale.US, "app[build][libraries][%s][version]", new Object[]{kVar.mo8562a()});
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo8486b(KitInfo kVar) {
        return String.format(Locale.US, "app[build][libraries][%s][type]", new Object[]{kVar.mo8562a()});
    }
}
