package p005b.p006a.p007a.p008a.p009a.p014d;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import p005b.p006a.p007a.p008a.p009a.p011b.CommonUtils;
import p005b.p006a.p007a.p008a.p009a.p011b.CurrentTimeProvider;

/* renamed from: b.a.a.a.a.d.b */
public abstract class EventsFilesManager<T> {
    public static final int MAX_BYTE_SIZE_PER_FILE = 8000;
    public static final int MAX_FILES_IN_BATCH = 1;
    public static final int MAX_FILES_TO_KEEP = 100;
    public static final String ROLL_OVER_FILE_NAME_SEPARATOR = "_";
    protected final Context context;
    protected final CurrentTimeProvider currentTimeProvider;
    private final int defaultMaxFilesToKeep;
    protected final EventsStorage eventStorage;
    protected volatile long lastRollOverTime;
    protected final List<EventsStorageListener> rollOverListeners = new CopyOnWriteArrayList();
    protected final EventTransform<T> transform;

    /* renamed from: b.a.a.a.a.d.b$a */
    /* compiled from: EventsFilesManager */
    static class C0451a {

        /* renamed from: a */
        final File f219a;

        /* renamed from: b */
        final long f220b;

        public C0451a(File file, long j) {
            this.f219a = file;
            this.f220b = j;
        }
    }

    /* access modifiers changed from: protected */
    public abstract String generateUniqueRollOverFileName();

    public EventsFilesManager(Context context2, EventTransform<T> aVar, CurrentTimeProvider kVar, EventsStorage cVar, int i) {
        this.context = context2.getApplicationContext();
        this.transform = aVar;
        this.eventStorage = cVar;
        this.currentTimeProvider = kVar;
        this.lastRollOverTime = this.currentTimeProvider.mo8282a();
        this.defaultMaxFilesToKeep = i;
    }

    public void writeEvent(T t) {
        byte[] bytes = this.transform.toBytes(t);
        rollFileOverIfNeeded(bytes.length);
        this.eventStorage.mo8408a(bytes);
    }

    public void registerRollOverListener(EventsStorageListener dVar) {
        if (dVar != null) {
            this.rollOverListeners.add(dVar);
        }
    }

    public boolean rollFileOver() {
        boolean z = true;
        String str = null;
        if (!this.eventStorage.mo8410b()) {
            str = generateUniqueRollOverFileName();
            this.eventStorage.mo8406a(str);
            CommonUtils.m136a(this.context, 4, "Fabric", String.format(Locale.US, "generated new file %s", new Object[]{str}));
            this.lastRollOverTime = this.currentTimeProvider.mo8282a();
        } else {
            z = false;
        }
        triggerRollOverOnListeners(str);
        return z;
    }

    private void rollFileOverIfNeeded(int i) {
        if (!this.eventStorage.mo8409a(i, getMaxByteSizePerFile())) {
            CommonUtils.m136a(this.context, 4, "Fabric", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.eventStorage.mo8404a()), Integer.valueOf(i), Integer.valueOf(getMaxByteSizePerFile())}));
            rollFileOver();
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxFilesToKeep() {
        return this.defaultMaxFilesToKeep;
    }

    /* access modifiers changed from: protected */
    public int getMaxByteSizePerFile() {
        return MAX_BYTE_SIZE_PER_FILE;
    }

    public long getLastRollOverTime() {
        return this.lastRollOverTime;
    }

    private void triggerRollOverOnListeners(String str) {
        for (EventsStorageListener onRollOver : this.rollOverListeners) {
            try {
                onRollOver.onRollOver(str);
            } catch (Exception e) {
                CommonUtils.m138a(this.context, "One of the roll over listeners threw an exception", (Throwable) e);
            }
        }
    }

    public List<File> getBatchOfFilesToSend() {
        return this.eventStorage.mo8405a(1);
    }

    public void deleteSentFiles(List<File> list) {
        this.eventStorage.mo8407a(list);
    }

    public void deleteAllEventsFiles() {
        this.eventStorage.mo8407a(this.eventStorage.mo8411c());
        this.eventStorage.mo8412d();
    }

    public void deleteOldestInRollOverIfOverMax() {
        List<File> c = this.eventStorage.mo8411c();
        int maxFilesToKeep = getMaxFilesToKeep();
        if (c.size() > maxFilesToKeep) {
            int size = c.size() - maxFilesToKeep;
            CommonUtils.m137a(this.context, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c.size()), Integer.valueOf(maxFilesToKeep), Integer.valueOf(size)}));
            TreeSet treeSet = new TreeSet(new Comparator<C0451a>() {
                /* renamed from: a */
                public int compare(C0451a aVar, C0451a aVar2) {
                    return (int) (aVar.f220b - aVar2.f220b);
                }
            });
            for (File next : c) {
                treeSet.add(new C0451a(next, parseCreationTimestampFromFileName(next.getName())));
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((C0451a) it.next()).f219a);
                if (arrayList.size() == size) {
                    break;
                }
            }
            this.eventStorage.mo8407a((List<File>) arrayList);
        }
    }

    public long parseCreationTimestampFromFileName(String str) {
        long j = 0;
        String[] split = str.split(ROLL_OVER_FILE_NAME_SEPARATOR);
        if (split.length != 3) {
            return j;
        }
        try {
            return Long.valueOf(split[2]).longValue();
        } catch (NumberFormatException e) {
            return j;
        }
    }
}
