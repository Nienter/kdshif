package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzmb
public class zzeb {
    public static final zzeb zzzj = new zzeb();

    protected zzeb() {
    }

    public static zzeb zzey() {
        return zzzj;
    }

    public zzdy zza(Context context, zzey zzey) {
        Date birthday = zzey.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzey.getContentUrl();
        int gender = zzey.getGender();
        Set<String> keywords = zzey.getKeywords();
        List unmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean isTestDevice = zzey.isTestDevice(context);
        int zzfa = zzey.zzfa();
        Location location = zzey.getLocation();
        Bundle networkExtrasBundle = zzey.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzey.getManualImpressionsEnabled();
        String publisherProvidedId = zzey.getPublisherProvidedId();
        SearchAdRequest zzeX = zzey.zzeX();
        zzfj zzfj = zzeX != null ? new zzfj(zzeX) : null;
        String str = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            str = zzeh.zzeO().zza(Thread.currentThread().getStackTrace(), applicationContext.getPackageName());
        }
        return new zzdy(7, time, networkExtrasBundle, gender, unmodifiableList, isTestDevice, zzfa, manualImpressionsEnabled, publisherProvidedId, zzfj, location, contentUrl, zzey.zzeZ(), zzey.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzey.zzfb())), zzey.zzeW(), str, zzey.isDesignedForFamilies());
    }

    public zznx zza(Context context, zzey zzey, String str) {
        return new zznx(zza(context, zzey), str);
    }
}
