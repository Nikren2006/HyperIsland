package miuix.animation.listener;

import androidx.annotation.Nullable;
import java.util.Collection;
import miuix.animation.IAnimTarget;
import miuix.animation.base.AnimSpecialConfig;
import miuix.animation.internal.AnimInfo;
import miuix.animation.internal.AnimTask;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IIntValueProperty;
import miuix.animation.utils.LogUtils;

/* JADX INFO: loaded from: classes4.dex */
public class UpdateInfo {
    public int frameCount;
    public boolean isCompleted;
    public boolean justStart;
    public final FloatProperty property;
    public final boolean useInt;
    public double velocity;
    public Integer preparedTransitionId = null;
    public final AnimInfo animInfo = new AnimInfo();

    public UpdateInfo(FloatProperty floatProperty) {
        this.property = floatProperty;
        this.useInt = floatProperty instanceof IIntValueProperty;
    }

    public static UpdateInfo findBy(Collection<UpdateInfo> collection, FloatProperty floatProperty) {
        for (UpdateInfo updateInfo : collection) {
            if (updateInfo.property.equals(floatProperty)) {
                return updateInfo;
            }
        }
        return null;
    }

    public static UpdateInfo findByName(Collection<UpdateInfo> collection, String str) {
        for (UpdateInfo updateInfo : collection) {
            if (updateInfo.property.getName().equals(str)) {
                return updateInfo;
            }
        }
        return null;
    }

    public static float getSafeFloatValue(float f2, @Nullable AnimSpecialConfig animSpecialConfig) {
        if (animSpecialConfig == null || !animSpecialConfig.hasSetSafeValue) {
            return f2;
        }
        double d2 = animSpecialConfig.minValue;
        if (f2 >= ((float) d2)) {
            d2 = animSpecialConfig.maxValue;
            if (f2 <= ((float) d2)) {
                return f2;
            }
        }
        return (float) d2;
    }

    public static int getSafeIntValue(int i2, @Nullable AnimSpecialConfig animSpecialConfig) {
        if (animSpecialConfig == null || !animSpecialConfig.hasSetSafeValue) {
            return i2;
        }
        double d2 = animSpecialConfig.minValue;
        if (i2 >= ((int) d2)) {
            d2 = animSpecialConfig.maxValue;
            if (i2 <= ((int) d2)) {
                return i2;
            }
        }
        return (int) d2;
    }

    public float getFloatValue() {
        AnimInfo animInfo = this.animInfo;
        double d2 = animInfo.setToValue;
        if (d2 != Double.MAX_VALUE) {
            return (float) d2;
        }
        double d3 = animInfo.value;
        if (d3 >= 3.4028234663852886E38d) {
            d3 = 3.4028234663852886E38d;
        }
        float f2 = (float) d3;
        if (f2 != Float.MAX_VALUE) {
            return Math.max(-3.4028235E38f, f2);
        }
        LogUtils.debug("warning value is Float.MAX_VALUE !! correct to startValue " + this.animInfo.startValue + " " + this, new Object[0]);
        AnimInfo animInfo2 = this.animInfo;
        double d4 = animInfo2.startValue;
        animInfo2.value = d4;
        return (float) d4;
    }

    public int getIntValue() {
        AnimInfo animInfo = this.animInfo;
        double d2 = animInfo.setToValue;
        if (d2 != Double.MAX_VALUE) {
            return (int) d2;
        }
        double d3 = animInfo.value;
        int i2 = d3 >= Double.MAX_VALUE ? Integer.MAX_VALUE : (int) d3;
        if (i2 != Integer.MAX_VALUE) {
            return Math.max(-2147483647, i2);
        }
        LogUtils.debug("warning value is Integer.MAX_VALUE !! correct to startValue " + this.animInfo.startValue + " " + this, new Object[0]);
        AnimInfo animInfo2 = this.animInfo;
        double d4 = animInfo2.startValue;
        animInfo2.value = d4;
        return (int) d4;
    }

    public Class<?> getType() {
        return this.property instanceof IIntValueProperty ? Integer.TYPE : Float.TYPE;
    }

    public <T> T getValue(Class<T> cls) {
        return (cls == Float.class || cls == Float.TYPE) ? (T) Float.valueOf(getFloatValue()) : (cls == Double.class || cls == Double.TYPE) ? (T) Double.valueOf(this.animInfo.value) : (T) Integer.valueOf(getIntValue());
    }

    public boolean isValid() {
        return this.property != null;
    }

    public void reset() {
        this.isCompleted = false;
        this.frameCount = 0;
    }

    public void setOp(byte b2) {
        boolean z2 = b2 == 0 || b2 > 2;
        this.isCompleted = z2;
        if (z2 && AnimTask.isRunning(this.animInfo.op)) {
            this.animInfo.justEnd = true;
        }
        this.animInfo.op = b2;
        if (LogUtils.isLogMoreEnable()) {
            LogUtils.debug("---- UpdateInfo id=" + hashCode(), "name=" + this.property.getName(), "setOp=" + ((int) b2), "justEnd=" + this.animInfo.justEnd, "completed=" + this.isCompleted);
        }
    }

    public void setTargetValue(IAnimTarget iAnimTarget, boolean z2) {
        if (z2) {
            if (this.useInt) {
                iAnimTarget.doSetIntValue((IIntValueProperty) this.property, getIntValue());
                return;
            } else {
                iAnimTarget.doSetValue(this.property, getFloatValue());
                return;
            }
        }
        if (this.useInt) {
            iAnimTarget.setIntValue((IIntValueProperty) this.property, getIntValue());
        } else {
            iAnimTarget.setValue(this.property, getFloatValue());
        }
    }

    public void skipToTargetValue(IAnimTarget iAnimTarget) {
        AnimInfo animInfo = this.animInfo;
        double d2 = animInfo.targetValue;
        if (d2 != Double.MAX_VALUE) {
            animInfo.value = d2;
        }
        this.velocity = 0.0d;
        setTargetValue(iAnimTarget, false);
        setOp((byte) 3);
    }

    public String toString() {
        return "UpdateInfo{id=" + hashCode() + " " + this.property.getName() + "=" + this.animInfo.value + ", v_format=" + (this.useInt ? Integer.toHexString((int) this.animInfo.value) : Double.toString(this.animInfo.value)) + ", p=" + this.property + ", op=" + ((int) this.animInfo.op) + ", v=" + this.animInfo.value + ", start-v=" + this.animInfo.startValue + ", target-v=" + this.animInfo.targetValue + ", useInt=" + this.useInt + ", completed=" + this.isCompleted + ", setTo-v=" + this.animInfo.setToValue + ", velocity=" + this.velocity + ", start-t=" + this.animInfo.startTime + ", frameCount=" + this.frameCount + ", frameInterval=" + this.animInfo.frameInterval + '}';
    }
}
