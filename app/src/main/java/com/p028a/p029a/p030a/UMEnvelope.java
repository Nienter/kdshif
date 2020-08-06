package com.p028a.p029a.p030a;

import com.facebook.internal.ServerProtocol;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.a.a.a.ap */
public class UMEnvelope implements TBase<UMEnvelope, C0588e>, Serializable, Cloneable {

    /* renamed from: k */
    public static final Map<C0588e, FieldMetaData> f1328k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public static final TStruct f1329l = new TStruct("UMEnvelope");
    /* access modifiers changed from: private */

    /* renamed from: m */
    public static final TField f1330m = new TField(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (byte) 11, 1);
    /* access modifiers changed from: private */

    /* renamed from: n */
    public static final TField f1331n = new TField("address", (byte) 11, 2);
    /* access modifiers changed from: private */

    /* renamed from: o */
    public static final TField f1332o = new TField("signature", (byte) 11, 3);
    /* access modifiers changed from: private */

    /* renamed from: p */
    public static final TField f1333p = new TField("serial_num", (byte) 8, 4);
    /* access modifiers changed from: private */

    /* renamed from: q */
    public static final TField f1334q = new TField("ts_secs", (byte) 8, 5);
    /* access modifiers changed from: private */

    /* renamed from: r */
    public static final TField f1335r = new TField("length", (byte) 8, 6);
    /* access modifiers changed from: private */

    /* renamed from: s */
    public static final TField f1336s = new TField("entity", (byte) 11, 7);
    /* access modifiers changed from: private */

    /* renamed from: t */
    public static final TField f1337t = new TField("guid", (byte) 11, 8);
    /* access modifiers changed from: private */

    /* renamed from: u */
    public static final TField f1338u = new TField("checksum", (byte) 11, 9);
    /* access modifiers changed from: private */

    /* renamed from: v */
    public static final TField f1339v = new TField("codex", (byte) 8, 10);

    /* renamed from: w */
    private static final Map<Class<? extends IScheme>, SchemeFactory> f1340w = new HashMap();

    /* renamed from: C */
    private byte f1341C;

    /* renamed from: D */
    private C0588e[] f1342D;

    /* renamed from: a */
    public String f1343a;

    /* renamed from: b */
    public String f1344b;

    /* renamed from: c */
    public String f1345c;

    /* renamed from: d */
    public int f1346d;

    /* renamed from: e */
    public int f1347e;

    /* renamed from: f */
    public int f1348f;

    /* renamed from: g */
    public ByteBuffer f1349g;

    /* renamed from: h */
    public String f1350h;

    /* renamed from: i */
    public String f1351i;

    /* renamed from: j */
    public int f1352j;

    /* renamed from: com.a.a.a.ap$a */
    /* compiled from: UMEnvelope */
    private static class C0584a extends StandardScheme<UMEnvelope> {
        private C0584a() {
        }

        /* renamed from: a */
        public void mo9150b(TProtocol bvVar, UMEnvelope apVar) {
            bvVar.mo9432f();
            while (true) {
                TField h = bvVar.mo9434h();
                if (h.f1476b == 0) {
                    bvVar.mo9433g();
                    if (!apVar.mo9343n()) {
                        throw new TProtocolException("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!apVar.mo9346r()) {
                        throw new TProtocolException("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (!apVar.mo9350u()) {
                        throw new TProtocolException("Required field 'length' was not found in serialized data! Struct: " + toString());
                    } else {
                        apVar.mo9308I();
                        return;
                    }
                } else {
                    switch (h.f1477c) {
                        case 1:
                            if (h.f1476b != 11) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1343a = bvVar.mo9448v();
                                apVar.mo9314a(true);
                                break;
                            }
                        case 2:
                            if (h.f1476b != 11) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1344b = bvVar.mo9448v();
                                apVar.mo9316b(true);
                                break;
                            }
                        case 3:
                            if (h.f1476b != 11) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1345c = bvVar.mo9448v();
                                apVar.mo9320c(true);
                                break;
                            }
                        case 4:
                            if (h.f1476b != 8) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1346d = bvVar.mo9445s();
                                apVar.mo9324d(true);
                                break;
                            }
                        case 5:
                            if (h.f1476b != 8) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1347e = bvVar.mo9445s();
                                apVar.mo9327e(true);
                                break;
                            }
                        case 6:
                            if (h.f1476b != 8) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1348f = bvVar.mo9445s();
                                apVar.mo9331f(true);
                                break;
                            }
                        case 7:
                            if (h.f1476b != 11) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1349g = bvVar.mo9449w();
                                apVar.mo9333g(true);
                                break;
                            }
                        case 8:
                            if (h.f1476b != 11) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1350h = bvVar.mo9448v();
                                apVar.mo9334h(true);
                                break;
                            }
                        case 9:
                            if (h.f1476b != 11) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1351i = bvVar.mo9448v();
                                apVar.mo9337i(true);
                                break;
                            }
                        case 10:
                            if (h.f1476b != 8) {
                                TProtocolUtil.m2077a(bvVar, h.f1476b);
                                break;
                            } else {
                                apVar.f1352j = bvVar.mo9445s();
                                apVar.mo9339j(true);
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
        public void mo9148a(TProtocol bvVar, UMEnvelope apVar) {
            apVar.mo9308I();
            bvVar.mo9421a(UMEnvelope.f1329l);
            if (apVar.f1343a != null) {
                bvVar.mo9418a(UMEnvelope.f1330m);
                bvVar.mo9422a(apVar.f1343a);
                bvVar.mo9426b();
            }
            if (apVar.f1344b != null) {
                bvVar.mo9418a(UMEnvelope.f1331n);
                bvVar.mo9422a(apVar.f1344b);
                bvVar.mo9426b();
            }
            if (apVar.f1345c != null) {
                bvVar.mo9418a(UMEnvelope.f1332o);
                bvVar.mo9422a(apVar.f1345c);
                bvVar.mo9426b();
            }
            bvVar.mo9418a(UMEnvelope.f1333p);
            bvVar.mo9416a(apVar.f1346d);
            bvVar.mo9426b();
            bvVar.mo9418a(UMEnvelope.f1334q);
            bvVar.mo9416a(apVar.f1347e);
            bvVar.mo9426b();
            bvVar.mo9418a(UMEnvelope.f1335r);
            bvVar.mo9416a(apVar.f1348f);
            bvVar.mo9426b();
            if (apVar.f1349g != null) {
                bvVar.mo9418a(UMEnvelope.f1336s);
                bvVar.mo9423a(apVar.f1349g);
                bvVar.mo9426b();
            }
            if (apVar.f1350h != null) {
                bvVar.mo9418a(UMEnvelope.f1337t);
                bvVar.mo9422a(apVar.f1350h);
                bvVar.mo9426b();
            }
            if (apVar.f1351i != null) {
                bvVar.mo9418a(UMEnvelope.f1338u);
                bvVar.mo9422a(apVar.f1351i);
                bvVar.mo9426b();
            }
            if (apVar.mo9307H()) {
                bvVar.mo9418a(UMEnvelope.f1339v);
                bvVar.mo9416a(apVar.f1352j);
                bvVar.mo9426b();
            }
            bvVar.mo9427c();
            bvVar.mo9414a();
        }
    }

    /* renamed from: com.a.a.a.ap$b */
    /* compiled from: UMEnvelope */
    private static class C0585b implements SchemeFactory {
        private C0585b() {
        }

        /* renamed from: a */
        public C0584a mo9152b() {
            return new C0584a();
        }
    }

    /* renamed from: com.a.a.a.ap$c */
    /* compiled from: UMEnvelope */
    private static class C0586c extends TupleScheme<UMEnvelope> {
        private C0586c() {
        }

        /* renamed from: a */
        public void mo9148a(TProtocol bvVar, UMEnvelope apVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            cbVar.mo9422a(apVar.f1343a);
            cbVar.mo9422a(apVar.f1344b);
            cbVar.mo9422a(apVar.f1345c);
            cbVar.mo9416a(apVar.f1346d);
            cbVar.mo9416a(apVar.f1347e);
            cbVar.mo9416a(apVar.f1348f);
            cbVar.mo9423a(apVar.f1349g);
            cbVar.mo9422a(apVar.f1350h);
            cbVar.mo9422a(apVar.f1351i);
            BitSet bitSet = new BitSet();
            if (apVar.mo9307H()) {
                bitSet.set(0);
            }
            cbVar.mo9459a(bitSet, 1);
            if (apVar.mo9307H()) {
                cbVar.mo9416a(apVar.f1352j);
            }
        }

        /* renamed from: b */
        public void mo9150b(TProtocol bvVar, UMEnvelope apVar) {
            TTupleProtocol cbVar = (TTupleProtocol) bvVar;
            apVar.f1343a = cbVar.mo9448v();
            apVar.mo9314a(true);
            apVar.f1344b = cbVar.mo9448v();
            apVar.mo9316b(true);
            apVar.f1345c = cbVar.mo9448v();
            apVar.mo9320c(true);
            apVar.f1346d = cbVar.mo9445s();
            apVar.mo9324d(true);
            apVar.f1347e = cbVar.mo9445s();
            apVar.mo9327e(true);
            apVar.f1348f = cbVar.mo9445s();
            apVar.mo9331f(true);
            apVar.f1349g = cbVar.mo9449w();
            apVar.mo9333g(true);
            apVar.f1350h = cbVar.mo9448v();
            apVar.mo9334h(true);
            apVar.f1351i = cbVar.mo9448v();
            apVar.mo9337i(true);
            if (cbVar.mo9460b(1).get(0)) {
                apVar.f1352j = cbVar.mo9445s();
                apVar.mo9339j(true);
            }
        }
    }

    /* renamed from: com.a.a.a.ap$d */
    /* compiled from: UMEnvelope */
    private static class C0587d implements SchemeFactory {
        private C0587d() {
        }

        /* renamed from: a */
        public C0586c mo9152b() {
            return new C0586c();
        }
    }

    /* renamed from: com.a.a.a.ap$e */
    /* compiled from: UMEnvelope */
    public enum C0588e implements TFieldIdEnum {
        VERSION(1, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION),
        ADDRESS(2, "address"),
        SIGNATURE(3, "signature"),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");
        

        /* renamed from: k */
        private static final Map<String, C0588e> f1363k = null;

        /* renamed from: l */
        private final short f1365l;

        /* renamed from: m */
        private final String f1366m;

        static {
            f1363k = new HashMap();
            Iterator it = EnumSet.allOf(C0588e.class).iterator();
            while (it.hasNext()) {
                C0588e eVar = (C0588e) it.next();
                f1363k.put(eVar.mo9363b(), eVar);
            }
        }

        /* renamed from: a */
        public static C0588e m1759a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C0588e m1761b(int i) {
            C0588e a = m1759a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C0588e m1760a(String str) {
            return f1363k.get(str);
        }

        private C0588e(short s, String str) {
            this.f1365l = s;
            this.f1366m = str;
        }

        /* renamed from: a */
        public short mo9362a() {
            return this.f1365l;
        }

        /* renamed from: b */
        public String mo9363b() {
            return this.f1366m;
        }
    }

    static {
        f1340w.put(StandardScheme.class, new C0585b());
        f1340w.put(TupleScheme.class, new C0587d());
        EnumMap enumMap = new EnumMap(C0588e.class);
        enumMap.put(C0588e.VERSION, new FieldMetaData(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0588e.ADDRESS, new FieldMetaData("address", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0588e.SIGNATURE, new FieldMetaData("signature", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0588e.SERIAL_NUM, new FieldMetaData("serial_num", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(C0588e.TS_SECS, new FieldMetaData("ts_secs", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(C0588e.LENGTH, new FieldMetaData("length", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(C0588e.ENTITY, new FieldMetaData("entity", (byte) 1, new FieldValueMetaData((byte) 11, true)));
        enumMap.put(C0588e.GUID, new FieldMetaData("guid", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0588e.CHECKSUM, new FieldMetaData("checksum", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(C0588e.CODEX, new FieldMetaData("codex", (byte) 2, new FieldValueMetaData((byte) 8)));
        f1328k = Collections.unmodifiableMap(enumMap);
        FieldMetaData.m1940a(UMEnvelope.class, f1328k);
    }

    public UMEnvelope() {
        this.f1341C = 0;
        this.f1342D = new C0588e[]{C0588e.CODEX};
    }

    public UMEnvelope(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f1343a = str;
        this.f1344b = str2;
        this.f1345c = str3;
        this.f1346d = i;
        mo9324d(true);
        this.f1347e = i2;
        mo9327e(true);
        this.f1348f = i3;
        mo9331f(true);
        this.f1349g = byteBuffer;
        this.f1350h = str4;
        this.f1351i = str5;
    }

    public UMEnvelope(UMEnvelope apVar) {
        this.f1341C = 0;
        this.f1342D = new C0588e[]{C0588e.CODEX};
        this.f1341C = apVar.f1341C;
        if (apVar.mo9328e()) {
            this.f1343a = apVar.f1343a;
        }
        if (apVar.mo9335h()) {
            this.f1344b = apVar.f1344b;
        }
        if (apVar.mo9340k()) {
            this.f1345c = apVar.f1345c;
        }
        this.f1346d = apVar.f1346d;
        this.f1347e = apVar.f1347e;
        this.f1348f = apVar.f1348f;
        if (apVar.mo9354y()) {
            this.f1349g = TBaseHelper.m1936d(apVar.f1349g);
        }
        if (apVar.mo9301B()) {
            this.f1350h = apVar.f1350h;
        }
        if (apVar.mo9304E()) {
            this.f1351i = apVar.f1351i;
        }
        this.f1352j = apVar.f1352j;
    }

    /* renamed from: a */
    public UMEnvelope mo9145p() {
        return new UMEnvelope(this);
    }

    /* renamed from: b */
    public void mo9126b() {
        this.f1343a = null;
        this.f1344b = null;
        this.f1345c = null;
        mo9324d(false);
        this.f1346d = 0;
        mo9327e(false);
        this.f1347e = 0;
        mo9331f(false);
        this.f1348f = 0;
        this.f1349g = null;
        this.f1350h = null;
        this.f1351i = null;
        mo9339j(false);
        this.f1352j = 0;
    }

    /* renamed from: c */
    public String mo9319c() {
        return this.f1343a;
    }

    /* renamed from: a */
    public UMEnvelope mo9311a(String str) {
        this.f1343a = str;
        return this;
    }

    /* renamed from: d */
    public void mo9323d() {
        this.f1343a = null;
    }

    /* renamed from: e */
    public boolean mo9328e() {
        return this.f1343a != null;
    }

    /* renamed from: a */
    public void mo9314a(boolean z) {
        if (!z) {
            this.f1343a = null;
        }
    }

    /* renamed from: f */
    public String mo9330f() {
        return this.f1344b;
    }

    /* renamed from: b */
    public UMEnvelope mo9315b(String str) {
        this.f1344b = str;
        return this;
    }

    /* renamed from: g */
    public void mo9332g() {
        this.f1344b = null;
    }

    /* renamed from: h */
    public boolean mo9335h() {
        return this.f1344b != null;
    }

    /* renamed from: b */
    public void mo9316b(boolean z) {
        if (!z) {
            this.f1344b = null;
        }
    }

    /* renamed from: i */
    public String mo9336i() {
        return this.f1345c;
    }

    /* renamed from: c */
    public UMEnvelope mo9318c(String str) {
        this.f1345c = str;
        return this;
    }

    /* renamed from: j */
    public void mo9338j() {
        this.f1345c = null;
    }

    /* renamed from: k */
    public boolean mo9340k() {
        return this.f1345c != null;
    }

    /* renamed from: c */
    public void mo9320c(boolean z) {
        if (!z) {
            this.f1345c = null;
        }
    }

    /* renamed from: l */
    public int mo9341l() {
        return this.f1346d;
    }

    /* renamed from: a */
    public UMEnvelope mo9310a(int i) {
        this.f1346d = i;
        mo9324d(true);
        return this;
    }

    /* renamed from: m */
    public void mo9342m() {
        this.f1341C = EncodingUtils.m1912b(this.f1341C, 0);
    }

    /* renamed from: n */
    public boolean mo9343n() {
        return EncodingUtils.m1910a(this.f1341C, 0);
    }

    /* renamed from: d */
    public void mo9324d(boolean z) {
        this.f1341C = EncodingUtils.m1908a(this.f1341C, 0, z);
    }

    /* renamed from: o */
    public int mo9344o() {
        return this.f1347e;
    }

    /* renamed from: c */
    public UMEnvelope mo9317c(int i) {
        this.f1347e = i;
        mo9327e(true);
        return this;
    }

    /* renamed from: q */
    public void mo9345q() {
        this.f1341C = EncodingUtils.m1912b(this.f1341C, 1);
    }

    /* renamed from: r */
    public boolean mo9346r() {
        return EncodingUtils.m1910a(this.f1341C, 1);
    }

    /* renamed from: e */
    public void mo9327e(boolean z) {
        this.f1341C = EncodingUtils.m1908a(this.f1341C, 1, z);
    }

    /* renamed from: s */
    public int mo9347s() {
        return this.f1348f;
    }

    /* renamed from: d */
    public UMEnvelope mo9321d(int i) {
        this.f1348f = i;
        mo9331f(true);
        return this;
    }

    /* renamed from: t */
    public void mo9348t() {
        this.f1341C = EncodingUtils.m1912b(this.f1341C, 2);
    }

    /* renamed from: u */
    public boolean mo9350u() {
        return EncodingUtils.m1910a(this.f1341C, 2);
    }

    /* renamed from: f */
    public void mo9331f(boolean z) {
        this.f1341C = EncodingUtils.m1908a(this.f1341C, 2, z);
    }

    /* renamed from: v */
    public byte[] mo9351v() {
        mo9312a(TBaseHelper.m1935c(this.f1349g));
        if (this.f1349g == null) {
            return null;
        }
        return this.f1349g.array();
    }

    /* renamed from: w */
    public ByteBuffer mo9352w() {
        return this.f1349g;
    }

    /* renamed from: a */
    public UMEnvelope mo9313a(byte[] bArr) {
        mo9312a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    /* renamed from: a */
    public UMEnvelope mo9312a(ByteBuffer byteBuffer) {
        this.f1349g = byteBuffer;
        return this;
    }

    /* renamed from: x */
    public void mo9353x() {
        this.f1349g = null;
    }

    /* renamed from: y */
    public boolean mo9354y() {
        return this.f1349g != null;
    }

    /* renamed from: g */
    public void mo9333g(boolean z) {
        if (!z) {
            this.f1349g = null;
        }
    }

    /* renamed from: z */
    public String mo9355z() {
        return this.f1350h;
    }

    /* renamed from: d */
    public UMEnvelope mo9322d(String str) {
        this.f1350h = str;
        return this;
    }

    /* renamed from: A */
    public void mo9300A() {
        this.f1350h = null;
    }

    /* renamed from: B */
    public boolean mo9301B() {
        return this.f1350h != null;
    }

    /* renamed from: h */
    public void mo9334h(boolean z) {
        if (!z) {
            this.f1350h = null;
        }
    }

    /* renamed from: C */
    public String mo9302C() {
        return this.f1351i;
    }

    /* renamed from: e */
    public UMEnvelope mo9326e(String str) {
        this.f1351i = str;
        return this;
    }

    /* renamed from: D */
    public void mo9303D() {
        this.f1351i = null;
    }

    /* renamed from: E */
    public boolean mo9304E() {
        return this.f1351i != null;
    }

    /* renamed from: i */
    public void mo9337i(boolean z) {
        if (!z) {
            this.f1351i = null;
        }
    }

    /* renamed from: F */
    public int mo9305F() {
        return this.f1352j;
    }

    /* renamed from: e */
    public UMEnvelope mo9325e(int i) {
        this.f1352j = i;
        mo9339j(true);
        return this;
    }

    /* renamed from: G */
    public void mo9306G() {
        this.f1341C = EncodingUtils.m1912b(this.f1341C, 3);
    }

    /* renamed from: H */
    public boolean mo9307H() {
        return EncodingUtils.m1910a(this.f1341C, 3);
    }

    /* renamed from: j */
    public void mo9339j(boolean z) {
        this.f1341C = EncodingUtils.m1908a(this.f1341C, 3, z);
    }

    /* renamed from: f */
    public C0588e mo9125b(int i) {
        return C0588e.m1759a(i);
    }

    /* renamed from: a */
    public void mo9122a(TProtocol bvVar) {
        f1340w.get(bvVar.mo9456y()).mo9152b().mo9150b(bvVar, this);
    }

    /* renamed from: b */
    public void mo9127b(TProtocol bvVar) {
        f1340w.get(bvVar.mo9456y()).mo9152b().mo9148a(bvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        if (this.f1343a == null) {
            sb.append("null");
        } else {
            sb.append(this.f1343a);
        }
        sb.append(", ");
        sb.append("address:");
        if (this.f1344b == null) {
            sb.append("null");
        } else {
            sb.append(this.f1344b);
        }
        sb.append(", ");
        sb.append("signature:");
        if (this.f1345c == null) {
            sb.append("null");
        } else {
            sb.append(this.f1345c);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.f1346d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.f1347e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f1348f);
        sb.append(", ");
        sb.append("entity:");
        if (this.f1349g == null) {
            sb.append("null");
        } else {
            TBaseHelper.m1932a(this.f1349g, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        if (this.f1350h == null) {
            sb.append("null");
        } else {
            sb.append(this.f1350h);
        }
        sb.append(", ");
        sb.append("checksum:");
        if (this.f1351i == null) {
            sb.append("null");
        } else {
            sb.append(this.f1351i);
        }
        if (mo9307H()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.f1352j);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: I */
    public void mo9308I() {
        if (this.f1343a == null) {
            throw new TProtocolException("Required field 'version' was not present! Struct: " + toString());
        } else if (this.f1344b == null) {
            throw new TProtocolException("Required field 'address' was not present! Struct: " + toString());
        } else if (this.f1345c == null) {
            throw new TProtocolException("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.f1349g == null) {
            throw new TProtocolException("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.f1350h == null) {
            throw new TProtocolException("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.f1351i == null) {
            throw new TProtocolException("Required field 'checksum' was not present! Struct: " + toString());
        }
    }
}
