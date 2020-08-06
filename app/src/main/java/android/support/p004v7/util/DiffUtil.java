package android.support.p004v7.util;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.p004v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: android.support.v7.util.DiffUtil */
public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator<Snake>() {
        public int compare(Snake snake, Snake snake2) {
            int i = snake.f92x - snake2.f92x;
            return i == 0 ? snake.f93y - snake2.f93y : i;
        }
    };

    /* renamed from: android.support.v7.util.DiffUtil$Callback */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        @Nullable
        public Object getChangePayload(int i, int i2) {
            return null;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$DiffResult */
    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> list, int[] iArr, int[] iArr2, boolean z) {
            this.mSnakes = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake snake = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if (snake == null || snake.f92x != 0 || snake.f93y != 0) {
                Snake snake2 = new Snake();
                snake2.f92x = 0;
                snake2.f93y = 0;
                snake2.removal = false;
                snake2.size = 0;
                snake2.reverse = false;
                this.mSnakes.add(0, snake2);
            }
        }

        private void findMatchingItems() {
            int i = this.mOldListSize;
            int i2 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i3 = snake.f92x + snake.size;
                int i4 = snake.f93y + snake.size;
                if (this.mDetectMoves) {
                    while (i > i3) {
                        findAddition(i, i2, size);
                        i--;
                    }
                    while (i2 > i4) {
                        findRemoval(i, i2, size);
                        i2--;
                    }
                }
                for (int i5 = 0; i5 < snake.size; i5++) {
                    int i6 = snake.f92x + i5;
                    int i7 = snake.f93y + i5;
                    int i8 = this.mCallback.areContentsTheSame(i6, i7) ? 1 : 2;
                    this.mOldItemStatuses[i6] = (i7 << 5) | i8;
                    this.mNewItemStatuses[i7] = i8 | (i6 << 5);
                }
                i = snake.f92x;
                i2 = snake.f93y;
            }
        }

        private void findAddition(int i, int i2, int i3) {
            if (this.mOldItemStatuses[i - 1] == 0) {
                findMatchingItem(i, i2, i3, false);
            }
        }

        private void findRemoval(int i, int i2, int i3) {
            if (this.mNewItemStatuses[i2 - 1] == 0) {
                findMatchingItem(i, i2, i3, true);
            }
        }

        private boolean findMatchingItem(int i, int i2, int i3, boolean z) {
            int i4;
            int i5;
            int i6 = 8;
            if (z) {
                int i7 = i2 - 1;
                i2--;
                i4 = i7;
                i5 = i;
            } else {
                i4 = i - 1;
                i5 = i - 1;
            }
            int i8 = i5;
            while (i3 >= 0) {
                Snake snake = this.mSnakes.get(i3);
                int i9 = snake.f92x + snake.size;
                int i10 = snake.f93y + snake.size;
                if (z) {
                    for (int i11 = i8 - 1; i11 >= i9; i11--) {
                        if (this.mCallback.areItemsTheSame(i11, i4)) {
                            int i12 = this.mCallback.areContentsTheSame(i11, i4) ? 8 : 4;
                            this.mNewItemStatuses[i4] = (i11 << 5) | 16;
                            this.mOldItemStatuses[i11] = i12 | (i4 << 5);
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (int i13 = i2 - 1; i13 >= i10; i13--) {
                        if (this.mCallback.areItemsTheSame(i4, i13)) {
                            if (!this.mCallback.areContentsTheSame(i4, i13)) {
                                i6 = 4;
                            }
                            this.mOldItemStatuses[i - 1] = (i13 << 5) | 16;
                            this.mNewItemStatuses[i13] = ((i - 1) << 5) | i6;
                            return true;
                        }
                    }
                    continue;
                }
                i8 = snake.f92x;
                i2 = snake.f93y;
                i3--;
            }
            return false;
        }

        public void dispatchUpdatesTo(final RecyclerView.Adapter adapter) {
            dispatchUpdatesTo((ListUpdateCallback) new ListUpdateCallback() {
                public void onInserted(int i, int i2) {
                    adapter.notifyItemRangeInserted(i, i2);
                }

                public void onRemoved(int i, int i2) {
                    adapter.notifyItemRangeRemoved(i, i2);
                }

                public void onMoved(int i, int i2) {
                    adapter.notifyItemMoved(i, i2);
                }

                public void onChanged(int i, int i2, Object obj) {
                    adapter.notifyItemRangeChanged(i, i2, obj);
                }
            });
        }

        public void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            ArrayList arrayList = new ArrayList();
            int i = this.mOldListSize;
            int i2 = this.mNewListSize;
            int size = this.mSnakes.size() - 1;
            int i3 = i2;
            while (size >= 0) {
                Snake snake = this.mSnakes.get(size);
                int i4 = snake.size;
                int i5 = snake.f92x + i4;
                int i6 = snake.f93y + i4;
                if (i5 < i) {
                    dispatchRemovals(arrayList, batchingListUpdateCallback, i5, i - i5, i5);
                }
                if (i6 < i3) {
                    dispatchAdditions(arrayList, batchingListUpdateCallback, i5, i3 - i6, i6);
                }
                for (int i7 = i4 - 1; i7 >= 0; i7--) {
                    if ((this.mOldItemStatuses[snake.f92x + i7] & 31) == 2) {
                        batchingListUpdateCallback.onChanged(snake.f92x + i7, 1, this.mCallback.getChangePayload(snake.f92x + i7, snake.f93y + i7));
                    }
                }
                i = snake.f92x;
                size--;
                i3 = snake.f93y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> list, int i, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                PostponedUpdate postponedUpdate = list.get(size);
                if (postponedUpdate.posInOwnerList == i && postponedUpdate.removal == z) {
                    list.remove(size);
                    while (true) {
                        int i2 = size;
                        if (i2 >= list.size()) {
                            return postponedUpdate;
                        }
                        PostponedUpdate postponedUpdate2 = list.get(i2);
                        postponedUpdate2.currentPos = (z ? 1 : -1) + postponedUpdate2.currentPos;
                        size = i2 + 1;
                    }
                } else {
                    size--;
                }
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onInserted(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = this.mNewItemStatuses[i3 + i4] & 31;
                switch (i5) {
                    case 0:
                        listUpdateCallback.onInserted(i, 1);
                        for (PostponedUpdate postponedUpdate : list) {
                            postponedUpdate.currentPos++;
                        }
                        break;
                    case 4:
                    case 8:
                        int i6 = this.mNewItemStatuses[i3 + i4] >> 5;
                        listUpdateCallback.onMoved(removePostponedUpdate(list, i6, true).currentPos, i);
                        if (i5 != 4) {
                            break;
                        } else {
                            listUpdateCallback.onChanged(i, 1, this.mCallback.getChangePayload(i6, i3 + i4));
                            break;
                        }
                    case 16:
                        list.add(new PostponedUpdate(i3 + i4, i, false));
                        break;
                    default:
                        throw new IllegalStateException("unknown flag for pos " + (i4 + i3) + " " + Long.toBinaryString((long) i5));
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onRemoved(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = this.mOldItemStatuses[i3 + i4] & 31;
                switch (i5) {
                    case 0:
                        listUpdateCallback.onRemoved(i + i4, 1);
                        for (PostponedUpdate next : list) {
                            next.currentPos--;
                        }
                        break;
                    case 4:
                    case 8:
                        int i6 = this.mOldItemStatuses[i3 + i4] >> 5;
                        PostponedUpdate removePostponedUpdate = removePostponedUpdate(list, i6, false);
                        listUpdateCallback.onMoved(i + i4, removePostponedUpdate.currentPos - 1);
                        if (i5 != 4) {
                            break;
                        } else {
                            listUpdateCallback.onChanged(removePostponedUpdate.currentPos - 1, 1, this.mCallback.getChangePayload(i3 + i4, i6));
                            break;
                        }
                    case 16:
                        list.add(new PostponedUpdate(i3 + i4, i + i4, true));
                        break;
                    default:
                        throw new IllegalStateException("unknown flag for pos " + (i4 + i3) + " " + Long.toBinaryString((long) i5));
                }
            }
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$PostponedUpdate */
    private static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int i, int i2, boolean z) {
            this.posInOwnerList = i;
            this.currentPos = i2;
            this.removal = z;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$Range */
    static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int i, int i2, int i3, int i4) {
            this.oldListStart = i;
            this.oldListEnd = i2;
            this.newListStart = i3;
            this.newListEnd = i4;
        }
    }

    /* renamed from: android.support.v7.util.DiffUtil$Snake */
    static class Snake {
        boolean removal;
        boolean reverse;
        int size;

        /* renamed from: x */
        int f92x;

        /* renamed from: y */
        int f93y;

        Snake() {
        }
    }

    private DiffUtil() {
    }

    public static DiffResult calculateDiff(Callback callback) {
        return calculateDiff(callback, true);
    }

    public static DiffResult calculateDiff(Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        int abs = oldListSize + newListSize + Math.abs(oldListSize - newListSize);
        int[] iArr = new int[(abs * 2)];
        int[] iArr2 = new int[(abs * 2)];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake diffPartial = diffPartial(callback, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, iArr, iArr2, abs);
            if (diffPartial != null) {
                if (diffPartial.size > 0) {
                    arrayList.add(diffPartial);
                }
                diffPartial.f92x += range.oldListStart;
                diffPartial.f93y += range.newListStart;
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                if (diffPartial.reverse) {
                    range2.oldListEnd = diffPartial.f92x;
                    range2.newListEnd = diffPartial.f93y;
                } else if (diffPartial.removal) {
                    range2.oldListEnd = diffPartial.f92x - 1;
                    range2.newListEnd = diffPartial.f93y;
                } else {
                    range2.oldListEnd = diffPartial.f92x;
                    range2.newListEnd = diffPartial.f93y - 1;
                }
                arrayList2.add(range2);
                if (!diffPartial.reverse) {
                    range.oldListStart = diffPartial.f92x + diffPartial.size;
                    range.newListStart = diffPartial.f93y + diffPartial.size;
                } else if (diffPartial.removal) {
                    range.oldListStart = diffPartial.f92x + diffPartial.size + 1;
                    range.newListStart = diffPartial.f93y + diffPartial.size;
                } else {
                    range.oldListStart = diffPartial.f92x + diffPartial.size;
                    range.newListStart = diffPartial.f93y + diffPartial.size + 1;
                }
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, SNAKE_COMPARATOR);
        return new DiffResult(callback, arrayList, iArr, iArr2, z);
    }

    private static Snake diffPartial(Callback callback, int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        int i6;
        boolean z;
        int i7;
        boolean z2;
        int i8 = i2 - i;
        int i9 = i4 - i3;
        if (i2 - i < 1 || i4 - i3 < 1) {
            return null;
        }
        int i10 = i8 - i9;
        int i11 = ((i8 + i9) + 1) / 2;
        Arrays.fill(iArr, (i5 - i11) - 1, i5 + i11 + 1, 0);
        Arrays.fill(iArr2, ((i5 - i11) - 1) + i10, i5 + i11 + 1 + i10, i8);
        boolean z3 = i10 % 2 != 0;
        for (int i12 = 0; i12 <= i11; i12++) {
            int i13 = -i12;
            while (i13 <= i12) {
                if (i13 == (-i12) || (i13 != i12 && iArr[(i5 + i13) - 1] < iArr[i5 + i13 + 1])) {
                    i7 = iArr[i5 + i13 + 1];
                    z2 = false;
                } else {
                    i7 = iArr[(i5 + i13) - 1] + 1;
                    z2 = true;
                }
                int i14 = i7;
                int i15 = i7 - i13;
                while (i14 < i8 && i15 < i9 && callback.areItemsTheSame(i + i14, i3 + i15)) {
                    i14++;
                    i15++;
                }
                iArr[i5 + i13] = i14;
                if (!z3 || i13 < (i10 - i12) + 1 || i13 > (i10 + i12) - 1 || iArr[i5 + i13] < iArr2[i5 + i13]) {
                    i13 += 2;
                } else {
                    Snake snake = new Snake();
                    snake.f92x = iArr2[i5 + i13];
                    snake.f93y = snake.f92x - i13;
                    snake.size = iArr[i5 + i13] - iArr2[i5 + i13];
                    snake.removal = z2;
                    snake.reverse = false;
                    return snake;
                }
            }
            int i16 = -i12;
            while (i16 <= i12) {
                int i17 = i16 + i10;
                if (i17 == i12 + i10 || (i17 != (-i12) + i10 && iArr2[(i5 + i17) - 1] < iArr2[i5 + i17 + 1])) {
                    i6 = iArr2[(i5 + i17) - 1];
                    z = false;
                } else {
                    i6 = iArr2[(i5 + i17) + 1] - 1;
                    z = true;
                }
                int i18 = i6;
                int i19 = i6 - i17;
                while (i18 > 0 && i19 > 0 && callback.areItemsTheSame((i + i18) - 1, (i3 + i19) - 1)) {
                    i18--;
                    i19--;
                }
                iArr2[i5 + i17] = i18;
                if (z3 || i16 + i10 < (-i12) || i16 + i10 > i12 || iArr[i5 + i17] < iArr2[i5 + i17]) {
                    i16 += 2;
                } else {
                    Snake snake2 = new Snake();
                    snake2.f92x = iArr2[i5 + i17];
                    snake2.f93y = snake2.f92x - i17;
                    snake2.size = iArr[i5 + i17] - iArr2[i5 + i17];
                    snake2.removal = z;
                    snake2.reverse = true;
                    return snake2;
                }
            }
        }
        throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
    }
}
