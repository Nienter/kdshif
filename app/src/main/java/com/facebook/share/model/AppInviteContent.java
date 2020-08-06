package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public final class AppInviteContent implements ShareModel {
    public static final Parcelable.Creator<AppInviteContent> CREATOR = new Parcelable.Creator<AppInviteContent>() {
        public AppInviteContent createFromParcel(Parcel parcel) {
            return new AppInviteContent(parcel);
        }

        public AppInviteContent[] newArray(int i) {
            return new AppInviteContent[i];
        }
    };
    private final String applinkUrl;
    private final Builder.Destination destination;
    private final String previewImageUrl;
    private final String promoCode;
    private final String promoText;

    public static class Builder implements ShareModelBuilder<AppInviteContent, Builder> {
        /* access modifiers changed from: private */
        public String applinkUrl;
        /* access modifiers changed from: private */
        public Destination destination;
        /* access modifiers changed from: private */
        public String previewImageUrl;
        /* access modifiers changed from: private */
        public String promoCode;
        /* access modifiers changed from: private */
        public String promoText;

        public enum Destination {
            FACEBOOK("facebook"),
            MESSENGER("messenger");
            
            private final String name;

            private Destination(String str) {
                this.name = str;
            }

            public boolean equalsName(String str) {
                if (str == null) {
                    return false;
                }
                return this.name.equals(str);
            }

            public String toString() {
                return this.name;
            }
        }

        public Builder setApplinkUrl(String str) {
            this.applinkUrl = str;
            return this;
        }

        public Builder setPreviewImageUrl(String str) {
            this.previewImageUrl = str;
            return this;
        }

        public Builder setPromotionDetails(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                if (str.length() > 80) {
                    throw new IllegalArgumentException("Invalid promotion text, promotionText needs to be between1 and 80 characters long");
                } else if (!isAlphanumericWithSpaces(str)) {
                    throw new IllegalArgumentException("Invalid promotion text, promotionText can only contain alphanumericcharacters and spaces.");
                } else if (!TextUtils.isEmpty(str2)) {
                    if (str2.length() > 10) {
                        throw new IllegalArgumentException("Invalid promotion code, promotionCode can be between1 and 10 characters long");
                    } else if (!isAlphanumericWithSpaces(str2)) {
                        throw new IllegalArgumentException("Invalid promotion code, promotionCode can only contain alphanumeric characters and spaces.");
                    }
                }
            } else if (!TextUtils.isEmpty(str2)) {
                throw new IllegalArgumentException("promotionCode cannot be specified without a valid promotionText");
            }
            this.promoCode = str2;
            this.promoText = str;
            return this;
        }

        public Builder setDestination(Destination destination2) {
            this.destination = destination2;
            return this;
        }

        public AppInviteContent build() {
            return new AppInviteContent(this);
        }

        public Builder readFrom(AppInviteContent appInviteContent) {
            if (appInviteContent == null) {
                return this;
            }
            return setApplinkUrl(appInviteContent.getApplinkUrl()).setPreviewImageUrl(appInviteContent.getPreviewImageUrl()).setPromotionDetails(appInviteContent.getPromotionText(), appInviteContent.getPromotionCode()).setDestination(appInviteContent.getDestination());
        }

        private boolean isAlphanumericWithSpaces(String str) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!Character.isDigit(charAt) && !Character.isLetter(charAt) && !Character.isSpaceChar(charAt)) {
                    return false;
                }
            }
            return true;
        }
    }

    private AppInviteContent(Builder builder) {
        this.applinkUrl = builder.applinkUrl;
        this.previewImageUrl = builder.previewImageUrl;
        this.promoCode = builder.promoCode;
        this.promoText = builder.promoText;
        this.destination = builder.destination;
    }

    AppInviteContent(Parcel parcel) {
        this.applinkUrl = parcel.readString();
        this.previewImageUrl = parcel.readString();
        this.promoText = parcel.readString();
        this.promoCode = parcel.readString();
        String readString = parcel.readString();
        if (readString.length() > 0) {
            this.destination = Builder.Destination.valueOf(readString);
        } else {
            this.destination = Builder.Destination.FACEBOOK;
        }
    }

    public String getApplinkUrl() {
        return this.applinkUrl;
    }

    public String getPreviewImageUrl() {
        return this.previewImageUrl;
    }

    public String getPromotionCode() {
        return this.promoCode;
    }

    public String getPromotionText() {
        return this.promoText;
    }

    public Builder.Destination getDestination() {
        if (this.destination != null) {
            return this.destination;
        }
        return Builder.Destination.FACEBOOK;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.applinkUrl);
        parcel.writeString(this.previewImageUrl);
        parcel.writeString(this.promoText);
        parcel.writeString(this.promoCode);
        parcel.writeString(this.destination.toString());
    }
}
