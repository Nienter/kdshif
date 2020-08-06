package com.snaperfect.style.daguerre.crop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p001v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.activity.EditActivity;
import com.snaperfect.style.daguerre.math.CGSize;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import java.util.HashMap;

public class CropActivity extends Activity {

    /* renamed from: m */
    private static C1534c[] f1936m = CropData.f1977b;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Context f1937a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public PhotoAsset f1938b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Bitmap f1939c;

    /* renamed from: d */
    private boolean f1940d = false;

    /* renamed from: e */
    private int[] f1941e = {-1, -1};
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1525c f1942f = null;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public CropImage f1943g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public CropImageView f1944h;

    /* renamed from: i */
    private HorizontalScrollView f1945i;

    /* renamed from: j */
    private HorizontalScrollView f1946j = null;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f1947k;

    /* renamed from: l */
    private HashMap<C1525c, Integer> f1948l;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public Activity f1949n;

    /* renamed from: com.snaperfect.style.daguerre.crop.CropActivity$a */
    private class C1523a implements View.OnClickListener {
        private C1523a() {
        }

        public void onClick(View view) {
            Log.i("CropActivity", "Click crop aspect");
            int id = view.getId();
            if (id == R.id.crop_free_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_RATIOFREE);
                CropActivity.this.m2678a(0, 0);
            } else if (id == R.id.crop_11_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_1_1);
                CropActivity.this.m2678a(1, 1);
            } else if (id == R.id.crop_23_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_2_3);
                CropActivity.this.m2678a(2, 3);
            } else if (id == R.id.crop_32_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_3_2);
                CropActivity.this.m2678a(3, 2);
            } else if (id == R.id.crop_34_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_3_4);
                CropActivity.this.m2678a(3, 4);
            } else if (id == R.id.crop_43_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_4_3);
                CropActivity.this.m2678a(4, 3);
            } else if (id == R.id.crop_916_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_9_16);
                CropActivity.this.m2678a(9, 16);
            } else if (id == R.id.crop_169_btn) {
                CropActivity.this.m2680a(view);
                CropActivity.this.mo17034a(C1525c.CROP_16_9);
                CropActivity.this.m2678a(16, 9);
            }
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.crop.CropActivity$b */
    private class C1524b implements View.OnClickListener {
        private C1524b() {
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.menu_cancel) {
                Log.i("CropActivity", "cancel button");
            } else if (id == R.id.menu_confirm && CropActivity.this.f1939c != null) {
                CropActivity.this.f1938b.mo17255a(CropActivity.this.f1943g.f1981d.mo17083b().mo17135a(new CGSize((float) CropActivity.this.f1939c.getWidth(), (float) CropActivity.this.f1939c.getHeight())));
                CropActivity.this.f1938b.mo17250a(CropActivity.this.f1942f.ordinal());
                Log.i("CropActivity", "confirm button");
                Intent intent = new Intent(CropActivity.this.f1949n, EditActivity.class);
                IntentUtils.m3095a(intent, "asset", CropActivity.this.f1938b);
                CropActivity.this.setResult(101, intent);
            }
            if (CropActivity.this.f1949n != null) {
                CropActivity.this.f1949n.finish();
            }
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.crop.CropActivity$c */
    enum C1525c {
        CROP_RATIOFREE,
        CROP_1_1,
        CROP_3_4,
        CROP_2_3,
        CROP_9_16,
        CROP_4_3,
        CROP_3_2,
        CROP_16_9
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(1);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_crop);
        this.f1937a = getApplicationContext();
        this.f1949n = this;
        this.f1947k = (int) ScreenInfo.m3112a(this.f1937a).f2102a;
        Log.i("CropActivity", "Crop onCreate");
        m2679a(getIntent());
        m2677a();
        m2689d();
        m2687c();
        mo17034a(C1525c.CROP_RATIOFREE);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (!this.f1940d) {
            this.f1940d = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    CropActivity.this.f1938b.mo17254a(CropActivity.this.f1937a, CropActivity.this.f1947k, (PhotoAsset.C1581d) new PhotoAsset.C1581d() {
                        /* renamed from: a */
                        public void mo16936a(@Nullable Bitmap bitmap) {
                            Bitmap unused = CropActivity.this.f1939c = bitmap;
                            CropActivity.this.f1944h.setImageBitmap(CropActivity.this.f1939c);
                            CropActivity.this.f1944h.mo17053a(CropActivity.this.f1939c, true);
                            CropActivity.this.m2678a(0, 0);
                        }
                    });
                }
            }, 100);
        }
    }

    /* renamed from: a */
    private void m2679a(Intent intent) {
        if (intent != null) {
            this.f1938b = (PhotoAsset) IntentUtils.m3093a(intent, "asset", PhotoAsset.CREATOR);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17034a(C1525c cVar) {
        C1525c cVar2 = this.f1942f;
        this.f1942f = cVar;
        if (cVar2 != null) {
            LinearLayout linearLayout = (LinearLayout) findViewById(this.f1948l.get(cVar2).intValue());
            ImageButton imageButton = (ImageButton) linearLayout.getChildAt(0);
            TextView textView = (TextView) linearLayout.getChildAt(1);
            C1534c b = m2684b(cVar2);
            if (!(imageButton == null || textView == null || b == null)) {
                imageButton.setBackgroundResource(b.mo17075c());
                textView.setTextColor(ContextCompat.getColor(this.f1937a, R.color.mt_font_color));
            }
        }
        LinearLayout linearLayout2 = (LinearLayout) findViewById(this.f1948l.get(this.f1942f).intValue());
        ImageButton imageButton2 = (ImageButton) linearLayout2.getChildAt(0);
        TextView textView2 = (TextView) linearLayout2.getChildAt(1);
        C1534c b2 = m2684b(this.f1942f);
        if (this != null && !isFinishing()) {
            imageButton2.setBackgroundResource(b2.mo17074b());
            textView2.setTextColor(ContextCompat.getColor(this.f1937a, R.color.edit_bar_status));
        }
    }

    /* renamed from: a */
    private void m2677a() {
        Log.i("CropActivity", "initial controls");
        findViewById(R.id.crop_free_btn).setOnClickListener(new C1523a());
        findViewById(R.id.crop_11_btn).setOnClickListener(new C1523a());
        findViewById(R.id.crop_23_btn).setOnClickListener(new C1523a());
        findViewById(R.id.crop_32_btn).setOnClickListener(new C1523a());
        findViewById(R.id.crop_34_btn).setOnClickListener(new C1523a());
        findViewById(R.id.crop_43_btn).setOnClickListener(new C1523a());
        findViewById(R.id.crop_916_btn).setOnClickListener(new C1523a());
        findViewById(R.id.crop_169_btn).setOnClickListener(new C1523a());
        m2685b();
    }

    /* renamed from: b */
    private void m2685b() {
        for (C1534c cVar : f1936m) {
            LinearLayout linearLayout = (LinearLayout) findViewById(cVar.mo17073a());
            ImageButton imageButton = (ImageButton) linearLayout.getChildAt(0);
            TextView textView = (TextView) linearLayout.getChildAt(1);
            if (!(imageButton == null || textView == null)) {
                imageButton.setBackgroundResource(cVar.mo17075c());
                textView.setTextColor(ContextCompat.getColor(this.f1937a, R.color.mt_font_color));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2680a(View view) {
        if (view != null) {
            if (this.f1946j == null) {
                this.f1946j = (HorizontalScrollView) findViewById(R.id.crop_scrollview);
            }
            if (this.f1946j != null) {
                int width = view.getWidth();
                int left = (this.f1947k - this.f1946j.getLeft()) - (view.getRight() - this.f1946j.getScrollX());
                if (left < width) {
                    this.f1946j.smoothScrollBy(width - left, 0);
                    return;
                }
                int left2 = view.getLeft() - this.f1946j.getScrollX();
                if (left2 < width) {
                    this.f1946j.smoothScrollBy(left2 - width, 0);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2678a(int i, int i2) {
        Log.i("CropActivity", "do crop");
        if (this.f1943g.f1981d == null) {
            this.f1943g.mo17066b(i, i2);
            this.f1943g.mo17065a(this.f1939c);
        } else {
            this.f1943g.mo17064a(i, i2);
        }
        this.f1941e[0] = i;
        this.f1941e[1] = i2;
    }

    /* renamed from: c */
    private void m2687c() {
        this.f1948l = new HashMap<>();
        this.f1948l.put(C1525c.CROP_RATIOFREE, Integer.valueOf(R.id.crop_free_btn));
        this.f1948l.put(C1525c.CROP_1_1, Integer.valueOf(R.id.crop_11_btn));
        this.f1948l.put(C1525c.CROP_2_3, Integer.valueOf(R.id.crop_23_btn));
        this.f1948l.put(C1525c.CROP_3_2, Integer.valueOf(R.id.crop_32_btn));
        this.f1948l.put(C1525c.CROP_3_4, Integer.valueOf(R.id.crop_34_btn));
        this.f1948l.put(C1525c.CROP_4_3, Integer.valueOf(R.id.crop_43_btn));
        this.f1948l.put(C1525c.CROP_9_16, Integer.valueOf(R.id.crop_916_btn));
        this.f1948l.put(C1525c.CROP_16_9, Integer.valueOf(R.id.crop_169_btn));
    }

    /* renamed from: b */
    private C1534c m2684b(C1525c cVar) {
        return f1936m[cVar.ordinal()];
    }

    /* renamed from: d */
    private void m2689d() {
        this.f1944h = (CropImageView) findViewById(R.id.crop_image);
        this.f1945i = (HorizontalScrollView) findViewById(R.id.crop_scrollview);
        this.f1943g = new CropImage(this, this.f1944h);
        ((TextView) findViewById(R.id.menu_title)).setText(R.string.edit_toolbar_crop);
        findViewById(R.id.menu_cancel).setOnClickListener(new C1524b());
        findViewById(R.id.menu_confirm).setOnClickListener(new C1524b());
    }
}
