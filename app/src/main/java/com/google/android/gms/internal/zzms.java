package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.util.zzo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@zzmb
public final class zzms extends zza {
    public static final Parcelable.Creator<zzms> CREATOR = new zzmt();
    final int mVersionCode;
    ParcelFileDescriptor zzSn;
    private Parcelable zzSo;
    private boolean zzSp;

    zzms(int i, ParcelFileDescriptor parcelFileDescriptor) {
        this.mVersionCode = i;
        this.zzSn = parcelFileDescriptor;
        this.zzSo = null;
        this.zzSp = true;
    }

    public zzms(SafeParcelable safeParcelable) {
        this.mVersionCode = 1;
        this.zzSn = null;
        this.zzSo = safeParcelable;
        this.zzSp = false;
    }

    /* JADX INFO: finally extract failed */
    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzSn == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzSo.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzSn = zzj(marshall);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }
        zzmt.zza(this, parcel, i);
    }

    /* JADX INFO: finally extract failed */
    public <T extends SafeParcelable> T zza(Parcelable.Creator<T> creator) {
        if (this.zzSp) {
            if (this.zzSn == null) {
                zzpe.m2432e("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzSn));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr, 0, bArr.length);
                zzo.zzb(dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    this.zzSo = (SafeParcelable) creator.createFromParcel(obtain);
                    obtain.recycle();
                    this.zzSp = false;
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th2) {
                zzo.zzb(dataInputStream);
                throw th2;
            }
        }
        return (SafeParcelable) this.zzSo;
    }

    /* access modifiers changed from: protected */
    public <T> ParcelFileDescriptor zzj(final byte[] bArr) {
        final ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = null;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new Runnable(this) {
                    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
                    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f  */
                    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
                    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
                    public void run() {
                        DataOutputStream dataOutputStream;
                        try {
                            dataOutputStream = new DataOutputStream(autoCloseOutputStream);
                            try {
                                dataOutputStream.writeInt(bArr.length);
                                dataOutputStream.write(bArr);
                                zzo.zzb(dataOutputStream);
                            } catch (IOException e) {
                                e = e;
                                try {
                                    zzpe.zzb("Error transporting the ad response", e);
                                    zzv.zzcN().zza((Throwable) e, "LargeParcelTeleporter.pipeData.1");
                                    if (dataOutputStream != null) {
                                        zzo.zzb(autoCloseOutputStream);
                                    } else {
                                        zzo.zzb(dataOutputStream);
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    if (dataOutputStream != null) {
                                        zzo.zzb(autoCloseOutputStream);
                                    } else {
                                        zzo.zzb(dataOutputStream);
                                    }
                                    throw th;
                                }
                            }
                        } catch (IOException e2) {
                            e = e2;
                            dataOutputStream = null;
                            zzpe.zzb("Error transporting the ad response", e);
                            zzv.zzcN().zza((Throwable) e, "LargeParcelTeleporter.pipeData.1");
                            if (dataOutputStream != null) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            dataOutputStream = null;
                            if (dataOutputStream != null) {
                            }
                            throw th;
                        }
                    }
                }).start();
                return createPipe[0];
            } catch (IOException e) {
                e = e;
            }
        } catch (IOException e2) {
            e = e2;
            autoCloseOutputStream = autoCloseOutputStream2;
            zzpe.zzb("Error transporting the ad response", e);
            zzv.zzcN().zza((Throwable) e, "LargeParcelTeleporter.pipeData.2");
            zzo.zzb(autoCloseOutputStream);
            return autoCloseOutputStream2;
        }
    }
}
