package miui.systemui.controlcenter.panel;

import E0.a;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ViewProperty;

/* JADX INFO: loaded from: classes.dex */
public abstract class SecondaryPanelBase<T extends ViewGroup, S> extends ControlCenterViewController<T> implements SecondaryPanelRouter.SecondaryPanel<S> {
    private static final int ANIM_TRANS_Y = 100;
    public static final Companion Companion = new Companion(null);
    private static final String STATE_HIDE = "qs_customizer_hide";
    private static final String STATE_SHOW = "qs_customizer_show";
    private IStateStyle anim;
    private Function0 completeAction;
    private final AnimState hideAnim;
    private final SecondaryPanelBase$hideListener$1 hideListener;
    private final a mainPanelController;
    private boolean pendingShowing;
    private final AnimState showAnim;
    private final SecondaryPanelBase$showListener$1 showListener;
    private final String tag;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r6v10, types: [miui.systemui.controlcenter.panel.SecondaryPanelBase$hideListener$1] */
    /* JADX WARN: Type inference failed for: r6v9, types: [miui.systemui.controlcenter.panel.SecondaryPanelBase$showListener$1] */
    public SecondaryPanelBase(T view, a mainPanelController) {
        super(view);
        n.g(view, "view");
        n.g(mainPanelController, "mainPanelController");
        this.mainPanelController = mainPanelController;
        String simpleName = getClass().getSimpleName();
        n.f(simpleName, "getSimpleName(...)");
        this.tag = simpleName;
        AnimState animState = new AnimState(STATE_SHOW);
        ViewProperty viewProperty = ViewProperty.ALPHA;
        AnimState animStateAdd = animState.add(viewProperty, 1.0f, new long[0]);
        ViewProperty viewProperty2 = ViewProperty.TRANSLATION_Y;
        AnimState animStateAdd2 = animStateAdd.add(viewProperty2, 0.0f, new long[0]);
        n.f(animStateAdd2, "add(...)");
        this.showAnim = animStateAdd2;
        AnimState animStateAdd3 = new AnimState(STATE_HIDE).add(viewProperty, 0.0f, new long[0]).add(viewProperty2, 100, new long[0]);
        n.f(animStateAdd3, "add(...)");
        this.hideAnim = animStateAdd3;
        this.showListener = new TransitionListener(this) { // from class: miui.systemui.controlcenter.panel.SecondaryPanelBase$showListener$1
            final /* synthetic */ SecondaryPanelBase<T, S> this$0;

            {
                this.this$0 = this;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                boolean z2;
                boolean z3 = true;
                this.this$0.onAnimUpdate(true);
                if (collection != null) {
                    loop0: while (true) {
                        for (UpdateInfo updateInfo : collection) {
                            z2 = z2 && updateInfo.isCompleted;
                        }
                    }
                    z3 = z2;
                }
                if (z3) {
                    Log.i(this.this$0.getTag(), "showListener onComplete");
                    Function0 function0 = ((SecondaryPanelBase) this.this$0).completeAction;
                    ((SecondaryPanelBase) this.this$0).completeAction = null;
                    if (function0 != null) {
                        function0.invoke();
                    }
                }
            }
        };
        this.hideListener = new TransitionListener(this) { // from class: miui.systemui.controlcenter.panel.SecondaryPanelBase$hideListener$1
            final /* synthetic */ SecondaryPanelBase<T, S> this$0;

            {
                this.this$0 = this;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                boolean z2;
                this.this$0.onAnimUpdate(false);
                boolean z3 = true;
                if (collection != null) {
                    loop0: while (true) {
                        for (UpdateInfo updateInfo : collection) {
                            z2 = z2 && updateInfo.isCompleted;
                        }
                    }
                    z3 = z2;
                }
                if (z3) {
                    Log.i(this.this$0.getTag(), "hideListener onComplete");
                    Function0 function0 = ((SecondaryPanelBase) this.this$0).completeAction;
                    ((SecondaryPanelBase) this.this$0).completeAction = null;
                    if (function0 != null) {
                        function0.invoke();
                    }
                }
            }
        };
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void cancelPrepare() {
        Log.i(getTag(), "cancelPrepare");
        if (!this.pendingShowing) {
            suppressLayout(false);
        } else {
            updateVisibility(8);
            this.pendingShowing = false;
        }
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void forceToHide() {
        Log.i(getTag(), "forceToHide");
        this.completeAction = null;
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
        IStateStyle iStateStyle2 = this.anim;
        if (iStateStyle2 != null) {
            iStateStyle2.setTo(getHideAnim());
        }
        onHidden(null);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void forceToShow(S s2) {
        Log.i(getTag(), "forceToShow");
        this.completeAction = null;
        prepareShow(s2);
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
        IStateStyle iStateStyle2 = this.anim;
        if (iStateStyle2 != null) {
            iStateStyle2.setTo(getShowAnim());
        }
        onShown();
    }

    public final IStateStyle getAnim() {
        return this.anim;
    }

    public AnimState getHideAnim() {
        return this.hideAnim;
    }

    public AnimState getShowAnim() {
        return this.showAnim;
    }

    public String getTag() {
        return this.tag;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public boolean isAnimating() {
        return this.completeAction != null;
    }

    public void onAnimUpdate(boolean z2) {
    }

    @Override // miui.systemui.util.ViewController
    @CallSuper
    public void onCreate() {
        super.onCreate();
        this.anim = Folme.useAt(getView()).state();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    @CallSuper
    public void onDestroy() {
        this.anim = null;
        Folme.clean(getView());
        this.completeAction = null;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void onHidden(SecondaryPanelRouter.SecondaryPanel<?> secondaryPanel) {
        Log.i(getTag(), "onHidden");
        suppressLayout(false);
        updateVisibility(8);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void onMainPanelHidden() {
        ((MainPanelController) this.mainPanelController.get()).scrollToTop();
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void onShown() {
        Log.i(getTag(), "onShown");
        suppressLayout(false);
        updateVisibility(0);
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void prepareHide() {
        Log.i(getTag(), "prepareHide");
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public boolean prepareShow(S s2) {
        Log.i(getTag(), "prepareShow");
        suppressLayout(false);
        updateVisibility(0);
        this.pendingShowing = true;
        return true;
    }

    public final void setAnim(IStateStyle iStateStyle) {
        this.anim = iStateStyle;
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void startHide(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        Log.i(getTag(), "startHide");
        suppressLayout(true);
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
        this.completeAction = completeAction;
        IStateStyle iStateStyle2 = this.anim;
        if (iStateStyle2 != null) {
            iStateStyle2.to(getHideAnim(), updateHideAnimConfig().addListeners(this.hideListener));
        }
    }

    @Override // miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    @CallSuper
    public void startShow(Function0 completeAction) {
        n.g(completeAction, "completeAction");
        Log.i(getTag(), "startShow");
        this.pendingShowing = false;
        IStateStyle iStateStyle = this.anim;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
        this.completeAction = completeAction;
        IStateStyle iStateStyle2 = this.anim;
        if (iStateStyle2 != null) {
            iStateStyle2.to(getShowAnim(), updateShowAnimConfig().setDelay(30L).addListeners(this.showListener));
        }
    }

    public void suppressLayout(boolean z2) {
        getView().suppressLayout(z2);
    }

    public AnimConfig updateHideAnimConfig() {
        AnimConfig ease = new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.18f));
        n.f(ease, "setEase(...)");
        return ease;
    }

    public AnimConfig updateShowAnimConfig() {
        AnimConfig ease = new AnimConfig().setEase(FolmeEase.spring(0.95f, 0.35f));
        n.f(ease, "setEase(...)");
        return ease;
    }

    public void updateVisibility(int i2) {
        getView().setVisibility(i2);
    }
}
