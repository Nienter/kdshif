package com.snaperfect.style.daguerre.utils;

import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

/* renamed from: com.snaperfect.style.daguerre.utils.l */
public class PathUtils {
    /* renamed from: a */
    public static Path m3110a(String str) {
        boolean z;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        int length = str.length();
        ParserHelper kVar = new ParserHelper(str);
        kVar.mo17298a();
        Path path = new Path();
        RectF rectF = new RectF();
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        char c = 'x';
        float f12 = 0.0f;
        float f13 = 0.0f;
        while (kVar.f2267a < length) {
            char charAt = str.charAt(kVar.f2267a);
            if (!Character.isDigit(charAt) && charAt != '.' && charAt != '-') {
                kVar.mo17300c();
            } else if (c == 'M') {
                charAt = 'L';
            } else if (c == 'm') {
                charAt = 'l';
            } else {
                charAt = c;
            }
            path.computeBounds(rectF, true);
            switch (charAt) {
                case 'A':
                case 'a':
                    float e = kVar.mo17302e();
                    float e2 = kVar.mo17302e();
                    float e3 = kVar.mo17302e();
                    int e4 = (int) kVar.mo17302e();
                    int e5 = (int) kVar.mo17302e();
                    float e6 = kVar.mo17302e();
                    float e7 = kVar.mo17302e();
                    if (charAt == 'a') {
                        e6 += f12;
                        e7 += f13;
                    }
                    m3111a(path, (double) f12, (double) f13, (double) e6, (double) e7, (double) e, (double) e2, (double) e3, e4 == 1, e5 == 1);
                    z = false;
                    f = f8;
                    f2 = f9;
                    f4 = f10;
                    f3 = f11;
                    f6 = e7;
                    f5 = e6;
                    break;
                case 'C':
                case 'c':
                    float e8 = kVar.mo17302e();
                    float e9 = kVar.mo17302e();
                    float e10 = kVar.mo17302e();
                    float e11 = kVar.mo17302e();
                    float e12 = kVar.mo17302e();
                    float e13 = kVar.mo17302e();
                    if (charAt == 'c') {
                        e10 += f12;
                        e12 += f12;
                        e9 += f13;
                        e11 += f13;
                        e13 += f13;
                        f7 = e8 + f12;
                    } else {
                        f7 = e8;
                    }
                    path.cubicTo(f7, e9, e10, e11, e12, e13);
                    z = true;
                    f = f8;
                    f2 = f9;
                    float f14 = e11;
                    f3 = e10;
                    f4 = f14;
                    float f15 = e13;
                    f5 = e12;
                    f6 = f15;
                    break;
                case 'H':
                case 'h':
                    float e14 = kVar.mo17302e();
                    if (charAt != 'h') {
                        path.lineTo(e14, f13);
                        f = f8;
                        f2 = f9;
                        f4 = f10;
                        f3 = f11;
                        f5 = e14;
                        f6 = f13;
                        z = false;
                        break;
                    } else {
                        path.rLineTo(e14, 0.0f);
                        float f16 = e14 + f12;
                        f = f8;
                        f2 = f9;
                        f4 = f10;
                        f3 = f11;
                        f5 = f16;
                        f6 = f13;
                        z = false;
                        break;
                    }
                case 'L':
                case 'l':
                    float e15 = kVar.mo17302e();
                    float e16 = kVar.mo17302e();
                    if (charAt != 'l') {
                        path.lineTo(e15, e16);
                        z = false;
                        f = f8;
                        f2 = f9;
                        f4 = f10;
                        f3 = f11;
                        float f17 = e16;
                        f5 = e15;
                        f6 = f17;
                        break;
                    } else {
                        path.rLineTo(e15, e16);
                        float f18 = e15 + f12;
                        float f19 = e16 + f13;
                        z = false;
                        f = f8;
                        f2 = f9;
                        f4 = f10;
                        f3 = f11;
                        float f20 = f19;
                        f5 = f18;
                        f6 = f20;
                        break;
                    }
                case 'M':
                case 'm':
                    float e17 = kVar.mo17302e();
                    float e18 = kVar.mo17302e();
                    if (charAt == 'm') {
                        path.rMoveTo(e17, e18);
                        float f21 = e17 + f12;
                        f = f13 + e18;
                        f2 = f21;
                    } else {
                        path.moveTo(e17, e18);
                        f = e18;
                        f2 = e17;
                    }
                    z = false;
                    f4 = f10;
                    f3 = f11;
                    f6 = f;
                    f5 = f2;
                    break;
                case 'Q':
                case 'q':
                    float e19 = kVar.mo17302e();
                    float e20 = kVar.mo17302e();
                    float e21 = kVar.mo17302e();
                    float e22 = kVar.mo17302e();
                    if (charAt == 'q') {
                        e21 += f12;
                        e22 += f13;
                        e19 += f12;
                        e20 += f13;
                    }
                    path.cubicTo(f12, f13, e19, e20, e21, e22);
                    z = true;
                    f = f8;
                    f2 = f9;
                    float f22 = e20;
                    f3 = e19;
                    f4 = f22;
                    float f23 = e22;
                    f5 = e21;
                    f6 = f23;
                    break;
                case 'S':
                case 's':
                    float e23 = kVar.mo17302e();
                    float e24 = kVar.mo17302e();
                    float e25 = kVar.mo17302e();
                    float e26 = kVar.mo17302e();
                    if (charAt == 's') {
                        e23 += f12;
                        e25 += f12;
                        e24 += f13;
                        e26 += f13;
                    }
                    path.cubicTo((f12 * 2.0f) - f11, (2.0f * f13) - f10, e23, e24, e25, e26);
                    z = true;
                    f = f8;
                    f2 = f9;
                    float f24 = e24;
                    f3 = e23;
                    f4 = f24;
                    float f25 = e26;
                    f5 = e25;
                    f6 = f25;
                    break;
                case 'T':
                case 't':
                    float e27 = kVar.mo17302e();
                    float e28 = kVar.mo17302e();
                    if (charAt == 't') {
                        e27 += f12;
                        e28 += f13;
                    }
                    float f26 = (2.0f * f12) - f11;
                    float f27 = (2.0f * f13) - f10;
                    path.cubicTo(f12, f13, f26, f27, e27, e28);
                    z = true;
                    f = f8;
                    f2 = f9;
                    float f28 = f27;
                    f3 = f26;
                    f4 = f28;
                    float f29 = e28;
                    f5 = e27;
                    f6 = f29;
                    break;
                case 'V':
                case 'v':
                    float e29 = kVar.mo17302e();
                    if (charAt != 'v') {
                        path.lineTo(f12, e29);
                        z = false;
                        f2 = f9;
                        f4 = f10;
                        f3 = f11;
                        f6 = e29;
                        f5 = f12;
                        f = f8;
                        break;
                    } else {
                        path.rLineTo(0.0f, e29);
                        float f30 = e29 + f13;
                        z = false;
                        f2 = f9;
                        f4 = f10;
                        f3 = f11;
                        f6 = f30;
                        f5 = f12;
                        f = f8;
                        break;
                    }
                case 'Z':
                case 'z':
                    path.close();
                    z = false;
                    f = f8;
                    f2 = f9;
                    f4 = f10;
                    f3 = f11;
                    f6 = f8;
                    f5 = f9;
                    break;
                default:
                    Log.w("PathUtils", "Invalid path command: " + charAt);
                    kVar.mo17300c();
                    f2 = f9;
                    f4 = f10;
                    f3 = f11;
                    f6 = f13;
                    f5 = f12;
                    f = f8;
                    z = false;
                    break;
            }
            if (!z) {
                f4 = f6;
                f3 = f5;
            }
            kVar.mo17298a();
            f8 = f;
            f9 = f2;
            f10 = f4;
            f11 = f3;
            f13 = f6;
            c = charAt;
            f12 = f5;
        }
        return path;
    }

    /* renamed from: a */
    private static void m3111a(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, boolean z, boolean z2) {
        double d8;
        double d9;
        double d10;
        double d11;
        double d12;
        double d13 = (d - d3) / 2.0d;
        double d14 = (d2 - d4) / 2.0d;
        double radians = Math.toRadians(d7 % 360.0d);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d15 = (cos * d13) + (sin * d14);
        double d16 = (d13 * (-sin)) + (d14 * cos);
        double abs = Math.abs(d5);
        double abs2 = Math.abs(d6);
        double d17 = abs * abs;
        double d18 = abs2 * abs2;
        double d19 = d15 * d15;
        double d20 = d16 * d16;
        double d21 = (d19 / d17) + (d20 / d18);
        if (d21 > 1.0d) {
            double sqrt = abs * Math.sqrt(d21);
            double sqrt2 = abs2 * Math.sqrt(d21);
            d8 = sqrt2;
            d9 = sqrt;
            d10 = sqrt2 * sqrt2;
            d11 = sqrt * sqrt;
        } else {
            d8 = abs2;
            d9 = abs;
            d10 = d18;
            d11 = d17;
        }
        if (z == z2) {
            d12 = -1.0d;
        } else {
            d12 = 1.0d;
        }
        double d22 = (((d11 * d10) - (d11 * d20)) - (d10 * d19)) / ((d10 * d19) + (d11 * d20));
        if (d22 < 0.0d) {
            d22 = 0.0d;
        }
        double sqrt3 = Math.sqrt(d22) * d12;
        double d23 = ((d9 * d16) / d8) * sqrt3;
        double d24 = sqrt3 * (-((d8 * d15) / d9));
        double d25 = ((d + d3) / 2.0d) + ((cos * d23) - (sin * d24));
        double d26 = ((d2 + d4) / 2.0d) + (cos * d24) + (sin * d23);
        double d27 = (d15 - d23) / d9;
        double d28 = (d16 - d24) / d8;
        double d29 = ((-d15) - d23) / d9;
        double d30 = ((-d16) - d24) / d8;
        double degrees = Math.toDegrees((d28 < 0.0d ? -1.0d : 1.0d) * Math.acos(d27 / Math.sqrt((d27 * d27) + (d28 * d28))));
        double degrees2 = Math.toDegrees(((d27 * d30) - (d29 * d28) < 0.0d ? -1.0d : 1.0d) * Math.acos(((d28 * d30) + (d27 * d29)) / Math.sqrt(((d27 * d27) + (d28 * d28)) * ((d29 * d29) + (d30 * d30)))));
        if (!z2 && degrees2 > 0.0d) {
            degrees2 -= 360.0d;
        } else if (z2 && degrees2 < 0.0d) {
            degrees2 += 360.0d;
        }
        path.addArc(new RectF((float) (d25 - d9), (float) (d26 - d8), (float) (d25 + d9), (float) (d26 + d8)), (float) (degrees % 360.0d), (float) (degrees2 % 360.0d));
    }
}
