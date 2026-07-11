package miuix.graphics.shadow;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import androidx.annotation.NonNull;
import miuix.core.util.MaterialConfig;

/* JADX INFO: loaded from: classes3.dex */
public class DropShadowConfig {
    float blurRadiusDp;
    BlurMaskFilter.Blur blurStyle;
    boolean clipShadowEnable;
    float dispersion;
    float offsetXDp;
    float offsetYDp;
    int shadowColor;
    int shadowDarkColor;

    public DropShadowConfig(float f2) {
        this(f2, BlurMaskFilter.Blur.NORMAL);
    }

    public static class Builder {
        private final DropShadowConfig dropShadowConfig;

        public Builder(float f2) {
            this.dropShadowConfig = new DropShadowConfig(f2);
        }

        public DropShadowConfig create() {
            return this.dropShadowConfig;
        }

        public Builder setBlurRadius(float f2) {
            this.dropShadowConfig.blurRadiusDp = f2;
            return this;
        }

        public Builder setClipShadowEnable(boolean z2) {
            this.dropShadowConfig.clipShadowEnable = z2;
            return this;
        }

        public Builder setColor(int i2, int i3) {
            DropShadowConfig dropShadowConfig = this.dropShadowConfig;
            dropShadowConfig.shadowColor = i2;
            dropShadowConfig.shadowDarkColor = i3;
            return this;
        }

        public Builder setDispersion(float f2) {
            this.dropShadowConfig.dispersion = f2;
            return this;
        }

        public Builder setOffsetXDp(int i2) {
            this.dropShadowConfig.offsetXDp = i2;
            return this;
        }

        public Builder setOffsetYDp(int i2) {
            this.dropShadowConfig.offsetYDp = i2;
            return this;
        }

        public Builder setStyle(BlurMaskFilter.Blur blur) {
            this.dropShadowConfig.blurStyle = blur;
            return this;
        }

        public Builder(@NonNull MaterialConfig.ShadowConfig shadowConfig) {
            int i2 = shadowConfig.shadowColor;
            this.dropShadowConfig = new DropShadowConfig(i2, i2, shadowConfig.shadowOffsetX, shadowConfig.shadowOffsetY, shadowConfig.shadowRadius, shadowConfig.shadowDispersion, BlurMaskFilter.Blur.NORMAL);
        }
    }

    public DropShadowConfig(float f2, BlurMaskFilter.Blur blur) {
        this(Color.parseColor("#0D000000"), Color.parseColor("#0DFFFFFF"), 0.0f, 0.0f, f2, 1.0f, blur);
    }

    public DropShadowConfig(int i2, int i3, float f2, float f3, float f4, float f5, BlurMaskFilter.Blur blur) {
        this.clipShadowEnable = true;
        this.shadowColor = i2;
        this.shadowDarkColor = i3;
        this.offsetXDp = f2;
        this.offsetYDp = f3;
        this.blurRadiusDp = f4;
        this.dispersion = f5;
        this.blurStyle = blur;
    }
}
