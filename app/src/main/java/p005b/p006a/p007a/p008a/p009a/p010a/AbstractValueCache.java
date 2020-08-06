package p005b.p006a.p007a.p008a.p009a.p010a;

import android.content.Context;

/* renamed from: b.a.a.a.a.a.a */
public abstract class AbstractValueCache<T> implements ValueCache<T> {

    /* renamed from: a */
    private final ValueCache<T> f106a;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract T mo8249a(Context context);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo8251a(Context context, T t);

    public AbstractValueCache(ValueCache<T> cVar) {
        this.f106a = cVar;
    }

    /* renamed from: a */
    public final synchronized T mo8250a(Context context, ValueLoader<T> dVar) {
        T a;
        a = mo8249a(context);
        if (a == null) {
            a = this.f106a != null ? this.f106a.mo8250a(context, dVar) : dVar.load(context);
            m88b(context, a);
        }
        return a;
    }

    /* renamed from: b */
    private void m88b(Context context, T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        mo8251a(context, t);
    }
}
