package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.widget.NonTouchableFrameLayout;
import miui.systemui.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterSecondaryBinding implements ViewBinding {

    @NonNull
    public final BrightnessPanelBinding brightnessPanel;

    @NonNull
    public final ConstraintLayout controlCenterSecondary;

    @NonNull
    public final DetailPanelBinding detailPanel;

    @NonNull
    public final NonTouchableFrameLayout fakeContainer;

    @NonNull
    public final MediaPanelBinding mediaPanel;

    @NonNull
    private final ConstraintLayout rootView;

    @NonNull
    public final VolumePanelBinding volumePanel;

    private ControlCenterSecondaryBinding(@NonNull ConstraintLayout constraintLayout, @NonNull BrightnessPanelBinding brightnessPanelBinding, @NonNull ConstraintLayout constraintLayout2, @NonNull DetailPanelBinding detailPanelBinding, @NonNull NonTouchableFrameLayout nonTouchableFrameLayout, @NonNull MediaPanelBinding mediaPanelBinding, @NonNull VolumePanelBinding volumePanelBinding) {
        this.rootView = constraintLayout;
        this.brightnessPanel = brightnessPanelBinding;
        this.controlCenterSecondary = constraintLayout2;
        this.detailPanel = detailPanelBinding;
        this.fakeContainer = nonTouchableFrameLayout;
        this.mediaPanel = mediaPanelBinding;
        this.volumePanel = volumePanelBinding;
    }

    @NonNull
    public static ControlCenterSecondaryBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        int i2 = R.id.brightness_panel;
        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById2 != null) {
            BrightnessPanelBinding brightnessPanelBindingBind = BrightnessPanelBinding.bind(viewFindChildViewById2);
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i2 = R.id.detail_panel;
            View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i2);
            if (viewFindChildViewById3 != null) {
                DetailPanelBinding detailPanelBindingBind = DetailPanelBinding.bind(viewFindChildViewById3);
                i2 = R.id.fake_container;
                NonTouchableFrameLayout nonTouchableFrameLayout = (NonTouchableFrameLayout) ViewBindings.findChildViewById(view, i2);
                if (nonTouchableFrameLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.media_panel))) != null) {
                    MediaPanelBinding mediaPanelBindingBind = MediaPanelBinding.bind(viewFindChildViewById);
                    i2 = R.id.volume_panel;
                    View viewFindChildViewById4 = ViewBindings.findChildViewById(view, i2);
                    if (viewFindChildViewById4 != null) {
                        return new ControlCenterSecondaryBinding(constraintLayout, brightnessPanelBindingBind, constraintLayout, detailPanelBindingBind, nonTouchableFrameLayout, mediaPanelBindingBind, VolumePanelBinding.bind(viewFindChildViewById4));
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlCenterSecondaryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlCenterSecondaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.control_center_secondary, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
}
