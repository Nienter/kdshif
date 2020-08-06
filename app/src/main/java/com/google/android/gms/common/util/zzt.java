package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class zzt {
    private static String zzaGZ = null;
    private static final int zzaHa = Process.myPid();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    static String zzdk(int i) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        String str = null;
        if (i > 0) {
            try {
                allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                BufferedReader bufferedReader3 = new BufferedReader(new FileReader(new StringBuilder(25).append("/proc/").append(i).append("/cmdline").toString()));
                try {
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    str = bufferedReader3.readLine().trim();
                    zzo.zzb(bufferedReader3);
                } catch (IOException e) {
                    bufferedReader2 = bufferedReader3;
                    zzo.zzb(bufferedReader2);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader3;
                    zzo.zzb(bufferedReader);
                    throw th;
                }
            } catch (IOException e2) {
                bufferedReader2 = str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = str;
                th = th4;
                zzo.zzb(bufferedReader);
                throw th;
            }
        }
        return str;
    }

    public static String zzyK() {
        if (zzaGZ == null) {
            zzaGZ = zzdk(zzaHa);
        }
        return zzaGZ;
    }
}
