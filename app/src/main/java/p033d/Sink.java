package p033d;

import java.io.Closeable;
import java.io.Flushable;

/* renamed from: d.r */
public interface Sink extends Closeable, Flushable {
    /* renamed from: a */
    Timeout mo8695a();

    /* renamed from: a_ */
    void mo8624a_(Buffer cVar, long j);

    void close();

    void flush();
}
