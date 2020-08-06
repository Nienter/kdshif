package p005b.p006a.p007a.p008a.p009a.p012c;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* renamed from: b.a.a.a.a.c.h */
public class PriorityFutureTask<V> extends FutureTask<V> implements Dependency<C0449l>, PriorityProvider, C0449l {

    /* renamed from: b */
    final Object f213b;

    public PriorityFutureTask(Callable<V> callable) {
        super(callable);
        this.f213b = mo8376a((Object) callable);
    }

    public PriorityFutureTask(Runnable runnable, V v) {
        super(runnable, v);
        this.f213b = mo8376a((Object) runnable);
    }

    public int compareTo(Object obj) {
        return ((PriorityProvider) mo8375a()).compareTo(obj);
    }

    /* renamed from: a */
    public void addDependency(C0449l lVar) {
        ((Dependency) ((PriorityProvider) mo8375a())).addDependency(lVar);
    }

    public Collection<C0449l> getDependencies() {
        return ((Dependency) ((PriorityProvider) mo8375a())).getDependencies();
    }

    public boolean areDependenciesMet() {
        return ((Dependency) ((PriorityProvider) mo8375a())).areDependenciesMet();
    }

    public Priority getPriority() {
        return ((PriorityProvider) mo8375a()).getPriority();
    }

    public void setFinished(boolean z) {
        ((C0449l) ((PriorityProvider) mo8375a())).setFinished(z);
    }

    public boolean isFinished() {
        return ((C0449l) ((PriorityProvider) mo8375a())).isFinished();
    }

    public void setError(Throwable th) {
        ((C0449l) ((PriorityProvider) mo8375a())).setError(th);
    }

    /* renamed from: a */
    public <T extends Dependency<C0449l> & PriorityProvider & C0449l> T mo8375a() {
        return (Dependency) this.f213b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T extends Dependency<C0449l> & PriorityProvider & C0449l> T mo8376a(Object obj) {
        if (PriorityTask.isProperDelegate(obj)) {
            return (Dependency) obj;
        }
        return new PriorityTask();
    }
}
