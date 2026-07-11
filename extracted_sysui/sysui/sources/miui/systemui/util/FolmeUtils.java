package miui.systemui.util;

import miuix.animation.Folme;
import miuix.animation.IAnimTarget;
import miuix.animation.IStateStyle;
import miuix.animation.controller.IFolmeStateStyle;

/* JADX INFO: loaded from: classes4.dex */
public final class FolmeUtils {
    public static final FolmeUtils INSTANCE = new FolmeUtils();

    private FolmeUtils() {
    }

    public static /* synthetic */ IStateStyle useValue$default(FolmeUtils folmeUtils, Object obj, float f2, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            f2 = Float.MAX_VALUE;
        }
        return folmeUtils.useValue(obj, f2);
    }

    public final void clean(Object target) {
        kotlin.jvm.internal.n.g(target, "target");
        Folme.clean(target);
    }

    public final IStateStyle useValue(Object target, float f2) {
        IAnimTarget target2;
        kotlin.jvm.internal.n.g(target, "target");
        IStateStyle iStateStyleUseValue = Folme.useValue(target);
        IFolmeStateStyle iFolmeStateStyle = iStateStyleUseValue instanceof IFolmeStateStyle ? (IFolmeStateStyle) iStateStyleUseValue : null;
        if (iFolmeStateStyle != null && (target2 = iFolmeStateStyle.getTarget()) != null) {
            target2.setDefaultMinVisibleChange(f2);
        }
        kotlin.jvm.internal.n.f(iStateStyleUseValue, "apply(...)");
        return iStateStyleUseValue;
    }
}
