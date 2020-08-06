package com.google.android.gms.internal;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzhb;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzgy extends zzhb.zza implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private final FrameLayout zzGI;
    private Map<String, WeakReference<View>> zzGJ = new HashMap();
    @Nullable
    private zzgn zzGK;
    boolean zzGL = false;
    int zzGM;
    int zzGN;
    @Nullable
    private zzgu zzGd;
    private final Object zzrN = new Object();
    /* access modifiers changed from: private */
    @Nullable
    public FrameLayout zzsc;

    public zzgy(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzGI = frameLayout;
        this.zzsc = frameLayout2;
        zzv.zzdh().zza((View) this.zzGI, (ViewTreeObserver.OnGlobalLayoutListener) this);
        zzv.zzdh().zza((View) this.zzGI, (ViewTreeObserver.OnScrollChangedListener) this);
        this.zzGI.setOnTouchListener(this);
        this.zzGI.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void zzd(zzgv zzgv) {
        synchronized (this.zzrN) {
            if (this.zzGJ == null || !this.zzGJ.containsKey("2011")) {
                zzgv.zzfW();
                return;
            }
            final View view = (View) this.zzGJ.get("2011").get();
            if (!(view instanceof FrameLayout)) {
                zzgv.zzfW();
            } else {
                zzgv.zza(view, (zzgs) new zzgs() {
                    public void zzc(MotionEvent motionEvent) {
                        zzgy.this.onTouch(null, motionEvent);
                    }

                    public void zzfR() {
                        zzgy.this.onClick(view);
                    }
                });
            }
        }
    }

    public void destroy() {
        synchronized (this.zzrN) {
            if (this.zzsc != null) {
                this.zzsc.removeAllViews();
            }
            this.zzsc = null;
            this.zzGJ = null;
            this.zzGK = null;
            this.zzGd = null;
        }
    }

    /* access modifiers changed from: package-private */
    public int getMeasuredHeight() {
        return this.zzGI.getMeasuredHeight();
    }

    /* access modifiers changed from: package-private */
    public int getMeasuredWidth() {
        return this.zzGI.getMeasuredWidth();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void onClick(View view) {
        synchronized (this.zzrN) {
            if (this.zzGd != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("x", zzB(this.zzGM));
                    jSONObject.put("y", zzB(this.zzGN));
                } catch (JSONException e) {
                    zzpe.zzbe("Unable to get click location");
                }
                if (this.zzGK == null || !this.zzGK.zzfJ().equals(view)) {
                    this.zzGd.zza(view, this.zzGJ, jSONObject, this.zzGI);
                } else if (!(this.zzGd instanceof zzgt) || ((zzgt) this.zzGd).zzfT() == null) {
                    this.zzGd.zza(view, "1007", jSONObject, this.zzGJ, this.zzGI);
                } else {
                    ((zzgt) this.zzGd).zzfT().zza(view, "1007", jSONObject, this.zzGJ, this.zzGI);
                }
            }
        }
    }

    public void onGlobalLayout() {
        synchronized (this.zzrN) {
            if (this.zzGL) {
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzsc == null)) {
                    this.zzsc.setLayoutParams(new FrameLayout.LayoutParams(measuredWidth, measuredHeight));
                    this.zzGL = false;
                }
            }
            if (this.zzGd != null) {
                this.zzGd.zzd(this.zzGI, this.zzGJ);
            }
        }
    }

    public void onScrollChanged() {
        synchronized (this.zzrN) {
            if (this.zzGd != null) {
                this.zzGd.zzd(this.zzGI, this.zzGJ);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzrN) {
            if (this.zzGd != null) {
                Point zze = zze(motionEvent);
                this.zzGM = zze.x;
                this.zzGN = zze.y;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float) zze.x, (float) zze.y);
                this.zzGd.zzd(obtain);
                obtain.recycle();
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int zzB(int i) {
        return zzeh.zzeO().zzc(this.zzGd.getContext(), i);
    }

    public zzd zzU(String str) {
        View view = null;
        synchronized (this.zzrN) {
            if (this.zzGJ == null) {
                return null;
            }
            WeakReference weakReference = this.zzGJ.get(str);
            if (weakReference != null) {
                view = (View) weakReference.get();
            }
            zzd zzA = zze.zzA(view);
            return zzA;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzgn zzc(zzgv zzgv) {
        return zzgv.zza((View.OnClickListener) this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    public void zzc(String str, zzd zzd) {
        View view = (View) zze.zzE(zzd);
        synchronized (this.zzrN) {
            if (this.zzGJ != null) {
                if (view == null) {
                    this.zzGJ.remove(str);
                } else {
                    this.zzGJ.put(str, new WeakReference(view));
                    view.setOnTouchListener(this);
                    view.setClickable(true);
                    view.setOnClickListener(this);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Point zze(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        this.zzGI.getLocationOnScreen(iArr);
        return new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
    }

    public void zze(zzd zzd) {
        synchronized (this.zzrN) {
            zzj(null);
            Object zzE = zze.zzE(zzd);
            if (!(zzE instanceof zzgv)) {
                zzpe.zzbe("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            if (this.zzsc != null) {
                this.zzsc.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
                this.zzGI.requestLayout();
            }
            this.zzGL = true;
            final zzgv zzgv = (zzgv) zzE;
            if (this.zzGd != null && zzfx.zzDV.get().booleanValue()) {
                this.zzGd.zzc(this.zzGI, this.zzGJ);
            }
            if (!(this.zzGd instanceof zzgt) || !((zzgt) this.zzGd).zzfS()) {
                this.zzGd = zzgv;
                if (zzgv instanceof zzgt) {
                    ((zzgt) zzgv).zzc(null);
                }
            } else {
                ((zzgt) this.zzGd).zzc(zzgv);
            }
            if (zzfx.zzDV.get().booleanValue()) {
                this.zzsc.setClickable(false);
            }
            this.zzsc.removeAllViews();
            this.zzGK = zzc(zzgv);
            if (this.zzGK != null) {
                if (this.zzGJ != null) {
                    this.zzGJ.put("1007", new WeakReference(this.zzGK.zzfJ()));
                }
                this.zzsc.addView(this.zzGK);
            }
            zzgv.zza((View) this.zzGI, this.zzGJ, (View.OnTouchListener) this, (View.OnClickListener) this);
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    zzqp zzfU = zzgv.zzfU();
                    if (!(zzfU == null || zzgy.this.zzsc == null)) {
                        zzgy.this.zzsc.addView(zzfU.getView());
                    }
                    if (!(zzgv instanceof zzgt)) {
                        zzgy.this.zzd(zzgv);
                    }
                }
            });
            zzj(this.zzGI);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzj(@Nullable View view) {
        if (this.zzGd != null) {
            zzgu zzfT = this.zzGd instanceof zzgt ? ((zzgt) this.zzGd).zzfT() : this.zzGd;
            if (zzfT != null) {
                zzfT.zzj(view);
            }
        }
    }
}
