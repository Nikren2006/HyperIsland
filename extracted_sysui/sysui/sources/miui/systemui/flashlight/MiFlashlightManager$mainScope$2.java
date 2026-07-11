package miui.systemui.flashlight;

import g1.E;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightManager$mainScope$2 extends o implements Function0 {
    public static final MiFlashlightManager$mainScope$2 INSTANCE = new MiFlashlightManager$mainScope$2();

    public MiFlashlightManager$mainScope$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final E invoke() {
        return CoroutineScopeKt.MainScope();
    }
}
