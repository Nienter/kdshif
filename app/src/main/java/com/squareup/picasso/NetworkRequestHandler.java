package com.squareup.picasso;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import p018c.CacheControl;
import p018c.Request;
import p018c.Response;
import p018c.ResponseBody;
import p033d.Source;

/* renamed from: com.squareup.picasso.t */
class NetworkRequestHandler extends RequestHandler {

    /* renamed from: a */
    private final Downloader f2544a;

    /* renamed from: b */
    private final Stats f2545b;

    /* renamed from: com.squareup.picasso.t$a */
    /* compiled from: NetworkRequestHandler */
    static class C1633a extends IOException {
        C1633a(String str) {
            super(str);
        }
    }

    /* renamed from: com.squareup.picasso.t$b */
    /* compiled from: NetworkRequestHandler */
    static final class C1634b extends IOException {
        final int code;
        final int networkPolicy;

        C1634b(int i, int i2) {
            super("HTTP " + i);
            this.code = i;
            this.networkPolicy = i2;
        }
    }

    public NetworkRequestHandler(Downloader jVar, Stats acVar) {
        this.f2544a = jVar;
        this.f2545b = acVar;
    }

    /* renamed from: a */
    public boolean mo17477a(C1645y yVar) {
        String scheme = yVar.f2596d.getScheme();
        return "http".equals(scheme) || "https".equals(scheme);
    }

    /* renamed from: a */
    public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
        Response a = this.f2544a.mo17557a(m3387b(yVar, i));
        ResponseBody h = a.mo9051h();
        if (!a.mo9047d()) {
            h.close();
            throw new C1634b(a.mo9045c(), yVar.f2595c);
        }
        Picasso.C1640d dVar = a.mo9054k() == null ? Picasso.C1640d.NETWORK : Picasso.C1640d.DISK;
        if (dVar == Picasso.C1640d.DISK && h.mo8681a() == 0) {
            h.close();
            throw new C1633a("Received response with 0 content-length header.");
        }
        if (dVar == Picasso.C1640d.NETWORK && h.mo8681a() > 0) {
            this.f2545b.mo17485a(h.mo8681a());
        }
        return new RequestHandler.C1612a((Source) h.mo8682b(), dVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo17475a() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo17478a(boolean z, NetworkInfo networkInfo) {
        return networkInfo == null || networkInfo.isConnected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo17479b() {
        return true;
    }

    /* renamed from: b */
    private static Request m3387b(C1645y yVar, int i) {
        CacheControl dVar = null;
        if (i != 0) {
            if (NetworkPolicy.isOfflineOnly(i)) {
                dVar = CacheControl.f841b;
            } else {
                CacheControl.C0526a aVar = new CacheControl.C0526a();
                if (!NetworkPolicy.shouldReadFromDiskCache(i)) {
                    aVar.mo8875a();
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(i)) {
                    aVar.mo8877b();
                }
                dVar = aVar.mo8879d();
            }
        }
        Request.C0541a a = new Request.C0541a().mo9033a(yVar.f2596d.toString());
        if (dVar != null) {
            a.mo9030a(dVar);
        }
        return a.mo9036a();
    }
}
