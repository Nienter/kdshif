package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.share.model.ShareModel;
import com.facebook.share.model.ShareModelBuilder;

public class LikeContent implements ShareModel {
    public static final Parcelable.Creator<LikeContent> CREATOR = new Parcelable.Creator<LikeContent>() {
        public LikeContent createFromParcel(Parcel parcel) {
            return new LikeContent(parcel);
        }

        public LikeContent[] newArray(int i) {
            return new LikeContent[i];
        }
    };
    private final String objectId;
    private final String objectType;

    public static class Builder implements ShareModelBuilder<LikeContent, Builder> {
        /* access modifiers changed from: private */
        public String objectId;
        /* access modifiers changed from: private */
        public String objectType;

        public Builder setObjectId(String str) {
            this.objectId = str;
            return this;
        }

        public Builder setObjectType(String str) {
            this.objectType = str;
            return this;
        }

        public LikeContent build() {
            return new LikeContent(this);
        }

        public Builder readFrom(LikeContent likeContent) {
            if (likeContent == null) {
                return this;
            }
            return setObjectId(likeContent.getObjectId()).setObjectType(likeContent.getObjectType());
        }
    }

    private LikeContent(Builder builder) {
        this.objectId = builder.objectId;
        this.objectType = builder.objectType;
    }

    LikeContent(Parcel parcel) {
        this.objectId = parcel.readString();
        this.objectType = parcel.readString();
    }

    public String getObjectId() {
        return this.objectId;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.objectId);
        parcel.writeString(this.objectType);
    }
}
