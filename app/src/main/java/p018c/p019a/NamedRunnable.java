package p018c.p019a;

/* renamed from: c.a.b */
public abstract class NamedRunnable implements Runnable {

    /* renamed from: b */
    protected final String f483b;

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo8633b();

    public NamedRunnable(String str, Object... objArr) {
        this.f483b = Util.m646a(str, objArr);
    }

    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f483b);
        try {
            mo8633b();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
