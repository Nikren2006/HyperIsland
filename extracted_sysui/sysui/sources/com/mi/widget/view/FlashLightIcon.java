package com.mi.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.AttrRes;
import androidx.annotation.Keep;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 1)
@Keep
public final class FlashLightIcon extends AppCompatImageView {
    public static final int $stable = 0;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlashLightIcon(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlashLightIcon(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ FlashLightIcon(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlashLightIcon(Context context, AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        setBackgroundResource(e0.c.f4022g);
        setScaleType(ImageView.ScaleType.CENTER);
    }
}
