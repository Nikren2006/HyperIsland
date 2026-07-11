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
public final class FakeDynamicSmallIslandTemplateBinding implements ViewBinding {

    @NonNull
    public final FrameLayout fakeSmallContainer;

    @NonNull
    private final FrameLayout rootView;

    private FakeDynamicSmallIslandTemplateBinding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.fakeSmallContainer = frameLayout2;
    }

    @NonNull
    public static FakeDynamicSmallIslandTemplateBinding bind(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return new FakeDynamicSmallIslandTemplateBinding(frameLayout, frameLayout);
    }

    @NonNull
    public static FakeDynamicSmallIslandTemplateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FakeDynamicSmallIslandTemplateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.fake_dynamic_small_island_template, viewGroup, false);
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
