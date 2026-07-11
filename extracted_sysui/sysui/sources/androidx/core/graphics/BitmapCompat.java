package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapCompat {

    @RequiresApi(27)
    public static class Api27Impl {
        private Api27Impl() {
        }

        public static Bitmap copyBitmapIfHardware(Bitmap bitmap) {
            if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
                return bitmap;
            }
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            return bitmap.copy(Api31Impl.getHardwareBitmapConfig(bitmap), true);
        }

        public static Bitmap createBitmapWithSourceColorspace(int i2, int i3, Bitmap bitmap, boolean z2) {
            Bitmap.Config config = bitmap.getConfig();
            ColorSpace colorSpace = bitmap.getColorSpace();
            ColorSpace colorSpace2 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            if (z2 && !bitmap.getColorSpace().equals(colorSpace2)) {
                config = Bitmap.Config.RGBA_F16;
                colorSpace = colorSpace2;
            } else if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
                config = Api31Impl.getHardwareBitmapConfig(bitmap);
            }
            return Bitmap.createBitmap(i2, i3, config, bitmap.hasAlpha(), colorSpace);
        }

        public static boolean isAlreadyF16AndLinear(Bitmap bitmap) {
            return bitmap.getConfig() == Bitmap.Config.RGBA_F16 && bitmap.getColorSpace().equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB));
        }
    }

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void setPaintBlendMode(Paint paint) {
            paint.setBlendMode(BlendMode.SRC);
        }
    }

    @RequiresApi(31)
    public static class Api31Impl {
        private Api31Impl() {
        }

        public static Bitmap.Config getHardwareBitmapConfig(Bitmap bitmap) {
            return bitmap.getHardwareBuffer().getFormat() == 22 ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
        }
    }

    private BitmapCompat() {
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i2, int i3, Rect rect, boolean z2) {
        int i4;
        double dFloor;
        Bitmap bitmap2;
        int i5;
        int i6;
        if (i2 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("dstW and dstH must be > 0!");
        }
        if (rect != null && (rect.isEmpty() || rect.left < 0 || rect.right > bitmap.getWidth() || rect.top < 0 || rect.bottom > bitmap.getHeight())) {
            throw new IllegalArgumentException("srcRect must be contained by srcBm!");
        }
        Bitmap bitmapCopyBitmapIfHardware = Api27Impl.copyBitmapIfHardware(bitmap);
        int iWidth = rect != null ? rect.width() : bitmap.getWidth();
        int iHeight = rect != null ? rect.height() : bitmap.getHeight();
        float f2 = i2 / iWidth;
        float f3 = i3 / iHeight;
        int i7 = rect != null ? rect.left : 0;
        int i8 = rect != null ? rect.top : 0;
        if (i7 == 0 && i8 == 0 && i2 == bitmap.getWidth() && i3 == bitmap.getHeight()) {
            return (bitmap.isMutable() && bitmap == bitmapCopyBitmapIfHardware) ? bitmap.copy(bitmap.getConfig(), true) : bitmapCopyBitmapIfHardware;
        }
        Paint paint = new Paint(1);
        paint.setFilterBitmap(true);
        Api29Impl.setPaintBlendMode(paint);
        if (iWidth == i2 && iHeight == i3) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, bitmapCopyBitmapIfHardware.getConfig());
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapCopyBitmapIfHardware, -i7, -i8, paint);
            return bitmapCreateBitmap;
        }
        double dLog = Math.log(2.0d);
        if (f2 > 1.0f) {
            i4 = i7;
            dFloor = Math.ceil(Math.log(f2) / dLog);
        } else {
            i4 = i7;
            dFloor = Math.floor(Math.log(f2) / dLog);
        }
        int i9 = (int) dFloor;
        int iCeil = (int) (f3 > 1.0f ? Math.ceil(Math.log(f3) / dLog) : Math.floor(Math.log(f3) / dLog));
        if (!z2 || Api27Impl.isAlreadyF16AndLinear(bitmap)) {
            bitmap2 = null;
            i5 = i4;
            i6 = 0;
        } else {
            Bitmap bitmapCreateBitmapWithSourceColorspace = Api27Impl.createBitmapWithSourceColorspace(i9 > 0 ? sizeAtStep(iWidth, i2, 1, i9) : iWidth, iCeil > 0 ? sizeAtStep(iHeight, i3, 1, iCeil) : iHeight, bitmap, true);
            new Canvas(bitmapCreateBitmapWithSourceColorspace).drawBitmap(bitmapCopyBitmapIfHardware, -i4, -i8, paint);
            i6 = 1;
            i8 = 0;
            i5 = 0;
            bitmap2 = bitmapCopyBitmapIfHardware;
            bitmapCopyBitmapIfHardware = bitmapCreateBitmapWithSourceColorspace;
        }
        Rect rect2 = new Rect(i5, i8, iWidth, iHeight);
        Rect rect3 = new Rect();
        int i10 = i9;
        int i11 = iCeil;
        while (true) {
            if (i10 == 0 && i11 == 0) {
                break;
            }
            if (i10 < 0) {
                i10++;
            } else if (i10 > 0) {
                i10--;
            }
            if (i11 < 0) {
                i11++;
            } else if (i11 > 0) {
                i11--;
            }
            Bitmap bitmap3 = bitmapCopyBitmapIfHardware;
            Paint paint2 = paint;
            rect3.set(0, 0, sizeAtStep(iWidth, i2, i10, i9), sizeAtStep(iHeight, i3, i11, iCeil));
            boolean z3 = i10 == 0 && i11 == 0;
            boolean z4 = bitmap2 != null && bitmap2.getWidth() == i2 && bitmap2.getHeight() == i3;
            if (bitmap2 == null || bitmap2 == bitmap || ((z2 && !Api27Impl.isAlreadyF16AndLinear(bitmap2)) || (z3 && !(z4 && i6 == 0)))) {
                if (bitmap2 != bitmap && bitmap2 != null) {
                    bitmap2.recycle();
                }
                bitmapCopyBitmapIfHardware = Api27Impl.createBitmapWithSourceColorspace(sizeAtStep(iWidth, i2, i10 > 0 ? i6 : i10, i9), sizeAtStep(iHeight, i3, i11 > 0 ? i6 : i11, iCeil), bitmap, z2 && !z3);
            } else {
                bitmapCopyBitmapIfHardware = bitmap2;
            }
            new Canvas(bitmapCopyBitmapIfHardware).drawBitmap(bitmap3, rect2, rect3, paint2);
            rect2.set(rect3);
            bitmap2 = bitmap3;
            paint = paint2;
        }
        if (bitmap2 != bitmap && bitmap2 != null) {
            bitmap2.recycle();
        }
        return bitmapCopyBitmapIfHardware;
    }

    @ReplaceWith(expression = "bitmap.getAllocationByteCount()")
    @Deprecated
    public static int getAllocationByteCount(Bitmap bitmap) {
        return bitmap.getAllocationByteCount();
    }

    @ReplaceWith(expression = "bitmap.hasMipMap()")
    @Deprecated
    public static boolean hasMipMap(Bitmap bitmap) {
        return bitmap.hasMipMap();
    }

    @ReplaceWith(expression = "bitmap.setHasMipMap(hasMipMap)")
    @Deprecated
    public static void setHasMipMap(Bitmap bitmap, boolean z2) {
        bitmap.setHasMipMap(z2);
    }

    @VisibleForTesting
    public static int sizeAtStep(int i2, int i3, int i4, int i5) {
        return i4 == 0 ? i3 : i4 > 0 ? i2 * (1 << (i5 - i4)) : i3 << ((-i4) - 1);
    }
}
