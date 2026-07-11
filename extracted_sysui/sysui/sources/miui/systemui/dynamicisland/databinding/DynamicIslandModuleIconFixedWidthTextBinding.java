package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.dynamicisland.R;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleIconFixedWidthTextBinding implements ViewBinding {

    @NonNull
    public final TimerTextEffectView fixedWidthText;

    @NonNull
    public final LinearLayout iconFixedWidthTextLayout;

    @NonNull
    public final DynamicIslandModuleIcon1Binding islandContainerModuleIcon;

    @NonNull
    private final LinearLayout rootView;

    @NonNull
    public final TimerTextEffectView unitText;

    private DynamicIslandModuleIconFixedWidthTextBinding(@NonNull LinearLayout linearLayout, @NonNull TimerTextEffectView timerTextEffectView, @NonNull LinearLayout linearLayout2, @NonNull DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1Binding, @NonNull TimerTextEffectView timerTextEffectView2) {
        this.rootView = linearLayout;
        this.fixedWidthText = timerTextEffectView;
        this.iconFixedWidthTextLayout = linearLayout2;
        this.islandContainerModuleIcon = dynamicIslandModuleIcon1Binding;
        this.unitText = timerTextEffectView2;
    }

    @NonNull
    public static DynamicIslandModuleIconFixedWidthTextBinding bind(@NonNull View view) {
        int i2 = R.id.fixed_width_text;
        TimerTextEffectView timerTextEffectView = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
        if (timerTextEffectView != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            i2 = R.id.island_container_module_icon;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
            if (viewFindChildViewById != null) {
                DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1BindingBind = DynamicIslandModuleIcon1Binding.bind(viewFindChildViewById);
                i2 = R.id.unit_text;
                TimerTextEffectView timerTextEffectView2 = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
                if (timerTextEffectView2 != null) {
                    return new DynamicIslandModuleIconFixedWidthTextBinding(linearLayout, timerTextEffectView, linearLayout, dynamicIslandModuleIcon1BindingBind, timerTextEffectView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleIconFixedWidthTextBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleIconFixedWidthTextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_icon_fixed_width_text, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
