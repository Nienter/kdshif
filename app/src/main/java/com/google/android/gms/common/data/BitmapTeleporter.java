package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapTeleporter extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zza();
    final int mVersionCode;
    ParcelFileDescriptor zzSn;
    private Bitmap zzaCg;
    private boolean zzaCh;
    private File zzaCi;
    final int zzanR;

    BitmapTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.mVersionCode = i;
        this.zzSn = parcelFileDescriptor;
        this.zzanR = i2;
        this.zzaCg = null;
        this.zzaCh = false;
    }

    public BitmapTeleporter(Bitmap bitmap) {
        this.mVersionCode = 1;
        this.zzSn = null;
        this.zzanR = 0;
        this.zzaCg = bitmap;
        this.zzaCh = true;
    }

    private void zza(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    private FileOutputStream zzwA() {
        if (this.zzaCi == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.zzaCi);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.zzSn = ParcelFileDescriptor.open(createTempFile, 268435456);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    public void release() {
        if (!this.zzaCh) {
            try {
                this.zzSn.close();
            } catch (IOException e) {
                Log.w("BitmapTeleporter", "Could not close PFD", e);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzSn == null) {
            Bitmap bitmap = this.zzaCg;
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(zzwA());
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                zza(dataOutputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                zza(dataOutputStream);
                throw th;
            }
        }
        zza.zza(this, parcel, i | 1);
        this.zzSn = null;
    }

    public void zzd(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zzaCi = file;
    }

    public Bitmap zzwz() {
        if (!this.zzaCh) {
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzSn));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                int readInt = dataInputStream.readInt();
                int readInt2 = dataInputStream.readInt();
                Bitmap.Config valueOf = Bitmap.Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(bArr);
                zza(dataInputStream);
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Bitmap createBitmap = Bitmap.createBitmap(readInt, readInt2, valueOf);
                createBitmap.copyPixelsFromBuffer(wrap);
                this.zzaCg = createBitmap;
                this.zzaCh = true;
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zza(dataInputStream);
                throw th;
            }
        }
        return this.zzaCg;
    }
}
