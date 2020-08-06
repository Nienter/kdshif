package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzaaw {
    protected final zzaax zzaBs;

    protected zzaaw(zzaax zzaax) {
        this.zzaBs = zzaax;
    }

    protected static zzaax zzc(zzaav zzaav) {
        return zzaav.zzwl() ? zzabm.zza(zzaav.zzwn()) : zzaay.zzt(zzaav.zzwm());
    }

    public static zzaax zzs(Activity activity) {
        return zzc(new zzaav(activity));
    }

    @MainThread
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public Activity getActivity() {
        return this.zzaBs.zzwo();
    }

    @MainThread
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @MainThread
    public void onCreate(Bundle bundle) {
    }

    @MainThread
    public void onDestroy() {
    }

    @MainThread
    public void onSaveInstanceState(Bundle bundle) {
    }

    @MainThread
    public void onStart() {
    }

    @MainThread
    public void onStop() {
    }
}
