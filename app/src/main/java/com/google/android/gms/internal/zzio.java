package com.google.android.gms.internal;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzmb
public class zzio extends zzil {
    private static final Set<String> zzIh = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzIi = new DecimalFormat("#,###");
    private File zzIj;
    private boolean zzIk;

    public zzio(zzqp zzqp) {
        super(zzqp);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzpe.zzbe("Context.getCacheDir() returned null");
            return;
        }
        this.zzIj = new File(cacheDir, "admobVideoStreams");
        if (!this.zzIj.isDirectory() && !this.zzIj.mkdirs()) {
            String valueOf = String.valueOf(this.zzIj.getAbsolutePath());
            zzpe.zzbe(valueOf.length() != 0 ? "Could not create preload cache directory at ".concat(valueOf) : new String("Could not create preload cache directory at "));
            this.zzIj = null;
        } else if (!this.zzIj.setReadable(true, false) || !this.zzIj.setExecutable(true, false)) {
            String valueOf2 = String.valueOf(this.zzIj.getAbsolutePath());
            zzpe.zzbe(valueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(valueOf2) : new String("Could not set cache file permissions at "));
            this.zzIj = null;
        }
    }

    private File zzb(File file) {
        return new File(this.zzIj, String.valueOf(file.getName()).concat(".done"));
    }

    private static void zzc(File file) {
        if (file.isFile()) {
            file.setLastModified(System.currentTimeMillis());
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
    }

    public void abort() {
        this.zzIk = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0306, code lost:
        r5 = r5 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0307, code lost:
        if (r5 <= r14) goto L_0x0336;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0319, code lost:
        if (r3.length() == 0) goto L_0x032b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x031b, code lost:
        r3 = "File too big for full file cache. Size: ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0326, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0327, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0328, code lost:
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:?, code lost:
        r3 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0331, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0332, code lost:
        r3 = null;
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
        r17.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x033d, code lost:
        if (r16.write(r17) > 0) goto L_0x0339;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x033f, code lost:
        r17.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x034e, code lost:
        if ((r18.currentTimeMillis() - r20) <= (1000 * r22)) goto L_0x0389;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        r2 = java.lang.String.valueOf(java.lang.Long.toString(r22));
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 29).append("Timeout exceeded. Limit: ").append(r2).append(" sec").toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0384, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0385, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0386, code lost:
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x038d, code lost:
        if (r26.zzIk == false) goto L_0x039e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0398, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0399, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x039a, code lost:
        r3 = null;
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x03a2, code lost:
        if (r0.tryAcquire() == false) goto L_0x02fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03a4, code lost:
        zza(r27, r12.getAbsolutePath(), r5, r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03b2, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x03b3, code lost:
        r3 = null;
        r4 = "error";
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x03b8, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x03c0, code lost:
        if (com.google.android.gms.internal.zzpe.zzai(3) == false) goto L_0x03fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x03c2, code lost:
        r2 = zzIi.format((long) r5);
        com.google.android.gms.internal.zzpe.zzbc(new java.lang.StringBuilder((java.lang.String.valueOf(r2).length() + 22) + java.lang.String.valueOf(r27).length()).append("Preloaded ").append(r2).append(" bytes from ").append(r27).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x03fe, code lost:
        r12.setReadable(true, false);
        zzc(r13);
        zza(r27, r12.getAbsolutePath(), r5);
        zzIh.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0419, code lost:
        com.google.android.gms.internal.zzpe.zzc(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 25).append("Preload failed for URL \"").append(r27).append("\"").toString(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0443, code lost:
        r2 = new java.lang.String("Could not delete partial cache file at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0450, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0451, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0457, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0458, code lost:
        r3 = null;
        r4 = "error";
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e8, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r3 = com.google.android.gms.ads.internal.zzv.zzcW().zzb(r27, com.google.android.gms.internal.zzfx.zzBn.get().intValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0104, code lost:
        if ((r3 instanceof java.net.HttpURLConnection) == false) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0106, code lost:
        r2 = r3.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0110, code lost:
        if (r2 < 400) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0112, code lost:
        r4 = "badUrl";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0122, code lost:
        if (r3.length() == 0) goto L_0x01cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0124, code lost:
        r3 = "HTTP request failed. Code: ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0156, code lost:
        throw new java.io.IOException(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 32).append("HTTP status code ").append(r2).append(" at ").append(r27).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0157, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x015a, code lost:
        if ((r2 instanceof java.lang.RuntimeException) != false) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x015c, code lost:
        com.google.android.gms.ads.internal.zzv.zzcN().zza(r2, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x016c, code lost:
        if (r26.zzIk != false) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x016e, code lost:
        com.google.android.gms.internal.zzpe.zzbd(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 26).append("Preload aborted for URL \"").append(r27).append("\"").toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a2, code lost:
        r2 = java.lang.String.valueOf(r12.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01b0, code lost:
        if (r2.length() != 0) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b2, code lost:
        r2 = "Could not delete partial cache file at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b6, code lost:
        com.google.android.gms.internal.zzpe.zzbe(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b9, code lost:
        zza(r27, r12.getAbsolutePath(), r4, r3);
        zzIh.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r3 = new java.lang.String("HTTP request failed. Code: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01d3, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01d4, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r6 = r3.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01da, code lost:
        if (r6 >= 0) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01dc, code lost:
        r2 = java.lang.String.valueOf(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01e6, code lost:
        if (r2.length() == 0) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e8, code lost:
        r2 = "Stream cache aborted, missing content-length header at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01ec, code lost:
        com.google.android.gms.internal.zzpe.zzbe(r2);
        zza(r27, r12.getAbsolutePath(), "contentLengthMissing", null);
        zzIh.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0205, code lost:
        r2 = new java.lang.String("Stream cache aborted, missing content-length header at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x020b, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x020c, code lost:
        r3 = null;
        r4 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0210, code lost:
        r4 = zzIi.format((long) r6);
        r14 = com.google.android.gms.internal.zzfx.zzBi.get().intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0223, code lost:
        if (r6 <= r14) goto L_0x028a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0225, code lost:
        com.google.android.gms.internal.zzpe.zzbe(new java.lang.StringBuilder((java.lang.String.valueOf(r4).length() + 33) + java.lang.String.valueOf(r27).length()).append("Content length ").append(r4).append(" exceeds limit at ").append(r27).toString());
        r2 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0264, code lost:
        if (r2.length() == 0) goto L_0x027f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0266, code lost:
        r2 = "File too big for full file cache. Size: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x026a, code lost:
        zza(r27, r12.getAbsolutePath(), "sizeExceeded", r2);
        zzIh.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x027f, code lost:
        r2 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0285, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0286, code lost:
        r3 = null;
        r4 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x028a, code lost:
        com.google.android.gms.internal.zzpe.zzbc(new java.lang.StringBuilder((java.lang.String.valueOf(r4).length() + 20) + java.lang.String.valueOf(r27).length()).append("Caching ").append(r4).append(" bytes from ").append(r27).toString());
        r15 = java.nio.channels.Channels.newChannel(r3.getInputStream());
        r11 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r16 = r11.getChannel();
        r17 = java.nio.ByteBuffer.allocate(1048576);
        r18 = com.google.android.gms.ads.internal.zzv.zzcP();
        r5 = 0;
        r20 = r18.currentTimeMillis();
        r0 = new com.google.android.gms.internal.zzpt(com.google.android.gms.internal.zzfx.zzBm.get().longValue());
        r22 = com.google.android.gms.internal.zzfx.zzBl.get().longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02fe, code lost:
        r2 = r15.read(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0304, code lost:
        if (r2 < 0) goto L_0x03b8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0419  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0443  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01b2  */
    public boolean zzad(String str) {
        if (this.zzIj == null) {
            zza(str, null, "noCacheDir", null);
            return false;
        }
        while (zzgg() > zzfx.zzBh.get().intValue()) {
            if (!zzgh()) {
                zzpe.zzbe("Unable to expire stream cache");
                zza(str, null, "expireFailed", null);
                return false;
            }
        }
        File file = new File(this.zzIj, zzae(str));
        File zzb = zzb(file);
        if (!file.isFile() || !zzb.isFile()) {
            String valueOf = String.valueOf(this.zzIj.getAbsolutePath());
            String valueOf2 = String.valueOf(str);
            String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            synchronized (zzIh) {
                if (zzIh.contains(concat)) {
                    String valueOf3 = String.valueOf(str);
                    zzpe.zzbe(valueOf3.length() != 0 ? "Stream cache already in progress at ".concat(valueOf3) : new String("Stream cache already in progress at "));
                    zza(str, file.getAbsolutePath(), "inProgress", null);
                    return false;
                }
                zzIh.add(concat);
            }
        } else {
            int length = (int) file.length();
            String valueOf4 = String.valueOf(str);
            zzpe.zzbc(valueOf4.length() != 0 ? "Stream cache hit at ".concat(valueOf4) : new String("Stream cache hit at "));
            zza(str, file.getAbsolutePath(), length);
            return true;
        }
    }

    public int zzgg() {
        int i = 0;
        if (this.zzIj != null) {
            for (File name : this.zzIj.listFiles()) {
                if (!name.getName().endsWith(".done")) {
                    i++;
                }
            }
        }
        return i;
    }

    public boolean zzgh() {
        boolean z;
        long j;
        File file;
        if (this.zzIj == null) {
            return false;
        }
        File file2 = null;
        long j2 = Long.MAX_VALUE;
        File[] listFiles = this.zzIj.listFiles();
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file3 = listFiles[i];
            if (!file3.getName().endsWith(".done")) {
                j = file3.lastModified();
                if (j < j2) {
                    file = file3;
                    i++;
                    file2 = file;
                    j2 = j;
                }
            }
            j = j2;
            file = file2;
            i++;
            file2 = file;
            j2 = j;
        }
        if (file2 != null) {
            z = file2.delete();
            File zzb = zzb(file2);
            if (zzb.isFile()) {
                z &= zzb.delete();
            }
        } else {
            z = false;
        }
        return z;
    }
}
