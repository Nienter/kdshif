package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import android.support.p001v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzauf;
import com.google.android.gms.internal.zzauh;
import com.google.android.gms.measurement.AppMeasurement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

class zzass extends zzats {
    zzass(zzatp zzatp) {
        super(zzatp);
    }

    private Boolean zza(zzauf.zzb zzb, zzauh.zzb zzb2, long j) {
        if (zzb.zzbvp != null) {
            Boolean zza = zza(j, zzb.zzbvp);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzauf.zzc zzc : zzb.zzbvn) {
            if (TextUtils.isEmpty(zzc.zzbvu)) {
                zzJt().zzLc().zzj("null or empty param name in filter. event", zzb2.name);
                return null;
            }
            hashSet.add(zzc.zzbvu);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzauh.zzc zzc2 : zzb2.zzbvV) {
            if (hashSet.contains(zzc2.name)) {
                if (zzc2.zzbvZ != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbvZ);
                } else if (zzc2.zzbvc != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbvc);
                } else if (zzc2.zzaFy != null) {
                    arrayMap.put(zzc2.name, zzc2.zzaFy);
                } else {
                    zzJt().zzLc().zze("Unknown value for param. event, param", zzb2.name, zzc2.name);
                    return null;
                }
            }
        }
        for (zzauf.zzc zzc3 : zzb.zzbvn) {
            boolean equals = Boolean.TRUE.equals(zzc3.zzbvt);
            String str = zzc3.zzbvu;
            if (TextUtils.isEmpty(str)) {
                zzJt().zzLc().zzj("Event has empty param name. event", zzb2.name);
                return null;
            }
            Object obj = arrayMap.get(str);
            if (obj instanceof Long) {
                if (zzc3.zzbvs == null) {
                    zzJt().zzLc().zze("No number filter for long param. event, param", zzb2.name, str);
                    return null;
                }
                Boolean zza2 = zza(((Long) obj).longValue(), zzc3.zzbvs);
                if (zza2 == null) {
                    return null;
                }
                if ((!zza2.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj instanceof Double) {
                if (zzc3.zzbvs == null) {
                    zzJt().zzLc().zze("No number filter for double param. event, param", zzb2.name, str);
                    return null;
                }
                Boolean zza3 = zza(((Double) obj).doubleValue(), zzc3.zzbvs);
                if (zza3 == null) {
                    return null;
                }
                if ((!zza3.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj instanceof String) {
                if (zzc3.zzbvr == null) {
                    zzJt().zzLc().zze("No string filter for String param. event, param", zzb2.name, str);
                    return null;
                }
                Boolean zza4 = zza((String) obj, zzc3.zzbvr);
                if (zza4 == null) {
                    return null;
                }
                if ((!zza4.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj == null) {
                zzJt().zzLg().zze("Missing param for filter. event, param", zzb2.name, str);
                return false;
            } else {
                zzJt().zzLc().zze("Unknown param type. event, param", zzb2.name, str);
                return null;
            }
        }
        return true;
    }

    private Boolean zza(zzauf.zze zze, zzauh.zzg zzg) {
        zzauf.zzc zzc = zze.zzbvC;
        if (zzc == null) {
            zzJt().zzLc().zzj("Missing property filter. property", zzg.name);
            return null;
        }
        boolean equals = Boolean.TRUE.equals(zzc.zzbvt);
        if (zzg.zzbvZ != null) {
            if (zzc.zzbvs != null) {
                return zza(zza(zzg.zzbvZ.longValue(), zzc.zzbvs), equals);
            }
            zzJt().zzLc().zzj("No number filter for long property. property", zzg.name);
            return null;
        } else if (zzg.zzbvc != null) {
            if (zzc.zzbvs != null) {
                return zza(zza(zzg.zzbvc.doubleValue(), zzc.zzbvs), equals);
            }
            zzJt().zzLc().zzj("No number filter for double property. property", zzg.name);
            return null;
        } else if (zzg.zzaFy == null) {
            zzJt().zzLc().zzj("User property has no value, property", zzg.name);
            return null;
        } else if (zzc.zzbvr != null) {
            return zza(zza(zzg.zzaFy, zzc.zzbvr), equals);
        } else {
            if (zzc.zzbvs == null) {
                zzJt().zzLc().zzj("No string or number filter defined. property", zzg.name);
                return null;
            } else if (zzaue.zzgi(zzg.zzaFy)) {
                return zza(zza(zzg.zzaFy, zzc.zzbvs), equals);
            } else {
                zzJt().zzLc().zze("Invalid user property value for Numeric number filter. property, value", zzg.name, zzg.zzaFy);
                return null;
            }
        }
    }

    static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private Boolean zza(BigDecimal bigDecimal, int i, BigDecimal bigDecimal2, BigDecimal bigDecimal3, BigDecimal bigDecimal4, double d) {
        boolean z = true;
        if (bigDecimal == null) {
            return null;
        }
        if (i == 4) {
            if (bigDecimal3 == null || bigDecimal4 == null) {
                return null;
            }
        } else if (bigDecimal2 == null) {
            return null;
        }
        switch (i) {
            case 1:
                if (bigDecimal.compareTo(bigDecimal2) != -1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (bigDecimal.compareTo(bigDecimal2) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d != 0.0d) {
                    if (!(bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1)) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal.compareTo(bigDecimal2) != 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 4:
                if (bigDecimal.compareTo(bigDecimal3) == -1 || bigDecimal.compareTo(bigDecimal4) == 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }

    private List<String> zza(String[] strArr, boolean z) {
        if (z) {
            return Arrays.asList(strArr);
        }
        ArrayList arrayList = new ArrayList();
        for (String upperCase : strArr) {
            arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
        }
        return arrayList;
    }

    public Boolean zza(double d, zzauf.zzd zzd) {
        try {
            return zza(new BigDecimal(d), zzd, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Boolean zza(long j, zzauf.zzd zzd) {
        try {
            return zza(new BigDecimal(j), zzd, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Boolean zza(String str, zzauf.zzd zzd) {
        Boolean bool = null;
        if (!zzaue.zzgi(str)) {
            return bool;
        }
        try {
            return zza(new BigDecimal(str), zzd, 0.0d);
        } catch (NumberFormatException e) {
            return bool;
        }
    }

    /* access modifiers changed from: package-private */
    public Boolean zza(String str, zzauf.zzf zzf) {
        String str2 = null;
        zzac.zzw(zzf);
        if (str == null || zzf.zzbvD == null || zzf.zzbvD.intValue() == 0) {
            return null;
        }
        if (zzf.zzbvD.intValue() == 6) {
            if (zzf.zzbvG == null || zzf.zzbvG.length == 0) {
                return null;
            }
        } else if (zzf.zzbvE == null) {
            return null;
        }
        int intValue = zzf.zzbvD.intValue();
        boolean z = zzf.zzbvF != null && zzf.zzbvF.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzf.zzbvE : zzf.zzbvE.toUpperCase(Locale.ENGLISH);
        List<String> zza = zzf.zzbvG == null ? null : zza(zzf.zzbvG, z);
        if (intValue == 1) {
            str2 = upperCase;
        }
        return zza(str, intValue, z, upperCase, zza, str2);
    }

    /* access modifiers changed from: package-private */
    public Boolean zza(BigDecimal bigDecimal, zzauf.zzd zzd, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        zzac.zzw(zzd);
        if (zzd.zzbvv == null || zzd.zzbvv.intValue() == 0) {
            return null;
        }
        if (zzd.zzbvv.intValue() == 4) {
            if (zzd.zzbvy == null || zzd.zzbvz == null) {
                return null;
            }
        } else if (zzd.zzbvx == null) {
            return null;
        }
        int intValue = zzd.zzbvv.intValue();
        if (zzd.zzbvv.intValue() == 4) {
            if (!zzaue.zzgi(zzd.zzbvy) || !zzaue.zzgi(zzd.zzbvz)) {
                return null;
            }
            try {
                bigDecimal4 = new BigDecimal(zzd.zzbvy);
                bigDecimal3 = new BigDecimal(zzd.zzbvz);
                bigDecimal2 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (!zzaue.zzgi(zzd.zzbvx)) {
            return null;
        } else {
            try {
                bigDecimal2 = new BigDecimal(zzd.zzbvx);
                bigDecimal3 = null;
                bigDecimal4 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        return zza(bigDecimal, intValue, bigDecimal2, bigDecimal4, bigDecimal3, d);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zza(String str, zzauf.zza[] zzaArr) {
        zzac.zzw(zzaArr);
        for (zzauf.zza zza : zzaArr) {
            for (zzauf.zzb zzb : zza.zzbvj) {
                String str2 = AppMeasurement.zza.zzbpx.get(zzb.zzbvm);
                if (str2 != null) {
                    zzb.zzbvm = str2;
                }
                for (zzauf.zzc zzc : zzb.zzbvn) {
                    String str3 = AppMeasurement.zze.zzbpy.get(zzc.zzbvu);
                    if (str3 != null) {
                        zzc.zzbvu = str3;
                    }
                }
            }
            for (zzauf.zze zze : zza.zzbvi) {
                String str4 = AppMeasurement.zzg.zzbpC.get(zze.zzbvB);
                if (str4 != null) {
                    zze.zzbvB = str4;
                }
            }
        }
        zzJo().zzb(str, zzaArr);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public zzauh.zza[] zza(String str, zzauh.zzb[] zzbArr, zzauh.zzg[] zzgArr) {
        Map map;
        zzauf.zze zze;
        zzasy zzKX;
        Map map2;
        zzac.zzdv(str);
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map<Integer, zzauh.zzf> zzfC = zzJo().zzfC(str);
        if (zzfC != null) {
            for (Integer intValue : zzfC.keySet()) {
                int intValue2 = intValue.intValue();
                zzauh.zzf zzf = zzfC.get(Integer.valueOf(intValue2));
                BitSet bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue2));
                BitSet bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue2));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(intValue2), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(intValue2), bitSet2);
                }
                for (int i = 0; i < zzf.zzbwC.length * 64; i++) {
                    if (zzaue.zza(zzf.zzbwC, i)) {
                        zzJt().zzLg().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue2), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzaue.zza(zzf.zzbwD, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zzauh.zza zza = new zzauh.zza();
                arrayMap.put(Integer.valueOf(intValue2), zza);
                zza.zzbvT = false;
                zza.zzbvS = zzf;
                zza.zzbvR = new zzauh.zzf();
                zza.zzbvR.zzbwD = zzaue.zza(bitSet);
                zza.zzbvR.zzbwC = zzaue.zza(bitSet2);
            }
        }
        if (zzbArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            int length = zzbArr.length;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= length) {
                    break;
                }
                zzauh.zzb zzb = zzbArr[i3];
                zzasy zzP = zzJo().zzP(str, zzb.name);
                if (zzP == null) {
                    zzJt().zzLc().zze("Event aggregate wasn't created during raw event logging. appId, event", zzati.zzfI(str), zzb.name);
                    zzKX = new zzasy(str, zzb.name, 1, 1, zzb.zzbvW.longValue());
                } else {
                    zzKX = zzP.zzKX();
                }
                zzJo().zza(zzKX);
                long j = zzKX.zzbqJ;
                Map map3 = (Map) arrayMap4.get(zzb.name);
                if (map3 == null) {
                    Map zzS = zzJo().zzS(str, zzb.name);
                    if (zzS == null) {
                        zzS = new ArrayMap();
                    }
                    arrayMap4.put(zzb.name, zzS);
                    map2 = zzS;
                } else {
                    map2 = map3;
                }
                for (Integer intValue3 : map2.keySet()) {
                    int intValue4 = intValue3.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue4))) {
                        zzJt().zzLg().zzj("Skipping failed audience ID", Integer.valueOf(intValue4));
                    } else {
                        zzauh.zza zza2 = (zzauh.zza) arrayMap.get(Integer.valueOf(intValue4));
                        BitSet bitSet3 = (BitSet) arrayMap2.get(Integer.valueOf(intValue4));
                        BitSet bitSet4 = (BitSet) arrayMap3.get(Integer.valueOf(intValue4));
                        if (zza2 == null) {
                            zzauh.zza zza3 = new zzauh.zza();
                            arrayMap.put(Integer.valueOf(intValue4), zza3);
                            zza3.zzbvT = true;
                            bitSet3 = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue4), bitSet3);
                            bitSet4 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue4), bitSet4);
                        }
                        for (zzauf.zzb zzb2 : (List) map2.get(Integer.valueOf(intValue4))) {
                            if (zzJt().zzai(2)) {
                                zzJt().zzLg().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue4), zzb2.zzbvl, zzb2.zzbvm);
                                zzJt().zzLg().zzj("Filter definition", zzaue.zza(zzb2));
                            }
                            if (zzb2.zzbvl == null || zzb2.zzbvl.intValue() > 256) {
                                zzJt().zzLc().zze("Invalid event filter ID. appId, id", zzati.zzfI(str), String.valueOf(zzb2.zzbvl));
                            } else if (bitSet3.get(zzb2.zzbvl.intValue())) {
                                zzJt().zzLg().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), zzb2.zzbvl);
                            } else {
                                Boolean zza4 = zza(zzb2, zzb, j);
                                zzJt().zzLg().zzj("Event filter result", zza4 == null ? "null" : zza4);
                                if (zza4 == null) {
                                    hashSet.add(Integer.valueOf(intValue4));
                                } else {
                                    bitSet4.set(zzb2.zzbvl.intValue());
                                    if (zza4.booleanValue()) {
                                        bitSet3.set(zzb2.zzbvl.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
                i2 = i3 + 1;
            }
        }
        if (zzgArr != null) {
            ArrayMap arrayMap5 = new ArrayMap();
            for (zzauh.zzg zzg : zzgArr) {
                Map map4 = (Map) arrayMap5.get(zzg.name);
                if (map4 == null) {
                    Map zzT = zzJo().zzT(str, zzg.name);
                    if (zzT == null) {
                        zzT = new ArrayMap();
                    }
                    arrayMap5.put(zzg.name, zzT);
                    map = zzT;
                } else {
                    map = map4;
                }
                for (Integer intValue5 : map.keySet()) {
                    int intValue6 = intValue5.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue6))) {
                        zzJt().zzLg().zzj("Skipping failed audience ID", Integer.valueOf(intValue6));
                    } else {
                        zzauh.zza zza5 = (zzauh.zza) arrayMap.get(Integer.valueOf(intValue6));
                        BitSet bitSet5 = (BitSet) arrayMap2.get(Integer.valueOf(intValue6));
                        BitSet bitSet6 = (BitSet) arrayMap3.get(Integer.valueOf(intValue6));
                        if (zza5 == null) {
                            zzauh.zza zza6 = new zzauh.zza();
                            arrayMap.put(Integer.valueOf(intValue6), zza6);
                            zza6.zzbvT = true;
                            bitSet5 = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue6), bitSet5);
                            bitSet6 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue6), bitSet6);
                        }
                        Iterator it = ((List) map.get(Integer.valueOf(intValue6))).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            zze = (zzauf.zze) it.next();
                            if (zzJt().zzai(2)) {
                                zzJt().zzLg().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue6), zze.zzbvl, zze.zzbvB);
                                zzJt().zzLg().zzj("Filter definition", zzaue.zza(zze));
                            }
                            if (zze.zzbvl == null || zze.zzbvl.intValue() > 256) {
                                zzJt().zzLc().zze("Invalid property filter ID. appId, id", zzati.zzfI(str), String.valueOf(zze.zzbvl));
                                hashSet.add(Integer.valueOf(intValue6));
                            } else if (bitSet5.get(zze.zzbvl.intValue())) {
                                zzJt().zzLg().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue6), zze.zzbvl);
                            } else {
                                Boolean zza7 = zza(zze, zzg);
                                zzJt().zzLg().zzj("Property filter result", zza7 == null ? "null" : zza7);
                                if (zza7 == null) {
                                    hashSet.add(Integer.valueOf(intValue6));
                                } else {
                                    bitSet6.set(zze.zzbvl.intValue());
                                    if (zza7.booleanValue()) {
                                        bitSet5.set(zze.zzbvl.intValue());
                                    }
                                }
                            }
                        }
                        zzJt().zzLc().zze("Invalid property filter ID. appId, id", zzati.zzfI(str), String.valueOf(zze.zzbvl));
                        hashSet.add(Integer.valueOf(intValue6));
                    }
                }
            }
        }
        zzauh.zza[] zzaArr = new zzauh.zza[arrayMap2.size()];
        int i4 = 0;
        for (Integer intValue7 : arrayMap2.keySet()) {
            int intValue8 = intValue7.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue8))) {
                zzauh.zza zza8 = (zzauh.zza) arrayMap.get(Integer.valueOf(intValue8));
                if (zza8 == null) {
                    zza8 = new zzauh.zza();
                }
                zzauh.zza zza9 = zza8;
                zzaArr[i4] = zza9;
                zza9.zzbvh = Integer.valueOf(intValue8);
                zza9.zzbvR = new zzauh.zzf();
                zza9.zzbvR.zzbwD = zzaue.zza((BitSet) arrayMap2.get(Integer.valueOf(intValue8)));
                zza9.zzbvR.zzbwC = zzaue.zza((BitSet) arrayMap3.get(Integer.valueOf(intValue8)));
                zzJo().zza(str, intValue8, zza9.zzbvR);
                i4++;
            }
        }
        return (zzauh.zza[]) Arrays.copyOf(zzaArr, i4);
    }

    /* access modifiers changed from: protected */
    public void zzmr() {
    }
}
