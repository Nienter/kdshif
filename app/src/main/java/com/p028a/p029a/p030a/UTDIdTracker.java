package com.p028a.p029a.p030a;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.a.a.a.m */
public class UTDIdTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final Pattern f1621a = Pattern.compile("UTDID\">([^<]+)");

    /* renamed from: b */
    private Context f1622b;

    public UTDIdTracker(Context context) {
        super("utdid");
        this.f1622b = context;
    }

    /* renamed from: a */
    public String mo9458a() {
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke(null, new Object[]{this.f1622b});
        } catch (Exception e) {
            return m2324b();
        }
    }

    /* renamed from: b */
    private String m2324b() {
        FileInputStream fileInputStream;
        File c = m2326c();
        if (c == null || !c.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(c);
            String b = m2325b(HelperUtils.m1824a((InputStream) fileInputStream));
            HelperUtils.m1832c(fileInputStream);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            HelperUtils.m1832c(fileInputStream);
            throw th;
        }
    }

    /* renamed from: b */
    private String m2325b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = f1621a.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /* renamed from: c */
    private File m2326c() {
        if (!DeviceConfig.m1791a(this.f1622b, "android.permission.WRITE_EXTERNAL_STORAGE") || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
        } catch (Exception e) {
            return null;
        }
    }
}
