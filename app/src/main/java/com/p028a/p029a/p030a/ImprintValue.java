package com.p028a.p029a.p030a;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.a.a.a.an */
public class ImprintValue implements TBase<ImprintValue, C0575e>, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map<C0575e, FieldMetaData> f1292d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final TStruct f1293e = new TStruct("ImprintValue");
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static final TField f1294f = new TField(FirebaseAnalytics.Param.VALUE, (byte) 11, 1);
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final TField f1295g = new TField("ts", (byte) 10, 2);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final TField f1296h = new TField("guid", (byte) 11, 3);

    /* renamed from: i */
    private static final Map<Class<? extends IScheme>, SchemeFactory> f1297i = new HashMap();

    /* renamed from: a */
    public String f1298a;

    /* renamed from: b */
    public long f1299b;

    /* renamed from: c */
    public String f1300c;

    /* renamed from: l */
    private byte f1301l;

    /* renamed from: m */
    private C0575e[] f1302m;

    /* renamed from: com.a.a.a.an$a */
    /* compiled from: ImprintValue */
    private static class C0571a extends StandardScheme<ImprintValue> {
        private C0571a() {
        }

        /* renamed from: a */
        public void mo9150b(TProtocol bvVar, ImprintValue anVar) {
            bvVar.mo9432f();
            while (true) {
                TField h = bvVar.mo9434h();
                if (h.f1476b == 0) {
                    bvVar.mo9433g();
                    if (!anVar.mo9259h()) {
                        throw new TProtocolException("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    anVar.mo9263l();
                    return;
                }
                switch (h.f1477c) {
                    case 1:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            anVar.f1298a = bvVar.mo9448v();
                            anVar.mo9250a(true);
                            break;
                        }
                    case 2:
                        if (h.f1476b != 10) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            anVar.f1299b = bvVar.mo9446t();
                            anVar.mo9252b(true);
                            break;
                        }
                    case 3:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            anVar.f1300c = bvVar.mo9448v();
                            anVar.mo9254c(true);
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
        public void mo9148a(TProtocol bvVar, ImprintValue anVar) {
            anVar.mo9263l();
            bvVar.mo9421a(ImprintValue.f1293e);
            if (anVar.f1298a != null && anVar.mo9256e()) {
                bvVar.mo9418a(ImprintValue.f1294f);
                bvVar.mo9422a(anVar.f1298a);
                bvVar.mo9426b();
            }
            bvVar.mo9418a(ImprintValue.f1295g);
            bvVar.mo9417a(anVar.f1299b);
            bvVar.mo9426b();
            if (anVar.f1300c != null) {
                bvVar.mo9418a(ImprintValue.f1296h);
                bvVar.mo9422a(anVar.f1300c);
                bvVar.mo9426b();
            }
            bvVar.mo9427c();
            bvVar.mo9414a();
        }
    }

    /* renamed from: com.a.a.a.an$b */
    /* compiled from: ImprintValue */
    private static class C0572b implements SchemeFactory {
        private C0572b() {
        }

        /* renamed from: a */
        public C0571a mo9152b() {
            return new C0571a();
        }
    }

    /* renamed from: com.a.a.a.an$c */
    /* compiled from: ImprintValue */
    private static class C0573c extends TupleScheme<ImprintValue> {
        private C0573c() {
        }

        /* renamed from: a */
        public void mo9148a(TProtocol bvVar, ImprintValue anVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            cbVar.mo9417a(anVar.f1299b);
            cbVar.mo9422a(anVar.f1300c);
            BitSet bitSet = new BitSet();
            if (anVar.mo9256e()) {
                bitSet.set(0);
            }
            cbVar.mo9459a(bitSet, 1);
            if (anVar.mo9256e()) {
                cbVar.mo9422a(anVar.f1298a);
            }
        }

        /* renamed from: b */
        public void mo9150b(TProtocol bvVar, ImprintValue anVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            anVar.f1299b = cbVar.mo9446t();
            anVar.mo9252b(true);
            anVar.f1300c = cbVar.mo9448v();
            anVar.mo9254c(true);
            if (cbVar.mo9460b(1).get(0)) {
                anVar.f1298a = cbVar.mo9448v();
                anVar.mo9250a(true);
            }
        }
    }

    /* renamed from: com.a.a.a.an$d */
    /* compiled from: ImprintValue */
    private static class C0574d implements SchemeFactory {
        private C0574d() {
        }

        /* renamed from: a */
        public C0573c mo9152b() {
            return new C0573c();
        }
    }

    /* renamed from: com.a.a.a.an$e */
    /* compiled from: ImprintValue */
    public enum C0575e implements TFieldIdEnum {
        VALUE(1, FirebaseAnalytics.Param.VALUE),
        TS(2, "ts"),
        GUID(3, "guid");
        

        /* renamed from: d */
        private static final Map<String, C0575e> f1306d = null;

        /* renamed from: e */
        private final short f1308e;

        /* renamed from: f */
        private final String f1309f;

        static {
            f1306d = new HashMap();
            Iterator it = EnumSet.allOf(C0575e.class).iterator();
            while (it.hasNext()) {
                C0575e eVar = (C0575e) it.next();
                f1306d.put(eVar.mo9272b(), eVar);
            }
        }

        /* renamed from: a */
        public static C0575e m1627a(int i) {
            switch (i) {
                case 1:
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C0575e m1629b(int i) {
            C0575e a = m1627a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C0575e m1628a(String str) {
            return f1306d.get(str);
        }

        private C0575e(short s, String str) {
            this.f1308e = s;
            this.f1309f = str;
        }

        /* renamed from: a */
        public short mo9271a() {
            return this.f1308e;
        }

        /* renamed from: b */
        public String mo9272b() {
            return this.f1309f;
        }
    }

    static {
        f1297i.put(StandardScheme.class, new C0572b());
        f1297i.put(TupleScheme.class, new C0574d());
        EnumMap enumMap = new EnumMap(C0575e.class);
        enumMap.put(C0575e.VALUE, new FieldMetaData(FirebaseAnalytics.Param.VALUE, (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0575e.TS, new FieldMetaData("ts", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(C0575e.GUID, new FieldMetaData("guid", (byte) 1, new FieldValueMetaData((byte) 11)));
        f1292d = Collections.unmodifiableMap(enumMap);
        FieldMetaData.m1940a(ImprintValue.class, f1292d);
    }

    public ImprintValue() {
        this.f1301l = 0;
        this.f1302m = new C0575e[]{C0575e.VALUE};
    }

    public ImprintValue(long j, String str) {
        this();
        this.f1299b = j;
        mo9252b(true);
        this.f1300c = str;
    }

    public ImprintValue(ImprintValue anVar) {
        this.f1301l = 0;
        this.f1302m = new C0575e[]{C0575e.VALUE};
        this.f1301l = anVar.f1301l;
        if (anVar.mo9256e()) {
            this.f1298a = anVar.f1298a;
        }
        this.f1299b = anVar.f1299b;
        if (anVar.mo9262k()) {
            this.f1300c = anVar.f1300c;
        }
    }

    /* renamed from: a */
    public ImprintValue mo9145p() {
        return new ImprintValue(this);
    }

    /* renamed from: b */
    public void mo9126b() {
        this.f1298a = null;
        mo9252b(false);
        this.f1299b = 0;
        this.f1300c = null;
    }

    /* renamed from: c */
    public String mo9253c() {
        return this.f1298a;
    }

    /* renamed from: a */
    public ImprintValue mo9249a(String str) {
        this.f1298a = str;
        return this;
    }

    /* renamed from: d */
    public void mo9255d() {
        this.f1298a = null;
    }

    /* renamed from: e */
    public boolean mo9256e() {
        return this.f1298a != null;
    }

    /* renamed from: a */
    public void mo9250a(boolean z) {
        if (!z) {
            this.f1298a = null;
        }
    }

    /* renamed from: f */
    public long mo9257f() {
        return this.f1299b;
    }

    /* renamed from: a */
    public ImprintValue mo9248a(long j) {
        this.f1299b = j;
        mo9252b(true);
        return this;
    }

    /* renamed from: g */
    public void mo9258g() {
        this.f1301l = EncodingUtils.m1912b(this.f1301l, 0);
    }

    /* renamed from: h */
    public boolean mo9259h() {
        return EncodingUtils.m1910a(this.f1301l, 0);
    }

    /* renamed from: b */
    public void mo9252b(boolean z) {
        this.f1301l = EncodingUtils.m1908a(this.f1301l, 0, z);
    }

    /* renamed from: i */
    public String mo9260i() {
        return this.f1300c;
    }

    /* renamed from: b */
    public ImprintValue mo9251b(String str) {
        this.f1300c = str;
        return this;
    }

    /* renamed from: j */
    public void mo9261j() {
        this.f1300c = null;
    }

    /* renamed from: k */
    public boolean mo9262k() {
        return this.f1300c != null;
    }

    /* renamed from: c */
    public void mo9254c(boolean z) {
        if (!z) {
            this.f1300c = null;
        }
    }

    /* renamed from: a */
    public C0575e mo9125b(int i) {
        return C0575e.m1627a(i);
    }

    /* renamed from: a */
    public void mo9122a(TProtocol bvVar) {
        f1297i.get(bvVar.mo9456y()).mo9152b().mo9150b(bvVar, this);
    }

    /* renamed from: b */
    public void mo9127b(TProtocol bvVar) {
        f1297i.get(bvVar.mo9456y()).mo9152b().mo9148a(bvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ImprintValue(");
        boolean z = true;
        if (mo9256e()) {
            sb.append("value:");
            if (this.f1298a == null) {
                sb.append("null");
            } else {
                sb.append(this.f1298a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.f1299b);
        sb.append(", ");
        sb.append("guid:");
        if (this.f1300c == null) {
            sb.append("null");
        } else {
            sb.append(this.f1300c);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: l */
    public void mo9263l() {
        if (this.f1300c == null) {
            throw new TProtocolException("Required field 'guid' was not present! Struct: " + toString());
        }
    }
}
