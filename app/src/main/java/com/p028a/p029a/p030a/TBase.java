package com.p028a.p029a.p030a;

import com.p028a.p029a.p030a.TBase;
import com.p028a.p029a.p030a.TFieldIdEnum;
import java.io.Serializable;

/* renamed from: com.a.a.a.be */
public interface TBase<T extends TBase<?, ?>, F extends TFieldIdEnum> extends Serializable {
    /* renamed from: a */
    void mo9122a(TProtocol bvVar);

    /* renamed from: b */
    F mo9125b(int i);

    /* renamed from: b */
    void mo9126b();

    /* renamed from: b */
    void mo9127b(TProtocol bvVar);

    /* renamed from: p */
    TBase<T, F> mo9145p();
}
