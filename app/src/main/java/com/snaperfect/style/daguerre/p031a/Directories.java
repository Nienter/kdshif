package com.snaperfect.style.daguerre.p031a;

import android.content.Context;
import android.os.Environment;
import com.snaperfect.inframe1.R;
import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;

/* renamed from: com.snaperfect.style.daguerre.a.a */
public abstract class Directories {

    /* renamed from: a */
    private static String f1727a;

    /* renamed from: b */
    private static String f1728b;

    /* renamed from: c */
    private static String f1729c;

    /* renamed from: d */
    private static String f1730d;

    /* renamed from: e */
    private static String f1731e;

    /* renamed from: f */
    private static String f1732f;

    /* renamed from: g */
    private static final String f1733g = (Environment.getExternalStorageDirectory() + "/DCIM/Camera");

    /* renamed from: h */
    private static String f1734h;

    /* renamed from: i */
    private static String f1735i;

    /* renamed from: j */
    private static String f1736j;

    /* renamed from: k */
    private static HashSet<String> f1737k = new HashSet<>();

    /* renamed from: a */
    public static void m2436a(Context context) {
        f1727a = Environment.getExternalStorageDirectory() + "/." + context.getString(R.string.app_name);
        f1728b = Environment.getExternalStorageDirectory() + "/" + context.getString(R.string.app_name);
        m2438b();
        new File(f1727a).mkdirs();
        new File(f1728b).mkdirs();
        new File(f1729c).mkdirs();
    }

    /* renamed from: a */
    public static String m2433a() {
        return f1728b;
    }

    /* renamed from: a */
    public static Throwable m2435a(String str) {
        try {
            new File(str).delete();
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    /* renamed from: a */
    public static String m2434a(String str, String str2) {
        if (!f1737k.contains(str)) {
            File file = new File(f1729c + str);
            if (file.exists() || file.mkdir()) {
                f1737k.add(str);
            }
        }
        return f1729c + str + "/" + str2;
    }

    /* renamed from: a */
    public static void m2437a(String str, int i) {
        File file = new File(f1729c + str);
        final long currentTimeMillis = System.currentTimeMillis() - ((long) (((i * 3600) * 24) * 1000));
        if (file.exists() && file.isDirectory()) {
            try {
                for (File delete : file.listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        return file.lastModified() < currentTimeMillis;
                    }
                })) {
                    delete.delete();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    private static void m2438b() {
        f1734h = f1727a + "/temp.jpg";
        f1735i = f1727a + "/photo";
        f1736j = f1728b;
        f1729c = f1727a + "/cache/";
        f1732f = f1727a + "/data/";
        f1730d = f1727a + "/share/";
        m2439b(f1727a + "/crop/");
    }

    /* renamed from: b */
    public static void m2439b(String str) {
        f1731e = str;
    }
}
