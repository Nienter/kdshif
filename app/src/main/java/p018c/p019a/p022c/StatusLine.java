package p018c.p019a.p022c;

import java.net.ProtocolException;
import p018c.Protocol;

/* renamed from: c.a.c.k */
public final class StatusLine {

    /* renamed from: a */
    public final Protocol f564a;

    /* renamed from: b */
    public final int f565b;

    /* renamed from: c */
    public final String f566c;

    public StatusLine(Protocol vVar, int i, String str) {
        this.f564a = vVar;
        this.f565b = i;
        this.f566c = str;
    }

    /* renamed from: a */
    public static StatusLine m716a(String str) {
        Protocol vVar;
        String str2;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                vVar = Protocol.HTTP_1_0;
            } else if (charAt == 1) {
                vVar = Protocol.HTTP_1_1;
            } else {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            vVar = Protocol.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        if (str.length() < i + 3) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            int parseInt = Integer.parseInt(str.substring(i, i + 3));
            if (str.length() <= i + 3) {
                str2 = "";
            } else if (str.charAt(i + 3) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            } else {
                str2 = str.substring(i + 4);
            }
            return new StatusLine(vVar, parseInt, str2);
        } catch (NumberFormatException e) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f564a == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ').append(this.f565b);
        if (this.f566c != null) {
            sb.append(' ').append(this.f566c);
        }
        return sb.toString();
    }
}
