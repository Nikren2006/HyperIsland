package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.ColorFilter;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.BlendModeUtils;

/* JADX INFO: loaded from: classes.dex */
public class BlendModeColorFilterCompat {

    @RequiresApi(29)
    public static class Api29Impl {
        private Api29Impl() {
        }

        public static ColorFilter createBlendModeColorFilter(int i2, Object obj) {
            return new BlendModeColorFilter(i2, (BlendMode) obj);
        }
    }

    private BlendModeColorFilterCompat() {
    }

    public static ColorFilter createBlendModeColorFilterCompat(int i2, BlendModeCompat blendModeCompat) {
        Object objObtainBlendModeFromCompat = BlendModeUtils.Api29Impl.obtainBlendModeFromCompat(blendModeCompat);
        if (objObtainBlendModeFromCompat != null) {
            return Api29Impl.createBlendModeColorFilter(i2, objObtainBlendModeFromCompat);
        }
        return null;
    }
}
