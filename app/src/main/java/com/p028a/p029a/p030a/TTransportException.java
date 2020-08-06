package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.cj */
public class TTransportException extends TException {

    /* renamed from: a */
    public static final int f1501a = 0;

    /* renamed from: b */
    public static final int f1502b = 1;

    /* renamed from: c */
    public static final int f1503c = 2;

    /* renamed from: d */
    public static final int f1504d = 3;

    /* renamed from: e */
    public static final int f1505e = 4;

    /* renamed from: f */
    protected int f1506f = 0;

    public TTransportException() {
    }

    public TTransportException(int i) {
        this.f1506f = i;
    }

    public TTransportException(int i, String str) {
        super(str);
        this.f1506f = i;
    }

    public TTransportException(String str) {
        super(str);
    }

    public TTransportException(int i, Throwable th) {
        super(th);
        this.f1506f = i;
    }

    public TTransportException(Throwable th) {
        super(th);
    }

    public TTransportException(String str, Throwable th) {
        super(str, th);
    }

    public TTransportException(int i, String str, Throwable th) {
        super(str, th);
        this.f1506f = i;
    }

    /* renamed from: a */
    public int mo9472a() {
        return this.f1506f;
    }
}
