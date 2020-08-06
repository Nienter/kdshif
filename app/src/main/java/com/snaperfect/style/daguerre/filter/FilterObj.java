package com.snaperfect.style.daguerre.filter;

import android.content.Context;
import com.snaperfect.inframe1.R;
import com.snaperfect.style.daguerre.utils.Pair;
import java.util.ArrayList;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageExposureFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageLightnessFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageOverlayFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImageToneCurveFilter;

/* renamed from: com.snaperfect.style.daguerre.filter.b */
public class FilterObj {

    /* renamed from: a */
    public static final FilterObj[] f2048a = {new FilterObj("Original", new Class[]{GPUImageFilter.class}, new Object[]{null}), new FilterObj("Light", new Class[]{GPUImageSaturationFilter.class, GPUImageExposureFilter.class, GPUImageToneCurveFilter.class}, new Object[]{Float.valueOf(1.14f), Float.valueOf(1.01f), Integer.valueOf(R.raw.light)}), new FilterObj("Cheerful", (int) R.raw.cheerful, (Pair) f2049e), new FilterObj("Depressed", (int) R.raw.depressed, (Pair) f2049e), new FilterObj("Terrified", 0.5f, R.raw.terrified, f2049e), new FilterObj("Pleased", (int) R.raw.pleased, (Pair) f2049e), new FilterObj("Delighted", 1.4f, R.raw.delighted, f2049e), new FilterObj("Intense", 0.8f, R.raw.intense, f2049e), new FilterObj("Relieved", (int) R.raw.relieved, (Pair) f2050f), new FilterObj("Excited", (int) R.raw.excited, (Pair) f2049e), new FilterObj("Amazed", (int) R.raw.amazed, (Pair) f2050f), new FilterObj("Passionate", (int) R.raw.passionate, (Pair) f2049e), new FilterObj("Relaxed", (int) R.raw.relaxed, (Pair) f2050f), new FilterObj("Mad", (int) R.raw.mad, (Pair) f2049e), new FilterObj("Down", 0.5f, R.raw.down, f2050f), new FilterObj("Angry", 0.0f, R.raw.blue, f2049e), new FilterObj("Upset", 0.0f, R.raw.upset, f2049e), new FilterObj("Blue", 0.0f, R.raw.angry, f2049e), new FilterObj("Sad", 0.0f, R.raw.sad, f2049e), new FilterObj("Painful", 0.0f, R.raw.painful, f2050f), new FilterObj("Dark", new Class[]{GPUImageSaturationFilter.class, GPUImageLightnessFilter.class, GPUImageToneCurveFilter.class, GPUImageOverlayFilter.class}, new Object[]{Float.valueOf(0.0f), Float.valueOf(-0.4f), Integer.valueOf(R.raw.sad), f2049e})};

    /* renamed from: e */
    private static final Pair<Integer, Integer> f2049e = new Pair<>(-5855578, -9605779);

    /* renamed from: f */
    private static final Pair<Integer, Integer> f2050f = new Pair<>(-4539718, -11447983);

    /* renamed from: b */
    private String f2051b;

    /* renamed from: c */
    private Class[] f2052c;

    /* renamed from: d */
    private Object[] f2053d;

    public FilterObj(String str, Class[] clsArr, Object[] objArr) {
        this.f2051b = str;
        this.f2052c = clsArr;
        this.f2053d = objArr;
    }

    public FilterObj(String str, int i, Pair iVar) {
        this(str, new Class[]{GPUImageToneCurveFilter.class, GPUImageOverlayFilter.class}, new Object[]{Integer.valueOf(i), iVar});
    }

    public FilterObj(String str, float f, int i, Pair iVar) {
        this(str, new Class[]{GPUImageSaturationFilter.class, GPUImageToneCurveFilter.class, GPUImageOverlayFilter.class}, new Object[]{Float.valueOf(f), Integer.valueOf(i), iVar});
    }

    /* renamed from: a */
    public String mo17102a() {
        return this.f2051b;
    }

    /* renamed from: a */
    public GPUImageFilterGroup mo17103a(Context context) {
        ArrayList arrayList = new ArrayList(this.f2052c.length);
        for (int i = 0; i < this.f2052c.length; i++) {
            Class<GPUImageLightnessFilter> cls = this.f2052c[i];
            Object obj = this.f2053d[i];
            try {
                GPUImageFilter newInstance = cls.newInstance();
                if (cls == GPUImageToneCurveFilter.class) {
                    ((GPUImageToneCurveFilter) newInstance).mo17904a(context.getResources().openRawResource(((Integer) obj).intValue()));
                } else if (cls == GPUImageOverlayFilter.class) {
                    Pair iVar = (Pair) obj;
                    ((GPUImageOverlayFilter) newInstance).mo17874a(((Integer) iVar.f2264a).intValue());
                    ((GPUImageOverlayFilter) newInstance).mo17877b(((Integer) iVar.f2265b).intValue());
                } else if (cls == GPUImageSaturationFilter.class) {
                    ((GPUImageSaturationFilter) newInstance).mo17897a(((Float) obj).floatValue());
                } else if (cls == GPUImageExposureFilter.class) {
                    ((GPUImageExposureFilter) newInstance).mo17834a(((Float) obj).floatValue());
                } else if (cls == GPUImageLightnessFilter.class) {
                    ((GPUImageLightnessFilter) newInstance).mo17871a(((Float) obj).floatValue());
                }
                arrayList.add(newInstance);
            } catch (Exception e) {
            }
        }
        return new GPUImageFilterGroup(arrayList);
    }
}
