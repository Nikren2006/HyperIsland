package miui.systemui.dynamicisland.anim;

import H0.d;
import H0.e;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import j1.u;
import java.lang.reflect.Method;
import java.util.HashSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.event.handler.SmallIslandStateHandler;
import miui.systemui.dynamicisland.view.DynamicIslandBigIslandView;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ConvenienceExtensionsKt;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandAnimationController {
    public static final String TAG = "DynamicIslandAnim";
    private final miui.systemui.dynamicisland.anim.DynamicIslandAnimationCallback animationCallback;
    private final DynamicIslandAnimationDelegate.Factory animationDelegateFactory;
    private boolean animatorRunning;
    private final HashSet<String> animatorSet;
    private final d callback$delegate;
    private DynamicIslandState currentBigIsland;
    private DynamicIslandState currentExpanded;
    private final DynamicIslandWindowState dynamicIslandWindowState;
    private DynamicIslandState lastExpanded;
    public static final Companion Companion = new Companion(null);
    private static final d setMiSelfBlurMethod$delegate = e.b(DynamicIslandAnimationController$Companion$setMiSelfBlurMethod$2.INSTANCE);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Method getSetMiSelfBlurMethod() {
            return (Method) DynamicIslandAnimationController.setMiSelfBlurMethod$delegate.getValue();
        }

        public final void setMiSelfBlurCompat(View view, int i2) {
            n.g(view, "<this>");
            try {
                Method setMiSelfBlurMethod = getSetMiSelfBlurMethod();
                if (setMiSelfBlurMethod != null) {
                    setMiSelfBlurMethod.invoke(view, Integer.valueOf(i2), null);
                }
            } catch (Throwable th) {
                Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "Invoke setMiSelfBlurMethod failed.", th);
            }
        }

        private Companion() {
        }
    }

    public interface DynamicIslandAnimationCallback {
        void onAnimationFinished();

        void onAnimationStart(boolean z2, DynamicIslandContentView dynamicIslandContentView);

        void onStateChange(String str, DynamicIslandContentView dynamicIslandContentView);
    }

    public DynamicIslandAnimationController(E0.a eventCoordinator, DynamicIslandWindowState dynamicIslandWindowState, DynamicIslandAnimationDelegate.Factory animationDelegateFactory) {
        n.g(eventCoordinator, "eventCoordinator");
        n.g(dynamicIslandWindowState, "dynamicIslandWindowState");
        n.g(animationDelegateFactory, "animationDelegateFactory");
        this.dynamicIslandWindowState = dynamicIslandWindowState;
        this.animationDelegateFactory = animationDelegateFactory;
        this.callback$delegate = ConvenienceExtensionsKt.getKotlinLazy(eventCoordinator);
        this.animatorSet = new HashSet<>();
        miui.systemui.dynamicisland.anim.DynamicIslandAnimationCallback dynamicIslandAnimationCallback = new miui.systemui.dynamicisland.anim.DynamicIslandAnimationCallback();
        DynamicIslandAnimationType dynamicIslandAnimationType = DynamicIslandAnimationType.ALL;
        dynamicIslandAnimationCallback.addAnimationCallback(dynamicIslandAnimationType, DynamicIslandAnimationCallbackType.ANIM_START, new DynamicIslandAnimationController$animationCallback$1$1(this));
        dynamicIslandAnimationCallback.addAnimationCallback(dynamicIslandAnimationType, DynamicIslandAnimationCallbackType.ANIM_FINISH, new DynamicIslandAnimationController$animationCallback$1$2(this));
        dynamicIslandAnimationCallback.addAnimationCallback(dynamicIslandAnimationType, DynamicIslandAnimationCallbackType.ANIM_CANCEL, new DynamicIslandAnimationController$animationCallback$1$3(this));
        this.animationCallback = dynamicIslandAnimationCallback;
    }

    public static final /* synthetic */ HashSet access$getAnimatorSet$p(DynamicIslandAnimationController dynamicIslandAnimationController) {
        return dynamicIslandAnimationController.animatorSet;
    }

    private final void appExpandedToAppExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "appExpandedToAppExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.appExpandedToAppExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void appExpandedToBigIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "appExpandedToBigIslandAnimation");
        if (islandAppAnimating(dynamicIslandContentView) && (animator = getAnimator(dynamicIslandContentView)) != null) {
            animator.setAppClose(true);
        }
        DynamicIslandAnimationDelegate animator2 = getAnimator(dynamicIslandContentView);
        if (animator2 != null) {
            animator2.appExpandedToBigIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void appExpandedToHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "appExpandedToHiddenAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.appExpandedToHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void appExpandedToMiniWindowExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "appExpandedToMiniWindowExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.appExpandedToMiniWindowExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void appExpandedToSmallIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "appExpandedToSmallIslandAnimation");
        if (islandAppAnimating(dynamicIslandContentView) && (animator = getAnimator(dynamicIslandContentView)) != null) {
            animator.setAppClose(true);
        }
        DynamicIslandAnimationDelegate animator2 = getAnimator(dynamicIslandContentView);
        if (animator2 != null) {
            animator2.appExpandedToSmallIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void beforeBigIslandToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "beforeBigIslandToTempHiddenAnimation: " + dynamicIslandContentView);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.beforeBigIslandToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void beforeSmallIslandToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToTempHiddenNoAnimation: " + dynamicIslandContentView);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.beforeSmallIslandToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandChangedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandChangedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandChangedAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandChangedNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandChangedNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandChangedNoAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToAppExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToAppExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandToAppExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToDeletedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandWindowState windowState;
        DynamicIslandState state = dynamicIslandContentView.getState();
        if ((state == null || !state.getDeleteNoAnimation()) && ((windowState = dynamicIslandContentView.getWindowState()) == null || !windowState.isToScreenLockNoAnimation())) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToDeletedAnimation");
            DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
            if (animator != null) {
                animator.bigIslandToDeletedAnimation(dynamicIslandContentView);
                return;
            }
            return;
        }
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToDeletedNoAnimation");
        DynamicIslandAnimationDelegate animator2 = getAnimator(dynamicIslandContentView);
        if (animator2 != null) {
            animator2.bigIslandToDeletedNoAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandToExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToHiddenAnimation: " + dynamicIslandContentView);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandToHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToMiniWindowExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToMiniWindowExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandToMiniWindowExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToSmallIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToSmallIslandAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandToSmallIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToSmallIslandNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToSmallIslandNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandToSmallIslandNoAnimation(dynamicIslandContentView);
        }
    }

    private final void bigIslandToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandWindowState windowState = dynamicIslandContentView.getWindowState();
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "bigIslandToTempHiddenAnimation: " + dynamicIslandContentView + " view.windowState.screenLocked: " + (windowState != null ? windowState.getScreenLocked() : null));
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.bigIslandToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedChangedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedChangedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedChangedAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToAppExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToAppExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToAppExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToBigIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToBigIslandAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToBigIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToBigIslandNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToBigIslandNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToBigIslandNoAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToDeletedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToDeletedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToDeletedAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToDeletedNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToDeletedNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToDeletedNoAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToHiddenAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToHiddenAnimation(dynamicIslandContentView, false);
        }
    }

    private final void expandedToMiniWindowAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToMiniWindowAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToMiniWindowAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToSmallIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToSmallIslandAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToSmallIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToSmallIslandNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToSmallIslandNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToSmallIslandNoAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToTempHiddenAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void expandedToTempHiddenNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "expandedToTempHiddenNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.expandedToTempHiddenNoAnimation(dynamicIslandContentView);
        }
    }

    private final DynamicIslandAnimationDelegate getAnimator(DynamicIslandBaseContentView dynamicIslandBaseContentView) {
        return dynamicIslandBaseContentView.getAnimator(this.animationDelegateFactory);
    }

    private final DynamicIslandEventCoordinator getCallback() {
        return (DynamicIslandEventCoordinator) this.callback$delegate.getValue();
    }

    private final void hiddenChangedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenChangedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenChangedAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenChangedNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenChangedNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenChangedNoAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenToAppExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenToAppExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenToAppExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenToBigIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenToBigIslandAnimation");
        showBigIsland(dynamicIslandContentView);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenToBigIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenToDeletedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenToDeletedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenToDeletedAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenToMiniWindowExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenToMiniWindowExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenToMiniWindowExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenToSmallIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenToSmallIslandAnimation");
        showSmallIsland(dynamicIslandContentView);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenToSmallIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenToTempHiddenAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void hiddenToTempHiddenNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "hiddenToTempHiddenNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.hiddenToTempHiddenNoAnimation(dynamicIslandContentView);
        }
    }

    private final void initToAppExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "initToAppExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.initToAppExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void initToBigIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "initToBigIslandAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.initToBigIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void initToExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "initToExpandedAnimation");
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return;
        }
        animator.initToExpandedAnimation(dynamicIslandContentView);
    }

    private final void initToMiniWindowExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "initToMiniWindowExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.initToMiniWindowExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void initToSmallIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "initToSmallIslandAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.initToSmallIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void initToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "initToTempHiddenAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.initToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void miniWindowToAppExpanded(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "miniWindowToAppExpanded");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.miniWindowToAppExpanded(dynamicIslandContentView);
        }
    }

    private final void miniWindowToBigIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "miniWindowToBigIslandAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.miniWindowToBigIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void miniWindowToSmallIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "miniWindowToSmallIslandAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.miniWindowToSmallIslandAnimation(dynamicIslandContentView);
        }
    }

    private final void miniWindowToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "miniWindowToTempHiddenAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.miniWindowToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void miniWindowToTempHiddenNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "miniWindowToTempHiddenNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.miniWindowToTempHiddenNoAnimation(dynamicIslandContentView);
        }
    }

    private final boolean needUpdateWindowHeight(DynamicIslandContentView dynamicIslandContentView) {
        return (dynamicIslandContentView.getLastState() instanceof DynamicIslandState.Expanded) || (dynamicIslandContentView.getState() instanceof DynamicIslandState.Expanded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnimatorRunning(boolean z2) {
        if (this.animatorRunning != z2) {
            if (z2) {
                Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "animatorRunning is true");
            } else {
                Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "animatorRunning is false");
            }
            this.animatorRunning = z2;
            if (z2) {
                return;
            }
            getCallback().onAnimationFinished();
        }
    }

    public static final void setMiSelfBlurCompat(View view, int i2) {
        Companion.setMiSelfBlurCompat(view, i2);
    }

    private final void smallIslandChangedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandChangedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandChangedAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandChangedNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandChangedNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandChangedNoAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandScaleAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.i(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandScaleAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandScaleAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandToAppExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToAppExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandToAppExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandToBigIslandAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToBigIslandAnimation " + (animator != null ? Boolean.valueOf(animator.getAppClose()) : null));
        DynamicIslandAnimationDelegate animator2 = getAnimator(dynamicIslandContentView);
        if (animator2 != null) {
            DynamicIslandAnimationDelegate animator3 = getAnimator(dynamicIslandContentView);
            boolean z2 = false;
            if (animator3 != null && animator3.getAppClose()) {
                z2 = true;
            }
            animator2.smallIslandToBigIslandAnimation(dynamicIslandContentView, z2);
        }
    }

    private final void smallIslandToDeletedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandWindowState windowState;
        DynamicIslandState state = dynamicIslandContentView.getState();
        if ((state == null || !state.getDeleteNoAnimation()) && ((windowState = dynamicIslandContentView.getWindowState()) == null || !windowState.isToScreenLockNoAnimation())) {
            Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToDeletedAnimation");
            DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
            if (animator != null) {
                animator.smallIslandToDeletedAnimation(dynamicIslandContentView);
                return;
            }
            return;
        }
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToDeletedNoAnimation");
        DynamicIslandAnimationDelegate animator2 = getAnimator(dynamicIslandContentView);
        if (animator2 != null) {
            animator2.smallIslandToDeletedNoAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandToExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandToExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandToHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToHiddenAnimation, appClose: " + (animator != null ? Boolean.valueOf(animator.getAppClose()) : null));
        DynamicIslandAnimationDelegate animator2 = getAnimator(dynamicIslandContentView);
        if (animator2 != null) {
            DynamicIslandAnimationDelegate animator3 = getAnimator(dynamicIslandContentView);
            boolean z2 = false;
            if (animator3 != null && animator3.getAppClose()) {
                z2 = true;
            }
            animator2.smallIslandToHiddenAnimation(dynamicIslandContentView, z2);
        }
    }

    private final void smallIslandToHiddenNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToHiddenNoAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandToHiddenNoAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandToMiniWindowExpandedAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToMiniWindowExpandedAnimation");
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandToMiniWindowExpandedAnimation(dynamicIslandContentView);
        }
    }

    private final void smallIslandToTempHiddenAnimation(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandWindowState windowState = dynamicIslandContentView.getWindowState();
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "smallIslandToTempHiddenAnimation: " + dynamicIslandContentView + " view.windowState.screenLocked: " + (windowState != null ? windowState.getScreenLocked() : null));
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.smallIslandToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    private final void tempHiddenToBigIslandAnimation(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "tempHiddenToBigIslandAnimation: " + dynamicIslandContentView + " noAnim=" + z2);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.tempHiddenToBigIslandAnimation(dynamicIslandContentView, z2);
        }
    }

    private final void tempHiddenToSmallIslandAnimation(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "tempHiddenToSmallIslandAnimation: " + dynamicIslandContentView + " noAnim=" + z2);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.tempHiddenToSmallIslandAnimation(dynamicIslandContentView, z2);
        }
    }

    private final void updateOrientationBigIslandToTempHiddenNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "updateOrientationBigIslandToTempHiddenNoAnimation: " + dynamicIslandContentView);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.updateOrientationBigIslandToTempHiddenNoAnimation(dynamicIslandContentView);
        }
    }

    private final void updateOrientationSmallIslandToTempHiddenNoAnimation(DynamicIslandContentView dynamicIslandContentView) {
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "updateOrientationSmallIslandToTempHiddenNoAnimation: " + dynamicIslandContentView);
        DynamicIslandAnimationDelegate animator = getAnimator(dynamicIslandContentView);
        if (animator != null) {
            animator.updateOrientationSmallIslandToTempHiddenAnimation(dynamicIslandContentView);
        }
    }

    public final boolean canExpandedViewTrack(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        DynamicIslandAnimationDelegate animator2;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "canExpandedViewTrack: " + ((dynamicIslandContentView == null || (animator2 = getAnimator(dynamicIslandContentView)) == null) ? null : Float.valueOf(animator2.getExpandedViewAnimatingProgress())));
        return ((dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) ? 1.0f : animator.getExpandedViewAnimatingProgress()) > 0.8f;
    }

    public final miui.systemui.dynamicisland.anim.DynamicIslandAnimationCallback getAnimationCallback$miui_dynamicisland_release() {
        return this.animationCallback;
    }

    public final DynamicIslandState getCurrentBigIsland() {
        return this.currentBigIsland;
    }

    public final DynamicIslandState getCurrentExpanded() {
        return this.currentExpanded;
    }

    public final DynamicIslandWindowState getDynamicIslandWindowState() {
        return this.dynamicIslandWindowState;
    }

    public final DynamicIslandState getLastExpanded() {
        return this.lastExpanded;
    }

    public final int getSmallIslandX() {
        Context context;
        DynamicIslandContentView current = getCallback().getBigIslandStateHandler().getCurrent();
        if (current != null && (context = current.getContext()) != null && CommonUtils.isLayoutRtl(context)) {
            return (DynamicIslandBaseContentView.getCurrentBigIslandX$default(current, null, 1, null) - current.getSpace()) - current.getSmallIslandViewWidth();
        }
        return (current != null ? DynamicIslandBaseContentView.getCurrentBigIslandX$default(current, null, 1, null) : 0) + (current != null ? DynamicIslandBaseContentView.getCurrentBigIslandWidth$default(current, null, 1, null) : 0) + (current != null ? current.getSpace() : 0);
    }

    public final boolean isWindowAnimating(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return false;
        }
        return animator.getIslandWindowAnimRunning();
    }

    public final boolean islandAppAnimating(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return false;
        }
        return animator.getIslandAppAnimRunning();
    }

    public final boolean islandFreeformAnimating(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return false;
        }
        return animator.getIslandFreeformAnimRunning();
    }

    public final void onPress(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return;
        }
        animator.onPress(dynamicIslandContentView);
    }

    public final void onStateChange(DynamicIslandContentView dynamicIslandContentView) {
        SmallIslandStateHandler smallIslandStateHandler;
        DynamicIslandContentView current;
        DynamicIslandAnimationDelegate animator;
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "view: " + dynamicIslandContentView);
        if (dynamicIslandContentView == null) {
            return;
        }
        if (dynamicIslandContentView.getBigIslandViewWidth() == 0) {
            Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "view?.bigIslandViewWidth == 0");
            dynamicIslandContentView.calculateBigIslandWidth();
        }
        boolean z2 = false;
        if (dynamicIslandContentView.getVisibility() == 8) {
            dynamicIslandContentView.setVisibility(0);
        }
        getCallback().onAnimationStart(needUpdateWindowHeight(dynamicIslandContentView), dynamicIslandContentView);
        if (((dynamicIslandContentView.getLastState() instanceof DynamicIslandState.Expanded) || (dynamicIslandContentView.getState() instanceof DynamicIslandState.Expanded)) && (!(dynamicIslandContentView.getLastState() instanceof DynamicIslandState.Expanded) || !(dynamicIslandContentView.getState() instanceof DynamicIslandState.Expanded))) {
            updateExpandedViewMiniBar(dynamicIslandContentView);
        }
        DynamicIslandWindowState dynamicIslandWindowState = this.dynamicIslandWindowState;
        DynamicIslandData currentIslandData = dynamicIslandContentView.getCurrentIslandData();
        Boolean bool = null;
        boolean zIsTempHidden = dynamicIslandWindowState.isTempHidden(currentIslandData != null ? currentIslandData.getProperties() : null);
        DynamicIslandContentFakeView fakeView = dynamicIslandContentView.getFakeView();
        if (fakeView != null) {
            fakeView.onStateChanged(dynamicIslandContentView);
        }
        Boolean tempHiddenChange = this.dynamicIslandWindowState.getTempHiddenChange();
        Boolean configChange = this.dynamicIslandWindowState.getConfigChange();
        boolean zIsToScreenLockNoAnimation = this.dynamicIslandWindowState.isToScreenLockNoAnimation();
        if (!(dynamicIslandContentView.getState() instanceof DynamicIslandState.Hidden) && (animator = getAnimator(dynamicIslandContentView)) != null) {
            animator.setHiddenStateFrom(null);
        }
        Log.e(DynamicIslandConstants.TAG_DEBUG_ANIM, "tempHiddenChange=" + tempHiddenChange + " isTempHidden=" + zIsTempHidden + " isToScreenLockNoAnimation=" + zIsToScreenLockNoAnimation + " configChange=" + configChange);
        BoostHelper.getInstance().setDynamicVIPTaskIfNeeded(1000, dynamicIslandContentView);
        BoostHelper.getInstance().requestDynamicIslandThreadLevelPriority(1000, dynamicIslandContentView);
        DynamicIslandState lastState = dynamicIslandContentView.getLastState();
        if (lastState instanceof DynamicIslandState.Init) {
            DynamicIslandState state = dynamicIslandContentView.getState();
            if (state instanceof DynamicIslandState.Expanded) {
                initToExpandedAnimation(dynamicIslandContentView);
                z2 = true;
            } else if (state instanceof DynamicIslandState.SmallIsland) {
                if (zIsTempHidden) {
                    updateOrientationSmallIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                } else {
                    initToSmallIslandAnimation(dynamicIslandContentView);
                }
            } else if (!(state instanceof DynamicIslandState.BigIsland)) {
                if (state instanceof DynamicIslandState.AppExpanded ? true : state instanceof DynamicIslandState.SubAppExpanded) {
                    initToAppExpandedAnimation(dynamicIslandContentView);
                } else {
                    if (state instanceof DynamicIslandState.MiniWindowExpanded ? true : state instanceof DynamicIslandState.SubMiniWindowExpanded) {
                        initToMiniWindowExpandedAnimation(dynamicIslandContentView);
                    } else if (state instanceof DynamicIslandState.Deleted) {
                        hiddenToDeletedAnimation(dynamicIslandContentView);
                    }
                }
            } else if (zIsTempHidden) {
                updateOrientationBigIslandToTempHiddenNoAnimation(dynamicIslandContentView);
            } else {
                initToBigIslandAnimation(dynamicIslandContentView);
            }
        } else if (lastState instanceof DynamicIslandState.SmallIsland) {
            DynamicIslandState state2 = dynamicIslandContentView.getState();
            if (state2 instanceof DynamicIslandState.Expanded) {
                smallIslandToExpandedAnimation(dynamicIslandContentView);
            } else if (state2 instanceof DynamicIslandState.BigIsland) {
                smallIslandToBigIslandAnimation(dynamicIslandContentView);
                if (zIsTempHidden) {
                    if (n.c(configChange, Boolean.TRUE)) {
                        updateOrientationBigIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                    } else {
                        bigIslandToTempHiddenAnimation(dynamicIslandContentView);
                    }
                }
            } else if (state2 instanceof DynamicIslandState.SmallIsland) {
                if (zIsToScreenLockNoAnimation) {
                    beforeSmallIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
                if (!zIsTempHidden) {
                    Boolean bool2 = Boolean.TRUE;
                    if (n.c(tempHiddenChange, bool2)) {
                        if (n.c(configChange, bool2)) {
                            tempHiddenToSmallIslandAnimation(dynamicIslandContentView, true);
                        } else {
                            tempHiddenToSmallIslandAnimation(dynamicIslandContentView, false);
                        }
                    } else if (n.c(configChange, bool2)) {
                        smallIslandChangedNoAnimation(dynamicIslandContentView);
                    } else if (!zIsToScreenLockNoAnimation) {
                        smallIslandChangedAnimation(dynamicIslandContentView);
                    }
                } else if (n.c(configChange, Boolean.TRUE) || zIsToScreenLockNoAnimation) {
                    updateOrientationSmallIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                } else {
                    smallIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
            } else if (state2 instanceof DynamicIslandState.Hidden) {
                if (n.c(configChange, Boolean.TRUE) || zIsToScreenLockNoAnimation) {
                    smallIslandToHiddenNoAnimation(dynamicIslandContentView);
                } else {
                    smallIslandToHiddenAnimation(dynamicIslandContentView);
                }
                getCallback().onStateChange(DynamicIslandConstants.SMALL_TO_HIDDEN, dynamicIslandContentView);
            } else if (state2 instanceof DynamicIslandState.SubAppExpanded) {
                smallIslandToHiddenAnimation(dynamicIslandContentView);
            } else if (state2 instanceof DynamicIslandState.AppExpanded) {
                smallIslandToAppExpandedAnimation(dynamicIslandContentView);
            } else if (state2 instanceof DynamicIslandState.SubMiniWindowExpanded) {
                smallIslandToHiddenAnimation(dynamicIslandContentView);
            } else if (state2 instanceof DynamicIslandState.MiniWindowExpanded) {
                smallIslandToMiniWindowExpandedAnimation(dynamicIslandContentView);
            } else if (state2 instanceof DynamicIslandState.Deleted) {
                smallIslandToDeletedAnimation(dynamicIslandContentView);
            }
        } else if (lastState instanceof DynamicIslandState.BigIsland) {
            DynamicIslandState state3 = dynamicIslandContentView.getState();
            if (state3 instanceof DynamicIslandState.Expanded) {
                bigIslandToExpandedAnimation(dynamicIslandContentView);
            } else if (state3 instanceof DynamicIslandState.BigIsland) {
                if (zIsToScreenLockNoAnimation) {
                    beforeBigIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
                if (!zIsTempHidden) {
                    Boolean bool3 = Boolean.TRUE;
                    if (n.c(tempHiddenChange, bool3)) {
                        if (n.c(configChange, bool3)) {
                            tempHiddenToBigIslandAnimation(dynamicIslandContentView, true);
                        } else {
                            tempHiddenToBigIslandAnimation(dynamicIslandContentView, false);
                        }
                    } else if (n.c(configChange, bool3)) {
                        bigIslandChangedNoAnimation(dynamicIslandContentView);
                    } else if (!zIsToScreenLockNoAnimation) {
                        bigIslandChangedAnimation(dynamicIslandContentView);
                    }
                } else if (n.c(configChange, Boolean.TRUE) || zIsToScreenLockNoAnimation) {
                    updateOrientationBigIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                } else {
                    bigIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
            } else if (state3 instanceof DynamicIslandState.SmallIsland) {
                if (zIsToScreenLockNoAnimation) {
                    beforeSmallIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
                if (zIsTempHidden) {
                    if (n.c(configChange, Boolean.TRUE)) {
                        updateOrientationSmallIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                    } else {
                        smallIslandToTempHiddenAnimation(dynamicIslandContentView);
                    }
                } else if (n.c(configChange, Boolean.TRUE)) {
                    bigIslandToSmallIslandNoAnimation(dynamicIslandContentView);
                } else {
                    bigIslandToSmallIslandAnimation(dynamicIslandContentView);
                }
            } else if (state3 instanceof DynamicIslandState.Hidden) {
                bigIslandToHiddenAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.BIG_TO_HIDDEN, dynamicIslandContentView);
            } else if (state3 instanceof DynamicIslandState.SubAppExpanded) {
                bigIslandToHiddenAnimation(dynamicIslandContentView);
            } else if (state3 instanceof DynamicIslandState.AppExpanded) {
                bigIslandToAppExpandedAnimation(dynamicIslandContentView);
            } else if (state3 instanceof DynamicIslandState.SubMiniWindowExpanded) {
                bigIslandToHiddenAnimation(dynamicIslandContentView);
            } else if (state3 instanceof DynamicIslandState.MiniWindowExpanded) {
                bigIslandToMiniWindowExpandedAnimation(dynamicIslandContentView);
            } else if (state3 instanceof DynamicIslandState.Deleted) {
                bigIslandToDeletedAnimation(dynamicIslandContentView);
            }
        } else if (lastState instanceof DynamicIslandState.Expanded) {
            DynamicIslandState state4 = dynamicIslandContentView.getState();
            if (state4 instanceof DynamicIslandState.BigIsland) {
                if (zIsToScreenLockNoAnimation) {
                    expandedToTempHiddenNoAnimation(dynamicIslandContentView);
                }
                if (zIsTempHidden) {
                    if (n.c(configChange, Boolean.TRUE)) {
                        expandedToTempHiddenNoAnimation(dynamicIslandContentView);
                    } else {
                        expandedToTempHiddenAnimation(dynamicIslandContentView);
                    }
                } else if (n.c(configChange, Boolean.TRUE)) {
                    expandedToBigIslandNoAnimation(dynamicIslandContentView);
                } else {
                    expandedToBigIslandAnimation(dynamicIslandContentView);
                }
                getCallback().onStateChange(DynamicIslandConstants.EXPANDED_TO_BIG, dynamicIslandContentView);
            } else if (state4 instanceof DynamicIslandState.SmallIsland) {
                if (zIsToScreenLockNoAnimation) {
                    expandedToTempHiddenNoAnimation(dynamicIslandContentView);
                }
                if (zIsTempHidden) {
                    if (n.c(configChange, Boolean.TRUE)) {
                        expandedToTempHiddenNoAnimation(dynamicIslandContentView);
                    } else {
                        expandedToTempHiddenAnimation(dynamicIslandContentView);
                    }
                } else if (n.c(configChange, Boolean.TRUE)) {
                    expandedToSmallIslandNoAnimation(dynamicIslandContentView);
                } else {
                    expandedToSmallIslandAnimation(dynamicIslandContentView);
                }
            } else {
                if (state4 instanceof DynamicIslandState.Expanded) {
                    expandedChangedAnimation(dynamicIslandContentView);
                } else if (state4 instanceof DynamicIslandState.AppExpanded) {
                    expandedToAppExpandedAnimation(dynamicIslandContentView);
                } else if (state4 instanceof DynamicIslandState.MiniWindowExpanded) {
                    expandedToMiniWindowAnimation(dynamicIslandContentView);
                } else if (state4 instanceof DynamicIslandState.Hidden) {
                    if (zIsToScreenLockNoAnimation) {
                        expandedToTempHiddenNoAnimation(dynamicIslandContentView);
                    }
                    if (!zIsTempHidden) {
                        expandedToHiddenAnimation(dynamicIslandContentView);
                        DynamicIslandEventCoordinator dynamicIslandEventCoordinator = dynamicIslandContentView.getDynamicIslandEventCoordinator();
                        if (dynamicIslandEventCoordinator != null && (smallIslandStateHandler = dynamicIslandEventCoordinator.getSmallIslandStateHandler()) != null && (current = smallIslandStateHandler.getCurrent()) != null) {
                            smallIslandScaleAnimation(current);
                        }
                    } else if (n.c(configChange, Boolean.TRUE)) {
                        expandedToTempHiddenNoAnimation(dynamicIslandContentView);
                    } else {
                        expandedToTempHiddenAnimation(dynamicIslandContentView);
                    }
                    getCallback().onStateChange(DynamicIslandConstants.EXPANDED_TO_HIDDEN, dynamicIslandContentView);
                } else if (state4 instanceof DynamicIslandState.Deleted) {
                    if (zIsToScreenLockNoAnimation) {
                        expandedToDeletedNoAnimation(dynamicIslandContentView);
                    }
                    DynamicIslandState state5 = dynamicIslandContentView.getState();
                    if (state5 != null && state5.getDeleteNoAnimation()) {
                        z2 = true;
                    }
                    if (z2) {
                        expandedToDeletedNoAnimation(dynamicIslandContentView);
                    } else {
                        expandedToDeletedAnimation(dynamicIslandContentView);
                    }
                }
                z2 = true;
            }
        } else if (lastState instanceof DynamicIslandState.SubAppExpanded) {
            DynamicIslandState state6 = dynamicIslandContentView.getState();
            if (state6 instanceof DynamicIslandState.BigIsland) {
                if (!zIsTempHidden) {
                    hiddenToBigIslandAnimation(dynamicIslandContentView);
                }
            } else if (state6 instanceof DynamicIslandState.SmallIsland) {
                if (!zIsTempHidden) {
                    hiddenToSmallIslandAnimation(dynamicIslandContentView);
                } else if (n.c(configChange, Boolean.TRUE)) {
                    updateOrientationSmallIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                } else {
                    smallIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
            } else if (state6 instanceof DynamicIslandState.SubAppExpanded) {
                if (zIsToScreenLockNoAnimation) {
                    hiddenToTempHiddenNoAnimation(dynamicIslandContentView);
                }
                if (!zIsTempHidden) {
                    hiddenChangedAnimation(dynamicIslandContentView);
                } else if (n.c(configChange, Boolean.TRUE)) {
                    hiddenChangedNoAnimation(dynamicIslandContentView);
                } else {
                    hiddenToTempHiddenAnimation(dynamicIslandContentView);
                }
            } else if (state6 instanceof DynamicIslandState.Hidden) {
                expandedToHiddenAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.SUB_APP_TO_HIDDEN, dynamicIslandContentView);
            } else if (state6 instanceof DynamicIslandState.Deleted) {
                hiddenToDeletedAnimation(dynamicIslandContentView);
            }
        } else if (lastState instanceof DynamicIslandState.AppExpanded) {
            DynamicIslandState state7 = dynamicIslandContentView.getState();
            if (state7 instanceof DynamicIslandState.BigIsland) {
                appExpandedToBigIslandAnimation(dynamicIslandContentView);
                if (zIsTempHidden) {
                    if (n.c(configChange, Boolean.TRUE)) {
                        updateOrientationBigIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                    } else {
                        bigIslandToTempHiddenAnimation(dynamicIslandContentView);
                    }
                }
            } else if (state7 instanceof DynamicIslandState.SmallIsland) {
                if (!zIsTempHidden) {
                    appExpandedToSmallIslandAnimation(dynamicIslandContentView);
                } else if (n.c(configChange, Boolean.TRUE)) {
                    updateOrientationSmallIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                } else {
                    smallIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
            } else if (state7 instanceof DynamicIslandState.Hidden) {
                appExpandedToHiddenAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.APP_TO_HIDDEN, dynamicIslandContentView);
            } else if (state7 instanceof DynamicIslandState.Deleted) {
                hiddenToDeletedAnimation(dynamicIslandContentView);
            } else if (state7 instanceof DynamicIslandState.MiniWindowExpanded) {
                appExpandedToMiniWindowExpandedAnimation(dynamicIslandContentView);
            } else if (state7 instanceof DynamicIslandState.AppExpanded) {
                appExpandedToAppExpandedAnimation(dynamicIslandContentView);
                if (zIsTempHidden) {
                    hiddenChangedNoAnimation(dynamicIslandContentView);
                } else {
                    hiddenChangedAnimation(dynamicIslandContentView);
                }
            }
        } else if (lastState instanceof DynamicIslandState.SubMiniWindowExpanded) {
            DynamicIslandState state8 = dynamicIslandContentView.getState();
            if (state8 instanceof DynamicIslandState.BigIsland) {
                if (!zIsTempHidden) {
                    hiddenToBigIslandAnimation(dynamicIslandContentView);
                }
            } else if (state8 instanceof DynamicIslandState.SmallIsland) {
                if (zIsTempHidden) {
                    miniWindowToTempHiddenAnimation(dynamicIslandContentView);
                } else {
                    hiddenToSmallIslandAnimation(dynamicIslandContentView);
                }
            } else if (state8 instanceof DynamicIslandState.Hidden) {
                if (zIsTempHidden) {
                    miniWindowToTempHiddenAnimation(dynamicIslandContentView);
                } else {
                    hiddenToTempHiddenAnimation(dynamicIslandContentView);
                }
                getCallback().onStateChange(DynamicIslandConstants.SUB_MINI_WINDOW_TO_HIDDEN, dynamicIslandContentView);
            } else if (state8 instanceof DynamicIslandState.SubMiniWindowExpanded) {
                if (zIsToScreenLockNoAnimation) {
                    hiddenToTempHiddenNoAnimation(dynamicIslandContentView);
                }
                if (!zIsTempHidden) {
                    hiddenChangedAnimation(dynamicIslandContentView);
                } else if (n.c(configChange, Boolean.TRUE)) {
                    hiddenChangedNoAnimation(dynamicIslandContentView);
                } else {
                    hiddenToTempHiddenAnimation(dynamicIslandContentView);
                }
            } else if (state8 instanceof DynamicIslandState.Deleted) {
                hiddenToDeletedAnimation(dynamicIslandContentView);
            }
        } else if (lastState instanceof DynamicIslandState.MiniWindowExpanded) {
            DynamicIslandState state9 = dynamicIslandContentView.getState();
            if (state9 instanceof DynamicIslandState.BigIsland) {
                if (zIsTempHidden) {
                    miniWindowToTempHiddenAnimation(dynamicIslandContentView);
                } else {
                    miniWindowToBigIslandAnimation(dynamicIslandContentView);
                }
            } else if (state9 instanceof DynamicIslandState.SmallIsland) {
                if (zIsTempHidden) {
                    miniWindowToTempHiddenAnimation(dynamicIslandContentView);
                } else {
                    miniWindowToSmallIslandAnimation(dynamicIslandContentView);
                }
            } else if (state9 instanceof DynamicIslandState.MiniWindowExpanded) {
                if (zIsToScreenLockNoAnimation) {
                    miniWindowToTempHiddenNoAnimation(dynamicIslandContentView);
                }
                if (zIsTempHidden) {
                    hiddenChangedNoAnimation(dynamicIslandContentView);
                } else {
                    hiddenChangedAnimation(dynamicIslandContentView);
                }
            } else if (state9 instanceof DynamicIslandState.AppExpanded) {
                miniWindowToAppExpanded(dynamicIslandContentView);
            } else if (state9 instanceof DynamicIslandState.Hidden) {
                expandedToHiddenAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.MINI_WINDOW_TO_HIDDEN, dynamicIslandContentView);
            } else if (state9 instanceof DynamicIslandState.Deleted) {
                hiddenToDeletedAnimation(dynamicIslandContentView);
            }
        } else if (lastState instanceof DynamicIslandState.Hidden) {
            DynamicIslandState state10 = dynamicIslandContentView.getState();
            if (state10 instanceof DynamicIslandState.BigIsland) {
                if (!zIsTempHidden) {
                    hiddenToBigIslandAnimation(dynamicIslandContentView);
                }
                getCallback().onStateChange(DynamicIslandConstants.HIDDEN_TO_BIG, dynamicIslandContentView);
            } else if (state10 instanceof DynamicIslandState.SmallIsland) {
                if (!zIsTempHidden) {
                    hiddenToSmallIslandAnimation(dynamicIslandContentView);
                } else if (n.c(configChange, Boolean.TRUE)) {
                    updateOrientationSmallIslandToTempHiddenNoAnimation(dynamicIslandContentView);
                } else {
                    smallIslandToTempHiddenAnimation(dynamicIslandContentView);
                }
                getCallback().onStateChange(DynamicIslandConstants.HIDDEN_TO_SMALL, dynamicIslandContentView);
            } else if (state10 instanceof DynamicIslandState.Expanded) {
                initToExpandedAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.HIDDEN_TO_EXPANDED, dynamicIslandContentView);
            } else if (state10 instanceof DynamicIslandState.Hidden) {
                if (zIsToScreenLockNoAnimation) {
                    hiddenToTempHiddenNoAnimation(dynamicIslandContentView);
                }
                if (!zIsTempHidden) {
                    hiddenChangedAnimation(dynamicIslandContentView);
                } else if (n.c(configChange, Boolean.TRUE)) {
                    hiddenChangedNoAnimation(dynamicIslandContentView);
                } else {
                    hiddenToTempHiddenAnimation(dynamicIslandContentView);
                }
            } else if (state10 instanceof DynamicIslandState.Deleted) {
                hiddenToDeletedAnimation(dynamicIslandContentView);
            } else if (state10 instanceof DynamicIslandState.SubMiniWindowExpanded) {
                hiddenToTempHiddenAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.HIDDEN_TO_SUB_MINI_WINDOW, dynamicIslandContentView);
            } else if (state10 instanceof DynamicIslandState.MiniWindowExpanded) {
                hiddenToMiniWindowExpandedAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.HIDDEN_TO_MINI_WINDOW, dynamicIslandContentView);
            } else if (state10 instanceof DynamicIslandState.SubAppExpanded) {
                hiddenToTempHiddenAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.HIDDEN_TO_SUB_APP, dynamicIslandContentView);
            } else if (state10 instanceof DynamicIslandState.AppExpanded) {
                hiddenToAppExpandedAnimation(dynamicIslandContentView);
                getCallback().onStateChange(DynamicIslandConstants.HIDDEN_TO_APP, dynamicIslandContentView);
            }
        }
        DynamicIslandState lastState2 = dynamicIslandContentView.getLastState();
        if (lastState2 instanceof DynamicIslandState.Init ? true : lastState2 instanceof DynamicIslandState.SmallIsland ? true : lastState2 instanceof DynamicIslandState.BigIsland ? true : lastState2 instanceof DynamicIslandState.Expanded) {
            DynamicIslandState state11 = dynamicIslandContentView.getState();
            if (state11 instanceof DynamicIslandState.AppExpanded ? true : state11 instanceof DynamicIslandState.SubAppExpanded ? true : state11 instanceof DynamicIslandState.MiniWindowExpanded ? true : state11 instanceof DynamicIslandState.SubMiniWindowExpanded) {
                bool = Boolean.TRUE;
            }
        } else {
            if (lastState2 instanceof DynamicIslandState.AppExpanded ? true : lastState2 instanceof DynamicIslandState.SubAppExpanded ? true : lastState2 instanceof DynamicIslandState.MiniWindowExpanded ? true : lastState2 instanceof DynamicIslandState.SubMiniWindowExpanded) {
                DynamicIslandState state12 = dynamicIslandContentView.getState();
                if (!(state12 instanceof DynamicIslandState.BigIsland ? true : state12 instanceof DynamicIslandState.SmallIsland)) {
                    if (state12 instanceof DynamicIslandState.AppExpanded ? true : state12 instanceof DynamicIslandState.SubAppExpanded ? true : state12 instanceof DynamicIslandState.MiniWindowExpanded ? true : state12 instanceof DynamicIslandState.SubMiniWindowExpanded) {
                        bool = Boolean.TRUE;
                    }
                } else if (!zIsTempHidden) {
                    bool = Boolean.FALSE;
                }
            }
        }
        if ((!zIsTempHidden || z2) && !zIsToScreenLockNoAnimation) {
            Boolean bool4 = Boolean.TRUE;
            if (!n.c(bool, bool4)) {
                if (n.c(tempHiddenChange, bool4) || n.c(bool, Boolean.FALSE)) {
                    getCallback().onStateChange(DynamicIslandConstants.TEMP_HIDDEN_TO_SHOW, dynamicIslandContentView);
                    return;
                }
                return;
            }
        }
        getCallback().onStateChange(DynamicIslandConstants.SHOW_TO_TEMP_HIDDEN, dynamicIslandContentView);
    }

    public final void resetContainerAlpha(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return;
        }
        animator.resetContainerAlpha();
    }

    public final void resetFakeViewAnimState(DynamicIslandContentView dynamicIslandContentView, boolean z2) {
        DynamicIslandAnimationDelegate animator;
        Log.d(DynamicIslandConstants.TAG_DEBUG_ANIM, "resetFakeViewAnimState: " + z2);
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return;
        }
        animator.resetFakeViewAnimState(dynamicIslandContentView, z2);
    }

    public final void resetPress(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return;
        }
        animator.resetPress(dynamicIslandContentView);
    }

    public final void setCurrentBigIsland(DynamicIslandState dynamicIslandState) {
        this.currentBigIsland = dynamicIslandState;
    }

    public final void setCurrentExpanded(DynamicIslandState dynamicIslandState) {
        this.currentExpanded = dynamicIslandState;
    }

    public final void setLastExpanded(DynamicIslandState dynamicIslandState) {
        this.lastExpanded = dynamicIslandState;
    }

    public final void showBigIsland(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandExpandedView expandedView = view.getExpandedView();
        if (expandedView != null) {
            expandedView.setVisibility(4);
        }
        DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
        if (bigIslandView != null) {
            bigIslandView.setVisibility(0);
        }
        FrameLayout smallIslandView = view.getSmallIslandView();
        if (smallIslandView != null) {
            smallIslandView.setVisibility(8);
        }
        DynamicIslandBigIslandView bigIslandView2 = view.getBigIslandView();
        if (bigIslandView2 == null) {
            return;
        }
        bigIslandView2.setAlpha(1.0f);
    }

    public final void showSmallIsland(DynamicIslandContentView view) {
        n.g(view, "view");
        DynamicIslandExpandedView expandedView = view.getExpandedView();
        if (expandedView != null) {
            expandedView.setVisibility(4);
        }
        DynamicIslandBigIslandView bigIslandView = view.getBigIslandView();
        if (bigIslandView != null) {
            bigIslandView.setVisibility(8);
        }
        FrameLayout smallIslandView = view.getSmallIslandView();
        if (smallIslandView != null) {
            smallIslandView.setVisibility(0);
        }
        FrameLayout smallIslandView2 = view.getSmallIslandView();
        if (smallIslandView2 == null) {
            return;
        }
        smallIslandView2.setAlpha(1.0f);
    }

    public final void updateAppExpandedStateWhenAnimStart(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandAnimationDelegate animator;
        if (dynamicIslandContentView == null || (animator = getAnimator(dynamicIslandContentView)) == null) {
            return;
        }
        animator.expandedToHiddenNoAnim(dynamicIslandContentView);
    }

    public final void updateExpandedViewMiniBar(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandContentFakeView fakeView;
        if (dynamicIslandContentView != null) {
            dynamicIslandContentView.updateMiniBar(dynamicIslandContentView);
        }
        if (dynamicIslandContentView == null || (fakeView = dynamicIslandContentView.getFakeView()) == null) {
            return;
        }
        fakeView.updateMiniBar(dynamicIslandContentView);
    }

    public final void updateIslandWindowAnimRunning(boolean z2, DynamicIslandContentView dynamicIslandContentView, boolean z3) {
        DynamicIslandAnimationDelegate animator;
        if (z3) {
            u uVar = dynamicIslandContentView != null ? dynamicIslandContentView.get_islandFreeformAnimRunning() : null;
            if (uVar != null) {
                uVar.setValue(Boolean.valueOf(z2));
            }
            animator = dynamicIslandContentView != null ? getAnimator(dynamicIslandContentView) : null;
            if (animator == null) {
                return;
            }
            animator.setIslandFreeformAnimRunning(z2);
            return;
        }
        DynamicIslandAnimationDelegate animator2 = dynamicIslandContentView != null ? getAnimator(dynamicIslandContentView) : null;
        if (animator2 != null) {
            animator2.setAppClose(false);
        }
        u uVar2 = dynamicIslandContentView != null ? dynamicIslandContentView.get_islandAppAnimRunning() : null;
        if (uVar2 != null) {
            uVar2.setValue(Boolean.valueOf(z2));
        }
        animator = dynamicIslandContentView != null ? getAnimator(dynamicIslandContentView) : null;
        if (animator == null) {
            return;
        }
        animator.setIslandAppAnimRunning(z2);
    }
}
