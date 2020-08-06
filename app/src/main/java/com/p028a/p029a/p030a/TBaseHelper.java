package com.p028a.p029a.p030a;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* renamed from: com.a.a.a.bf */
public final class TBaseHelper {

    /* renamed from: a */
    private static final Comparator f1428a = new C0611a();

    /* renamed from: com.a.a.a.bf$a */
    /* compiled from: TBaseHelper */
    private static class C0611a implements Comparator {
        private C0611a() {
        }

        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if (obj instanceof List) {
                return TBaseHelper.m1927a((List) obj, (List) obj2);
            }
            if (obj instanceof Set) {
                return TBaseHelper.m1929a((Set) obj, (Set) obj2);
            }
            if (obj instanceof Map) {
                return TBaseHelper.m1928a((Map) obj, (Map) obj2);
            }
            if (obj instanceof byte[]) {
                return TBaseHelper.m1930a((byte[]) obj, (byte[]) obj2);
            }
            return TBaseHelper.m1925a((Comparable) obj, (Comparable) obj2);
        }
    }

    /* renamed from: a */
    public static int m1923a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        if (b2 < b) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m1924a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        if (i2 < i) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m1930a(byte[] bArr, byte[] bArr2) {
        int a = m1924a(bArr.length, bArr2.length);
        if (a != 0) {
            return a;
        }
        for (int i = 0; i < bArr.length; i++) {
            int a2 = m1923a(bArr[i], bArr2[i]);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m1925a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    /* renamed from: a */
    public static int m1927a(List list, List list2) {
        int a = m1924a(list.size(), list2.size());
        if (a != 0) {
            return a;
        }
        for (int i = 0; i < list.size(); i++) {
            int compare = f1428a.compare(list.get(i), list2.get(i));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m1929a(Set set, Set set2) {
        int a = m1924a(set.size(), set2.size());
        if (a != 0) {
            return a;
        }
        TreeSet treeSet = new TreeSet(f1428a);
        treeSet.addAll(set);
        TreeSet treeSet2 = new TreeSet(f1428a);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compare = f1428a.compare(it.next(), it2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m1928a(Map map, Map map2) {
        int a = m1924a(map.size(), map2.size());
        if (a != 0) {
            return a;
        }
        TreeMap treeMap = new TreeMap(f1428a);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        TreeMap treeMap2 = new TreeMap(f1428a);
        treeMap2.putAll(map2);
        Iterator it2 = treeMap2.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            int compare = f1428a.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            int compare2 = f1428a.compare(entry.getValue(), entry2.getValue());
            if (compare2 != 0) {
                return compare2;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static void m1932a(ByteBuffer byteBuffer, StringBuilder sb) {
        int i;
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int position = arrayOffset + byteBuffer.position();
        int limit = byteBuffer.limit() + arrayOffset;
        if (limit - position > 128) {
            i = position + 128;
        } else {
            i = limit;
        }
        for (int i2 = position; i2 < i; i2++) {
            if (i2 > position) {
                sb.append(" ");
            }
            sb.append(m1931a(array[i2]));
        }
        if (limit != i) {
            sb.append("...");
        }
    }

    /* renamed from: a */
    public static String m1931a(byte b) {
        return Integer.toHexString((b | 256) & 511).toUpperCase().substring(1);
    }

    /* renamed from: a */
    public static byte[] m1933a(ByteBuffer byteBuffer) {
        if (m1934b(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        m1926a(byteBuffer, bArr, 0);
        return bArr;
    }

    /* renamed from: b */
    public static boolean m1934b(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    /* renamed from: a */
    public static int m1926a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i, remaining);
        return remaining;
    }

    /* renamed from: c */
    public static ByteBuffer m1935c(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        return !m1934b(byteBuffer) ? ByteBuffer.wrap(m1933a(byteBuffer)) : byteBuffer;
    }

    /* renamed from: d */
    public static ByteBuffer m1936d(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(new byte[byteBuffer.remaining()]);
        if (byteBuffer.hasArray()) {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), wrap.array(), 0, byteBuffer.remaining());
            return wrap;
        }
        byteBuffer.slice().get(wrap.array());
        return wrap;
    }
}
