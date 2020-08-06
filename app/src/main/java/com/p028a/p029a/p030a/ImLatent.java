package com.p028a.p029a.p030a;

import android.content.Context;
import com.p028a.p029a.AnalyticsConfig;
import com.p028a.p029a.p030a.ImprintHandler;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

/* renamed from: com.a.a.a.ai */
public class ImLatent implements OnImprintChangedListener {

    /* renamed from: l */
    private static ImLatent f1208l = null;

    /* renamed from: a */
    private final long f1209a = 1296000000;

    /* renamed from: b */
    private final long f1210b = 129600000;

    /* renamed from: c */
    private final int f1211c = 1800000;

    /* renamed from: d */
    private final int f1212d = AbstractSpiCall.DEFAULT_TIMEOUT;

    /* renamed from: e */
    private StoreHelper f1213e;

    /* renamed from: f */
    private StatTracer f1214f;

    /* renamed from: g */
    private long f1215g = 1296000000;

    /* renamed from: h */
    private int f1216h = AbstractSpiCall.DEFAULT_TIMEOUT;

    /* renamed from: i */
    private long f1217i = 0;

    /* renamed from: j */
    private long f1218j = 0;

    /* renamed from: k */
    private Context f1219k;

    /* renamed from: a */
    public static synchronized ImLatent m1395a(Context context, StatTracer aeVar) {
        ImLatent aiVar;
        synchronized (ImLatent.class) {
            if (f1208l == null) {
                f1208l = new ImLatent(context, aeVar);
                f1208l.mo9104a(ImprintHandler.m2284a(context).mo9583b());
            }
            aiVar = f1208l;
        }
        return aiVar;
    }

    private ImLatent(Context context, StatTracer aeVar) {
        this.f1219k = context;
        this.f1213e = StoreHelper.m1888a(context);
        this.f1214f = aeVar;
    }

    /* renamed from: a */
    public boolean mo9116a() {
        if (this.f1213e.mo9394f() || this.f1214f.mo9093e()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f1214f.mo9100l();
        if (currentTimeMillis > this.f1215g) {
            this.f1217i = (long) DataHelper.m1771a(this.f1216h, Envelope.m1875a(this.f1219k));
            this.f1218j = currentTimeMillis;
            return true;
        } else if (currentTimeMillis <= 129600000) {
            return false;
        } else {
            this.f1217i = 0;
            this.f1218j = currentTimeMillis;
            return true;
        }
    }

    /* renamed from: b */
    public long mo9117b() {
        return this.f1217i;
    }

    /* renamed from: a */
    public void mo9104a(ImprintHandler.C0639a aVar) {
        this.f1215g = aVar.mo9588a(1296000000);
        int b = aVar.mo9593b(0);
        if (b != 0) {
            this.f1216h = b;
        } else if (AnalyticsConfig.f1164i <= 0 || AnalyticsConfig.f1164i > 1800000) {
            this.f1216h = AbstractSpiCall.DEFAULT_TIMEOUT;
        } else {
            this.f1216h = AnalyticsConfig.f1164i;
        }
    }
}
