package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p001v4.util.Pools;
import android.support.p001v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

final class DirectedAcyclicGraph<T> {
    private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap<>();
    private final Pools.Pool<ArrayList<T>> mListPool = new Pools.SimplePool(10);
    private final ArrayList<T> mSortResult = new ArrayList<>();
    private final HashSet<T> mSortTmpMarked = new HashSet<>();

    DirectedAcyclicGraph() {
    }

    /* access modifiers changed from: package-private */
    public void addNode(@NonNull T t) {
        if (!this.mGraph.containsKey(t)) {
            this.mGraph.put(t, null);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean contains(@NonNull T t) {
        return this.mGraph.containsKey(t);
    }

    /* access modifiers changed from: package-private */
    public void addEdge(@NonNull T t, @NonNull T t2) {
        if (!this.mGraph.containsKey(t) || !this.mGraph.containsKey(t2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arrayList = this.mGraph.get(t);
        if (arrayList == null) {
            arrayList = getEmptyList();
            this.mGraph.put(t, arrayList);
        }
        arrayList.add(t2);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public List getIncomingEdges(@NonNull T t) {
        return this.mGraph.get(t);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public List getOutgoingEdges(@NonNull T t) {
        ArrayList arrayList;
        ArrayList arrayList2 = null;
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList valueAt = this.mGraph.valueAt(i);
            if (valueAt != null && valueAt.contains(t)) {
                if (arrayList2 == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = arrayList2;
                }
                arrayList.add(this.mGraph.keyAt(i));
                arrayList2 = arrayList;
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public boolean hasOutgoingEdges(@NonNull T t) {
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList valueAt = this.mGraph.valueAt(i);
            if (valueAt != null && valueAt.contains(t)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList valueAt = this.mGraph.valueAt(i);
            if (valueAt != null) {
                poolList(valueAt);
            }
        }
        this.mGraph.clear();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ArrayList<T> getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            dfs(this.mGraph.keyAt(i), this.mSortResult, this.mSortTmpMarked);
        }
        return this.mSortResult;
    }

    private void dfs(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (!arrayList.contains(t)) {
            if (hashSet.contains(t)) {
                throw new RuntimeException("This graph contains cyclic dependencies");
            }
            hashSet.add(t);
            ArrayList arrayList2 = this.mGraph.get(t);
            if (arrayList2 != null) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    dfs(arrayList2.get(i), arrayList, hashSet);
                }
            }
            hashSet.remove(t);
            arrayList.add(t);
        }
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.mGraph.size();
    }

    @NonNull
    private ArrayList<T> getEmptyList() {
        ArrayList<T> acquire = this.mListPool.acquire();
        if (acquire == null) {
            return new ArrayList<>();
        }
        return acquire;
    }

    private void poolList(@NonNull ArrayList<T> arrayList) {
        arrayList.clear();
        this.mListPool.release(arrayList);
    }
}
