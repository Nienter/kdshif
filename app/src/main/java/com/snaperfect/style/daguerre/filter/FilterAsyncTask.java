package com.snaperfect.style.daguerre.filter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.snaperfect.style.daguerre.utils.PhotoAsset;
import p034jp.p035co.cyberagent.android.gpuimage.GPUImage;

/* renamed from: com.snaperfect.style.daguerre.filter.a */
public class FilterAsyncTask extends AsyncTask<Object, Void, Bitmap> {

    /* renamed from: a */
    PhotoAsset.C1581d f2047a;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap doInBackground(Object... objArr) {
        Bitmap bitmap = objArr[0];
        try {
            GPUImage aVar = objArr[2];
            this.f2047a = objArr[3];
            aVar.mo17789a(objArr[1]);
            return aVar.mo17790b(bitmap);
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        this.f2047a.mo16936a(bitmap);
    }
}
