package com.squareup.picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.squareup.picasso.o */
final class MarkableInputStream extends InputStream {

    /* renamed from: a */
    private final InputStream f2533a;

    /* renamed from: b */
    private long f2534b;

    /* renamed from: c */
    private long f2535c;

    /* renamed from: d */
    private long f2536d;

    /* renamed from: e */
    private long f2537e;

    /* renamed from: f */
    private boolean f2538f;

    /* renamed from: g */
    private int f2539g;

    public MarkableInputStream(InputStream inputStream) {
        this(inputStream, 4096);
    }

    public MarkableInputStream(InputStream inputStream, int i) {
        this(inputStream, i, 1024);
    }

    private MarkableInputStream(InputStream inputStream, int i, int i2) {
        this.f2537e = -1;
        this.f2538f = true;
        this.f2539g = -1;
        this.f2533a = !inputStream.markSupported() ? new BufferedInputStream(inputStream, i) : inputStream;
        this.f2539g = i2;
    }

    public void mark(int i) {
        this.f2537e = mo17558a(i);
    }

    /* renamed from: a */
    public long mo17558a(int i) {
        long j = this.f2534b + ((long) i);
        if (this.f2536d < j) {
            m3379b(j);
        }
        return this.f2534b;
    }

    /* renamed from: a */
    public void mo17560a(boolean z) {
        this.f2538f = z;
    }

    /* renamed from: b */
    private void m3379b(long j) {
        try {
            if (this.f2535c >= this.f2534b || this.f2534b > this.f2536d) {
                this.f2535c = this.f2534b;
                this.f2533a.mark((int) (j - this.f2534b));
            } else {
                this.f2533a.reset();
                this.f2533a.mark((int) (j - this.f2535c));
                m3378a(this.f2535c, this.f2534b);
            }
            this.f2536d = j;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to mark: " + e);
        }
    }

    public void reset() {
        mo17559a(this.f2537e);
    }

    /* renamed from: a */
    public void mo17559a(long j) {
        if (this.f2534b > this.f2536d || j < this.f2535c) {
            throw new IOException("Cannot reset");
        }
        this.f2533a.reset();
        m3378a(this.f2535c, j);
        this.f2534b = j;
    }

    /* renamed from: a */
    private void m3378a(long j, long j2) {
        while (j < j2) {
            long skip = this.f2533a.skip(j2 - j);
            if (skip == 0) {
                if (read() != -1) {
                    skip = 1;
                } else {
                    return;
                }
            }
            j += skip;
        }
    }

    public int read() {
        if (!this.f2538f && this.f2534b + 1 > this.f2536d) {
            m3379b(this.f2536d + ((long) this.f2539g));
        }
        int read = this.f2533a.read();
        if (read != -1) {
            this.f2534b++;
        }
        return read;
    }

    public int read(byte[] bArr) {
        if (!this.f2538f && this.f2534b + ((long) bArr.length) > this.f2536d) {
            m3379b(this.f2534b + ((long) bArr.length) + ((long) this.f2539g));
        }
        int read = this.f2533a.read(bArr);
        if (read != -1) {
            this.f2534b += (long) read;
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!this.f2538f && this.f2534b + ((long) i2) > this.f2536d) {
            m3379b(this.f2534b + ((long) i2) + ((long) this.f2539g));
        }
        int read = this.f2533a.read(bArr, i, i2);
        if (read != -1) {
            this.f2534b += (long) read;
        }
        return read;
    }

    public long skip(long j) {
        if (!this.f2538f && this.f2534b + j > this.f2536d) {
            m3379b(this.f2534b + j + ((long) this.f2539g));
        }
        long skip = this.f2533a.skip(j);
        this.f2534b += skip;
        return skip;
    }

    public int available() {
        return this.f2533a.available();
    }

    public void close() {
        this.f2533a.close();
    }

    public boolean markSupported() {
        return this.f2533a.markSupported();
    }
}
