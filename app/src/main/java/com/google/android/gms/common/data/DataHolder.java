package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzc;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepName
public final class DataHolder extends com.google.android.gms.common.internal.safeparcel.zza implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zze();
    private static final zza zzaCx = new zza(new String[0], null) {
        public zza zza(ContentValues contentValues) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }

        public zza zzb(HashMap<String, Object> hashMap) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }
    };
    boolean mClosed;
    final int mVersionCode;
    private final String[] zzaCq;
    Bundle zzaCr;
    private final CursorWindow[] zzaCs;
    private final Bundle zzaCt;
    int[] zzaCu;
    int zzaCv;
    private boolean zzaCw;
    private final int zzauz;

    public static class zza {
        private final HashMap<Object, Integer> zzaCA;
        private boolean zzaCB;
        private String zzaCC;
        /* access modifiers changed from: private */
        public final String[] zzaCq;
        /* access modifiers changed from: private */
        public final ArrayList<HashMap<String, Object>> zzaCy;
        private final String zzaCz;

        private zza(String[] strArr, String str) {
            this.zzaCq = (String[]) zzac.zzw(strArr);
            this.zzaCy = new ArrayList<>();
            this.zzaCz = str;
            this.zzaCA = new HashMap<>();
            this.zzaCB = false;
            this.zzaCC = null;
        }

        private int zzc(HashMap<String, Object> hashMap) {
            if (this.zzaCz == null) {
                return -1;
            }
            Object obj = hashMap.get(this.zzaCz);
            if (obj == null) {
                return -1;
            }
            Integer num = this.zzaCA.get(obj);
            if (num != null) {
                return num.intValue();
            }
            this.zzaCA.put(obj, Integer.valueOf(this.zzaCy.size()));
            return -1;
        }

        public zza zza(ContentValues contentValues) {
            zzc.zzt(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Map.Entry next : contentValues.valueSet()) {
                hashMap.put((String) next.getKey(), next.getValue());
            }
            return zzb((HashMap<String, Object>) hashMap);
        }

        public zza zzb(HashMap<String, Object> hashMap) {
            zzc.zzt(hashMap);
            int zzc = zzc(hashMap);
            if (zzc == -1) {
                this.zzaCy.add(hashMap);
            } else {
                this.zzaCy.remove(zzc);
                this.zzaCy.add(zzc, hashMap);
            }
            this.zzaCB = false;
            return this;
        }

        public DataHolder zzcE(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.zzaCw = true;
        this.mVersionCode = i;
        this.zzaCq = strArr;
        this.zzaCs = cursorWindowArr;
        this.zzauz = i2;
        this.zzaCt = bundle;
    }

    private DataHolder(zza zza2, int i, Bundle bundle) {
        this(zza2.zzaCq, zza(zza2, -1), i, bundle);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zzaCw = true;
        this.mVersionCode = 1;
        this.zzaCq = (String[]) zzac.zzw(strArr);
        this.zzaCs = (CursorWindow[]) zzac.zzw(cursorWindowArr);
        this.zzauz = i;
        this.zzaCt = bundle;
        zzwD();
    }

    public static DataHolder zza(int i, Bundle bundle) {
        return new DataHolder(zzaCx, i, bundle);
    }

    private static CursorWindow[] zza(zza zza2, int i) {
        int i2;
        boolean z;
        CursorWindow cursorWindow;
        if (zza2.zzaCq.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList zzb2 = (i < 0 || i >= zza2.zzaCy.size()) ? zza2.zzaCy : zza2.zzaCy.subList(0, i);
        int size = zzb2.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(zza2.zzaCq.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", new StringBuilder(72).append("Allocating additional cursor window for large data set (row ").append(i3).append(")").toString());
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(zza2.zzaCq.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb2.get(i3);
                boolean z3 = true;
                for (int i4 = 0; i4 < zza2.zzaCq.length && z3; i4++) {
                    String str = zza2.zzaCq[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z3 = cursorWindow2.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        z3 = cursorWindow2.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        z3 = cursorWindow2.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        z3 = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        z3 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i4);
                    } else if (obj instanceof byte[]) {
                        z3 = cursorWindow2.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        z3 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else if (obj instanceof Float) {
                        z3 = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i3, i4);
                    } else {
                        String valueOf = String.valueOf(obj);
                        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(valueOf).toString());
                    }
                }
                if (z3) {
                    i2 = i3;
                    z = false;
                    cursorWindow = cursorWindow2;
                } else if (z2) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", new StringBuilder(74).append("Couldn't populate window data for row ").append(i3).append(" - allocating new window.").toString());
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(zza2.zzaCq.length);
                    arrayList.add(cursorWindow3);
                    i2 = i3 - 1;
                    cursorWindow = cursorWindow3;
                    z = true;
                }
                z2 = z;
                cursorWindow2 = cursorWindow;
                i3 = i2 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList.get(i5)).close();
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static zza zzc(String[] strArr) {
        return new zza(strArr, null);
    }

    public static DataHolder zzcD(int i) {
        return zza(i, (Bundle) null);
    }

    private void zzi(String str, int i) {
        if (this.zzaCr == null || !this.zzaCr.containsKey(str)) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "No such column: ".concat(valueOf) : new String("No such column: "));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.zzaCv) {
            throw new CursorIndexOutOfBoundsException(i, this.zzaCv);
        }
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.zzaCs) {
                    close.close();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            if (this.zzaCw && this.zzaCs.length > 0 && !isClosed()) {
                close();
                String valueOf = String.valueOf(toString());
                Log.e("DataBuffer", new StringBuilder(String.valueOf(valueOf).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(valueOf).append(")").toString());
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        return this.zzaCv;
    }

    public int getStatusCode() {
        return this.zzauz;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzi(str, i);
        this.zzaCs[i2].copyStringToBuffer(i, this.zzaCr.getInt(str), charArrayBuffer);
    }

    public long zzb(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaCs[i2].getLong(i, this.zzaCr.getInt(str));
    }

    public int zzc(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaCs[i2].getInt(i, this.zzaCr.getInt(str));
    }

    public int zzcC(int i) {
        int i2 = 0;
        zzac.zzar(i >= 0 && i < this.zzaCv);
        while (true) {
            if (i2 >= this.zzaCu.length) {
                break;
            } else if (i < this.zzaCu[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.zzaCu.length ? i2 - 1 : i2;
    }

    public String zzd(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaCs[i2].getString(i, this.zzaCr.getInt(str));
    }

    public boolean zzdj(String str) {
        return this.zzaCr.containsKey(str);
    }

    public boolean zze(String str, int i, int i2) {
        zzi(str, i);
        return Long.valueOf(this.zzaCs[i2].getLong(i, this.zzaCr.getInt(str))).longValue() == 1;
    }

    public float zzf(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaCs[i2].getFloat(i, this.zzaCr.getInt(str));
    }

    public byte[] zzg(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaCs[i2].getBlob(i, this.zzaCr.getInt(str));
    }

    public Uri zzh(String str, int i, int i2) {
        String zzd = zzd(str, i, i2);
        if (zzd == null) {
            return null;
        }
        return Uri.parse(zzd);
    }

    public boolean zzi(String str, int i, int i2) {
        zzi(str, i);
        return this.zzaCs[i2].isNull(i, this.zzaCr.getInt(str));
    }

    public void zzwD() {
        this.zzaCr = new Bundle();
        for (int i = 0; i < this.zzaCq.length; i++) {
            this.zzaCr.putInt(this.zzaCq[i], i);
        }
        this.zzaCu = new int[this.zzaCs.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzaCs.length; i3++) {
            this.zzaCu[i3] = i2;
            i2 += this.zzaCs[i3].getNumRows() - (i2 - this.zzaCs[i3].getStartPosition());
        }
        this.zzaCv = i2;
    }

    /* access modifiers changed from: package-private */
    public String[] zzwE() {
        return this.zzaCq;
    }

    /* access modifiers changed from: package-private */
    public CursorWindow[] zzwF() {
        return this.zzaCs;
    }

    public Bundle zzwy() {
        return this.zzaCt;
    }
}
