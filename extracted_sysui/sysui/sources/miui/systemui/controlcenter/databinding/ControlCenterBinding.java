package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.widget.LinearLayout;
import miui.systemui.widget.View;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterBinding implements ViewBinding {

    @NonNull
    public final CustomizerPanelBinding customizerPanel;

    @NonNull
    public final LinearLayout mainPanelContainer;

    @NonNull
    public final MainPanelHeaderBinding mainPanelHeader;

    @NonNull
    private final ControlCenterWindowViewImpl rootView;

    @NonNull
    public final SmartHomePanelBinding smartHomePanel;

    @NonNull
    public final View themeBackground;

    private ControlCenterBinding(@NonNull ControlCenterWindowViewImpl controlCenterWindowViewImpl, @NonNull CustomizerPanelBinding customizerPanelBinding, @NonNull LinearLayout linearLayout, @NonNull MainPanelHeaderBinding mainPanelHeaderBinding, @NonNull SmartHomePanelBinding smartHomePanelBinding, @NonNull View view) {
        this.rootView = controlCenterWindowViewImpl;
        this.customizerPanel = customizerPanelBinding;
        this.mainPanelContainer = linearLayout;
        this.mainPanelHeader = mainPanelHeaderBinding;
        this.smartHomePanel = smartHomePanelBinding;
        this.themeBackground = view;
    }

    @NonNull
    public static ControlCenterBinding bind(@NonNull android.view.View view) {
        android.view.View viewFindChildViewById;
        int i2 = R.id.customizer_panel;
        android.view.View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById2 != null) {
            CustomizerPanelBinding customizerPanelBindingBind = CustomizerPanelBinding.bind(viewFindChildViewById2);
            i2 = R.id.main_panel_container;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
            if (linearLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.main_panel_header))) != null) {
                MainPanelHeaderBinding mainPanelHeaderBindingBind = MainPanelHeaderBinding.bind(viewFindChildViewById);
                i2 = R.id.smart_home_panel;
                android.view.View viewFindChildViewById3 = ViewBindings.findChildViewById(view, i2);
                if (viewFindChildViewById3 != null) {
                    SmartHomePanelBinding smartHomePanelBindingBind = SmartHomePanelBinding.bind(viewFindChildViewById3);
                    i2 = R.id.theme_background;
                    View view2 = (View) ViewBindings.findChildViewById(view, i2);
                    if (view2 != null) {
                        return new ControlCenterBinding((ControlCenterWindowViewImpl) view, customizerPanelBindingBind, linearLayout, mainPanelHeaderBindingBind, smartHomePanelBindingBind, view2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlCenterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlCenterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        android.view.View viewInflate = layoutInflater.inflate(R.layout.control_center, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ControlCenterWindowViewImpl getRoot() {
        return this.rootView;
    }
}
