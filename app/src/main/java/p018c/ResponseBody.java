package p018c;

import java.io.Closeable;
import p018c.p019a.Util;
import p033d.Buffer;
import p033d.BufferedSource;

/* renamed from: c.aa */
public abstract class ResponseBody implements Closeable {
    /* renamed from: a */
    public abstract long mo8681a();

    /* renamed from: b */
    public abstract BufferedSource mo8682b();

    public void close() {
        Util.m652a((Closeable) mo8682b());
    }

    /* renamed from: a */
    public static ResponseBody m1017a(MediaType tVar, byte[] bArr) {
        return m1016a(tVar, (long) bArr.length, new Buffer().mo17655c(bArr));
    }

    /* renamed from: a */
    public static ResponseBody m1016a(final MediaType tVar, final long j, final BufferedSource eVar) {
        if (eVar != null) {
            return new ResponseBody() {
                /* renamed from: a */
                public long mo8681a() {
                    return j;
                }

                /* renamed from: b */
                public BufferedSource mo8682b() {
                    return eVar;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
