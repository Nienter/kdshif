package com.snaperfect.style.daguerre.frame;

import android.content.Context;
import android.support.p001v4.content.ContextCompat;
import com.snaperfect.inframe1.R;

/* renamed from: com.snaperfect.style.daguerre.frame.e */
public class FrameColor {

    /* renamed from: a */
    public static int[] f2095a = {R.color.bg_01, R.color.bg_02, R.color.bg_03, R.color.bg_04, R.color.bg_05, R.color.bg_06, R.color.bg_07, R.color.bg_08, R.color.bg_09, R.color.bg_10, R.color.bg_11, R.color.bg_12, R.color.bg_13, R.color.bg_14, R.color.bg_15, R.color.bg_16, R.color.bg_17, R.color.bg_18, R.color.bg_19, R.color.bg_20, R.color.bg_21, R.color.bg_22, R.color.bg_23, R.color.bg_24, R.color.bg_25, R.color.bg_26, R.color.bg_27, R.color.bg_28, R.color.bg_29, R.color.bg_30, R.color.bg_31, R.color.bg_32};

    /* renamed from: b */
    public static int[][] f2096b = {new int[]{R.color.dawn_1, R.color.dawn_2}, new int[]{R.color.predawn_1, R.color.predawn_2}, new int[]{R.color.cosmic_fusion_1, R.color.cosmic_fusion_2}, new int[]{R.color.nepal_1, R.color.nepal_2}, new int[]{R.color.azure_pop_1, R.color.azure_pop_2}, new int[]{R.color.dusk_1, R.color.dusk_2}, new int[]{R.color.diana_1, R.color.diana_2}, new int[]{R.color.sunset_1, R.color.sunset_2}, new int[]{R.color.grapefruit_sunset_1, R.color.grapefruit_sunset_2}, new int[]{R.color.sweet_morning_1, R.color.sweet_morning_2}, new int[]{R.color.politics_1, R.color.politics_2}, new int[]{R.color.transfile_1, R.color.transfile_2}, new int[]{R.color.red_ocean_1, R.color.red_ocean_2}, new int[]{R.color.alihossein_1, R.color.alihossein_2}, new int[]{R.color.ali_1, R.color.ali_2}, new int[]{R.color.superman_1, R.color.superman_2}, new int[]{R.color.vikings_1, R.color.vikings_2}, new int[]{R.color.christmas_1, R.color.christmas_2}, new int[]{R.color.green_blue_1, R.color.green_blue_2}, new int[]{R.color.pincho_1, R.color.pincho_2}, new int[]{R.color.timber_1, R.color.timber_2}, new int[]{R.color.lizard_1, R.color.lizard_2}, new int[]{R.color.portrait_1, R.color.portrait_2}, new int[]{R.color.shore_1, R.color.shore_2}, new int[]{R.color.virgin_1, R.color.virgin_2}, new int[]{R.color.candy_1, R.color.candy_2}, new int[]{R.color.winter_1, R.color.winter_2}, new int[]{R.color.lost_1, R.color.lost_2}, new int[]{R.color.memory_1, R.color.memory_2}, new int[]{R.color.miaka_1, R.color.miaka_2}, new int[]{R.color.darya_1, R.color.darya_2}, new int[]{R.color.opa_1, R.color.opa_2}, new int[]{R.color.frozen_1, R.color.frozen_2}, new int[]{R.color.rose_1, R.color.rose_2}, new int[]{R.color.horizon_1, R.color.horizon_2}, new int[]{R.color.mantle_1, R.color.mantle_2}};

    /* renamed from: a */
    public static int m2828a(Context context, int i) {
        for (int i2 = 0; i2 < f2095a.length; i2++) {
            if (i == m2830b(context, f2095a[i2])) {
                return i2;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static int m2829a(Context context, int i, int i2) {
        for (int i3 = 0; i3 < f2096b.length; i3++) {
            int b = m2830b(context, f2096b[i3][0]);
            int b2 = m2830b(context, f2096b[i3][1]);
            if (i == b && i2 == b2) {
                return i3;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public static int m2830b(Context context, int i) {
        return ContextCompat.getColor(context, i);
    }
}
