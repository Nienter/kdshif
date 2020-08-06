package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import com.google.android.gms.internal.zzdh;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzmb
public class zzde {
    private final int zzyc;
    private final int zzyd;
    private final int zzye;
    private final zzdd zzyf = new zzdg();

    static class zza {
        ByteArrayOutputStream zzyg = new ByteArrayOutputStream(4096);
        Base64OutputStream zzyh = new Base64OutputStream(this.zzyg, 10);

        public String toString() {
            String str;
            try {
                this.zzyh.close();
            } catch (IOException e) {
                zzpe.zzb("HashManager: Unable to convert to Base64.", e);
            }
            try {
                this.zzyg.close();
                str = this.zzyg.toString();
            } catch (IOException e2) {
                zzpe.zzb("HashManager: Unable to convert to Base64.", e2);
                str = "";
            } finally {
                this.zzyg = null;
                this.zzyh = null;
            }
            return str;
        }

        public void write(byte[] bArr) {
            this.zzyh.write(bArr);
        }
    }

    public zzde(int i) {
        this.zzyd = i;
        this.zzyc = 6;
        this.zzye = 0;
    }

    /* access modifiers changed from: package-private */
    public String zzG(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        zza zzep = zzep();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzyd, new Comparator<zzdh.zza>(this) {
            /* renamed from: zza */
            public int compare(zzdh.zza zza, zzdh.zza zza2) {
                int i = zza.zzyk - zza2.zzyk;
                return i != 0 ? i : (int) (zza.value - zza2.value);
            }
        });
        for (String zzI : split) {
            String[] zzI2 = zzdf.zzI(zzI);
            if (zzI2.length != 0) {
                zzdh.zza(zzI2, this.zzyd, this.zzyc, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzep.write(this.zzyf.zzF(((zzdh.zza) it.next()).zzyj));
            } catch (IOException e) {
                zzpe.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzep.toString();
    }

    public String zza(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toLowerCase(Locale.US));
            stringBuffer.append(10);
        }
        return zzG(stringBuffer.toString());
    }

    /* access modifiers changed from: package-private */
    public zza zzep() {
        return new zza();
    }
}
