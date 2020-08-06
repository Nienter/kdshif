package p005b.p006a.p007a.p008a.p009a.p016f;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import p005b.p006a.p007a.p008a.Kit;

/* renamed from: b.a.a.a.a.f.d */
public class PreferenceStoreImpl implements PreferenceStore {

    /* renamed from: a */
    private final SharedPreferences f265a;

    /* renamed from: b */
    private final String f266b;

    /* renamed from: c */
    private final Context f267c;

    public PreferenceStoreImpl(Context context, String str) {
        if (context == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f267c = context;
        this.f266b = str;
        this.f265a = this.f267c.getSharedPreferences(this.f266b, 0);
    }

    @Deprecated
    public PreferenceStoreImpl(Kit iVar) {
        this(iVar.getContext(), iVar.getClass().getName());
    }

    /* renamed from: a */
    public SharedPreferences mo8481a() {
        return this.f265a;
    }

    /* renamed from: b */
    public SharedPreferences.Editor mo8483b() {
        return this.f265a.edit();
    }

    @TargetApi(9)
    /* renamed from: a */
    public boolean mo8482a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
