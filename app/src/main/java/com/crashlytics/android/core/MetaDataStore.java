package com.crashlytics.android.core;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;

class MetaDataStore {
    private static final String KEYDATA_SUFFIX = "keys";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NAME = "userName";
    private static final String METADATA_EXT = ".meta";
    private static final String USERDATA_SUFFIX = "user";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final File filesDir;

    public MetaDataStore(File file) {
        this.filesDir = file;
    }

    public void writeUserData(String str, UserMetaData userMetaData) {
        BufferedWriter bufferedWriter;
        File userDataFileForSession = getUserDataFileForSession(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            String userDataToJson = userDataToJson(userMetaData);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userDataFileForSession), UTF_8));
            try {
                bufferedWriter.write(userDataToJson);
                bufferedWriter.flush();
                CommonUtils.m140a((Closeable) bufferedWriter, "Failed to close user metadata file.");
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error serializing user metadata.", e);
                    CommonUtils.m140a((Closeable) bufferedWriter, "Failed to close user metadata file.");
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter2 = bufferedWriter;
                    CommonUtils.m140a((Closeable) bufferedWriter2, "Failed to close user metadata file.");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            bufferedWriter = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error serializing user metadata.", e);
            CommonUtils.m140a((Closeable) bufferedWriter, "Failed to close user metadata file.");
        } catch (Throwable th2) {
            th = th2;
            CommonUtils.m140a((Closeable) bufferedWriter2, "Failed to close user metadata file.");
            throw th;
        }
    }

    public UserMetaData readUserData(String str) {
        FileInputStream fileInputStream;
        File userDataFileForSession = getUserDataFileForSession(str);
        if (!userDataFileForSession.exists()) {
            return UserMetaData.EMPTY;
        }
        try {
            fileInputStream = new FileInputStream(userDataFileForSession);
            try {
                UserMetaData jsonToUserData = jsonToUserData(CommonUtils.m129a((InputStream) fileInputStream));
                CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
                return jsonToUserData;
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
                    CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    return UserMetaData.EMPTY;
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
            CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
            return UserMetaData.EMPTY;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
            throw th;
        }
    }

    public void writeKeyData(String str, Map<String, String> map) {
        BufferedWriter bufferedWriter;
        File keysFileForSession = getKeysFileForSession(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            String keysDataToJson = keysDataToJson(map);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(keysFileForSession), UTF_8));
            try {
                bufferedWriter.write(keysDataToJson);
                bufferedWriter.flush();
                CommonUtils.m140a((Closeable) bufferedWriter, "Failed to close key/value metadata file.");
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error serializing key/value metadata.", e);
                    CommonUtils.m140a((Closeable) bufferedWriter, "Failed to close key/value metadata file.");
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter2 = bufferedWriter;
                    CommonUtils.m140a((Closeable) bufferedWriter2, "Failed to close key/value metadata file.");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            bufferedWriter = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error serializing key/value metadata.", e);
            CommonUtils.m140a((Closeable) bufferedWriter, "Failed to close key/value metadata file.");
        } catch (Throwable th2) {
            th = th2;
            CommonUtils.m140a((Closeable) bufferedWriter2, "Failed to close key/value metadata file.");
            throw th;
        }
    }

    public Map<String, String> readKeyData(String str) {
        FileInputStream fileInputStream;
        File keysFileForSession = getKeysFileForSession(str);
        if (!keysFileForSession.exists()) {
            return Collections.emptyMap();
        }
        try {
            fileInputStream = new FileInputStream(keysFileForSession);
            try {
                Map<String, String> jsonToKeysData = jsonToKeysData(CommonUtils.m129a((InputStream) fileInputStream));
                CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
                return jsonToKeysData;
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
                    CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    return Collections.emptyMap();
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error deserializing user metadata.", e);
            CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
            return Collections.emptyMap();
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            CommonUtils.m140a((Closeable) fileInputStream, "Failed to close user metadata file.");
            throw th;
        }
    }

    private File getUserDataFileForSession(String str) {
        return new File(this.filesDir, str + USERDATA_SUFFIX + METADATA_EXT);
    }

    private File getKeysFileForSession(String str) {
        return new File(this.filesDir, str + KEYDATA_SUFFIX + METADATA_EXT);
    }

    private static UserMetaData jsonToUserData(String str) {
        JSONObject jSONObject = new JSONObject(str);
        return new UserMetaData(valueOrNull(jSONObject, KEY_USER_ID), valueOrNull(jSONObject, KEY_USER_NAME), valueOrNull(jSONObject, KEY_USER_EMAIL));
    }

    private static String userDataToJson(final UserMetaData userMetaData) {
        return new JSONObject() {
            {
                put(MetaDataStore.KEY_USER_ID, userMetaData.f1714id);
                put(MetaDataStore.KEY_USER_NAME, userMetaData.name);
                put(MetaDataStore.KEY_USER_EMAIL, userMetaData.email);
            }
        }.toString();
    }

    private static Map<String, String> jsonToKeysData(String str) {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, valueOrNull(jSONObject, next));
        }
        return hashMap;
    }

    private static String keysDataToJson(Map<String, String> map) {
        return new JSONObject(map).toString();
    }

    private static String valueOrNull(JSONObject jSONObject, String str) {
        if (!jSONObject.isNull(str)) {
            return jSONObject.optString(str, null);
        }
        return null;
    }
}
