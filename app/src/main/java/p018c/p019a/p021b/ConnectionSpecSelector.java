package p018c.p019a.p021b;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import p018c.ConnectionSpec;
import p018c.p019a.Internal;

/* renamed from: c.a.b.b */
public final class ConnectionSpecSelector {

    /* renamed from: a */
    private final List<ConnectionSpec> f485a;

    /* renamed from: b */
    private int f486b = 0;

    /* renamed from: c */
    private boolean f487c;

    /* renamed from: d */
    private boolean f488d;

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.f485a = list;
    }

    /* renamed from: a */
    public ConnectionSpec mo8635a(SSLSocket sSLSocket) {
        ConnectionSpec kVar;
        int i = this.f486b;
        int size = this.f485a.size();
        int i2 = i;
        while (true) {
            if (i2 >= size) {
                kVar = null;
                break;
            }
            kVar = this.f485a.get(i2);
            if (kVar.mo8904a(sSLSocket)) {
                this.f486b = i2 + 1;
                break;
            }
            i2++;
        }
        if (kVar == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f488d + ", modes=" + this.f485a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
        }
        this.f487c = m590b(sSLSocket);
        Internal.f418a.mo8585a(kVar, sSLSocket, this.f488d);
        return kVar;
    }

    /* renamed from: a */
    public boolean mo8636a(IOException iOException) {
        this.f488d = true;
        if (!this.f487c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        if ((iOException instanceof SSLHandshakeException) || (iOException instanceof SSLProtocolException)) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private boolean m590b(SSLSocket sSLSocket) {
        int i = this.f486b;
        while (true) {
            int i2 = i;
            if (i2 >= this.f485a.size()) {
                return false;
            }
            if (this.f485a.get(i2).mo8904a(sSLSocket)) {
                return true;
            }
            i = i2 + 1;
        }
    }
}
