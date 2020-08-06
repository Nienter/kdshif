package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

@zzmb
public class zzdo extends zza {
    public static final Parcelable.Creator<zzdo> CREATOR = new zzdp();
    @Nullable
    public final String url;
    public final int version;
    public final String zzyA;
    public final Bundle zzyB;
    public final boolean zzyC;
    public final long zzyx;
    public final String zzyy;
    public final String zzyz;

    zzdo(int i, @Nullable String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z) {
        this.version = i;
        this.url = str;
        this.zzyx = j;
        this.zzyy = str2 == null ? "" : str2;
        this.zzyz = str3 == null ? "" : str3;
        this.zzyA = str4 == null ? "" : str4;
        this.zzyB = bundle == null ? new Bundle() : bundle;
        this.zzyC = z;
    }

    @Nullable
    public static zzdo zzJ(String str) {
        return zze(Uri.parse(str));
    }

    @Nullable
    public static zzdo zze(Uri uri) {
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                zzpe.zzbe(new StringBuilder(62).append("Expected 2 path parts for namespace and id, found :").append(pathSegments.size()).toString());
                return null;
            }
            String str = pathSegments.get(0);
            String str2 = pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long parseLong = queryParameter2 == null ? 0 : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String next : zzv.zzcL().zzh(uri)) {
                if (next.startsWith("tag.")) {
                    bundle.putString(next.substring("tag.".length()), uri.getQueryParameter(next));
                }
            }
            return new zzdo(1, queryParameter, parseLong, host, str, str2, bundle, equals);
        } catch (NullPointerException | NumberFormatException e) {
            zzpe.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzdp.zza(this, parcel, i);
    }
}
