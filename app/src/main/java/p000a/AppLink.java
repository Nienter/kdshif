package p000a;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

/* renamed from: a.b */
public class AppLink {

    /* renamed from: a */
    private Uri f5a;

    /* renamed from: b */
    private List<C0002a> f6b;

    /* renamed from: c */
    private Uri f7c;

    /* renamed from: a.b$a */
    /* compiled from: AppLink */
    public static class C0002a {

        /* renamed from: a */
        private final Uri f8a;

        /* renamed from: b */
        private final String f9b;

        /* renamed from: c */
        private final String f10c;

        /* renamed from: d */
        private final String f11d;

        public C0002a(String str, String str2, Uri uri, String str3) {
            this.f9b = str;
            this.f10c = str2;
            this.f8a = uri;
            this.f11d = str3;
        }
    }

    public AppLink(Uri uri, List<C0002a> list, Uri uri2) {
        this.f5a = uri;
        this.f6b = list == null ? Collections.emptyList() : list;
        this.f7c = uri2;
    }
}
