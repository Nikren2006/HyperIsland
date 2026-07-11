package miuix.overscroller.widget;

import android.view.View;
import androidx.annotation.NonNull;
import miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler;

/* JADX INFO: loaded from: classes.dex */
public class AnimationHelper {
    public static void postInvalidateOnAnimation(View view) {
        AnimationHandler.getInstance().postVsyncCallback();
        view.postInvalidateOnAnimation();
    }

    public static void postOnAnimation(@NonNull View view, @NonNull Runnable runnable) {
        AnimationHandler.getInstance().postVsyncCallback();
        view.postOnAnimation(runnable);
    }
}
