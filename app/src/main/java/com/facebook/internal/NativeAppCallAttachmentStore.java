package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public final class NativeAppCallAttachmentStore {
    static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
    private static final String TAG = NativeAppCallAttachmentStore.class.getName();
    private static File attachmentsDirectory;

    public static final class Attachment {
        /* access modifiers changed from: private */
        public final String attachmentName;
        private final String attachmentUrl;
        /* access modifiers changed from: private */
        public Bitmap bitmap;
        /* access modifiers changed from: private */
        public final UUID callId;
        /* access modifiers changed from: private */
        public boolean isContentUri;
        /* access modifiers changed from: private */
        public Uri originalUri;
        /* access modifiers changed from: private */
        public boolean shouldCreateFile;

        private Attachment(UUID uuid, Bitmap bitmap2, Uri uri) {
            String attachmentUrl2;
            boolean z = true;
            this.callId = uuid;
            this.bitmap = bitmap2;
            this.originalUri = uri;
            if (uri != null) {
                String scheme = uri.getScheme();
                if ("content".equalsIgnoreCase(scheme)) {
                    this.isContentUri = true;
                    this.shouldCreateFile = (uri.getAuthority() == null || uri.getAuthority().startsWith(ShareConstants.WEB_DIALOG_PARAM_MEDIA)) ? false : z;
                } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                    this.shouldCreateFile = true;
                } else if (!Utility.isWebUri(uri)) {
                    throw new FacebookException("Unsupported scheme for media Uri : " + scheme);
                }
            } else if (bitmap2 != null) {
                this.shouldCreateFile = true;
            } else {
                throw new FacebookException("Cannot share media without a bitmap or Uri set");
            }
            this.attachmentName = !this.shouldCreateFile ? null : UUID.randomUUID().toString();
            if (!this.shouldCreateFile) {
                attachmentUrl2 = this.originalUri.toString();
            } else {
                attachmentUrl2 = FacebookContentProvider.getAttachmentUrl(FacebookSdk.getApplicationId(), uuid, this.attachmentName);
            }
            this.attachmentUrl = attachmentUrl2;
        }

        public String getAttachmentUrl() {
            return this.attachmentUrl;
        }
    }

    private NativeAppCallAttachmentStore() {
    }

    public static Attachment createAttachment(UUID uuid, Bitmap bitmap) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(bitmap, "attachmentBitmap");
        return new Attachment(uuid, bitmap, null);
    }

    public static Attachment createAttachment(UUID uuid, Uri uri) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(uri, "attachmentUri");
        return new Attachment(uuid, null, uri);
    }

    private static void processAttachmentBitmap(Bitmap bitmap, File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            Utility.closeQuietly(fileOutputStream);
        }
    }

    private static void processAttachmentFile(Uri uri, boolean z, File file) {
        InputStream openInputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if (!z) {
            try {
                openInputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                Utility.closeQuietly(fileOutputStream);
                throw th;
            }
        } else {
            openInputStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(uri);
        }
        Utility.copyAndCloseInputStream(openInputStream, fileOutputStream);
        Utility.closeQuietly(fileOutputStream);
    }

    public static void addAttachments(Collection<Attachment> collection) {
        if (collection != null && collection.size() != 0) {
            if (attachmentsDirectory == null) {
                cleanupAllAttachments();
            }
            ensureAttachmentsDirectoryExists();
            ArrayList<File> arrayList = new ArrayList<>();
            try {
                for (Attachment next : collection) {
                    if (next.shouldCreateFile) {
                        File attachmentFile = getAttachmentFile(next.callId, next.attachmentName, true);
                        arrayList.add(attachmentFile);
                        if (next.bitmap != null) {
                            processAttachmentBitmap(next.bitmap, attachmentFile);
                        } else if (next.originalUri != null) {
                            processAttachmentFile(next.originalUri, next.isContentUri, attachmentFile);
                        }
                    }
                }
            } catch (IOException e) {
                IOException iOException = e;
                Log.e(TAG, "Got unexpected exception:" + iOException);
                for (File delete : arrayList) {
                    try {
                        delete.delete();
                    } catch (Exception e2) {
                    }
                }
                throw new FacebookException((Throwable) iOException);
            }
        }
    }

    public static void cleanupAttachmentsForCall(UUID uuid) {
        File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, false);
        if (attachmentsDirectoryForCall != null) {
            Utility.deleteDirectory(attachmentsDirectoryForCall);
        }
    }

    public static File openAttachment(UUID uuid, String str) {
        if (Utility.isNullOrEmpty(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return getAttachmentFile(uuid, str, false);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    static synchronized File getAttachmentsDirectory() {
        File file;
        synchronized (NativeAppCallAttachmentStore.class) {
            if (attachmentsDirectory == null) {
                attachmentsDirectory = new File(FacebookSdk.getApplicationContext().getCacheDir(), ATTACHMENTS_DIR_NAME);
            }
            file = attachmentsDirectory;
        }
        return file;
    }

    static File ensureAttachmentsDirectoryExists() {
        File attachmentsDirectory2 = getAttachmentsDirectory();
        attachmentsDirectory2.mkdirs();
        return attachmentsDirectory2;
    }

    static File getAttachmentsDirectoryForCall(UUID uuid, boolean z) {
        if (attachmentsDirectory == null) {
            return null;
        }
        File file = new File(attachmentsDirectory, uuid.toString());
        if (!z || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    static File getAttachmentFile(UUID uuid, String str, boolean z) {
        File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, z);
        if (attachmentsDirectoryForCall == null) {
            return null;
        }
        try {
            return new File(attachmentsDirectoryForCall, URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void cleanupAllAttachments() {
        Utility.deleteDirectory(getAttachmentsDirectory());
    }
}
