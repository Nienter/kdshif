package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzaxo;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzg {
    private final Set<Scope> zzaEb;
    private final Map<Api<?>, zza> zzaEc;
    private final zzaxo zzaEd;
    private Integer zzaEe;
    private final Account zzagg;
    private final String zzahp;
    private final Set<Scope> zzaxN;
    private final int zzaxP;
    private final View zzaxQ;
    private final String zzaxR;

    public static final class zza {
        public final boolean zzaEf;
        public final Set<Scope> zzajm;

        public zza(Set<Scope> set, boolean z) {
            zzac.zzw(set);
            this.zzajm = Collections.unmodifiableSet(set);
            this.zzaEf = z;
        }
    }

    public zzg(Account account, Set<Scope> set, Map<Api<?>, zza> map, int i, View view, String str, String str2, zzaxo zzaxo) {
        this.zzagg = account;
        this.zzaxN = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        this.zzaEc = map == null ? Collections.EMPTY_MAP : map;
        this.zzaxQ = view;
        this.zzaxP = i;
        this.zzahp = str;
        this.zzaxR = str2;
        this.zzaEd = zzaxo;
        HashSet hashSet = new HashSet(this.zzaxN);
        for (zza zza2 : this.zzaEc.values()) {
            hashSet.addAll(zza2.zzajm);
        }
        this.zzaEb = Collections.unmodifiableSet(hashSet);
    }

    public static zzg zzaA(Context context) {
        return new GoogleApiClient.Builder(context).zzuP();
    }

    public Account getAccount() {
        return this.zzagg;
    }

    @Deprecated
    public String getAccountName() {
        if (this.zzagg != null) {
            return this.zzagg.name;
        }
        return null;
    }

    public Set<Scope> zzc(Api<?> api) {
        zza zza2 = this.zzaEc.get(api);
        if (zza2 == null || zza2.zzajm.isEmpty()) {
            return this.zzaxN;
        }
        HashSet hashSet = new HashSet(this.zzaxN);
        hashSet.addAll(zza2.zzajm);
        return hashSet;
    }

    public void zzc(Integer num) {
        this.zzaEe = num;
    }

    public Account zzwU() {
        return this.zzagg != null ? this.zzagg : new Account("<<default account>>", "com.google");
    }

    public int zzxd() {
        return this.zzaxP;
    }

    public Set<Scope> zzxe() {
        return this.zzaxN;
    }

    public Set<Scope> zzxf() {
        return this.zzaEb;
    }

    public Map<Api<?>, zza> zzxg() {
        return this.zzaEc;
    }

    public String zzxh() {
        return this.zzahp;
    }

    public String zzxi() {
        return this.zzaxR;
    }

    public View zzxj() {
        return this.zzaxQ;
    }

    public zzaxo zzxk() {
        return this.zzaEd;
    }

    public Integer zzxl() {
        return this.zzaEe;
    }
}
