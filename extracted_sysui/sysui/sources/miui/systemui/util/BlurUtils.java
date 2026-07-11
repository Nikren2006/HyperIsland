package miui.systemui.util;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import miui.systemui.quicksettings.common.R;
import miuix.os.DeviceHelper;

/* JADX INFO: loaded from: classes4.dex */
public class BlurUtils {
    private static final String DISABLE_WINDOW_BLURS = "disable_window_blurs";
    private static final String TAG = "BlurUtils";
    private boolean mBlurDisabled = false;
    private final ContentObserver mContentObserver;
    private Context mContext;

    public BlurUtils(Context context) {
        ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: miui.systemui.util.BlurUtils.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2) {
                BlurUtils.this.updateBlurDisabled();
            }
        };
        this.mContentObserver = contentObserver;
        this.mContext = context;
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(DISABLE_WINDOW_BLURS), false, contentObserver);
        updateBlurDisabled();
    }

    private int blur2BgColor(Float f2, int i2) {
        int color = this.mContext.getColor(i2);
        int i3 = (color >> 24) & 255;
        return (color & (~(i3 << 24))) | (((int) (i3 * MiuiMathUtils.INSTANCE.constrain(f2.floatValue(), 0.0f, 1.0f))) << 24);
    }

    public static boolean isFlipDevice(Context context) {
        return DeviceHelper.detectType(context) == 4 || CommonUtils.isFlipDevice();
    }

    public static boolean isFlipTinyScreen(Context context) {
        return isFlipDevice(context) && isTinyScreen(context);
    }

    public static boolean isLowEndDevice() {
        return DeviceUtils.isLowEndDevice();
    }

    public static boolean isTinyScreen(Context context) {
        Point screenSize = CommonUtils.getScreenSize(context);
        return ((int) (((float) Math.max(screenSize.x, screenSize.y)) / context.getResources().getDisplayMetrics().density)) <= 670;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBlurDisabled() {
        this.mBlurDisabled = Settings.Global.getInt(this.mContext.getContentResolver(), DISABLE_WINDOW_BLURS, 0) != 0;
    }

    public void destroy() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
        this.mContext = null;
    }

    public void setBackgroundBlur(View view, Float f2, Window window) {
        if ((isLowEndDevice() || this.mBlurDisabled || window == null) && view != null) {
            view.setBackgroundColor(blur2BgColor(f2, R.color.volume_and_globalActions_solid_background_color));
            return;
        }
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(blur2BgColor(f2, R.color.volume_and_globalActions_blur_dim_color)));
            try {
                Window.class.getMethod("setBackgroundBlurRadius", Integer.TYPE).invoke(window, Integer.valueOf(CommonUtils.INSTANCE.blurRadiusOfRatio(f2.floatValue())));
            } catch (Exception e2) {
                Log.e(TAG, "setBackgroundBlurRadius Exception", e2);
            }
        }
    }

    public void setBackgroundBlur(View view, Float f2, Window window, int i2, int i3) {
        if ((isLowEndDevice() || this.mBlurDisabled) && view != null) {
            view.setBackgroundColor(blur2BgColor(f2, i3));
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(blur2BgColor(f2, i2)));
        try {
            Window.class.getMethod("setBackgroundBlurRadius", Integer.TYPE).invoke(window, Integer.valueOf(CommonUtils.INSTANCE.blurRadiusOfRatio(f2.floatValue())));
        } catch (Exception e2) {
            Log.e(TAG, "setBackgroundBlurRadius Exception", e2);
        }
    }
}
