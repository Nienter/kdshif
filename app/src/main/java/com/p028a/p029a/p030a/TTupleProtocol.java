package com.p028a.p029a.p030a;

import java.util.BitSet;

/* renamed from: com.a.a.a.cb */
public final class TTupleProtocol extends TCompactProtocol {
    /* renamed from: y */
    public Class<? extends IScheme> mo9456y() {
        return TupleScheme.class;
    }

    /* renamed from: a */
    public void mo9459a(BitSet bitSet, int i) {
        for (byte a : m2081b(bitSet, i)) {
            mo9451a(a);
        }
    }

    /* renamed from: b */
    public BitSet mo9460b(int i) {
        int ceil = (int) Math.ceil(((double) i) / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = mo9443q();
        }
        return m2080a(bArr);
    }

    /* renamed from: a */
    public static BitSet m2080a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    /* renamed from: b */
    public static byte[] m2081b(BitSet bitSet, int i) {
        byte[] bArr = new byte[((int) Math.ceil(((double) i) / 8.0d))];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) (bArr[length] | (1 << (i2 % 8)));
            }
        }
        return bArr;
    }
}
