package miuix.colorful.texteffect;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;

/* JADX INFO: loaded from: classes3.dex */
public class TextEffectUtils {
    static RenderEffect[] PREBUILT_BLUR_RENDER_EFFECTS = new RenderEffect[30];

    static {
        int i2 = 0;
        while (i2 < 30) {
            int i3 = i2 + 1;
            float f2 = i3;
            try {
                PREBUILT_BLUR_RENDER_EFFECTS[i2] = RenderEffect.createBlurEffect(f2, f2, Shader.TileMode.CLAMP);
            } catch (Exception e2) {
                Log.d("TextEffectUtils", "createBlurEffect fail index: " + i2 + " error:" + e2);
            }
            i2 = i3;
        }
    }

    public static float adjustTextDrawX(String str, float f2, boolean z2, boolean z3, int i2, Paint paint, int i3) {
        if (str == null) {
            return f2;
        }
        if (z2) {
            return 0.0f;
        }
        if (z3 || (i2 & 8388611) == 8388611 || (i2 & GravityCompat.END) == 8388613 || (i2 & 1) != 1) {
            return f2;
        }
        return (i3 - paint.measureText(str)) / 2.0f;
    }

    @Nullable
    public static Bitmap createTextBitmap(Paint paint, @Nullable String str, boolean z2, float f2) {
        if (str == null) {
            return null;
        }
        Canvas canvas = new Canvas();
        float fMeasureText = paint.measureText(str);
        int i2 = -paint.getFontMetricsInt().top;
        int i3 = paint.getFontMetricsInt().bottom - paint.getFontMetricsInt().top;
        if (!str.isEmpty()) {
            Paint.FontMetricsInt fontMetricsInt = new Paint.FontMetricsInt();
            paint.getFontMetricsInt((CharSequence) str, 0, str.length(), 0, str.length(), false, fontMetricsInt);
            int i4 = fontMetricsInt.top;
            i3 = fontMetricsInt.bottom - i4;
            i2 = -i4;
        }
        int i5 = (int) (z2 ? f2 : fMeasureText);
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i5, i3, Bitmap.Config.ARGB_8888);
            try {
                canvas.setBitmap(bitmapCreateBitmap);
                canvas.drawText(str, (i5 - fMeasureText) / 2.0f, i2, paint);
            } catch (Exception unused) {
                if (bitmapCreateBitmap != null && !bitmapCreateBitmap.isRecycled()) {
                    bitmapCreateBitmap.recycle();
                }
            }
            return bitmapCreateBitmap;
        } catch (IllegalArgumentException | OutOfMemoryError unused2) {
            return null;
        }
    }

    public static void drawNumericAnimContent(@Nullable String str, TextSwitcherAnimator textSwitcherAnimator, @Nullable RenderNode renderNode, Matrix matrix, @NonNull SparseArray<Bitmap> sparseArray, float f2, float f3, int i2, int i3, int i4, Canvas canvas, Paint paint) {
        Bitmap bitmap;
        if (str == null || (bitmap = sparseArray.get(Integer.parseInt(str))) == null) {
            return;
        }
        int iMax = Math.max(0, Math.min(255, textSwitcherAnimator.getAlphaInt()));
        int iMax2 = Math.max(0, Math.min(30, textSwitcherAnimator.getBlurRadius()));
        int translationY = (int) textSwitcherAnimator.getTranslationY();
        float rotationX = textSwitcherAnimator.getRotationX();
        float scale = textSwitcherAnimator.getScale();
        if (renderNode == null) {
            paint.setAlpha(iMax);
            matrix.reset();
            matrix.postScale(scale, scale, f2 / 2.0f, (i4 - i2) / 2.0f);
            matrix.postTranslate(f3, i2 + translationY);
            canvas.drawBitmap(bitmap, matrix, paint);
            return;
        }
        renderNode.setRotationX(rotationX);
        if (textSwitcherAnimator.getEffectLevel() >= 3) {
            if (iMax2 > 0) {
                renderNode.setRenderEffect(PREBUILT_BLUR_RENDER_EFFECTS[iMax2 - 1]);
            } else {
                renderNode.setRenderEffect(null);
            }
        }
        renderNode.setTranslationY(translationY);
        renderNode.setAlpha(iMax / 255.0f);
        renderNode.setScaleX(scale);
        renderNode.setScaleY(scale);
        renderNode.setPosition((int) f3, i2, (int) (f3 + bitmap.getWidth()), i4);
        renderNode.beginRecording(bitmap.getWidth(), bitmap.getHeight()).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        renderNode.endRecording();
        canvas.drawRenderNode(renderNode);
    }

    public static void drawTextBitmapAnimContent(@Nullable Bitmap bitmap, TextSwitcherAnimator textSwitcherAnimator, @Nullable RenderNode renderNode, Matrix matrix, float[] fArr, float f2, float f3, int i2, int i3, int i4, Canvas canvas, Paint paint) {
        if (bitmap != null && canvas.isHardwareAccelerated()) {
            int iMax = Math.max(0, Math.min(255, textSwitcherAnimator.getAlphaInt()));
            int iMax2 = Math.max(0, Math.min(30, textSwitcherAnimator.getBlurRadius()));
            int translationY = (int) textSwitcherAnimator.getTranslationY();
            float rotationX = textSwitcherAnimator.getRotationX();
            float scale = textSwitcherAnimator.getScale();
            if (renderNode == null) {
                paint.setAlpha(iMax);
                matrix.reset();
                matrix.postScale(scale, scale, f2 / 2.0f, (i4 - i2) / 2.0f);
                if (fArr == null || fArr.length <= 0) {
                    matrix.postTranslate(f3, i2 + translationY);
                } else {
                    matrix.postTranslate(fArr[0] + f3, i2 + translationY);
                }
                canvas.drawBitmap(bitmap, matrix, paint);
                return;
            }
            renderNode.setRotationX(rotationX);
            if (textSwitcherAnimator.getEffectLevel() >= 3) {
                if (iMax2 > 0) {
                    renderNode.setRenderEffect(PREBUILT_BLUR_RENDER_EFFECTS[iMax2 - 1]);
                } else {
                    renderNode.setRenderEffect(null);
                }
            }
            renderNode.setTranslationY(translationY);
            renderNode.setAlpha(iMax / 255.0f);
            renderNode.setScaleX(scale);
            renderNode.setScaleY(scale);
            if (fArr == null || fArr.length <= 0) {
                renderNode.setPosition((int) f3, i2, (int) (bitmap.getWidth() + f3), i4);
            } else {
                float f4 = fArr[0];
                renderNode.setPosition((int) (f3 + f4), i2, (int) (f4 + f3 + bitmap.getWidth()), i4);
            }
            renderNode.beginRecording(bitmap.getWidth(), bitmap.getHeight()).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            renderNode.endRecording();
            canvas.drawRenderNode(renderNode);
        }
    }

    public static void findMinEndIndex(String str, Paint paint, int i2, int i3, int i4, TextUtils.TruncateAt truncateAt, String str2, Point point) {
        if (paint.measureText(str.substring(i3, i4) + str2) <= i2) {
            point.set(i3, i4);
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.START || truncateAt == TextUtils.TruncateAt.MIDDLE) {
            int i5 = i3 + 1;
            if (i5 > i4 - 1) {
                point.set(i3, i4);
                return;
            } else {
                findMinEndIndex(str, paint, i2, i5, i4, truncateAt, str2, point);
                return;
            }
        }
        if (truncateAt == TextUtils.TruncateAt.END) {
            int i6 = i4 - 1;
            if (i6 < i3 + 1) {
                point.set(i3, i4);
            } else {
                findMinEndIndex(str, paint, i2, i3, i6, truncateAt, str2, point);
            }
        }
    }

    public static String getFinalEllipsizeText(String str, TextUtils.TruncateAt truncateAt, boolean z2, Point point, String str2) {
        int length = str.length();
        int i2 = point.y;
        int i3 = point.x;
        if (length == i2 - i3) {
            return str;
        }
        if (truncateAt == TextUtils.TruncateAt.MIDDLE) {
            return new StringBuilder(str.substring(i3, i2)).insert(point.y / 2, str2).toString();
        }
        if (truncateAt == TextUtils.TruncateAt.END) {
            if (z2) {
                return str2 + str.substring(point.x, point.y);
            }
            return str.substring(point.x, point.y) + str2;
        }
        if (truncateAt != TextUtils.TruncateAt.START) {
            return str;
        }
        if (z2) {
            return str.substring(point.x, point.y) + str2;
        }
        return str2 + str.substring(point.x, point.y);
    }

    public static boolean isGoogleSystem() {
        return "Google".equals(Build.MANUFACTURER);
    }
}
