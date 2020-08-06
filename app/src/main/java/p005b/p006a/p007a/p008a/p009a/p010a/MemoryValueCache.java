package p005b.p006a.p007a.p008a.p009a.p010a;

import android.content.Context;

/* renamed from: b.a.a.a.a.a.b */
public class MemoryValueCache<T> extends AbstractValueCache<T> {

    /* renamed from: a */
    private T f107a;

    public MemoryValueCache() {
        this(null);
    }

    public MemoryValueCache(ValueCache<T> cVar) {
        super(cVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public T mo8249a(Context context) {
        return this.f107a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8251a(Context context, T t) {
        this.f107a = t;
    }
}
