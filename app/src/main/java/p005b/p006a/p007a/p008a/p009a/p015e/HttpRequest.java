package p005b.p006a.p007a.p008a.p009a.p015e;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;

/* renamed from: b.a.a.a.a.e.d */
public class HttpRequest {

    /* renamed from: b */
    private static final String[] f235b = new String[0];

    /* renamed from: c */
    private static C0455b f236c = C0455b.f252a;

    /* renamed from: a */
    public final URL f237a;

    /* renamed from: d */
    private HttpURLConnection f238d = null;

    /* renamed from: e */
    private final String f239e;

    /* renamed from: f */
    private C0459e f240f;

    /* renamed from: g */
    private boolean f241g;

    /* renamed from: h */
    private boolean f242h = true;

    /* renamed from: i */
    private boolean f243i = false;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f244j = 8192;

    /* renamed from: k */
    private String f245k;

    /* renamed from: l */
    private int f246l;

    /* renamed from: b.a.a.a.a.e.d$a */
    /* compiled from: HttpRequest */
    protected static abstract class C0454a<V> extends C0458d<V> {

        /* renamed from: a */
        private final Closeable f250a;

        /* renamed from: b */
        private final boolean f251b;

        protected C0454a(Closeable closeable, boolean z) {
            this.f250a = closeable;
            this.f251b = z;
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void mo8464c() {
            if (this.f250a instanceof Flushable) {
                ((Flushable) this.f250a).flush();
            }
            if (this.f251b) {
                try {
                    this.f250a.close();
                } catch (IOException e) {
                }
            } else {
                this.f250a.close();
            }
        }
    }

    /* renamed from: b.a.a.a.a.e.d$b */
    /* compiled from: HttpRequest */
    public interface C0455b {

        /* renamed from: a */
        public static final C0455b f252a = new C0455b() {
            /* renamed from: a */
            public HttpURLConnection mo8465a(URL url) {
                return (HttpURLConnection) url.openConnection();
            }

            /* renamed from: a */
            public HttpURLConnection mo8466a(URL url, Proxy proxy) {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        };

        /* renamed from: a */
        HttpURLConnection mo8465a(URL url);

        /* renamed from: a */
        HttpURLConnection mo8466a(URL url, Proxy proxy);
    }

    /* renamed from: b.a.a.a.a.e.d$c */
    /* compiled from: HttpRequest */
    public static class C0457c extends RuntimeException {
        private static final long serialVersionUID = -1170466989781746231L;

        protected C0457c(IOException iOException) {
            super(iOException);
        }

        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    /* renamed from: b.a.a.a.a.e.d$d */
    /* compiled from: HttpRequest */
    protected static abstract class C0458d<V> implements Callable<V> {
        /* access modifiers changed from: protected */
        /* renamed from: b */
        public abstract V mo8463b();

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public abstract void mo8464c();

        protected C0458d() {
        }

        public V call() {
            boolean z = true;
            try {
                V b = mo8463b();
                try {
                    mo8464c();
                    return b;
                } catch (IOException e) {
                    throw new C0457c(e);
                }
            } catch (C0457c e2) {
                throw e2;
            } catch (IOException e3) {
                throw new C0457c(e3);
            } catch (Throwable th) {
                th = th;
            }
            try {
                mo8464c();
            } catch (IOException e4) {
                if (!z) {
                    throw new C0457c(e4);
                }
            }
            throw th;
        }
    }

    /* renamed from: b.a.a.a.a.e.d$e */
    /* compiled from: HttpRequest */
    public static class C0459e extends BufferedOutputStream {

        /* renamed from: a */
        private final CharsetEncoder f253a;

        public C0459e(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.f253a = Charset.forName(HttpRequest.m310f(str)).newEncoder();
        }

        /* renamed from: a */
        public C0459e mo8469a(String str) {
            ByteBuffer encode = this.f253a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public static String m310f(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    /* renamed from: a */
    private static StringBuilder m302a(String str, StringBuilder sb) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            sb.append('/');
        }
        return sb;
    }

    /* renamed from: b */
    private static StringBuilder m305b(String str, StringBuilder sb) {
        int indexOf = str.indexOf(63);
        int length = sb.length() - 1;
        if (indexOf == -1) {
            sb.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            sb.append('&');
        }
        return sb;
    }

    /* renamed from: a */
    public static String m300a(CharSequence charSequence) {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            if (url.getPort() != -1) {
                host = host + ':' + Integer.toString(r0);
            }
            try {
                String aSCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = aSCIIString.indexOf(63);
                if (indexOf <= 0 || indexOf + 1 >= aSCIIString.length()) {
                    return aSCIIString;
                }
                return aSCIIString.substring(0, indexOf + 1) + aSCIIString.substring(indexOf + 1).replace("+", "%2B");
            } catch (URISyntaxException e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new C0457c(iOException);
            }
        } catch (IOException e2) {
            throw new C0457c(e2);
        }
    }

    /* renamed from: a */
    public static String m301a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder sb = new StringBuilder(charSequence2);
        m302a(charSequence2, sb);
        m305b(charSequence2, sb);
        Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
        Map.Entry next = it.next();
        sb.append(next.getKey().toString());
        sb.append('=');
        Object value = next.getValue();
        if (value != null) {
            sb.append(value);
        }
        while (it.hasNext()) {
            sb.append('&');
            Map.Entry next2 = it.next();
            sb.append(next2.getKey().toString());
            sb.append('=');
            Object value2 = next2.getValue();
            if (value2 != null) {
                sb.append(value2);
            }
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static HttpRequest m303b(CharSequence charSequence) {
        return new HttpRequest(charSequence, "GET");
    }

    /* renamed from: a */
    public static HttpRequest m299a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String a = m301a(charSequence, map);
        if (z) {
            a = m300a((CharSequence) a);
        }
        return m303b((CharSequence) a);
    }

    /* renamed from: c */
    public static HttpRequest m306c(CharSequence charSequence) {
        return new HttpRequest(charSequence, "POST");
    }

    /* renamed from: b */
    public static HttpRequest m304b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String a = m301a(charSequence, map);
        if (z) {
            a = m300a((CharSequence) a);
        }
        return m306c((CharSequence) a);
    }

    /* renamed from: d */
    public static HttpRequest m307d(CharSequence charSequence) {
        return new HttpRequest(charSequence, "PUT");
    }

    /* renamed from: e */
    public static HttpRequest m308e(CharSequence charSequence) {
        return new HttpRequest(charSequence, "DELETE");
    }

    public HttpRequest(CharSequence charSequence, String str) {
        try {
            this.f237a = new URL(charSequence.toString());
            this.f239e = str;
        } catch (MalformedURLException e) {
            throw new C0457c(e);
        }
    }

    /* renamed from: q */
    private Proxy m311q() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f245k, this.f246l));
    }

    /* renamed from: r */
    private HttpURLConnection m312r() {
        HttpURLConnection a;
        try {
            if (this.f245k != null) {
                a = f236c.mo8466a(this.f237a, m311q());
            } else {
                a = f236c.mo8465a(this.f237a);
            }
            a.setRequestMethod(this.f239e);
            return a;
        } catch (IOException e) {
            throw new C0457c(e);
        }
    }

    public String toString() {
        return mo8460p() + ' ' + mo8459o();
    }

    /* renamed from: a */
    public HttpURLConnection mo8435a() {
        if (this.f238d == null) {
            this.f238d = m312r();
        }
        return this.f238d;
    }

    /* renamed from: b */
    public int mo8436b() {
        try {
            mo8455k();
            return mo8435a().getResponseCode();
        } catch (IOException e) {
            throw new C0457c(e);
        }
    }

    /* renamed from: c */
    public boolean mo8442c() {
        return 200 == mo8436b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public ByteArrayOutputStream mo8445d() {
        int j = mo8454j();
        if (j > 0) {
            return new ByteArrayOutputStream(j);
        }
        return new ByteArrayOutputStream();
    }

    /* renamed from: a */
    public String mo8434a(String str) {
        ByteArrayOutputStream d = mo8445d();
        try {
            mo8424a((InputStream) mo8450f(), (OutputStream) d);
            return d.toString(m310f(str));
        } catch (IOException e) {
            throw new C0457c(e);
        }
    }

    /* renamed from: e */
    public String mo8447e() {
        return mo8434a(mo8452h());
    }

    /* renamed from: f */
    public BufferedInputStream mo8450f() {
        return new BufferedInputStream(mo8451g(), this.f244j);
    }

    /* renamed from: g */
    public InputStream mo8451g() {
        InputStream errorStream;
        if (mo8436b() < 400) {
            try {
                errorStream = mo8435a().getInputStream();
            } catch (IOException e) {
                throw new C0457c(e);
            }
        } else {
            errorStream = mo8435a().getErrorStream();
            if (errorStream == null) {
                try {
                    errorStream = mo8435a().getInputStream();
                } catch (IOException e2) {
                    throw new C0457c(e2);
                }
            }
        }
        if (!this.f243i || !"gzip".equals(mo8453i())) {
            return errorStream;
        }
        try {
            return new GZIPInputStream(errorStream);
        } catch (IOException e3) {
            throw new C0457c(e3);
        }
    }

    /* renamed from: a */
    public HttpRequest mo8423a(int i) {
        mo8435a().setConnectTimeout(i);
        return this;
    }

    /* renamed from: a */
    public HttpRequest mo8426a(String str, String str2) {
        mo8435a().setRequestProperty(str, str2);
        return this;
    }

    /* renamed from: a */
    public HttpRequest mo8432a(Map.Entry<String, String> entry) {
        return mo8426a(entry.getKey(), entry.getValue());
    }

    /* renamed from: b */
    public String mo8438b(String str) {
        mo8456l();
        return mo8435a().getHeaderField(str);
    }

    /* renamed from: c */
    public int mo8440c(String str) {
        return mo8422a(str, -1);
    }

    /* renamed from: a */
    public int mo8422a(String str, int i) {
        mo8456l();
        return mo8435a().getHeaderFieldInt(str, i);
    }

    /* renamed from: b */
    public String mo8439b(String str, String str2) {
        return mo8441c(mo8438b(str), str2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public String mo8441c(String str, String str2) {
        int i;
        int i2;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            i2 = indexOf;
            i = length;
        } else {
            int i3 = indexOf2;
            i2 = indexOf;
            i = i3;
        }
        while (i2 < i) {
            int indexOf3 = str.indexOf(61, i2);
            if (indexOf3 != -1 && indexOf3 < i && str2.equals(str.substring(i2, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, i).trim();
                int length2 = trim.length();
                if (length2 != 0) {
                    if (length2 > 2 && '\"' == trim.charAt(0) && '\"' == trim.charAt(length2 - 1)) {
                        return trim.substring(1, length2 - 1);
                    }
                    return trim;
                }
            }
            int i4 = i + 1;
            int indexOf4 = str.indexOf(59, i4);
            if (indexOf4 == -1) {
                indexOf4 = length;
            }
            int i5 = indexOf4;
            i2 = i4;
            i = i5;
        }
        return null;
    }

    /* renamed from: h */
    public String mo8452h() {
        return mo8439b("Content-Type", "charset");
    }

    /* renamed from: a */
    public HttpRequest mo8433a(boolean z) {
        mo8435a().setUseCaches(z);
        return this;
    }

    /* renamed from: i */
    public String mo8453i() {
        return mo8438b("Content-Encoding");
    }

    /* renamed from: d */
    public HttpRequest mo8443d(String str) {
        return mo8444d(str, null);
    }

    /* renamed from: d */
    public HttpRequest mo8444d(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return mo8426a("Content-Type", str);
        }
        return mo8426a("Content-Type", str + "; charset=" + str2);
    }

    /* renamed from: j */
    public int mo8454j() {
        return mo8440c("Content-Length");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public HttpRequest mo8424a(InputStream inputStream, OutputStream outputStream) {
        final InputStream inputStream2 = inputStream;
        final OutputStream outputStream2 = outputStream;
        return (HttpRequest) new C0454a<HttpRequest>(inputStream, this.f242h) {
            /* renamed from: a */
            public HttpRequest mo8463b() {
                byte[] bArr = new byte[HttpRequest.this.f244j];
                while (true) {
                    int read = inputStream2.read(bArr);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    outputStream2.write(bArr, 0, read);
                }
            }
        }.call();
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public HttpRequest mo8455k() {
        if (this.f240f != null) {
            if (this.f241g) {
                this.f240f.mo8469a("\r\n--00content0boundary00--\r\n");
            }
            if (this.f242h) {
                try {
                    this.f240f.close();
                } catch (IOException e) {
                }
            } else {
                this.f240f.close();
            }
            this.f240f = null;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public HttpRequest mo8456l() {
        try {
            return mo8455k();
        } catch (IOException e) {
            throw new C0457c(e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public HttpRequest mo8457m() {
        if (this.f240f == null) {
            mo8435a().setDoOutput(true);
            this.f240f = new C0459e(mo8435a().getOutputStream(), mo8441c(mo8435a().getRequestProperty("Content-Type"), "charset"), this.f244j);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public HttpRequest mo8458n() {
        if (!this.f241g) {
            this.f241g = true;
            mo8443d("multipart/form-data; boundary=00content0boundary00").mo8457m();
            this.f240f.mo8469a("--00content0boundary00\r\n");
        } else {
            this.f240f.mo8469a("\r\n--00content0boundary00\r\n");
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public HttpRequest mo8428a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"").append(str);
        if (str2 != null) {
            sb.append("\"; filename=\"").append(str2);
        }
        sb.append('\"');
        mo8449f("Content-Disposition", sb.toString());
        if (str3 != null) {
            mo8449f("Content-Type", str3);
        }
        return mo8448f((CharSequence) "\r\n");
    }

    /* renamed from: e */
    public HttpRequest mo8446e(String str, String str2) {
        return mo8437b(str, (String) null, str2);
    }

    /* renamed from: b */
    public HttpRequest mo8437b(String str, String str2, String str3) {
        return mo8431a(str, str2, (String) null, str3);
    }

    /* renamed from: a */
    public HttpRequest mo8431a(String str, String str2, String str3, String str4) {
        try {
            mo8458n();
            mo8428a(str, str2, str3);
            this.f240f.mo8469a(str4);
            return this;
        } catch (IOException e) {
            throw new C0457c(e);
        }
    }

    /* renamed from: a */
    public HttpRequest mo8425a(String str, Number number) {
        return mo8427a(str, (String) null, number);
    }

    /* renamed from: a */
    public HttpRequest mo8427a(String str, String str2, Number number) {
        return mo8437b(str, str2, number != null ? number.toString() : null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0020 A[SYNTHETIC, Splitter:B:16:0x0020] */
    /* renamed from: a */
    public HttpRequest mo8429a(String str, String str2, String str3, File file) {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                HttpRequest a = mo8430a(str, str2, str3, (InputStream) bufferedInputStream);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {
                    }
                }
                return a;
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            bufferedInputStream = null;
            try {
                throw new C0457c(e);
            } catch (Throwable th) {
                th = th;
                if (bufferedInputStream != null) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public HttpRequest mo8430a(String str, String str2, String str3, InputStream inputStream) {
        try {
            mo8458n();
            mo8428a(str, str2, str3);
            mo8424a(inputStream, (OutputStream) this.f240f);
            return this;
        } catch (IOException e) {
            throw new C0457c(e);
        }
    }

    /* renamed from: f */
    public HttpRequest mo8449f(String str, String str2) {
        return mo8448f((CharSequence) str).mo8448f((CharSequence) ": ").mo8448f((CharSequence) str2).mo8448f((CharSequence) "\r\n");
    }

    /* renamed from: f */
    public HttpRequest mo8448f(CharSequence charSequence) {
        try {
            mo8457m();
            this.f240f.mo8469a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new C0457c(e);
        }
    }

    /* renamed from: o */
    public URL mo8459o() {
        return mo8435a().getURL();
    }

    /* renamed from: p */
    public String mo8460p() {
        return mo8435a().getRequestMethod();
    }
}
