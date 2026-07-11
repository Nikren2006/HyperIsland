package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.widget.AnimateColorView;
import miui.systemui.controlcenter.widget.NoTransformTouchFrameLayout;
import miui.systemui.controlcenter.widget.ToggleSliderView;
import miui.systemui.controlcenter.widget.VerticalSeekBar;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.View;

/* JADX INFO: loaded from: classes.dex */
public final class ToggleSliderItemViewBinding implements ViewBinding {

    @NonNull
    public final FrameLayout brightnessShadow;

    @NonNull
    public final AnimateColorView icon;

    @NonNull
    public final View progress;

    @NonNull
    public final View progressBg;

    @NonNull
    private final ToggleSliderView rootView;

    @NonNull
    public final VerticalSeekBar slider;

    @NonNull
    public final ToggleSliderView toggleSlider;

    @NonNull
    public final NoTransformTouchFrameLayout toggleSliderInner;

    @NonNull
    public final TextView topText;

    private ToggleSliderItemViewBinding(@NonNull ToggleSliderView toggleSliderView, @NonNull FrameLayout frameLayout, @NonNull AnimateColorView animateColorView, @NonNull View view, @NonNull View view2, @NonNull VerticalSeekBar verticalSeekBar, @NonNull ToggleSliderView toggleSliderView2, @NonNull NoTransformTouchFrameLayout noTransformTouchFrameLayout, @NonNull TextView textView) {
        this.rootView = toggleSliderView;
        this.brightnessShadow = frameLayout;
        this.icon = animateColorView;
        this.progress = view;
        this.progressBg = view2;
        this.slider = verticalSeekBar;
        this.toggleSlider = toggleSliderView2;
        this.toggleSliderInner = noTransformTouchFrameLayout;
        this.topText = textView;
    }

    @NonNull
    public static ToggleSliderItemViewBinding bind(@NonNull android.view.View view) {
        int i2 = R.id.brightness_shadow;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
        if (frameLayout != null) {
            i2 = R.id.icon;
            AnimateColorView animateColorView = (AnimateColorView) ViewBindings.findChildViewById(view, i2);
            if (animateColorView != null) {
                i2 = R.id.progress;
                View view2 = (View) ViewBindings.findChildViewById(view, i2);
                if (view2 != null) {
                    i2 = R.id.progress_bg;
                    View view3 = (View) ViewBindings.findChildViewById(view, i2);
                    if (view3 != null) {
                        i2 = R.id.slider;
                        VerticalSeekBar verticalSeekBar = (VerticalSeekBar) ViewBindings.findChildViewById(view, i2);
                        if (verticalSeekBar != null) {
                            ToggleSliderView toggleSliderView = (ToggleSliderView) view;
                            i2 = R.id.toggle_slider_inner;
                            NoTransformTouchFrameLayout noTransformTouchFrameLayout = (NoTransformTouchFrameLayout) ViewBindings.findChildViewById(view, i2);
                            if (noTransformTouchFrameLayout != null) {
                                i2 = R.id.top_text;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                                if (textView != null) {
                                    return new ToggleSliderItemViewBinding(toggleSliderView, frameLayout, animateColorView, view2, view3, verticalSeekBar, toggleSliderView, noTransformTouchFrameLayout, textView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ToggleSliderItemViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ToggleSliderItemViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        android.view.View viewInflate = layoutInflater.inflate(R.layout.toggle_slider_item_view, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ToggleSliderView getRoot() {
        return this.rootView;
    }
}
