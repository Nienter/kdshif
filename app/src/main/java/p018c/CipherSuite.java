package p018c;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: c.h */
public final class CipherSuite {

    /* renamed from: A */
    public static final CipherSuite f869A = m1082a("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);

    /* renamed from: B */
    public static final CipherSuite f870B = m1082a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);

    /* renamed from: C */
    public static final CipherSuite f871C = m1082a("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);

    /* renamed from: D */
    public static final CipherSuite f872D = m1082a("TLS_RSA_WITH_AES_128_CBC_SHA", 47);

    /* renamed from: E */
    public static final CipherSuite f873E = m1082a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);

    /* renamed from: F */
    public static final CipherSuite f874F = m1082a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);

    /* renamed from: G */
    public static final CipherSuite f875G = m1082a("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);

    /* renamed from: H */
    public static final CipherSuite f876H = m1082a("TLS_RSA_WITH_AES_256_CBC_SHA", 53);

    /* renamed from: I */
    public static final CipherSuite f877I = m1082a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);

    /* renamed from: J */
    public static final CipherSuite f878J = m1082a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);

    /* renamed from: K */
    public static final CipherSuite f879K = m1082a("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);

    /* renamed from: L */
    public static final CipherSuite f880L = m1082a("TLS_RSA_WITH_NULL_SHA256", 59);

    /* renamed from: M */
    public static final CipherSuite f881M = m1082a("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);

    /* renamed from: N */
    public static final CipherSuite f882N = m1082a("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);

    /* renamed from: O */
    public static final CipherSuite f883O = m1082a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);

    /* renamed from: P */
    public static final CipherSuite f884P = m1082a("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);

    /* renamed from: Q */
    public static final CipherSuite f885Q = m1082a("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);

    /* renamed from: R */
    public static final CipherSuite f886R = m1082a("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);

    /* renamed from: S */
    public static final CipherSuite f887S = m1082a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);

    /* renamed from: T */
    public static final CipherSuite f888T = m1082a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);

    /* renamed from: U */
    public static final CipherSuite f889U = m1082a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);

    /* renamed from: V */
    public static final CipherSuite f890V = m1082a("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);

    /* renamed from: W */
    public static final CipherSuite f891W = m1082a("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);

    /* renamed from: X */
    public static final CipherSuite f892X = m1082a("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);

    /* renamed from: Y */
    public static final CipherSuite f893Y = m1082a("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);

    /* renamed from: Z */
    public static final CipherSuite f894Z = m1082a("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);

    /* renamed from: a */
    public static final CipherSuite f895a = m1082a("SSL_RSA_WITH_NULL_MD5", 1);

    /* renamed from: aA */
    public static final CipherSuite f896aA = m1082a("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);

    /* renamed from: aB */
    public static final CipherSuite f897aB = m1082a("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);

    /* renamed from: aC */
    public static final CipherSuite f898aC = m1082a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);

    /* renamed from: aD */
    public static final CipherSuite f899aD = m1082a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);

    /* renamed from: aE */
    public static final CipherSuite f900aE = m1082a("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);

    /* renamed from: aF */
    public static final CipherSuite f901aF = m1082a("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);

    /* renamed from: aG */
    public static final CipherSuite f902aG = m1082a("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);

    /* renamed from: aH */
    public static final CipherSuite f903aH = m1082a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);

    /* renamed from: aI */
    public static final CipherSuite f904aI = m1082a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);

    /* renamed from: aJ */
    public static final CipherSuite f905aJ = m1082a("TLS_ECDH_anon_WITH_NULL_SHA", 49173);

    /* renamed from: aK */
    public static final CipherSuite f906aK = m1082a("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);

    /* renamed from: aL */
    public static final CipherSuite f907aL = m1082a("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);

    /* renamed from: aM */
    public static final CipherSuite f908aM = m1082a("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);

    /* renamed from: aN */
    public static final CipherSuite f909aN = m1082a("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);

    /* renamed from: aO */
    public static final CipherSuite f910aO = m1082a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);

    /* renamed from: aP */
    public static final CipherSuite f911aP = m1082a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);

    /* renamed from: aQ */
    public static final CipherSuite f912aQ = m1082a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);

    /* renamed from: aR */
    public static final CipherSuite f913aR = m1082a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);

    /* renamed from: aS */
    public static final CipherSuite f914aS = m1082a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);

    /* renamed from: aT */
    public static final CipherSuite f915aT = m1082a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);

    /* renamed from: aU */
    public static final CipherSuite f916aU = m1082a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);

    /* renamed from: aV */
    public static final CipherSuite f917aV = m1082a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);

    /* renamed from: aW */
    public static final CipherSuite f918aW = m1082a("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);

    /* renamed from: aX */
    public static final CipherSuite f919aX = m1082a("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);

    /* renamed from: aY */
    public static final CipherSuite f920aY = m1082a("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);

    /* renamed from: aZ */
    public static final CipherSuite f921aZ = m1082a("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);

    /* renamed from: aa */
    public static final CipherSuite f922aa = m1082a("TLS_PSK_WITH_RC4_128_SHA", 138);

    /* renamed from: ab */
    public static final CipherSuite f923ab = m1082a("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);

    /* renamed from: ac */
    public static final CipherSuite f924ac = m1082a("TLS_PSK_WITH_AES_128_CBC_SHA", 140);

    /* renamed from: ad */
    public static final CipherSuite f925ad = m1082a("TLS_PSK_WITH_AES_256_CBC_SHA", 141);

    /* renamed from: ae */
    public static final CipherSuite f926ae = m1082a("TLS_RSA_WITH_SEED_CBC_SHA", 150);

    /* renamed from: af */
    public static final CipherSuite f927af = m1082a("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);

    /* renamed from: ag */
    public static final CipherSuite f928ag = m1082a("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);

    /* renamed from: ah */
    public static final CipherSuite f929ah = m1082a("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);

    /* renamed from: ai */
    public static final CipherSuite f930ai = m1082a("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);

    /* renamed from: aj */
    public static final CipherSuite f931aj = m1082a("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);

    /* renamed from: ak */
    public static final CipherSuite f932ak = m1082a("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);

    /* renamed from: al */
    public static final CipherSuite f933al = m1082a("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);

    /* renamed from: am */
    public static final CipherSuite f934am = m1082a("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);

    /* renamed from: an */
    public static final CipherSuite f935an = m1082a("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);

    /* renamed from: ao */
    public static final CipherSuite f936ao = m1082a("TLS_FALLBACK_SCSV", 22016);

    /* renamed from: ap */
    public static final CipherSuite f937ap = m1082a("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);

    /* renamed from: aq */
    public static final CipherSuite f938aq = m1082a("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);

    /* renamed from: ar */
    public static final CipherSuite f939ar = m1082a("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);

    /* renamed from: as */
    public static final CipherSuite f940as = m1082a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);

    /* renamed from: at */
    public static final CipherSuite f941at = m1082a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);

    /* renamed from: au */
    public static final CipherSuite f942au = m1082a("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);

    /* renamed from: av */
    public static final CipherSuite f943av = m1082a("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);

    /* renamed from: aw */
    public static final CipherSuite f944aw = m1082a("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);

    /* renamed from: ax */
    public static final CipherSuite f945ax = m1082a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);

    /* renamed from: ay */
    public static final CipherSuite f946ay = m1082a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);

    /* renamed from: az */
    public static final CipherSuite f947az = m1082a("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);

    /* renamed from: b */
    public static final CipherSuite f948b = m1082a("SSL_RSA_WITH_NULL_SHA", 2);

    /* renamed from: ba */
    public static final CipherSuite f949ba = m1082a("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);

    /* renamed from: bb */
    public static final CipherSuite f950bb = m1082a("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);

    /* renamed from: bc */
    public static final CipherSuite f951bc = m1082a("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);

    /* renamed from: bd */
    public static final CipherSuite f952bd = m1082a("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);

    /* renamed from: be */
    public static final CipherSuite f953be = m1082a("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);

    /* renamed from: bf */
    public static final CipherSuite f954bf = m1082a("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);

    /* renamed from: bg */
    public static final CipherSuite f955bg = m1082a("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);

    /* renamed from: bh */
    public static final CipherSuite f956bh = m1082a("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);

    /* renamed from: bj */
    private static final ConcurrentMap<String, CipherSuite> f957bj = new ConcurrentHashMap();

    /* renamed from: c */
    public static final CipherSuite f958c = m1082a("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);

    /* renamed from: d */
    public static final CipherSuite f959d = m1082a("SSL_RSA_WITH_RC4_128_MD5", 4);

    /* renamed from: e */
    public static final CipherSuite f960e = m1082a("SSL_RSA_WITH_RC4_128_SHA", 5);

    /* renamed from: f */
    public static final CipherSuite f961f = m1082a("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);

    /* renamed from: g */
    public static final CipherSuite f962g = m1082a("SSL_RSA_WITH_DES_CBC_SHA", 9);

    /* renamed from: h */
    public static final CipherSuite f963h = m1082a("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);

    /* renamed from: i */
    public static final CipherSuite f964i = m1082a("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);

    /* renamed from: j */
    public static final CipherSuite f965j = m1082a("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);

    /* renamed from: k */
    public static final CipherSuite f966k = m1082a("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);

    /* renamed from: l */
    public static final CipherSuite f967l = m1082a("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);

    /* renamed from: m */
    public static final CipherSuite f968m = m1082a("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);

    /* renamed from: n */
    public static final CipherSuite f969n = m1082a("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);

    /* renamed from: o */
    public static final CipherSuite f970o = m1082a("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);

    /* renamed from: p */
    public static final CipherSuite f971p = m1082a("SSL_DH_anon_WITH_RC4_128_MD5", 24);

    /* renamed from: q */
    public static final CipherSuite f972q = m1082a("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);

    /* renamed from: r */
    public static final CipherSuite f973r = m1082a("SSL_DH_anon_WITH_DES_CBC_SHA", 26);

    /* renamed from: s */
    public static final CipherSuite f974s = m1082a("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);

    /* renamed from: t */
    public static final CipherSuite f975t = m1082a("TLS_KRB5_WITH_DES_CBC_SHA", 30);

    /* renamed from: u */
    public static final CipherSuite f976u = m1082a("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);

    /* renamed from: v */
    public static final CipherSuite f977v = m1082a("TLS_KRB5_WITH_RC4_128_SHA", 32);

    /* renamed from: w */
    public static final CipherSuite f978w = m1082a("TLS_KRB5_WITH_DES_CBC_MD5", 34);

    /* renamed from: x */
    public static final CipherSuite f979x = m1082a("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);

    /* renamed from: y */
    public static final CipherSuite f980y = m1082a("TLS_KRB5_WITH_RC4_128_MD5", 36);

    /* renamed from: z */
    public static final CipherSuite f981z = m1082a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);

    /* renamed from: bi */
    final String f982bi;

    /* renamed from: a */
    public static CipherSuite m1081a(String str) {
        CipherSuite hVar = (CipherSuite) f957bj.get(str);
        if (hVar != null) {
            return hVar;
        }
        CipherSuite hVar2 = new CipherSuite(str);
        CipherSuite putIfAbsent = f957bj.putIfAbsent(str, hVar2);
        if (putIfAbsent == null) {
            return hVar2;
        }
        return putIfAbsent;
    }

    private CipherSuite(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.f982bi = str;
    }

    /* renamed from: a */
    private static CipherSuite m1082a(String str, int i) {
        return m1081a(str);
    }

    /* renamed from: a */
    public String mo8894a() {
        return this.f982bi;
    }

    public String toString() {
        return this.f982bi;
    }
}
