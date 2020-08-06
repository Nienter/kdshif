package com.p028a.p029a.p030a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.a.a.a.aq */
public class AdvertisingId {

    /* renamed from: com.a.a.a.aq$a */
    /* compiled from: AdvertisingId */
    private static final class C0590a {

        /* renamed from: a */
        private final String f1367a;

        /* renamed from: b */
        private final boolean f1368b;

        C0590a(String str, boolean z) {
            this.f1367a = str;
            this.f1368b = z;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public String m1766a() {
            return this.f1367a;
        }
    }

    /* renamed from: com.a.a.a.aq$b */
    /* compiled from: AdvertisingId */
    private static final class C0591b implements ServiceConnection {

        /* renamed from: a */
        boolean f1369a;

        /* renamed from: b */
        private final LinkedBlockingQueue<IBinder> f1370b;

        private C0591b() {
            this.f1369a = false;
            this.f1370b = new LinkedBlockingQueue<>(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f1370b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        /* renamed from: a */
        public IBinder mo9364a() {
            if (this.f1369a) {
                throw new IllegalStateException();
            }
            this.f1369a = true;
            return this.f1370b.take();
        }
    }

    /* renamed from: com.a.a.a.aq$c */
    /* compiled from: AdvertisingId */
    private static final class C0592c implements IInterface {

        /* renamed from: a */
        private IBinder f1371a;

        public C0592c(IBinder iBinder) {
            this.f1371a = iBinder;
        }

        public IBinder asBinder() {
            return this.f1371a;
        }

        /* renamed from: a */
        public String mo9367a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f1371a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* renamed from: a */
        public boolean mo9368a(boolean z) {
            boolean z2 = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(z ? 1 : 0);
                this.f1371a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                return z2;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* renamed from: a */
    public static String m1764a(Context context) {
        try {
            C0590a b = m1765b(context);
            if (b == null) {
                return null;
            }
            return b.m1766a();
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    private static C0590a m1765b(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            C0591b bVar = new C0591b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (context.bindService(intent, bVar, 1)) {
                try {
                    C0592c cVar = new C0592c(bVar.mo9364a());
                    C0590a aVar = new C0590a(cVar.mo9367a(), cVar.mo9368a(true));
                    context.unbindService(bVar);
                    return aVar;
                } catch (Exception e) {
                    throw e;
                } catch (Throwable th) {
                    context.unbindService(bVar);
                    throw th;
                }
            } else {
                throw new IOException("Google Play connection failed");
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
