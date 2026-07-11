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
import miui.systemui.notification.HyperChronometerPlugin;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandModuleSameWidthDigitBinding implements ViewBinding {

    @NonNull
    public final HyperChronometerPlugin islandChronometer;

    @NonNull
    public final LinearLayout islandContainerModuleSameWidthDigit;

    @NonNull
    public final FrameLayout islandContainerModuleText;

    @NonNull
    public final TimerTextEffectView islandContent;

    @NonNull
    public final View islandTextShade;

    @NonNull
    public final TimerTextEffectView islandTitle;

    @NonNull
    private final FrameLayout rootView;

    private DynamicIslandModuleSameWidthDigitBinding(@NonNull FrameLayout frameLayout, @NonNull HyperChronometerPlugin hyperChronometerPlugin, @NonNull LinearLayout linearLayout, @NonNull FrameLayout frameLayout2, @NonNull TimerTextEffectView timerTextEffectView, @NonNull View view, @NonNull TimerTextEffectView timerTextEffectView2) {
        this.rootView = frameLayout;
        this.islandChronometer = hyperChronometerPlugin;
        this.islandContainerModuleSameWidthDigit = linearLayout;
        this.islandContainerModuleText = frameLayout2;
        this.islandContent = timerTextEffectView;
        this.islandTextShade = view;
        this.islandTitle = timerTextEffectView2;
    }

    @NonNull
    public static DynamicIslandModuleSameWidthDigitBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        int i2 = R.id.island_chronometer;
        HyperChronometerPlugin hyperChronometerPlugin = (HyperChronometerPlugin) ViewBindings.findChildViewById(view, i2);
        if (hyperChronometerPlugin != null) {
            i2 = R.id.island_container_module_same_width_digit;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
            if (linearLayout != null) {
                FrameLayout frameLayout = (FrameLayout) view;
                i2 = R.id.island_content;
                TimerTextEffectView timerTextEffectView = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
                if (timerTextEffectView != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.island_text_shade))) != null) {
                    i2 = R.id.island_title;
                    TimerTextEffectView timerTextEffectView2 = (TimerTextEffectView) ViewBindings.findChildViewById(view, i2);
                    if (timerTextEffectView2 != null) {
                        return new DynamicIslandModuleSameWidthDigitBinding(frameLayout, hyperChronometerPlugin, linearLayout, frameLayout, timerTextEffectView, viewFindChildViewById, timerTextEffectView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DynamicIslandModuleSameWidthDigitBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DynamicIslandModuleSameWidthDigitBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.dynamic_island_module_same_width_digit, viewGroup, false);
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
