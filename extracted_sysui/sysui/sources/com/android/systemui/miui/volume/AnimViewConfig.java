package com.android.systemui.miui.volume;

import android.view.View;
import com.miui.maml.folme.AnimatedProperty;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import kotlin.jvm.functions.Function1;
import miuix.animation.base.AnimSpecialConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes2.dex */
public final class AnimViewConfig {
    private final String ALPHA;
    private final String SCALE;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    private final String f1463X;
    private final TransitionListener folmeListener;
    private final View target;
    private HashMap<String, UpdateCallback> updateCallbackMap;
    public ViewArgs viewArgs;

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.AnimViewConfig$updateInfo$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).floatValue());
            return H0.s.f314a;
        }

        public final void invoke(float f2) {
            if (AnimViewConfig.this.getTarget().getX() == f2) {
                return;
            }
            AnimViewConfig.this.getTarget().setX(f2);
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.AnimViewConfig$updateInfo$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).floatValue());
            return H0.s.f314a;
        }

        public final void invoke(float f2) {
            if (AnimViewConfig.this.getTarget().getScaleX() == f2 && AnimViewConfig.this.getTarget().getScaleY() == f2) {
                return;
            }
            AnimViewConfig.this.getTarget().setScaleX(f2);
            AnimViewConfig.this.getTarget().setScaleY(f2);
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.miui.volume.AnimViewConfig$updateInfo$3, reason: invalid class name */
    public static final class AnonymousClass3 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).floatValue());
            return H0.s.f314a;
        }

        public final void invoke(float f2) {
            if (AnimViewConfig.this.getTarget().getAlpha() == f2) {
                return;
            }
            AnimViewConfig.this.getTarget().setAlpha(f2);
        }
    }

    public AnimViewConfig(View target, TransitionListener folmeListener) {
        kotlin.jvm.internal.n.g(target, "target");
        kotlin.jvm.internal.n.g(folmeListener, "folmeListener");
        this.target = target;
        this.folmeListener = folmeListener;
        this.updateCallbackMap = new HashMap<>();
        this.f1463X = AnimatedProperty.PROPERTY_NAME_X;
        this.SCALE = "scale";
        this.ALPHA = "alpha";
    }

    private final void updateValue(Collection<? extends UpdateInfo> collection, String str, Function1 function1) {
        UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, str);
        if (updateInfoFindByName != null) {
            float floatValue = updateInfoFindByName.getFloatValue();
            if (Float.isInfinite(floatValue) || Float.isNaN(floatValue)) {
                return;
            }
            float floatValue2 = updateInfoFindByName.getFloatValue();
            UpdateCallback updateCallback = this.updateCallbackMap.get(str);
            if (updateCallback == null) {
                function1.invoke(Float.valueOf(floatValue2));
                return;
            }
            UpdateResult updateResultCallback = updateCallback.callback(floatValue2);
            if (updateResultCallback.isContinue()) {
                float result = updateResultCallback.getResult();
                if (Float.isInfinite(result) || Float.isNaN(result)) {
                    function1.invoke(Float.valueOf(updateResultCallback.getResult()));
                } else {
                    function1.invoke(Float.valueOf(floatValue2));
                }
            }
        }
    }

    public final void addUpdateCallback(String tag, UpdateCallback callback) {
        kotlin.jvm.internal.n.g(tag, "tag");
        kotlin.jvm.internal.n.g(callback, "callback");
        this.updateCallbackMap.put(tag, callback);
    }

    public final AnimSpecialConfig folmeConfig(String tag, float... values) {
        kotlin.jvm.internal.n.g(tag, "tag");
        kotlin.jvm.internal.n.g(values, "values");
        ViewArgs viewArgs = getViewArgs();
        long delayX = kotlin.jvm.internal.n.c(tag, this.f1463X) ? viewArgs.getDelayX() : kotlin.jvm.internal.n.c(tag, this.SCALE) ? viewArgs.getDelayScale() : kotlin.jvm.internal.n.c(tag, this.ALPHA) ? viewArgs.getDelayAlpha() : getViewArgs().getDelay();
        AnimSpecialConfig animSpecialConfig = new AnimSpecialConfig();
        animSpecialConfig.delay = delayX;
        animSpecialConfig.ease = EaseManager.getStyle(-2, Arrays.copyOf(values, values.length));
        return animSpecialConfig;
    }

    public final String getALPHA() {
        return this.ALPHA;
    }

    public final TransitionListener getFolmeListener() {
        return this.folmeListener;
    }

    public final String getSCALE() {
        return this.SCALE;
    }

    public final View getTarget() {
        return this.target;
    }

    public final ViewArgs getViewArgs() {
        ViewArgs viewArgs = this.viewArgs;
        if (viewArgs != null) {
            return viewArgs;
        }
        kotlin.jvm.internal.n.w("viewArgs");
        return null;
    }

    public final String getX() {
        return this.f1463X;
    }

    public final String propertyName(String tag) {
        kotlin.jvm.internal.n.g(tag, "tag");
        return tag + "_" + this.target.getClass().getSimpleName() + "_" + this.target.hashCode();
    }

    public final void setViewArgs(ViewArgs viewArgs) {
        kotlin.jvm.internal.n.g(viewArgs, "<set-?>");
        this.viewArgs = viewArgs;
    }

    public final void updateInfo(Collection<? extends UpdateInfo> collection) {
        updateValue(collection, propertyName(this.f1463X), new AnonymousClass1());
        updateValue(collection, propertyName(this.SCALE), new AnonymousClass2());
        updateValue(collection, propertyName(this.ALPHA), new AnonymousClass3());
    }
}
