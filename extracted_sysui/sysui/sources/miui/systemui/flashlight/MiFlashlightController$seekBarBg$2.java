package miui.systemui.flashlight;

import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.util.MiBlurCompat;
import miuix.core.util.HyperMaterialUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$seekBarBg$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$seekBarBg$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final ImageView invoke() {
        ImageView imageView = (ImageView) this.this$0.findViewById(R.id.mi_seekbar_bg);
        if (HyperMaterialUtils.isFeatureEnable(imageView.getContext())) {
            MiBlurCompat.setMiViewBlurModeCompat(imageView, 3);
            imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.mi_seekbar_bg_advanced));
            int[] intArray = imageView.getContext().getResources().getIntArray(R.array.flashlight_seekbar_bg_colors);
            n.f(intArray, "getIntArray(...)");
            MiBlurCompat.setMiBackgroundBlendColors$default(imageView, intArray, 0.0f, 2, (Object) null);
        } else {
            imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.getContext(), R.drawable.mi_seekbar_bg));
        }
        return imageView;
    }
}
