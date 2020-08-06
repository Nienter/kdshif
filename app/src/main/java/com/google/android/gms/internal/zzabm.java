package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentActivity;
import android.support.p001v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzabm extends Fragment implements zzaax {
    private static WeakHashMap<FragmentActivity, WeakReference<zzabm>> zzaBt = new WeakHashMap<>();
    /* access modifiers changed from: private */
    public int zzJh = 0;
    private Map<String, zzaaw> zzaBu = new ArrayMap();
    /* access modifiers changed from: private */
    public Bundle zzaBv;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r0 != null) goto L_0x0012;
     */
    public static zzabm zza(FragmentActivity fragmentActivity) {
        zzabm zzabm;
        WeakReference weakReference = zzaBt.get(fragmentActivity);
        if (weakReference != null) {
            zzabm = (zzabm) weakReference.get();
        }
        try {
            zzabm = (zzabm) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (zzabm == null || zzabm.isRemoving()) {
                zzabm = new zzabm();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) zzabm, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzaBt.put(fragmentActivity, new WeakReference(zzabm));
            return zzabm;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e);
        }
    }

    private void zzb(final String str, @NonNull final zzaaw zzaaw) {
        if (this.zzJh > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (zzabm.this.zzJh >= 1) {
                        zzaaw.onCreate(zzabm.this.zzaBv != null ? zzabm.this.zzaBv.getBundle(str) : null);
                    }
                    if (zzabm.this.zzJh >= 2) {
                        zzaaw.onStart();
                    }
                    if (zzabm.this.zzJh >= 3) {
                        zzaaw.onStop();
                    }
                    if (zzabm.this.zzJh >= 4) {
                        zzaaw.onDestroy();
                    }
                }
            });
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (zzaaw dump : this.zzaBu.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (zzaaw onActivityResult : this.zzaBu.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzJh = 1;
        this.zzaBv = bundle;
        for (Map.Entry next : this.zzaBu.entrySet()) {
            ((zzaaw) next.getValue()).onCreate(bundle != null ? bundle.getBundle((String) next.getKey()) : null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.zzJh = 4;
        for (zzaaw onDestroy : this.zzaBu.values()) {
            onDestroy.onDestroy();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Map.Entry next : this.zzaBu.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((zzaaw) next.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) next.getKey(), bundle2);
            }
        }
    }

    public void onStart() {
        super.onStart();
        this.zzJh = 2;
        for (zzaaw onStart : this.zzaBu.values()) {
            onStart.onStart();
        }
    }

    public void onStop() {
        super.onStop();
        this.zzJh = 3;
        for (zzaaw onStop : this.zzaBu.values()) {
            onStop.onStop();
        }
    }

    public <T extends zzaaw> T zza(String str, Class<T> cls) {
        return (zzaaw) cls.cast(this.zzaBu.get(str));
    }

    public void zza(String str, @NonNull zzaaw zzaaw) {
        if (!this.zzaBu.containsKey(str)) {
            this.zzaBu.put(str, zzaaw);
            zzb(str, zzaaw);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
    }

    /* renamed from: zzws */
    public FragmentActivity zzwo() {
        return getActivity();
    }
}
