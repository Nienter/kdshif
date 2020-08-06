package com.squareup.picasso;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import p033d.BufferedSource;
import p033d.ByteString;

/* renamed from: com.squareup.picasso.ah */
final class Utils {

    /* renamed from: a */
    static final StringBuilder f2463a = new StringBuilder();

    /* renamed from: b */
    private static final ByteString f2464b = ByteString.encodeUtf8("RIFF");

    /* renamed from: c */
    private static final ByteString f2465c = ByteString.encodeUtf8("WEBP");

    /* renamed from: com.squareup.picasso.ah$a */
    /* compiled from: Utils */
    private static class C1616a extends Thread {
        public C1616a(Runnable runnable) {
            super(runnable);
        }

        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    /* renamed from: com.squareup.picasso.ah$b */
    /* compiled from: Utils */
    static class C1617b implements ThreadFactory {
        C1617b() {
        }

        public Thread newThread(Runnable runnable) {
            return new C1616a(runnable);
        }
    }

    /* renamed from: a */
    static int m3271a(Bitmap bitmap) {
        int allocationByteCount = Build.VERSION.SDK_INT >= 19 ? bitmap.getAllocationByteCount() : bitmap.getByteCount();
        if (allocationByteCount >= 0) {
            return allocationByteCount;
        }
        throw new IllegalStateException("Negative size: " + bitmap);
    }

    /* renamed from: a */
    static <T> T m3276a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* renamed from: a */
    static void m3281a() {
        if (m3289c()) {
            throw new IllegalStateException("Method call should not happen from the main thread.");
        }
    }

    /* renamed from: b */
    static void m3287b() {
        if (!m3289c()) {
            throw new IllegalStateException("Method call should happen from the main thread.");
        }
    }

    /* renamed from: c */
    static boolean m3289c() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /* renamed from: a */
    static String m3277a(BitmapHunter cVar) {
        return m3278a(cVar, "");
    }

    /* renamed from: a */
    static String m3278a(BitmapHunter cVar, String str) {
        StringBuilder sb = new StringBuilder(str);
        Action i = cVar.mo17513i();
        if (i != null) {
            sb.append(i.f2415b.mo17598a());
        }
        List<Action> k = cVar.mo17515k();
        if (k != null) {
            int size = k.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (i2 > 0 || i != null) {
                    sb.append(", ");
                }
                sb.append(k.get(i2).f2415b.mo17598a());
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    static void m3283a(String str, String str2, String str3) {
        m3284a(str, str2, str3, "");
    }

    /* renamed from: a */
    static void m3284a(String str, String str2, String str3, String str4) {
        Log.d("Picasso", String.format("%1$-11s %2$-12s %3$s %4$s", new Object[]{str, str2, str3, str4}));
    }

    /* renamed from: a */
    static String m3279a(C1645y yVar) {
        String a = m3280a(yVar, f2463a);
        f2463a.setLength(0);
        return a;
    }

    /* renamed from: a */
    static String m3280a(C1645y yVar, StringBuilder sb) {
        if (yVar.f2598f != null) {
            sb.ensureCapacity(yVar.f2598f.length() + 50);
            sb.append(yVar.f2598f);
        } else if (yVar.f2596d != null) {
            String uri = yVar.f2596d.toString();
            sb.ensureCapacity(uri.length() + 50);
            sb.append(uri);
        } else {
            sb.ensureCapacity(50);
            sb.append(yVar.f2597e);
        }
        sb.append(10);
        if (yVar.f2606n != 0.0f) {
            sb.append("rotation:").append(yVar.f2606n);
            if (yVar.f2609q) {
                sb.append('@').append(yVar.f2607o).append('x').append(yVar.f2608p);
            }
            sb.append(10);
        }
        if (yVar.mo17601d()) {
            sb.append("resize:").append(yVar.f2600h).append('x').append(yVar.f2601i);
            sb.append(10);
        }
        if (yVar.f2602j) {
            sb.append("centerCrop:").append(yVar.f2603k).append(10);
        } else if (yVar.f2604l) {
            sb.append("centerInside").append(10);
        }
        if (yVar.f2599g != null) {
            int size = yVar.f2599g.size();
            for (int i = 0; i < size; i++) {
                sb.append(yVar.f2599g.get(i).mo17274a());
                sb.append(10);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    static File m3274a(Context context) {
        File file = new File(context.getApplicationContext().getCacheDir(), "picasso-cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    @TargetApi(18)
    /* renamed from: a */
    static long m3272a(File file) {
        long j;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            j = ((Build.VERSION.SDK_INT < 18 ? (long) statFs.getBlockSize() : statFs.getBlockSizeLong()) * (Build.VERSION.SDK_INT < 18 ? (long) statFs.getBlockCount() : statFs.getBlockCountLong())) / 50;
        } catch (IllegalArgumentException e) {
            j = 5242880;
        }
        return Math.max(Math.min(j, 52428800), 5242880);
    }

    /* renamed from: b */
    static int m3286b(Context context) {
        ActivityManager activityManager = (ActivityManager) m3275a(context, "activity");
        return (int) ((((long) ((context.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass())) * 1048576) / 7);
    }

    /* renamed from: c */
    static boolean m3290c(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (Build.VERSION.SDK_INT < 17) {
                if (Settings.System.getInt(contentResolver, "airplane_mode_on", 0) != 0) {
                    return true;
                }
                return false;
            } else if (Settings.Global.getInt(contentResolver, "airplane_mode_on", 0) == 0) {
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        } catch (SecurityException e2) {
            return false;
        }
    }

    /* renamed from: a */
    static <T> T m3275a(Context context, String str) {
        return context.getSystemService(str);
    }

    /* renamed from: b */
    static boolean m3288b(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: a */
    static boolean m3285a(BufferedSource eVar) {
        return eVar.mo17645a(0, f2464b) && eVar.mo17645a(8, f2465c);
    }

    /* renamed from: a */
    static int m3270a(Resources resources, C1645y yVar) {
        if (yVar.f2597e != 0 || yVar.f2596d == null) {
            return yVar.f2597e;
        }
        String authority = yVar.f2596d.getAuthority();
        if (authority == null) {
            throw new FileNotFoundException("No package provided: " + yVar.f2596d);
        }
        List<String> pathSegments = yVar.f2596d.getPathSegments();
        if (pathSegments == null || pathSegments.isEmpty()) {
            throw new FileNotFoundException("No path segments: " + yVar.f2596d);
        } else if (pathSegments.size() == 1) {
            try {
                return Integer.parseInt(pathSegments.get(0));
            } catch (NumberFormatException e) {
                throw new FileNotFoundException("Last path segment is not a resource ID: " + yVar.f2596d);
            }
        } else if (pathSegments.size() == 2) {
            return resources.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
        } else {
            throw new FileNotFoundException("More than two path segments: " + yVar.f2596d);
        }
    }

    /* renamed from: a */
    static Resources m3273a(Context context, C1645y yVar) {
        if (yVar.f2597e != 0 || yVar.f2596d == null) {
            return context.getResources();
        }
        String authority = yVar.f2596d.getAuthority();
        if (authority == null) {
            throw new FileNotFoundException("No package provided: " + yVar.f2596d);
        }
        try {
            return context.getPackageManager().getResourcesForApplication(authority);
        } catch (PackageManager.NameNotFoundException e) {
            throw new FileNotFoundException("Unable to obtain resources for package: " + yVar.f2596d);
        }
    }

    /* renamed from: a */
    static void m3282a(Looper looper) {
        C16151 r0 = new Handler(looper) {
            public void handleMessage(Message message) {
                sendMessageDelayed(obtainMessage(), 1000);
            }
        };
        r0.sendMessageDelayed(r0.obtainMessage(), 1000);
    }
}
