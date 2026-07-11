package miuix.overscroller.internal.dynamicanimation.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Choreographer;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AnimationHandler {
    private static final long FRAME_DELAY_MS = 10;
    private static final String TAG = "OverScroller Animation";
    private AnimationFrameCallbackProvider mProvider;
    private static final boolean SUPPORT_MI_MOTION = Boolean.parseBoolean(LiteSystemProperties.get("ro.display.mimotion", "false"));
    public static final ThreadLocal<AnimationHandler> sAnimatorHandler = new ThreadLocal<>();
    private final ArrayMap<AnimationFrameCallback, Long> mDelayedCallbackStartTime = new ArrayMap<>();
    private final ArrayList<AnimationFrameCallback> mAnimationCallbacks = new ArrayList<>();
    private final AnimationCallbackDispatcher mCallbackDispatcher = new AnimationCallbackDispatcher();
    private long mCurrentFrameTime = 0;
    private boolean mListDirty = false;

    public class AnimationCallbackDispatcher {
        public AnimationCallbackDispatcher() {
        }

        public void dispatchAnimationFrame(long j2) {
            AnimationHandler.this.doAnimationFrame(j2);
            if (AnimationHandler.this.mAnimationCallbacks.size() > 0) {
                AnimationHandler.this.getProvider().postFrameCallback();
            }
        }
    }

    public interface AnimationFrameCallback {
        boolean doAnimationFrame(long j2);
    }

    public static abstract class AnimationFrameCallbackProvider {
        final AnimationCallbackDispatcher mDispatcher;

        public AnimationFrameCallbackProvider(AnimationCallbackDispatcher animationCallbackDispatcher) {
            this.mDispatcher = animationCallbackDispatcher;
        }

        public long getFrameDeltaNanos() {
            return 0L;
        }

        public boolean getFromFramePeriodNsecs() {
            return false;
        }

        public abstract Looper getLooper();

        public abstract boolean isCurrentThread();

        public abstract void postFrameCallback();

        public void postVsyncCallback() {
        }
    }

    @Deprecated
    public static class FrameCallbackProvider14 extends AnimationFrameCallbackProvider {
        private final Handler mHandler;
        private long mLastFrameTime;
        private final Runnable mRunnable;

        public FrameCallbackProvider14(AnimationCallbackDispatcher animationCallbackDispatcher) {
            super(animationCallbackDispatcher);
            this.mLastFrameTime = -1L;
            this.mRunnable = new Runnable() { // from class: miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider14.1
                @Override // java.lang.Runnable
                public void run() {
                    FrameCallbackProvider14.this.mLastFrameTime = SystemClock.uptimeMillis();
                    FrameCallbackProvider14 frameCallbackProvider14 = FrameCallbackProvider14.this;
                    frameCallbackProvider14.mDispatcher.dispatchAnimationFrame(frameCallbackProvider14.mLastFrameTime);
                }
            };
            this.mHandler = new Handler(Looper.myLooper());
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public Looper getLooper() {
            return this.mHandler.getLooper();
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public boolean isCurrentThread() {
            return Thread.currentThread() == this.mHandler.getLooper().getThread();
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public void postFrameCallback() {
            this.mHandler.postDelayed(this.mRunnable, Math.max(AnimationHandler.FRAME_DELAY_MS - (SystemClock.uptimeMillis() - this.mLastFrameTime), 0L));
        }
    }

    public static class FrameCallbackProvider16 extends AnimationFrameCallbackProvider {
        private final Choreographer mChoreographer;
        private final Choreographer.FrameCallback mChoreographerCallback;
        private final Looper mLooper;

        public FrameCallbackProvider16(AnimationCallbackDispatcher animationCallbackDispatcher) {
            super(animationCallbackDispatcher);
            this.mChoreographer = Choreographer.getInstance();
            this.mLooper = Looper.myLooper();
            this.mChoreographerCallback = new Choreographer.FrameCallback() { // from class: miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider16.1
                @Override // android.view.Choreographer.FrameCallback
                public void doFrame(long j2) {
                    FrameCallbackProvider16.this.mDispatcher.dispatchAnimationFrame(j2);
                }
            };
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public Looper getLooper() {
            return this.mLooper;
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public boolean isCurrentThread() {
            return Thread.currentThread() == this.mLooper.getThread();
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public void postFrameCallback() {
            this.mChoreographer.postFrameCallback(this.mChoreographerCallback);
        }
    }

    @RequiresApi(api = 33)
    public static class FrameCallbackProvider33 extends AnimationFrameCallbackProvider {
        private final Choreographer mChoreographer;
        private final Choreographer.FrameCallback mChoreographerCallback;
        private long mFrameDeltaNanos;
        private boolean mFromFramePeriodNsecs;
        private Method mGetFramePeriodNsecs;
        private final Looper mLooper;
        private final Choreographer.VsyncCallback mVsyncCallback;

        public FrameCallbackProvider33(AnimationCallbackDispatcher animationCallbackDispatcher) {
            super(animationCallbackDispatcher);
            this.mChoreographer = Choreographer.getInstance();
            this.mGetFramePeriodNsecs = null;
            this.mLooper = Looper.myLooper();
            this.mFrameDeltaNanos = 0L;
            this.mFromFramePeriodNsecs = false;
            if (AnimationHandler.SUPPORT_MI_MOTION && this.mGetFramePeriodNsecs == null) {
                try {
                    Method declaredMethod = Choreographer.class.getDeclaredMethod("getFramePeriodNsecs", null);
                    this.mGetFramePeriodNsecs = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception unused) {
                    Log.w(AnimationHandler.TAG, "get getFramePeriodNSec failed ");
                }
            }
            this.mVsyncCallback = new Choreographer.VsyncCallback() { // from class: miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.1
                /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
                @Override // android.view.Choreographer.VsyncCallback
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onVsync(@androidx.annotation.NonNull android.view.Choreographer.FrameData r8) {
                    /*
                        r7 = this;
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider33 r0 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.this
                        java.lang.reflect.Method r0 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.access$300(r0)
                        r1 = 1
                        r2 = 0
                        if (r0 == 0) goto L3e
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider33 r0 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.this     // Catch: java.lang.Exception -> L35
                        java.lang.reflect.Method r0 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.access$300(r0)     // Catch: java.lang.Exception -> L35
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider33 r3 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.this     // Catch: java.lang.Exception -> L35
                        android.view.Choreographer r3 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.access$400(r3)     // Catch: java.lang.Exception -> L35
                        r4 = 0
                        java.lang.Object r0 = r0.invoke(r3, r4)     // Catch: java.lang.Exception -> L35
                        java.lang.Long r0 = (java.lang.Long) r0     // Catch: java.lang.Exception -> L35
                        long r3 = r0.longValue()     // Catch: java.lang.Exception -> L35
                        r5 = -1
                        int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                        if (r0 == 0) goto L3e
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider33 r0 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.this     // Catch: java.lang.Exception -> L35
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.access$502(r0, r3)     // Catch: java.lang.Exception -> L35
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider33 r0 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.this     // Catch: java.lang.Exception -> L33
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.access$602(r0, r1)     // Catch: java.lang.Exception -> L33
                        r0 = r1
                        goto L3f
                    L33:
                        r0 = r1
                        goto L36
                    L35:
                        r0 = r2
                    L36:
                        java.lang.String r3 = "OverScroller Animation"
                        java.lang.String r4 = "onVsync getFramePeriodNSec failed"
                        android.util.Log.w(r3, r4)
                        goto L3f
                    L3e:
                        r0 = r2
                    L3f:
                        if (r0 != 0) goto L65
                        android.view.Choreographer$FrameTimeline[] r8 = r8.getFrameTimelines()
                        int r0 = r8.length
                        if (r0 <= r1) goto L65
                        int r0 = r0 - r1
                        r1 = r8[r0]
                        long r3 = r1.getExpectedPresentationTimeNanos()
                        r8 = r8[r2]
                        long r1 = r8.getExpectedPresentationTimeNanos()
                        long r3 = r3 - r1
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler$FrameCallbackProvider33 r7 = miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.this
                        double r1 = (double) r3
                        r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                        double r1 = r1 * r3
                        double r3 = (double) r0
                        double r1 = r1 / r3
                        long r0 = java.lang.Math.round(r1)
                        miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.access$502(r7, r0)
                    L65:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.AnonymousClass1.onVsync(android.view.Choreographer$FrameData):void");
                }
            };
            this.mChoreographerCallback = new Choreographer.FrameCallback() { // from class: miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.FrameCallbackProvider33.2
                @Override // android.view.Choreographer.FrameCallback
                public void doFrame(long j2) {
                    FrameCallbackProvider33.this.mDispatcher.dispatchAnimationFrame(j2);
                }
            };
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public long getFrameDeltaNanos() {
            return this.mFrameDeltaNanos;
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public boolean getFromFramePeriodNsecs() {
            return this.mFromFramePeriodNsecs;
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public Looper getLooper() {
            return this.mLooper;
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public boolean isCurrentThread() {
            return Thread.currentThread() == this.mLooper.getThread();
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public void postFrameCallback() {
            this.mChoreographer.postVsyncCallback(this.mVsyncCallback);
            this.mChoreographer.postFrameCallback(this.mChoreographerCallback);
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.AnimationHandler.AnimationFrameCallbackProvider
        public void postVsyncCallback() {
            this.mChoreographer.postVsyncCallback(this.mVsyncCallback);
        }
    }

    private void cleanUpList() {
        if (this.mListDirty) {
            for (int size = this.mAnimationCallbacks.size() - 1; size >= 0; size--) {
                if (this.mAnimationCallbacks.get(size) == null) {
                    this.mAnimationCallbacks.remove(size);
                }
            }
            this.mListDirty = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAnimationFrame(long j2) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        for (int i2 = 0; i2 < this.mAnimationCallbacks.size(); i2++) {
            AnimationFrameCallback animationFrameCallback = this.mAnimationCallbacks.get(i2);
            if (animationFrameCallback != null && isCallbackDue(animationFrameCallback, jUptimeMillis)) {
                animationFrameCallback.doAnimationFrame(j2);
            }
        }
        cleanUpList();
    }

    public static long getFrameTime() {
        ThreadLocal<AnimationHandler> threadLocal = sAnimatorHandler;
        if (threadLocal.get() == null) {
            return 0L;
        }
        return threadLocal.get().mCurrentFrameTime;
    }

    public static AnimationHandler getInstance() {
        ThreadLocal<AnimationHandler> threadLocal = sAnimatorHandler;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler());
        }
        return threadLocal.get();
    }

    private boolean isCallbackDue(AnimationFrameCallback animationFrameCallback, long j2) {
        Long l2 = this.mDelayedCallbackStartTime.get(animationFrameCallback);
        if (l2 == null) {
            return true;
        }
        if (l2.longValue() >= j2) {
            return false;
        }
        this.mDelayedCallbackStartTime.remove(animationFrameCallback);
        return true;
    }

    public void addAnimationFrameCallback(AnimationFrameCallback animationFrameCallback, long j2) {
        if (this.mAnimationCallbacks.size() == 0) {
            getProvider().postFrameCallback();
        }
        if (!this.mAnimationCallbacks.contains(animationFrameCallback)) {
            this.mAnimationCallbacks.add(animationFrameCallback);
        }
        if (j2 > 0) {
            this.mDelayedCallbackStartTime.put(animationFrameCallback, Long.valueOf(SystemClock.uptimeMillis() + j2));
        }
    }

    public long getFrameDeltaNanos() {
        return getProvider().getFrameDeltaNanos();
    }

    public boolean getFromFramePeriodNsecs() {
        return getProvider().getFromFramePeriodNsecs();
    }

    public Looper getLooper() {
        return getProvider().getLooper();
    }

    public AnimationFrameCallbackProvider getProvider() {
        if (this.mProvider == null) {
            this.mProvider = new FrameCallbackProvider33(this.mCallbackDispatcher);
        }
        return this.mProvider;
    }

    public boolean isCurrentThread() {
        return getProvider().isCurrentThread();
    }

    public void postVsyncCallback() {
        getProvider().postVsyncCallback();
    }

    public void recreateProvider() {
        this.mProvider = new FrameCallbackProvider33(this.mCallbackDispatcher);
    }

    public void removeCallback(AnimationFrameCallback animationFrameCallback) {
        this.mDelayedCallbackStartTime.remove(animationFrameCallback);
        int iIndexOf = this.mAnimationCallbacks.indexOf(animationFrameCallback);
        if (iIndexOf >= 0) {
            this.mAnimationCallbacks.set(iIndexOf, null);
            this.mListDirty = true;
        }
    }

    public void setProvider(AnimationFrameCallbackProvider animationFrameCallbackProvider) {
        this.mProvider = animationFrameCallbackProvider;
    }
}
