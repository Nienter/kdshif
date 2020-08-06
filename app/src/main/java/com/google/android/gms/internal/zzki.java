package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.C0955R;
import com.google.android.gms.ads.internal.zzv;
import java.util.Map;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

@zzmb
public class zzki extends zzko {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzFs;
    private String zzLk;
    private long zzLl;
    private long zzLm;
    private String zzLn;
    private String zzLo;

    public zzki(zzqp zzqp, Map<String, String> map) {
        super(zzqp, "createCalendarEvent");
        this.zzFs = map;
        this.mContext = zzqp.zzkR();
        zzgO();
    }

    private String zzav(String str) {
        return TextUtils.isEmpty(this.zzFs.get(str)) ? "" : this.zzFs.get(str);
    }

    private long zzaw(String str) {
        String str2 = this.zzFs.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void zzgO() {
        this.zzLk = zzav("description");
        this.zzLn = zzav("summary");
        this.zzLl = zzaw("start_ticks");
        this.zzLm = zzaw("end_ticks");
        this.zzLo = zzav("location");
    }

    /* access modifiers changed from: package-private */
    @TargetApi(14)
    public Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra(ShareConstants.WEB_DIALOG_PARAM_TITLE, this.zzLk);
        data.putExtra("eventLocation", this.zzLo);
        data.putExtra("description", this.zzLn);
        if (this.zzLl > -1) {
            data.putExtra("beginTime", this.zzLl);
        }
        if (this.zzLm > -1) {
            data.putExtra("endTime", this.zzLm);
        }
        data.setFlags(268435456);
        return data;
    }

    public void execute() {
        if (this.mContext == null) {
            zzay("Activity context is not available.");
        } else if (!zzv.zzcJ().zzC(this.mContext).zzfk()) {
            zzay("This feature is not available on the device.");
        } else {
            AlertDialog.Builder zzB = zzv.zzcJ().zzB(this.mContext);
            Resources resources = zzv.zzcN().getResources();
            zzB.setTitle(resources != null ? resources.getString(C0955R.string.create_calendar_title) : "Create calendar event");
            zzB.setMessage(resources != null ? resources.getString(C0955R.string.create_calendar_message) : "Allow Ad to create a calendar event?");
            zzB.setPositiveButton(resources != null ? resources.getString(C0955R.string.accept) : AbstractSpiCall.HEADER_ACCEPT, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    zzv.zzcJ().zzb(zzki.this.mContext, zzki.this.createIntent());
                }
            });
            zzB.setNegativeButton(resources != null ? resources.getString(C0955R.string.decline) : "Decline", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    zzki.this.zzay("Operation denied by user.");
                }
            });
            zzB.create().show();
        }
    }
}
