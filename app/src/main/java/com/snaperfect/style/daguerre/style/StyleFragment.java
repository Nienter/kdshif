package com.snaperfect.style.daguerre.style;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p001v4.app.Fragment;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import com.facebook.internal.AnalyticsEvents;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.adapter.HorizontalAdapter;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;

public class StyleFragment extends Fragment implements HorizontalAdapter.C1511a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C1556a f2118a;

    /* renamed from: b */
    private PhotoAsset f2119b;

    /* renamed from: c */
    private int f2120c;

    /* renamed from: d */
    private int f2121d;

    /* renamed from: e */
    private Context f2122e;

    /* renamed from: f */
    private View f2123f;

    /* renamed from: g */
    private RecyclerView f2124g;

    /* renamed from: h */
    private HorizontalAdapter f2125h;

    /* renamed from: i */
    private LinearLayoutManager f2126i;

    /* renamed from: com.snaperfect.style.daguerre.style.StyleFragment$a */
    public interface C1556a {
        /* renamed from: a */
        GPUImage mo16899a();

        /* renamed from: a */
        void mo16900a(int i);

        /* renamed from: a */
        void mo16904a(boolean z);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2122e = getActivity();
        this.f2121d = (int) Math.min(ScreenInfo.m3112a(this.f2122e).f2102a / 4.0f, 256.0f);
        if (this.f2123f == null) {
            this.f2123f = layoutInflater.inflate(R.layout.style_fragment, viewGroup, false);
        } else {
            ((ViewGroup) this.f2123f.getParent()).removeView(this.f2123f);
        }
        m2925b();
        return this.f2123f;
    }

    /* renamed from: b */
    private void m2925b() {
        Bundle arguments = getArguments();
        this.f2119b = (PhotoAsset) IntentUtils.m3094a(arguments, "asset", PhotoAsset.CREATOR);
        this.f2120c = arguments.getInt(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        m2928e();
        m2926c();
    }

    /* renamed from: c */
    private void m2926c() {
        this.f2124g = (RecyclerView) getView().findViewById(R.id.style_horizontal_recycler_view);
        this.f2124g.setHasFixedSize(true);
        this.f2124g.setDrawingCacheEnabled(true);
        this.f2126i = new LinearLayoutManager(this.f2122e, 0, false);
        this.f2124g.setLayoutManager(this.f2126i);
        m2927d();
    }

    /* renamed from: d */
    private void m2927d() {
        this.f2125h = new HorizontalAdapter(this.f2122e, this.f2120c, this);
        this.f2124g.setAdapter(this.f2125h);
        this.f2125h.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int intValue = ((Integer) view.getTag()).intValue();
                StyleFragment.this.m2923a(i);
                StyleFragment.this.f2118a.mo16900a(intValue);
            }
        });
        this.f2125h.mo16979a(this.f2126i, this.f2120c, ((int) ScreenInfo.m3112a(this.f2122e).f2102a) / 2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2923a(int i) {
        this.f2126i.scrollToPositionWithOffset(i, (int) (((float) (this.f2123f.getWidth() / 2)) - ((float) (this.f2124g.findViewHolderForAdapterPosition(this.f2126i.findFirstCompletelyVisibleItemPosition()).itemView.getWidth() / 2))));
    }

    /* renamed from: e */
    private void m2928e() {
        C15552 r1 = new View.OnClickListener() {
            public void onClick(View view) {
                StyleFragment.this.getView().setVisibility(8);
                StyleFragment.this.f2118a.mo16904a(view.getId() == R.id.menu_cancel);
            }
        };
        ((TextView) getView().findViewById(R.id.menu_title)).setText(R.string.edit_toolbar_style);
        getView().findViewById(R.id.menu_confirm).setOnClickListener(r1);
        getView().findViewById(R.id.menu_cancel).setOnClickListener(r1);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.f2118a = (C1556a) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnToolbarSelectedListener");
        }
    }

    /* renamed from: a */
    public int mo16984a() {
        return StyleObj.f2134h.length;
    }

    /* renamed from: a */
    public void mo16985a(HorizontalAdapter.ImageTextCellHolder imageTextCellHolder, int i) {
        StyleObj bVar = StyleObj.f2134h[i];
        imageTextCellHolder.itemView.setTag(Integer.valueOf(i));
        imageTextCellHolder.itemView.setId(bVar.f2138a);
        imageTextCellHolder.f1869c = bVar.f2138a;
        imageTextCellHolder.f1867a.setText(bVar.f2139b);
        imageTextCellHolder.f1868b.setImageDrawable(null);
        this.f2119b.mo17253a(i, this.f2118a.mo16899a(), this.f2121d, (int) R.drawable.sample_thumbnail, imageTextCellHolder.f1868b);
    }
}
