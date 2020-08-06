package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzif implements zzhx {
    private final Map<String, zza> zzHR = new HashMap();
    private final Object zzrN = new Object();

    public interface zza {
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public void zza(zzqp zzqp, Map<String, String> map) {
        String str = map.get("id");
        String str2 = map.get("fail");
        map.get("fail_reason");
        String str3 = map.get("result");
        synchronized (this.zzrN) {
            if (this.zzHR.remove(str) == null) {
                String valueOf = String.valueOf(str);
                zzpe.zzbe(valueOf.length() != 0 ? "Received result for unexpected method invocation: ".concat(valueOf) : new String("Received result for unexpected method invocation: "));
            } else if (TextUtils.isEmpty(str2)) {
                if (str3 != null) {
                    try {
                        new JSONObject(str3);
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }
            }
        }
    }
}
