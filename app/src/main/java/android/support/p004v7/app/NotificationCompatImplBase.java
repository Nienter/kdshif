package android.support.p004v7.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.p001v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.p001v4.app.NotificationCompat;
import android.support.p001v4.app.NotificationCompatBase;
import android.support.p004v7.appcompat.C0309R;
import android.widget.RemoteViews;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@TargetApi(9)
@RequiresApi(9)
/* renamed from: android.support.v7.app.NotificationCompatImplBase */
class NotificationCompatImplBase {
    private static final int MAX_ACTION_BUTTONS = 3;
    static final int MAX_MEDIA_BUTTONS = 5;
    static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;

    NotificationCompatImplBase() {
    }

    @TargetApi(11)
    @RequiresApi(11)
    public static <T extends NotificationCompatBase.Action> RemoteViews overrideContentViewMedia(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, List<T> list, int[] iArr, boolean z2, PendingIntent pendingIntent, boolean z3) {
        RemoteViews generateContentViewMedia = generateContentViewMedia(context, charSequence, charSequence2, charSequence3, i, bitmap, charSequence4, z, j, i2, list, iArr, z2, pendingIntent, z3);
        notificationBuilderWithBuilderAccessor.getBuilder().setContent(generateContentViewMedia);
        if (z2) {
            notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
        }
        return generateContentViewMedia;
    }

    @TargetApi(11)
    @RequiresApi(11)
    private static <T extends NotificationCompatBase.Action> RemoteViews generateContentViewMedia(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, List<T> list, int[] iArr, boolean z2, PendingIntent pendingIntent, boolean z3) {
        int min;
        RemoteViews applyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, i, 0, bitmap, charSequence4, z, j, i2, 0, z3 ? C0309R.layout.notification_template_media_custom : C0309R.layout.notification_template_media, true);
        int size = list.size();
        if (iArr == null) {
            min = 0;
        } else {
            min = Math.min(iArr.length, 3);
        }
        applyStandardTemplate.removeAllViews(C0309R.C0311id.media_actions);
        if (min > 0) {
            for (int i3 = 0; i3 < min; i3++) {
                if (i3 >= size) {
                    throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(i3), Integer.valueOf(size - 1)}));
                }
                applyStandardTemplate.addView(C0309R.C0311id.media_actions, generateMediaActionButton(context, (NotificationCompatBase.Action) list.get(iArr[i3])));
            }
        }
        if (z2) {
            applyStandardTemplate.setViewVisibility(C0309R.C0311id.end_padder, 8);
            applyStandardTemplate.setViewVisibility(C0309R.C0311id.cancel_action, 0);
            applyStandardTemplate.setOnClickPendingIntent(C0309R.C0311id.cancel_action, pendingIntent);
            applyStandardTemplate.setInt(C0309R.C0311id.cancel_action, "setAlpha", context.getResources().getInteger(C0309R.integer.cancel_button_image_alpha));
        } else {
            applyStandardTemplate.setViewVisibility(C0309R.C0311id.end_padder, 0);
            applyStandardTemplate.setViewVisibility(C0309R.C0311id.cancel_action, 8);
        }
        return applyStandardTemplate;
    }

    @TargetApi(16)
    @RequiresApi(16)
    public static <T extends NotificationCompatBase.Action> void overrideMediaBigContentView(Notification notification, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, int i3, List<T> list, boolean z2, PendingIntent pendingIntent, boolean z3) {
        notification.bigContentView = generateMediaBigView(context, charSequence, charSequence2, charSequence3, i, bitmap, charSequence4, z, j, i2, i3, list, z2, pendingIntent, z3);
        if (z2) {
            notification.flags |= 2;
        }
    }

    @TargetApi(11)
    @RequiresApi(11)
    public static <T extends NotificationCompatBase.Action> RemoteViews generateMediaBigView(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, int i3, List<T> list, boolean z2, PendingIntent pendingIntent, boolean z3) {
        int min = Math.min(list.size(), 5);
        RemoteViews applyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, i, 0, bitmap, charSequence4, z, j, i2, i3, getBigMediaLayoutResource(z3, min), false);
        applyStandardTemplate.removeAllViews(C0309R.C0311id.media_actions);
        if (min > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 >= min) {
                    break;
                }
                applyStandardTemplate.addView(C0309R.C0311id.media_actions, generateMediaActionButton(context, (NotificationCompatBase.Action) list.get(i5)));
                i4 = i5 + 1;
            }
        }
        if (z2) {
            applyStandardTemplate.setViewVisibility(C0309R.C0311id.cancel_action, 0);
            applyStandardTemplate.setInt(C0309R.C0311id.cancel_action, "setAlpha", context.getResources().getInteger(C0309R.integer.cancel_button_image_alpha));
            applyStandardTemplate.setOnClickPendingIntent(C0309R.C0311id.cancel_action, pendingIntent);
        } else {
            applyStandardTemplate.setViewVisibility(C0309R.C0311id.cancel_action, 8);
        }
        return applyStandardTemplate;
    }

    @TargetApi(11)
    @RequiresApi(11)
    private static RemoteViews generateMediaActionButton(Context context, NotificationCompatBase.Action action) {
        boolean z = action.getActionIntent() == null;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), C0309R.layout.notification_media_action);
        remoteViews.setImageViewResource(C0309R.C0311id.action0, action.getIcon());
        if (!z) {
            remoteViews.setOnClickPendingIntent(C0309R.C0311id.action0, action.getActionIntent());
        }
        if (Build.VERSION.SDK_INT >= 15) {
            remoteViews.setContentDescription(C0309R.C0311id.action0, action.getTitle());
        }
        return remoteViews;
    }

    @TargetApi(11)
    @RequiresApi(11)
    private static int getBigMediaLayoutResource(boolean z, int i) {
        return i <= 3 ? z ? C0309R.layout.notification_template_big_media_narrow_custom : C0309R.layout.notification_template_big_media_narrow : z ? C0309R.layout.notification_template_big_media_custom : C0309R.layout.notification_template_big_media;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    public static RemoteViews applyStandardTemplateWithActions(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, int i2, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i3, int i4, int i5, boolean z2, ArrayList<NotificationCompat.Action> arrayList) {
        boolean z3;
        int i6;
        RemoteViews applyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, i, i2, bitmap, charSequence4, z, j, i3, i4, i5, z2);
        applyStandardTemplate.removeAllViews(C0309R.C0311id.actions);
        if (arrayList != null) {
            int size = arrayList.size();
            if (size > 0) {
                if (size > 3) {
                    i6 = 3;
                } else {
                    i6 = size;
                }
                for (int i7 = 0; i7 < i6; i7++) {
                    applyStandardTemplate.addView(C0309R.C0311id.actions, generateActionButton(context, arrayList.get(i7)));
                }
                z3 = true;
                int i8 = !z3 ? 0 : 8;
                applyStandardTemplate.setViewVisibility(C0309R.C0311id.actions, i8);
                applyStandardTemplate.setViewVisibility(C0309R.C0311id.action_divider, i8);
                return applyStandardTemplate;
            }
        }
        z3 = false;
        if (!z3) {
        }
        applyStandardTemplate.setViewVisibility(C0309R.C0311id.actions, i8);
        applyStandardTemplate.setViewVisibility(C0309R.C0311id.action_divider, i8);
        return applyStandardTemplate;
    }

    private static RemoteViews generateActionButton(Context context, NotificationCompat.Action action) {
        int actionLayoutResource;
        boolean z = action.actionIntent == null;
        String packageName = context.getPackageName();
        if (z) {
            actionLayoutResource = getActionTombstoneLayoutResource();
        } else {
            actionLayoutResource = getActionLayoutResource();
        }
        RemoteViews remoteViews = new RemoteViews(packageName, actionLayoutResource);
        remoteViews.setImageViewBitmap(C0309R.C0311id.action_image, createColoredBitmap(context, action.getIcon(), context.getResources().getColor(C0309R.color.notification_action_color_filter)));
        remoteViews.setTextViewText(C0309R.C0311id.action_text, action.title);
        if (!z) {
            remoteViews.setOnClickPendingIntent(C0309R.C0311id.action_container, action.actionIntent);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            remoteViews.setContentDescription(C0309R.C0311id.action_container, action.title);
        }
        return remoteViews;
    }

    private static Bitmap createColoredBitmap(Context context, int i, int i2) {
        return createColoredBitmap(context, i, i2, 0);
    }

    private static Bitmap createColoredBitmap(Context context, int i, int i2, int i3) {
        Drawable drawable = context.getResources().getDrawable(i);
        int intrinsicWidth = i3 == 0 ? drawable.getIntrinsicWidth() : i3;
        if (i3 == 0) {
            i3 = drawable.getIntrinsicHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i3, Bitmap.Config.ARGB_8888);
        drawable.setBounds(0, 0, intrinsicWidth, i3);
        if (i2 != 0) {
            drawable.mutate().setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN));
        }
        drawable.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    private static int getActionLayoutResource() {
        return C0309R.layout.notification_action;
    }

    private static int getActionTombstoneLayoutResource() {
        return C0309R.layout.notification_action_tombstone;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01ed  */
    public static RemoteViews applyStandardTemplate(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, int i2, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i3, int i4, int i5, boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        Resources resources = context.getResources();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), i5);
        boolean z7 = i3 < -1;
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
            if (z7) {
                remoteViews.setInt(C0309R.C0311id.notification_background, "setBackgroundResource", C0309R.C0310drawable.notification_bg_low);
                remoteViews.setInt(C0309R.C0311id.icon, "setBackgroundResource", C0309R.C0310drawable.notification_template_icon_low_bg);
            } else {
                remoteViews.setInt(C0309R.C0311id.notification_background, "setBackgroundResource", C0309R.C0310drawable.notification_bg);
                remoteViews.setInt(C0309R.C0311id.icon, "setBackgroundResource", C0309R.C0310drawable.notification_template_icon_bg);
            }
        }
        if (bitmap != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                remoteViews.setViewVisibility(C0309R.C0311id.icon, 0);
                remoteViews.setImageViewBitmap(C0309R.C0311id.icon, bitmap);
            } else {
                remoteViews.setViewVisibility(C0309R.C0311id.icon, 8);
            }
            if (i2 != 0) {
                int dimensionPixelSize = resources.getDimensionPixelSize(C0309R.dimen.notification_right_icon_size);
                int dimensionPixelSize2 = dimensionPixelSize - (resources.getDimensionPixelSize(C0309R.dimen.notification_small_icon_background_padding) * 2);
                if (Build.VERSION.SDK_INT >= 21) {
                    remoteViews.setImageViewBitmap(C0309R.C0311id.right_icon, createIconWithBackground(context, i2, dimensionPixelSize, dimensionPixelSize2, i4));
                } else {
                    remoteViews.setImageViewBitmap(C0309R.C0311id.right_icon, createColoredBitmap(context, i2, -1));
                }
                remoteViews.setViewVisibility(C0309R.C0311id.right_icon, 0);
            }
        } else if (i2 != 0) {
            remoteViews.setViewVisibility(C0309R.C0311id.icon, 0);
            if (Build.VERSION.SDK_INT >= 21) {
                remoteViews.setImageViewBitmap(C0309R.C0311id.icon, createIconWithBackground(context, i2, resources.getDimensionPixelSize(C0309R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(C0309R.dimen.notification_big_circle_margin), resources.getDimensionPixelSize(C0309R.dimen.notification_small_icon_size_as_large), i4));
            } else {
                remoteViews.setImageViewBitmap(C0309R.C0311id.icon, createColoredBitmap(context, i2, -1));
            }
        }
        if (charSequence != null) {
            remoteViews.setTextViewText(C0309R.C0311id.title, charSequence);
        }
        if (charSequence2 != null) {
            remoteViews.setTextViewText(C0309R.C0311id.text, charSequence2);
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z8 = Build.VERSION.SDK_INT < 21 && bitmap != null;
        if (charSequence3 != null) {
            remoteViews.setTextViewText(C0309R.C0311id.info, charSequence3);
            remoteViews.setViewVisibility(C0309R.C0311id.info, 0);
            z8 = true;
            z4 = true;
        } else if (i > 0) {
            if (i > resources.getInteger(C0309R.integer.status_bar_notification_info_maxnum)) {
                remoteViews.setTextViewText(C0309R.C0311id.info, resources.getString(C0309R.string.status_bar_notification_info_overflow));
            } else {
                remoteViews.setTextViewText(C0309R.C0311id.info, NumberFormat.getIntegerInstance().format((long) i));
            }
            remoteViews.setViewVisibility(C0309R.C0311id.info, 0);
            z8 = true;
            z4 = true;
        } else {
            remoteViews.setViewVisibility(C0309R.C0311id.info, 8);
            z4 = z3;
        }
        if (charSequence4 != null && Build.VERSION.SDK_INT >= 16) {
            remoteViews.setTextViewText(C0309R.C0311id.text, charSequence4);
            if (charSequence2 != null) {
                remoteViews.setTextViewText(C0309R.C0311id.text2, charSequence2);
                remoteViews.setViewVisibility(C0309R.C0311id.text2, 0);
                z5 = true;
                if (z5 && Build.VERSION.SDK_INT >= 16) {
                    if (z2) {
                        remoteViews.setTextViewTextSize(C0309R.C0311id.text, 0, (float) resources.getDimensionPixelSize(C0309R.dimen.notification_subtext_size));
                    }
                    remoteViews.setViewPadding(C0309R.C0311id.line1, 0, 0, 0, 0);
                }
                if (j == 0) {
                    if (!z || Build.VERSION.SDK_INT < 16) {
                        remoteViews.setViewVisibility(C0309R.C0311id.time, 0);
                        remoteViews.setLong(C0309R.C0311id.time, "setTime", j);
                    } else {
                        remoteViews.setViewVisibility(C0309R.C0311id.chronometer, 0);
                        remoteViews.setLong(C0309R.C0311id.chronometer, "setBase", (SystemClock.elapsedRealtime() - System.currentTimeMillis()) + j);
                        remoteViews.setBoolean(C0309R.C0311id.chronometer, "setStarted", true);
                    }
                    z6 = true;
                } else {
                    z6 = z8;
                }
                remoteViews.setViewVisibility(C0309R.C0311id.right_side, !z6 ? 0 : 8);
                remoteViews.setViewVisibility(C0309R.C0311id.line3, !z4 ? 0 : 8);
                return remoteViews;
            }
            remoteViews.setViewVisibility(C0309R.C0311id.text2, 8);
        }
        z5 = false;
        if (z2) {
        }
        remoteViews.setViewPadding(C0309R.C0311id.line1, 0, 0, 0, 0);
        if (j == 0) {
        }
        remoteViews.setViewVisibility(C0309R.C0311id.right_side, !z6 ? 0 : 8);
        remoteViews.setViewVisibility(C0309R.C0311id.line3, !z4 ? 0 : 8);
        return remoteViews;
    }

    public static Bitmap createIconWithBackground(Context context, int i, int i2, int i3, int i4) {
        int i5 = C0309R.C0310drawable.notification_icon_background;
        if (i4 == 0) {
            i4 = 0;
        }
        Bitmap createColoredBitmap = createColoredBitmap(context, i5, i4, i2);
        Canvas canvas = new Canvas(createColoredBitmap);
        Drawable mutate = context.getResources().getDrawable(i).mutate();
        mutate.setFilterBitmap(true);
        int i6 = (i2 - i3) / 2;
        mutate.setBounds(i6, i6, i3 + i6, i3 + i6);
        mutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
        mutate.draw(canvas);
        return createColoredBitmap;
    }

    public static void buildIntoRemoteViews(Context context, RemoteViews remoteViews, RemoteViews remoteViews2) {
        hideNormalContent(remoteViews);
        remoteViews.removeAllViews(C0309R.C0311id.notification_main_column);
        remoteViews.addView(C0309R.C0311id.notification_main_column, remoteViews2.clone());
        remoteViews.setViewVisibility(C0309R.C0311id.notification_main_column, 0);
        if (Build.VERSION.SDK_INT >= 21) {
            remoteViews.setViewPadding(C0309R.C0311id.notification_main_column_container, 0, calculateTopPadding(context), 0, 0);
        }
    }

    private static void hideNormalContent(RemoteViews remoteViews) {
        remoteViews.setViewVisibility(C0309R.C0311id.title, 8);
        remoteViews.setViewVisibility(C0309R.C0311id.text2, 8);
        remoteViews.setViewVisibility(C0309R.C0311id.text, 8);
    }

    public static int calculateTopPadding(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(C0309R.dimen.notification_top_pad);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(C0309R.dimen.notification_top_pad_large_text);
        float constrain = (constrain(context.getResources().getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
        return Math.round((((float) dimensionPixelSize) * (1.0f - constrain)) + (((float) dimensionPixelSize2) * constrain));
    }

    public static float constrain(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        return f > f3 ? f3 : f;
    }
}
