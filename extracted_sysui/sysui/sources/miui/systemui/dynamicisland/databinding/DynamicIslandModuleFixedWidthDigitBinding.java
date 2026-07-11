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
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleFixedWidthDigitBinding implements ViewBinding {

    @NonNull
    public final FrameLayout islandContainerModuleFixWidthDigit;

    @NonNull
    public final LinearLayout islandContainerModuleFixedWidthDigit;

    @NonNull
    public final TimerTextEffectView islandContent;

    @NonNull
    public final TimerTextEffectView islandDigit;

    @NonNull
    public final View islandTextShade;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleFixedWidthDigitBinding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2, @NonNull LinearLayout linearLayout, @NonNull TimerTextEffectView timerTextEffectView, @NonNull TimerTextEffectView timerTextEffectView2, @NonNull View view) {
        this.rootView = frameLayout;
        this.islandContainerModuleFixWidthDigit = frameLayout2;
        this.islandContainerModuleFixedWidthDigit = linearLayout;
        this.islandContent = timerTextEffectView;
        this.islandDigit = timerTextEffectView2;
        this.islandTextShade = view;
    }

    @NonNull
    public static DynamicIslandModuleFixedWidthDigitBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        FrameLayout frameLayout = (FrameLayout) view;
        int i2 = R.id.island_container_module_fixed_width_digit;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
        if (linearLayout != null) {
            i2 = R.id.island_content;
            TimerTextEffectView timerTextEffectView = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
            if (timerTextEffectView != null) {
                i2 = R.id.island_digit;
                TimerTextEffectView timerTextEffectView2 = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
                if (timerTextEffectView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.island_text_shade))) != null) {
                    return new DynamicIslandModuleFixedWidthDigitBinding(frameLayout, frameLayout, linearLayout, timerTextEffectView, timerTextEffectView2, viewFindChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleFixedWidthDigitBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleFixedWidthDigitBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_fixed_width_digit, viewGroup, false);
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
