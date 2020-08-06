package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.C0955R;
import com.google.android.gms.ads.internal.zzv;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@zzmb
public class zzpl {
    /* access modifiers changed from: private */
    public final Context mContext;
    private int mState;
    private final float zzLP;
    private String zzWZ;
    private float zzXa;
    private float zzXb;
    private float zzXc;
    /* access modifiers changed from: private */
    public String zztq;

    public zzpl(Context context) {
        this.mState = 0;
        this.mContext = context;
        this.zzLP = context.getResources().getDisplayMetrics().density;
    }

    public zzpl(Context context, String str) {
        this(context);
        this.zzWZ = str;
    }

    private int zza(List<String> list, String str, boolean z) {
        if (!z) {
            return -1;
        }
        list.add(str);
        return list.size() - 1;
    }

    static String zzaZ(String str) {
        if (TextUtils.isEmpty(str)) {
            return "No debug information";
        }
        Uri build = new Uri.Builder().encodedQuery(str.replaceAll("\\+", "%20")).build();
        StringBuilder sb = new StringBuilder();
        Map<String, String> zzg = zzv.zzcJ().zzg(build);
        for (String next : zzg.keySet()) {
            sb.append(next).append(" = ").append(zzg.get(next)).append("\n\n");
        }
        String trim = sb.toString().trim();
        return TextUtils.isEmpty(trim) ? "No debug information" : trim;
    }

    private void zzku() {
        if (!(this.mContext instanceof Activity)) {
            zzpe.zzbd("Can not create dialog without Activity Context");
            return;
        }
        Resources resources = zzv.zzcN().getResources();
        String string = resources != null ? resources.getString(C0955R.string.debug_menu_title) : "Select a Debug Mode";
        String string2 = resources != null ? resources.getString(C0955R.string.debug_menu_ad_information) : "Ad Information";
        String string3 = resources != null ? resources.getString(C0955R.string.debug_menu_creative_preview) : "Creative Preview";
        String string4 = resources != null ? resources.getString(C0955R.string.debug_menu_troubleshooting) : "Troubleshooting";
        ArrayList arrayList = new ArrayList();
        final int zza = zza((List<String>) arrayList, string2, true);
        final int zza2 = zza((List<String>) arrayList, string3, zzfx.zzEQ.get().booleanValue());
        final int zza3 = zza((List<String>) arrayList, string4, zzfx.zzER.get().booleanValue());
        new AlertDialog.Builder(this.mContext).setTitle(string).setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == zza) {
                    zzpl.this.zzkv();
                } else if (i == zza2 && zzfx.zzEQ.get().booleanValue()) {
                    zzpl.this.zzkw();
                } else if (i == zza3 && zzfx.zzER.get().booleanValue()) {
                    zzpl.this.zzkx();
                }
            }
        }).create().show();
    }

    /* access modifiers changed from: private */
    public void zzkv() {
        if (!(this.mContext instanceof Activity)) {
            zzpe.zzbd("Can not create dialog without Activity Context");
            return;
        }
        final String zzaZ = zzaZ(this.zzWZ);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setMessage(zzaZ);
        builder.setTitle("Ad Information");
        builder.setPositiveButton("Share", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                zzv.zzcJ().zzb(zzpl.this.mContext, Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", zzaZ), "Share via"));
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener(this) {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void zzkw() {
        zzpe.zzbc("Debug mode [Creative Preview] selected.");
        zzph.zza((Runnable) new Runnable() {
            public void run() {
                zzv.zzcR().zzj(zzpl.this.mContext, zzpl.this.zztq);
            }
        });
    }

    /* access modifiers changed from: private */
    public void zzkx() {
        zzpe.zzbc("Debug mode [Troubleshooting] selected.");
        zzph.zza((Runnable) new Runnable() {
            public void run() {
                zzv.zzcR().zzk(zzpl.this.mContext, zzpl.this.zztq);
            }
        });
    }

    public void setAdUnitId(String str) {
        this.zztq = str;
    }

    public void showDialog() {
        if (zzfx.zzER.get().booleanValue() || zzfx.zzEQ.get().booleanValue()) {
            zzku();
        } else {
            zzkv();
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(int i, float f, float f2) {
        if (i == 0) {
            this.mState = 0;
            this.zzXa = f;
            this.zzXb = f2;
            this.zzXc = f2;
        } else if (this.mState == -1) {
        } else {
            if (i == 2) {
                if (f2 > this.zzXb) {
                    this.zzXb = f2;
                } else if (f2 < this.zzXc) {
                    this.zzXc = f2;
                }
                if (this.zzXb - this.zzXc > 30.0f * this.zzLP) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (f - this.zzXa >= 50.0f * this.zzLP) {
                        this.zzXa = f;
                        this.mState++;
                    }
                } else if ((this.mState == 1 || this.mState == 3) && f - this.zzXa <= -50.0f * this.zzLP) {
                    this.zzXa = f;
                    this.mState++;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (f > this.zzXa) {
                        this.zzXa = f;
                    }
                } else if (this.mState == 2 && f < this.zzXa) {
                    this.zzXa = f;
                }
            } else if (i == 1 && this.mState == 4) {
                showDialog();
            }
        }
    }

    public void zzaY(String str) {
        this.zzWZ = str;
    }

    public void zzg(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
