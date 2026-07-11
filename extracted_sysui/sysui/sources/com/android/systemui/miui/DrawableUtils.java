package com.android.systemui.miui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;

/* JADX INFO: loaded from: classes2.dex */
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

    private static boolean isWrapperDrawable(Drawable drawable) {
        return (drawable instanceof DrawableWrapper) || (drawable instanceof ScaleDrawable) || (drawable instanceof ClipDrawable) || (drawable instanceof InsetDrawable) || (drawable instanceof RotateDrawable);
    }
}
