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
public final class DynamicIslandModuleImageText2Binding implements ViewBinding {

    @NonNull
    public final FrameLayout islandContainerModuleImageText2;

    @NonNull
    public final DynamicIslandModuleRightTextBinding islandContainerModuleText;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleImageText2Binding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull DynamicIslandModuleRightTextBinding dynamicIslandModuleRightTextBinding) {
        this.rootView = frameLayout;
        this.islandContainerModuleImageText2 = frameLayout2;
        this.islandContainerModuleText = dynamicIslandModuleRightTextBinding;
    }

    @NonNull
    public static DynamicIslandModuleImageText2Binding bind(@NonNull View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i2 = R.id.island_container_module_text;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            return new DynamicIslandModuleImageText2Binding(frameLayout, frameLayout, DynamicIslandModuleRightTextBinding.bind(viewFindChildViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleImageText2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleImageText2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_image_text_2, viewGroup, false);
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
