package p018c;

import p018c.p019a.Util;
import p033d.BufferedSink;

/* renamed from: c.y */
public abstract class RequestBody {
    /* renamed from: a */
    public abstract MediaType mo9038a();

    /* renamed from: a */
    public abstract void mo9039a(BufferedSink dVar);

    /* renamed from: b */
    public long mo9040b() {
        return -1;
    }

    /* renamed from: a */
    public static RequestBody m1271a(MediaType tVar, byte[] bArr) {
        return m1272a(tVar, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static RequestBody m1272a(final MediaType tVar, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        Util.m651a((long) bArr.length, (long) i, (long) i2);
        return new RequestBody() {
            /* renamed from: a */
            public MediaType mo9038a() {
                return tVar;
            }

            /* renamed from: b */
            public long mo9040b() {
                return (long) i2;
            }

            /* renamed from: a */
            public void mo9039a(BufferedSink dVar) {
                dVar.mo17656c(bArr, i, i2);
            }
        };
    }
}
