package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zzmb
class zzne {
    private String zzD;
    private final String zzOn;
    private int zzPF;
    private final List<String> zzTR;
    private final List<String> zzTS;
    private final String zzTT;
    private final String zzTU;
    private final String zzTV;
    private final String zzTW;
    private final boolean zzTX;
    private final boolean zzTY;
    private final String zzTZ;

    public zzne(int i, Map<String, String> map) {
        this.zzD = map.get("url");
        this.zzTU = map.get("base_uri");
        this.zzTV = map.get("post_parameters");
        this.zzTX = parseBoolean(map.get("drt_include"));
        this.zzTY = parseBoolean(map.get("pan_include"));
        this.zzTT = map.get("activation_overlay_url");
        this.zzTS = zzaL(map.get("check_packages"));
        this.zzOn = map.get("request_id");
        this.zzTW = map.get(ShareConstants.MEDIA_TYPE);
        this.zzTR = zzaL(map.get("errors"));
        this.zzPF = i;
        this.zzTZ = map.get("fetched_ad");
    }

    private static boolean parseBoolean(String str) {
        return str != null && (str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) || str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    private List<String> zzaL(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    public int getErrorCode() {
        return this.zzPF;
    }

    public String getRequestId() {
        return this.zzOn;
    }

    public String getType() {
        return this.zzTW;
    }

    public String getUrl() {
        return this.zzD;
    }

    public void setUrl(String str) {
        this.zzD = str;
    }

    public List<String> zzji() {
        return this.zzTR;
    }

    public String zzjj() {
        return this.zzTU;
    }

    public String zzjk() {
        return this.zzTV;
    }

    public boolean zzjl() {
        return this.zzTX;
    }

    public String zzjm() {
        return this.zzTZ;
    }
}
