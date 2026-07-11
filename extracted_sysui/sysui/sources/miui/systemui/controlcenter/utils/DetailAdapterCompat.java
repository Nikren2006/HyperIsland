package miui.systemui.controlcenter.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import androidx.annotation.ColorInt;
import com.android.systemui.plugins.qs.DetailAdapter;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class DetailAdapterCompat {
    public static final DetailAdapterCompat INSTANCE = new DetailAdapterCompat();

    private DetailAdapterCompat() {
    }

    public final int[] getBackgroundBlendColorCompat(DetailAdapter detailAdapter) {
        n.g(detailAdapter, "<this>");
        try {
            return detailAdapter.getBackgroundBlendColors();
        } catch (Throwable unused) {
            return null;
        }
    }

    @ColorInt
    public final int getBackgroundColorCompat(DetailAdapter detailAdapter, Context context) {
        n.g(detailAdapter, "<this>");
        n.g(context, "context");
        try {
            return detailAdapter.getBackgroundColor();
        } catch (Throwable unused) {
            return context.getColor(R.color.secondary_panel_background_color);
        }
    }

    public final ColorStateList getButtonBackgroundColorCompat(DetailAdapter detailAdapter, Context context) {
        n.g(detailAdapter, "<this>");
        n.g(context, "context");
        try {
            ColorStateList buttonBackgroundColor = detailAdapter.getButtonBackgroundColor();
            if (buttonBackgroundColor == null) {
                buttonBackgroundColor = context.getColorStateList(R.color.detail_panel_button_background_color_state);
            }
            n.d(buttonBackgroundColor);
            return buttonBackgroundColor;
        } catch (Throwable unused) {
            ColorStateList colorStateList = context.getColorStateList(R.color.detail_panel_button_background_color_state);
            n.d(colorStateList);
            return colorStateList;
        }
    }

    @ColorInt
    public final int getButtonTextColorCompat(DetailAdapter detailAdapter, Context context) {
        n.g(detailAdapter, "<this>");
        n.g(context, "context");
        try {
            return detailAdapter.getButtonTextColor();
        } catch (Throwable unused) {
            return context.getColor(R.color.detail_panel_button_text_color);
        }
    }

    @ColorInt
    public final int getTitleTextColorCompat(DetailAdapter detailAdapter, Context context) {
        n.g(detailAdapter, "<this>");
        n.g(context, "context");
        try {
            return detailAdapter.getTitleTextColor();
        } catch (Throwable unused) {
            return context.getColor(R.color.detail_panel_header_text_color);
        }
    }
}
