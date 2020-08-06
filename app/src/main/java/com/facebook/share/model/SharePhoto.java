package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareMedia;
import java.util.ArrayList;
import java.util.List;

public final class SharePhoto extends ShareMedia {
    public static final Parcelable.Creator<SharePhoto> CREATOR = new Parcelable.Creator<SharePhoto>() {
        public SharePhoto createFromParcel(Parcel parcel) {
            return new SharePhoto(parcel);
        }

        public SharePhoto[] newArray(int i) {
            return new SharePhoto[i];
        }
    };
    private final Bitmap bitmap;
    private final String caption;
    private final Uri imageUrl;
    private final boolean userGenerated;

    public static final class Builder extends ShareMedia.Builder<SharePhoto, Builder> {
        /* access modifiers changed from: private */
        public Bitmap bitmap;
        /* access modifiers changed from: private */
        public String caption;
        /* access modifiers changed from: private */
        public Uri imageUrl;
        /* access modifiers changed from: private */
        public boolean userGenerated;

        public Builder setBitmap(@Nullable Bitmap bitmap2) {
            this.bitmap = bitmap2;
            return this;
        }

        public Builder setImageUrl(@Nullable Uri uri) {
            this.imageUrl = uri;
            return this;
        }

        public Builder setUserGenerated(boolean z) {
            this.userGenerated = z;
            return this;
        }

        public Builder setCaption(@Nullable String str) {
            this.caption = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Uri getImageUrl() {
            return this.imageUrl;
        }

        /* access modifiers changed from: package-private */
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public SharePhoto build() {
            return new SharePhoto(this);
        }

        public Builder readFrom(SharePhoto sharePhoto) {
            if (sharePhoto == null) {
                return this;
            }
            return ((Builder) super.readFrom(sharePhoto)).setBitmap(sharePhoto.getBitmap()).setImageUrl(sharePhoto.getImageUrl()).setUserGenerated(sharePhoto.getUserGenerated()).setCaption(sharePhoto.getCaption());
        }

        /* access modifiers changed from: package-private */
        public Builder readFrom(Parcel parcel) {
            return readFrom((SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }

        static void writePhotoListTo(Parcel parcel, int i, List<SharePhoto> list) {
            ShareMedia[] shareMediaArr = new ShareMedia[list.size()];
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < list.size()) {
                    shareMediaArr[i3] = list.get(i3);
                    i2 = i3 + 1;
                } else {
                    parcel.writeParcelableArray(shareMediaArr, i);
                    return;
                }
            }
        }

        static List<SharePhoto> readPhotoListFrom(Parcel parcel) {
            List<ShareMedia> readListFrom = readListFrom(parcel);
            ArrayList arrayList = new ArrayList();
            for (ShareMedia next : readListFrom) {
                if (next instanceof SharePhoto) {
                    arrayList.add((SharePhoto) next);
                }
            }
            return arrayList;
        }
    }

    private SharePhoto(Builder builder) {
        super((ShareMedia.Builder) builder);
        this.bitmap = builder.bitmap;
        this.imageUrl = builder.imageUrl;
        this.userGenerated = builder.userGenerated;
        this.caption = builder.caption;
    }

    SharePhoto(Parcel parcel) {
        super(parcel);
        this.bitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.imageUrl = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.userGenerated = parcel.readByte() != 0;
        this.caption = parcel.readString();
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }

    public boolean getUserGenerated() {
        return this.userGenerated;
    }

    public String getCaption() {
        return this.caption;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.bitmap, 0);
        parcel.writeParcelable(this.imageUrl, 0);
        if (this.userGenerated) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.caption);
    }

    public ShareMedia.Type getMediaType() {
        return ShareMedia.Type.PHOTO;
    }
}
