package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzey;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class PublisherAdRequest {
    public static final String DEVICE_ID_EMULATOR = zzey.DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    private final zzey zzrE;

    public static final class Builder {
        /* access modifiers changed from: private */
        public final zzey.zza zzrF = new zzey.zza();

        public Builder addCategoryExclusion(String str) {
            this.zzrF.zzR(str);
            return this;
        }

        public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zzrF.zzb(cls, bundle);
            return this;
        }

        public Builder addCustomTargeting(String str, String str2) {
            this.zzrF.zze(str, str2);
            return this;
        }

        public Builder addCustomTargeting(String str, List<String> list) {
            if (list != null) {
                this.zzrF.zze(str, TextUtils.join(",", list));
            }
            return this;
        }

        public Builder addKeyword(String str) {
            this.zzrF.zzL(str);
            return this;
        }

        public Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zzrF.zza(networkExtras);
            return this;
        }

        public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzrF.zza(cls, bundle);
            return this;
        }

        public Builder addTestDevice(String str) {
            this.zzrF.zzM(str);
            return this;
        }

        public PublisherAdRequest build() {
            return new PublisherAdRequest(this);
        }

        public Builder setBirthday(Date date) {
            this.zzrF.zza(date);
            return this;
        }

        public Builder setContentUrl(String str) {
            zzac.zzb(str, (Object) "Content URL must be non-null.");
            zzac.zzh(str, "Content URL must be non-empty.");
            zzac.zzb(str.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, Integer.valueOf(str.length()));
            this.zzrF.zzO(str);
            return this;
        }

        public Builder setGender(int i) {
            this.zzrF.zzx(i);
            return this;
        }

        public Builder setIsDesignedForFamilies(boolean z) {
            this.zzrF.zzp(z);
            return this;
        }

        public Builder setLocation(Location location) {
            this.zzrF.zzb(location);
            return this;
        }

        @Deprecated
        public Builder setManualImpressionsEnabled(boolean z) {
            this.zzrF.setManualImpressionsEnabled(z);
            return this;
        }

        public Builder setPublisherProvidedId(String str) {
            this.zzrF.zzP(str);
            return this;
        }

        public Builder setRequestAgent(String str) {
            this.zzrF.zzQ(str);
            return this;
        }

        public Builder tagForChildDirectedTreatment(boolean z) {
            this.zzrF.zzo(z);
            return this;
        }
    }

    private PublisherAdRequest(Builder builder) {
        this.zzrE = new zzey(builder.zzrF);
    }

    public static void updateCorrelator() {
    }

    public Date getBirthday() {
        return this.zzrE.getBirthday();
    }

    public String getContentUrl() {
        return this.zzrE.getContentUrl();
    }

    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzrE.getCustomEventExtrasBundle(cls);
    }

    public Bundle getCustomTargeting() {
        return this.zzrE.getCustomTargeting();
    }

    public int getGender() {
        return this.zzrE.getGender();
    }

    public Set<String> getKeywords() {
        return this.zzrE.getKeywords();
    }

    public Location getLocation() {
        return this.zzrE.getLocation();
    }

    public boolean getManualImpressionsEnabled() {
        return this.zzrE.getManualImpressionsEnabled();
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzrE.getNetworkExtras(cls);
    }

    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzrE.getNetworkExtrasBundle(cls);
    }

    public String getPublisherProvidedId() {
        return this.zzrE.getPublisherProvidedId();
    }

    public boolean isTestDevice(Context context) {
        return this.zzrE.isTestDevice(context);
    }

    public zzey zzbq() {
        return this.zzrE;
    }
}
