package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.C0955R;
import com.google.android.gms.ads.internal.zzv;
import java.util.Map;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

@zzmb
public class zzkl extends zzko {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzFs;

    public zzkl(zzqp zzqp, Map<String, String> map) {
        super(zzqp, "storePicture");
        this.zzFs = map;
        this.mContext = zzqp.zzkR();
    }

    public void execute() {
        if (this.mContext == null) {
            zzay("Activity context is not available");
        } else if (!zzv.zzcJ().zzC(this.mContext).zzfi()) {
            zzay("Feature is not supported by the device.");
        } else {
            final String str = this.zzFs.get("iurl");
            if (TextUtils.isEmpty(str)) {
                zzay("Image url cannot be empty.");
            } else if (!URLUtil.isValidUrl(str)) {
                String valueOf = String.valueOf(str);
                zzay(valueOf.length() != 0 ? "Invalid image url: ".concat(valueOf) : new String("Invalid image url: "));
            } else {
                final String zzax = zzax(str);
                if (!zzv.zzcJ().zzaX(zzax)) {
                    String valueOf2 = String.valueOf(zzax);
                    zzay(valueOf2.length() != 0 ? "Image type not recognized: ".concat(valueOf2) : new String("Image type not recognized: "));
                    return;
                }
                Resources resources = zzv.zzcN().getResources();
                AlertDialog.Builder zzB = zzv.zzcJ().zzB(this.mContext);
                zzB.setTitle(resources != null ? resources.getString(C0955R.string.store_picture_title) : "Save image");
                zzB.setMessage(resources != null ? resources.getString(C0955R.string.store_picture_message) : "Allow Ad to store image in Picture gallery?");
                zzB.setPositiveButton(resources != null ? resources.getString(C0955R.string.accept) : AbstractSpiCall.HEADER_ACCEPT, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            ((DownloadManager) zzkl.this.mContext.getSystemService("download")).enqueue(zzkl.this.zzj(str, zzax));
                        } catch (IllegalStateException e) {
                            zzkl.this.zzay("Could not store picture.");
                        }
                    }
                });
                zzB.setNegativeButton(resources != null ? resources.getString(C0955R.string.decline) : "Decline", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        zzkl.this.zzay("User canceled the download.");
                    }
                });
                zzB.create().show();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String zzax(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    /* access modifiers changed from: package-private */
    public DownloadManager.Request zzj(String str, String str2) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        zzv.zzcL().zza(request);
        return request;
    }
}
