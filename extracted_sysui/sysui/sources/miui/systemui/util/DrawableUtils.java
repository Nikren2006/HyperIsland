package miui.systemui.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.Icon;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class DrawableUtils {
    private DrawableUtils() {
    }

    public static LayerDrawable combine(Drawable drawable, Drawable drawable2, int i2) {
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
        layerDrawable.setLayerGravity(1, i2);
        return layerDrawable;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Drawable findDrawableById(Drawable drawable, int i2) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i3 = 0; i3 < layerDrawable.getNumberOfLayers(); i3++) {
                if (layerDrawable.getId(i3) == i2) {
                    return layerDrawable.getDrawable(i3);
                }
                Drawable drawableFindDrawableById = findDrawableById(layerDrawable.getDrawable(i3), i2);
                if (drawableFindDrawableById != null) {
                    return drawableFindDrawableById;
                }
            }
        } else if (isWrapperDrawable(drawable)) {
            return findDrawableById(getWrappedDrawable(drawable, null), i2);
        }
        return null;
    }

    private static Drawable getWrappedDrawable(Drawable drawable, Drawable drawable2) {
        return drawable instanceof DrawableWrapper ? ((DrawableWrapper) drawable).getDrawable() : drawable instanceof ScaleDrawable ? ((ScaleDrawable) drawable).getDrawable() : drawable instanceof ClipDrawable ? ((ClipDrawable) drawable).getDrawable() : drawable instanceof InsetDrawable ? ((InsetDrawable) drawable).getDrawable() : drawable instanceof RotateDrawable ? ((RotateDrawable) drawable).getDrawable() : drawable2;
    }

    public static Bitmap invertDrawable(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), config);
        new Canvas(bitmapCreateBitmap2).drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap2;
    }

    public static boolean isSameIcon(Icon icon, Icon icon2) {
        if (icon != null && icon2 != null) {
            try {
                return ((Boolean) Icon.class.getMethod("sameAs", Icon.class).invoke(icon, icon2)).booleanValue();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isValidBitmapDrawable(Drawable drawable) {
        String str;
        boolean z2 = false;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null && !bitmapDrawable.getBitmap().isRecycled()) {
                z2 = true;
            }
            if (!z2) {
                if (bitmapDrawable.getBitmap() == null) {
                    str = " Bitmap==null";
                } else {
                    str = " isRecycled:" + bitmapDrawable.getBitmap().isRecycled();
                }
                Log.d("DrawableUtils", str);
            }
        }
        return z2;
    }

    private static boolean isWrapperDrawable(Drawable drawable) {
        return (drawable instanceof DrawableWrapper) || (drawable instanceof ScaleDrawable) || (drawable instanceof ClipDrawable) || (drawable instanceof InsetDrawable) || (drawable instanceof RotateDrawable);
    }
}
