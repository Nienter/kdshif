package p033d;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* renamed from: d.a */
public class AsyncTimeout extends Timeout {

    /* renamed from: a */
    private static final long f2643a = TimeUnit.SECONDS.toMillis(60);

    /* renamed from: c */
    private static final long f2644c = TimeUnit.MILLISECONDS.toNanos(f2643a);
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static AsyncTimeout f2645d;

    /* renamed from: e */
    private boolean f2646e;

    /* renamed from: f */
    private AsyncTimeout f2647f;

    /* renamed from: g */
    private long f2648g;

    /* renamed from: d.a$a */
    /* compiled from: AsyncTimeout */
    private static final class C1650a extends Thread {
        public C1650a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r0.mo8767a();
         */
        public void run() {
            while (true) {
                try {
                    synchronized (AsyncTimeout.class) {
                        AsyncTimeout e = AsyncTimeout.m3452e();
                        if (e != null) {
                            if (e == AsyncTimeout.f2645d) {
                                AsyncTimeout unused = AsyncTimeout.f2645d = null;
                                return;
                            }
                        }
                    }
                } catch (InterruptedException e2) {
                }
            }
        }
    }

    /* renamed from: c */
    public final void mo17629c() {
        if (this.f2646e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long b_ = mo17746b_();
        boolean c_ = mo17747c_();
        if (b_ != 0 || c_) {
            this.f2646e = true;
            m3449a(this, b_, c_);
        }
    }

    /* renamed from: a */
    private static synchronized void m3449a(AsyncTimeout aVar, long j, boolean z) {
        synchronized (AsyncTimeout.class) {
            if (f2645d == null) {
                f2645d = new AsyncTimeout();
                new C1650a().start();
            }
            long nanoTime = System.nanoTime();
            if (j != 0 && z) {
                aVar.f2648g = Math.min(j, aVar.mo17748d() - nanoTime) + nanoTime;
            } else if (j != 0) {
                aVar.f2648g = nanoTime + j;
            } else if (z) {
                aVar.f2648g = aVar.mo17748d();
            } else {
                throw new AssertionError();
            }
            long b = aVar.m3450b(nanoTime);
            AsyncTimeout aVar2 = f2645d;
            while (aVar2.f2647f != null && b >= aVar2.f2647f.m3450b(nanoTime)) {
                aVar2 = aVar2.f2647f;
            }
            aVar.f2647f = aVar2.f2647f;
            aVar2.f2647f = aVar;
            if (aVar2 == f2645d) {
                AsyncTimeout.class.notify();
            }
        }
    }

    /* renamed from: a_ */
    public final boolean mo17627a_() {
        if (!this.f2646e) {
            return false;
        }
        this.f2646e = false;
        return m3451b(this);
    }

    /* renamed from: b */
    private static synchronized boolean m3451b(AsyncTimeout aVar) {
        boolean z;
        synchronized (AsyncTimeout.class) {
            AsyncTimeout aVar2 = f2645d;
            while (true) {
                if (aVar2 == null) {
                    z = true;
                    break;
                } else if (aVar2.f2647f == aVar) {
                    aVar2.f2647f = aVar.f2647f;
                    aVar.f2647f = null;
                    z = false;
                    break;
                } else {
                    aVar2 = aVar2.f2647f;
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    private long m3450b(long j) {
        return this.f2648g - j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8767a() {
    }

    /* renamed from: a */
    public final Sink mo17624a(final Sink rVar) {
        return new Sink() {
            /* renamed from: a_ */
            public void mo8624a_(Buffer cVar, long j) {
                C1658u.m3672a(cVar.f2657b, 0, j);
                long j2 = j;
                while (j2 > 0) {
                    Segment oVar = cVar.f2656a;
                    long j3 = 0;
                    while (true) {
                        if (j3 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                            break;
                        }
                        long j4 = ((long) (cVar.f2656a.f2686c - cVar.f2656a.f2685b)) + j3;
                        if (j4 >= j2) {
                            j3 = j2;
                            break;
                        } else {
                            oVar = oVar.f2689f;
                            j3 = j4;
                        }
                    }
                    AsyncTimeout.this.mo17629c();
                    try {
                        rVar.mo8624a_(cVar, j3);
                        j2 -= j3;
                        AsyncTimeout.this.mo17626a(true);
                    } catch (IOException e) {
                        throw AsyncTimeout.this.mo17628b(e);
                    } catch (Throwable th) {
                        AsyncTimeout.this.mo17626a(false);
                        throw th;
                    }
                }
            }

            public void flush() {
                AsyncTimeout.this.mo17629c();
                try {
                    rVar.flush();
                    AsyncTimeout.this.mo17626a(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.mo17628b(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.mo17626a(false);
                    throw th;
                }
            }

            public void close() {
                AsyncTimeout.this.mo17629c();
                try {
                    rVar.close();
                    AsyncTimeout.this.mo17626a(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.mo17628b(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.mo17626a(false);
                    throw th;
                }
            }

            /* renamed from: a */
            public Timeout mo8695a() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + rVar + ")";
            }
        };
    }

    /* renamed from: a */
    public final Source mo17625a(final Source sVar) {
        return new Source() {
            /* renamed from: a */
            public long mo8592a(Buffer cVar, long j) {
                AsyncTimeout.this.mo17629c();
                try {
                    long a = sVar.mo8592a(cVar, j);
                    AsyncTimeout.this.mo17626a(true);
                    return a;
                } catch (IOException e) {
                    throw AsyncTimeout.this.mo17628b(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.mo17626a(false);
                    throw th;
                }
            }

            public void close() {
                try {
                    sVar.close();
                    AsyncTimeout.this.mo17626a(true);
                } catch (IOException e) {
                    throw AsyncTimeout.this.mo17628b(e);
                } catch (Throwable th) {
                    AsyncTimeout.this.mo17626a(false);
                    throw th;
                }
            }

            /* renamed from: a */
            public Timeout mo8593a() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + sVar + ")";
            }
        };
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo17626a(boolean z) {
        if (mo17627a_() && z) {
            throw mo8766a((IOException) null);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final IOException mo17628b(IOException iOException) {
        return !mo17627a_() ? iOException : mo8766a(iOException);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public IOException mo8766a(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* renamed from: e */
    static AsyncTimeout m3452e() {
        AsyncTimeout aVar = f2645d.f2647f;
        if (aVar == null) {
            long nanoTime = System.nanoTime();
            AsyncTimeout.class.wait(f2643a);
            if (f2645d.f2647f != null || System.nanoTime() - nanoTime < f2644c) {
                return null;
            }
            return f2645d;
        }
        long b = aVar.m3450b(System.nanoTime());
        if (b > 0) {
            long j = b / 1000000;
            AsyncTimeout.class.wait(j, (int) (b - (1000000 * j)));
            return null;
        }
        f2645d.f2647f = aVar.f2647f;
        aVar.f2647f = null;
        return aVar;
    }
}
