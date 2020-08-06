package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.SharedPreferences;
import com.p028a.p029a.AnalyticsConfig;
import java.io.File;
import org.json.JSONObject;

/* renamed from: com.a.a.a.b */
public class Envelope {

    /* renamed from: a */
    private final byte[] f1401a = {0, 0, 0, 0, 0, 0, 0, 0};

    /* renamed from: b */
    private final int f1402b = 1;

    /* renamed from: c */
    private final int f1403c = 0;

    /* renamed from: d */
    private String f1404d = "1.0";

    /* renamed from: e */
    private String f1405e = null;

    /* renamed from: f */
    private byte[] f1406f = null;

    /* renamed from: g */
    private byte[] f1407g = null;

    /* renamed from: h */
    private byte[] f1408h = null;

    /* renamed from: i */
    private int f1409i = 0;

    /* renamed from: j */
    private int f1410j = 0;

    /* renamed from: k */
    private int f1411k = 0;

    /* renamed from: l */
    private byte[] f1412l = null;

    /* renamed from: m */
    private byte[] f1413m = null;

    /* renamed from: n */
    private boolean f1414n = false;

    private Envelope(byte[] bArr, String str, byte[] bArr2) {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.f1405e = str;
        this.f1411k = bArr.length;
        this.f1412l = DeflaterHelper.m1782a(bArr);
        this.f1410j = (int) (System.currentTimeMillis() / 1000);
        this.f1413m = bArr2;
    }

    /* renamed from: a */
    public static String m1875a(Context context) {
        SharedPreferences a = PreferenceWrapper.m1333a(context);
        if (a == null) {
            return null;
        }
        return a.getString("signature", null);
    }

    /* renamed from: a */
    public void mo9380a(String str) {
        this.f1406f = DataHelper.m1775a(str);
    }

    /* renamed from: a */
    public String mo9378a() {
        return DataHelper.m1773a(this.f1406f);
    }

    /* renamed from: a */
    public void mo9379a(int i) {
        this.f1409i = i;
    }

    /* renamed from: a */
    public void mo9381a(boolean z) {
        this.f1414n = z;
    }

    /* renamed from: a */
    public static Envelope m1874a(Context context, String str, byte[] bArr) {
        try {
            String n = DeviceConfig.m1810n(context);
            String c = DeviceConfig.m1796c(context);
            SharedPreferences a = PreferenceWrapper.m1333a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            Envelope bVar = new Envelope(bArr, str, (c + n).getBytes());
            bVar.mo9380a(string);
            bVar.mo9379a(i);
            bVar.mo9382b();
            a.edit().putInt("serial", i + 1).putString("signature", bVar.mo9378a()).commit();
            bVar.mo9383b(context);
            return bVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static Envelope m1877b(Context context, String str, byte[] bArr) {
        try {
            String n = DeviceConfig.m1810n(context);
            String c = DeviceConfig.m1796c(context);
            SharedPreferences a = PreferenceWrapper.m1333a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            Envelope bVar = new Envelope(bArr, str, (c + n).getBytes());
            bVar.mo9381a(true);
            bVar.mo9380a(string);
            bVar.mo9379a(i);
            bVar.mo9382b();
            a.edit().putInt("serial", i + 1).putString("signature", bVar.mo9378a()).commit();
            bVar.mo9383b(context);
            return bVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public void mo9382b() {
        if (this.f1406f == null) {
            this.f1406f = m1878d();
        }
        if (this.f1414n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f1406f, 1, bArr, 0, 16);
                this.f1412l = DataHelper.m1776a(this.f1412l, bArr);
            } catch (Exception e) {
            }
        }
        this.f1407g = m1876a(this.f1406f, this.f1410j);
        this.f1408h = m1879e();
    }

    /* renamed from: a */
    private byte[] m1876a(byte[] bArr, int i) {
        byte[] b = DataHelper.m1778b(this.f1413m);
        byte[] b2 = DataHelper.m1778b(this.f1412l);
        int length = b.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2 * 2] = b2[i2];
            bArr2[(i2 * 2) + 1] = b[i2];
        }
        for (int i3 = 0; i3 < 2; i3++) {
            bArr2[i3] = bArr[i3];
            bArr2[(bArr2.length - i3) - 1] = bArr[(bArr.length - i3) - 1];
        }
        byte[] bArr3 = {(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        for (int i4 = 0; i4 < bArr2.length; i4++) {
            bArr2[i4] = (byte) (bArr2[i4] ^ bArr3[i4 % 4]);
        }
        return bArr2;
    }

    /* renamed from: d */
    private byte[] m1878d() {
        return m1876a(this.f1401a, (int) (System.currentTimeMillis() / 1000));
    }

    /* renamed from: e */
    private byte[] m1879e() {
        return DataHelper.m1778b((DataHelper.m1773a(this.f1406f) + this.f1409i + this.f1410j + this.f1411k + DataHelper.m1773a(this.f1407g)).getBytes());
    }

    /* renamed from: c */
    public byte[] mo9384c() {
        UMEnvelope apVar = new UMEnvelope();
        apVar.mo9311a(this.f1404d);
        apVar.mo9315b(this.f1405e);
        apVar.mo9318c(DataHelper.m1773a(this.f1406f));
        apVar.mo9310a(this.f1409i);
        apVar.mo9317c(this.f1410j);
        apVar.mo9321d(this.f1411k);
        apVar.mo9313a(this.f1412l);
        apVar.mo9325e(this.f1414n ? 1 : 0);
        apVar.mo9322d(DataHelper.m1773a(this.f1407g));
        apVar.mo9326e(DataHelper.m1773a(this.f1408h));
        try {
            return new TSerializer().mo9408a(apVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public void mo9383b(Context context) {
        String str = this.f1405e;
        String e = ImprintHandler.m2284a(context).mo9583b().mo9600e(null);
        String a = DataHelper.m1773a(this.f1406f);
        byte[] bArr = new byte[16];
        System.arraycopy(this.f1406f, 2, bArr, 0, 16);
        String a2 = DataHelper.m1773a(DataHelper.m1778b(bArr));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", str);
            if (e != null) {
                jSONObject.put("umid", e);
            }
            jSONObject.put("signature", a);
            jSONObject.put("checksum", a2);
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            HelperUtils.m1827a(new File(file, "exchangeIdentity.json"), jSONObject.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("appkey", str);
            jSONObject2.put("channel", AnalyticsConfig.m1313b(context));
            if (e != null) {
                jSONObject2.put("umid", HelperUtils.m1830b(e));
            }
            HelperUtils.m1827a(new File(context.getFilesDir(), "exid.dat"), jSONObject2.toString());
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public String toString() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("version : %s\n", new Object[]{this.f1404d}));
        sb.append(String.format("address : %s\n", new Object[]{this.f1405e}));
        sb.append(String.format("signature : %s\n", new Object[]{DataHelper.m1773a(this.f1406f)}));
        sb.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.f1409i)}));
        sb.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.f1410j)}));
        sb.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.f1411k)}));
        sb.append(String.format("guid : %s\n", new Object[]{DataHelper.m1773a(this.f1407g)}));
        sb.append(String.format("checksum : %s ", new Object[]{DataHelper.m1773a(this.f1408h)}));
        Object[] objArr = new Object[1];
        if (!this.f1414n) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        sb.append(String.format("codex : %d", objArr));
        return sb.toString();
    }
}
