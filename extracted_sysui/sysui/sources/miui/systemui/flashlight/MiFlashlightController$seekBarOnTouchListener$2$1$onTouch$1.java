package miui.systemui.flashlight;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import android.view.MotionEvent;
import g1.E;
import kotlin.jvm.functions.Function2;
import miui.systemui.flashlight.MiFlashlightController$seekBarOnTouchListener$2;
import miui.systemui.util.TalkBackUtils;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.flashlight.MiFlashlightController$seekBarOnTouchListener$2$1$onTouch$1", f = "MiFlashlightController.kt", l = {240}, m = "invokeSuspend")
public final class MiFlashlightController$seekBarOnTouchListener$2$1$onTouch$1 extends l implements Function2 {
    final /* synthetic */ MotionEvent $event;
    int label;
    final /* synthetic */ MiFlashlightController this$0;
    final /* synthetic */ MiFlashlightController$seekBarOnTouchListener$2.AnonymousClass1 this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$seekBarOnTouchListener$2$1$onTouch$1(MiFlashlightController miFlashlightController, MiFlashlightController$seekBarOnTouchListener$2.AnonymousClass1 anonymousClass1, MotionEvent motionEvent, d dVar) {
        super(2, dVar);
        this.this$0 = miFlashlightController;
        this.this$1 = anonymousClass1;
        this.$event = motionEvent;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new MiFlashlightController$seekBarOnTouchListener$2$1$onTouch$1(this.this$0, this.this$1, this.$event, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((MiFlashlightController$seekBarOnTouchListener$2$1$onTouch$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            TalkBackUtils talkBackUtils = this.this$0.getTalkBackUtils();
            this.label = 1;
            obj = talkBackUtils.getTalkBackEnabled(this);
            if (obj == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            this.this$1.doTouch(this.$event);
        }
        return s.f314a;
    }
}
