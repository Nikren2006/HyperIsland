package miui.systemui.flashlight;

import android.view.View;
import android.widget.SeekBar;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$seekbarTouchView$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$seekbarTouchView$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final SeekBar invoke() {
        View viewFindViewById = this.this$0.findViewById(R.id.mi_seekbar_touch_view);
        MiFlashlightController miFlashlightController = this.this$0;
        SeekBar seekBar = (SeekBar) viewFindViewById;
        seekBar.setOnSeekBarChangeListener(miFlashlightController.getSeekbarTouchViewOnChangeListener());
        seekBar.setOnTouchListener(miFlashlightController.getSeekBarOnTouchListener());
        return seekBar;
    }
}
