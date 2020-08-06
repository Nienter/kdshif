package com.snaperfect.style.daguerre.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.p001v4.internal.view.SupportMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.p028a.p029a.MobclickAgent;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.adapter.ImageViewPopupHelper;
import com.snaperfect.style.daguerre.utils.AppEvent;
import com.snaperfect.style.daguerre.utils.CustomSupportUtils;
import com.snaperfect.style.daguerre.utils.FileUtils;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import com.snaperfect.style.daguerre.utils.StoreUtils;
import com.squareup.picasso.C1626e;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareActivity extends BaseActivity {

    /* renamed from: l */
    private static C1509b[] f1829l = {new C1509b(R.id.share_save, R.mipmap.share_icon_save, "Save", "save"), new C1509b(R.id.share_instagram, R.mipmap.share_icon_ins, "Instagram", "com.instagram.android"), new C1509b(R.id.share_whatsapp, R.mipmap.share_icon_wa, "WhatsApp", "com.whatsapp"), new C1509b(R.id.share_facebook, R.mipmap.share_icon_fb, "Facebook", "com.facebook.katana"), new C1509b(R.id.share_twitter, R.mipmap.share_icon_tw, "Twitter", "com.twitter.android"), new C1509b(R.id.share_others, R.mipmap.share_icon_other, "Other", FacebookRequestErrorClassification.KEY_OTHER)};

    /* renamed from: d */
    private ImageView f1830d;

    /* renamed from: e */
    private LinearLayout f1831e;

    /* renamed from: f */
    private AdView f1832f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Context f1833g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public int f1834h;

    /* renamed from: i */
    private ProgressDialog f1835i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public PhotoAsset f1836j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public boolean f1837k;

    /* renamed from: com.snaperfect.style.daguerre.activity.ShareActivity$a */
    private class C1507a extends BaseAdapter {

        /* renamed from: b */
        private final List<C1509b> f1853b;

        /* renamed from: c */
        private final LayoutInflater f1854c;

        C1507a(Context context, List<C1509b> list) {
            this.f1854c = LayoutInflater.from(context);
            this.f1853b = list;
        }

        public int getCount() {
            return this.f1853b.size();
        }

        public Object getItem(int i) {
            return this.f1853b.get(i);
        }

        public long getItemId(int i) {
            return (long) this.f1853b.get(i).f1856a;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x005b, code lost:
            if (r0 == r8.getTag()) goto L_0x0056;
         */
        public View getView(int i, View view, ViewGroup viewGroup) {
            C1509b bVar = this.f1853b.get(i);
            if (view == null) {
                view = this.f1854c.inflate(R.layout.share_item, viewGroup, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.share_img);
            view.setLayoutParams(new AbsListView.LayoutParams(ShareActivity.this.f1834h / 3, -2));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            view.setId(bVar.f1856a);
            view.setTag(bVar);
            imageView.setImageResource(bVar.f1857b);
            ((TextView) view.findViewById(R.id.share_des)).setText(bVar.f1858c);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ShareActivity.this.m2578a((C1509b) view.getTag());
                }
            });
            return view;
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.activity.ShareActivity$b */
    private static class C1509b {

        /* renamed from: a */
        final int f1856a;
        @DrawableRes

        /* renamed from: b */
        final int f1857b;

        /* renamed from: c */
        String f1858c;

        /* renamed from: d */
        final String f1859d;

        public C1509b(int i, int i2, String str, String str2) {
            this.f1856a = i;
            this.f1857b = i2;
            this.f1858c = str;
            this.f1859d = str2;
        }

        /* renamed from: a */
        public boolean mo16976a(Context context) {
            if (this.f1856a == R.id.share_save || this.f1856a == R.id.share_others) {
                return true;
            }
            try {
                context.getPackageManager().getApplicationInfo(this.f1859d, 0);
                return true;
            } catch (PackageManager.NameNotFoundException e) {
                return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share);
        this.f1833g = getApplicationContext();
        this.f1834h = (int) ScreenInfo.m3112a(this.f1833g).f2102a;
        f1829l[0].f1858c = this.f1833g.getString(R.string.share_save);
        this.f1831e = (LinearLayout) findViewById(R.id.share_ad_panel);
        this.f1832f = (AdView) findViewById(R.id.ad_view);
        this.f1832f.loadAd(new AdRequest.Builder().build());
        this.f1832f.setAdListener(new AdListener() {
            public void onAdLoaded() {
                super.onAdLoaded();
                AppEvent.m3081a(ShareActivity.this.f1833g, ShareActivity.this.f1742c, "AdShow", "result", AppEventsConstants.EVENT_PARAM_VALUE_YES, ShareConstants.MEDIA_TYPE, "rect", "vender", "ADMob");
            }

            public void onAdFailedToLoad(int i) {
                AppEvent.m3081a(ShareActivity.this.f1833g, ShareActivity.this.f1742c, "AdShow", "result", AppEventsConstants.EVENT_PARAM_VALUE_NO, ShareConstants.MEDIA_TYPE, "rect", "vender", "ADMob");
            }
        });
        this.f1836j = (PhotoAsset) IntentUtils.m3093a(getIntent(), "asset", PhotoAsset.CREATOR);
        m2585c();
        mo16957a();
        final ImageView imageView = (ImageView) findViewById(R.id.share_input_img);
        this.f1830d = imageView;
        Picasso.m3395b().mo17574a(new File(this.f1836j.mo17259b())).mo17618a(imageView, (C1626e) new C1626e() {
            /* renamed from: a */
            public void mo16960a() {
                if (ShareActivity.this.f1836j == null) {
                    imageView.setImageDrawable(null);
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ImageViewPopupHelper.m2633a(this, imageView);
                    }
                });
            }

            /* renamed from: a */
            public void mo16961a(Exception exc) {
            }
        });
        m2589e();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f1832f != null) {
            this.f1832f.resume();
        }
        MobclickAgent.m2413b(this);
    }

    public void onPause() {
        if (this.f1832f != null) {
            this.f1832f.pause();
        }
        super.onPause();
        MobclickAgent.m2410a(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.f1832f != null) {
            this.f1832f.setAdListener(null);
            this.f1831e.removeAllViews();
            this.f1832f.destroy();
        }
        if (this.f1835i != null) {
            this.f1835i.dismiss();
            this.f1835i = null;
        }
        m2583b();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    /* renamed from: b */
    private void m2583b() {
        this.f1830d.setImageDrawable(null);
        this.f1830d = null;
        this.f1831e = null;
        this.f1832f = null;
        this.f1833g = null;
        this.f1836j = null;
    }

    /* renamed from: c */
    private void m2585c() {
        ((ImageView) findViewById(R.id.share_title_back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.finish();
            }
        });
        findViewById(R.id.share_home).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ShareActivity.this, MainActivity.class);
                intent.setFlags(67108864);
                ShareActivity.this.startActivity(intent);
                ShareActivity.this.finish();
            }
        });
    }

    /* renamed from: d */
    private List<C1509b> m2588d() {
        ArrayList arrayList = new ArrayList();
        for (C1509b bVar : f1829l) {
            if (bVar.mo16976a(this.f1833g)) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    private void m2589e() {
        int c = StoreUtils.m3120c();
        int i = c >>> 24;
        final int i2 = (c >> 8) & SupportMenu.USER_MASK;
        final int i3 = c & 255;
        if (i != 0) {
            return;
        }
        if (i3 == 3 || i3 == 6 || i3 == 12 || i3 == 24) {
            C15055 r1 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String str;
                    int i2;
                    switch (i) {
                        case -3:
                            str = "Cancel";
                            i2 = 0;
                            break;
                        case -2:
                            ShareActivity.this.startActivity(Intent.createChooser(CustomSupportUtils.m3085a(ShareActivity.this.f1833g), ShareActivity.this.f1833g.getString(R.string.activity_title_feedback)));
                            str = "Feedback";
                            i2 = 1;
                            break;
                        case -1:
                            CustomSupportUtils.m3086b(this);
                            str = "Rate";
                            i2 = 2;
                            break;
                        default:
                            str = null;
                            i2 = 0;
                            break;
                    }
                    StoreUtils.m3121c(ShareActivity.this.f1833g, (i2 << 24) | (i2 << 8) | i3);
                    AppEvent.m3081a(ShareActivity.this.f1833g, ShareActivity.this.f1742c, "Rating", ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, String.valueOf(i2), "count", String.valueOf(i3), NativeProtocol.WEB_DIALOG_ACTION, str);
                }
            };
            new AlertDialog.Builder(this).setTitle(mo16890a(R.string.dialog_title_rating, R.string.app_name)).setNeutralButton(R.string.dialog_button_later, r1).setNegativeButton(R.string.dialog_button_feedback, r1).setPositiveButton(R.string.dialog_button_rating, r1).create().show();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16957a() {
        ((GridView) findViewById(R.id.share_item_btn)).setAdapter(new C1507a(this.f1833g, m2588d()));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2577a(int i) {
        if (this.f1835i == null) {
            this.f1835i = new ProgressDialog(this);
            this.f1835i.setCancelable(false);
        }
        this.f1835i.setMessage(this.f1833g.getString(i));
        this.f1835i.show();
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m2590f() {
        if (this.f1835i != null) {
            this.f1835i.dismiss();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2578a(final C1509b bVar) {
        switch (bVar.f1856a) {
            case R.id.share_facebook /*2131689486*/:
                new ShareDialog((Activity) this).show((ShareContent) new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setImageUrl(this.f1836j.mo17247a(this.f1833g)).build()).build(), ShareDialog.Mode.AUTOMATIC);
                break;
            case R.id.share_instagram /*2131689487*/:
            case R.id.share_twitter /*2131689490*/:
            case R.id.share_whatsapp /*2131689491*/:
                m2584b(bVar.f1859d);
                break;
            case R.id.share_others /*2131689488*/:
                mo16958a("Share To");
                break;
            case R.id.share_save /*2131689489*/:
                if (!this.f1837k) {
                    new AsyncTask<String, Void, Throwable>() {
                        /* access modifiers changed from: protected */
                        public void onPreExecute() {
                            ShareActivity.this.m2577a((int) R.string.loading);
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Throwable doInBackground(String... strArr) {
                            Context a = ShareActivity.this.f1833g;
                            if (a == null) {
                                a = ShareActivity.this.getApplicationContext();
                            }
                            if (a == null) {
                                return new IllegalStateException("Can not get app context");
                            }
                            return FileUtils.m3090a(a, strArr[0]);
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public void onPostExecute(Throwable th) {
                            ShareActivity.this.m2590f();
                            if (th == null) {
                                boolean unused = ShareActivity.this.f1837k = true;
                                ((ImageView) ShareActivity.this.findViewById(bVar.f1856a).findViewById(R.id.share_img)).setImageResource(R.mipmap.share_icon_saved);
                                ((TextView) ShareActivity.this.findViewById(bVar.f1856a).findViewById(R.id.share_des)).setText(R.string.share_saved);
                                Toast.makeText(this, R.string.toast_share_save_success, 0).show();
                                return;
                            }
                            ShareActivity.this.mo16891a(R.string.dialog_title_share_save_fail, R.string.dialog_msg_share_save_fail, null);
                        }
                    }.execute(new String[]{this.f1836j.mo17259b()});
                    break;
                } else {
                    return;
                }
        }
        AppEvent.m3081a(this.f1833g, this.f1742c, "Share", "option", bVar.f1856a == R.id.share_save ? "Save" : bVar.f1858c);
    }

    /* renamed from: a */
    public void mo16958a(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", this.f1836j.mo17247a(this.f1833g));
        intent.addFlags(268435456);
        startActivity(Intent.createChooser(intent, str));
    }

    /* renamed from: b */
    private void m2584b(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", this.f1836j.mo17247a(this.f1833g));
        intent.setPackage(str);
        startActivity(intent);
    }
}
