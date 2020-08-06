package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.C0955R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzz;

@Deprecated
public final class zzaas {
    private static zzaas zzaBn;
    private static final Object zztU = new Object();
    private final String zzVQ;
    private final Status zzaBo;
    private final boolean zzaBp;
    private final boolean zzaBq;

    zzaas(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(C0955R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            this.zzaBq = z2 ? false : z;
            z = z2;
        } else {
            this.zzaBq = false;
        }
        this.zzaBp = z;
        String zzaD = zzz.zzaD(context);
        zzaD = zzaD == null ? new zzam(context).getString("google_app_id") : zzaD;
        if (TextUtils.isEmpty(zzaD)) {
            this.zzaBo = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzVQ = null;
            return;
        }
        this.zzVQ = zzaD;
        this.zzaBo = Status.zzayh;
    }

    public static Status zzay(Context context) {
        Status status;
        zzac.zzb(context, (Object) "Context must not be null.");
        synchronized (zztU) {
            if (zzaBn == null) {
                zzaBn = new zzaas(context);
            }
            status = zzaBn.zzaBo;
        }
        return status;
    }

    private static zzaas zzdc(String str) {
        zzaas zzaas;
        synchronized (zztU) {
            if (zzaBn == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            zzaas = zzaBn;
        }
        return zzaas;
    }

    public static String zzwj() {
        return zzdc("getGoogleAppId").zzVQ;
    }

    public static boolean zzwk() {
        return zzdc("isMeasurementExplicitlyDisabled").zzaBq;
    }
}
