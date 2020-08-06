package p005b.p006a.p007a.p008a.p009a.p012c;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import p005b.p006a.p007a.p008a.p009a.p012c.AsyncTask;

/* renamed from: b.a.a.a.a.c.f */
public abstract class PriorityAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements Dependency<C0449l>, PriorityProvider, C0449l {

    /* renamed from: a */
    private final PriorityTask f209a = new PriorityTask();

    /* renamed from: b.a.a.a.a.c.f$a */
    /* compiled from: PriorityAsyncTask */
    private static class C0446a<Result> implements Executor {

        /* renamed from: a */
        private final Executor f210a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final PriorityAsyncTask f211b;

        public C0446a(Executor executor, PriorityAsyncTask fVar) {
            this.f210a = executor;
            this.f211b = fVar;
        }

        public void execute(Runnable runnable) {
            this.f210a.execute(new PriorityFutureTask<Result>(runnable, null) {
                /* renamed from: a */
                public <T extends Dependency<C0449l> & PriorityProvider & C0449l> T mo8375a() {
                    return C0446a.this.f211b;
                }
            });
        }
    }

    /* renamed from: a */
    public final void mo8367a(ExecutorService executorService, Params... paramsArr) {
        super.mo8322a((Executor) new C0446a(executorService, this), paramsArr);
    }

    public int compareTo(Object obj) {
        return Priority.compareTo(this, obj);
    }

    /* renamed from: a */
    public void addDependency(C0449l lVar) {
        if (mo8327b() != AsyncTask.C0444d.PENDING) {
            throw new IllegalStateException("Must not add Dependency after task is running");
        }
        ((Dependency) ((PriorityProvider) mo8369e())).addDependency(lVar);
    }

    public Collection<C0449l> getDependencies() {
        return ((Dependency) ((PriorityProvider) mo8369e())).getDependencies();
    }

    public boolean areDependenciesMet() {
        return ((Dependency) ((PriorityProvider) mo8369e())).areDependenciesMet();
    }

    public Priority getPriority() {
        return ((PriorityProvider) mo8369e()).getPriority();
    }

    public void setFinished(boolean z) {
        ((C0449l) ((PriorityProvider) mo8369e())).setFinished(z);
    }

    public boolean isFinished() {
        return ((C0449l) ((PriorityProvider) mo8369e())).isFinished();
    }

    public void setError(Throwable th) {
        ((C0449l) ((PriorityProvider) mo8369e())).setError(th);
    }

    /* renamed from: e */
    public <T extends Dependency<C0449l> & PriorityProvider & C0449l> T mo8369e() {
        return this.f209a;
    }
}
