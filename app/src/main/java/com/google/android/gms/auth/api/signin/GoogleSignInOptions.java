package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzf;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends zza implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zzb();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    private static Comparator<Scope> zzajc = new Comparator<Scope>() {
        /* renamed from: zza */
        public int compare(Scope scope, Scope scope2) {
            return scope.zzuS().compareTo(scope2.zzuS());
        }
    };
    public static final Scope zzajd = new Scope(Scopes.PROFILE);
    public static final Scope zzaje = new Scope("email");
    public static final Scope zzajf = new Scope("openid");
    final int versionCode;
    /* access modifiers changed from: private */
    public Account zzagg;
    /* access modifiers changed from: private */
    public final ArrayList<Scope> zzajg;
    /* access modifiers changed from: private */
    public boolean zzajh;
    /* access modifiers changed from: private */
    public final boolean zzaji;
    /* access modifiers changed from: private */
    public final boolean zzajj;
    /* access modifiers changed from: private */
    public String zzajk;
    /* access modifiers changed from: private */
    public String zzajl;

    public static final class Builder {
        private Account zzagg;
        private boolean zzajh;
        private boolean zzaji;
        private boolean zzajj;
        private String zzajk;
        private String zzajl;
        private Set<Scope> zzajm = new HashSet();

        public Builder() {
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            zzac.zzw(googleSignInOptions);
            this.zzajm = new HashSet(googleSignInOptions.zzajg);
            this.zzaji = googleSignInOptions.zzaji;
            this.zzajj = googleSignInOptions.zzajj;
            this.zzajh = googleSignInOptions.zzajh;
            this.zzajk = googleSignInOptions.zzajk;
            this.zzagg = googleSignInOptions.zzagg;
            this.zzajl = googleSignInOptions.zzajl;
        }

        private String zzcx(String str) {
            zzac.zzdv(str);
            zzac.zzb(this.zzajk == null || this.zzajk.equals(str), (Object) "two different server client ids provided");
            return str;
        }

        public GoogleSignInOptions build() {
            if (this.zzajh && (this.zzagg == null || !this.zzajm.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions((Set) this.zzajm, this.zzagg, this.zzajh, this.zzaji, this.zzajj, this.zzajk, this.zzajl);
        }

        public Builder requestEmail() {
            this.zzajm.add(GoogleSignInOptions.zzaje);
            return this;
        }

        public Builder requestId() {
            this.zzajm.add(GoogleSignInOptions.zzajf);
            return this;
        }

        public Builder requestIdToken(String str) {
            this.zzajh = true;
            this.zzajk = zzcx(str);
            return this;
        }

        public Builder requestProfile() {
            this.zzajm.add(GoogleSignInOptions.zzajd);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zzajm.add(scope);
            this.zzajm.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public Builder requestServerAuthCode(String str, boolean z) {
            this.zzaji = true;
            this.zzajk = zzcx(str);
            this.zzajj = z;
            return this;
        }

        public Builder setAccountName(String str) {
            this.zzagg = new Account(zzac.zzdv(str), "com.google");
            return this;
        }

        public Builder setHostedDomain(String str) {
            this.zzajl = zzac.zzdv(str);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this.versionCode = i;
        this.zzajg = arrayList;
        this.zzagg = account;
        this.zzajh = z;
        this.zzaji = z2;
        this.zzajj = z3;
        this.zzajk = str;
        this.zzajl = str2;
    }

    private GoogleSignInOptions(Set<Scope> set, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this(2, (ArrayList<Scope>) new ArrayList(set), account, z, z2, z3, str, str2);
    }

    @Nullable
    public static GoogleSignInOptions zzcw(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(hashSet, !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null));
    }

    private JSONObject zzqI() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzajg, zzajc);
            Iterator<Scope> it = this.zzajg.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().zzuS());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zzagg != null) {
                jSONObject.put("accountName", this.zzagg.name);
            }
            jSONObject.put("idTokenRequested", this.zzajh);
            jSONObject.put("forceCodeForRefreshToken", this.zzajj);
            jSONObject.put("serverAuthRequested", this.zzaji);
            if (!TextUtils.isEmpty(this.zzajk)) {
                jSONObject.put("serverClientId", this.zzajk);
            }
            if (!TextUtils.isEmpty(this.zzajl)) {
                jSONObject.put("hostedDomain", this.zzajl);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zzajg.size() != googleSignInOptions.zzqJ().size() || !this.zzajg.containsAll(googleSignInOptions.zzqJ())) {
                return false;
            }
            if (this.zzagg == null) {
                if (googleSignInOptions.getAccount() != null) {
                    return false;
                }
            } else if (!this.zzagg.equals(googleSignInOptions.getAccount())) {
                return false;
            }
            if (TextUtils.isEmpty(this.zzajk)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzqN())) {
                    return false;
                }
            } else if (!this.zzajk.equals(googleSignInOptions.zzqN())) {
                return false;
            }
            return this.zzajj == googleSignInOptions.zzqM() && this.zzajh == googleSignInOptions.zzqK() && this.zzaji == googleSignInOptions.zzqL();
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Account getAccount() {
        return this.zzagg;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.zzajg.toArray(new Scope[this.zzajg.size()]);
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        Iterator<Scope> it = this.zzajg.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().zzuS());
        }
        Collections.sort(arrayList);
        return new zzf().zzq(arrayList).zzq(this.zzagg).zzq(this.zzajk).zzad(this.zzajj).zzad(this.zzajh).zzad(this.zzaji).zzqV();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public String zzqG() {
        return zzqI().toString();
    }

    public ArrayList<Scope> zzqJ() {
        return new ArrayList<>(this.zzajg);
    }

    public boolean zzqK() {
        return this.zzajh;
    }

    public boolean zzqL() {
        return this.zzaji;
    }

    public boolean zzqM() {
        return this.zzajj;
    }

    public String zzqN() {
        return this.zzajk;
    }

    public String zzqO() {
        return this.zzajl;
    }
}
