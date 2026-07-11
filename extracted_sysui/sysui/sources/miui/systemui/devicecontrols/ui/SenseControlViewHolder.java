package miui.systemui.devicecontrols.ui;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.devicecontrols.CustomIconCache;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.databinding.ControlsFavoriteSenseItemBinding;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class SenseControlViewHolder extends ControlViewHolder {
    private final ControlsFavoriteSenseItemBinding binding;

    /* JADX WARN: Illegal instructions before constructor call */
    public SenseControlViewHolder(ControlsFavoriteSenseItemBinding binding, ControlsController controlsController, DelayableExecutor uiExecutor, DelayableExecutor bgExecutor, ControlActionCoordinator controlActionCoordinator, CustomIconCache iconCache) {
        kotlin.jvm.internal.n.g(binding, "binding");
        kotlin.jvm.internal.n.g(controlsController, "controlsController");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(controlActionCoordinator, "controlActionCoordinator");
        kotlin.jvm.internal.n.g(iconCache, "iconCache");
        ConstraintLayout root = binding.getRoot();
        kotlin.jvm.internal.n.f(root, "getRoot(...)");
        super(root, controlsController, uiExecutor, bgExecutor, controlActionCoordinator, iconCache);
        this.binding = binding;
    }

    public final ControlsFavoriteSenseItemBinding getBinding() {
        return this.binding;
    }

    @Override // miui.systemui.devicecontrols.ui.ControlViewHolder, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        if (configUtils.textAppearanceChanged(i2)) {
            this.binding.title.setTextAppearance(R.style.TextAppearance_Control_Device_Title);
            this.binding.subtitle.setTextAppearance(R.style.TextAppearance_Control_Device_Subtitle);
            this.binding.status.setTextAppearance(R.style.TextAppearance_Control_Device_Status);
            this.binding.title.setAutoSizeTextTypeUniformWithConfiguration(getContext().getResources().getDimensionPixelSize(R.dimen.device_controls_item_title_min_text_size), getContext().getResources().getDimensionPixelSize(R.dimen.device_controls_item_title_max_text_size), getContext().getResources().getDimensionPixelSize(R.dimen.device_controls_item_auto_size_step_granularity), 0);
        }
        if (zDimensionsChanged) {
            Resources resources = this.itemView.getResources();
            float dimension = resources.getDimension(R.dimen.control_corner_radius);
            Drawable background = getLayout().getBackground();
            kotlin.jvm.internal.n.e(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
            Drawable drawableFindDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(R.id.background);
            GradientDrawable gradientDrawable = drawableFindDrawableByLayerId instanceof GradientDrawable ? (GradientDrawable) drawableFindDrawableByLayerId : null;
            if (gradientDrawable != null) {
                gradientDrawable.setCornerRadius(dimension);
            }
            Drawable drawable = getClipLayer().getDrawable();
            GradientDrawable gradientDrawable2 = drawable instanceof GradientDrawable ? (GradientDrawable) drawable : null;
            if (gradientDrawable2 != null) {
                gradientDrawable2.setCornerRadius(dimension);
            }
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = this.itemView;
            kotlin.jvm.internal.n.f(itemView, "itemView");
            CommonUtils.setLayoutSize$default(commonUtils, itemView, resources.getDimensionPixelSize(R.dimen.device_controls_item_width), resources.getDimensionPixelSize(R.dimen.device_controls_sense_item_height), false, 4, null);
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.device_controls_sense_item_icon_size);
            ImageView imageView = this.binding.icon;
            kotlin.jvm.internal.n.d(imageView);
            CommonUtils.setLayoutSize$default(commonUtils, imageView, dimensionPixelSize, dimensionPixelSize, false, 4, null);
            CommonUtils.setMarginStart$default(commonUtils, imageView, resources.getDimensionPixelSize(R.dimen.device_controls_sense_item_icon_margin_start), false, 2, null);
            TextView textView = this.binding.title;
            kotlin.jvm.internal.n.d(textView);
            CommonUtils.setMarginStart$default(commonUtils, textView, resources.getDimensionPixelSize(R.dimen.device_controls_sense_item_title_margin_start), false, 2, null);
            CommonUtils.setMarginEnd$default(commonUtils, textView, resources.getDimensionPixelSize(R.dimen.device_controls_item_title_margin_horizontal), false, 2, null);
            View view = this.binding.vBorder;
            kotlin.jvm.internal.n.d(view);
            CommonUtils.setLayoutSize$default(commonUtils, view, resources.getDimensionPixelSize(R.dimen.device_controls_item_border_width), resources.getDimensionPixelSize(R.dimen.device_controls_item_border_height), false, 4, null);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.device_controls_item_border_margin_horizontal);
            CommonUtils.setMarginStart$default(commonUtils, view, dimensionPixelSize2, false, 2, null);
            CommonUtils.setMarginEnd$default(commonUtils, view, dimensionPixelSize2, false, 2, null);
        }
    }
}
