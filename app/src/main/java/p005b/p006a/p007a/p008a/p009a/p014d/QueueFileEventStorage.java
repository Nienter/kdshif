package p005b.p006a.p007a.p008a.p009a.p014d;

import android.content.Context;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.QueueFile;

/* renamed from: b.a.a.a.a.d.h */
public class QueueFileEventStorage implements EventsStorage {

    /* renamed from: a */
    private final Context f221a;

    /* renamed from: b */
    private final File f222b;

    /* renamed from: c */
    private final String f223c;

    /* renamed from: d */
    private final File f224d;

    /* renamed from: e */
    private QueueFile f225e = new QueueFile(this.f224d);

    /* renamed from: f */
    private File f226f;

    public QueueFileEventStorage(Context context, File file, String str, String str2) {
        this.f221a = context;
        this.f222b = file;
        this.f223c = str2;
        this.f224d = new File(this.f222b, str);
        m278e();
    }

    /* renamed from: e */
    private void m278e() {
        this.f226f = new File(this.f222b, this.f223c);
        if (!this.f226f.exists()) {
            this.f226f.mkdirs();
        }
    }

    /* renamed from: a */
    public void mo8408a(byte[] bArr) {
        this.f225e.mo8308a(bArr);
    }

    /* renamed from: a */
    public int mo8404a() {
        return this.f225e.mo8306a();
    }

    /* renamed from: a */
    public void mo8406a(String str) {
        this.f225e.close();
        m277a(this.f224d, new File(this.f226f, str));
        this.f225e = new QueueFile(this.f224d);
    }

    /* renamed from: a */
    private void m277a(File file, File file2) {
        FileInputStream fileInputStream;
        OutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                outputStream = mo8417a(file2);
                CommonUtils.m142a((InputStream) fileInputStream, outputStream, new byte[1024]);
                CommonUtils.m140a((Closeable) fileInputStream, "Failed to close file input stream");
                CommonUtils.m140a((Closeable) outputStream, "Failed to close output stream");
                file.delete();
            } catch (Throwable th) {
                th = th;
                CommonUtils.m140a((Closeable) fileInputStream, "Failed to close file input stream");
                CommonUtils.m140a((Closeable) outputStream, "Failed to close output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            CommonUtils.m140a((Closeable) fileInputStream, "Failed to close file input stream");
            CommonUtils.m140a((Closeable) outputStream, "Failed to close output stream");
            file.delete();
            throw th;
        }
    }

    /* renamed from: a */
    public OutputStream mo8417a(File file) {
        return new FileOutputStream(file);
    }

    /* renamed from: a */
    public List<File> mo8405a(int i) {
        ArrayList arrayList = new ArrayList();
        for (File add : this.f226f.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() >= i) {
                break;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo8407a(List<File> list) {
        for (File next : list) {
            CommonUtils.m137a(this.f221a, String.format("deleting sent analytics file %s", new Object[]{next.getName()}));
            next.delete();
        }
    }

    /* renamed from: c */
    public List<File> mo8411c() {
        return Arrays.asList(this.f226f.listFiles());
    }

    /* renamed from: d */
    public void mo8412d() {
        try {
            this.f225e.close();
        } catch (IOException e) {
        }
        this.f224d.delete();
    }

    /* renamed from: b */
    public boolean mo8410b() {
        return this.f225e.mo8311b();
    }

    /* renamed from: a */
    public boolean mo8409a(int i, int i2) {
        return this.f225e.mo8310a(i, i2);
    }
}
