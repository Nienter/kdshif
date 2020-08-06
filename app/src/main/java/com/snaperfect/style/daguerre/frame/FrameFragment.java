package com.snaperfect.style.daguerre.frame;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.style.BackgroundInfo;
import com.snaperfect.style.daguerre.utils.DisplayUtil;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.Pair;

public class FrameFragment extends Fragment {

    /* renamed from: a */
    private static String f2054a = "FrameFragment";
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Context f2055b;

    /* renamed from: c */
    private LayoutInflater f2056c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public LinearLayout f2057d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public LinearLayout f2058e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public RelativeLayout f2059f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public HorizontalScrollView f2060g;

    /* renamed from: h */
    private SeekBar f2061h;

    /* renamed from: i */
    private Activity f2062i;

    /* renamed from: j */
    private ImageView f2063j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public ImageView f2064k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public C1546a f2065l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public BackgroundInfo f2066m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public boolean f2067n = true;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public boolean f2068o = true;

    /* renamed from: com.snaperfect.style.daguerre.frame.FrameFragment$a */
    public interface C1546a {
        /* renamed from: a */
        BackgroundInfo mo16898a(int i, int i2);

        /* renamed from: b */
        BackgroundInfo mo16907b(int i);

        /* renamed from: b */
        void mo16910b(boolean z);

        /* renamed from: c */
        BackgroundInfo mo16911c(int i);
    }

    /* renamed from: com.snaperfect.style.daguerre.frame.FrameFragment$b */
    private class C1547b implements SeekBar.OnSeekBarChangeListener {
        private C1547b() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            BackgroundInfo unused = FrameFragment.this.f2066m = FrameFragment.this.f2065l.mo16911c(i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.frame_fragment, viewGroup, false);
        this.f2057d = (LinearLayout) inflate.findViewById(R.id.color_picker_id).findViewById(R.id.item_list);
        this.f2058e = (LinearLayout) inflate.findViewById(R.id.gradient_picker_id).findViewById(R.id.item_list);
        this.f2055b = getActivity();
        this.f2056c = LayoutInflater.from(this.f2055b);
        this.f2062i = getActivity();
        this.f2063j = (ImageView) this.f2062i.findViewById(R.id.adjust_img);
        this.f2066m = (BackgroundInfo) IntentUtils.m3094a(getArguments(), "background", BackgroundInfo.CREATOR);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        m2781a();
        m2789a(this.f2066m.f2114b == 2);
    }

    /* renamed from: a */
    private void m2781a() {
        C15401 r0 = new View.OnClickListener() {
            public void onClick(View view) {
                FrameFragment.this.getView().setVisibility(8);
                FrameFragment.this.f2065l.mo16910b(view.getId() == R.id.tab_bar_cancel);
            }
        };
        getView().findViewById(R.id.tab_bar_cancel).setOnClickListener(r0);
        getView().findViewById(R.id.tab_bar_confirm).setOnClickListener(r0);
    }

    /* renamed from: a */
    private void m2789a(boolean z) {
        final ImageView imageView = (ImageView) getView().findViewById(R.id.tab_button1);
        final ImageView imageView2 = (ImageView) getView().findViewById(R.id.tab_button2);
        final ImageView imageView3 = (ImageView) getView().findViewById(R.id.tab_button3);
        imageView.setImageResource(R.mipmap.edit_icon_blur);
        imageView2.setImageResource(R.mipmap.edit_icon_gradient);
        imageView3.setImageResource(R.mipmap.edit_icon_color);
        this.f2059f = (RelativeLayout) getView().findViewById(R.id.frame_blur_toolbar);
        this.f2060g = (HorizontalScrollView) getView().findViewById(R.id.color_picker_id);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FrameFragment.this.f2059f.setVisibility(0);
                FrameFragment.this.f2060g.setVisibility(8);
                FrameFragment.this.m2792b();
                FrameFragment.this.m2784a(FrameFragment.this.f2064k, imageView);
                ImageView unused = FrameFragment.this.f2064k = imageView;
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FrameFragment.this.f2059f.setVisibility(8);
                FrameFragment.this.f2060g.setVisibility(0);
                if (FrameFragment.this.f2067n) {
                    FrameFragment.this.mo17104a(FrameFragment.this.f2066m.mo17178a(FrameFragment.this.f2055b, 2));
                    boolean unused = FrameFragment.this.f2067n = false;
                } else {
                    FrameFragment.this.m2785a(FrameFragment.this.f2057d, FrameFragment.this.f2057d.getChildAt(FrameFragment.this.f2066m.mo17178a(FrameFragment.this.f2055b, 0)));
                }
                FrameFragment.this.m2784a(FrameFragment.this.f2064k, imageView3);
                ImageView unused2 = FrameFragment.this.f2064k = imageView3;
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FrameFragment.this.f2059f.setVisibility(8);
                FrameFragment.this.f2060g.setVisibility(8);
                if (FrameFragment.this.f2068o) {
                    FrameFragment.this.mo17105a(FrameFragment.this.f2058e, FrameFragment.this.f2066m.mo17178a(FrameFragment.this.f2055b, 1));
                    boolean unused = FrameFragment.this.f2068o = false;
                } else {
                    FrameFragment.this.m2785a(FrameFragment.this.f2058e, FrameFragment.this.f2058e.getChildAt(FrameFragment.this.f2066m.mo17178a(FrameFragment.this.f2055b, 1)));
                }
                FrameFragment.this.m2784a(FrameFragment.this.f2064k, imageView2);
                ImageView unused2 = FrameFragment.this.f2064k = imageView2;
            }
        });
        switch (this.f2066m.f2114b) {
            case 0:
                imageView3.performClick();
                return;
            case 1:
                imageView2.performClick();
                return;
            case 2:
                imageView.performClick();
                return;
            case 3:
                imageView3.performClick();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2784a(ImageView imageView, ImageView imageView2) {
        m2782a(this.f2055b, imageView, imageView2);
    }

    /* renamed from: a */
    private void m2782a(Context context, ImageView imageView, ImageView imageView2) {
        if (imageView != null) {
            m2783a((View) imageView, (Drawable) null);
        }
        m2783a((View) imageView2, ContextCompat.getDrawable(context, R.drawable.bg_edit_tab_selected));
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2792b() {
        this.f2061h = (SeekBar) getView().findViewById(R.id.frame_blur_seekbar);
        this.f2061h.setEnabled(true);
        this.f2061h.setMax(30);
        m2786a(this.f2061h);
        this.f2061h.setProgress(this.f2066m.mo17186h());
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2785a(LinearLayout linearLayout, View view) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childAt = linearLayout.getChildAt(i);
            ((ViewGroup.MarginLayoutParams) childAt.getLayoutParams()).topMargin = (int) DisplayUtil.m3087a((float) (childAt == view ? 0 : 7));
        }
        linearLayout.requestLayout();
    }

    /* renamed from: a */
    public void mo17104a(int i) {
        int[] iArr = FrameColor.f2095a;
        C15445 r4 = new View.OnClickListener() {
            public void onClick(View view) {
                FrameFragment.this.m2785a(FrameFragment.this.f2057d, view);
                BackgroundInfo unused = FrameFragment.this.f2066m = FrameFragment.this.f2065l.mo16907b(((Integer) view.getTag()).intValue());
            }
        };
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.f2056c.inflate(R.layout.color_picker_item, this.f2057d);
            View childAt = this.f2057d.getChildAt(i2);
            int b = FrameColor.m2830b(this.f2055b, iArr[i2]);
            childAt.setTag(Integer.valueOf(b));
            childAt.setBackgroundColor(b);
            childAt.setOnClickListener(r4);
            if (i == i2) {
                ((ViewGroup.MarginLayoutParams) childAt.getLayoutParams()).topMargin = 0;
            }
        }
        this.f2056c.inflate(R.layout.color_picker_item, this.f2057d);
    }

    /* renamed from: a */
    public void mo17105a(LinearLayout linearLayout, int i) {
        int[][] iArr = FrameColor.f2096b;
        C15456 r4 = new View.OnClickListener() {
            public void onClick(View view) {
                FrameFragment.this.m2785a(FrameFragment.this.f2058e, view);
                Pair iVar = (Pair) view.getTag();
                BackgroundInfo unused = FrameFragment.this.f2066m = FrameFragment.this.f2065l.mo16898a(((Integer) iVar.f2264a).intValue(), ((Integer) iVar.f2265b).intValue());
            }
        };
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.f2056c.inflate(R.layout.color_picker_item, this.f2058e);
            int b = FrameColor.m2830b(this.f2055b, iArr[i2][0]);
            int b2 = FrameColor.m2830b(this.f2055b, iArr[i2][1]);
            View childAt = this.f2058e.getChildAt(i2);
            childAt.setTag(new Pair(Integer.valueOf(b), Integer.valueOf(b2)));
            childAt.setOnClickListener(r4);
            m2783a(childAt, (Drawable) BitmapFramed.m2809a(b, b2));
            if (i == i2) {
                ((ViewGroup.MarginLayoutParams) childAt.getLayoutParams()).topMargin = 0;
            }
        }
        this.f2056c.inflate(R.layout.color_picker_item, this.f2058e);
    }

    /* renamed from: a */
    private void m2783a(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    private void m2786a(SeekBar seekBar) {
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new C1547b());
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.f2065l = (C1546a) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSelectedListener");
        }
    }
}
