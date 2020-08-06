package com.google.android.gms.internal;

import com.google.android.gms.internal.zzde;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzmb
public class zzdj {
    private final int zzyd;
    private final zzdd zzyf;
    private String zzyn;
    private String zzyo;
    private final boolean zzyp = false;
    private final int zzyq;
    private final int zzyr;

    public class zza implements Comparator<zzdc> {
        public zza(zzdj zzdj) {
        }

        /* renamed from: zza */
        public int compare(zzdc zzdc, zzdc zzdc2) {
            if (zzdc.zzek() < zzdc2.zzek()) {
                return -1;
            }
            if (zzdc.zzek() > zzdc2.zzek()) {
                return 1;
            }
            if (zzdc.zzej() < zzdc2.zzej()) {
                return -1;
            }
            if (zzdc.zzej() > zzdc2.zzej()) {
                return 1;
            }
            float zzem = (zzdc.zzem() - zzdc.zzek()) * (zzdc.zzel() - zzdc.zzej());
            float zzem2 = (zzdc2.zzem() - zzdc2.zzek()) * (zzdc2.zzel() - zzdc2.zzej());
            if (zzem <= zzem2) {
                return zzem < zzem2 ? 1 : 0;
            }
            return -1;
        }
    }

    public zzdj(int i, int i2, int i3) {
        this.zzyd = i;
        if (i2 > 64 || i2 < 0) {
            this.zzyq = 64;
        } else {
            this.zzyq = i2;
        }
        if (i3 < 1) {
            this.zzyr = 1;
        } else {
            this.zzyr = i3;
        }
        this.zzyf = new zzdi(this.zzyq);
    }

    /* access modifiers changed from: package-private */
    public String zza(String str, char c) {
        StringBuilder sb = new StringBuilder(str);
        boolean z = false;
        int i = 1;
        while (i + 2 <= sb.length()) {
            if (sb.charAt(i) == '\'') {
                if (sb.charAt(i - 1) == c || !((sb.charAt(i + 1) == 's' || sb.charAt(i + 1) == 'S') && (i + 2 == sb.length() || sb.charAt(i + 2) == c))) {
                    sb.setCharAt(i, c);
                } else {
                    sb.insert(i, c);
                    i += 2;
                }
                z = true;
            }
            i++;
        }
        if (z) {
            return sb.toString();
        }
        return null;
    }

    public String zza(ArrayList<String> arrayList, ArrayList<zzdc> arrayList2) {
        Collections.sort(arrayList2, new zza(this));
        HashSet hashSet = new HashSet();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= arrayList2.size() || !zza(Normalizer.normalize(arrayList.get(arrayList2.get(i2).zzen()), Normalizer.Form.NFKC).toLowerCase(Locale.US), (HashSet<String>) hashSet)) {
                zzde.zza zza2 = new zzde.zza();
                this.zzyn = "";
                Iterator it = hashSet.iterator();
            } else {
                i = i2 + 1;
            }
        }
        zzde.zza zza22 = new zzde.zza();
        this.zzyn = "";
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            try {
                zza22.write(this.zzyf.zzF((String) it2.next()));
            } catch (IOException e) {
                zzpe.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zza22.toString();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x002f A[SYNTHETIC] */
    public boolean zza(String str, HashSet<String> hashSet) {
        String str2;
        String[] zzd;
        boolean z;
        String[] split = str.split("\n");
        if (split.length == 0) {
            return true;
        }
        for (String str3 : split) {
            if (str3.indexOf("'") != -1) {
                str2 = zza(str3, ' ');
                if (str2 != null) {
                    this.zzyo = str2;
                    zzd = zzdf.zzd(str2, true);
                    if (zzd.length < this.zzyr) {
                        for (int i = 0; i < zzd.length; i++) {
                            String str4 = "";
                            int i2 = 0;
                            while (true) {
                                if (i2 >= this.zzyr) {
                                    z = true;
                                    break;
                                } else if (i + i2 >= zzd.length) {
                                    z = false;
                                    break;
                                } else {
                                    if (i2 > 0) {
                                        str4 = String.valueOf(str4).concat(" ");
                                    }
                                    String valueOf = String.valueOf(str4);
                                    String valueOf2 = String.valueOf(zzd[i + i2]);
                                    str4 = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                                    i2++;
                                }
                            }
                            if (!z) {
                                break;
                            }
                            hashSet.add(str4);
                            if (hashSet.size() >= this.zzyd) {
                                return false;
                            }
                        }
                        if (hashSet.size() >= this.zzyd) {
                            return false;
                        }
                    }
                }
            }
            str2 = str3;
            zzd = zzdf.zzd(str2, true);
            if (zzd.length < this.zzyr) {
            }
        }
        return true;
    }
}
