package p034jp.p035co.cyberagent.android.gpuimage;

import java.util.Arrays;
import java.util.List;

/* renamed from: jp.co.cyberagent.android.gpuimage.ab */
public class GPUImageTwoPassFilter extends GPUImageFilterGroup {
    public GPUImageTwoPassFilter(String str, String str2, String str3, String str4) {
        super(null);
        mo17859a(new GPUImageFilter(str, str2));
        mo17859a(new GPUImageFilter(str3, str4));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17809a(String str, String str2, String str3, String str4) {
        if (((GPUImageFilter) this.f2793a.get(0)).mo17847i() || ((GPUImageFilter) this.f2793a.get(1)).mo17847i()) {
            mo17858a((List<GPUImageFilter>) Arrays.asList(new GPUImageFilter[]{new GPUImageFilter(str, str2), new GPUImageFilter(str3, str4)}));
            return;
        }
        ((GPUImageFilter) this.f2793a.get(0)).mo17840a(str, str2);
        ((GPUImageFilter) this.f2793a.get(1)).mo17840a(str3, str4);
    }
}
