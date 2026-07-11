package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class ToggleSliderView extends NoTransformTouchFrameLayout {
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToggleSliderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        ((VerticalSeekBar) findViewById(R.id.slider)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: miui.systemui.controlcenter.widget.ToggleSliderView.onFinishInflate.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = ToggleSliderView.this.onSeekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onProgressChanged(seekBar, i2, z2);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = ToggleSliderView.this.onSeekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = ToggleSliderView.this.onSeekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
            }
        });
    }

    public final void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener l2) {
        n.g(l2, "l");
        this.onSeekBarChangeListener = l2;
    }
}
