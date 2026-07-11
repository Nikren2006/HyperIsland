package com.miui.maml.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.VectorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.xiaomi.onetrack.util.aa;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class BitmapUtils {
    private static final String LOG_TAG = "MAML-BitmapUtils";
    private static final int sAlphaThreshold = 50;
    private static final Canvas sCanvas;
    private static final int sColorByteSize = 4;
    private static volatile Paint sCutPaint;

    static {
        Canvas canvas = new Canvas();
        sCanvas = canvas;
        canvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
    }

    public static Bitmap composeIcon(Bitmap bitmap, Bitmap bitmap2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int byteCount = bitmap.getByteCount() / 4;
        int rowBytes = bitmap.getRowBytes() / 4;
        int[] iArr = new int[byteCount];
        bitmap.getPixels(iArr, 0, rowBytes, 0, 0, width, height);
        bitmap.recycle();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(iArr, 0, rowBytes, 0, 0, width, height, true, (Paint) null);
        if (bitmap2 != null) {
            if (sCutPaint == null) {
                synchronized (BitmapUtils.class) {
                    try {
                        if (sCutPaint == null) {
                            Paint paint = new Paint();
                            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                            sCutPaint = paint;
                        }
                    } finally {
                    }
                }
            }
            canvas.drawBitmap(bitmap2, 0.0f, 0.0f, sCutPaint);
            bitmapCreateBitmap.getPixels(iArr, 0, rowBytes, 0, 0, width, height);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        }
        canvas.drawBitmap(iArr, 0, rowBytes, 0, 0, width, height, true, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Bitmap drawableToBitmapWithoutBlank(Drawable drawable, float f2, boolean z2) {
        Bitmap bitmapCreateBitmap;
        Canvas canvas = sCanvas;
        synchronized (canvas) {
            try {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
                if (drawable instanceof PaintDrawable) {
                    PaintDrawable paintDrawable = (PaintDrawable) drawable;
                    paintDrawable.setIntrinsicWidth(intrinsicWidth);
                    paintDrawable.setIntrinsicHeight(intrinsicHeight);
                } else if (drawable instanceof BitmapDrawable) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                    int density = bitmapDrawable.getBitmap().getDensity();
                    if (density != displayMetrics.densityDpi) {
                        bitmapDrawable.setTargetDensity(density);
                        f2 = getScaleRatio(drawable);
                        Log.d(LOG_TAG, "BitmapDensity = " + density + "  setTargetDensity = " + density);
                    }
                }
                if (z2) {
                    f2 = 1.0f;
                }
                drawable.setBounds(0, 0, intrinsicWidth, intrinsicWidth);
                bitmapCreateBitmap = Bitmap.createBitmap(displayMetrics, intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
                canvas.setBitmap(bitmapCreateBitmap);
                canvas.save();
                float f3 = intrinsicWidth;
                float f4 = f3 * f2;
                canvas.translate((f3 - f4) / 2.0f, (intrinsicHeight - f4) / 2.0f);
                canvas.scale(f2, f2);
                drawable.draw(canvas);
                canvas.restore();
                canvas.setBitmap(null);
            } catch (Throwable th) {
                throw th;
            }
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap getBitmapFromDrawable(Context context, @DrawableRes int i2, int i3, int i4) {
        Drawable drawable = ContextCompat.getDrawable(context, i2);
        if (!(drawable instanceof BitmapDrawable) && !(drawable instanceof VectorDrawable) && !(drawable instanceof VectorDrawableCompat)) {
            throw new IllegalArgumentException("unsupported drawable type");
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private static byte[] getBuffer(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int byteCount = bitmap.getByteCount() / 4;
        byte[] bArr = null;
        try {
            int[] iArr = new int[byteCount];
            bitmap.getPixels(iArr, 0, bitmap.getRowBytes() / 4, 0, 0, width, height);
            bArr = new byte[bitmap.getByteCount()];
            for (int i2 = 0; i2 < byteCount; i2++) {
                int i3 = i2 * 4;
                int i4 = iArr[i2];
                bArr[i3 + 3] = (byte) ((i4 >> 24) & 255);
                bArr[i3] = (byte) ((i4 >> 16) & 255);
                bArr[i3 + 1] = (byte) ((i4 >> 8) & 255);
                bArr[i3 + 2] = (byte) (i4 & 255);
            }
        } catch (OutOfMemoryError e2) {
            Log.e(LOG_TAG, "failed to get buffer, baseWidth = " + width + ", baseHeight = " + height, e2);
        }
        return bArr;
    }

    private static float getContentRatio(Drawable drawable) {
        if (!(drawable instanceof BitmapDrawable)) {
            return -1.0f;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            return -1.0f;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int rowBytes = bitmap.getRowBytes();
        byte[] buffer = getBuffer(bitmap);
        if (buffer == null || buffer.length < 1) {
            Log.w(LOG_TAG, "getContentRatio fail pixels invalid.");
            return -1.0f;
        }
        int edgePosition = getEdgePosition(width, height, rowBytes, buffer, true, false);
        int edgePosition2 = getEdgePosition(width, height, rowBytes, buffer, true, true);
        int edgePosition3 = getEdgePosition(width, height, rowBytes, buffer, false, false);
        int edgePosition4 = getEdgePosition(width, height, rowBytes, buffer, false, true);
        if (edgePosition >= 0 && edgePosition2 >= 0 && edgePosition3 >= 0 && edgePosition4 >= 0) {
            return Math.min(drawable.getIntrinsicWidth() / Math.min(drawable.getIntrinsicWidth(), (edgePosition4 - edgePosition3) + 1), drawable.getIntrinsicHeight() / Math.min(drawable.getIntrinsicHeight(), (edgePosition2 - edgePosition) + 1));
        }
        Log.w(LOG_TAG, "getContentRatio fail, edge invalid:" + edgePosition + aa.f3429b + edgePosition2 + aa.f3429b + edgePosition3 + aa.f3429b + edgePosition4);
        return -1.0f;
    }

    private static int getEdgePosition(int i2, int i3, int i4, byte[] bArr, boolean z2, boolean z3) {
        int i5 = !z3 ? -1 : i2;
        int i6 = !z3 ? -1 : i3;
        int i7 = z3 ? -1 : 1;
        int i8 = z3 ? -1 : 1;
        if (bArr != null) {
            int i9 = 0;
            while (i9 == 0) {
                if (z2) {
                    i6 += i8;
                    if (i6 < 0 || i6 >= i3) {
                        break;
                    }
                    i5 = 0;
                    while (i5 < i2) {
                        if ((bArr[(i6 * i4) + (i5 << 2) + 3] & TransitionInfo.INIT) > 50) {
                            i9++;
                        }
                        i5++;
                    }
                } else {
                    i5 += i7;
                    if (i5 < 0 || i5 >= i2) {
                        break;
                    }
                    i6 = 0;
                    while (i6 < i3) {
                        if ((bArr[(i6 * i4) + (i5 << 2) + 3] & TransitionInfo.INIT) > 50) {
                            i9++;
                        }
                        i6++;
                    }
                }
            }
        }
        return z2 ? i6 : i5;
    }

    public static float getScaleRatio(Drawable drawable) {
        if (drawable instanceof PaintDrawable) {
            return 1.0f;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth > 0 && intrinsicHeight > 0) {
            float contentRatio = getContentRatio(drawable);
            Log.d(LOG_TAG, "Content Ratio = " + contentRatio);
            if (contentRatio > 0.0f) {
                return contentRatio;
            }
        }
        return 1.0f;
    }
}
