package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;

/* JADX INFO: loaded from: classes.dex */
public class DetailPanelMoreButtonView extends TextView {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DetailPanelMoreButtonView(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    @Override // android.view.View
    public void dispatchSetPressed(boolean z2) {
        Context context = getContext();
        n.f(context, "getContext(...)");
        if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context)) {
            CommonUtils.INSTANCE.clearMiBlurBlendEffect(this);
        } else {
            MiBlurCompat.setMiViewBlurModeCompat(this, 1);
            MiBlurCompat.setMiBackgroundBlendColors(this, z2 ? MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_MORE_BUTTON_PRESSED_BLEND_COLORS() : MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS());
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DetailPanelMoreButtonView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DetailPanelMoreButtonView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ DetailPanelMoreButtonView(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailPanelMoreButtonView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
    }
}
