package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.bd */
public class ShortStack {

    /* renamed from: a */
    private short[] f1426a;

    /* renamed from: b */
    private int f1427b = -1;

    public ShortStack(int i) {
        this.f1426a = new short[i];
    }

    /* renamed from: a */
    public short mo9402a() {
        short[] sArr = this.f1426a;
        int i = this.f1427b;
        this.f1427b = i - 1;
        return sArr[i];
    }

    /* renamed from: a */
    public void mo9403a(short s) {
        if (this.f1426a.length == this.f1427b + 1) {
            m1914c();
        }
        short[] sArr = this.f1426a;
        int i = this.f1427b + 1;
        this.f1427b = i;
        sArr[i] = s;
    }

    /* renamed from: c */
    private void m1914c() {
        short[] sArr = new short[(this.f1426a.length * 2)];
        System.arraycopy(this.f1426a, 0, sArr, 0, this.f1426a.length);
        this.f1426a = sArr;
    }

    /* renamed from: b */
    public void mo9404b() {
        this.f1427b = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.f1426a.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.f1427b) {
                sb.append(">>");
            }
            sb.append(this.f1426a[i]);
            if (i == this.f1427b) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }
}
