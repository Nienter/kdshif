package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.InputStream;
import p033d.Okio;

/* renamed from: com.squareup.picasso.f */
class ContactsPhotoRequestHandler extends RequestHandler {

    /* renamed from: a */
    private static final UriMatcher f2499a = new UriMatcher(-1);

    /* renamed from: b */
    private final Context f2500b;

    static {
        f2499a.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        f2499a.addURI("com.android.contacts", "contacts/lookup/*", 1);
        f2499a.addURI("com.android.contacts", "contacts/#/photo", 2);
        f2499a.addURI("com.android.contacts", "contacts/#", 3);
        f2499a.addURI("com.android.contacts", "display_photo/#", 4);
    }

    ContactsPhotoRequestHandler(Context context) {
        this.f2500b = context;
    }

    /* renamed from: a */
    public boolean mo17477a(C1645y yVar) {
        Uri uri = yVar.f2596d;
        return "content".equals(uri.getScheme()) && ContactsContract.Contacts.CONTENT_URI.getHost().equals(uri.getHost()) && f2499a.match(yVar.f2596d) != -1;
    }

    /* renamed from: a */
    public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
        InputStream b = m3333b(yVar);
        if (b == null) {
            return null;
        }
        return new RequestHandler.C1612a(Okio.m3596a(b), Picasso.C1640d.DISK);
    }

    /* renamed from: b */
    private InputStream m3333b(C1645y yVar) {
        ContentResolver contentResolver = this.f2500b.getContentResolver();
        Uri uri = yVar.f2596d;
        switch (f2499a.match(uri)) {
            case 1:
                uri = ContactsContract.Contacts.lookupContact(contentResolver, uri);
                if (uri == null) {
                    return null;
                }
                break;
            case 2:
            case 4:
                return contentResolver.openInputStream(uri);
            case 3:
                break;
            default:
                throw new IllegalStateException("Invalid uri: " + uri);
        }
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }
}
