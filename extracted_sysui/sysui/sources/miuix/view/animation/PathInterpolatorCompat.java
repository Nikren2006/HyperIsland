package miuix.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes5.dex */
public final class PathInterpolatorCompat {

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static PathInterpolator createPathInterpolator(Path path) {
            return new PathInterpolator(path);
        }

        public static PathInterpolator createPathInterpolator(float f2, float f3) {
            return new PathInterpolator(f2, f3);
        }

        public static PathInterpolator createPathInterpolator(float f2, float f3, float f4, float f5) {
            return new PathInterpolator(f2, f3, f4, f5);
        }
    }

    private PathInterpolatorCompat() {
    }

    @NonNull
    public static Interpolator create(@NonNull Path path) {
        return Api26Impl.createPathInterpolator(path);
    }

    @NonNull
    public static Interpolator create(float f2, float f3) {
        return Api26Impl.createPathInterpolator(f2, f3);
    }

    @NonNull
    public static Interpolator create(float f2, float f3, float f4, float f5) {
        return Api26Impl.createPathInterpolator(f2, f3, f4, f5);
    }
}
