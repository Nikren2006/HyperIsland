package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.dynamicisland.DynamicIslandBackgroundView;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandViewBinding implements ViewBinding {

    @NonNull
    public final DynamicIslandBackgroundView islandContainer;

    @NonNull
    public final DynamicIslandContentView islandContent;

    @NonNull
    private final DynamicIslandBackgroundView rootView;

    private DynamicIslandViewBinding(@NonNull DynamicIslandBackgroundView dynamicIslandBackgroundView, @NonNull DynamicIslandBackgroundView dynamicIslandBackgroundView2, @NonNull DynamicIslandContentView dynamicIslandContentView) {
        this.rootView = dynamicIslandBackgroundView;
        this.islandContainer = dynamicIslandBackgroundView2;
        this.islandContent = dynamicIslandContentView;
    }

    @NonNull
    public static DynamicIslandViewBinding bind(@NonNull View view) {
        DynamicIslandBackgroundView dynamicIslandBackgroundView = (DynamicIslandBackgroundView) view;
        int i2 = R.id.island_content;
        DynamicIslandContentView dynamicIslandContentView = (DynamicIslandContentView) ViewBindings.findChildViewById(view, i2);
        if (dynamicIslandContentView != null) {
            return new DynamicIslandViewBinding(dynamicIslandBackgroundView, dynamicIslandBackgroundView, dynamicIslandContentView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_view, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public DynamicIslandBackgroundView getRoot() {
        return this.rootView;
    }
}
