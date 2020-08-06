package p018c.p019a.p020a;

import android.support.p004v7.widget.helper.ItemTouchHelper;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import p018c.CacheControl;
import p018c.Headers;
import p018c.Request;
import p018c.Response;
import p018c.p019a.Internal;
import p018c.p019a.p022c.HttpDate;
import p018c.p019a.p022c.HttpHeaders;

/* renamed from: c.a.a.c */
public final class CacheStrategy {

    /* renamed from: a */
    public final Request f425a;

    /* renamed from: b */
    public final Response f426b;

    /* renamed from: c.a.a.c$a */
    /* compiled from: CacheStrategy */
    public static class C0468a {

        /* renamed from: a */
        final long f427a;

        /* renamed from: b */
        final Request f428b;

        /* renamed from: c */
        final Response f429c;

        /* renamed from: d */
        private Date f430d;

        /* renamed from: e */
        private String f431e;

        /* renamed from: f */
        private Date f432f;

        /* renamed from: g */
        private String f433g;

        /* renamed from: h */
        private Date f434h;

        /* renamed from: i */
        private long f435i;

        /* renamed from: j */
        private long f436j;

        /* renamed from: k */
        private String f437k;

        /* renamed from: l */
        private int f438l = -1;

        public C0468a(long j, Request xVar, Response zVar) {
            this.f427a = j;
            this.f428b = xVar;
            this.f429c = zVar;
            if (zVar != null) {
                this.f435i = zVar.mo9056m();
                this.f436j = zVar.mo9057n();
                Headers g = zVar.mo9050g();
                int a = g.mo8937a();
                for (int i = 0; i < a; i++) {
                    String a2 = g.mo8938a(i);
                    String b = g.mo8941b(i);
                    if ("Date".equalsIgnoreCase(a2)) {
                        this.f430d = HttpDate.m675a(b);
                        this.f431e = b;
                    } else if ("Expires".equalsIgnoreCase(a2)) {
                        this.f434h = HttpDate.m675a(b);
                    } else if ("Last-Modified".equalsIgnoreCase(a2)) {
                        this.f432f = HttpDate.m675a(b);
                        this.f433g = b;
                    } else if ("ETag".equalsIgnoreCase(a2)) {
                        this.f437k = b;
                    } else if ("Age".equalsIgnoreCase(a2)) {
                        this.f438l = HttpHeaders.m685b(b, -1);
                    }
                }
            }
        }

        /* renamed from: a */
        public CacheStrategy mo8597a() {
            CacheStrategy b = m544b();
            if (b.f425a == null || !this.f428b.mo9027f().mo8873i()) {
                return b;
            }
            return new CacheStrategy(null, null);
        }

        /* renamed from: b */
        private CacheStrategy m544b() {
            long j;
            String str;
            String str2;
            long j2 = 0;
            if (this.f429c == null) {
                return new CacheStrategy(this.f428b, null);
            }
            if (this.f428b.mo9028g() && this.f429c.mo9049f() == null) {
                return new CacheStrategy(this.f428b, null);
            }
            if (!CacheStrategy.m542a(this.f429c, this.f428b)) {
                return new CacheStrategy(this.f428b, null);
            }
            CacheControl f = this.f428b.mo9027f();
            if (f.mo8865a() || m543a(this.f428b)) {
                return new CacheStrategy(this.f428b, null);
            }
            long d = m546d();
            long c = m545c();
            if (f.mo8867c() != -1) {
                c = Math.min(c, TimeUnit.SECONDS.toMillis((long) f.mo8867c()));
            }
            if (f.mo8872h() != -1) {
                j = TimeUnit.SECONDS.toMillis((long) f.mo8872h());
            } else {
                j = 0;
            }
            CacheControl l = this.f429c.mo9055l();
            if (!l.mo8870f() && f.mo8871g() != -1) {
                j2 = TimeUnit.SECONDS.toMillis((long) f.mo8871g());
            }
            if (l.mo8865a() || d + j >= j2 + c) {
                if (this.f437k != null) {
                    str = "If-None-Match";
                    str2 = this.f437k;
                } else if (this.f432f != null) {
                    str = "If-Modified-Since";
                    str2 = this.f433g;
                } else if (this.f430d == null) {
                    return new CacheStrategy(this.f428b, null);
                } else {
                    str = "If-Modified-Since";
                    str2 = this.f431e;
                }
                Headers.C0534a b = this.f428b.mo9024c().mo8940b();
                Internal.f418a.mo8587a(b, str, str2);
                return new CacheStrategy(this.f428b.mo9026e().mo9031a(b.mo8948a()).mo9036a(), this.f429c);
            }
            Response.C0543a i = this.f429c.mo9052i();
            if (j + d >= c) {
                i.mo9068a("Warning", "110 HttpURLConnection \"Response is stale\"");
            }
            if (d > 86400000 && m547e()) {
                i.mo9068a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
            }
            return new CacheStrategy(null, i.mo9069a());
        }

        /* renamed from: c */
        private long m545c() {
            CacheControl l = this.f429c.mo9055l();
            if (l.mo8867c() != -1) {
                return TimeUnit.SECONDS.toMillis((long) l.mo8867c());
            }
            if (this.f434h != null) {
                long time = this.f434h.getTime() - (this.f430d != null ? this.f430d.getTime() : this.f436j);
                if (time <= 0) {
                    time = 0;
                }
                return time;
            } else if (this.f432f == null || this.f429c.mo9041a().mo9020a().mo8967k() != null) {
                return 0;
            } else {
                long time2 = (this.f430d != null ? this.f430d.getTime() : this.f435i) - this.f432f.getTime();
                if (time2 > 0) {
                    return time2 / 10;
                }
                return 0;
            }
        }

        /* renamed from: d */
        private long m546d() {
            long j = 0;
            if (this.f430d != null) {
                j = Math.max(0, this.f436j - this.f430d.getTime());
            }
            if (this.f438l != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.f438l));
            }
            return j + (this.f436j - this.f435i) + (this.f427a - this.f436j);
        }

        /* renamed from: e */
        private boolean m547e() {
            return this.f429c.mo9055l().mo8867c() == -1 && this.f434h == null;
        }

        /* renamed from: a */
        private static boolean m543a(Request xVar) {
            return (xVar.mo9021a("If-Modified-Since") == null && xVar.mo9021a("If-None-Match") == null) ? false : true;
        }
    }

    CacheStrategy(Request xVar, Response zVar) {
        this.f425a = xVar;
        this.f426b = zVar;
    }

    /* renamed from: a */
    public static boolean m542a(Response zVar, Request xVar) {
        switch (zVar.mo9045c()) {
            case ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION:
            case 203:
            case 204:
            case 300:
            case 301:
            case 308:
            case 404:
            case 405:
            case 410:
            case 414:
            case 501:
                break;
            case 302:
            case 307:
                if (zVar.mo9042a("Expires") == null && zVar.mo9055l().mo8867c() == -1 && !zVar.mo9055l().mo8869e() && !zVar.mo9055l().mo8868d()) {
                    return false;
                }
            default:
                return false;
        }
        return !zVar.mo9055l().mo8866b() && !xVar.mo9027f().mo8866b();
    }
}
