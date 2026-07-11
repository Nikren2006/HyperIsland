package miui.systemui.controlcenter.panel.secondary.detail;

import android.os.SystemClock;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.plugins.qs.DetailAdapter;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;
import miui.systemui.controlcenter.panel.secondary.DetailPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import miui.systemui.controlcenter.widget.DetailPanelMoreButtonView;
import miui.systemui.controlcenter.widget.MaxHeightLinearLayout;
import miui.systemui.util.CommonUtils;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class DetailPanelCellAnimator extends SecondaryPanelDelegateBase<DetailPanelParams> {
    private static DetailPanelCellAnimator$Companion$COLOR$1 COLOR = null;
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle EASE_COLOR;
    private static final EaseManager.EaseStyle EASE_SIZE;
    private static DetailPanelCellAnimator$Companion$SIZE$1 SIZE = null;
    private static final String TAG = "DetailPanelCellAnimator";
    private final IStateStyle anim;
    private final DetailPanelCellAnimator$animListener$1 animListener;
    private AnimValue animValue;
    private AnimValue beforeAnimationValue;
    private SecondaryPanelAnimatorBase.ViewValue beforeContainer;
    private SecondaryPanelAnimatorBase.ViewValue beforeContentInner;
    private SecondaryPanelAnimatorBase.ViewValue beforeMore;
    private final DetailPanelBinding binding;
    private final Choreographer choreographer;
    private float color;
    private final Choreographer.FrameCallback frameCallback;
    private final View.OnLayoutChangeListener layoutChangeListener;
    private boolean needAnim;
    private boolean orientationChangeTrigger;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private float size;
    private int startAnimOrientation;
    private boolean startShown;
    private boolean updateScheduled;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator$Companion$SIZE$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator$Companion$COLOR$1] */
    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_SIZE = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_COLOR = easeStyleSpring2;
        SIZE = new FloatProperty<DetailPanelCellAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator$Companion$SIZE$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(DetailPanelCellAnimator anim) {
                n.g(anim, "anim");
                return anim.size;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(DetailPanelCellAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.size == f2) {
                    return;
                }
                anim.size = f2;
                anim.scheduleUpdate();
            }
        };
        COLOR = new FloatProperty<DetailPanelCellAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator$Companion$COLOR$1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(DetailPanelCellAnimator anim) {
                n.g(anim, "anim");
                return anim.color;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(DetailPanelCellAnimator anim, float f2) {
                n.g(anim, "anim");
                if (anim.color == f2) {
                    return;
                }
                anim.color = f2;
                anim.scheduleUpdate();
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v5, types: [miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator$animListener$1] */
    public DetailPanelCellAnimator(ControlCenterSecondaryBinding secondaryBinding, DetailPanelBinding binding) {
        super(secondaryBinding);
        n.g(secondaryBinding, "secondaryBinding");
        n.g(binding, "binding");
        this.secondaryBinding = secondaryBinding;
        this.binding = binding;
        this.choreographer = Choreographer.getInstance();
        this.anim = Folme.useValue(this);
        this.layoutChangeListener = new View.OnLayoutChangeListener() { // from class: miui.systemui.controlcenter.panel.secondary.detail.b
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                DetailPanelCellAnimator.layoutChangeListener$lambda$0(this.f5461a, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        };
        this.animListener = new TransitionListener() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator$animListener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                Log.w("DetailPanelCellAnimator", "anim onCancel");
                this.this$0.getVContainer().suppressLayout(false);
                this.this$0.size = 1.0f;
                this.this$0.color = 1.0f;
                DetailPanelCellAnimator detailPanelCellAnimator = this.this$0;
                detailPanelCellAnimator.startAnimOrientation = detailPanelCellAnimator.getContext().getResources().getConfiguration().orientation;
                this.this$0.scheduleUpdate();
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                Log.w("DetailPanelCellAnimator", "anim onComplete");
                this.this$0.getVContainer().suppressLayout(false);
            }
        };
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.controlcenter.panel.secondary.detail.c
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                DetailPanelCellAnimator.frameCallback$lambda$7(this.f5462a, j2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$7(DetailPanelCellAnimator this$0, long j2) {
        n.g(this$0, "this$0");
        this$0.updateScheduled = false;
        AnimValue animValue = this$0.animValue;
        if (animValue == null) {
            return;
        }
        if (this$0.startAnimOrientation != this$0.getContext().getResources().getConfiguration().orientation) {
            IStateStyle iStateStyle = this$0.anim;
            if (iStateStyle != null) {
                iStateStyle.cancel();
                return;
            }
            return;
        }
        boolean onlyBoundsUpdate = animValue.getOnlyBoundsUpdate();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        float top = animValue.getBeforeContainer().getTop() + ((animValue.getAfterContainer().getTop() - animValue.getBeforeContainer().getTop()) * this$0.size);
        float height = animValue.getBeforeContainer().getHeight() + ((animValue.getAfterContainer().getHeight() - animValue.getBeforeContainer().getHeight()) * this$0.size);
        float width = animValue.getAfterContentInner().getWidth() * this$0.size;
        float height2 = animValue.getAfterContentInner().getHeight() * this$0.size;
        float width2 = (animValue.getAfterContentInner().getWidth() - width) / 2;
        float top2 = animValue.getBeforeMore().getTop() + ((animValue.getAfterMore().getTop() - animValue.getBeforeMore().getTop()) * this$0.size);
        this$0.getVContainer().setLeftTopRightBottom(animValue.getAfterContainer().getLeft(), Y0.b.b(top), animValue.getAfterContainer().getLeft() + animValue.getAfterContainer().getWidth(), Y0.b.b(top + height));
        if (onlyBoundsUpdate) {
            animValue.getVContentInner().setLeftTopRightBottom(animValue.getAfterContentInner().getLeft(), animValue.getAfterContentInner().getTop(), animValue.getAfterContentInner().getWidth(), Y0.b.b(animValue.getAfterContentInner().getTop() + ((animValue.getAfterContentInner().getHeight() - animValue.getBeforeContentInner().getHeight()) * this$0.size) + animValue.getBeforeContentInner().getHeight()));
        } else {
            this$0.getVContent().setLeft(Y0.b.b(width2));
            View vContentInner = animValue.getVContentInner();
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            commonUtils.setAlphaEx(vContentInner, this$0.color);
            vContentInner.setPivotX(0.0f);
            vContentInner.setPivotY(0.0f);
            commonUtils.setScaleXEx(vContentInner, width / animValue.getAfterContentInner().getWidth());
            commonUtils.setScaleYEx(vContentInner, height2 / animValue.getAfterContentInner().getHeight());
        }
        this$0.getVMore().setLeftTopRightBottom(animValue.getAfterMore().getLeft(), Y0.b.b(top2), animValue.getAfterMore().getLeft() + animValue.getAfterMore().getWidth(), Y0.b.b(top2 + animValue.getAfterMore().getHeight()));
        Log.i(TAG, "frameCallback " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + "ms " + this$0.size + " " + this$0.color);
    }

    private final boolean getFromCell() {
        DetailAdapter adapter;
        DetailPanelParams panelParams = getPanelParams();
        if (panelParams == null || (adapter = panelParams.getAdapter()) == null) {
            return false;
        }
        return SecondaryParamsKt.fromCell(adapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MaxHeightLinearLayout getVContainer() {
        MaxHeightLinearLayout detailContainer = this.binding.detailContainer;
        n.f(detailContainer, "detailContainer");
        return detailContainer;
    }

    private final FrameLayout getVContent() {
        FrameLayout content = this.binding.content;
        n.f(content, "content");
        return content;
    }

    private final DetailPanelMoreButtonView getVMore() {
        DetailPanelMoreButtonView moreButton = this.binding.moreButton;
        n.f(moreButton, "moreButton");
        return moreButton;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void layoutChangeListener$lambda$0(DetailPanelCellAnimator this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        this$0.onLayoutChanged(i2, i3, i4, i5, i6, i7, i8, i9);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void onLayoutChanged(int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelCellAnimator.onLayoutChanged(int, int, int, int, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        this.choreographer.postFrameCallback(this.frameCallback);
    }

    private final void startAnim() {
        Log.i(TAG, "startAnim " + this.animValue);
        this.beforeAnimationValue = this.animValue;
        getVContainer().suppressLayout(true);
        this.size = 0.0f;
        this.color = 0.0f;
        this.startAnimOrientation = getContext().getResources().getConfiguration().orientation;
        this.frameCallback.doFrame(0L);
        AnimConfig animConfig = new AnimConfig();
        animConfig.addListeners(this.animListener);
        animConfig.setSpecial(SIZE, EASE_SIZE, new float[0]);
        animConfig.setSpecial(COLOR, EASE_COLOR, 100L, new float[0]);
        IStateStyle iStateStyle = this.anim;
        DetailPanelCellAnimator$Companion$SIZE$1 detailPanelCellAnimator$Companion$SIZE$1 = SIZE;
        Float fValueOf = Float.valueOf(1.0f);
        iStateStyle.to(detailPanelCellAnimator$Companion$SIZE$1, fValueOf, COLOR, fValueOf, animConfig);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        if (ConfigUtils.INSTANCE.orientationChanged(i2)) {
            this.needAnim = false;
            this.orientationChangeTrigger = true;
            IStateStyle iStateStyle = this.anim;
            if (iStateStyle != null) {
                iStateStyle.cancel();
            }
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        getVContainer().addOnLayoutChangeListener(this.layoutChangeListener);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        getVContainer().removeOnLayoutChangeListener(this.layoutChangeListener);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void onHidden() {
        super.onHidden();
        if (getFromCell()) {
            getVContainer().suppressLayout(false);
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareHide() {
        super.prepareHide();
        if (getFromCell()) {
            IStateStyle iStateStyle = this.anim;
            if (iStateStyle != null) {
                iStateStyle.cancel();
            }
            getVContainer().suppressLayout(false);
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void startShow() {
        super.startShow();
        if (getFromCell()) {
            this.needAnim = true;
            this.startShown = true;
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareShow(DetailPanelParams detailPanelParams) {
        super.prepareShow(detailPanelParams);
        if (getFromCell()) {
            this.needAnim = false;
            this.startShown = false;
            this.orientationChangeTrigger = false;
            this.animValue = null;
            getVContainer().suppressLayout(false);
            View childAt = getVContent().getChildAt(0);
            if (childAt != null) {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                commonUtils.setAlphaEx(childAt, 1.0f);
                commonUtils.setScaleXEx(childAt, 1.0f);
                commonUtils.setScaleYEx(childAt, 1.0f);
            }
        }
    }

    public static final class AnimValue {
        private final SecondaryPanelAnimatorBase.ViewValue afterContainer;
        private final SecondaryPanelAnimatorBase.ViewValue afterContentInner;
        private final SecondaryPanelAnimatorBase.ViewValue afterMore;
        private final SecondaryPanelAnimatorBase.ViewValue beforeContainer;
        private final SecondaryPanelAnimatorBase.ViewValue beforeContentInner;
        private final SecondaryPanelAnimatorBase.ViewValue beforeMore;
        private final boolean onlyBoundsUpdate;
        private final View vContentInner;

        public AnimValue(SecondaryPanelAnimatorBase.ViewValue beforeContainer, SecondaryPanelAnimatorBase.ViewValue beforeContentInner, SecondaryPanelAnimatorBase.ViewValue beforeMore, SecondaryPanelAnimatorBase.ViewValue afterContainer, SecondaryPanelAnimatorBase.ViewValue afterContentInner, SecondaryPanelAnimatorBase.ViewValue afterMore, View vContentInner, boolean z2) {
            n.g(beforeContainer, "beforeContainer");
            n.g(beforeContentInner, "beforeContentInner");
            n.g(beforeMore, "beforeMore");
            n.g(afterContainer, "afterContainer");
            n.g(afterContentInner, "afterContentInner");
            n.g(afterMore, "afterMore");
            n.g(vContentInner, "vContentInner");
            this.beforeContainer = beforeContainer;
            this.beforeContentInner = beforeContentInner;
            this.beforeMore = beforeMore;
            this.afterContainer = afterContainer;
            this.afterContentInner = afterContentInner;
            this.afterMore = afterMore;
            this.vContentInner = vContentInner;
            this.onlyBoundsUpdate = z2;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component1() {
            return this.beforeContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component2() {
            return this.beforeContentInner;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component3() {
            return this.beforeMore;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component4() {
            return this.afterContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component5() {
            return this.afterContentInner;
        }

        public final SecondaryPanelAnimatorBase.ViewValue component6() {
            return this.afterMore;
        }

        public final View component7() {
            return this.vContentInner;
        }

        public final boolean component8() {
            return this.onlyBoundsUpdate;
        }

        public final AnimValue copy(SecondaryPanelAnimatorBase.ViewValue beforeContainer, SecondaryPanelAnimatorBase.ViewValue beforeContentInner, SecondaryPanelAnimatorBase.ViewValue beforeMore, SecondaryPanelAnimatorBase.ViewValue afterContainer, SecondaryPanelAnimatorBase.ViewValue afterContentInner, SecondaryPanelAnimatorBase.ViewValue afterMore, View vContentInner, boolean z2) {
            n.g(beforeContainer, "beforeContainer");
            n.g(beforeContentInner, "beforeContentInner");
            n.g(beforeMore, "beforeMore");
            n.g(afterContainer, "afterContainer");
            n.g(afterContentInner, "afterContentInner");
            n.g(afterMore, "afterMore");
            n.g(vContentInner, "vContentInner");
            return new AnimValue(beforeContainer, beforeContentInner, beforeMore, afterContainer, afterContentInner, afterMore, vContentInner, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimValue)) {
                return false;
            }
            AnimValue animValue = (AnimValue) obj;
            return n.c(this.beforeContainer, animValue.beforeContainer) && n.c(this.beforeContentInner, animValue.beforeContentInner) && n.c(this.beforeMore, animValue.beforeMore) && n.c(this.afterContainer, animValue.afterContainer) && n.c(this.afterContentInner, animValue.afterContentInner) && n.c(this.afterMore, animValue.afterMore) && n.c(this.vContentInner, animValue.vContentInner) && this.onlyBoundsUpdate == animValue.onlyBoundsUpdate;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getAfterContainer() {
            return this.afterContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getAfterContentInner() {
            return this.afterContentInner;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getAfterMore() {
            return this.afterMore;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getBeforeContainer() {
            return this.beforeContainer;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getBeforeContentInner() {
            return this.beforeContentInner;
        }

        public final SecondaryPanelAnimatorBase.ViewValue getBeforeMore() {
            return this.beforeMore;
        }

        public final boolean getOnlyBoundsUpdate() {
            return this.onlyBoundsUpdate;
        }

        public final View getVContentInner() {
            return this.vContentInner;
        }

        public int hashCode() {
            return (((((((((((((this.beforeContainer.hashCode() * 31) + this.beforeContentInner.hashCode()) * 31) + this.beforeMore.hashCode()) * 31) + this.afterContainer.hashCode()) * 31) + this.afterContentInner.hashCode()) * 31) + this.afterMore.hashCode()) * 31) + this.vContentInner.hashCode()) * 31) + Boolean.hashCode(this.onlyBoundsUpdate);
        }

        public String toString() {
            return "AnimValue(beforeContainer=" + this.beforeContainer + ", beforeContentInner=" + this.beforeContentInner + ", beforeMore=" + this.beforeMore + ", afterContainer=" + this.afterContainer + ", afterContentInner=" + this.afterContentInner + ", afterMore=" + this.afterMore + ", vContentInner=" + this.vContentInner + ", onlyBoundsUpdate=" + this.onlyBoundsUpdate + ")";
        }

        public /* synthetic */ AnimValue(SecondaryPanelAnimatorBase.ViewValue viewValue, SecondaryPanelAnimatorBase.ViewValue viewValue2, SecondaryPanelAnimatorBase.ViewValue viewValue3, SecondaryPanelAnimatorBase.ViewValue viewValue4, SecondaryPanelAnimatorBase.ViewValue viewValue5, SecondaryPanelAnimatorBase.ViewValue viewValue6, View view, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(viewValue, viewValue2, viewValue3, viewValue4, viewValue5, viewValue6, view, (i2 & 128) != 0 ? false : z2);
        }
    }
}
