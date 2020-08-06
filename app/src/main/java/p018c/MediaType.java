package p018c;

import java.util.regex.Pattern;

/* renamed from: c.t */
public final class MediaType {

    /* renamed from: a */
    private static final Pattern f1051a = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* renamed from: b */
    private static final Pattern f1052b = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* renamed from: c */
    private final String f1053c;

    public String toString() {
        return this.f1053c;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MediaType) && ((MediaType) obj).f1053c.equals(this.f1053c);
    }

    public int hashCode() {
        return this.f1053c.hashCode();
    }
}
