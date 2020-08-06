package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class zzack {

    public static class zza<I, O> extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final zzacm CREATOR = new zzacm();
        private final int mVersionCode;
        protected final int zzaFA;
        protected final boolean zzaFB;
        protected final int zzaFC;
        protected final boolean zzaFD;
        protected final String zzaFE;
        protected final int zzaFF;
        protected final Class<? extends zzack> zzaFG;
        protected final String zzaFH;
        private zzaco zzaFI;
        /* access modifiers changed from: private */
        public zzb<I, O> zzaFJ;

        zza(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, zzacf zzacf) {
            this.mVersionCode = i;
            this.zzaFA = i2;
            this.zzaFB = z;
            this.zzaFC = i3;
            this.zzaFD = z2;
            this.zzaFE = str;
            this.zzaFF = i4;
            if (str2 == null) {
                this.zzaFG = null;
                this.zzaFH = null;
            } else {
                this.zzaFG = zzacr.class;
                this.zzaFH = str2;
            }
            if (zzacf == null) {
                this.zzaFJ = null;
            } else {
                this.zzaFJ = zzacf.zzxI();
            }
        }

        protected zza(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends zzack> cls, zzb<I, O> zzb) {
            this.mVersionCode = 1;
            this.zzaFA = i;
            this.zzaFB = z;
            this.zzaFC = i2;
            this.zzaFD = z2;
            this.zzaFE = str;
            this.zzaFF = i3;
            this.zzaFG = cls;
            if (cls == null) {
                this.zzaFH = null;
            } else {
                this.zzaFH = cls.getCanonicalName();
            }
            this.zzaFJ = zzb;
        }

        public static zza zza(String str, int i, zzb<?, ?> zzb, boolean z) {
            return new zza(7, z, 0, false, str, i, null, zzb);
        }

        public static <T extends zzack> zza<T, T> zza(String str, int i, Class<T> cls) {
            return new zza<>(11, false, 11, false, str, i, cls, null);
        }

        public static <T extends zzack> zza<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new zza<>(11, true, 11, true, str, i, cls, null);
        }

        public static zza<Integer, Integer> zzk(String str, int i) {
            return new zza<>(0, false, 0, false, str, i, null, null);
        }

        public static zza<Boolean, Boolean> zzl(String str, int i) {
            return new zza<>(6, false, 6, false, str, i, null, null);
        }

        public static zza<String, String> zzm(String str, int i) {
            return new zza<>(7, false, 7, false, str, i, null, null);
        }

        public I convertBack(O o) {
            return this.zzaFJ.convertBack(o);
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            zzaa.zza zzg = zzaa.zzv(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("typeIn", Integer.valueOf(this.zzaFA)).zzg("typeInArray", Boolean.valueOf(this.zzaFB)).zzg("typeOut", Integer.valueOf(this.zzaFC)).zzg("typeOutArray", Boolean.valueOf(this.zzaFD)).zzg("outputFieldName", this.zzaFE).zzg("safeParcelFieldId", Integer.valueOf(this.zzaFF)).zzg("concreteTypeName", zzxS());
            Class<? extends zzack> zzxR = zzxR();
            if (zzxR != null) {
                zzg.zzg("concreteType.class", zzxR.getCanonicalName());
            }
            if (this.zzaFJ != null) {
                zzg.zzg("converterName", this.zzaFJ.getClass().getCanonicalName());
            }
            return zzg.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacm.zza(this, parcel, i);
        }

        public void zza(zzaco zzaco) {
            this.zzaFI = zzaco;
        }

        public int zzxL() {
            return this.zzaFA;
        }

        public boolean zzxM() {
            return this.zzaFB;
        }

        public int zzxN() {
            return this.zzaFC;
        }

        public boolean zzxO() {
            return this.zzaFD;
        }

        public String zzxP() {
            return this.zzaFE;
        }

        public int zzxQ() {
            return this.zzaFF;
        }

        public Class<? extends zzack> zzxR() {
            return this.zzaFG;
        }

        /* access modifiers changed from: package-private */
        public String zzxS() {
            if (this.zzaFH == null) {
                return null;
            }
            return this.zzaFH;
        }

        public boolean zzxT() {
            return this.zzaFJ != null;
        }

        /* access modifiers changed from: package-private */
        public zzacf zzxU() {
            if (this.zzaFJ == null) {
                return null;
            }
            return zzacf.zza(this.zzaFJ);
        }

        public Map<String, zza<?, ?>> zzxV() {
            zzac.zzw(this.zzaFH);
            zzac.zzw(this.zzaFI);
            return this.zzaFI.zzdA(this.zzaFH);
        }
    }

    public interface zzb<I, O> {
        I convertBack(O o);
    }

    private void zza(StringBuilder sb, zza zza2, Object obj) {
        if (zza2.zzxL() == 11) {
            sb.append(((zzack) zza2.zzxR().cast(obj)).toString());
        } else if (zza2.zzxL() == 7) {
            sb.append("\"");
            sb.append(zzp.zzdC((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    private void zza(StringBuilder sb, zza zza2, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, zza2, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map<String, zza<?, ?>> zzxK = zzxK();
        StringBuilder sb = new StringBuilder(100);
        for (String next : zzxK.keySet()) {
            zza zza2 = zzxK.get(next);
            if (zza(zza2)) {
                Object zza3 = zza(zza2, zzb(zza2));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(next).append("\":");
                if (zza3 != null) {
                    switch (zza2.zzxN()) {
                        case 8:
                            sb.append("\"").append(zzc.zzq((byte[]) zza3)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(zzc.zzr((byte[]) zza3)).append("\"");
                            break;
                        case 10:
                            zzq.zza(sb, (HashMap) zza3);
                            break;
                        default:
                            if (!zza2.zzxM()) {
                                zza(sb, zza2, zza3);
                                break;
                            } else {
                                zza(sb, zza2, (ArrayList<Object>) (ArrayList) zza3);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public <O, I> I zza(zza<I, O> zza2, Object obj) {
        return zza2.zzaFJ != null ? zza2.convertBack(obj) : obj;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zza zza2) {
        return zza2.zzxN() == 11 ? zza2.zzxO() ? zzdz(zza2.zzxP()) : zzdy(zza2.zzxP()) : zzdx(zza2.zzxP());
    }

    /* access modifiers changed from: protected */
    public Object zzb(zza zza2) {
        String zzxP = zza2.zzxP();
        if (zza2.zzxR() == null) {
            return zzdw(zza2.zzxP());
        }
        zzdw(zza2.zzxP());
        zzac.zza(true, "Concrete field shouldn't be value object: %s", zza2.zzxP());
        zza2.zzxO();
        try {
            char upperCase = Character.toUpperCase(zzxP.charAt(0));
            String valueOf = String.valueOf(zzxP.substring(1));
            return getClass().getMethod(new StringBuilder(String.valueOf(valueOf).length() + 4).append("get").append(upperCase).append(valueOf).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zzdw(String str);

    /* access modifiers changed from: protected */
    public abstract boolean zzdx(String str);

    /* access modifiers changed from: protected */
    public boolean zzdy(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* access modifiers changed from: protected */
    public boolean zzdz(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public abstract Map<String, zza<?, ?>> zzxK();
}
