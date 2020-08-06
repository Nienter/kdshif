package com.crashlytics.android.answers;

public class InviteEvent extends PredefinedEvent<InviteEvent> {
    static final String METHOD_ATTRIBUTE = "method";
    static final String TYPE = "invite";

    public InviteEvent putMethod(String str) {
        this.predefinedAttributes.put(METHOD_ATTRIBUTE, str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public String getPredefinedType() {
        return TYPE;
    }
}
