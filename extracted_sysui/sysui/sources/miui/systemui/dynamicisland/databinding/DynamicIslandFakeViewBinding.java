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
public final class DynamicIslandFakeViewBinding implements ViewBinding {

    @NonNull
    public final FrameLayout fakeBigIslandView;

    @NonNull
    public final FrameLayout fakeContainer;

    @NonNull
    public final FrameLayout fakeExpandedView;

    @NonNull
    public final View fakeIslandMask;

    @NonNull
    public final FrameLayout fakeSmallIslandView;

    @NonNull
    public final View miniWindowBar;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandFakeViewBinding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull FrameLayout frameLayout3, @NonNull FrameLayout frameLayout4, @NonNull View view, @NonNull FrameLayout frameLayout5, @NonNull View view2) {
        this.rootView = frameLayout;
        this.fakeBigIslandView = frameLayout2;
        this.fakeContainer = frameLayout3;
        this.fakeExpandedView = frameLayout4;
        this.fakeIslandMask = view;
        this.fakeSmallIslandView = frameLayout5;
        this.miniWindowBar = view2;
    }

    @NonNull
    public static DynamicIslandFakeViewBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i2 = R.id.fake_big_island_view;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
        if (frameLayout != null) {
            FrameLayout frameLayout2 = (FrameLayout) view;
            i2 = R.id.fake_expanded_view;
            FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
            if (frameLayout3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.fake_island_mask))) != null) {
                i2 = R.id.fake_small_island_view;
                FrameLayout frameLayout4 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
                if (frameLayout4 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i2 = R.id.mini_window_bar))) != null) {
                    return new DynamicIslandFakeViewBinding(frameLayout2, frameLayout, frameLayout2, frameLayout3, viewFindChildViewById, frameLayout4, viewFindChildViewById2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandFakeViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandFakeViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_fake_view, viewGroup, false);
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
