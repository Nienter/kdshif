package com.p028a.p029a.p030a;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.a.a.a.aj */
public class IdJournal implements TBase<IdJournal, C0551e>, Serializable, Cloneable {

    /* renamed from: e */
    public static final Map<C0551e, FieldMetaData> f1220e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static final TStruct f1221f = new TStruct("IdJournal");
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final TField f1222g = new TField("domain", (byte) 11, 1);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final TField f1223h = new TField("old_id", (byte) 11, 2);
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static final TField f1224i = new TField("new_id", (byte) 11, 3);
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static final TField f1225j = new TField("ts", (byte) 10, 4);

    /* renamed from: k */
    private static final Map<Class<? extends IScheme>, SchemeFactory> f1226k = new HashMap();

    /* renamed from: a */
    public String f1227a;

    /* renamed from: b */
    public String f1228b;

    /* renamed from: c */
    public String f1229c;

    /* renamed from: d */
    public long f1230d;

    /* renamed from: n */
    private byte f1231n;

    /* renamed from: o */
    private C0551e[] f1232o;

    /* renamed from: com.a.a.a.aj$a */
    /* compiled from: IdJournal */
    private static class C0547a extends StandardScheme<IdJournal> {
        private C0547a() {
        }

        /* renamed from: a */
        public void mo9150b(TProtocol bvVar, IdJournal ajVar) {
            bvVar.mo9432f();
            while (true) {
                TField h = bvVar.mo9434h();
                if (h.f1476b == 0) {
                    bvVar.mo9433g();
                    if (!ajVar.mo9143n()) {
                        throw new TProtocolException("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    ajVar.mo9144o();
                    return;
                }
                switch (h.f1477c) {
                    case 1:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            ajVar.f1227a = bvVar.mo9448v();
                            ajVar.mo9123a(true);
                            break;
                        }
                    case 2:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            ajVar.f1228b = bvVar.mo9448v();
                            ajVar.mo9128b(true);
                            break;
                        }
                    case 3:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            ajVar.f1229c = bvVar.mo9448v();
                            ajVar.mo9131c(true);
                            break;
                        }
                    case 4:
                        if (h.f1476b != 10) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            ajVar.f1230d = bvVar.mo9446t();
                            ajVar.mo9133d(true);
                            break;
                        }
                    default:
                        TProtocolUtil.m2077a(bvVar, h.f1476b);
                        break;
                }
                bvVar.mo9435i();
            }
        }

        /* renamed from: b */
        public void mo9148a(TProtocol bvVar, IdJournal ajVar) {
            ajVar.mo9144o();
            bvVar.mo9421a(IdJournal.f1221f);
            if (ajVar.f1227a != null) {
                bvVar.mo9418a(IdJournal.f1222g);
                bvVar.mo9422a(ajVar.f1227a);
                bvVar.mo9426b();
            }
            if (ajVar.f1228b != null && ajVar.mo9137h()) {
                bvVar.mo9418a(IdJournal.f1223h);
                bvVar.mo9422a(ajVar.f1228b);
                bvVar.mo9426b();
            }
            if (ajVar.f1229c != null) {
                bvVar.mo9418a(IdJournal.f1224i);
                bvVar.mo9422a(ajVar.f1229c);
                bvVar.mo9426b();
            }
            bvVar.mo9418a(IdJournal.f1225j);
            bvVar.mo9417a(ajVar.f1230d);
            bvVar.mo9426b();
            bvVar.mo9427c();
            bvVar.mo9414a();
        }
    }

    /* renamed from: com.a.a.a.aj$b */
    /* compiled from: IdJournal */
    private static class C0548b implements SchemeFactory {
        private C0548b() {
        }

        /* renamed from: a */
        public C0547a mo9152b() {
            return new C0547a();
        }
    }

    /* renamed from: com.a.a.a.aj$c */
    /* compiled from: IdJournal */
    private static class C0549c extends TupleScheme<IdJournal> {
        private C0549c() {
        }

        /* renamed from: a */
        public void mo9148a(TProtocol bvVar, IdJournal ajVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            cbVar.mo9422a(ajVar.f1227a);
            cbVar.mo9422a(ajVar.f1229c);
            cbVar.mo9417a(ajVar.f1230d);
            BitSet bitSet = new BitSet();
            if (ajVar.mo9137h()) {
                bitSet.set(0);
            }
            cbVar.mo9459a(bitSet, 1);
            if (ajVar.mo9137h()) {
                cbVar.mo9422a(ajVar.f1228b);
            }
        }

        /* renamed from: b */
        public void mo9150b(TProtocol bvVar, IdJournal ajVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            ajVar.f1227a = cbVar.mo9448v();
            ajVar.mo9123a(true);
            ajVar.f1229c = cbVar.mo9448v();
            ajVar.mo9131c(true);
            ajVar.f1230d = cbVar.mo9446t();
            ajVar.mo9133d(true);
            if (cbVar.mo9460b(1).get(0)) {
                ajVar.f1228b = cbVar.mo9448v();
                ajVar.mo9128b(true);
            }
        }
    }

    /* renamed from: com.a.a.a.aj$d */
    /* compiled from: IdJournal */
    private static class C0550d implements SchemeFactory {
        private C0550d() {
        }

        /* renamed from: a */
        public C0549c mo9152b() {
            return new C0549c();
        }
    }

    /* renamed from: com.a.a.a.aj$e */
    /* compiled from: IdJournal */
    public enum C0551e implements TFieldIdEnum {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");
        

        /* renamed from: e */
        private static final Map<String, C0551e> f1237e = null;

        /* renamed from: f */
        private final short f1239f;

        /* renamed from: g */
        private final String f1240g;

        static {
            f1237e = new HashMap();
            Iterator it = EnumSet.allOf(C0551e.class).iterator();
            while (it.hasNext()) {
                C0551e eVar = (C0551e) it.next();
                f1237e.put(eVar.mo9157b(), eVar);
            }
        }

        /* renamed from: a */
        public static C0551e m1444a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C0551e m1446b(int i) {
            C0551e a = m1444a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C0551e m1445a(String str) {
            return f1237e.get(str);
        }

        private C0551e(short s, String str) {
            this.f1239f = s;
            this.f1240g = str;
        }

        /* renamed from: a */
        public short mo9156a() {
            return this.f1239f;
        }

        /* renamed from: b */
        public String mo9157b() {
            return this.f1240g;
        }
    }

    static {
        f1226k.put(StandardScheme.class, new C0548b());
        f1226k.put(TupleScheme.class, new C0550d());
        EnumMap enumMap = new EnumMap(C0551e.class);
        enumMap.put(C0551e.DOMAIN, new FieldMetaData("domain", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0551e.OLD_ID, new FieldMetaData("old_id", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0551e.NEW_ID, new FieldMetaData("new_id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0551e.TS, new FieldMetaData("ts", (byte) 1, new FieldValueMetaData((byte) 10)));
        f1220e = Collections.unmodifiableMap(enumMap);
        FieldMetaData.m1940a(IdJournal.class, f1220e);
    }

    public IdJournal() {
        this.f1231n = 0;
        this.f1232o = new C0551e[]{C0551e.OLD_ID};
    }

    public IdJournal(String str, String str2, long j) {
        this();
        this.f1227a = str;
        this.f1229c = str2;
        this.f1230d = j;
        mo9133d(true);
    }

    public IdJournal(IdJournal ajVar) {
        this.f1231n = 0;
        this.f1232o = new C0551e[]{C0551e.OLD_ID};
        this.f1231n = ajVar.f1231n;
        if (ajVar.mo9134e()) {
            this.f1227a = ajVar.f1227a;
        }
        if (ajVar.mo9137h()) {
            this.f1228b = ajVar.f1228b;
        }
        if (ajVar.mo9140k()) {
            this.f1229c = ajVar.f1229c;
        }
        this.f1230d = ajVar.f1230d;
    }

    /* renamed from: a */
    public IdJournal mo9145p() {
        return new IdJournal(this);
    }

    /* renamed from: b */
    public void mo9126b() {
        this.f1227a = null;
        this.f1228b = null;
        this.f1229c = null;
        mo9133d(false);
        this.f1230d = 0;
    }

    /* renamed from: c */
    public String mo9130c() {
        return this.f1227a;
    }

    /* renamed from: a */
    public IdJournal mo9121a(String str) {
        this.f1227a = str;
        return this;
    }

    /* renamed from: d */
    public void mo9132d() {
        this.f1227a = null;
    }

    /* renamed from: e */
    public boolean mo9134e() {
        return this.f1227a != null;
    }

    /* renamed from: a */
    public void mo9123a(boolean z) {
        if (!z) {
            this.f1227a = null;
        }
    }

    /* renamed from: f */
    public String mo9135f() {
        return this.f1228b;
    }

    /* renamed from: b */
    public IdJournal mo9124b(String str) {
        this.f1228b = str;
        return this;
    }

    /* renamed from: g */
    public void mo9136g() {
        this.f1228b = null;
    }

    /* renamed from: h */
    public boolean mo9137h() {
        return this.f1228b != null;
    }

    /* renamed from: b */
    public void mo9128b(boolean z) {
        if (!z) {
            this.f1228b = null;
        }
    }

    /* renamed from: i */
    public String mo9138i() {
        return this.f1229c;
    }

    /* renamed from: c */
    public IdJournal mo9129c(String str) {
        this.f1229c = str;
        return this;
    }

    /* renamed from: j */
    public void mo9139j() {
        this.f1229c = null;
    }

    /* renamed from: k */
    public boolean mo9140k() {
        return this.f1229c != null;
    }

    /* renamed from: c */
    public void mo9131c(boolean z) {
        if (!z) {
            this.f1229c = null;
        }
    }

    /* renamed from: l */
    public long mo9141l() {
        return this.f1230d;
    }

    /* renamed from: a */
    public IdJournal mo9120a(long j) {
        this.f1230d = j;
        mo9133d(true);
        return this;
    }

    /* renamed from: m */
    public void mo9142m() {
        this.f1231n = EncodingUtils.m1912b(this.f1231n, 0);
    }

    /* renamed from: n */
    public boolean mo9143n() {
        return EncodingUtils.m1910a(this.f1231n, 0);
    }

    /* renamed from: d */
    public void mo9133d(boolean z) {
        this.f1231n = EncodingUtils.m1908a(this.f1231n, 0, z);
    }

    /* renamed from: a */
    public C0551e mo9125b(int i) {
        return C0551e.m1444a(i);
    }

    /* renamed from: a */
    public void mo9122a(TProtocol bvVar) {
        f1226k.get(bvVar.mo9456y()).mo9152b().mo9150b(bvVar, this);
    }

    /* renamed from: b */
    public void mo9127b(TProtocol bvVar) {
        f1226k.get(bvVar.mo9456y()).mo9152b().mo9148a(bvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        if (this.f1227a == null) {
            sb.append("null");
        } else {
            sb.append(this.f1227a);
        }
        if (mo9137h()) {
            sb.append(", ");
            sb.append("old_id:");
            if (this.f1228b == null) {
                sb.append("null");
            } else {
                sb.append(this.f1228b);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        if (this.f1229c == null) {
            sb.append("null");
        } else {
            sb.append(this.f1229c);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f1230d);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: o */
    public void mo9144o() {
        if (this.f1227a == null) {
            throw new TProtocolException("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.f1229c == null) {
            throw new TProtocolException("Required field 'new_id' was not present! Struct: " + toString());
        }
    }
}
