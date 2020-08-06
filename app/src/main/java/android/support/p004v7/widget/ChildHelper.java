package android.support.p004v7.widget;

import android.support.p004v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.ChildHelper */
class ChildHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    final Bucket mBucket = new Bucket();
    final Callback mCallback;
    final List<View> mHiddenViews = new ArrayList();

    /* renamed from: android.support.v7.widget.ChildHelper$Bucket */
    static class Bucket {
        static final int BITS_PER_WORD = 64;
        static final long LAST_BIT = Long.MIN_VALUE;
        long mData = 0;
        Bucket next;

        Bucket() {
        }

        /* access modifiers changed from: package-private */
        public void set(int i) {
            if (i >= 64) {
                ensureNext();
                this.next.set(i - 64);
                return;
            }
            this.mData |= 1 << i;
        }

        private void ensureNext() {
            if (this.next == null) {
                this.next = new Bucket();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear(int i) {
            if (i < 64) {
                this.mData &= (1 << i) ^ -1;
            } else if (this.next != null) {
                this.next.clear(i - 64);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean get(int i) {
            if (i < 64) {
                return (this.mData & (1 << i)) != 0;
            }
            ensureNext();
            return this.next.get(i - 64);
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mData = 0;
            if (this.next != null) {
                this.next.reset();
            }
        }

        /* access modifiers changed from: package-private */
        public void insert(int i, boolean z) {
            if (i >= 64) {
                ensureNext();
                this.next.insert(i - 64, z);
                return;
            }
            boolean z2 = (this.mData & LAST_BIT) != 0;
            long j = (1 << i) - 1;
            this.mData = (((j ^ -1) & this.mData) << 1) | (this.mData & j);
            if (z) {
                set(i);
            } else {
                clear(i);
            }
            if (z2 || this.next != null) {
                ensureNext();
                this.next.insert(0, z2);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean remove(int i) {
            if (i >= 64) {
                ensureNext();
                return this.next.remove(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.mData & j) != 0;
            this.mData &= j ^ -1;
            long j2 = j - 1;
            this.mData = Long.rotateRight((j2 ^ -1) & this.mData, 1) | (this.mData & j2);
            if (this.next == null) {
                return z;
            }
            if (this.next.get(0)) {
                set(63);
            }
            this.next.remove(0);
            return z;
        }

        /* access modifiers changed from: package-private */
        public int countOnesBefore(int i) {
            if (this.next == null) {
                if (i >= 64) {
                    return Long.bitCount(this.mData);
                }
                return Long.bitCount(this.mData & ((1 << i) - 1));
            } else if (i < 64) {
                return Long.bitCount(this.mData & ((1 << i) - 1));
            } else {
                return this.next.countOnesBefore(i - 64) + Long.bitCount(this.mData);
            }
        }

        public String toString() {
            if (this.next == null) {
                return Long.toBinaryString(this.mData);
            }
            return this.next.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }

    /* renamed from: android.support.v7.widget.ChildHelper$Callback */
    interface Callback {
        void addView(View view, int i);

        void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams);

        void detachViewFromParent(int i);

        View getChildAt(int i);

        int getChildCount();

        RecyclerView.ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void onEnteredHiddenState(View view);

        void onLeftHiddenState(View view);

        void removeAllViews();

        void removeViewAt(int i);
    }

    ChildHelper(Callback callback) {
        this.mCallback = callback;
    }

    private void hideViewInternal(View view) {
        this.mHiddenViews.add(view);
        this.mCallback.onEnteredHiddenState(view);
    }

    private boolean unhideViewInternal(View view) {
        if (!this.mHiddenViews.remove(view)) {
            return false;
        }
        this.mCallback.onLeftHiddenState(view);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addView(View view, boolean z) {
        addView(view, -1, z);
    }

    /* access modifiers changed from: package-private */
    public void addView(View view, int i, boolean z) {
        int offset;
        if (i < 0) {
            offset = this.mCallback.getChildCount();
        } else {
            offset = getOffset(i);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        this.mCallback.addView(view, offset);
    }

    private int getOffset(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.mCallback.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            int countOnesBefore = i - (i2 - this.mBucket.countOnesBefore(i2));
            if (countOnesBefore == 0) {
                while (this.mBucket.get(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += countOnesBefore;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void removeView(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild >= 0) {
            if (this.mBucket.remove(indexOfChild)) {
                unhideViewInternal(view);
            }
            this.mCallback.removeViewAt(indexOfChild);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeViewAt(int i) {
        int offset = getOffset(i);
        View childAt = this.mCallback.getChildAt(offset);
        if (childAt != null) {
            if (this.mBucket.remove(offset)) {
                unhideViewInternal(childAt);
            }
            this.mCallback.removeViewAt(offset);
        }
    }

    /* access modifiers changed from: package-private */
    public View getChildAt(int i) {
        return this.mCallback.getChildAt(getOffset(i));
    }

    /* access modifiers changed from: package-private */
    public void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            this.mCallback.onLeftHiddenState(this.mHiddenViews.get(size));
            this.mHiddenViews.remove(size);
        }
        this.mCallback.removeAllViews();
    }

    /* access modifiers changed from: package-private */
    public View findHiddenNonRemovedView(int i) {
        int size = this.mHiddenViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.mHiddenViews.get(i2);
            RecyclerView.ViewHolder childViewHolder = this.mCallback.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == i && !childViewHolder.isInvalid() && !childViewHolder.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void attachViewToParent(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int offset;
        if (i < 0) {
            offset = this.mCallback.getChildCount();
        } else {
            offset = getOffset(i);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        this.mCallback.attachViewToParent(view, offset, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    /* access modifiers changed from: package-private */
    public int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    /* access modifiers changed from: package-private */
    public View getUnfilteredChildAt(int i) {
        return this.mCallback.getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public void detachViewFromParent(int i) {
        int offset = getOffset(i);
        this.mBucket.remove(offset);
        this.mCallback.detachViewFromParent(offset);
    }

    /* access modifiers changed from: package-private */
    public int indexOfChild(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild != -1 && !this.mBucket.get(indexOfChild)) {
            return indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    /* access modifiers changed from: package-private */
    public void hide(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.mBucket.set(indexOfChild);
        hideViewInternal(view);
    }

    /* access modifiers changed from: package-private */
    public void unhide(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (!this.mBucket.get(indexOfChild)) {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        } else {
            this.mBucket.clear(indexOfChild);
            unhideViewInternal(view);
        }
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    /* access modifiers changed from: package-private */
    public boolean removeViewIfHidden(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild == -1) {
            if (unhideViewInternal(view)) {
            }
            return true;
        } else if (!this.mBucket.get(indexOfChild)) {
            return false;
        } else {
            this.mBucket.remove(indexOfChild);
            if (!unhideViewInternal(view)) {
            }
            this.mCallback.removeViewAt(indexOfChild);
            return true;
        }
    }
}
