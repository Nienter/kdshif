package com.p028a.p029a.p030a;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.a.a.bk */
public class FieldMetaData implements Serializable {

    /* renamed from: d */
    private static Map<Class<? extends TBase>, Map<? extends TFieldIdEnum, FieldMetaData>> f1434d = new HashMap();

    /* renamed from: a */
    public final String f1435a;

    /* renamed from: b */
    public final byte f1436b;

    /* renamed from: c */
    public final FieldValueMetaData f1437c;

    public FieldMetaData(String str, byte b, FieldValueMetaData blVar) {
        this.f1435a = str;
        this.f1436b = b;
        this.f1437c = blVar;
    }

    /* renamed from: a */
    public static void m1940a(Class<? extends TBase> cls, Map<? extends TFieldIdEnum, FieldMetaData> map) {
        f1434d.put(cls, map);
    }

    /* renamed from: a */
    public static Map<? extends TFieldIdEnum, FieldMetaData> m1939a(Class<? extends TBase> cls) {
        if (!f1434d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return f1434d.get(cls);
    }
}
