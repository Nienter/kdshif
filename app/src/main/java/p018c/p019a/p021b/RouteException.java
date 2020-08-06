package p018c.p019a.p021b;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: c.a.b.e */
public final class RouteException extends RuntimeException {

    /* renamed from: a */
    private static final Method f504a;
    private IOException lastException;

    static {
        Method method;
        try {
            method = Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Exception e) {
            method = null;
        }
        f504a = method;
    }

    public RouteException(IOException iOException) {
        super(iOException);
        this.lastException = iOException;
    }

    public IOException getLastConnectException() {
        return this.lastException;
    }

    public void addConnectException(IOException iOException) {
        m612a(iOException, this.lastException);
        this.lastException = iOException;
    }

    /* renamed from: a */
    private void m612a(IOException iOException, IOException iOException2) {
        if (f504a != null) {
            try {
                f504a.invoke(iOException, new Object[]{iOException2});
            } catch (IllegalAccessException | InvocationTargetException e) {
            }
        }
    }
}
