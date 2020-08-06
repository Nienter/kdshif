package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.bs */
public class TField {

    /* renamed from: a */
    public final String f1475a;

    /* renamed from: b */
    public final byte f1476b;

    /* renamed from: c */
    public final short f1477c;

    public TField() {
        this("", (byte) 0, 0);
    }

    public TField(String str, byte b, short s) {
        this.f1475a = str;
        this.f1476b = b;
        this.f1477c = s;
    }

    public String toString() {
        return "<TField name:'" + this.f1475a + "' type:" + this.f1476b + " field-id:" + this.f1477c + ">";
    }
}
