package androidx.core.view;

import android.graphics.Insets;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.DisplayCutout;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DisplayCutoutCompat {
    private final DisplayCutout mDisplayCutout;

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static DisplayCutout createDisplayCutout(Rect rect, List<Rect> list) {
            return new DisplayCutout(rect, list);
        }

        public static List<Rect> getBoundingRects(DisplayCutout displayCutout) {
            return displayCutout.getBoundingRects();
        }

        public static int getSafeInsetBottom(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetBottom();
        }

        public static int getSafeInsetLeft(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetLeft();
        }

        public static int getSafeInsetRight(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetRight();
        }

        public static int getSafeInsetTop(DisplayCutout displayCutout) {
            return displayCutout.getSafeInsetTop();
        }
    }

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static DisplayCutout createDisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4) {
            return new DisplayCutout(insets, rect, rect2, rect3, rect4);
        }
    }

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static DisplayCutout createDisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2) {
            return new DisplayCutout(insets, rect, rect2, rect3, rect4, insets2);
        }

        public static Insets getWaterfallInsets(DisplayCutout displayCutout) {
            return displayCutout.getWaterfallInsets();
        }
    }

    @RequiresApi(31)
    public static class Api31Impl {
        private Api31Impl() {
        }

        public static Path getCutoutPath(DisplayCutout displayCutout) {
            return displayCutout.getCutoutPath();
        }
    }

    @RequiresApi(33)
    public static class Api33Impl {
        private Api33Impl() {
        }

        public static DisplayCutout createDisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2, Path path) {
            DisplayCutout.Builder waterfallInsets = new DisplayCutout.Builder().setSafeInsets(insets).setWaterfallInsets(insets2);
            if (rect != null) {
                waterfallInsets.setBoundingRectLeft(rect);
            }
            if (rect2 != null) {
                waterfallInsets.setBoundingRectTop(rect2);
            }
            if (rect3 != null) {
                waterfallInsets.setBoundingRectRight(rect3);
            }
            if (rect4 != null) {
                waterfallInsets.setBoundingRectBottom(rect4);
            }
            if (path != null) {
                waterfallInsets.setCutoutPath(path);
            }
            return waterfallInsets.build();
        }
    }

    public DisplayCutoutCompat(Rect rect, List<Rect> list) {
        this(Api28Impl.createDisplayCutout(rect, list));
    }

    private static DisplayCutout constructDisplayCutout(androidx.core.graphics.Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, androidx.core.graphics.Insets insets2, Path path) {
        return Api33Impl.createDisplayCutout(insets.toPlatformInsets(), rect, rect2, rect3, rect4, insets2.toPlatformInsets(), path);
    }

    public static DisplayCutoutCompat wrap(DisplayCutout displayCutout) {
        if (displayCutout == null) {
            return null;
        }
        return new DisplayCutoutCompat(displayCutout);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DisplayCutoutCompat.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.mDisplayCutout, ((DisplayCutoutCompat) obj).mDisplayCutout);
    }

    public List<Rect> getBoundingRects() {
        return Api28Impl.getBoundingRects(this.mDisplayCutout);
    }

    public Path getCutoutPath() {
        return Api31Impl.getCutoutPath(this.mDisplayCutout);
    }

    public int getSafeInsetBottom() {
        return Api28Impl.getSafeInsetBottom(this.mDisplayCutout);
    }

    public int getSafeInsetLeft() {
        return Api28Impl.getSafeInsetLeft(this.mDisplayCutout);
    }

    public int getSafeInsetRight() {
        return Api28Impl.getSafeInsetRight(this.mDisplayCutout);
    }

    public int getSafeInsetTop() {
        return Api28Impl.getSafeInsetTop(this.mDisplayCutout);
    }

    public androidx.core.graphics.Insets getWaterfallInsets() {
        return androidx.core.graphics.Insets.toCompatInsets(Api30Impl.getWaterfallInsets(this.mDisplayCutout));
    }

    public int hashCode() {
        DisplayCutout displayCutout = this.mDisplayCutout;
        if (displayCutout == null) {
            return 0;
        }
        return displayCutout.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }

    @RequiresApi(28)
    public DisplayCutout unwrap() {
        return this.mDisplayCutout;
    }

    public DisplayCutoutCompat(androidx.core.graphics.Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, androidx.core.graphics.Insets insets2) {
        this(constructDisplayCutout(insets, rect, rect2, rect3, rect4, insets2, null));
    }

    public DisplayCutoutCompat(androidx.core.graphics.Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, androidx.core.graphics.Insets insets2, Path path) {
        this(constructDisplayCutout(insets, rect, rect2, rect3, rect4, insets2, path));
    }

    private DisplayCutoutCompat(DisplayCutout displayCutout) {
        this.mDisplayCutout = displayCutout;
    }
}
