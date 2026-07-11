package miui.systemui.dynamicisland.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SizeF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.mi.widget.view.LightBgView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DynamicGlowEffectView extends FrameLayout {
    private static final Companion Companion = new Companion(null);
    private static final String LOG_TAG = "DynamicGlowEffectView";
    private boolean adaptiveGlowViewSize;
    private float alphaOfGlowEffect;
    private boolean enabledGlowEffect;
    private final LightBgView mContainer;
    private ViewGroup mGlowEffectBottomContainer;
    private View mGlowEffectBottomView;
    private ViewGroup mGlowEffectUpperContainer;
    private View mGlowEffectUpperView;
    private float zOrderOfGlowEffect;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicGlowEffectView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        n.g(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mContainer$lambda$3$lambda$2(DynamicGlowEffectView this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        boolean z2 = this$0.adaptiveGlowViewSize;
        if (!z2) {
            Log.i(LOG_TAG, "Ignore update glow effect view size du to adaptiveGlowViewSize=" + z2);
            return;
        }
        View view2 = this$0.mGlowEffectUpperView;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            }
            layoutParams.width = i4 - i2;
            layoutParams.height = i5 - i3;
            view2.setLayoutParams(layoutParams);
        }
        View view3 = this$0.mGlowEffectUpperView;
        if (view3 != null) {
            view3.measure(View.MeasureSpec.makeMeasureSpec(i4 - i2, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i5 - i3, BasicMeasure.EXACTLY));
        }
        View view4 = this$0.mGlowEffectBottomView;
        if (view4 != null) {
            ViewGroup.LayoutParams layoutParams2 = view4.getLayoutParams();
            if (layoutParams2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            }
            layoutParams2.width = i4 - i2;
            layoutParams2.height = i5 - i3;
            view4.setLayoutParams(layoutParams2);
        }
    }

    public static /* synthetic */ void stopGlowEffect$miui_dynamicisland_release$default(DynamicGlowEffectView dynamicGlowEffectView, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopGlowEffect");
        }
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        dynamicGlowEffectView.stopGlowEffect$miui_dynamicisland_release(z2);
    }

    public final boolean getAdaptiveGlowViewSize() {
        return this.adaptiveGlowViewSize;
    }

    public final float getAlphaOfGlowEffect$miui_dynamicisland_release() {
        return this.alphaOfGlowEffect;
    }

    public final LightBgView getMContainer() {
        return this.mContainer;
    }

    public final ViewGroup getMGlowEffectBottomContainer() {
        return this.mGlowEffectBottomContainer;
    }

    public final View getMGlowEffectBottomView() {
        return this.mGlowEffectBottomView;
    }

    public final ViewGroup getMGlowEffectUpperContainer() {
        return this.mGlowEffectUpperContainer;
    }

    public final View getMGlowEffectUpperView() {
        return this.mGlowEffectUpperView;
    }

    public final float getZOrderOfGlowEffect$miui_dynamicisland_release() {
        return this.zOrderOfGlowEffect;
    }

    public final void initGlowEffect$miui_dynamicisland_release(ViewGroup topContainer, ViewGroup bottomContainer) {
        n.g(topContainer, "topContainer");
        n.g(bottomContainer, "bottomContainer");
        View view = new View(getContext());
        view.setBackgroundColor(0);
        topContainer.addView(view);
        this.mGlowEffectUpperView = view;
        View view2 = new View(getContext());
        view2.setBackgroundColor(0);
        bottomContainer.addView(view2);
        this.mGlowEffectBottomView = view2;
        this.mGlowEffectBottomContainer = bottomContainer;
        this.mGlowEffectUpperContainer = topContainer;
        this.mContainer.setEffectView(this.mGlowEffectUpperView, view2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        View view = this.mGlowEffectUpperView;
        if (view != null) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            this.mGlowEffectUpperView = null;
        }
        View view2 = this.mGlowEffectBottomView;
        if (view2 != null) {
            ViewParent parent2 = view2.getParent();
            ViewGroup viewGroup2 = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
            if (viewGroup2 != null) {
                viewGroup2.removeView(view2);
            }
            this.mGlowEffectBottomView = null;
        }
    }

    public final void setAdaptiveGlowViewSize(boolean z2) {
        this.adaptiveGlowViewSize = z2;
    }

    public final void setAlphaOfGlowEffect$miui_dynamicisland_release(float f2) {
        this.alphaOfGlowEffect = f2;
        View view = this.mGlowEffectUpperView;
        if (view != null) {
            view.setAlpha(f2);
        }
        View view2 = this.mGlowEffectBottomView;
        if (view2 == null) {
            return;
        }
        view2.setAlpha(f2);
    }

    public final void setGlowEffectPosition(float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 - f2;
        float f8 = f5 - f3;
        this.mContainer.setSizeOfGlowRadius(f6);
        this.mContainer.setBottomApertureSize(new SizeF(f7, f8));
        this.mContainer.setUpperApertureSize(new SizeF(f7, f8));
        View view = this.mGlowEffectUpperView;
        if (view != null) {
            view.setTranslationX(((f7 / 2.0f) + f2) - (view.getWidth() / 2.0f));
            view.setTranslationY(((f8 / 2.0f) + f3) - (view.getHeight() / 2.0f));
        }
        View view2 = this.mGlowEffectBottomView;
        if (view2 != null) {
            view2.setTranslationX((f2 + (f7 / 2.0f)) - (view2.getWidth() / 2.0f));
            view2.setTranslationY((f3 + (f8 / 2.0f)) - (view2.getHeight() / 2.0f));
        }
    }

    public final void setMGlowEffectBottomContainer(ViewGroup viewGroup) {
        this.mGlowEffectBottomContainer = viewGroup;
    }

    public final void setMGlowEffectBottomView(View view) {
        this.mGlowEffectBottomView = view;
    }

    public final void setMGlowEffectUpperContainer(ViewGroup viewGroup) {
        this.mGlowEffectUpperContainer = viewGroup;
    }

    public final void setMGlowEffectUpperView(View view) {
        this.mGlowEffectUpperView = view;
    }

    public final void setZOrderOfGlowEffect$miui_dynamicisland_release(float f2) {
        this.zOrderOfGlowEffect = f2;
        View view = this.mGlowEffectUpperView;
        ViewParent parent = view != null ? view.getParent() : null;
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null || viewGroup.getZ() >= f2) {
            return;
        }
        Log.i(LOG_TAG, "GlowEffectUpperView z-order must greater than or equal to " + f2 + ", Adjust z-order to " + f2);
        viewGroup.setZ(f2);
    }

    public final void startGlowEffect$miui_dynamicisland_release() {
        if (this.enabledGlowEffect) {
            return;
        }
        this.enabledGlowEffect = true;
        LightBgView.start$default(this.mContainer, false, 1, null);
    }

    public final void stopGlowEffect$miui_dynamicisland_release(boolean z2) {
        if (this.enabledGlowEffect) {
            this.enabledGlowEffect = false;
            this.mContainer.stop(z2);
        }
    }

    public /* synthetic */ DynamicGlowEffectView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicGlowEffectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        this.adaptiveGlowViewSize = true;
        LightBgView lightBgView = new LightBgView(context, null, 0, 0, null, 30, null);
        lightBgView.setAutoApertureSize(false);
        lightBgView.setSizeOfGlowArea(lightBgView.getResources().getDimension(R.dimen.island_glow_effect_area_size));
        addView(lightBgView);
        lightBgView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miui.systemui.dynamicisland.view.a
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                DynamicGlowEffectView.mContainer$lambda$3$lambda$2(this.f5730a, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        });
        this.mContainer = lightBgView;
        this.alphaOfGlowEffect = 1.0f;
    }
}
