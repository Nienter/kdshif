package p018c.p019a.p025f;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import p033d.Okio;
import p033d.Sink;
import p033d.Source;

/* renamed from: c.a.f.a */
public interface FileSystem {

    /* renamed from: a */
    public static final FileSystem f759a = new FileSystem() {
        /* renamed from: a */
        public Source mo8805a(File file) {
            return Okio.m3595a(file);
        }

        /* renamed from: b */
        public Sink mo8807b(File file) {
            try {
                return Okio.m3599b(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return Okio.m3599b(file);
            }
        }

        /* renamed from: c */
        public Sink mo8808c(File file) {
            try {
                return Okio.m3602c(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return Okio.m3602c(file);
            }
        }

        /* renamed from: d */
        public void mo8809d(File file) {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        /* renamed from: e */
        public boolean mo8810e(File file) {
            return file.exists();
        }

        /* renamed from: f */
        public long mo8811f(File file) {
            return file.length();
        }

        /* renamed from: a */
        public void mo8806a(File file, File file2) {
            mo8809d(file2);
            if (!file.renameTo(file2)) {
                throw new IOException("failed to rename " + file + " to " + file2);
            }
        }

        /* renamed from: g */
        public void mo8812g(File file) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("not a readable directory: " + file);
            }
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    mo8812g(file2);
                }
                if (!file2.delete()) {
                    throw new IOException("failed to delete " + file2);
                }
            }
        }
    };

    /* renamed from: a */
    Source mo8805a(File file);

    /* renamed from: a */
    void mo8806a(File file, File file2);

    /* renamed from: b */
    Sink mo8807b(File file);

    /* renamed from: c */
    Sink mo8808c(File file);

    /* renamed from: d */
    void mo8809d(File file);

    /* renamed from: e */
    boolean mo8810e(File file);

    /* renamed from: f */
    long mo8811f(File file);

    /* renamed from: g */
    void mo8812g(File file);
}
