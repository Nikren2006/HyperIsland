package androidx.core.view;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class VelocityTrackerCompat {
    private static Map<VelocityTracker, VelocityTrackerFallback> sFallbackTrackers = Collections.synchronizedMap(new WeakHashMap());

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static float getAxisVelocity(VelocityTracker velocityTracker, int i2, int i3) {
            return velocityTracker.getAxisVelocity(i2, i3);
        }

        public static boolean isAxisSupported(VelocityTracker velocityTracker, int i2) {
            return velocityTracker.isAxisSupported(i2);
        }

        public static float getAxisVelocity(VelocityTracker velocityTracker, int i2) {
            return velocityTracker.getAxisVelocity(i2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface VelocityTrackableMotionEventAxis {
    }

    private VelocityTrackerCompat() {
    }

    public static void addMovement(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        velocityTracker.addMovement(motionEvent);
    }

    public static void clear(VelocityTracker velocityTracker) {
        velocityTracker.clear();
        removeFallbackForTracker(velocityTracker);
    }

    public static void computeCurrentVelocity(VelocityTracker velocityTracker, int i2, float f2) {
        velocityTracker.computeCurrentVelocity(i2, f2);
        VelocityTrackerFallback fallbackTrackerOrNull = getFallbackTrackerOrNull(velocityTracker);
        if (fallbackTrackerOrNull != null) {
            fallbackTrackerOrNull.computeCurrentVelocity(i2, f2);
        }
    }

    public static float getAxisVelocity(VelocityTracker velocityTracker, int i2) {
        return Api34Impl.getAxisVelocity(velocityTracker, i2);
    }

    private static VelocityTrackerFallback getFallbackTrackerOrNull(VelocityTracker velocityTracker) {
        return sFallbackTrackers.get(velocityTracker);
    }

    @ReplaceWith(expression = "tracker.getXVelocity(pointerId)")
    @Deprecated
    public static float getXVelocity(VelocityTracker velocityTracker, int i2) {
        return velocityTracker.getXVelocity(i2);
    }

    @ReplaceWith(expression = "tracker.getYVelocity(pointerId)")
    @Deprecated
    public static float getYVelocity(VelocityTracker velocityTracker, int i2) {
        return velocityTracker.getYVelocity(i2);
    }

    public static boolean isAxisSupported(VelocityTracker velocityTracker, int i2) {
        return Api34Impl.isAxisSupported(velocityTracker, i2);
    }

    public static void recycle(VelocityTracker velocityTracker) {
        velocityTracker.recycle();
        removeFallbackForTracker(velocityTracker);
    }

    private static void removeFallbackForTracker(VelocityTracker velocityTracker) {
        sFallbackTrackers.remove(velocityTracker);
    }

    public static float getAxisVelocity(VelocityTracker velocityTracker, int i2, int i3) {
        return Api34Impl.getAxisVelocity(velocityTracker, i2, i3);
    }

    public static void computeCurrentVelocity(VelocityTracker velocityTracker, int i2) {
        computeCurrentVelocity(velocityTracker, i2, Float.MAX_VALUE);
    }
}
