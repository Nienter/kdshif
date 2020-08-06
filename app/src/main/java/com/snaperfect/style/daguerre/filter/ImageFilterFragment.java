package com.snaperfect.style.daguerre.filter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p001v4.app.Fragment;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.adapter.HorizontalAdapter;
import com.snaperfect.style.daguerre.utils.IntentUtils;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import com.snaperfect.style.daguerre.utils.ScreenInfo;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;

public class ImageFilterFragment extends Fragment implements HorizontalAdapter.C1511a {

    /* renamed from: a */
    private LayoutInflater f2032a;

    /* renamed from: b */
    private Activity f2033b;

    /* renamed from: c */
    private PhotoAsset f2034c;

    /* renamed from: d */
    private int f2035d;

    /* renamed from: e */
    private float f2036e;

    /* renamed from: f */
    private int f2037f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public C1539a f2038g;

    /* renamed from: h */
    private RecyclerView f2039h;

    /* renamed from: i */
    private HorizontalAdapter f2040i;

    /* renamed from: j */
    private LinearLayoutManager f2041j;

    /* renamed from: com.snaperfect.style.daguerre.filter.ImageFilterFragment$a */
    public interface C1539a {
        /* renamed from: a */
        GPUImage mo16899a();

        /* renamed from: c */
        void mo16914c(boolean z);

        /* renamed from: d */
        void mo16916d(int i);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.filter_fragment, viewGroup, false);
        inflate.setVisibility(0);
        this.f2033b = getActivity();
        this.f2037f = (int) (ScreenInfo.m3112a(this.f2033b).f2102a / 4.0f);
        this.f2032a = layoutInflater;
        m2765b();
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        m2767d();
        m2766c();
    }

    /* renamed from: b */
    private void m2765b() {
        Bundle arguments = getArguments();
        this.f2034c = (PhotoAsset) IntentUtils.m3094a(arguments, "asset", PhotoAsset.CREATOR);
        this.f2035d = arguments.getInt("filter");
        this.f2036e = arguments.getFloat("mix");
    }

    /* renamed from: c */
    private void m2766c() {
        this.f2039h = (RecyclerView) getView().findViewById(R.id.filter_scroll_bar);
        this.f2039h.setHasFixedSize(true);
        this.f2039h.setDrawingCacheEnabled(true);
        this.f2041j = new LinearLayoutManager(this.f2033b, 0, false);
        this.f2039h.setLayoutManager(this.f2041j);
        this.f2040i = new HorizontalAdapter(this.f2033b, this.f2035d, this);
        this.f2039h.setAdapter(this.f2040i);
        this.f2040i.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int intValue = ((Integer) view.getTag()).intValue();
                ImageFilterFragment.this.f2038g.mo16916d(intValue);
                ImageFilterFragment.this.m2763a(intValue);
            }
        });
        this.f2040i.mo16979a(this.f2041j, this.f2035d, ((int) ScreenInfo.m3112a(this.f2033b).f2102a) / 2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2763a(int i) {
        this.f2041j.scrollToPositionWithOffset(i, (int) (((float) (getView().getWidth() / 2)) - ((float) (this.f2039h.findViewHolderForAdapterPosition(this.f2041j.findFirstCompletelyVisibleItemPosition()).itemView.getWidth() / 2))));
    }

    /* renamed from: d */
    private void m2767d() {
        if (this.f2033b != null) {
            C15372 r1 = new View.OnClickListener() {
                public void onClick(View view) {
                    ImageFilterFragment.this.getView().setVisibility(8);
                    ImageFilterFragment.this.f2038g.mo16914c(view.getId() == R.id.menu_cancel);
                }
            };
            ((TextView) getView().findViewById(R.id.menu_title)).setText(R.string.edit_toolbar_filter);
            getView().findViewById(R.id.menu_cancel).setOnClickListener(r1);
            getView().findViewById(R.id.menu_confirm).setOnClickListener(r1);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.f2038g = (C1539a) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnToolbarSelectedListener");
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: a */
    public int mo16984a() {
        return FilterObj.f2048a.length;
    }

    /* renamed from: a */
    public void mo16985a(HorizontalAdapter.ImageTextCellHolder imageTextCellHolder, final int i) {
        imageTextCellHolder.f1867a.setText(FilterObj.f2048a[i].mo17102a());
        imageTextCellHolder.itemView.setTag(Integer.valueOf(i));
        imageTextCellHolder.f1868b.setImageDrawable(null);
        imageTextCellHolder.f1868b.setTag(Integer.valueOf(i));
        FilterObj bVar = FilterObj.f2048a[i];
        final ImageView imageView = imageTextCellHolder.f1868b;
        this.f2034c.mo17257a(this.f2038g.mo16899a(), this.f2037f, i, 0, (PhotoAsset.C1581d) new PhotoAsset.C1581d() {
            /* renamed from: a */
            public void mo16936a(@Nullable Bitmap bitmap) {
                if (((Integer) imageView.getTag()).intValue() == i) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }
}
