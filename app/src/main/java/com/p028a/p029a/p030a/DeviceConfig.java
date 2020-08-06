package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.p028a.p029a.AnalyticsConfig;
import com.p028a.p029a.AnalyticsConstants;
import com.p028a.p029a.MobclickAgent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

/* renamed from: com.a.a.a.at */
public class DeviceConfig {

    /* renamed from: a */
    protected static final String f1374a = DeviceConfig.class.getName();

    /* renamed from: a */
    public static String m1787a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m1793b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    /* renamed from: a */
    public static boolean m1791a(Context context, String str) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } catch (Throwable th) {
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: b */
    private static String m1792b() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (!"wlan0".equals(nextElement.getName())) {
                    if ("eth0".equals(nextElement.getName())) {
                    }
                }
                byte[] hardwareAddress = nextElement.getHardwareAddress();
                if (hardwareAddress == null || hardwareAddress.length == 0) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                for (byte valueOf : hardwareAddress) {
                    sb.append(String.format("%02X:", new Object[]{Byte.valueOf(valueOf)}));
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString().toLowerCase(Locale.getDefault());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* renamed from: c */
    private static String m1795c() {
        int i = 0;
        try {
            String[] strArr = {"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
            while (true) {
                int i2 = i;
                if (i2 >= strArr.length) {
                    break;
                }
                try {
                    String a = m1788a(strArr[i2]);
                    if (a != null) {
                        return a;
                    }
                    i = i2 + 1;
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    private static String m1788a(String str) {
        BufferedReader bufferedReader;
        String str2 = null;
        try {
            FileReader fileReader = new FileReader(str);
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(fileReader, 1024);
                    try {
                        str2 = bufferedReader2.readLine();
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable th) {
                            }
                        }
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable th2) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = bufferedReader2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedReader = str2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (bufferedReader != 0) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
        }
        return str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001d  */
    /* renamed from: a */
    public static String m1786a() {
        String str;
        FileNotFoundException fileNotFoundException;
        String str2 = null;
        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str2 = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (Throwable th) {
                    try {
                        MLog.m1845c(f1374a, "Could not read from file /proc/cpuinfo", th);
                    } catch (FileNotFoundException e) {
                        FileNotFoundException fileNotFoundException2 = e;
                        str = str2;
                        fileNotFoundException = fileNotFoundException2;
                    }
                }
            }
        } catch (FileNotFoundException e2) {
            FileNotFoundException fileNotFoundException3 = e2;
            str = str2;
            fileNotFoundException = fileNotFoundException3;
            MLog.m1845c(f1374a, "Could not open file /proc/cpuinfo", fileNotFoundException);
            str2 = str;
            if (str2 == null) {
            }
        }
        if (str2 == null) {
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        return "";
    }

    /* renamed from: c */
    public static String m1796c(Context context) {
        if (MobclickAgent.C0649a.E_UM_ANALYTICS_OEM.toValue() == AnalyticsConfig.m1315d(context) || MobclickAgent.C0649a.E_UM_GAME_OEM.toValue() == AnalyticsConfig.m1315d(context)) {
            return m1783A(context);
        }
        return m1822z(context);
    }

    /* renamed from: d */
    public static String m1798d(Context context) {
        return HelperUtils.m1830b(m1796c(context));
    }

    /* renamed from: e */
    public static String m1799e(Context context) {
        if (m1801f(context) == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().mcc;
        int i2 = context.getResources().getConfiguration().mnc;
        if (i == 0) {
            return null;
        }
        String valueOf = String.valueOf(i2);
        if (i2 < 10) {
            valueOf = String.format("%02d", new Object[]{Integer.valueOf(i2)});
        }
        return new StringBuffer().append(String.valueOf(i)).append(valueOf).toString();
    }

    /* renamed from: f */
    public static String m1801f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        if (m1791a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    /* renamed from: g */
    public static String m1803g(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            if (m1791a(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Throwable th) {
        }
        return "";
    }

    /* renamed from: h */
    public static String[] m1804h(Context context) {
        String[] strArr = {"", ""};
        try {
            if (!m1791a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                strArr[0] = "";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                strArr[0] = "";
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = "Wi-Fi";
            return strArr;
        } catch (Throwable th) {
        }
    }

    /* renamed from: i */
    public static boolean m1805i(Context context) {
        return "Wi-Fi".equals(m1804h(context)[0]);
    }

    /* renamed from: j */
    public static boolean m1806j(Context context) {
        try {
            if (m1791a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        return activeNetworkInfo.isConnectedOrConnecting();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    /* renamed from: k */
    public static int m1807k(Context context) {
        try {
            Calendar instance = Calendar.getInstance(m1820x(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Throwable th) {
            MLog.m1843b(f1374a, "error in getTimeZone", th);
        }
        return 8;
    }

    /* renamed from: l */
    public static String[] m1808l(Context context) {
        String[] strArr = new String[2];
        try {
            Locale x = m1820x(context);
            if (x != null) {
                strArr[0] = x.getCountry();
                strArr[1] = x.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
        } catch (Throwable th) {
            MLog.m1845c(f1374a, "error in getLocaleInfo", th);
        }
        return strArr;
    }

    /* renamed from: x */
    private static Locale m1820x(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Throwable th) {
            MLog.m1839a(f1374a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    /* renamed from: m */
    public static String m1809m(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                MLog.m1839a(f1374a, "getAppkey failed. the applicationinfo is null!");
            }
        } catch (Throwable th) {
            MLog.m1845c(f1374a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", th);
        }
        return null;
    }

    /* renamed from: n */
    public static String m1810n(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return m1821y(context);
        }
        if (Build.VERSION.SDK_INT == 23) {
            String b = m1792b();
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            if (AnalyticsConstants.f1688a) {
                return m1795c();
            }
            return m1821y(context);
        }
        String b2 = m1792b();
        if (TextUtils.isEmpty(b2)) {
            return m1821y(context);
        }
        return b2;
    }

    /* renamed from: y */
    private static String m1821y(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (m1791a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            return "";
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: o */
    public static int[] m1811o(Context context) {
        int i;
        int i2;
        int i3;
        int i4;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                i2 = m1785a((Object) displayMetrics, "noncompatWidthPixels");
                i = m1785a((Object) displayMetrics, "noncompatHeightPixels");
            } else {
                i = -1;
                i2 = -1;
            }
            if (i2 == -1 || i == -1) {
                i3 = displayMetrics.widthPixels;
                i4 = displayMetrics.heightPixels;
            } else {
                i3 = i2;
                i4 = i;
            }
            int[] iArr = new int[2];
            if (i3 > i4) {
                iArr[0] = i4;
                iArr[1] = i3;
                return iArr;
            }
            iArr[0] = i3;
            iArr[1] = i4;
            return iArr;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    private static int m1785a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Throwable th) {
            return -1;
        }
    }

    /* renamed from: p */
    public static String m1812p(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2 != null) {
                        return obj2;
                    }
                }
            }
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        } catch (Throwable th) {
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    /* renamed from: q */
    public static String m1813q(Context context) {
        return context.getPackageName();
    }

    /* renamed from: r */
    public static String m1814r(Context context) {
        try {
            return m1790a(MessageDigest.getInstance("MD5").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(m1813q(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m1790a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = AppEventsConstants.EVENT_PARAM_VALUE_NO + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase(Locale.getDefault()));
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    /* renamed from: s */
    public static String m1815s(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    /* renamed from: t */
    public static String m1816t(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable th) {
            return z;
        }
    }

    /* renamed from: z */
    private static String m1822z(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            String B = m1784B(context);
            if (!TextUtils.isEmpty(B)) {
                return B;
            }
            String y = m1821y(context);
            if (!TextUtils.isEmpty(y)) {
                return y;
            }
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(string)) {
                return m1797d();
            }
            return string;
        } else if (Build.VERSION.SDK_INT == 23) {
            String B2 = m1784B(context);
            if (!TextUtils.isEmpty(B2)) {
                return B2;
            }
            String b = m1792b();
            if (TextUtils.isEmpty(b)) {
                if (AnalyticsConstants.f1688a) {
                    b = m1795c();
                } else {
                    b = m1821y(context);
                }
            }
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(string2)) {
                return m1797d();
            }
            return string2;
        } else {
            String B3 = m1784B(context);
            if (!TextUtils.isEmpty(B3)) {
                return B3;
            }
            String d = m1797d();
            if (!TextUtils.isEmpty(d)) {
                return d;
            }
            String string3 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string3)) {
                return string3;
            }
            String b2 = m1792b();
            if (TextUtils.isEmpty(b2)) {
                return m1821y(context);
            }
            return b2;
        }
    }

    /* renamed from: A */
    private static String m1783A(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String y = m1821y(context);
            if (!TextUtils.isEmpty(y)) {
                return y;
            }
            String d = m1797d();
            if (TextUtils.isEmpty(d)) {
                return m1784B(context);
            }
            return d;
        } else if (Build.VERSION.SDK_INT == 23) {
            String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string2)) {
                return string2;
            }
            String b = m1792b();
            if (TextUtils.isEmpty(b)) {
                if (AnalyticsConstants.f1688a) {
                    b = m1795c();
                } else {
                    b = m1821y(context);
                }
            }
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            String d2 = m1797d();
            if (TextUtils.isEmpty(d2)) {
                return m1784B(context);
            }
            return d2;
        } else {
            String string3 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string3)) {
                return string3;
            }
            String d3 = m1797d();
            if (!TextUtils.isEmpty(d3)) {
                return d3;
            }
            String B = m1784B(context);
            if (!TextUtils.isEmpty(B)) {
                return B;
            }
            String b2 = m1792b();
            if (TextUtils.isEmpty(b2)) {
                return m1821y(context);
            }
            return b2;
        }
    }

    /* renamed from: B */
    private static String m1784B(Context context) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        if (telephonyManager == null) {
            return "";
        }
        try {
            if (m1791a(context, "android.permission.READ_PHONE_STATE")) {
                str = telephonyManager.getDeviceId();
            } else {
                str = "";
            }
            return str;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: d */
    private static String m1797d() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return "";
    }

    /* renamed from: u */
    public static String m1817u(Context context) {
        Properties e = m1800e();
        try {
            String property = e.getProperty("ro.miui.ui.version.name");
            if (!TextUtils.isEmpty(property)) {
                return "MIUI";
            }
            if (m1802f()) {
                return "Flyme";
            }
            if (!TextUtils.isEmpty(m1789a(e))) {
                return "YunOS";
            }
            return property;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: v */
    public static String m1818v(Context context) {
        Properties e = m1800e();
        try {
            String property = e.getProperty("ro.miui.ui.version.name");
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            if (m1802f()) {
                try {
                    return m1794b(e);
                } catch (Throwable th) {
                    return property;
                }
            } else {
                try {
                    return m1789a(e);
                } catch (Throwable th2) {
                    return property;
                }
            }
        } catch (Throwable th3) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m1789a(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (!TextUtils.isEmpty(property)) {
            return property;
        }
        return null;
    }

    /* renamed from: b */
    private static String m1794b(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0023 A[SYNTHETIC, Splitter:B:12:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002c A[SYNTHETIC, Splitter:B:17:0x002c] */
    /* renamed from: e */
    private static Properties m1800e() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            try {
                properties.load(fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileInputStream2 = fileInputStream;
                th = th3;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable th4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            if (fileInputStream2 != null) {
            }
            throw th;
        }
        return properties;
    }

    /* renamed from: f */
    private static boolean m1802f() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: w */
    public static String m1819w(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            if (telephonyManager == null || !m1791a(context, "android.permission.READ_PHONE_STATE")) {
                str = null;
            } else {
                str = telephonyManager.getDeviceId();
            }
            try {
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
                String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (!TextUtils.isEmpty(string) || Build.VERSION.SDK_INT < 9) {
                    return string;
                }
                return Build.SERIAL;
            } catch (Throwable th) {
                return str;
            }
        } catch (Throwable th2) {
            return null;
        }
    }
}
