package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandFakeContentViewBinding implements ViewBinding {

    @NonNull
    public final DynamicIslandContentFakeView fakeContent;

    @NonNull
    private final DynamicIslandContentFakeView rootView;

    private DynamicIslandFakeContentViewBinding(@NonNull DynamicIslandContentFakeView dynamicIslandContentFakeView, @NonNull DynamicIslandContentFakeView dynamicIslandContentFakeView2) {
        this.rootView = dynamicIslandContentFakeView;
        this.fakeContent = dynamicIslandContentFakeView2;
    }

    @NonNull
    public static DynamicIslandFakeContentViewBinding bind(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        DynamicIslandContentFakeView dynamicIslandContentFakeView = (DynamicIslandContentFakeView) view;
        return new DynamicIslandFakeContentViewBinding(dynamicIslandContentFakeView, dynamicIslandContentFakeView);
    }

    @NonNull
    public static DynamicIslandFakeContentViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandFakeContentViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_fake_content_view, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public DynamicIslandContentFakeView getRoot() {
        return this.rootView;
    }
}
