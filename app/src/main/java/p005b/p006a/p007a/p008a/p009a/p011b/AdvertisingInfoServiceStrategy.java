package p005b.p006a.p007a.p008a.p009a.p011b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import p005b.p006a.p007a.p008a.Fabric;

/* renamed from: b.a.a.a.a.b.e */
class AdvertisingInfoServiceStrategy implements AdvertisingInfoStrategy {

    /* renamed from: a */
    private final Context f115a;

    /* renamed from: b.a.a.a.a.b.e$a */
    /* compiled from: AdvertisingInfoServiceStrategy */
    private static final class C0421a implements ServiceConnection {

        /* renamed from: a */
        private boolean f116a;

        /* renamed from: b */
        private final LinkedBlockingQueue<IBinder> f117b;

        private C0421a() {
            this.f116a = false;
            this.f117b = new LinkedBlockingQueue<>(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f117b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f117b.clear();
        }

        /* renamed from: a */
        public IBinder mo8265a() {
            if (this.f116a) {
                Fabric.m456h().mo8515e("Fabric", "getBinder already called");
            }
            this.f116a = true;
            try {
                return this.f117b.poll(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return null;
            }
        }
    }

    /* renamed from: b.a.a.a.a.b.e$b */
    /* compiled from: AdvertisingInfoServiceStrategy */
    private static final class C0422b implements IInterface {

        /* renamed from: a */
        private final IBinder f118a;

        public C0422b(IBinder iBinder) {
            this.f118a = iBinder;
        }

        public IBinder asBinder() {
            return this.f118a;
        }

        /* renamed from: a */
        public String mo8268a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            String str = null;
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f118a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Exception e) {
                Fabric.m456h().mo8506a("Fabric", "Could not get parcel from Google Play Service to capture AdvertisingId");
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return str;
        }

        /* JADX INFO: finally extract failed */
        /* renamed from: b */
        public boolean mo8270b() {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.f118a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Exception e) {
                Fabric.m456h().mo8506a("Fabric", "Could not get parcel from Google Play Service to capture Advertising limitAdTracking");
                obtain2.recycle();
                obtain.recycle();
                return false;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }
    }

    public AdvertisingInfoServiceStrategy(Context context) {
        this.f115a = context.getApplicationContext();
    }

    /* renamed from: a */
    public AdvertisingInfo mo8263a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Fabric.m456h().mo8506a("Fabric", "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return null;
        }
        try {
            this.f115a.getPackageManager().getPackageInfo("com.android.vending", 0);
            C0421a aVar = new C0421a();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (this.f115a.bindService(intent, aVar, 1)) {
                    C0422b bVar = new C0422b(aVar.mo8265a());
                    AdvertisingInfo bVar2 = new AdvertisingInfo(bVar.mo8268a(), bVar.mo8270b());
                    this.f115a.unbindService(aVar);
                    return bVar2;
                }
                Fabric.m456h().mo8506a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId");
                return null;
            } catch (Exception e) {
                Fabric.m456h().mo8514d("Fabric", "Exception in binding to Google Play Service to capture AdvertisingId", e);
                this.f115a.unbindService(aVar);
                return null;
            } catch (Throwable th) {
                Fabric.m456h().mo8507a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId", th);
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Fabric.m456h().mo8506a("Fabric", "Unable to find Google Play Services package name");
            return null;
        } catch (Exception e3) {
            Fabric.m456h().mo8507a("Fabric", "Unable to determine if Google Play Services is available", (Throwable) e3);
            return null;
        }
    }
}
