package p018c.p019a.p024e;

import java.io.IOException;
import p018c.p019a.Util;
import p033d.ByteString;

/* renamed from: c.a.e.e */
public final class Http2 {

    /* renamed from: a */
    static final ByteString f620a = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* renamed from: b */
    static final String[] f621b = new String[64];

    /* renamed from: c */
    static final String[] f622c = new String[256];

    /* renamed from: d */
    private static final String[] f623d = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    static {
        for (int i = 0; i < f622c.length; i++) {
            f622c[i] = Util.m646a("%8s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        f621b[0] = "";
        f621b[1] = "END_STREAM";
        int[] iArr = {1};
        f621b[8] = "PADDED";
        for (int i2 : iArr) {
            f621b[i2 | 8] = f621b[iArr[r0]] + "|PADDED";
        }
        f621b[4] = "END_HEADERS";
        f621b[32] = "PRIORITY";
        f621b[36] = "END_HEADERS|PRIORITY";
        for (int i3 : new int[]{4, 32, 36}) {
            for (int i4 : iArr) {
                f621b[i4 | i3] = f621b[i4] + '|' + f621b[i3];
                f621b[i4 | i3 | 8] = f621b[i4] + '|' + f621b[i3] + "|PADDED";
            }
        }
        for (int i5 = 0; i5 < f621b.length; i5++) {
            if (f621b[i5] == null) {
                f621b[i5] = f622c[i5];
            }
        }
    }

    private Http2() {
    }

    /* renamed from: a */
    static IllegalArgumentException m770a(String str, Object... objArr) {
        throw new IllegalArgumentException(Util.m646a(str, objArr));
    }

    /* renamed from: b */
    static IOException m773b(String str, Object... objArr) {
        throw new IOException(Util.m646a(str, objArr));
    }

    /* renamed from: a */
    static String m772a(boolean z, int i, int i2, byte b, byte b2) {
        String a = b < f623d.length ? f623d[b] : Util.m646a("0x%02x", Byte.valueOf(b));
        String a2 = m771a(b, b2);
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = a;
        objArr[4] = a2;
        return Util.m646a("%s 0x%08x %5d %-13s %s", objArr);
    }

    /* renamed from: a */
    static String m771a(byte b, byte b2) {
        if (b2 == 0) {
            return "";
        }
        switch (b) {
            case 2:
            case 3:
            case 7:
            case 8:
                return f622c[b2];
            case 4:
            case 6:
                return b2 == 1 ? "ACK" : f622c[b2];
            default:
                String str = b2 < f621b.length ? f621b[b2] : f622c[b2];
                if (b == 5 && (b2 & 4) != 0) {
                    return str.replace("HEADERS", "PUSH_PROMISE");
                }
                if (b != 0 || (b2 & 32) == 0) {
                    return str;
                }
                return str.replace("PRIORITY", "COMPRESSED");
        }
    }
}
