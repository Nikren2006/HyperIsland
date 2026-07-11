package miuix.animation;

import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.physics.FactorOperator;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes4.dex */
public interface IStateStyle extends FolmeStyle, IStateContainer {
    IStateStyle add(String str, float f2);

    IStateStyle add(String str, float f2, long j2);

    IStateStyle add(String str, int i2);

    IStateStyle add(String str, int i2, long j2);

    IStateStyle add(FloatProperty floatProperty, float f2);

    IStateStyle add(FloatProperty floatProperty, float f2, long j2);

    IStateStyle add(FloatProperty floatProperty, int i2);

    IStateStyle add(FloatProperty floatProperty, int i2, long j2);

    IStateStyle addInitProperty(String str, float f2);

    IStateStyle addInitProperty(String str, int i2);

    IStateStyle addInitProperty(FloatProperty floatProperty, float f2);

    IStateStyle addInitProperty(FloatProperty floatProperty, int i2);

    IStateStyle addListener(TransitionListener transitionListener);

    void cancel(AnimState animState);

    AnimState getCurrentState();

    float getPredictFriction(FloatProperty floatProperty, float f2);

    float getPredictValue(FloatProperty floatProperty, float... fArr);

    IStateStyle removeListener(TransitionListener transitionListener);

    IStateStyle set(Object obj);

    IStateStyle setConfig(AnimConfig animConfig, FloatProperty... floatPropertyArr);

    IStateStyle setEase(int i2, float... fArr);

    IStateStyle setEase(int i2, FactorOperator... factorOperatorArr);

    IStateStyle setEase(FloatProperty floatProperty, int i2, float... fArr);

    IStateStyle setEase(EaseManager.EaseStyle easeStyle, FloatProperty... floatPropertyArr);

    IStateStyle setTransitionFlags(long j2, FloatProperty... floatPropertyArr);
}
