package p005b.p006a.p007a.p008a.p009a.p011b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import p005b.p006a.p007a.p008a.Fabric;

/* renamed from: b.a.a.a.a.b.i */
public class CommonUtils {

    /* renamed from: a */
    public static final Comparator<File> f119a = new Comparator<File>() {
        /* renamed from: a */
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    };

    /* renamed from: b */
    private static Boolean f120b = null;

    /* renamed from: c */
    private static final char[] f121c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: d */
    private static long f122d = -1;

    /* renamed from: b.a.a.a.a.b.i$a */
    /* compiled from: CommonUtils */
    enum C0424a {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        

        /* renamed from: a */
        private static final Map<String, C0424a> f123a = null;

        static {
            f123a = new HashMap(4);
            f123a.put("armeabi-v7a", ARMV7);
            f123a.put("armeabi", ARMV6);
            f123a.put("arm64-v8a", ARM64);
            f123a.put("x86", X86_32);
        }

        static C0424a getValue() {
            String str = Build.CPU_ABI;
            if (TextUtils.isEmpty(str)) {
                Fabric.m456h().mo8506a("Fabric", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            C0424a aVar = f123a.get(str.toLowerCase(Locale.US));
            if (aVar == null) {
                return UNKNOWN;
            }
            return aVar;
        }
    }

    /* renamed from: a */
    public static SharedPreferences m126a(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    /* renamed from: a */
    public static String m128a(File file, String str) {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = null;
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            str2 = split[1];
                            break;
                        }
                    } catch (Exception e) {
                        e = e;
                        try {
                            Fabric.m456h().mo8516e("Fabric", "Error parsing " + file, e);
                            m140a((Closeable) bufferedReader, "Failed to close system file reader.");
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                            m140a((Closeable) bufferedReader, "Failed to close system file reader.");
                            throw th;
                        }
                    }
                }
                m140a((Closeable) bufferedReader, "Failed to close system file reader.");
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
                Fabric.m456h().mo8516e("Fabric", "Error parsing " + file, e);
                m140a((Closeable) bufferedReader, "Failed to close system file reader.");
                return str2;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                m140a((Closeable) bufferedReader, "Failed to close system file reader.");
                throw th;
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static int m121a() {
        return C0424a.getValue().ordinal();
    }

    /* renamed from: b */
    public static synchronized long m144b() {
        long j;
        synchronized (CommonUtils.class) {
            if (f122d == -1) {
                long j2 = 0;
                String a = m128a(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(a)) {
                    String upperCase = a.toUpperCase(Locale.US);
                    try {
                        if (upperCase.endsWith("KB")) {
                            j2 = m124a(upperCase, "KB", 1024);
                        } else if (upperCase.endsWith("MB")) {
                            j2 = m124a(upperCase, "MB", 1048576);
                        } else if (upperCase.endsWith("GB")) {
                            j2 = m124a(upperCase, "GB", 1073741824);
                        } else {
                            Fabric.m456h().mo8506a("Fabric", "Unexpected meminfo format while computing RAM: " + upperCase);
                        }
                    } catch (NumberFormatException e) {
                        Fabric.m456h().mo8516e("Fabric", "Unexpected meminfo format while computing RAM: " + upperCase, e);
                    }
                }
                f122d = j2;
            }
            j = f122d;
        }
        return j;
    }

    /* renamed from: a */
    static long m124a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    /* renamed from: a */
    public static ActivityManager.RunningAppProcessInfo m125a(String str, Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.processName.equals(str)) {
                    return next;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m129a(InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    /* renamed from: a */
    public static String m131a(String str) {
        return m132a(str, "SHA-1");
    }

    /* renamed from: b */
    public static String m149b(String str) {
        return m132a(str, "SHA-256");
    }

    /* renamed from: b */
    public static String m148b(InputStream inputStream) {
        return m130a(inputStream, "SHA-1");
    }

    /* renamed from: a */
    private static String m132a(String str, String str2) {
        return m134a(str.getBytes(), str2);
    }

    /* renamed from: a */
    private static String m130a(InputStream inputStream, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return m133a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Exception e) {
            Fabric.m456h().mo8516e("Fabric", "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    /* renamed from: a */
    private static String m134a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return m133a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            Fabric.m456h().mo8516e("Fabric", "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    /* renamed from: a */
    public static String m135a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String append : arrayList) {
            sb.append(append);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            return m131a(sb2);
        }
        return null;
    }

    /* renamed from: b */
    public static long m145b(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    /* renamed from: c */
    public static long m150c(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (((long) statFs.getAvailableBlocks()) * blockSize);
    }

    /* renamed from: c */
    public static Float m151c(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        return Float.valueOf(((float) registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
    }

    /* renamed from: d */
    public static boolean m154d(Context context) {
        if (m157f(context)) {
            return false;
        }
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null;
    }

    /* renamed from: a */
    public static void m137a(Context context, String str) {
        if (m156e(context)) {
            Fabric.m456h().mo8506a("Fabric", str);
        }
    }

    /* renamed from: a */
    public static void m138a(Context context, String str, Throwable th) {
        if (m156e(context)) {
            Fabric.m456h().mo8515e("Fabric", str);
        }
    }

    /* renamed from: a */
    public static void m136a(Context context, int i, String str, String str2) {
        if (m156e(context)) {
            Fabric.m456h().mo8504a(i, "Fabric", str2);
        }
    }

    /* renamed from: e */
    public static boolean m156e(Context context) {
        if (f120b == null) {
            f120b = Boolean.valueOf(m143a(context, "com.crashlytics.Trace", false));
        }
        return f120b.booleanValue();
    }

    /* renamed from: a */
    public static boolean m143a(Context context, String str, boolean z) {
        if (context == null) {
            return z;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return z;
        }
        int a = m122a(context, str, "bool");
        if (a > 0) {
            return resources.getBoolean(a);
        }
        int a2 = m122a(context, str, "string");
        if (a2 > 0) {
            return Boolean.parseBoolean(context.getString(a2));
        }
        return z;
    }

    /* renamed from: a */
    public static int m122a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, m161j(context));
    }

    /* renamed from: f */
    public static boolean m157f(Context context) {
        return ServerProtocol.DIALOG_PARAM_SDK_VERSION.equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Settings.Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    /* renamed from: g */
    public static boolean m158g(Context context) {
        boolean f = m157f(context);
        String str = Build.TAGS;
        if ((!f && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (f || !file.exists()) {
            return false;
        }
        return true;
    }

    /* renamed from: c */
    public static boolean m152c() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    /* renamed from: h */
    public static int m159h(Context context) {
        int i = 0;
        if (m157f(context)) {
            i = 1;
        }
        if (m158g(context)) {
            i |= 2;
        }
        if (m152c()) {
            return i | 4;
        }
        return i;
    }

    /* renamed from: a */
    public static int m123a(Context context, boolean z) {
        Float c = m151c(context);
        if (!z || c == null) {
            return 1;
        }
        if (((double) c.floatValue()) >= 99.0d) {
            return 3;
        }
        if (((double) c.floatValue()) < 99.0d) {
            return 2;
        }
        return 0;
    }

    /* renamed from: a */
    public static String m133a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            cArr[i * 2] = f121c[b >>> 4];
            cArr[(i * 2) + 1] = f121c[b & 15];
        }
        return new String(cArr);
    }

    /* renamed from: i */
    public static boolean m160i(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: b */
    public static String m147b(Context context, String str) {
        int a = m122a(context, str, "string");
        if (a > 0) {
            return context.getString(a);
        }
        return "";
    }

    /* renamed from: a */
    public static void m140a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Fabric.m456h().mo8516e("Fabric", str, e);
            }
        }
    }

    /* renamed from: a */
    public static void m141a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                Fabric.m456h().mo8516e("Fabric", str, e);
            }
        }
    }

    /* renamed from: d */
    public static boolean m155d(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: a */
    public static String m127a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(' ', '0');
    }

    /* renamed from: j */
    public static String m161j(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

    /* renamed from: a */
    public static void m142a(InputStream inputStream, OutputStream outputStream, byte[] bArr) {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    public static String m146b(int i) {
        switch (i) {
            case 2:
                return "V";
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            case 7:
                return "A";
            default:
                return "?";
        }
    }

    /* renamed from: k */
    public static String m162k(Context context) {
        InputStream inputStream;
        Throwable th;
        String str = null;
        try {
            inputStream = context.getResources().openRawResource(m163l(context));
            try {
                String b = m148b(inputStream);
                if (!m155d(b)) {
                    str = b;
                }
                m140a((Closeable) inputStream, "Failed to close icon input stream.");
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            inputStream = null;
        } catch (Throwable th2) {
            inputStream = null;
            th = th2;
            m140a((Closeable) inputStream, "Failed to close icon input stream.");
            throw th;
        }
        return str;
        try {
            Fabric.m456h().mo8516e("Fabric", "Could not calculate hash for app icon.", e);
            m140a((Closeable) inputStream, "Failed to close icon input stream.");
            return str;
        } catch (Throwable th3) {
            th = th3;
            m140a((Closeable) inputStream, "Failed to close icon input stream.");
            throw th;
        }
    }

    /* renamed from: l */
    public static int m163l(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    /* renamed from: m */
    public static String m164m(Context context) {
        int a = m122a(context, "io.fabric.android.build_id", "string");
        if (a == 0) {
            a = m122a(context, "com.crashlytics.android.build_id", "string");
        }
        if (a == 0) {
            return null;
        }
        String string = context.getResources().getString(a);
        Fabric.m456h().mo8506a("Fabric", "Build ID is: " + string);
        return string;
    }

    /* renamed from: a */
    public static void m139a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: c */
    public static boolean m153c(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: n */
    public static boolean m165n(Context context) {
        if (!m153c(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
