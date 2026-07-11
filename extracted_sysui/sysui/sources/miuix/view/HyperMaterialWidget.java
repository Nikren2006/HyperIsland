package miuix.view;

import androidx.annotation.Nullable;
import miuix.core.util.MaterialConfig;
import miuix.core.util.MaterialDayNightConfig;

/* JADX INFO: loaded from: classes5.dex */
public interface HyperMaterialWidget {
    @Nullable
    default MaterialConfig getCurrentMaterial() {
        return null;
    }

    @Nullable
    default MaterialDayNightConfig getMaterial() {
        return null;
    }

    default void setMaterial(@Nullable MaterialConfig materialConfig) {
    }

    default void updateMaterialEffect() {
    }

    default void setMaterial(@Nullable MaterialDayNightConfig materialDayNightConfig) {
    }
}
