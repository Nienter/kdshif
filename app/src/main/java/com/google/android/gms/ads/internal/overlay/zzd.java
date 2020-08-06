package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.TextureView;
import android.view.View;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.internal.zzmb;
import com.google.android.gms.internal.zzpe;
import com.google.android.gms.internal.zzpi;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@TargetApi(14)
@zzmb
public class zzd extends zzj implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzMc = new HashMap();
    private final zzz zzMd;
    private final boolean zzMe;
    private int zzMf = 0;
    private int zzMg = 0;
    private MediaPlayer zzMh;
    private Uri zzMi;
    private int zzMj;
    private int zzMk;
    private int zzMl;
    private int zzMm;
    private int zzMn;
    private zzy zzMo;
    private boolean zzMp;
    private int zzMq;
    /* access modifiers changed from: private */
    public zzi zzMr;

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            zzMc.put(-1004, "MEDIA_ERROR_IO");
            zzMc.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzMc.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzMc.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzMc.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzMc.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzMc.put(1, "MEDIA_ERROR_UNKNOWN");
        zzMc.put(1, "MEDIA_INFO_UNKNOWN");
        zzMc.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzMc.put(701, "MEDIA_INFO_BUFFERING_START");
        zzMc.put(702, "MEDIA_INFO_BUFFERING_END");
        zzMc.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzMc.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzMc.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            zzMc.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzMc.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzd(Context context, boolean z, boolean z2, zzz zzz) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzMd = zzz;
        this.zzMp = z;
        this.zzMe = z2;
        this.zzMd.zza((zzj) this);
    }

    private void zzI(int i) {
        if (i == 3) {
            this.zzMd.zzib();
            this.zzNk.zzib();
        } else if (this.zzMf == 3) {
            this.zzMd.zzic();
            this.zzNk.zzic();
        }
        this.zzMf = i;
    }

    private void zzJ(int i) {
        this.zzMg = i;
    }

    private void zza(float f) {
        if (this.zzMh != null) {
            try {
                this.zzMh.setVolume(f, f);
            } catch (IllegalStateException e) {
            }
        } else {
            zzpe.zzbe("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private void zzhe() {
        SurfaceTexture surfaceTexture;
        zzpe.m2431v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture2 = getSurfaceTexture();
        if (this.zzMi != null && surfaceTexture2 != null) {
            zzy(false);
            try {
                this.zzMh = zzv.zzda().zzhO();
                this.zzMh.setOnBufferingUpdateListener(this);
                this.zzMh.setOnCompletionListener(this);
                this.zzMh.setOnErrorListener(this);
                this.zzMh.setOnInfoListener(this);
                this.zzMh.setOnPreparedListener(this);
                this.zzMh.setOnVideoSizeChangedListener(this);
                this.zzMl = 0;
                if (this.zzMp) {
                    this.zzMo = new zzy(getContext());
                    this.zzMo.zza(surfaceTexture2, getWidth(), getHeight());
                    this.zzMo.start();
                    surfaceTexture = this.zzMo.zzhQ();
                    if (surfaceTexture == null) {
                        this.zzMo.zzhP();
                        this.zzMo = null;
                    }
                    this.zzMh.setDataSource(getContext(), this.zzMi);
                    this.zzMh.setSurface(zzv.zzdb().zza(surfaceTexture));
                    this.zzMh.setAudioStreamType(3);
                    this.zzMh.setScreenOnWhilePlaying(true);
                    this.zzMh.prepareAsync();
                    zzI(1);
                }
                surfaceTexture = surfaceTexture2;
                this.zzMh.setDataSource(getContext(), this.zzMi);
                this.zzMh.setSurface(zzv.zzdb().zza(surfaceTexture));
                this.zzMh.setAudioStreamType(3);
                this.zzMh.setScreenOnWhilePlaying(true);
                this.zzMh.prepareAsync();
                zzI(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzMi);
                zzpe.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzMh, 1, 0);
            }
        }
    }

    private void zzhf() {
        if (this.zzMe && zzhg() && this.zzMh.getCurrentPosition() > 0 && this.zzMg != 3) {
            zzpe.m2431v("AdMediaPlayerView nudging MediaPlayer");
            zza(0.0f);
            this.zzMh.start();
            int currentPosition = this.zzMh.getCurrentPosition();
            long currentTimeMillis = zzv.zzcP().currentTimeMillis();
            while (zzhg() && this.zzMh.getCurrentPosition() == currentPosition) {
                if (zzv.zzcP().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzMh.pause();
            zzhh();
        }
    }

    private boolean zzhg() {
        return (this.zzMh == null || this.zzMf == -1 || this.zzMf == 0 || this.zzMf == 1) ? false : true;
    }

    private void zzy(boolean z) {
        zzpe.m2431v("AdMediaPlayerView release");
        if (this.zzMo != null) {
            this.zzMo.zzhP();
            this.zzMo = null;
        }
        if (this.zzMh != null) {
            this.zzMh.reset();
            this.zzMh.release();
            this.zzMh = null;
            zzI(0);
            if (z) {
                this.zzMg = 0;
                zzJ(0);
            }
        }
    }

    public int getCurrentPosition() {
        if (zzhg()) {
            return this.zzMh.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (zzhg()) {
            return this.zzMh.getDuration();
        }
        return -1;
    }

    public int getVideoHeight() {
        if (this.zzMh != null) {
            return this.zzMh.getVideoHeight();
        }
        return 0;
    }

    public int getVideoWidth() {
        if (this.zzMh != null) {
            return this.zzMh.getVideoWidth();
        }
        return 0;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzMl = i;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        zzpe.m2431v("AdMediaPlayerView completion");
        zzI(5);
        zzJ(5);
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMr != null) {
                    zzd.this.zzMr.zzhB();
                }
            }
        });
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        final String str = zzMc.get(Integer.valueOf(i));
        final String str2 = zzMc.get(Integer.valueOf(i2));
        zzpe.zzbe(new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(str).append(":").append(str2).toString());
        zzI(-1);
        zzJ(-1);
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMr != null) {
                    zzd.this.zzMr.zzk(str, str2);
                }
            }
        });
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzMc.get(Integer.valueOf(i));
        String str2 = zzMc.get(Integer.valueOf(i2));
        zzpe.m2431v(new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(str).append(":").append(str2).toString());
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.zzMj, i);
        int defaultSize2 = getDefaultSize(this.zzMk, i2);
        if (this.zzMj > 0 && this.zzMk > 0 && this.zzMo == null) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            defaultSize2 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.zzMj * defaultSize2 < this.zzMk * size) {
                    defaultSize = (this.zzMj * defaultSize2) / this.zzMk;
                } else if (this.zzMj * defaultSize2 > this.zzMk * size) {
                    defaultSize2 = (this.zzMk * size) / this.zzMj;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                int i3 = (this.zzMk * size) / this.zzMj;
                if (mode2 != Integer.MIN_VALUE || i3 <= defaultSize2) {
                    defaultSize2 = i3;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.zzMj * defaultSize2) / this.zzMk;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i4 = this.zzMj;
                int i5 = this.zzMk;
                if (mode2 != Integer.MIN_VALUE || i5 <= defaultSize2) {
                    defaultSize2 = i5;
                    defaultSize = i4;
                } else {
                    defaultSize = (this.zzMj * defaultSize2) / this.zzMk;
                }
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize2 = (this.zzMk * size) / this.zzMj;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (this.zzMo != null) {
            this.zzMo.zzi(defaultSize, defaultSize2);
        }
        if (Build.VERSION.SDK_INT == 16) {
            if ((this.zzMm > 0 && this.zzMm != defaultSize) || (this.zzMn > 0 && this.zzMn != defaultSize2)) {
                zzhf();
            }
            this.zzMm = defaultSize;
            this.zzMn = defaultSize2;
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        zzpe.m2431v("AdMediaPlayerView prepared");
        zzI(2);
        this.zzMd.zzhz();
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMr != null) {
                    zzd.this.zzMr.zzhz();
                }
            }
        });
        this.zzMj = mediaPlayer.getVideoWidth();
        this.zzMk = mediaPlayer.getVideoHeight();
        if (this.zzMq != 0) {
            seekTo(this.zzMq);
        }
        zzhf();
        int i = this.zzMj;
        zzpe.zzbd(new StringBuilder(62).append("AdMediaPlayerView stream dimensions: ").append(i).append(" x ").append(this.zzMk).toString());
        if (this.zzMg == 3) {
            play();
        }
        zzhh();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzpe.m2431v("AdMediaPlayerView surface created");
        zzhe();
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMr != null) {
                    zzd.this.zzMr.zzhy();
                }
            }
        });
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzpe.m2431v("AdMediaPlayerView surface destroyed");
        if (this.zzMh != null && this.zzMq == 0) {
            this.zzMq = this.zzMh.getCurrentPosition();
        }
        if (this.zzMo != null) {
            this.zzMo.zzhP();
        }
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMr != null) {
                    zzd.this.zzMr.onPaused();
                    zzd.this.zzMr.zzhC();
                }
            }
        });
        zzy(true);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, final int i, final int i2) {
        boolean z = true;
        zzpe.m2431v("AdMediaPlayerView surface changed");
        boolean z2 = this.zzMg == 3;
        if (!(this.zzMj == i && this.zzMk == i2)) {
            z = false;
        }
        if (this.zzMh != null && z2 && z) {
            if (this.zzMq != 0) {
                seekTo(this.zzMq);
            }
            play();
        }
        if (this.zzMo != null) {
            this.zzMo.zzi(i, i2);
        }
        zzpi.zzWR.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMr != null) {
                    zzd.this.zzMr.zzf(i, i2);
                }
            }
        });
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzMd.zzb(this);
        this.zzNj.zza(surfaceTexture, this.zzMr);
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zzpe.m2431v(new StringBuilder(57).append("AdMediaPlayerView size changed: ").append(i).append(" x ").append(i2).toString());
        this.zzMj = mediaPlayer.getVideoWidth();
        this.zzMk = mediaPlayer.getVideoHeight();
        if (this.zzMj != 0 && this.zzMk != 0) {
            requestLayout();
        }
    }

    public void pause() {
        zzpe.m2431v("AdMediaPlayerView pause");
        if (zzhg() && this.zzMh.isPlaying()) {
            this.zzMh.pause();
            zzI(4);
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    if (zzd.this.zzMr != null) {
                        zzd.this.zzMr.onPaused();
                    }
                }
            });
        }
        zzJ(4);
    }

    public void play() {
        zzpe.m2431v("AdMediaPlayerView play");
        if (zzhg()) {
            this.zzMh.start();
            zzI(3);
            this.zzNj.zzhA();
            zzpi.zzWR.post(new Runnable() {
                public void run() {
                    if (zzd.this.zzMr != null) {
                        zzd.this.zzMr.zzhA();
                    }
                }
            });
        }
        zzJ(3);
    }

    public void seekTo(int i) {
        zzpe.m2431v(new StringBuilder(34).append("AdMediaPlayerView seek ").append(i).toString());
        if (zzhg()) {
            this.zzMh.seekTo(i);
            this.zzMq = 0;
            return;
        }
        this.zzMq = i;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        this.zzMi = uri;
        this.zzMq = 0;
        zzhe();
        requestLayout();
        invalidate();
    }

    public void stop() {
        zzpe.m2431v("AdMediaPlayerView stop");
        if (this.zzMh != null) {
            this.zzMh.stop();
            this.zzMh.release();
            this.zzMh = null;
            zzI(0);
            zzJ(0);
        }
        this.zzMd.onStop();
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getName());
        String valueOf2 = String.valueOf(Integer.toHexString(hashCode()));
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("@").append(valueOf2).toString();
    }

    public void zza(float f, float f2) {
        if (this.zzMo != null) {
            this.zzMo.zzb(f, f2);
        }
    }

    public void zza(zzi zzi) {
        this.zzMr = zzi;
    }

    public String zzhd() {
        String valueOf = String.valueOf(this.zzMp ? " spherical" : "");
        return valueOf.length() != 0 ? "MediaPlayer".concat(valueOf) : new String("MediaPlayer");
    }

    public void zzhh() {
        zza(this.zzNk.zzie());
    }
}
