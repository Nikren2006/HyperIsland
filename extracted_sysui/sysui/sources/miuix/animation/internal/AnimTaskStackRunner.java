package miuix.animation.internal;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import miuix.animation.IAnimTarget;
import miuix.animation.base.AnimConfig;
import miuix.animation.base.AnimSpecialConfig;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ColorProperty;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ViewPropertyExt;
import miuix.animation.styles.ForegroundColorStyle;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.LogUtils;

/* JADX INFO: loaded from: classes4.dex */
class AnimTaskStackRunner {
    static int INIT_RESULT_CODE_FAILED = 1;
    static int INIT_RESULT_CODE_SUCCESS = 0;
    static int INIT_RESULT_CODE_VALUE_INVALID = 2;
    private static final String SECTION_TAG = "Folme.TaskRunner_doFrame";
    static final ThreadLocal<AnimData> animDataLocal = new ThreadLocal<>();
    static final ThreadLocal<List<UpdateInfo>> tempTaskUpdateList = new ThreadLocal<List<UpdateInfo>>() { // from class: miuix.animation.internal.AnimTaskStackRunner.1
        @Override // java.lang.ThreadLocal
        @Nullable
        public List<UpdateInfo> initialValue() {
            return new ArrayList();
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:106:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x031c A[Catch: Exception -> 0x02bd, TRY_LEAVE, TryCatch #11 {Exception -> 0x02bd, blocks: (B:131:0x02a3, B:133:0x02aa, B:135:0x02b6, B:138:0x02c2, B:140:0x02c8, B:142:0x02ce, B:144:0x02d4, B:150:0x0315, B:152:0x031c), top: B:203:0x02a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x024a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void doAnimationFrame(miuix.animation.internal.AnimTask r30, long r31, long r33, int r35, double r36, boolean r38) {
        /*
            Method dump skipped, instruction units count: 1005
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.animation.internal.AnimTaskStackRunner.doAnimationFrame(miuix.animation.internal.AnimTask, long, long, int, double, boolean):void");
    }

    private static void finishProperty(AnimStats animStats, AnimData animData) {
        animData.setOp((byte) 5);
        animStats.failCount++;
    }

    private static int initTask(IAnimTarget iAnimTarget, AnimData animData, long j2, long j3) {
        if ((animData.property instanceof ViewPropertyExt.ForegroundProperty) && !ForegroundColorStyle.isValid(iAnimTarget, animData)) {
            animData.value = animData.targetValue;
            animData.progress = 1.0d;
            return INIT_RESULT_CODE_FAILED;
        }
        if (!setValues(animData)) {
            return INIT_RESULT_CODE_FAILED;
        }
        if (isValueInvalid(animData)) {
            animData.reset();
            animData.value = animData.startValue;
            return INIT_RESULT_CODE_VALUE_INVALID;
        }
        animData.startTime = j2 - j3;
        animData.frameCount = 0;
        animData.setOp((byte) 2);
        return INIT_RESULT_CODE_SUCCESS;
    }

    private static boolean isValueInvalid(AnimData animData) {
        return animData.startValue == animData.targetValue && Math.abs(animData.velocity) < 16.66666603088379d;
    }

    private static void printDelayTaskLog(AnimData animData, Object obj, long j2) {
        LogUtils.logThread(CommonUtils.TAG, "++++++ data.start:check delay", "tag=" + obj + "@" + obj.hashCode(), String.format("p='%s'", animData.property.getName()), "delay=" + animData.delay, "op=" + ((int) animData.op), "initTime=" + animData.initTime, "totalT_ms=" + ((j2 * 1.0d) / 1000000.0d));
    }

    private static void printSetValueFailedLog(AnimData animData, Object obj) {
        LogUtils.logThread(CommonUtils.TAG, "++++++ data.start:setValueFailed, break", String.format("p='%s'", animData.property.getName()), "tag=" + obj + "@" + obj.hashCode(), "op=" + ((int) animData.op), "value=" + animData.value, "start-v=" + animData.startValue, "target-v= " + animData.targetValue);
    }

    private static void printSetupInUpdateLog(AnimData animData, IAnimTarget iAnimTarget, Object obj) {
        LogUtils.logThread(CommonUtils.TAG, "++++++ data.setup when op is update and no delay: " + String.format("p='%s'", animData.property.getName()), "tag=" + obj + "@" + obj.hashCode(), "value=" + animData.value, "start-v=" + animData.startValue, "target-v=" + animData.targetValue, "target=" + iAnimTarget, "ease=" + animData.ease, "progress=" + animData.progress, "velocity=" + animData.velocity, "delay=" + animData.delay, "op=" + ((int) animData.op));
    }

    private static void printSetupLog(AnimData animData, IAnimTarget iAnimTarget, TransitionInfo transitionInfo, AnimStats animStats) {
        LogUtils.logThread(CommonUtils.TAG, "++++++ data.setup: info.id=" + transitionInfo.id, String.format("p='%s'", animData.property.getName()), "tag=" + transitionInfo.key + "@" + transitionInfo.key.hashCode(), "value=" + animData.value, "start-v=" + animData.startValue, "target-v=" + animData.targetValue, "progress=" + animData.progress, "animStats=" + animStats, "ease=" + animData.ease, "velocity=" + animData.velocity, "delay=" + animData.delay, "op=" + ((int) animData.op), "target=" + iAnimTarget);
    }

    private static void printStartFinishLog(AnimData animData, IAnimTarget iAnimTarget, Object obj) {
        LogUtils.logThread(CommonUtils.TAG, "++++++ data.start:finish", String.format("p='%s'", animData.property.getName()), "tag=" + obj + "@" + obj.hashCode(), "op=" + ((int) animData.op), "value=" + animData.value, "start-v=" + animData.startValue, "target-v=" + animData.targetValue, "progress=" + animData.progress, "ease=" + animData.ease, "delay=" + animData.delay, "velocity=" + animData.velocity, "target=" + iAnimTarget);
    }

    private static void printUpdateAnimLog(AnimData animData, IAnimTarget iAnimTarget, TransitionInfo transitionInfo, double d2) {
        LogUtils.logThread(CommonUtils.TAG, "------ data.update: info.id=" + transitionInfo.id, String.format("p='%s'", animData.property.getName()), "tag=" + transitionInfo.key + "@" + transitionInfo.key.hashCode(), "op=" + ((int) animData.op), "frame=" + animData.frameCount, "value=" + animData.value, "start-v=" + animData.startValue, "target-v=" + animData.targetValue, "value_hex=" + Integer.toHexString((int) animData.value), "delta_s=" + d2, "interval=" + animData.frameInterval, "progress=" + animData.progress, "target=" + iAnimTarget, "justEnd=" + animData.justEnd, "init-t=" + animData.initTime, "start-t=" + animData.startTime, "velocity=" + animData.velocity);
    }

    private static void printValueInvalidFailedLog(AnimData animData, Object obj) {
        LogUtils.logThread(CommonUtils.TAG, "++++++ data.start:valueInvalidFailedLog, start-v equal target-v, so break", String.format("p='%s'", animData.property.getName()), "tag=" + obj + "@" + obj.hashCode(), "op=" + ((int) animData.op), "value=" + animData.value, "start-v=" + animData.startValue, "target-v=" + animData.targetValue, "velocity=" + animData.velocity);
    }

    public static void reuse(AnimStats animStats, AnimData animData, IAnimTarget iAnimTarget, AnimConfig animConfig, AnimSpecialConfig animSpecialConfig, long j2, long j3) {
        if (AnimValueUtils.isInvalid(animData.startValue)) {
            animData.startValue = AnimValueUtils.getValue(iAnimTarget, animData.property, animData.startValue);
        }
        animData.initTime = j2 - j3;
        animData.setOp((byte) 1);
        int i2 = animStats.failCount;
        if (i2 > 0) {
            animStats.failCount = i2 - 1;
        }
        if (animStats.focusEndCount > 0 && animConfig.isFocusPropertyForComplete(animData.property)) {
            animStats.focusEndCount--;
        }
        float fromSpeed = AnimConfigUtils.getFromSpeed(animConfig, animSpecialConfig);
        if (fromSpeed != Float.MAX_VALUE) {
            animData.velocity = fromSpeed;
        }
    }

    private static void setStartData(AnimData animData) {
        animData.progress = 0.0d;
        animData.reset();
    }

    private static boolean setValues(AnimData animData) {
        if (AnimValueUtils.isValid(animData.value)) {
            if (AnimValueUtils.isInvalid(animData.startValue)) {
                animData.startValue = animData.value;
            }
            return true;
        }
        if (!AnimValueUtils.isValid(animData.startValue)) {
            return false;
        }
        animData.value = animData.startValue;
        return true;
    }

    public static void setup(AnimStats animStats, AnimData animData, IAnimTarget iAnimTarget, AnimConfig animConfig, AnimSpecialConfig animSpecialConfig, long j2, long j3, Object obj) {
        double d2 = animData.startValue;
        if (d2 == Double.MAX_VALUE || d2 == 3.4028234663852886E38d || d2 == 2.147483647E9d) {
            animData.startValue = AnimValueUtils.getValue(iAnimTarget, animData.property, d2);
        }
        long j4 = j2 - j3;
        animData.initTime = j4;
        animStats.startedCount++;
        if (animData.op != 2 || animData.delay > 0) {
            animData.setOp((byte) 1);
            float fromSpeed = AnimConfigUtils.getFromSpeed(animConfig, animSpecialConfig);
            if (fromSpeed != Float.MAX_VALUE) {
                animData.velocity = fromSpeed;
            }
            if (animData.logEnabled) {
                LogUtils.logThread(CommonUtils.TAG, "++++++ data.setup path0");
                return;
            }
            return;
        }
        animData.startTime = j4;
        animData.delay = 0L;
        float fromSpeed2 = AnimConfigUtils.getFromSpeed(animConfig, animSpecialConfig);
        if (fromSpeed2 != Float.MAX_VALUE) {
            animData.velocity = fromSpeed2;
        }
        animStats.prepareCount--;
        setStartData(animData);
        if (animData.logEnabled) {
            LogUtils.logThread(CommonUtils.TAG, "++++++ data.setup path1");
            printSetupInUpdateLog(animData, iAnimTarget, obj);
        }
    }

    public static void start(AnimStats animStats, AnimData animData, IAnimTarget iAnimTarget, long j2, long j3, TransitionInfo transitionInfo) {
        if (animData.delay > 0) {
            if (animData.logEnabled) {
                printDelayTaskLog(animData, transitionInfo.key, j2);
            }
            if (transitionInfo.currentTime < transitionInfo.startTime + (animData.delay * FolmeCore.NANOS_TO_MS)) {
                return;
            }
            double value = AnimValueUtils.getValue(iAnimTarget, animData.property, Double.MAX_VALUE);
            if (value != Double.MAX_VALUE) {
                animData.startValue = value;
            }
            if (animData.logEnabled) {
                LogUtils.logThread(CommonUtils.TAG, "+++++ data.delay-start: time's up", "info.id=" + transitionInfo.id, String.format("p='%s'", animData.property.getName()));
            }
        }
        animStats.prepareCount--;
        int iInitTask = initTask(iAnimTarget, animData, j2, j3);
        if (iInitTask == INIT_RESULT_CODE_SUCCESS) {
            setStartData(animData);
            if (animData.logEnabled) {
                printStartFinishLog(animData, iAnimTarget, transitionInfo.key);
                return;
            }
            return;
        }
        finishProperty(animStats, animData);
        if (animData.logEnabled) {
            if (iInitTask == INIT_RESULT_CODE_FAILED) {
                printSetValueFailedLog(animData, transitionInfo.key);
            } else if (iInitTask == INIT_RESULT_CODE_VALUE_INVALID) {
                printValueInvalidFailedLog(animData, transitionInfo.key);
            }
        }
    }

    private static void update(AnimStats animStats, AnimData animData, IAnimTarget iAnimTarget, long j2, long j3, double d2, int i2, TransitionInfo transitionInfo) {
        double d3;
        if (animData.velocity == 0.0d && animData.justStart) {
            d3 = 0.0d;
        } else {
            animData.frameCount++;
            d3 = d2;
        }
        animStats.updateCount++;
        animData.frameInterval = d3;
        animData.duration += d3;
        FloatProperty floatProperty = animData.property;
        if (floatProperty == ViewPropertyExt.FOREGROUND || floatProperty == ViewPropertyExt.BACKGROUND || (floatProperty instanceof ColorProperty)) {
            FolmeCore.doAnimationFrame(iAnimTarget, true, animData, j2, d3, i2);
        } else {
            FolmeCore.doAnimationFrame(iAnimTarget, false, animData, j2, d3, i2);
            if (animData.logEnabled) {
                LogUtils.logThread(CommonUtils.TAG, "------ data.update doAnimationFrame: info.id=" + transitionInfo.id, String.format("p='%s'", animData.property.getName()), "value=" + animData.value, "velocity=" + animData.velocity);
            }
        }
        if (animData.justStart) {
            animData.justStart = false;
        }
        if (animData.op == 3) {
            animData.justEnd = true;
            animStats.endCount++;
        }
        if (animData.logEnabled) {
            printUpdateAnimLog(animData, iAnimTarget, transitionInfo, d3);
        }
    }
}
