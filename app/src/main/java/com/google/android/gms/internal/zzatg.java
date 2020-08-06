package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Parcel;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;

public class zzatg extends zzats {
    private final zza zzbrD = new zza(getContext(), zznV());
    private boolean zzbrE;

    @TargetApi(11)
    private class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, null, 1);
        }

        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e) {
                if (Build.VERSION.SDK_INT < 11 || !(e instanceof SQLiteDatabaseLockedException)) {
                    zzatg.this.zzJt().zzLa().log("Opening the local database failed, dropping and recreating it");
                    String zznV = zzatg.this.zznV();
                    if (!zzatg.this.getContext().getDatabasePath(zznV).delete()) {
                        zzatg.this.zzJt().zzLa().zzj("Failed to delete corrupted local db file", zznV);
                    }
                    try {
                        return super.getWritableDatabase();
                    } catch (SQLiteException e2) {
                        zzatg.this.zzJt().zzLa().zzj("Failed to open local database. Events will bypass local storage", e2);
                        return null;
                    }
                } else {
                    throw e;
                }
            }
        }

        @WorkerThread
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzasu.zza(zzatg.this.zzJt(), sQLiteDatabase);
        }

        @WorkerThread
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            zzasu.zza(zzatg.this.zzJt(), sQLiteDatabase, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
        }

        @WorkerThread
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzatg(zzatp zzatp) {
        super(zzatp);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ce, code lost:
        r3 = r3 + 1;
     */
    @WorkerThread
    @TargetApi(11)
    private boolean zza(int i, byte[] bArr) {
        zzJe();
        zzmq();
        if (this.zzbrE) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShareConstants.MEDIA_TYPE, Integer.valueOf(i));
        contentValues.put("entry", bArr);
        zzJv().zzKt();
        int i2 = 0;
        int i3 = 5;
        while (i2 < 5) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase == null) {
                    this.zzbrE = true;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                sQLiteDatabase.beginTransaction();
                long j = 0;
                Cursor rawQuery = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                if (rawQuery != null && rawQuery.moveToFirst()) {
                    j = rawQuery.getLong(0);
                }
                if (j >= 100000) {
                    zzJt().zzLa().log("Data loss, local db full");
                    long j2 = (100000 - j) + 1;
                    long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j2)});
                    if (delete != j2) {
                        zzJt().zzLa().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(delete), Long.valueOf(j2 - delete));
                    }
                }
                sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (SQLiteFullException e) {
                zzJt().zzLa().zzj("Error writing entry to local database", e);
                this.zzbrE = true;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteException e2) {
                if (Build.VERSION.SDK_INT < 11 || !(e2 instanceof SQLiteDatabaseLockedException)) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    zzJt().zzLa().zzj("Error writing entry to local database", e2);
                    this.zzbrE = true;
                } else {
                    SystemClock.sleep((long) i3);
                    i3 += 20;
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }
        zzJt().zzLc().log("Failed to write entry to local database");
        return false;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public SQLiteDatabase getWritableDatabase() {
        if (Build.VERSION.SDK_INT < 11 || this.zzbrE) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzbrD.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzbrE = true;
        return null;
    }

    public /* bridge */ /* synthetic */ void zzJd() {
        super.zzJd();
    }

    public /* bridge */ /* synthetic */ void zzJe() {
        super.zzJe();
    }

    public /* bridge */ /* synthetic */ void zzJf() {
        super.zzJf();
    }

    public /* bridge */ /* synthetic */ zzaso zzJg() {
        return super.zzJg();
    }

    public /* bridge */ /* synthetic */ zzass zzJh() {
        return super.zzJh();
    }

    public /* bridge */ /* synthetic */ zzatu zzJi() {
        return super.zzJi();
    }

    public /* bridge */ /* synthetic */ zzatf zzJj() {
        return super.zzJj();
    }

    public /* bridge */ /* synthetic */ zzasw zzJk() {
        return super.zzJk();
    }

    public /* bridge */ /* synthetic */ zzatw zzJl() {
        return super.zzJl();
    }

    public /* bridge */ /* synthetic */ zzatv zzJm() {
        return super.zzJm();
    }

    public /* bridge */ /* synthetic */ zzatg zzJn() {
        return super.zzJn();
    }

    public /* bridge */ /* synthetic */ zzasu zzJo() {
        return super.zzJo();
    }

    public /* bridge */ /* synthetic */ zzaue zzJp() {
        return super.zzJp();
    }

    public /* bridge */ /* synthetic */ zzatn zzJq() {
        return super.zzJq();
    }

    public /* bridge */ /* synthetic */ zzaty zzJr() {
        return super.zzJr();
    }

    public /* bridge */ /* synthetic */ zzato zzJs() {
        return super.zzJs();
    }

    public /* bridge */ /* synthetic */ zzati zzJt() {
        return super.zzJt();
    }

    public /* bridge */ /* synthetic */ zzatl zzJu() {
        return super.zzJu();
    }

    public /* bridge */ /* synthetic */ zzast zzJv() {
        return super.zzJv();
    }

    /* access modifiers changed from: package-private */
    public boolean zzKP() {
        return getContext().getDatabasePath(zznV()).exists();
    }

    public boolean zza(zzatb zzatb) {
        if (Build.VERSION.SDK_INT < 11) {
            return false;
        }
        Parcel obtain = Parcel.obtain();
        zzatb.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzJt().zzLc().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public boolean zza(zzaub zzaub) {
        if (Build.VERSION.SDK_INT < 11) {
            return false;
        }
        Parcel obtain = Parcel.obtain();
        zzaub.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzJt().zzLc().log("User property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x00c7 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0172 A[SYNTHETIC, Splitter:B:97:0x0172] */
    @TargetApi(11)
    public List<com.google.android.gms.common.internal.safeparcel.zza> zzls(int i) {
        int i2;
        int i3;
        SQLiteDatabase sQLiteDatabase;
        int i4;
        zzaub zzaub;
        zzmq();
        zzJe();
        if (Build.VERSION.SDK_INT < 11) {
            return null;
        }
        if (this.zzbrE) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zzKP()) {
            return arrayList;
        }
        i2 = 5;
        i3 = 0;
        while (i3 < 5) {
            sQLiteDatabase = null;
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    try {
                        this.zzbrE = true;
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        return null;
                    } catch (SQLiteFullException e) {
                        SQLiteFullException sQLiteFullException = e;
                        sQLiteDatabase = writableDatabase;
                        e = sQLiteFullException;
                    } catch (SQLiteException e2) {
                        SQLiteException sQLiteException = e2;
                        sQLiteDatabase = writableDatabase;
                        e = sQLiteException;
                        if (Build.VERSION.SDK_INT >= 11 || !(e instanceof SQLiteDatabaseLockedException)) {
                            if (sQLiteDatabase != null) {
                                if (sQLiteDatabase.inTransaction()) {
                                    sQLiteDatabase.endTransaction();
                                }
                            }
                            zzJt().zzLa().zzj("Error reading entries from local database", e);
                            this.zzbrE = true;
                            i4 = i2;
                        } else {
                            SystemClock.sleep((long) i2);
                            i4 = i2 + 20;
                        }
                        if (sQLiteDatabase == null) {
                            sQLiteDatabase.close();
                        }
                        i3++;
                        i2 = i4;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        sQLiteDatabase = writableDatabase;
                        th = th2;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        throw th;
                    }
                } else {
                    writableDatabase.beginTransaction();
                    Cursor query = writableDatabase.query("messages", new String[]{"rowid", ShareConstants.MEDIA_TYPE, "entry"}, null, null, null, null, "rowid asc", Integer.toString(i));
                    long j = -1;
                    while (query.moveToNext()) {
                        long j2 = query.getLong(0);
                        int i5 = query.getInt(1);
                        byte[] blob = query.getBlob(2);
                        if (i5 == 0) {
                            Parcel obtain = Parcel.obtain();
                            try {
                                obtain.unmarshall(blob, 0, blob.length);
                                obtain.setDataPosition(0);
                                zzatb createFromParcel = zzatb.CREATOR.createFromParcel(obtain);
                                obtain.recycle();
                                if (createFromParcel != null) {
                                    arrayList.add(createFromParcel);
                                }
                            } catch (zzb.zza e3) {
                                zzJt().zzLa().log("Failed to load event from local database");
                                obtain.recycle();
                                j = j2;
                            } catch (Throwable th3) {
                                obtain.recycle();
                                throw th3;
                            }
                        } else if (i5 == 1) {
                            Parcel obtain2 = Parcel.obtain();
                            try {
                                obtain2.unmarshall(blob, 0, blob.length);
                                obtain2.setDataPosition(0);
                                zzaub = zzaub.CREATOR.createFromParcel(obtain2);
                                obtain2.recycle();
                            } catch (zzb.zza e4) {
                                zzJt().zzLa().log("Failed to load user property from local database");
                                obtain2.recycle();
                                zzaub = null;
                            } catch (Throwable th4) {
                                obtain2.recycle();
                                throw th4;
                            }
                            if (zzaub != null) {
                                arrayList.add(zzaub);
                            }
                        } else {
                            zzJt().zzLa().log("Unknown record type in local database");
                        }
                        j = j2;
                    }
                    query.close();
                    if (writableDatabase.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                        zzJt().zzLa().log("Fewer entries removed from local database than expected");
                    }
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return arrayList;
                }
            } catch (SQLiteFullException e5) {
                e = e5;
            } catch (SQLiteException e6) {
                e = e6;
                if (Build.VERSION.SDK_INT >= 11) {
                }
                if (sQLiteDatabase != null) {
                }
                zzJt().zzLa().zzj("Error reading entries from local database", e);
                this.zzbrE = true;
                i4 = i2;
                if (sQLiteDatabase == null) {
                }
                i3++;
                i2 = i4;
            }
        }
        zzJt().zzLc().log("Failed to read events from database in reasonable time");
        return null;
        try {
            zzJt().zzLa().zzj("Error reading entries from local database", e);
            this.zzbrE = true;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
                i4 = i2;
            } else {
                i4 = i2;
            }
            i3++;
            i2 = i4;
        } catch (Throwable th5) {
            th = th5;
            if (sQLiteDatabase != null) {
            }
            throw th;
        }
    }

    public /* bridge */ /* synthetic */ void zzmq() {
        super.zzmq();
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    /* access modifiers changed from: package-private */
    public String zznV() {
        return zzJv().zzKj();
    }

    public /* bridge */ /* synthetic */ zze zznq() {
        return super.zznq();
    }
}
