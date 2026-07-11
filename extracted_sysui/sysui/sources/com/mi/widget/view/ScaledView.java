package com.mi.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.AttrRes;
import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.StyleRes;
import androidx.annotation.UiThread;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public class ScaledView extends View {
    public static final int $stable = 8;

    @FloatRange(from = 0.0d, to = 1.0d)
    private float childScale;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ScaledView(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    @UiThread
    public final float getChildScale() {
        return this.childScale;
    }

    @Override // android.view.View
    public void layout(int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int i8 = ((int) ((i6 - r0) * 0.5f)) + i2;
        int i9 = ((int) ((i7 - r1) * 0.5f)) + i3;
        setScaleX(1.0f / this.childScale);
        setScaleY(1.0f / this.childScale);
        super.layout(i8, i9, ((int) Math.ceil(i6 * this.childScale)) + i8, ((int) Math.ceil(i7 * this.childScale)) + i9);
    }

    @AnyThread
    public final void setChildScale(float f2) {
        this.childScale = f2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ScaledView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ScaledView(Context context, AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ ScaledView(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScaledView(Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
        this.childScale = 1.0f;
    }
}
