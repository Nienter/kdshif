package p005b.p006a.p007a.p008a.p009a.p012c.p013a;

/* renamed from: b.a.a.a.a.c.a.e */
public class RetryState {

    /* renamed from: a */
    private final int f205a;

    /* renamed from: b */
    private final Backoff f206b;

    /* renamed from: c */
    private final RetryPolicy f207c;

    public RetryState(Backoff aVar, RetryPolicy dVar) {
        this(0, aVar, dVar);
    }

    public RetryState(int i, Backoff aVar, RetryPolicy dVar) {
        this.f205a = i;
        this.f206b = aVar;
        this.f207c = dVar;
    }

    /* renamed from: a */
    public long mo8340a() {
        return this.f206b.getDelayMillis(this.f205a);
    }

    /* renamed from: b */
    public RetryState mo8341b() {
        return new RetryState(this.f205a + 1, this.f206b, this.f207c);
    }

    /* renamed from: c */
    public RetryState mo8342c() {
        return new RetryState(this.f206b, this.f207c);
    }
}
