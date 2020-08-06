package com.snaperfect.style.daguerre.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.p031a.Directories;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* renamed from: com.snaperfect.style.daguerre.utils.f */
public class FileUtils {
    /* renamed from: a */
    public static void m3091a(File file, File file2) {
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        FileChannel channel = fileInputStream.getChannel();
        channel.transferTo(0, channel.size(), fileOutputStream.getChannel());
        fileInputStream.close();
        fileOutputStream.close();
    }

    /* renamed from: a */
    public static Throwable m3090a(Context context, String str) {
        String a = Localize.m3098a("%s_%d.jpg", context.getString(R.string.app_name), Long.valueOf(System.currentTimeMillis()));
        String a2 = Localize.m3098a("%s/%s", Directories.m2433a(), a);
        File file = new File(a2);
        File file2 = new File(str);
        if (!file2.exists()) {
            return new IllegalStateException("Generated image not exist");
        }
        try {
            m3091a(file2, file);
            MediaStore.Images.Media.insertImage(context.getContentResolver(), a2, a, null);
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            return null;
        } catch (IOException | NullPointerException e) {
            return e;
        } catch (OutOfMemoryError e2) {
            System.gc();
            return e2;
        }
    }
}
