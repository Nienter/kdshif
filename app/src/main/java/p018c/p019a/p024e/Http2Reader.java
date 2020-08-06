package p018c.p019a.p024e;

import android.support.p004v7.widget.ActivityChooserView;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import p018c.p019a.Util;
import p018c.p019a.p024e.Hpack;
import p033d.Buffer;
import p033d.BufferedSource;
import p033d.ByteString;
import p033d.Source;
import p033d.Timeout;

/* renamed from: c.a.e.h */
final class Http2Reader implements Closeable {

    /* renamed from: a */
    static final Logger f702a = Logger.getLogger(Http2.class.getName());

    /* renamed from: b */
    final Hpack.C0486a f703b = new Hpack.C0486a(4096, this.f705d);

    /* renamed from: c */
    private final BufferedSource f704c;

    /* renamed from: d */
    private final C0503a f705d = new C0503a(this.f704c);

    /* renamed from: e */
    private final boolean f706e;

    /* renamed from: c.a.e.h$a */
    /* compiled from: Http2Reader */
    static final class C0503a implements Source {

        /* renamed from: a */
        int f707a;

        /* renamed from: b */
        byte f708b;

        /* renamed from: c */
        int f709c;

        /* renamed from: d */
        int f710d;

        /* renamed from: e */
        short f711e;

        /* renamed from: f */
        private final BufferedSource f712f;

        public C0503a(BufferedSource eVar) {
            this.f712f = eVar;
        }

        /* renamed from: a */
        public long mo8592a(Buffer cVar, long j) {
            while (this.f710d == 0) {
                this.f712f.mo17671g((long) this.f711e);
                this.f711e = 0;
                if ((this.f708b & 4) != 0) {
                    return -1;
                }
                m849b();
            }
            long a = this.f712f.mo8592a(cVar, Math.min(j, (long) this.f710d));
            if (a == -1) {
                return -1;
            }
            this.f710d = (int) (((long) this.f710d) - a);
            return a;
        }

        /* renamed from: a */
        public Timeout mo8593a() {
            return this.f712f.mo8593a();
        }

        public void close() {
        }

        /* renamed from: b */
        private void m849b() {
            int i = this.f709c;
            int a = Http2Reader.m835a(this.f712f);
            this.f710d = a;
            this.f707a = a;
            byte h = (byte) (this.f712f.mo17672h() & 255);
            this.f708b = (byte) (this.f712f.mo17672h() & 255);
            if (Http2Reader.f702a.isLoggable(Level.FINE)) {
                Http2Reader.f702a.fine(Http2.m772a(true, this.f709c, this.f707a, h, this.f708b));
            }
            this.f709c = this.f712f.mo17679j() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (h != 9) {
                throw Http2.m773b("%s != TYPE_CONTINUATION", Byte.valueOf(h));
            } else if (this.f709c != i) {
                throw Http2.m773b("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    /* renamed from: c.a.e.h$b */
    /* compiled from: Http2Reader */
    interface C0504b {
        /* renamed from: a */
        void mo8734a();

        /* renamed from: a */
        void mo8735a(int i, int i2, int i3, boolean z);

        /* renamed from: a */
        void mo8736a(int i, int i2, List<Header> list);

        /* renamed from: a */
        void mo8737a(int i, long j);

        /* renamed from: a */
        void mo8738a(int i, ErrorCode bVar);

        /* renamed from: a */
        void mo8739a(int i, ErrorCode bVar, ByteString fVar);

        /* renamed from: a */
        void mo8740a(boolean z, int i, int i2);

        /* renamed from: a */
        void mo8741a(boolean z, int i, int i2, List<Header> list);

        /* renamed from: a */
        void mo8742a(boolean z, int i, BufferedSource eVar, int i2);

        /* renamed from: a */
        void mo8743a(boolean z, C0510n nVar);
    }

    public Http2Reader(BufferedSource eVar, boolean z) {
        this.f704c = eVar;
        this.f706e = z;
    }

    /* renamed from: a */
    public void mo8744a(C0504b bVar) {
        if (!this.f706e) {
            ByteString c = this.f704c.mo17657c((long) Http2.f620a.size());
            if (f702a.isLoggable(Level.FINE)) {
                f702a.fine(Util.m646a("<< CONNECTION %s", c.hex()));
            }
            if (!Http2.f620a.equals(c)) {
                throw Http2.m773b("Expected a connection header but was %s", c.utf8());
            }
        } else if (!mo8745a(true, bVar)) {
            throw Http2.m773b("Required SETTINGS preface not received", new Object[0]);
        }
    }

    /* renamed from: a */
    public boolean mo8745a(boolean z, C0504b bVar) {
        try {
            this.f704c.mo17643a(9);
            int a = m835a(this.f704c);
            if (a < 0 || a > 16384) {
                throw Http2.m773b("FRAME_SIZE_ERROR: %s", Integer.valueOf(a));
            }
            byte h = (byte) (this.f704c.mo17672h() & 255);
            if (!z || h == 4) {
                byte h2 = (byte) (this.f704c.mo17672h() & 255);
                int j = this.f704c.mo17679j() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                if (f702a.isLoggable(Level.FINE)) {
                    f702a.fine(Http2.m772a(true, j, a, h, h2));
                }
                switch (h) {
                    case 0:
                        m839b(bVar, a, h2, j);
                        return true;
                    case 1:
                        m838a(bVar, a, h2, j);
                        return true;
                    case 2:
                        m840c(bVar, a, h2, j);
                        return true;
                    case 3:
                        m841d(bVar, a, h2, j);
                        return true;
                    case 4:
                        m842e(bVar, a, h2, j);
                        return true;
                    case 5:
                        m843f(bVar, a, h2, j);
                        return true;
                    case 6:
                        m844g(bVar, a, h2, j);
                        return true;
                    case 7:
                        m845h(bVar, a, h2, j);
                        return true;
                    case 8:
                        m846i(bVar, a, h2, j);
                        return true;
                    default:
                        this.f704c.mo17671g((long) a);
                        return true;
                }
            } else {
                throw Http2.m773b("Expected a SETTINGS frame but was %s", Byte.valueOf(h));
            }
        } catch (IOException e) {
            return false;
        }
    }

    /* renamed from: a */
    private void m838a(C0504b bVar, int i, byte b, int i2) {
        short s = 0;
        if (i2 == 0) {
            throw Http2.m773b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z = (b & 1) != 0;
        if ((b & 8) != 0) {
            s = (short) (this.f704c.mo17672h() & 255);
        }
        if ((b & 32) != 0) {
            m837a(bVar, i2);
            i -= 5;
        }
        bVar.mo8741a(z, i2, -1, m836a(m834a(i, b, s), s, b, i2));
    }

    /* renamed from: a */
    private List<Header> m836a(int i, short s, byte b, int i2) {
        C0503a aVar = this.f705d;
        this.f705d.f710d = i;
        aVar.f707a = i;
        this.f705d.f711e = s;
        this.f705d.f708b = b;
        this.f705d.f709c = i2;
        this.f703b.mo8700a();
        return this.f703b.mo8701b();
    }

    /* renamed from: b */
    private void m839b(C0504b bVar, int i, byte b, int i2) {
        boolean z;
        boolean z2 = true;
        short s = 0;
        if ((b & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((b & 32) == 0) {
            z2 = false;
        }
        if (z2) {
            throw Http2.m773b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        if ((b & 8) != 0) {
            s = (short) (this.f704c.mo17672h() & 255);
        }
        bVar.mo8742a(z, i2, this.f704c, m834a(i, b, s));
        this.f704c.mo17671g((long) s);
    }

    /* renamed from: c */
    private void m840c(C0504b bVar, int i, byte b, int i2) {
        if (i != 5) {
            throw Http2.m773b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
        } else if (i2 == 0) {
            throw Http2.m773b("TYPE_PRIORITY streamId == 0", new Object[0]);
        } else {
            m837a(bVar, i2);
        }
    }

    /* renamed from: a */
    private void m837a(C0504b bVar, int i) {
        int j = this.f704c.mo17679j();
        bVar.mo8735a(i, j & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, (this.f704c.mo17672h() & 255) + 1, (Integer.MIN_VALUE & j) != 0);
    }

    /* renamed from: d */
    private void m841d(C0504b bVar, int i, byte b, int i2) {
        if (i != 4) {
            throw Http2.m773b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
        } else if (i2 == 0) {
            throw Http2.m773b("TYPE_RST_STREAM streamId == 0", new Object[0]);
        } else {
            int j = this.f704c.mo17679j();
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(j);
            if (fromHttp2 == null) {
                throw Http2.m773b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(j));
            } else {
                bVar.mo8738a(i2, fromHttp2);
            }
        }
    }

    /* renamed from: e */
    private void m842e(C0504b bVar, int i, byte b, int i2) {
        if (i2 != 0) {
            throw Http2.m773b("TYPE_SETTINGS streamId != 0", new Object[0]);
        } else if ((b & 1) != 0) {
            if (i != 0) {
                throw Http2.m773b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            bVar.mo8734a();
        } else if (i % 6 != 0) {
            throw Http2.m773b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
        } else {
            C0510n nVar = new C0510n();
            for (int i3 = 0; i3 < i; i3 += 6) {
                short i4 = this.f704c.mo17678i();
                int j = this.f704c.mo17679j();
                switch (i4) {
                    case 2:
                        if (!(j == 0 || j == 1)) {
                            throw Http2.m773b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        }
                    case 3:
                        i4 = 4;
                        break;
                    case 4:
                        i4 = 7;
                        if (j >= 0) {
                            break;
                        } else {
                            throw Http2.m773b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        }
                    case 5:
                        if (j >= 16384 && j <= 16777215) {
                            break;
                        } else {
                            throw Http2.m773b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(j));
                        }
                }
                nVar.mo8795a(i4, j);
            }
            bVar.mo8743a(false, nVar);
        }
    }

    /* renamed from: f */
    private void m843f(C0504b bVar, int i, byte b, int i2) {
        short s = 0;
        if (i2 == 0) {
            throw Http2.m773b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        if ((b & 8) != 0) {
            s = (short) (this.f704c.mo17672h() & 255);
        }
        bVar.mo8736a(i2, this.f704c.mo17679j() & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, m836a(m834a(i - 4, b, s), s, b, i2));
    }

    /* renamed from: g */
    private void m844g(C0504b bVar, int i, byte b, int i2) {
        boolean z = true;
        if (i != 8) {
            throw Http2.m773b("TYPE_PING length != 8: %s", Integer.valueOf(i));
        } else if (i2 != 0) {
            throw Http2.m773b("TYPE_PING streamId != 0", new Object[0]);
        } else {
            int j = this.f704c.mo17679j();
            int j2 = this.f704c.mo17679j();
            if ((b & 1) == 0) {
                z = false;
            }
            bVar.mo8740a(z, j, j2);
        }
    }

    /* renamed from: h */
    private void m845h(C0504b bVar, int i, byte b, int i2) {
        if (i < 8) {
            throw Http2.m773b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
        } else if (i2 != 0) {
            throw Http2.m773b("TYPE_GOAWAY streamId != 0", new Object[0]);
        } else {
            int j = this.f704c.mo17679j();
            int j2 = this.f704c.mo17679j();
            int i3 = i - 8;
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(j2);
            if (fromHttp2 == null) {
                throw Http2.m773b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(j2));
            }
            ByteString fVar = ByteString.EMPTY;
            if (i3 > 0) {
                fVar = this.f704c.mo17657c((long) i3);
            }
            bVar.mo8739a(j, fromHttp2, fVar);
        }
    }

    /* renamed from: i */
    private void m846i(C0504b bVar, int i, byte b, int i2) {
        if (i != 4) {
            throw Http2.m773b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
        }
        long j = ((long) this.f704c.mo17679j()) & 2147483647L;
        if (j == 0) {
            throw Http2.m773b("windowSizeIncrement was 0", Long.valueOf(j));
        } else {
            bVar.mo8737a(i2, j);
        }
    }

    public void close() {
        this.f704c.close();
    }

    /* renamed from: a */
    static int m835a(BufferedSource eVar) {
        return ((eVar.mo17672h() & 255) << 16) | ((eVar.mo17672h() & 255) << 8) | (eVar.mo17672h() & 255);
    }

    /* renamed from: a */
    static int m834a(int i, byte b, short s) {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        throw Http2.m773b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
    }
}
