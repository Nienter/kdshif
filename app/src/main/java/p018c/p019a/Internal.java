package p018c.p019a;

import java.net.Socket;
import javax.net.ssl.SSLSocket;
import p018c.Address;
import p018c.ConnectionPool;
import p018c.ConnectionSpec;
import p018c.Headers;
import p018c.Response;
import p018c.p019a.p021b.RealConnection;
import p018c.p019a.p021b.RouteDatabase;
import p018c.p019a.p021b.StreamAllocation;

/* renamed from: c.a.a */
public abstract class Internal {

    /* renamed from: a */
    public static Internal f418a;

    /* renamed from: a */
    public abstract int mo8582a(Response.C0543a aVar);

    /* renamed from: a */
    public abstract RealConnection mo8583a(ConnectionPool jVar, Address aVar, StreamAllocation gVar);

    /* renamed from: a */
    public abstract RouteDatabase mo8584a(ConnectionPool jVar);

    /* renamed from: a */
    public abstract void mo8585a(ConnectionSpec kVar, SSLSocket sSLSocket, boolean z);

    /* renamed from: a */
    public abstract void mo8586a(Headers.C0534a aVar, String str);

    /* renamed from: a */
    public abstract void mo8587a(Headers.C0534a aVar, String str, String str2);

    /* renamed from: a */
    public abstract boolean mo8588a(ConnectionPool jVar, RealConnection cVar);

    /* renamed from: b */
    public abstract Socket mo8589b(ConnectionPool jVar, Address aVar, StreamAllocation gVar);

    /* renamed from: b */
    public abstract void mo8590b(ConnectionPool jVar, RealConnection cVar);
}
