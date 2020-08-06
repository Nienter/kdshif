package com.p028a.p029a.p030a;

import java.nio.ByteBuffer;

/* renamed from: com.a.a.a.bv */
public abstract class TProtocol {

    /* renamed from: e */
    protected TTransport f1483e;

    /* renamed from: a */
    public abstract void mo9414a();

    /* renamed from: a */
    public abstract void mo9416a(int i);

    /* renamed from: a */
    public abstract void mo9417a(long j);

    /* renamed from: a */
    public abstract void mo9418a(TField bsVar);

    /* renamed from: a */
    public abstract void mo9419a(TList btVar);

    /* renamed from: a */
    public abstract void mo9420a(TMap buVar);

    /* renamed from: a */
    public abstract void mo9421a(TStruct caVar);

    /* renamed from: a */
    public abstract void mo9422a(String str);

    /* renamed from: a */
    public abstract void mo9423a(ByteBuffer byteBuffer);

    /* renamed from: b */
    public abstract void mo9426b();

    /* renamed from: c */
    public abstract void mo9427c();

    /* renamed from: d */
    public abstract void mo9429d();

    /* renamed from: e */
    public abstract void mo9431e();

    /* renamed from: f */
    public abstract TStruct mo9432f();

    /* renamed from: g */
    public abstract void mo9433g();

    /* renamed from: h */
    public abstract TField mo9434h();

    /* renamed from: i */
    public abstract void mo9435i();

    /* renamed from: j */
    public abstract TMap mo9436j();

    /* renamed from: k */
    public abstract void mo9437k();

    /* renamed from: l */
    public abstract TList mo9438l();

    /* renamed from: m */
    public abstract void mo9439m();

    /* renamed from: n */
    public abstract TSet mo9440n();

    /* renamed from: o */
    public abstract void mo9441o();

    /* renamed from: p */
    public abstract boolean mo9442p();

    /* renamed from: q */
    public abstract byte mo9443q();

    /* renamed from: r */
    public abstract short mo9444r();

    /* renamed from: s */
    public abstract int mo9445s();

    /* renamed from: t */
    public abstract long mo9446t();

    /* renamed from: u */
    public abstract double mo9447u();

    /* renamed from: v */
    public abstract String mo9448v();

    /* renamed from: w */
    public abstract ByteBuffer mo9449w();

    protected TProtocol(TTransport ciVar) {
        this.f1483e = ciVar;
    }

    /* renamed from: x */
    public void mo9454x() {
    }

    /* renamed from: y */
    public Class<? extends IScheme> mo9456y() {
        return StandardScheme.class;
    }
}
