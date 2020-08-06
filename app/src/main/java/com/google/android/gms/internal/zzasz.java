package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;

public class zzasz extends zza implements Iterable<String> {
    public static final Parcelable.Creator<zzasz> CREATOR = new zzata();
    public final int versionCode;
    /* access modifiers changed from: private */
    public final Bundle zzbqM;

    zzasz(int i, Bundle bundle) {
        this.versionCode = i;
        this.zzbqM = bundle;
    }

    zzasz(Bundle bundle) {
        zzac.zzw(bundle);
        this.zzbqM = bundle;
        this.versionCode = 1;
    }

    /* access modifiers changed from: package-private */
    public Object get(String str) {
        return this.zzbqM.get(str);
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            Iterator<String> zzbqN = zzasz.this.zzbqM.keySet().iterator();

            public boolean hasNext() {
                return this.zzbqN.hasNext();
            }

            public String next() {
                return this.zzbqN.next();
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove not supported");
            }
        };
    }

    public int size() {
        return this.zzbqM.size();
    }

    public String toString() {
        return this.zzbqM.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzata.zza(this, parcel, i);
    }

    public Bundle zzKY() {
        return new Bundle(this.zzbqM);
    }
}