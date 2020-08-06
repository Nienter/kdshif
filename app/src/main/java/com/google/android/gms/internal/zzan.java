package com.google.android.gms.internal;

import android.support.p001v4.media.session.PlaybackStateCompat;
import com.google.android.gms.internal.zzaf;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzan {
    static boolean zzlI = false;
    /* access modifiers changed from: private */
    public static MessageDigest zzlJ = null;
    private static final Object zzlK = new Object();
    private static final Object zzlL = new Object();
    static CountDownLatch zzlM = new CountDownLatch(1);

    private static final class zza implements Runnable {
        private zza() {
        }

        public void run() {
            try {
                MessageDigest unused = zzan.zzlJ = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
            } finally {
                zzan.zzlM.countDown();
            }
        }
    }

    static void zzR() {
        synchronized (zzlL) {
            if (!zzlI) {
                zzlI = true;
                new Thread(new zza()).start();
            }
        }
    }

    static MessageDigest zzS() {
        zzR();
        boolean z = false;
        try {
            z = zzlM.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        if (z && zzlJ != null) {
            return zzlJ;
        }
        return null;
    }

    private static int zza(boolean z) {
        return z ? 239 : 255;
    }

    static String zza(zzaf.zza zza2, String str, boolean z) {
        return zza(zzbut.zzf(zza2), str, z);
    }

    static String zza(String str, String str2, boolean z) {
        byte[] zzb = zzb(str, str2, z);
        return zzb != null ? zzal.zza(zzb, true) : Integer.toString(7);
    }

    static String zza(byte[] bArr, String str, boolean z) {
        return zzal.zza(z ? zzb(bArr, str) : zza(bArr, str), true);
    }

    static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + i) - 1) / i;
        Vector<byte[]> vector = new Vector<>();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * i;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > i ? i3 + i : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return vector;
    }

    static void zza(String str, byte[] bArr) {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        new zzbtm(str.getBytes("UTF-8")).zzY(bArr);
    }

    static byte[] zza(byte[] bArr, String str) {
        Vector<byte[]> zza2 = zza(bArr, 255);
        if (zza2 == null || zza2.size() == 0) {
            return zzb(zzbut.zzf(zzb(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str);
        }
        zzaf.zzf zzf = new zzaf.zzf();
        zzf.zzcz = new byte[zza2.size()][];
        Iterator<byte[]> it = zza2.iterator();
        int i = 0;
        while (it.hasNext()) {
            zzf.zzcz[i] = zzb(it.next(), str, false);
            i++;
        }
        zzf.zzcu = zzh(bArr);
        return zzbut.zzf(zzf);
    }

    static zzaf.zza zzb(long j) {
        zzaf.zza zza2 = new zzaf.zza();
        zza2.zzbs = Long.valueOf(j);
        return zza2;
    }

    static byte[] zzb(String str, String str2, boolean z) {
        zzaf.zzc zzc = new zzaf.zzc();
        try {
            zzc.zzcs = str.length() < 3 ? str.getBytes("ISO-8859-1") : zzal.zza(str, true);
            zzc.zzct = z ? str2.length() < 3 ? str2.getBytes("ISO-8859-1") : zzal.zza(str2, true) : (str2 == null || str2.length() == 0) ? Integer.toString(5).getBytes("ISO-8859-1") : zzal.zza(zza(str2.getBytes("ISO-8859-1"), (String) null, zzfx.zzDi.get().booleanValue()), true);
            return zzbut.zzf(zzc);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            return null;
        }
    }

    static byte[] zzb(byte[] bArr, String str) {
        return zzb(bArr, str, true);
    }

    private static byte[] zzb(byte[] bArr, String str, boolean z) {
        byte[] array;
        int zza2 = zza(z);
        if (bArr.length > zza2) {
            bArr = zzbut.zzf(zzb(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < zza2) {
            byte[] bArr2 = new byte[(zza2 - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            array = ByteBuffer.allocate(zza2 + 1).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            array = ByteBuffer.allocate(zza2 + 1).put((byte) bArr.length).put(bArr).array();
        }
        if (z) {
            array = ByteBuffer.allocate(256).put(zzh(array)).put(array).array();
        }
        byte[] bArr3 = new byte[256];
        new zzao().zzb(array, bArr3);
        if (str != null && str.length() > 0) {
            zza(str, bArr3);
        }
        return bArr3;
    }

    public static byte[] zzh(byte[] bArr) {
        byte[] digest;
        synchronized (zzlK) {
            MessageDigest zzS = zzS();
            if (zzS == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            zzS.reset();
            zzS.update(bArr);
            digest = zzlJ.digest();
        }
        return digest;
    }
}
