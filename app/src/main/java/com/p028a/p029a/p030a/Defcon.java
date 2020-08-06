package com.p028a.p029a.p030a;

import android.content.Context;
import com.p028a.p029a.p030a.ImprintHandler;
import org.json.JSONObject;

/* renamed from: com.a.a.a.ah */
public class Defcon implements OnImprintChangedListener {

    /* renamed from: c */
    private static Defcon f1205c = null;

    /* renamed from: a */
    private int f1206a = 0;

    /* renamed from: b */
    private final long f1207b = 60000;

    /* renamed from: a */
    public static synchronized Defcon m1388a(Context context) {
        Defcon ahVar;
        synchronized (Defcon.class) {
            if (f1205c == null) {
                f1205c = new Defcon();
                f1205c.mo9112a(ImprintHandler.m2284a(context).mo9583b().mo9587a(0));
            }
            ahVar = f1205c;
        }
        return ahVar;
    }

    private Defcon() {
    }

    /* renamed from: a */
    public void mo9113a(JSONObject jSONObject, Context context) {
        if (this.f1206a == 1) {
            jSONObject.remove("error");
            jSONObject.remove("ekv");
            jSONObject.remove("gkv");
            jSONObject.remove("cc");
            UMStoreManager.m2242a(context).mo9557a(false, true);
            UMCCAggregatedManager.m2160a(context).mo9523b(new C0614cl());
        } else if (this.f1206a == 2) {
            jSONObject.remove("sessions");
            try {
                jSONObject.put("sessions", mo9111a());
            } catch (Exception e) {
            }
            jSONObject.remove("error");
            jSONObject.remove("ekv");
            jSONObject.remove("gkv");
            jSONObject.remove("cc");
            UMStoreManager.m2242a(context).mo9557a(false, true);
            UMCCAggregatedManager.m2160a(context).mo9523b(new C0614cl());
        } else if (this.f1206a == 3) {
            jSONObject.remove("sessions");
            jSONObject.remove("error");
            jSONObject.remove("ekv");
            jSONObject.remove("gkv");
            jSONObject.remove("cc");
            UMStoreManager.m2242a(context).mo9557a(false, true);
            UMCCAggregatedManager.m2160a(context).mo9523b(new C0614cl());
        }
    }

    /* renamed from: a */
    public JSONObject mo9111a() {
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("id", SessionTracker.m1349a());
            jSONObject.put("start_time", currentTimeMillis);
            jSONObject.put("end_time", currentTimeMillis + 60000);
            jSONObject.put("duration", 60000);
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    /* renamed from: b */
    public long mo9114b() {
        switch (this.f1206a) {
            case 1:
                return 14400000;
            case 2:
                return 28800000;
            case 3:
                return 86400000;
            default:
                return 0;
        }
    }

    /* renamed from: a */
    public void mo9112a(int i) {
        if (i >= 0 && i <= 3) {
            this.f1206a = i;
        }
    }

    /* renamed from: c */
    public boolean mo9115c() {
        return this.f1206a != 0;
    }

    /* renamed from: a */
    public void mo9104a(ImprintHandler.C0639a aVar) {
        mo9112a(aVar.mo9587a(0));
    }
}
