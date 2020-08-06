package p005b.p006a.p007a.p008a.p009a.p011b;

import android.os.Process;

/* renamed from: b.a.a.a.a.b.h */
public abstract class BackgroundPriorityRunnable implements Runnable {
    /* access modifiers changed from: protected */
    public abstract void onRun();

    public final void run() {
        Process.setThreadPriority(10);
        onRun();
    }
}
