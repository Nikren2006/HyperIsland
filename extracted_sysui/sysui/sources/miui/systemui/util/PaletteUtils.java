package miui.systemui.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.palette.graphics.Palette;
import androidx.palette.graphics.Target;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class PaletteUtils {
    private static final float BLACK_MAX_LIGHTNESS = 0.08f;
    private static final float BLEND_ALPHA = 0.14f;
    private static final float POPULATION_FRACTION_FOR_WHITE_OR_BLACK = 2.5f;
    private static final int RESIZE_BITMAP_AREA = 22500;
    private static final float WHITE_MIN_LIGHTNESS = 0.9f;

    public interface OnThreeColorsExtractedListener {
        void onColorsExtracted(Integer num, Integer num2, Integer num3);
    }

    public static int blendWithColor(int i2, boolean z2) {
        int iAlpha = Color.alpha(i2);
        int iRed = Color.red(i2);
        int iGreen = Color.green(i2);
        int iBlue = Color.blue(i2);
        int i3 = !z2 ? 0 : 255;
        int i4 = i3;
        return Color.argb((int) ((iAlpha * 0.86f) + (255 * 0.14f)), (int) ((iRed * 0.86f) + (i3 * 0.14f)), (int) ((iGreen * 0.86f) + (i4 * 0.14f)), (int) ((iBlue * 0.86f) + (i4 * 0.14f)));
    }

    public static void extractTopThreeTargetColors(Bitmap bitmap, final OnThreeColorsExtractedListener onThreeColorsExtractedListener) {
        if (bitmap == null || onThreeColorsExtractedListener == null) {
            return;
        }
        Palette.Builder builderFrom = Palette.from(bitmap);
        builderFrom.addTarget(Target.VIBRANT);
        builderFrom.addTarget(Target.LIGHT_VIBRANT);
        builderFrom.addTarget(Target.DARK_VIBRANT);
        builderFrom.addTarget(Target.MUTED);
        builderFrom.addTarget(Target.LIGHT_MUTED);
        builderFrom.addTarget(Target.DARK_MUTED);
        builderFrom.generate(new Palette.PaletteAsyncListener() { // from class: miui.systemui.util.s
            @Override // androidx.palette.graphics.Palette.PaletteAsyncListener
            public final void onGenerated(Palette palette) {
                PaletteUtils.lambda$extractTopThreeTargetColors$0(onThreeColorsExtractedListener, palette);
            }
        });
    }

    public static Palette.Swatch findBackgroundSwatch(Bitmap bitmap) {
        return findBackgroundSwatch(generateArtworkPaletteBuilder(bitmap).generate());
    }

    public static Palette.Builder generateArtworkPaletteBuilder(Bitmap bitmap) {
        return Palette.from(bitmap).setRegion(0, 0, bitmap.getWidth(), bitmap.getHeight()).clearFilters().resizeBitmapArea(RESIZE_BITMAP_AREA);
    }

    private static boolean isBlack(float[] fArr) {
        return fArr[2] <= BLACK_MAX_LIGHTNESS;
    }

    private static boolean isWhite(float[] fArr) {
        return fArr[2] >= 0.9f;
    }

    public static boolean isWhiteOrBlack(float[] fArr) {
        return isBlack(fArr) || isWhite(fArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$extractTopThreeTargetColors$0(OnThreeColorsExtractedListener onThreeColorsExtractedListener, Palette palette) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Target> it = palette.getTargets().iterator();
        while (it.hasNext()) {
            Palette.Swatch swatchForTarget = palette.getSwatchForTarget(it.next());
            if (swatchForTarget != null && !arrayList2.contains(swatchForTarget)) {
                arrayList.add(Integer.valueOf(swatchForTarget.getRgb()));
                arrayList2.add(swatchForTarget);
            }
            if (arrayList.size() >= 3) {
                break;
            }
        }
        onThreeColorsExtractedListener.onColorsExtracted(arrayList.size() > 0 ? (Integer) arrayList.get(0) : null, arrayList.size() > 1 ? (Integer) arrayList.get(1) : null, arrayList.size() > 2 ? (Integer) arrayList.get(2) : null);
    }

    public static Palette.Swatch findBackgroundSwatch(Palette palette) {
        Palette.Swatch dominantSwatch = palette.getDominantSwatch();
        if (dominantSwatch == null) {
            return new Palette.Swatch(-1, 100);
        }
        if (!isWhiteOrBlack(dominantSwatch.getHsl())) {
            return dominantSwatch;
        }
        float population = -1.0f;
        Palette.Swatch swatch = null;
        for (Palette.Swatch swatch2 : palette.getSwatches()) {
            if (swatch2 != dominantSwatch && swatch2.getPopulation() > population && !isWhiteOrBlack(swatch2.getHsl())) {
                population = swatch2.getPopulation();
                swatch = swatch2;
            }
        }
        return (swatch != null && ((float) dominantSwatch.getPopulation()) / population <= POPULATION_FRACTION_FOR_WHITE_OR_BLACK) ? swatch : dominantSwatch;
    }
}
