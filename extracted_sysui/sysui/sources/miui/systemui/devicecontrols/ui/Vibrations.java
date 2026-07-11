package miui.systemui.devicecontrols.ui;

import android.os.VibrationEffect;

/* JADX INFO: loaded from: classes3.dex */
public final class Vibrations {
    public static final Vibrations INSTANCE;
    private static final VibrationEffect rangeEdgeEffect;
    private static final VibrationEffect rangeMiddleEffect;

    static {
        Vibrations vibrations = new Vibrations();
        INSTANCE = vibrations;
        rangeEdgeEffect = vibrations.initRangeEdgeEffect();
        rangeMiddleEffect = vibrations.initRangeMiddleEffect();
    }

    private Vibrations() {
    }

    private final VibrationEffect initRangeEdgeEffect() {
        VibrationEffect.Composition compositionStartComposition = VibrationEffect.startComposition();
        compositionStartComposition.addPrimitive(7, 0.5f);
        VibrationEffect vibrationEffectCompose = compositionStartComposition.compose();
        kotlin.jvm.internal.n.f(vibrationEffectCompose, "compose(...)");
        return vibrationEffectCompose;
    }

    private final VibrationEffect initRangeMiddleEffect() {
        VibrationEffect.Composition compositionStartComposition = VibrationEffect.startComposition();
        compositionStartComposition.addPrimitive(7, 0.1f);
        VibrationEffect vibrationEffectCompose = compositionStartComposition.compose();
        kotlin.jvm.internal.n.f(vibrationEffectCompose, "compose(...)");
        return vibrationEffectCompose;
    }

    public final VibrationEffect getRangeEdgeEffect() {
        return rangeEdgeEffect;
    }

    public final VibrationEffect getRangeMiddleEffect() {
        return rangeMiddleEffect;
    }
}
