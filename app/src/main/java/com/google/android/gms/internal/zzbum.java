package com.google.android.gms.internal;

import android.support.p001v4.media.TransportMediator;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzbum {
    private final ByteBuffer zzcrW;

    public static class zza extends IOException {
        zza(int i, int i2) {
            super(new StringBuilder(108).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(i).append(" limit ").append(i2).append(").").toString());
        }
    }

    private zzbum(ByteBuffer byteBuffer) {
        this.zzcrW = byteBuffer;
        this.zzcrW.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzbum(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzH(int i, int i2) {
        return zzqs(i) + zzqp(i2);
    }

    public static int zzI(int i, int i2) {
        return zzqs(i) + zzqq(i2);
    }

    private static int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i3) < 65536) {
                        throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3).toString());
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int length = charSequence.length();
        int i4 = 0;
        int i5 = i + i2;
        while (i4 < length && i4 + i < i5) {
            char charAt = charSequence.charAt(i4);
            if (charAt >= 128) {
                break;
            }
            bArr[i + i4] = (byte) charAt;
            i4++;
        }
        if (i4 == length) {
            return i + length;
        }
        int i6 = i + i4;
        while (i4 < length) {
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128 && i6 < i5) {
                i3 = i6 + 1;
                bArr[i6] = (byte) charAt2;
            } else if (charAt2 < 2048 && i6 <= i5 - 2) {
                int i7 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 6) | 960);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i6 <= i5 - 3) {
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 12) | 480);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i3 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
            } else if (i6 <= i5 - 4) {
                if (i4 + 1 != charSequence.length()) {
                    i4++;
                    char charAt3 = charSequence.charAt(i4);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i10 = i6 + 1;
                        bArr[i6] = (byte) ((codePoint >>> 18) | 240);
                        int i11 = i10 + 1;
                        bArr[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i3 = i12 + 1;
                        bArr[i12] = (byte) ((codePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i4 - 1).toString());
            } else {
                throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charAt2).append(" at index ").append(i6).toString());
            }
            i4++;
            i6 = i3;
        }
        return i6;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static zzbum zzae(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    public static int zzag(byte[] bArr) {
        return zzqu(bArr.length) + bArr.length;
    }

    public static int zzb(int i, double d) {
        return zzqs(i) + 8;
    }

    public static int zzb(int i, zzbut zzbut) {
        return (zzqs(i) * 2) + zzd(zzbut);
    }

    private static int zzb(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = i;
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt >= 2048) {
                i3 += zza(charSequence, i2);
                break;
            }
            i2++;
            i3 = ((127 - charAt) >>> 31) + i3;
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(((long) i3) + 4294967296L).toString());
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 2048) {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int codePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((codePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i - 1).toString());
            }
            i++;
        }
    }

    public static int zzba(long j) {
        return zzbe(j);
    }

    public static int zzbb(long j) {
        return zzbe(j);
    }

    public static int zzbc(long j) {
        return zzbe(zzbg(j));
    }

    public static int zzbe(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static long zzbg(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzc(int i, zzbut zzbut) {
        return zzqs(i) + zze(zzbut);
    }

    public static int zzc(int i, byte[] bArr) {
        return zzqs(i) + zzag(bArr);
    }

    public static zzbum zzc(byte[] bArr, int i, int i2) {
        return new zzbum(bArr, i, i2);
    }

    public static int zzd(int i, float f) {
        return zzqs(i) + 4;
    }

    public static int zzd(zzbut zzbut) {
        return zzbut.zzacZ();
    }

    public static int zze(int i, long j) {
        return zzqs(i) + zzba(j);
    }

    public static int zze(zzbut zzbut) {
        int zzacZ = zzbut.zzacZ();
        return zzacZ + zzqu(zzacZ);
    }

    public static int zzf(int i, long j) {
        return zzqs(i) + zzbb(j);
    }

    public static int zzg(int i, long j) {
        return zzqs(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzqs(i) + zzbc(j);
    }

    public static int zzh(int i, boolean z) {
        return zzqs(i) + 1;
    }

    public static int zzkc(String str) {
        int zzb = zzb((CharSequence) str);
        return zzb + zzqu(zzb);
    }

    public static int zzqp(int i) {
        if (i >= 0) {
            return zzqu(i);
        }
        return 10;
    }

    public static int zzqq(int i) {
        return zzqu(zzqw(i));
    }

    public static int zzqs(int i) {
        return zzqu(zzbuw.zzK(i, 0));
    }

    public static int zzqu(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzqw(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public static int zzr(int i, String str) {
        return zzqs(i) + zzkc(str);
    }

    public void zzF(int i, int i2) {
        zzJ(i, 0);
        zzqn(i2);
    }

    public void zzG(int i, int i2) {
        zzJ(i, 0);
        zzqo(i2);
    }

    public void zzJ(int i, int i2) {
        zzqt(zzbuw.zzK(i, i2));
    }

    public void zza(int i, double d) {
        zzJ(i, 1);
        zzn(d);
    }

    public void zza(int i, long j) {
        zzJ(i, 0);
        zzaW(j);
    }

    public void zza(int i, zzbut zzbut) {
        zzJ(i, 2);
        zzc(zzbut);
    }

    public void zzaW(long j) {
        zzbd(j);
    }

    public void zzaX(long j) {
        zzbd(j);
    }

    public void zzaY(long j) {
        zzbf(j);
    }

    public void zzaZ(long j) {
        zzbd(zzbg(j));
    }

    public int zzacL() {
        return this.zzcrW.remaining();
    }

    public void zzacM() {
        if (zzacL() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void zzaf(byte[] bArr) {
        zzqt(bArr.length);
        zzah(bArr);
    }

    public void zzah(byte[] bArr) {
        zzd(bArr, 0, bArr.length);
    }

    public void zzb(int i, long j) {
        zzJ(i, 0);
        zzaX(j);
    }

    public void zzb(int i, byte[] bArr) {
        zzJ(i, 2);
        zzaf(bArr);
    }

    public void zzb(zzbut zzbut) {
        zzbut.zza(this);
    }

    public void zzbd(long j) {
        while ((-128 & j) != 0) {
            zzqr((((int) j) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            j >>>= 7;
        }
        zzqr((int) j);
    }

    public void zzbf(long j) {
        if (this.zzcrW.remaining() < 8) {
            throw new zza(this.zzcrW.position(), this.zzcrW.limit());
        }
        this.zzcrW.putLong(j);
    }

    public void zzbl(boolean z) {
        zzqr(z ? 1 : 0);
    }

    public void zzc(byte b) {
        if (!this.zzcrW.hasRemaining()) {
            throw new zza(this.zzcrW.position(), this.zzcrW.limit());
        }
        this.zzcrW.put(b);
    }

    public void zzc(int i, float f) {
        zzJ(i, 5);
        zzk(f);
    }

    public void zzc(int i, long j) {
        zzJ(i, 1);
        zzaY(j);
    }

    public void zzc(zzbut zzbut) {
        zzqt(zzbut.zzacY());
        zzbut.zza(this);
    }

    public void zzd(int i, long j) {
        zzJ(i, 0);
        zzaZ(j);
    }

    public void zzd(byte[] bArr, int i, int i2) {
        if (this.zzcrW.remaining() >= i2) {
            this.zzcrW.put(bArr, i, i2);
            return;
        }
        throw new zza(this.zzcrW.position(), this.zzcrW.limit());
    }

    public void zzg(int i, boolean z) {
        zzJ(i, 0);
        zzbl(z);
    }

    public void zzk(float f) {
        zzqv(Float.floatToIntBits(f));
    }

    public void zzkb(String str) {
        try {
            int zzqu = zzqu(str.length());
            if (zzqu == zzqu(str.length() * 3)) {
                int position = this.zzcrW.position();
                if (this.zzcrW.remaining() < zzqu) {
                    throw new zza(zzqu + position, this.zzcrW.limit());
                }
                this.zzcrW.position(position + zzqu);
                zza((CharSequence) str, this.zzcrW);
                int position2 = this.zzcrW.position();
                this.zzcrW.position(position);
                zzqt((position2 - position) - zzqu);
                this.zzcrW.position(position2);
                return;
            }
            zzqt(zzb((CharSequence) str));
            zza((CharSequence) str, this.zzcrW);
        } catch (BufferOverflowException e) {
            zza zza2 = new zza(this.zzcrW.position(), this.zzcrW.limit());
            zza2.initCause(e);
            throw zza2;
        }
    }

    public void zzn(double d) {
        zzbf(Double.doubleToLongBits(d));
    }

    public void zzq(int i, String str) {
        zzJ(i, 2);
        zzkb(str);
    }

    public void zzqn(int i) {
        if (i >= 0) {
            zzqt(i);
        } else {
            zzbd((long) i);
        }
    }

    public void zzqo(int i) {
        zzqt(zzqw(i));
    }

    public void zzqr(int i) {
        zzc((byte) i);
    }

    public void zzqt(int i) {
        while ((i & -128) != 0) {
            zzqr((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
        }
        zzqr(i);
    }

    public void zzqv(int i) {
        if (this.zzcrW.remaining() < 4) {
            throw new zza(this.zzcrW.position(), this.zzcrW.limit());
        }
        this.zzcrW.putInt(i);
    }
}
