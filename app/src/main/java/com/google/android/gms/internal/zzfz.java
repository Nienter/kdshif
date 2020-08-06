package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzv;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzmb
public class zzfz {
    final Context mContext;
    String zzFb;
    BlockingQueue<zzgf> zzFd;
    ExecutorService zzFe;
    LinkedHashMap<String, String> zzFf = new LinkedHashMap<>();
    Map<String, zzgc> zzFg = new HashMap();
    private AtomicBoolean zzFh;
    private File zzFi;
    final String zzvU;

    public zzfz(Context context, String str, String str2, Map<String, String> map) {
        this.mContext = context;
        this.zzvU = str;
        this.zzFb = str2;
        this.zzFh = new AtomicBoolean(false);
        this.zzFh.set(zzfx.zzBM.get().booleanValue());
        if (this.zzFh.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzFi = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Map.Entry next : map.entrySet()) {
            this.zzFf.put((String) next.getKey(), (String) next.getValue());
        }
        this.zzFd = new ArrayBlockingQueue(30);
        this.zzFe = Executors.newSingleThreadExecutor();
        this.zzFe.execute(new Runnable() {
            public void run() {
                zzfz.this.zzfs();
            }
        });
        this.zzFg.put(NativeProtocol.WEB_DIALOG_ACTION, zzgc.zzFl);
        this.zzFg.put("ad_format", zzgc.zzFl);
        this.zzFg.put("e", zzgc.zzFm);
    }

    private void zzb(Map<String, String> map, String str) {
        String zza = zza(this.zzFb, map, str);
        if (this.zzFh.get()) {
            zzc(this.zzFi, zza);
        } else {
            zzv.zzcJ().zzc(this.mContext, this.zzvU, zza);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029 A[SYNTHETIC, Splitter:B:16:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0038 A[SYNTHETIC, Splitter:B:23:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    private void zzc(@Nullable File file, String str) {
        FileOutputStream fileOutputStream;
        if (file != null) {
            try {
                fileOutputStream = new FileOutputStream(file, true);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.write(10);
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        zzpe.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        zzpe.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e);
                        if (fileOutputStream == null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                zzpe.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e3);
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e = e4;
                fileOutputStream = null;
                zzpe.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e);
                if (fileOutputStream == null) {
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e5) {
                        zzpe.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e5);
                    }
                }
                throw th;
            }
        } else {
            zzpe.zzbe("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
        }
    }

    /* access modifiers changed from: private */
    public void zzfs() {
        while (true) {
            try {
                zzgf take = this.zzFd.take();
                String zzfy = take.zzfy();
                if (!TextUtils.isEmpty(zzfy)) {
                    zzb(zza(this.zzFf, take.zzfz()), zzfy);
                }
            } catch (InterruptedException e) {
                zzpe.zzc("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }

    public zzgc zzV(String str) {
        zzgc zzgc = this.zzFg.get(str);
        return zzgc != null ? zzgc : zzgc.zzFk;
    }

    /* access modifiers changed from: package-private */
    public String zza(String str, Map<String, String> map, @NonNull String str2) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        for (Map.Entry next : map.entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        StringBuilder sb = new StringBuilder(buildUpon.build().toString());
        sb.append("&").append("it").append("=").append(str2);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Map.Entry next : map2.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzV(str).zzf(str2, (String) next.getValue()));
        }
        return linkedHashMap;
    }

    public boolean zza(zzgf zzgf) {
        return this.zzFd.offer(zzgf);
    }

    public void zzc(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzFf.put("e", TextUtils.join(",", list));
        }
    }
}
