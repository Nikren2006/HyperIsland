package miui.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public class ConstraintLayout extends androidx.constraintlayout.widget.ConstraintLayout {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConstraintLayout(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ ConstraintLayout(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
    }
}
