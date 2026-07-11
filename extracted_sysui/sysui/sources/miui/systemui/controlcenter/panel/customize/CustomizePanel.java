package miui.systemui.controlcenter.panel.customize;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizePanel extends FrameLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomizePanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.not_added_container);
        viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), windowInsets != null ? windowInsets.getStableInsetBottom() : viewGroup.getPaddingBottom());
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        n.f(windowInsetsOnApplyWindowInsets, "onApplyWindowInsets(...)");
        return windowInsetsOnApplyWindowInsets;
    }
}
