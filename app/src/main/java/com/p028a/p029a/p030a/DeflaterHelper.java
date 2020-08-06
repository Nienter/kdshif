package com.p028a.p029a.p030a;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

/* renamed from: com.a.a.a.as */
public class DeflaterHelper {

    /* renamed from: a */
    public static int f1373a;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0035  */
    /* renamed from: a */
    public static byte[] m1782a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        f1373a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int deflate = deflater.deflate(bArr2);
                    f1373a += deflate;
                    byteArrayOutputStream.write(bArr2, 0, deflate);
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            deflater.end();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayOutputStream = null;
            th = th4;
            if (byteArrayOutputStream != null) {
            }
            throw th;
        }
    }
}
