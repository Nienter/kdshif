package com.p028a.p029a.p030a;

import com.facebook.appevents.AppEventsConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.a.a.a.da */
public abstract class AbstractIdTracker {

    /* renamed from: a */
    private final int f1577a = 10;

    /* renamed from: b */
    private final int f1578b = 20;

    /* renamed from: c */
    private final String f1579c;

    /* renamed from: d */
    private List<IdJournal> f1580d;

    /* renamed from: e */
    private IdSnapshot f1581e;

    /* renamed from: a */
    public abstract String mo9458a();

    public AbstractIdTracker(String str) {
        this.f1579c = str;
    }

    /* renamed from: e */
    public boolean mo9564e() {
        return mo9601b();
    }

    /* renamed from: f */
    public String mo9565f() {
        return this.f1579c;
    }

    /* renamed from: g */
    public boolean mo9566g() {
        if (this.f1581e == null || this.f1581e.mo9172i() <= 20) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private boolean mo9601b() {
        IdSnapshot akVar = this.f1581e;
        String c = akVar == null ? null : akVar.mo9165c();
        int i = akVar == null ? 0 : akVar.mo9172i();
        String a = mo9561a(mo9458a());
        if (a == null || a.equals(c)) {
            return false;
        }
        if (akVar == null) {
            akVar = new IdSnapshot();
        }
        akVar.mo9161a(a);
        akVar.mo9160a(System.currentTimeMillis());
        akVar.mo9159a(i + 1);
        IdJournal ajVar = new IdJournal();
        ajVar.mo9121a(this.f1579c);
        ajVar.mo9129c(a);
        ajVar.mo9124b(c);
        ajVar.mo9120a(akVar.mo9169f());
        if (this.f1580d == null) {
            this.f1580d = new ArrayList(2);
        }
        this.f1580d.add(ajVar);
        if (this.f1580d.size() > 10) {
            this.f1580d.remove(0);
        }
        this.f1581e = akVar;
        return true;
    }

    /* renamed from: h */
    public IdSnapshot mo9567h() {
        return this.f1581e;
    }

    /* renamed from: i */
    public List<IdJournal> mo9568i() {
        return this.f1580d;
    }

    /* renamed from: a */
    public void mo9563a(List<IdJournal> list) {
        this.f1580d = list;
    }

    /* renamed from: a */
    public String mo9561a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(trim) || "unknown".equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }

    /* renamed from: a */
    public void mo9562a(IdTracking alVar) {
        this.f1581e = alVar.mo9196d().get(this.f1579c);
        List<IdJournal> i = alVar.mo9201i();
        if (i != null && i.size() > 0) {
            if (this.f1580d == null) {
                this.f1580d = new ArrayList();
            }
            for (IdJournal next : i) {
                if (this.f1579c.equals(next.f1227a)) {
                    this.f1580d.add(next);
                }
            }
        }
    }
}
