package p000a;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* renamed from: a.j */
public class Task<TResult> {

    /* renamed from: a */
    public static final ExecutorService f27a = BoltsExecutors.m5a();

    /* renamed from: b */
    public static final Executor f28b = AndroidExecutors.m2b();

    /* renamed from: c */
    private static final Executor f29c = BoltsExecutors.m6b();

    /* renamed from: d */
    private static volatile C0012b f30d;

    /* renamed from: m */
    private static Task<?> f31m = new Task<>((Object) null);

    /* renamed from: n */
    private static Task<Boolean> f32n = new Task<>((Boolean) true);

    /* renamed from: o */
    private static Task<Boolean> f33o = new Task<>((Boolean) false);

    /* renamed from: p */
    private static Task<?> f34p = new Task<>(true);

    /* renamed from: e */
    private final Object f35e = new Object();

    /* renamed from: f */
    private boolean f36f;

    /* renamed from: g */
    private boolean f37g;

    /* renamed from: h */
    private TResult f38h;

    /* renamed from: i */
    private Exception f39i;

    /* renamed from: j */
    private boolean f40j;

    /* renamed from: k */
    private UnobservedErrorNotifier f41k;

    /* renamed from: l */
    private List<Continuation<TResult, Void>> f42l = new ArrayList();

    /* renamed from: a.j$a */
    /* compiled from: Task */
    public class C0011a extends TaskCompletionSource<TResult> {
        C0011a() {
        }
    }

    /* renamed from: a.j$b */
    /* compiled from: Task */
    public interface C0012b {
        /* renamed from: a */
        void mo31a(Task<?> jVar, UnobservedTaskException mVar);
    }

    /* renamed from: a */
    public static C0012b m15a() {
        return f30d;
    }

    Task() {
    }

    private Task(TResult tresult) {
        mo17b(tresult);
    }

    private Task(boolean z) {
        if (z) {
            mo24i();
        } else {
            mo17b((Object) null);
        }
    }

    /* renamed from: b */
    public static <TResult> Task<TResult>.a m19b() {
        Task jVar = new Task();
        jVar.getClass();
        return new C0011a();
    }

    /* renamed from: c */
    public boolean mo19c() {
        boolean z;
        synchronized (this.f35e) {
            z = this.f36f;
        }
        return z;
    }

    /* renamed from: d */
    public boolean mo20d() {
        boolean z;
        synchronized (this.f35e) {
            z = this.f37g;
        }
        return z;
    }

    /* renamed from: e */
    public boolean mo21e() {
        boolean z;
        synchronized (this.f35e) {
            z = mo23g() != null;
        }
        return z;
    }

    /* renamed from: f */
    public TResult mo22f() {
        TResult tresult;
        synchronized (this.f35e) {
            tresult = this.f38h;
        }
        return tresult;
    }

    /* renamed from: g */
    public Exception mo23g() {
        Exception exc;
        synchronized (this.f35e) {
            if (this.f39i != null) {
                this.f40j = true;
                if (this.f41k != null) {
                    this.f41k.mo39a();
                    this.f41k = null;
                }
            }
            exc = this.f39i;
        }
        return exc;
    }

    /* renamed from: a */
    public static <TResult> Task<TResult> m17a(TResult tresult) {
        if (tresult == null) {
            return f31m;
        }
        if (tresult instanceof Boolean) {
            return ((Boolean) tresult).booleanValue() ? f32n : f33o;
        }
        TaskCompletionSource kVar = new TaskCompletionSource();
        kVar.mo36b(tresult);
        return kVar.mo32a();
    }

    /* renamed from: a */
    public static <TResult> Task<TResult> m16a(Exception exc) {
        TaskCompletionSource kVar = new TaskCompletionSource();
        kVar.mo35b(exc);
        return kVar.mo32a();
    }

    /* renamed from: h */
    public static <TResult> Task<TResult> m23h() {
        return f34p;
    }

    /* renamed from: a */
    public <TContinuationResult> Task<TContinuationResult> mo13a(Continuation<TResult, TContinuationResult> hVar, Executor executor, CancellationToken eVar) {
        boolean c;
        final TaskCompletionSource kVar = new TaskCompletionSource();
        synchronized (this.f35e) {
            c = mo19c();
            if (!c) {
                final Continuation<TResult, TContinuationResult> hVar2 = hVar;
                final Executor executor2 = executor;
                final CancellationToken eVar2 = eVar;
                this.f42l.add(new Continuation<TResult, Void>() {
                    /* renamed from: a */
                    public Void then(Task<TResult> jVar) {
                        Task.m21c(kVar, hVar2, jVar, executor2, eVar2);
                        return null;
                    }
                });
            }
        }
        if (c) {
            m21c(kVar, hVar, this, executor, eVar);
        }
        return kVar.mo32a();
    }

    /* renamed from: a */
    public <TContinuationResult> Task<TContinuationResult> mo11a(Continuation<TResult, TContinuationResult> hVar) {
        return mo13a(hVar, f29c, null);
    }

    /* renamed from: a */
    public <TContinuationResult> Task<TContinuationResult> mo12a(Continuation<TResult, Task<TContinuationResult>> hVar, Executor executor) {
        return mo15b(hVar, executor, null);
    }

    /* renamed from: b */
    public <TContinuationResult> Task<TContinuationResult> mo15b(Continuation<TResult, Task<TContinuationResult>> hVar, Executor executor, CancellationToken eVar) {
        boolean c;
        final TaskCompletionSource kVar = new TaskCompletionSource();
        synchronized (this.f35e) {
            c = mo19c();
            if (!c) {
                final Continuation<TResult, Task<TContinuationResult>> hVar2 = hVar;
                final Executor executor2 = executor;
                final CancellationToken eVar2 = eVar;
                this.f42l.add(new Continuation<TResult, Void>() {
                    /* renamed from: a */
                    public Void then(Task<TResult> jVar) {
                        Task.m22d(kVar, hVar2, jVar, executor2, eVar2);
                        return null;
                    }
                });
            }
        }
        if (c) {
            m22d(kVar, hVar, this, executor, eVar);
        }
        return kVar.mo32a();
    }

    /* renamed from: c */
    public <TContinuationResult> Task<TContinuationResult> mo18c(final Continuation<TResult, TContinuationResult> hVar, Executor executor, final CancellationToken eVar) {
        return mo12a(new Continuation<TResult, Task<TContinuationResult>>() {
            /* renamed from: a */
            public Task<TContinuationResult> then(Task<TResult> jVar) {
                if (eVar != null && eVar.mo3a()) {
                    return Task.m23h();
                }
                if (jVar.mo21e()) {
                    return Task.m16a(jVar.mo23g());
                }
                if (jVar.mo20d()) {
                    return Task.m23h();
                }
                return jVar.mo11a((Continuation<TResult, TContinuationResult>) hVar);
            }
        }, executor);
    }

    /* renamed from: b */
    public <TContinuationResult> Task<TContinuationResult> mo14b(Continuation<TResult, TContinuationResult> hVar) {
        return mo18c(hVar, f29c, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static <TContinuationResult, TResult> void m21c(final TaskCompletionSource<TContinuationResult> kVar, final Continuation<TResult, TContinuationResult> hVar, final Task<TResult> jVar, Executor executor, final CancellationToken eVar) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (eVar == null || !eVar.mo3a()) {
                        try {
                            kVar.mo36b(hVar.then(jVar));
                        } catch (CancellationException e) {
                            kVar.mo38c();
                        } catch (Exception e2) {
                            kVar.mo35b(e2);
                        }
                    } else {
                        kVar.mo38c();
                    }
                }
            });
        } catch (Exception e) {
            kVar.mo35b((Exception) new ExecutorException(e));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static <TContinuationResult, TResult> void m22d(final TaskCompletionSource<TContinuationResult> kVar, final Continuation<TResult, Task<TContinuationResult>> hVar, final Task<TResult> jVar, Executor executor, final CancellationToken eVar) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (eVar == null || !eVar.mo3a()) {
                        try {
                            Task jVar = (Task) hVar.then(jVar);
                            if (jVar == null) {
                                kVar.mo36b(null);
                            } else {
                                jVar.mo11a(new Continuation<TContinuationResult, Void>() {
                                    /* renamed from: a */
                                    public Void then(Task<TContinuationResult> jVar) {
                                        if (eVar != null && eVar.mo3a()) {
                                            kVar.mo38c();
                                        } else if (jVar.mo20d()) {
                                            kVar.mo38c();
                                        } else if (jVar.mo21e()) {
                                            kVar.mo35b(jVar.mo23g());
                                        } else {
                                            kVar.mo36b(jVar.mo22f());
                                        }
                                        return null;
                                    }
                                });
                            }
                        } catch (CancellationException e) {
                            kVar.mo38c();
                        } catch (Exception e2) {
                            kVar.mo35b(e2);
                        }
                    } else {
                        kVar.mo38c();
                    }
                }
            });
        } catch (Exception e) {
            kVar.mo35b((Exception) new ExecutorException(e));
        }
    }

    /* renamed from: j */
    private void m24j() {
        synchronized (this.f35e) {
            for (Continuation then : this.f42l) {
                try {
                    then.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.f42l = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public boolean mo24i() {
        boolean z = true;
        synchronized (this.f35e) {
            if (this.f36f) {
                z = false;
            } else {
                this.f36f = true;
                this.f37g = true;
                this.f35e.notifyAll();
                m24j();
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo17b(TResult tresult) {
        boolean z = true;
        synchronized (this.f35e) {
            if (this.f36f) {
                z = false;
            } else {
                this.f36f = true;
                this.f38h = tresult;
                this.f35e.notifyAll();
                m24j();
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return true;
     */
    /* renamed from: b */
    public boolean mo16b(Exception exc) {
        synchronized (this.f35e) {
            if (this.f36f) {
                return false;
            }
            this.f36f = true;
            this.f39i = exc;
            this.f40j = false;
            this.f35e.notifyAll();
            m24j();
            if (!this.f40j && m15a() != null) {
                this.f41k = new UnobservedErrorNotifier(this);
            }
        }
    }
}
