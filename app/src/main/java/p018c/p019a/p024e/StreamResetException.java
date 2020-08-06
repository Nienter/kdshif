package p018c.p019a.p024e;

import java.io.IOException;

/* renamed from: c.a.e.o */
public final class StreamResetException extends IOException {
    public final ErrorCode errorCode;

    public StreamResetException(ErrorCode bVar) {
        super("stream was reset: " + bVar);
        this.errorCode = bVar;
    }
}
