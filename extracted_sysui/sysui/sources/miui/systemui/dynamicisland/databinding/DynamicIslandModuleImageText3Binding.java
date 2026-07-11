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

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleImageText3Binding implements ViewBinding {

    @NonNull
    public final DynamicIslandModuleIcon1Binding islandContainerModuleIcon;

    @NonNull
    public final FrameLayout islandContainerModuleImageText3;

    @NonNull
    public final DynamicIslandModuleRightTextBinding islandContainerModuleText;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleImageText3Binding(@NonNull FrameLayout frameLayout, @NonNull DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1Binding, @NonNull FrameLayout frameLayout2, @NonNull DynamicIslandModuleRightTextBinding dynamicIslandModuleRightTextBinding) {
        this.rootView = frameLayout;
        this.islandContainerModuleIcon = dynamicIslandModuleIcon1Binding;
        this.islandContainerModuleImageText3 = frameLayout2;
        this.islandContainerModuleText = dynamicIslandModuleRightTextBinding;
    }

    @NonNull
    public static DynamicIslandModuleImageText3Binding bind(@NonNull View view) {
        int i2 = R.id.island_container_module_icon;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1BindingBind = DynamicIslandModuleIcon1Binding.bind(viewFindChildViewById);
            FrameLayout frameLayout = (FrameLayout) view;
            int i3 = R.id.island_container_module_text;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i3);
            if (viewFindChildViewById2 != null) {
                return new DynamicIslandModuleImageText3Binding(frameLayout, dynamicIslandModuleIcon1BindingBind, frameLayout, DynamicIslandModuleRightTextBinding.bind(viewFindChildViewById2));
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleImageText3Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleImageText3Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_image_text_3, viewGroup, false);
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
