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
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.content.DynamicIslandEmptyContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowViewBinding implements ViewBinding {

    @NonNull
    public final DynamicIslandEmptyContentView emptyContentView;

    @NonNull
    public final FrameLayout glowEffectBottomContainer;

    @NonNull
    public final FrameLayout glowEffectTopContainer;

    @NonNull
    private final DynamicIslandWindowView rootView;

    private DynamicIslandWindowViewBinding(@NonNull DynamicIslandWindowView dynamicIslandWindowView, @NonNull DynamicIslandEmptyContentView dynamicIslandEmptyContentView, @NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2) {
        this.rootView = dynamicIslandWindowView;
        this.emptyContentView = dynamicIslandEmptyContentView;
        this.glowEffectBottomContainer = frameLayout;
        this.glowEffectTopContainer = frameLayout2;
    }

    @NonNull
    public static DynamicIslandWindowViewBinding bind(@NonNull View view) {
        int i2 = R.id.empty_content_view;
        DynamicIslandEmptyContentView dynamicIslandEmptyContentView = (DynamicIslandEmptyContentView) ViewBindings.findChildViewById(view, i2);
        if (dynamicIslandEmptyContentView != null) {
            i2 = R.id.glow_effect_bottom_container;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
            if (frameLayout != null) {
                i2 = R.id.glow_effect_top_container;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
                if (frameLayout2 != null) {
                    return new DynamicIslandWindowViewBinding((DynamicIslandWindowView) view, dynamicIslandEmptyContentView, frameLayout, frameLayout2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandWindowViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandWindowViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_window_view, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public DynamicIslandWindowView getRoot() {
        return this.rootView;
    }
}
