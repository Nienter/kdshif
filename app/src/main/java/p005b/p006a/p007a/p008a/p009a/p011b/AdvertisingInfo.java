package p005b.p006a.p007a.p008a.p009a.p011b;

/* renamed from: b.a.a.a.a.b.b */
class AdvertisingInfo {

    /* renamed from: a */
    public final String f108a;

    /* renamed from: b */
    public final boolean f109b;

    AdvertisingInfo(String str, boolean z) {
        this.f108a = str;
        this.f109b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvertisingInfo bVar = (AdvertisingInfo) obj;
        if (this.f109b != bVar.f109b) {
            return false;
        }
        if (this.f108a != null) {
            if (this.f108a.equals(bVar.f108a)) {
                return true;
            }
        } else if (bVar.f108a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.f108a != null) {
            i = this.f108a.hashCode();
        } else {
            i = 0;
        }
        int i3 = i * 31;
        if (this.f109b) {
            i2 = 1;
        }
        return i3 + i2;
    }
}
