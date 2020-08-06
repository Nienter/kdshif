package com.google.android.gms.internal;

import com.google.android.gms.internal.zzax;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzbx {
    protected static final String TAG = zzbx.class.getSimpleName();
    private final String className;
    private final zzbc zzpC;
    private final String zzrh;
    private final int zzri = 2;
    private volatile Method zzrj = null;
    private List<Class> zzrk;
    private CountDownLatch zzrl = new CountDownLatch(1);

    public zzbx(zzbc zzbc, String str, String str2, List<Class> list) {
        this.zzpC = zzbc;
        this.className = str;
        this.zzrh = str2;
        this.zzrk = new ArrayList(list);
        this.zzpC.zzaJ().submit(new Runnable() {
            public void run() {
                zzbx.this.zzbm();
            }
        });
    }

    /* access modifiers changed from: private */
    public void zzbm() {
        try {
            Class loadClass = this.zzpC.zzaK().loadClass(zzd(this.zzpC.zzaM(), this.className));
            if (loadClass != null) {
                this.zzrj = loadClass.getMethod(zzd(this.zzpC.zzaM(), this.zzrh), (Class[]) this.zzrk.toArray(new Class[this.zzrk.size()]));
                if (this.zzrj == null) {
                    this.zzrl.countDown();
                } else {
                    this.zzrl.countDown();
                }
            }
        } catch (zzax.zza e) {
        } catch (UnsupportedEncodingException e2) {
        } catch (ClassNotFoundException e3) {
        } catch (NoSuchMethodException e4) {
        } catch (NullPointerException e5) {
        } finally {
            this.zzrl.countDown();
        }
    }

    private String zzd(byte[] bArr, String str) {
        return new String(this.zzpC.zzaL().zzc(bArr, str), "UTF-8");
    }

    public Method zzbn() {
        if (this.zzrj != null) {
            return this.zzrj;
        }
        try {
            if (this.zzrl.await(2, TimeUnit.SECONDS)) {
                return this.zzrj;
            }
            return null;
        } catch (InterruptedException e) {
            return null;
        }
    }
}
