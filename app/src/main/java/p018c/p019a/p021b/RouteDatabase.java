package p018c.p019a.p021b;

import java.util.LinkedHashSet;
import java.util.Set;
import p018c.Route;

/* renamed from: c.a.b.d */
public final class RouteDatabase {

    /* renamed from: a */
    private final Set<Route> f503a = new LinkedHashSet();

    /* renamed from: a */
    public synchronized void mo8648a(Route abVar) {
        this.f503a.add(abVar);
    }

    /* renamed from: b */
    public synchronized void mo8649b(Route abVar) {
        this.f503a.remove(abVar);
    }

    /* renamed from: c */
    public synchronized boolean mo8650c(Route abVar) {
        return this.f503a.contains(abVar);
    }
}
