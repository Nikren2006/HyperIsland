package com.android.systemui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class UnPressableRelativeLayout extends RelativeLayout {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UnPressableRelativeLayout(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSetPressed(boolean z2) {
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UnPressableRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ UnPressableRelativeLayout(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnPressableRelativeLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
    }
}
