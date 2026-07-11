package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.dynamicisland.R;

/* JADX INFO: loaded from: classes3.dex */
public final class FakeDynamicBigIslandTemplateBinding implements ViewBinding {

    @NonNull
    public final FrameLayout fakeAreaCutout;

    @NonNull
    public final FrameLayout fakeAreaLeft;

    @NonNull
    public final FrameLayout fakeAreaRight;

    @NonNull
    public final LinearLayout fakeBigContainer;

    @NonNull
    private final LinearLayout rootView;

    private FakeDynamicBigIslandTemplateBinding(@NonNull LinearLayout linearLayout, @NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull FrameLayout frameLayout3, @NonNull LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.fakeAreaCutout = frameLayout;
        this.fakeAreaLeft = frameLayout2;
        this.fakeAreaRight = frameLayout3;
        this.fakeBigContainer = linearLayout2;
    }

    @NonNull
    public static FakeDynamicBigIslandTemplateBinding bind(@NonNull View view) {
        int i2 = R.id.fake_area_cutout;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
        if (frameLayout != null) {
            i2 = R.id.fake_area_left;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
            if (frameLayout2 != null) {
                i2 = R.id.fake_area_right;
                FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
                if (frameLayout3 != null) {
                    LinearLayout linearLayout = (LinearLayout) view;
                    return new FakeDynamicBigIslandTemplateBinding(linearLayout, frameLayout, frameLayout2, frameLayout3, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FakeDynamicBigIslandTemplateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FakeDynamicBigIslandTemplateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.fake_dynamic_big_island_template, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
