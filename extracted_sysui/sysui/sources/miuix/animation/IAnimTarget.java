package miuix.animation;

import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import miuix.animation.base.AnimConfigLink;
import miuix.animation.controller.AnimState;
import miuix.animation.internal.AnimManager;
import miuix.animation.internal.NotifyManager;
import miuix.animation.internal.TargetHandler;
import miuix.animation.internal.TargetVelocityTracker;
import miuix.animation.listener.ListenerNotifier;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IIntValueProperty;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.LogUtils;

/* JADX INFO: loaded from: classes4.dex */
public abstract class IAnimTarget<T> {
    public static final long FLAT_ONESHOT = 1;
    static final AtomicInteger sTargetIds = new AtomicInteger(Integer.MAX_VALUE);
    public final AnimManager animManager;

    @Nullable
    protected TargetHandler handler;
    public final int id;
    float mDefaultMinVisible;
    long mFlags;
    long mFlagsSetTime;
    boolean mIsSleep;
    Map<Object, Float> mMinVisibleChanges;
    boolean mShouldCheckValue;
    final TargetVelocityTracker mTracker;
    NotifyManager notifyManager;

    public IAnimTarget(@Nullable Looper looper) {
        AnimManager animManager = new AnimManager();
        this.animManager = animManager;
        this.notifyManager = new NotifyManager(this);
        this.mDefaultMinVisible = Float.MAX_VALUE;
        this.mMinVisibleChanges = new ConcurrentHashMap();
        this.mShouldCheckValue = true;
        this.id = sTargetIds.decrementAndGet();
        this.mTracker = new TargetVelocityTracker();
        this.handler = createHandler(looper);
        if (LogUtils.isLogMoreEnable()) {
            LogUtils.debug("IAnimTarget create with looper! " + looper, new Object[0]);
        }
        animManager.setTarget(this);
    }

    public boolean allowAnimRun() {
        return true;
    }

    public void awake() {
        this.mIsSleep = false;
    }

    public boolean canClear() {
        return !isValid() || (!this.animManager.hasAnimSetup() && hasFlags(1L) && !isAnimRunning(new FloatProperty[0]) && isValidFlag());
    }

    public boolean canClearInvalid() {
        return !isValid() && isIdle();
    }

    public void cancelRunningAnim() {
        this.animManager.cancel();
    }

    public abstract void clean();

    public TargetHandler createHandler(Looper looper) {
        if (looper == null) {
            Log.w(CommonUtils.TAG, "warning!! the AnimTarget has created in a thread without Looper, the animation will do not work!!you should use HandlerThread instead of Thread, trace:" + Log.getStackTraceString(new Throwable()));
            return null;
        }
        if (Folme.getUiLooperByTid(looper.getThread().getId()) == null) {
            if (LogUtils.isLogDetailEnable()) {
                LogUtils.debug("IAnimTarget.createHandler registerUiLooper " + looper + " tid " + looper.getThread().getId(), new Object[0]);
            }
            Folme.registerUiLooper(looper);
        }
        return new TargetHandler(looper, this);
    }

    public void doSetIntValue(IIntValueProperty iIntValueProperty, int i2) {
        T targetObject = getTargetObject();
        if (targetObject == null || Math.abs(i2) == Integer.MAX_VALUE) {
            return;
        }
        iIntValueProperty.setIntValue(targetObject, i2);
    }

    public void doSetValue(FloatProperty floatProperty, float f2) {
        T targetObject = getTargetObject();
        if (targetObject == null || Math.abs(f2) == Float.MAX_VALUE || Float.isNaN(f2) || Float.isInfinite(f2)) {
            return;
        }
        floatProperty.setValue(targetObject, f2);
    }

    public void enableCheckValue(boolean z2) {
        this.mShouldCheckValue = z2;
    }

    public void executeOnInitialized(Runnable runnable) {
        post(runnable);
    }

    public void finalize() throws Throwable {
        if (LogUtils.isLogMoreEnable()) {
            LogUtils.debug("IAnimTarget was destroyed！" + this, new Object[0]);
        }
        super.finalize();
    }

    public float getDefaultMinVisible() {
        return 1.0f;
    }

    public abstract double getDoubleValue(@NonNull FloatProperty floatProperty);

    @Nullable
    public TargetHandler getHandler() {
        if (this.handler == null) {
            this.handler = createHandler(Looper.myLooper());
        }
        return this.handler;
    }

    public int getId() {
        return this.id;
    }

    public int getIntValue(IIntValueProperty iIntValueProperty) {
        T targetObject = getTargetObject();
        if (targetObject != null) {
            return iIntValueProperty.getIntValue(targetObject);
        }
        return Integer.MAX_VALUE;
    }

    public void getLocationOnScreen(int[] iArr) {
        iArr[1] = 0;
        iArr[0] = 0;
    }

    public float getMinVisibleChange(FloatProperty floatProperty) {
        return !this.mMinVisibleChanges.containsKey(floatProperty) ? getMinVisibleChange(floatProperty.getName()) : getMinVisibleChange((Object) floatProperty);
    }

    public ListenerNotifier getNotifier() {
        return this.notifyManager.getNotifier();
    }

    public abstract T getTargetObject();

    public double getThresholdVelocity(@NonNull FloatProperty floatProperty) {
        return floatProperty.getMinVisibleChange() * 0.75f * 8.333333f;
    }

    @NonNull
    public final UpdateInfo getUpdateInfo(FloatProperty floatProperty) {
        return this.animManager.getUpdateInfo(floatProperty);
    }

    public float getValue(FloatProperty floatProperty) {
        T targetObject = getTargetObject();
        if (targetObject != null) {
            return floatProperty.getValue(targetObject);
        }
        return Float.MAX_VALUE;
    }

    public double getVelocity(String str) {
        return 0.0d;
    }

    public boolean hasFlags(long j2) {
        return CommonUtils.hasFlags(this.mFlags, j2);
    }

    public boolean isAnimRunning(FloatProperty... floatPropertyArr) {
        return this.animManager.isAnimRunning(floatPropertyArr);
    }

    public boolean isIdle() {
        return isIdle(false);
    }

    public boolean isSleep() {
        return this.mIsSleep;
    }

    public boolean isValid() {
        return true;
    }

    public boolean isValidFlag() {
        return SystemClock.elapsedRealtime() - this.mFlagsSetTime > 3;
    }

    public void onFrameEnd(boolean z2) {
    }

    public void post(Runnable runnable) {
        TargetHandler handler = getHandler();
        if (handler == null || handler.isInTargetThread()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }

    public void postDelayed(Runnable runnable, long j2) {
        TargetHandler handler = getHandler();
        if (handler != null) {
            handler.postDelayed(runnable, j2);
        }
    }

    public void removeTask(Runnable runnable) {
        TargetHandler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public IAnimTarget setDefaultMinVisibleChange(float f2) {
        this.mDefaultMinVisible = f2;
        return this;
    }

    public void setFlags(long j2) {
        this.mFlags = j2;
        this.mFlagsSetTime = SystemClock.elapsedRealtime();
    }

    public final void setIntValue(final IIntValueProperty iIntValueProperty, final int i2) {
        TargetHandler handler = getHandler();
        if (handler == null || handler.isInTargetThread()) {
            doSetIntValue(iIntValueProperty, i2);
        } else {
            handler.post(new Runnable() { // from class: miuix.animation.IAnimTarget.2
                @Override // java.lang.Runnable
                public void run() {
                    IAnimTarget.this.doSetIntValue(iIntValueProperty, i2);
                }
            });
        }
    }

    @Deprecated
    public IAnimTarget setMinVisibleChange(float f2, FloatProperty... floatPropertyArr) {
        for (FloatProperty floatProperty : floatPropertyArr) {
            floatProperty.setMinVisibleChange(f2);
        }
        return this;
    }

    public void setToNotify(AnimState animState, @Nullable AnimConfigLink animConfigLink) {
        this.notifyManager.setToNotify(animState, animConfigLink);
    }

    public final void setValue(final FloatProperty floatProperty, final float f2) {
        TargetHandler handler = getHandler();
        if (handler == null || handler.isInTargetThread()) {
            doSetValue(floatProperty, f2);
        } else {
            handler.post(new Runnable() { // from class: miuix.animation.IAnimTarget.1
                @Override // java.lang.Runnable
                public void run() {
                    IAnimTarget.this.doSetValue(floatProperty, f2);
                }
            });
        }
    }

    public void setVelocity(String str, double d2) {
    }

    public boolean shouldCheckValue() {
        return this.mShouldCheckValue;
    }

    public boolean shouldUseIntValue(FloatProperty floatProperty) {
        return floatProperty instanceof IIntValueProperty;
    }

    public void sleep() {
        this.mIsSleep = true;
    }

    public String toString() {
        if (getTargetObject() != this) {
            StringBuilder sb = new StringBuilder();
            sb.append("Value{");
            sb.append(isValid() ? "valid " : "invalid ");
            sb.append(getTargetObject());
            sb.append("}");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Value{");
        sb2.append(isValid() ? "valid " : "invalid ");
        sb2.append("@");
        sb2.append(hashCode());
        sb2.append(" self}");
        return sb2.toString();
    }

    public void trackVelocity(FloatProperty floatProperty, double d2) {
        this.mTracker.trackVelocity(this, floatProperty, d2);
    }

    public double getVelocity(@Nullable FloatProperty floatProperty) {
        if (floatProperty == null) {
            return 0.0d;
        }
        return this.animManager.getVelocity(floatProperty);
    }

    public boolean isIdle(boolean z2) {
        return z2 ? (this.animManager.hasAnimSetup() || isAnimRunning(new FloatProperty[0])) ? false : true : (this.animManager.hasAnimSetup() || isAnimRunning(new FloatProperty[0]) || !isValidFlag()) ? false : true;
    }

    public void setVelocity(FloatProperty floatProperty, double d2) {
        if (d2 != 3.4028234663852886E38d) {
            this.animManager.setVelocity(floatProperty, (float) d2);
        }
    }

    @Deprecated
    public IAnimTarget setMinVisibleChange(Object obj, float f2) {
        if (obj instanceof FloatProperty) {
            ((FloatProperty) obj).setMinVisibleChange(f2);
        } else {
            this.mMinVisibleChanges.put(obj, Float.valueOf(f2));
        }
        return this;
    }

    public float getMinVisibleChange(Object obj) {
        Float f2 = this.mMinVisibleChanges.get(obj);
        if (f2 != null) {
            return f2.floatValue();
        }
        float f3 = this.mDefaultMinVisible;
        return f3 != Float.MAX_VALUE ? f3 : getDefaultMinVisible();
    }

    @Deprecated
    public IAnimTarget setMinVisibleChange(float f2, String... strArr) {
        for (String str : strArr) {
            setMinVisibleChange(str, f2);
        }
        return this;
    }

    public IAnimTarget() {
        AnimManager animManager = new AnimManager();
        this.animManager = animManager;
        this.notifyManager = new NotifyManager(this);
        this.mDefaultMinVisible = Float.MAX_VALUE;
        this.mMinVisibleChanges = new ConcurrentHashMap();
        this.mShouldCheckValue = true;
        this.id = sTargetIds.decrementAndGet();
        this.mTracker = new TargetVelocityTracker();
        this.handler = createHandler(Looper.myLooper());
        if (LogUtils.isLogMoreEnable()) {
            LogUtils.debug("IAnimTarget create ! ", new Object[0]);
        }
        animManager.setTarget(this);
    }
}
