package androidx.core.widget;

import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class PopupWindowCompat {
    private static final String TAG = "PopupWindowCompatApi21";
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Field sOverlapAnchorField;
    private static boolean sOverlapAnchorFieldAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    @RequiresApi(23)
    public static class Api23Impl {
        private Api23Impl() {
        }

        public static boolean getOverlapAnchor(PopupWindow popupWindow) {
            return popupWindow.getOverlapAnchor();
        }

        public static int getWindowLayoutType(PopupWindow popupWindow) {
            return popupWindow.getWindowLayoutType();
        }

        public static void setOverlapAnchor(PopupWindow popupWindow, boolean z2) {
            popupWindow.setOverlapAnchor(z2);
        }

        public static void setWindowLayoutType(PopupWindow popupWindow, int i2) {
            popupWindow.setWindowLayoutType(i2);
        }
    }

    private PopupWindowCompat() {
    }

    public static boolean getOverlapAnchor(PopupWindow popupWindow) {
        return Api23Impl.getOverlapAnchor(popupWindow);
    }

    public static int getWindowLayoutType(PopupWindow popupWindow) {
        return Api23Impl.getWindowLayoutType(popupWindow);
    }

    public static void setOverlapAnchor(PopupWindow popupWindow, boolean z2) {
        Api23Impl.setOverlapAnchor(popupWindow, z2);
    }

    public static void setWindowLayoutType(PopupWindow popupWindow, int i2) {
        Api23Impl.setWindowLayoutType(popupWindow, i2);
    }

    @ReplaceWith(expression = "popup.showAsDropDown(anchor, xoff, yoff, gravity)")
    @Deprecated
    public static void showAsDropDown(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
        popupWindow.showAsDropDown(view, i2, i3, i4);
    }
}
