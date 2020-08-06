package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.a.a.a.ae */
public class StatTracer implements IRequestStat {

    /* renamed from: a */
    public int f1186a;

    /* renamed from: b */
    public int f1187b;

    /* renamed from: c */
    public long f1188c;

    /* renamed from: d */
    private final int f1189d = 3600000;

    /* renamed from: e */
    private int f1190e;

    /* renamed from: f */
    private long f1191f = 0;

    /* renamed from: g */
    private long f1192g = 0;

    /* renamed from: h */
    private Context f1193h;

    public StatTracer(Context context) {
        m1360a(context);
    }

    /* renamed from: a */
    private void m1360a(Context context) {
        this.f1193h = context.getApplicationContext();
        SharedPreferences a = PreferenceWrapper.m1333a(context);
        this.f1186a = a.getInt("successful_request", 0);
        this.f1187b = a.getInt("failed_requests ", 0);
        this.f1190e = a.getInt("last_request_spent_ms", 0);
        this.f1188c = a.getLong("last_request_time", 0);
        this.f1191f = a.getLong("last_req", 0);
    }

    /* renamed from: e */
    public boolean mo9093e() {
        boolean z;
        boolean z2;
        if (this.f1188c == 0) {
            z = true;
        } else {
            z = false;
        }
        if (!StoreHelper.m1888a(this.f1193h).mo9394f()) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    /* renamed from: f */
    public void mo9094f() {
        this.f1186a++;
        this.f1188c = this.f1191f;
    }

    /* renamed from: g */
    public void mo9095g() {
        this.f1187b++;
    }

    /* renamed from: h */
    public void mo9096h() {
        this.f1191f = System.currentTimeMillis();
    }

    /* renamed from: i */
    public void mo9097i() {
        this.f1190e = (int) (System.currentTimeMillis() - this.f1191f);
    }

    /* renamed from: j */
    public void mo9098j() {
        PreferenceWrapper.m1333a(this.f1193h).edit().putInt("successful_request", this.f1186a).putInt("failed_requests ", this.f1187b).putInt("last_request_spent_ms", this.f1190e).putLong("last_request_time", this.f1188c).putLong("last_req", this.f1191f).commit();
    }

    /* renamed from: k */
    public long mo9099k() {
        SharedPreferences a = PreferenceWrapper.m1333a(this.f1193h);
        this.f1192g = PreferenceWrapper.m1333a(this.f1193h).getLong("first_activate_time", 0);
        if (this.f1192g == 0) {
            this.f1192g = System.currentTimeMillis();
            a.edit().putLong("first_activate_time", this.f1192g).commit();
        }
        return this.f1192g;
    }

    /* renamed from: l */
    public long mo9100l() {
        return this.f1191f;
    }

    /* renamed from: a */
    public void mo9089a() {
        mo9096h();
    }

    /* renamed from: b */
    public void mo9090b() {
        mo9097i();
    }

    /* renamed from: c */
    public void mo9091c() {
        mo9094f();
    }

    /* renamed from: d */
    public void mo9092d() {
        mo9095g();
    }
}
