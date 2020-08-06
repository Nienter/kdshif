package p018c.p019a.p024e;

import android.support.p001v4.internal.view.SupportMenu;
import java.util.Arrays;

/* renamed from: c.a.e.n */
/* compiled from: Settings */
public final class C0510n {

    /* renamed from: a */
    private int f757a;

    /* renamed from: b */
    private final int[] f758b = new int[10];

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8796a() {
        this.f757a = 0;
        Arrays.fill(this.f758b, 0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0510n mo8795a(int i, int i2) {
        if (i < this.f758b.length) {
            this.f757a = (1 << i) | this.f757a;
            this.f758b[i] = i2;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo8798a(int i) {
        if (((1 << i) & this.f757a) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo8800b(int i) {
        return this.f758b[i];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo8799b() {
        return Integer.bitCount(this.f757a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo8801c() {
        if ((2 & this.f757a) != 0) {
            return this.f758b[1];
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo8802c(int i) {
        return (16 & this.f757a) != 0 ? this.f758b[4] : i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8804d(int i) {
        return (32 & this.f757a) != 0 ? this.f758b[5] : i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8803d() {
        return (128 & this.f757a) != 0 ? this.f758b[7] : SupportMenu.USER_MASK;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo8797a(C0510n nVar) {
        for (int i = 0; i < 10; i++) {
            if (nVar.mo8798a(i)) {
                mo8795a(i, nVar.mo8800b(i));
            }
        }
    }
}
