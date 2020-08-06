package com.snaperfect.style.daguerre.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.p001v4.app.ActivityCompat;
import android.support.p001v4.content.ContextCompat;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.p028a.p029a.MobclickAgent;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.adapter.ImageListAdapter;
import com.snaperfect.style.daguerre.p031a.Directories;

public class MainActivity extends BaseActivity {

    /* renamed from: d */
    boolean f1824d = false;

    /* renamed from: e */
    private ImageListAdapter f1825e;

    /* renamed from: f */
    private boolean f1826f;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        m2574a();
        ((TextView) findViewById(R.id.main_topbar_txt)).setTypeface(Typeface.createFromAsset(this.f1740a.getAssets(), "fonts/Lobster_1.4.otf"));
    }

    /* renamed from: a */
    private void m2574a() {
        if (!this.f1826f) {
            this.f1826f = true;
            if (Build.VERSION.SDK_INT < 23) {
                m2575b();
                return;
            }
            String[] strArr = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
            int checkSelfPermission = ContextCompat.checkSelfPermission(this.f1741b, strArr[0]);
            ContextCompat.checkSelfPermission(this.f1741b, strArr[1]);
            if (Build.VERSION.SDK_INT < 23 || checkSelfPermission == 0) {
                m2575b();
            } else {
                ActivityCompat.requestPermissions(this.f1741b, strArr, 1);
            }
        }
    }

    /* renamed from: b */
    private void m2575b() {
        Directories.m2436a(this.f1740a);
        Directories.m2437a("style_cache", 7);
        Directories.m2437a("preview_cache", 7);
        Directories.m2437a("edit_cache", 1);
        Directories.m2437a("result", 1);
        this.f1825e = new ImageListAdapter(this.f1740a, this, this.f1742c);
        ((ListView) findViewById(R.id.main_photo_list)).setAdapter(this.f1825e);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.f1826f = false;
        switch (i) {
            case 1:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    new AlertDialog.Builder(this).setTitle(this.f1740a.getString(R.string.album_permission_denied_title)).setMessage(mo16890a(R.string.album_permission_denied_content, R.string.app_name)).setPositiveButton(R.string.album_permission_setting, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                            intent.addFlags(268435456);
                            MainActivity.this.startActivity(intent);
                        }
                    }).setNegativeButton(R.string.dialog_button_cancel, null).create().show();
                    return;
                }
                m2575b();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.f1825e == null && Build.VERSION.SDK_INT >= 23) {
            m2574a();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f1825e != null) {
            this.f1825e.notifyDataSetChanged();
        }
        MobclickAgent.m2413b(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.m2410a(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f1825e != null) {
            this.f1825e.mo16993a();
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("MainActivity", "parent intent");
    }

    public void onBackPressed() {
        if (this.f1824d) {
            super.onBackPressed();
            return;
        }
        this.f1824d = true;
        Toast.makeText(this.f1740a, R.string.toast_tap_twice_exist, 0).show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MainActivity.this.f1824d = false;
            }
        }, 2000);
    }
}
