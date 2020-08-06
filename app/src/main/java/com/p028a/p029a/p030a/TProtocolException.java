package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.bw */
public class TProtocolException extends TException {

    /* renamed from: a */
    public static final int f1484a = 0;

    /* renamed from: b */
    public static final int f1485b = 1;

    /* renamed from: c */
    public static final int f1486c = 2;

    /* renamed from: d */
    public static final int f1487d = 3;

    /* renamed from: e */
    public static final int f1488e = 4;

    /* renamed from: f */
    public static final int f1489f = 5;

    /* renamed from: g */
    protected int f1490g = 0;

    public TProtocolException() {
    }

    public TProtocolException(int i) {
        this.f1490g = i;
    }

    public TProtocolException(int i, String str) {
        super(str);
        this.f1490g = i;
    }

    public TProtocolException(String str) {
        super(str);
    }

    public TProtocolException(int i, Throwable th) {
        super(th);
        this.f1490g = i;
    }

    public TProtocolException(Throwable th) {
        super(th);
    }

    public TProtocolException(String str, Throwable th) {
        super(str, th);
    }

    public TProtocolException(int i, String str, Throwable th) {
        super(str, th);
        this.f1490g = i;
    }

    /* renamed from: a */
    public int mo9457a() {
        return this.f1490g;
    }
}
