package miui.systemui.flashlight;

import android.widget.SeekBar;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$seekbarTouchViewOnChangeListener$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$seekbarTouchViewOnChangeListener$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.flashlight.MiFlashlightController$seekbarTouchViewOnChangeListener$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MiFlashlightController miFlashlightController = this.this$0;
        return new SeekBar.OnSeekBarChangeListener() { // from class: miui.systemui.flashlight.MiFlashlightController$seekbarTouchViewOnChangeListener$2.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
                if (z2) {
                    float max = i2 / miFlashlightController.getSeekbar().getMax();
                    miFlashlightController.setFlashlightStatus(max);
                    MiFlashlightController.setUiLogicProgress$default(miFlashlightController, max, false, 2, null);
                    miFlashlightController.hapticWhenSeekBarToEdge(i2);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
    }
}
