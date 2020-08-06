package com.p028a.p029a.p030a;

import com.facebook.internal.ServerProtocol;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.a.a.a.ak */
public class IdSnapshot implements TBase<IdSnapshot, C0557e>, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map<C0557e, FieldMetaData> f1241d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final TStruct f1242e = new TStruct("IdSnapshot");
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static final TField f1243f = new TField("identity", (byte) 11, 1);
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final TField f1244g = new TField("ts", (byte) 10, 2);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final TField f1245h = new TField(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (byte) 8, 3);

    /* renamed from: i */
    private static final Map<Class<? extends IScheme>, SchemeFactory> f1246i = new HashMap();

    /* renamed from: a */
    public String f1247a;

    /* renamed from: b */
    public long f1248b;

    /* renamed from: c */
    public int f1249c;

    /* renamed from: m */
    private byte f1250m;

    /* renamed from: com.a.a.a.ak$a */
    /* compiled from: IdSnapshot */
    private static class C0553a extends StandardScheme<IdSnapshot> {
        private C0553a() {
        }

        /* renamed from: a */
        public void mo9150b(TProtocol bvVar, IdSnapshot akVar) {
            bvVar.mo9432f();
            while (true) {
                TField h = bvVar.mo9434h();
                if (h.f1476b == 0) {
                    bvVar.mo9433g();
                    if (!akVar.mo9171h()) {
                        throw new TProtocolException("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (!akVar.mo9174k()) {
                        throw new TProtocolException("Required field 'version' was not found in serialized data! Struct: " + toString());
                    } else {
                        akVar.mo9175l();
                        return;
                    }
                } else {
                    switch (h.f1477c) {
                        case 1:
                            if (h.f1476b != 11) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                akVar.f1247a = bvVar.mo9448v();
                                akVar.mo9162a(true);
                                break;
                            }
                        case 2:
                            if (h.f1476b != 10) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                akVar.f1248b = bvVar.mo9446t();
                                akVar.mo9163b(true);
                                break;
                            }
                        case 3:
                            if (h.f1476b != 8) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                akVar.f1249c = bvVar.mo9445s();
                                akVar.mo9166c(true);
                                break;
                            }
                        default:
                            TProtocolUtil.m2077a(bvVar, h.f1476b);
                            break;
                    }
                    bvVar.mo9435i();
                }
            }
        }

        /* renamed from: b */
        public void mo9148a(TProtocol bvVar, IdSnapshot akVar) {
            akVar.mo9175l();
            bvVar.mo9421a(IdSnapshot.f1242e);
            if (akVar.f1247a != null) {
                bvVar.mo9418a(IdSnapshot.f1243f);
                bvVar.mo9422a(akVar.f1247a);
                bvVar.mo9426b();
            }
            bvVar.mo9418a(IdSnapshot.f1244g);
            bvVar.mo9417a(akVar.f1248b);
            bvVar.mo9426b();
            bvVar.mo9418a(IdSnapshot.f1245h);
            bvVar.mo9416a(akVar.f1249c);
            bvVar.mo9426b();
            bvVar.mo9427c();
            bvVar.mo9414a();
        }
    }

    /* renamed from: com.a.a.a.ak$b */
    /* compiled from: IdSnapshot */
    private static class C0554b implements SchemeFactory {
        private C0554b() {
        }

        /* renamed from: a */
        public C0553a mo9152b() {
            return new C0553a();
        }
    }

    /* renamed from: com.a.a.a.ak$c */
    /* compiled from: IdSnapshot */
    private static class C0555c extends TupleScheme<IdSnapshot> {
        private C0555c() {
        }

        /* renamed from: a */
        public void mo9148a(TProtocol bvVar, IdSnapshot akVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            cbVar.mo9422a(akVar.f1247a);
            cbVar.mo9417a(akVar.f1248b);
            cbVar.mo9416a(akVar.f1249c);
        }

        /* renamed from: b */
        public void mo9150b(TProtocol bvVar, IdSnapshot akVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            akVar.f1247a = cbVar.mo9448v();
            akVar.mo9162a(true);
            akVar.f1248b = cbVar.mo9446t();
            akVar.mo9163b(true);
            akVar.f1249c = cbVar.mo9445s();
            akVar.mo9166c(true);
        }
    }

    /* renamed from: com.a.a.a.ak$d */
    /* compiled from: IdSnapshot */
    private static class C0556d implements SchemeFactory {
        private C0556d() {
        }

        /* renamed from: a */
        public C0555c mo9152b() {
            return new C0555c();
        }
    }

    /* renamed from: com.a.a.a.ak$e */
    /* compiled from: IdSnapshot */
    public enum C0557e implements TFieldIdEnum {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
        

        /* renamed from: d */
        private static final Map<String, C0557e> f1254d = null;

        /* renamed from: e */
        private final short f1256e;

        /* renamed from: f */
        private final String f1257f;

        static {
            f1254d = new HashMap();
            Iterator it = EnumSet.allOf(C0557e.class).iterator();
            while (it.hasNext()) {
                C0557e eVar = (C0557e) it.next();
                f1254d.put(eVar.mo9184b(), eVar);
            }
        }

        /* renamed from: a */
        public static C0557e m1488a(int i) {
            switch (i) {
                case 1:
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C0557e m1490b(int i) {
            C0557e a = m1488a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C0557e m1489a(String str) {
            return f1254d.get(str);
        }

        private C0557e(short s, String str) {
            this.f1256e = s;
            this.f1257f = str;
        }

        /* renamed from: a */
        public short mo9183a() {
            return this.f1256e;
        }

        /* renamed from: b */
        public String mo9184b() {
            return this.f1257f;
        }
    }

    static {
        f1246i.put(StandardScheme.class, new C0554b());
        f1246i.put(TupleScheme.class, new C0556d());
        EnumMap enumMap = new EnumMap(C0557e.class);
        enumMap.put(C0557e.IDENTITY, new FieldMetaData("identity", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0557e.TS, new FieldMetaData("ts", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(C0557e.VERSION, new FieldMetaData(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (byte) 1, new FieldValueMetaData((byte) 8)));
        f1241d = Collections.unmodifiableMap(enumMap);
        FieldMetaData.m1940a(IdSnapshot.class, f1241d);
    }

    public IdSnapshot() {
        this.f1250m = 0;
    }

    public IdSnapshot(String str, long j, int i) {
        this();
        this.f1247a = str;
        this.f1248b = j;
        mo9163b(true);
        this.f1249c = i;
        mo9166c(true);
    }

    public IdSnapshot(IdSnapshot akVar) {
        this.f1250m = 0;
        this.f1250m = akVar.f1250m;
        if (akVar.mo9168e()) {
            this.f1247a = akVar.f1247a;
        }
        this.f1248b = akVar.f1248b;
        this.f1249c = akVar.f1249c;
    }

    /* renamed from: a */
    public IdSnapshot mo9145p() {
        return new IdSnapshot(this);
    }

    /* renamed from: b */
    public void mo9126b() {
        this.f1247a = null;
        mo9163b(false);
        this.f1248b = 0;
        mo9166c(false);
        this.f1249c = 0;
    }

    /* renamed from: c */
    public String mo9165c() {
        return this.f1247a;
    }

    /* renamed from: a */
    public IdSnapshot mo9161a(String str) {
        this.f1247a = str;
        return this;
    }

    /* renamed from: d */
    public void mo9167d() {
        this.f1247a = null;
    }

    /* renamed from: e */
    public boolean mo9168e() {
        return this.f1247a != null;
    }

    /* renamed from: a */
    public void mo9162a(boolean z) {
        if (!z) {
            this.f1247a = null;
        }
    }

    /* renamed from: f */
    public long mo9169f() {
        return this.f1248b;
    }

    /* renamed from: a */
    public IdSnapshot mo9160a(long j) {
        this.f1248b = j;
        mo9163b(true);
        return this;
    }

    /* renamed from: g */
    public void mo9170g() {
        this.f1250m = EncodingUtils.m1912b(this.f1250m, 0);
    }

    /* renamed from: h */
    public boolean mo9171h() {
        return EncodingUtils.m1910a(this.f1250m, 0);
    }

    /* renamed from: b */
    public void mo9163b(boolean z) {
        this.f1250m = EncodingUtils.m1908a(this.f1250m, 0, z);
    }

    /* renamed from: i */
    public int mo9172i() {
        return this.f1249c;
    }

    /* renamed from: a */
    public IdSnapshot mo9159a(int i) {
        this.f1249c = i;
        mo9166c(true);
        return this;
    }

    /* renamed from: j */
    public void mo9173j() {
        this.f1250m = EncodingUtils.m1912b(this.f1250m, 1);
    }

    /* renamed from: k */
    public boolean mo9174k() {
        return EncodingUtils.m1910a(this.f1250m, 1);
    }

    /* renamed from: c */
    public void mo9166c(boolean z) {
        this.f1250m = EncodingUtils.m1908a(this.f1250m, 1, z);
    }

    /* renamed from: c */
    public C0557e mo9125b(int i) {
        return C0557e.m1488a(i);
    }

    /* renamed from: a */
    public void mo9122a(TProtocol bvVar) {
        f1246i.get(bvVar.mo9456y()).mo9152b().mo9150b(bvVar, this);
    }

    /* renamed from: b */
    public void mo9127b(TProtocol bvVar) {
        f1246i.get(bvVar.mo9456y()).mo9152b().mo9148a(bvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        if (this.f1247a == null) {
            sb.append("null");
        } else {
            sb.append(this.f1247a);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f1248b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f1249c);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: l */
    public void mo9175l() {
        if (this.f1247a == null) {
            throw new TProtocolException("Required field 'identity' was not present! Struct: " + toString());
        }
    }
}
