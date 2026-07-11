package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
class CanvasUtils {
    private static Method sInorderBarrierMethod;
    private static boolean sOrderMethodsFetched;
    private static Method sReorderBarrierMethod;

    private CanvasUtils() {
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    public static void enableZ(@NonNull Canvas canvas, boolean z2) {
        if (z2) {
            canvas.enableZ();
        } else {
            canvas.disableZ();
        }
    }
}
