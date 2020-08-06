package p000a;

import java.util.Locale;

/* renamed from: a.e */
public class CancellationToken {

    /* renamed from: a */
    private final CancellationTokenSource f17a;

    /* renamed from: a */
    public boolean mo3a() {
        return this.f17a.mo7a();
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(this.f17a.mo7a())});
    }
}
