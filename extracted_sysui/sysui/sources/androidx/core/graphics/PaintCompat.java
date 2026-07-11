package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.BlendModeUtils;
import androidx.core.util.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class PaintCompat {
    private static final String EM_STRING = "m";
    private static final String TOFU_STRING = "\udfffd";
    private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    @RequiresApi(23)
    public static class Api23Impl {
        private Api23Impl() {
        }

        public static boolean hasGlyph(Paint paint, String str) {
            return paint.hasGlyph(str);
        }
    }

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void setBlendMode(Paint paint, Object obj) {
            paint.setBlendMode((BlendMode) obj);
        }
    }

    private PaintCompat() {
    }

    public static boolean hasGlyph(Paint paint, String str) {
        return Api23Impl.hasGlyph(paint, str);
    }

    private static Pair<Rect, Rect> obtainEmptyRects() {
        ThreadLocal<Pair<Rect, Rect>> threadLocal = sRectThreadLocal;
        Pair<Rect, Rect> pair = threadLocal.get();
        if (pair == null) {
            Pair<Rect, Rect> pair2 = new Pair<>(new Rect(), new Rect());
            threadLocal.set(pair2);
            return pair2;
        }
        pair.first.setEmpty();
        pair.second.setEmpty();
        return pair;
    }

    public static boolean setBlendMode(Paint paint, BlendModeCompat blendModeCompat) {
        Api29Impl.setBlendMode(paint, blendModeCompat != null ? BlendModeUtils.Api29Impl.obtainBlendModeFromCompat(blendModeCompat) : null);
        return true;
    }
}
