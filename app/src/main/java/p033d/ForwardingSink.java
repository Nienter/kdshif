package p033d;

/* renamed from: d.g */
public abstract class ForwardingSink implements Sink {

    /* renamed from: a */
    private final Sink f2659a;

    public ForwardingSink(Sink rVar) {
        if (rVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2659a = rVar;
    }

    /* renamed from: a_ */
    public void mo8624a_(Buffer cVar, long j) {
        this.f2659a.mo8624a_(cVar, j);
    }

    public void flush() {
        this.f2659a.flush();
    }

    /* renamed from: a */
    public Timeout mo8695a() {
        return this.f2659a.mo8695a();
    }

    public void close() {
        this.f2659a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f2659a.toString() + ")";
    }
}
