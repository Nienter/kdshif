package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

@zzmb
public class zzon extends zza {
    public static final Parcelable.Creator<zzon> CREATOR = new zzoo();
    public final int versionCode;
    public final String zzVk;
    public final String zzVl;
    public final boolean zzVm;
    public final boolean zzVn;
    public final List<String> zzVo;

    public zzon(int i, String str, String str2, boolean z, boolean z2, List<String> list) {
        this.versionCode = i;
        this.zzVk = str;
        this.zzVl = str2;
        this.zzVm = z;
        this.zzVn = z2;
        this.zzVo = list;
    }

    @Nullable
    public static zzon zzi(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("click_string", "");
        String optString2 = jSONObject.optString("report_url", "");
        boolean optBoolean = jSONObject.optBoolean("rendered_ad_enabled", false);
        boolean optBoolean2 = jSONObject.optBoolean("non_malicious_reporting_enabled", false);
        JSONArray optJSONArray = jSONObject.optJSONArray("allowed_headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            String optString3 = optJSONArray.optString(i);
            if (!TextUtils.isEmpty(optString3)) {
                arrayList.add(optString3.toLowerCase(Locale.ENGLISH));
            }
        }
        return new zzon(2, optString, optString2, optBoolean, optBoolean2, arrayList);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzoo.zza(this, parcel, i);
    }
}
