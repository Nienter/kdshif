package p005b.p006a.p007a.p008a.p009a.p011b;

import android.os.SystemClock;
import android.util.Log;

/* renamed from: b.a.a.a.a.b.u */
public class TimingMetric {

    /* renamed from: a */
    private final String f171a;

    /* renamed from: b */
    private final String f172b;

    /* renamed from: c */
    private final boolean f173c;

    /* renamed from: d */
    private long f174d;

    /* renamed from: e */
    private long f175e;

    public TimingMetric(String str, String str2) {
        this.f171a = str;
        this.f172b = str2;
        this.f173c = !Log.isLoggable(str2, 2);
    }

    /* renamed from: a */
    public synchronized void mo8320a() {
        if (!this.f173c) {
            this.f174d = SystemClock.elapsedRealtime();
            this.f175e = 0;
        }
    }

    /* renamed from: b */
    public synchronized void mo8321b() {
        if (!this.f173c) {
            if (this.f175e == 0) {
                this.f175e = SystemClock.elapsedRealtime() - this.f174d;
                m230c();
            }
        }
    }

    /* renamed from: c */
    private void m230c() {
        Log.v(this.f172b, this.f171a + ": " + this.f175e + "ms");
    }
}
