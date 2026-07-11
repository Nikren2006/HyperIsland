package miui.systemui.dynamicisland.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandBigIslandView extends DynamicGlowEffectView {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicIslandBigIslandView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        n.g(context, "context");
    }

    public final ViewGroup getContentView$miui_dynamicisland_release() {
        return getMContainer();
    }

    public final void suppressAdaptiveGlowViewSize$miui_dynamicisland_release() {
        setAdaptiveGlowViewSize(false);
    }

    public final void updateGlowEffectAnim$miui_dynamicisland_release(float f2, float f3, float f4, float f5, float f6) {
        setGlowEffectPosition(f2, f3, f4, f5, f6);
    }

    public /* synthetic */ DynamicIslandBigIslandView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandBigIslandView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
    }
}
