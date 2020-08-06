package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzaf;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzar implements zzaq {
    protected MotionEvent zzpI;
    protected LinkedList<MotionEvent> zzpJ = new LinkedList<>();
    protected long zzpK = 0;
    protected long zzpL = 0;
    protected long zzpM = 0;
    protected long zzpN = 0;
    protected long zzpO = 0;
    protected long zzpP = 0;
    protected long zzpQ = 0;
    private boolean zzpR = false;
    protected boolean zzpS = false;
    protected DisplayMetrics zzpT;

    protected zzar(Context context) {
        try {
            zzan.zzR();
            this.zzpT = context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
        }
    }

    private String zza(Context context, String str, boolean z, View view, byte[] bArr) {
        zzaf.zza zza;
        boolean z2 = true;
        zzad.zza zza2 = null;
        if (bArr != null && bArr.length > 0) {
            try {
                zza2 = zzad.zza.zzc(bArr);
            } catch (zzbus e) {
            }
        }
        if (z) {
            try {
                zza = zza(context, view);
                this.zzpR = true;
            } catch (NoSuchAlgorithmException e2) {
                return Integer.toString(7);
            } catch (UnsupportedEncodingException e3) {
                return Integer.toString(7);
            } catch (Throwable th) {
                return Integer.toString(3);
            }
        } else {
            zza = zza(context, zza2);
        }
        if (zza == null || zza.zzacZ() == 0) {
            return Integer.toString(5);
        }
        if (zzb(z)) {
            z2 = false;
        }
        return zzan.zza(zza, str, z2);
    }

    private static boolean zza(zzbd zzbd) {
        return (zzbd == null || zzbd.zzce == null || zzbd.zzqQ == null) ? false : true;
    }

    private boolean zzb(zzbd zzbd) {
        return (this.zzpT == null || zzbd == null || zzbd.zzcc == null || zzbd.zzqR == null) ? false : true;
    }

    private static boolean zzb(boolean z) {
        if (!zzfx.zzDi.get().booleanValue()) {
            return true;
        }
        return zzfx.zzDD.get().booleanValue() && z;
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr);

    /* access modifiers changed from: protected */
    public abstract zzaf.zza zza(Context context, View view);

    /* access modifiers changed from: protected */
    public abstract zzaf.zza zza(Context context, zzad.zza zza);

    public String zza(Context context, String str, View view) {
        return zza(context, str, true, view, null);
    }

    public String zza(Context context, byte[] bArr) {
        if (!zzbe.zzbd() || !zzfx.zzDC.get().booleanValue()) {
            return zza(context, null, false, null, bArr);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public void zza(int i, int i2, int i3) {
        if (this.zzpI != null) {
            this.zzpI.recycle();
        }
        if (this.zzpT != null) {
            this.zzpI = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * this.zzpT.density, ((float) i2) * this.zzpT.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzpI = null;
        }
        this.zzpS = false;
    }

    public void zza(MotionEvent motionEvent) {
        if (this.zzpR) {
            this.zzpN = 0;
            this.zzpM = 0;
            this.zzpL = 0;
            this.zzpK = 0;
            this.zzpO = 0;
            this.zzpQ = 0;
            this.zzpP = 0;
            Iterator it = this.zzpJ.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzpJ.clear();
            this.zzpI = null;
            this.zzpR = false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.zzpK++;
                break;
            case 1:
                this.zzpI = MotionEvent.obtain(motionEvent);
                this.zzpJ.add(this.zzpI);
                if (this.zzpJ.size() > 6) {
                    this.zzpJ.remove().recycle();
                }
                this.zzpM++;
                try {
                    this.zzpO = zza(new Throwable().getStackTrace());
                    break;
                } catch (zzaz e) {
                    break;
                }
            case 2:
                this.zzpL += (long) (motionEvent.getHistorySize() + 1);
                if (zzfx.zzDq.get().booleanValue() || zzfx.zzDl.get().booleanValue()) {
                    try {
                        zzbd zzb = zzb(motionEvent);
                        if (zza(zzb)) {
                            this.zzpP += zzb.zzce.longValue() + zzb.zzqQ.longValue();
                        }
                        if (zzb(zzb)) {
                            this.zzpQ = zzb.zzqR.longValue() + zzb.zzcc.longValue() + this.zzpQ;
                            break;
                        }
                    } catch (zzaz e2) {
                        break;
                    }
                }
                break;
            case 3:
                this.zzpN++;
                break;
        }
        this.zzpS = true;
    }

    /* access modifiers changed from: protected */
    public abstract zzbd zzb(MotionEvent motionEvent);

    public String zzb(Context context) {
        if (!zzbe.zzbd() || !zzfx.zzDC.get().booleanValue()) {
            return zza(context, null, false, null, null);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public String zzb(Context context, String str) {
        return zza(context, str, (View) null);
    }
}
