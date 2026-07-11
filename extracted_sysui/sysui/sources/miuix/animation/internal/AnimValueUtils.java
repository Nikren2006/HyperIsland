package miuix.animation.internal;

import miuix.animation.IAnimTarget;
import miuix.animation.ViewTarget;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IIntValueProperty;
import miuix.animation.property.ISpecificProperty;
import miuix.animation.utils.CommonUtils;

/* JADX INFO: loaded from: classes4.dex */
public class AnimValueUtils {
    private AnimValueUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static double getCurTargetValue(IAnimTarget iAnimTarget, FloatProperty floatProperty, double d2) {
        double dSignum = Math.signum(d2);
        double dAbs = Math.abs(d2);
        if (dAbs == 1000000.0d) {
            return dSignum * ((double) CommonUtils.getSize(iAnimTarget, floatProperty));
        }
        double intValue = iAnimTarget instanceof ViewTarget ? floatProperty instanceof IIntValueProperty ? iAnimTarget.getIntValue((IIntValueProperty) floatProperty) : iAnimTarget.getValue(floatProperty) : iAnimTarget.getDoubleValue(floatProperty);
        return dAbs == 1000100.0d ? intValue * dSignum : intValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static double getValue(IAnimTarget iAnimTarget, FloatProperty floatProperty, double d2) {
        return floatProperty instanceof ISpecificProperty ? ((ISpecificProperty) floatProperty).getSpecificValue((float) d2) : getCurTargetValue(iAnimTarget, floatProperty, d2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static double getValueOfTarget(IAnimTarget iAnimTarget, FloatProperty floatProperty, double d2) {
        return d2 == 2.147483647E9d ? iAnimTarget.getIntValue((IIntValueProperty) floatProperty) : d2 == 3.4028234663852886E38d ? iAnimTarget.getValue(floatProperty) : getValue(iAnimTarget, floatProperty, d2);
    }

    public static boolean handleSetToValue(UpdateInfo updateInfo) {
        AnimInfo animInfo = updateInfo.animInfo;
        if (!isValid(animInfo.setToValue)) {
            return false;
        }
        animInfo.value = animInfo.setToValue;
        animInfo.setToValue = Double.MAX_VALUE;
        return true;
    }

    public static boolean isInvalid(double d2) {
        return d2 == Double.MAX_VALUE || d2 == 3.4028234663852886E38d || d2 == 2.147483647E9d;
    }

    public static boolean isValid(double d2) {
        return !isInvalid(d2);
    }
}
