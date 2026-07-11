package miuix.appcompat.internal.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import miuix.appcompat.R;

/* JADX INFO: loaded from: classes3.dex */
public class LayoutUIUtils {
    public static final int COMMON_FONT_SEARCH_MODE_TEXT_SIZE_DP = 27;
    public static final int EXTRA_HPADDING_HUGE = 3;
    public static final int EXTRA_HPADDING_INVALID = 0;
    public static final int EXTRA_HPADDING_LARGE = 2;
    public static final int EXTRA_HPADDING_SMALL = 1;
    public static final int TALL_FONT_SEARCH_MODE_TEXT_SIZE_DP = 16;

    public static int getExtraPaddingByLevel(Context context, int i2) {
        if (i2 == 1) {
            return context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_window_extra_padding_horizontal_small);
        }
        if (i2 == 2) {
            return context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_window_extra_padding_horizontal_large);
        }
        if (i2 != 3) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_window_extra_padding_horizontal_huge);
    }

    public static boolean isLevelValid(int i2) {
        return i2 >= 0 && i2 <= 3;
    }

    public static void resetSearchModeStubInputTextSize(Resources resources, View view, int i2) {
        TextView textView;
        if (view == null || resources == null || (textView = (TextView) view.findViewById(android.R.id.input)) == null) {
            return;
        }
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_search_edit_text_size);
        float f2 = resources.getDisplayMetrics().density;
        float f3 = dimensionPixelSize / f2;
        float textSize = textView.getTextSize() / f2;
        float f4 = i2;
        if (textSize <= f4 || f3 <= f4) {
            return;
        }
        textView.setTextSize(1, f4);
    }
}
