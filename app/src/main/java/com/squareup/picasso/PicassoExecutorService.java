package com.squareup.picasso;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Utils;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.squareup.picasso.x */
class PicassoExecutorService extends ThreadPoolExecutor {

    /* renamed from: com.squareup.picasso.x$a */
    /* compiled from: PicassoExecutorService */
    private static final class C1644a extends FutureTask<BitmapHunter> implements Comparable<C1644a> {

        /* renamed from: a */
        private final BitmapHunter f2591a;

        public C1644a(BitmapHunter cVar) {
            super(cVar, null);
            this.f2591a = cVar;
        }

        /* renamed from: a */
        public int compareTo(C1644a aVar) {
            Picasso.C1641e n = this.f2591a.mo17518n();
            Picasso.C1641e n2 = aVar.f2591a.mo17518n();
            return n == n2 ? this.f2591a.f2474a - aVar.f2591a.f2474a : n2.ordinal() - n.ordinal();
        }
    }

    PicassoExecutorService() {
        super(3, 3, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new Utils.C1617b());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17594a(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            m3418a(3);
            return;
        }
        switch (networkInfo.getType()) {
            case 0:
                switch (networkInfo.getSubtype()) {
                    case 1:
                    case 2:
                        m3418a(1);
                        return;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 12:
                        m3418a(2);
                        return;
                    case 13:
                    case 14:
                    case 15:
                        m3418a(3);
                        return;
                    default:
                        m3418a(3);
                        return;
                }
            case 1:
            case 6:
            case 9:
                m3418a(4);
                return;
            default:
                m3418a(3);
                return;
        }
    }

    /* renamed from: a */
    private void m3418a(int i) {
        setCorePoolSize(i);
        setMaximumPoolSize(i);
    }

    public Future<?> submit(Runnable runnable) {
        C1644a aVar = new C1644a((BitmapHunter) runnable);
        execute(aVar);
        return aVar;
    }
}
