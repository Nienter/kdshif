package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.zzc;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.zza;
import com.google.android.gms.dynamite.zzb;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.util.HashMap;

public final class DynamiteModule {
    public static final zzb zzaQA = new zzb() {
        public zzb.C1698zzb zza(Context context, String str, zzb.zza zza) {
            zzb.C1698zzb zzb = new zzb.C1698zzb();
            zzb.zzaQD = zza.zzA(context, str);
            if (zzb.zzaQD != 0) {
                zzb.zzaQE = zza.zzb(context, str, false);
            } else {
                zzb.zzaQE = zza.zzb(context, str, true);
            }
            if (zzb.zzaQD == 0 && zzb.zzaQE == 0) {
                zzb.zzaQF = 0;
            } else if (zzb.zzaQE >= zzb.zzaQD) {
                zzb.zzaQF = 1;
            } else {
                zzb.zzaQF = -1;
            }
            return zzb;
        }
    };
    private static zza zzaQr;
    private static final HashMap<String, byte[]> zzaQs = new HashMap<>();
    private static String zzaQt;
    private static final zzb.zza zzaQu = new zzb.zza() {
        public int zzA(Context context, String str) {
            return DynamiteModule.zzA(context, str);
        }

        public DynamiteModule zza(Context context, String str, int i) {
            return DynamiteModule.zza(context, str, i);
        }

        public int zzb(Context context, String str, boolean z) {
            return DynamiteModule.zzb(context, str, z);
        }
    };
    private static final zzb.zza zzaQv = new zzb.zza() {
        public int zzA(Context context, String str) {
            return DynamiteModule.zzA(context, str);
        }

        public DynamiteModule zza(Context context, String str, int i) {
            return DynamiteModule.zzb(context, str, i);
        }

        public int zzb(Context context, String str, boolean z) {
            return DynamiteModule.zzc(context, str, z);
        }
    };
    public static final zzb zzaQw = new zzb() {
        public zzb.C1698zzb zza(Context context, String str, zzb.zza zza) {
            zzb.C1698zzb zzb = new zzb.C1698zzb();
            zzb.zzaQE = zza.zzb(context, str, true);
            if (zzb.zzaQE != 0) {
                zzb.zzaQF = 1;
            } else {
                zzb.zzaQD = zza.zzA(context, str);
                if (zzb.zzaQD != 0) {
                    zzb.zzaQF = -1;
                }
            }
            return zzb;
        }
    };
    public static final zzb zzaQx = new zzb() {
        public zzb.C1698zzb zza(Context context, String str, zzb.zza zza) {
            zzb.C1698zzb zzb = new zzb.C1698zzb();
            zzb.zzaQD = zza.zzA(context, str);
            if (zzb.zzaQD != 0) {
                zzb.zzaQF = -1;
            } else {
                zzb.zzaQE = zza.zzb(context, str, true);
                if (zzb.zzaQE != 0) {
                    zzb.zzaQF = 1;
                }
            }
            return zzb;
        }
    };
    public static final zzb zzaQy = new zzb() {
        public zzb.C1698zzb zza(Context context, String str, zzb.zza zza) {
            zzb.C1698zzb zzb = new zzb.C1698zzb();
            zzb.zzaQD = zza.zzA(context, str);
            zzb.zzaQE = zza.zzb(context, str, true);
            if (zzb.zzaQD == 0 && zzb.zzaQE == 0) {
                zzb.zzaQF = 0;
            } else if (zzb.zzaQD >= zzb.zzaQE) {
                zzb.zzaQF = -1;
            } else {
                zzb.zzaQF = 1;
            }
            return zzb;
        }
    };
    public static final zzb zzaQz = new zzb() {
        public zzb.C1698zzb zza(Context context, String str, zzb.zza zza) {
            zzb.C1698zzb zzb = new zzb.C1698zzb();
            zzb.zzaQD = zza.zzA(context, str);
            zzb.zzaQE = zza.zzb(context, str, true);
            if (zzb.zzaQD == 0 && zzb.zzaQE == 0) {
                zzb.zzaQF = 0;
            } else if (zzb.zzaQE >= zzb.zzaQD) {
                zzb.zzaQF = 1;
            } else {
                zzb.zzaQF = -1;
            }
            return zzb;
        }
    };
    private final Context zzaQB;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    public interface zzb {

        public interface zza {
            int zzA(Context context, String str);

            DynamiteModule zza(Context context, String str, int i);

            int zzb(Context context, String str, boolean z);
        }

        /* renamed from: com.google.android.gms.dynamite.DynamiteModule$zzb$zzb  reason: collision with other inner class name */
        public static class C1698zzb {
            public int zzaQD = 0;
            public int zzaQE = 0;
            public int zzaQF = 0;
        }

        C1698zzb zza(Context context, String str, zza zza2);
    }

    private DynamiteModule(Context context) {
        this.zzaQB = (Context) zzac.zzw(context);
    }

    public static int zzA(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            String valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            String valueOf2 = String.valueOf("ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length() + String.valueOf(valueOf2).length()).append(valueOf).append(str).append(".").append(valueOf2).toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf3 = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", new StringBuilder(String.valueOf(valueOf3).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf3).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String valueOf4 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf4.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf4) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zzB(Context context, String str) {
        return zzb(context, str, false);
    }

    private static DynamiteModule zzC(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static ClassLoader zzD(Context context, String str) {
        ClassLoader classLoader;
        synchronized (DynamiteLoaderClassLoader.class) {
            if (DynamiteLoaderClassLoader.sClassLoader != null) {
                classLoader = DynamiteLoaderClassLoader.sClassLoader;
            } else {
                Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                Field declaredField = loadClass.getDeclaredField("sClassLoader");
                synchronized (loadClass) {
                    DynamiteLoaderClassLoader.sClassLoader = (ClassLoader) declaredField.get(null);
                    if (DynamiteLoaderClassLoader.sClassLoader != null) {
                        classLoader = DynamiteLoaderClassLoader.sClassLoader;
                    } else {
                        DynamiteLoaderClassLoader.sClassLoader = new PathClassLoader(str, ClassLoader.getSystemClassLoader()) {
                            /* access modifiers changed from: protected */
                            public Class<?> loadClass(String str, boolean z) {
                                if (!str.startsWith("java.") && !str.startsWith("android.")) {
                                    try {
                                        return findClass(str);
                                    } catch (ClassNotFoundException e) {
                                    }
                                }
                                return super.loadClass(str, z);
                            }
                        };
                        declaredField.set(null, DynamiteLoaderClassLoader.sClassLoader);
                        classLoader = DynamiteLoaderClassLoader.sClassLoader;
                    }
                }
            }
        }
        return classLoader;
    }

    private static Context zza(Context context, String str, byte[] bArr, String str2) {
        if (str2 == null || str2.isEmpty()) {
            Log.e("DynamiteModule", "No valid DynamiteLoader APK path");
            return null;
        }
        try {
            return (Context) zze.zzE(zzb.zza.zzcf((IBinder) zzD(context, str2).loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0])).zza(zze.zzA(context), str, bArr));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(valueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    public static DynamiteModule zza(Context context, zzb zzb2, String str) {
        if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
            return zza(context, zzb2, str, zzaQu);
        }
        try {
            return zza(context, zzb2, str, zzaQv);
        } catch (zza e) {
            String valueOf = String.valueOf(e.toString());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load module via fast route".concat(valueOf) : new String("Failed to load module via fast route"));
            return zza(context, zzb2, str, zzaQu);
        }
    }

    public static DynamiteModule zza(Context context, zzb zzb2, String str, zzb.zza zza2) {
        zzb.C1698zzb zza3 = zzb2.zza(context, str, zza2);
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza3.zzaQD).append(" and remote module ").append(str).append(":").append(zza3.zzaQE).toString());
        if (zza3.zzaQF == 0 || ((zza3.zzaQF == -1 && zza3.zzaQD == 0) || (zza3.zzaQF == 1 && zza3.zzaQE == 0))) {
            throw new zza(new StringBuilder(91).append("No acceptable module found. Local version is ").append(zza3.zzaQD).append(" and remote version is ").append(zza3.zzaQE).append(".").toString());
        } else if (zza3.zzaQF == -1) {
            return zzC(context, str);
        } else {
            if (zza3.zzaQF == 1) {
                try {
                    return zza2.zza(context, str, zza3.zzaQE);
                } catch (zza e) {
                    zza zza4 = e;
                    String valueOf = String.valueOf(zza4.getMessage());
                    Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                    if (zza3.zzaQD != 0) {
                        final int i = zza3.zzaQD;
                        if (zzb2.zza(context, str, new zzb.zza() {
                            public int zzA(Context context, String str) {
                                return i;
                            }

                            public DynamiteModule zza(Context context, String str, int i) {
                                throw new zza("local only VersionPolicy should not load from remote");
                            }

                            public int zzb(Context context, String str, boolean z) {
                                return 0;
                            }
                        }).zzaQF == -1) {
                            return zzC(context, str);
                        }
                    }
                    throw new zza("Remote load failed. No local fallback found.", zza4);
                }
            } else {
                throw new zza(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(zza3.zzaQF).toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public static DynamiteModule zza(Context context, String str, int i) {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zza zzaU = zzaU(context);
        if (zzaU == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            zzd zza2 = zzaU.zza(zze.zzA(context), str, i);
            if (zze.zzE(zza2) != null) {
                return new DynamiteModule((Context) zze.zzE(zza2));
            }
            throw new zza("Failed to load remote module.");
        } catch (RemoteException e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    private static zza zzaU(Context context) {
        synchronized (DynamiteModule.class) {
            if (zzaQr != null) {
                zza zza2 = zzaQr;
                return zza2;
            } else if (zzc.zzuz().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    zza zzce = zza.C1699zza.zzce((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                    if (zzce != null) {
                        zzaQr = zzce;
                        return zzce;
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
                    return null;
                }
            }
        }
    }

    public static int zzb(Context context, String str, boolean z) {
        zza zzaU = zzaU(context);
        if (zzaU == null) {
            return 0;
        }
        try {
            return zzaU.zza(zze.zzA(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static DynamiteModule zzb(Context context, String str, int i) {
        byte[] bArr;
        String str2;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            bArr = zzaQs.get(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString());
            str2 = zzaQt;
        }
        if (bArr == null) {
            throw new zza("Module implementation could not be found.");
        }
        Context zza2 = zza(context.getApplicationContext(), str, bArr, str2);
        if (zza2 != null) {
            return new DynamiteModule(zza2);
        }
        throw new zza("Failed to get module context");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
    public static int zzc(Context context, String str, boolean z) {
        Cursor cursor;
        Cursor cursor2 = null;
        String str2 = z ? "api_force_staging" : "api";
        try {
            String valueOf = String.valueOf("content://com.google.android.gms.chimera/");
            Uri parse = Uri.parse(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str2).length() + String.valueOf(str).length()).append(valueOf).append(str2).append("/").append(str).toString());
            if (context != null) {
                ContentResolver contentResolver = context.getContentResolver();
                if (contentResolver != null) {
                    cursor = contentResolver.query(parse, null, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                int i = cursor.getInt(0);
                                if (i > 0) {
                                    synchronized (DynamiteModule.class) {
                                        zzaQs.put(new StringBuilder(String.valueOf(str).length() + 12).append(str).append(":").append(i).toString(), Base64.decode(cursor.getString(3), 0));
                                        zzaQt = cursor.getString(2);
                                    }
                                }
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return i;
                            }
                        } catch (Exception e) {
                            e = e;
                        }
                    }
                    Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                    throw new zza("Failed to connect to dynamite module ContentResolver.");
                }
            }
            throw new zza("Failed to get dynamite module ContentResolver.");
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        try {
            if (e instanceof zza) {
                throw e;
            }
            throw new zza("V2 version check failed", e);
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    public Context zzBd() {
        return this.zzaQB;
    }

    public IBinder zzdX(String str) {
        try {
            return (IBinder) this.zzaQB.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e);
        }
    }
}
