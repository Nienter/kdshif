package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.applinks.AppLinkData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

@zzmb
class zzit {
    private final Object[] mParams;

    zzit(zzdy zzdy, String str, int i) {
        this.mParams = zza(zzdy, str, i);
    }

    private static Object[] zza(zzdy zzdy, String str, int i) {
        HashSet hashSet = new HashSet(Arrays.asList(zzfx.zzCC.get().split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(zzdy.zzyF));
        }
        if (hashSet.contains(AppLinkData.ARGUMENTS_EXTRAS_KEY)) {
            arrayList.add(zzc(zzdy.extras));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(zzdy.zzyG));
        }
        if (hashSet.contains("keywords")) {
            if (zzdy.zzyH != null) {
                arrayList.add(zzdy.zzyH.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(zzdy.zzyI));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(zzdy.zzyJ));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(zzdy.zzyK));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(zzdy.zzyL);
        }
        if (hashSet.contains("location")) {
            if (zzdy.zzyN != null) {
                arrayList.add(zzdy.zzyN.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(zzdy.zzyO);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zzc(zzdy.zzyP));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zzc(zzdy.zzyQ));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (zzdy.zzyR != null) {
                arrayList.add(zzdy.zzyR.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(zzdy.zzyS);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(zzdy.zzyT);
        }
        return arrayList.toArray();
    }

    private static String zzc(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            sb.append(obj == null ? "null" : obj instanceof Bundle ? zzc((Bundle) obj) : obj.toString());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzit)) {
            return false;
        }
        return Arrays.equals(this.mParams, ((zzit) obj).mParams);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mParams);
    }

    public String toString() {
        String valueOf = String.valueOf(Arrays.toString(this.mParams));
        return new StringBuilder(String.valueOf(valueOf).length() + 24).append("[InterstitialAdPoolKey ").append(valueOf).append("]").toString();
    }
}
