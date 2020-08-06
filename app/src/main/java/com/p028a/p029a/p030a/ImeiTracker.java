package com.p028a.p029a.p030a;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;

/* renamed from: com.a.a.a.f */
public class ImeiTracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1593a;

    public ImeiTracker(Context context) {
        super("imei");
        this.f1593a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f1593a.getSystemService(PlaceFields.PHONE);
        if (telephonyManager == null) {
        }
        try {
            if (DeviceConfig.m1791a(this.f1593a, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
