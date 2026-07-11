package miui.systemui.controlcenter.panel.main.devicecenter.entry;

import android.content.Context;
import android.util.AttributeSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.widget.ClickableRecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterEntryRecyclerView extends ClickableRecyclerView {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceCenterEntryRecyclerView(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeviceCenterEntryRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ DeviceCenterEntryRecyclerView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceCenterEntryRecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        setNestedScrollingEnabled(false);
        setOverScrollMode(2);
    }
}
