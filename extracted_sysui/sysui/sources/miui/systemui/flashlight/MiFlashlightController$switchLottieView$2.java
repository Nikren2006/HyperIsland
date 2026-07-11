package miui.systemui.flashlight;

import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.util.MiBlurCompat;
import miuix.animation.Folme;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$switchLottieView$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$switchLottieView$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final LottieAnimationView invoke() {
        LottieAnimationView lottieAnimationView = (LottieAnimationView) this.this$0.findViewById(R.id.mi_flashlight_lottie_view);
        Folme.use((View) lottieAnimationView).touch().setScale(0.8f, ITouchStyle.TouchType.DOWN).setScale(1.0f, ITouchStyle.TouchType.UP).handleTouchOf(lottieAnimationView, new AnimConfig[0]);
        MiBlurCompat.setMiViewBlurModeCompat(lottieAnimationView, 3);
        int[] intArray = lottieAnimationView.getContext().getResources().getIntArray(R.array.flashlight_switch_colors);
        n.f(intArray, "getIntArray(...)");
        MiBlurCompat.setMiBackgroundBlendColors$default(lottieAnimationView, intArray, 0.0f, 2, (Object) null);
        return lottieAnimationView;
    }
}
