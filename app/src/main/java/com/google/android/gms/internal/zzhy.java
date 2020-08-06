package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.google.android.gms.ads.internal.zzv;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzmb
public class zzhy implements zzhx {
    private final Context mContext;
    private final zzqa zztr;

    @zzmb
    static class zza {
        private final String mValue;
        private final String zzAH;

        public zza(String str, String str2) {
            this.zzAH = str;
            this.mValue = str2;
        }

        public String getKey() {
            return this.zzAH;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @zzmb
    static class zzb {
        private final String zzHC;
        private final URL zzHD;
        private final ArrayList<zza> zzHE;
        private final String zzHF;

        public zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzHC = str;
            this.zzHD = url;
            if (arrayList == null) {
                this.zzHE = new ArrayList<>();
            } else {
                this.zzHE = arrayList;
            }
            this.zzHF = str2;
        }

        public String zzfZ() {
            return this.zzHC;
        }

        public URL zzga() {
            return this.zzHD;
        }

        public ArrayList<zza> zzgb() {
            return this.zzHE;
        }

        public String zzgc() {
            return this.zzHF;
        }
    }

    @zzmb
    class zzc {
        private final zzd zzHG;
        private final boolean zzHH;
        private final String zzHI;

        public zzc(zzhy zzhy, boolean z, zzd zzd, String str) {
            this.zzHH = z;
            this.zzHG = zzd;
            this.zzHI = str;
        }

        public String getReason() {
            return this.zzHI;
        }

        public boolean isSuccess() {
            return this.zzHH;
        }

        public zzd zzgd() {
            return this.zzHG;
        }
    }

    @zzmb
    static class zzd {
        private final String zzFU;
        private final String zzHC;
        private final int zzHJ;
        private final List<zza> zzHK;

        public zzd(String str, int i, List<zza> list, String str2) {
            this.zzHC = str;
            this.zzHJ = i;
            if (list == null) {
                this.zzHK = new ArrayList();
            } else {
                this.zzHK = list;
            }
            this.zzFU = str2;
        }

        public String getBody() {
            return this.zzFU;
        }

        public int getResponseCode() {
            return this.zzHJ;
        }

        public String zzfZ() {
            return this.zzHC;
        }

        public Iterable<zza> zzge() {
            return this.zzHK;
        }
    }

    public zzhy(Context context, zzqa zzqa) {
        this.mContext = context;
        this.zztr = zzqa;
    }

    /* access modifiers changed from: protected */
    public zzc zza(zzb zzb2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) zzb2.zzga().openConnection();
            zzv.zzcJ().zza(this.mContext, this.zztr.zzaZ, false, httpURLConnection);
            Iterator<zza> it = zzb2.zzgb().iterator();
            while (it.hasNext()) {
                zza next = it.next();
                httpURLConnection.addRequestProperty(next.getKey(), next.getValue());
            }
            if (!TextUtils.isEmpty(zzb2.zzgc())) {
                httpURLConnection.setDoOutput(true);
                byte[] bytes = zzb2.zzgc().getBytes();
                httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
            }
            ArrayList arrayList = new ArrayList();
            if (httpURLConnection.getHeaderFields() != null) {
                for (Map.Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
                    for (String zza2 : (List) entry.getValue()) {
                        arrayList.add(new zza((String) entry.getKey(), zza2));
                    }
                }
            }
            return new zzc(this, true, new zzd(zzb2.zzfZ(), httpURLConnection.getResponseCode(), arrayList, zzv.zzcJ().zza(new InputStreamReader(httpURLConnection.getInputStream()))), null);
        } catch (Exception e) {
            return new zzc(this, false, null, e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject zza(zzd zzd2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", zzd2.zzfZ());
            if (zzd2.getBody() != null) {
                jSONObject.put("body", zzd2.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza next : zzd2.zzge()) {
                jSONArray.put(new JSONObject().put("key", next.getKey()).put(FirebaseAnalytics.Param.VALUE, next.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", zzd2.getResponseCode());
        } catch (JSONException e) {
            zzpe.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    public void zza(final zzqp zzqp, final Map<String, String> map) {
        zzph.zza((Runnable) new Runnable() {
            public void run() {
                zzpe.zzbc("Received Http request.");
                final JSONObject zzaa = zzhy.this.zzaa((String) map.get("http_request"));
                if (zzaa == null) {
                    zzpe.m2432e("Response should not be null.");
                } else {
                    zzpi.zzWR.post(new Runnable() {
                        public void run() {
                            zzqp.zzb("fetchHttpRequestCompleted", zzaa);
                            zzpe.zzbc("Dispatched http response.");
                        }
                    });
                }
            }
        });
    }

    public JSONObject zzaa(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            String str2 = "";
            try {
                str2 = jSONObject.optString("http_request_id");
                zzc zza2 = zza(zzb(jSONObject));
                if (zza2.isSuccess()) {
                    jSONObject2.put("response", zza(zza2.zzgd()));
                    jSONObject2.put(GraphResponse.SUCCESS_KEY, true);
                    return jSONObject2;
                }
                jSONObject2.put("response", new JSONObject().put("http_request_id", str2));
                jSONObject2.put(GraphResponse.SUCCESS_KEY, false);
                jSONObject2.put("reason", zza2.getReason());
                return jSONObject2;
            } catch (Exception e) {
                try {
                    jSONObject2.put("response", new JSONObject().put("http_request_id", str2));
                    jSONObject2.put(GraphResponse.SUCCESS_KEY, false);
                    jSONObject2.put("reason", e.toString());
                    return jSONObject2;
                } catch (JSONException e2) {
                    return jSONObject2;
                }
            }
        } catch (JSONException e3) {
            zzpe.m2432e("The request is not a valid JSON.");
            try {
                return new JSONObject().put(GraphResponse.SUCCESS_KEY, false);
            } catch (JSONException e4) {
                return new JSONObject();
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzb zzb(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        String optString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(optString2);
        } catch (MalformedURLException e) {
            zzpe.zzb("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString("key"), optJSONObject.optString(FirebaseAnalytics.Param.VALUE)));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }
}
