package android.support.p001v4.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;

@TargetApi(24)
@RequiresApi(24)
/* renamed from: android.support.v4.print.PrintHelperApi24 */
class PrintHelperApi24 extends PrintHelperApi23 {
    PrintHelperApi24(Context context) {
        super(context);
        this.mIsMinMarginsHandlingCorrect = true;
        this.mPrintActivityRespectsOrientation = true;
    }
}
