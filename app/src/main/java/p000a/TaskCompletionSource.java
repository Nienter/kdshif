package p000a;

/* renamed from: a.k */
public class TaskCompletionSource<TResult> {

    /* renamed from: a */
    private final Task<TResult> f66a = new Task<>();

    /* renamed from: a */
    public Task<TResult> mo32a() {
        return this.f66a;
    }

    /* renamed from: b */
    public boolean mo37b() {
        return this.f66a.mo24i();
    }

    /* renamed from: a */
    public boolean mo34a(TResult tresult) {
        return this.f66a.mo17b(tresult);
    }

    /* renamed from: a */
    public boolean mo33a(Exception exc) {
        return this.f66a.mo16b(exc);
    }

    /* renamed from: c */
    public void mo38c() {
        if (!mo37b()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    /* renamed from: b */
    public void mo36b(TResult tresult) {
        if (!mo34a(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    /* renamed from: b */
    public void mo35b(Exception exc) {
        if (!mo33a(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
