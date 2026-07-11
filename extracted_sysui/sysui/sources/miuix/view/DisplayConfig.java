package miuix.view;

import android.content.res.Configuration;
import android.util.DisplayMetrics;

/* JADX INFO: loaded from: classes5.dex */
public class DisplayConfig {
    public int defaultBitmapDensity;
    public float density;
    public int densityDpi;
    public float fontScale;
    public float scaledDensity;
    public int windowHeightDp;
    public int windowWidthDp;

    public DisplayConfig(DisplayMetrics displayMetrics) {
        int i2 = displayMetrics.densityDpi;
        this.defaultBitmapDensity = i2;
        this.densityDpi = i2;
        float f2 = displayMetrics.density;
        this.density = f2;
        float f3 = displayMetrics.scaledDensity;
        this.scaledDensity = f3;
        this.fontScale = f3 / f2;
        this.windowWidthDp = (int) ((displayMetrics.widthPixels / f2) + 0.5f);
        this.windowHeightDp = (int) ((displayMetrics.heightPixels / f2) + 0.5f);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DisplayConfig)) {
            return false;
        }
        DisplayConfig displayConfig = (DisplayConfig) obj;
        return Float.compare(this.density, displayConfig.density) == 0 && Float.compare(this.scaledDensity, displayConfig.scaledDensity) == 0 && Float.compare(this.fontScale, displayConfig.fontScale) == 0 && this.densityDpi == displayConfig.densityDpi && this.defaultBitmapDensity == displayConfig.defaultBitmapDensity;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "{ densityDpi:" + this.densityDpi + ", density:" + this.density + ", windowWidthDp:" + this.windowWidthDp + ", windowHeightDp: " + this.windowHeightDp + ", scaledDensity:" + this.scaledDensity + ", fontScale: " + this.fontScale + ", defaultBitmapDensity:" + this.defaultBitmapDensity + "}";
    }

    public DisplayConfig(Configuration configuration) {
        this.windowWidthDp = configuration.screenWidthDp;
        this.windowHeightDp = configuration.screenHeightDp;
        int i2 = configuration.densityDpi;
        this.defaultBitmapDensity = i2;
        this.densityDpi = i2;
        float f2 = i2 * 0.00625f;
        this.density = f2;
        float f3 = configuration.fontScale;
        this.fontScale = f3;
        this.scaledDensity = f2 * (f3 == 0.0f ? 1.0f : f3);
    }
}
