package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.p001v4.content.ContextCompat;
import android.support.p001v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzbqj;
import com.google.android.gms.internal.zzbqk;
import com.google.android.gms.internal.zzbql;
import com.google.android.gms.internal.zzbqm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final List<String> zzbUA = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zzbUB = Arrays.asList(new String[0]);
    private static final Set<String> zzbUC = Collections.emptySet();
    private static final List<String> zzbUy = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzbUz = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    static final Map<String, FirebaseApp> zzbha = new ArrayMap();
    /* access modifiers changed from: private */
    public static final Object zztU = new Object();
    private final String mName;
    private final FirebaseOptions zzbUD;
    private final AtomicBoolean zzbUE = new AtomicBoolean(false);
    private final AtomicBoolean zzbUF = new AtomicBoolean();
    private final List<zza> zzbUG = new CopyOnWriteArrayList();
    private final List<zzb> zzbUH = new CopyOnWriteArrayList();
    private final List<Object> zzbUI = new CopyOnWriteArrayList();
    private zzbql zzbUJ;
    private final Context zzvZ;

    public interface zza {
        void zzb(@NonNull zzbqm zzbqm);
    }

    public interface zzb {
        void zzaQ(boolean z);
    }

    @TargetApi(24)
    private static class zzc extends BroadcastReceiver {
        private static AtomicReference<zzc> zzbUK = new AtomicReference<>();
        private final Context zzvZ;

        public zzc(Context context) {
            this.zzvZ = context;
        }

        /* access modifiers changed from: private */
        public static void zzbR(Context context) {
            if (zzbUK.get() == null) {
                zzc zzc = new zzc(context);
                if (zzbUK.compareAndSet(null, zzc)) {
                    context.registerReceiver(zzc, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.zztU) {
                for (FirebaseApp zza : FirebaseApp.zzbha.values()) {
                    zza.zzTw();
                }
            }
            unregister();
        }

        public void unregister() {
            this.zzvZ.unregisterReceiver(this);
        }
    }

    protected FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zzvZ = (Context) zzac.zzw(context);
        this.mName = zzac.zzdv(str);
        this.zzbUD = (FirebaseOptions) zzac.zzw(firebaseOptions);
    }

    public static List<FirebaseApp> getApps(Context context) {
        ArrayList arrayList;
        zzbqk zzbZ = zzbqk.zzbZ(context);
        synchronized (zztU) {
            arrayList = new ArrayList(zzbha.values());
            Set<String> zzaaq = zzbqk.zzaap().zzaaq();
            zzaaq.removeAll(zzbha.keySet());
            for (String next : zzaaq) {
                zzbZ.zzjD(next);
                arrayList.add(initializeApp(context, null, next));
            }
        }
        return arrayList;
    }

    @Nullable
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (zztU) {
            firebaseApp = zzbha.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                String valueOf = String.valueOf(zzt.zzyK());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(valueOf).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        String concat;
        synchronized (zztU) {
            firebaseApp = zzbha.get(zzit(str));
            if (firebaseApp == null) {
                List<String> zzTv = zzTv();
                if (zzTv.isEmpty()) {
                    concat = "";
                } else {
                    String valueOf = String.valueOf(TextUtils.join(", ", zzTv));
                    concat = valueOf.length() != 0 ? "Available app names: ".concat(valueOf) : new String("Available app names: ");
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, concat}));
            }
        }
        return firebaseApp;
    }

    @Nullable
    public static FirebaseApp initializeApp(Context context) {
        FirebaseApp initializeApp;
        synchronized (zztU) {
            if (zzbha.containsKey(DEFAULT_APP_NAME)) {
                initializeApp = getInstance();
            } else {
                FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
                initializeApp = fromResource == null ? null : initializeApp(context, fromResource);
            }
        }
        return initializeApp;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzbqk zzbZ = zzbqk.zzbZ(context);
        zzbQ(context);
        String zzit = zzit(str);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (zztU) {
            zzac.zza(!zzbha.containsKey(zzit), (Object) new StringBuilder(String.valueOf(zzit).length() + 33).append("FirebaseApp name ").append(zzit).append(" already exists!").toString());
            zzac.zzb(context, (Object) "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, zzit, firebaseOptions);
            zzbha.put(zzit, firebaseApp);
        }
        zzbZ.zzg(firebaseApp);
        firebaseApp.zza(FirebaseApp.class, firebaseApp, zzbUy);
        if (firebaseApp.zzTt()) {
            firebaseApp.zza(FirebaseApp.class, firebaseApp, zzbUz);
            firebaseApp.zza(Context.class, firebaseApp.getApplicationContext(), zzbUA);
        }
        return firebaseApp;
    }

    private void zzTs() {
        zzac.zza(!this.zzbUF.get(), (Object) "FirebaseApp was deleted");
    }

    private static List<String> zzTv() {
        com.google.android.gms.common.util.zza zza2 = new com.google.android.gms.common.util.zza();
        synchronized (zztU) {
            for (FirebaseApp name : zzbha.values()) {
                zza2.add(name.getName());
            }
            zzbqk zzaap = zzbqk.zzaap();
            if (zzaap != null) {
                zza2.addAll(zzaap.zzaaq());
            }
        }
        ArrayList arrayList = new ArrayList(zza2);
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void zzTw() {
        zza(FirebaseApp.class, this, zzbUy);
        if (zzTt()) {
            zza(FirebaseApp.class, this, zzbUz);
            zza(Context.class, this.zzvZ, zzbUA);
        }
    }

    private <T> void zza(Class<T> cls, T t, Iterable<String> iterable) {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.zzvZ);
        if (isDeviceProtectedStorage) {
            zzc.zzbR(this.zzvZ);
        }
        for (String next : iterable) {
            if (isDeviceProtectedStorage) {
                try {
                    if (!zzbUB.contains(next)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (zzbUC.contains(next)) {
                        throw new IllegalStateException(String.valueOf(next).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(next).concat(" is not linked. Skipping initialization."));
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(String.valueOf(next).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (InvocationTargetException e3) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
                } catch (IllegalAccessException e4) {
                    String valueOf = String.valueOf(next);
                    Log.wtf("FirebaseApp", valueOf.length() != 0 ? "Failed to initialize ".concat(valueOf) : new String("Failed to initialize "), e4);
                }
            }
            Method method = Class.forName(next).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }

    public static void zzaQ(boolean z) {
        synchronized (zztU) {
            Iterator it = new ArrayList(zzbha.values()).iterator();
            while (it.hasNext()) {
                FirebaseApp firebaseApp = (FirebaseApp) it.next();
                if (firebaseApp.zzbUE.get()) {
                    firebaseApp.zzaR(z);
                }
            }
        }
    }

    private void zzaR(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (zzb zzaQ : this.zzbUH) {
            zzaQ.zzaQ(z);
        }
    }

    @TargetApi(14)
    private static void zzbQ(Context context) {
        if (zzs.zzyA() && (context.getApplicationContext() instanceof Application)) {
            zzbqj.zza((Application) context.getApplicationContext());
        }
    }

    private static String zzit(@NonNull String str) {
        return str.trim();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseApp)) {
            return false;
        }
        return this.mName.equals(((FirebaseApp) obj).getName());
    }

    @NonNull
    public Context getApplicationContext() {
        zzTs();
        return this.zzvZ;
    }

    @NonNull
    public String getName() {
        zzTs();
        return this.mName;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zzTs();
        return this.zzbUD;
    }

    public Task<GetTokenResult> getToken(boolean z) {
        zzTs();
        return this.zzbUJ == null ? Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.")) : this.zzbUJ.zzaS(z);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        zzTs();
        if (this.zzbUE.compareAndSet(!z, z)) {
            boolean zzaao = zzbqj.zzaan().zzaao();
            if (z && zzaao) {
                zzaR(true);
            } else if (!z && zzaao) {
                zzaR(false);
            }
        }
    }

    public String toString() {
        return zzaa.zzv(this).zzg("name", this.mName).zzg("options", this.zzbUD).toString();
    }

    public boolean zzTt() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    public String zzTu() {
        String valueOf = String.valueOf(com.google.android.gms.common.util.zzc.zzs(getName().getBytes()));
        String valueOf2 = String.valueOf(com.google.android.gms.common.util.zzc.zzs(getOptions().getApplicationId().getBytes()));
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("+").append(valueOf2).toString();
    }

    public void zza(@NonNull zzbql zzbql) {
        this.zzbUJ = (zzbql) zzac.zzw(zzbql);
    }

    @UiThread
    public void zza(@NonNull zzbqm zzbqm) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (zza zzb2 : this.zzbUG) {
            zzb2.zzb(zzbqm);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    public void zza(@NonNull zza zza2) {
        zzTs();
        zzac.zzw(zza2);
        this.zzbUG.add(zza2);
    }

    public void zza(zzb zzb2) {
        zzTs();
        if (this.zzbUE.get() && zzbqj.zzaan().zzaao()) {
            zzb2.zzaQ(true);
        }
        this.zzbUH.add(zzb2);
    }
}
