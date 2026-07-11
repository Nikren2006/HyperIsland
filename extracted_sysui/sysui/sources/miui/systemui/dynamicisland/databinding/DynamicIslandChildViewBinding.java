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
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandChildViewBinding implements ViewBinding {

    @NonNull
    public final DynamicIslandBigIslandView bigIslandView;

    @NonNull
    public final FrameLayout container;

    @NonNull
    public final DynamicIslandExpandedView expandedView;

    @NonNull
    public final View islandMask;

    @NonNull
    public final View miniWindowBar;

    @NonNull
    private final FrameLayout rootView;

    @NonNull
    public final FrameLayout smallIslandView;

    private DynamicIslandChildViewBinding(@NonNull FrameLayout frameLayout, @NonNull DynamicIslandBigIslandView dynamicIslandBigIslandView, @NonNull FrameLayout frameLayout2, @NonNull DynamicIslandExpandedView dynamicIslandExpandedView, @NonNull View view, @NonNull View view2, @NonNull FrameLayout frameLayout3) {
        this.rootView = frameLayout;
        this.bigIslandView = dynamicIslandBigIslandView;
        this.container = frameLayout2;
        this.expandedView = dynamicIslandExpandedView;
        this.islandMask = view;
        this.miniWindowBar = view2;
        this.smallIslandView = frameLayout3;
    }

    @NonNull
    public static DynamicIslandChildViewBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i2 = R.id.big_island_view;
        DynamicIslandBigIslandView dynamicIslandBigIslandView = (DynamicIslandBigIslandView) ViewBindings.findChildViewById(view, i2);
        if (dynamicIslandBigIslandView != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            i2 = R.id.expanded_view;
            DynamicIslandExpandedView dynamicIslandExpandedView = (DynamicIslandExpandedView) ViewBindings.findChildViewById(view, i2);
            if (dynamicIslandExpandedView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.island_mask))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i2 = R.id.mini_window_bar))) != null) {
                i2 = R.id.small_island_view;
                FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
                if (frameLayout2 != null) {
                    return new DynamicIslandChildViewBinding(frameLayout, dynamicIslandBigIslandView, frameLayout, dynamicIslandExpandedView, viewFindChildViewById, viewFindChildViewById2, frameLayout2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandChildViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandChildViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_child_view, viewGroup, false);
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
