package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.dynamicisland.R;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleTextOverIconBinding implements ViewBinding {

    @NonNull
    public final DynamicIslandModuleIcon1Binding islandContainerModuleIcon;

    @NonNull
    public final FrameLayout islandContainerModuleTextOverIcon;

    @NonNull
    public final TimerTextEffectView islandTitle;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleTextOverIconBinding(@NonNull FrameLayout frameLayout, @NonNull DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1Binding, @NonNull FrameLayout frameLayout2, @NonNull TimerTextEffectView timerTextEffectView) {
        this.rootView = frameLayout;
        this.islandContainerModuleIcon = dynamicIslandModuleIcon1Binding;
        this.islandContainerModuleTextOverIcon = frameLayout2;
        this.islandTitle = timerTextEffectView;
    }

    @NonNull
    public static DynamicIslandModuleTextOverIconBinding bind(@NonNull View view) {
        int i2 = R.id.island_container_module_icon;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1BindingBind = DynamicIslandModuleIcon1Binding.bind(viewFindChildViewById);
            FrameLayout frameLayout = (FrameLayout) view;
            int i3 = R.id.island_title;
            TimerTextEffectView timerTextEffectView = (TimerTextEffectView) ViewBindings.findChildViewById(view, i3);
            if (timerTextEffectView != null) {
                return new DynamicIslandModuleTextOverIconBinding(frameLayout, dynamicIslandModuleIcon1BindingBind, frameLayout, timerTextEffectView);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleTextOverIconBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleTextOverIconBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_text_over_icon, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
