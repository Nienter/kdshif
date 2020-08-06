package p005b.p006a.p007a.p008a.p009a.p012c;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: b.a.a.a.a.c.j */
public class PriorityTask implements Dependency<C0449l>, PriorityProvider, C0449l {
    private final List<C0449l> dependencies = new ArrayList();
    private final AtomicBoolean hasRun = new AtomicBoolean(false);
    private final AtomicReference<Throwable> throwable = new AtomicReference<>(null);

    public synchronized Collection<C0449l> getDependencies() {
        return Collections.unmodifiableCollection(this.dependencies);
    }

    public synchronized void addDependency(C0449l lVar) {
        this.dependencies.add(lVar);
    }

    public boolean areDependenciesMet() {
        for (C0449l isFinished : getDependencies()) {
            if (!isFinished.isFinished()) {
                return false;
            }
        }
        return true;
    }

    public synchronized void setFinished(boolean z) {
        this.hasRun.set(z);
    }

    public boolean isFinished() {
        return this.hasRun.get();
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public void setError(Throwable th) {
        this.throwable.set(th);
    }

    public Throwable getError() {
        return this.throwable.get();
    }

    public int compareTo(Object obj) {
        return Priority.compareTo(this, obj);
    }

    public static boolean isProperDelegate(Object obj) {
        try {
            Dependency bVar = (Dependency) obj;
            C0449l lVar = (C0449l) obj;
            PriorityProvider iVar = (PriorityProvider) obj;
            if (bVar == null || lVar == null || iVar == null) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
