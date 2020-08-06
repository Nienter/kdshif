package com.p028a.p029a.p030a;

import com.p028a.p029a.p030a.TCompactProtocol;

/* renamed from: com.a.a.a.bg */
public class TDeserializer {

    /* renamed from: a */
    private final TProtocol f1429a;

    /* renamed from: b */
    private final TMemoryInputTransport f1430b;

    public TDeserializer() {
        this(new TCompactProtocol.C0613a());
    }

    public TDeserializer(TProtocolFactory bxVar) {
        this.f1430b = new TMemoryInputTransport();
        this.f1429a = bxVar.mo9450a(this.f1430b);
    }

    /* renamed from: a */
    public void mo9407a(TBase beVar, byte[] bArr) {
        try {
            this.f1430b.mo9465a(bArr);
            beVar.mo9122a(this.f1429a);
        } finally {
            this.f1430b.mo9463a();
            this.f1429a.mo9454x();
        }
    }
}
