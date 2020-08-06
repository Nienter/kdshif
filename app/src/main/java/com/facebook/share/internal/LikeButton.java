package com.facebook.share.internal;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.C0750R;
import com.facebook.FacebookButtonBase;
import com.facebook.internal.AnalyticsEvents;

public class LikeButton extends FacebookButtonBase {
    public LikeButton(Context context, boolean z) {
        super(context, null, 0, 0, AnalyticsEvents.EVENT_LIKE_BUTTON_CREATE, AnalyticsEvents.EVENT_LIKE_BUTTON_DID_TAP);
        setSelected(z);
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        updateForLikeStatus();
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super.configureButton(context, attributeSet, i, i2);
        updateForLikeStatus();
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return C0750R.C0753style.com_facebook_button_like;
    }

    private void updateForLikeStatus() {
        if (isSelected()) {
            setCompoundDrawablesWithIntrinsicBounds(C0750R.C0751drawable.com_facebook_button_like_icon_selected, 0, 0, 0);
            setText(getResources().getString(C0750R.string.com_facebook_like_button_liked));
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(C0750R.C0751drawable.com_facebook_button_icon, 0, 0, 0);
        setText(getResources().getString(C0750R.string.com_facebook_like_button_not_liked));
    }
}
