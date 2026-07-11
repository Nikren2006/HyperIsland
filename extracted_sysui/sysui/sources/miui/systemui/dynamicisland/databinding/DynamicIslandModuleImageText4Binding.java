package miui.systemui.dynamicisland.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mi.widget.view.ChargerView;
import com.mi.widget.view.PowerSaveView;
import miui.systemui.dynamicisland.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleImageText4Binding implements ViewBinding {

    @NonNull
    public final ChargerView chargeView;

    @NonNull
    public final FrameLayout islandContainerModuleImageText1;

    @NonNull
    public final PowerSaveView powerSaveView;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleImageText4Binding(@NonNull FrameLayout frameLayout, @NonNull ChargerView chargerView, @NonNull FrameLayout frameLayout2, @NonNull PowerSaveView powerSaveView) {
        this.rootView = frameLayout;
        this.chargeView = chargerView;
        this.islandContainerModuleImageText1 = frameLayout2;
        this.powerSaveView = powerSaveView;
    }

    @NonNull
    public static DynamicIslandModuleImageText4Binding bind(@NonNull View view) {
        int i2 = R.id.charge_view;
        ChargerView chargerView = (ChargerView) ViewBindings.findChildViewById(view, i2);
        if (chargerView != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            int i3 = R.id.power_save_view;
            PowerSaveView powerSaveView = (PowerSaveView) ViewBindings.findChildViewById(view, i3);
            if (powerSaveView != null) {
                return new DynamicIslandModuleImageText4Binding(frameLayout, chargerView, frameLayout, powerSaveView);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleImageText4Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleImageText4Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_image_text_4, viewGroup, false);
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
