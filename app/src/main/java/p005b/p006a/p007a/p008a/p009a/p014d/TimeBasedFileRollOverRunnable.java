package p005b.p006a.p007a.p008a.p009a.p014d;

import android.content.Context;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;

/* renamed from: b.a.a.a.a.d.i */
public class TimeBasedFileRollOverRunnable implements Runnable {

    /* renamed from: a */
    private final Context f227a;

    /* renamed from: b */
    private final FileRollOverManager f228b;

    public TimeBasedFileRollOverRunnable(Context context, FileRollOverManager eVar) {
        this.f227a = context;
        this.f228b = eVar;
    }

    public void run() {
        try {
            CommonUtils.m137a(this.f227a, "Performing time based file roll over.");
            if (!this.f228b.rollFileOver()) {
                this.f228b.cancelTimeBasedFileRollOver();
            }
        } catch (Exception e) {
            CommonUtils.m138a(this.f227a, "Failed to roll over file", (Throwable) e);
        }
    }
}
