package com.p028a.p029a.p030a;

import com.facebook.internal.ServerProtocol;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.a.a.a.am */
public class Imprint implements TBase<Imprint, C0569e>, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map<C0569e, FieldMetaData> f1275d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final TStruct f1276e = new TStruct("Imprint");
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static final TField f1277f = new TField("property", (byte) 13, 1);
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final TField f1278g = new TField(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (byte) 8, 2);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final TField f1279h = new TField("checksum", (byte) 11, 3);

    /* renamed from: i */
    private static final Map<Class<? extends IScheme>, SchemeFactory> f1280i = new HashMap();

    /* renamed from: a */
    public Map<String, ImprintValue> f1281a;

    /* renamed from: b */
    public int f1282b;

    /* renamed from: c */
    public String f1283c;

    /* renamed from: l */
    private byte f1284l;

    /* renamed from: com.a.a.a.am$a */
    /* compiled from: Imprint */
    private static class C0565a extends StandardScheme<Imprint> {
        private C0565a() {
        }

        /* renamed from: a */
        public void mo9150b(TProtocol bvVar, Imprint amVar) {
            bvVar.mo9432f();
            while (true) {
                TField h = bvVar.mo9434h();
                if (h.f1476b == 0) {
                    bvVar.mo9433g();
                    if (!amVar.mo9232i()) {
                        throw new TProtocolException("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                    amVar.mo9236m();
                    return;
                }
                switch (h.f1477c) {
                    case 1:
                        if (h.f1476b != 13) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            TMap j = bvVar.mo9436j();
                            amVar.f1281a = new HashMap(j.f1482c * 2);
                            for (int i = 0; i < j.f1482c; i++) {
                                String v = bvVar.mo9448v();
                                ImprintValue anVar = new ImprintValue();
                                anVar.mo9122a(bvVar);
                                amVar.f1281a.put(v, anVar);
                            }
                            bvVar.mo9437k();
                            amVar.mo9222a(true);
                            break;
                        }
                    case 2:
                        if (h.f1476b != 8) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            amVar.f1282b = bvVar.mo9445s();
                            amVar.mo9223b(true);
                            break;
                        }
                    case 3:
                        if (h.f1476b != 11) {
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                        } else {
                            amVar.f1283c = bvVar.mo9448v();
                            amVar.mo9226c(true);
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
        public void mo9148a(TProtocol bvVar, Imprint amVar) {
            amVar.mo9236m();
            bvVar.mo9421a(Imprint.f1276e);
            if (amVar.f1281a != null) {
                bvVar.mo9418a(Imprint.f1277f);
                bvVar.mo9420a(new TMap((byte) 11, (byte) 12, amVar.f1281a.size()));
                for (Map.Entry next : amVar.f1281a.entrySet()) {
                    bvVar.mo9422a((String) next.getKey());
                    ((ImprintValue) next.getValue()).mo9127b(bvVar);
                }
                bvVar.mo9429d();
                bvVar.mo9426b();
            }
            bvVar.mo9418a(Imprint.f1278g);
            bvVar.mo9416a(amVar.f1282b);
            bvVar.mo9426b();
            if (amVar.f1283c != null) {
                bvVar.mo9418a(Imprint.f1279h);
                bvVar.mo9422a(amVar.f1283c);
                bvVar.mo9426b();
            }
            bvVar.mo9427c();
            bvVar.mo9414a();
        }
    }

    /* renamed from: com.a.a.a.am$b */
    /* compiled from: Imprint */
    private static class C0566b implements SchemeFactory {
        private C0566b() {
        }

        /* renamed from: a */
        public C0565a mo9152b() {
            return new C0565a();
        }
    }

    /* renamed from: com.a.a.a.am$c */
    /* compiled from: Imprint */
    private static class C0567c extends TupleScheme<Imprint> {
        private C0567c() {
        }

        /* renamed from: a */
        public void mo9148a(TProtocol bvVar, Imprint amVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            cbVar.mo9416a(amVar.f1281a.size());
            for (Map.Entry next : amVar.f1281a.entrySet()) {
                cbVar.mo9422a((String) next.getKey());
                ((ImprintValue) next.getValue()).mo9127b((TProtocol) cbVar);
            }
            cbVar.mo9416a(amVar.f1282b);
            cbVar.mo9422a(amVar.f1283c);
        }

        /* renamed from: b */
        public void mo9150b(TProtocol bvVar, Imprint amVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            TMap buVar = new TMap((byte) 11, (byte) 12, cbVar.mo9445s());
            amVar.f1281a = new HashMap(buVar.f1482c * 2);
            for (int i = 0; i < buVar.f1482c; i++) {
                String v = cbVar.mo9448v();
                ImprintValue anVar = new ImprintValue();
                anVar.mo9122a((TProtocol) cbVar);
                amVar.f1281a.put(v, anVar);
            }
            amVar.mo9222a(true);
            amVar.f1282b = cbVar.mo9445s();
            amVar.mo9223b(true);
            amVar.f1283c = cbVar.mo9448v();
            amVar.mo9226c(true);
        }
    }

    /* renamed from: com.a.a.a.am$d */
    /* compiled from: Imprint */
    private static class C0568d implements SchemeFactory {
        private C0568d() {
        }

        /* renamed from: a */
        public C0567c mo9152b() {
            return new C0567c();
        }
    }

    /* renamed from: com.a.a.a.am$e */
    /* compiled from: Imprint */
    public enum C0569e implements TFieldIdEnum {
        PROPERTY(1, "property"),
        VERSION(2, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION),
        CHECKSUM(3, "checksum");
        

        /* renamed from: d */
        private static final Map<String, C0569e> f1288d = null;

        /* renamed from: e */
        private final short f1290e;

        /* renamed from: f */
        private final String f1291f;

        static {
            f1288d = new HashMap();
            Iterator it = EnumSet.allOf(C0569e.class).iterator();
            while (it.hasNext()) {
                C0569e eVar = (C0569e) it.next();
                f1288d.put(eVar.mo9245b(), eVar);
            }
        }

        /* renamed from: a */
        public static C0569e m1583a(int i) {
            switch (i) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C0569e m1585b(int i) {
            C0569e a = m1583a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C0569e m1584a(String str) {
            return f1288d.get(str);
        }

        private C0569e(short s, String str) {
            this.f1290e = s;
            this.f1291f = str;
        }

        /* renamed from: a */
        public short mo9244a() {
            return this.f1290e;
        }

        /* renamed from: b */
        public String mo9245b() {
            return this.f1291f;
        }
    }

    static {
        f1280i.put(StandardScheme.class, new C0566b());
        f1280i.put(TupleScheme.class, new C0568d());
        EnumMap enumMap = new EnumMap(C0569e.class);
        enumMap.put(C0569e.PROPERTY, new FieldMetaData("property", (byte) 1, new MapMetaData((byte) 13, new FieldValueMetaData((byte) 11), new StructMetaData((byte) 12, ImprintValue.class))));
        enumMap.put(C0569e.VERSION, new FieldMetaData(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(C0569e.CHECKSUM, new FieldMetaData("checksum", (byte) 1, new FieldValueMetaData((byte) 11)));
        f1275d = Collections.unmodifiableMap(enumMap);
        FieldMetaData.m1940a(Imprint.class, f1275d);
    }

    public Imprint() {
        this.f1284l = 0;
    }

    public Imprint(Map<String, ImprintValue> map, int i, String str) {
        this();
        this.f1281a = map;
        this.f1282b = i;
        mo9223b(true);
        this.f1283c = str;
    }

    public Imprint(Imprint amVar) {
        this.f1284l = 0;
        this.f1284l = amVar.f1284l;
        if (amVar.mo9229f()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : amVar.f1281a.entrySet()) {
                hashMap.put((String) next.getKey(), new ImprintValue((ImprintValue) next.getValue()));
            }
            this.f1281a = hashMap;
        }
        this.f1282b = amVar.f1282b;
        if (amVar.mo9235l()) {
            this.f1283c = amVar.f1283c;
        }
    }

    /* renamed from: a */
    public Imprint mo9145p() {
        return new Imprint(this);
    }

    /* renamed from: b */
    public void mo9126b() {
        this.f1281a = null;
        mo9223b(false);
        this.f1282b = 0;
        this.f1283c = null;
    }

    /* renamed from: c */
    public int mo9224c() {
        if (this.f1281a == null) {
            return 0;
        }
        return this.f1281a.size();
    }

    /* renamed from: a */
    public void mo9221a(String str, ImprintValue anVar) {
        if (this.f1281a == null) {
            this.f1281a = new HashMap();
        }
        this.f1281a.put(str, anVar);
    }

    /* renamed from: d */
    public Map<String, ImprintValue> mo9227d() {
        return this.f1281a;
    }

    /* renamed from: a */
    public Imprint mo9220a(Map<String, ImprintValue> map) {
        this.f1281a = map;
        return this;
    }

    /* renamed from: e */
    public void mo9228e() {
        this.f1281a = null;
    }

    /* renamed from: f */
    public boolean mo9229f() {
        return this.f1281a != null;
    }

    /* renamed from: a */
    public void mo9222a(boolean z) {
        if (!z) {
            this.f1281a = null;
        }
    }

    /* renamed from: g */
    public int mo9230g() {
        return this.f1282b;
    }

    /* renamed from: a */
    public Imprint mo9218a(int i) {
        this.f1282b = i;
        mo9223b(true);
        return this;
    }

    /* renamed from: h */
    public void mo9231h() {
        this.f1284l = EncodingUtils.m1912b(this.f1284l, 0);
    }

    /* renamed from: i */
    public boolean mo9232i() {
        return EncodingUtils.m1910a(this.f1284l, 0);
    }

    /* renamed from: b */
    public void mo9223b(boolean z) {
        this.f1284l = EncodingUtils.m1908a(this.f1284l, 0, z);
    }

    /* renamed from: j */
    public String mo9233j() {
        return this.f1283c;
    }

    /* renamed from: a */
    public Imprint mo9219a(String str) {
        this.f1283c = str;
        return this;
    }

    /* renamed from: k */
    public void mo9234k() {
        this.f1283c = null;
    }

    /* renamed from: l */
    public boolean mo9235l() {
        return this.f1283c != null;
    }

    /* renamed from: c */
    public void mo9226c(boolean z) {
        if (!z) {
            this.f1283c = null;
        }
    }

    /* renamed from: c */
    public C0569e mo9125b(int i) {
        return C0569e.m1583a(i);
    }

    /* renamed from: a */
    public void mo9122a(TProtocol bvVar) {
        f1280i.get(bvVar.mo9456y()).mo9152b().mo9150b(bvVar, this);
    }

    /* renamed from: b */
    public void mo9127b(TProtocol bvVar) {
        f1280i.get(bvVar.mo9456y()).mo9152b().mo9148a(bvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        if (this.f1281a == null) {
            sb.append("null");
        } else {
            sb.append(this.f1281a);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f1282b);
        sb.append(", ");
        sb.append("checksum:");
        if (this.f1283c == null) {
            sb.append("null");
        } else {
            sb.append(this.f1283c);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: m */
    public void mo9236m() {
        if (this.f1281a == null) {
            throw new TProtocolException("Required field 'property' was not present! Struct: " + toString());
        } else if (this.f1283c == null) {
            throw new TProtocolException("Required field 'checksum' was not present! Struct: " + toString());
        }
    }
}
