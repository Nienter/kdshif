package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class zzbuq implements Cloneable {
    private Object value;
    private zzbuo<?, ?> zzcsd;
    private List<zzbuv> zzcse = new ArrayList();

    zzbuq() {
    }

    private byte[] toByteArray() {
        byte[] bArr = new byte[zzv()];
        zza(zzbum.zzae(bArr));
        return bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbuq)) {
            return false;
        }
        zzbuq zzbuq = (zzbuq) obj;
        if (this.value == null || zzbuq.value == null) {
            if (this.zzcse != null && zzbuq.zzcse != null) {
                return this.zzcse.equals(zzbuq.zzcse);
            }
            try {
                return Arrays.equals(toByteArray(), zzbuq.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzcsd == zzbuq.zzcsd) {
            return !this.zzcsd.zzciF.isArray() ? this.value.equals(zzbuq.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzbuq.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzbuq.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzbuq.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzbuq.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzbuq.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzbuq.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzbuq.value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbum zzbum) {
        if (this.value != null) {
            this.zzcsd.zza(this.value, zzbum);
            return;
        }
        for (zzbuv zza : this.zzcse) {
            zza.zza(zzbum);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbuv zzbuv) {
        this.zzcse.add(zzbuv);
    }

    /* renamed from: zzacQ */
    public final zzbuq clone() {
        int i = 0;
        zzbuq zzbuq = new zzbuq();
        try {
            zzbuq.zzcsd = this.zzcsd;
            if (this.zzcse == null) {
                zzbuq.zzcse = null;
            } else {
                zzbuq.zzcse.addAll(this.zzcse);
            }
            if (this.value != null) {
                if (this.value instanceof zzbut) {
                    zzbuq.value = (zzbut) ((zzbut) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzbuq.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzbuq.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzbuq.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzbuq.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzbuq.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzbuq.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzbuq.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbut[]) {
                    zzbut[] zzbutArr = (zzbut[]) this.value;
                    zzbut[] zzbutArr2 = new zzbut[zzbutArr.length];
                    zzbuq.value = zzbutArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzbutArr.length) {
                            break;
                        }
                        zzbutArr2[i3] = (zzbut) zzbutArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzbuq;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T zzb(zzbuo<?, T> zzbuo) {
        if (this.value == null) {
            this.zzcsd = zzbuo;
            this.value = zzbuo.zzZ(this.zzcse);
            this.zzcse = null;
        } else if (!this.zzcsd.equals(zzbuo)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public int zzv() {
        int i = 0;
        if (this.value != null) {
            return this.zzcsd.zzaR(this.value);
        }
        Iterator<zzbuv> it = this.zzcse.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = it.next().zzv() + i2;
        }
    }
}
