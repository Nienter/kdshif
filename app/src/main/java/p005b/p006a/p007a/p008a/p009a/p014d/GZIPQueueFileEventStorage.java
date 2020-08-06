package p005b.p006a.p007a.p008a.p009a.p014d;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: b.a.a.a.a.d.g */
public class GZIPQueueFileEventStorage extends QueueFileEventStorage {
    public GZIPQueueFileEventStorage(Context context, File file, String str, String str2) {
        super(context, file, str, str2);
    }

    /* renamed from: a */
    public OutputStream mo8417a(File file) {
        return new GZIPOutputStream(new FileOutputStream(file));
    }
}
