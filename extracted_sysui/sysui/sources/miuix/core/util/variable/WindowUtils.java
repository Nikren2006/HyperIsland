package miuix.core.util.variable;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes3.dex */
public class WindowUtils {
    private WindowUtils() {
    }

    public static void changeWindowBackground(Activity activity, float f2) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.alpha = f2;
        activity.getWindow().setAttributes(attributes);
    }

    public static void setTranslucentStatus(Window window, int i2) {
        WindowWrapper.setTranslucentStatus(window, i2);
    }
}
