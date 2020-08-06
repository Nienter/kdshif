package com.crashlytics.android.core;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.AppMeasurementEventLogger;
import com.crashlytics.android.answers.EventLogger;
import com.crashlytics.android.core.CrashPromptDialog;
import com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler;
import com.crashlytics.android.core.LogFileManager;
import com.crashlytics.android.core.ReportUploader;
import com.crashlytics.android.core.internal.models.SessionEventData;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import p005b.p006a.p007a.p008a.Fabric;
import p005b.p006a.p007a.p008a.Kit;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.Crash;
import p005b.p006a.p007a.p008a.p009a.p011b.DeliveryMechanism;
import p005b.p006a.p007a.p008a.p009a.p011b.IdManager;
import p005b.p006a.p007a.p008a.p009a.p015e.HttpRequestFactory;
import p005b.p006a.p007a.p008a.p009a.p016f.FileStore;
import p005b.p006a.p007a.p008a.p009a.p017g.PromptSettingsData;
import p005b.p006a.p007a.p008a.p009a.p017g.SessionSettingsData;
import p005b.p006a.p007a.p008a.p009a.p017g.Settings;
import p005b.p006a.p007a.p008a.p009a.p017g.SettingsData;

class CrashlyticsController {
    private static final int ANALYZER_VERSION = 1;
    private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    static final String FATAL_SESSION_DIR = "fatal-sessions";
    static final String FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS = "clx";
    static final String FIREBASE_APPLICATION_EXCEPTION = "_ae";
    static final String FIREBASE_CRASH_TYPE = "fatal";
    private static final int FIREBASE_CRASH_TYPE_FATAL = 1;
    static final String FIREBASE_REALTIME = "_r";
    static final String FIREBASE_TIMESTAMP = "timestamp";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    private static final String[] INITIAL_SESSION_PART_TAGS = {SESSION_USER_TAG, SESSION_APP_TAG, SESSION_OS_TAG, SESSION_DEVICE_TAG};
    static final String INVALID_CLS_CACHE_DIR = "invalidClsFiles";
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    };
    static final int MAX_INVALID_SESSIONS = 4;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    static final int MAX_STACK_SIZE = 1024;
    static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    static final String SESSION_APP_TAG = "SessionApp";
    static final FilenameFilter SESSION_BEGIN_FILE_FILTER = new FileNameContainsFilter(SESSION_BEGIN_TAG) {
        public boolean accept(File file, String str) {
            return super.accept(file, str) && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    static final FileFilter SESSION_DIRECTORY_FILTER = new FileFilter() {
        public boolean accept(File file) {
            return file.isDirectory() && file.getName().length() == 35;
        }
    };
    static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.length() == ClsFileOutputStream.SESSION_FILE_EXTENSION.length() + 35 && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    /* access modifiers changed from: private */
    public static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_JSON_SUFFIX = ".json";
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_USER_TAG = "SessionUser";
    private static final boolean SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT = false;
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };
    /* access modifiers changed from: private */
    public final AppData appData;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    /* access modifiers changed from: private */
    public final CrashlyticsCore crashlyticsCore;
    private final DevicePowerStateListener devicePowerStateListener;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final FileStore fileStore;
    private final EventLogger firebaseAnalytics;
    private final boolean firebaseCrashlyticsEnabled;
    private final ReportUploader.HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    /* access modifiers changed from: private */
    public final LogFileManager logFileManager;
    private final PreferenceManager preferenceManager;
    private final ReportUploader.ReportFilesProvider reportFilesProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    /* access modifiers changed from: private */
    public final String unityVersion;

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, str) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    private interface CodedOutputStreamWriteAction {
        void writeTo(CodedOutputStream codedOutputStream);
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String str) {
            this.string = str;
        }

        public boolean accept(File file, String str) {
            return str.contains(this.string) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    private interface FileOutputStreamWriteAction {
        void writeTo(FileOutputStream fileOutputStream);
    }

    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, str) || str.contains(CrashlyticsController.SESSION_EVENT_MISSING_BINARY_IMGS_TAG);
        }
    }

    private static final class LogFileDirectoryProvider implements LogFileManager.DirectoryProvider {
        private static final String LOG_FILES_DIR = "log-files";
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore aVar) {
            this.rootFileStore = aVar;
        }

        public File getLogFileDir() {
            File file = new File(this.rootFileStore.mo8479a(), LOG_FILES_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
    }

    private static final class PrivacyDialogCheck implements ReportUploader.SendCheck {
        private final Kit kit;
        /* access modifiers changed from: private */
        public final PreferenceManager preferenceManager;
        private final PromptSettingsData promptData;

        public PrivacyDialogCheck(Kit iVar, PreferenceManager preferenceManager2, PromptSettingsData oVar) {
            this.kit = iVar;
            this.preferenceManager = preferenceManager2;
            this.promptData = oVar;
        }

        public boolean canSendReports() {
            Activity b = this.kit.getFabric().mo8521b();
            if (b == null || b.isFinishing()) {
                return true;
            }
            final CrashPromptDialog create = CrashPromptDialog.create(b, this.promptData, new CrashPromptDialog.AlwaysSendCallback() {
                public void sendUserReportsWithoutPrompting(boolean z) {
                    PrivacyDialogCheck.this.preferenceManager.setShouldAlwaysSendReports(z);
                }
            });
            b.runOnUiThread(new Runnable() {
                public void run() {
                    create.show();
                }
            });
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Waiting for user opt-in.");
            create.await();
            return create.getOptIn();
        }
    }

    private final class ReportUploaderFilesProvider implements ReportUploader.ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        public File[] getInvalidSessionFiles() {
            return CrashlyticsController.this.getInvalidFilesDir().listFiles();
        }
    }

    private final class ReportUploaderHandlingExceptionCheck implements ReportUploader.HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    private static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context2, Report report2, ReportUploader reportUploader2) {
            this.context = context2;
            this.report = report2;
            this.reportUploader = reportUploader2;
        }

        public void run() {
            if (CommonUtils.m165n(this.context)) {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Attempting to send crash report at time of crash...");
                this.reportUploader.forceUpload(this.report);
            }
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String str) {
            this.sessionId = str;
        }

        public boolean accept(File file, String str) {
            if (!str.equals(this.sessionId + ClsFileOutputStream.SESSION_FILE_EXTENSION) && str.contains(this.sessionId) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION)) {
                return true;
            }
            return false;
        }
    }

    CrashlyticsController(CrashlyticsCore crashlyticsCore2, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker, HttpRequestFactory eVar, IdManager pVar, PreferenceManager preferenceManager2, FileStore aVar, AppData appData2, UnityVersionProvider unityVersionProvider, boolean z) {
        this.crashlyticsCore = crashlyticsCore2;
        this.backgroundWorker = crashlyticsBackgroundWorker;
        this.httpRequestFactory = eVar;
        this.idManager = pVar;
        this.preferenceManager = preferenceManager2;
        this.fileStore = aVar;
        this.appData = appData2;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.firebaseCrashlyticsEnabled = z;
        Context context = crashlyticsCore2.getContext();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(aVar);
        this.logFileManager = new LogFileManager(context, this.logFileDirectoryProvider);
        this.reportFilesProvider = new ReportUploaderFilesProvider();
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        this.devicePowerStateListener = new DevicePowerStateListener(context);
        this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
        this.firebaseAnalytics = AppMeasurementEventLogger.getEventLogger(context);
    }

    /* access modifiers changed from: package-private */
    public void enableExceptionHandling(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        openSession();
        this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new CrashlyticsUncaughtExceptionHandler.CrashListener() {
            public void onUncaughtException(Thread thread, Throwable th) {
                CrashlyticsController.this.handleUncaughtException(thread, th);
            }
        }, uncaughtExceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
    }

    /* access modifiers changed from: package-private */
    public synchronized void handleUncaughtException(final Thread thread, final Throwable th) {
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
        this.devicePowerStateListener.dispose();
        final Date date = new Date();
        this.backgroundWorker.submitAndWait(new Callable<Void>() {
            public Void call() {
                CrashlyticsController.this.crashlyticsCore.createCrashMarker();
                CrashlyticsController.this.writeFatal(date, thread, th);
                SettingsData b = Settings.m419a().mo8500b();
                SessionSettingsData pVar = b != null ? b.f339b : null;
                CrashlyticsController.this.doCloseSessions(pVar);
                CrashlyticsController.this.doOpenSession();
                if (pVar != null) {
                    CrashlyticsController.this.trimSessionFiles(pVar.f331g);
                }
                if (!CrashlyticsController.this.shouldPromptUserBeforeSendingCrashReports(b)) {
                    CrashlyticsController.this.sendSessionReports(b);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void submitAllReports(float f, SettingsData tVar) {
        if (tVar == null) {
            Fabric.m456h().mo8513d(CrashlyticsCore.TAG, "Could not send reports. Settings are not available.");
            return;
        }
        new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(tVar.f338a.f296d), this.reportFilesProvider, this.handlingExceptionCheck).uploadReports(f, shouldPromptUserBeforeSendingCrashReports(tVar) ? new PrivacyDialogCheck(this.crashlyticsCore, this.preferenceManager, tVar.f340c) : new ReportUploader.AlwaysSendCheck());
    }

    /* access modifiers changed from: package-private */
    public void writeToLog(final long j, final String str) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.logFileManager.writeToLog(j, str);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void writeNonFatalException(final Thread thread, final Throwable th) {
        final Date date = new Date();
        this.backgroundWorker.submit((Runnable) new Runnable() {
            public void run() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.doWriteNonFatal(date, thread, th);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void cacheUserData(final String str, final String str2, final String str3) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(CrashlyticsController.this.getCurrentSessionId(), new UserMetaData(str, str2, str3));
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void cacheKeyData(final Map<String, String> map) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(CrashlyticsController.this.getCurrentSessionId(), map);
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void openSession() {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public String getCurrentSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length > 0) {
            return getSessionIdFromSessionFile(listSortedSessionBeginFiles[0]);
        }
        return null;
    }

    private String getPreviousSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length > 1) {
            return getSessionIdFromSessionFile(listSortedSessionBeginFiles[1]);
        }
        return null;
    }

    static String getSessionIdFromSessionFile(File file) {
        return file.getName().substring(0, 35);
    }

    /* access modifiers changed from: package-private */
    public boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean finalizeSessions(final SessionSettingsData pVar) {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            public Boolean call() {
                if (CrashlyticsController.this.isHandlingException()) {
                    Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Finalizing previously open sessions.");
                CrashlyticsController.this.doCloseSessions(pVar, true);
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    /* access modifiers changed from: private */
    public void doOpenSession() {
        Date date = new Date();
        String clsuuid = new CLSUUID(this.idManager).toString();
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Opening a new session with ID " + clsuuid);
        writeBeginSession(clsuuid, date);
        writeSessionApp(clsuuid);
        writeSessionOS(clsuuid);
        writeSessionDevice(clsuuid);
        this.logFileManager.setCurrentSession(clsuuid);
    }

    /* access modifiers changed from: package-private */
    public void doCloseSessions(SessionSettingsData pVar) {
        doCloseSessions(pVar, false);
    }

    /* access modifiers changed from: private */
    public void doCloseSessions(SessionSettingsData pVar, boolean z) {
        int i = z ? 1 : 0;
        trimOpenSessions(i + 8);
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length <= i) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "No open sessions to be closed.");
            return;
        }
        writeSessionUser(getSessionIdFromSessionFile(listSortedSessionBeginFiles[i]));
        if (pVar == null) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Unable to close session. Settings are not loaded.");
        } else {
            closeOpenSessions(listSortedSessionBeginFiles, i, pVar.f327c);
        }
    }

    private void closeOpenSessions(File[] fileArr, int i, int i2) {
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Closing open sessions.");
        while (i < fileArr.length) {
            File file = fileArr[i];
            String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Closing session: " + sessionIdFromSessionFile);
            writeSessionPartsToSessionFile(file, sessionIdFromSessionFile, i2);
            i++;
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream != null) {
            try {
                clsFileOutputStream.closeInProgressStream();
            } catch (IOException e) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private void recursiveDelete(Set<File> set) {
        for (File recursiveDelete : set) {
            recursiveDelete(recursiveDelete);
        }
    }

    private void recursiveDelete(File file) {
        if (file.isDirectory()) {
            for (File recursiveDelete : file.listFiles()) {
                recursiveDelete(recursiveDelete);
            }
        }
        file.delete();
    }

    private void deleteSessionPartFilesFor(String str) {
        for (File delete : listSessionPartFilesFor(str)) {
            delete.delete();
        }
    }

    private File[] listSessionPartFilesFor(String str) {
        return listFilesMatching(new SessionPartFileFilter(str));
    }

    /* access modifiers changed from: package-private */
    public File[] listCompleteSessionFiles() {
        LinkedList linkedList = new LinkedList();
        Collections.addAll(linkedList, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) linkedList.toArray(new File[linkedList.size()]);
    }

    /* access modifiers changed from: package-private */
    public File[] listSessionBeginFiles() {
        return listFilesMatching(SESSION_BEGIN_FILE_FILTER);
    }

    private File[] listSortedSessionBeginFiles() {
        File[] listSessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(listSessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return listSessionBeginFiles;
    }

    /* access modifiers changed from: private */
    public File[] listFilesMatching(FilenameFilter filenameFilter) {
        return listFilesMatching(getFilesDir(), filenameFilter);
    }

    private File[] listFilesMatching(File file, FilenameFilter filenameFilter) {
        return ensureFileArrayNotNull(file.listFiles(filenameFilter));
    }

    private File[] listFiles(File file) {
        return ensureFileArrayNotNull(file.listFiles());
    }

    private File[] ensureFileArrayNotNull(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    private void trimSessionEventFiles(String str, int i) {
        Utils.capFileCount(getFilesDir(), new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG), i, SMALLEST_FILE_NAME_FIRST);
    }

    /* access modifiers changed from: package-private */
    public void trimSessionFiles(int i) {
        int capFileCount = i - Utils.capFileCount(getFatalSessionFilesDir(), i, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, capFileCount - Utils.capFileCount(getNonFatalSessionFilesDir(), capFileCount, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int i) {
        HashSet hashSet = new HashSet();
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        int min = Math.min(i, listSortedSessionBeginFiles.length);
        for (int i2 = 0; i2 < min; i2++) {
            hashSet.add(getSessionIdFromSessionFile(listSortedSessionBeginFiles[i2]));
        }
        this.logFileManager.discardOldLogFiles(hashSet);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), hashSet);
    }

    private void retainSessions(File[] fileArr, Set<String> set) {
        for (File file : fileArr) {
            Matcher matcher = SESSION_FILE_PATTERN.matcher(file.getName());
            if (!matcher.matches()) {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Deleting unknown file: " + r3);
                file.delete();
            } else if (!set.contains(matcher.group(1))) {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Trimming session file: " + r3);
                file.delete();
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String str, File[] fileArr, int i) {
        if (fileArr.length <= i) {
            return fileArr;
        }
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(i)}));
        trimSessionEventFiles(str, i);
        return listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
    }

    /* access modifiers changed from: package-private */
    public void cleanInvalidTempFiles() {
        this.backgroundWorker.submit((Runnable) new Runnable() {
            public void run() {
                CrashlyticsController.this.doCleanInvalidTempFiles(CrashlyticsController.this.listFilesMatching(new InvalidPartFileFilter()));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void doCleanInvalidTempFiles(File[] fileArr) {
        File file;
        final HashSet hashSet = new HashSet();
        int length = fileArr.length;
        int i = 0;
        while (i < length) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Found invalid session part file: " + file);
            hashSet.add(getSessionIdFromSessionFile(file));
            i++;
        }
        if (!hashSet.isEmpty()) {
            File invalidFilesDir = getInvalidFilesDir();
            if (!invalidFilesDir.exists()) {
                invalidFilesDir.mkdir();
            }
            for (File file2 : listFilesMatching(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    if (str.length() < 35) {
                        return false;
                    }
                    return hashSet.contains(str.substring(0, 35));
                }
            })) {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Moving session file: " + file2);
                if (!file2.renameTo(new File(invalidFilesDir, file2.getName()))) {
                    Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Could not move session file. Deleting " + file2);
                    file2.delete();
                }
            }
            trimInvalidSessionFiles();
        }
    }

    private void trimInvalidSessionFiles() {
        File invalidFilesDir = getInvalidFilesDir();
        if (invalidFilesDir.exists()) {
            File[] listFilesMatching = listFilesMatching(invalidFilesDir, new InvalidPartFileFilter());
            Arrays.sort(listFilesMatching, Collections.reverseOrder());
            HashSet hashSet = new HashSet();
            for (int i = 0; i < listFilesMatching.length && hashSet.size() < 4; i++) {
                hashSet.add(getSessionIdFromSessionFile(listFilesMatching[i]));
            }
            retainSessions(listFiles(invalidFilesDir), hashSet);
        }
    }

    /* access modifiers changed from: private */
    public void writeFatal(Date date, Thread thread, Throwable th) {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId == null) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Tried to write a fatal exception while no session was open.", null);
                CommonUtils.m141a((Flushable) null, "Failed to flush to session begin file.");
                CommonUtils.m140a((Closeable) null, "Failed to close fatal exception file output stream.");
                return;
            }
            recordFatalExceptionAnswersEvent(currentSessionId, th.getClass().getName());
            recordFatalFirebaseEvent(date.getTime());
            ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + SESSION_FATAL_TAG);
            try {
                codedOutputStream = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream2);
                writeSessionEvent(codedOutputStream, date, thread, th, EVENT_TYPE_CRASH, true);
                CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.m140a((Closeable) clsFileOutputStream2, "Failed to close fatal exception file output stream.");
            } catch (Exception e) {
                e = e;
                clsFileOutputStream = clsFileOutputStream2;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", e);
                    CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
                    CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
                    CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                clsFileOutputStream = clsFileOutputStream2;
                CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            clsFileOutputStream = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", e);
            CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th4) {
            th = th4;
            clsFileOutputStream = null;
            CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void writeExternalCrashEvent(final SessionEventData sessionEventData) {
        this.backgroundWorker.submit(new Callable<Void>() {
            public Void call() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.doWriteExternalCrashEvent(sessionEventData);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public void doWriteExternalCrashEvent(SessionEventData sessionEventData) {
        ClsFileOutputStream clsFileOutputStream;
        boolean z = true;
        CodedOutputStream codedOutputStream = null;
        try {
            String previousSessionId = getPreviousSessionId();
            if (previousSessionId == null) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Tried to write a native crash while no session was open.", null);
                CommonUtils.m141a((Flushable) null, "Failed to flush to session begin file.");
                CommonUtils.m140a((Closeable) null, "Failed to close fatal exception file output stream.");
                return;
            }
            recordFatalExceptionAnswersEvent(previousSessionId, String.format(Locale.US, "<native-crash [%s (%s)]>", new Object[]{sessionEventData.signal.code, sessionEventData.signal.name}));
            if (sessionEventData.binaryImages == null || sessionEventData.binaryImages.length <= 0) {
                z = false;
            }
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), previousSessionId + (z ? SESSION_FATAL_TAG : SESSION_EVENT_MISSING_BINARY_IMGS_TAG));
            try {
                codedOutputStream = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                NativeCrashWriter.writeNativeCrash(sessionEventData, new LogFileManager(this.crashlyticsCore.getContext(), this.logFileDirectoryProvider, previousSessionId), new MetaDataStore(getFilesDir()).readKeyData(previousSessionId), codedOutputStream);
                CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
                CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", e);
                    CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
                    CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
                    CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            clsFileOutputStream = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", e);
            CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th2) {
            th = th2;
            clsFileOutputStream = null;
            CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session begin file.");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void doWriteNonFatal(Date date, Thread thread, Throwable th) {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        String currentSessionId = getCurrentSessionId();
        if (currentSessionId == null) {
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Tried to write a non-fatal exception while no session was open.", null);
            return;
        }
        recordLoggedExceptionAnswersEvent(currentSessionId, th.getClass().getName());
        try {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
            ClsFileOutputStream clsFileOutputStream2 = new ClsFileOutputStream(getFilesDir(), currentSessionId + SESSION_NON_FATAL_TAG + CommonUtils.m127a(this.eventCounter.getAndIncrement()));
            try {
                codedOutputStream = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream2);
                writeSessionEvent(codedOutputStream, date, thread, th, "error", false);
                CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to non-fatal file.");
                CommonUtils.m140a((Closeable) clsFileOutputStream2, "Failed to close non-fatal file output stream.");
            } catch (Exception e) {
                e = e;
                clsFileOutputStream = clsFileOutputStream2;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", e);
                    CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to non-fatal file.");
                    CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
                    trimSessionEventFiles(currentSessionId, 64);
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to non-fatal file.");
                    CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                clsFileOutputStream = clsFileOutputStream2;
                CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to non-fatal file.");
                CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            clsFileOutputStream = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", e);
            CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to non-fatal file.");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Throwable th4) {
            th = th4;
            clsFileOutputStream = null;
            CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to non-fatal file.");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
            throw th;
        }
        try {
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Exception e3) {
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "An error occurred when trimming non-fatal files.", e3);
        }
    }

    private void writeSessionPartFile(String str, String str2, CodedOutputStreamWriteAction codedOutputStreamWriteAction) {
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), str + str2);
            try {
                codedOutputStream = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                codedOutputStreamWriteAction.writeTo(codedOutputStream);
                CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session " + str2 + " file.");
                CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close session " + str2 + " file.");
            } catch (Throwable th) {
                th = th;
                CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session " + str2 + " file.");
                CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close session " + str2 + " file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            clsFileOutputStream = null;
            CommonUtils.m141a((Flushable) codedOutputStream, "Failed to flush to session " + str2 + " file.");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close session " + str2 + " file.");
            throw th;
        }
    }

    private void writeFile(String str, String str2, FileOutputStreamWriteAction fileOutputStreamWriteAction) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(getFilesDir(), str + str2));
            try {
                fileOutputStreamWriteAction.writeTo(fileOutputStream);
                CommonUtils.m140a((Closeable) fileOutputStream, "Failed to close " + str2 + " file.");
            } catch (Throwable th) {
                th = th;
                CommonUtils.m140a((Closeable) fileOutputStream, "Failed to close " + str2 + " file.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            CommonUtils.m140a((Closeable) fileOutputStream, "Failed to close " + str2 + " file.");
            throw th;
        }
    }

    private void writeBeginSession(String str, Date date) {
        final String format = String.format(Locale.US, GENERATOR_FORMAT, new Object[]{this.crashlyticsCore.getVersion()});
        final long time = date.getTime() / 1000;
        final String str2 = str;
        writeSessionPartFile(str, SESSION_BEGIN_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeBeginSession(codedOutputStream, str2, format, time);
            }
        });
        final String str3 = str;
        writeFile(str, "BeginSession.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("session_id", str3);
                        put("generator", format);
                        put("started_at_seconds", Long.valueOf(time));
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionApp(String str) {
        final String c = this.idManager.mo8292c();
        final String str2 = this.appData.versionCode;
        final String str3 = this.appData.versionName;
        final String b = this.idManager.mo8291b();
        final int id = DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId();
        writeSessionPartFile(str, SESSION_APP_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionApp(codedOutputStream, c, CrashlyticsController.this.appData.apiKey, str2, str3, b, id, CrashlyticsController.this.unityVersion);
            }
        });
        writeFile(str, "SessionApp.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("app_identifier", c);
                        put("api_key", CrashlyticsController.this.appData.apiKey);
                        put("version_code", str2);
                        put("version_name", str3);
                        put("install_uuid", b);
                        put("delivery_mechanism", Integer.valueOf(id));
                        put("unity_version", TextUtils.isEmpty(CrashlyticsController.this.unityVersion) ? "" : CrashlyticsController.this.unityVersion);
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionOS(String str) {
        final boolean g = CommonUtils.m158g(this.crashlyticsCore.getContext());
        writeSessionPartFile(str, SESSION_OS_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionOS(codedOutputStream, Build.VERSION.RELEASE, Build.VERSION.CODENAME, g);
            }
        });
        writeFile(str, "SessionOS.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, Build.VERSION.RELEASE);
                        put("build_version", Build.VERSION.CODENAME);
                        put("is_rooted", Boolean.valueOf(g));
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionDevice(String str) {
        Context context = this.crashlyticsCore.getContext();
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        final int a = CommonUtils.m121a();
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long b = CommonUtils.m144b();
        final long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        final boolean f = CommonUtils.m157f(context);
        final Map<IdManager.C0430a, String> h = this.idManager.mo8297h();
        final int h2 = CommonUtils.m159h(context);
        writeSessionPartFile(str, SESSION_DEVICE_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionDevice(codedOutputStream, a, Build.MODEL, availableProcessors, b, blockCount, f, h, h2, Build.MANUFACTURER, Build.PRODUCT);
            }
        });
        writeFile(str, "SessionDevice.json", new FileOutputStreamWriteAction() {
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("arch", Integer.valueOf(a));
                        put("build_model", Build.MODEL);
                        put("available_processors", Integer.valueOf(availableProcessors));
                        put("total_ram", Long.valueOf(b));
                        put("disk_space", Long.valueOf(blockCount));
                        put("is_emulator", Boolean.valueOf(f));
                        put("ids", h);
                        put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(h2));
                        put("build_manufacturer", Build.MANUFACTURER);
                        put("build_product", Build.PRODUCT);
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionUser(String str) {
        final UserMetaData userMetaData = getUserMetaData(str);
        writeSessionPartFile(str, SESSION_USER_TAG, new CodedOutputStreamWriteAction() {
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionUser(codedOutputStream, userMetaData.f1714id, userMetaData.name, userMetaData.email);
            }
        });
    }

    private void writeSessionEvent(CodedOutputStream codedOutputStream, Date date, Thread thread, Throwable th, String str, boolean z) {
        Thread[] threadArr;
        Map<String, String> treeMap;
        TrimmedThrowableData trimmedThrowableData = new TrimmedThrowableData(th, this.stackTraceTrimmingStrategy);
        Context context = this.crashlyticsCore.getContext();
        long time = date.getTime() / 1000;
        Float c = CommonUtils.m151c(context);
        int a = CommonUtils.m123a(context, this.devicePowerStateListener.isPowerConnected());
        boolean d = CommonUtils.m154d(context);
        int i = context.getResources().getConfiguration().orientation;
        long b = CommonUtils.m144b() - CommonUtils.m145b(context);
        long c2 = CommonUtils.m150c(Environment.getDataDirectory().getPath());
        ActivityManager.RunningAppProcessInfo a2 = CommonUtils.m125a(context.getPackageName(), context);
        LinkedList linkedList = new LinkedList();
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        String str2 = this.appData.buildId;
        String c3 = this.idManager.mo8292c();
        if (z) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            threadArr = new Thread[allStackTraces.size()];
            int i2 = 0;
            Iterator<Map.Entry<Thread, StackTraceElement[]>> it = allStackTraces.entrySet().iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                threadArr[i3] = (Thread) next.getKey();
                linkedList.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) next.getValue()));
                i2 = i3 + 1;
            }
        } else {
            threadArr = new Thread[0];
        }
        if (!CommonUtils.m143a(context, COLLECT_CUSTOM_KEYS, true)) {
            treeMap = new TreeMap<>();
        } else {
            Map<String, String> attributes = this.crashlyticsCore.getAttributes();
            treeMap = (attributes == null || attributes.size() <= 1) ? attributes : new TreeMap<>(attributes);
        }
        SessionProtobufHelper.writeSessionEvent(codedOutputStream, time, str, trimmedThrowableData, thread, stackTraceElementArr, threadArr, linkedList, treeMap, this.logFileManager, a2, i, c3, str2, c, a, d, b, c2);
    }

    private void writeSessionPartsToSessionFile(File file, String str, int i) {
        boolean z;
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Collecting session parts for ID " + str);
        File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + SESSION_FATAL_TAG));
        boolean z2 = listFilesMatching != null && listFilesMatching.length > 0;
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{str, Boolean.valueOf(z2)}));
        File[] listFilesMatching2 = listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
        if (listFilesMatching2 == null || listFilesMatching2.length <= 0) {
            z = false;
        } else {
            z = true;
        }
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{str, Boolean.valueOf(z)}));
        if (z2 || z) {
            synthesizeSessionFile(file, str, getTrimmedNonFatalFiles(str, listFilesMatching2, i), z2 ? listFilesMatching[0] : null);
        } else {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "No events present for session ID " + str);
        }
        Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Removing session part files for ID " + str);
        deleteSessionPartFilesFor(str);
    }

    private void synthesizeSessionFile(File file, String str, File[] fileArr, File file2) {
        ClsFileOutputStream clsFileOutputStream;
        boolean z = file2 != null;
        File fatalSessionFilesDir = z ? getFatalSessionFilesDir() : getNonFatalSessionFilesDir();
        if (!fatalSessionFilesDir.exists()) {
            fatalSessionFilesDir.mkdirs();
        }
        try {
            clsFileOutputStream = new ClsFileOutputStream(fatalSessionFilesDir, str);
            try {
                CodedOutputStream newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Collecting SessionStart data for session ID " + str);
                writeToCosFromFile(newInstance, file);
                newInstance.writeUInt64(4, new Date().getTime() / 1000);
                newInstance.writeBool(5, z);
                newInstance.writeUInt32(11, 1);
                newInstance.writeEnum(12, 3);
                writeInitialPartsTo(newInstance, str);
                writeNonFatalEventsTo(newInstance, fileArr, str);
                if (z) {
                    writeToCosFromFile(newInstance, file2);
                }
                CommonUtils.m141a((Flushable) newInstance, "Error flushing session file stream");
                CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close CLS file");
            } catch (Exception e) {
                e = e;
                try {
                    Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
                    CommonUtils.m141a((Flushable) null, "Error flushing session file stream");
                    closeWithoutRenamingOrLog(clsFileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.m141a((Flushable) null, "Error flushing session file stream");
                    CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close CLS file");
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            clsFileOutputStream = null;
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
            CommonUtils.m141a((Flushable) null, "Error flushing session file stream");
            closeWithoutRenamingOrLog(clsFileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            clsFileOutputStream = null;
            CommonUtils.m141a((Flushable) null, "Error flushing session file stream");
            CommonUtils.m140a((Closeable) clsFileOutputStream, "Failed to close CLS file");
            throw th;
        }
    }

    private static void writeNonFatalEventsTo(CodedOutputStream codedOutputStream, File[] fileArr, String str) {
        Arrays.sort(fileArr, CommonUtils.f119a);
        for (File file : fileArr) {
            try {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, file.getName()}));
                writeToCosFromFile(codedOutputStream, file);
            } catch (Exception e) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream codedOutputStream, String str) {
        for (String str2 : INITIAL_SESSION_PART_TAGS) {
            File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + str2 + ClsFileOutputStream.SESSION_FILE_EXTENSION));
            if (listFilesMatching.length == 0) {
                Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Can't find " + str2 + " data for session ID " + str, null);
            } else {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Collecting " + str2 + " data for session ID " + str);
                writeToCosFromFile(codedOutputStream, listFilesMatching[0]);
            }
        }
    }

    private static void writeToCosFromFile(CodedOutputStream codedOutputStream, File file) {
        FileInputStream fileInputStream;
        if (!file.exists()) {
            Fabric.m456h().mo8516e(CrashlyticsCore.TAG, "Tried to include a file that doesn't exist: " + file.getName(), null);
            return;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                copyToCodedOutputStream(fileInputStream, codedOutputStream, (int) file.length());
                CommonUtils.m140a((Closeable) fileInputStream, "Failed to close file input stream.");
            } catch (Throwable th) {
                th = th;
                CommonUtils.m140a((Closeable) fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            CommonUtils.m140a((Closeable) fileInputStream, "Failed to close file input stream.");
            throw th;
        }
    }

    private static void copyToCodedOutputStream(InputStream inputStream, CodedOutputStream codedOutputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < bArr.length) {
            int read = inputStream.read(bArr, i2, bArr.length - i2);
            if (read < 0) {
                break;
            }
            i2 += read;
        }
        codedOutputStream.writeRawBytes(bArr);
    }

    private UserMetaData getUserMetaData(String str) {
        if (isHandlingException()) {
            return new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail());
        }
        return new MetaDataStore(getFilesDir()).readUserData(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isHandlingException() {
        return this.crashHandler != null && this.crashHandler.isHandlingException();
    }

    /* access modifiers changed from: package-private */
    public File getFilesDir() {
        return this.fileStore.mo8479a();
    }

    /* access modifiers changed from: package-private */
    public File getFatalSessionFilesDir() {
        return new File(getFilesDir(), FATAL_SESSION_DIR);
    }

    /* access modifiers changed from: package-private */
    public File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), NONFATAL_SESSION_DIR);
    }

    /* access modifiers changed from: package-private */
    public File getInvalidFilesDir() {
        return new File(getFilesDir(), INVALID_CLS_CACHE_DIR);
    }

    /* access modifiers changed from: private */
    public boolean shouldPromptUserBeforeSendingCrashReports(SettingsData tVar) {
        if (tVar != null && tVar.f341d.f310a && !this.preferenceManager.shouldAlwaysSendReports()) {
            return true;
        }
        return false;
    }

    private CreateReportSpiCall getCreateReportSpiCall(String str) {
        return new DefaultCreateReportSpiCall(this.crashlyticsCore, CommonUtils.m147b(this.crashlyticsCore.getContext(), CRASHLYTICS_API_ENDPOINT), str, this.httpRequestFactory);
    }

    /* access modifiers changed from: private */
    public void sendSessionReports(SettingsData tVar) {
        if (tVar == null) {
            Fabric.m456h().mo8513d(CrashlyticsCore.TAG, "Cannot send reports. Settings are unavailable.");
            return;
        }
        Context context = this.crashlyticsCore.getContext();
        ReportUploader reportUploader = new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(tVar.f338a.f296d), this.reportFilesProvider, this.handlingExceptionCheck);
        for (File sessionReport : listCompleteSessionFiles()) {
            this.backgroundWorker.submit((Runnable) new SendReportRunnable(context, new SessionReport(sessionReport, SEND_AT_CRASHTIME_HEADER), reportUploader));
        }
    }

    private static void recordLoggedExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) Fabric.m447a(Answers.class);
        if (answers == null) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new Crash.C0426b(str, str2));
        }
    }

    private static void recordFatalExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) Fabric.m447a(Answers.class);
        if (answers == null) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new Crash.C0425a(str, str2));
        }
    }

    private void recordFatalFirebaseEvent(long j) {
        if (firebaseCrashExists()) {
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
        } else if (!this.firebaseCrashlyticsEnabled) {
        } else {
            if (this.firebaseAnalytics != null) {
                Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Logging Crashlytics event to Firebase");
                Bundle bundle = new Bundle();
                bundle.putInt(FIREBASE_REALTIME, 1);
                bundle.putInt(FIREBASE_CRASH_TYPE, 1);
                bundle.putLong(FIREBASE_TIMESTAMP, j);
                this.firebaseAnalytics.logEvent(FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS, FIREBASE_APPLICATION_EXCEPTION, bundle);
                return;
            }
            Fabric.m456h().mo8506a(CrashlyticsCore.TAG, "Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
        }
    }

    private boolean firebaseCrashExists() {
        try {
            Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
