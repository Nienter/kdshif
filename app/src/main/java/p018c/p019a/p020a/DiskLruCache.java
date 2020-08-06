package p018c.p019a.p020a;

import com.facebook.appevents.AppEventsConstants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import p018c.p019a.Util;
import p018c.p019a.p025f.FileSystem;
import p018c.p019a.p026g.Platform;
import p033d.BufferedSink;
import p033d.BufferedSource;
import p033d.Okio;
import p033d.Sink;
import p033d.Source;

/* renamed from: c.a.a.d */
public final class DiskLruCache implements Closeable, Flushable {

    /* renamed from: a */
    static final Pattern f439a = Pattern.compile("[a-z0-9_-]{1,120}");

    /* renamed from: m */
    static final /* synthetic */ boolean f440m = (!DiskLruCache.class.desiredAssertionStatus());

    /* renamed from: b */
    final FileSystem f441b;

    /* renamed from: c */
    final File f442c;

    /* renamed from: d */
    final int f443d;

    /* renamed from: e */
    BufferedSink f444e;

    /* renamed from: f */
    final LinkedHashMap<String, C0473b> f445f = new LinkedHashMap<>(0, 0.75f, true);

    /* renamed from: g */
    int f446g;

    /* renamed from: h */
    boolean f447h;

    /* renamed from: i */
    boolean f448i;

    /* renamed from: j */
    boolean f449j;

    /* renamed from: k */
    boolean f450k;

    /* renamed from: l */
    boolean f451l;

    /* renamed from: n */
    private final File f452n;

    /* renamed from: o */
    private final File f453o;

    /* renamed from: p */
    private final File f454p;

    /* renamed from: q */
    private final int f455q;

    /* renamed from: r */
    private long f456r;

    /* renamed from: s */
    private long f457s = 0;

    /* renamed from: t */
    private long f458t = 0;

    /* renamed from: u */
    private final Executor f459u;

    /* renamed from: v */
    private final Runnable f460v = new Runnable() {
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
            r4.f461a.f451l = true;
            r4.f461a.f444e = p033d.Okio.m3589a(p033d.Okio.m3591a());
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        public void run() {
            boolean z = true;
            synchronized (DiskLruCache.this) {
                if (DiskLruCache.this.f448i) {
                    z = false;
                }
                if (!z && !DiskLruCache.this.f449j) {
                    try {
                        DiskLruCache.this.mo8609e();
                    } catch (IOException e) {
                        DiskLruCache.this.f450k = true;
                    }
                    if (DiskLruCache.this.mo8605c()) {
                        DiskLruCache.this.mo8604b();
                        DiskLruCache.this.f446g = 0;
                    }
                }
            }
        }
    };

    /* renamed from: c.a.a.d$a */
    /* compiled from: DiskLruCache */
    public final class C0471a {

        /* renamed from: a */
        final C0473b f464a;

        /* renamed from: b */
        final boolean[] f465b;

        /* renamed from: d */
        private boolean f467d;

        C0471a(C0473b bVar) {
            this.f464a = bVar;
            this.f465b = bVar.f473e ? null : new boolean[DiskLruCache.this.f443d];
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8615a() {
            if (this.f464a.f474f == this) {
                for (int i = 0; i < DiskLruCache.this.f443d; i++) {
                    try {
                        DiskLruCache.this.f441b.mo8809d(this.f464a.f472d[i]);
                    } catch (IOException e) {
                    }
                }
                this.f464a.f474f = null;
            }
        }

        /* renamed from: a */
        public Sink mo8614a(int i) {
            Sink a;
            synchronized (DiskLruCache.this) {
                if (this.f467d) {
                    throw new IllegalStateException();
                } else if (this.f464a.f474f != this) {
                    a = Okio.m3591a();
                } else {
                    if (!this.f464a.f473e) {
                        this.f465b[i] = true;
                    }
                    try {
                        a = new FaultHidingSink(DiskLruCache.this.f441b.mo8807b(this.f464a.f472d[i])) {
                            /* access modifiers changed from: protected */
                            /* renamed from: a */
                            public void mo8613a(IOException iOException) {
                                synchronized (DiskLruCache.this) {
                                    C0471a.this.mo8615a();
                                }
                            }
                        };
                    } catch (FileNotFoundException e) {
                        a = Okio.m3591a();
                    }
                }
            }
            return a;
        }

        /* renamed from: b */
        public void mo8616b() {
            synchronized (DiskLruCache.this) {
                if (this.f467d) {
                    throw new IllegalStateException();
                }
                if (this.f464a.f474f == this) {
                    DiskLruCache.this.mo8601a(this, true);
                }
                this.f467d = true;
            }
        }

        /* renamed from: c */
        public void mo8617c() {
            synchronized (DiskLruCache.this) {
                if (this.f467d) {
                    throw new IllegalStateException();
                }
                if (this.f464a.f474f == this) {
                    DiskLruCache.this.mo8601a(this, false);
                }
                this.f467d = true;
            }
        }
    }

    /* renamed from: c.a.a.d$b */
    /* compiled from: DiskLruCache */
    private final class C0473b {

        /* renamed from: a */
        final String f469a;

        /* renamed from: b */
        final long[] f470b;

        /* renamed from: c */
        final File[] f471c;

        /* renamed from: d */
        final File[] f472d;

        /* renamed from: e */
        boolean f473e;

        /* renamed from: f */
        C0471a f474f;

        /* renamed from: g */
        long f475g;

        C0473b(String str) {
            this.f469a = str;
            this.f470b = new long[DiskLruCache.this.f443d];
            this.f471c = new File[DiskLruCache.this.f443d];
            this.f472d = new File[DiskLruCache.this.f443d];
            StringBuilder append = new StringBuilder(str).append('.');
            int length = append.length();
            for (int i = 0; i < DiskLruCache.this.f443d; i++) {
                append.append(i);
                this.f471c[i] = new File(DiskLruCache.this.f442c, append.toString());
                append.append(".tmp");
                this.f472d[i] = new File(DiskLruCache.this.f442c, append.toString());
                append.setLength(length);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8620a(String[] strArr) {
            if (strArr.length != DiskLruCache.this.f443d) {
                throw m574b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f470b[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m574b(strArr);
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo8619a(BufferedSink dVar) {
            for (long k : this.f470b) {
                dVar.mo17677i(32).mo17681k(k);
            }
        }

        /* renamed from: b */
        private IOException m574b(String[] strArr) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C0474c mo8618a() {
            int i = 0;
            if (!Thread.holdsLock(DiskLruCache.this)) {
                throw new AssertionError();
            }
            Source[] sVarArr = new Source[DiskLruCache.this.f443d];
            long[] jArr = (long[]) this.f470b.clone();
            int i2 = 0;
            while (i2 < DiskLruCache.this.f443d) {
                try {
                    sVarArr[i2] = DiskLruCache.this.f441b.mo8805a(this.f471c[i2]);
                    i2++;
                } catch (FileNotFoundException e) {
                    while (i < DiskLruCache.this.f443d && sVarArr[i] != null) {
                        Util.m652a((Closeable) sVarArr[i]);
                        i++;
                    }
                    try {
                        DiskLruCache.this.mo8602a(this);
                    } catch (IOException e2) {
                    }
                    return null;
                }
            }
            return new C0474c(this.f469a, this.f475g, sVarArr, jArr);
        }
    }

    /* renamed from: c.a.a.d$c */
    /* compiled from: DiskLruCache */
    public final class C0474c implements Closeable {

        /* renamed from: b */
        private final String f478b;

        /* renamed from: c */
        private final long f479c;

        /* renamed from: d */
        private final Source[] f480d;

        /* renamed from: e */
        private final long[] f481e;

        C0474c(String str, long j, Source[] sVarArr, long[] jArr) {
            this.f478b = str;
            this.f479c = j;
            this.f480d = sVarArr;
            this.f481e = jArr;
        }

        /* renamed from: a */
        public C0471a mo8621a() {
            return DiskLruCache.this.mo8598a(this.f478b, this.f479c);
        }

        /* renamed from: a */
        public Source mo8622a(int i) {
            return this.f480d[i];
        }

        public void close() {
            for (Source a : this.f480d) {
                Util.m652a((Closeable) a);
            }
        }
    }

    DiskLruCache(FileSystem aVar, File file, int i, int i2, long j, Executor executor) {
        this.f441b = aVar;
        this.f442c = file;
        this.f455q = i;
        this.f452n = new File(file, "journal");
        this.f453o = new File(file, "journal.tmp");
        this.f454p = new File(file, "journal.bkp");
        this.f443d = i2;
        this.f456r = j;
        this.f459u = executor;
    }

    /* renamed from: a */
    public synchronized void mo8600a() {
        if (!f440m && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (!this.f448i) {
            if (this.f441b.mo8810e(this.f454p)) {
                if (this.f441b.mo8810e(this.f452n)) {
                    this.f441b.mo8809d(this.f454p);
                } else {
                    this.f441b.mo8806a(this.f454p, this.f452n);
                }
            }
            if (this.f441b.mo8810e(this.f452n)) {
                try {
                    m552g();
                    m554i();
                    this.f448i = true;
                } catch (IOException e) {
                    Platform.m981b().mo8816a(5, "DiskLruCache " + this.f442c + " is corrupt: " + e.getMessage() + ", removing", (Throwable) e);
                    mo8610f();
                    this.f449j = false;
                } catch (Throwable th) {
                    this.f449j = false;
                    throw th;
                }
            }
            mo8604b();
            this.f448i = true;
        }
    }

    /* renamed from: a */
    public static DiskLruCache m549a(FileSystem aVar, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            return new DiskLruCache(aVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.m650a("OkHttp DiskLruCache", true)));
        }
    }

    /* renamed from: g */
    private void m552g() {
        int i;
        BufferedSource a = Okio.m3590a(this.f441b.mo8805a(this.f452n));
        try {
            String q = a.mo17688q();
            String q2 = a.mo17688q();
            String q3 = a.mo17688q();
            String q4 = a.mo17688q();
            String q5 = a.mo17688q();
            if (!"libcore.io.DiskLruCache".equals(q) || !AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(q2) || !Integer.toString(this.f455q).equals(q3) || !Integer.toString(this.f443d).equals(q4) || !"".equals(q5)) {
                throw new IOException("unexpected journal header: [" + q + ", " + q2 + ", " + q4 + ", " + q5 + "]");
            }
            i = 0;
            while (true) {
                m550d(a.mo17688q());
                i++;
            }
        } catch (EOFException e) {
            this.f446g = i - this.f445f.size();
            if (!a.mo17664e()) {
                mo8604b();
            } else {
                this.f444e = m553h();
            }
            Util.m652a((Closeable) a);
        } catch (Throwable th) {
            Util.m652a((Closeable) a);
            throw th;
        }
    }

    /* renamed from: h */
    private BufferedSink m553h() {
        return Okio.m3589a((Sink) new FaultHidingSink(this.f441b.mo8808c(this.f452n)) {

            /* renamed from: a */
            static final /* synthetic */ boolean f462a = (!DiskLruCache.class.desiredAssertionStatus());

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void mo8613a(IOException iOException) {
                if (f462a || Thread.holdsLock(DiskLruCache.this)) {
                    DiskLruCache.this.f447h = true;
                    return;
                }
                throw new AssertionError();
            }
        });
    }

    /* renamed from: d */
    private void m550d(String str) {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf != "REMOVE".length() || !str.startsWith("REMOVE")) {
                str2 = substring;
            } else {
                this.f445f.remove(substring);
                return;
            }
        } else {
            str2 = str.substring(i, indexOf2);
        }
        C0473b bVar = this.f445f.get(str2);
        if (bVar == null) {
            bVar = new C0473b(str2);
            this.f445f.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.f473e = true;
            bVar.f474f = null;
            bVar.mo8620a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.f474f = new C0471a(bVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* renamed from: i */
    private void m554i() {
        this.f441b.mo8809d(this.f453o);
        Iterator<C0473b> it = this.f445f.values().iterator();
        while (it.hasNext()) {
            C0473b next = it.next();
            if (next.f474f == null) {
                for (int i = 0; i < this.f443d; i++) {
                    this.f457s += next.f470b[i];
                }
            } else {
                next.f474f = null;
                for (int i2 = 0; i2 < this.f443d; i2++) {
                    this.f441b.mo8809d(next.f471c[i2]);
                    this.f441b.mo8809d(next.f472d[i2]);
                }
                it.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void mo8604b() {
        if (this.f444e != null) {
            this.f444e.close();
        }
        BufferedSink a = Okio.m3589a(this.f441b.mo8807b(this.f453o));
        try {
            a.mo17652b("libcore.io.DiskLruCache").mo17677i(10);
            a.mo17652b(AppEventsConstants.EVENT_PARAM_VALUE_YES).mo17677i(10);
            a.mo17681k((long) this.f455q).mo17677i(10);
            a.mo17681k((long) this.f443d).mo17677i(10);
            a.mo17677i(10);
            for (C0473b next : this.f445f.values()) {
                if (next.f474f != null) {
                    a.mo17652b("DIRTY").mo17677i(32);
                    a.mo17652b(next.f469a);
                    a.mo17677i(10);
                } else {
                    a.mo17652b("CLEAN").mo17677i(32);
                    a.mo17652b(next.f469a);
                    next.mo8619a(a);
                    a.mo17677i(10);
                }
            }
            a.close();
            if (this.f441b.mo8810e(this.f452n)) {
                this.f441b.mo8806a(this.f452n, this.f454p);
            }
            this.f441b.mo8806a(this.f453o, this.f452n);
            this.f441b.mo8809d(this.f454p);
            this.f444e = m553h();
            this.f447h = false;
            this.f451l = false;
        } catch (Throwable th) {
            a.close();
            throw th;
        }
    }

    /* renamed from: a */
    public synchronized C0474c mo8599a(String str) {
        C0474c cVar;
        mo8600a();
        m555j();
        m551e(str);
        C0473b bVar = this.f445f.get(str);
        if (bVar == null || !bVar.f473e) {
            cVar = null;
        } else {
            cVar = bVar.mo8618a();
            if (cVar == null) {
                cVar = null;
            } else {
                this.f446g++;
                this.f444e.mo17652b("READ").mo17677i(32).mo17652b(str).mo17677i(10);
                if (mo8605c()) {
                    this.f459u.execute(this.f460v);
                }
            }
        }
        return cVar;
    }

    /* renamed from: b */
    public C0471a mo8603b(String str) {
        return mo8598a(str, -1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized C0471a mo8598a(String str, long j) {
        C0471a aVar;
        C0473b bVar;
        mo8600a();
        m555j();
        m551e(str);
        C0473b bVar2 = this.f445f.get(str);
        if (j == -1 || (bVar2 != null && bVar2.f475g == j)) {
            if (bVar2 != null) {
                if (bVar2.f474f != null) {
                    aVar = null;
                }
            }
            if (this.f450k || this.f451l) {
                this.f459u.execute(this.f460v);
                aVar = null;
            } else {
                this.f444e.mo17652b("DIRTY").mo17677i(32).mo17652b(str).mo17677i(10);
                this.f444e.flush();
                if (this.f447h) {
                    aVar = null;
                } else {
                    if (bVar2 == null) {
                        C0473b bVar3 = new C0473b(str);
                        this.f445f.put(str, bVar3);
                        bVar = bVar3;
                    } else {
                        bVar = bVar2;
                    }
                    aVar = new C0471a(bVar);
                    bVar.f474f = aVar;
                }
            }
        } else {
            aVar = null;
        }
        return aVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo8601a(C0471a aVar, boolean z) {
        synchronized (this) {
            C0473b bVar = aVar.f464a;
            if (bVar.f474f != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!bVar.f473e) {
                    int i = 0;
                    while (true) {
                        if (i < this.f443d) {
                            if (!aVar.f465b[i]) {
                                aVar.mo8617c();
                                throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                            } else if (!this.f441b.mo8810e(bVar.f472d[i])) {
                                aVar.mo8617c();
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
            for (int i2 = 0; i2 < this.f443d; i2++) {
                File file = bVar.f472d[i2];
                if (!z) {
                    this.f441b.mo8809d(file);
                } else if (this.f441b.mo8810e(file)) {
                    File file2 = bVar.f471c[i2];
                    this.f441b.mo8806a(file, file2);
                    long j = bVar.f470b[i2];
                    long f = this.f441b.mo8811f(file2);
                    bVar.f470b[i2] = f;
                    this.f457s = (this.f457s - j) + f;
                }
            }
            this.f446g++;
            bVar.f474f = null;
            if (bVar.f473e || z) {
                bVar.f473e = true;
                this.f444e.mo17652b("CLEAN").mo17677i(32);
                this.f444e.mo17652b(bVar.f469a);
                bVar.mo8619a(this.f444e);
                this.f444e.mo17677i(10);
                if (z) {
                    long j2 = this.f458t;
                    this.f458t = 1 + j2;
                    bVar.f475g = j2;
                }
            } else {
                this.f445f.remove(bVar.f469a);
                this.f444e.mo17652b("REMOVE").mo17677i(32);
                this.f444e.mo17652b(bVar.f469a);
                this.f444e.mo17677i(10);
            }
            this.f444e.flush();
            if (this.f457s > this.f456r || mo8605c()) {
                this.f459u.execute(this.f460v);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo8605c() {
        return this.f446g >= 2000 && this.f446g >= this.f445f.size();
    }

    /* renamed from: c */
    public synchronized boolean mo8606c(String str) {
        boolean a;
        mo8600a();
        m555j();
        m551e(str);
        C0473b bVar = this.f445f.get(str);
        if (bVar == null) {
            a = false;
        } else {
            a = mo8602a(bVar);
            if (a && this.f457s <= this.f456r) {
                this.f450k = false;
            }
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo8602a(C0473b bVar) {
        if (bVar.f474f != null) {
            bVar.f474f.mo8615a();
        }
        for (int i = 0; i < this.f443d; i++) {
            this.f441b.mo8809d(bVar.f471c[i]);
            this.f457s -= bVar.f470b[i];
            bVar.f470b[i] = 0;
        }
        this.f446g++;
        this.f444e.mo17652b("REMOVE").mo17677i(32).mo17652b(bVar.f469a).mo17677i(10);
        this.f445f.remove(bVar.f469a);
        if (mo8605c()) {
            this.f459u.execute(this.f460v);
        }
        return true;
    }

    /* renamed from: d */
    public synchronized boolean mo8608d() {
        return this.f449j;
    }

    /* renamed from: j */
    private synchronized void m555j() {
        if (mo8608d()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() {
        if (this.f448i) {
            m555j();
            mo8609e();
            this.f444e.flush();
        }
    }

    public synchronized void close() {
        if (!this.f448i || this.f449j) {
            this.f449j = true;
        } else {
            for (C0473b bVar : (C0473b[]) this.f445f.values().toArray(new C0473b[this.f445f.size()])) {
                if (bVar.f474f != null) {
                    bVar.f474f.mo8617c();
                }
            }
            mo8609e();
            this.f444e.close();
            this.f444e = null;
            this.f449j = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo8609e() {
        while (this.f457s > this.f456r) {
            mo8602a(this.f445f.values().iterator().next());
        }
        this.f450k = false;
    }

    /* renamed from: f */
    public void mo8610f() {
        close();
        this.f441b.mo8812g(this.f442c);
    }

    /* renamed from: e */
    private void m551e(String str) {
        if (!f439a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
