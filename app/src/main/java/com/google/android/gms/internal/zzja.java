package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzix;
import com.google.android.gms.internal.zzqi;
import java.util.Map;
import p005b.p006a.p007a.p008a.p009a.p011b.AbstractSpiCall;

@zzmb
public class zzja {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String zzJd;
    /* access modifiers changed from: private */
    public zzpn<zzix> zzJe;
    private zzpn<zzix> zzJf;
    /* access modifiers changed from: private */
    @Nullable
    public zzd zzJg;
    /* access modifiers changed from: private */
    public int zzJh;
    /* access modifiers changed from: private */
    public final Object zzrN;
    /* access modifiers changed from: private */
    public final zzqa zztr;

    static class zza {
        static int zzJs = 60000;
        static int zzJt = AbstractSpiCall.DEFAULT_TIMEOUT;
    }

    public static class zzb<T> implements zzpn<T> {
        public void zzd(T t) {
        }
    }

    public static class zzc extends zzqj<zzjb> {
        /* access modifiers changed from: private */
        public final zzd zzJu;
        private boolean zzJv;
        private final Object zzrN = new Object();

        public zzc(zzd zzd) {
            this.zzJu = zzd;
        }

        public void release() {
            synchronized (this.zzrN) {
                if (!this.zzJv) {
                    this.zzJv = true;
                    zza(new zzqi.zzc<zzjb>(this) {
                        /* renamed from: zzb */
                        public void zzd(zzjb zzjb) {
                            zzpe.m2431v("Ending javascript session.");
                            ((zzjc) zzjb).zzgA();
                        }
                    }, new zzqi.zzb());
                    zza(new zzqi.zzc<zzjb>() {
                        /* renamed from: zzb */
                        public void zzd(zzjb zzjb) {
                            zzpe.m2431v("Releasing engine reference.");
                            zzc.this.zzJu.zzgx();
                        }
                    }, new zzqi.zza() {
                        public void run() {
                            zzc.this.zzJu.zzgx();
                        }
                    });
                }
            }
        }
    }

    public static class zzd extends zzqj<zzix> {
        /* access modifiers changed from: private */
        public zzpn<zzix> zzJf;
        private boolean zzJx;
        private int zzJy;
        private final Object zzrN = new Object();

        public zzd(zzpn<zzix> zzpn) {
            this.zzJf = zzpn;
            this.zzJx = false;
            this.zzJy = 0;
        }

        public zzc zzgw() {
            final zzc zzc = new zzc(this);
            synchronized (this.zzrN) {
                zza(new zzqi.zzc<zzix>(this) {
                    /* renamed from: zza */
                    public void zzd(zzix zzix) {
                        zzpe.m2431v("Getting a new session for JS Engine.");
                        zzc.zzg(zzix.zzgt());
                    }
                }, new zzqi.zza(this) {
                    public void run() {
                        zzpe.m2431v("Rejecting reference for JS Engine.");
                        zzc.reject();
                    }
                });
                zzac.zzar(this.zzJy >= 0);
                this.zzJy++;
            }
            return zzc;
        }

        /* access modifiers changed from: protected */
        public void zzgx() {
            boolean z = true;
            synchronized (this.zzrN) {
                if (this.zzJy < 1) {
                    z = false;
                }
                zzac.zzar(z);
                zzpe.m2431v("Releasing 1 reference for JS Engine");
                this.zzJy--;
                zzgz();
            }
        }

        public void zzgy() {
            boolean z = true;
            synchronized (this.zzrN) {
                if (this.zzJy < 0) {
                    z = false;
                }
                zzac.zzar(z);
                zzpe.m2431v("Releasing root reference. JS Engine will be destroyed once other references are released.");
                this.zzJx = true;
                zzgz();
            }
        }

        /* access modifiers changed from: protected */
        public void zzgz() {
            synchronized (this.zzrN) {
                zzac.zzar(this.zzJy >= 0);
                if (!this.zzJx || this.zzJy != 0) {
                    zzpe.m2431v("There are still references to the engine. Not destroying.");
                } else {
                    zzpe.m2431v("No reference is left (including root). Cleaning up engine.");
                    zza(new zzqi.zzc<zzix>() {
                        /* renamed from: zza */
                        public void zzd(final zzix zzix) {
                            zzv.zzcJ().runOnUiThread(new Runnable() {
                                public void run() {
                                    zzd.this.zzJf.zzd(zzix);
                                    zzix.destroy();
                                }
                            });
                        }
                    }, new zzqi.zzb());
                }
            }
        }
    }

    public static class zze extends zzqj<zzjb> {
        private zzc zzJD;

        public zze(zzc zzc) {
            this.zzJD = zzc;
        }

        public void finalize() {
            this.zzJD.release();
            this.zzJD = null;
        }

        public int getStatus() {
            return this.zzJD.getStatus();
        }

        public void reject() {
            this.zzJD.reject();
        }

        public void zza(zzqi.zzc<zzjb> zzc, zzqi.zza zza) {
            this.zzJD.zza(zzc, zza);
        }

        /* renamed from: zzf */
        public void zzg(zzjb zzjb) {
            this.zzJD.zzg(zzjb);
        }
    }

    public zzja(Context context, zzqa zzqa, String str) {
        this.zzrN = new Object();
        this.zzJh = 1;
        this.zzJd = str;
        this.mContext = context.getApplicationContext();
        this.zztr = zzqa;
        this.zzJe = new zzb();
        this.zzJf = new zzb();
    }

    public zzja(Context context, zzqa zzqa, String str, zzpn<zzix> zzpn, zzpn<zzix> zzpn2) {
        this(context, zzqa, str);
        this.zzJe = zzpn;
        this.zzJf = zzpn2;
    }

    private zzd zza(@Nullable final zzav zzav) {
        final zzd zzd2 = new zzd(this.zzJf);
        zzv.zzcJ().runOnUiThread(new Runnable() {
            public void run() {
                final zzix zza = zzja.this.zza(zzja.this.mContext, zzja.this.zztr, zzav);
                zza.zza(new zzix.zza() {
                    public void zzgu() {
                        zzpi.zzWR.postDelayed(new Runnable() {
                            /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                                return;
                             */
                            public void run() {
                                synchronized (zzja.this.zzrN) {
                                    if (zzd2.getStatus() != -1 && zzd2.getStatus() != 1) {
                                        zzd2.reject();
                                        zzv.zzcJ().runOnUiThread(new Runnable() {
                                            public void run() {
                                                zza.destroy();
                                            }
                                        });
                                        zzpe.m2431v("Could not receive loaded message in a timely manner. Rejecting.");
                                    }
                                }
                            }
                        }, (long) zza.zzJt);
                    }
                });
                zza.zza("/jsLoaded", (zzhx) new zzhx() {
                    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                        return;
                     */
                    public void zza(zzqp zzqp, Map<String, String> map) {
                        synchronized (zzja.this.zzrN) {
                            if (zzd2.getStatus() != -1 && zzd2.getStatus() != 1) {
                                int unused = zzja.this.zzJh = 0;
                                zzja.this.zzJe.zzd(zza);
                                zzd2.zzg(zza);
                                zzd unused2 = zzja.this.zzJg = zzd2;
                                zzpe.m2431v("Successfully loaded JS Engine.");
                            }
                        }
                    }
                });
                final zzpu zzpu = new zzpu();
                C12783 r2 = new zzhx() {
                    public void zza(zzqp zzqp, Map<String, String> map) {
                        synchronized (zzja.this.zzrN) {
                            zzpe.zzbd("JS Engine is requesting an update");
                            if (zzja.this.zzJh == 0) {
                                zzpe.zzbd("Starting reload.");
                                int unused = zzja.this.zzJh = 2;
                                zzja.this.zzb(zzav);
                            }
                            zza.zzb("/requestReload", (zzhx) zzpu.get());
                        }
                    }
                };
                zzpu.set(r2);
                zza.zza("/requestReload", (zzhx) r2);
                if (zzja.this.zzJd.endsWith(".js")) {
                    zza.zzal(zzja.this.zzJd);
                } else if (zzja.this.zzJd.startsWith("<html>")) {
                    zza.zzan(zzja.this.zzJd);
                } else {
                    zza.zzam(zzja.this.zzJd);
                }
                zzpi.zzWR.postDelayed(new Runnable() {
                    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                        return;
                     */
                    public void run() {
                        synchronized (zzja.this.zzrN) {
                            if (zzd2.getStatus() != -1 && zzd2.getStatus() != 1) {
                                zzd2.reject();
                                zzv.zzcJ().runOnUiThread(new Runnable() {
                                    public void run() {
                                        zza.destroy();
                                    }
                                });
                                zzpe.m2431v("Could not receive loaded message in a timely manner. Rejecting.");
                            }
                        }
                    }
                }, (long) zza.zzJs);
            }
        });
        return zzd2;
    }

    /* access modifiers changed from: protected */
    public zzix zza(Context context, zzqa zzqa, @Nullable zzav zzav) {
        return new zziz(context, zzqa, zzav, null);
    }

    /* access modifiers changed from: protected */
    public zzd zzb(@Nullable zzav zzav) {
        final zzd zza2 = zza(zzav);
        zza2.zza(new zzqi.zzc<zzix>() {
            /* renamed from: zza */
            public void zzd(zzix zzix) {
                synchronized (zzja.this.zzrN) {
                    int unused = zzja.this.zzJh = 0;
                    if (!(zzja.this.zzJg == null || zza2 == zzja.this.zzJg)) {
                        zzpe.m2431v("New JS engine is loaded, marking previous one as destroyable.");
                        zzja.this.zzJg.zzgy();
                    }
                    zzd unused2 = zzja.this.zzJg = zza2;
                }
            }
        }, new zzqi.zza() {
            public void run() {
                synchronized (zzja.this.zzrN) {
                    int unused = zzja.this.zzJh = 1;
                    zzpe.m2431v("Failed loading new engine. Marking new engine destroyable.");
                    zza2.zzgy();
                }
            }
        });
        return zza2;
    }

    public zzc zzc(@Nullable zzav zzav) {
        zzc zzc2;
        synchronized (this.zzrN) {
            if (this.zzJg == null || this.zzJg.getStatus() == -1) {
                this.zzJh = 2;
                this.zzJg = zzb(zzav);
                zzc2 = this.zzJg.zzgw();
            } else if (this.zzJh == 0) {
                zzc2 = this.zzJg.zzgw();
            } else if (this.zzJh == 1) {
                this.zzJh = 2;
                zzb(zzav);
                zzc2 = this.zzJg.zzgw();
            } else {
                zzc2 = this.zzJh == 2 ? this.zzJg.zzgw() : this.zzJg.zzgw();
            }
        }
        return zzc2;
    }

    public zzc zzgv() {
        return zzc((zzav) null);
    }
}
