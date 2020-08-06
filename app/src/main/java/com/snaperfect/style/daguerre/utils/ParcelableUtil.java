package com.snaperfect.style.daguerre.utils;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.snaperfect.style.daguerre.utils.j */
public class ParcelableUtil {
    /* renamed from: a */
    public static byte[] m3101a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* renamed from: a */
    public static Parcel m3099a(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        return obtain;
    }

    /* renamed from: a */
    public static <T> T m3100a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel a = m3099a(bArr);
        T createFromParcel = creator.createFromParcel(a);
        a.recycle();
        return createFromParcel;
    }
}
