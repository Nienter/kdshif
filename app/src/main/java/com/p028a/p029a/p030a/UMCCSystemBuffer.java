package com.p028a.p029a.p030a;

import java.io.Serializable;

/* renamed from: com.a.a.a.co */
public class UMCCSystemBuffer implements Serializable {

    /* renamed from: b */
    private String f1519b;

    /* renamed from: c */
    private long f1520c;

    /* renamed from: d */
    private long f1521d;

    /* renamed from: e */
    private String f1522e;

    private UMCCSystemBuffer() {
        this.f1519b = null;
        this.f1520c = 0;
        this.f1521d = 0;
        this.f1522e = null;
    }

    public UMCCSystemBuffer(String str, long j, long j2) {
        this(str, j, j2, null);
    }

    public UMCCSystemBuffer(String str, long j, long j2, String str2) {
        this.f1519b = null;
        this.f1520c = 0;
        this.f1521d = 0;
        this.f1522e = null;
        this.f1519b = str;
        this.f1520c = j;
        this.f1521d = j2;
        this.f1522e = str2;
    }

    /* renamed from: a */
    public UMCCSystemBuffer mo9506a() {
        this.f1521d++;
        return this;
    }

    /* renamed from: b */
    public String mo9509b() {
        return this.f1522e;
    }

    /* renamed from: a */
    public void mo9508a(String str) {
        this.f1522e = str;
    }

    /* renamed from: c */
    public String mo9511c() {
        return this.f1519b;
    }

    /* renamed from: b */
    public void mo9510b(String str) {
        this.f1519b = str;
    }

    /* renamed from: d */
    public long mo9512d() {
        return this.f1520c;
    }

    /* renamed from: e */
    public long mo9513e() {
        return this.f1521d;
    }

    /* renamed from: a */
    public UMCCSystemBuffer mo9507a(UMCCSystemBuffer coVar) {
        this.f1521d = coVar.mo9513e() + this.f1521d;
        this.f1520c = coVar.mo9512d();
        return this;
    }
}
