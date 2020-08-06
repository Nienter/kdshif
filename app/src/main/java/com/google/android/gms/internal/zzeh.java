package com.google.android.gms.internal;

@zzmb
public class zzeh {
    private static final Object zztU = new Object();
    private static zzeh zzzF;
    private final zzpx zzzG = new zzpx();
    private final zzeg zzzH = new zzeg(new zzdx(), new zzdw(), new zzfd(), new zzhn(), new zznv(), new zzlf(), new zzkq());

    static {
        zza(new zzeh());
    }

    protected zzeh() {
    }

    protected static void zza(zzeh zzeh) {
        synchronized (zztU) {
            zzzF = zzeh;
        }
    }

    private static zzeh zzeN() {
        zzeh zzeh;
        synchronized (zztU) {
            zzeh = zzzF;
        }
        return zzeh;
    }

    public static zzpx zzeO() {
        return zzeN().zzzG;
    }

    public static zzeg zzeP() {
        return zzeN().zzzH;
    }
}
