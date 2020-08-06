package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AccessTokenAppIdPair;
import com.facebook.appevents.AppEvent;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.Utility;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

class AppEventStore {
    private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    private static final String TAG = AppEventStore.class.getName();

    private static class MovedClassObjectInputStream extends ObjectInputStream {
        private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
        private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1";

        public MovedClassObjectInputStream(InputStream inputStream) {
            super(inputStream);
        }

        /* access modifiers changed from: protected */
        public ObjectStreamClass readClassDescriptor() {
            ObjectStreamClass readClassDescriptor = super.readClassDescriptor();
            if (readClassDescriptor.getName().equals(ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                return ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            }
            if (readClassDescriptor.getName().equals(APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME)) {
                return ObjectStreamClass.lookup(AppEvent.SerializationProxyV1.class);
            }
            return readClassDescriptor;
        }
    }

    AppEventStore() {
    }

    public static synchronized void persistEvents(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
        synchronized (AppEventStore.class) {
            AppEventUtility.assertIsNotMainThread();
            PersistedEvents readAndClearStore = readAndClearStore();
            if (readAndClearStore.containsKey(accessTokenAppIdPair)) {
                readAndClearStore.get(accessTokenAppIdPair).addAll(sessionEventsState.getEventsToPersist());
            } else {
                readAndClearStore.addEvents(accessTokenAppIdPair, sessionEventsState.getEventsToPersist());
            }
            saveEventsToDisk(readAndClearStore);
        }
    }

    public static synchronized void persistEvents(AppEventCollection appEventCollection) {
        synchronized (AppEventStore.class) {
            AppEventUtility.assertIsNotMainThread();
            PersistedEvents readAndClearStore = readAndClearStore();
            for (AccessTokenAppIdPair next : appEventCollection.keySet()) {
                readAndClearStore.addEvents(next, appEventCollection.get(next).getEventsToPersist());
            }
            saveEventsToDisk(readAndClearStore);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002f A[SYNTHETIC, Splitter:B:15:0x002f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0044=Splitter:B:27:0x0044, B:55:0x008a=Splitter:B:55:0x008a} */
    public static synchronized PersistedEvents readAndClearStore() {
        MovedClassObjectInputStream movedClassObjectInputStream;
        PersistedEvents persistedEvents;
        MovedClassObjectInputStream movedClassObjectInputStream2;
        MovedClassObjectInputStream movedClassObjectInputStream3 = null;
        synchronized (AppEventStore.class) {
            AppEventUtility.assertIsNotMainThread();
            Context applicationContext = FacebookSdk.getApplicationContext();
            try {
                movedClassObjectInputStream = new MovedClassObjectInputStream(new BufferedInputStream(applicationContext.openFileInput(PERSISTED_EVENTS_FILENAME)));
                try {
                    persistedEvents = (PersistedEvents) movedClassObjectInputStream.readObject();
                    Utility.closeQuietly(movedClassObjectInputStream);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e);
                    }
                } catch (FileNotFoundException e2) {
                    movedClassObjectInputStream2 = movedClassObjectInputStream;
                    Utility.closeQuietly(movedClassObjectInputStream2);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                        persistedEvents = null;
                    } catch (Exception e3) {
                        Log.w(TAG, "Got unexpected exception when removing events file: ", e3);
                        persistedEvents = null;
                    }
                    if (persistedEvents == null) {
                    }
                    return persistedEvents;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        Log.w(TAG, "Got unexpected exception while reading events: ", e);
                        Utility.closeQuietly(movedClassObjectInputStream);
                        try {
                            applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                            persistedEvents = null;
                        } catch (Exception e5) {
                            Log.w(TAG, "Got unexpected exception when removing events file: ", e5);
                            persistedEvents = null;
                        }
                        if (persistedEvents == null) {
                        }
                        return persistedEvents;
                    } catch (Throwable th) {
                        th = th;
                        movedClassObjectInputStream3 = movedClassObjectInputStream;
                        Utility.closeQuietly(movedClassObjectInputStream3);
                        try {
                            applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                        } catch (Exception e6) {
                            Log.w(TAG, "Got unexpected exception when removing events file: ", e6);
                        }
                        throw th;
                    }
                }
            } catch (FileNotFoundException e7) {
                movedClassObjectInputStream2 = null;
                Utility.closeQuietly(movedClassObjectInputStream2);
                applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                persistedEvents = null;
                if (persistedEvents == null) {
                }
                return persistedEvents;
            } catch (Exception e8) {
                e = e8;
                movedClassObjectInputStream = null;
                Log.w(TAG, "Got unexpected exception while reading events: ", e);
                Utility.closeQuietly(movedClassObjectInputStream);
                applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                persistedEvents = null;
                if (persistedEvents == null) {
                }
                return persistedEvents;
            } catch (Throwable th2) {
                th = th2;
                Utility.closeQuietly(movedClassObjectInputStream3);
                applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                throw th;
            }
            if (persistedEvents == null) {
                persistedEvents = new PersistedEvents();
            }
        }
        return persistedEvents;
    }

    private static void saveEventsToDisk(PersistedEvents persistedEvents) {
        ObjectOutputStream objectOutputStream;
        Context applicationContext = FacebookSdk.getApplicationContext();
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(applicationContext.openFileOutput(PERSISTED_EVENTS_FILENAME, 0)));
            try {
                objectOutputStream.writeObject(persistedEvents);
                Utility.closeQuietly(objectOutputStream);
            } catch (Exception e) {
                e = e;
                try {
                    Log.w(TAG, "Got unexpected exception while persisting events: ", e);
                    try {
                        applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
                    } catch (Exception e2) {
                    }
                    Utility.closeQuietly(objectOutputStream);
                } catch (Throwable th) {
                    th = th;
                    Utility.closeQuietly(objectOutputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            objectOutputStream = null;
            Log.w(TAG, "Got unexpected exception while persisting events: ", e);
            applicationContext.getFileStreamPath(PERSISTED_EVENTS_FILENAME).delete();
            Utility.closeQuietly(objectOutputStream);
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream = null;
            Utility.closeQuietly(objectOutputStream);
            throw th;
        }
    }
}
