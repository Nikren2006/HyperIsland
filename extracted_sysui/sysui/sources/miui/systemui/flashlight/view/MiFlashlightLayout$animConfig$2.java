package miui.systemui.flashlight.view;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightLayout$animConfig$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightLayout this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightLayout$animConfig$2(MiFlashlightLayout miFlashlightLayout) {
        super(0);
        this.this$0 = miFlashlightLayout;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AnimConfig invoke() {
        AnimConfig animConfig = new AnimConfig();
        final MiFlashlightLayout miFlashlightLayout = this.this$0;
        return animConfig.addListeners(new TransitionListener() { // from class: miui.systemui.flashlight.view.MiFlashlightLayout$animConfig$2.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                miFlashlightLayout.isAlphaAnimRunning = true;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                miFlashlightLayout.isAlphaAnimRunning = false;
                miFlashlightLayout.onAnimEnd();
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
            }
        });
    }
}
