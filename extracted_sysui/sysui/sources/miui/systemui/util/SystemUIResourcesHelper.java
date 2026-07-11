package miui.systemui.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;

/* JADX INFO: loaded from: classes4.dex */
public interface SystemUIResourcesHelper {
    static /* synthetic */ Float getFraction$default(SystemUIResourcesHelper systemUIResourcesHelper, String str, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFraction");
        }
        if ((i4 & 2) != 0) {
            i2 = 1;
        }
        if ((i4 & 4) != 0) {
            i3 = 1;
        }
        return systemUIResourcesHelper.getFraction(str, i2, i3);
    }

    Boolean getBoolean(String str);

    @ColorInt
    Integer getColor(String str);

    @Dimension
    Integer getDimensionPixelSize(String str);

    Drawable getDrawable(String str);

    Float getFraction(String str, int i2, int i3);

    Integer getInteger(String str);

    int getResId(String str, String str2);

    String getString(String str);

    String getString(String str, Object... objArr);

    View inflateLayout(String str);
}
