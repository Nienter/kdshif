package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.p001v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzasu;
import com.google.android.gms.internal.zzati;
import com.google.android.gms.internal.zzatj;
import com.google.android.gms.internal.zzaug;
import com.google.android.gms.internal.zzauh;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

public class zzatp {
    private static volatile zzatp zzbtc;
    private final Context mContext;
    private final boolean zzacO;
    private long zzbtA;
    private FileLock zzbtB;
    private FileChannel zzbtC;
    private List<Long> zzbtD;
    private int zzbtE;
    private int zzbtF;
    private long zzbtG = -1;
    private final zzast zzbtd;
    private final zzatl zzbte;
    private final zzati zzbtf;
    private final zzato zzbtg;
    private final zzaty zzbth;
    private final zzatn zzbti;
    private final AppMeasurement zzbtj;
    private final FirebaseAnalytics zzbtk;
    private final zzaue zzbtl;
    private final zzasu zzbtm;
    private final zzatg zzbtn;
    private final zzatj zzbto;
    private final zzatv zzbtp;
    private final zzatw zzbtq;
    private final zzasw zzbtr;
    private final zzatu zzbts;
    private final zzatf zzbtt;
    private final zzatk zzbtu;
    private final zzaua zzbtv;
    private final zzass zzbtw;
    private final zzaso zzbtx;
    private boolean zzbty;
    private Boolean zzbtz;
    private final zze zzuI;

    private class zza implements zzasu.zzb {
        zzauh.zze zzbtI;
        List<Long> zzbtJ;
        long zzbtK;
        List<zzauh.zzb> zztf;

        private zza() {
        }

        private long zza(zzauh.zzb zzb) {
            return ((zzb.zzbvW.longValue() / 1000) / 60) / 60;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return this.zztf == null || this.zztf.isEmpty();
        }

        public boolean zza(long j, zzauh.zzb zzb) {
            zzac.zzw(zzb);
            if (this.zztf == null) {
                this.zztf = new ArrayList();
            }
            if (this.zzbtJ == null) {
                this.zzbtJ = new ArrayList();
            }
            if (this.zztf.size() > 0 && zza(this.zztf.get(0)) != zza(zzb)) {
                return false;
            }
            long zzacZ = this.zzbtK + ((long) zzb.zzacZ());
            if (zzacZ >= ((long) zzatp.this.zzJv().zzKr())) {
                return false;
            }
            this.zzbtK = zzacZ;
            this.zztf.add(zzb);
            this.zzbtJ.add(Long.valueOf(j));
            return this.zztf.size() < zzatp.this.zzJv().zzKs();
        }

        public void zzb(zzauh.zze zze) {
            zzac.zzw(zze);
            this.zzbtI = zze;
        }
    }

    zzatp(zzatt zzatt) {
        zzac.zzw(zzatt);
        this.mContext = zzatt.mContext;
        this.zzuI = zzatt.zzn(this);
        this.zzbtd = zzatt.zza(this);
        zzatl zzb = zzatt.zzb(this);
        zzb.initialize();
        this.zzbte = zzb;
        zzati zzc = zzatt.zzc(this);
        zzc.initialize();
        this.zzbtf = zzc;
        zzJt().zzLe().zzj("App measurement is starting up, version", Long.valueOf(zzJv().zzJD()));
        zzJt().zzLe().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzJt().zzLf().log("Debug-level message logging enabled");
        zzJt().zzLf().zzj("AppMeasurement singleton hash", Integer.valueOf(System.identityHashCode(this)));
        zzaue zzj = zzatt.zzj(this);
        zzj.initialize();
        this.zzbtl = zzj;
        zzasw zzq = zzatt.zzq(this);
        zzq.initialize();
        this.zzbtr = zzq;
        zzatf zzr = zzatt.zzr(this);
        zzr.initialize();
        this.zzbtt = zzr;
        zzJv().zzKk();
        String zzjI = zzr.zzjI();
        if (zzJp().zzgh(zzjI)) {
            zzJt().zzLe().log("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
        } else {
            zzati.zza zzLe = zzJt().zzLe();
            String valueOf = String.valueOf(zzjI);
            zzLe.log(valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app "));
        }
        zzasu zzk = zzatt.zzk(this);
        zzk.initialize();
        this.zzbtm = zzk;
        zzatg zzl = zzatt.zzl(this);
        zzl.initialize();
        this.zzbtn = zzl;
        zzass zzu = zzatt.zzu(this);
        zzu.initialize();
        this.zzbtw = zzu;
        this.zzbtx = zzatt.zzv(this);
        zzatj zzm = zzatt.zzm(this);
        zzm.initialize();
        this.zzbto = zzm;
        zzatv zzo = zzatt.zzo(this);
        zzo.initialize();
        this.zzbtp = zzo;
        zzatw zzp = zzatt.zzp(this);
        zzp.initialize();
        this.zzbtq = zzp;
        zzatu zzi = zzatt.zzi(this);
        zzi.initialize();
        this.zzbts = zzi;
        zzaua zzt = zzatt.zzt(this);
        zzt.initialize();
        this.zzbtv = zzt;
        this.zzbtu = zzatt.zzs(this);
        this.zzbtj = zzatt.zzh(this);
        this.zzbtk = zzatt.zzg(this);
        zzaty zze = zzatt.zze(this);
        zze.initialize();
        this.zzbth = zze;
        zzatn zzf = zzatt.zzf(this);
        zzf.initialize();
        this.zzbti = zzf;
        zzato zzd = zzatt.zzd(this);
        zzd.initialize();
        this.zzbtg = zzd;
        if (this.zzbtE != this.zzbtF) {
            zzJt().zzLa().zze("Not all components initialized", Integer.valueOf(this.zzbtE), Integer.valueOf(this.zzbtF));
        }
        this.zzacO = true;
        this.zzbtd.zzKk();
        if (!(this.mContext.getApplicationContext() instanceof Application)) {
            zzJt().zzLc().log("Application context is not an Application");
        } else if (Build.VERSION.SDK_INT >= 14) {
            zzJi().zzLQ();
        } else {
            zzJt().zzLf().log("Not tracking deep linking pre-ICS");
        }
        this.zzbtg.zzm(new Runnable() {
            public void run() {
                zzatp.this.start();
            }
        });
    }

    private boolean zzLH() {
        zzmq();
        zznA();
        return zzJo().zzKM() || !TextUtils.isEmpty(zzJo().zzKG());
    }

    @WorkerThread
    private void zzLI() {
        zzmq();
        zznA();
        if (zzLM()) {
            if (!zzLt() || !zzLH()) {
                zzLz().unregister();
                zzLA().cancel();
                return;
            }
            long zzLJ = zzLJ();
            if (zzLJ == 0) {
                zzLz().unregister();
                zzLA().cancel();
            } else if (!zzLy().zzpA()) {
                zzLz().zzpx();
                zzLA().cancel();
            } else {
                long j = zzJu().zzbsi.get();
                long zzKw = zzJv().zzKw();
                if (!zzJp().zzf(j, zzKw)) {
                    zzLJ = Math.max(zzLJ, j + zzKw);
                }
                zzLz().unregister();
                long currentTimeMillis = zzLJ - zznq().currentTimeMillis();
                if (currentTimeMillis <= 0) {
                    currentTimeMillis = zzJv().zzKz();
                    zzJu().zzbsg.set(zznq().currentTimeMillis());
                }
                zzJt().zzLg().zzj("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis));
                zzLA().zzx(currentTimeMillis);
            }
        }
    }

    private long zzLJ() {
        long currentTimeMillis = zznq().currentTimeMillis();
        long zzKC = zzJv().zzKC();
        boolean z = zzJo().zzKN() || zzJo().zzKH();
        long zzKy = z ? zzJv().zzKy() : zzJv().zzKx();
        long j = zzJu().zzbsg.get();
        long j2 = zzJu().zzbsh.get();
        long max = Math.max(zzJo().zzKK(), zzJo().zzKL());
        if (max == 0) {
            return 0;
        }
        long abs = currentTimeMillis - Math.abs(max - currentTimeMillis);
        long abs2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
        long max2 = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), abs2);
        long j3 = abs + zzKC;
        if (z && max2 > 0) {
            j3 = Math.min(abs, max2) + zzKy;
        }
        if (!zzJp().zzf(max2, zzKy)) {
            j3 = max2 + zzKy;
        }
        if (abs2 == 0 || abs2 < abs) {
            return j3;
        }
        for (int i = 0; i < zzJv().zzKE(); i++) {
            j3 += ((long) (1 << i)) * zzJv().zzKD();
            if (j3 > abs2) {
                return j3;
            }
        }
        return 0;
    }

    private void zza(zzatr zzatr) {
        if (zzatr == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private void zza(zzats zzats) {
        if (zzats == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzats.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    private boolean zza(zzasx zzasx) {
        if (zzasx.zzbqI == null) {
            return false;
        }
        Iterator<String> it = zzasx.zzbqI.iterator();
        while (it.hasNext()) {
            if ("_r".equals(it.next())) {
                return true;
            }
        }
        return zzJq().zzY(zzasx.zzVQ, zzasx.mName) && zzJo().zza(zzLE(), zzasx.zzVQ, false, false, false, false, false).zzbqz < ((long) zzJv().zzfp(zzasx.zzVQ));
    }

    private zzauh.zza[] zza(String str, zzauh.zzg[] zzgArr, zzauh.zzb[] zzbArr) {
        zzac.zzdv(str);
        return zzJh().zza(str, zzbArr, zzgArr);
    }

    public static zzatp zzbu(Context context) {
        zzac.zzw(context);
        zzac.zzw(context.getApplicationContext());
        if (zzbtc == null) {
            synchronized (zzatp.class) {
                if (zzbtc == null) {
                    zzbtc = new zzatt(context).zzLP();
                }
            }
        }
        return zzbtc;
    }

    @WorkerThread
    private void zzf(zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzw(zzasq);
        zzac.zzdv(zzasq.packageName);
        zzasp zzfy = zzJo().zzfy(zzasq.packageName);
        String zzfL = zzJu().zzfL(zzasq.packageName);
        boolean z = false;
        if (zzfy == null) {
            zzasp zzasp = new zzasp(this, zzasq.packageName);
            zzasp.zzfh(zzJu().zzLj());
            zzasp.zzfj(zzfL);
            zzfy = zzasp;
            z = true;
        } else if (!zzfL.equals(zzfy.zzJx())) {
            zzfy.zzfj(zzfL);
            zzfy.zzfh(zzJu().zzLj());
            z = true;
        }
        if (!TextUtils.isEmpty(zzasq.zzbqf) && !zzasq.zzbqf.equals(zzfy.getGmpAppId())) {
            zzfy.zzfi(zzasq.zzbqf);
            z = true;
        }
        if (!TextUtils.isEmpty(zzasq.zzbqn) && !zzasq.zzbqn.equals(zzfy.zzJy())) {
            zzfy.zzfk(zzasq.zzbqn);
            z = true;
        }
        if (!(zzasq.zzbqh == 0 || zzasq.zzbqh == zzfy.zzJD())) {
            zzfy.zzaa(zzasq.zzbqh);
            z = true;
        }
        if (!TextUtils.isEmpty(zzasq.zzbhg) && !zzasq.zzbhg.equals(zzfy.zzmy())) {
            zzfy.setAppVersion(zzasq.zzbhg);
            z = true;
        }
        if (zzasq.zzbqm != zzfy.zzJB()) {
            zzfy.zzZ(zzasq.zzbqm);
            z = true;
        }
        if (!TextUtils.isEmpty(zzasq.zzbqg) && !zzasq.zzbqg.equals(zzfy.zzJC())) {
            zzfy.zzfl(zzasq.zzbqg);
            z = true;
        }
        if (zzasq.zzbqi != zzfy.zzJE()) {
            zzfy.zzab(zzasq.zzbqi);
            z = true;
        }
        if (zzasq.zzbqk != zzfy.zzJF()) {
            zzfy.setMeasurementEnabled(zzasq.zzbqk);
            z = true;
        }
        if (!TextUtils.isEmpty(zzasq.zzbqj) && !zzasq.zzbqj.equals(zzfy.zzJQ())) {
            zzfy.zzfm(zzasq.zzbqj);
            z = true;
        }
        if (z) {
            zzJo().zza(zzfy);
        }
    }

    private boolean zzj(String str, long j) {
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        boolean z4;
        zzJo().beginTransaction();
        try {
            zza zza2 = new zza();
            zzJo().zza(str, j, this.zzbtG, zza2);
            if (!zza2.isEmpty()) {
                boolean z5 = false;
                zzauh.zze zze = zza2.zzbtI;
                zze.zzbwd = new zzauh.zzb[zza2.zztf.size()];
                int i3 = 0;
                int i4 = 0;
                while (i4 < zza2.zztf.size()) {
                    if (zzJq().zzX(zza2.zzbtI.zzaR, zza2.zztf.get(i4).name)) {
                        zzJt().zzLc().zze("Dropping blacklisted raw event. appId", zzati.zzfI(str), zza2.zztf.get(i4).name);
                        if ((zzJp().zzgj(zza2.zzbtI.zzaR) || zzJp().zzgk(zza2.zzbtI.zzaR)) || "_err".equals(zza2.zztf.get(i4).name)) {
                            i = i3;
                            z2 = z5;
                        } else {
                            zzJp().zza(11, "_ev", zza2.zztf.get(i4).name, 0);
                            i = i3;
                            z2 = z5;
                        }
                    } else {
                        if (zzJq().zzY(zza2.zzbtI.zzaR, zza2.zztf.get(i4).name)) {
                            boolean z6 = false;
                            boolean z7 = false;
                            if (zza2.zztf.get(i4).zzbvV == null) {
                                zza2.zztf.get(i4).zzbvV = new zzauh.zzc[0];
                            }
                            zzauh.zzc[] zzcArr = zza2.zztf.get(i4).zzbvV;
                            int length = zzcArr.length;
                            int i5 = 0;
                            while (i5 < length) {
                                zzauh.zzc zzc = zzcArr[i5];
                                if ("_c".equals(zzc.name)) {
                                    zzc.zzbvZ = 1L;
                                    z6 = true;
                                    z4 = z7;
                                } else if ("_r".equals(zzc.name)) {
                                    zzc.zzbvZ = 1L;
                                    z4 = true;
                                } else {
                                    z4 = z7;
                                }
                                i5++;
                                z7 = z4;
                            }
                            if (!z6) {
                                zzJt().zzLg().zzj("Marking event as conversion", zza2.zztf.get(i4).name);
                                zzauh.zzc[] zzcArr2 = (zzauh.zzc[]) Arrays.copyOf(zza2.zztf.get(i4).zzbvV, zza2.zztf.get(i4).zzbvV.length + 1);
                                zzauh.zzc zzc2 = new zzauh.zzc();
                                zzc2.name = "_c";
                                zzc2.zzbvZ = 1L;
                                zzcArr2[zzcArr2.length - 1] = zzc2;
                                zza2.zztf.get(i4).zzbvV = zzcArr2;
                            }
                            if (!z7) {
                                zzJt().zzLg().zzj("Marking event as real-time", zza2.zztf.get(i4).name);
                                zzauh.zzc[] zzcArr3 = (zzauh.zzc[]) Arrays.copyOf(zza2.zztf.get(i4).zzbvV, zza2.zztf.get(i4).zzbvV.length + 1);
                                zzauh.zzc zzc3 = new zzauh.zzc();
                                zzc3.name = "_r";
                                zzc3.zzbvZ = 1L;
                                zzcArr3[zzcArr3.length - 1] = zzc3;
                                zza2.zztf.get(i4).zzbvV = zzcArr3;
                            }
                            boolean z8 = true;
                            boolean zzfW = zzaue.zzfW(zza2.zztf.get(i4).name);
                            if (zzJo().zza(zzLE(), zza2.zzbtI.zzaR, false, false, false, false, true).zzbqz > ((long) zzJv().zzfp(zza2.zzbtI.zzaR))) {
                                zzauh.zzb zzb = zza2.zztf.get(i4);
                                int i6 = 0;
                                while (true) {
                                    if (i6 >= zzb.zzbvV.length) {
                                        break;
                                    } else if ("_r".equals(zzb.zzbvV[i6].name)) {
                                        zzauh.zzc[] zzcArr4 = new zzauh.zzc[(zzb.zzbvV.length - 1)];
                                        if (i6 > 0) {
                                            System.arraycopy(zzb.zzbvV, 0, zzcArr4, 0, i6);
                                        }
                                        if (i6 < zzcArr4.length) {
                                            System.arraycopy(zzb.zzbvV, i6 + 1, zzcArr4, i6, zzcArr4.length - i6);
                                        }
                                        zzb.zzbvV = zzcArr4;
                                    } else {
                                        i6++;
                                    }
                                }
                                z8 = z5;
                            }
                            if (zzfW && zzJo().zza(zzLE(), zza2.zzbtI.zzaR, false, false, true, false, false).zzbqx > ((long) zzJv().zzfo(zza2.zzbtI.zzaR))) {
                                zzJt().zzLc().zzj("Too many conversions. Not logging as conversion. appId", zzati.zzfI(str));
                                zzauh.zzb zzb2 = zza2.zztf.get(i4);
                                boolean z9 = false;
                                zzauh.zzc zzc4 = null;
                                zzauh.zzc[] zzcArr5 = zzb2.zzbvV;
                                int length2 = zzcArr5.length;
                                int i7 = 0;
                                while (i7 < length2) {
                                    zzauh.zzc zzc5 = zzcArr5[i7];
                                    if ("_c".equals(zzc5.name)) {
                                        z3 = z9;
                                    } else if ("_err".equals(zzc5.name)) {
                                        zzauh.zzc zzc6 = zzc4;
                                        z3 = true;
                                        zzc5 = zzc6;
                                    } else {
                                        zzc5 = zzc4;
                                        z3 = z9;
                                    }
                                    i7++;
                                    z9 = z3;
                                    zzc4 = zzc5;
                                }
                                if (z9 && zzc4 != null) {
                                    zzauh.zzc[] zzcArr6 = new zzauh.zzc[(zzb2.zzbvV.length - 1)];
                                    int i8 = 0;
                                    zzauh.zzc[] zzcArr7 = zzb2.zzbvV;
                                    int length3 = zzcArr7.length;
                                    int i9 = 0;
                                    while (i9 < length3) {
                                        zzauh.zzc zzc7 = zzcArr7[i9];
                                        if (zzc7 != zzc4) {
                                            i2 = i8 + 1;
                                            zzcArr6[i8] = zzc7;
                                        } else {
                                            i2 = i8;
                                        }
                                        i9++;
                                        i8 = i2;
                                    }
                                    zzb2.zzbvV = zzcArr6;
                                    z = z8;
                                } else if (zzc4 != null) {
                                    zzc4.name = "_err";
                                    zzc4.zzbvZ = 10L;
                                    z = z8;
                                } else {
                                    zzJt().zzLa().zzj("Did not find conversion parameter. appId", zzati.zzfI(str));
                                }
                            }
                            z = z8;
                        } else {
                            z = z5;
                        }
                        zze.zzbwd[i3] = zza2.zztf.get(i4);
                        i = i3 + 1;
                        z2 = z;
                    }
                    i4++;
                    i3 = i;
                    z5 = z2;
                }
                if (i3 < zza2.zztf.size()) {
                    zze.zzbwd = (zzauh.zzb[]) Arrays.copyOf(zze.zzbwd, i3);
                }
                zze.zzbww = zza(zza2.zzbtI.zzaR, zza2.zzbtI.zzbwe, zze.zzbwd);
                zze.zzbwg = Long.MAX_VALUE;
                zze.zzbwh = Long.MIN_VALUE;
                for (zzauh.zzb zzb3 : zze.zzbwd) {
                    if (zzb3.zzbvW.longValue() < zze.zzbwg.longValue()) {
                        zze.zzbwg = zzb3.zzbvW;
                    }
                    if (zzb3.zzbvW.longValue() > zze.zzbwh.longValue()) {
                        zze.zzbwh = zzb3.zzbvW;
                    }
                }
                String str2 = zza2.zzbtI.zzaR;
                zzasp zzfy = zzJo().zzfy(str2);
                if (zzfy == null) {
                    zzJt().zzLa().zzj("Bundling raw events w/o app info. appId", zzati.zzfI(str));
                } else if (zze.zzbwd.length > 0) {
                    long zzJA = zzfy.zzJA();
                    zze.zzbwj = zzJA != 0 ? Long.valueOf(zzJA) : null;
                    long zzJz = zzfy.zzJz();
                    if (zzJz != 0) {
                        zzJA = zzJz;
                    }
                    zze.zzbwi = zzJA != 0 ? Long.valueOf(zzJA) : null;
                    zzfy.zzJJ();
                    zze.zzbwu = Integer.valueOf((int) zzfy.zzJG());
                    zzfy.zzX(zze.zzbwg.longValue());
                    zzfy.zzY(zze.zzbwh.longValue());
                    zze.zzbqj = zzfy.zzJR();
                    zzJo().zza(zzfy);
                }
                if (zze.zzbwd.length > 0) {
                    zzJv().zzKk();
                    zzaug.zzb zzfO = zzJq().zzfO(zza2.zzbtI.zzaR);
                    if (zzfO == null || zzfO.zzbvK == null) {
                        zzJt().zzLc().zzj("Did not find measurement config or missing version info. appId", zzati.zzfI(str));
                    } else {
                        zze.zzbwB = zzfO.zzbvK;
                    }
                    zzJo().zza(zze, z5);
                }
                zzJo().zzG(zza2.zzbtJ);
                zzJo().zzfF(str2);
                zzJo().setTransactionSuccessful();
                return zze.zzbwd.length > 0;
            }
            zzJo().setTransactionSuccessful();
            zzJo().endTransaction();
            return false;
        } finally {
            zzJo().endTransaction();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getGmpAppIdOnPackageSide(final String str) {
        zzJd();
        try {
            return (String) zzJs().zze(new Callable<String>() {
                /* renamed from: zzou */
                public String call() {
                    zzasp zzfy = zzatp.this.zzJo().zzfy(str);
                    if (zzfy == null) {
                        return null;
                    }
                    return zzfy.getGmpAppId();
                }
            }).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzJt().zzLa().zze("Failed to get gmp app id. appId", zzati.zzfI(str), e);
            return null;
        }
    }

    @WorkerThread
    public boolean isEnabled() {
        boolean z = false;
        zzmq();
        zznA();
        if (zzJv().zzKl()) {
            return false;
        }
        Boolean zzKm = zzJv().zzKm();
        if (zzKm != null) {
            z = zzKm.booleanValue();
        } else if (!zzJv().zzwk()) {
            z = true;
        }
        return zzJu().zzaG(z);
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void start() {
        zzmq();
        zzJo().zzKI();
        if (zzJu().zzbsg.get() == 0) {
            zzJu().zzbsg.set(zznq().currentTimeMillis());
        }
        if (zzLt()) {
            zzJv().zzKk();
            if (!TextUtils.isEmpty(zzJj().getGmpAppId())) {
                String zzLm = zzJu().zzLm();
                if (zzLm == null) {
                    zzJu().zzfM(zzJj().getGmpAppId());
                } else if (!zzLm.equals(zzJj().getGmpAppId())) {
                    zzJt().zzLe().log("Rechecking which service to use due to a GMP App Id change");
                    zzJu().zzLo();
                    this.zzbtq.disconnect();
                    this.zzbtq.zzoc();
                    zzJu().zzfM(zzJj().getGmpAppId());
                }
            }
            zzJv().zzKk();
            if (!TextUtils.isEmpty(zzJj().getGmpAppId())) {
                zzJi().zzLR();
            }
        } else if (isEnabled()) {
            if (!zzJp().zzbV("android.permission.INTERNET")) {
                zzJt().zzLa().log("App is missing INTERNET permission");
            }
            if (!zzJp().zzbV("android.permission.ACCESS_NETWORK_STATE")) {
                zzJt().zzLa().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzJv().zzKk();
            zzacx.zzaQ(getContext());
            if (!zzatm.zzi(getContext(), false)) {
                zzJt().zzLa().log("AppMeasurementReceiver not registered/enabled");
            }
            if (!zzatx.zzj(getContext(), false)) {
                zzJt().zzLa().log("AppMeasurementService not registered/enabled");
            }
            zzJt().zzLa().log("Uploading is not possible. App measurement disabled");
        }
        zzLI();
    }

    /* access modifiers changed from: protected */
    public void zzH(List<Long> list) {
        zzac.zzas(!list.isEmpty());
        if (this.zzbtD != null) {
            zzJt().zzLa().log("Set uploading progress before finishing the previous upload");
        } else {
            this.zzbtD = new ArrayList(list);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzJd() {
        zzJv().zzKk();
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public void zzJe() {
        zzJv().zzKk();
    }

    public zzaso zzJg() {
        zza((zzatr) this.zzbtx);
        return this.zzbtx;
    }

    public zzass zzJh() {
        zza((zzats) this.zzbtw);
        return this.zzbtw;
    }

    public zzatu zzJi() {
        zza((zzats) this.zzbts);
        return this.zzbts;
    }

    public zzatf zzJj() {
        zza((zzats) this.zzbtt);
        return this.zzbtt;
    }

    public zzasw zzJk() {
        zza((zzats) this.zzbtr);
        return this.zzbtr;
    }

    public zzatw zzJl() {
        zza((zzats) this.zzbtq);
        return this.zzbtq;
    }

    public zzatv zzJm() {
        zza((zzats) this.zzbtp);
        return this.zzbtp;
    }

    public zzatg zzJn() {
        zza((zzats) this.zzbtn);
        return this.zzbtn;
    }

    public zzasu zzJo() {
        zza((zzats) this.zzbtm);
        return this.zzbtm;
    }

    public zzaue zzJp() {
        zza((zzatr) this.zzbtl);
        return this.zzbtl;
    }

    public zzatn zzJq() {
        zza((zzats) this.zzbti);
        return this.zzbti;
    }

    public zzaty zzJr() {
        zza((zzats) this.zzbth);
        return this.zzbth;
    }

    public zzato zzJs() {
        zza((zzats) this.zzbtg);
        return this.zzbtg;
    }

    public zzati zzJt() {
        zza((zzats) this.zzbtf);
        return this.zzbtf;
    }

    public zzatl zzJu() {
        zza((zzatr) this.zzbte);
        return this.zzbte;
    }

    public zzast zzJv() {
        return this.zzbtd;
    }

    public zzaua zzLA() {
        zza((zzats) this.zzbtv);
        return this.zzbtv;
    }

    /* access modifiers changed from: package-private */
    public FileChannel zzLB() {
        return this.zzbtC;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzLC() {
        zzmq();
        zznA();
        if (zzLM() && zzLD()) {
            zzv(zza(zzLB()), zzJj().zzKZ());
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzLD() {
        zzmq();
        try {
            this.zzbtC = new RandomAccessFile(new File(getContext().getFilesDir(), this.zzbtm.zznV()), "rw").getChannel();
            this.zzbtB = this.zzbtC.tryLock();
            if (this.zzbtB != null) {
                zzJt().zzLg().log("Storage concurrent access okay");
                return true;
            }
            zzJt().zzLa().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzJt().zzLa().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzJt().zzLa().zzj("Failed to access storage lock file", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public long zzLE() {
        return ((((zznq().currentTimeMillis() + zzJu().zzLk()) / 1000) / 60) / 60) / 24;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzLF() {
        zzmq();
        return this.zzbtD != null;
    }

    @WorkerThread
    public void zzLG() {
        String str;
        List<Pair<zzauh.zze, Long>> list;
        zzmq();
        zznA();
        zzJv().zzKk();
        Boolean zzLn = zzJu().zzLn();
        if (zzLn == null) {
            zzJt().zzLc().log("Upload data called on the client side before use of service was decided");
        } else if (zzLn.booleanValue()) {
            zzJt().zzLa().log("Upload called in the client side when service should be used");
        } else if (zzLF()) {
            zzJt().zzLc().log("Uploading requested multiple times");
        } else if (!zzLy().zzpA()) {
            zzJt().zzLc().log("Network not connected, ignoring upload request");
            zzLI();
        } else {
            long currentTimeMillis = zznq().currentTimeMillis();
            zzao(currentTimeMillis - zzJv().zzKv());
            long j = zzJu().zzbsg.get();
            if (j != 0) {
                zzJt().zzLf().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
            }
            String zzKG = zzJo().zzKG();
            if (!TextUtils.isEmpty(zzKG)) {
                if (this.zzbtG == -1) {
                    this.zzbtG = zzJo().zzKO();
                }
                List<Pair<zzauh.zze, Long>> zzn = zzJo().zzn(zzKG, zzJv().zzfu(zzKG), zzJv().zzfv(zzKG));
                if (!zzn.isEmpty()) {
                    Iterator<Pair<zzauh.zze, Long>> it = zzn.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str = null;
                            break;
                        }
                        zzauh.zze zze = (zzauh.zze) it.next().first;
                        if (!TextUtils.isEmpty(zze.zzbwq)) {
                            str = zze.zzbwq;
                            break;
                        }
                    }
                    if (str != null) {
                        int i = 0;
                        while (true) {
                            if (i >= zzn.size()) {
                                break;
                            }
                            zzauh.zze zze2 = (zzauh.zze) zzn.get(i).first;
                            if (!TextUtils.isEmpty(zze2.zzbwq) && !zze2.zzbwq.equals(str)) {
                                list = zzn.subList(0, i);
                                break;
                            }
                            i++;
                        }
                    }
                    list = zzn;
                    zzauh.zzd zzd = new zzauh.zzd();
                    zzd.zzbwa = new zzauh.zze[list.size()];
                    ArrayList arrayList = new ArrayList(list.size());
                    for (int i2 = 0; i2 < zzd.zzbwa.length; i2++) {
                        zzd.zzbwa[i2] = (zzauh.zze) list.get(i2).first;
                        arrayList.add((Long) list.get(i2).second);
                        zzd.zzbwa[i2].zzbwp = Long.valueOf(zzJv().zzJD());
                        zzd.zzbwa[i2].zzbwf = Long.valueOf(currentTimeMillis);
                        zzd.zzbwa[i2].zzbwv = Boolean.valueOf(zzJv().zzKk());
                    }
                    String zzb = zzJt().zzai(2) ? zzaue.zzb(zzd) : null;
                    byte[] zza2 = zzJp().zza(zzd);
                    String zzKu = zzJv().zzKu();
                    try {
                        URL url = new URL(zzKu);
                        zzH(arrayList);
                        zzJu().zzbsh.set(currentTimeMillis);
                        String str2 = "?";
                        if (zzd.zzbwa.length > 0) {
                            str2 = zzd.zzbwa[0].zzaR;
                        }
                        zzJt().zzLg().zzd("Uploading data. app, uncompressed size, data", str2, Integer.valueOf(zza2.length), zzb);
                        zzLy().zza(zzKG, url, zza2, null, new zzatj.zza() {
                            public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                                zzatp.this.zza(i, th, bArr);
                            }
                        });
                    } catch (MalformedURLException e) {
                        zzJt().zzLa().zze("Failed to parse upload URL. Not uploading. appId", zzati.zzfI(zzKG), zzKu);
                    }
                }
            } else {
                this.zzbtG = -1;
                String zzam = zzJo().zzam(currentTimeMillis - zzJv().zzKv());
                if (!TextUtils.isEmpty(zzam)) {
                    zzasp zzfy = zzJo().zzfy(zzam);
                    if (zzfy != null) {
                        zzb(zzfy);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzLK() {
        this.zzbtF++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzLL() {
        zzmq();
        zznA();
        if (!this.zzbty) {
            zzJt().zzLe().log("This instance being marked as an uploader");
            zzLC();
        }
        this.zzbty = true;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzLM() {
        zzmq();
        zznA();
        return this.zzbty;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public boolean zzLt() {
        boolean z = false;
        zznA();
        zzmq();
        if (this.zzbtz == null || this.zzbtA == 0 || (this.zzbtz != null && !this.zzbtz.booleanValue() && Math.abs(zznq().elapsedRealtime() - this.zzbtA) > 1000)) {
            this.zzbtA = zznq().elapsedRealtime();
            zzJv().zzKk();
            if (zzJp().zzbV("android.permission.INTERNET") && zzJp().zzbV("android.permission.ACCESS_NETWORK_STATE")) {
                zzacx.zzaQ(getContext());
                if (zzatm.zzi(getContext(), false) && zzatx.zzj(getContext(), false)) {
                    z = true;
                }
            }
            this.zzbtz = Boolean.valueOf(z);
            if (this.zzbtz.booleanValue()) {
                this.zzbtz = Boolean.valueOf(zzJp().zzgd(zzJj().getGmpAppId()));
            }
        }
        return this.zzbtz.booleanValue();
    }

    public zzati zzLu() {
        if (this.zzbtf == null || !this.zzbtf.isInitialized()) {
            return null;
        }
        return this.zzbtf;
    }

    /* access modifiers changed from: package-private */
    public zzato zzLv() {
        return this.zzbtg;
    }

    public AppMeasurement zzLw() {
        return this.zzbtj;
    }

    public FirebaseAnalytics zzLx() {
        return this.zzbtk;
    }

    public zzatj zzLy() {
        zza((zzats) this.zzbto);
        return this.zzbto;
    }

    public zzatk zzLz() {
        if (this.zzbtu != null) {
            return this.zzbtu;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public void zzV(boolean z) {
        zzLI();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public int zza(FileChannel fileChannel) {
        int i = 0;
        zzmq();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzJt().zzLa().log("Bad chanel to read from");
            return i;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read == 4) {
                allocate.flip();
                return allocate.getInt();
            } else if (read == -1) {
                return i;
            } else {
                zzJt().zzLc().zzj("Unexpected data length. Bytes read", Integer.valueOf(read));
                return i;
            }
        } catch (IOException e) {
            zzJt().zzLa().zzj("Failed to read from channel", e);
            return i;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    @WorkerThread
    public void zza(int i, Throwable th, byte[] bArr) {
        boolean z = false;
        zzmq();
        zznA();
        if (bArr == null) {
            bArr = new byte[0];
        }
        List<Long> list = this.zzbtD;
        this.zzbtD = null;
        if ((i == 200 || i == 204) && th == null) {
            zzJu().zzbsg.set(zznq().currentTimeMillis());
            zzJu().zzbsh.set(0);
            zzLI();
            zzJt().zzLg().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            zzJo().beginTransaction();
            try {
                for (Long longValue : list) {
                    zzJo().zzal(longValue.longValue());
                }
                zzJo().setTransactionSuccessful();
                zzJo().endTransaction();
                if (!zzLy().zzpA() || !zzLH()) {
                    this.zzbtG = -1;
                    zzLI();
                    return;
                }
                zzLG();
            } catch (Throwable th2) {
                zzJo().endTransaction();
                throw th2;
            }
        } else {
            zzJt().zzLg().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzJu().zzbsh.set(zznq().currentTimeMillis());
            if (i == 503 || i == 429) {
                z = true;
            }
            if (z) {
                zzJu().zzbsi.set(zznq().currentTimeMillis());
            }
            zzLI();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zza(zzasq zzasq, long j) {
        zzasp zzfy = zzJo().zzfy(zzasq.packageName);
        if (!(zzfy == null || zzfy.getGmpAppId() == null || zzfy.getGmpAppId().equals(zzasq.zzbqf))) {
            zzJt().zzLc().zzj("New GMP App Id passed in. Removing cached database data. appId", zzati.zzfI(zzfy.zzjI()));
            zzJo().zzfD(zzfy.zzjI());
            zzfy = null;
        }
        if (zzfy != null && zzfy.zzmy() != null && !zzfy.zzmy().equals(zzasq.zzbhg)) {
            Bundle bundle = new Bundle();
            bundle.putString("_pv", zzfy.zzmy());
            zzb(new zzatb("_au", new zzasz(bundle), "auto", j), zzasq);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzasx zzasx, zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzw(zzasx);
        zzac.zzw(zzasq);
        zzac.zzdv(zzasx.zzVQ);
        zzac.zzas(zzasx.zzVQ.equals(zzasq.packageName));
        zzauh.zze zze = new zzauh.zze();
        zze.zzbwc = 1;
        zze.zzbwk = AbstractSpiCall.ANDROID_CLIENT_TYPE;
        zze.zzaR = zzasq.packageName;
        zze.zzbqg = zzasq.zzbqg;
        zze.zzbhg = zzasq.zzbhg;
        zze.zzbwx = Integer.valueOf((int) zzasq.zzbqm);
        zze.zzbwo = Long.valueOf(zzasq.zzbqh);
        zze.zzbqf = zzasq.zzbqf;
        zze.zzbwt = zzasq.zzbqi == 0 ? null : Long.valueOf(zzasq.zzbqi);
        Pair<String, Boolean> zzfK = zzJu().zzfK(zzasq.packageName);
        if (!TextUtils.isEmpty((CharSequence) zzfK.first)) {
            zze.zzbwq = (String) zzfK.first;
            zze.zzbwr = (Boolean) zzfK.second;
        } else if (!zzJk().zzbt(this.mContext)) {
            String string = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzJt().zzLc().zzj("null secure ID. appId", zzati.zzfI(zze.zzaR));
                string = "null";
            } else if (string.isEmpty()) {
                zzJt().zzLc().zzj("empty secure ID. appId", zzati.zzfI(zze.zzaR));
            }
            zze.zzbwA = string;
        }
        zze.zzbwl = zzJk().zzkm();
        zze.zzba = zzJk().zzKU();
        zze.zzbwn = Integer.valueOf((int) zzJk().zzKV());
        zze.zzbwm = zzJk().zzKW();
        zze.zzbwp = null;
        zze.zzbwf = null;
        zze.zzbwg = null;
        zze.zzbwh = null;
        zzasp zzfy = zzJo().zzfy(zzasq.packageName);
        if (zzfy == null) {
            zzfy = new zzasp(this, zzasq.packageName);
            zzfy.zzfh(zzJu().zzLj());
            zzfy.zzfk(zzasq.zzbqn);
            zzfy.zzfi(zzasq.zzbqf);
            zzfy.zzfj(zzJu().zzfL(zzasq.packageName));
            zzfy.zzac(0);
            zzfy.zzX(0);
            zzfy.zzY(0);
            zzfy.setAppVersion(zzasq.zzbhg);
            zzfy.zzZ(zzasq.zzbqm);
            zzfy.zzfl(zzasq.zzbqg);
            zzfy.zzaa(zzasq.zzbqh);
            zzfy.zzab(zzasq.zzbqi);
            zzfy.setMeasurementEnabled(zzasq.zzbqk);
            zzJo().zza(zzfy);
        }
        zze.zzbws = zzfy.getAppInstanceId();
        zze.zzbqn = zzfy.zzJy();
        List<zzaud> zzfx = zzJo().zzfx(zzasq.packageName);
        zze.zzbwe = new zzauh.zzg[zzfx.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < zzfx.size()) {
                zzauh.zzg zzg = new zzauh.zzg();
                zze.zzbwe[i2] = zzg;
                zzg.name = zzfx.get(i2).mName;
                zzg.zzbwF = Long.valueOf(zzfx.get(i2).zzbvd);
                zzJp().zza(zzg, zzfx.get(i2).zzYe);
                i = i2 + 1;
            } else {
                try {
                    zzJo().zza(zzasx, zzJo().zza(zze), zza(zzasx));
                    return;
                } catch (IOException e) {
                    zzJt().zzLa().zze("Data loss. Failed to insert raw event metadata. appId", zzati.zzfI(zze.zzaR), e);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zza(int i, FileChannel fileChannel) {
        zzmq();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzJt().zzLa().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzJt().zzLa().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzJt().zzLa().zzj("Failed to write to channel", e);
            return false;
        }
    }

    @WorkerThread
    public byte[] zza(@NonNull zzatb zzatb, @Size(min = 1) String str) {
        long j;
        zznA();
        zzmq();
        zzJd();
        zzac.zzw(zzatb);
        zzac.zzdv(str);
        zzauh.zzd zzd = new zzauh.zzd();
        zzJo().beginTransaction();
        try {
            zzasp zzfy = zzJo().zzfy(str);
            if (zzfy == null) {
                zzJt().zzLf().zzj("Log and bundle not available. package_name", str);
                return new byte[0];
            } else if (!zzfy.zzJF()) {
                zzJt().zzLf().zzj("Log and bundle disabled. package_name", str);
                byte[] bArr = new byte[0];
                zzJo().endTransaction();
                return bArr;
            } else {
                zzauh.zze zze = new zzauh.zze();
                zzd.zzbwa = new zzauh.zze[]{zze};
                zze.zzbwc = 1;
                zze.zzbwk = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                zze.zzaR = zzfy.zzjI();
                zze.zzbqg = zzfy.zzJC();
                zze.zzbhg = zzfy.zzmy();
                zze.zzbwx = Integer.valueOf((int) zzfy.zzJB());
                zze.zzbwo = Long.valueOf(zzfy.zzJD());
                zze.zzbqf = zzfy.getGmpAppId();
                zze.zzbwt = Long.valueOf(zzfy.zzJE());
                Pair<String, Boolean> zzfK = zzJu().zzfK(zzfy.zzjI());
                if (!TextUtils.isEmpty((CharSequence) zzfK.first)) {
                    zze.zzbwq = (String) zzfK.first;
                    zze.zzbwr = (Boolean) zzfK.second;
                }
                zze.zzbwl = zzJk().zzkm();
                zze.zzba = zzJk().zzKU();
                zze.zzbwn = Integer.valueOf((int) zzJk().zzKV());
                zze.zzbwm = zzJk().zzKW();
                zze.zzbws = zzfy.getAppInstanceId();
                zze.zzbqn = zzfy.zzJy();
                List<zzaud> zzfx = zzJo().zzfx(zzfy.zzjI());
                zze.zzbwe = new zzauh.zzg[zzfx.size()];
                for (int i = 0; i < zzfx.size(); i++) {
                    zzauh.zzg zzg = new zzauh.zzg();
                    zze.zzbwe[i] = zzg;
                    zzg.name = zzfx.get(i).mName;
                    zzg.zzbwF = Long.valueOf(zzfx.get(i).zzbvd);
                    zzJp().zza(zzg, zzfx.get(i).zzYe);
                }
                Bundle zzKY = zzatb.zzbqP.zzKY();
                if ("_iap".equals(zzatb.name)) {
                    zzKY.putLong("_c", 1);
                    zzJt().zzLf().log("Marking in-app purchase as real-time");
                    zzKY.putLong("_r", 1);
                }
                zzKY.putString("_o", zzatb.zzbqQ);
                if (zzJp().zzgh(zze.zzaR)) {
                    zzJp().zza(zzKY, "_dbg", (Object) 1L);
                    zzJp().zza(zzKY, "_r", (Object) 1L);
                }
                zzasy zzP = zzJo().zzP(str, zzatb.name);
                if (zzP == null) {
                    zzJo().zza(new zzasy(str, zzatb.name, 1, 0, zzatb.zzbqR));
                    j = 0;
                } else {
                    j = zzP.zzbqL;
                    zzJo().zza(zzP.zzan(zzatb.zzbqR).zzKX());
                }
                zzasx zzasx = new zzasx(this, zzatb.zzbqQ, str, zzatb.name, zzatb.zzbqR, j, zzKY);
                zzauh.zzb zzb = new zzauh.zzb();
                zze.zzbwd = new zzauh.zzb[]{zzb};
                zzb.zzbvW = Long.valueOf(zzasx.zzavX);
                zzb.name = zzasx.mName;
                zzb.zzbvX = Long.valueOf(zzasx.zzbqH);
                zzb.zzbvV = new zzauh.zzc[zzasx.zzbqI.size()];
                Iterator<String> it = zzasx.zzbqI.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String next = it.next();
                    zzauh.zzc zzc = new zzauh.zzc();
                    zzb.zzbvV[i2] = zzc;
                    zzc.name = next;
                    zzJp().zza(zzc, zzasx.zzbqI.get(next));
                    i2++;
                }
                zze.zzbww = zza(zzfy.zzjI(), zze.zzbwe, zze.zzbwd);
                zze.zzbwg = zzb.zzbvW;
                zze.zzbwh = zzb.zzbvW;
                long zzJA = zzfy.zzJA();
                zze.zzbwj = zzJA != 0 ? Long.valueOf(zzJA) : null;
                long zzJz = zzfy.zzJz();
                if (zzJz != 0) {
                    zzJA = zzJz;
                }
                zze.zzbwi = zzJA != 0 ? Long.valueOf(zzJA) : null;
                zzfy.zzJJ();
                zze.zzbwu = Integer.valueOf((int) zzfy.zzJG());
                zze.zzbwp = Long.valueOf(zzJv().zzJD());
                zze.zzbwf = Long.valueOf(zznq().currentTimeMillis());
                zze.zzbwv = Boolean.TRUE;
                zzfy.zzX(zze.zzbwg.longValue());
                zzfy.zzY(zze.zzbwh.longValue());
                zzJo().zza(zzfy);
                zzJo().setTransactionSuccessful();
                zzJo().endTransaction();
                try {
                    byte[] bArr2 = new byte[zzd.zzacZ()];
                    zzbum zzae = zzbum.zzae(bArr2);
                    zzd.zza(zzae);
                    zzae.zzacM();
                    return zzJp().zzk(bArr2);
                } catch (IOException e) {
                    zzJt().zzLa().zze("Data loss. Failed to bundle and serialize. appId", zzati.zzfI(str), e);
                    return null;
                }
            }
        } finally {
            zzJo().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzao(long j) {
        return zzj(null, j);
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzasp zzasp) {
        String zzO = zzJv().zzO(zzasp.getGmpAppId(), zzasp.getAppInstanceId());
        try {
            URL url = new URL(zzO);
            zzJt().zzLg().zzj("Fetching remote configuration", zzasp.zzjI());
            zzaug.zzb zzfO = zzJq().zzfO(zzasp.zzjI());
            ArrayMap arrayMap = null;
            String zzfP = zzJq().zzfP(zzasp.zzjI());
            if (zzfO != null && !TextUtils.isEmpty(zzfP)) {
                arrayMap = new ArrayMap();
                arrayMap.put("If-Modified-Since", zzfP);
            }
            zzLy().zza(zzasp.zzjI(), url, arrayMap, new zzatj.zza() {
                public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                    zzatp.this.zzb(str, i, th, bArr, map);
                }
            });
        } catch (MalformedURLException e) {
            zzJt().zzLa().zze("Failed to parse config URL. Not fetching. appId", zzati.zzfI(zzasp.zzjI()), zzO);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzasq zzasq, long j) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        zzmq();
        zznA();
        Bundle bundle = new Bundle();
        bundle.putLong("_c", 1);
        bundle.putLong("_r", 1);
        bundle.putLong("_uwa", 0);
        bundle.putLong("_pfo", 0);
        bundle.putLong("_sys", 0);
        bundle.putLong("_sysu", 0);
        if (getContext().getPackageManager() == null) {
            zzJt().zzLa().zzj("PackageManager is null, first open report might be inaccurate. appId", zzati.zzfI(zzasq.packageName));
        } else {
            try {
                packageInfo = zzacx.zzaQ(getContext()).getPackageInfo(zzasq.packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                zzJt().zzLa().zze("Package info is null, first open report might be inaccurate. appId", zzati.zzfI(zzasq.packageName), e);
                packageInfo = null;
            }
            if (!(packageInfo == null || packageInfo.firstInstallTime == 0 || packageInfo.firstInstallTime == packageInfo.lastUpdateTime)) {
                bundle.putLong("_uwa", 1);
            }
            try {
                applicationInfo = zzacx.zzaQ(getContext()).getApplicationInfo(zzasq.packageName, 0);
            } catch (PackageManager.NameNotFoundException e2) {
                zzJt().zzLa().zze("Application info is null, first open report might be inaccurate. appId", zzati.zzfI(zzasq.packageName), e2);
                applicationInfo = null;
            }
            if (applicationInfo != null) {
                if ((applicationInfo.flags & 1) != 0) {
                    bundle.putLong("_sys", 1);
                }
                if ((applicationInfo.flags & 128) != 0) {
                    bundle.putLong("_sysu", 1);
                }
            }
        }
        long zzfE = zzJo().zzfE(zzasq.packageName);
        if (zzfE >= 0) {
            bundle.putLong("_pfo", zzfE);
        }
        zzb(new zzatb("_f", new zzasz(bundle), "auto", j), zzasq);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:66:0x0252=Splitter:B:66:0x0252, B:95:0x0344=Splitter:B:95:0x0344} */
    @WorkerThread
    public void zzb(zzatb zzatb, zzasq zzasq) {
        zzasy zzan;
        long j;
        zzaud zzaud;
        long nanoTime = System.nanoTime();
        zzmq();
        zznA();
        String str = zzasq.packageName;
        zzac.zzdv(str);
        if (zzaue.zzc(zzatb, zzasq)) {
            if (!zzasq.zzbqk && !"_in".equals(zzatb.name)) {
                zzf(zzasq);
            } else if (zzJq().zzX(str, zzatb.name)) {
                zzJt().zzLc().zze("Dropping blacklisted event. appId", zzati.zzfI(str), zzatb.name);
                boolean z = zzJp().zzgj(str) || zzJp().zzgk(str);
                if (!z && !"_err".equals(zzatb.name)) {
                    zzJp().zza(11, "_ev", zzatb.name, 0);
                }
                if (z) {
                    zzasp zzfy = zzJo().zzfy(str);
                    if (zzfy != null) {
                        if (Math.abs(zznq().currentTimeMillis() - Math.max(zzfy.zzJI(), zzfy.zzJH())) > zzJv().zzKp()) {
                            zzJt().zzLf().log("Fetching config for blacklisted app");
                            zzb(zzfy);
                        }
                    }
                }
            } else {
                if (zzJt().zzai(2)) {
                    zzJt().zzLg().zzj("Logging event", zzatb);
                }
                zzJo().beginTransaction();
                try {
                    Bundle zzKY = zzatb.zzbqP.zzKY();
                    zzf(zzasq);
                    if ("_iap".equals(zzatb.name) || FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzatb.name)) {
                        String string = zzKY.getString(FirebaseAnalytics.Param.CURRENCY);
                        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzatb.name)) {
                            double d = zzKY.getDouble(FirebaseAnalytics.Param.VALUE) * 1000000.0d;
                            if (d == 0.0d) {
                                d = ((double) zzKY.getLong(FirebaseAnalytics.Param.VALUE)) * 1000000.0d;
                            }
                            if (d > 9.223372036854776E18d || d < -9.223372036854776E18d) {
                                zzJt().zzLc().zze("Data lost. Currency value is too big. appId", zzati.zzfI(str), Double.valueOf(d));
                                zzJo().setTransactionSuccessful();
                                zzJo().endTransaction();
                                return;
                            }
                            j = Math.round(d);
                        } else {
                            j = zzKY.getLong(FirebaseAnalytics.Param.VALUE);
                        }
                        if (!TextUtils.isEmpty(string)) {
                            String upperCase = string.toUpperCase(Locale.US);
                            if (upperCase.matches("[A-Z]{3}")) {
                                String valueOf = String.valueOf("_ltv_");
                                String valueOf2 = String.valueOf(upperCase);
                                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                                zzaud zzR = zzJo().zzR(str, concat);
                                if (zzR == null || !(zzR.zzYe instanceof Long)) {
                                    zzJo().zzz(str, zzJv().zzfr(str) - 1);
                                    zzaud = new zzaud(str, concat, zznq().currentTimeMillis(), Long.valueOf(j));
                                } else {
                                    zzaud = new zzaud(str, concat, zznq().currentTimeMillis(), Long.valueOf(j + ((Long) zzR.zzYe).longValue()));
                                }
                                if (!zzJo().zza(zzaud)) {
                                    zzJt().zzLa().zzd("Too many unique user properties are set. Ignoring user property. appId", zzati.zzfI(str), zzaud.mName, zzaud.zzYe);
                                    zzJp().zza(9, (String) null, (String) null, 0);
                                }
                            }
                        }
                    }
                    boolean zzfW = zzaue.zzfW(zzatb.name);
                    boolean equals = "_err".equals(zzatb.name);
                    zzasu.zza zza2 = zzJo().zza(zzLE(), str, true, zzfW, false, equals, false);
                    long zzKc = zza2.zzbqw - zzJv().zzKc();
                    if (zzKc > 0) {
                        if (zzKc % 1000 == 1) {
                            zzJt().zzLa().zze("Data loss. Too many events logged. appId, count", zzati.zzfI(str), Long.valueOf(zza2.zzbqw));
                        }
                        zzJp().zza(16, "_ev", zzatb.name, 0);
                        zzJo().setTransactionSuccessful();
                        return;
                    }
                    if (zzfW) {
                        long zzKd = zza2.zzbqv - zzJv().zzKd();
                        if (zzKd > 0) {
                            if (zzKd % 1000 == 1) {
                                zzJt().zzLa().zze("Data loss. Too many public events logged. appId, count", zzati.zzfI(str), Long.valueOf(zza2.zzbqv));
                            }
                            zzJp().zza(16, "_ev", zzatb.name, 0);
                            zzJo().setTransactionSuccessful();
                            zzJo().endTransaction();
                            return;
                        }
                    }
                    if (equals) {
                        long zzfn = zza2.zzbqy - ((long) zzJv().zzfn(zzasq.packageName));
                        if (zzfn > 0) {
                            if (zzfn == 1) {
                                zzJt().zzLa().zze("Too many error events logged. appId, count", zzati.zzfI(str), Long.valueOf(zza2.zzbqy));
                            }
                            zzJo().setTransactionSuccessful();
                            zzJo().endTransaction();
                            return;
                        }
                    }
                    zzJp().zza(zzKY, "_o", (Object) zzatb.zzbqQ);
                    if (zzJp().zzgh(str)) {
                        zzJp().zza(zzKY, "_dbg", (Object) 1L);
                        zzJp().zza(zzKY, "_r", (Object) 1L);
                    }
                    long zzfz = zzJo().zzfz(str);
                    if (zzfz > 0) {
                        zzJt().zzLc().zze("Data lost. Too many events stored on disk, deleted. appId", zzati.zzfI(str), Long.valueOf(zzfz));
                    }
                    zzasx zzasx = new zzasx(this, zzatb.zzbqQ, str, zzatb.name, zzatb.zzbqR, 0, zzKY);
                    zzasy zzP = zzJo().zzP(str, zzasx.mName);
                    if (zzP == null) {
                        long zzfG = zzJo().zzfG(str);
                        zzJv().zzKb();
                        if (zzfG >= 500) {
                            zzJt().zzLa().zzd("Too many event names used, ignoring event. appId, name, supported count", zzati.zzfI(str), zzasx.mName, Integer.valueOf(zzJv().zzKb()));
                            zzJp().zza(8, (String) null, (String) null, 0);
                            zzJo().endTransaction();
                            return;
                        }
                        zzan = new zzasy(str, zzasx.mName, 0, 0, zzasx.zzavX);
                    } else {
                        zzasx = zzasx.zza(this, zzP.zzbqL);
                        zzan = zzP.zzan(zzasx.zzavX);
                    }
                    zzJo().zza(zzan);
                    zza(zzasx, zzasq);
                    zzJo().setTransactionSuccessful();
                    if (zzJt().zzai(2)) {
                        zzJt().zzLg().zzj("Event recorded", zzasx);
                    }
                    zzJo().endTransaction();
                    zzLI();
                    zzJt().zzLg().zzj("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                } finally {
                    zzJo().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzatb zzatb, String str) {
        zzasp zzfy = zzJo().zzfy(str);
        if (zzfy == null || TextUtils.isEmpty(zzfy.zzmy())) {
            zzJt().zzLf().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = zzacx.zzaQ(getContext()).getPackageInfo(str, 0).versionName;
            if (zzfy.zzmy() != null && !zzfy.zzmy().equals(str2)) {
                zzJt().zzLc().zzj("App version does not match; dropping event. appId", zzati.zzfI(str));
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            if (!"_ui".equals(zzatb.name)) {
                zzJt().zzLc().zzj("Could not find package. appId", zzati.zzfI(str));
            }
        }
        zzatb zzatb2 = zzatb;
        zzb(zzatb2, new zzasq(str, zzfy.getGmpAppId(), zzfy.zzmy(), zzfy.zzJB(), zzfy.zzJC(), zzfy.zzJD(), zzfy.zzJE(), null, zzfy.zzJF(), false, zzfy.zzJy()));
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzats zzats) {
        this.zzbtE++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(zzaub zzaub, zzasq zzasq) {
        int i = 0;
        zzmq();
        zznA();
        if (!TextUtils.isEmpty(zzasq.zzbqf)) {
            if (!zzasq.zzbqk) {
                zzf(zzasq);
                return;
            }
            int zzga = zzJp().zzga(zzaub.name);
            if (zzga != 0) {
                String zza2 = zzJp().zza(zzaub.name, zzJv().zzJV(), true);
                if (zzaub.name != null) {
                    i = zzaub.name.length();
                }
                zzJp().zza(zzga, "_ev", zza2, i);
                return;
            }
            int zzm = zzJp().zzm(zzaub.name, zzaub.getValue());
            if (zzm != 0) {
                String zza3 = zzJp().zza(zzaub.name, zzJv().zzJV(), true);
                Object value = zzaub.getValue();
                if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                    i = String.valueOf(value).length();
                }
                zzJp().zza(zzm, "_ev", zza3, i);
                return;
            }
            Object zzn = zzJp().zzn(zzaub.name, zzaub.getValue());
            if (zzn != null) {
                zzaud zzaud = new zzaud(zzasq.packageName, zzaub.name, zzaub.zzbuZ, zzn);
                zzJt().zzLf().zze("Setting user property", zzaud.mName, zzn);
                zzJo().beginTransaction();
                try {
                    zzf(zzasq);
                    boolean zza4 = zzJo().zza(zzaud);
                    zzJo().setTransactionSuccessful();
                    if (zza4) {
                        zzJt().zzLf().zze("User property set", zzaud.mName, zzaud.zzYe);
                    } else {
                        zzJt().zzLa().zze("Too many unique user properties are set. Ignoring user property", zzaud.mName, zzaud.zzYe);
                        zzJp().zza(9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzJo().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        boolean z = false;
        zzmq();
        zznA();
        zzac.zzdv(str);
        if (bArr == null) {
            bArr = new byte[0];
        }
        zzJo().beginTransaction();
        try {
            zzasp zzfy = zzJo().zzfy(str);
            boolean z2 = (i == 200 || i == 204 || i == 304) && th == null;
            if (zzfy == null) {
                zzJt().zzLc().zzj("App does not exist in onConfigFetched. appId", zzati.zzfI(str));
            } else if (z2 || i == 404) {
                List list = map != null ? map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
                if (i == 404 || i == 304) {
                    if (zzJq().zzfO(str) == null && !zzJq().zzb(str, null, null)) {
                        zzJo().endTransaction();
                        return;
                    }
                } else if (!zzJq().zzb(str, bArr, str2)) {
                    zzJo().endTransaction();
                    return;
                }
                zzfy.zzad(zznq().currentTimeMillis());
                zzJo().zza(zzfy);
                if (i == 404) {
                    zzJt().zzLc().zzj("Config not found. Using empty config. appId", zzati.zzfI(str));
                } else {
                    zzJt().zzLg().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (!zzLy().zzpA() || !zzLH()) {
                    zzLI();
                } else {
                    zzLG();
                }
            } else {
                zzfy.zzae(zznq().currentTimeMillis());
                zzJo().zza(zzfy);
                zzJt().zzLg().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzJq().zzfQ(str);
                zzJu().zzbsh.set(zznq().currentTimeMillis());
                if (i == 503 || i == 429) {
                    z = true;
                }
                if (z) {
                    zzJu().zzbsi.set(zznq().currentTimeMillis());
                }
                zzLI();
            }
            zzJo().setTransactionSuccessful();
        } finally {
            zzJo().endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzc(zzasq zzasq, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_et", 1);
        zzb(new zzatb("_e", new zzasz(bundle), "auto", j), zzasq);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzc(zzaub zzaub, zzasq zzasq) {
        zzmq();
        zznA();
        if (!TextUtils.isEmpty(zzasq.zzbqf)) {
            if (!zzasq.zzbqk) {
                zzf(zzasq);
                return;
            }
            zzJt().zzLf().zzj("Removing user property", zzaub.name);
            zzJo().beginTransaction();
            try {
                zzf(zzasq);
                zzJo().zzQ(zzasq.packageName, zzaub.name);
                zzJo().setTransactionSuccessful();
                zzJt().zzLf().zzj("User property removed", zzaub.name);
            } finally {
                zzJo().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzdv(zzasq.packageName);
        zzf(zzasq);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzd(zzasq zzasq, long j) {
        zzb(new zzatb("_cd", new zzasz(new Bundle()), "auto", j), zzasq);
    }

    @WorkerThread
    public void zze(zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzw(zzasq);
        zzac.zzdv(zzasq.packageName);
        if (!TextUtils.isEmpty(zzasq.zzbqf)) {
            if (!zzasq.zzbqk) {
                zzf(zzasq);
                return;
            }
            long currentTimeMillis = zznq().currentTimeMillis();
            zzJo().beginTransaction();
            try {
                zza(zzasq, currentTimeMillis);
                zzf(zzasq);
                if (zzJo().zzP(zzasq.packageName, "_f") == null) {
                    zzb(new zzaub("_fot", currentTimeMillis, Long.valueOf((1 + (currentTimeMillis / 3600000)) * 3600000), "auto"), zzasq);
                    zzb(zzasq, currentTimeMillis);
                    zzc(zzasq, currentTimeMillis);
                } else if (zzasq.zzbql) {
                    zzd(zzasq, currentTimeMillis);
                }
                zzJo().setTransactionSuccessful();
            } finally {
                zzJo().endTransaction();
            }
        }
    }

    public String zzfR(final String str) {
        try {
            return (String) zzJs().zzd(new Callable<String>() {
                /* renamed from: zzou */
                public String call() {
                    zzasp zzfy = zzatp.this.zzJo().zzfy(str);
                    if (zzfy == null) {
                        return null;
                    }
                    return zzfy.getAppInstanceId();
                }
            }).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzJt().zzLa().zze("Failed to get app instance id. appId", zzati.zzfI(str), e);
            return null;
        }
    }

    @WorkerThread
    public void zzmq() {
        zzJs().zzmq();
    }

    /* access modifiers changed from: package-private */
    public void zznA() {
        if (!this.zzacO) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    public zze zznq() {
        return this.zzuI;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzv(int i, int i2) {
        zzmq();
        if (i > i2) {
            zzJt().zzLa().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            return false;
        }
        if (i < i2) {
            if (zza(i2, zzLB())) {
                zzJt().zzLg().zze("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            } else {
                zzJt().zzLa().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
                return false;
            }
        }
        return true;
    }
}
