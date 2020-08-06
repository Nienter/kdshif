package com.snaperfect.style.daguerre.style;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.snaperfect.style.daguerre.frame.FrameColor;
import com.snaperfect.style.daguerre.utils.Localize;
import com.snaperfect.style.daguerre.utils.StoreUtils;

public class BackgroundInfo implements Parcelable {
    public static final Parcelable.Creator<BackgroundInfo> CREATOR = new Parcelable.Creator<BackgroundInfo>() {
        /* renamed from: a */
        public BackgroundInfo createFromParcel(Parcel parcel) {
            return new BackgroundInfo(parcel);
        }

        /* renamed from: a */
        public BackgroundInfo[] newArray(int i) {
            return new BackgroundInfo[i];
        }
    };

    /* renamed from: a */
    public static final String[] f2112a = {"Color", "Gradient", "Blur", "Image"};

    /* renamed from: c */
    static final /* synthetic */ boolean f2113c = (!BackgroundInfo.class.desiredAssertionStatus());

    /* renamed from: b */
    public int f2114b;

    /* renamed from: d */
    private int f2115d;

    /* renamed from: e */
    private int f2116e;

    /* renamed from: f */
    private float f2117f;

    private BackgroundInfo(int i, int i2, int i3) {
        this.f2114b = i;
        this.f2115d = i2;
        this.f2116e = i3;
    }

    private BackgroundInfo(int i, int i2, int i3, float f) {
        this.f2114b = i;
        this.f2115d = i2;
        this.f2116e = i3;
        this.f2117f = f;
    }

    private BackgroundInfo(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readFloat());
    }

    /* renamed from: a */
    public static BackgroundInfo m2907a(int i) {
        return new BackgroundInfo(0, i, 0);
    }

    /* renamed from: a */
    public static BackgroundInfo m2906a() {
        return m2907a(-1);
    }

    /* renamed from: a */
    public static BackgroundInfo m2908a(int i, int i2) {
        return new BackgroundInfo(1, i, i2);
    }

    /* renamed from: b */
    public static BackgroundInfo m2911b(int i, int i2) {
        return new BackgroundInfo(3, i, i2);
    }

    /* renamed from: b */
    public static BackgroundInfo m2910b() {
        return new BackgroundInfo(2, -1, 0);
    }

    /* renamed from: a */
    public static BackgroundInfo m2909a(int i, int i2, float f) {
        return new BackgroundInfo(2, i, i2, f);
    }

    /* renamed from: c */
    public int mo17179c() {
        if (f2113c || this.f2114b == 0) {
            return this.f2115d;
        }
        throw new AssertionError();
    }

    /* renamed from: d */
    public int mo17180d() {
        if (f2113c || this.f2114b == 1) {
            return this.f2115d;
        }
        throw new AssertionError();
    }

    /* renamed from: e */
    public int mo17182e() {
        if (f2113c || this.f2114b == 1) {
            return this.f2116e;
        }
        throw new AssertionError();
    }

    /* renamed from: f */
    public int mo17184f() {
        if (this.f2114b == 3) {
            return this.f2115d;
        }
        return 0;
    }

    /* renamed from: g */
    public int mo17185g() {
        if (this.f2114b == 3) {
            return this.f2116e;
        }
        return 0;
    }

    /* renamed from: h */
    public int mo17186h() {
        return this.f2115d < 0 ? StoreUtils.m3118b() : this.f2115d;
    }

    /* renamed from: i */
    public int mo17187i() {
        if (this.f2114b == 2) {
            return this.f2116e;
        }
        return 0;
    }

    /* renamed from: a */
    public int mo17178a(Context context, int i) {
        if (this.f2114b != i) {
            return -1;
        }
        if (i == 0) {
            return FrameColor.m2828a(context, this.f2115d);
        }
        if (i == 1) {
            return FrameColor.m2829a(context, this.f2115d, this.f2116e);
        }
        return -1;
    }

    public String toString() {
        return Localize.m3098a("BackgroundInfo@%s", f2112a[this.f2114b]);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BackgroundInfo)) {
            return false;
        }
        return this.f2114b == ((BackgroundInfo) obj).f2114b && this.f2115d == ((BackgroundInfo) obj).f2115d && this.f2116e == ((BackgroundInfo) obj).f2116e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2114b);
        parcel.writeInt(this.f2115d);
        parcel.writeInt(this.f2116e);
        parcel.writeFloat(this.f2117f);
    }
}
