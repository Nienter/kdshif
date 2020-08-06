package p018c.p019a.p026g;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: c.a.g.d */
class OptionalMethod<T> {

    /* renamed from: a */
    private final Class<?> f781a;

    /* renamed from: b */
    private final String f782b;

    /* renamed from: c */
    private final Class[] f783c;

    public OptionalMethod(Class<?> cls, String str, Class... clsArr) {
        this.f781a = cls;
        this.f782b = str;
        this.f783c = clsArr;
    }

    /* renamed from: a */
    public boolean mo8829a(T t) {
        return m972a(t.getClass()) != null;
    }

    /* renamed from: a */
    public Object mo8828a(T t, Object... objArr) {
        Object obj = null;
        Method a = m972a(t.getClass());
        if (a == null) {
            return obj;
        }
        try {
            return a.invoke(t, objArr);
        } catch (IllegalAccessException e) {
            return obj;
        }
    }

    /* renamed from: b */
    public Object mo8830b(T t, Object... objArr) {
        try {
            return mo8828a(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* renamed from: c */
    public Object mo8831c(T t, Object... objArr) {
        Method a = m972a(t.getClass());
        if (a == null) {
            throw new AssertionError("Method " + this.f782b + " not supported for object " + t);
        }
        try {
            return a.invoke(t, objArr);
        } catch (IllegalAccessException e) {
            AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + a);
            assertionError.initCause(e);
            throw assertionError;
        }
    }

    /* renamed from: d */
    public Object mo8832d(T t, Object... objArr) {
        try {
            return mo8831c(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* renamed from: a */
    private Method m972a(Class<?> cls) {
        if (this.f782b == null) {
            return null;
        }
        Method a = m973a(cls, this.f782b, this.f783c);
        if (a == null || this.f781a == null || this.f781a.isAssignableFrom(a.getReturnType())) {
            return a;
        }
        return null;
    }

    /* renamed from: a */
    private static Method m973a(Class<?> cls, String str, Class[] clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) == 0) {
                    return null;
                }
                return method;
            } catch (NoSuchMethodException e) {
                return method;
            }
        } catch (NoSuchMethodException e2) {
            return null;
        }
    }
}
