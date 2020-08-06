package p018c.p019a.p024e;

import java.util.List;
import p033d.BufferedSource;

/* renamed from: c.a.e.m */
public interface PushObserver {

    /* renamed from: a */
    public static final PushObserver f756a = new PushObserver() {
        /* renamed from: a */
        public boolean mo8793a(int i, List<Header> list) {
            return true;
        }

        /* renamed from: a */
        public boolean mo8794a(int i, List<Header> list, boolean z) {
            return true;
        }

        /* renamed from: a */
        public boolean mo8792a(int i, BufferedSource eVar, int i2, boolean z) {
            eVar.mo17671g((long) i2);
            return true;
        }

        /* renamed from: a */
        public void mo8791a(int i, ErrorCode bVar) {
        }
    };

    /* renamed from: a */
    void mo8791a(int i, ErrorCode bVar);

    /* renamed from: a */
    boolean mo8792a(int i, BufferedSource eVar, int i2, boolean z);

    /* renamed from: a */
    boolean mo8793a(int i, List<Header> list);

    /* renamed from: a */
    boolean mo8794a(int i, List<Header> list, boolean z);
}
