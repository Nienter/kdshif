package p018c.p019a.p022c;

import p018c.Headers;
import p018c.ResponseBody;
import p033d.BufferedSource;

/* renamed from: c.a.c.h */
public final class RealResponseBody extends ResponseBody {

    /* renamed from: a */
    private final Headers f557a;

    /* renamed from: b */
    private final BufferedSource f558b;

    public RealResponseBody(Headers qVar, BufferedSource eVar) {
        this.f557a = qVar;
        this.f558b = eVar;
    }

    /* renamed from: a */
    public long mo8681a() {
        return HttpHeaders.m679a(this.f557a);
    }

    /* renamed from: b */
    public BufferedSource mo8682b() {
        return this.f558b;
    }
}
