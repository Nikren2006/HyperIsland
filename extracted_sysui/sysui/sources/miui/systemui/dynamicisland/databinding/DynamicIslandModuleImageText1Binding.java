package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import miui.systemui.dynamicisland.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleImageText1Binding implements ViewBinding {

    @NonNull
    public final FrameLayout islandContainerModuleImageText1;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleImageText1Binding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.islandContainerModuleImageText1 = frameLayout2;
    }

    @NonNull
    public static DynamicIslandModuleImageText1Binding bind(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return new DynamicIslandModuleImageText1Binding(frameLayout, frameLayout);
    }

    @NonNull
    public static DynamicIslandModuleImageText1Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleImageText1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_image_text_1, viewGroup, false);
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
