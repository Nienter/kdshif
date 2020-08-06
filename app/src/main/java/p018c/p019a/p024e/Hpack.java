package p018c.p019a.p024e;

import android.support.p001v4.media.TransportMediator;
import android.support.p004v7.widget.ActivityChooserView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import p018c.p019a.Util;
import p033d.Buffer;
import p033d.BufferedSource;
import p033d.ByteString;
import p033d.Okio;
import p033d.Source;

/* renamed from: c.a.e.d */
final class Hpack {

    /* renamed from: a */
    static final Header[] f600a = {new Header(Header.f596f, ""), new Header(Header.f593c, "GET"), new Header(Header.f593c, "POST"), new Header(Header.f594d, "/"), new Header(Header.f594d, "/index.html"), new Header(Header.f595e, "http"), new Header(Header.f595e, "https"), new Header(Header.f592b, "200"), new Header(Header.f592b, "204"), new Header(Header.f592b, "206"), new Header(Header.f592b, "304"), new Header(Header.f592b, "400"), new Header(Header.f592b, "404"), new Header(Header.f592b, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};

    /* renamed from: b */
    static final Map<ByteString, Integer> f601b = m744a();

    /* renamed from: c.a.e.d$a */
    /* compiled from: Hpack */
    static final class C0486a {

        /* renamed from: a */
        Header[] f602a;

        /* renamed from: b */
        int f603b;

        /* renamed from: c */
        int f604c;

        /* renamed from: d */
        int f605d;

        /* renamed from: e */
        private final List<Header> f606e;

        /* renamed from: f */
        private final BufferedSource f607f;

        /* renamed from: g */
        private final int f608g;

        /* renamed from: h */
        private int f609h;

        C0486a(int i, Source sVar) {
            this(i, i, sVar);
        }

        C0486a(int i, int i2, Source sVar) {
            this.f606e = new ArrayList();
            this.f602a = new Header[8];
            this.f603b = this.f602a.length - 1;
            this.f604c = 0;
            this.f605d = 0;
            this.f608g = i;
            this.f609h = i2;
            this.f607f = Okio.m3590a(sVar);
        }

        /* renamed from: d */
        private void m749d() {
            if (this.f609h >= this.f605d) {
                return;
            }
            if (this.f609h == 0) {
                m751e();
            } else {
                m745a(this.f605d - this.f609h);
            }
        }

        /* renamed from: e */
        private void m751e() {
            Arrays.fill(this.f602a, null);
            this.f603b = this.f602a.length - 1;
            this.f604c = 0;
            this.f605d = 0;
        }

        /* renamed from: a */
        private int m745a(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f602a.length;
                while (true) {
                    length--;
                    if (length < this.f603b || i <= 0) {
                        System.arraycopy(this.f602a, this.f603b + 1, this.f602a, this.f603b + 1 + i2, this.f604c);
                        this.f603b += i2;
                    } else {
                        i -= this.f602a[length].f599i;
                        this.f605d -= this.f602a[length].f599i;
                        this.f604c--;
                        i2++;
                    }
                }
                System.arraycopy(this.f602a, this.f603b + 1, this.f602a, this.f603b + 1 + i2, this.f604c);
                this.f603b += i2;
            }
            return i2;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8700a() {
            while (!this.f607f.mo17664e()) {
                byte h = this.f607f.mo17672h() & 255;
                if (h == 128) {
                    throw new IOException("index == 0");
                } else if ((h & 128) == 128) {
                    m747b(mo8699a((int) h, (int) TransportMediator.KEYCODE_MEDIA_PAUSE) - 1);
                } else if (h == 64) {
                    m755g();
                } else if ((h & 64) == 64) {
                    m752e(mo8699a((int) h, 63) - 1);
                } else if ((h & 32) == 32) {
                    this.f609h = mo8699a((int) h, 31);
                    if (this.f609h < 0 || this.f609h > this.f608g) {
                        throw new IOException("Invalid dynamic table size update " + this.f609h);
                    }
                    m749d();
                } else if (h == 16 || h == 0) {
                    m754f();
                } else {
                    m750d(mo8699a((int) h, 15) - 1);
                }
            }
        }

        /* renamed from: b */
        public List<Header> mo8701b() {
            ArrayList arrayList = new ArrayList(this.f606e);
            this.f606e.clear();
            return arrayList;
        }

        /* renamed from: b */
        private void m747b(int i) {
            if (m756g(i)) {
                this.f606e.add(Hpack.f600a[i]);
                return;
            }
            int c = m748c(i - Hpack.f600a.length);
            if (c < 0 || c > this.f602a.length - 1) {
                throw new IOException("Header index too large " + (i + 1));
            }
            this.f606e.add(this.f602a[c]);
        }

        /* renamed from: c */
        private int m748c(int i) {
            return this.f603b + 1 + i;
        }

        /* renamed from: d */
        private void m750d(int i) {
            this.f606e.add(new Header(m753f(i), mo8702c()));
        }

        /* renamed from: f */
        private void m754f() {
            this.f606e.add(new Header(Hpack.m743a(mo8702c()), mo8702c()));
        }

        /* renamed from: e */
        private void m752e(int i) {
            m746a(-1, new Header(m753f(i), mo8702c()));
        }

        /* renamed from: g */
        private void m755g() {
            m746a(-1, new Header(Hpack.m743a(mo8702c()), mo8702c()));
        }

        /* renamed from: f */
        private ByteString m753f(int i) {
            if (m756g(i)) {
                return Hpack.f600a[i].f597g;
            }
            return this.f602a[m748c(i - Hpack.f600a.length)].f597g;
        }

        /* renamed from: g */
        private boolean m756g(int i) {
            return i >= 0 && i <= Hpack.f600a.length + -1;
        }

        /* renamed from: a */
        private void m746a(int i, Header cVar) {
            int i2;
            this.f606e.add(cVar);
            int i3 = cVar.f599i;
            if (i != -1) {
                i3 -= this.f602a[m748c(i)].f599i;
            }
            if (i3 > this.f609h) {
                m751e();
                return;
            }
            int a = m745a((this.f605d + i3) - this.f609h);
            if (i == -1) {
                if (this.f604c + 1 > this.f602a.length) {
                    Header[] cVarArr = new Header[(this.f602a.length * 2)];
                    System.arraycopy(this.f602a, 0, cVarArr, this.f602a.length, this.f602a.length);
                    this.f603b = this.f602a.length - 1;
                    this.f602a = cVarArr;
                }
                this.f603b = this.f603b - 1;
                this.f602a[i2] = cVar;
                this.f604c++;
            } else {
                this.f602a[a + m748c(i) + i] = cVar;
            }
            this.f605d = i3 + this.f605d;
        }

        /* renamed from: h */
        private int m757h() {
            return this.f607f.mo17672h() & 255;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo8699a(int i, int i2) {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int h = m757h();
                if ((h & 128) == 0) {
                    return (h << i4) + i2;
                }
                i2 += (h & TransportMediator.KEYCODE_MEDIA_PAUSE) << i4;
                i4 += 7;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public ByteString mo8702c() {
            int h = m757h();
            boolean z = (h & 128) == 128;
            int a = mo8699a(h, (int) TransportMediator.KEYCODE_MEDIA_PAUSE);
            if (z) {
                return ByteString.m3563of(Huffman.m909a().mo8787a(this.f607f.mo17668f((long) a)));
            }
            return this.f607f.mo17657c((long) a);
        }
    }

    /* renamed from: c.a.e.d$b */
    /* compiled from: Hpack */
    static final class C0487b {

        /* renamed from: a */
        int f610a;

        /* renamed from: b */
        int f611b;

        /* renamed from: c */
        Header[] f612c;

        /* renamed from: d */
        int f613d;

        /* renamed from: e */
        int f614e;

        /* renamed from: f */
        int f615f;

        /* renamed from: g */
        private final Buffer f616g;

        /* renamed from: h */
        private final boolean f617h;

        /* renamed from: i */
        private int f618i;

        /* renamed from: j */
        private boolean f619j;

        C0487b(Buffer cVar) {
            this(4096, true, cVar);
        }

        C0487b(int i, boolean z, Buffer cVar) {
            this.f618i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.f612c = new Header[8];
            this.f613d = this.f612c.length - 1;
            this.f614e = 0;
            this.f615f = 0;
            this.f610a = i;
            this.f611b = i;
            this.f617h = z;
            this.f616g = cVar;
        }

        /* renamed from: a */
        private void m762a() {
            Arrays.fill(this.f612c, null);
            this.f613d = this.f612c.length - 1;
            this.f614e = 0;
            this.f615f = 0;
        }

        /* renamed from: b */
        private int m764b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f612c.length;
                while (true) {
                    length--;
                    if (length < this.f613d || i <= 0) {
                        System.arraycopy(this.f612c, this.f613d + 1, this.f612c, this.f613d + 1 + i2, this.f614e);
                        Arrays.fill(this.f612c, this.f613d + 1, this.f613d + 1 + i2, null);
                        this.f613d += i2;
                    } else {
                        i -= this.f612c[length].f599i;
                        this.f615f -= this.f612c[length].f599i;
                        this.f614e--;
                        i2++;
                    }
                }
                System.arraycopy(this.f612c, this.f613d + 1, this.f612c, this.f613d + 1 + i2, this.f614e);
                Arrays.fill(this.f612c, this.f613d + 1, this.f613d + 1 + i2, null);
                this.f613d += i2;
            }
            return i2;
        }

        /* renamed from: a */
        private void m763a(Header cVar) {
            int i;
            int i2 = cVar.f599i;
            if (i2 > this.f611b) {
                m762a();
                return;
            }
            m764b((this.f615f + i2) - this.f611b);
            if (this.f614e + 1 > this.f612c.length) {
                Header[] cVarArr = new Header[(this.f612c.length * 2)];
                System.arraycopy(this.f612c, 0, cVarArr, this.f612c.length, this.f612c.length);
                this.f613d = this.f612c.length - 1;
                this.f612c = cVarArr;
            }
            this.f613d = this.f613d - 1;
            this.f612c[i] = cVar;
            this.f614e++;
            this.f615f = i2 + this.f615f;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8706a(List<Header> list) {
            int i;
            int i2;
            if (this.f619j) {
                if (this.f618i < this.f611b) {
                    mo8704a(this.f618i, 31, 32);
                }
                this.f619j = false;
                this.f618i = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                mo8704a(this.f611b, 31, 32);
            }
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Header cVar = list.get(i3);
                ByteString asciiLowercase = cVar.f597g.toAsciiLowercase();
                ByteString fVar = cVar.f598h;
                Integer num = Hpack.f601b.get(asciiLowercase);
                if (num != null) {
                    i = num.intValue() + 1;
                    if (i > 1 && i < 8) {
                        if (Util.m656a((Object) Hpack.f600a[i - 1].f598h, (Object) fVar)) {
                            i2 = i;
                        } else if (Util.m656a((Object) Hpack.f600a[i].f598h, (Object) fVar)) {
                            i2 = i + 1;
                        }
                    }
                    i2 = -1;
                } else {
                    i = -1;
                    i2 = -1;
                }
                if (i2 == -1) {
                    int i4 = this.f613d + 1;
                    int length = this.f612c.length;
                    while (true) {
                        if (i4 >= length) {
                            break;
                        }
                        if (Util.m656a((Object) this.f612c[i4].f597g, (Object) asciiLowercase)) {
                            if (Util.m656a((Object) this.f612c[i4].f598h, (Object) fVar)) {
                                i2 = (i4 - this.f613d) + Hpack.f600a.length;
                                break;
                            } else if (i == -1) {
                                i = (i4 - this.f613d) + Hpack.f600a.length;
                            }
                        }
                        i4++;
                    }
                }
                if (i2 != -1) {
                    mo8704a(i2, TransportMediator.KEYCODE_MEDIA_PAUSE, 128);
                } else if (i == -1) {
                    this.f616g.mo17677i(64);
                    mo8705a(asciiLowercase);
                    mo8705a(fVar);
                    m763a(cVar);
                } else if (!asciiLowercase.startsWith(Header.f591a) || Header.f596f.equals(asciiLowercase)) {
                    mo8704a(i, 63, 64);
                    mo8705a(fVar);
                    m763a(cVar);
                } else {
                    mo8704a(i, 15, 0);
                    mo8705a(fVar);
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8704a(int i, int i2, int i3) {
            if (i < i2) {
                this.f616g.mo17677i(i3 | i);
                return;
            }
            this.f616g.mo17677i(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.f616g.mo17677i((i4 & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
                i4 >>>= 7;
            }
            this.f616g.mo17677i(i4);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8705a(ByteString fVar) {
            if (!this.f617h || Huffman.m909a().mo8785a(fVar) >= fVar.size()) {
                mo8704a(fVar.size(), TransportMediator.KEYCODE_MEDIA_PAUSE, 0);
                this.f616g.mo17639a(fVar);
                return;
            }
            Buffer cVar = new Buffer();
            Huffman.m909a().mo8786a(fVar, cVar);
            ByteString o = cVar.mo17686o();
            mo8704a(o.size(), TransportMediator.KEYCODE_MEDIA_PAUSE, 128);
            this.f616g.mo17639a(o);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8703a(int i) {
            this.f610a = i;
            int min = Math.min(i, 16384);
            if (this.f611b != min) {
                if (min < this.f611b) {
                    this.f618i = Math.min(this.f618i, min);
                }
                this.f619j = true;
                this.f611b = min;
                m765b();
            }
        }

        /* renamed from: b */
        private void m765b() {
            if (this.f611b >= this.f615f) {
                return;
            }
            if (this.f611b == 0) {
                m762a();
            } else {
                m764b(this.f615f - this.f611b);
            }
        }
    }

    /* renamed from: a */
    private static Map<ByteString, Integer> m744a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f600a.length);
        for (int i = 0; i < f600a.length; i++) {
            if (!linkedHashMap.containsKey(f600a[i].f597g)) {
                linkedHashMap.put(f600a[i].f597g, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* renamed from: a */
    static ByteString m743a(ByteString fVar) {
        int i = 0;
        int size = fVar.size();
        while (i < size) {
            byte b = fVar.getByte(i);
            if (b < 65 || b > 90) {
                i++;
            } else {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + fVar.utf8());
            }
        }
        return fVar;
    }
}
