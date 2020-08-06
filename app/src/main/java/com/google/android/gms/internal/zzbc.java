package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzax;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzbc {
    private static final String TAG = zzbc.class.getSimpleName();
    protected static final Object zzqC = new Object();
    protected static final Object zzqG = new Object();
    private static zzc zzqI = null;
    private volatile boolean zzpX = false;
    private Future zzqA = null;
    /* access modifiers changed from: private */
    public volatile boolean zzqB = false;
    private zzap zzqD;
    private GoogleApiClient zzqE = null;
    protected boolean zzqF = false;
    protected boolean zzqH = false;
    protected boolean zzqJ = false;
    private Map<Pair<String, String>, zzbx> zzqK;
    protected Context zzqr;
    protected Context zzqs;
    private ExecutorService zzqt;
    private DexClassLoader zzqu;
    private zzax zzqv;
    private byte[] zzqw;
    private volatile AdvertisingIdClient zzqx = null;
    private Future zzqy = null;
    private volatile zzaf.zza zzqz = null;

    private zzbc(Context context) {
        this.zzqr = context;
        this.zzqs = context.getApplicationContext();
        this.zzqK = new HashMap();
    }

    public static zzbc zza(Context context, String str, String str2, boolean z) {
        zzbc zzbc = new zzbc(context);
        try {
            zzbc.zzc(str, str2, z);
            return zzbc;
        } catch (zzaz e) {
            return null;
        }
    }

    @NonNull
    private File zza(String str, File file, String str2) {
        File file2 = new File(String.format("%s/%s.jar", new Object[]{file, str2}));
        if (!file2.exists()) {
            byte[] zzc = this.zzqv.zzc(this.zzqw, str);
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(zzc, 0, zzc.length);
            fileOutputStream.close();
        }
        return file2;
    }

    private void zza(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009b A[SYNTHETIC, Splitter:B:27:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a0 A[SYNTHETIC, Splitter:B:30:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ac A[SYNTHETIC, Splitter:B:36:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b1 A[SYNTHETIC, Splitter:B:39:0x00b1] */
    private void zza(File file, String str) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        ? r1 = 0;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
            if (file3.exists()) {
                long length = file3.length();
                if (length > 0) {
                    byte[] bArr = new byte[((int) length)];
                    try {
                        fileInputStream = new FileInputStream(file3);
                        try {
                            if (fileInputStream.read(bArr) <= 0) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                }
                                zza(file3);
                                return;
                            }
                            zzaf.zzd zzd = new zzaf.zzd();
                            zzd.zzcw = Build.VERSION.SDK.getBytes();
                            zzd.zzcv = str.getBytes();
                            byte[] bytes = this.zzqv.zzd(this.zzqw, bArr).getBytes();
                            zzd.data = bytes;
                            zzd.zzcu = zzan.zzh(bytes);
                            file2.createNewFile();
                            fileOutputStream = new FileOutputStream(file2);
                            try {
                                byte[] zzf = zzbut.zzf(zzd);
                                fileOutputStream.write(zzf, 0, zzf.length);
                                fileOutputStream.close();
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2) {
                                }
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                }
                                zza(file3);
                            } catch (IOException e4) {
                                r1 = fileInputStream;
                                if (r1 != 0) {
                                }
                                if (fileOutputStream != null) {
                                }
                                zza(file3);
                            } catch (NoSuchAlgorithmException e5) {
                                r1 = fileInputStream;
                                if (r1 != 0) {
                                }
                                if (fileOutputStream != null) {
                                }
                                zza(file3);
                            } catch (zzax.zza e6) {
                                r1 = fileInputStream;
                                if (r1 != 0) {
                                }
                                if (fileOutputStream != null) {
                                }
                                zza(file3);
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                r1 = fileOutputStream;
                                th = th2;
                                if (fileInputStream != null) {
                                }
                                if (r1 != 0) {
                                }
                                zza(file3);
                                throw th;
                            }
                        } catch (IOException e7) {
                            fileOutputStream = null;
                            r1 = fileInputStream;
                        } catch (NoSuchAlgorithmException e8) {
                            fileOutputStream = null;
                            r1 = fileInputStream;
                        } catch (zzax.zza e9) {
                            fileOutputStream = null;
                            r1 = fileInputStream;
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e10) {
                                }
                            }
                            if (r1 != 0) {
                                try {
                                    r1.close();
                                } catch (IOException e11) {
                                }
                            }
                            zza(file3);
                            throw th;
                        }
                    } catch (IOException e12) {
                        fileOutputStream = null;
                        if (r1 != 0) {
                            try {
                                r1.close();
                            } catch (IOException e13) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e14) {
                            }
                        }
                        zza(file3);
                    } catch (NoSuchAlgorithmException e15) {
                        fileOutputStream = null;
                        if (r1 != 0) {
                        }
                        if (fileOutputStream != null) {
                        }
                        zza(file3);
                    } catch (zzax.zza e16) {
                        fileOutputStream = null;
                        if (r1 != 0) {
                        }
                        if (fileOutputStream != null) {
                        }
                        zza(file3);
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = null;
                        if (fileInputStream != null) {
                        }
                        if (r1 != 0) {
                        }
                        zza(file3);
                        throw th;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzaU() {
        try {
            if (this.zzqx == null && this.zzqs != null) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzqs);
                advertisingIdClient.start();
                this.zzqx = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e) {
            this.zzqx = null;
        }
    }

    private void zzaV() {
        if (zzfx.zzDD.get().booleanValue()) {
            zzaW();
        }
    }

    /* access modifiers changed from: private */
    public void zzaX() {
        if (this.zzqH) {
            try {
                this.zzqz = zzapt.zzi(this.zzqr, this.zzqr.getPackageName(), Integer.toString(this.zzqr.getPackageManager().getPackageInfo(this.zzqr.getPackageName(), 0).versionCode));
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
    }

    private void zzaY() {
        boolean z = true;
        this.zzqt.execute(new Runnable() {
            public void run() {
                zzfx.initialize(zzbc.this.zzqr);
            }
        });
        zzqI = zzc.zzuz();
        this.zzqF = zzqI.zzak(this.zzqr) > 0;
        if (zzqI.isGooglePlayServicesAvailable(this.zzqr) != 0) {
            z = false;
        }
        this.zzqH = z;
        if (this.zzqr.getApplicationContext() != null) {
            this.zzqE = new GoogleApiClient.Builder(this.zzqr).addApi(zzzf.API).build();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c2 A[SYNTHETIC, Splitter:B:42:0x00c2] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c7 A[SYNTHETIC, Splitter:B:45:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d1 A[SYNTHETIC, Splitter:B:51:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d6 A[SYNTHETIC, Splitter:B:54:0x00d6] */
    private boolean zzb(File file, String str) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        ? r1 = 0;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
        if (file3.exists()) {
            return false;
        }
        try {
            long length = file2.length();
            if (length <= 0) {
                zza(file2);
                return false;
            }
            byte[] bArr = new byte[((int) length)];
            fileInputStream = new FileInputStream(file2);
            try {
                if (fileInputStream.read(bArr) <= 0) {
                    Log.d(TAG, "Cannot read the cache data.");
                    zza(file2);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                    return false;
                }
                zzaf.zzd zze = zzaf.zzd.zze(bArr);
                if (!str.equals(new String(zze.zzcv)) || !Arrays.equals(zze.zzcu, zzan.zzh(zze.data)) || !Arrays.equals(zze.zzcw, Build.VERSION.SDK.getBytes())) {
                    zza(file2);
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                    }
                    return false;
                }
                byte[] zzc = this.zzqv.zzc(this.zzqw, new String(zze.data));
                file3.createNewFile();
                FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                try {
                    fileOutputStream2.write(zzc, 0, zzc.length);
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                    try {
                        fileOutputStream2.close();
                        return true;
                    } catch (IOException e4) {
                        return true;
                    }
                } catch (IOException e5) {
                    fileOutputStream = fileOutputStream2;
                    r1 = fileInputStream;
                    if (r1 != 0) {
                    }
                    if (fileOutputStream != null) {
                    }
                    return false;
                } catch (NoSuchAlgorithmException e6) {
                    fileOutputStream = fileOutputStream2;
                    r1 = fileInputStream;
                    if (r1 != 0) {
                    }
                    if (fileOutputStream != null) {
                    }
                    return false;
                } catch (zzax.zza e7) {
                    fileOutputStream = fileOutputStream2;
                    r1 = fileInputStream;
                    if (r1 != 0) {
                    }
                    if (fileOutputStream != null) {
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    r1 = fileOutputStream2;
                    if (fileInputStream != null) {
                    }
                    if (r1 != 0) {
                    }
                    throw th;
                }
            } catch (IOException e8) {
                fileOutputStream = null;
                r1 = fileInputStream;
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (IOException e9) {
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e10) {
                    }
                }
                return false;
            } catch (NoSuchAlgorithmException e11) {
                fileOutputStream = null;
                r1 = fileInputStream;
                if (r1 != 0) {
                }
                if (fileOutputStream != null) {
                }
                return false;
            } catch (zzax.zza e12) {
                fileOutputStream = null;
                r1 = fileInputStream;
                if (r1 != 0) {
                }
                if (fileOutputStream != null) {
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e13) {
                    }
                }
                if (r1 != 0) {
                    try {
                        r1.close();
                    } catch (IOException e14) {
                    }
                }
                throw th;
            }
        } catch (IOException e15) {
            fileOutputStream = null;
            if (r1 != 0) {
            }
            if (fileOutputStream != null) {
            }
            return false;
        } catch (NoSuchAlgorithmException e16) {
            fileOutputStream = null;
            if (r1 != 0) {
            }
            if (fileOutputStream != null) {
            }
            return false;
        } catch (zzax.zza e17) {
            fileOutputStream = null;
            if (r1 != 0) {
            }
            if (fileOutputStream != null) {
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
            }
            if (r1 != 0) {
            }
            throw th;
        }
    }

    private void zzc(boolean z) {
        this.zzpX = z;
        if (z) {
            this.zzqy = this.zzqt.submit(new Runnable() {
                public void run() {
                    zzbc.this.zzaU();
                }
            });
        }
    }

    private boolean zzc(String str, String str2, boolean z) {
        this.zzqt = Executors.newCachedThreadPool();
        zzc(z);
        zzaY();
        zzaV();
        if (!zzbe.zzbd() || !zzfx.zzDB.get().booleanValue()) {
            zzo(str);
            zzp(str2);
            this.zzqD = new zzap(this);
            return true;
        }
        throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
    }

    private void zzo(String str) {
        this.zzqv = new zzax(null);
        try {
            this.zzqw = this.zzqv.zzn(str);
        } catch (zzax.zza e) {
            throw new zzaz(e);
        }
    }

    private boolean zzp(String str) {
        File file;
        String zzX;
        File zza;
        try {
            File cacheDir = this.zzqr.getCacheDir();
            if (cacheDir == null) {
                cacheDir = this.zzqr.getDir("dex", 0);
                if (cacheDir == null) {
                    throw new zzaz();
                }
            }
            file = cacheDir;
            zzX = zzay.zzX();
            zza = zza(str, file, zzX);
            zzb(file, zzX);
            this.zzqu = new DexClassLoader(zza.getAbsolutePath(), file.getAbsolutePath(), null, this.zzqr.getClassLoader());
            zza(zza);
            zza(file, zzX);
            zzq(String.format("%s/%s.dex", new Object[]{file, zzX}));
            return true;
        } catch (FileNotFoundException e) {
            throw new zzaz(e);
        } catch (IOException e2) {
            throw new zzaz(e2);
        } catch (zzax.zza e3) {
            throw new zzaz(e3);
        } catch (NullPointerException e4) {
            throw new zzaz(e4);
        } catch (Throwable th) {
            zza(zza);
            zza(file, zzX);
            zzq(String.format("%s/%s.dex", new Object[]{file, zzX}));
            throw th;
        }
    }

    private void zzq(String str) {
        zza(new File(str));
    }

    public Context getApplicationContext() {
        return this.zzqs;
    }

    public Context getContext() {
        return this.zzqr;
    }

    public int zzT() {
        zzap zzaQ = zzaQ();
        if (zzaQ != null) {
            return zzaQ.zzT();
        }
        return Integer.MIN_VALUE;
    }

    public boolean zza(String str, String str2, List<Class> list) {
        if (this.zzqK.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzqK.put(new Pair(str, str2), new zzbx(this, str, str2, list));
        return true;
    }

    public ExecutorService zzaJ() {
        return this.zzqt;
    }

    public DexClassLoader zzaK() {
        return this.zzqu;
    }

    public zzax zzaL() {
        return this.zzqv;
    }

    public byte[] zzaM() {
        return this.zzqw;
    }

    public GoogleApiClient zzaN() {
        return this.zzqE;
    }

    public boolean zzaO() {
        return this.zzqF;
    }

    public boolean zzaP() {
        return this.zzqJ;
    }

    public zzap zzaQ() {
        return this.zzqD;
    }

    public boolean zzaR() {
        return this.zzqH;
    }

    public zzaf.zza zzaS() {
        return this.zzqz;
    }

    public Future zzaT() {
        return this.zzqA;
    }

    public void zzaW() {
        synchronized (zzqC) {
            if (!this.zzqB) {
                this.zzqA = this.zzqt.submit(new Runnable() {
                    public void run() {
                        zzbc.this.zzaX();
                        synchronized (zzbc.zzqC) {
                            boolean unused = zzbc.this.zzqB = false;
                        }
                    }
                });
                this.zzqB = true;
            }
        }
    }

    public AdvertisingIdClient zzaZ() {
        if (!this.zzpX) {
            return null;
        }
        if (this.zzqx != null) {
            return this.zzqx;
        }
        if (this.zzqy != null) {
            try {
                this.zzqy.get(2000, TimeUnit.MILLISECONDS);
                this.zzqy = null;
            } catch (InterruptedException | ExecutionException e) {
            } catch (TimeoutException e2) {
                this.zzqy.cancel(true);
            }
        }
        return this.zzqx;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    public void zzba() {
        synchronized (zzqG) {
            if (!this.zzqJ) {
                if (!this.zzqH || this.zzqE == null) {
                    this.zzqJ = false;
                } else {
                    this.zzqE.connect();
                    this.zzqJ = true;
                }
            }
        }
    }

    public void zzbb() {
        synchronized (zzqG) {
            if (this.zzqJ && this.zzqE != null) {
                this.zzqE.disconnect();
                this.zzqJ = false;
            }
        }
    }

    public Method zzc(String str, String str2) {
        zzbx zzbx = this.zzqK.get(new Pair(str, str2));
        if (zzbx == null) {
            return null;
        }
        return zzbx.zzbn();
    }
}
