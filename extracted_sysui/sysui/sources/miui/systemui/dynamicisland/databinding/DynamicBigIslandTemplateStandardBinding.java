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
public final class DynamicBigIslandTemplateStandardBinding implements ViewBinding {

    @NonNull
    public final FrameLayout areaCutout;

    @NonNull
    public final FrameLayout areaLeft;

    @NonNull
    public final FrameLayout areaRight;

    @NonNull
    public final LinearLayout bigContainer;

    @NonNull
    private final LinearLayout rootView;

    private DynamicBigIslandTemplateStandardBinding(@NonNull LinearLayout linearLayout, @NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull FrameLayout frameLayout3, @NonNull LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.areaCutout = frameLayout;
        this.areaLeft = frameLayout2;
        this.areaRight = frameLayout3;
        this.bigContainer = linearLayout2;
    }

    @NonNull
    public static DynamicBigIslandTemplateStandardBinding bind(@NonNull View view) {
        int i2 = R.id.area_cutout;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
        if (frameLayout != null) {
            i2 = R.id.area_left;
            FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
            if (frameLayout2 != null) {
                i2 = R.id.area_right;
                FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view, i2);
                if (frameLayout3 != null) {
                    LinearLayout linearLayout = (LinearLayout) view;
                    return new DynamicBigIslandTemplateStandardBinding(linearLayout, frameLayout, frameLayout2, frameLayout3, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicBigIslandTemplateStandardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicBigIslandTemplateStandardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_big_island_template_standard, viewGroup, false);
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
