package com.p028a.p029a.p030a;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.a.a.a.br */
public class UMCCDBUtils {
    /* renamed from: a */
    public static String m2038a(Context context) {
        return "/data/data/" + context.getPackageName() + "/databases/cc/";
    }

    /* renamed from: a */
    public static String m2039a(List<String> list) {
        return TextUtils.join("!", list);
    }

    /* renamed from: a */
    public static JSONArray m2040a(String str) {
        String[] split = str.split("!");
        JSONArray jSONArray = new JSONArray();
        for (String put : split) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    /* renamed from: b */
    public static List<String> m2041b(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }
}
