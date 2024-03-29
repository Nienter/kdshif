package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.C0955R;

public class zzam {
    private final Resources zzaFn;
    private final String zzaFo = this.zzaFn.getResourcePackageName(C0955R.string.common_google_play_services_unknown_issue);

    public zzam(Context context) {
        zzac.zzw(context);
        this.zzaFn = context.getResources();
    }

    public String getString(String str) {
        int identifier = this.zzaFn.getIdentifier(str, "string", this.zzaFo);
        if (identifier == 0) {
            return null;
        }
        return this.zzaFn.getString(identifier);
    }
}
