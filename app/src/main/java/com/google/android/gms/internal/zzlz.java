package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzma;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

@zzmb
public class zzlz implements zzma {
    private static zzma zzQJ = null;
    private static final Object zztU = new Object();
    private final Object zzQK = new Object();
    private final String zzQL;
    private final WeakHashMap<Thread, Boolean> zzQM = new WeakHashMap<>();
    private final zzqa zztZ;

    zzlz(String str, zzqa zzqa) {
        this.zzQL = str;
        this.zztZ = zzqa;
        zziW();
        zziV();
    }

    public static zzma zzb(Context context, zzqa zzqa) {
        String packageName;
        synchronized (zztU) {
            if (zzQJ == null) {
                if (zzfx.zzAW.get().booleanValue()) {
                    try {
                        packageName = context.getApplicationContext().getPackageName();
                    } catch (Throwable th) {
                        zzpe.zzbe("Cannot obtain package name, proceeding.");
                    }
                    zzQJ = new zzlz(packageName, zzqa);
                } else {
                    zzQJ = new zzma.zza();
                }
            }
        }
        return zzQJ;
    }

    private Throwable zzd(Throwable th) {
        Throwable th2;
        if (zzfx.zzAX.get().booleanValue()) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th3 = null;
        while (!linkedList.isEmpty()) {
            Throwable th4 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th4.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean z = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (zzaH(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    z = true;
                } else if (zzaI(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                } else {
                    arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                }
            }
            if (z) {
                th2 = th3 == null ? new Throwable(th4.getMessage()) : new Throwable(th4.getMessage(), th3);
                th2.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            } else {
                th2 = th3;
            }
            th3 = th2;
        }
        return th3;
    }

    private void zziV() {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                try {
                    zzlz.this.zza(thread, th);
                    if (defaultUncaughtExceptionHandler != null) {
                        defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                    }
                } catch (Throwable th2) {
                    if (defaultUncaughtExceptionHandler != null) {
                        defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                    }
                    throw th2;
                }
            }
        });
    }

    private void zziW() {
        zza(Looper.getMainLooper().getThread());
    }

    /* access modifiers changed from: package-private */
    public String zza(Class cls, Throwable th, String str) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT)).appendQueryParameter("device", zzv.zzcJ().zzkm()).appendQueryParameter("js", this.zztZ.zzaZ).appendQueryParameter("appid", this.zzQL).appendQueryParameter("exceptiontype", cls.getName()).appendQueryParameter("stacktrace", stringWriter.toString()).appendQueryParameter("eids", TextUtils.join(",", zzfx.zzfn())).appendQueryParameter("exceptionkey", str).appendQueryParameter("cl", "139965458").appendQueryParameter("rc", "dev").toString();
    }

    public void zza(Thread thread) {
        if (thread != null) {
            synchronized (this.zzQK) {
                this.zzQM.put(thread, true);
            }
            final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    try {
                        zzlz.this.zza(thread, th);
                        if (uncaughtExceptionHandler != null) {
                            uncaughtExceptionHandler.uncaughtException(thread, th);
                        }
                    } catch (Throwable th2) {
                        if (uncaughtExceptionHandler != null) {
                            uncaughtExceptionHandler.uncaughtException(thread, th);
                        }
                        throw th2;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void zza(Thread thread, Throwable th) {
        if (zzb(th)) {
            zzc(th);
        }
    }

    public void zza(Throwable th, String str) {
        Throwable zzd = zzd(th);
        if (zzd != null) {
            Class<?> cls = th.getClass();
            ArrayList arrayList = new ArrayList();
            arrayList.add(zza(cls, zzd, str));
            zzv.zzcJ().zza((List<String>) arrayList, zzv.zzcN().zzjQ());
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzaH(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith(zzfx.zzAY.get())) {
            return true;
        }
        try {
            return Class.forName(str).isAnnotationPresent(zzmb.class);
        } catch (Exception e) {
            Exception exc = e;
            String valueOf = String.valueOf(str);
            zzpe.zza(valueOf.length() != 0 ? "Fail to check class type for class ".concat(valueOf) : new String("Fail to check class type for class "), exc);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzaI(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("android.") || str.startsWith("java.");
    }

    /* access modifiers changed from: protected */
    public boolean zzb(Throwable th) {
        boolean z = true;
        if (th == null) {
            return false;
        }
        boolean z2 = false;
        boolean z3 = false;
        while (th != null) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (zzaH(stackTraceElement.getClassName())) {
                    z3 = true;
                }
                if (getClass().getName().equals(stackTraceElement.getClassName())) {
                    z2 = true;
                }
            }
            th = th.getCause();
        }
        if (!z3 || z2) {
            z = false;
        }
        return z;
    }

    public void zzc(Throwable th) {
        zza(th, "");
    }
}
