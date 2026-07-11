package miui.systemui.flashlight;

import android.widget.SeekBar;
import androidx.appcompat.content.res.AppCompatResources;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.util.MiBlurCompat;
import miuix.core.util.HyperMaterialUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$seekbar$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$seekbar$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final SeekBar invoke() {
        SeekBar seekBar = (SeekBar) this.this$0.findViewById(R.id.mi_fl_seekbar);
        if (HyperMaterialUtils.isFeatureEnable(seekBar.getContext())) {
            MiBlurCompat.setMiViewBlurModeCompat(seekBar, 3);
            seekBar.setProgressDrawable(AppCompatResources.getDrawable(seekBar.getContext(), R.drawable.mi_seekbar_advanced));
            int[] intArray = seekBar.getContext().getResources().getIntArray(R.array.flashlight_seekbar_blend_colors);
            n.f(intArray, "getIntArray(...)");
            MiBlurCompat.setMiBackgroundBlendColors$default(seekBar, intArray, 0.0f, 2, (Object) null);
        } else {
            seekBar.setProgressDrawable(AppCompatResources.getDrawable(seekBar.getContext(), R.drawable.mi_seekbar));
        }
        seekBar.setImportantForAccessibility(2);
        return seekBar;
    }
}
