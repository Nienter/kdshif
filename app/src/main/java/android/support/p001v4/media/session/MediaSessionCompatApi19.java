package android.support.p001v4.media.session;

import android.annotation.TargetApi;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.p001v4.media.session.MediaSessionCompatApi18;

@TargetApi(19)
@RequiresApi(19)
/* renamed from: android.support.v4.media.session.MediaSessionCompatApi19 */
class MediaSessionCompatApi19 {
    private static final long ACTION_SET_RATING = 128;
    private static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    private static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    private static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi19$Callback */
    interface Callback extends MediaSessionCompatApi18.Callback {
        void onSetRating(Object obj);
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi19$OnMetadataUpdateListener */
    static class OnMetadataUpdateListener<T extends Callback> implements RemoteControlClient.OnMetadataUpdateListener {
        protected final T mCallback;

        public OnMetadataUpdateListener(T t) {
            this.mCallback = t;
        }

        public void onMetadataUpdate(int i, Object obj) {
            if (i == 268435457 && (obj instanceof Rating)) {
                this.mCallback.onSetRating(obj);
            }
        }
    }

    MediaSessionCompatApi19() {
    }

    public static void setTransportControlFlags(Object obj, long j) {
        ((RemoteControlClient) obj).setTransportControlFlags(getRccTransportControlFlagsFromActions(j));
    }

    public static Object createMetadataUpdateListener(Callback callback) {
        return new OnMetadataUpdateListener(callback);
    }

    public static void setMetadata(Object obj, Bundle bundle, long j) {
        RemoteControlClient.MetadataEditor editMetadata = ((RemoteControlClient) obj).editMetadata(true);
        MediaSessionCompatApi14.buildOldMetadata(bundle, editMetadata);
        addNewMetadata(bundle, editMetadata);
        if ((128 & j) != 0) {
            editMetadata.addEditableKey(268435457);
        }
        editMetadata.apply();
    }

    public static void setOnMetadataUpdateListener(Object obj, Object obj2) {
        ((RemoteControlClient) obj).setMetadataUpdateListener((RemoteControlClient.OnMetadataUpdateListener) obj2);
    }

    static int getRccTransportControlFlagsFromActions(long j) {
        int rccTransportControlFlagsFromActions = MediaSessionCompatApi18.getRccTransportControlFlagsFromActions(j);
        if ((128 & j) != 0) {
            return rccTransportControlFlagsFromActions | 512;
        }
        return rccTransportControlFlagsFromActions;
    }

    static void addNewMetadata(Bundle bundle, RemoteControlClient.MetadataEditor metadataEditor) {
        if (bundle != null) {
            if (bundle.containsKey("android.media.metadata.YEAR")) {
                metadataEditor.putLong(8, bundle.getLong("android.media.metadata.YEAR"));
            }
            if (bundle.containsKey("android.media.metadata.RATING")) {
                metadataEditor.putObject(101, bundle.getParcelable("android.media.metadata.RATING"));
            }
            if (bundle.containsKey("android.media.metadata.USER_RATING")) {
                metadataEditor.putObject(268435457, bundle.getParcelable("android.media.metadata.USER_RATING"));
            }
        }
    }
}
