package com.snaperfect.style.daguerre.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import com.snaperfect.inframe1.R;

/* renamed from: com.snaperfect.style.daguerre.utils.d */
public class CustomSupportUtils {
    /* renamed from: a */
    public static Intent m3085a(Context context) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", context.getString(R.string.email_addr_feedback), null));
        intent.putExtra("android.intent.extra.SUBJECT", Localize.m3097a(context, R.string.email_subject_feedback, Integer.valueOf(R.string.app_name), Integer.valueOf(R.string.app_version)));
        String a = Localize.m3097a(context, R.string.email_template_feedback, Integer.valueOf(R.string.hint_feedback_mail), Integer.valueOf(R.string.app_name), Integer.valueOf(R.string.app_version), Build.MODEL, Build.VERSION.RELEASE);
        if (Build.VERSION.SDK_INT < 24) {
            intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(a));
        } else {
            intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(a, 0));
        }
        return intent;
    }

    /* renamed from: b */
    public static void m3086b(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
        intent.addFlags(1208483840);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
}
