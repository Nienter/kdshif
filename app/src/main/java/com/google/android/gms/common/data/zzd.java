package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zzaCo = {ShareConstants.WEB_DIALOG_PARAM_DATA};
    private final Parcelable.Creator<T> zzaCp;

    public zzd(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.zzaCp = creator;
    }

    public static <T extends SafeParcelable> void zza(DataHolder.zza zza, T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShareConstants.WEB_DIALOG_PARAM_DATA, obtain.marshall());
        zza.zza(contentValues);
        obtain.recycle();
    }

    public static DataHolder.zza zzwC() {
        return DataHolder.zzc(zzaCo);
    }

    /* renamed from: zzcB */
    public T get(int i) {
        byte[] zzg = this.zzazI.zzg(ShareConstants.WEB_DIALOG_PARAM_DATA, i, this.zzazI.zzcC(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        T t = (SafeParcelable) this.zzaCp.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }
}
