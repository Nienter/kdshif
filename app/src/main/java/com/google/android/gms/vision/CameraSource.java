package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.support.p004v7.widget.ActivityChooserView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.Frame;
import java.lang.Thread;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CameraSource {
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int zzLS;
    /* access modifiers changed from: private */
    public final Object zzbLP;
    /* access modifiers changed from: private */
    public Camera zzbLQ;
    /* access modifiers changed from: private */
    public int zzbLR;
    /* access modifiers changed from: private */
    public Size zzbLS;
    /* access modifiers changed from: private */
    public float zzbLT;
    /* access modifiers changed from: private */
    public int zzbLU;
    /* access modifiers changed from: private */
    public int zzbLV;
    /* access modifiers changed from: private */
    public boolean zzbLW;
    private SurfaceView zzbLX;
    private SurfaceTexture zzbLY;
    private boolean zzbLZ;
    /* access modifiers changed from: private */
    public Thread zzbMa;
    /* access modifiers changed from: private */
    public zzb zzbMb;
    /* access modifiers changed from: private */
    public Map<byte[], ByteBuffer> zzbMc;

    public static class Builder {
        private final Detector<?> zzbMd;
        private CameraSource zzbMe = new CameraSource();

        public Builder(Context context, Detector<?> detector) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            } else {
                this.zzbMd = detector;
                Context unused = this.zzbMe.mContext = context;
            }
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzbMe;
            CameraSource cameraSource2 = this.zzbMe;
            cameraSource2.getClass();
            zzb unused = cameraSource.zzbMb = new zzb(this.zzbMd);
            return this.zzbMe;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            boolean unused = this.zzbMe.zzbLW = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                int unused = this.zzbMe.zzbLR = i;
                return this;
            }
            throw new IllegalArgumentException(new StringBuilder(27).append("Invalid camera: ").append(i).toString());
        }

        public Builder setRequestedFps(float f) {
            if (f <= 0.0f) {
                throw new IllegalArgumentException(new StringBuilder(28).append("Invalid fps: ").append(f).toString());
            }
            float unused = this.zzbMe.zzbLT = f;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                throw new IllegalArgumentException(new StringBuilder(45).append("Invalid preview size: ").append(i).append("x").append(i2).toString());
            }
            int unused = this.zzbMe.zzbLU = i;
            int unused2 = this.zzbMe.zzbLV = i2;
            return this;
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    private class zza implements Camera.PreviewCallback {
        private zza() {
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.zzbMb.zza(bArr, camera);
        }
    }

    private class zzb implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = (!CameraSource.class.desiredAssertionStatus());
        private long zzaed = SystemClock.elapsedRealtime();
        private Detector<?> zzbMd;
        private boolean zzbMg = true;
        private long zzbMh;
        private int zzbMi = 0;
        private ByteBuffer zzbMj;
        private final Object zzrN = new Object();

        zzb(Detector<?> detector) {
            this.zzbMd = detector;
        }

        /* access modifiers changed from: package-private */
        @SuppressLint({"Assert"})
        public void release() {
            if ($assertionsDisabled || CameraSource.this.zzbMa.getState() == Thread.State.TERMINATED) {
                this.zzbMd.release();
                this.zzbMd = null;
                return;
            }
            throw new AssertionError();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r6.zzbMd.receiveFrame(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0078, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            android.util.Log.e("CameraSource", "Exception thrown from receiver.", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x008f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0090, code lost:
            com.google.android.gms.vision.CameraSource.zzb(r6.zzbMf).addCallbackBuffer(r2.array());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x009d, code lost:
            throw r0;
         */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        @SuppressLint({"InlinedApi"})
        public void run() {
            while (true) {
                synchronized (this.zzrN) {
                    while (this.zzbMg && this.zzbMj == null) {
                        try {
                            this.zzrN.wait();
                        } catch (InterruptedException e) {
                            Log.d("CameraSource", "Frame processing loop terminated.", e);
                            return;
                        }
                    }
                    if (this.zzbMg) {
                        Frame build = new Frame.Builder().setImageData(this.zzbMj, CameraSource.this.zzbLS.getWidth(), CameraSource.this.zzbLS.getHeight(), 17).setId(this.zzbMi).setTimestampMillis(this.zzbMh).setRotation(CameraSource.this.zzLS).build();
                        ByteBuffer byteBuffer = this.zzbMj;
                        this.zzbMj = null;
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setActive(boolean z) {
            synchronized (this.zzrN) {
                this.zzbMg = z;
                this.zzrN.notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public void zza(byte[] bArr, Camera camera) {
            synchronized (this.zzrN) {
                if (this.zzbMj != null) {
                    camera.addCallbackBuffer(this.zzbMj.array());
                    this.zzbMj = null;
                }
                if (!CameraSource.this.zzbMc.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.zzbMh = SystemClock.elapsedRealtime() - this.zzaed;
                this.zzbMi++;
                this.zzbMj = (ByteBuffer) CameraSource.this.zzbMc.get(bArr);
                this.zzrN.notifyAll();
            }
        }
    }

    private class zzc implements Camera.PictureCallback {
        /* access modifiers changed from: private */
        public PictureCallback zzbMk;

        private zzc() {
        }

        public void onPictureTaken(byte[] bArr, Camera camera) {
            if (this.zzbMk != null) {
                this.zzbMk.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.zzbLP) {
                if (CameraSource.this.zzbLQ != null) {
                    CameraSource.this.zzbLQ.startPreview();
                }
            }
        }
    }

    private class zzd implements Camera.ShutterCallback {
        /* access modifiers changed from: private */
        public ShutterCallback zzbMl;

        private zzd(CameraSource cameraSource) {
        }

        public void onShutter() {
            if (this.zzbMl != null) {
                this.zzbMl.onShutter();
            }
        }
    }

    static class zze {
        private Size zzbMm;
        private Size zzbMn;

        public zze(Camera.Size size, Camera.Size size2) {
            this.zzbMm = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzbMn = new Size(size2.width, size2.height);
            }
        }

        public Size zzSj() {
            return this.zzbMm;
        }

        public Size zzSk() {
            return this.zzbMn;
        }
    }

    private CameraSource() {
        this.zzbLP = new Object();
        this.zzbLR = 0;
        this.zzbLT = 30.0f;
        this.zzbLU = 1024;
        this.zzbLV = 768;
        this.zzbLW = false;
        this.zzbMc = new HashMap();
    }

    @SuppressLint({"InlinedApi"})
    private Camera zzSi() {
        int zzne = zzne(this.zzbLR);
        if (zzne == -1) {
            throw new RuntimeException("Could not find requested camera.");
        }
        Camera open = Camera.open(zzne);
        zze zza2 = zza(open, this.zzbLU, this.zzbLV);
        if (zza2 == null) {
            throw new RuntimeException("Could not find suitable preview size.");
        }
        Size zzSk = zza2.zzSk();
        this.zzbLS = zza2.zzSj();
        int[] zza3 = zza(open, this.zzbLT);
        if (zza3 == null) {
            throw new RuntimeException("Could not find suitable preview frames per second range.");
        }
        Camera.Parameters parameters = open.getParameters();
        if (zzSk != null) {
            parameters.setPictureSize(zzSk.getWidth(), zzSk.getHeight());
        }
        parameters.setPreviewSize(this.zzbLS.getWidth(), this.zzbLS.getHeight());
        parameters.setPreviewFpsRange(zza3[0], zza3[1]);
        parameters.setPreviewFormat(17);
        zza(open, parameters, zzne);
        if (this.zzbLW) {
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                parameters.setFocusMode("continuous-video");
            } else {
                Log.i("CameraSource", "Camera auto focus is not supported on this device.");
            }
        }
        open.setParameters(parameters);
        open.setPreviewCallbackWithBuffer(new zza());
        open.addCallbackBuffer(zza(this.zzbLS));
        open.addCallbackBuffer(zza(this.zzbLS));
        open.addCallbackBuffer(zza(this.zzbLS));
        open.addCallbackBuffer(zza(this.zzbLS));
        return open;
    }

    static zze zza(Camera camera, int i, int i2) {
        int i3;
        zze zze2;
        List<zze> zza2 = zza(camera);
        zze zze3 = null;
        int i4 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (zze next : zza2) {
            Size zzSj = next.zzSj();
            int abs = Math.abs(zzSj.getHeight() - i2) + Math.abs(zzSj.getWidth() - i);
            if (abs < i4) {
                int i5 = abs;
                zze2 = next;
                i3 = i5;
            } else {
                i3 = i4;
                zze2 = zze3;
            }
            i4 = i3;
            zze3 = zze2;
        }
        return zze3;
    }

    static List<zze> zza(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size next : supportedPreviewSizes) {
            float f = ((float) next.width) / ((float) next.height);
            Iterator<Camera.Size> it = supportedPictureSizes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Camera.Size next2 = it.next();
                if (Math.abs(f - (((float) next2.width) / ((float) next2.height))) < 0.01f) {
                    arrayList.add(new zze(next, next2));
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size zze2 : supportedPreviewSizes) {
                arrayList.add(new zze(zze2, null));
            }
        }
        return arrayList;
    }

    private void zza(Camera camera, Camera.Parameters parameters, int i) {
        int i2;
        int i3;
        int i4;
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (rotation) {
            case 0:
                i2 = 0;
                break;
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
            default:
                Log.e("CameraSource", new StringBuilder(31).append("Bad rotation value: ").append(rotation).toString());
                i2 = 0;
                break;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i4 = (i2 + cameraInfo.orientation) % 360;
            i3 = (360 - i4) % 360;
        } else {
            i3 = ((cameraInfo.orientation - i2) + 360) % 360;
            i4 = i3;
        }
        this.zzLS = i4 / 90;
        camera.setDisplayOrientation(i3);
        parameters.setRotation(i4);
    }

    @SuppressLint({"InlinedApi"})
    private byte[] zza(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((long) (ImageFormat.getBitsPerPixel(17) * (size.getHeight() * size.getWidth())))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.zzbMc.put(bArr, wrap);
        return bArr;
    }

    @SuppressLint({"InlinedApi"})
    static int[] zza(Camera camera, float f) {
        int i;
        int[] iArr;
        int i2 = (int) (1000.0f * f);
        int[] iArr2 = null;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int[] next : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i2 - next[0]) + Math.abs(i2 - next[1]);
            if (abs < i3) {
                int i4 = abs;
                iArr = next;
                i = i4;
            } else {
                i = i3;
                iArr = iArr2;
            }
            i3 = i;
            iArr2 = iArr;
        }
        return iArr2;
    }

    private static int zzne(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < Camera.getNumberOfCameras(); i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return i2;
            }
        }
        return -1;
    }

    public int getCameraFacing() {
        return this.zzbLR;
    }

    public Size getPreviewSize() {
        return this.zzbLS;
    }

    public void release() {
        synchronized (this.zzbLP) {
            stop();
            this.zzbMb.release();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start() {
        synchronized (this.zzbLP) {
            if (this.zzbLQ == null) {
                this.zzbLQ = zzSi();
                if (Build.VERSION.SDK_INT >= 11) {
                    this.zzbLY = new SurfaceTexture(100);
                    this.zzbLQ.setPreviewTexture(this.zzbLY);
                    this.zzbLZ = true;
                } else {
                    this.zzbLX = new SurfaceView(this.mContext);
                    this.zzbLQ.setPreviewDisplay(this.zzbLX.getHolder());
                    this.zzbLZ = false;
                }
                this.zzbLQ.startPreview();
                this.zzbMa = new Thread(this.zzbMb);
                this.zzbMb.setActive(true);
                this.zzbMa.start();
            }
        }
        return this;
    }

    @RequiresPermission("android.permission.CAMERA")
    public CameraSource start(SurfaceHolder surfaceHolder) {
        synchronized (this.zzbLP) {
            if (this.zzbLQ == null) {
                this.zzbLQ = zzSi();
                this.zzbLQ.setPreviewDisplay(surfaceHolder);
                this.zzbLQ.startPreview();
                this.zzbMa = new Thread(this.zzbMb);
                this.zzbMb.setActive(true);
                this.zzbMa.start();
                this.zzbLZ = false;
            }
        }
        return this;
    }

    public void stop() {
        synchronized (this.zzbLP) {
            this.zzbMb.setActive(false);
            if (this.zzbMa != null) {
                try {
                    this.zzbMa.join();
                } catch (InterruptedException e) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzbMa = null;
            }
            if (this.zzbLQ != null) {
                this.zzbLQ.stopPreview();
                this.zzbLQ.setPreviewCallbackWithBuffer(null);
                try {
                    if (this.zzbLZ) {
                        this.zzbLQ.setPreviewTexture(null);
                    } else {
                        this.zzbLQ.setPreviewDisplay(null);
                    }
                } catch (Exception e2) {
                    String valueOf = String.valueOf(e2);
                    Log.e("CameraSource", new StringBuilder(String.valueOf(valueOf).length() + 32).append("Failed to clear camera preview: ").append(valueOf).toString());
                }
                this.zzbLQ.release();
                this.zzbLQ = null;
            }
            this.zzbMc.clear();
        }
        return;
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzbLP) {
            if (this.zzbLQ != null) {
                zzd zzd2 = new zzd();
                ShutterCallback unused = zzd2.zzbMl = shutterCallback;
                zzc zzc2 = new zzc();
                PictureCallback unused2 = zzc2.zzbMk = pictureCallback;
                this.zzbLQ.takePicture(zzd2, null, null, zzc2);
            }
        }
    }
}
