package miuix.animation.base;

import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import miuix.animation.FolmeEase;
import miuix.animation.internal.DesignReview;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.EaseManager;
import miuix.animation.utils.LogUtils;

/* JADX INFO: loaded from: classes4.dex */
public class AnimConfig implements DesignReview {
    public static final long FLAG_AUTO_INIT = 8;
    public static final long FLAG_DELTA = 1;
    public static final long FLAG_INIT = 2;
    public static final long FLAG_INT = 4;
    public static final int TINT_ALPHA = 0;
    public static final int TINT_AUTO = -1;
    public static final int TINT_OPAQUE = 1;
    public static final int TINT_REGION_USER_DEFINED = 3;
    public static final EaseManager.EaseStyle sDefEase = FolmeEase.spring(0.95f, 0.35f);
    public long delay;
    public EaseManager.EaseStyle ease;
    public long flags;
    public float fromSpeed;
    public final HashSet<TransitionListener> listeners;

    @Nullable
    private Set<String> mFocusPropertyNames;
    private Looper mObserverLooper;
    private final Map<String, AnimSpecialConfig> mSpecialNameMap;

    @Deprecated
    public long minDuration;
    public boolean startImmediately;
    public Object tag;
    public int tintMode;

    public AnimConfig() {
        this(false);
    }

    public void addFocusPropertyForComplete(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        if (this.mFocusPropertyNames == null) {
            this.mFocusPropertyNames = new HashSet();
        }
        this.mFocusPropertyNames.addAll(Arrays.asList(strArr));
    }

    public AnimConfig addListeners(TransitionListener... transitionListenerArr) {
        Collections.addAll(this.listeners, transitionListenerArr);
        return this;
    }

    public void addSpecialConfigs(AnimConfig animConfig) {
        Map<String, AnimSpecialConfig> map = animConfig.mSpecialNameMap;
        if (map != null) {
            this.mSpecialNameMap.putAll(map);
        }
    }

    public void clear() {
        this.delay = 0L;
        this.ease = null;
        this.listeners.clear();
        this.tag = null;
        this.flags = 0L;
        this.fromSpeed = Float.MAX_VALUE;
        this.startImmediately = true;
        this.minDuration = 0L;
        this.tintMode = -1;
        this.mFocusPropertyNames = null;
        Map<String, AnimSpecialConfig> map = this.mSpecialNameMap;
        if (map != null) {
            map.clear();
        }
    }

    public void clearFocusPropertyForComplete() {
        this.mFocusPropertyNames = null;
    }

    public void copy(AnimConfig animConfig) {
        if (animConfig == null || animConfig == this) {
            return;
        }
        this.delay = animConfig.delay;
        this.ease = animConfig.ease;
        this.listeners.addAll(animConfig.listeners);
        this.tag = animConfig.tag;
        this.flags = animConfig.flags;
        this.fromSpeed = animConfig.fromSpeed;
        this.startImmediately = animConfig.startImmediately;
        this.minDuration = animConfig.minDuration;
        this.tintMode = animConfig.tintMode;
        addFocusPropertyForComplete(animConfig);
        Map<String, AnimSpecialConfig> map = this.mSpecialNameMap;
        if (map != null) {
            map.clear();
            this.mSpecialNameMap.putAll(animConfig.mSpecialNameMap);
        }
    }

    public AnimConfig enableStartImmediately(boolean z2) {
        this.startImmediately = z2;
        return this;
    }

    @Override // miuix.animation.internal.DesignReview
    public String getDesignInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"");
        sb.append("ease");
        sb.append("\": ");
        EaseManager.EaseStyle easeStyle = this.ease;
        ArrayList arrayList = null;
        sb.append(easeStyle != null ? easeStyle.getDesignInfo() : null);
        if (this.delay > 0) {
            sb.append(", ");
            sb.append("\"");
            sb.append("delay");
            sb.append("\": ");
            sb.append(this.delay);
        }
        if (!this.mSpecialNameMap.isEmpty()) {
            for (String str : this.mSpecialNameMap.keySet()) {
                if (arrayList == null) {
                    arrayList = new ArrayList(this.mSpecialNameMap.size());
                }
                AnimSpecialConfig animSpecialConfig = this.mSpecialNameMap.get(str);
                if (animSpecialConfig != null && animSpecialConfig.ease != null) {
                    arrayList.add("\"" + str + "\":" + animSpecialConfig.getDesignInfo());
                }
            }
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            sb.append(", ");
            sb.append("\"");
            sb.append("special");
            sb.append("\": ");
            sb.append('{');
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                sb.append((String) arrayList.get(i2));
                if (i2 == arrayList.size() - 1) {
                    break;
                }
                sb.append(", ");
            }
            sb.append("}");
        }
        sb.append("}");
        return sb.toString();
    }

    public int getFocusPropertyCount() {
        Set<String> set = this.mFocusPropertyNames;
        if (set == null) {
            return 0;
        }
        return set.size();
    }

    public Looper getObserverLooper() {
        return this.mObserverLooper;
    }

    public AnimSpecialConfig getSpecialConfig(FloatProperty floatProperty) {
        return queryAndCreateSpecial(floatProperty, false);
    }

    public Set<String> getSpecialSet() {
        return this.mSpecialNameMap.keySet();
    }

    public boolean isFocusPropertyForComplete(@NonNull FloatProperty floatProperty) {
        Set<String> set = this.mFocusPropertyNames;
        if (set == null) {
            return false;
        }
        return set.contains(floatProperty.getName());
    }

    public AnimSpecialConfig queryAndCreateSpecial(FloatProperty floatProperty) {
        return queryAndCreateSpecial(floatProperty, true);
    }

    public AnimConfig removeListeners(TransitionListener... transitionListenerArr) {
        if (transitionListenerArr.length == 0) {
            this.listeners.clear();
        } else {
            this.listeners.removeAll(Arrays.asList(transitionListenerArr));
        }
        return this;
    }

    public AnimConfig setDelay(long j2) {
        this.delay = j2;
        return this;
    }

    public AnimConfig setEase(EaseManager.EaseStyle easeStyle) {
        this.ease = easeStyle;
        return this;
    }

    public AnimConfig setFromSpeed(float f2) {
        this.fromSpeed = f2;
        return this;
    }

    public AnimConfig setMinDuration(long j2) {
        this.minDuration = j2;
        return this;
    }

    public void setObserverLooper(@Nullable Looper looper) {
        this.mObserverLooper = looper;
    }

    public AnimConfig setSpecial(String str, long j2, float... fArr) {
        return setSpecial(str, (EaseManager.EaseStyle) null, j2, fArr);
    }

    public AnimConfig setTag(Object obj) {
        this.tag = obj;
        return this;
    }

    public AnimConfig setTintMode(int i2) {
        this.tintMode = i2;
        return this;
    }

    public String toString() {
        return "AnimConfig@" + hashCode() + "{\n\t\tdelay=" + this.delay + ", minDuration=" + this.minDuration + ", ease=" + this.ease + ",\n\t\tfromSpeed=" + this.fromSpeed + ", startImmediately=" + this.startImmediately + ", tintMode=" + this.tintMode + ", tag=" + this.tag + ",\n\t\tflags=" + this.flags + ", listeners=" + this.listeners + ", focusP=" + ((Object) CommonUtils.setToString(this.mFocusPropertyNames)) + ",\n\t\tspecialNameMap=" + ((Object) CommonUtils.mapToString(this.mSpecialNameMap, "    ")) + "\n\t}";
    }

    public AnimConfig(boolean z2) {
        this.fromSpeed = Float.MAX_VALUE;
        this.startImmediately = true;
        this.mFocusPropertyNames = null;
        this.mObserverLooper = null;
        this.tintMode = -1;
        if (z2) {
            this.mSpecialNameMap = null;
            this.listeners = null;
        } else {
            this.mSpecialNameMap = new HashMap();
            this.listeners = new HashSet<>();
        }
    }

    public AnimSpecialConfig getSpecialConfig(String str) {
        return queryAndCreateSpecial(str, false);
    }

    public AnimSpecialConfig queryAndCreateSpecial(String str) {
        return queryAndCreateSpecial(str, true);
    }

    @Deprecated
    public AnimConfig setEase(int i2, float... fArr) {
        EaseManager.EaseStyle style = EaseManager.getStyle(i2, fArr);
        this.ease = style;
        if ((style instanceof EaseManager.DurationMotionEaseStyle) && (fArr == null || fArr.length == 0)) {
            style.setFactors(300.0d);
            LogUtils.logThread(CommonUtils.TAG, "Folme use warning!! You can't setEase " + FolmeEase.getStyleName(i2) + " by style without factors, trace:" + Log.getStackTraceString(new Throwable()));
        }
        return this;
    }

    public AnimConfig setSpecial(String str, EaseManager.EaseStyle easeStyle, float... fArr) {
        return setSpecial(str, easeStyle, 0L, fArr);
    }

    private AnimSpecialConfig queryAndCreateSpecial(@Nullable FloatProperty floatProperty, boolean z2) {
        if (floatProperty == null) {
            return null;
        }
        return queryAndCreateSpecial(floatProperty.getName(), z2);
    }

    public AnimConfig setSpecial(String str, EaseManager.EaseStyle easeStyle, long j2, float... fArr) {
        setSpecial(queryAndCreateSpecial(str, true), easeStyle, j2, fArr);
        return this;
    }

    private AnimSpecialConfig queryAndCreateSpecial(String str, boolean z2) {
        AnimSpecialConfig animSpecialConfig = this.mSpecialNameMap.get(str);
        if (animSpecialConfig != null || !z2) {
            return animSpecialConfig;
        }
        AnimSpecialConfig animSpecialConfig2 = new AnimSpecialConfig();
        this.mSpecialNameMap.put(str, animSpecialConfig2);
        return animSpecialConfig2;
    }

    public void addFocusPropertyForComplete(FloatProperty... floatPropertyArr) {
        if (floatPropertyArr == null || floatPropertyArr.length <= 0) {
            return;
        }
        if (this.mFocusPropertyNames == null) {
            this.mFocusPropertyNames = new HashSet();
        }
        for (FloatProperty floatProperty : floatPropertyArr) {
            this.mFocusPropertyNames.add(floatProperty.getName());
        }
    }

    public AnimConfig setSpecial(FloatProperty floatProperty, long j2, float... fArr) {
        return setSpecial(floatProperty, (EaseManager.EaseStyle) null, j2, fArr);
    }

    public AnimConfig setSpecial(FloatProperty floatProperty, EaseManager.EaseStyle easeStyle, float... fArr) {
        setSpecial(floatProperty, easeStyle, -1L, fArr);
        return this;
    }

    public AnimConfig setSpecial(FloatProperty floatProperty, EaseManager.EaseStyle easeStyle, long j2, float... fArr) {
        setSpecial(queryAndCreateSpecial(floatProperty, true), easeStyle, j2, fArr);
        return this;
    }

    public void setSpecial(AnimSpecialConfig animSpecialConfig, EaseManager.EaseStyle easeStyle, long j2, float... fArr) {
        if (easeStyle != null) {
            animSpecialConfig.setEase(easeStyle);
        }
        if (j2 > 0) {
            animSpecialConfig.setDelay(j2);
        }
        if (fArr.length > 0) {
            animSpecialConfig.setFromSpeed(fArr[0]);
        }
    }

    public void addFocusPropertyForComplete(AnimConfig animConfig) {
        if (animConfig.mFocusPropertyNames != null) {
            if (this.mFocusPropertyNames == null) {
                this.mFocusPropertyNames = new HashSet();
            }
            this.mFocusPropertyNames.addAll(animConfig.mFocusPropertyNames);
        }
    }

    public AnimConfig(AnimConfig animConfig) {
        this(false);
        copy(animConfig);
    }

    public AnimConfig setSpecial(FloatProperty floatProperty, AnimSpecialConfig animSpecialConfig) {
        if (animSpecialConfig != null) {
            this.mSpecialNameMap.put(floatProperty.getName(), animSpecialConfig);
        } else {
            this.mSpecialNameMap.remove(floatProperty.getName());
        }
        return this;
    }

    public AnimConfig setSpecial(String str, AnimSpecialConfig animSpecialConfig) {
        if (animSpecialConfig != null) {
            this.mSpecialNameMap.put(str, animSpecialConfig);
        } else {
            this.mSpecialNameMap.remove(str);
        }
        return this;
    }
}
