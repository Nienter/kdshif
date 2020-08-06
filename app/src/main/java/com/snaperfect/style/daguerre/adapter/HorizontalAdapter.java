package com.snaperfect.style.daguerre.adapter;

import android.content.Context;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.snaperfect.inframe1.R;

public class HorizontalAdapter extends RecyclerView.Adapter<ImageTextCellHolder> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public RecyclerView f1860a;

    /* renamed from: b */
    private final LayoutInflater f1861b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final Context f1862c;

    /* renamed from: d */
    private final C1511a f1863d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public int f1864e;

    /* renamed from: f */
    private AdapterView.OnItemClickListener f1865f;

    /* renamed from: g */
    private AdapterView.OnItemLongClickListener f1866g;

    public class ImageTextCellHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        /* renamed from: a */
        public TextView f1867a;

        /* renamed from: b */
        public ImageView f1868b;

        /* renamed from: c */
        public int f1869c;

        /* renamed from: e */
        private LinearLayout f1871e;

        /* renamed from: f */
        private boolean f1872f;

        private ImageTextCellHolder(View view) {
            super(view);
            this.f1867a = (TextView) view.findViewById(R.id.vit_text_view);
            this.f1868b = (ImageView) view.findViewById(R.id.vit_image_view);
            this.f1871e = (LinearLayout) view.findViewById(R.id.vit_image_container);
            this.itemView.setOnClickListener(this);
            this.itemView.setOnLongClickListener(this);
        }

        public void onClick(View view) {
            if (!this.f1872f) {
                ImageTextCellHolder imageTextCellHolder = (ImageTextCellHolder) HorizontalAdapter.this.f1860a.findViewHolderForAdapterPosition(HorizontalAdapter.this.f1864e);
                if (imageTextCellHolder != null) {
                    imageTextCellHolder.m2610a(false);
                }
                int unused = HorizontalAdapter.this.f1864e = ((Integer) this.itemView.getTag()).intValue();
                m2610a(true);
                HorizontalAdapter.this.m2600a(this);
            }
        }

        public boolean onLongClick(View view) {
            HorizontalAdapter.this.m2603b(this);
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public void m2610a(boolean z) {
            this.f1872f = z;
            this.f1867a.setTextColor(ContextCompat.getColor(HorizontalAdapter.this.f1862c, z ? R.color.mt_color : R.color.edit_tip_text_normal));
            this.f1871e.setBackgroundResource(z ? R.drawable.bg_borders : R.drawable.bg_border_unpress);
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.adapter.HorizontalAdapter$a */
    public interface C1511a {
        /* renamed from: a */
        int mo16984a();

        /* renamed from: a */
        void mo16985a(ImageTextCellHolder imageTextCellHolder, int i);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.f1865f = onItemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.f1866g = onItemLongClickListener;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2600a(ImageTextCellHolder imageTextCellHolder) {
        if (this.f1865f != null) {
            this.f1865f.onItemClick(null, imageTextCellHolder.itemView, imageTextCellHolder.getAdapterPosition(), imageTextCellHolder.getItemId());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2603b(ImageTextCellHolder imageTextCellHolder) {
        if (this.f1866g != null) {
            this.f1866g.onItemLongClick(null, imageTextCellHolder.itemView, imageTextCellHolder.getAdapterPosition(), imageTextCellHolder.getItemId());
        }
    }

    public HorizontalAdapter(Context context, int i, C1511a aVar) {
        this.f1862c = context;
        this.f1861b = LayoutInflater.from(context);
        this.f1864e = i;
        this.f1863d = aVar;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.f1860a = recyclerView;
    }

    /* renamed from: a */
    public ImageTextCellHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ImageTextCellHolder(this.f1861b.inflate(R.layout.v_image_text_item, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(ImageTextCellHolder imageTextCellHolder, int i) {
        imageTextCellHolder.m2610a(i == this.f1864e);
        this.f1863d.mo16985a(imageTextCellHolder, i);
    }

    public int getItemCount() {
        return this.f1863d.mo16984a();
    }

    /* renamed from: a */
    public boolean mo16979a(LinearLayoutManager linearLayoutManager, int i, int i2) {
        this.f1864e = i;
        if (i < 0 || i >= getItemCount()) {
            return false;
        }
        notifyItemChanged(i);
        linearLayoutManager.scrollToPositionWithOffset(i, i2);
        return true;
    }
}
