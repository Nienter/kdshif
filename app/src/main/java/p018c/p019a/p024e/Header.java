package p018c.p019a.p024e;

import p018c.p019a.Util;
import p033d.ByteString;

/* renamed from: c.a.e.c */
public final class Header {

    /* renamed from: a */
    public static final ByteString f591a = ByteString.encodeUtf8(":");

    /* renamed from: b */
    public static final ByteString f592b = ByteString.encodeUtf8(":status");

    /* renamed from: c */
    public static final ByteString f593c = ByteString.encodeUtf8(":method");

    /* renamed from: d */
    public static final ByteString f594d = ByteString.encodeUtf8(":path");

    /* renamed from: e */
    public static final ByteString f595e = ByteString.encodeUtf8(":scheme");

    /* renamed from: f */
    public static final ByteString f596f = ByteString.encodeUtf8(":authority");

    /* renamed from: g */
    public final ByteString f597g;

    /* renamed from: h */
    public final ByteString f598h;

    /* renamed from: i */
    final int f599i;

    public Header(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public Header(ByteString fVar, String str) {
        this(fVar, ByteString.encodeUtf8(str));
    }

    public Header(ByteString fVar, ByteString fVar2) {
        this.f597g = fVar;
        this.f598h = fVar2;
        this.f599i = fVar.size() + 32 + fVar2.size();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Header)) {
            return false;
        }
        Header cVar = (Header) obj;
        if (!this.f597g.equals(cVar.f597g) || !this.f598h.equals(cVar.f598h)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.f597g.hashCode() + 527) * 31) + this.f598h.hashCode();
    }

    public String toString() {
        return Util.m646a("%s: %s", this.f597g.utf8(), this.f598h.utf8());
    }
}
