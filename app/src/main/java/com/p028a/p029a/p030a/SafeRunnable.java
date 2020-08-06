package com.p028a.p029a.p030a;

/* renamed from: com.a.a.a.ba */
public abstract class SafeRunnable implements Runnable {
    /* renamed from: a */
    public abstract void mo9386a();

    public void run() {
        try {
            mo9386a();
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }
}
