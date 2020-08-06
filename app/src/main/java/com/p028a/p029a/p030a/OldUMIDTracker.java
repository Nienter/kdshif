package com.p028a.p029a.p030a;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;

/* renamed from: com.a.a.a.j */
public class OldUMIDTracker extends AbstractIdTracker {

    /* renamed from: a */
    private Context f1617a;

    /* renamed from: b */
    private String f1618b = null;

    /* renamed from: c */
    private String f1619c = null;

    public OldUMIDTracker(Context context) {
        super("oldumid");
        this.f1617a = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        return this.f1618b;
    }

    /* renamed from: b */
    public boolean mo9601b() {
        return mo9602c();
    }

    /* renamed from: c */
    public boolean mo9602c() {
        this.f1619c = ImprintHandler.m2284a(this.f1617a).mo9583b().mo9600e(null);
        if (!TextUtils.isEmpty(this.f1619c)) {
            this.f1619c = DataHelper.m1780c(this.f1619c);
            String a = HelperUtils.m1823a(new File("/sdcard/Android/data/.um/sysid.dat"));
            String a2 = HelperUtils.m1823a(new File("/sdcard/Android/obj/.um/sysid.dat"));
            String a3 = HelperUtils.m1823a(new File("/data/local/tmp/.um/sysid.dat"));
            if (TextUtils.isEmpty(a)) {
                m2317l();
            } else if (!this.f1619c.equals(a)) {
                this.f1618b = a;
                return true;
            }
            if (TextUtils.isEmpty(a2)) {
                m2316k();
            } else if (!this.f1619c.equals(a2)) {
                this.f1618b = a2;
                return true;
            }
            if (TextUtils.isEmpty(a3)) {
                m2315j();
            } else if (!this.f1619c.equals(a3)) {
                this.f1618b = a3;
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public void mo9603d() {
        try {
            m2317l();
            m2316k();
            m2315j();
        } catch (Exception e) {
        }
    }

    /* renamed from: j */
    private void m2315j() {
        try {
            m2314b("/data/local/tmp/.um");
            HelperUtils.m1827a(new File("/data/local/tmp/.um/sysid.dat"), this.f1619c);
        } catch (Throwable th) {
        }
    }

    /* renamed from: k */
    private void m2316k() {
        try {
            m2314b("/sdcard/Android/obj/.um");
            HelperUtils.m1827a(new File("/sdcard/Android/obj/.um/sysid.dat"), this.f1619c);
        } catch (Throwable th) {
        }
    }

    /* renamed from: l */
    private void m2317l() {
        try {
            m2314b("/sdcard/Android/data/.um");
            HelperUtils.m1827a(new File("/sdcard/Android/data/.um/sysid.dat"), this.f1619c);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private void m2314b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
