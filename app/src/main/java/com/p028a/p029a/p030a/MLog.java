package com.p028a.p029a.p030a;

import android.text.TextUtils;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;

/* renamed from: com.a.a.a.aw */
public class MLog {

    /* renamed from: a */
    public static boolean f1376a = false;

    /* renamed from: b */
    private static String f1377b = "MobclickAgent";

    /* renamed from: c */
    private static int f1378c = 2000;

    /* renamed from: a */
    public static void m1839a(String str, Object... objArr) {
        String str2 = "";
        try {
            if (str.contains("%")) {
                m1845c(f1377b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = objArr[0];
            }
            m1845c(str, str2, null);
        } catch (Throwable th) {
            m1840a(th);
        }
    }

    /* renamed from: a */
    public static void m1840a(Throwable th) {
        m1845c(f1377b, null, th);
    }

    /* renamed from: a */
    public static void m1838a(String str, Throwable th) {
        m1845c(f1377b, str, th);
    }

    /* renamed from: a */
    public static void m1836a(String str) {
        m1837a(f1377b, str, null);
    }

    /* renamed from: b */
    public static void m1842b(String str) {
        m1843b(f1377b, str, null);
    }

    /* renamed from: c */
    public static void m1844c(String str) {
        m1845c(f1377b, str, null);
    }

    /* renamed from: a */
    public static void m1837a(String str, String str2, Throwable th) {
        if (f1376a) {
            m1835a(2, str, str2, th);
        }
    }

    /* renamed from: b */
    public static void m1843b(String str, String str2, Throwable th) {
        if (f1376a) {
            m1835a(3, str, str2, th);
        }
    }

    /* renamed from: c */
    public static void m1845c(String str, String str2, Throwable th) {
        if (f1376a) {
            m1835a(5, str, str2, th);
        }
    }

    /* renamed from: a */
    private static void m1835a(int i, String str, String str2, Throwable th) {
        int i2 = 0;
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i3 = f1378c;
            int i4 = 0;
            while (true) {
                if (i2 < 100) {
                    if (length <= i3) {
                        switch (i) {
                            case 1:
                                Log.v(str, str2.substring(i4, length));
                                break;
                            case 2:
                                Log.d(str, str2.substring(i4, length));
                                break;
                            case 3:
                                Log.i(str, str2.substring(i4, length));
                                break;
                            case 4:
                                Log.w(str, str2.substring(i4, length));
                                break;
                            case 5:
                                Log.e(str, str2.substring(i4, length));
                                break;
                        }
                    } else {
                        switch (i) {
                            case 1:
                                Log.v(str, str2.substring(i4, i3));
                                break;
                            case 2:
                                Log.d(str, str2.substring(i4, i3));
                                break;
                            case 3:
                                Log.i(str, str2.substring(i4, i3));
                                break;
                            case 4:
                                Log.w(str, str2.substring(i4, i3));
                                break;
                            case 5:
                                Log.e(str, str2.substring(i4, i3));
                                break;
                        }
                        i2++;
                        i4 = i3;
                        i3 = f1378c + i3;
                    }
                }
            }
        }
        if (th != null) {
            String b = m1841b(th);
            if (!TextUtils.isEmpty(b)) {
                switch (i) {
                    case 1:
                        Log.v(str, b);
                        return;
                    case 2:
                        Log.d(str, b);
                        return;
                    case 3:
                        Log.i(str, b);
                        return;
                    case 4:
                        Log.w(str, b);
                        return;
                    case 5:
                        Log.e(str, b);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029 A[SYNTHETIC, Splitter:B:16:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0037 A[SYNTHETIC, Splitter:B:23:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003c  */
    /* renamed from: b */
    public static String m1841b(Throwable th) {
        StringWriter stringWriter;
        PrintWriter printWriter;
        StringWriter stringWriter2 = null;
        String str = "";
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
                try {
                    th.printStackTrace(printWriter);
                    printWriter.flush();
                    stringWriter.flush();
                    str = stringWriter.toString();
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable th2) {
                        }
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (stringWriter != null) {
                    }
                    if (printWriter != null) {
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                printWriter = null;
                if (stringWriter != null) {
                }
                if (printWriter != null) {
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            printWriter = null;
            stringWriter = null;
            if (stringWriter != null) {
                try {
                    stringWriter.close();
                } catch (Throwable th6) {
                }
            }
            if (printWriter != null) {
                printWriter.close();
            }
            throw th;
        }
        return str;
    }
}
