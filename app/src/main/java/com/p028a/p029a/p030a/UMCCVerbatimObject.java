package com.p028a.p029a.p030a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.a.a.a.cp */
public class UMCCVerbatimObject implements Serializable {

    /* renamed from: b */
    private List<String> f1523b = new ArrayList();

    /* renamed from: c */
    private String f1524c;

    /* renamed from: d */
    private long f1525d;

    /* renamed from: e */
    private long f1526e;

    /* renamed from: f */
    private String f1527f;

    public UMCCVerbatimObject(List<String> list, long j, String str, long j2) {
        this.f1523b = list;
        this.f1525d = j;
        this.f1524c = str;
        this.f1526e = j2;
        m2152f();
    }

    /* renamed from: f */
    private void m2152f() {
        this.f1527f = UMCCTimeRange.m2222a(this.f1526e);
    }

    /* renamed from: a */
    public List<String> mo9514a() {
        return this.f1523b;
    }

    /* renamed from: b */
    public String mo9515b() {
        return this.f1524c;
    }

    /* renamed from: c */
    public long mo9516c() {
        return this.f1525d;
    }

    /* renamed from: d */
    public long mo9517d() {
        return this.f1526e;
    }

    /* renamed from: e */
    public String mo9518e() {
        return this.f1527f;
    }
}
