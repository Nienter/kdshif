package com.p028a.p029a.p030a;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* renamed from: com.a.a.a.ck */
public class UMCCPathDatabaseContext extends ContextWrapper {

    /* renamed from: a */
    private String f1507a;

    public UMCCPathDatabaseContext(Context context, String str) {
        super(context);
        this.f1507a = str;
    }

    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str).getAbsolutePath(), cursorFactory);
    }

    public File getDatabasePath(String str) {
        File file = new File(this.f1507a + str);
        if (!file.getParentFile().exists() && !file.getParentFile().isDirectory()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }
}
