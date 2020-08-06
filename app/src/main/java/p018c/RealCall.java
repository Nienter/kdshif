package p018c;

import java.io.IOException;
import java.util.ArrayList;
import p018c.p019a.NamedRunnable;
import p018c.p019a.p020a.CacheInterceptor;
import p018c.p019a.p021b.ConnectInterceptor;
import p018c.p019a.p022c.BridgeInterceptor;
import p018c.p019a.p022c.CallServerInterceptor;
import p018c.p019a.p022c.RealInterceptorChain;
import p018c.p019a.p022c.RetryAndFollowUpInterceptor;
import p018c.p019a.p026g.Platform;

/* renamed from: c.w */
final class RealCall implements Call {

    /* renamed from: a */
    final OkHttpClient f1109a;

    /* renamed from: b */
    final RetryAndFollowUpInterceptor f1110b;

    /* renamed from: c */
    final Request f1111c;

    /* renamed from: d */
    final boolean f1112d;

    /* renamed from: e */
    private boolean f1113e;

    /* renamed from: c.w$a */
    /* compiled from: RealCall */
    final class C0540a extends NamedRunnable {

        /* renamed from: a */
        final /* synthetic */ RealCall f1114a;

        /* renamed from: c */
        private final Callback f1115c;

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public String mo9019a() {
            return this.f1114a.f1111c.mo9020a().mo8961f();
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo8633b() {
            boolean z = true;
            boolean z2 = false;
            try {
                Response f = this.f1114a.mo9018f();
                if (this.f1114a.f1110b.mo8684a()) {
                    try {
                        this.f1115c.mo8883a((Call) this.f1114a, new IOException("Canceled"));
                    } catch (IOException e) {
                        e = e;
                    }
                } else {
                    this.f1115c.mo8882a((Call) this.f1114a, f);
                }
                this.f1114a.f1109a.mo9005t().mo8926a(this);
                return;
            } catch (IOException e2) {
                e = e2;
                z = z2;
            }
            if (z) {
                try {
                    Platform.m981b().mo8816a(4, "Callback failure for " + this.f1114a.mo9016d(), (Throwable) e);
                } catch (Throwable th) {
                    this.f1114a.f1109a.mo9005t().mo8926a(this);
                    throw th;
                }
            } else {
                this.f1115c.mo8883a((Call) this.f1114a, e);
            }
            this.f1114a.f1109a.mo9005t().mo8926a(this);
        }
    }

    RealCall(OkHttpClient uVar, Request xVar, boolean z) {
        this.f1109a = uVar;
        this.f1111c = xVar;
        this.f1112d = z;
        this.f1110b = new RetryAndFollowUpInterceptor(uVar, z);
    }

    /* renamed from: a */
    public Response mo8880a() {
        synchronized (this) {
            if (this.f1113e) {
                throw new IllegalStateException("Already Executed");
            }
            this.f1113e = true;
        }
        m1245g();
        try {
            this.f1109a.mo9005t().mo8927a(this);
            Response f = mo9018f();
            if (f != null) {
                return f;
            }
            throw new IOException("Canceled");
        } finally {
            this.f1109a.mo9005t().mo8929b(this);
        }
    }

    /* renamed from: g */
    private void m1245g() {
        this.f1110b.mo8683a(Platform.m981b().mo8814a("response.body().close()"));
    }

    /* renamed from: b */
    public boolean mo9013b() {
        return this.f1110b.mo8684a();
    }

    /* renamed from: c */
    public RealCall clone() {
        return new RealCall(this.f1109a, this.f1111c, this.f1112d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public String mo9016d() {
        return (mo9013b() ? "canceled " : "") + (this.f1112d ? "web socket" : "call") + " to " + mo9017e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public String mo9017e() {
        return this.f1111c.mo9020a().mo8969m();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public Response mo9018f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f1109a.mo9008w());
        arrayList.add(this.f1110b);
        arrayList.add(new BridgeInterceptor(this.f1109a.mo8991f()));
        arrayList.add(new CacheInterceptor(this.f1109a.mo8993h()));
        arrayList.add(new ConnectInterceptor(this.f1109a));
        if (!this.f1112d) {
            arrayList.addAll(this.f1109a.mo9009x());
        }
        arrayList.add(new CallServerInterceptor(this.f1112d));
        return new RealInterceptorChain(arrayList, null, null, null, 0, this.f1111c).mo8677a(this.f1111c);
    }
}
