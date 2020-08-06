package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zzaQd;
    /* access modifiers changed from: private */
    public Bundle zzaQe;
    /* access modifiers changed from: private */
    public LinkedList<C1695zza> zzaQf;
    private final zzf<T> zzaQg = new zzf<T>() {
        public void zza(T t) {
            LifecycleDelegate unused = zza.this.zzaQd = t;
            Iterator it = zza.this.zzaQf.iterator();
            while (it.hasNext()) {
                ((C1695zza) it.next()).zzb(zza.this.zzaQd);
            }
            zza.this.zzaQf.clear();
            Bundle unused2 = zza.this.zzaQe = null;
        }
    };

    /* renamed from: com.google.android.gms.dynamic.zza$zza  reason: collision with other inner class name */
    private interface C1695zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    private void zza(Bundle bundle, C1695zza zza) {
        if (this.zzaQd != null) {
            zza.zzb(this.zzaQd);
            return;
        }
        if (this.zzaQf == null) {
            this.zzaQf = new LinkedList<>();
        }
        this.zzaQf.add(zza);
        if (bundle != null) {
            if (this.zzaQe == null) {
                this.zzaQe = (Bundle) bundle.clone();
            } else {
                this.zzaQe.putAll(bundle);
            }
        }
        zza(this.zzaQg);
    }

    @VisibleForTesting
    static void zza(FrameLayout frameLayout, GoogleApiAvailability googleApiAvailability) {
        final Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String zzi = zzh.zzi(context, isGooglePlayServicesAvailable);
        String zzk = zzh.zzk(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zzi);
        linearLayout.addView(textView);
        final Intent zzb = googleApiAvailability.zzb(context, isGooglePlayServicesAvailable, null);
        if (zzb != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zzk);
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    try {
                        context.startActivity(zzb);
                    } catch (ActivityNotFoundException e) {
                        Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
                    }
                }
            });
        }
    }

    public static void zzb(FrameLayout frameLayout) {
        zza(frameLayout, GoogleApiAvailability.getInstance());
    }

    private void zzgk(int i) {
        while (!this.zzaQf.isEmpty() && this.zzaQf.getLast().getState() >= i) {
            this.zzaQf.removeLast();
        }
    }

    public void onCreate(final Bundle bundle) {
        zza(bundle, (C1695zza) new C1695zza() {
            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaQd.onCreate(bundle);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        zza(bundle, (C1695zza) new C1695zza() {
            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(zza.this.zzaQd.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }
        });
        if (this.zzaQd == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.zzaQd != null) {
            this.zzaQd.onDestroy();
        } else {
            zzgk(1);
        }
    }

    public void onDestroyView() {
        if (this.zzaQd != null) {
            this.zzaQd.onDestroyView();
        } else {
            zzgk(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        zza(bundle2, (C1695zza) new C1695zza() {
            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaQd.onInflate(activity, bundle, bundle2);
            }
        });
    }

    public void onLowMemory() {
        if (this.zzaQd != null) {
            this.zzaQd.onLowMemory();
        }
    }

    public void onPause() {
        if (this.zzaQd != null) {
            this.zzaQd.onPause();
        } else {
            zzgk(5);
        }
    }

    public void onResume() {
        zza((Bundle) null, (C1695zza) new C1695zza() {
            public int getState() {
                return 5;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaQd.onResume();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.zzaQd != null) {
            this.zzaQd.onSaveInstanceState(bundle);
        } else if (this.zzaQe != null) {
            bundle.putAll(this.zzaQe);
        }
    }

    public void onStart() {
        zza((Bundle) null, (C1695zza) new C1695zza() {
            public int getState() {
                return 4;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaQd.onStart();
            }
        });
    }

    public void onStop() {
        if (this.zzaQd != null) {
            this.zzaQd.onStop();
        } else {
            zzgk(4);
        }
    }

    public T zzAY() {
        return this.zzaQd;
    }

    /* access modifiers changed from: protected */
    public void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzf<T> zzf);
}
