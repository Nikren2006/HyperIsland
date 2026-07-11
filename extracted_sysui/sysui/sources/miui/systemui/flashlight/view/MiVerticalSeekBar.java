package miui.systemui.flashlight.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"AppCompatCustomView"})
public final class MiVerticalSeekBar extends SeekBar {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiVerticalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas c2) {
        n.g(c2, "c");
        c2.rotate(-90.0f);
        c2.translate(-getHeight(), 0.0f);
        super.onDraw(c2);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i3, i2);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i3, i2, i4, i5);
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i2) {
        super.setProgress(i2);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }
}
