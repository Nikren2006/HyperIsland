package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.widget.BrightnessPanelRootView;
import miui.systemui.widget.LinearLayout;
import miui.systemui.widget.View;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelBinding implements ViewBinding {

    @NonNull
    public final BrightnessPanelRootView brightnessContainer;

    @NonNull
    public final View brightnessPanelBg;

    @NonNull
    private final BrightnessPanelRootView rootView;

    @NonNull
    public final LinearLayout tilesContainer;

    @NonNull
    public final LinearLayout tilesContent;

    @NonNull
    public final ToggleSliderItemViewBinding toggleSlider;

    private BrightnessPanelBinding(@NonNull BrightnessPanelRootView brightnessPanelRootView, @NonNull BrightnessPanelRootView brightnessPanelRootView2, @NonNull View view, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull ToggleSliderItemViewBinding toggleSliderItemViewBinding) {
        this.rootView = brightnessPanelRootView;
        this.brightnessContainer = brightnessPanelRootView2;
        this.brightnessPanelBg = view;
        this.tilesContainer = linearLayout;
        this.tilesContent = linearLayout2;
        this.toggleSlider = toggleSliderItemViewBinding;
    }

    @NonNull
    public static BrightnessPanelBinding bind(@NonNull android.view.View view) {
        android.view.View viewFindChildViewById;
        BrightnessPanelRootView brightnessPanelRootView = (BrightnessPanelRootView) view;
        int i2 = R.id.brightness_panel_bg;
        View view2 = (View) ViewBindings.findChildViewById(view, i2);
        if (view2 != null) {
            i2 = R.id.tiles_container;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
            if (linearLayout != null) {
                i2 = R.id.tiles_content;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                if (linearLayout2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.toggle_slider))) != null) {
                    return new BrightnessPanelBinding(brightnessPanelRootView, brightnessPanelRootView, view2, linearLayout, linearLayout2, ToggleSliderItemViewBinding.bind(viewFindChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static BrightnessPanelBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BrightnessPanelBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        android.view.View viewInflate = layoutInflater.inflate(R.layout.brightness_panel, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public BrightnessPanelRootView getRoot() {
        return this.rootView;
    }
}
