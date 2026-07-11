package miuix.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.renderscript.RenderScript;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileOutputStream;
import java.util.regex.Pattern;
import miuix.core.util.Utf8TextUtils;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.io.ResettableInputStream;

/* JADX INFO: loaded from: classes3.dex */
public class BitmapFactory extends android.graphics.BitmapFactory {
    private static final String[] APPELLATION_SUFFIX;
    private static final Pattern ASIALANGPATTERN;
    public static final int BITMAP_COLOR_MODE_DARK = 0;
    public static final int BITMAP_COLOR_MODE_LIGHT = 2;
    public static final int BITMAP_COLOR_MODE_MEDIUM = 1;
    public static final int MODE_DARK = 1;
    public static final int MODE_DAYNIGHT = 2;
    public static final int MODE_LIGHT = 0;
    static RenderScript sRsContext;
    private static final Paint sSrcInPaint;
    static Object sLockForRsContext = new Object();
    private static byte[] PNG_HEAD_FORMAT = {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final ThreadLocal<Canvas> sCanvasCache = new ThreadLocal<>();

    public static class CropOption {
        public int borderColor;
        public int borderWidth;
        public int rx;
        public int ry;
        public Rect srcBmpDrawingArea;

        public CropOption() {
        }

        public CropOption(int i2, int i3, int i4, int i5) {
            this.rx = i2;
            this.ry = i3;
            this.borderWidth = i4;
            this.borderColor = i5;
        }

        public CropOption(CropOption cropOption) {
            this.rx = cropOption.rx;
            this.ry = cropOption.ry;
            this.borderWidth = cropOption.borderWidth;
            this.borderColor = cropOption.borderColor;
            this.srcBmpDrawingArea = cropOption.srcBmpDrawingArea;
        }
    }

    static {
        Paint paint = new Paint(1);
        sSrcInPaint = paint;
        paint.setFilterBitmap(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        ASIALANGPATTERN = Pattern.compile("[\u3100-ㄭㆠ-ㆺ一-鿌㐀-䶵豈-龎⼀-⿕⺀-⻳㇀-㇣ᄀ-ᇿꥠ-ꥼힰ-ퟻㄱ-ㆎ가-힣\u3040-ゟ゠-ヿㇰ-ㇿ㆐-㆟ꀀ-ꒌ꒐-꓆]");
        APPELLATION_SUFFIX = new String[]{"老师", "先生", "老板", "仔", "手机", "叔", "阿姨", "宅", "伯", "伯母", "伯父", "哥", "姐", "弟", "妹", "舅", "姑", "父", "主任", "经理", "工作", "同事", "律师", "司机", "师傅", "师父", "爷", "奶", "中介", "董", "总", "太太", "保姆", "某", "秘书", "处长", "局长", "班长", "兄", "助理"};
    }

    public BitmapFactory() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    private static int between(int i2, int i3, int i4) {
        return Math.min(i3, Math.max(i2, i4));
    }

    public static Bitmap composeBitmap(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3) {
        return composeBitmap(bitmap, bitmap2, drawable, drawable2, drawable3, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), bitmap2 != null ? new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()) : new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
    }

    private static int computeSampleSize(ResettableInputStream resettableInputStream, int i2) {
        if (i2 <= 0) {
            return 1;
        }
        BitmapFactory.Options bitmapSize = getBitmapSize(resettableInputStream);
        return (int) Math.sqrt((((double) bitmapSize.outWidth) * ((double) bitmapSize.outHeight)) / ((double) i2));
    }

    private static boolean containsEastAsianCharacter(String str) {
        return ASIALANGPATTERN.matcher(str).find();
    }

    private static Bitmap copyToEmpty(Bitmap bitmap) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        }
        return Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
    }

    @Deprecated
    public static Bitmap createNameBitmap(Context context, String str, int i2) {
        return createNameBitmap(context, str, i2, 0, 0);
    }

    public static Bitmap createPhoto(Context context, Bitmap bitmap) {
        return createPhoto(context, bitmap, context.getResources().getDimensionPixelSize(R.dimen.contact_photo_width));
    }

    public static Bitmap cropBitmap(Bitmap bitmap, CropOption cropOption) {
        if (bitmap != null) {
            return cropBitmap(bitmap, copyToEmpty(bitmap), cropOption);
        }
        return null;
    }

    public static Bitmap decodeBitmap(String str, boolean z2) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap bitmapDecodeBitmap = decodeBitmap(resettableInputStream, -1, z2);
            resettableInputStream.close();
            return bitmapDecodeBitmap;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap fastBlur(Context context, Bitmap bitmap, int i2) {
        Bitmap bitmapCopyToEmpty = copyToEmpty(bitmap);
        fastBlur(context, bitmap, bitmapCopyToEmpty, i2);
        return bitmapCopyToEmpty;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0059, code lost:
    
        r2 = transferF16ToARGB(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap fastblur_v17(android.content.Context r9, android.graphics.Bitmap r10, android.graphics.Bitmap r11, int r12) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.graphics.BitmapFactory.fastblur_v17(android.content.Context, android.graphics.Bitmap, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }

    public static int getBitmapColorMode(Bitmap bitmap, int i2) {
        int height = bitmap.getHeight() / i2;
        int width = bitmap.getWidth() / i2;
        int i3 = (width * height) / 5;
        Bitmap bitmapScaleBitmap = scaleBitmap(bitmap, width, height);
        int i4 = 2;
        int i5 = 0;
        for (int i6 = 0; i6 < width; i6++) {
            int i7 = 0;
            while (true) {
                if (i7 < height) {
                    int pixel = bitmapScaleBitmap.getPixel(i6, i7);
                    if (((int) ((((double) ((16711680 & pixel) >> 16)) * 0.3d) + (((double) ((65280 & pixel) >> 8)) * 0.59d) + (((double) (pixel & 255)) * 0.11d))) < 180) {
                        i5++;
                        if (i5 > i3) {
                            i4 = 1;
                        }
                        if (i5 > i3 * 2) {
                            i4 = 0;
                            break;
                        }
                    }
                    i7++;
                }
            }
        }
        if (bitmapScaleBitmap != bitmap) {
            bitmapScaleBitmap.recycle();
        }
        return i4;
    }

    public static BitmapFactory.Options getBitmapSize(ResettableInputStream resettableInputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        android.graphics.BitmapFactory.decodeStream(resettableInputStream, null, options);
        return options;
    }

    private static Canvas getCachedCanvas() {
        ThreadLocal<Canvas> threadLocal = sCanvasCache;
        Canvas canvas = threadLocal.get();
        if (canvas != null) {
            return canvas;
        }
        Canvas canvas2 = new Canvas();
        threadLocal.set(canvas2);
        return canvas2;
    }

    public static Bitmap getRoundBitmap(Bitmap bitmap, float f2) {
        return getRoundBitmap(bitmap, f2, Bitmap.Config.ARGB_8888);
    }

    private static String getWordFromName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!containsEastAsianCharacter(str)) {
            return Utf8TextUtils.subString(str, 0, 1).toUpperCase();
        }
        String strRemoveSuffix = removeSuffix(str);
        if (TextUtils.isEmpty(strRemoveSuffix)) {
            return null;
        }
        int length = strRemoveSuffix.length();
        return strRemoveSuffix.substring(length - 1, length).trim();
    }

    public static boolean isPngFormat(Context context, Uri uri) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(context, uri);
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zIsPngFormat = isPngFormat(resettableInputStream);
            resettableInputStream.close();
            return zIsPngFormat;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap maskOutBitmap(Bitmap bitmap, Drawable drawable, Bitmap bitmap2, Rect rect, Rect rect2) {
        int i2;
        if (bitmap2 == null && rect2 == null) {
            return null;
        }
        int i3 = 0;
        if (bitmap2 == null) {
            if (rect2.height() <= 0 || rect2.width() <= 0) {
                return null;
            }
            bitmap2 = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
        } else if (rect2 == null) {
            rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        }
        Canvas cachedCanvas = getCachedCanvas();
        cachedCanvas.setBitmap(bitmap2);
        cachedCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (drawable != null) {
            drawable.setBounds(rect2);
            drawable.draw(cachedCanvas);
        }
        if (rect == null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int iWidth = rect2.width();
            float fHeight = rect2.height();
            float f2 = iWidth;
            float f3 = fHeight / f2;
            float f4 = width;
            float f5 = f4 / f2;
            float f6 = height;
            float f7 = f6 / fHeight;
            if (f5 > f7) {
                int i4 = (int) (f6 / f3);
                i3 = (width - i4) / 2;
                width = i4;
                i2 = 0;
            } else if (f5 < f7) {
                int i5 = (int) (f3 * f4);
                i2 = (height - i5) / 2;
                height = i5;
            } else {
                i2 = 0;
            }
            rect = new Rect(i3, i2, width + i3, height + i2);
        }
        cachedCanvas.drawBitmap(bitmap, rect, rect2, sSrcInPaint);
        return bitmap2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004f A[LOOP:1: B:8:0x000c->B:20:0x004f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0052 A[EDGE_INSN: B:33:0x0052->B:21:0x0052 BREAK  A[LOOP:1: B:8:0x000c->B:20:0x004f], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String removeSuffix(java.lang.String r7) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 == 0) goto L8
            r7 = 0
            return r7
        L8:
            r0 = r7
        L9:
            r1 = 0
            r2 = r1
            r3 = r2
        Lc:
            java.lang.String[] r4 = miuix.graphics.BitmapFactory.APPELLATION_SUFFIX
            int r5 = r4.length
            r6 = 1
            if (r2 >= r5) goto L52
            r5 = r4[r2]
            boolean r5 = r0.endsWith(r5)
            if (r5 == 0) goto L2b
            int r3 = r0.length()
            r4 = r4[r2]
            int r4 = r4.length()
            int r3 = r3 - r4
            java.lang.String r0 = r0.substring(r1, r3)
        L29:
            r3 = r6
            goto L48
        L2b:
            int r4 = r0.length()
            int r4 = r4 - r6
            char r4 = r0.charAt(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            boolean r4 = containsEastAsianCharacter(r4)
            if (r4 != 0) goto L48
            int r3 = r0.length()
            int r3 = r3 - r6
            java.lang.String r0 = r0.substring(r1, r3)
            goto L29
        L48:
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 == 0) goto L4f
            goto L52
        L4f:
            int r2 = r2 + 1
            goto Lc
        L52:
            if (r3 == 0) goto L5a
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L9
        L5a:
            if (r0 == 0) goto L60
            java.lang.String r0 = r0.trim()
        L60:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L6f
            int r0 = r7.length()
            int r0 = r0 - r6
            java.lang.String r0 = r7.substring(r0)
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.graphics.BitmapFactory.removeSuffix(java.lang.String):java.lang.String");
    }

    public static boolean saveToFile(Bitmap bitmap, String str) {
        return saveToFile(bitmap, str, false);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap.getWidth() == i2 && bitmap.getHeight() == i3) {
            return bitmap;
        }
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, config);
        scaleBitmap(bitmap, bitmapCreateBitmap);
        return bitmapCreateBitmap;
    }

    private static Bitmap transferF16ToARGB(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == 0 || height == 0) {
            return bitmap;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setFlags(3);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    @Deprecated
    public static Bitmap createNameBitmap(Context context, String str, int i2, int i3, int i4) {
        return createNameBitmap(context, str, i2, i3, i4, 0);
    }

    public static Bitmap getRoundBitmap(Bitmap bitmap, float f2, Bitmap.Config config) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        return bitmapCreateBitmap;
    }

    public static boolean saveToFile(Bitmap bitmap, String str, boolean z2) throws Throwable {
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            bitmap.compress(z2 ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap createNameBitmap(Context context, String str, int i2, int i3) {
        return createNameBitmap(context, str, i2, 0, 0, i3);
    }

    public static Bitmap createPhoto(Context context, Bitmap bitmap, int i2) {
        Resources resources = context.getResources();
        return composeBitmap(bitmap, null, resources.getDrawable(R.drawable.ic_contact_photo_mask), resources.getDrawable(R.drawable.ic_contact_photo_fg), resources.getDrawable(R.drawable.ic_contact_photo_bg), i2);
    }

    public static Bitmap cropBitmap(Bitmap bitmap, Bitmap bitmap2, CropOption cropOption) {
        if (bitmap == null || bitmap2 == null) {
            return null;
        }
        CropOption cropOption2 = cropOption == null ? new CropOption() : cropOption;
        Rect rect = cropOption2.srcBmpDrawingArea;
        if (rect == null) {
            rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        int iBetween = between(0, bitmap.getWidth() - 1, rect.left);
        int iBetween2 = between(iBetween, bitmap.getWidth(), rect.right);
        int iBetween3 = between(0, bitmap.getHeight() - 1, rect.top);
        int iBetween4 = between(iBetween3, bitmap.getHeight(), rect.bottom);
        int i2 = iBetween2 - iBetween;
        int i3 = iBetween4 - iBetween3;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        cropOption2.borderWidth = between(0, Math.min(width, height) / 2, cropOption2.borderWidth);
        cropOption2.rx = between(0, width / 2, cropOption2.rx);
        cropOption2.ry = between(0, height / 2, cropOption2.ry);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setDither(true);
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawARGB(0, 0, 0, 0);
        int i4 = cropOption2.rx;
        int i5 = cropOption2.borderWidth;
        if (i4 - i5 > 0 && cropOption2.ry - i5 > 0) {
            int i6 = cropOption2.borderWidth;
            RectF rectF = new RectF(i6, i6, width - i6, height - i6);
            int i7 = cropOption2.rx;
            int i8 = cropOption2.borderWidth;
            canvas.drawRoundRect(rectF, i7 - i8, cropOption2.ry - i8, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
        int i9 = cropOption2.borderWidth;
        float f2 = i2;
        float f3 = width - (i9 * 2);
        float f4 = i3;
        float f5 = height - (i9 * 2);
        float fMin = Math.min((f2 * 1.0f) / f3, (1.0f * f4) / f5);
        int i10 = (int) ((f2 - (f3 * fMin)) / 2.0f);
        int i11 = (int) ((f4 - (f5 * fMin)) / 2.0f);
        Rect rect2 = new Rect(iBetween + i10, iBetween3 + i11, iBetween2 - i10, iBetween4 - i11);
        int i12 = cropOption2.borderWidth;
        canvas.drawBitmap(bitmap, rect2, new Rect(i12, i12, width - i12, height - i12), paint);
        if (cropOption2.borderWidth > 0) {
            int i13 = cropOption2.borderColor;
            if ((i13 >>> 24) != 0) {
                paint.setColor(i13);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
                canvas.drawRoundRect(new RectF(0.0f, 0.0f, width, height), cropOption2.rx, cropOption2.ry, paint);
            }
        }
        return bitmap2;
    }

    public static Bitmap fastBlur(Context context, Bitmap bitmap, Bitmap bitmap2, int i2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null || bitmap.getWidth() != bitmap2.getWidth() || bitmap.getHeight() != bitmap2.getHeight()) {
            bitmap2 = copyToEmpty(bitmap);
        }
        fastblur_v17(context, bitmap, bitmap2, i2);
        return bitmap2;
    }

    public static Bitmap createNameBitmap(Context context, String str, int i2, int i3, int i4, int i5) {
        Drawable drawable;
        int color;
        int iResolveColor;
        if (str == null) {
            return null;
        }
        String wordFromName = getWordFromName(str.trim());
        if (TextUtils.isEmpty(wordFromName)) {
            return null;
        }
        if (i3 != 0) {
            drawable = context.getResources().getDrawable(i3);
        } else if (i5 == 0) {
            drawable = context.getResources().getDrawable(R.drawable.word_photo_bg_light);
        } else if (1 == i5) {
            drawable = context.getResources().getDrawable(R.drawable.word_photo_bg_dark);
        } else if (2 == i5) {
            drawable = AttributeResolver.resolveDrawable(context, R.attr.wordPhotoBackground);
            if (drawable == null) {
                if (ViewUtils.isNightMode(context)) {
                    drawable = context.getResources().getDrawable(R.drawable.word_photo_bg_dark);
                } else {
                    drawable = context.getResources().getDrawable(R.drawable.word_photo_bg_light);
                }
            }
        } else {
            throw new IllegalArgumentException("unknown mode when get drawable: " + i5);
        }
        drawable.setBounds(new Rect(0, 0, i2, i2));
        if (i4 != 0) {
            color = context.getResources().getColor(i4);
        } else if (i5 == 0) {
            color = context.getResources().getColor(R.color.word_photo_color);
        } else if (1 == i5) {
            color = context.getResources().getColor(R.color.word_photo_color_dark);
        } else if (2 == i5) {
            try {
                iResolveColor = AttributeResolver.resolveColor(context, R.attr.wordPhotoTextColor);
            } catch (Exception unused) {
                iResolveColor = -1;
            }
            if (iResolveColor != -1) {
                color = iResolveColor;
            } else if (ViewUtils.isNightMode(context)) {
                color = context.getResources().getColor(R.color.word_photo_color_dark);
            } else {
                color = context.getResources().getColor(R.color.word_photo_color);
            }
        } else {
            throw new IllegalArgumentException("unknown mode when get photo color: " + i5);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.draw(canvas);
        Paint paint = new Paint(1);
        paint.setFilterBitmap(true);
        paint.setColor(color);
        paint.setTextSize(i2 * 0.6f);
        paint.getTextBounds(wordFromName, 0, wordFromName.length(), new Rect());
        canvas.drawText(wordFromName, (int) (((double) (i2 - (r6.right + r6.left))) * 0.5d), (int) (((double) (i2 - (r6.top + r6.bottom))) * 0.5d), paint);
        return bitmapCreateBitmap;
    }

    public static BitmapFactory.Options getBitmapSize(String str) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            BitmapFactory.Options bitmapSize = getBitmapSize(resettableInputStream);
            resettableInputStream.close();
            return bitmapSize;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap composeBitmap(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3, int i2) {
        return composeBitmap(bitmap, bitmap2, drawable, drawable2, drawable3, null, new Rect(0, 0, i2, i2));
    }

    public static Bitmap decodeBitmap(String str, int i2, int i3, boolean z2) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap bitmapDecodeBitmap = decodeBitmap(resettableInputStream, i2, i3, z2);
            resettableInputStream.close();
            return bitmapDecodeBitmap;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static boolean isPngFormat(String str) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zIsPngFormat = isPngFormat(resettableInputStream);
            resettableInputStream.close();
            return zIsPngFormat;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap composeBitmap(Bitmap bitmap, Bitmap bitmap2, Drawable drawable, Drawable drawable2, Drawable drawable3, Rect rect, Rect rect2) {
        if (bitmap2 == null && rect2 == null) {
            return null;
        }
        if (bitmap2 == null) {
            if (rect2.height() <= 0 || rect2.width() <= 0) {
                return null;
            }
            bitmap2 = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
        } else if (rect2 == null) {
            rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        }
        Bitmap bitmapMaskOutBitmap = maskOutBitmap(bitmap, drawable, null, rect, rect2);
        if (bitmapMaskOutBitmap != null) {
            Canvas cachedCanvas = getCachedCanvas();
            cachedCanvas.setBitmap(bitmap2);
            if (drawable3 != null) {
                drawable3.setBounds(rect2);
                drawable3.draw(cachedCanvas);
            }
            cachedCanvas.drawBitmap(bitmapMaskOutBitmap, rect2, rect2, (Paint) null);
            bitmapMaskOutBitmap.recycle();
            if (drawable2 != null) {
                drawable2.setBounds(rect2);
                drawable2.draw(cachedCanvas);
            }
        } else {
            Log.e("BitmapFactory", "Get mask bitmap failed");
        }
        return bitmap2;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap2 == null) {
            return null;
        }
        if (bitmap.getWidth() == bitmap2.getWidth() && bitmap.getHeight() == bitmap2.getHeight()) {
            return bitmap;
        }
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), paint);
        return bitmap2;
    }

    public static BitmapFactory.Options getBitmapSize(Context context, Uri uri) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(context, uri);
        } catch (Throwable th) {
            th = th;
        }
        try {
            BitmapFactory.Options bitmapSize = getBitmapSize(resettableInputStream);
            resettableInputStream.close();
            return bitmapSize;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap decodeBitmap(Context context, Uri uri, boolean z2) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(context, uri);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap bitmapDecodeBitmap = decodeBitmap(resettableInputStream, -1, z2);
            resettableInputStream.close();
            return bitmapDecodeBitmap;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static boolean isPngFormat(ResettableInputStream resettableInputStream) {
        int length = PNG_HEAD_FORMAT.length;
        byte[] bArr = new byte[length];
        if (resettableInputStream.read(bArr) >= length) {
            return isPngFormat(bArr);
        }
        return false;
    }

    private static boolean isPngFormat(byte[] bArr) {
        if (bArr == null || bArr.length < PNG_HEAD_FORMAT.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = PNG_HEAD_FORMAT;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    public static Bitmap decodeBitmap(Context context, Uri uri, int i2, int i3, boolean z2) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(context, uri);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap bitmapDecodeBitmap = decodeBitmap(resettableInputStream, i2, i3, z2);
            resettableInputStream.close();
            return bitmapDecodeBitmap;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap decodeBitmap(String str, int i2, boolean z2) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(str);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap bitmapDecodeBitmap = decodeBitmap(resettableInputStream, i2, z2);
            resettableInputStream.close();
            return bitmapDecodeBitmap;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    public static Bitmap decodeBitmap(Context context, Uri uri, int i2, boolean z2) throws Throwable {
        ResettableInputStream resettableInputStream;
        ResettableInputStream resettableInputStream2 = null;
        try {
            resettableInputStream = new ResettableInputStream(context, uri);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap bitmapDecodeBitmap = decodeBitmap(resettableInputStream, i2, z2);
            resettableInputStream.close();
            return bitmapDecodeBitmap;
        } catch (Throwable th2) {
            th = th2;
            resettableInputStream2 = resettableInputStream;
            if (resettableInputStream2 != null) {
                resettableInputStream2.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap decodeBitmap(miuix.io.ResettableInputStream r4, int r5, boolean r6) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inSampleSize = r1
            r1 = 0
            r0.inScaled = r1
            int r5 = computeSampleSize(r4, r5)
            r0.inSampleSize = r5
        L11:
            int r5 = r1 + 1
            r2 = 3
            r3 = 0
            if (r1 >= r2) goto L2b
            r4.reset()     // Catch: java.lang.OutOfMemoryError -> L1f
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r4, r3, r0)     // Catch: java.lang.OutOfMemoryError -> L1f
            goto L2b
        L1f:
            r1 = move-exception
            if (r6 == 0) goto L2a
            int r1 = r0.inSampleSize
            int r1 = r1 * 2
            r0.inSampleSize = r1
            r1 = r5
            goto L11
        L2a:
            throw r1
        L2b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.graphics.BitmapFactory.decodeBitmap(miuix.io.ResettableInputStream, int, boolean):android.graphics.Bitmap");
    }

    public static Bitmap decodeBitmap(ResettableInputStream resettableInputStream, int i2, int i3, boolean z2) {
        int i4 = i2 * i3;
        if (i2 <= 0 || i3 <= 0) {
            i4 = -1;
        }
        Bitmap bitmapDecodeBitmap = decodeBitmap(resettableInputStream, i4, z2);
        if (bitmapDecodeBitmap == null) {
            return null;
        }
        if (i4 <= 0) {
            return bitmapDecodeBitmap;
        }
        Bitmap bitmapScaleBitmap = scaleBitmap(bitmapDecodeBitmap, i2, i3);
        if (bitmapDecodeBitmap != bitmapScaleBitmap) {
            bitmapDecodeBitmap.recycle();
        }
        return bitmapScaleBitmap;
    }
}
