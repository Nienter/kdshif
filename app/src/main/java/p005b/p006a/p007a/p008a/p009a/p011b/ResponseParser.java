package p005b.p006a.p007a.p008a.p009a.p011b;

/* renamed from: b.a.a.a.a.b.s */
public class ResponseParser {
    /* renamed from: a */
    public static int m228a(int i) {
        if (i >= 200 && i <= 299) {
            return 0;
        }
        if (i >= 300 && i <= 399) {
            return 1;
        }
        if (i >= 400 && i <= 499) {
            return 0;
        }
        if (i >= 500) {
            return 1;
        }
        return 1;
    }
}
