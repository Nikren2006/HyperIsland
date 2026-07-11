package miui.systemui.controlcenter.panel.main.devicecenter.entry;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.util.ViewOutlineProviderExt;

/* JADX INFO: loaded from: classes.dex */
public class DeviceCenterEntryFrameLayout extends FrameLayout {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceCenterEntryFrameLayout(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setClipToOutline(true);
        setOutlineProvider(ViewOutlineProviderExt.INSTANCE.getSOLID_BACKGROUND());
        ControlCenterUtils.INSTANCE.createCardFolmeTouchStyle(this);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceCenterEntryFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceCenterEntryFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ DeviceCenterEntryFrameLayout(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterEntryFrameLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
    }
}
