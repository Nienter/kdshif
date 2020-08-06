package com.p028a.p029a.p030a;

import com.p028a.p029a.p030a.TCompactProtocol;
import java.io.ByteArrayOutputStream;

/* renamed from: com.a.a.a.bj */
public class TSerializer {

    /* renamed from: a */
    private final ByteArrayOutputStream f1431a;

    /* renamed from: b */
    private final TIOStreamTransport f1432b;

    /* renamed from: c */
    private TProtocol f1433c;

    public TSerializer() {
        this(new TCompactProtocol.C0613a());
    }

    public TSerializer(TProtocolFactory bxVar) {
        this.f1431a = new ByteArrayOutputStream();
        this.f1432b = new TIOStreamTransport(this.f1431a);
        this.f1433c = bxVar.mo9450a(this.f1432b);
    }

    /* renamed from: a */
    public byte[] mo9408a(TBase beVar) {
        this.f1431a.reset();
        beVar.mo9127b(this.f1433c);
        return this.f1431a.toByteArray();
    }
}
