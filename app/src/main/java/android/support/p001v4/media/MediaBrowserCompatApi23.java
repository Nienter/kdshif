package android.support.p001v4.media;

import android.annotation.TargetApi;
import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@TargetApi(23)
@RequiresApi(23)
/* renamed from: android.support.v4.media.MediaBrowserCompatApi23 */
class MediaBrowserCompatApi23 {

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi23$ItemCallback */
    interface ItemCallback {
        void onError(@NonNull String str);

        void onItemLoaded(Parcel parcel);
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi23$ItemCallbackProxy */
    static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser.ItemCallback {
        protected final T mItemCallback;

        public ItemCallbackProxy(T t) {
            this.mItemCallback = t;
        }

        public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
            if (mediaItem == null) {
                this.mItemCallback.onItemLoaded(null);
                return;
            }
            Parcel obtain = Parcel.obtain();
            mediaItem.writeToParcel(obtain, 0);
            this.mItemCallback.onItemLoaded(obtain);
        }

        public void onError(@NonNull String str) {
            this.mItemCallback.onError(str);
        }
    }

    MediaBrowserCompatApi23() {
    }

    public static Object createItemCallback(ItemCallback itemCallback) {
        return new ItemCallbackProxy(itemCallback);
    }

    public static void getItem(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).getItem(str, (MediaBrowser.ItemCallback) obj2);
    }
}
