package p005b.p006a.p007a.p008a.p009a.p011b;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: b.a.a.a.a.b.r */
public class QueueFile implements Closeable {

    /* renamed from: b */
    private static final Logger f155b = Logger.getLogger(QueueFile.class.getName());

    /* renamed from: a */
    int f156a;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final RandomAccessFile f157c;

    /* renamed from: d */
    private int f158d;

    /* renamed from: e */
    private C0433a f159e;

    /* renamed from: f */
    private C0433a f160f;

    /* renamed from: g */
    private final byte[] f161g = new byte[16];

    /* renamed from: b.a.a.a.a.b.r$a */
    /* compiled from: QueueFile */
    static class C0433a {

        /* renamed from: a */
        static final C0433a f165a = new C0433a(0, 0);

        /* renamed from: b */
        final int f166b;

        /* renamed from: c */
        final int f167c;

        C0433a(int i, int i2) {
            this.f166b = i;
            this.f167c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.f166b + ", length = " + this.f167c + "]";
        }
    }

    /* renamed from: b.a.a.a.a.b.r$b */
    /* compiled from: QueueFile */
    private final class C0434b extends InputStream {

        /* renamed from: b */
        private int f169b;

        /* renamed from: c */
        private int f170c;

        private C0434b(C0433a aVar) {
            this.f169b = QueueFile.this.m211b(aVar.f166b + 4);
            this.f170c = aVar.f167c;
        }

        public int read(byte[] bArr, int i, int i2) {
            Object unused = QueueFile.m213b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.f170c <= 0) {
                return -1;
            } else {
                if (i2 > this.f170c) {
                    i2 = this.f170c;
                }
                QueueFile.this.m214b(this.f169b, bArr, i, i2);
                this.f169b = QueueFile.this.m211b(this.f169b + i2);
                this.f170c -= i2;
                return i2;
            }
        }

        public int read() {
            if (this.f170c == 0) {
                return -1;
            }
            QueueFile.this.f157c.seek((long) this.f169b);
            int read = QueueFile.this.f157c.read();
            this.f169b = QueueFile.this.m211b(this.f169b + 1);
            this.f170c--;
            return read;
        }
    }

    /* renamed from: b.a.a.a.a.b.r$c */
    /* compiled from: QueueFile */
    public interface C0435c {
        void read(InputStream inputStream, int i);
    }

    public QueueFile(File file) {
        if (!file.exists()) {
            m209a(file);
        }
        this.f157c = m212b(file);
        m218e();
    }

    /* renamed from: b */
    private static void m215b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    /* renamed from: a */
    private static void m210a(byte[] bArr, int... iArr) {
        int i = 0;
        for (int b : iArr) {
            m215b(bArr, i, b);
            i += 4;
        }
    }

    /* renamed from: a */
    private static int m202a(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    /* renamed from: e */
    private void m218e() {
        this.f157c.seek(0);
        this.f157c.readFully(this.f161g);
        this.f156a = m202a(this.f161g, 0);
        if (((long) this.f156a) > this.f157c.length()) {
            throw new IOException("File is truncated. Expected length: " + this.f156a + ", Actual length: " + this.f157c.length());
        }
        this.f158d = m202a(this.f161g, 4);
        int a = m202a(this.f161g, 8);
        int a2 = m202a(this.f161g, 12);
        this.f159e = m203a(a);
        this.f160f = m203a(a2);
    }

    /* renamed from: a */
    private void m206a(int i, int i2, int i3, int i4) {
        m210a(this.f161g, i, i2, i3, i4);
        this.f157c.seek(0);
        this.f157c.write(this.f161g);
    }

    /* renamed from: a */
    private C0433a m203a(int i) {
        if (i == 0) {
            return C0433a.f165a;
        }
        this.f157c.seek((long) i);
        return new C0433a(i, this.f157c.readInt());
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    private static void m209a(File file) {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile b = m212b(file2);
        try {
            b.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            b.seek(0);
            byte[] bArr = new byte[16];
            m210a(bArr, 4096, 0, 0, 0);
            b.write(bArr);
            b.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            b.close();
            throw th;
        }
    }

    /* renamed from: b */
    private static RandomAccessFile m212b(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public int m211b(int i) {
        return i < this.f156a ? i : (i + 16) - this.f156a;
    }

    /* renamed from: a */
    private void m207a(int i, byte[] bArr, int i2, int i3) {
        int b = m211b(i);
        if (b + i3 <= this.f156a) {
            this.f157c.seek((long) b);
            this.f157c.write(bArr, i2, i3);
            return;
        }
        int i4 = this.f156a - b;
        this.f157c.seek((long) b);
        this.f157c.write(bArr, i2, i4);
        this.f157c.seek(16);
        this.f157c.write(bArr, i2 + i4, i3 - i4);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m214b(int i, byte[] bArr, int i2, int i3) {
        int b = m211b(i);
        if (b + i3 <= this.f156a) {
            this.f157c.seek((long) b);
            this.f157c.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.f156a - b;
        this.f157c.seek((long) b);
        this.f157c.readFully(bArr, i2, i4);
        this.f157c.seek(16);
        this.f157c.readFully(bArr, i2 + i4, i3 - i4);
    }

    /* renamed from: a */
    public void mo8308a(byte[] bArr) {
        mo8309a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public synchronized void mo8309a(byte[] bArr, int i, int i2) {
        int b;
        m213b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        m216c(i2);
        boolean b2 = mo8311b();
        if (b2) {
            b = 16;
        } else {
            b = m211b(this.f160f.f166b + 4 + this.f160f.f167c);
        }
        C0433a aVar = new C0433a(b, i2);
        m215b(this.f161g, 0, i2);
        m207a(aVar.f166b, this.f161g, 0, 4);
        m207a(aVar.f166b + 4, bArr, i, i2);
        m206a(this.f156a, this.f158d + 1, b2 ? aVar.f166b : this.f159e.f166b, aVar.f166b);
        this.f160f = aVar;
        this.f158d++;
        if (b2) {
            this.f159e = this.f160f;
        }
    }

    /* renamed from: a */
    public int mo8306a() {
        if (this.f158d == 0) {
            return 16;
        }
        if (this.f160f.f166b >= this.f159e.f166b) {
            return (this.f160f.f166b - this.f159e.f166b) + 4 + this.f160f.f167c + 16;
        }
        return (((this.f160f.f166b + 4) + this.f160f.f167c) + this.f156a) - this.f159e.f166b;
    }

    /* renamed from: f */
    private int m219f() {
        return this.f156a - mo8306a();
    }

    /* renamed from: b */
    public synchronized boolean mo8311b() {
        return this.f158d == 0;
    }

    /* renamed from: c */
    private void m216c(int i) {
        int i2 = i + 4;
        int f = m219f();
        if (f < i2) {
            int i3 = this.f156a;
            do {
                f += i3;
                i3 <<= 1;
            } while (f < i2);
            m217d(i3);
            int b = m211b(this.f160f.f166b + 4 + this.f160f.f167c);
            if (b < this.f159e.f166b) {
                FileChannel channel = this.f157c.getChannel();
                channel.position((long) this.f156a);
                int i4 = b - 4;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f160f.f166b < this.f159e.f166b) {
                int i5 = (this.f156a + this.f160f.f166b) - 16;
                m206a(i3, this.f158d, this.f159e.f166b, i5);
                this.f160f = new C0433a(i5, this.f160f.f167c);
            } else {
                m206a(i3, this.f158d, this.f159e.f166b, this.f160f.f166b);
            }
            this.f156a = i3;
        }
    }

    /* renamed from: d */
    private void m217d(int i) {
        this.f157c.setLength((long) i);
        this.f157c.getChannel().force(true);
    }

    /* renamed from: a */
    public synchronized void mo8307a(C0435c cVar) {
        int i = this.f159e.f166b;
        for (int i2 = 0; i2 < this.f158d; i2++) {
            C0433a a = m203a(i);
            cVar.read(new C0434b(a), a.f167c);
            i = m211b(a.f167c + a.f166b + 4);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static <T> T m213b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* renamed from: c */
    public synchronized void mo8312c() {
        if (mo8311b()) {
            throw new NoSuchElementException();
        } else if (this.f158d == 1) {
            mo8314d();
        } else {
            int b = m211b(this.f159e.f166b + 4 + this.f159e.f167c);
            m214b(b, this.f161g, 0, 4);
            int a = m202a(this.f161g, 0);
            m206a(this.f156a, this.f158d - 1, b, this.f160f.f166b);
            this.f158d--;
            this.f159e = new C0433a(b, a);
        }
    }

    /* renamed from: d */
    public synchronized void mo8314d() {
        m206a(4096, 0, 0, 0);
        this.f158d = 0;
        this.f159e = C0433a.f165a;
        this.f160f = C0433a.f165a;
        if (this.f156a > 4096) {
            m217d(4096);
        }
        this.f156a = 4096;
    }

    public synchronized void close() {
        this.f157c.close();
    }

    /* renamed from: a */
    public boolean mo8310a(int i, int i2) {
        return (mo8306a() + 4) + i <= i2;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('[');
        sb.append("fileLength=").append(this.f156a);
        sb.append(", size=").append(this.f158d);
        sb.append(", first=").append(this.f159e);
        sb.append(", last=").append(this.f160f);
        sb.append(", element lengths=[");
        try {
            mo8307a((C0435c) new C0435c() {

                /* renamed from: a */
                boolean f162a = true;

                public void read(InputStream inputStream, int i) {
                    if (this.f162a) {
                        this.f162a = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i);
                }
            });
        } catch (IOException e) {
            f155b.log(Level.WARNING, "read error", e);
        }
        sb.append("]]");
        return sb.toString();
    }
}
