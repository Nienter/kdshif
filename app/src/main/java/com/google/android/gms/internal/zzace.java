package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzq;

public class zzace {
    private final String mTag;
    private final String zzaEV;
    private final zzq zzaFr;
    private final int zzaeb;

    private zzace(String str, String str2) {
        this.zzaEV = str2;
        this.mTag = str;
        this.zzaFr = new zzq(str);
        this.zzaeb = getLogLevel();
    }

    public zzace(String str, String... strArr) {
        this(str, zzd(strArr));
    }

    private int getLogLevel() {
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        return i;
    }

    private static String zzd(String... strArr) {
        if (strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (String str : strArr) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append(str);
        }
        sb.append(']').append(' ');
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String format(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return this.zzaEV.concat(str);
    }

    public void zza(String str, Object... objArr) {
        if (zzai(2)) {
            Log.v(this.mTag, format(str, objArr));
        }
    }

    public boolean zzai(int i) {
        return this.zzaeb <= i;
    }

    public void zzb(String str, Object... objArr) {
        if (zzai(3)) {
            Log.d(this.mTag, format(str, objArr));
        }
    }

    public void zze(String str, Object... objArr) {
        Log.i(this.mTag, format(str, objArr));
    }

    public void zzf(String str, Object... objArr) {
        Log.w(this.mTag, format(str, objArr));
    }
}
