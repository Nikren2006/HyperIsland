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
import miui.systemui.widget.CircularProgressBar;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleCombineImageBinding implements ViewBinding {

    @NonNull
    public final FrameLayout islandContainerModuleImageText1;

    @NonNull
    public final DynamicIslandModuleIcon1Binding islandIcon1;

    @NonNull
    public final DynamicIslandModuleIcon1Binding islandIcon2;

    @NonNull
    public final CircularProgressBar islandProgress;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleCombineImageBinding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1Binding, @NonNull DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1Binding2, @NonNull CircularProgressBar circularProgressBar) {
        this.rootView = frameLayout;
        this.islandContainerModuleImageText1 = frameLayout2;
        this.islandIcon1 = dynamicIslandModuleIcon1Binding;
        this.islandIcon2 = dynamicIslandModuleIcon1Binding2;
        this.islandProgress = circularProgressBar;
    }

    @NonNull
    public static DynamicIslandModuleCombineImageBinding bind(@NonNull View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i2 = R.id.island_icon_1;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1BindingBind = DynamicIslandModuleIcon1Binding.bind(viewFindChildViewById);
            i2 = R.id.island_icon_2;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i2);
            if (viewFindChildViewById2 != null) {
                DynamicIslandModuleIcon1Binding dynamicIslandModuleIcon1BindingBind2 = DynamicIslandModuleIcon1Binding.bind(viewFindChildViewById2);
                i2 = R.id.island_progress;
                CircularProgressBar circularProgressBar = (CircularProgressBar) ViewBindings.findChildViewById(view, i2);
                if (circularProgressBar != null) {
                    return new DynamicIslandModuleCombineImageBinding(frameLayout, frameLayout, dynamicIslandModuleIcon1BindingBind, dynamicIslandModuleIcon1BindingBind2, circularProgressBar);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleCombineImageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleCombineImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_combine_image, viewGroup, false);
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
