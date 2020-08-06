package com.snaperfect.style.daguerre.frame;

import android.content.res.Resources;
import android.support.annotation.Nullable;

import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.math.CGSize;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;

/* renamed from: com.snaperfect.style.daguerre.frame.c */
public class CollageFrame {

    /* renamed from: e */
    private static HashMap<String, CollageFrame> f2081e = null;

    /* renamed from: a */
    public final CGSize f2082a;

    /* renamed from: b */
    public final CollageFrameTile[] f2083b;

    /* renamed from: c */
    public final int f2084c;

    /* renamed from: d */
    private float f2085d;

    /* renamed from: a */
    private static CollageFrameTile[] m2813a(JSONArray jSONArray) {
        CollageFrameTile[] dVarArr = new CollageFrameTile[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            dVarArr[i] = new CollageFrameTile(jSONArray.getJSONObject(i));
        }
        return dVarArr;
    }

    /* renamed from: a */
    private static CollageFrameTile[] m2814a(CollageFrameTile[] dVarArr, CGSize cGSize) {
        CollageFrameTile[] dVarArr2 = new CollageFrameTile[dVarArr.length];
        for (int i = 0; i < dVarArr.length; i++) {
            dVarArr2[i] = new CollageFrameTile(dVarArr[i], cGSize);
        }
        return dVarArr2;
    }

    public CollageFrame(JSONObject jSONObject) {
        CollageFrameTile[] dVarArr;
        Throwable th;
        CGSize cGSize;
        CGSize cGSize2 = AspectRatioType.f2080b[0];
        CollageFrameTile[] dVarArr2 = new CollageFrameTile[0];
        try {
            cGSize = new CGSize(jSONObject.getString("size"));
            try {
                dVarArr2 = m2813a(jSONObject.getJSONArray("tiles"));
                int i = jSONObject.getInt("ratio");
                this.f2082a = cGSize;
                this.f2083b = dVarArr2;
                this.f2084c = i;
            } catch (JSONException e) {
                this.f2082a = cGSize;
                this.f2083b = dVarArr2;
                this.f2084c = 0;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                cGSize2 = cGSize;
                dVarArr = dVarArr2;
                th = th3;
                this.f2082a = cGSize2;
                this.f2083b = dVarArr;
                this.f2084c = 0;
                throw th;
            }
        } catch (JSONException e2) {
            cGSize = cGSize2;
            this.f2082a = cGSize;
            this.f2083b = dVarArr2;
            this.f2084c = 0;
        } catch (Throwable th4) {
            Throwable th5 = th4;
            dVarArr = dVarArr2;
            th = th5;
            this.f2082a = cGSize2;
            this.f2083b = dVarArr;
            this.f2084c = 0;
            throw th;
        }
    }

    public CollageFrame(CollageFrame cVar, int i, CGSize cGSize) {
        this.f2084c = i;
        this.f2082a = CGSize.m2875d(AspectRatioType.f2080b[i], cGSize);
        this.f2083b = m2814a(cVar.f2083b, CGSize.m2873b(this.f2082a, cVar.f2082a));
    }

    /* renamed from: a */
    public float mo17115a() {
        if (this.f2085d == 0.0f) {
            this.f2085d = Float.MAX_VALUE;
            for (CollageFrameTile c : this.f2083b) {
                float c2 = c.mo17120c();
                if (c2 < this.f2085d) {
                    this.f2085d = c2;
                }
            }
        }
        return this.f2085d;
    }

    /* renamed from: a */
    public static void m2812a(Resources resources) {
        if (f2081e == null) {
            InputStream openRawResource = resources.openRawResource(R.raw.frames);
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[1024];
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource, "UTF-8"));
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (read != -1) {
                        stringWriter.write(cArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                        }
                    }
                }
                openRawResource.close();
            } catch (Exception e2) {
                try {
                    openRawResource.close();
                } catch (IOException e3) {
                }
            } catch (Throwable th) {
                try {
                    openRawResource.close();
                } catch (IOException e4) {
                }
                throw th;
            }
            try {
                JSONArray jSONArray = new JSONArray(stringWriter.toString());
                f2081e = new HashMap<>(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    f2081e.put(jSONObject.getString("name"), new CollageFrame(jSONObject));
                }
            } catch (JSONException e5) {
            }
        }
    }

    @Nullable
    /* renamed from: a */
    public static CollageFrame m2811a(String str) {
        if (f2081e.containsKey(str)) {
            return f2081e.get(str);
        }
        return null;
    }
}
