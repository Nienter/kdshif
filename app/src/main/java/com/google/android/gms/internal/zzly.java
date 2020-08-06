package com.google.android.gms.internal;

import android.support.p001v4.util.SimpleArrayMap;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.internal.zzlu;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONObject;

@zzmb
public class zzly implements zzlu.zza<zzgr> {
    private final boolean zzQH;

    public zzly(boolean z) {
        this.zzQH = z;
    }

    private void zza(zzlu zzlu, JSONObject jSONObject, SimpleArrayMap<String, Future<zzgo>> simpleArrayMap) {
        simpleArrayMap.put(jSONObject.getString("name"), zzlu.zza(jSONObject, "image_value", this.zzQH));
    }

    private void zza(JSONObject jSONObject, SimpleArrayMap<String, String> simpleArrayMap) {
        simpleArrayMap.put(jSONObject.getString("name"), jSONObject.getString("string_value"));
    }

    private <K, V> SimpleArrayMap<K, V> zzc(SimpleArrayMap<K, Future<V>> simpleArrayMap) {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap<>();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= simpleArrayMap.size()) {
                return simpleArrayMap2;
            }
            simpleArrayMap2.put(simpleArrayMap.keyAt(i2), simpleArrayMap.valueAt(i2).get());
            i = i2 + 1;
        }
    }

    /* renamed from: zzd */
    public zzgr zza(zzlu zzlu, JSONObject jSONObject) {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        zzqf<zzgm> zze = zzlu.zze(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString(ShareConstants.MEDIA_TYPE);
            if ("string".equals(string)) {
                zza(jSONObject2, (SimpleArrayMap<String, String>) simpleArrayMap2);
            } else if ("image".equals(string)) {
                zza(zzlu, jSONObject2, simpleArrayMap);
            } else {
                String valueOf = String.valueOf(string);
                zzpe.zzbe(valueOf.length() != 0 ? "Unknown custom asset type: ".concat(valueOf) : new String("Unknown custom asset type: "));
            }
        }
        return new zzgr(jSONObject.getString("custom_template_id"), zzc(simpleArrayMap), simpleArrayMap2, (zzgm) zze.get());
    }
}
