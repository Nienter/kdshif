package p033d;

import com.facebook.share.internal.ShareConstants;
import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: d.f */
public class ByteString implements Serializable, Comparable<ByteString> {
    public static final ByteString EMPTY = m3563of(new byte[0]);
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final long serialVersionUID = 1;
    final byte[] data;
    transient int hashCode;
    transient String utf8;

    ByteString(byte[] bArr) {
        this.data = bArr;
    }

    /* renamed from: of */
    public static ByteString m3563of(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    /* renamed from: of */
    public static ByteString m3564of(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        C1658u.m3672a((long) bArr.length, (long) i, (long) i2);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new ByteString(bArr2);
    }

    /* renamed from: of */
    public static ByteString m3562of(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("data == null");
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return new ByteString(bArr);
    }

    public static ByteString encodeUtf8(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString fVar = new ByteString(str.getBytes(C1658u.f2697a));
        fVar.utf8 = str;
        return fVar;
    }

    public static ByteString encodeString(String str, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        } else if (charset != null) {
            return new ByteString(str.getBytes(charset));
        } else {
            throw new IllegalArgumentException("charset == null");
        }
    }

    public String utf8() {
        String str = this.utf8;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, C1658u.f2697a);
        this.utf8 = str2;
        return str2;
    }

    public String string(Charset charset) {
        if (charset != null) {
            return new String(this.data, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    public String base64() {
        return Base64.m3466a(this.data);
    }

    public ByteString md5() {
        return m3560a("MD5");
    }

    public ByteString sha1() {
        return m3560a("SHA-1");
    }

    public ByteString sha256() {
        return m3560a("SHA-256");
    }

    /* renamed from: a */
    private ByteString m3560a(String str) {
        try {
            return m3563of(MessageDigest.getInstance(str).digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public ByteString hmacSha1(ByteString fVar) {
        return m3561a("HmacSHA1", fVar);
    }

    public ByteString hmacSha256(ByteString fVar) {
        return m3561a("HmacSHA256", fVar);
    }

    /* renamed from: a */
    private ByteString m3561a(String str, ByteString fVar) {
        try {
            Mac instance = Mac.getInstance(str);
            instance.init(new SecretKeySpec(fVar.toByteArray(), str));
            return m3563of(instance.doFinal(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public String base64Url() {
        return Base64.m3469b(this.data);
    }

    public static ByteString decodeBase64(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] a = Base64.m3468a(str);
        if (a != null) {
            return new ByteString(a);
        }
        return null;
    }

    public String hex() {
        char[] cArr = new char[(this.data.length * 2)];
        int i = 0;
        for (byte b : this.data) {
            int i2 = i + 1;
            cArr[i] = HEX_DIGITS[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = HEX_DIGITS[b & 15];
        }
        return new String(cArr);
    }

    public static ByteString decodeHex(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        } else if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        } else {
            byte[] bArr = new byte[(str.length() / 2)];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) ((m3559a(str.charAt(i * 2)) << 4) + m3559a(str.charAt((i * 2) + 1)));
            }
            return m3563of(bArr);
        }
    }

    /* renamed from: a */
    private static int m3559a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static ByteString read(InputStream inputStream, int i) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i);
        } else {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    throw new EOFException();
                }
                i2 += read;
            }
            return new ByteString(bArr);
        }
    }

    public ByteString toAsciiLowercase() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.data.length) {
                return this;
            }
            byte b = this.data[i2];
            if (b < 65 || b > 90) {
                i = i2 + 1;
            } else {
                byte[] bArr = (byte[]) this.data.clone();
                bArr[i2] = (byte) (b + 32);
                for (int i3 = i2 + 1; i3 < bArr.length; i3++) {
                    byte b2 = bArr[i3];
                    if (b2 >= 65 && b2 <= 90) {
                        bArr[i3] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArr);
            }
        }
    }

    public ByteString toAsciiUppercase() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.data.length) {
                return this;
            }
            byte b = this.data[i2];
            if (b < 97 || b > 122) {
                i = i2 + 1;
            } else {
                byte[] bArr = (byte[]) this.data.clone();
                bArr[i2] = (byte) (b - 32);
                for (int i3 = i2 + 1; i3 < bArr.length; i3++) {
                    byte b2 = bArr[i3];
                    if (b2 >= 97 && b2 <= 122) {
                        bArr[i3] = (byte) (b2 - 32);
                    }
                }
                return new ByteString(bArr);
            }
        }
    }

    public ByteString substring(int i) {
        return substring(i, this.data.length);
    }

    public ByteString substring(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (i2 > this.data.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        } else {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (i == 0 && i2 == this.data.length) {
                return this;
            } else {
                byte[] bArr = new byte[i3];
                System.arraycopy(this.data, i, bArr, 0, i3);
                return new ByteString(bArr);
            }
        }
    }

    public byte getByte(int i) {
        return this.data[i];
    }

    public int size() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        return (byte[]) this.data.clone();
    }

    /* access modifiers changed from: package-private */
    public byte[] internalArray() {
        return this.data;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(this.data).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.data);
    }

    /* access modifiers changed from: package-private */
    public void write(Buffer cVar) {
        cVar.mo17656c(this.data, 0, this.data.length);
    }

    public boolean rangeEquals(int i, ByteString fVar, int i2, int i3) {
        return fVar.rangeEquals(i2, this.data, i, i3);
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.data.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && C1658u.m3674a(this.data, i, bArr, i2, i3);
    }

    public final boolean startsWith(ByteString fVar) {
        return rangeEquals(0, fVar, 0, fVar.size());
    }

    public final boolean startsWith(byte[] bArr) {
        return rangeEquals(0, bArr, 0, bArr.length);
    }

    public final boolean endsWith(ByteString fVar) {
        return rangeEquals(size() - fVar.size(), fVar, 0, fVar.size());
    }

    public final boolean endsWith(byte[] bArr) {
        return rangeEquals(size() - bArr.length, bArr, 0, bArr.length);
    }

    public final int indexOf(ByteString fVar) {
        return indexOf(fVar.internalArray(), 0);
    }

    public final int indexOf(ByteString fVar, int i) {
        return indexOf(fVar.internalArray(), i);
    }

    public final int indexOf(byte[] bArr) {
        return indexOf(bArr, 0);
    }

    public int indexOf(byte[] bArr, int i) {
        int length = this.data.length - bArr.length;
        for (int max = Math.max(i, 0); max <= length; max++) {
            if (C1658u.m3674a(this.data, max, bArr, 0, bArr.length)) {
                return max;
            }
        }
        return -1;
    }

    public final int lastIndexOf(ByteString fVar) {
        return lastIndexOf(fVar.internalArray(), size());
    }

    public final int lastIndexOf(ByteString fVar, int i) {
        return lastIndexOf(fVar.internalArray(), i);
    }

    public final int lastIndexOf(byte[] bArr) {
        return lastIndexOf(bArr, size());
    }

    public int lastIndexOf(byte[] bArr, int i) {
        for (int min = Math.min(i, this.data.length - bArr.length); min >= 0; min--) {
            if (C1658u.m3674a(this.data, min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString) || ((ByteString) obj).size() != this.data.length || !((ByteString) obj).rangeEquals(0, this.data, 0, this.data.length)) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode2 = Arrays.hashCode(this.data);
        this.hashCode = hashCode2;
        return hashCode2;
    }

    public int compareTo(ByteString fVar) {
        int size = size();
        int size2 = fVar.size();
        int min = Math.min(size, size2);
        int i = 0;
        while (i < min) {
            byte b = getByte(i) & 255;
            byte b2 = fVar.getByte(i) & 255;
            if (b == b2) {
                i++;
            } else if (b < b2) {
                return -1;
            } else {
                return 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        if (size >= size2) {
            return 1;
        }
        return -1;
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String utf82 = utf8();
        int codePointIndexToCharIndex = codePointIndexToCharIndex(utf82, 64);
        if (codePointIndexToCharIndex != -1) {
            String replace = utf82.substring(0, codePointIndexToCharIndex).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            return codePointIndexToCharIndex < utf82.length() ? "[size=" + this.data.length + " text=" + replace + "…]" : "[text=" + replace + "]";
        } else if (this.data.length <= 64) {
            return "[hex=" + hex() + "]";
        } else {
            return "[size=" + this.data.length + " hex=" + substring(0, 64).hex() + "…]";
        }
    }

    static int codePointIndexToCharIndex(String str, int i) {
        int i2 = 0;
        int length = str.length();
        int i3 = 0;
        while (i2 < length) {
            if (i3 == i) {
                return i2;
            }
            int codePointAt = str.codePointAt(i2);
            if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                return -1;
            }
            i3++;
            i2 += Character.charCount(codePointAt);
        }
        return str.length();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        ByteString read = read(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = ByteString.class.getDeclaredField(ShareConstants.WEB_DIALOG_PARAM_DATA);
            declaredField.setAccessible(true);
            declaredField.set(this, read.data);
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        } catch (IllegalAccessException e2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }
}
