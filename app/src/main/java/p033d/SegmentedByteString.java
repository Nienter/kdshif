package p033d;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* renamed from: d.q */
final class SegmentedByteString extends ByteString {
    final transient int[] directory;
    final transient byte[][] segments;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SegmentedByteString(Buffer cVar, int i) {
        super(null);
        int i2 = 0;
        C1658u.m3672a(cVar.f2657b, 0, (long) i);
        Segment oVar = cVar.f2656a;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            if (oVar.f2686c == oVar.f2685b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += oVar.f2686c - oVar.f2685b;
            i3++;
            oVar = oVar.f2689f;
        }
        this.segments = new byte[i3][];
        this.directory = new int[(i3 * 2)];
        Segment oVar2 = cVar.f2656a;
        int i5 = 0;
        while (i2 < i) {
            this.segments[i5] = oVar2.f2684a;
            int i6 = (oVar2.f2686c - oVar2.f2685b) + i2;
            if (i6 > i) {
                i6 = i;
            }
            this.directory[i5] = i6;
            this.directory[this.segments.length + i5] = oVar2.f2685b;
            oVar2.f2687d = true;
            i5++;
            oVar2 = oVar2.f2689f;
            i2 = i6;
        }
    }

    public String utf8() {
        return m3654a().utf8();
    }

    public String string(Charset charset) {
        return m3654a().string(charset);
    }

    public String base64() {
        return m3654a().base64();
    }

    public String hex() {
        return m3654a().hex();
    }

    public ByteString toAsciiLowercase() {
        return m3654a().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return m3654a().toAsciiUppercase();
    }

    public ByteString md5() {
        return m3654a().md5();
    }

    public ByteString sha1() {
        return m3654a().sha1();
    }

    public ByteString sha256() {
        return m3654a().sha256();
    }

    public ByteString hmacSha1(ByteString fVar) {
        return m3654a().hmacSha1(fVar);
    }

    public ByteString hmacSha256(ByteString fVar) {
        return m3654a().hmacSha256(fVar);
    }

    public String base64Url() {
        return m3654a().base64Url();
    }

    public ByteString substring(int i) {
        return m3654a().substring(i);
    }

    public ByteString substring(int i, int i2) {
        return m3654a().substring(i, i2);
    }

    public byte getByte(int i) {
        C1658u.m3672a((long) this.directory[this.segments.length - 1], (long) i, 1);
        int a = m3653a(i);
        return this.segments[a][(i - (a == 0 ? 0 : this.directory[a - 1])) + this.directory[this.segments.length + a]];
    }

    /* renamed from: a */
    private int m3653a(int i) {
        int binarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public int size() {
        return this.directory[this.segments.length - 1];
    }

    public byte[] toByteArray() {
        int i = 0;
        byte[] bArr = new byte[this.directory[this.segments.length - 1]];
        int length = this.segments.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            System.arraycopy(this.segments[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) {
        int i = 0;
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.segments.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            outputStream.write(this.segments[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    /* access modifiers changed from: package-private */
    public void write(Buffer cVar) {
        int i = 0;
        int length = this.segments.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            Segment oVar = new Segment(this.segments[i], i3, (i3 + i4) - i2);
            if (cVar.f2656a == null) {
                oVar.f2690g = oVar;
                oVar.f2689f = oVar;
                cVar.f2656a = oVar;
            } else {
                cVar.f2656a.f2690g.mo17767a(oVar);
            }
            i++;
            i2 = i4;
        }
        cVar.f2657b = ((long) i2) + cVar.f2657b;
    }

    public boolean rangeEquals(int i, ByteString fVar, int i2, int i3) {
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int a = m3653a(i);
        while (i3 > 0) {
            int i4 = a == 0 ? 0 : this.directory[a - 1];
            int min = Math.min(i3, ((this.directory[a] - i4) + i4) - i);
            if (!fVar.rangeEquals(i2, this.segments[a], (i - i4) + this.directory[this.segments.length + a], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int a = m3653a(i);
        while (i3 > 0) {
            int i4 = a == 0 ? 0 : this.directory[a - 1];
            int min = Math.min(i3, ((this.directory[a] - i4) + i4) - i);
            if (!C1658u.m3674a(this.segments[a], (i - i4) + this.directory[this.segments.length + a], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public int indexOf(byte[] bArr, int i) {
        return m3654a().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return m3654a().lastIndexOf(bArr, i);
    }

    /* renamed from: a */
    private ByteString m3654a() {
        return new ByteString(toByteArray());
    }

    /* access modifiers changed from: package-private */
    public byte[] internalArray() {
        return toByteArray();
    }

    public boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString) || ((ByteString) obj).size() != size() || !rangeEquals(0, (ByteString) obj, 0, size())) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            i = 1;
            int length = this.segments.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.segments[i2];
                int i4 = this.directory[length + i2];
                int i5 = this.directory[i2];
                int i6 = (i5 - i3) + i4;
                int i7 = i4;
                int i8 = i;
                for (int i9 = i7; i9 < i6; i9++) {
                    i8 = (i8 * 31) + bArr[i9];
                }
                i2++;
                i3 = i5;
                i = i8;
            }
            this.hashCode = i;
        }
        return i;
    }

    public String toString() {
        return m3654a().toString();
    }

    private Object writeReplace() {
        return m3654a();
    }
}
