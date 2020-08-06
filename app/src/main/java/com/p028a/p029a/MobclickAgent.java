package com.p028a.p029a;

import android.content.Context;
import com.p028a.p029a.p030a.MLog;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.a.b */
public class MobclickAgent {

    /* renamed from: a */
    private static final InternalAgent f1685a = new InternalAgent();

    /* renamed from: com.a.a.b$a */
    /* compiled from: MobclickAgent */
    public enum C0649a {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_UM_ANALYTICS_OEM(224),
        E_UM_GAME_OEM(225);
        

        /* renamed from: a */
        private int f1687a;

        private C0649a(int i) {
            this.f1687a = i;
        }

        public int toValue() {
            return this.f1687a;
        }
    }

    /* renamed from: a */
    public static void m2410a(Context context) {
        f1685a.mo9642b(context);
    }

    /* renamed from: b */
    public static void m2413b(Context context) {
        if (context == null) {
            MLog.m1844c("unexpected null context in onResume");
        } else {
            f1685a.mo9639a(context);
        }
    }

    /* renamed from: a */
    public static void m2411a(Context context, String str) {
        f1685a.mo9640a(context, str, null, -1, 1);
    }

    /* renamed from: a */
    public static void m2412a(Context context, String str, Map<String, String> map) {
        if (map == null) {
            MLog.m1844c("input map is null");
            return;
        }
        f1685a.mo9641a(context, str, new HashMap(map), -1);
    }
}
