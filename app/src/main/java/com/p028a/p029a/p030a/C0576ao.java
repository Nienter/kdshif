package com.p028a.p029a.p030a;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.a.a.a.ao */
/* compiled from: Response */
public class C0576ao implements TBase<C0576ao, C0582e>, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map<C0582e, FieldMetaData> f1310d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final TStruct f1311e = new TStruct("Response");
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static final TField f1312f = new TField("resp_code", (byte) 8, 1);
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final TField f1313g = new TField("msg", (byte) 11, 2);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final TField f1314h = new TField("imprint", (byte) 12, 3);

    /* renamed from: i */
    private static final Map<Class<? extends IScheme>, SchemeFactory> f1315i = new HashMap();

    /* renamed from: a */
    public int f1316a;

    /* renamed from: b */
    public String f1317b;

    /* renamed from: c */
    public Imprint f1318c;

    /* renamed from: l */
    private byte f1319l;

    /* renamed from: m */
    private C0582e[] f1320m;

    /* renamed from: com.a.a.a.ao$a */
    /* compiled from: Response */
    private static class C0578a extends StandardScheme<C0576ao> {
        private C0578a() {
        }

        /* renamed from: a */
        public void mo9150b(TProtocol bvVar, C0576ao aoVar) {
            bvVar.mo9432f();
            while (true) {
                TField h = bvVar.mo9434h();
                if (h.f1476b == 0) {
                    bvVar.mo9433g();
                    if (!aoVar.mo9283e()) {
                        throw new TProtocolException("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                    }
                    aoVar.mo9290l();
                    return;
                }
                switch (h.f1477c) {
                    case 1:
                        if (h.f1476b != 8) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            aoVar.f1316a = bvVar.mo9445s();
                            aoVar.mo9277a(true);
                            break;
                        }
                    case 2:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            aoVar.f1317b = bvVar.mo9448v();
                            aoVar.mo9278b(true);
                            break;
                        }
                    case 3:
                        if (h.f1476b != 12) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            aoVar.f1318c = new Imprint();
                            aoVar.f1318c.mo9122a(bvVar);
                            aoVar.mo9281c(true);
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
        public void mo9148a(TProtocol bvVar, C0576ao aoVar) {
            aoVar.mo9290l();
            bvVar.mo9421a(C0576ao.f1311e);
            bvVar.mo9418a(C0576ao.f1312f);
            bvVar.mo9416a(aoVar.f1316a);
            bvVar.mo9426b();
            if (aoVar.f1317b != null && aoVar.mo9286h()) {
                bvVar.mo9418a(C0576ao.f1313g);
                bvVar.mo9422a(aoVar.f1317b);
                bvVar.mo9426b();
            }
            if (aoVar.f1318c != null && aoVar.mo9289k()) {
                bvVar.mo9418a(C0576ao.f1314h);
                aoVar.f1318c.mo9127b(bvVar);
                bvVar.mo9426b();
            }
            bvVar.mo9427c();
            bvVar.mo9414a();
        }
    }

    /* renamed from: com.a.a.a.ao$b */
    /* compiled from: Response */
    private static class C0579b implements SchemeFactory {
        private C0579b() {
        }

        /* renamed from: a */
        public C0578a mo9152b() {
            return new C0578a();
        }
    }

    /* renamed from: com.a.a.a.ao$c */
    /* compiled from: Response */
    private static class C0580c extends TupleScheme<C0576ao> {
        private C0580c() {
        }

        /* renamed from: a */
        public void mo9148a(TProtocol bvVar, C0576ao aoVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            cbVar.mo9416a(aoVar.f1316a);
            BitSet bitSet = new BitSet();
            if (aoVar.mo9286h()) {
                bitSet.set(0);
            }
            if (aoVar.mo9289k()) {
                bitSet.set(1);
            }
            cbVar.mo9459a(bitSet, 2);
            if (aoVar.mo9286h()) {
                cbVar.mo9422a(aoVar.f1317b);
            }
            if (aoVar.mo9289k()) {
                aoVar.f1318c.mo9127b((TProtocol) cbVar);
            }
        }

        /* renamed from: b */
        public void mo9150b(TProtocol bvVar, C0576ao aoVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            aoVar.f1316a = cbVar.mo9445s();
            aoVar.mo9277a(true);
            BitSet b = cbVar.mo9460b(2);
            if (b.get(0)) {
                aoVar.f1317b = cbVar.mo9448v();
                aoVar.mo9278b(true);
            }
            if (b.get(1)) {
                aoVar.f1318c = new Imprint();
                aoVar.f1318c.mo9122a((TProtocol) cbVar);
                aoVar.mo9281c(true);
            }
        }
    }

    /* renamed from: com.a.a.a.ao$d */
    /* compiled from: Response */
    private static class C0581d implements SchemeFactory {
        private C0581d() {
        }

        /* renamed from: a */
        public C0580c mo9152b() {
            return new C0580c();
        }
    }

    /* renamed from: com.a.a.a.ao$e */
    /* compiled from: Response */
    public enum C0582e implements TFieldIdEnum {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, "imprint");
        

        /* renamed from: d */
        private static final Map<String, C0582e> f1324d = null;

        /* renamed from: e */
        private final short f1326e;

        /* renamed from: f */
        private final String f1327f;

        static {
            f1324d = new HashMap();
            Iterator it = EnumSet.allOf(C0582e.class).iterator();
            while (it.hasNext()) {
                C0582e eVar = (C0582e) it.next();
                f1324d.put(eVar.mo9299b(), eVar);
            }
        }

        /* renamed from: a */
        public static C0582e m1671a(int i) {
            switch (i) {
                case 1:
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C0582e m1673b(int i) {
            C0582e a = m1671a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C0582e m1672a(String str) {
            return f1324d.get(str);
        }

        private C0582e(short s, String str) {
            this.f1326e = s;
            this.f1327f = str;
        }

        /* renamed from: a */
        public short mo9298a() {
            return this.f1326e;
        }

        /* renamed from: b */
        public String mo9299b() {
            return this.f1327f;
        }
    }

    static {
        f1315i.put(StandardScheme.class, new C0579b());
        f1315i.put(TupleScheme.class, new C0581d());
        EnumMap enumMap = new EnumMap(C0582e.class);
        enumMap.put(C0582e.RESP_CODE, new FieldMetaData("resp_code", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(C0582e.MSG, new FieldMetaData("msg", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0582e.IMPRINT, new FieldMetaData("imprint", (byte) 2, new StructMetaData((byte) 12, Imprint.class)));
        f1310d = Collections.unmodifiableMap(enumMap);
        FieldMetaData.m1940a(C0576ao.class, f1310d);
    }

    public C0576ao() {
        this.f1319l = 0;
        this.f1320m = new C0582e[]{C0582e.MSG, C0582e.IMPRINT};
    }

    public C0576ao(int i) {
        this();
        this.f1316a = i;
        mo9277a(true);
    }

    public C0576ao(C0576ao aoVar) {
        this.f1319l = 0;
        this.f1320m = new C0582e[]{C0582e.MSG, C0582e.IMPRINT};
        this.f1319l = aoVar.f1319l;
        this.f1316a = aoVar.f1316a;
        if (aoVar.mo9286h()) {
            this.f1317b = aoVar.f1317b;
        }
        if (aoVar.mo9289k()) {
            this.f1318c = new Imprint(aoVar.f1318c);
        }
    }

    /* renamed from: a */
    public C0576ao mo9145p() {
        return new C0576ao(this);
    }

    /* renamed from: b */
    public void mo9126b() {
        mo9277a(false);
        this.f1316a = 0;
        this.f1317b = null;
        this.f1318c = null;
    }

    /* renamed from: c */
    public int mo9279c() {
        return this.f1316a;
    }

    /* renamed from: a */
    public C0576ao mo9274a(int i) {
        this.f1316a = i;
        mo9277a(true);
        return this;
    }

    /* renamed from: d */
    public void mo9282d() {
        this.f1319l = EncodingUtils.m1912b(this.f1319l, 0);
    }

    /* renamed from: e */
    public boolean mo9283e() {
        return EncodingUtils.m1910a(this.f1319l, 0);
    }

    /* renamed from: a */
    public void mo9277a(boolean z) {
        this.f1319l = EncodingUtils.m1908a(this.f1319l, 0, z);
    }

    /* renamed from: f */
    public String mo9284f() {
        return this.f1317b;
    }

    /* renamed from: a */
    public C0576ao mo9276a(String str) {
        this.f1317b = str;
        return this;
    }

    /* renamed from: g */
    public void mo9285g() {
        this.f1317b = null;
    }

    /* renamed from: h */
    public boolean mo9286h() {
        return this.f1317b != null;
    }

    /* renamed from: b */
    public void mo9278b(boolean z) {
        if (!z) {
            this.f1317b = null;
        }
    }

    /* renamed from: i */
    public Imprint mo9287i() {
        return this.f1318c;
    }

    /* renamed from: a */
    public C0576ao mo9275a(Imprint amVar) {
        this.f1318c = amVar;
        return this;
    }

    /* renamed from: j */
    public void mo9288j() {
        this.f1318c = null;
    }

    /* renamed from: k */
    public boolean mo9289k() {
        return this.f1318c != null;
    }

    /* renamed from: c */
    public void mo9281c(boolean z) {
        if (!z) {
            this.f1318c = null;
        }
    }

    /* renamed from: c */
    public C0582e mo9125b(int i) {
        return C0582e.m1671a(i);
    }

    /* renamed from: a */
    public void mo9122a(TProtocol bvVar) {
        f1315i.get(bvVar.mo9456y()).mo9152b().mo9150b(bvVar, this);
    }

    /* renamed from: b */
    public void mo9127b(TProtocol bvVar) {
        f1315i.get(bvVar.mo9456y()).mo9152b().mo9148a(bvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.f1316a);
        if (mo9286h()) {
            sb.append(", ");
            sb.append("msg:");
            if (this.f1317b == null) {
                sb.append("null");
            } else {
                sb.append(this.f1317b);
            }
        }
        if (mo9289k()) {
            sb.append(", ");
            sb.append("imprint:");
            if (this.f1318c == null) {
                sb.append("null");
            } else {
                sb.append(this.f1318c);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: l */
    public void mo9290l() {
        if (this.f1318c != null) {
            this.f1318c.mo9236m();
        }
    }
}
