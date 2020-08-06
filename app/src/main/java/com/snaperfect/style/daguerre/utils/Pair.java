package com.snaperfect.style.daguerre.utils;

/* renamed from: com.snaperfect.style.daguerre.utils.i */
public class Pair<F, S> {

    /* renamed from: a */
    public final F f2264a;

    /* renamed from: b */
    public final S f2265b;

    public Pair(F f, S s) {
        this.f2264a = f;
        this.f2265b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair iVar = (Pair) obj;
        if (!this.f2264a.equals(iVar.f2264a) || !this.f2265b.equals(iVar.f2265b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = this.f2264a == null ? 0 : this.f2264a.hashCode();
        if (this.f2265b != null) {
            i = this.f2265b.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "{" + this.f2264a + ", " + this.f2265b + "}";
    }
}
