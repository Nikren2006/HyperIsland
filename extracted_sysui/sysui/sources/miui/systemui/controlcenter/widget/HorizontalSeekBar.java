package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.widget.RelativeSeekBarInjector;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalSeekBar extends SeekBar {
    private final RelativeSeekBarInjector injector;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HorizontalSeekBar(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.injector.transformTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HorizontalSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ HorizontalSeekBar(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizontalSeekBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
        this.injector = new RelativeSeekBarInjector(this, true);
    }
}
