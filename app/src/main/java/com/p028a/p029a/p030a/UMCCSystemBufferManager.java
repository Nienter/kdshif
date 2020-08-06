package com.p028a.p029a.p030a;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.a.a.ct */
public class UMCCSystemBufferManager implements Serializable {

    /* renamed from: b */
    private Map<String, UMCCSystemBuffer> f1556b = new HashMap();

    /* renamed from: a */
    public void mo9541a(C0614cl clVar, String str) {
        if (this.f1556b.containsKey(str)) {
            m2214c(str);
        } else {
            m2213b(str);
        }
        clVar.mo9396a(this, false);
    }

    /* renamed from: a */
    public Map<String, UMCCSystemBuffer> mo9540a() {
        return this.f1556b;
    }

    /* renamed from: b */
    public void mo9545b() {
        this.f1556b.clear();
    }

    /* renamed from: a */
    public void mo9543a(Map<String, UMCCSystemBuffer> map) {
        this.f1556b = map;
    }

    /* renamed from: a */
    public boolean mo9544a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (Map.Entry<String, UMCCSystemBuffer> key : this.f1556b.entrySet()) {
            if (((String) key.getKey()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private void m2213b(String str) {
        this.f1556b.put(str, new UMCCSystemBuffer(str, System.currentTimeMillis(), 1));
    }

    /* renamed from: c */
    private void m2214c(String str) {
        this.f1556b.put(str, this.f1556b.get(str).mo9506a());
    }

    /* renamed from: a */
    public void mo9542a(UMCCSystemBuffer coVar) {
        if (mo9544a(coVar.mo9511c())) {
            m2212b(coVar);
        } else {
            this.f1556b.put(coVar.mo9511c(), coVar);
        }
    }

    /* renamed from: b */
    private void m2212b(UMCCSystemBuffer coVar) {
        this.f1556b.put(coVar.mo9511c(), this.f1556b.get(coVar.mo9511c()).mo9507a(coVar));
    }
}
