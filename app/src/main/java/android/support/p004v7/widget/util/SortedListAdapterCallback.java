package android.support.p004v7.widget.util;

import android.support.p004v7.util.SortedList;
import android.support.p004v7.widget.RecyclerView;

/* renamed from: android.support.v7.widget.util.SortedListAdapterCallback */
public abstract class SortedListAdapterCallback<T2> extends SortedList.Callback<T2> {
    final RecyclerView.Adapter mAdapter;

    public SortedListAdapterCallback(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    public void onInserted(int i, int i2) {
        this.mAdapter.notifyItemRangeInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.mAdapter.notifyItemRangeRemoved(i, i2);
    }

    public void onMoved(int i, int i2) {
        this.mAdapter.notifyItemMoved(i, i2);
    }

    public void onChanged(int i, int i2) {
        this.mAdapter.notifyItemRangeChanged(i, i2);
    }
}
