package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzaf;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class zzat extends zzar {
    private static final String TAG = zzat.class.getSimpleName();
    private static long startTime = 0;
    protected static volatile zzbc zzpC = null;
    protected static final Object zzpV = new Object();
    static boolean zzpW = false;
    protected boolean zzpX = false;
    protected String zzpY;
    protected boolean zzpZ = false;
    protected boolean zzqa = false;

    protected zzat(Context context, String str) {
        super(context);
        this.zzpY = str;
        this.zzpX = false;
    }

    protected zzat(Context context, String str, boolean z) {
        super(context);
        this.zzpY = str;
        this.zzpX = z;
    }

    static zzbd zza(zzbc zzbc, MotionEvent motionEvent, DisplayMetrics displayMetrics) {
        Method zzc = zzbc.zzc(zzay.zzaD(), zzay.zzaE());
        if (zzc == null || motionEvent == null) {
            throw new zzaz();
        }
        try {
            return new zzbd((String) zzc.invoke(null, new Object[]{motionEvent, displayMetrics}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzaz(e);
        }
    }

    protected static synchronized void zza(Context context, boolean z) {
        synchronized (zzat.class) {
            if (!zzpW) {
                startTime = zzbe.zzbc().longValue() / 1000;
                zzpC = zzb(context, z);
                zzpW = true;
            }
        }
    }

    private static void zza(zzbc zzbc) {
        List<Context> singletonList = Collections.singletonList(Context.class);
        zzbc.zza(zzay.zzaj(), zzay.zzak(), (List<Class>) singletonList);
        zzbc.zza(zzay.zzat(), zzay.zzau(), (List<Class>) singletonList);
        zzbc.zza(zzay.zzar(), zzay.zzas(), (List<Class>) singletonList);
        zzbc.zza(zzay.zzad(), zzay.zzae(), (List<Class>) singletonList);
        zzbc.zza(zzay.zzan(), zzay.zzao(), (List<Class>) singletonList);
        zzbc.zza(zzay.zzZ(), zzay.zzaa(), (List<Class>) singletonList);
        zzbc.zza(zzay.zzaF(), zzay.zzaG(), (List<Class>) singletonList);
        List asList = Arrays.asList(new Class[]{MotionEvent.class, DisplayMetrics.class});
        zzbc.zza(zzay.zzaD(), zzay.zzaE(), (List<Class>) asList);
        zzbc.zza(zzay.zzaB(), zzay.zzaC(), (List<Class>) asList);
        zzbc.zza(zzay.zzah(), zzay.zzai(), (List<Class>) Collections.emptyList());
        zzbc.zza(zzay.zzaz(), zzay.zzaA(), (List<Class>) Collections.emptyList());
        zzbc.zza(zzay.zzap(), zzay.zzaq(), (List<Class>) Collections.emptyList());
        zzbc.zza(zzay.zzaf(), zzay.zzag(), (List<Class>) Collections.emptyList());
        zzbc.zza(zzay.zzal(), zzay.zzam(), (List<Class>) Collections.emptyList());
        zzbc.zza(zzay.zzax(), zzay.zzay(), (List<Class>) Collections.emptyList());
        zzbc.zza(zzay.zzab(), zzay.zzac(), (List<Class>) Arrays.asList(new Class[]{Context.class, Boolean.TYPE, Boolean.TYPE}));
        zzbc.zza(zzay.zzav(), zzay.zzaw(), (List<Class>) Arrays.asList(new Class[]{StackTraceElement[].class}));
        zzbc.zza(zzay.zzaH(), zzay.zzaI(), (List<Class>) Arrays.asList(new Class[]{View.class}));
    }

    private void zza(zzbc zzbc, zzaf.zza zza) {
        try {
            zzbd zza2 = zza(zzbc, this.zzpI, this.zzpT);
            zza.zzbm = zza2.zzqM;
            zza.zzbn = zza2.zzqN;
            zza.zzbo = zza2.zzqO;
            if (this.zzpS) {
                zza.zzbA = zza2.zzce;
                zza.zzbB = zza2.zzcc;
            }
            if (zzfx.zzDq.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
                zzaf.zza.C1708zza zza3 = new zzaf.zza.C1708zza();
                zzbd zzb = zzb(this.zzpI);
                zza3.zzbm = zzb.zzqM;
                zza3.zzbn = zzb.zzqN;
                zza3.zzch = zzb.zzqO;
                if (this.zzpS) {
                    zza3.zzcc = zzb.zzcc;
                    zza3.zzce = zzb.zzce;
                    zza3.zzcg = Integer.valueOf(zzb.zzqP.longValue() != 0 ? 1 : 0);
                    if (this.zzpL > 0) {
                        zza3.zzcd = this.zzpT != null ? Long.valueOf(Math.round(((double) this.zzpQ) / ((double) this.zzpL))) : null;
                        zza3.zzcf = Long.valueOf(Math.round(((double) this.zzpP) / ((double) this.zzpL)));
                    }
                    zza3.zzcj = zzb.zzcj;
                    zza3.zzci = zzb.zzci;
                    zza3.zzck = Integer.valueOf(zzb.zzqS.longValue() != 0 ? 1 : 0);
                    if (this.zzpO > 0) {
                        zza3.zzcl = Long.valueOf(this.zzpO);
                    }
                }
                zza.zzbR = zza3;
            }
        } catch (zzaz e) {
        }
        if (this.zzpK > 0) {
            zza.zzbF = Long.valueOf(this.zzpK);
        }
        if (this.zzpL > 0) {
            zza.zzbE = Long.valueOf(this.zzpL);
        }
        if (this.zzpM > 0) {
            zza.zzbD = Long.valueOf(this.zzpM);
        }
        if (this.zzpN > 0) {
            zza.zzbG = Long.valueOf(this.zzpN);
        }
        try {
            int size = this.zzpJ.size() - 1;
            if (size > 0) {
                zza.zzbS = new zzaf.zza.C1708zza[size];
                for (int i = 0; i < size; i++) {
                    zzbd zza4 = zza(zzbc, (MotionEvent) this.zzpJ.get(i), this.zzpT);
                    zzaf.zza.C1708zza zza5 = new zzaf.zza.C1708zza();
                    zza5.zzbm = zza4.zzqM;
                    zza5.zzbn = zza4.zzqN;
                    zza.zzbS[i] = zza5;
                }
            }
        } catch (zzaz e2) {
            zza.zzbS = null;
        }
    }

    protected static zzbc zzb(Context context, boolean z) {
        if (zzpC == null) {
            synchronized (zzpV) {
                if (zzpC == null) {
                    zzbc zza = zzbc.zza(context, zzay.getKey(), zzay.zzY(), z);
                    zza(zza);
                    zzpC = zza;
                }
            }
        }
        return zzpC;
    }

    /* access modifiers changed from: protected */
    public long zza(StackTraceElement[] stackTraceElementArr) {
        Method zzc = zzpC.zzc(zzay.zzav(), zzay.zzaw());
        if (zzc == null || stackTraceElementArr == null) {
            throw new zzaz();
        }
        try {
            return new zzba((String) zzc.invoke(null, new Object[]{stackTraceElementArr})).zzqm.longValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzaz(e);
        }
    }

    /* access modifiers changed from: protected */
    public zzaf.zza zza(Context context, View view) {
        zzaf.zza zza = new zzaf.zza();
        if (!TextUtils.isEmpty(this.zzpY)) {
            zza.zzaZ = this.zzpY;
        }
        zzbc zzb = zzb(context, this.zzpX);
        zzb.zzba();
        zzb(zzb, zza, view);
        zzb.zzbb();
        return zza;
    }

    /* access modifiers changed from: protected */
    public zzaf.zza zza(Context context, zzad.zza zza) {
        zzaf.zza zza2 = new zzaf.zza();
        if (!TextUtils.isEmpty(this.zzpY)) {
            zza2.zzaZ = this.zzpY;
        }
        zzbc zzb = zzb(context, this.zzpX);
        zzb.zzba();
        zza(zzb, zza2, zza);
        zzb.zzbb();
        return zza2;
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zza(zzbc zzbc, zzaf.zza zza, View view) {
        ArrayList arrayList = new ArrayList();
        if (zzbc.zzaJ() == null) {
            return arrayList;
        }
        int zzT = zzbc.zzT();
        arrayList.add(new zzbn(zzbc, zza));
        ArrayList arrayList2 = arrayList;
        arrayList2.add(new zzbq(zzbc, zzay.zzap(), zzay.zzaq(), zza, zzT, 1));
        ArrayList arrayList3 = arrayList;
        arrayList3.add(new zzbl(zzbc, zzay.zzah(), zzay.zzai(), zza, startTime, zzT, 25));
        ArrayList arrayList4 = arrayList;
        arrayList4.add(new zzbk(zzbc, zzay.zzaf(), zzay.zzag(), zza, zzT, 44));
        ArrayList arrayList5 = arrayList;
        arrayList5.add(new zzbg(zzbc, zzay.zzZ(), zzay.zzaa(), zza, zzT, 3));
        ArrayList arrayList6 = arrayList;
        arrayList6.add(new zzbo(zzbc, zzay.zzal(), zzay.zzam(), zza, zzT, 22));
        if (zzfx.zzDw.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
            ArrayList arrayList7 = arrayList;
            arrayList7.add(new zzbj(zzbc, zzay.zzad(), zzay.zzae(), zza, zzT, 5));
        }
        if (zzfx.zzDp.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
            ArrayList arrayList8 = arrayList;
            arrayList8.add(new zzbv(zzbc, zzay.zzaF(), zzay.zzaG(), zza, zzT, 48));
        }
        if (zzfx.zzDu.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
            ArrayList arrayList9 = arrayList;
            arrayList9.add(new zzbt(zzbc, zzay.zzax(), zzay.zzay(), zza, zzT, 51));
        }
        if (zzfx.zzDz.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
            ArrayList arrayList10 = arrayList;
            arrayList10.add(new zzbs(zzbc, zzay.zzav(), zzay.zzaw(), zza, zzT, 45, new Throwable().getStackTrace()));
        }
        if (zzfx.zzDA.get().booleanValue()) {
            ArrayList arrayList11 = arrayList;
            arrayList11.add(new zzbw(zzbc, zzay.zzaH(), zzay.zzaI(), zza, zzT, 57, view));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zza(zzbc zzbc, zzaf.zza zza, zzad.zza zza2) {
        if (zzbc.zzaJ() != null) {
            zza(zzb(zzbc, zza, zza2));
        }
    }

    /* access modifiers changed from: protected */
    public void zza(List<Callable<Void>> list) {
        if (zzpC != null) {
            ExecutorService zzaJ = zzpC.zzaJ();
            if (zzaJ != null && !list.isEmpty()) {
                try {
                    zzaJ.invokeAll(list, zzfx.zzDg.get().longValue(), TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Log.d(TAG, String.format("class methods got exception: %s", new Object[]{zzbe.zza(e)}));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzbd zzb(MotionEvent motionEvent) {
        Method zzc = zzpC.zzc(zzay.zzaB(), zzay.zzaC());
        if (zzc == null || motionEvent == null) {
            throw new zzaz();
        }
        try {
            return new zzbd((String) zzc.invoke(null, new Object[]{motionEvent, this.zzpT}));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzaz(e);
        }
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zzb(zzbc zzbc, zzaf.zza zza, zzad.zza zza2) {
        int zzT = zzbc.zzT();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzbi(zzbc, zzay.zzab(), zzay.zzac(), zza, zzT, 27, zzfx.zzDk.get().booleanValue() || zzfx.zzDl.get().booleanValue(), zza2));
        arrayList.add(new zzbl(zzbc, zzay.zzah(), zzay.zzai(), zza, startTime, zzT, 25));
        arrayList.add(new zzbq(zzbc, zzay.zzap(), zzay.zzaq(), zza, zzT, 1));
        arrayList.add(new zzbr(zzbc, zzay.zzar(), zzay.zzas(), zza, zzT, 31));
        arrayList.add(new zzbu(zzbc, zzay.zzaz(), zzay.zzaA(), zza, zzT, 33));
        arrayList.add(new zzbh(zzbc, zzay.zzat(), zzay.zzau(), zza, zzT, 29));
        arrayList.add(new zzbj(zzbc, zzay.zzad(), zzay.zzae(), zza, zzT, 5));
        arrayList.add(new zzbp(zzbc, zzay.zzan(), zzay.zzao(), zza, zzT, 12));
        arrayList.add(new zzbg(zzbc, zzay.zzZ(), zzay.zzaa(), zza, zzT, 3));
        arrayList.add(new zzbk(zzbc, zzay.zzaf(), zzay.zzag(), zza, zzT, 44));
        arrayList.add(new zzbo(zzbc, zzay.zzal(), zzay.zzam(), zza, zzT, 22));
        if (zzfx.zzDn.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
            arrayList.add(new zzbv(zzbc, zzay.zzaF(), zzay.zzaG(), zza, zzT, 48));
        }
        if (zzfx.zzDs.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
            arrayList.add(new zzbt(zzbc, zzay.zzax(), zzay.zzay(), zza, zzT, 51));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zzb(zzbc zzbc, zzaf.zza zza, View view) {
        zza(zzbc, zza);
        zza(zza(zzbc, zza, view));
    }
}
