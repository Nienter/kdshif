package com.p028a.p029a.p030a;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.a.a.a.au */
public class HelperUtils {

    /* renamed from: a */
    public static final String f1375a = System.getProperty("line.separator");

    /* renamed from: a */
    public static String m1826a(String str, int i) {
        String str2;
        int i2;
        String str3 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                String substring = str.substring(0, str.length() < i ? str.length() : i);
                try {
                    int i3 = i;
                    String str4 = substring;
                    int length = substring.getBytes("UTF-8").length;
                    str3 = str4;
                    while (length > i) {
                        i3--;
                        if (i3 > str.length()) {
                            i2 = str.length();
                        } else {
                            i2 = i3;
                        }
                        String substring2 = str.substring(0, i2);
                        str3 = substring2;
                        length = substring2.getBytes("UTF-8").length;
                    }
                } catch (Exception e) {
                    Exception exc = e;
                    str3 = str2;
                    e = exc;
                    MLog.m1840a((Throwable) e);
                    return str3;
                }
            }
        } catch (Exception e2) {
            e = e2;
            MLog.m1840a((Throwable) e);
            return str3;
        }
        return str3;
    }

    /* renamed from: a */
    public static String m1825a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte valueOf : digest) {
                stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(valueOf)}));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    /* renamed from: b */
    public static String m1830b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            MLog.m1843b("helper", "getMD5 error", e);
            return "";
        }
    }

    /* renamed from: a */
    public static String m1824a(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 == read) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, read);
        }
    }

    /* renamed from: b */
    public static byte[] m1831b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* renamed from: a */
    public static void m1828a(File file, byte[] bArr) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
        } finally {
            m1829a((OutputStream) fileOutputStream);
        }
    }

    /* renamed from: a */
    public static void m1827a(File file, String str) {
        m1828a(file, str.getBytes());
    }

    /* renamed from: a */
    public static String m1823a(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2;
        try {
            if (!file.exists()) {
                m1832c(null);
                return null;
            }
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String str = new String(bArr);
                m1832c(fileInputStream);
                return str;
            } catch (Throwable th2) {
                th = th2;
                m1832c(fileInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            m1832c(fileInputStream);
            throw th;
        }
    }

    /* renamed from: c */
    public static void m1832c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public static void m1829a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
    }
}
