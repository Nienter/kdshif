package com.google.android.gms.internal;

import android.os.SystemClock;
import com.google.android.gms.internal.zzb;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class zzv implements zzb {
    private final Map<String, zza> zzav;
    private long zzaw;
    private final File zzax;
    private final int zzay;

    static class zza {
        public String zza;
        public String zzaA;
        public long zzaz;
        public long zzb;
        public long zzc;
        public long zzd;
        public long zze;
        public Map<String, String> zzf;

        private zza() {
        }

        public zza(String str, zzb.zza zza2) {
            this.zzaA = str;
            this.zzaz = (long) zza2.data.length;
            this.zza = zza2.zza;
            this.zzb = zza2.zzb;
            this.zzc = zza2.zzc;
            this.zzd = zza2.zzd;
            this.zze = zza2.zze;
            this.zzf = zza2.zzf;
        }

        public static zza zzf(InputStream inputStream) {
            zza zza2 = new zza();
            if (zzv.zzb(inputStream) != 538247942) {
                throw new IOException();
            }
            zza2.zzaA = zzv.zzd(inputStream);
            zza2.zza = zzv.zzd(inputStream);
            if (zza2.zza.equals("")) {
                zza2.zza = null;
            }
            zza2.zzb = zzv.zzc(inputStream);
            zza2.zzc = zzv.zzc(inputStream);
            zza2.zzd = zzv.zzc(inputStream);
            zza2.zze = zzv.zzc(inputStream);
            zza2.zzf = zzv.zze(inputStream);
            return zza2;
        }

        public boolean zza(OutputStream outputStream) {
            try {
                zzv.zza(outputStream, 538247942);
                zzv.zza(outputStream, this.zzaA);
                zzv.zza(outputStream, this.zza == null ? "" : this.zza);
                zzv.zza(outputStream, this.zzb);
                zzv.zza(outputStream, this.zzc);
                zzv.zza(outputStream, this.zzd);
                zzv.zza(outputStream, this.zze);
                zzv.zza(this.zzf, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                zzs.zzb("%s", e.toString());
                return false;
            }
        }

        public zzb.zza zzb(byte[] bArr) {
            zzb.zza zza2 = new zzb.zza();
            zza2.data = bArr;
            zza2.zza = this.zza;
            zza2.zzb = this.zzb;
            zza2.zzc = this.zzc;
            zza2.zzd = this.zzd;
            zza2.zze = this.zze;
            zza2.zzf = this.zzf;
            return zza2;
        }
    }

    private static class zzb extends FilterInputStream {
        /* access modifiers changed from: private */
        public int zzaB;

        private zzb(InputStream inputStream) {
            super(inputStream);
            this.zzaB = 0;
        }

        public int read() {
            int read = super.read();
            if (read != -1) {
                this.zzaB++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.zzaB += read;
            }
            return read;
        }
    }

    public zzv(File file) {
        this(file, 5242880);
    }

    public zzv(File file, int i) {
        this.zzav = new LinkedHashMap(16, 0.75f, true);
        this.zzaw = 0;
        this.zzax = file;
        this.zzay = i;
    }

    private void removeEntry(String str) {
        zza zza2 = this.zzav.get(str);
        if (zza2 != null) {
            this.zzaw -= zza2.zzaz;
            this.zzav.remove(str);
        }
    }

    private static int zza(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void zza(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static void zza(OutputStream outputStream, long j) {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void zza(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private void zza(String str, zza zza2) {
        if (!this.zzav.containsKey(str)) {
            this.zzaw += zza2.zzaz;
        } else {
            this.zzaw = (zza2.zzaz - this.zzav.get(str).zzaz) + this.zzaw;
        }
        this.zzav.put(str, zza2);
    }

    static void zza(Map<String, String> map, OutputStream outputStream) {
        if (map != null) {
            zza(outputStream, map.size());
            for (Map.Entry next : map.entrySet()) {
                zza(outputStream, (String) next.getKey());
                zza(outputStream, (String) next.getValue());
            }
            return;
        }
        zza(outputStream, 0);
    }

    private static byte[] zza(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException(new StringBuilder(50).append("Expected ").append(i).append(" bytes, read ").append(i2).append(" bytes").toString());
    }

    static int zzb(InputStream inputStream) {
        return (zza(inputStream) << 0) | 0 | (zza(inputStream) << 8) | (zza(inputStream) << 16) | (zza(inputStream) << 24);
    }

    static long zzc(InputStream inputStream) {
        return 0 | ((((long) zza(inputStream)) & 255) << 0) | ((((long) zza(inputStream)) & 255) << 8) | ((((long) zza(inputStream)) & 255) << 16) | ((((long) zza(inputStream)) & 255) << 24) | ((((long) zza(inputStream)) & 255) << 32) | ((((long) zza(inputStream)) & 255) << 40) | ((((long) zza(inputStream)) & 255) << 48) | ((((long) zza(inputStream)) & 255) << 56);
    }

    private void zzc(int i) {
        int i2;
        if (this.zzaw + ((long) i) >= ((long) this.zzay)) {
            if (zzs.DEBUG) {
                zzs.zza("Pruning old cache entries.", new Object[0]);
            }
            long j = this.zzaw;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, zza>> it = this.zzav.entrySet().iterator();
            int i3 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = i3;
                    break;
                }
                zza zza2 = (zza) it.next().getValue();
                if (zzf(zza2.zzaA).delete()) {
                    this.zzaw -= zza2.zzaz;
                } else {
                    zzs.zzb("Could not delete cache entry for key=%s, filename=%s", zza2.zzaA, zze(zza2.zzaA));
                }
                it.remove();
                i2 = i3 + 1;
                if (((float) (this.zzaw + ((long) i))) < ((float) this.zzay) * 0.9f) {
                    break;
                }
                i3 = i2;
            }
            if (zzs.DEBUG) {
                zzs.zza("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.zzaw - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    static String zzd(InputStream inputStream) {
        return new String(zza(inputStream, (int) zzc(inputStream)), "UTF-8");
    }

    private String zze(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(String.valueOf(str.substring(0, length).hashCode()));
        String valueOf2 = String.valueOf(String.valueOf(str.substring(length).hashCode()));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    static Map<String, String> zze(InputStream inputStream) {
        int zzb2 = zzb(inputStream);
        Map<String, String> emptyMap = zzb2 == 0 ? Collections.emptyMap() : new HashMap<>(zzb2);
        for (int i = 0; i < zzb2; i++) {
            emptyMap.put(zzd(inputStream).intern(), zzd(inputStream).intern());
        }
        return emptyMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[SYNTHETIC, Splitter:B:27:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005d A[SYNTHETIC, Splitter:B:30:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0066 A[SYNTHETIC, Splitter:B:35:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0050 A[SYNTHETIC] */
    public synchronized void initialize() {
        BufferedInputStream bufferedInputStream;
        if (this.zzax.exists()) {
            File[] listFiles = this.zzax.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    BufferedInputStream bufferedInputStream2 = null;
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                        try {
                            zza zzf = zza.zzf(bufferedInputStream);
                            zzf.zzaz = file.length();
                            zza(zzf.zzaA, zzf);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e) {
                            }
                        } catch (IOException e2) {
                            if (file != null) {
                                try {
                                    file.delete();
                                } catch (Throwable th) {
                                    Throwable th2 = th;
                                    bufferedInputStream2 = bufferedInputStream;
                                    th = th2;
                                    if (bufferedInputStream2 != null) {
                                        try {
                                            bufferedInputStream2.close();
                                        } catch (IOException e3) {
                                        }
                                    }
                                    throw th;
                                }
                            }
                            if (bufferedInputStream == null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                        }
                    } catch (IOException e5) {
                        bufferedInputStream = null;
                        if (file != null) {
                        }
                        if (bufferedInputStream == null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (bufferedInputStream2 != null) {
                        }
                        throw th;
                    }
                }
            }
        } else if (!this.zzax.mkdirs()) {
            zzs.zzc("Unable to create cache dir %s", this.zzax.getAbsolutePath());
        }
        return;
    }

    public synchronized void remove(String str) {
        boolean delete = zzf(str).delete();
        removeEntry(str);
        if (!delete) {
            zzs.zzb("Could not delete cache entry for key=%s, filename=%s", str, zze(str));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0064 A[SYNTHETIC, Splitter:B:32:0x0064] */
    public synchronized zzb.zza zza(String str) {
        zzb zzb2;
        zzb.zza zza2;
        zza zza3 = this.zzav.get(str);
        if (zza3 == null) {
            zza2 = null;
        } else {
            File zzf = zzf(str);
            try {
                zzb2 = new zzb(new FileInputStream(zzf));
                try {
                    zza.zzf(zzb2);
                    zza2 = zza3.zzb(zza((InputStream) zzb2, (int) (zzf.length() - ((long) zzb2.zzaB))));
                    try {
                        zzb2.close();
                    } catch (IOException e) {
                        zza2 = null;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (IOException e3) {
                e = e3;
                zzb2 = null;
                try {
                    zzs.zzb("%s: %s", zzf.getAbsolutePath(), e.toString());
                    remove(str);
                    if (zzb2 != null) {
                        try {
                            zzb2.close();
                        } catch (IOException e4) {
                            zza2 = null;
                        }
                    }
                    zza2 = null;
                    return zza2;
                } catch (Throwable th) {
                    th = th;
                    if (zzb2 != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                zzb2 = null;
                if (zzb2 != null) {
                    try {
                        zzb2.close();
                    } catch (IOException e5) {
                        zza2 = null;
                    }
                }
                throw th;
            }
        }
        return zza2;
    }

    public synchronized void zza(String str, zzb.zza zza2) {
        zzc(zza2.data.length);
        File zzf = zzf(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzf);
            zza zza3 = new zza(str, zza2);
            if (!zza3.zza(fileOutputStream)) {
                fileOutputStream.close();
                zzs.zzb("Failed to write header for %s", zzf.getAbsolutePath());
                throw new IOException();
            }
            fileOutputStream.write(zza2.data);
            fileOutputStream.close();
            zza(str, zza3);
        } catch (IOException e) {
            if (!zzf.delete()) {
                zzs.zzb("Could not clean up file %s", zzf.getAbsolutePath());
            }
        }
    }

    public File zzf(String str) {
        return new File(this.zzax, zze(str));
    }
}
