package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.zzc;

@SuppressLint({"NewApi"})
public final class zzb extends zzc.zza {
    private Fragment zzaQn;

    private zzb(Fragment fragment) {
        this.zzaQn = fragment;
    }

    public static zzb zza(Fragment fragment) {
        if (fragment != null) {
            return new zzb(fragment);
        }
        return null;
    }

    public Bundle getArguments() {
        return this.zzaQn.getArguments();
    }

    public int getId() {
        return this.zzaQn.getId();
    }

    public boolean getRetainInstance() {
        return this.zzaQn.getRetainInstance();
    }

    public String getTag() {
        return this.zzaQn.getTag();
    }

    public int getTargetRequestCode() {
        return this.zzaQn.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.zzaQn.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzA(this.zzaQn.getView());
    }

    public boolean isAdded() {
        return this.zzaQn.isAdded();
    }

    public boolean isDetached() {
        return this.zzaQn.isDetached();
    }

    public boolean isHidden() {
        return this.zzaQn.isHidden();
    }

    public boolean isInLayout() {
        return this.zzaQn.isInLayout();
    }

    public boolean isRemoving() {
        return this.zzaQn.isRemoving();
    }

    public boolean isResumed() {
        return this.zzaQn.isResumed();
    }

    public boolean isVisible() {
        return this.zzaQn.isVisible();
    }

    public void setHasOptionsMenu(boolean z) {
        this.zzaQn.setHasOptionsMenu(z);
    }

    public void setMenuVisibility(boolean z) {
        this.zzaQn.setMenuVisibility(z);
    }

    public void setRetainInstance(boolean z) {
        this.zzaQn.setRetainInstance(z);
    }

    public void setUserVisibleHint(boolean z) {
        this.zzaQn.setUserVisibleHint(z);
    }

    public void startActivity(Intent intent) {
        this.zzaQn.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.zzaQn.startActivityForResult(intent, i);
    }

    public zzd zzAZ() {
        return zze.zzA(this.zzaQn.getActivity());
    }

    public zzc zzBa() {
        return zza(this.zzaQn.getParentFragment());
    }

    public zzd zzBb() {
        return zze.zzA(this.zzaQn.getResources());
    }

    public zzc zzBc() {
        return zza(this.zzaQn.getTargetFragment());
    }

    public void zzC(zzd zzd) {
        this.zzaQn.registerForContextMenu((View) zze.zzE(zzd));
    }

    public void zzD(zzd zzd) {
        this.zzaQn.unregisterForContextMenu((View) zze.zzE(zzd));
    }
}
