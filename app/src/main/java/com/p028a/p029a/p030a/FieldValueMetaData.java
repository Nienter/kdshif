package com.p028a.p029a.p030a;

import java.io.Serializable;

/* renamed from: com.a.a.a.bl */
public class FieldValueMetaData implements Serializable {

    /* renamed from: a */
    private final boolean f1438a;

    /* renamed from: b */
    public final byte f1439b;

    /* renamed from: c */
    private final String f1440c;

    /* renamed from: d */
    private final boolean f1441d;

    public FieldValueMetaData(byte b, boolean z) {
        this.f1439b = b;
        this.f1438a = false;
        this.f1440c = null;
        this.f1441d = z;
    }

    public FieldValueMetaData(byte b) {
        this(b, false);
    }

    public FieldValueMetaData(byte b, String str) {
        this.f1439b = b;
        this.f1438a = true;
        this.f1440c = str;
        this.f1441d = false;
    }

    /* renamed from: a */
    public boolean mo9409a() {
        return this.f1438a;
    }

    /* renamed from: b */
    public String mo9410b() {
        return this.f1440c;
    }

    /* renamed from: c */
    public boolean mo9411c() {
        return this.f1439b == 12;
    }

    /* renamed from: d */
    public boolean mo9412d() {
        return this.f1439b == 15 || this.f1439b == 13 || this.f1439b == 14;
    }

    /* renamed from: e */
    public boolean mo9413e() {
        return this.f1441d;
    }
}
