package com.snaperfect.style.daguerre.text;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.frame.BitmapFramed;
import com.snaperfect.style.daguerre.frame.FrameColor;
import com.snaperfect.style.daguerre.utils.DisplayUtil;
import com.snaperfect.style.daguerre.widget.GridText;

public class TextFragment extends Fragment {

    /* renamed from: a */
    C1567a f2148a;

    /* renamed from: b */
    private Context f2149b;

    /* renamed from: c */
    private FrameLayout f2150c;

    /* renamed from: d */
    private FrameLayout f2151d;

    /* renamed from: e */
    private View f2152e;

    /* renamed from: f */
    private ImageView[] f2153f;

    /* renamed from: g */
    private ImageButton[] f2154g;

    /* renamed from: h */
    private ImageView f2155h;

    /* renamed from: i */
    private LinearLayout f2156i;

    /* renamed from: j */
    private ListView f2157j;

    /* renamed from: k */
    private SeekBar f2158k;

    /* renamed from: l */
    private SeekBar f2159l;

    /* renamed from: m */
    private LayoutInflater f2160m;

    /* renamed from: n */
    private boolean f2161n;

    /* renamed from: o */
    private boolean f2162o;

    /* renamed from: p */
    private View f2163p;

    /* renamed from: com.snaperfect.style.daguerre.text.TextFragment$a */
    public interface C1567a {
        /* renamed from: c */
        GridText mo16912c();

        /* renamed from: d */
        void mo16918d(boolean z);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.f2148a = (C1567a) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSelectedListener");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.f2149b = getActivity();
        this.f2153f = new ImageView[4];
        this.f2154g = new ImageButton[3];
        View inflate = layoutInflater.inflate(R.layout.text_fragment, viewGroup, false);
        this.f2160m = LayoutInflater.from(this.f2149b);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        m2948a();
        getView().findViewById(R.id.tab_bar_confirm).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextFragment.this.f2148a.mo16918d(view.getId() == R.id.tab_bar_cancel);
                TextFragment.this.getView().setVisibility(8);
            }
        });
    }

    /* renamed from: a */
    private void m2948a() {
        this.f2150c = (FrameLayout) getView().findViewById(R.id.text_control_panel);
        this.f2151d = (FrameLayout) getView().findViewById(R.id.text_color_list);
        this.f2152e = getView().findViewById(R.id.text_other_toolbar);
        this.f2156i = (LinearLayout) this.f2151d.findViewById(R.id.item_list);
        this.f2157j = (ListView) getView().findViewById(R.id.text_font_toolbar);
        C15603 r2 = new View.OnClickListener() {
            public void onClick(View view) {
                TextFragment.this.m2952a((ImageView) view);
            }
        };
        C15614 r3 = new View.OnClickListener() {
            public void onClick(View view) {
                TextFragment.this.m2951a((ImageButton) view);
            }
        };
        this.f2153f[0] = (ImageView) getView().findViewById(R.id.tab_button1);
        this.f2155h = this.f2153f[0];
        this.f2153f[0].setOnClickListener(r2);
        this.f2153f[1] = (ImageView) getView().findViewById(R.id.tab_button2);
        this.f2153f[1].setOnClickListener(r2);
        this.f2153f[2] = (ImageView) getView().findViewById(R.id.tab_button3);
        this.f2153f[2].setOnClickListener(r2);
        this.f2153f[3] = (ImageView) getView().findViewById(R.id.tab_button4);
        this.f2153f[3].setOnClickListener(r2);
        this.f2153f[0].setImageResource(R.mipmap.edit_icon_keyboard);
        this.f2153f[1].setImageResource(R.mipmap.edit_icon_font);
        this.f2153f[2].setImageResource(R.mipmap.edit_icon_color);
        this.f2153f[3].setImageResource(R.mipmap.edit_icon_text_other);
        this.f2154g[0] = (ImageButton) getView().findViewById(R.id.text_align_left);
        this.f2154g[1] = (ImageButton) getView().findViewById(R.id.text_align_right);
        this.f2154g[2] = (ImageButton) getView().findViewById(R.id.text_align_center);
        for (ImageButton onClickListener : this.f2154g) {
            onClickListener.setOnClickListener(r3);
        }
        this.f2158k = (SeekBar) getView().findViewById(R.id.text_shadow_slider);
        this.f2159l = (SeekBar) getView().findViewById(R.id.text_alpha_slider);
        this.f2158k.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                GridText c = TextFragment.this.f2148a.mo16912c();
                if (c != null) {
                    c.setShadow(((float) i) / 100.0f);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.f2159l.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                GridText c = TextFragment.this.f2148a.mo16912c();
                if (c != null) {
                    c.setContentAlpha(((float) i) / 100.0f);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        BitmapFramed.m2810a((View) this.f2155h, ContextCompat.getDrawable(this.f2149b, R.drawable.bg_edit_tab_selected));
    }

    /* renamed from: a */
    private void m2949a(Context context, ImageView imageView, ImageView imageView2) {
        if (imageView != null) {
            BitmapFramed.m2810a((View) imageView, (Drawable) null);
        }
        BitmapFramed.m2810a((View) imageView2, ContextCompat.getDrawable(context, R.drawable.bg_edit_tab_selected));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2952a(ImageView imageView) {
        m2961b(imageView);
        m2949a(this.f2149b, this.f2155h, imageView);
        GridText c = this.f2148a.mo16912c();
        if (c != null) {
            if (imageView == this.f2153f[0]) {
                this.f2150c.setVisibility(8);
                this.f2156i.setEnabled(false);
                if (!c.mo17307a()) {
                    c.setEditing(true);
                }
            } else if (imageView == this.f2153f[1]) {
                if (c.mo17307a()) {
                    c.mo17325a((GridText.C1593a) new GridText.C1593a() {
                        /* renamed from: a */
                        public void mo17216a() {
                            TextFragment.this.m2960b();
                        }
                    });
                } else {
                    m2960b();
                }
            } else if (imageView == this.f2153f[2]) {
                if (c.mo17307a()) {
                    c.mo17325a((GridText.C1593a) new GridText.C1593a() {
                        /* renamed from: a */
                        public void mo17216a() {
                            TextFragment.this.m2963c();
                        }
                    });
                } else {
                    m2963c();
                }
            } else if (imageView == this.f2153f[3]) {
                if (c.mo17307a()) {
                    c.mo17325a((GridText.C1593a) new GridText.C1593a() {
                        /* renamed from: a */
                        public void mo17216a() {
                            TextFragment.this.m2965d();
                        }
                    });
                } else {
                    m2965d();
                }
            }
            this.f2155h = imageView;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2951a(ImageButton imageButton) {
        boolean z;
        for (ImageButton imageButton2 : this.f2154g) {
            if (imageButton == imageButton2) {
                z = true;
            } else {
                z = false;
            }
            imageButton2.setSelected(z);
        }
        GridText c = this.f2148a.mo16912c();
        if (c != null) {
            if (imageButton.getId() == R.id.text_align_left) {
                c.setAlignment(Layout.Alignment.ALIGN_NORMAL);
            } else if (imageButton.getId() == R.id.text_align_center) {
                c.setAlignment(Layout.Alignment.ALIGN_CENTER);
            } else if (imageButton.getId() == R.id.text_align_right) {
                c.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2960b() {
        this.f2150c.setVisibility(0);
        this.f2157j.setVisibility(0);
        this.f2151d.setVisibility(8);
        this.f2152e.setVisibility(8);
        if (!this.f2162o) {
            this.f2162o = true;
            m2966e();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m2963c() {
        this.f2150c.setVisibility(0);
        this.f2151d.setVisibility(0);
        this.f2157j.setVisibility(8);
        this.f2152e.setVisibility(8);
        GridText c = this.f2148a.mo16912c();
        if (!this.f2161n && c != null) {
            this.f2161n = true;
            m2953a(this.f2156i, FrameColor.m2828a(this.f2149b, c.getColor()));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m2965d() {
        boolean z;
        this.f2150c.setVisibility(0);
        this.f2152e.setVisibility(0);
        this.f2151d.setVisibility(8);
        this.f2157j.setVisibility(8);
        GridText c = this.f2148a.mo16912c();
        if (c != null) {
            ImageButton imageButton = this.f2154g[c.getTextInfo().mo17234d().ordinal()];
            for (ImageButton imageButton2 : this.f2154g) {
                if (imageButton2 == imageButton) {
                    z = true;
                } else {
                    z = false;
                }
                imageButton2.setSelected(z);
            }
            this.f2159l.setProgress((int) (c.getContentAlpha() * 100.0f));
            this.f2158k.setProgress((int) (c.getShadow() * 100.0f));
        }
    }

    /* renamed from: b */
    private void m2961b(ImageView imageView) {
        boolean z;
        for (ImageView imageView2 : this.f2153f) {
            if (imageView2 != imageView) {
                z = true;
            } else {
                z = false;
            }
            imageView2.setClickable(z);
        }
    }

    /* renamed from: e */
    private void m2966e() {
        this.f2157j.setVisibility(0);
        final TextFontAdapter bVar = new TextFontAdapter(this.f2149b);
        this.f2157j.setAdapter(bVar);
        this.f2157j.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Typeface a = bVar.mo17218a(((Integer) view.getTag()).intValue());
                GridText c = TextFragment.this.f2148a.mo16912c();
                if (c != null) {
                    c.setFont(a);
                }
                TextFragment.this.m2950a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2950a(View view) {
        if (this.f2163p != null) {
            ((TextView) this.f2163p.findViewById(R.id.text_font)).setTextColor(FrameColor.m2830b(this.f2149b, R.color.text_font_color));
        }
        if (view != null) {
            ((TextView) view.findViewById(R.id.text_font)).setTextColor(FrameColor.m2830b(this.f2149b, R.color.mt_font_color));
        }
        this.f2163p = view;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2954a(LinearLayout linearLayout, View view) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childAt = linearLayout.getChildAt(i);
            ((ViewGroup.MarginLayoutParams) childAt.getLayoutParams()).topMargin = (int) DisplayUtil.m3087a((float) (childAt == view ? 0 : 7));
        }
        linearLayout.requestLayout();
    }

    /* renamed from: a */
    private void m2953a(final LinearLayout linearLayout, int i) {
        int[] iArr = FrameColor.f2095a;
        C15592 r4 = new View.OnClickListener() {
            public void onClick(View view) {
                TextFragment.this.m2954a(linearLayout, view);
                TextFragment.this.f2148a.mo16912c().setColor(((Integer) view.getTag()).intValue());
            }
        };
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.f2160m.inflate(R.layout.color_picker_item, linearLayout);
            View childAt = linearLayout.getChildAt(i2);
            int b = FrameColor.m2830b(this.f2149b, iArr[i2]);
            childAt.setTag(Integer.valueOf(b));
            childAt.setBackgroundColor(b);
            childAt.setOnClickListener(r4);
            if (i == i2) {
                ((ViewGroup.MarginLayoutParams) childAt.getLayoutParams()).topMargin = 0;
            }
        }
        this.f2160m.inflate(R.layout.color_picker_item, linearLayout);
    }

    public void onResume() {
        super.onResume();
    }
}
