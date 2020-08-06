package p005b.p006a.p007a.p008a.p009a.p016f;

import android.content.Context;
import java.io.File;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;

/* renamed from: b.a.a.a.a.f.b */
public class FileStoreImpl implements FileStore {

    /* renamed from: a */
    private final Context f262a;

    /* renamed from: b */
    private final String f263b;

    /* renamed from: c */
    private final String f264c;

    public FileStoreImpl(Kit iVar) {
        if (iVar.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f262a = iVar.getContext();
        this.f263b = iVar.getPath();
        this.f264c = "Android/" + this.f262a.getPackageName();
    }

    /* renamed from: a */
    public File mo8479a() {
        return mo8480a(this.f262a.getFilesDir());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public File mo8480a(File file) {
        if (file == null) {
            Fabric.m456h().mo8506a("Fabric", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            Fabric.m456h().mo8513d("Fabric", "Couldn't create file");
        }
        return null;
    }
}
