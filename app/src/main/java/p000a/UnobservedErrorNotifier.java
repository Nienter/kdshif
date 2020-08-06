package p000a;

import p000a.Task;

/* renamed from: a.l */
class UnobservedErrorNotifier {

    /* renamed from: a */
    private Task<?> f67a;

    public UnobservedErrorNotifier(Task<?> jVar) {
        this.f67a = jVar;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            Task<?> jVar = this.f67a;
            if (jVar != null) {
                Task.C0012b a = Task.m15a();
                if (a != null) {
                    a.mo31a(jVar, new UnobservedTaskException(jVar.mo23g()));
                }
            }
        } finally {
            super.finalize();
        }
    }

    /* renamed from: a */
    public void mo39a() {
        this.f67a = null;
    }
}
