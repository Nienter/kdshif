package p005b.p006a.p007a.p008a;

import android.util.Log;

/* renamed from: b.a.a.a.b */
public class DefaultLogger implements Logger {

    /* renamed from: a */
    private int f360a;

    public DefaultLogger(int i) {
        this.f360a = i;
    }

    public DefaultLogger() {
        this.f360a = 4;
    }

    /* renamed from: a */
    public boolean mo8508a(String str, int i) {
        return this.f360a <= i;
    }

    /* renamed from: a */
    public void mo8507a(String str, String str2, Throwable th) {
        if (mo8508a(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    /* renamed from: b */
    public void mo8510b(String str, String str2, Throwable th) {
        if (mo8508a(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    /* renamed from: c */
    public void mo8512c(String str, String str2, Throwable th) {
        if (mo8508a(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    /* renamed from: d */
    public void mo8514d(String str, String str2, Throwable th) {
        if (mo8508a(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    /* renamed from: e */
    public void mo8516e(String str, String str2, Throwable th) {
        if (mo8508a(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    /* renamed from: a */
    public void mo8506a(String str, String str2) {
        mo8507a(str, str2, (Throwable) null);
    }

    /* renamed from: b */
    public void mo8509b(String str, String str2) {
        mo8510b(str, str2, null);
    }

    /* renamed from: c */
    public void mo8511c(String str, String str2) {
        mo8512c(str, str2, null);
    }

    /* renamed from: d */
    public void mo8513d(String str, String str2) {
        mo8514d(str, str2, null);
    }

    /* renamed from: e */
    public void mo8515e(String str, String str2) {
        mo8516e(str, str2, null);
    }

    /* renamed from: a */
    public void mo8504a(int i, String str, String str2) {
        mo8505a(i, str, str2, false);
    }

    /* renamed from: a */
    public void mo8505a(int i, String str, String str2, boolean z) {
        if (z || mo8508a(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
