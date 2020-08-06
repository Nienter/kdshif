package p033d;

/* renamed from: d.h */
public abstract class ForwardingSource implements Source {

    /* renamed from: a */
    private final Source f2660a;

    public ForwardingSource(Source sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2660a = sVar;
    }

    /* renamed from: a */
    public long mo8592a(Buffer cVar, long j) {
        return this.f2660a.mo8592a(cVar, j);
    }

    /* renamed from: a */
    public Timeout mo8593a() {
        return this.f2660a.mo8593a();
    }

    public void close() {
        this.f2660a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f2660a.toString() + ")";
    }
}
