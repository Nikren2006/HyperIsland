package com.android.systemui.miui.volume;

import d1.InterfaceC0330i;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes2.dex */
public final class MiuiColorTransitionAnim<T> {
    private IStateStyle anim;
    private T fromColor;
    private final Z0.c progress$delegate;
    private T toColor;
    private final Function3 translate;
    static final /* synthetic */ InterfaceC0330i[] $$delegatedProperties = {kotlin.jvm.internal.z.d(new kotlin.jvm.internal.q(MiuiColorTransitionAnim.class, "progress", "getProgress()F", 0))};
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle TRANSITION_EASE = FolmeEase.sinOut(200);
    private static final MiuiColorTransitionAnim$Companion$PROPERTY_PROCESS$1 PROPERTY_PROCESS = new FloatProperty<MiuiColorTransitionAnim<Object>>() { // from class: com.android.systemui.miui.volume.MiuiColorTransitionAnim$Companion$PROPERTY_PROCESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(MiuiColorTransitionAnim<Object> miuiColorTransitionAnim) {
            if (miuiColorTransitionAnim != null) {
                return miuiColorTransitionAnim.getProgress();
            }
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(MiuiColorTransitionAnim<Object> miuiColorTransitionAnim, float f2) {
            if (miuiColorTransitionAnim == null) {
                return;
            }
            miuiColorTransitionAnim.setProgress(f2);
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public MiuiColorTransitionAnim(Function3 translate) {
        kotlin.jvm.internal.n.g(translate, "translate");
        this.translate = translate;
        Z0.a aVar = Z0.a.f970a;
        final Float fValueOf = Float.valueOf(-1.0f);
        this.progress$delegate = new Z0.b(fValueOf) { // from class: com.android.systemui.miui.volume.MiuiColorTransitionAnim$special$$inlined$observable$1
            @Override // Z0.b
            public void afterChange(InterfaceC0330i property, Float f2, Float f3) {
                Object obj;
                kotlin.jvm.internal.n.g(property, "property");
                float fFloatValue = f3.floatValue();
                if (f2.floatValue() == fFloatValue || (obj = this.fromColor) == null) {
                    return;
                }
                Function3 translate2 = this.getTranslate();
                Object obj2 = this.toColor;
                kotlin.jvm.internal.n.d(obj2);
                translate2.invoke(obj, obj2, Float.valueOf(fFloatValue));
            }
        };
    }

    public static /* synthetic */ void transition$default(MiuiColorTransitionAnim miuiColorTransitionAnim, Object obj, Object obj2, boolean z2, int i2, Object obj3) {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        miuiColorTransitionAnim.transition(obj, obj2, z2);
    }

    public final float getProgress() {
        return ((Number) this.progress$delegate.getValue(this, $$delegatedProperties[0])).floatValue();
    }

    public final Function3 getTranslate() {
        return this.translate;
    }

    public final void release() {
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
        this.anim = null;
        Folme.clean(this);
        this.fromColor = null;
        this.toColor = null;
    }

    public final void setProgress(float f2) {
        this.progress$delegate.setValue(this, $$delegatedProperties[0], Float.valueOf(f2));
    }

    public final void transition(T t2, T t3, boolean z2) {
        this.fromColor = t2;
        this.toColor = t3;
        Float fValueOf = Float.valueOf(1.0f);
        if (t2 == null || kotlin.jvm.internal.n.c(t2, t3)) {
            this.translate.invoke(t3, t3, fValueOf);
            return;
        }
        if (z2) {
            IStateStyle iStateStyleUseValue = this.anim;
            if (iStateStyleUseValue == null) {
                iStateStyleUseValue = Folme.useValue(this);
            }
            this.anim = iStateStyleUseValue;
            kotlin.jvm.internal.n.d(iStateStyleUseValue);
            iStateStyleUseValue.cancel();
            IStateStyle iStateStyle = this.anim;
            kotlin.jvm.internal.n.d(iStateStyle);
            MiuiColorTransitionAnim$Companion$PROPERTY_PROCESS$1 miuiColorTransitionAnim$Companion$PROPERTY_PROCESS$1 = PROPERTY_PROCESS;
            iStateStyle.setTo(miuiColorTransitionAnim$Companion$PROPERTY_PROCESS$1, Float.valueOf(0.0f)).to(miuiColorTransitionAnim$Companion$PROPERTY_PROCESS$1, fValueOf, TRANSITION_EASE);
        }
    }
}
