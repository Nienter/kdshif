package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.WorkerThread;
import android.support.p001v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzauf;
import com.google.android.gms.internal.zzauh;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzasu extends zzats {
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbqp = new ArrayMap(18);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbqq = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbqr = new ArrayMap(1);
    /* access modifiers changed from: private */
    public static final Map<String, String> zzbqs = new ArrayMap(1);
    private final zzc zzbqt = new zzc(getContext(), zznV());
    /* access modifiers changed from: private */
    public final zzatz zzbqu = new zzatz(zznq());

    public static class zza {
        long zzbqv;
        long zzbqw;
        long zzbqx;
        long zzbqy;
        long zzbqz;
    }

    interface zzb {
        boolean zza(long j, zzauh.zzb zzb);

        void zzb(zzauh.zze zze);
    }

    private class zzc extends SQLiteOpenHelper {
        zzc(Context context, String str) {
            super(context, str, null, 1);
        }

        @WorkerThread
        public SQLiteDatabase getWritableDatabase() {
            if (!zzasu.this.zzbqu.zzz(zzasu.this.zzJv().zzKg())) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e) {
                zzasu.this.zzbqu.start();
                zzasu.this.zzJt().zzLa().log("Opening the database failed, dropping and recreating it");
                String zznV = zzasu.this.zznV();
                if (!zzasu.this.getContext().getDatabasePath(zznV).delete()) {
                    zzasu.this.zzJt().zzLa().zzj("Failed to delete corrupted db file", zznV);
                }
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zzasu.this.zzbqu.clear();
                    return writableDatabase;
                } catch (SQLiteException e2) {
                    zzasu.this.zzJt().zzLa().zzj("Failed to open freshly created database", e2);
                    throw e2;
                }
            }
        }

        @WorkerThread
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase);
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
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "events", "CREATE TABLE IF NOT EXISTS events ( app_id TEXT NOT NULL, name TEXT NOT NULL, lifetime_count INTEGER NOT NULL, current_bundle_count INTEGER NOT NULL, last_fire_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,lifetime_count,current_bundle_count,last_fire_timestamp", null);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "user_attributes", "CREATE TABLE IF NOT EXISTS user_attributes ( app_id TEXT NOT NULL, name TEXT NOT NULL, set_timestamp INTEGER NOT NULL, value BLOB NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,set_timestamp,value", null);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zzasu.zzbqp);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "queue", "CREATE TABLE IF NOT EXISTS queue ( app_id TEXT NOT NULL, bundle_end_timestamp INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,bundle_end_timestamp,data", zzasu.zzbqr);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "raw_events_metadata", "CREATE TABLE IF NOT EXISTS raw_events_metadata ( app_id TEXT NOT NULL, metadata_fingerprint INTEGER NOT NULL, metadata BLOB NOT NULL, PRIMARY KEY (app_id, metadata_fingerprint));", "app_id,metadata_fingerprint,metadata", null);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "raw_events", "CREATE TABLE IF NOT EXISTS raw_events ( app_id TEXT NOT NULL, name TEXT NOT NULL, timestamp INTEGER NOT NULL, metadata_fingerprint INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,name,timestamp,metadata_fingerprint,data", zzasu.zzbqq);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "event_filters", "CREATE TABLE IF NOT EXISTS event_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, event_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, event_name, audience_id, filter_id));", "app_id,audience_id,filter_id,event_name,data", null);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "property_filters", "CREATE TABLE IF NOT EXISTS property_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, property_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, property_name, audience_id, filter_id));", "app_id,audience_id,filter_id,property_name,data", null);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "audience_filter_values", "CREATE TABLE IF NOT EXISTS audience_filter_values ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, current_results BLOB, PRIMARY KEY (app_id, audience_id));", "app_id,audience_id,current_results", null);
            zzasu.zza(zzasu.this.zzJt(), sQLiteDatabase, "app2", "CREATE TABLE IF NOT EXISTS app2 ( app_id TEXT NOT NULL, first_open_count INTEGER NOT NULL, PRIMARY KEY (app_id));", "app_id,first_open_count", zzasu.zzbqs);
        }

        @WorkerThread
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    static {
        zzbqp.put("app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;");
        zzbqp.put("app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;");
        zzbqp.put("gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;");
        zzbqp.put("dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;");
        zzbqp.put("measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;");
        zzbqp.put("last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;");
        zzbqp.put("day", "ALTER TABLE apps ADD COLUMN day INTEGER;");
        zzbqp.put("daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;");
        zzbqp.put("daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;");
        zzbqp.put("daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;");
        zzbqp.put("remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;");
        zzbqp.put("config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;");
        zzbqp.put("failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;");
        zzbqp.put("app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;");
        zzbqp.put("firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;");
        zzbqp.put("daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;");
        zzbqp.put("daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;");
        zzbqp.put("health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;");
        zzbqq.put("realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;");
        zzbqr.put("has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;");
        zzbqs.put("previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;");
    }

    zzasu(zzatp zzatp) {
        super(zzatp);
    }

    private boolean zzKP() {
        return getContext().getDatabasePath(zznV()).exists();
    }

    @WorkerThread
    @TargetApi(11)
    static int zza(Cursor cursor, int i) {
        if (Build.VERSION.SDK_INT >= 11) {
            return cursor.getType(i);
        }
        CursorWindow window = ((SQLiteCursor) cursor).getWindow();
        int position = cursor.getPosition();
        if (window.isNull(position, i)) {
            return 0;
        }
        if (window.isLong(position, i)) {
            return 1;
        }
        if (window.isFloat(position, i)) {
            return 2;
        }
        if (window.isString(position, i)) {
            return 3;
        }
        return window.isBlob(position, i) ? 4 : -1;
    }

    @WorkerThread
    private long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } else if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    static void zza(zzati zzati, SQLiteDatabase sQLiteDatabase) {
        if (zzati == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        } else if (Build.VERSION.SDK_INT >= 9) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzati.zzLc().log("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzati.zzLc().log("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzati.zzLc().log("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzati.zzLc().log("Failed to turn on database write permission for owner");
            }
        }
    }

    @WorkerThread
    static void zza(zzati zzati, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Map<String, String> map) {
        if (zzati == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzati, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            zza(zzati, sQLiteDatabase, str, str3, map);
        } catch (SQLiteException e) {
            zzati.zzLa().zzj("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    @WorkerThread
    static void zza(zzati zzati, SQLiteDatabase sQLiteDatabase, String str, String str2, Map<String, String> map) {
        if (zzati == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Set<String> zzb2 = zzb(sQLiteDatabase, str);
        for (String str3 : str2.split(",")) {
            if (!zzb2.remove(str3)) {
                throw new SQLiteException(new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str3).length()).append("Table ").append(str).append(" is missing required column: ").append(str3).toString());
            }
        }
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                if (!zzb2.remove(next.getKey())) {
                    sQLiteDatabase.execSQL((String) next.getValue());
                }
            }
        }
        if (!zzb2.isEmpty()) {
            zzati.zzLc().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb2));
        }
    }

    @WorkerThread
    private void zza(String str, zzauf.zza zza2) {
        boolean z = false;
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzac.zzw(zza2);
        zzac.zzw(zza2.zzbvj);
        zzac.zzw(zza2.zzbvi);
        if (zza2.zzbvh == null) {
            zzJt().zzLc().zzj("Audience with no ID. appId", zzati.zzfI(str));
            return;
        }
        int intValue = zza2.zzbvh.intValue();
        for (zzauf.zzb zzb2 : zza2.zzbvj) {
            if (zzb2.zzbvl == null) {
                zzJt().zzLc().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzati.zzfI(str), zza2.zzbvh);
                return;
            }
        }
        for (zzauf.zze zze : zza2.zzbvi) {
            if (zze.zzbvl == null) {
                zzJt().zzLc().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzati.zzfI(str), zza2.zzbvh);
                return;
            }
        }
        boolean z2 = true;
        zzauf.zzb[] zzbArr = zza2.zzbvj;
        int length = zzbArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (!zza(str, intValue, zzbArr[i])) {
                z2 = false;
                break;
            } else {
                i++;
            }
        }
        if (z2) {
            zzauf.zze[] zzeArr = zza2.zzbvi;
            int length2 = zzeArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    break;
                } else if (!zza(str, intValue, zzeArr[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        z = z2;
        if (!z) {
            zzA(str, intValue);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    @WorkerThread
    static boolean zza(zzati zzati, SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor;
        Cursor cursor2 = null;
        if (zzati == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        try {
            cursor = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean moveToFirst = cursor.moveToFirst();
                if (cursor == null) {
                    return moveToFirst;
                }
                cursor.close();
                return moveToFirst;
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            if (cursor2 != null) {
            }
            throw th;
        }
        try {
            zzati.zzLc().zze("Error querying for table", str, e);
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private boolean zza(String str, int i, zzauf.zzb zzb2) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzac.zzw(zzb2);
        if (TextUtils.isEmpty(zzb2.zzbvm)) {
            zzJt().zzLc().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzati.zzfI(str), Integer.valueOf(i), String.valueOf(zzb2.zzbvl));
            return false;
        }
        try {
            byte[] bArr = new byte[zzb2.zzacZ()];
            zzbum zzae = zzbum.zzae(bArr);
            zzb2.zza(zzae);
            zzae.zzacM();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzb2.zzbvl);
            contentValues.put("event_name", zzb2.zzbvm);
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzJt().zzLa().zzj("Failed to insert event filter (got -1). appId", zzati.zzfI(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzJt().zzLa().zze("Error storing event filter. appId", zzati.zzfI(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzJt().zzLa().zze("Configuration loss. Failed to serialize event filter. appId", zzati.zzfI(str), e2);
            return false;
        }
    }

    @WorkerThread
    private boolean zza(String str, int i, zzauf.zze zze) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzac.zzw(zze);
        if (TextUtils.isEmpty(zze.zzbvB)) {
            zzJt().zzLc().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzati.zzfI(str), Integer.valueOf(i), String.valueOf(zze.zzbvl));
            return false;
        }
        try {
            byte[] bArr = new byte[zze.zzacZ()];
            zzbum zzae = zzbum.zzae(bArr);
            zze.zza(zzae);
            zzae.zzacM();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zze.zzbvl);
            contentValues.put("property_name", zze.zzbvB);
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzJt().zzLa().zzj("Failed to insert property filter (got -1). appId", zzati.zzfI(str));
                return false;
            } catch (SQLiteException e) {
                zzJt().zzLa().zze("Error storing property filter. appId", zzati.zzfI(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzJt().zzLa().zze("Configuration loss. Failed to serialize property filter. appId", zzati.zzfI(str), e2);
            return false;
        }
    }

    @WorkerThread
    private long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    @WorkerThread
    public void beginTransaction() {
        zznA();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public void endTransaction() {
        zznA();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public SQLiteDatabase getWritableDatabase() {
        zzmq();
        try {
            return this.zzbqt.getWritableDatabase();
        } catch (SQLiteException e) {
            zzJt().zzLc().zzj("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public void setTransactionSuccessful() {
        zznA();
        getWritableDatabase().setTransactionSuccessful();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzA(String str, int i) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(i)});
        writableDatabase.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(i)});
    }

    public void zzG(List<Long> list) {
        zzac.zzw(list);
        zzmq();
        zznA();
        StringBuilder sb = new StringBuilder("rowid in (");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                break;
            }
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(list.get(i2).longValue());
            i = i2 + 1;
        }
        sb.append(")");
        int delete = getWritableDatabase().delete("raw_events", sb.toString(), null);
        if (delete != list.size()) {
            zzJt().zzLa().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    @WorkerThread
    public String zzKG() {
        Cursor cursor;
        Throwable th;
        String str = null;
        try {
            cursor = getWritableDatabase().rawQuery("select app_id from queue where app_id not in (select app_id from apps where measurement_enabled=0) order by has_realtime desc, rowid asc limit 1;", null);
            try {
                if (cursor.moveToFirst()) {
                    str = cursor.getString(0);
                    if (cursor != null) {
                        cursor.close();
                    }
                } else if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                e = e;
                try {
                    zzJt().zzLa().zzj("Database error getting next bundle app id", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th3) {
            cursor = null;
            th = th3;
            if (cursor != null) {
            }
            throw th;
        }
        return str;
    }

    public boolean zzKH() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzKI() {
        zzmq();
        zznA();
        if (zzKP()) {
            long j = zzJu().zzbsj.get();
            long elapsedRealtime = zznq().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzJv().zzKo()) {
                zzJu().zzbsj.set(elapsedRealtime);
                zzKJ();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzKJ() {
        zzmq();
        zznA();
        if (zzKP()) {
            int delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zznq().currentTimeMillis()), String.valueOf(zzJv().zzKn())});
            if (delete > 0) {
                zzJt().zzLg().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
            }
        }
    }

    @WorkerThread
    public long zzKK() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    @WorkerThread
    public long zzKL() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public boolean zzKM() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public boolean zzKN() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public long zzKO() {
        long j = -1;
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            zzJt().zzLa().zzj("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0094  */
    @WorkerThread
    public zzasy zzP(String str, String str2) {
        Cursor cursor;
        Cursor cursor2 = null;
        zzac.zzdv(str);
        zzac.zzdv(str2);
        zzmq();
        zznA();
        try {
            Cursor query = getWritableDatabase().query("events", new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                zzasy zzasy = new zzasy(str, str2, query.getLong(0), query.getLong(1), query.getLong(2));
                if (query.moveToNext()) {
                    zzJt().zzLa().zzj("Got multiple records for event aggregates, expected one. appId", zzati.zzfI(str));
                }
                if (query == null) {
                    return zzasy;
                }
                query.close();
                return zzasy;
            } catch (SQLiteException e) {
                e = e;
                cursor = query;
            } catch (Throwable th) {
                th = th;
                cursor2 = query;
                if (cursor2 != null) {
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        try {
            zzJt().zzLa().zzd("Error querying events. appId", zzati.zzfI(str), str2, e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    @WorkerThread
    public void zzQ(String str, String str2) {
        zzac.zzdv(str);
        zzac.zzdv(str2);
        zzmq();
        zznA();
        try {
            zzJt().zzLg().zzj("Deleted user attribute rows:", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzJt().zzLa().zzd("Error deleting user attribute. appId", zzati.zzfI(str), str2, e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008a  */
    @WorkerThread
    public zzaud zzR(String str, String str2) {
        Cursor cursor;
        Cursor cursor2 = null;
        zzac.zzdv(str);
        zzac.zzdv(str2);
        zzmq();
        zznA();
        try {
            Cursor query = getWritableDatabase().query("user_attributes", new String[]{"set_timestamp", FirebaseAnalytics.Param.VALUE}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                zzaud zzaud = new zzaud(str, str2, query.getLong(0), zzb(query, 1));
                if (query.moveToNext()) {
                    zzJt().zzLa().zzj("Got multiple records for user property, expected one. appId", zzati.zzfI(str));
                }
                if (query == null) {
                    return zzaud;
                }
                query.close();
                return zzaud;
            } catch (SQLiteException e) {
                e = e;
                cursor = query;
            } catch (Throwable th) {
                th = th;
                cursor2 = query;
                if (cursor2 != null) {
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
        try {
            zzJt().zzLa().zzd("Error querying user property. appId", zzati.zzfI(str), str2, e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    public Map<Integer, List<zzauf.zzb>> zzS(String str, String str2) {
        Cursor cursor;
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzac.zzdv(str2);
        ArrayMap arrayMap = new ArrayMap();
        try {
            cursor = getWritableDatabase().query("event_filters", new String[]{"audience_id", ShareConstants.WEB_DIALOG_PARAM_DATA}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    Map<Integer, List<zzauf.zzb>> emptyMap = Collections.emptyMap();
                    if (cursor == null) {
                        return emptyMap;
                    }
                    cursor.close();
                    return emptyMap;
                }
                do {
                    zzbul zzad = zzbul.zzad(cursor.getBlob(1));
                    zzauf.zzb zzb2 = new zzauf.zzb();
                    try {
                        zzb2.zzb(zzad);
                        int i = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), list);
                        }
                        list.add(zzb2);
                    } catch (IOException e) {
                        zzJt().zzLa().zze("Failed to merge filter. appId", zzati.zzfI(str), e);
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
        try {
            zzJt().zzLa().zze("Database error querying filters. appId", zzati.zzfI(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b5  */
    public Map<Integer, List<zzauf.zze>> zzT(String str, String str2) {
        Cursor cursor;
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzac.zzdv(str2);
        ArrayMap arrayMap = new ArrayMap();
        try {
            cursor = getWritableDatabase().query("property_filters", new String[]{"audience_id", ShareConstants.WEB_DIALOG_PARAM_DATA}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    Map<Integer, List<zzauf.zze>> emptyMap = Collections.emptyMap();
                    if (cursor == null) {
                        return emptyMap;
                    }
                    cursor.close();
                    return emptyMap;
                }
                do {
                    zzbul zzad = zzbul.zzad(cursor.getBlob(1));
                    zzauf.zze zze = new zzauf.zze();
                    try {
                        zze.zzb(zzad);
                        int i = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), list);
                        }
                        list.add(zze);
                    } catch (IOException e) {
                        zzJt().zzLa().zze("Failed to merge filter", zzati.zzfI(str), e);
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
            if (cursor != null) {
            }
            throw th;
        }
        try {
            zzJt().zzLa().zze("Database error querying filters. appId", zzati.zzfI(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public long zzU(String str, String str2) {
        SQLiteException e;
        long j;
        zzac.zzdv(str);
        zzac.zzdv(str2);
        zzmq();
        zznA();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            j = zza(new StringBuilder(String.valueOf(str2).length() + 32).append("select ").append(str2).append(" from app2 where app_id=?").toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    zzJt().zzLa().zze("Failed to insert column (got -1). appId", zzati.zzfI(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzJt().zzLa().zze("Failed to update column (got 0). appId", zzati.zzfI(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzJt().zzLa().zzd("Error inserting column. appId", zzati.zzfI(str), str2, e);
                    return j;
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            j = 0;
            zzJt().zzLa().zzd("Error inserting column. appId", zzati.zzfI(str), str2, e);
            return j;
        }
    }

    public long zza(zzauh.zze zze) {
        zzmq();
        zznA();
        zzac.zzw(zze);
        zzac.zzdv(zze.zzaR);
        try {
            byte[] bArr = new byte[zze.zzacZ()];
            zzbum zzae = zzbum.zzae(bArr);
            zze.zza(zzae);
            zzae.zzacM();
            long zzz = zzJp().zzz(bArr);
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.zzaR);
            contentValues.put("metadata_fingerprint", Long.valueOf(zzz));
            contentValues.put("metadata", bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return zzz;
            } catch (SQLiteException e) {
                zzJt().zzLa().zze("Error storing raw event metadata. appId", zzati.zzfI(zze.zzaR), e);
                throw e;
            }
        } catch (IOException e2) {
            zzJt().zzLa().zze("Data loss. Failed to serialize event metadata. appId", zzati.zzfI(zze.zzaR), e2);
            throw e2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0135  */
    @WorkerThread
    public zza zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Cursor cursor;
        zzac.zzdv(str);
        zzmq();
        zznA();
        String[] strArr = {str};
        zza zza2 = new zza();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            cursor = writableDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    zzJt().zzLc().zzj("Not updating daily counts, app is not known. appId", zzati.zzfI(str));
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zza2;
                }
                if (cursor.getLong(0) == j) {
                    zza2.zzbqw = cursor.getLong(1);
                    zza2.zzbqv = cursor.getLong(2);
                    zza2.zzbqx = cursor.getLong(3);
                    zza2.zzbqy = cursor.getLong(4);
                    zza2.zzbqz = cursor.getLong(5);
                }
                if (z) {
                    zza2.zzbqw++;
                }
                if (z2) {
                    zza2.zzbqv++;
                }
                if (z3) {
                    zza2.zzbqx++;
                }
                if (z4) {
                    zza2.zzbqy++;
                }
                if (z5) {
                    zza2.zzbqz++;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j));
                contentValues.put("daily_public_events_count", Long.valueOf(zza2.zzbqv));
                contentValues.put("daily_events_count", Long.valueOf(zza2.zzbqw));
                contentValues.put("daily_conversions_count", Long.valueOf(zza2.zzbqx));
                contentValues.put("daily_error_events_count", Long.valueOf(zza2.zzbqy));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zza2.zzbqz));
                writableDatabase.update("apps", contentValues, "app_id=?", strArr);
                if (cursor != null) {
                    cursor.close();
                }
                return zza2;
            } catch (SQLiteException e) {
                e = e;
                try {
                    zzJt().zzLa().zze("Error updating daily counts. appId", zzati.zzfI(str), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zza2;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                    }
                    throw th;
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

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zza(ContentValues contentValues, String str, Object obj) {
        zzac.zzdv(str);
        zzac.zzw(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    public void zza(zzasp zzasp) {
        zzac.zzw(zzasp);
        zzmq();
        zznA();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzasp.zzjI());
        contentValues.put("app_instance_id", zzasp.getAppInstanceId());
        contentValues.put("gmp_app_id", zzasp.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzasp.zzJx());
        contentValues.put("last_bundle_index", Long.valueOf(zzasp.zzJG()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzasp.zzJz()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzasp.zzJA()));
        contentValues.put("app_version", zzasp.zzmy());
        contentValues.put("app_store", zzasp.zzJC());
        contentValues.put("gmp_version", Long.valueOf(zzasp.zzJD()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzasp.zzJE()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzasp.zzJF()));
        contentValues.put("day", Long.valueOf(zzasp.zzJK()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzasp.zzJL()));
        contentValues.put("daily_events_count", Long.valueOf(zzasp.zzJM()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzasp.zzJN()));
        contentValues.put("config_fetched_time", Long.valueOf(zzasp.zzJH()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzasp.zzJI()));
        contentValues.put("app_version_int", Long.valueOf(zzasp.zzJB()));
        contentValues.put("firebase_instance_id", zzasp.zzJy());
        contentValues.put("daily_error_events_count", Long.valueOf(zzasp.zzJP()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzasp.zzJO()));
        contentValues.put("health_monitor_sample", zzasp.zzJQ());
        try {
            if (getWritableDatabase().insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzJt().zzLa().zzj("Failed to insert/update app (got -1). appId", zzati.zzfI(zzasp.zzjI()));
            }
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Error storing app. appId", zzati.zzfI(zzasp.zzjI()), e);
        }
    }

    public void zza(zzasx zzasx, long j, boolean z) {
        int i = 0;
        zzmq();
        zznA();
        zzac.zzw(zzasx);
        zzac.zzdv(zzasx.zzVQ);
        zzauh.zzb zzb2 = new zzauh.zzb();
        zzb2.zzbvX = Long.valueOf(zzasx.zzbqH);
        zzb2.zzbvV = new zzauh.zzc[zzasx.zzbqI.size()];
        Iterator<String> it = zzasx.zzbqI.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            String next = it.next();
            zzauh.zzc zzc2 = new zzauh.zzc();
            zzb2.zzbvV[i2] = zzc2;
            zzc2.name = next;
            zzJp().zza(zzc2, zzasx.zzbqI.get(next));
            i2++;
        }
        try {
            byte[] bArr = new byte[zzb2.zzacZ()];
            zzbum zzae = zzbum.zzae(bArr);
            zzb2.zza(zzae);
            zzae.zzacM();
            zzJt().zzLg().zze("Saving event, name, data size", zzasx.mName, Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzasx.zzVQ);
            contentValues.put("name", zzasx.mName);
            contentValues.put("timestamp", Long.valueOf(zzasx.zzavX));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bArr);
            if (z) {
                i = 1;
            }
            contentValues.put("realtime", Integer.valueOf(i));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) == -1) {
                    zzJt().zzLa().zzj("Failed to insert raw event (got -1). appId", zzati.zzfI(zzasx.zzVQ));
                }
            } catch (SQLiteException e) {
                zzJt().zzLa().zze("Error storing raw event. appId", zzati.zzfI(zzasx.zzVQ), e);
            }
        } catch (IOException e2) {
            zzJt().zzLa().zze("Data loss. Failed to serialize event params/data. appId", zzati.zzfI(zzasx.zzVQ), e2);
        }
    }

    @WorkerThread
    public void zza(zzasy zzasy) {
        zzac.zzw(zzasy);
        zzmq();
        zznA();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzasy.zzVQ);
        contentValues.put("name", zzasy.mName);
        contentValues.put("lifetime_count", Long.valueOf(zzasy.zzbqJ));
        contentValues.put("current_bundle_count", Long.valueOf(zzasy.zzbqK));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzasy.zzbqL));
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzJt().zzLa().zzj("Failed to insert/update event aggregates (got -1). appId", zzati.zzfI(zzasy.zzVQ));
            }
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Error storing event aggregates. appId", zzati.zzfI(zzasy.zzVQ), e);
        }
    }

    @WorkerThread
    public void zza(zzauh.zze zze, boolean z) {
        zzmq();
        zznA();
        zzac.zzw(zze);
        zzac.zzdv(zze.zzaR);
        zzac.zzw(zze.zzbwh);
        zzKI();
        long currentTimeMillis = zznq().currentTimeMillis();
        if (zze.zzbwh.longValue() < currentTimeMillis - zzJv().zzKn() || zze.zzbwh.longValue() > zzJv().zzKn() + currentTimeMillis) {
            zzJt().zzLc().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzati.zzfI(zze.zzaR), Long.valueOf(currentTimeMillis), zze.zzbwh);
        }
        try {
            byte[] bArr = new byte[zze.zzacZ()];
            zzbum zzae = zzbum.zzae(bArr);
            zze.zza(zzae);
            zzae.zzacM();
            byte[] zzk = zzJp().zzk(bArr);
            zzJt().zzLg().zzj("Saving bundle, size", Integer.valueOf(zzk.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zze.zzaR);
            contentValues.put("bundle_end_timestamp", zze.zzbwh);
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_DATA, zzk);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) == -1) {
                    zzJt().zzLa().zzj("Failed to insert bundle (got -1). appId", zzati.zzfI(zze.zzaR));
                }
            } catch (SQLiteException e) {
                zzJt().zzLa().zze("Error storing bundle. appId", zzati.zzfI(zze.zzaR), e);
            }
        } catch (IOException e2) {
            zzJt().zzLa().zze("Data loss. Failed to serialize bundle. appId", zzati.zzfI(zze.zzaR), e2);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(String str, int i, zzauh.zzf zzf) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzac.zzw(zzf);
        try {
            byte[] bArr = new byte[zzf.zzacZ()];
            zzbum zzae = zzbum.zzae(bArr);
            zzf.zza(zzae);
            zzae.zzacM();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("current_results", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                    zzJt().zzLa().zzj("Failed to insert filter results (got -1). appId", zzati.zzfI(str));
                }
            } catch (SQLiteException e) {
                zzJt().zzLa().zze("Error storing filter results. appId", zzati.zzfI(str), e);
            }
        } catch (IOException e2) {
            zzJt().zzLa().zze("Configuration loss. Failed to serialize filter results. appId", zzati.zzfI(str), e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:98:0x0248  */
    public void zza(String str, long j, long j2, zzb zzb2) {
        String str2;
        Cursor cursor;
        String str3;
        String[] strArr;
        zzac.zzw(zzb2);
        zzmq();
        zznA();
        Cursor cursor2 = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (TextUtils.isEmpty(str)) {
                String[] strArr2 = j2 != -1 ? new String[]{String.valueOf(j2), String.valueOf(j)} : new String[]{String.valueOf(j)};
                String str4 = j2 != -1 ? "rowid <= ? and " : "";
                Cursor rawQuery = writableDatabase.rawQuery(new StringBuilder(String.valueOf(str4).length() + 148).append("select app_id, metadata_fingerprint from raw_events where ").append(str4).append("app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;").toString(), strArr2);
                if (rawQuery.moveToFirst()) {
                    str = rawQuery.getString(0);
                    String string = rawQuery.getString(1);
                    rawQuery.close();
                    str2 = string;
                    cursor = rawQuery;
                } else if (rawQuery != null) {
                    rawQuery.close();
                    return;
                } else {
                    return;
                }
            } else {
                String[] strArr3 = j2 != -1 ? new String[]{str, String.valueOf(j2)} : new String[]{str};
                String str5 = j2 != -1 ? " and rowid <= ?" : "";
                Cursor rawQuery2 = writableDatabase.rawQuery(new StringBuilder(String.valueOf(str5).length() + 84).append("select metadata_fingerprint from raw_events where app_id = ?").append(str5).append(" order by rowid limit 1;").toString(), strArr3);
                if (rawQuery2.moveToFirst()) {
                    String string2 = rawQuery2.getString(0);
                    rawQuery2.close();
                    str2 = string2;
                    cursor = rawQuery2;
                } else if (rawQuery2 != null) {
                    rawQuery2.close();
                    return;
                } else {
                    return;
                }
            }
            try {
                cursor = writableDatabase.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str, str2}, null, null, "rowid", "2");
                if (!cursor.moveToFirst()) {
                    zzJt().zzLa().zzj("Raw event metadata record is missing. appId", zzati.zzfI(str));
                    if (cursor != null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
                zzbul zzad = zzbul.zzad(cursor.getBlob(0));
                zzauh.zze zze = new zzauh.zze();
                try {
                    zze.zzb(zzad);
                    if (cursor.moveToNext()) {
                        zzJt().zzLc().zzj("Get multiple raw event metadata records, expected one. appId", zzati.zzfI(str));
                    }
                    cursor.close();
                    zzb2.zzb(zze);
                    if (j2 != -1) {
                        str3 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                        strArr = new String[]{str, str2, String.valueOf(j2)};
                    } else {
                        str3 = "app_id = ? and metadata_fingerprint = ?";
                        strArr = new String[]{str, str2};
                    }
                    Cursor query = writableDatabase.query("raw_events", new String[]{"rowid", "name", "timestamp", ShareConstants.WEB_DIALOG_PARAM_DATA}, str3, strArr, null, null, "rowid", null);
                    if (!query.moveToFirst()) {
                        zzJt().zzLc().zzj("Raw event data disappeared while in transaction. appId", zzati.zzfI(str));
                        if (query != null) {
                            query.close();
                            return;
                        }
                        return;
                    }
                    do {
                        long j3 = query.getLong(0);
                        zzbul zzad2 = zzbul.zzad(query.getBlob(3));
                        zzauh.zzb zzb3 = new zzauh.zzb();
                        try {
                            zzb3.zzb(zzad2);
                            zzb3.name = query.getString(1);
                            zzb3.zzbvW = Long.valueOf(query.getLong(2));
                            if (!zzb2.zza(j3, zzb3)) {
                                if (query != null) {
                                    query.close();
                                    return;
                                }
                                return;
                            }
                        } catch (IOException e) {
                            zzJt().zzLa().zze("Data loss. Failed to merge raw event. appId", zzati.zzfI(str), e);
                        }
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                        return;
                    }
                    return;
                } catch (IOException e2) {
                    zzJt().zzLa().zze("Data loss. Failed to merge raw event metadata. appId", zzati.zzfI(str), e2);
                    if (cursor != null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor2 = cursor;
            } catch (Throwable th) {
                th = th;
                cursor2 = cursor;
                if (cursor2 != null) {
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
        }
        try {
            zzJt().zzLa().zze("Data loss. Error selecting raw event. appId", zzati.zzfI(str), e);
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public boolean zza(zzaud zzaud) {
        zzac.zzw(zzaud);
        zzmq();
        zznA();
        if (zzR(zzaud.zzVQ, zzaud.mName) == null) {
            if (zzaue.zzfW(zzaud.mName)) {
                long zzb2 = zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzaud.zzVQ});
                zzJv().zzKe();
                if (zzb2 >= 25) {
                    return false;
                }
            } else {
                long zzb3 = zzb("select count(1) from user_attributes where app_id=?", new String[]{zzaud.zzVQ});
                zzJv().zzKf();
                if (zzb3 >= 50) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaud.zzVQ);
        contentValues.put("name", zzaud.mName);
        contentValues.put("set_timestamp", Long.valueOf(zzaud.zzbvd));
        zza(contentValues, FirebaseAnalytics.Param.VALUE, zzaud.zzYe);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzJt().zzLa().zzj("Failed to insert/update user property (got -1). appId", zzati.zzfI(zzaud.zzVQ));
            }
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Error storing user property. appId", zzati.zzfI(zzaud.zzVQ), e);
        }
        return true;
    }

    @WorkerThread
    public void zzal(long j) {
        zzmq();
        zznA();
        if (getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(j)}) != 1) {
            zzJt().zzLa().log("Deleted fewer rows from queue than expected");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r2v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARNING: Unknown variable types count: 1 */
    public String zzam(long j) {
        ? r2;
        Throwable th;
        Cursor cursor;
        String str = null;
        zzmq();
        zznA();
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(j)});
            try {
                if (!rawQuery.moveToFirst()) {
                    zzJt().zzLg().log("No expired configs for apps with pending events");
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else {
                    str = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                }
            } catch (SQLiteException e) {
                e = e;
                cursor = rawQuery;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = str;
        } catch (Throwable th2) {
            r2 = str;
            th = th2;
            if (r2 != 0) {
            }
            throw th;
        }
        return str;
        try {
            zzJt().zzLa().zzj("Error selecting expired configs", e);
            if (cursor != 0) {
                cursor.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            r2 = cursor;
            if (r2 != 0) {
                r2.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public Object zzb(Cursor cursor, int i) {
        int zza2 = zza(cursor, i);
        switch (zza2) {
            case 0:
                zzJt().zzLa().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzJt().zzLa().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzJt().zzLa().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(zza2));
                return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzb(String str, zzauf.zza[] zzaArr) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        zzac.zzw(zzaArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzfB(str);
            for (zzauf.zza zza2 : zzaArr) {
                zza(str, zza2);
            }
            ArrayList arrayList = new ArrayList();
            for (zzauf.zza zza3 : zzaArr) {
                arrayList.add(zza3.zzbvh);
            }
            zzc(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(String str, List<Integer> list) {
        zzac.zzdv(str);
        zznA();
        zzmq();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int zzfs = zzJv().zzfs(str);
            if (zzb2 <= ((long) zzfs)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Integer num = list.get(i);
                    if (num == null || !(num instanceof Integer)) {
                        return false;
                    }
                    arrayList.add(Integer.toString(num.intValue()));
                }
            }
            String valueOf = String.valueOf(TextUtils.join(",", arrayList));
            String sb = new StringBuilder(String.valueOf(valueOf).length() + 2).append("(").append(valueOf).append(")").toString();
            return writableDatabase.delete("audience_filter_values", new StringBuilder(String.valueOf(sb).length() + 140).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(sb).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(zzfs)}) > 0;
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Database error querying filters. appId", zzati.zzfI(str), e);
            return false;
        }
    }

    @WorkerThread
    public void zzd(String str, byte[] bArr) {
        zzac.zzdv(str);
        zzmq();
        zznA();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzJt().zzLa().zzj("Failed to update remote config (got 0). appId", zzati.zzfI(str));
            }
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Error storing remote config. appId", zzati.zzfI(str), e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0074  */
    @WorkerThread
    public byte[] zzfA(String str) {
        Cursor cursor;
        zzac.zzdv(str);
        zzmq();
        zznA();
        try {
            cursor = getWritableDatabase().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                byte[] blob = cursor.getBlob(0);
                if (cursor.moveToNext()) {
                    zzJt().zzLa().zzj("Got multiple records for app config, expected one. appId", zzati.zzfI(str));
                }
                if (cursor == null) {
                    return blob;
                }
                cursor.close();
                return blob;
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        try {
            zzJt().zzLa().zze("Error querying remote config. appId", zzati.zzfI(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzfB(String str) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("property_filters", "app_id=?", new String[]{str});
        writableDatabase.delete("event_filters", "app_id=?", new String[]{str});
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009b  */
    public Map<Integer, zzauh.zzf> zzfC(String str) {
        Cursor cursor;
        zznA();
        zzmq();
        zzac.zzdv(str);
        try {
            cursor = getWritableDatabase().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                ArrayMap arrayMap = new ArrayMap();
                do {
                    int i = cursor.getInt(0);
                    zzbul zzad = zzbul.zzad(cursor.getBlob(1));
                    zzauh.zzf zzf = new zzauh.zzf();
                    try {
                        zzf.zzb(zzad);
                        arrayMap.put(Integer.valueOf(i), zzf);
                    } catch (IOException e) {
                        zzJt().zzLa().zzd("Failed to merge filter results. appId, audienceId, error", zzati.zzfI(str), Integer.valueOf(i), e);
                    }
                } while (cursor.moveToNext());
                if (cursor == null) {
                    return arrayMap;
                }
                cursor.close();
                return arrayMap;
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        try {
            zzJt().zzLa().zze("Database error querying filter results. appId", zzati.zzfI(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzfD(String str) {
        zznA();
        zzmq();
        zzac.zzdv(str);
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("events", "app_id=?", strArr) + 0 + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("apps", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("event_filters", "app_id=?", strArr) + writableDatabase.delete("property_filters", "app_id=?", strArr);
            if (delete > 0) {
                zzJt().zzLg().zze("Deleted application data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Error deleting application data. appId, error", zzati.zzfI(str), e);
        }
    }

    @WorkerThread
    public long zzfE(String str) {
        zzac.zzdv(str);
        zzmq();
        zznA();
        return zzU(str, "first_open_count");
    }

    public void zzfF(String str) {
        try {
            getWritableDatabase().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{str, str});
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Failed to remove unused event metadata. appId", zzati.zzfI(str), e);
        }
    }

    public long zzfG(String str) {
        zzac.zzdv(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a6  */
    @WorkerThread
    public List<zzaud> zzfx(String str) {
        Cursor cursor;
        Cursor cursor2 = null;
        zzac.zzdv(str);
        zzmq();
        zznA();
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = getWritableDatabase().query("user_attributes", new String[]{"name", "set_timestamp", FirebaseAnalytics.Param.VALUE}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(zzJv().zzKf()));
            try {
                if (!query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                do {
                    String string = query.getString(0);
                    long j = query.getLong(1);
                    Object zzb2 = zzb(query, 2);
                    if (zzb2 == null) {
                        zzJt().zzLa().zzj("Read invalid user property value, ignoring it. appId", zzati.zzfI(str));
                    } else {
                        arrayList.add(new zzaud(str, string, j, zzb2));
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e) {
                e = e;
                cursor = query;
            } catch (Throwable th) {
                th = th;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
        try {
            zzJt().zzLa().zze("Error querying user properties. appId", zzati.zzfI(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x01d1  */
    @WorkerThread
    public zzasp zzfy(String str) {
        Cursor cursor;
        zzac.zzdv(str);
        zzmq();
        zznA();
        try {
            cursor = getWritableDatabase().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                }
                zzasp zzasp = new zzasp(this.zzbpw, str);
                zzasp.zzfh(cursor.getString(0));
                zzasp.zzfi(cursor.getString(1));
                zzasp.zzfj(cursor.getString(2));
                zzasp.zzac(cursor.getLong(3));
                zzasp.zzX(cursor.getLong(4));
                zzasp.zzY(cursor.getLong(5));
                zzasp.setAppVersion(cursor.getString(6));
                zzasp.zzfl(cursor.getString(7));
                zzasp.zzaa(cursor.getLong(8));
                zzasp.zzab(cursor.getLong(9));
                zzasp.setMeasurementEnabled((cursor.isNull(10) ? 1 : cursor.getInt(10)) != 0);
                zzasp.zzaf(cursor.getLong(11));
                zzasp.zzag(cursor.getLong(12));
                zzasp.zzah(cursor.getLong(13));
                zzasp.zzai(cursor.getLong(14));
                zzasp.zzad(cursor.getLong(15));
                zzasp.zzae(cursor.getLong(16));
                zzasp.zzZ(cursor.isNull(17) ? -2147483648L : (long) cursor.getInt(17));
                zzasp.zzfk(cursor.getString(18));
                zzasp.zzak(cursor.getLong(19));
                zzasp.zzaj(cursor.getLong(20));
                zzasp.zzfm(cursor.getString(21));
                zzasp.zzJw();
                if (cursor.moveToNext()) {
                    zzJt().zzLa().zzj("Got multiple records for app, expected one. appId", zzati.zzfI(str));
                }
                if (cursor == null) {
                    return zzasp;
                }
                cursor.close();
                return zzasp;
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        try {
            zzJt().zzLa().zze("Error querying app. appId", zzati.zzfI(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
            }
            throw th;
        }
    }

    public long zzfz(String str) {
        zzac.zzdv(str);
        zzmq();
        zznA();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(zzJv().zzfw(str))});
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Error deleting over the limit events. appId", zzati.zzfI(str), e);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e6  */
    @WorkerThread
    public List<Pair<zzauh.zze, Long>> zzn(String str, int i, int i2) {
        Cursor cursor;
        Cursor cursor2;
        List<Pair<zzauh.zze, Long>> emptyList;
        int i3;
        boolean z = true;
        zzmq();
        zznA();
        zzac.zzas(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        zzac.zzas(z);
        zzac.zzdv(str);
        try {
            cursor2 = getWritableDatabase().query("queue", new String[]{"rowid", ShareConstants.WEB_DIALOG_PARAM_DATA}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            try {
                if (!cursor2.moveToFirst()) {
                    emptyList = Collections.emptyList();
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                } else {
                    emptyList = new ArrayList<>();
                    int i4 = 0;
                    while (true) {
                        long j = cursor2.getLong(0);
                        try {
                            byte[] zzx = zzJp().zzx(cursor2.getBlob(1));
                            if (!emptyList.isEmpty() && zzx.length + i4 > i2) {
                                break;
                            }
                            zzbul zzad = zzbul.zzad(zzx);
                            zzauh.zze zze = new zzauh.zze();
                            try {
                                zze.zzb(zzad);
                                i3 = zzx.length + i4;
                                emptyList.add(Pair.create(zze, Long.valueOf(j)));
                            } catch (IOException e) {
                                zzJt().zzLa().zze("Failed to merge queued bundle. appId", zzati.zzfI(str), e);
                                i3 = i4;
                            }
                            if (!cursor2.moveToNext() || i3 > i2) {
                                break;
                            }
                            i4 = i3;
                        } catch (IOException e2) {
                            zzJt().zzLa().zze("Failed to unzip queued bundle. appId", zzati.zzfI(str), e2);
                            i3 = i4;
                        }
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor = cursor2;
            } catch (Throwable th) {
                th = th;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor2 = null;
            if (cursor2 != null) {
            }
            throw th;
        }
        return emptyList;
        try {
            zzJt().zzLa().zze("Error querying bundles. appId", zzati.zzfI(str), e);
            emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
        }
    }

    /* access modifiers changed from: package-private */
    public String zznV() {
        return zzJv().zzoV();
    }

    @WorkerThread
    public void zzz(String str, int i) {
        zzac.zzdv(str);
        zzmq();
        zznA();
        try {
            getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(i)});
        } catch (SQLiteException e) {
            zzJt().zzLa().zze("Error pruning currencies. appId", zzati.zzfI(str), e);
        }
    }
}
