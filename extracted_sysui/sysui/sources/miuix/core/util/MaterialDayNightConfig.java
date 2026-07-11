package miuix.core.util;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import miuix.core.util.MaterialConfig;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialDayNightConfig {

    @Nullable
    public MaterialConfig darkConfig;

    @Nullable
    public MaterialConfig defaultConfig;

    public MaterialDayNightConfig(@Nullable Parcel parcel) {
        if (parcel == null) {
            this.defaultConfig = null;
            this.darkConfig = null;
            return;
        }
        int i2 = parcel.readInt();
        if (i2 < 1) {
            this.defaultConfig = null;
            this.darkConfig = null;
            return;
        }
        this.defaultConfig = new MaterialConfig(parcel);
        if (i2 > 1) {
            this.darkConfig = new MaterialConfig(parcel);
        } else {
            this.darkConfig = null;
        }
    }

    @Nullable
    public static MaterialDayNightConfig create(@Nullable Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.setDataPosition(0);
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            MaterialDayNightConfig materialDayNightConfig = new MaterialDayNightConfig(parcelObtain);
            parcelObtain.recycle();
            return materialDayNightConfig;
        } catch (Exception unused) {
            parcelObtain.recycle();
            return null;
        }
    }

    @Nullable
    public MaterialConfig get(boolean z2) {
        MaterialConfig materialConfig = this.darkConfig;
        return (materialConfig == null || z2) ? this.defaultConfig : materialConfig;
    }

    @Nullable
    public MaterialConfig.BloomStrokeConfig getBloomStrokeConfig(boolean z2) {
        MaterialConfig materialConfig = get(z2);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getBloomStrokeConfig();
    }

    @Nullable
    public MaterialConfig.BlurConfig getBlurConfig(boolean z2) {
        MaterialConfig materialConfig = get(z2);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getBlurConfig();
    }

    @Nullable
    public MaterialConfig.ColorBlendConfig getColorBlendConfig(boolean z2) {
        MaterialConfig materialConfig = get(z2);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getColorBlendConfig();
    }

    @Nullable
    public MaterialConfig.BloomStrokeConfig getDefaultBloomStrokeConfig() {
        MaterialConfig materialConfig = get(true);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getBloomStrokeConfig();
    }

    @Nullable
    public MaterialConfig.BlurConfig getDefaultBlurConfig() {
        MaterialConfig materialConfig = get(true);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getBlurConfig();
    }

    @Nullable
    public MaterialConfig.ColorBlendConfig getDefaultColorBlendConfig() {
        MaterialConfig materialConfig = get(true);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getColorBlendConfig();
    }

    @Nullable
    public MaterialConfig.ShadowConfig getDefaultShadowConfig() {
        MaterialConfig materialConfig = get(true);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getShadowConfig();
    }

    @Nullable
    public MaterialConfig.ShadowConfig getShadowConfig(boolean z2) {
        MaterialConfig materialConfig = get(z2);
        if (materialConfig == null) {
            return null;
        }
        return materialConfig.getShadowConfig();
    }

    public MaterialDayNightConfig(@Nullable MaterialConfig materialConfig) {
        this.defaultConfig = materialConfig;
    }

    public MaterialDayNightConfig(@Nullable MaterialConfig materialConfig, @Nullable MaterialConfig materialConfig2) {
        this.defaultConfig = materialConfig;
        this.darkConfig = materialConfig2;
    }
}
