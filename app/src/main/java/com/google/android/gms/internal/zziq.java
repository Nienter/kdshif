package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zznt;
import java.util.LinkedList;
import java.util.List;

@zzmb
class zziq {
    /* access modifiers changed from: private */
    public final List<zza> zztf = new LinkedList();

    interface zza {
        void zzb(zzir zzir);
    }

    zziq() {
    }

    /* access modifiers changed from: package-private */
    public void zza(final zzir zzir) {
        Handler handler = zzpi.zzWR;
        for (final zza next : this.zztf) {
            handler.post(new Runnable(this) {
                public void run() {
                    try {
                        next.zzb(zzir);
                    } catch (RemoteException e) {
                        zzpe.zzc("Could not propagate interstitial ad event.", e);
                    }
                }
            });
        }
        this.zztf.clear();
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzl zzl) {
        zzl.zza((zzel) new zzel.zza() {
            public void onAdClosed() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzti != null) {
                            zzir.zzti.onAdClosed();
                        }
                        zzv.zzcY().zzgj();
                    }
                });
            }

            public void onAdFailedToLoad(final int i) {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzti != null) {
                            zzir.zzti.onAdFailedToLoad(i);
                        }
                    }
                });
                zzpe.m2431v("Pooled interstitial failed to load.");
            }

            public void onAdLeftApplication() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzti != null) {
                            zzir.zzti.onAdLeftApplication();
                        }
                    }
                });
            }

            public void onAdLoaded() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzti != null) {
                            zzir.zzti.onAdLoaded();
                        }
                    }
                });
                zzpe.m2431v("Pooled interstitial loaded.");
            }

            public void onAdOpened() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzti != null) {
                            zzir.zzti.onAdOpened();
                        }
                    }
                });
            }
        });
        zzl.zza((zzer) new zzer.zza() {
            public void onAppEvent(final String str, final String str2) {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIt != null) {
                            zzir.zzIt.onAppEvent(str, str2);
                        }
                    }
                });
            }
        });
        zzl.zza((zzkz) new zzkz.zza() {
            public void zza(final zzky zzky) {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIu != null) {
                            zzir.zzIu.zza(zzky);
                        }
                    }
                });
            }
        });
        zzl.zza((zzgj) new zzgj.zza() {
            public void zza(final zzgi zzgi) {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIv != null) {
                            zzir.zzIv.zza(zzgi);
                        }
                    }
                });
            }
        });
        zzl.zza((zzek) new zzek.zza() {
            public void onAdClicked() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIw != null) {
                            zzir.zzIw.onAdClicked();
                        }
                    }
                });
            }
        });
        zzl.zza((zznt) new zznt.zza() {
            public void onRewardedVideoAdClosed() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIx != null) {
                            zzir.zzIx.onRewardedVideoAdClosed();
                        }
                    }
                });
            }

            public void onRewardedVideoAdFailedToLoad(final int i) {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIx != null) {
                            zzir.zzIx.onRewardedVideoAdFailedToLoad(i);
                        }
                    }
                });
            }

            public void onRewardedVideoAdLeftApplication() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIx != null) {
                            zzir.zzIx.onRewardedVideoAdLeftApplication();
                        }
                    }
                });
            }

            public void onRewardedVideoAdLoaded() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIx != null) {
                            zzir.zzIx.onRewardedVideoAdLoaded();
                        }
                    }
                });
            }

            public void onRewardedVideoAdOpened() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIx != null) {
                            zzir.zzIx.onRewardedVideoAdOpened();
                        }
                    }
                });
            }

            public void onRewardedVideoStarted() {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIx != null) {
                            zzir.zzIx.onRewardedVideoStarted();
                        }
                    }
                });
            }

            public void zza(final zznq zznq) {
                zziq.this.zztf.add(new zza(this) {
                    public void zzb(zzir zzir) {
                        if (zzir.zzIx != null) {
                            zzir.zzIx.zza(zznq);
                        }
                    }
                });
            }
        });
    }
}
