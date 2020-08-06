package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.p004v7.widget.helper.ItemTouchHelper;
import com.facebook.C0750R;
import com.facebook.FacebookException;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.WorkQueue;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader {
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue = new WorkQueue(2);
    private static WorkQueue downloadQueue = new WorkQueue(8);
    private static Handler handler;
    private static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    private static class CacheReadWorkItem implements Runnable {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;

        CacheReadWorkItem(Context context2, RequestKey requestKey, boolean z) {
            this.context = context2;
            this.key = requestKey;
            this.allowCachedRedirects = z;
        }

        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }

    private static class DownloadImageWorkItem implements Runnable {
        private Context context;
        private RequestKey key;

        DownloadImageWorkItem(Context context2, RequestKey requestKey) {
            this.context = context2;
            this.key = requestKey;
        }

        public void run() {
            ImageDownloader.download(this.key, this.context);
        }
    }

    private static class DownloaderContext {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;

        private DownloaderContext() {
        }
    }

    private static class RequestKey {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        Uri uri;

        RequestKey(Uri uri2, Object obj) {
            this.uri = uri2;
            this.tag = obj;
        }

        public int hashCode() {
            return ((this.uri.hashCode() + 1073) * 37) + this.tag.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) obj;
            if (requestKey.uri == this.uri && requestKey.tag == this.tag) {
                return true;
            }
            return false;
        }
    }

    public static void downloadAsync(ImageRequest imageRequest) {
        if (imageRequest != null) {
            RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
            synchronized (pendingRequests) {
                DownloaderContext downloaderContext = pendingRequests.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = imageRequest;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                } else {
                    enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
                }
            }
        }
    }

    public static boolean cancelRequest(ImageRequest imageRequest) {
        boolean z;
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext == null) {
                z = false;
            } else if (downloaderContext.workItem.cancel()) {
                pendingRequests.remove(requestKey);
                z = true;
            } else {
                downloaderContext.isCancelled = true;
                z = true;
            }
        }
        return z;
    }

    public static void prioritizeRequest(ImageRequest imageRequest) {
        RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }

    private static void enqueueCacheRead(ImageRequest imageRequest, RequestKey requestKey, boolean z) {
        enqueueRequest(imageRequest, requestKey, cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, z));
    }

    private static void enqueueDownload(ImageRequest imageRequest, RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }

    private static void enqueueRequest(ImageRequest imageRequest, RequestKey requestKey, WorkQueue workQueue, Runnable runnable) {
        synchronized (pendingRequests) {
            DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = imageRequest;
            pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }

    private static void issueResponse(RequestKey requestKey, Exception exc, Bitmap bitmap, boolean z) {
        DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            final ImageRequest imageRequest = removePendingRequest.request;
            final ImageRequest.Callback callback = imageRequest.getCallback();
            if (callback != null) {
                final Exception exc2 = exc;
                final boolean z2 = z;
                final Bitmap bitmap2 = bitmap;
                getHandler().post(new Runnable() {
                    public void run() {
                        callback.onCompleted(new ImageResponse(imageRequest, exc2, z2, bitmap2));
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018  */
    public static void readFromCache(RequestKey requestKey, Context context, boolean z) {
        boolean z2;
        InputStream inputStream;
        boolean z3 = false;
        if (z) {
            Uri redirectedUri = UrlRedirectCache.getRedirectedUri(requestKey.uri);
            if (redirectedUri != null) {
                InputStream cachedImageStream = ImageResponseCache.getCachedImageStream(redirectedUri, context);
                if (cachedImageStream != null) {
                    z3 = true;
                }
                boolean z4 = z3;
                inputStream = cachedImageStream;
                z2 = z4;
                if (!z2) {
                    inputStream = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
                }
                if (inputStream == null) {
                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                    Utility.closeQuietly(inputStream);
                    issueResponse(requestKey, null, decodeStream, z2);
                    return;
                }
                DownloaderContext removePendingRequest = removePendingRequest(requestKey);
                if (removePendingRequest != null && !removePendingRequest.isCancelled) {
                    enqueueDownload(removePendingRequest.request, requestKey);
                    return;
                }
                return;
            }
        }
        z2 = false;
        inputStream = null;
        if (!z2) {
        }
        if (inputStream == null) {
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0080, code lost:
        com.facebook.internal.Utility.closeQuietly(r5);
        com.facebook.internal.Utility.disconnectQuietly(r0);
        r3 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ba, code lost:
        r10 = r1;
        r1 = r0;
        r0 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c2, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c3, code lost:
        r5 = null;
        r10 = r4;
        r4 = r0;
        r0 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b9 A[ExcHandler: all (r1v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:4:0x0015] */
    public static void download(RequestKey requestKey, Context context) {
        Closeable closeable;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        Exception exc;
        Object obj;
        InputStream interceptAndCacheImageStream;
        ? r3 = 0;
        boolean z = true;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(requestKey.uri.toString()).openConnection();
            try {
                httpURLConnection3.setInstanceFollowRedirects(false);
                switch (httpURLConnection3.getResponseCode()) {
                    case ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION:
                        interceptAndCacheImageStream = ImageResponseCache.interceptAndCacheImageStream(context, httpURLConnection3);
                        exc = null;
                        r3 = BitmapFactory.decodeStream(interceptAndCacheImageStream);
                        break;
                    case 301:
                    case 302:
                        String headerField = httpURLConnection3.getHeaderField("location");
                        if (Utility.isNullOrEmpty(headerField)) {
                            z = false;
                            exc = null;
                            interceptAndCacheImageStream = null;
                            break;
                        } else {
                            Uri parse = Uri.parse(headerField);
                            UrlRedirectCache.cacheUriRedirect(requestKey.uri, parse);
                            DownloaderContext removePendingRequest = removePendingRequest(requestKey);
                            if (removePendingRequest != null && !removePendingRequest.isCancelled) {
                                enqueueCacheRead(removePendingRequest.request, new RequestKey(parse, requestKey.tag), false);
                            }
                            z = false;
                            exc = null;
                            interceptAndCacheImageStream = null;
                            break;
                        }
                    default:
                        interceptAndCacheImageStream = httpURLConnection3.getErrorStream();
                        try {
                            StringBuilder sb = new StringBuilder();
                            if (interceptAndCacheImageStream != null) {
                                InputStreamReader inputStreamReader = new InputStreamReader(interceptAndCacheImageStream);
                                char[] cArr = new char[128];
                                while (true) {
                                    int read = inputStreamReader.read(cArr, 0, cArr.length);
                                    if (read > 0) {
                                        sb.append(cArr, 0, read);
                                    } else {
                                        Utility.closeQuietly(inputStreamReader);
                                    }
                                }
                            } else {
                                sb.append(context.getString(C0750R.string.com_facebook_image_download_unknown_error));
                            }
                            exc = new FacebookException(sb.toString());
                            break;
                        } catch (IOException e) {
                            IOException iOException = e;
                            httpURLConnection = httpURLConnection3;
                            e = iOException;
                            break;
                        } catch (Throwable th) {
                            r3 = obj;
                            Throwable th2 = th;
                            httpURLConnection2 = httpURLConnection3;
                            th = th2;
                            Utility.closeQuietly(r3);
                            Utility.disconnectQuietly(httpURLConnection2);
                            throw th;
                        }
                }
            } catch (IOException e2) {
                closeable = null;
                httpURLConnection = httpURLConnection3;
                e = e2;
                z = false;
            } catch (Throwable th3) {
            }
        } catch (IOException e3) {
            e = e3;
            closeable = null;
            httpURLConnection = null;
            Utility.closeQuietly(closeable);
            Utility.disconnectQuietly(httpURLConnection);
            exc = e;
            if (z) {
                issueResponse(requestKey, exc, r3, false);
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection2 = null;
            Utility.closeQuietly(r3);
            Utility.disconnectQuietly(httpURLConnection2);
            throw th;
        }
    }

    private static synchronized Handler getHandler() {
        Handler handler2;
        synchronized (ImageDownloader.class) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    private static DownloaderContext removePendingRequest(RequestKey requestKey) {
        DownloaderContext remove;
        synchronized (pendingRequests) {
            remove = pendingRequests.remove(requestKey);
        }
        return remove;
    }
}
