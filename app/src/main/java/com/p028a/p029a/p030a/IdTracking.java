package com.p028a.p029a.p030a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.a.a.a.al */
public class IdTracking implements TBase<IdTracking, C0563e>, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map<C0563e, FieldMetaData> f1258d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final TStruct f1259e = new TStruct("IdTracking");
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static final TField f1260f = new TField("snapshots", (byte) 13, 1);
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final TField f1261g = new TField("journals", (byte) 15, 2);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final TField f1262h = new TField("checksum", (byte) 11, 3);

    /* renamed from: i */
    private static final Map<Class<? extends IScheme>, SchemeFactory> f1263i = new HashMap();

    /* renamed from: a */
    public Map<String, IdSnapshot> f1264a;

    /* renamed from: b */
    public List<IdJournal> f1265b;

    /* renamed from: c */
    public String f1266c;

    /* renamed from: k */
    private C0563e[] f1267k;

    /* renamed from: com.a.a.a.al$a */
    /* compiled from: IdTracking */
    private static class C0559a extends StandardScheme<IdTracking> {
        private C0559a() {
        }

        /* renamed from: a */
        public void mo9150b(TProtocol bvVar, IdTracking alVar) {
            bvVar.mo9432f();
            while (true) {
                TField h = bvVar.mo9434h();
                if (h.f1476b == 0) {
                    bvVar.mo9433g();
                    alVar.mo9207o();
                    return;
                }
                switch (h.f1477c) {
                    case 1:
                        if (h.f1476b != 13) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            TMap j = bvVar.mo9436j();
                            alVar.f1264a = new HashMap(j.f1482c * 2);
                            for (int i = 0; i < j.f1482c; i++) {
                                String v = bvVar.mo9448v();
                                IdSnapshot akVar = new IdSnapshot();
                                akVar.mo9122a(bvVar);
                                alVar.f1264a.put(v, akVar);
                            }
                            bvVar.mo9437k();
                            alVar.mo9192a(true);
                            break;
                        }
                    case 2:
                        if (h.f1476b != 15) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            TList l = bvVar.mo9438l();
                            alVar.f1265b = new ArrayList(l.f1479b);
                            for (int i2 = 0; i2 < l.f1479b; i2++) {
                                IdJournal ajVar = new IdJournal();
                                ajVar.mo9122a(bvVar);
                                alVar.f1265b.add(ajVar);
                            }
                            bvVar.mo9439m();
                            alVar.mo9193b(true);
                            break;
                        }
                    case 3:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            alVar.f1266c = bvVar.mo9448v();
                            alVar.mo9195c(true);
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
        public void mo9148a(TProtocol bvVar, IdTracking alVar) {
            alVar.mo9207o();
            bvVar.mo9421a(IdTracking.f1259e);
            if (alVar.f1264a != null) {
                bvVar.mo9418a(IdTracking.f1260f);
                bvVar.mo9420a(new TMap((byte) 11, (byte) 12, alVar.f1264a.size()));
                for (Map.Entry next : alVar.f1264a.entrySet()) {
                    bvVar.mo9422a((String) next.getKey());
                    ((IdSnapshot) next.getValue()).mo9127b(bvVar);
                }
                bvVar.mo9429d();
                bvVar.mo9426b();
            }
            if (alVar.f1265b != null && alVar.mo9203k()) {
                bvVar.mo9418a(IdTracking.f1261g);
                bvVar.mo9419a(new TList((byte) 12, alVar.f1265b.size()));
                for (IdJournal b : alVar.f1265b) {
                    b.mo9127b(bvVar);
                }
                bvVar.mo9431e();
                bvVar.mo9426b();
            }
            if (alVar.f1266c != null && alVar.mo9206n()) {
                bvVar.mo9418a(IdTracking.f1262h);
                bvVar.mo9422a(alVar.f1266c);
                bvVar.mo9426b();
            }
            bvVar.mo9427c();
            bvVar.mo9414a();
        }
    }

    /* renamed from: com.a.a.a.al$b */
    /* compiled from: IdTracking */
    private static class C0560b implements SchemeFactory {
        private C0560b() {
        }

        /* renamed from: a */
        public C0559a mo9152b() {
            return new C0559a();
        }
    }

    /* renamed from: com.a.a.a.al$c */
    /* compiled from: IdTracking */
    private static class C0561c extends TupleScheme<IdTracking> {
        private C0561c() {
        }

        /* renamed from: a */
        public void mo9148a(TProtocol bvVar, IdTracking alVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            cbVar.mo9416a(alVar.f1264a.size());
            for (Map.Entry next : alVar.f1264a.entrySet()) {
                cbVar.mo9422a((String) next.getKey());
                ((IdSnapshot) next.getValue()).mo9127b((TProtocol) cbVar);
            }
            BitSet bitSet = new BitSet();
            if (alVar.mo9203k()) {
                bitSet.set(0);
            }
            if (alVar.mo9206n()) {
                bitSet.set(1);
            }
            cbVar.mo9459a(bitSet, 2);
            if (alVar.mo9203k()) {
                cbVar.mo9416a(alVar.f1265b.size());
                for (IdJournal b : alVar.f1265b) {
                    b.mo9127b((TProtocol) cbVar);
                }
            }
            if (alVar.mo9206n()) {
                cbVar.mo9422a(alVar.f1266c);
            }
        }

        /* renamed from: b */
        public void mo9150b(TProtocol bvVar, IdTracking alVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            TMap buVar = new TMap((byte) 11, (byte) 12, cbVar.mo9445s());
            alVar.f1264a = new HashMap(buVar.f1482c * 2);
            for (int i = 0; i < buVar.f1482c; i++) {
                String v = cbVar.mo9448v();
                IdSnapshot akVar = new IdSnapshot();
                akVar.mo9122a((TProtocol) cbVar);
                alVar.f1264a.put(v, akVar);
            }
            alVar.mo9192a(true);
            BitSet b = cbVar.mo9460b(2);
            if (b.get(0)) {
                TList btVar = new TList((byte) 12, cbVar.mo9445s());
                alVar.f1265b = new ArrayList(btVar.f1479b);
                for (int i2 = 0; i2 < btVar.f1479b; i2++) {
                    IdJournal ajVar = new IdJournal();
                    ajVar.mo9122a((TProtocol) cbVar);
                    alVar.f1265b.add(ajVar);
                }
                alVar.mo9193b(true);
            }
            if (b.get(1)) {
                alVar.f1266c = cbVar.mo9448v();
                alVar.mo9195c(true);
            }
        }
    }

    /* renamed from: com.a.a.a.al$d */
    /* compiled from: IdTracking */
    private static class C0562d implements SchemeFactory {
        private C0562d() {
        }

        /* renamed from: a */
        public C0561c mo9152b() {
            return new C0561c();
        }
    }

    /* renamed from: com.a.a.a.al$e */
    /* compiled from: IdTracking */
    public enum C0563e implements TFieldIdEnum {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");
        

        /* renamed from: d */
        private static final Map<String, C0563e> f1271d = null;

        /* renamed from: e */
        private final short f1273e;

        /* renamed from: f */
        private final String f1274f;

        static {
            f1271d = new HashMap();
            Iterator it = EnumSet.allOf(C0563e.class).iterator();
            while (it.hasNext()) {
                C0563e eVar = (C0563e) it.next();
                f1271d.put(eVar.mo9216b(), eVar);
            }
        }

        /* renamed from: a */
        public static C0563e m1537a(int i) {
            switch (i) {
                case 1:
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C0563e m1539b(int i) {
            C0563e a = m1537a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C0563e m1538a(String str) {
            return f1271d.get(str);
        }

        private C0563e(short s, String str) {
            this.f1273e = s;
            this.f1274f = str;
        }

        /* renamed from: a */
        public short mo9215a() {
            return this.f1273e;
        }

        /* renamed from: b */
        public String mo9216b() {
            return this.f1274f;
        }
    }

    static {
        f1263i.put(StandardScheme.class, new C0560b());
        f1263i.put(TupleScheme.class, new C0562d());
        EnumMap enumMap = new EnumMap(C0563e.class);
        enumMap.put(C0563e.SNAPSHOTS, new FieldMetaData("snapshots", (byte) 1, new MapMetaData((byte) 13, new FieldValueMetaData((byte) 11), new StructMetaData((byte) 12, IdSnapshot.class))));
        enumMap.put(C0563e.JOURNALS, new FieldMetaData("journals", (byte) 2, new ListMetaData((byte) 15, new StructMetaData((byte) 12, IdJournal.class))));
        enumMap.put(C0563e.CHECKSUM, new FieldMetaData("checksum", (byte) 2, new FieldValueMetaData((byte) 11)));
        f1258d = Collections.unmodifiableMap(enumMap);
        FieldMetaData.m1940a(IdTracking.class, f1258d);
    }

    public IdTracking() {
        this.f1267k = new C0563e[]{C0563e.JOURNALS, C0563e.CHECKSUM};
    }

    public IdTracking(Map<String, IdSnapshot> map) {
        this();
        this.f1264a = map;
    }

    public IdTracking(IdTracking alVar) {
        this.f1267k = new C0563e[]{C0563e.JOURNALS, C0563e.CHECKSUM};
        if (alVar.mo9198f()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : alVar.f1264a.entrySet()) {
                hashMap.put((String) next.getKey(), new IdSnapshot((IdSnapshot) next.getValue()));
            }
            this.f1264a = hashMap;
        }
        if (alVar.mo9203k()) {
            ArrayList arrayList = new ArrayList();
            for (IdJournal ajVar : alVar.f1265b) {
                arrayList.add(new IdJournal(ajVar));
            }
            this.f1265b = arrayList;
        }
        if (alVar.mo9206n()) {
            this.f1266c = alVar.f1266c;
        }
    }

    /* renamed from: a */
    public IdTracking mo9145p() {
        return new IdTracking(this);
    }

    /* renamed from: b */
    public void mo9126b() {
        this.f1264a = null;
        this.f1265b = null;
        this.f1266c = null;
    }

    /* renamed from: c */
    public int mo9194c() {
        if (this.f1264a == null) {
            return 0;
        }
        return this.f1264a.size();
    }

    /* renamed from: a */
    public void mo9191a(String str, IdSnapshot akVar) {
        if (this.f1264a == null) {
            this.f1264a = new HashMap();
        }
        this.f1264a.put(str, akVar);
    }

    /* renamed from: d */
    public Map<String, IdSnapshot> mo9196d() {
        return this.f1264a;
    }

    /* renamed from: a */
    public IdTracking mo9189a(Map<String, IdSnapshot> map) {
        this.f1264a = map;
        return this;
    }

    /* renamed from: e */
    public void mo9197e() {
        this.f1264a = null;
    }

    /* renamed from: f */
    public boolean mo9198f() {
        return this.f1264a != null;
    }

    /* renamed from: a */
    public void mo9192a(boolean z) {
        if (!z) {
            this.f1264a = null;
        }
    }

    /* renamed from: g */
    public int mo9199g() {
        if (this.f1265b == null) {
            return 0;
        }
        return this.f1265b.size();
    }

    /* renamed from: h */
    public Iterator<IdJournal> mo9200h() {
        if (this.f1265b == null) {
            return null;
        }
        return this.f1265b.iterator();
    }

    /* renamed from: a */
    public void mo9190a(IdJournal ajVar) {
        if (this.f1265b == null) {
            this.f1265b = new ArrayList();
        }
        this.f1265b.add(ajVar);
    }

    /* renamed from: i */
    public List<IdJournal> mo9201i() {
        return this.f1265b;
    }

    /* renamed from: a */
    public IdTracking mo9188a(List<IdJournal> list) {
        this.f1265b = list;
        return this;
    }

    /* renamed from: j */
    public void mo9202j() {
        this.f1265b = null;
    }

    /* renamed from: k */
    public boolean mo9203k() {
        return this.f1265b != null;
    }

    /* renamed from: b */
    public void mo9193b(boolean z) {
        if (!z) {
            this.f1265b = null;
        }
    }

    /* renamed from: l */
    public String mo9204l() {
        return this.f1266c;
    }

    /* renamed from: a */
    public IdTracking mo9187a(String str) {
        this.f1266c = str;
        return this;
    }

    /* renamed from: m */
    public void mo9205m() {
        this.f1266c = null;
    }

    /* renamed from: n */
    public boolean mo9206n() {
        return this.f1266c != null;
    }

    /* renamed from: c */
    public void mo9195c(boolean z) {
        if (!z) {
            this.f1266c = null;
        }
    }

    /* renamed from: a */
    public C0563e mo9125b(int i) {
        return C0563e.m1537a(i);
    }

    /* renamed from: a */
    public void mo9122a(TProtocol bvVar) {
        f1263i.get(bvVar.mo9456y()).mo9152b().mo9150b(bvVar, this);
    }

    /* renamed from: b */
    public void mo9127b(TProtocol bvVar) {
        f1263i.get(bvVar.mo9456y()).mo9152b().mo9148a(bvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        if (this.f1264a == null) {
            sb.append("null");
        } else {
            sb.append(this.f1264a);
        }
        if (mo9203k()) {
            sb.append(", ");
            sb.append("journals:");
            if (this.f1265b == null) {
                sb.append("null");
            } else {
                sb.append(this.f1265b);
            }
        }
        if (mo9206n()) {
            sb.append(", ");
            sb.append("checksum:");
            if (this.f1266c == null) {
                sb.append("null");
            } else {
                sb.append(this.f1266c);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: o */
    public void mo9207o() {
        if (this.f1264a == null) {
            throw new TProtocolException("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }
}
