package miuix.colorful.texteffect;

import android.graphics.Color;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class TextEffectConfig {
    public final int[] alphaIntConfig;
    public final int blurRadius;

    @Nullable
    public final int[] colorsConfig;

    public static class Builder {
        private int[] mColorsConfig;
        private int mBlurRadius = 40;
        private int[] mAlphaIntConfig = {0, 255};

        public Builder(int i2) {
            if (TextEffectConfig.isLightColor(i2)) {
                this.mColorsConfig = new int[]{-11045895, -6900225, -18037, i2};
            } else {
                this.mColorsConfig = new int[]{-18037, -6900225, -11045895, i2};
            }
        }

        public TextEffectConfig create() {
            return new TextEffectConfig(this.mBlurRadius, this.mAlphaIntConfig, this.mColorsConfig);
        }

        public TextEffectConfig createOnlyAlpha() {
            return new TextEffectConfig(0, this.mAlphaIntConfig, null);
        }

        public Builder setAlphaConfig(int[] iArr) {
            this.mAlphaIntConfig = iArr;
            return this;
        }

        public Builder setBlurRadius(int i2) {
            this.mBlurRadius = i2;
            return this;
        }

        public Builder setColorConfig(int[] iArr) {
            this.mColorsConfig = iArr;
            return this;
        }

        public Builder setDefaultTextColor(int i2) {
            this.mColorsConfig[r0.length - 1] = i2;
            return this;
        }
    }

    public TextEffectConfig(int i2, int[] iArr, @Nullable int[] iArr2) {
        this.blurRadius = i2;
        this.alphaIntConfig = iArr;
        this.colorsConfig = iArr2;
    }

    public static boolean isLightColor(int i2) {
        double dLuminance = ((double) Color.luminance(i2)) + 0.05d;
        return dLuminance * dLuminance > 0.0525d;
    }
}
