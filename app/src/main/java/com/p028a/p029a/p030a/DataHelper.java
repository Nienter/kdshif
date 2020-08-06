package com.p028a.p029a.p030a;

import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.p028a.p029a.AnalyticsConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.a.a.a.ar */
public class DataHelper {

    /* renamed from: a */
    private static final byte[] f1372a = {10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 8, 12, 13, 91};

    /* renamed from: a */
    public static byte[] m1775a(String str) {
        byte[] bArr = null;
        if (str != null) {
            int length = str.length();
            if (length % 2 == 0) {
                bArr = new byte[(length / 2)];
                for (int i = 0; i < length; i += 2) {
                    bArr[i / 2] = (byte) Integer.valueOf(str.substring(i, i + 2), 16).intValue();
                }
            }
        }
        return bArr;
    }

    /* renamed from: a */
    public static boolean m1774a(Context context, byte[] bArr) {
        long length = (long) bArr.length;
        if (length <= AnalyticsConstants.f1693f) {
            return false;
        }
        StoreHelper.m1888a(context).mo9393e();
        UMCCAggregatedManager.m2160a(context).mo9520a(length, System.currentTimeMillis(), "__data_size_of");
        return true;
    }

    /* renamed from: a */
    public static String m1773a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte valueOf : bArr) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(valueOf)}));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    /* renamed from: b */
    public static byte[] m1778b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m1776a(byte[] bArr, byte[] bArr2) {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f1372a));
        return instance.doFinal(bArr);
    }

    /* renamed from: b */
    public static byte[] m1779b(byte[] bArr, byte[] bArr2) {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f1372a));
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    public static int m1771a(int i, String str) {
        int i2 = 0;
        if (((double) new Random().nextFloat()) < 0.001d) {
            if (str == null) {
                MLog.m1839a("--->", "null signature..");
            }
            try {
                i2 = Integer.parseInt(str.substring(9, 11), 16);
            } catch (Exception e) {
            }
            return (i2 | 128) * 1000;
        }
        int nextInt = new Random().nextInt(i);
        if (nextInt > 255000 || nextInt < 128000) {
            return nextInt;
        }
        return 127000;
    }

    /* renamed from: a */
    public static String m1772a(Throwable th) {
        if (th == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }
            String obj = stringWriter.toString();
            printWriter.close();
            stringWriter.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static String m1777b(String str) {
        return "http://" + str + ".umeng.com/app_logs";
    }

    /* renamed from: c */
    public static String m1780c(String str) {
        byte[] bytes = str.getBytes();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(bytes);
            return m1781c(instance.digest());
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: c */
    static String m1781c(byte[] bArr) {
        String str = "";
        int i = 0;
        while (i < bArr.length) {
            if (Integer.toHexString(bArr[i] & 255).length() == 1) {
                str = str + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            i++;
            str = str + r2;
        }
        return str;
    }
}
