package miui.systemui.notification;

import H0.s;
import com.airbnb.lottie.LottieAnimationView;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class LottieProgressManager$LottieProgressSyncHelper$updateProgress$1 extends o implements Function2 {
    final /* synthetic */ float $progress;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LottieProgressManager$LottieProgressSyncHelper$updateProgress$1(float f2) {
        super(2);
        this.$progress = f2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (LottieAnimationView) obj2);
        return s.f314a;
    }

    public final void invoke(String scene, LottieAnimationView view) {
        n.g(scene, "scene");
        n.g(view, "view");
        view.setProgress(this.$progress);
    }
}
