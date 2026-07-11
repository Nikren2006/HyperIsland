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
public final class DynamicIslandModuleProgressTextBinding implements ViewBinding {

    @NonNull
    public final FrameLayout islandContainerModuleImageText2;

    @NonNull
    public final CircularProgressBar islandProgress;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleProgressTextBinding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull CircularProgressBar circularProgressBar) {
        this.rootView = frameLayout;
        this.islandContainerModuleImageText2 = frameLayout2;
        this.islandProgress = circularProgressBar;
    }

    @NonNull
    public static DynamicIslandModuleProgressTextBinding bind(@NonNull View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i2 = R.id.island_progress;
        CircularProgressBar circularProgressBar = (CircularProgressBar) ViewBindings.findChildViewById(view, i2);
        if (circularProgressBar != null) {
            return new DynamicIslandModuleProgressTextBinding(frameLayout, frameLayout, circularProgressBar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleProgressTextBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleProgressTextBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_progress_text, viewGroup, false);
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
