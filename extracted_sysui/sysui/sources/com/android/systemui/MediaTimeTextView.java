package com.android.systemui;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class MediaTimeTextView extends AppCompatTextView {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaTimeTextView(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    private final String millisecondsToTimeString(long j2) {
        return DateUtils.formatElapsedTime(c1.f.d(j2 / ((long) 1000), 0L));
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final void setMediaTime(long j2) {
        setText(millisecondsToTimeString(j2));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaTimeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ MediaTimeTextView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaTimeTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
    }
}
