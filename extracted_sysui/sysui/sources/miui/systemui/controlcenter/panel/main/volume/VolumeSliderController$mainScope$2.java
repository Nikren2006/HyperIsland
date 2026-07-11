package miui.systemui.controlcenter.panel.main.volume;

import g1.E;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: classes.dex */
public final class VolumeSliderController$mainScope$2 extends o implements Function0 {
    public static final VolumeSliderController$mainScope$2 INSTANCE = new VolumeSliderController$mainScope$2();

    public VolumeSliderController$mainScope$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final E invoke() {
        return CoroutineScopeKt.MainScope();
    }
}
