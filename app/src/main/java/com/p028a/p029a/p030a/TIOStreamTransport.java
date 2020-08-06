package com.p028a.p029a.p030a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.a.a.a.cg */
public class TIOStreamTransport extends TTransport {

    /* renamed from: a */
    protected InputStream f1496a = null;

    /* renamed from: b */
    protected OutputStream f1497b = null;

    protected TIOStreamTransport() {
    }

    public TIOStreamTransport(OutputStream outputStream) {
        this.f1497b = outputStream;
    }

    /* renamed from: a */
    public int mo9461a(byte[] bArr, int i, int i2) {
        if (this.f1496a == null) {
            throw new TTransportException(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.f1496a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new TTransportException(4);
        } catch (IOException e) {
            throw new TTransportException(0, (Throwable) e);
        }
    }

    /* renamed from: b */
    public void mo9462b(byte[] bArr, int i, int i2) {
        if (this.f1497b == null) {
            throw new TTransportException(1, "Cannot write to null outputStream");
        }
        try {
            this.f1497b.write(bArr, i, i2);
        } catch (IOException e) {
            throw new TTransportException(0, (Throwable) e);
        }
    }
}
