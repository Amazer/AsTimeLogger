package timelogger.cyc.astimelogger;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by cyc on 2017/9/26.
 */

public class ConstantValue {
    public static int displayWidth;      // 屏幕宽度
    public static int displayHeight;    // 屏幕高度

    public static void InitConstantValue(Activity act) {

        DisplayMetrics dispalyMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
        displayWidth = dispalyMetrics.widthPixels;
        displayHeight = dispalyMetrics.heightPixels;
        dispalyMetrics = null;
    }

    public static int GetPixelsWidthPercent(float percent) {
        percent = Math.max(0, percent);
        return (int) (displayWidth * percent);
    }

    public static int GetPixelsHeightPercent(float percent) {
        percent = Math.max(0, percent);
        return (int) (displayHeight * percent);
    }
}
