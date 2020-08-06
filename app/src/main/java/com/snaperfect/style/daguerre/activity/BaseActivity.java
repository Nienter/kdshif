package com.snaperfect.style.daguerre.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;

import com.snaperfect.inframe1.R;

import java.util.Formatter;

public class BaseActivity extends Activity {

    /* renamed from: d */
    private static String f1739d = "daguerre";

    /* renamed from: a */
    protected Context f1740a;

    /* renamed from: b */
    protected Activity f1741b;

    /* renamed from: c */

    /* renamed from: a */
    public final String mo16890a(int i, int... iArr) {
        String[] strArr = new String[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            strArr[i2] = this.f1740a.getString(iArr[i2]);
        }
        return new Formatter().format(this.f1740a.getString(i), strArr).toString();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f1740a = getApplicationContext();
        this.f1741b = this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16891a(@StringRes int i, @StringRes int i2, DialogInterface.OnDismissListener onDismissListener) {
        AlertDialog create = new AlertDialog.Builder(this).setTitle(i).setMessage(i2).setNegativeButton(R.string.dialog_button_confirm, null).create();
        create.setOnDismissListener(onDismissListener);
        create.show();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
