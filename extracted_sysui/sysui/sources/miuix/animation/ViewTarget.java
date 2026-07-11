package miuix.animation;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import miuix.animation.internal.TargetHandler;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ViewProperty;
import miuix.animation.property.ViewPropertyExt;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.LogUtils;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes4.dex */
public class ViewTarget extends IAnimTarget<View> {
    private static volatile Set<ViewProperty> pViewPropertySet = new HashSet();
    public static final ITargetCreator<View> sCreator;
    public static final ITargetCreator<View> sSimpleCreator;
    private WeakReference<Context> mContextRef;
    private boolean mIsSimple;
    private LifecycleCallbacks mLifecycleCallbacks;
    private Runnable mRegisterRunnable;
    private ViewLifecycleObserver mViewLifecycleObserver;
    private WeakReference<View> mViewRef;

    public class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        public LifecycleCallbacks() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
            ViewTarget.this.cleanSelf();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
            ViewTarget.this.awakeSelf();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
            if (activity.isFinishing() || !Folme.enableSleep()) {
                return;
            }
            ViewTarget.this.sleepSelf();
        }
    }

    public class ViewLifecycleObserver implements LifecycleObserver {
        public ViewLifecycleObserver() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            ViewTarget.this.cleanSelf();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            ViewTarget.this.awakeSelf();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onStop() {
            if (Folme.enableSleep()) {
                ViewTarget.this.sleepSelf();
            }
        }
    }

    static {
        pViewPropertySet.add(ViewProperty.TRANSLATION_X);
        pViewPropertySet.add(ViewProperty.TRANSLATION_Y);
        pViewPropertySet.add(ViewProperty.TRANSLATION_Z);
        pViewPropertySet.add(ViewProperty.SCALE_X);
        pViewPropertySet.add(ViewProperty.SCALE_Y);
        pViewPropertySet.add(ViewProperty.ROTATION);
        pViewPropertySet.add(ViewProperty.ROTATION_X);
        pViewPropertySet.add(ViewProperty.ROTATION_Y);
        pViewPropertySet.add(ViewProperty.f6001X);
        pViewPropertySet.add(ViewProperty.f6002Y);
        pViewPropertySet.add(ViewProperty.f6003Z);
        pViewPropertySet.add(ViewProperty.HEIGHT);
        pViewPropertySet.add(ViewProperty.WIDTH);
        pViewPropertySet.add(ViewProperty.ALPHA);
        pViewPropertySet.add(ViewProperty.AUTO_ALPHA);
        pViewPropertySet.add(ViewProperty.TRANSITION_ALPHA);
        pViewPropertySet.add(ViewProperty.SCROLL_X);
        pViewPropertySet.add(ViewProperty.SCROLL_Y);
        pViewPropertySet.add(ViewProperty.ELEVATION);
        pViewPropertySet.add(ViewProperty.BIG_VIEW_SCALE_X);
        pViewPropertySet.add(ViewProperty.BIG_VIEW_SCALE_Y);
        pViewPropertySet.add(ViewPropertyExt.BACKGROUND);
        pViewPropertySet.add(ViewPropertyExt.FOREGROUND);
        sCreator = new ITargetCreator<View>() { // from class: miuix.animation.ViewTarget.1
            @Override // miuix.animation.ITargetCreator
            public IAnimTarget createTarget(View view) {
                return new ViewTarget(view);
            }
        };
        sSimpleCreator = new ITargetCreator<View>() { // from class: miuix.animation.ViewTarget.2
            @Override // miuix.animation.ITargetCreator
            public IAnimTarget createTarget(View view) {
                ViewTarget viewTarget = new ViewTarget(view);
                viewTarget.mIsSimple = true;
                return viewTarget;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void awakeSelf() {
        if (this.mIsSimple) {
            return;
        }
        Folme.awake(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanSelf() {
        if (LogUtils.isLogMainEnabled()) {
            LogUtils.debug("ViewTarget.cleanSelf isSimple:" + this.mIsSimple + " " + this + LogUtils.getStackTrace(4), new Object[0]);
        }
        WeakReference<Context> weakReference = this.mContextRef;
        if (weakReference != null) {
            unRegisterLifecycle(weakReference.get());
        }
        setCorner(0.0f);
        if (this.mIsSimple) {
            return;
        }
        Folme.clean(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: doClean, reason: merged with bridge method [inline-methods] */
    public void lambda$clean$0() {
        WeakReference<View> weakReference;
        View view;
        if (isAnimRunning(new FloatProperty[0])) {
            cancelRunningAnim();
        }
        if (this.mRegisterRunnable != null && (weakReference = this.mViewRef) != null && (view = weakReference.get()) != null) {
            view.removeCallbacks(this.mRegisterRunnable);
            this.mRegisterRunnable = null;
        }
        this.animManager.clear();
        getNotifier().removeListeners();
        WeakReference<Context> weakReference2 = this.mContextRef;
        if (weakReference2 != null) {
            unRegisterLifecycle(weakReference2.get());
        }
    }

    private void executeTask(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e2) {
            Log.w(CommonUtils.TAG, "ViewTarget.executeTask failed, " + getTargetObject(), e2);
        }
    }

    public static FloatProperty getFloatProperty(String str) {
        for (ViewProperty viewProperty : pViewPropertySet) {
            if (viewProperty.getName().contentEquals(str)) {
                return viewProperty;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initLayout(View view, Runnable runnable) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            int i2 = R.id.miuix_animation_tag_init_layout;
            view.setTag(i2, Boolean.TRUE);
            ViewGroup viewGroup = (ViewGroup) parent;
            int left = viewGroup.getLeft();
            int top = viewGroup.getTop();
            int visibility = view.getVisibility();
            if (visibility == 8) {
                view.setVisibility(4);
            }
            viewGroup.measure(viewGroup.getWidth(), viewGroup.getHeight());
            viewGroup.layout(left, top, viewGroup.getWidth() + left, viewGroup.getHeight() + top);
            view.setVisibility(visibility);
            runnable.run();
            view.setTag(i2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v6, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    public boolean registerLifecycle(Context context) {
        while (context != 0) {
            if (context instanceof LifecycleOwner) {
                this.mContextRef = new WeakReference<>(context);
                if (this.mViewLifecycleObserver == null) {
                    this.mViewLifecycleObserver = new ViewLifecycleObserver();
                }
                ((LifecycleOwner) context).getLifecycle().addObserver(this.mViewLifecycleObserver);
                return true;
            }
            if (context instanceof Activity) {
                this.mContextRef = new WeakReference<>(context);
                if (this.mLifecycleCallbacks == null) {
                    this.mLifecycleCallbacks = new LifecycleCallbacks();
                }
                ((Activity) context).registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
                return true;
            }
            context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : 0;
        }
        return false;
    }

    private void setCorner(float f2) {
        View view = this.mViewRef.get();
        if (view != null) {
            view.setTag(R.id.miuix_animation_tag_view_hover_corners, Float.valueOf(f2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sleepSelf() {
        if (this.mIsSimple) {
            return;
        }
        Folme.sleep(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean unRegisterLifecycle(Context context) {
        LifecycleCallbacks lifecycleCallbacks;
        if (context == 0) {
            return false;
        }
        if (context instanceof LifecycleOwner) {
            if (this.mViewLifecycleObserver != null) {
                ((LifecycleOwner) context).getLifecycle().removeObserver(this.mViewLifecycleObserver);
            }
            this.mViewLifecycleObserver = null;
            return true;
        }
        if (!(context instanceof Activity) || (lifecycleCallbacks = this.mLifecycleCallbacks) == null) {
            return false;
        }
        ((Activity) context).unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
        this.mLifecycleCallbacks = null;
        return true;
    }

    @Override // miuix.animation.IAnimTarget
    public boolean allowAnimRun() {
        View targetObject = getTargetObject();
        return (targetObject == null || Folme.isInDraggingState(targetObject)) ? false : true;
    }

    @Override // miuix.animation.IAnimTarget
    public void clean() {
        executeTask(new Runnable() { // from class: miuix.animation.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f5936a.lambda$clean$0();
            }
        });
    }

    @Override // miuix.animation.IAnimTarget
    public TargetHandler createHandler(Looper looper) {
        if (looper == null) {
            looper = Folme.getLooper();
        } else if (looper != Looper.getMainLooper() && Folme.getUiLooperByTid(looper.getThread().getId()) == null) {
            if (LogUtils.isLogDetailEnable()) {
                LogUtils.debug("ViewTarget.createHandler registerUiLooper " + looper + " tid " + looper.getThread().getId(), new Object[0]);
            }
            Folme.registerUiLooper(looper);
        }
        if (looper != null) {
            return new TargetHandler(looper, this);
        }
        Log.w(CommonUtils.TAG, "warning!! the ViewTarget handler created failed, caused by creating in a thread without Looper, the animation will do not work!! trace:" + Log.getStackTraceString(new Throwable()));
        return null;
    }

    @Override // miuix.animation.IAnimTarget
    public void executeOnInitialized(final Runnable runnable) {
        final View view = this.mViewRef.get();
        if (view != null) {
            if (view.getVisibility() == 8 && !view.isLaidOut() && (view.getWidth() == 0 || view.getHeight() == 0)) {
                post(new Runnable() { // from class: miuix.animation.ViewTarget.4
                    @Override // java.lang.Runnable
                    public void run() {
                        ViewTarget.this.initLayout(view, runnable);
                    }
                });
            } else {
                post(runnable);
            }
        }
    }

    @Override // miuix.animation.IAnimTarget
    public double getDoubleValue(@NonNull FloatProperty floatProperty) {
        View targetObject;
        if (!(floatProperty instanceof ViewProperty) || (targetObject = getTargetObject()) == null) {
            return Double.MAX_VALUE;
        }
        return floatProperty.getValue(targetObject);
    }

    @Override // miuix.animation.IAnimTarget
    public void getLocationOnScreen(int[] iArr) {
        View view = this.mViewRef.get();
        if (view != null) {
            view.getLocationOnScreen(iArr);
        } else {
            iArr[1] = Integer.MAX_VALUE;
            iArr[0] = Integer.MAX_VALUE;
        }
    }

    @Override // miuix.animation.IAnimTarget
    public double getVelocity(String str) {
        return getVelocity(getFloatProperty(str));
    }

    @Override // miuix.animation.IAnimTarget
    public boolean isValid() {
        WeakReference<View> weakReference = this.mViewRef;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // miuix.animation.IAnimTarget
    public void onFrameEnd(boolean z2) {
        View view = this.mViewRef.get();
        if (!z2 || view == null) {
            return;
        }
        view.setTag(R.id.miuix_animation_tag_set_height, null);
        view.setTag(R.id.miuix_animation_tag_set_width, null);
        view.setTag(R.id.miuix_animation_tag_view_hover_corners, Float.valueOf(0.0f));
    }

    @Override // miuix.animation.IAnimTarget
    public void post(Runnable runnable) {
        View targetObject = getTargetObject();
        if (targetObject == null) {
            return;
        }
        TargetHandler handler = getHandler();
        if (handler == null || handler.isInTargetThread() || !targetObject.isAttachedToWindow()) {
            executeTask(runnable);
        } else {
            targetObject.post(runnable);
        }
    }

    @Override // miuix.animation.IAnimTarget
    public void setVelocity(String str, double d2) {
        setVelocity(getFloatProperty(str), d2);
    }

    @Override // miuix.animation.IAnimTarget
    public boolean shouldUseIntValue(FloatProperty floatProperty) {
        if (floatProperty == ViewProperty.WIDTH || floatProperty == ViewProperty.HEIGHT || floatProperty == ViewProperty.SCROLL_X || floatProperty == ViewProperty.SCROLL_Y) {
            return true;
        }
        return super.shouldUseIntValue(floatProperty);
    }

    @Override // miuix.animation.IAnimTarget
    public String toString() {
        String resourcePackageName;
        StringBuilder sb = new StringBuilder(256);
        View targetObject = getTargetObject();
        sb.append('#');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" ");
        sb.append(isValid() ? "valid" : "invalid");
        sb.append(" {");
        int id = targetObject != null ? targetObject.getId() : -1;
        if (id != -1) {
            sb.append(Integer.toHexString(id));
            Resources resources = targetObject.getResources();
            if (id > 0 && (id >>> 24) != 0 && resources != null) {
                int i2 = (-16777216) & id;
                if (i2 == 16777216) {
                    resourcePackageName = SystemMediaRouteProvider.PACKAGE_NAME;
                } else if (i2 != 2130706432) {
                    try {
                        resourcePackageName = resources.getResourcePackageName(id);
                    } catch (Resources.NotFoundException unused) {
                    }
                } else {
                    resourcePackageName = "app";
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                sb.append(" ");
                sb.append(resourcePackageName);
                sb.append(MethodCodeHelper.IDENTITY_INFO_SEPARATOR);
                sb.append(resourceTypeName);
                sb.append("/");
                sb.append(resourceEntryName);
            }
        } else {
            sb.append("NO_ID");
        }
        sb.append("/");
        if (targetObject != null) {
            sb.append(targetObject.getClass().getName());
        } else {
            sb.append("view reference is not available");
        }
        sb.append("}");
        return "View{" + ((Object) sb) + "}";
    }

    private ViewTarget(View view) {
        this.mRegisterRunnable = new Runnable() { // from class: miuix.animation.ViewTarget.3
            @Override // java.lang.Runnable
            public void run() {
                View view2 = (View) ViewTarget.this.mViewRef.get();
                if (view2 != null) {
                    ViewTarget.this.registerLifecycle(view2.getContext());
                }
                ViewTarget.this.mRegisterRunnable = null;
            }
        };
        this.mViewRef = new WeakReference<>(view);
        if (Folme.appContext == null) {
            Folme.appContext = view.getContext().getApplicationContext();
        }
        if (Looper.myLooper() == null) {
            view.post(this.mRegisterRunnable);
        } else {
            this.mRegisterRunnable.run();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // miuix.animation.IAnimTarget
    public View getTargetObject() {
        WeakReference<View> weakReference = this.mViewRef;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }
}
