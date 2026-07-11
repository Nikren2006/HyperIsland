package miui.systemui.flashlight;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity$showBgAnimConfig$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightActivity$showBgAnimConfig$2(MiFlashlightActivity miFlashlightActivity) {
        super(0);
        this.this$0 = miFlashlightActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AnimConfig invoke() {
        AnimConfig animConfig = new AnimConfig();
        final MiFlashlightActivity miFlashlightActivity = this.this$0;
        return animConfig.addListeners(new TransitionListener() { // from class: miui.systemui.flashlight.MiFlashlightActivity$showBgAnimConfig$2.1
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                MiFlashlightActivity miFlashlightActivity2 = miFlashlightActivity;
                miFlashlightActivity2.onBgAnimComplete(new MiFlashlightActivity$showBgAnimConfig$2$1$onComplete$1(miFlashlightActivity2));
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                miFlashlightActivity.onBgAnimUpdate(1.0f, collection, MiFlashlightActivity$showBgAnimConfig$2$1$onUpdate$1.INSTANCE);
            }
        });
    }
}
