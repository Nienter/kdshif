package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.p028a.p029a.AnalyticsConfig;
import com.p028a.p029a.AnalyticsConstants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

/* renamed from: com.a.a.a.w */
/* compiled from: NetworkHelper */
public class C0648w {

    /* renamed from: a */
    private String f1675a;

    /* renamed from: b */
    private String f1676b = "10.0.0.172";

    /* renamed from: c */
    private int f1677c = 80;

    /* renamed from: d */
    private Context f1678d;

    /* renamed from: e */
    private IRequestStat f1679e;

    public C0648w(Context context) {
        this.f1678d = context;
        this.f1675a = m2396a(context);
    }

    /* renamed from: a */
    public void mo9632a(IRequestStat vVar) {
        this.f1679e = vVar;
    }

    /* renamed from: b */
    private void m2398b() {
        String c = ImprintHandler.m2284a(this.f1678d).mo9583b().mo9597c("");
        String b = ImprintHandler.m2284a(this.f1678d).mo9583b().mo9594b("");
        if (!TextUtils.isEmpty(c)) {
            AnalyticsConstants.f1690c = DataHelper.m1777b(c);
        }
        if (!TextUtils.isEmpty(b)) {
            AnalyticsConstants.f1691d = DataHelper.m1777b(b);
        }
        AnalyticsConstants.f1692e = new String[]{AnalyticsConstants.f1690c, AnalyticsConstants.f1691d};
        int b2 = ABTest.m1377a(this.f1678d).mo9107b();
        if (b2 == -1) {
            return;
        }
        if (b2 == 0) {
            AnalyticsConstants.f1692e = new String[]{AnalyticsConstants.f1690c, AnalyticsConstants.f1691d};
        } else if (b2 == 1) {
            AnalyticsConstants.f1692e = new String[]{AnalyticsConstants.f1691d, AnalyticsConstants.f1690c};
        }
    }

    /* renamed from: a */
    public byte[] mo9633a(byte[] bArr) {
        byte[] bArr2 = null;
        m2398b();
        int i = 0;
        while (true) {
            if (i >= AnalyticsConstants.f1692e.length) {
                break;
            }
            bArr2 = m2397a(bArr, AnalyticsConstants.f1692e[i]);
            if (bArr2 == null) {
                if (this.f1679e != null) {
                    this.f1679e.mo9092d();
                }
                i++;
            } else if (this.f1679e != null) {
                this.f1679e.mo9091c();
            }
        }
        return bArr2;
    }

    /* renamed from: c */
    private boolean m2399c() {
        if (this.f1678d.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f1678d.getPackageName()) != 0) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f1678d.getSystemService("connectivity");
            if (!DeviceConfig.m1791a(this.f1678d, "android.permission.ACCESS_NETWORK_STATE")) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ff  */
    /* renamed from: a */
    private byte[] m2397a(byte[] bArr, String str) {
        HttpURLConnection httpURLConnection;
        boolean z;
        InputStream inputStream;
        try {
            if (this.f1679e != null) {
                this.f1679e.mo9089a();
            }
            if (m2399c()) {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f1676b, this.f1677c)));
            } else {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            }
            try {
                httpURLConnection.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                httpURLConnection.setRequestProperty("X-Umeng-Sdk", this.f1675a);
                httpURLConnection.setRequestProperty("Msg-Type", "envelope/json");
                httpURLConnection.setRequestProperty("Content-Type", "envelope/json");
                httpURLConnection.setConnectTimeout(AbstractSpiCall.DEFAULT_TIMEOUT);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                if (Build.VERSION.SDK_INT < 8) {
                    System.setProperty("http.keepAlive", "false");
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
                outputStream.close();
                if (this.f1679e != null) {
                    this.f1679e.mo9090b();
                }
                int responseCode = httpURLConnection.getResponseCode();
                String headerField = httpURLConnection.getHeaderField("Content-Type");
                if (TextUtils.isEmpty(headerField) || !headerField.equalsIgnoreCase("application/thrift")) {
                    z = false;
                } else {
                    z = true;
                }
                if (responseCode != 200 || !z) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                }
                MLog.m1842b("Send message to " + str);
                inputStream = httpURLConnection.getInputStream();
                byte[] b = HelperUtils.m1831b(inputStream);
                HelperUtils.m1832c(inputStream);
                if (httpURLConnection == null) {
                    return b;
                }
                httpURLConnection.disconnect();
                return b;
            } catch (Throwable th) {
                th = th;
                try {
                    MLog.m1838a("IOException,Failed to send message.", th);
                    if (httpURLConnection != null) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
            }
            throw th;
        }
    }

    /* renamed from: a */
    private String m2396a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append("/");
        stringBuffer.append("6.1.2");
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(DeviceConfig.m1815s(context));
            stringBuffer2.append("/");
            stringBuffer2.append(DeviceConfig.m1793b(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append("/");
            stringBuffer2.append(Build.VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(HelperUtils.m1825a(AnalyticsConfig.m1311a(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0090, code lost:
        if (r0 != null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d5, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d6, code lost:
        r9 = r1;
        r1 = r0;
        r0 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00db A[SYNTHETIC, Splitter:B:30:0x00db] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x008f A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:1:0x0001] */
    /* renamed from: a */
    public void mo9631a() {
        InputStream content;
        InputStream inputStream = null;
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            NetworkHelper abVar = new NetworkHelper(instance);
            abVar.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            HttpGet httpGet = new HttpGet("https://uop.umeng.com");
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "ISO-8859-1");
            HttpProtocolParams.setUseExpectContinue(basicHttpParams, true);
            ConnManagerParams.setTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, AbstractSpiCall.DEFAULT_TIMEOUT);
            HttpConnectionParams.setSoTimeout(basicHttpParams, AbstractSpiCall.DEFAULT_TIMEOUT);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", abVar, 443));
            content = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams).execute(httpGet).getEntity().getContent();
            if (content != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = content.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.close();
                String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                if (!TextUtils.isEmpty(str) && str.length() > 0 && str.length() < 50) {
                    SharedPreferences a = PreferenceWrapper.m1333a(this.f1678d);
                    if (a != null) {
                        a.edit().putString("uopdta", str).commit();
                    }
                }
            }
            if (content != null) {
                try {
                    content.close();
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
