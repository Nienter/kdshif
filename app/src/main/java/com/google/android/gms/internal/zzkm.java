package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzkm {
    private final boolean zzLJ;
    private final boolean zzLK;
    private final boolean zzLL;
    private final boolean zzLM;
    private final boolean zzLN;

    public static final class zza {
        /* access modifiers changed from: private */
        public boolean zzLJ;
        /* access modifiers changed from: private */
        public boolean zzLK;
        /* access modifiers changed from: private */
        public boolean zzLL;
        /* access modifiers changed from: private */
        public boolean zzLM;
        /* access modifiers changed from: private */
        public boolean zzLN;

        public zzkm zzgT() {
            return new zzkm(this);
        }

        public zza zzt(boolean z) {
            this.zzLJ = z;
            return this;
        }

        public zza zzu(boolean z) {
            this.zzLK = z;
            return this;
        }

        public zza zzv(boolean z) {
            this.zzLL = z;
            return this;
        }

        public zza zzw(boolean z) {
            this.zzLM = z;
            return this;
        }

        public zza zzx(boolean z) {
            this.zzLN = z;
            return this;
        }
    }

    private zzkm(zza zza2) {
        this.zzLJ = zza2.zzLJ;
        this.zzLK = zza2.zzLK;
        this.zzLL = zza2.zzLL;
        this.zzLM = zza2.zzLM;
        this.zzLN = zza2.zzLN;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject().put("sms", this.zzLJ).put("tel", this.zzLK).put("calendar", this.zzLL).put("storePicture", this.zzLM).put("inlineVideo", this.zzLN);
        } catch (JSONException e) {
            zzpe.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
