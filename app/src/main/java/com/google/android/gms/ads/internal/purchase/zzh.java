package com.google.android.gms.ads.internal.purchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@zzmb
public class zzh {
    /* access modifiers changed from: private */
    public static final String zzPj = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[]{"InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time"});
    private static zzh zzPl;
    private static final Object zzrN = new Object();
    private final zza zzPk;

    public class zza extends SQLiteOpenHelper {
        public zza(zzh zzh, Context context, String str) {
            super(context, str, null, 4);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(zzh.zzPj);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            zzpe.zzbd(new StringBuilder(64).append("Database updated from version ").append(i).append(" to version ").append(i2).toString());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
            onCreate(sQLiteDatabase);
        }
    }

    zzh(Context context) {
        this.zzPk = new zza(this, context, "google_inapp_purchase.db");
    }

    public static zzh zzq(Context context) {
        zzh zzh;
        synchronized (zzrN) {
            if (zzPl == null) {
                zzPl = new zzh(context);
            }
            zzh = zzPl;
        }
        return zzh;
    }

    public int getRecordCount() {
        Cursor cursor = null;
        int i = 0;
        synchronized (zzrN) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    Cursor rawQuery = writableDatabase.rawQuery("select count(*) from InAppPurchase", null);
                    if (rawQuery.moveToFirst()) {
                        i = rawQuery.getInt(0);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                    } else if (rawQuery != null) {
                        rawQuery.close();
                    }
                } catch (SQLiteException e) {
                    String valueOf = String.valueOf(e.getMessage());
                    zzpe.zzbe(valueOf.length() != 0 ? "Error getting record count".concat(valueOf) : new String("Error getting record count"));
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
        return i;
    }

    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzPk.getWritableDatabase();
        } catch (SQLiteException e) {
            zzpe.zzbe("Error opening writable conversion tracking database");
            return null;
        }
    }

    public zzf zza(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new zzf(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    public void zza(zzf zzf) {
        if (zzf != null) {
            synchronized (zzrN) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    writableDatabase.delete("InAppPurchase", String.format(Locale.US, "%s = %d", new Object[]{"purchase_id", Long.valueOf(zzf.zzPe)}), null);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    public void zzb(zzf zzf) {
        if (zzf != null) {
            synchronized (zzrN) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("product_id", zzf.zzPg);
                    contentValues.put("developer_payload", zzf.zzPf);
                    contentValues.put("record_time", Long.valueOf(SystemClock.elapsedRealtime()));
                    zzf.zzPe = writableDatabase.insert("InAppPurchase", null, contentValues);
                    if (((long) getRecordCount()) > 20000) {
                        zzir();
                    }
                }
            }
        }
    }

    public List<zzf> zzg(long j) {
        Cursor cursor;
        synchronized (zzrN) {
            LinkedList linkedList = new LinkedList();
            if (j <= 0) {
                return linkedList;
            }
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return linkedList;
            }
            try {
                cursor = writableDatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", String.valueOf(j));
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            linkedList.add(zza(cursor));
                        } while (cursor.moveToNext());
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (SQLiteException e) {
                    e = e;
                    try {
                        String valueOf = String.valueOf(e.getMessage());
                        zzpe.zzbe(valueOf.length() != 0 ? "Error extracing purchase info: ".concat(valueOf) : new String("Error extracing purchase info: "));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return linkedList;
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    public void zzir() {
        Cursor cursor;
        synchronized (zzrN) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    cursor = writableDatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                zza(zza(cursor));
                            }
                        } catch (SQLiteException e) {
                            e = e;
                            try {
                                String valueOf = String.valueOf(e.getMessage());
                                zzpe.zzbe(valueOf.length() != 0 ? "Error remove oldest record".concat(valueOf) : new String("Error remove oldest record"));
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return;
                            } catch (Throwable th) {
                                th = th;
                            }
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
    }
}
