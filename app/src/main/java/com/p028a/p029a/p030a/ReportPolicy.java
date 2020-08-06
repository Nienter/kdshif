package com.p028a.p029a.p030a;

import android.content.Context;

/* renamed from: com.a.a.a.az */
public class ReportPolicy {

    /* renamed from: com.a.a.a.az$a */
    /* compiled from: ReportPolicy */
    public static class C0595a extends C0602h {

        /* renamed from: a */
        private final long f1386a = 15000;

        /* renamed from: b */
        private StatTracer f1387b;

        public C0595a(StatTracer aeVar) {
            this.f1387b = aeVar;
        }

        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            if (System.currentTimeMillis() - this.f1387b.f1188c >= 15000) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.a.a.a.az$b */
    /* compiled from: ReportPolicy */
    public static class C0596b extends C0602h {

        /* renamed from: a */
        private Defcon f1388a;

        /* renamed from: b */
        private StatTracer f1389b;

        public C0596b(StatTracer aeVar, Defcon ahVar) {
            this.f1389b = aeVar;
            this.f1388a = ahVar;
        }

        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f1389b.f1188c >= this.f1388a.mo9114b()) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public boolean mo9376a() {
            return this.f1388a.mo9115c();
        }
    }

    /* renamed from: com.a.a.a.az$c */
    /* compiled from: ReportPolicy */
    public static class C0597c extends C0602h {

        /* renamed from: a */
        private long f1390a;

        /* renamed from: b */
        private long f1391b = 0;

        public C0597c(int i) {
            this.f1390a = (long) i;
            this.f1391b = System.currentTimeMillis();
        }

        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            if (System.currentTimeMillis() - this.f1391b >= this.f1390a) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public boolean mo9376a() {
            return System.currentTimeMillis() - this.f1391b < this.f1390a;
        }
    }

    /* renamed from: com.a.a.a.az$d */
    /* compiled from: ReportPolicy */
    public static class C0598d extends C0602h {
        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            return z;
        }
    }

    /* renamed from: com.a.a.a.az$e */
    /* compiled from: ReportPolicy */
    public static class C0599e extends C0602h {

        /* renamed from: a */
        private static long f1392a = 90000;

        /* renamed from: b */
        private static long f1393b = 86400000;

        /* renamed from: c */
        private long f1394c;

        /* renamed from: d */
        private StatTracer f1395d;

        public C0599e(StatTracer aeVar, long j) {
            this.f1395d = aeVar;
            mo9377a(j);
        }

        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            if (System.currentTimeMillis() - this.f1395d.f1188c >= this.f1394c) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public void mo9377a(long j) {
            if (j < f1392a || j > f1393b) {
                this.f1394c = f1392a;
            } else {
                this.f1394c = j;
            }
        }
    }

    /* renamed from: com.a.a.a.az$f */
    /* compiled from: ReportPolicy */
    public static class C0600f extends C0602h {

        /* renamed from: a */
        private long f1396a = 86400000;

        /* renamed from: b */
        private StatTracer f1397b;

        public C0600f(StatTracer aeVar) {
            this.f1397b = aeVar;
        }

        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            if (System.currentTimeMillis() - this.f1397b.f1188c >= this.f1396a) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.a.a.a.az$g */
    /* compiled from: ReportPolicy */
    public static class C0601g extends C0602h {
        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            return true;
        }
    }

    /* renamed from: com.a.a.a.az$h */
    /* compiled from: ReportPolicy */
    public static class C0602h {
        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            return true;
        }

        /* renamed from: a */
        public boolean mo9376a() {
            return true;
        }
    }

    /* renamed from: com.a.a.a.az$i */
    /* compiled from: ReportPolicy */
    public static class C0603i extends C0602h {

        /* renamed from: a */
        private Context f1398a = null;

        public C0603i(Context context) {
            this.f1398a = context;
        }

        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            return DeviceConfig.m1805i(this.f1398a);
        }
    }

    /* renamed from: com.a.a.a.az$j */
    /* compiled from: ReportPolicy */
    public static class C0604j extends C0602h {

        /* renamed from: a */
        private final long f1399a = 10800000;

        /* renamed from: b */
        private StatTracer f1400b;

        public C0604j(StatTracer aeVar) {
            this.f1400b = aeVar;
        }

        /* renamed from: a */
        public boolean mo9375a(boolean z) {
            if (System.currentTimeMillis() - this.f1400b.f1188c >= 10800000) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1859a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            default:
                return false;
        }
    }
}
