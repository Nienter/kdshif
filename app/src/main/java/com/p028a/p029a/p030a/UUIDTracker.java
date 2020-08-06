package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;

/* renamed from: com.a.a.a.n */
public class UUIDTracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1623a = null;

    /* renamed from: b */
    private String f1624b = null;

    /* renamed from: c */
    private String f1625c = null;

    public UUIDTracker(Context context) {
        super("uuid");
        this.f1623a = context;
        this.f1624b = null;
        this.f1625c = null;
    }

    /* renamed from: a */
    public String mo9458a() {
        try {
            if (!TextUtils.isEmpty(m2328a("ro.yunos.version", "")) && this.f1623a != null) {
                SharedPreferences a = PreferenceWrapper.m1333a(this.f1623a);
                if (a != null) {
                    String string = a.getString("yosuid", "");
                    if (!TextUtils.isEmpty(string)) {
                        return string;
                    }
                    this.f1625c = m2329b("23346339");
                    if (!(TextUtils.isEmpty(this.f1625c) || this.f1623a == null || a == null)) {
                        SharedPreferences.Editor edit = a.edit();
                        if (edit != null) {
                            edit.putString("yosuid", this.f1625c).commit();
                        }
                    }
                    return this.f1625c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v3, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0194, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0195, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0162, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0163, code lost:
        r4 = null;
        r6 = r1;
        r1 = r0;
        r0 = r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00d9 A[SYNTHETIC, Splitter:B:31:0x00d9] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00de A[SYNTHETIC, Splitter:B:34:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e3 A[SYNTHETIC, Splitter:B:37:0x00e3] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0111 A[SYNTHETIC, Splitter:B:57:0x0111] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0116 A[SYNTHETIC, Splitter:B:60:0x0116] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011b A[SYNTHETIC, Splitter:B:63:0x011b] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0139 A[SYNTHETIC, Splitter:B:76:0x0139] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x013e A[SYNTHETIC, Splitter:B:79:0x013e] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0143 A[SYNTHETIC, Splitter:B:82:0x0143] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0162 A[ExcHandler: all (r1v28 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:13:0x00a7] */
    /* renamed from: b */
    private String m2329b(String str) {
        DataOutputStream dataOutputStream;
        InputStream inputStream;
        HttpsURLConnection httpsURLConnection;
        HttpsURLConnection httpsURLConnection2;
        InputStream inputStream2;
        DataOutputStream dataOutputStream2;
        String str2;
        BufferedReader bufferedReader;
        ? r3 = 0;
        this.f1625c = m2328a("ro.yunos.openuuid", "");
        if (!TextUtils.isEmpty(this.f1625c)) {
            return this.f1625c;
        }
        this.f1624b = m2328a("ro.aliyun.clouduuid", "");
        if (TextUtils.isEmpty(this.f1624b)) {
            this.f1624b = m2328a("ro.sys.aliyun.clouduuid", "");
        }
        if (!TextUtils.isEmpty(this.f1624b)) {
            try {
                HttpsURLConnection httpsURLConnection3 = (HttpsURLConnection) new URL("https://cmnsguider.yunos.com:443/genDeviceToken").openConnection();
                try {
                    httpsURLConnection3.setConnectTimeout(30000);
                    httpsURLConnection3.setReadTimeout(30000);
                    httpsURLConnection3.setRequestMethod("POST");
                    httpsURLConnection3.setDoInput(true);
                    httpsURLConnection3.setDoOutput(true);
                    httpsURLConnection3.setUseCaches(false);
                    httpsURLConnection3.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpsURLConnection3.setHostnameVerifier(new HostnameVerifier() {
                        public boolean verify(String str, SSLSession sSLSession) {
                            return new BrowserCompatHostnameVerifier().verify("cmnsguider.yunos.com", sSLSession);
                        }
                    });
                    str2 = "appKey=" + URLEncoder.encode("23338940", "UTF-8") + "&uuid=" + URLEncoder.encode("FC1FE84794417B1BEF276234F6FB4E63", "UTF-8");
                    dataOutputStream = new DataOutputStream(httpsURLConnection3.getOutputStream());
                } catch (Exception e) {
                    inputStream2 = null;
                    httpsURLConnection2 = httpsURLConnection3;
                    e = e;
                    dataOutputStream2 = null;
                    try {
                        e.printStackTrace();
                        if (dataOutputStream2 != null) {
                        }
                        if (r3 != 0) {
                        }
                        if (inputStream2 != null) {
                        }
                        if (httpsURLConnection2 != null) {
                        }
                        return this.f1625c;
                    } catch (Throwable th) {
                        th = th;
                        dataOutputStream = dataOutputStream2;
                        httpsURLConnection = httpsURLConnection2;
                        inputStream = inputStream2;
                        r3 = r3;
                        if (dataOutputStream != null) {
                        }
                        if (r3 != 0) {
                        }
                        if (inputStream != null) {
                        }
                        if (httpsURLConnection != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    dataOutputStream = null;
                    inputStream = null;
                    Throwable th3 = th2;
                    httpsURLConnection = httpsURLConnection3;
                    th = th3;
                    if (dataOutputStream != null) {
                    }
                    if (r3 != 0) {
                    }
                    if (inputStream != null) {
                    }
                    if (httpsURLConnection != null) {
                    }
                    throw th;
                }
                try {
                    dataOutputStream.writeBytes(str2);
                    dataOutputStream.flush();
                    if (httpsURLConnection3.getResponseCode() == 200) {
                        inputStream = httpsURLConnection3.getInputStream();
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        } catch (Exception e2) {
                            e = e2;
                            bufferedReader = null;
                            r3 = inputStream;
                            try {
                                e.printStackTrace();
                                r3 = r3;
                                if (dataOutputStream != null) {
                                }
                                if (bufferedReader != null) {
                                }
                                if (r3 != 0) {
                                }
                                if (httpsURLConnection3 != null) {
                                }
                            } catch (Exception e3) {
                                httpsURLConnection2 = httpsURLConnection3;
                                e = e3;
                                dataOutputStream2 = dataOutputStream;
                                InputStream inputStream3 = r3;
                                r3 = bufferedReader;
                                inputStream2 = inputStream3;
                                e.printStackTrace();
                                if (dataOutputStream2 != null) {
                                    try {
                                        dataOutputStream2.close();
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (r3 != 0) {
                                    try {
                                        r3.close();
                                    } catch (Exception e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (Exception e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                if (httpsURLConnection2 != null) {
                                    httpsURLConnection2.disconnect();
                                }
                                return this.f1625c;
                            } catch (Throwable th4) {
                                inputStream = r3;
                                r3 = bufferedReader;
                                Throwable th5 = th4;
                                httpsURLConnection = httpsURLConnection3;
                                th = th5;
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                    } catch (Exception e7) {
                                        e7.printStackTrace();
                                    }
                                }
                                if (r3 != 0) {
                                    try {
                                        r3.close();
                                    } catch (Exception e8) {
                                        e8.printStackTrace();
                                    }
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e9) {
                                        e9.printStackTrace();
                                    }
                                }
                                if (httpsURLConnection != null) {
                                    httpsURLConnection.disconnect();
                                }
                                throw th;
                            }
                            return this.f1625c;
                        } catch (Throwable th6) {
                            Throwable th7 = th6;
                            httpsURLConnection = httpsURLConnection3;
                            th = th7;
                            if (dataOutputStream != null) {
                            }
                            if (r3 != 0) {
                            }
                            if (inputStream != null) {
                            }
                            if (httpsURLConnection != null) {
                            }
                            throw th;
                        }
                        try {
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine);
                            }
                            if (stringBuffer != null) {
                                this.f1625c = stringBuffer.toString();
                            }
                            r3 = inputStream;
                        } catch (Exception e10) {
                            e = e10;
                            r3 = inputStream;
                            e.printStackTrace();
                            r3 = r3;
                            if (dataOutputStream != null) {
                            }
                            if (bufferedReader != null) {
                            }
                            if (r3 != 0) {
                            }
                            if (httpsURLConnection3 != null) {
                            }
                            return this.f1625c;
                        } catch (Throwable th8) {
                            r3 = bufferedReader;
                            Throwable th9 = th8;
                            httpsURLConnection = httpsURLConnection3;
                            th = th9;
                            if (dataOutputStream != null) {
                            }
                            if (r3 != 0) {
                            }
                            if (inputStream != null) {
                            }
                            if (httpsURLConnection != null) {
                            }
                            throw th;
                        }
                    } else {
                        bufferedReader = null;
                    }
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception e11) {
                            e11.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e12) {
                            e12.printStackTrace();
                        }
                    }
                    if (r3 != 0) {
                        try {
                            r3.close();
                        } catch (Exception e13) {
                            e13.printStackTrace();
                        }
                    }
                    if (httpsURLConnection3 != null) {
                        httpsURLConnection3.disconnect();
                    }
                } catch (Exception e14) {
                    inputStream2 = null;
                    httpsURLConnection2 = httpsURLConnection3;
                    e = e14;
                    dataOutputStream2 = dataOutputStream;
                    e.printStackTrace();
                    if (dataOutputStream2 != null) {
                    }
                    if (r3 != 0) {
                    }
                    if (inputStream2 != null) {
                    }
                    if (httpsURLConnection2 != null) {
                    }
                    return this.f1625c;
                } catch (Throwable th10) {
                }
            } catch (Exception e15) {
                e = e15;
                dataOutputStream2 = null;
                inputStream2 = null;
                httpsURLConnection2 = null;
                e.printStackTrace();
                if (dataOutputStream2 != null) {
                }
                if (r3 != 0) {
                }
                if (inputStream2 != null) {
                }
                if (httpsURLConnection2 != null) {
                }
                return this.f1625c;
            } catch (Throwable th11) {
                th = th11;
                dataOutputStream = null;
                inputStream = null;
                httpsURLConnection = null;
                if (dataOutputStream != null) {
                }
                if (r3 != 0) {
                }
                if (inputStream != null) {
                }
                if (httpsURLConnection != null) {
                }
                throw th;
            }
        }
        return this.f1625c;
    }

    /* renamed from: a */
    public static String m2328a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }
}
