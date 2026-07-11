package miui.systemui.controlcenter.panel.secondary.detail;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.android.systemui.plugins.qs.DetailAdapter;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeUtilsExtKt;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.panel.secondary.DetailFromView;
import miui.systemui.controlcenter.panel.secondary.DetailPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.qs.tileview.QSCardItemView;
import miui.systemui.controlcenter.qs.tileview.QSItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miui.systemui.controlcenter.widget.MainPanelRecyclerView;
import miui.systemui.controlcenter.widget.MaxHeightLinearLayout;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.ImageView;
import miui.systemui.widget.LinearLayout;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class DetailPanelAnimator extends SecondaryPanelAnimatorBase<DetailPanelParams> {
    private final IStateStyle anim;
    private final DetailPanelAnimator$animListener$1 animListener;
    private AnimValue animValue;
    private float color;
    private final Context context;
    private final E0.a detailPanelController;
    private float fromContentColor;
    private DetailFromView fromView;
    private AnimValue lastAnimValue;
    private final E0.a mainPanelController;
    private float position;
    private float size;
    private final E0.a tilesDelegate;
    private float toContentColor;
    private final E0.a windowViewController;
    public static final Companion Companion = new Companion(null);
    private static DetailPanelAnimator$Companion$POSITION$1 POSITION = new FloatProperty<DetailPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator$Companion$POSITION$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DetailPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.position;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DetailPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.position == f2) {
                return;
            }
            anim.position = f2;
            anim.scheduleUpdate();
        }
    };
    private static DetailPanelAnimator$Companion$SIZE$1 SIZE = new FloatProperty<DetailPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator$Companion$SIZE$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DetailPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.size;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DetailPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.size == f2) {
                return;
            }
            anim.size = f2;
            anim.scheduleUpdate();
        }
    };
    private static DetailPanelAnimator$Companion$COLOR$1 COLOR = new FloatProperty<DetailPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator$Companion$COLOR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DetailPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.color;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DetailPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.color == f2) {
                return;
            }
            anim.color = f2;
            anim.scheduleUpdate();
        }
    };
    private static DetailPanelAnimator$Companion$FROM_CONTENT_COLOR$1 FROM_CONTENT_COLOR = new FloatProperty<DetailPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator$Companion$FROM_CONTENT_COLOR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DetailPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.fromContentColor;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DetailPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.fromContentColor == f2) {
                return;
            }
            anim.fromContentColor = f2;
            anim.scheduleUpdate();
        }
    };
    private static DetailPanelAnimator$Companion$TO_CONTENT_COLOR$1 TO_CONTENT_COLOR = new FloatProperty<DetailPanelAnimator>() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator$Companion$TO_CONTENT_COLOR$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DetailPanelAnimator anim) {
            n.g(anim, "anim");
            return anim.toContentColor;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DetailPanelAnimator anim, float f2) {
            n.g(anim, "anim");
            if (anim.toContentColor == f2) {
                return;
            }
            anim.toContentColor = f2;
            anim.scheduleUpdate();
        }
    };

    public static final class AnimValue {
        private final float fromCenterX;
        private final float fromCenterY;
        private final SecondaryPanelAnimatorBase.ViewLocValue fromFrame;
        private final SecondaryPanelAnimatorBase.ViewLocValue fromIcon;
        private final float fromIconCenterX;
        private final float fromIconCenterY;
        private final float fromRadius;
        private final float toCenterX;
        private final float toCenterY;
        private final SecondaryPanelAnimatorBase.ViewLocValue toFrame;
        private final float toRadius;

        public AnimValue(SecondaryPanelAnimatorBase.ViewLocValue fromFrame, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue, SecondaryPanelAnimatorBase.ViewLocValue toFrame, float f2, float f3) {
            n.g(fromFrame, "fromFrame");
            n.g(toFrame, "toFrame");
            this.fromFrame = fromFrame;
            this.fromIcon = viewLocValue;
            this.toFrame = toFrame;
            this.fromRadius = f2;
            this.toRadius = f3;
            float f4 = 2;
            this.fromCenterX = fromFrame.getLocLeft() + (fromFrame.getWidth() / f4);
            this.fromCenterY = fromFrame.getLocTop() + (fromFrame.getHeight() / f4);
            this.toCenterX = toFrame.getLocLeft() + (toFrame.getWidth() / f4);
            this.toCenterY = toFrame.getLocTop() + (toFrame.getHeight() / f4);
            this.fromIconCenterX = getFromIconLeft() + (getFromIconWidth() / f4);
            this.fromIconCenterY = getFromIconTop() + (getFromIconHeight() / f4);
        }

        public static /* synthetic */ AnimValue copy$default(AnimValue animValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue3, float f2, float f3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                viewLocValue = animValue.fromFrame;
            }
            if ((i2 & 2) != 0) {
                viewLocValue2 = animValue.fromIcon;
            }
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue4 = viewLocValue2;
            if ((i2 & 4) != 0) {
                viewLocValue3 = animValue.toFrame;
            }
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue5 = viewLocValue3;
            if ((i2 & 8) != 0) {
                f2 = animValue.fromRadius;
            }
            float f4 = f2;
            if ((i2 & 16) != 0) {
                f3 = animValue.toRadius;
            }
            return animValue.copy(viewLocValue, viewLocValue4, viewLocValue5, f4, f3);
        }

        private final int getFromIconHeight() {
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue = this.fromIcon;
            if (viewLocValue != null) {
                return viewLocValue.getHeight();
            }
            return 0;
        }

        private final int getFromIconLeft() {
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue = this.fromIcon;
            if (viewLocValue != null) {
                return viewLocValue.getLocLeft();
            }
            return 0;
        }

        private final int getFromIconTop() {
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue = this.fromIcon;
            if (viewLocValue != null) {
                return viewLocValue.getLocTop();
            }
            return 0;
        }

        private final int getFromIconWidth() {
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue = this.fromIcon;
            if (viewLocValue != null) {
                return viewLocValue.getWidth();
            }
            return 0;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component1() {
            return this.fromFrame;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component2() {
            return this.fromIcon;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue component3() {
            return this.toFrame;
        }

        public final float component4() {
            return this.fromRadius;
        }

        public final float component5() {
            return this.toRadius;
        }

        public final AnimValue copy(SecondaryPanelAnimatorBase.ViewLocValue fromFrame, SecondaryPanelAnimatorBase.ViewLocValue viewLocValue, SecondaryPanelAnimatorBase.ViewLocValue toFrame, float f2, float f3) {
            n.g(fromFrame, "fromFrame");
            n.g(toFrame, "toFrame");
            return new AnimValue(fromFrame, viewLocValue, toFrame, f2, f3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimValue)) {
                return false;
            }
            AnimValue animValue = (AnimValue) obj;
            return n.c(this.fromFrame, animValue.fromFrame) && n.c(this.fromIcon, animValue.fromIcon) && n.c(this.toFrame, animValue.toFrame) && Float.compare(this.fromRadius, animValue.fromRadius) == 0 && Float.compare(this.toRadius, animValue.toRadius) == 0;
        }

        public final float getFromCenterX() {
            return this.fromCenterX;
        }

        public final float getFromCenterY() {
            return this.fromCenterY;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromFrame() {
            return this.fromFrame;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getFromIcon() {
            return this.fromIcon;
        }

        public final float getFromIconCenterX() {
            return this.fromIconCenterX;
        }

        public final float getFromIconCenterY() {
            return this.fromIconCenterY;
        }

        public final float getFromRadius() {
            return this.fromRadius;
        }

        public final float getToCenterX() {
            return this.toCenterX;
        }

        public final float getToCenterY() {
            return this.toCenterY;
        }

        public final SecondaryPanelAnimatorBase.ViewLocValue getToFrame() {
            return this.toFrame;
        }

        public final float getToRadius() {
            return this.toRadius;
        }

        public int hashCode() {
            int iHashCode = this.fromFrame.hashCode() * 31;
            SecondaryPanelAnimatorBase.ViewLocValue viewLocValue = this.fromIcon;
            return ((((((iHashCode + (viewLocValue == null ? 0 : viewLocValue.hashCode())) * 31) + this.toFrame.hashCode()) * 31) + Float.hashCode(this.fromRadius)) * 31) + Float.hashCode(this.toRadius);
        }

        public String toString() {
            return "AnimValue(fromFrame=" + this.fromFrame + ", fromIcon=" + this.fromIcon + ", toFrame=" + this.toFrame + ", fromRadius=" + this.fromRadius + ", toRadius=" + this.toRadius + ")";
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v3, types: [miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator$animListener$1] */
    public DetailPanelAnimator(Context context, E0.a detailPanelController, E0.a windowViewController, E0.a mainPanelController, E0.a tilesDelegate) {
        super(context, mainPanelController);
        n.g(context, "context");
        n.g(detailPanelController, "detailPanelController");
        n.g(windowViewController, "windowViewController");
        n.g(mainPanelController, "mainPanelController");
        n.g(tilesDelegate, "tilesDelegate");
        this.context = context;
        this.detailPanelController = detailPanelController;
        this.windowViewController = windowViewController;
        this.mainPanelController = mainPanelController;
        this.tilesDelegate = tilesDelegate;
        this.anim = Folme.useValue(this);
        this.animListener = new TransitionListener() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator$animListener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                boolean z2 = true;
                if (collection != null) {
                    DetailPanelAnimator detailPanelAnimator = this.this$0;
                    boolean z3 = true;
                    for (UpdateInfo updateInfo : collection) {
                        z3 = z3 && updateInfo.isCompleted;
                        if (n.c(updateInfo.property, DetailPanelAnimator.COLOR)) {
                            detailPanelAnimator.doUpdateClipHeaderCheck(updateInfo.getFloatValue());
                        }
                    }
                    z2 = z3;
                }
                if (z2) {
                    this.this$0.onAnimComplete();
                }
            }
        };
        this.fromContentColor = 1.0f;
    }

    private final void calculateViewValues() {
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue;
        SecondaryPanelAnimatorBase.ViewLocValue fromFrame;
        AnimValue animValue;
        SecondaryPanelAnimatorBase.ViewLocValue toFrame;
        DetailFromView detailFromView = this.fromView;
        if (detailFromView == null) {
            return;
        }
        int[] iArr = new int[2];
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.getLocationInWindowWithoutTransform(detailFromView.getItemFrame(), iArr);
        SecondaryPanelAnimatorBase.ViewLocValue viewLocValue2 = new SecondaryPanelAnimatorBase.ViewLocValue(iArr[0], iArr[1], detailFromView.getItemFrame().getLeft(), detailFromView.getItemFrame().getTop(), detailFromView.getItemFrame().getWidth(), detailFromView.getItemFrame().getHeight());
        View iconFrame = detailFromView.getIconFrame();
        if (iconFrame != null) {
            int[] iArr2 = new int[2];
            commonUtils.getLocationInWindowWithoutTransform(iconFrame, iArr2);
            viewLocValue = new SecondaryPanelAnimatorBase.ViewLocValue(iArr2[0], iArr2[1], iconFrame.getLeft(), iconFrame.getTop(), iconFrame.getWidth(), iconFrame.getHeight());
        } else {
            viewLocValue = null;
        }
        int[] iArr3 = new int[2];
        commonUtils.getLocationInWindowWithoutTransform(getToView().getContainer(), iArr3);
        AnimValue animValue2 = new AnimValue(viewLocValue2, viewLocValue, new SecondaryPanelAnimatorBase.ViewLocValue(iArr3[0], iArr3[1], getToView().getContainer().getLeft(), getToView().getContainer().getTop(), getToView().getContainer().getWidth(), getToView().getContainer().getHeight()), detailFromView.getCornerRadius(), getToView().getContentBgRadius());
        AnimValue animValue3 = this.lastAnimValue;
        if (animValue3 != null && animValue3 != null && (fromFrame = animValue3.getFromFrame()) != null && animValue2.getFromFrame().getLocTop() == fromFrame.getLocTop() && !isOrientationChanged() && !isFoldStateChanged() && (animValue = this.lastAnimValue) != null && (toFrame = animValue.getToFrame()) != null && animValue2.getToFrame().getHeight() == toFrame.getHeight()) {
            this.animValue = this.lastAnimValue;
        } else {
            this.animValue = animValue2;
            this.lastAnimValue = animValue2;
        }
    }

    private final boolean checkAndUpdateFromView() {
        DetailAdapter adapter;
        String strFrom;
        QSRecord tile;
        View iconFrame;
        ViewGroup itemFrame;
        QSController qsController = ((MainPanelController) this.mainPanelController.get()).getQsController();
        DetailPanelParams panelParams = getPanelParams();
        if (panelParams == null || (adapter = panelParams.getAdapter()) == null || (strFrom = SecondaryParamsKt.from(adapter)) == null || (tile = qsController.getTile(strFrom)) == null) {
            return false;
        }
        Object holder = tile.getHolder();
        DetailFromView detailFromView = holder instanceof DetailFromView ? (DetailFromView) holder : null;
        if (detailFromView == null) {
            return false;
        }
        if (n.c(this.fromView, detailFromView)) {
            return true;
        }
        DetailFromView detailFromView2 = this.fromView;
        if (detailFromView2 != null && (itemFrame = detailFromView2.getItemFrame()) != null) {
            CommonUtils.INSTANCE.setVisible(itemFrame);
        }
        DetailFromView detailFromView3 = this.fromView;
        if (detailFromView3 != null && (iconFrame = detailFromView3.getIconFrame()) != null) {
            CommonUtils.INSTANCE.setVisible(iconFrame);
        }
        this.fromView = detailFromView;
        DetailPanelParams panelParams2 = getPanelParams();
        DetailFromView fromView = panelParams2 != null ? panelParams2.getFromView() : null;
        if (fromView == null) {
            return true;
        }
        fromView.setChangeToView(getToView());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void expand$lambda$7(DetailPanelAnimator this$0) {
        n.g(this$0, "this$0");
        ViewGroup fakeView = this$0.getFakeView();
        if (fakeView != null) {
            fakeView.suppressLayout(true);
        }
    }

    private final ViewGroup getFakeView() {
        return ((DetailPanelTilesDelegate) this.tilesDelegate.get()).getFakeView();
    }

    private final View getFakeViewScaleContent() {
        return ((DetailPanelTilesDelegate) this.tilesDelegate.get()).getFakeViewScaleContent();
    }

    private final DetailPanelParams getPanelParams() {
        return ((DetailPanelController) this.detailPanelController.get()).getPanelParams();
    }

    private final DetailPanelController getToView() {
        return (DetailPanelController) this.detailPanelController.get();
    }

    private final void onSecondaryVisible(boolean z2) {
        View iconFrame;
        ViewGroup itemFrame;
        ViewGroup itemFrame2;
        QSItemIconView icon;
        View iconFrame2;
        ViewGroup itemFrame3;
        if (!z2) {
            ViewGroup fakeView = getFakeView();
            if (fakeView != null) {
                CommonUtils.INSTANCE.setGone(fakeView);
            }
            DetailFromView detailFromView = this.fromView;
            if (detailFromView != null && (itemFrame = detailFromView.getItemFrame()) != null) {
                CommonUtils.INSTANCE.setVisible(itemFrame);
            }
            DetailFromView detailFromView2 = this.fromView;
            if (detailFromView2 == null || (iconFrame = detailFromView2.getIconFrame()) == null) {
                return;
            }
            CommonUtils.INSTANCE.setVisible(iconFrame);
            return;
        }
        ViewGroup fakeView2 = getFakeView();
        if (fakeView2 != null) {
            CommonUtils.INSTANCE.setVisible(fakeView2);
        }
        DetailFromView detailFromView3 = this.fromView;
        if ((detailFromView3 != null ? detailFromView3.getIconFrame() : null) != null) {
            DetailFromView detailFromView4 = this.fromView;
            if (detailFromView4 != null && (itemFrame3 = detailFromView4.getItemFrame()) != null) {
                CommonUtils.INSTANCE.setVisible(itemFrame3);
            }
            DetailFromView detailFromView5 = this.fromView;
            if (detailFromView5 != null && (iconFrame2 = detailFromView5.getIconFrame()) != null) {
                CommonUtils.INSTANCE.setInvisible(iconFrame2);
            }
        } else {
            DetailFromView detailFromView6 = this.fromView;
            if (detailFromView6 != null && (itemFrame2 = detailFromView6.getItemFrame()) != null) {
                CommonUtils.INSTANCE.setInvisible(itemFrame2);
            }
        }
        DetailFromView detailFromView7 = this.fromView;
        if (detailFromView7 == null || (icon = detailFromView7.getIcon()) == null) {
            return;
        }
        icon.forceStopIconAnimate();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void collapse(boolean z2) {
        ViewGroup itemFrame;
        boolean zIsExpanding = isExpanding();
        super.collapse(z2);
        boolean zCheckAndUpdateFromView = checkAndUpdateFromView();
        DetailFromView detailFromView = this.fromView;
        if (detailFromView != null && (itemFrame = detailFromView.getItemFrame()) != null) {
            ViewParent parent = itemFrame.getParent();
            MainPanelRecyclerView mainPanelRecyclerView = parent instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) parent : null;
            if (mainPanelRecyclerView != null) {
                mainPanelRecyclerView.setTopDrawingChild(itemFrame);
            }
            if (!itemFrame.isLayoutSuppressed() && !zIsExpanding) {
                calculateViewValues();
            }
        }
        ViewGroup fakeView = getFakeView();
        if (fakeView != null) {
            fakeView.suppressLayout(true);
        }
        Log.i(getTag(), "collapse " + zIsExpanding + " " + z2 + " " + getCollapseWithNoAnim() + " " + zCheckAndUpdateFromView + " " + this.animValue);
        ((DetailPanelTilesDelegate) this.tilesDelegate.get()).updateLayout(this.fromView, this.animValue);
        onSecondaryVisible(true);
        DetailFromView detailFromView2 = this.fromView;
        if (detailFromView2 != null) {
            detailFromView2.changeAction();
        }
        this.anim.cancel();
        AnimConfig animConfig = new AnimConfig();
        animConfig.addListeners(this.animListener);
        animConfig.setSpecial(POSITION, FolmeUtilsExtKt.getEASE_COLLAPSE_POSITION(), new float[0]);
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_COLLAPSE_SIZE(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_COLLAPSE_COLOR(), new float[0]);
        animConfig.setSpecial(FROM_CONTENT_COLOR, FolmeUtilsExtKt.getEASE_COLLAPSE_FROM_CONTENT_COLOR(), new float[0]);
        animConfig.setSpecial(TO_CONTENT_COLOR, FolmeUtilsExtKt.getEASE_COLLAPSE_TO_CONTENT_COLOR(), new float[0]);
        if (z2 && !getCollapseWithNoAnim() && zCheckAndUpdateFromView) {
            this.anim.to(POSITION, Float.valueOf(0.0f), SIZE, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f), FROM_CONTENT_COLOR, Float.valueOf(1.0f), TO_CONTENT_COLOR, Float.valueOf(0.0f), animConfig);
        } else {
            this.anim.setTo(POSITION, Float.valueOf(0.0f), SIZE, Float.valueOf(0.0f), COLOR, Float.valueOf(0.0f), FROM_CONTENT_COLOR, Float.valueOf(1.0f), TO_CONTENT_COLOR, Float.valueOf(0.0f), animConfig);
            onAnimComplete();
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void expand() {
        ViewGroup itemFrame;
        super.expand();
        DetailFromView detailFromView = this.fromView;
        if (detailFromView != null && (itemFrame = detailFromView.getItemFrame()) != null) {
            ViewParent parent = itemFrame.getParent();
            MainPanelRecyclerView mainPanelRecyclerView = parent instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) parent : null;
            if (mainPanelRecyclerView != null) {
                mainPanelRecyclerView.setTopDrawingChild(itemFrame);
            }
            if (!itemFrame.isLayoutSuppressed()) {
                calculateViewValues();
            }
        }
        Log.i(getTag(), "expand " + this.animValue);
        ViewGroup fakeView = getFakeView();
        if (fakeView != null) {
            fakeView.suppressLayout(false);
        }
        ((DetailPanelTilesDelegate) this.tilesDelegate.get()).updateLayout(this.fromView, this.animValue);
        onSecondaryVisible(true);
        DetailFromView detailFromView2 = this.fromView;
        if (detailFromView2 != null) {
            detailFromView2.changeAction();
        }
        ViewGroup fakeView2 = getFakeView();
        if (fakeView2 != null) {
            fakeView2.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.secondary.detail.a
                @Override // java.lang.Runnable
                public final void run() {
                    DetailPanelAnimator.expand$lambda$7(this.f5460a);
                }
            });
        }
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        AnimConfig animConfig = new AnimConfig();
        animConfig.addListeners(this.animListener);
        animConfig.setSpecial(POSITION, FolmeUtilsExtKt.getEASE_EXPAND_POSITION(), new float[0]);
        animConfig.setSpecial(SIZE, FolmeUtilsExtKt.getEASE_EXPAND_SIZE(), new float[0]);
        animConfig.setSpecial(COLOR, FolmeUtilsExtKt.getEASE_EXPAND_COLOR(), new float[0]);
        animConfig.setSpecial(FROM_CONTENT_COLOR, FolmeUtilsExtKt.getEASE_EXPAND_FROM_CONTENT_COLOR(), new float[0]);
        animConfig.setSpecial(TO_CONTENT_COLOR, FolmeUtilsExtKt.getEASE_EXPAND_TO_CONTENT_COLOR(), new float[0]);
        this.anim.to(POSITION, Float.valueOf(1.0f), SIZE, Float.valueOf(1.0f), COLOR, Float.valueOf(1.0f), FROM_CONTENT_COLOR, Float.valueOf(0.0f), TO_CONTENT_COLOR, Float.valueOf(1.0f), animConfig);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void forceUpdateClipHeader() {
        ((ControlCenterWindowViewController) this.windowViewController.get()).updateClip(isCollapsing());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void frameCallback() {
        AnimValue animValue;
        float width;
        float height;
        float left;
        float locTop;
        ImageView icon;
        super.frameCallback();
        DetailFromView detailFromView = this.fromView;
        if (detailFromView == null || (animValue = this.animValue) == null) {
            return;
        }
        float fromRadius = animValue.getFromRadius() + ((animValue.getToRadius() - animValue.getFromRadius()) * this.size);
        float f2 = isMainPanelCollapsing() ? 0.0f : this.fromContentColor;
        float f3 = isLowEndDevice() ? 1.0f : this.toContentColor;
        SecondaryPanelAnimatorBase.ViewLocValue fromIcon = animValue.getFromIcon();
        View iconFrame = detailFromView.getIconFrame();
        if (fromIcon == null || iconFrame == null) {
            width = animValue.getFromFrame().getWidth() + ((animValue.getToFrame().getWidth() - animValue.getFromFrame().getWidth()) * this.size);
            height = animValue.getFromFrame().getHeight() + ((animValue.getToFrame().getHeight() - animValue.getFromFrame().getHeight()) * this.size);
            float fromCenterX = animValue.getFromCenterX() + ((animValue.getToCenterX() - animValue.getFromCenterX()) * this.position);
            float f4 = 2;
            float fromCenterY = (animValue.getFromCenterY() + ((animValue.getToCenterY() - animValue.getFromCenterY()) * this.position)) - (height / f4);
            left = animValue.getToFrame().getLeft() + ((fromCenterX - (width / f4)) - animValue.getToFrame().getLocLeft());
            locTop = (fromCenterY - animValue.getToFrame().getLocTop()) + animValue.getToFrame().getTop();
            ViewGroup fakeView = getFakeView();
            if (fakeView != null) {
                CommonUtils.INSTANCE.setAlphaEx(fakeView, f2);
                ViewGroup fakeView2 = getFakeView();
                QSCardItemView qSCardItemView = fakeView2 instanceof QSCardItemView ? (QSCardItemView) fakeView2 : null;
                if (qSCardItemView != null) {
                    qSCardItemView.setCornerRadius(fromRadius);
                }
                fakeView.setLeftTopRightBottom(Y0.b.b(left), Y0.b.b(locTop), Y0.b.b(left + width), Y0.b.b(locTop + height));
            }
            View fakeViewScaleContent = getFakeViewScaleContent();
            if (fakeViewScaleContent != null) {
                fakeViewScaleContent.setPivotX(0.0f);
                fakeViewScaleContent.setPivotY(0.0f);
                float width2 = width / animValue.getFromFrame().getWidth();
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                commonUtils.setScaleXEx(fakeViewScaleContent, width2);
                commonUtils.setScaleYEx(fakeViewScaleContent, width2);
            }
        } else {
            width = fromIcon.getWidth() + ((animValue.getToFrame().getWidth() - fromIcon.getWidth()) * this.size);
            height = fromIcon.getHeight() + ((animValue.getToFrame().getHeight() - fromIcon.getHeight()) * this.size);
            float fromIconCenterX = animValue.getFromIconCenterX() + ((animValue.getToCenterX() - animValue.getFromIconCenterX()) * this.position);
            float f5 = 2;
            float fromIconCenterY = (animValue.getFromIconCenterY() + ((animValue.getToCenterY() - animValue.getFromIconCenterY()) * this.position)) - (height / f5);
            left = (animValue.getToFrame().getLeft() + (fromIconCenterX - (width / f5))) - animValue.getToFrame().getLocLeft();
            locTop = (animValue.getToFrame().getTop() + fromIconCenterY) - animValue.getToFrame().getLocTop();
            ViewGroup fakeView3 = getFakeView();
            if (fakeView3 != null) {
                CommonUtils commonUtils2 = CommonUtils.INSTANCE;
                commonUtils2.setAlphaEx(fakeView3, f2);
                fakeView3.setLeftTopRightBottom(Y0.b.b(left), Y0.b.b(locTop), Y0.b.b(left + width), Y0.b.b(locTop + height));
                ViewGroup fakeView4 = getFakeView();
                QSTileItemIconView qSTileItemIconView = fakeView4 instanceof QSTileItemIconView ? (QSTileItemIconView) fakeView4 : null;
                if (qSTileItemIconView != null) {
                    qSTileItemIconView.setCornerRadius(fromRadius);
                }
                ViewGroup fakeView5 = getFakeView();
                QSTileItemIconView qSTileItemIconView2 = fakeView5 instanceof QSTileItemIconView ? (QSTileItemIconView) fakeView5 : null;
                if (qSTileItemIconView2 != null && (icon = qSTileItemIconView2.getIcon()) != null) {
                    icon.setPivotX(0.0f);
                    icon.setPivotY(0.0f);
                    float scaleX = detailFromView.getItemFrame().getScaleX();
                    float scaleY = detailFromView.getItemFrame().getScaleY();
                    commonUtils2.setScaleXEx(icon, scaleX);
                    commonUtils2.setScaleYEx(icon, scaleY);
                    float f6 = (width - (scaleX * width)) / 2.0f;
                    float f7 = (height - (scaleY * height)) / 2.0f;
                    icon.setLeftTopRightBottom(Y0.b.b(f6), Y0.b.b(f7), Y0.b.b(f6 + width), Y0.b.b(f7 + height));
                }
            }
        }
        getToView().setBlurBgRatio(this.color);
        getToView().setContentBgRadius(fromRadius);
        MaxHeightLinearLayout container = getToView().getContainer();
        CommonUtils commonUtils3 = CommonUtils.INSTANCE;
        commonUtils3.setAlphaEx(container, this.color);
        container.setLeftTopRightBottom(Y0.b.b(left), Y0.b.b(locTop), Y0.b.b(left + width), Y0.b.b(locTop + height));
        LinearLayout scaleContent = getToView().getScaleContent();
        scaleContent.setPivotX(0.0f);
        scaleContent.setPivotY(0.0f);
        float width3 = width / animValue.getToFrame().getWidth();
        commonUtils3.setScaleXEx(scaleContent, width3);
        commonUtils3.setScaleYEx(scaleContent, width3);
        scaleContent.setTop(0);
        if (width3 != 0.0f) {
            height /= width3;
        }
        scaleContent.setBottom(Y0.b.b(height));
        commonUtils3.setAlphaEx(getToView().getContent(), f3);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public String getAnimStateString() {
        return "DetailPanelAnimator(position=" + this.position + ", size=" + this.size + ", color=" + this.color + ", fromContentColor=" + this.fromContentColor + ", toContentColor=" + this.toContentColor + ")";
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void onAnimComplete() {
        super.onAnimComplete();
        SecondaryPanelAnimatorBase.doFrame$default(this, 0L, 1, null);
        if (isCollapsed()) {
            onSecondaryVisible(false);
        }
        ViewGroup fakeView = getFakeView();
        if (fakeView != null) {
            fakeView.suppressLayout(false);
        }
        ((DetailPanelController) this.detailPanelController.get()).onAnimComplete();
        this.animValue = null;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase
    public void prepareExpand(DetailPanelParams detailPanelParams) {
        super.prepareExpand(detailPanelParams);
        this.fromView = detailPanelParams != null ? detailPanelParams.getFromView() : null;
        DetailFromView fromView = detailPanelParams != null ? detailPanelParams.getFromView() : null;
        if (fromView != null) {
            fromView.setChangeToView(getToView());
        }
        this.animValue = null;
        this.lastAnimValue = null;
    }
}
