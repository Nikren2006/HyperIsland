package miuix.animation.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaomi.onetrack.c.y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import miuix.animation.IAnimTarget;
import miuix.animation.ValueTarget;
import miuix.animation.base.AnimConfig;
import miuix.animation.base.AnimConfigLink;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ColorProperty;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.LinkNode;
import miuix.animation.utils.LogUtils;

/* JADX INFO: loaded from: classes4.dex */
class TransitionInfo extends LinkNode<TransitionInfo> implements DesignReview {
    public static final byte CANCEL = 3;
    public static final byte END = 4;
    public static final byte INIT = -1;
    public static final byte INVALID = -2;
    public static final byte PREPARE = 0;
    public static final byte SETUP = 1;
    public static final byte START = 2;
    private static final AtomicInteger sIdGenerator = new AtomicInteger();
    public List<AnimTask> animTasks;
    public volatile AnimConfig config;
    public long currentTime;
    public int frameCount;

    @Nullable
    public volatile AnimState from;
    public boolean hasOnStart;
    public boolean hasSendNotifyStart;
    public final int id;
    public volatile Object key;
    public Set<TransitionListener> listenerSetForNotify;
    private final AnimStats mInfoAnimStats;
    public long startTime;
    public byte state;
    public final Object tag;
    public final IAnimTarget target;

    @NonNull
    public volatile AnimState to;
    public List<UpdateInfo> updateList;
    public List<UpdateInfo> updateListForNotify;
    public Map<String, UpdateInfo> updateMap;

    public interface IUpdateInfoCreator {
        @NonNull
        UpdateInfo getUpdateInfo(FloatProperty floatProperty);
    }

    public TransitionInfo(IAnimTarget iAnimTarget, @Nullable AnimState animState, @NonNull AnimState animState2, AnimConfigLink animConfigLink) {
        int iIncrementAndGet = sIdGenerator.incrementAndGet();
        this.id = iIncrementAndGet;
        this.config = new AnimConfig();
        this.state = (byte) -1;
        this.updateMap = new HashMap();
        this.updateListForNotify = new ArrayList();
        this.listenerSetForNotify = new HashSet();
        this.animTasks = new ArrayList();
        this.mInfoAnimStats = new AnimStats();
        this.target = iAnimTarget;
        this.from = getState(animState);
        this.to = getState(animState2);
        Object tag = this.to.getTag();
        this.tag = tag;
        if (animState2.needDuplicate) {
            this.key = tag + String.valueOf(iIncrementAndGet);
        } else {
            this.key = tag;
        }
        this.updateList = null;
        initValueForColorProperty();
        this.config.copy(animState2.getConfig());
        if (animConfigLink != null) {
            animConfigLink.addTo(this.config);
        }
        iAnimTarget.getNotifier().addListeners(Integer.valueOf(iIncrementAndGet), this.config);
    }

    public static void decreasePrepareCountForDelayAnim(AnimStats animStats, AnimStats animStats2, UpdateInfo updateInfo, byte b2) {
        int i2;
        if (b2 != 1 || updateInfo.animInfo.delay <= 0 || (i2 = animStats.prepareCount) <= 0) {
            return;
        }
        animStats.prepareCount = i2 - 1;
        animStats2.prepareCount--;
    }

    private AnimState getState(AnimState animState) {
        if (animState == null || !animState.needDuplicate) {
            return animState;
        }
        AnimState animState2 = new AnimState();
        animState2.set(animState);
        return animState2;
    }

    private void initValueForColorProperty() {
        if (this.from == null) {
            return;
        }
        Iterator<Object> it = this.to.keySet().iterator();
        while (it.hasNext()) {
            FloatProperty tempProperty = this.to.getTempProperty(it.next());
            if ((tempProperty instanceof ColorProperty) && !AnimValueUtils.isValid(AnimValueUtils.getValueOfTarget(this.target, tempProperty, Double.MAX_VALUE))) {
                double d2 = this.from.get(this.target, tempProperty);
                if (AnimValueUtils.isValid(d2)) {
                    this.target.setIntValue((ColorProperty) tempProperty, (int) d2);
                }
            }
        }
    }

    public static void processInitValue(IAnimTarget iAnimTarget, AnimState animState, FloatProperty floatProperty, UpdateInfo updateInfo, boolean z2, boolean z3) {
        double init = animState.getInit(iAnimTarget, floatProperty);
        if (z3) {
            LogUtils.debug(" |---- processInitValue initValue=" + init + " property.name=" + floatProperty.getName() + " isCurValueValid=" + z2, new Object[0]);
        }
        if (AnimValueUtils.isValid(init)) {
            if (!z2) {
                updateInfo.animInfo.startValue = init;
                if (z3) {
                    LogUtils.debug(" |---- processInitValue set startValue with init op=" + ((int) updateInfo.animInfo.op) + " start-v=init-v=" + init + " value=" + updateInfo.animInfo.value, new Object[0]);
                    return;
                }
                return;
            }
            if (z3) {
                LogUtils.debug(" |---- processInitValue target.isIdle()=" + iAnimTarget.isIdle() + " target.isAnimRunning()=" + iAnimTarget.isAnimRunning(new FloatProperty[0]) + " target.isValidFlag()=" + iAnimTarget.isValidFlag(), new Object[0]);
            }
            if (iAnimTarget.animManager.isRunningAnimStateContainsProperty(updateInfo.property)) {
                return;
            }
            AnimInfo animInfo = updateInfo.animInfo;
            animInfo.startValue = init;
            animInfo.value = init;
            if (z3) {
                LogUtils.debug(" |---- processInitValue force set startValue / value with init when this property is not running, op=" + ((int) updateInfo.animInfo.op) + " start-v=init-v=" + init + " value=" + updateInfo.animInfo.value, new Object[0]);
            }
        }
    }

    public static void setupAllInfoToTarget(TransitionInfo transitionInfo) {
        IAnimTarget iAnimTarget = transitionInfo.target;
        do {
            iAnimTarget.animManager.setupTransition(transitionInfo);
            transitionInfo = transitionInfo.remove();
        } while (transitionInfo != null);
    }

    public static void tickOnFrame(TransitionInfo transitionInfo, long j2) {
        if (transitionInfo.frameCount != 0 || transitionInfo.config.startImmediately) {
            transitionInfo.currentTime += j2;
        }
        transitionInfo.frameCount++;
    }

    public boolean containsProperty(FloatProperty floatProperty) {
        return this.to.contains(floatProperty);
    }

    public boolean containsPropertyInUpdateList(FloatProperty floatProperty) {
        return this.updateMap.containsKey(floatProperty.getName());
    }

    public int getAnimCount() {
        return this.state >= 1 ? this.updateMap.size() : this.to.keySet().size();
    }

    @Override // miuix.animation.internal.DesignReview
    public String getDesignInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        if (this.from != null) {
            sb.append("\"");
            sb.append("fromState");
            sb.append("\": ");
            sb.append(this.from.getDesignInfo());
            sb.append(", ");
        }
        sb.append("\"");
        sb.append("toState");
        sb.append("\": ");
        sb.append(this.to.getDesignInfo());
        sb.append(", ");
        sb.append("\"");
        sb.append(y.f3234a);
        sb.append("\": ");
        sb.append(this.config.getDesignInfo());
        sb.append('}');
        return sb.toString();
    }

    public AnimStats getInfoAnimStats() {
        this.mInfoAnimStats.clear();
        Iterator<AnimTask> it = this.animTasks.iterator();
        while (it.hasNext()) {
            AnimStats.add(this.mInfoAnimStats, it.next().animStats);
        }
        return this.mInfoAnimStats;
    }

    public boolean hasUpdateInfo() {
        List<UpdateInfo> list = this.updateList;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public boolean initUpdateList(IUpdateInfoCreator iUpdateInfoCreator) {
        boolean z2;
        double d2;
        String str;
        boolean z3;
        Integer num;
        String str2;
        String str3;
        Iterator<Object> it;
        String str4;
        IUpdateInfoCreator iUpdateInfoCreator2 = iUpdateInfoCreator;
        long jNanoTime = System.nanoTime();
        this.startTime = jNanoTime;
        this.currentTime = jNanoTime;
        boolean z4 = false;
        this.frameCount = 0;
        AnimState animState = this.from;
        AnimState animState2 = this.to;
        boolean zIsLogMainEnabled = LogUtils.isLogMainEnabled();
        if (zIsLogMainEnabled) {
            LogUtils.debug("----- initUpdateList, id=" + this.id + ", key=" + this.key + "@" + this.key.hashCode() + ", start-t=" + this.startTime + ",\nf=" + animState + ",\nt=" + animState2 + ",\ntarget=" + this.target + ",\nconfig=" + this.config, new Object[0]);
        }
        ArrayList arrayList = new ArrayList();
        this.updateMap.clear();
        Iterator<Object> it2 = animState2.keySet().iterator();
        while (it2.hasNext()) {
            FloatProperty property = animState2.getProperty(this.target, it2.next());
            UpdateInfo updateInfo = iUpdateInfoCreator2.getUpdateInfo(property);
            boolean zHasFlags = CommonUtils.hasFlags(animState2.getConfigFlags(property), 8L);
            if (PredictTask.sCreator == iUpdateInfoCreator2 || ((num = updateInfo.preparedTransitionId) != null && num.intValue() == this.id)) {
                Iterator<Object> it3 = it2;
                boolean zHandleSetToValue = AnimValueUtils.handleSetToValue(updateInfo);
                if (zIsLogMainEnabled) {
                    StringBuilder sb = new StringBuilder();
                    z2 = zHasFlags;
                    sb.append("hasSetTo=");
                    sb.append(zHandleSetToValue);
                    LogUtils.debug(" |---- start get", "update name=" + updateInfo.property.getName(), "id=" + updateInfo.hashCode(), "needInit=" + zHasFlags, sb.toString(), " " + updateInfo);
                } else {
                    z2 = zHasFlags;
                }
                arrayList.add(updateInfo);
                this.updateMap.put(property.getName(), updateInfo);
                if (updateInfo.animInfo.op == 5) {
                    updateInfo.animInfo.reuse();
                    if (zIsLogMainEnabled) {
                        LogUtils.debug(" |---- reset", "update name=" + updateInfo.property.getName(), "id=" + updateInfo.hashCode(), " " + updateInfo);
                    }
                }
                updateInfo.animInfo.targetValue = animState2.get(this.target, property);
                if (animState != null) {
                    updateInfo.animInfo.startValue = animState.get(this.target, property);
                    str = " ";
                    z3 = zHandleSetToValue;
                } else {
                    double d3 = updateInfo.animInfo.startValue;
                    double valueOfTarget = AnimValueUtils.getValueOfTarget(this.target, property, d3);
                    boolean zIsValid = AnimValueUtils.isValid(valueOfTarget);
                    if (zIsValid) {
                        updateInfo.animInfo.startValue = valueOfTarget;
                    }
                    if (z2) {
                        d2 = valueOfTarget;
                        str = " ";
                        z3 = zHandleSetToValue;
                        processInitValue(this.target, animState2, property, updateInfo, zIsValid, zIsLogMainEnabled);
                    } else {
                        d2 = valueOfTarget;
                        str = " ";
                        z3 = zHandleSetToValue;
                    }
                    if (zIsLogMainEnabled) {
                        LogUtils.debug(" |---- f is null op=" + ((int) updateInfo.animInfo.op) + " start-v=" + d3 + " value=" + d2, new Object[0]);
                    }
                }
                if (updateInfo.animInfo.op == 5) {
                    AnimInfo animInfo = updateInfo.animInfo;
                    animInfo.value = animInfo.startValue;
                    if (zIsLogMainEnabled) {
                        LogUtils.debug(" |---- after reset value <= start-v=" + updateInfo.animInfo.startValue + str + updateInfo, new Object[0]);
                    }
                }
                updateInfo.preparedTransitionId = null;
                if (zIsLogMainEnabled) {
                    z4 = false;
                    LogUtils.debug(" |---- finish get " + z3 + ", op=" + ((int) updateInfo.animInfo.op), new Object[0]);
                } else {
                    z4 = false;
                }
                iUpdateInfoCreator2 = iUpdateInfoCreator;
                it2 = it3;
            } else {
                if (!zHasFlags || updateInfo.preparedTransitionId == null) {
                    str2 = "update name=";
                    str3 = "needInit=";
                    it = it2;
                    str4 = "id=";
                } else {
                    str2 = "update name=";
                    it = it2;
                    str4 = "id=";
                    str3 = "needInit=";
                    processInitValue(this.target, animState2, property, updateInfo, AnimValueUtils.isValid(AnimValueUtils.getValueOfTarget(this.target, property, updateInfo.animInfo.startValue)), zIsLogMainEnabled);
                }
                if (zIsLogMainEnabled) {
                    LogUtils.debug(" |---- init stop ", str2 + updateInfo.property.getName(), str4 + updateInfo.hashCode(), str3 + zHasFlags, "preparedTransitionId=" + updateInfo.preparedTransitionId, " continue");
                }
                iUpdateInfoCreator2 = iUpdateInfoCreator;
                it2 = it;
                z4 = false;
            }
        }
        if (arrayList.isEmpty()) {
            return z4;
        }
        refreshTasks(arrayList, true);
        return true;
    }

    public void refreshTasks(List<UpdateInfo> list, boolean z2) {
        boolean zIsLogMainEnabled = LogUtils.isLogMainEnabled();
        this.updateList = list;
        int size = list.size();
        int iMax = Math.max(1, size / 100);
        int iCeil = (int) Math.ceil(size / iMax);
        if (this.animTasks.size() < iMax) {
            for (int size2 = this.animTasks.size(); size2 < iMax; size2++) {
                this.animTasks.add(new AnimTask());
            }
        } else {
            List<AnimTask> list2 = this.animTasks;
            list2.subList(iMax, list2.size()).clear();
        }
        int i2 = 0;
        for (AnimTask animTask : this.animTasks) {
            animTask.info = this;
            int i3 = i2 + iCeil > size ? size - i2 : iCeil;
            int i4 = 0;
            if (this.config.getFocusPropertyCount() > 0) {
                for (int i5 = i2; i5 < i2 + i3; i5++) {
                    if (this.config.isFocusPropertyForComplete(this.updateList.get(i5).property)) {
                        i4++;
                    }
                }
            }
            if (zIsLogMainEnabled) {
                LogUtils.debug(" |---- refreshTasks startPos=" + i2 + " amount=" + i3 + " config.focusCount=" + this.config.getFocusPropertyCount() + " focusCount=" + i4, new Object[0]);
            }
            animTask.setup(i2, i3, i4);
            if (z2) {
                animTask.animStats.prepareCount = i3;
            } else {
                animTask.updateAnimStats();
            }
            i2 += i3;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransInfo{id=");
        sb.append(this.id);
        sb.append(", key=");
        sb.append(this.key);
        sb.append("@");
        sb.append(this.key.hashCode());
        sb.append(", state=");
        sb.append((int) this.state);
        sb.append(", propSize=");
        sb.append(this.to.keySet().size());
        sb.append(", delay=");
        sb.append(this.config.delay);
        sb.append(", start-t=");
        sb.append(this.startTime);
        sb.append(", target=");
        IAnimTarget iAnimTarget = this.target;
        boolean z2 = iAnimTarget instanceof ValueTarget;
        Object targetObject = iAnimTarget;
        if (z2) {
            targetObject = iAnimTarget.getTargetObject();
        }
        sb.append(targetObject);
        sb.append(", next=");
        sb.append(this.next);
        sb.append('}');
        return sb.toString();
    }
}
