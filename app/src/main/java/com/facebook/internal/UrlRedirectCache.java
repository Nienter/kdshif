package com.facebook.internal;

import android.net.Uri;
import com.facebook.LoggingBehavior;
import com.facebook.internal.FileLruCache;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

class UrlRedirectCache {
    private static final String REDIRECT_CONTENT_TAG = (TAG + "_Redirect");
    static final String TAG = UrlRedirectCache.class.getSimpleName();
    private static FileLruCache urlRedirectCache;

    UrlRedirectCache() {
    }

    static synchronized FileLruCache getCache() {
        FileLruCache fileLruCache;
        synchronized (UrlRedirectCache.class) {
            if (urlRedirectCache == null) {
                urlRedirectCache = new FileLruCache(TAG, new FileLruCache.Limits());
            }
            fileLruCache = urlRedirectCache;
        }
        return fileLruCache;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: java.io.InputStreamReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: java.io.InputStreamReader} */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    static Uri getRedirectedUri(Uri uri) {
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        Throwable th;
        Uri uri2 = null;
        boolean z = false;
        if (uri != null) {
            String uri3 = uri.toString();
            try {
                FileLruCache cache = getCache();
                String str = uri3;
                InputStreamReader inputStreamReader3 = uri2;
                while (true) {
                    try {
                        InputStream inputStream = cache.get(str, REDIRECT_CONTENT_TAG);
                        if (inputStream == null) {
                            break;
                        }
                        InputStreamReader inputStreamReader4 = new InputStreamReader(inputStream);
                        try {
                            char[] cArr = new char[128];
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                int read = inputStreamReader4.read(cArr, 0, cArr.length);
                                if (read <= 0) {
                                    break;
                                }
                                sb.append(cArr, 0, read);
                            }
                            Utility.closeQuietly(inputStreamReader4);
                            str = sb.toString();
                            inputStreamReader3 = inputStreamReader4;
                            z = true;
                        } catch (IOException e) {
                            inputStreamReader = inputStreamReader4;
                            Utility.closeQuietly(inputStreamReader);
                            return uri2;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStreamReader2 = inputStreamReader4;
                            Utility.closeQuietly(inputStreamReader2);
                            throw th;
                        }
                    } catch (IOException e2) {
                        inputStreamReader = inputStreamReader3;
                        Utility.closeQuietly(inputStreamReader);
                        return uri2;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStreamReader2 = inputStreamReader3;
                        Utility.closeQuietly(inputStreamReader2);
                        throw th;
                    }
                }
                if (z) {
                    uri2 = Uri.parse(str);
                    Utility.closeQuietly(inputStreamReader3);
                } else {
                    Utility.closeQuietly(inputStreamReader3);
                }
            } catch (IOException e3) {
                inputStreamReader = uri2;
                Utility.closeQuietly(inputStreamReader);
                return uri2;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                inputStreamReader2 = uri2;
                th = th5;
                Utility.closeQuietly(inputStreamReader2);
                throw th;
            }
        }
        return uri2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        r4 = r1;
        r1 = r0;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        com.facebook.internal.Utility.closeQuietly(r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:3:0x0006] */
    static void cacheUriRedirect(Uri uri, Uri uri2) {
        OutputStream openPutStream;
        if (uri != null && uri2 != null) {
            OutputStream outputStream = null;
            try {
                openPutStream = getCache().openPutStream(uri.toString(), REDIRECT_CONTENT_TAG);
                openPutStream.write(uri2.toString().getBytes());
                Utility.closeQuietly(openPutStream);
            } catch (IOException e) {
            } catch (Throwable th) {
                Throwable th2 = th;
                OutputStream outputStream2 = openPutStream;
                Throwable th3 = th2;
                Utility.closeQuietly(outputStream2);
                throw th3;
            }
        }
    }

    static void clearCache() {
        try {
            getCache().clearCache();
        } catch (IOException e) {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, "clearCache failed " + e.getMessage());
        }
    }
}
