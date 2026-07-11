package miui.systemui.controlcenter.panel.main.volume;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
public final class VolumeSliderController$animConfig$2 extends o implements Function0 {
    public static final VolumeSliderController$animConfig$2 INSTANCE = new VolumeSliderController$animConfig$2();

    public VolumeSliderController$animConfig$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final AnimConfig invoke() {
        return new AnimConfig().setEase(FolmeEase.decelerate(200L));
    }
}
