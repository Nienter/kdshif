package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzbii {
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri zzbTL = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzbTM = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzbTN = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    /* access modifiers changed from: private */
    public static final AtomicBoolean zzbTO = new AtomicBoolean();
    static HashMap<String, String> zzbTP;
    private static Object zzbTQ;
    private static boolean zzbTR;
    static String[] zzbTS = new String[0];

    public static long getLong(ContentResolver contentResolver, String str, long j) {
        String string = getString(contentResolver, str);
        if (string == null) {
            return j;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return j;
        }
    }

    @Deprecated
    public static String getString(ContentResolver contentResolver, String str) {
        return zza(contentResolver, str, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (zzbTR == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        if (zzbTP.isEmpty() == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        zzc(r9, zzbTS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        if (zzbTP.containsKey(r10) == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r0 = zzbTP.get(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        if (r0 == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        r11 = r0;
     */
    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzbii.class) {
            zza(contentResolver);
            Object obj = zzbTQ;
            if (zzbTP.containsKey(str)) {
                String str3 = zzbTP.get(str);
                if (str3 != null) {
                    str2 = str3;
                }
            } else {
                String[] strArr = zzbTS;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        Cursor query = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
                        if (query != null) {
                            try {
                                if (query.moveToFirst()) {
                                    String string = query.getString(1);
                                    if (string != null && string.equals(str2)) {
                                        string = str2;
                                    }
                                    zza(obj, str, string);
                                    if (string != null) {
                                        str2 = string;
                                    }
                                    if (query != null) {
                                        query.close();
                                    }
                                }
                            } catch (Throwable th) {
                                if (query != null) {
                                    query.close();
                                }
                                throw th;
                            }
                        }
                        zza(obj, str, (String) null);
                        if (query != null) {
                            query.close();
                        }
                    } else if (str.startsWith(strArr[i])) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        return str2;
    }

    public static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzbTL, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    treeMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzbTP == null) {
            zzbTO.set(false);
            zzbTP = new HashMap<>();
            zzbTQ = new Object();
            zzbTR = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new ContentObserver(null) {
                public void onChange(boolean z) {
                    zzbii.zzbTO.set(true);
                }
            });
        } else if (zzbTO.getAndSet(false)) {
            zzbTP.clear();
            zzbTQ = new Object();
            zzbTR = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzbii.class) {
            if (obj == zzbTQ) {
                zzbTP.put(str, str2);
            }
        }
    }

    public static void zzb(ContentResolver contentResolver, String... strArr) {
        if (strArr.length != 0) {
            synchronized (zzbii.class) {
                zza(contentResolver);
                String[] zzk = zzk(strArr);
                if (!zzbTR || zzbTP.isEmpty()) {
                    zzc(contentResolver, zzbTS);
                } else if (zzk.length != 0) {
                    zzc(contentResolver, zzk);
                }
            }
        }
    }

    private static void zzc(ContentResolver contentResolver, String[] strArr) {
        zzbTP.putAll(zza(contentResolver, strArr));
        zzbTR = true;
    }

    private static String[] zzk(String[] strArr) {
        HashSet hashSet = new HashSet((((zzbTS.length + strArr.length) * 4) / 3) + 1);
        hashSet.addAll(Arrays.asList(zzbTS));
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (hashSet.add(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return new String[0];
        }
        zzbTS = (String[]) hashSet.toArray(new String[hashSet.size()]);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
