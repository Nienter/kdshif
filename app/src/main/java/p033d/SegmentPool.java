package p033d;

import android.support.p001v4.media.session.PlaybackStateCompat;

/* renamed from: d.p */
final class SegmentPool {

    /* renamed from: a */
    static Segment f2691a;

    /* renamed from: b */
    static long f2692b;

    private SegmentPool() {
    }

    /* renamed from: a */
    static Segment m3651a() {
        synchronized (SegmentPool.class) {
            if (f2691a == null) {
                return new Segment();
            }
            Segment oVar = f2691a;
            f2691a = oVar.f2689f;
            oVar.f2689f = null;
            f2692b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            return oVar;
        }
    }

    /* renamed from: a */
    static void m3652a(Segment oVar) {
        if (oVar.f2689f != null || oVar.f2690g != null) {
            throw new IllegalArgumentException();
        } else if (!oVar.f2687d) {
            synchronized (SegmentPool.class) {
                if (f2692b + PlaybackStateCompat.ACTION_PLAY_FROM_URI <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    f2692b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    oVar.f2689f = f2691a;
                    oVar.f2686c = 0;
                    oVar.f2685b = 0;
                    f2691a = oVar;
                }
            }
        }
    }
}
