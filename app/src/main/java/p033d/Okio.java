package p033d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: d.l */
public final class Okio {

    /* renamed from: a */
    static final Logger f2671a = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    /* renamed from: a */
    public static BufferedSource m3590a(Source sVar) {
        return new RealBufferedSource(sVar);
    }

    /* renamed from: a */
    public static BufferedSink m3589a(Sink rVar) {
        return new RealBufferedSink(rVar);
    }

    /* renamed from: a */
    public static Sink m3592a(OutputStream outputStream) {
        return m3593a(outputStream, new Timeout());
    }

    /* renamed from: a */
    private static Sink m3593a(final OutputStream outputStream, final Timeout tVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (tVar != null) {
            return new Sink() {
                /* renamed from: a_ */
                public void mo8624a_(Buffer cVar, long j) {
                    C1658u.m3672a(cVar.f2657b, 0, j);
                    while (j > 0) {
                        tVar.mo17751g();
                        Segment oVar = cVar.f2656a;
                        int min = (int) Math.min(j, (long) (oVar.f2686c - oVar.f2685b));
                        outputStream.write(oVar.f2684a, oVar.f2685b, min);
                        oVar.f2685b += min;
                        j -= (long) min;
                        cVar.f2657b -= (long) min;
                        if (oVar.f2685b == oVar.f2686c) {
                            cVar.f2656a = oVar.mo17765a();
                            SegmentPool.m3652a(oVar);
                        }
                    }
                }

                public void flush() {
                    outputStream.flush();
                }

                public void close() {
                    outputStream.close();
                }

                /* renamed from: a */
                public Timeout mo8695a() {
                    return tVar;
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    /* renamed from: a */
    public static Sink m3594a(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        AsyncTimeout c = m3601c(socket);
        return c.mo17624a(m3593a(socket.getOutputStream(), (Timeout) c));
    }

    /* renamed from: a */
    public static Source m3596a(InputStream inputStream) {
        return m3597a(inputStream, new Timeout());
    }

    /* renamed from: a */
    private static Source m3597a(final InputStream inputStream, final Timeout tVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (tVar != null) {
            return new Source() {
                /* renamed from: a */
                public long mo8592a(Buffer cVar, long j) {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            tVar.mo17751g();
                            Segment e = cVar.mo17662e(1);
                            int read = inputStream.read(e.f2684a, e.f2686c, (int) Math.min(j, (long) (8192 - e.f2686c)));
                            if (read == -1) {
                                return -1;
                            }
                            e.f2686c += read;
                            cVar.f2657b += (long) read;
                            return (long) read;
                        } catch (AssertionError e2) {
                            if (Okio.m3598a(e2)) {
                                throw new IOException(e2);
                            }
                            throw e2;
                        }
                    }
                }

                public void close() {
                    inputStream.close();
                }

                /* renamed from: a */
                public Timeout mo8593a() {
                    return tVar;
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    /* renamed from: a */
    public static Source m3595a(File file) {
        if (file != null) {
            return m3596a((InputStream) new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    /* renamed from: b */
    public static Sink m3599b(File file) {
        if (file != null) {
            return m3592a((OutputStream) new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    /* renamed from: c */
    public static Sink m3602c(File file) {
        if (file != null) {
            return m3592a((OutputStream) new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    /* renamed from: a */
    public static Sink m3591a() {
        return new Sink() {
            /* renamed from: a_ */
            public void mo8624a_(Buffer cVar, long j) {
                cVar.mo17671g(j);
            }

            public void flush() {
            }

            /* renamed from: a */
            public Timeout mo8695a() {
                return Timeout.f2693b;
            }

            public void close() {
            }
        };
    }

    /* renamed from: b */
    public static Source m3600b(Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        AsyncTimeout c = m3601c(socket);
        return c.mo17625a(m3597a(socket.getInputStream(), (Timeout) c));
    }

    /* renamed from: c */
    private static AsyncTimeout m3601c(final Socket socket) {
        return new AsyncTimeout() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public IOException mo8766a(IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void mo8767a() {
                try {
                    socket.close();
                } catch (Exception e) {
                    Okio.f2671a.log(Level.WARNING, "Failed to close timed out socket " + socket, e);
                } catch (AssertionError e2) {
                    if (Okio.m3598a(e2)) {
                        Okio.f2671a.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    /* renamed from: a */
    static boolean m3598a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
