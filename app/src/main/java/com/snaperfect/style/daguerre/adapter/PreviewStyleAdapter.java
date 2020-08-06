package com.snaperfect.style.daguerre.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.snaperfect.inframe1.R;

public class PreviewStyleAdapter extends RecyclerView.Adapter<PreviewStyleAdapter.PreviewStyleHolder> {

    /* renamed from: a */
    private RecyclerView f1873a;

    /* renamed from: b */
    private final LayoutInflater f1874b;

    /* renamed from: c */
    private final C1513a f1875c;

    /* renamed from: d */
    private AdapterView.OnItemClickListener f1876d;

    /* renamed from: e */
    private AdapterView.OnItemLongClickListener f1877e;

    public class PreviewStyleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a */
        public TextView f1878a;

        /* renamed from: b */
        public ImageView f1879b;

        /* renamed from: c */
        public int f1880c;

        private PreviewStyleHolder(View view) {
            super(view);
            this.f1878a = (TextView) view.findViewById(R.id.vit_text_view);
            this.f1879b = (ImageView) view.findViewById(R.id.vit_image_view);
            this.itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            PreviewStyleAdapter.this.m2613a(this);
        }
    }

    /* renamed from: com.snaperfect.style.daguerre.adapter.PreviewStyleAdapter$a */
    public interface C1513a {
        /* renamed from: a */
        void mo16991a(PreviewStyleHolder previewStyleHolder, int i);

        /* renamed from: b */
        int mo16992b();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.f1876d = onItemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.f1877e = onItemLongClickListener;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2613a(PreviewStyleHolder previewStyleHolder) {
        if (this.f1876d != null) {
            this.f1876d.onItemClick(null, previewStyleHolder.itemView, previewStyleHolder.getAdapterPosition(), previewStyleHolder.getItemId());
        }
    }

    /* renamed from: a */
    public PreviewStyleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PreviewStyleHolder(this.f1874b.inflate(R.layout.style_list_item, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(PreviewStyleHolder previewStyleHolder, int i) {
        this.f1875c.mo16991a(previewStyleHolder, i);
    }

    public PreviewStyleAdapter(Context context, C1513a aVar) {
        this.f1874b = LayoutInflater.from(context);
        this.f1875c = aVar;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.f1873a = recyclerView;
    }

    public int getItemCount() {
        return this.f1875c.mo16992b();
    }
}
