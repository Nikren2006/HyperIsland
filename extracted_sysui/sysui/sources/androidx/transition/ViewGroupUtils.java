package androidx.transition;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
class ViewGroupUtils {
    private static Method sGetChildDrawingOrderMethod = null;
    private static boolean sGetChildDrawingOrderMethodFetched = false;
    private static boolean sTryHiddenSuppressLayout = true;

    private ViewGroupUtils() {
    }

    public static int getChildDrawingOrder(@NonNull ViewGroup viewGroup, int i2) {
        return viewGroup.getChildDrawingOrder(i2);
    }

    public static ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup viewGroup) {
        return new ViewGroupOverlayApi18(viewGroup);
    }

    @RequiresApi(18)
    @SuppressLint({"NewApi"})
    private static void hiddenSuppressLayout(@NonNull ViewGroup viewGroup, boolean z2) {
        if (sTryHiddenSuppressLayout) {
            try {
                viewGroup.suppressLayout(z2);
            } catch (NoSuchMethodError unused) {
                sTryHiddenSuppressLayout = false;
            }
        }
    }

    public static void suppressLayout(@NonNull ViewGroup viewGroup, boolean z2) {
        viewGroup.suppressLayout(z2);
    }
}
