package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import com.facebook.share.internal.ShareConstants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import p033d.Okio;

/* renamed from: com.squareup.picasso.p */
class MediaStoreRequestHandler extends ContentStreamRequestHandler {

    /* renamed from: b */
    private static final String[] f2540b = {"orientation"};

    /* renamed from: com.squareup.picasso.p$a */
    /* compiled from: MediaStoreRequestHandler */
    enum C1632a {
        MICRO(3, 96, 96),
        MINI(1, 512, 384),
        FULL(2, -1, -1);
        
        final int androidKind;
        final int height;
        final int width;

        private C1632a(int i, int i2, int i3) {
            this.androidKind = i;
            this.width = i2;
            this.height = i3;
        }
    }

    MediaStoreRequestHandler(Context context) {
        super(context);
    }

    /* renamed from: a */
    public boolean mo17477a(C1645y yVar) {
        Uri uri = yVar.f2596d;
        return "content".equals(uri.getScheme()) && ShareConstants.WEB_DIALOG_PARAM_MEDIA.equals(uri.getAuthority());
    }

    /* renamed from: a */
    public RequestHandler.C1612a mo17476a(C1645y yVar, int i) {
        Bitmap thumbnail;
        ContentResolver contentResolver = this.f2501a.getContentResolver();
        int a = m3383a(contentResolver, yVar.f2596d);
        String type = contentResolver.getType(yVar.f2596d);
        boolean z = type != null && type.startsWith("video/");
        if (yVar.mo17601d()) {
            C1632a a2 = m3384a(yVar.f2600h, yVar.f2601i);
            if (!z && a2 == C1632a.FULL) {
                return new RequestHandler.C1612a(null, Okio.m3596a(mo17530b(yVar)), Picasso.C1640d.DISK, a);
            }
            long parseId = ContentUris.parseId(yVar.f2596d);
            BitmapFactory.Options c = m3236c(yVar);
            c.inJustDecodeBounds = true;
            m3233a(yVar.f2600h, yVar.f2601i, a2.width, a2.height, c, yVar);
            if (z) {
                thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, parseId, a2 == C1632a.FULL ? 1 : a2.androidKind, c);
            } else {
                thumbnail = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, parseId, a2.androidKind, c);
            }
            if (thumbnail != null) {
                return new RequestHandler.C1612a(thumbnail, null, Picasso.C1640d.DISK, a);
            }
        }
        return new RequestHandler.C1612a(null, Okio.m3596a(mo17530b(yVar)), Picasso.C1640d.DISK, a);
    }

    /* renamed from: a */
    static C1632a m3384a(int i, int i2) {
        if (i <= C1632a.MICRO.width && i2 <= C1632a.MICRO.height) {
            return C1632a.MICRO;
        }
        if (i > C1632a.MINI.width || i2 > C1632a.MINI.height) {
            return C1632a.FULL;
        }
        return C1632a.MINI;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0034  */
    /* renamed from: a */
    static int m3383a(ContentResolver contentResolver, Uri uri) {
        Cursor cursor;
        Cursor cursor2;
        try {
            cursor = contentResolver.query(uri, f2540b, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        int i = cursor.getInt(0);
                        if (cursor == null) {
                            return i;
                        }
                        cursor.close();
                        return i;
                    }
                } catch (RuntimeException e) {
                    cursor2 = cursor;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (RuntimeException e2) {
            cursor2 = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor2 != null) {
            cursor2.close();
        }
        return 0;
    }
}
