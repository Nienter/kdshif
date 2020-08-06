package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

public class PlaceReport extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator<PlaceReport> CREATOR = new zzl();
    private final String mTag;
    final int mVersionCode;
    private final String zzabN;
    private final String zzblg;

    PlaceReport(int i, String str, String str2, String str3) {
        this.mVersionCode = i;
        this.zzblg = str;
        this.mTag = str2;
        this.zzabN = str3;
    }

    public static PlaceReport create(String str, String str2) {
        return zzj(str, str2, "unknown");
    }

    private static boolean zzeY(String str) {
        char c = 65535;
        switch (str.hashCode()) {
            case -1436706272:
                if (str.equals("inferredGeofencing")) {
                    c = 2;
                    break;
                }
                break;
            case -1194968642:
                if (str.equals("userReported")) {
                    c = 1;
                    break;
                }
                break;
            case -284840886:
                if (str.equals("unknown")) {
                    c = 0;
                    break;
                }
                break;
            case -262743844:
                if (str.equals("inferredReverseGeocoding")) {
                    c = 4;
                    break;
                }
                break;
            case 1164924125:
                if (str.equals("inferredSnappedToRoad")) {
                    c = 5;
                    break;
                }
                break;
            case 1287171955:
                if (str.equals("inferredRadioSignals")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    public static PlaceReport zzj(String str, String str2, String str3) {
        zzac.zzw(str);
        zzac.zzdv(str2);
        zzac.zzdv(str3);
        zzac.zzb(zzeY(str3), (Object) "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzaa.equal(this.zzblg, placeReport.zzblg) && zzaa.equal(this.mTag, placeReport.mTag) && zzaa.equal(this.zzabN, placeReport.zzabN);
    }

    public String getPlaceId() {
        return this.zzblg;
    }

    public String getSource() {
        return this.zzabN;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return zzaa.hashCode(this.zzblg, this.mTag, this.zzabN);
    }

    public String toString() {
        zzaa.zza zzv = zzaa.zzv(this);
        zzv.zzg("placeId", this.zzblg);
        zzv.zzg("tag", this.mTag);
        if (!"unknown".equals(this.zzabN)) {
            zzv.zzg(ShareConstants.FEED_SOURCE_PARAM, this.zzabN);
        }
        return zzv.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzl.zza(this, parcel, i);
    }
}
