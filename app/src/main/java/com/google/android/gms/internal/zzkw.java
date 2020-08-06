package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.facebook.share.internal.ShareConstants;
import org.json.JSONObject;

@zzmb
public class zzkw extends Handler {
    private final zzkv zzOF;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzkw(Context context) {
        this((zzkv) new zzkx(context.getApplicationContext() != null ? context.getApplicationContext() : context));
    }

    public zzkw(zzkv zzkv) {
        this.zzOF = zzkv;
    }

    private void zzc(JSONObject jSONObject) {
        try {
            this.zzOF.zza(jSONObject.getString("request_id"), jSONObject.getString("base_url"), jSONObject.getString("html"));
        } catch (Exception e) {
        }
    }

    public void handleMessage(Message message) {
        try {
            Bundle data = message.getData();
            if (data != null) {
                JSONObject jSONObject = new JSONObject(data.getString(ShareConstants.WEB_DIALOG_PARAM_DATA));
                if ("fetch_html".equals(jSONObject.getString("message_name"))) {
                    zzc(jSONObject);
                }
            }
        } catch (Exception e) {
        }
    }
}
