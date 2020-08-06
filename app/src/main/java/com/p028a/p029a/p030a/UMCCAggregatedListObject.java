package com.p028a.p029a.p030a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.a.a.a.cm */
public class UMCCAggregatedListObject implements Serializable {
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Map<List<String>, UMCCAggregatedObject> f1509b = new HashMap();

    /* renamed from: c */
    private long f1510c = 0;

    /* renamed from: a */
    public Map<List<String>, UMCCAggregatedObject> mo9476a() {
        return this.f1509b;
    }

    /* renamed from: a */
    public void mo9482a(Map<List<String>, UMCCAggregatedObject> map) {
        if (this.f1509b.size() <= 0) {
            this.f1509b = map;
        } else {
            m2113b(map);
        }
    }

    /* renamed from: b */
    private void m2113b(Map<List<String>, UMCCAggregatedObject> map) {
        new ArrayList();
        new ArrayList();
        Iterator<Map.Entry<List<String>, UMCCAggregatedObject>> it = this.f1509b.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            List list = (List) next.getKey();
            Iterator<Map.Entry<List<String>, UMCCAggregatedObject>> it2 = this.f1509b.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next2 = it2.next();
                List list2 = (List) next.getKey();
                if (!list.equals(list2)) {
                    this.f1509b.put(list2, next2.getValue());
                } else {
                    UMCCAggregatedObject cnVar = (UMCCAggregatedObject) next2.getValue();
                    m2112a((UMCCAggregatedObject) next.getValue(), cnVar);
                    this.f1509b.remove(list);
                    this.f1509b.put(list, cnVar);
                }
            }
        }
    }

    /* renamed from: a */
    private void m2112a(UMCCAggregatedObject cnVar, UMCCAggregatedObject cnVar2) {
        cnVar2.mo9499c(cnVar2.mo9503g() + cnVar.mo9503g());
        cnVar2.mo9495b(cnVar2.mo9502f() + cnVar.mo9502f());
        cnVar2.mo9489a(cnVar2.mo9501e() + cnVar.mo9501e());
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < cnVar.mo9500d().size()) {
                cnVar2.mo9492a(cnVar.mo9500d().get(i2));
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    public long mo9485b() {
        return this.f1510c;
    }

    /* renamed from: a */
    public void mo9477a(long j) {
        this.f1510c = j;
    }

    /* renamed from: a */
    public void mo9479a(final C0614cl clVar, UMCCVerbatimObject cpVar) {
        try {
            if (mo9483a((List<?>) cpVar.mo9514a())) {
                UMCCAggregatedObject cnVar = this.f1509b.get(cpVar.mo9514a());
                if (cnVar != null) {
                    cnVar.mo9490a(new C0614cl() {
                        /* renamed from: a */
                        public void mo9396a(Object obj, boolean z) {
                            UMCCAggregatedObject cnVar = (UMCCAggregatedObject) obj;
                            UMCCAggregatedListObject.this.f1509b.remove(cnVar.mo9488a());
                            UMCCAggregatedListObject.this.f1509b.put(cnVar.mo9494b(), cnVar);
                            clVar.mo9396a(this, false);
                        }
                    }, cpVar);
                } else {
                    mo9481a(clVar, cpVar.mo9514a(), cpVar);
                }
            } else {
                mo9481a(clVar, cpVar.mo9514a(), cpVar);
            }
        } catch (Exception e) {
            MLog.m1844c("aggregated faild!");
        }
    }

    /* renamed from: a */
    public void mo9481a(C0614cl clVar, List<String> list, UMCCVerbatimObject cpVar) {
        UMCCAggregatedObject cnVar = new UMCCAggregatedObject();
        cnVar.mo9491a(cpVar);
        this.f1509b.put(list, cnVar);
        clVar.mo9396a(this, false);
    }

    /* renamed from: a */
    public boolean mo9483a(List<?> list) {
        if (this.f1509b == null || !this.f1509b.containsKey(list)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void mo9478a(C0614cl clVar) {
        for (List next : this.f1509b.keySet()) {
            if (!clVar.mo9475a()) {
                clVar.mo9396a(this.f1509b.get(next), false);
            } else {
                return;
            }
        }
    }

    /* renamed from: c */
    public int mo9486c() {
        if (this.f1509b != null) {
            return this.f1509b.size();
        }
        return 0;
    }

    /* renamed from: d */
    public void mo9487d() {
        this.f1509b.clear();
    }

    /* renamed from: a */
    public boolean mo9484a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            arrayList.add(UMCCDBUtils.m2041b(list.get(i)));
        }
        if (list == null || list.size() == 0) {
            return false;
        }
        return arrayList.contains(list2);
    }

    /* renamed from: a */
    public void mo9480a(C0614cl clVar, UMCCVerbatimObject cpVar, List<String> list, List<String> list2) {
        while (list.size() >= 1) {
            try {
                if (list.size() == 1) {
                    if (!mo9484a(list2, list)) {
                        clVar.mo9396a(false, false);
                        return;
                    } else {
                        m2111a(clVar, cpVar, list);
                        return;
                    }
                } else if (mo9484a(list2, list)) {
                    m2111a(clVar, cpVar, list);
                    return;
                } else {
                    list.remove(list.size() - 1);
                }
            } catch (Exception e) {
                MLog.m1844c("overFlowAggregated faild");
                return;
            }
        }
    }

    /* renamed from: a */
    private void m2111a(C0614cl clVar, UMCCVerbatimObject cpVar, List<String> list) {
        if (mo9483a((List<?>) list)) {
            mo9479a(clVar, cpVar);
        } else {
            mo9481a(clVar, list, cpVar);
        }
    }
}
