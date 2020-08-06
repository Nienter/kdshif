package p018c.p019a.p020a;

import java.io.IOException;
import p033d.Buffer;
import p033d.ForwardingSink;
import p033d.Sink;

/* renamed from: c.a.a.e */
class FaultHidingSink extends ForwardingSink {

    /* renamed from: a */
    private boolean f482a;

    public FaultHidingSink(Sink rVar) {
        super(rVar);
    }

    /* renamed from: a_ */
    public void mo8624a_(Buffer cVar, long j) {
        if (this.f482a) {
            cVar.mo17671g(j);
            return;
        }
        try {
            super.mo8624a_(cVar, j);
        } catch (IOException e) {
            this.f482a = true;
            mo8613a(e);
        }
    }

    public void flush() {
        if (!this.f482a) {
            try {
                super.flush();
            } catch (IOException e) {
                this.f482a = true;
                mo8613a(e);
            }
        }
    }

    public void close() {
        if (!this.f482a) {
            try {
                super.close();
            } catch (IOException e) {
                this.f482a = true;
                mo8613a(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8613a(IOException iOException) {
    }
}
