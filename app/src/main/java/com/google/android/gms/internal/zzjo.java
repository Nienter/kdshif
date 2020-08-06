package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@zzmb
public class zzjo {
    public List<String> zza(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public void zza(Context context, String str, zzov zzov, String str2, boolean z, List<String> list) {
        if (list != null && !list.isEmpty()) {
            String str3 = z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
            for (String replaceAll : list) {
                String replaceAll2 = replaceAll.replaceAll("@gw_adlocid@", str2).replaceAll("@gw_adnetrefresh@", str3).replaceAll("@gw_qdata@", zzov.zzVr.zzKd).replaceAll("@gw_sdkver@", str).replaceAll("@gw_sessid@", zzv.zzcN().getSessionId()).replaceAll("@gw_seqnum@", zzov.zzRg);
                if (!TextUtils.isEmpty(zzov.zzVs)) {
                    replaceAll2 = replaceAll2.replaceAll("@gw_adnetstatus@", zzov.zzVs);
                }
                if (zzov.zzKA != null) {
                    replaceAll2 = replaceAll2.replaceAll("@gw_adnetid@", zzov.zzKA.zzJH).replaceAll("@gw_allocid@", zzov.zzKA.zzJJ);
                }
                new zzps(context, str, replaceAll2).zziw();
            }
        }
    }
}
