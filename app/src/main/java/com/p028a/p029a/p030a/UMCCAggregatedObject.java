package com.p028a.p029a.p030a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.a.a.a.cn */
public class UMCCAggregatedObject implements Serializable {

    /* renamed from: b */
    private List<String> f1513b = new ArrayList();

    /* renamed from: c */
    private List<String> f1514c = new ArrayList();

    /* renamed from: d */
    private long f1515d = 0;

    /* renamed from: e */
    private long f1516e = 0;

    /* renamed from: f */
    private long f1517f = 0;

    /* renamed from: g */
    private String f1518g = null;

    public UMCCAggregatedObject() {
    }

    public UMCCAggregatedObject(List<String> list, long j, long j2, long j3, List<String> list2, String str) {
        this.f1513b = list;
        this.f1514c = list2;
        this.f1515d = j;
        this.f1516e = j2;
        this.f1517f = j3;
        this.f1518g = str;
    }

    /* renamed from: a */
    public void mo9492a(String str) {
        try {
            if (this.f1514c.size() < UMCCAggregatedRestrictionManager.m2196a().mo9528b()) {
                this.f1514c.add(str);
            } else {
                this.f1514c.remove(this.f1514c.get(0));
                this.f1514c.add(str);
            }
            if (this.f1514c.size() > UMCCAggregatedRestrictionManager.m2196a().mo9528b()) {
                for (int i = 0; i < this.f1514c.size() - UMCCAggregatedRestrictionManager.m2196a().mo9528b(); i++) {
                    this.f1514c.remove(this.f1514c.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo9490a(C0614cl clVar, UMCCVerbatimObject cpVar) {
        mo9492a(cpVar.mo9515b());
        this.f1517f++;
        this.f1516e += cpVar.mo9516c();
        this.f1515d += cpVar.mo9517d();
        clVar.mo9396a(this, false);
    }

    /* renamed from: a */
    public void mo9491a(UMCCVerbatimObject cpVar) {
        this.f1517f = 1;
        this.f1513b = cpVar.mo9514a();
        mo9492a(cpVar.mo9515b());
        this.f1516e = cpVar.mo9516c();
        this.f1515d = System.currentTimeMillis();
        this.f1518g = UMCCTimeRange.m2222a(System.currentTimeMillis());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[key: ").append(this.f1513b).append("] [label: ").append(this.f1514c).append("][ totalTimeStamp").append(this.f1518g).append("][ value").append(this.f1516e).append("][ count").append(this.f1517f).append("][ timeWindowNum").append(this.f1518g).append("]");
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public String mo9488a() {
        return UMCCDBUtils.m2039a(this.f1513b);
    }

    /* renamed from: b */
    public List<String> mo9494b() {
        return this.f1513b;
    }

    /* renamed from: c */
    public String mo9498c() {
        return UMCCDBUtils.m2039a(this.f1514c);
    }

    /* renamed from: d */
    public List<String> mo9500d() {
        return this.f1514c;
    }

    /* renamed from: e */
    public long mo9501e() {
        return this.f1515d;
    }

    /* renamed from: f */
    public long mo9502f() {
        return this.f1516e;
    }

    /* renamed from: g */
    public long mo9503g() {
        return this.f1517f;
    }

    /* renamed from: h */
    public String mo9504h() {
        return this.f1518g;
    }

    /* renamed from: a */
    public void mo9493a(List<String> list) {
        this.f1513b = list;
    }

    /* renamed from: b */
    public void mo9497b(List<String> list) {
        this.f1514c = list;
    }

    /* renamed from: a */
    public void mo9489a(long j) {
        this.f1515d = j;
    }

    /* renamed from: b */
    public void mo9495b(long j) {
        this.f1516e = j;
    }

    /* renamed from: c */
    public void mo9499c(long j) {
        this.f1517f = j;
    }

    /* renamed from: b */
    public void mo9496b(String str) {
        this.f1518g = str;
    }
}
