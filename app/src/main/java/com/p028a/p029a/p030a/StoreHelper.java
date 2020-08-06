package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.GraphResponse;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;

/* renamed from: com.a.a.a.bb */
public final class StoreHelper {

    /* renamed from: a */
    private static StoreHelper f1415a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static Context f1416b;

    /* renamed from: c */
    private static String f1417c;

    /* renamed from: d */
    private C0606a f1418d;

    /* renamed from: com.a.a.a.bb$a */
    /* compiled from: StoreHelper */
    public static class C0606a {

        /* renamed from: a */
        private final int f1420a;

        /* renamed from: b */
        private File f1421b;

        /* renamed from: c */
        private FilenameFilter f1422c;

        public C0606a(Context context) {
            this(context, ".um");
        }

        public C0606a(Context context, String str) {
            this.f1420a = 10;
            this.f1422c = new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith("um");
                }
            };
            this.f1421b = new File(context.getFilesDir(), str);
            if (!this.f1421b.exists() || !this.f1421b.isDirectory()) {
                this.f1421b.mkdir();
            }
        }

        /* renamed from: a */
        public boolean mo9399a() {
            File[] listFiles = this.f1421b.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return false;
            }
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
            r2[r0].delete();
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* renamed from: a */
        public void mo9397a(C0609b bVar) {
            int i = 0;
            File[] listFiles = this.f1421b.listFiles(this.f1422c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                final int length = listFiles.length - 10;
                QueuedWork.m1849b(new Runnable() {
                    public void run() {
                        if (length > 0) {
                            UMCCAggregatedManager.m2160a(StoreHelper.f1416b).mo9520a((long) length, System.currentTimeMillis(), "__evp_file_of");
                        }
                    }
                });
                for (int i2 = 0; i2 < length; i2++) {
                    listFiles[i2].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                bVar.mo9080a(this.f1421b);
                int length2 = listFiles.length;
                while (i < length2) {
                    if (bVar.mo9081b(listFiles[i])) {
                        listFiles[i].delete();
                    }
                    i++;
                }
                bVar.mo9082c(this.f1421b);
            }
        }

        /* renamed from: a */
        public void mo9398a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    HelperUtils.m1828a(new File(this.f1421b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: com.a.a.a.bb$b */
    /* compiled from: StoreHelper */
    public interface C0609b {
        /* renamed from: a */
        void mo9080a(File file);

        /* renamed from: b */
        boolean mo9081b(File file);

        /* renamed from: c */
        void mo9082c(File file);
    }

    public StoreHelper(Context context) {
        this.f1418d = new C0606a(context);
    }

    /* renamed from: a */
    public static synchronized StoreHelper m1888a(Context context) {
        StoreHelper bbVar;
        synchronized (StoreHelper.class) {
            f1416b = context.getApplicationContext();
            f1417c = context.getPackageName();
            if (f1415a == null) {
                f1415a = new StoreHelper(context);
            }
            bbVar = f1415a;
        }
        return bbVar;
    }

    /* renamed from: a */
    public String[] mo9389a() {
        SharedPreferences i = m1890i();
        String string = i.getString("au_p", null);
        String string2 = i.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    /* renamed from: b */
    public String mo9390b() {
        SharedPreferences a = PreferenceWrapper.m1333a(f1416b);
        if (a != null) {
            return a.getString("appkey", null);
        }
        return null;
    }

    /* renamed from: c */
    public String mo9391c() {
        SharedPreferences a = PreferenceWrapper.m1333a(f1416b);
        if (a != null) {
            return a.getString("st", null);
        }
        return null;
    }

    /* renamed from: d */
    public int mo9392d() {
        SharedPreferences a = PreferenceWrapper.m1333a(f1416b);
        if (a != null) {
            return a.getInt("vt", 0);
        }
        return 0;
    }

    /* renamed from: e */
    public void mo9393e() {
        f1416b.deleteFile(m1891j());
        f1416b.deleteFile(m1892k());
        UMStoreManager.m2242a(f1416b).mo9557a(true, false);
        UMCCAggregatedManager.m2160a(f1416b).mo9523b((C0614cl) new C0614cl() {
            /* renamed from: a */
            public void mo9396a(Object obj, boolean z) {
                if (obj.equals(GraphResponse.SUCCESS_KEY)) {
                }
            }
        });
    }

    /* renamed from: a */
    public void mo9388a(byte[] bArr) {
        this.f1418d.mo9398a(bArr);
    }

    /* renamed from: f */
    public boolean mo9394f() {
        return this.f1418d.mo9399a();
    }

    /* renamed from: g */
    public C0606a mo9395g() {
        return this.f1418d;
    }

    /* renamed from: i */
    private SharedPreferences m1890i() {
        return f1416b.getSharedPreferences("mobclick_agent_user_" + f1417c, 0);
    }

    /* renamed from: j */
    private String m1891j() {
        return "mobclick_agent_header_" + f1417c;
    }

    /* renamed from: k */
    private String m1892k() {
        SharedPreferences a = PreferenceWrapper.m1333a(f1416b);
        if (a == null) {
            return "mobclick_agent_cached_" + f1417c + DeviceConfig.m1787a(f1416b);
        }
        int i = a.getInt("versioncode", 0);
        int parseInt = Integer.parseInt(DeviceConfig.m1787a(f1416b));
        if (i == 0 || parseInt == i) {
            return "mobclick_agent_cached_" + f1417c + DeviceConfig.m1787a(f1416b);
        }
        return "mobclick_agent_cached_" + f1417c + i;
    }
}
