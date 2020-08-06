package com.p028a.p029a.p030a;

import android.support.p004v7.widget.ActivityChooserView;

/* renamed from: com.a.a.a.by */
public class TProtocolUtil {

    /* renamed from: a */
    private static int f1491a = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;

    /* renamed from: a */
    public static void m2077a(TProtocol bvVar, byte b) {
        m2078a(bvVar, b, f1491a);
    }

    /* renamed from: a */
    public static void m2078a(TProtocol bvVar, byte b, int i) {
        int i2 = 0;
        if (i <= 0) {
            throw new TException("Maximum skip depth exceeded");
        }
        switch (b) {
            case 2:
                bvVar.mo9442p();
                return;
            case 3:
                bvVar.mo9443q();
                return;
            case 4:
                bvVar.mo9447u();
                return;
            case 6:
                bvVar.mo9444r();
                return;
            case 8:
                bvVar.mo9445s();
                return;
            case 10:
                bvVar.mo9446t();
                return;
            case 11:
                bvVar.mo9449w();
                return;
            case 12:
                bvVar.mo9432f();
                while (true) {
                    TField h = bvVar.mo9434h();
                    if (h.f1476b == 0) {
                        bvVar.mo9433g();
                        return;
                    } else {
                        m2078a(bvVar, h.f1476b, i - 1);
                        bvVar.mo9435i();
                    }
                }
            case 13:
                TMap j = bvVar.mo9436j();
                while (i2 < j.f1482c) {
                    m2078a(bvVar, j.f1480a, i - 1);
                    m2078a(bvVar, j.f1481b, i - 1);
                    i2++;
                }
                bvVar.mo9437k();
                return;
            case 14:
                TSet n = bvVar.mo9440n();
                while (i2 < n.f1493b) {
                    m2078a(bvVar, n.f1492a, i - 1);
                    i2++;
                }
                bvVar.mo9441o();
                return;
            case 15:
                TList l = bvVar.mo9438l();
                while (i2 < l.f1479b) {
                    m2078a(bvVar, l.f1478a, i - 1);
                    i2++;
                }
                bvVar.mo9439m();
                return;
            default:
                return;
        }
    }
}
