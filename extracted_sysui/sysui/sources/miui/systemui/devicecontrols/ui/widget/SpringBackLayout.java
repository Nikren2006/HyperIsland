package miui.systemui.devicecontrols.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowInsets;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class SpringBackLayout extends miuix.springback.view.SpringBackLayout {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SpringBackLayout(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        n.g(context, "context");
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        CommonUtils.INSTANCE.setMarginBottom(this, windowInsets != null ? windowInsets.getStableInsetBottom() : 0, true);
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        n.f(windowInsetsOnApplyWindowInsets, "onApplyWindowInsets(...)");
        return windowInsetsOnApplyWindowInsets;
    }

    public /* synthetic */ SpringBackLayout(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpringBackLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
    }
}
