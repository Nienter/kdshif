package p005b.p006a.p007a.p008a.p009a.p012c.p013a;

/* renamed from: b.a.a.a.a.c.a.c */
public class ExponentialBackoff implements Backoff {

    /* renamed from: a */
    private final long f203a;

    /* renamed from: b */
    private final int f204b;

    public ExponentialBackoff(long j, int i) {
        this.f203a = j;
        this.f204b = i;
    }

    public long getDelayMillis(int i) {
        return (long) (((double) this.f203a) * Math.pow((double) this.f204b, (double) i));
    }
}
