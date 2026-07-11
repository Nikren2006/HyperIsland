package miui.systemui.controlcenter.qs.tileview;

import H0.s;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.systemui.plugins.qs.QSTile;
import com.miui.circulate.device.api.Column;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.QsTileItemViewBinding;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.widget.QSMarkImageView;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.widget.TextView;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
public final class QSTileItemView extends FrameLayout implements QSItemView {
    private static final float BREATH_ALPHA = 0.5f;
    private static final long BREATH_DURATION = 400;
    private static final EaseManager.EaseStyle EASE_EDIT_HIDE_POSITION;
    private static final EaseManager.EaseStyle EASE_EDIT_SHOW_POSITION;
    private static final String TAG = "QSTileItemView";
    private String accessibilityClass;
    private boolean added;
    private final H0.d binding$delegate;
    private ObjectAnimator breathAnimator;
    private Function1 clickAction;
    private boolean compatTile;
    private int containerHeight;
    private QSTile.State customizeState;
    private IStateStyle heightAnim;
    private QSTileItemIconView icon;
    private IStateStyle labelAnim;
    private int labelHeight;
    private long lastTriggeredTime;
    private Function1 longClickAction;
    public MainPanelController mainPanelController;
    private IStateStyle markAnim;
    private Function1 markClickAction;
    private MainPanelController.Mode mode;
    private boolean needLongClickAction;
    private boolean removable;
    private QSTile.State state;
    private MainPanelController.Style style;
    private QSListController.TextMode textMode;
    private int tileState;
    private MainPanelItemViewHolder.TouchAnimator touchAnimator;
    public static final Companion Companion = new Companion(null);
    private static final EaseManager.EaseStyle ALPHA_EASE = FolmeEase.spring(0.95f, 0.15f);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MainPanelController.Mode.values().length];
            try {
                iArr[MainPanelController.Mode.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        EaseManager.EaseStyle easeStyleSpring = FolmeEase.spring(1.0f, 0.4f);
        n.f(easeStyleSpring, "spring(...)");
        EASE_EDIT_SHOW_POSITION = easeStyleSpring;
        EaseManager.EaseStyle easeStyleSpring2 = FolmeEase.spring(0.95f, 0.35f);
        n.f(easeStyleSpring2, "spring(...)");
        EASE_EDIT_HIDE_POSITION = easeStyleSpring2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSTileItemView(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    private final void changeExpand() {
        boolean showLabel = getShowLabel();
        getBinding().tileLabel.setVisibility(getLabelVisible());
        getBinding().tileLabel.setTranslationY(showLabel ? 0.0f : this.labelHeight);
        CommonUtils.setLayoutHeight$default(CommonUtils.INSTANCE, this, showLabel ? this.containerHeight + this.labelHeight : this.containerHeight, false, 2, null);
    }

    private final int getLabelVisible() {
        return getShowLabel() ? 0 : 4;
    }

    private final boolean getShowLabel() {
        return (this.mode == MainPanelController.Mode.EDIT || this.textMode == QSListController.TextMode.TEXT) && this.style != MainPanelController.Style.COMPACT;
    }

    private final boolean getShowMark() {
        return (this.removable || !this.added) && this.mode != MainPanelController.Mode.NORMAL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$0(QSTileItemView this$0, View view) {
        n.g(this$0, "this$0");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime > this$0.lastTriggeredTime + 200) {
            this$0.lastTriggeredTime = jElapsedRealtime;
            Function1 function1 = this$0.clickAction;
            if (function1 != null) {
                function1.invoke(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$1(QSTileItemView this$0, View view) {
        Function1 function1;
        n.g(this$0, "this$0");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime > this$0.lastTriggeredTime + 200) {
            this$0.lastTriggeredTime = jElapsedRealtime;
            if ((!this$0.removable && this$0.added) || this$0.mode == MainPanelController.Mode.NORMAL || (function1 = this$0.markClickAction) == null) {
                return;
            }
            function1.invoke(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onFinishInflate$lambda$2(QSTileItemView this$0, View view) {
        n.g(this$0, "this$0");
        if (this$0.mode == MainPanelController.Mode.EDIT && this$0.added) {
            this$0.announceForAccessibility(this$0.getResources().getString(R.string.qs_item_drag));
        }
        if (this$0.getMainPanelController().getStyle() != MainPanelController.Style.COMPACT) {
            Function1 function1 = this$0.longClickAction;
            if (!(function1 != null ? ((Boolean) function1.invoke(view)).booleanValue() : false)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void onStateUpdated(boolean r14) {
        /*
            Method dump skipped, instruction units count: 351
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.qs.tileview.QSTileItemView.onStateUpdated(boolean):void");
    }

    private final void setAdded(boolean z2) {
        if (this.added == z2) {
            return;
        }
        this.added = z2;
        updateMarkRes();
    }

    private final void showLabel(boolean z2, boolean z3) {
        AnimState animStateAdd = new AnimState("label").add(ViewProperty.ALPHA, z2 ? 1.0f : 0.0f, new long[0]);
        updateAnimConfig(z2);
        s sVar = s.f314a;
        IStateStyle iStateStyle = this.labelAnim;
        if (z3) {
            if (iStateStyle != null) {
                iStateStyle.to(animStateAdd, sVar);
            }
        } else if (iStateStyle != null) {
            iStateStyle.setTo(animStateAdd, sVar);
        }
    }

    private final void updateAccessibilityClickOperation() {
        String string;
        if (isClickable()) {
            AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, "");
            if (this.mode == MainPanelController.Mode.EDIT) {
                string = getContext().getString(this.added ? R.string.accessibility_desc_remove : R.string.accessibility_desc_add);
            } else {
                string = "";
            }
            ViewCompat.replaceAccessibilityAction(getIcon(), accessibilityActionCompat, string, null);
        } else {
            ViewCompat.removeAccessibilityAction(getIcon(), 16);
        }
        if (this.added && this.mode == MainPanelController.Mode.EDIT) {
            ViewCompat.replaceAccessibilityAction(getIcon(), new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32, ""), getContext().getString(R.string.qs_item_drag_and_sort), null);
        } else {
            ViewCompat.removeAccessibilityAction(getIcon(), 32);
        }
    }

    private final void updateAnimConfig(boolean z2) {
        new AnimConfig().setSpecial(ViewProperty.ALPHA, ALPHA_EASE, new float[0]).setSpecial(ViewProperty.TRANSLATION_Y, z2 ? EASE_EDIT_SHOW_POSITION : EASE_EDIT_HIDE_POSITION, new float[0]);
    }

    private final void updateContainerHeight() {
        this.containerHeight = getResources().getDimensionPixelSize(R.dimen.control_center_universal_1_row_with_margin_size);
        this.labelHeight = getResources().getDimensionPixelSize(R.dimen.qs_tile_item_label_height);
    }

    private final void updateMark(boolean z2) {
        boolean showMark = getShowMark();
        getBinding().mark.setVisibility(showMark ? 0 : 4);
        AnimState animStateAdd = new AnimState("mark").add(ViewProperty.ALPHA, showMark ? 1.0f : 0.0f, new long[0]);
        updateAnimConfig(showMark);
        s sVar = s.f314a;
        IStateStyle iStateStyle = this.markAnim;
        if (z2) {
            if (iStateStyle != null) {
                iStateStyle.to(animStateAdd, sVar);
            }
        } else if (iStateStyle != null) {
            iStateStyle.setTo(animStateAdd, sVar);
        }
    }

    private final void updateMarkRes() {
        getBinding().mark.setContentDescription(getContext().getString(this.added ? R.string.accessibility_desc_remove : R.string.accessibility_desc_add));
        getBinding().mark.setImageResource(this.added ? R.drawable.ic_qs_tile_mark_remove : R.drawable.ic_qs_tile_mark_add);
    }

    private final void updateTextSizeForKDDI() {
        getBinding().tileLabel.setTextSize(0, getContext().getResources().getDimensionPixelSize(CommonUtils.IS_KDDI_VERSION ? R.dimen.qs_tile_item_label_text_size_kddi : R.dimen.qs_tile_item_label_text_size));
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public QSTileItemView asView() {
        return this;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void attachListeners(Function1 function1, Function1 function12, Function1 function13, Function1 function14) {
        this.clickAction = function1;
        this.markClickAction = function13;
        this.longClickAction = function14;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void detachListeners() {
        this.clickAction = null;
        this.markClickAction = null;
        this.longClickAction = null;
    }

    public final QsTileItemViewBinding getBinding() {
        return (QsTileItemViewBinding) this.binding$delegate.getValue();
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public View getBlendTarget() {
        return getIcon().getIcon();
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public boolean getCompatTile() {
        return this.compatTile;
    }

    @Override // miui.systemui.controlcenter.widget.ExpandableView
    public float getCornerRadius() {
        return 0.0f;
    }

    public final QSTileItemIconView getIcon() {
        QSTileItemIconView qSTileItemIconView = this.icon;
        if (qSTileItemIconView != null) {
            return qSTileItemIconView;
        }
        n.w(Column.ICON);
        return null;
    }

    public final MainPanelController getMainPanelController() {
        MainPanelController mainPanelController = this.mainPanelController;
        if (mainPanelController != null) {
            return mainPanelController;
        }
        n.w("mainPanelController");
        return null;
    }

    public final boolean getNeedLongClickAction() {
        if (this.needLongClickAction) {
            Context context = getContext();
            n.f(context, "getContext(...)");
            if (!CommonUtils.isTinyScreen(context)) {
                return true;
            }
        }
        return false;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public MainPanelItemViewHolder.TouchAnimator getTouchAnimator() {
        return this.touchAnimator;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final void init(QSTileItemIconView icon) {
        n.g(icon, "icon");
        this.icon = icon;
        getBinding().iconFrame.addView(icon, new LinearLayout.LayoutParams(-1, -2));
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(icon.getIcon(), "alpha", 0.5f);
        objectAnimatorOfFloat.setDuration(BREATH_DURATION);
        objectAnimatorOfFloat.setInterpolator(new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f));
        objectAnimatorOfFloat.setRepeatMode(2);
        objectAnimatorOfFloat.setRepeatCount(-1);
        n.f(objectAnimatorOfFloat, "apply(...)");
        this.breathAnimator = objectAnimatorOfFloat;
        ViewCompat.setAccessibilityDelegate(icon, new AccessibilityDelegateCompat() { // from class: miui.systemui.controlcenter.qs.tileview.QSTileItemView.init.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                n.g(host, "host");
                n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (QSTileItemView.this.mode != MainPanelController.Mode.NORMAL) {
                    info.setClassName(Button.class.getName());
                    return;
                }
                if (TextUtils.isEmpty(QSTileItemView.this.accessibilityClass)) {
                    return;
                }
                info.setClassName(QSTileItemView.this.accessibilityClass);
                if (n.c(Switch.class.getName(), QSTileItemView.this.accessibilityClass)) {
                    boolean z2 = QSTileItemView.this.tileState == 2;
                    if (QSTileItemView.this.isClickable()) {
                        info.setChecked(z2);
                        info.setCheckable(true);
                    }
                }
                if (QSTileItemView.this.getNeedLongClickAction()) {
                    info.setLongClickable(true);
                    info.addAction(32);
                }
            }
        });
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void onConfigurationChanged(int i2) {
        suppressLayout(true);
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zColorsChanged = configUtils.colorsChanged(i2);
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        boolean zTextAppearanceChanged = configUtils.textAppearanceChanged(i2);
        boolean zTextsChanged = configUtils.textsChanged(i2);
        boolean zBlurChanged = configUtils.blurChanged(i2);
        if (zDimensionsChanged) {
            updateSize();
        }
        if (zTextAppearanceChanged || zTextsChanged) {
            updateTextAppearance();
            updateTextSizeForKDDI();
        }
        if (zColorsChanged || zTextsChanged) {
            updateMarkRes();
        }
        if (zColorsChanged || zDimensionsChanged || zBlurChanged) {
            getIcon().updateResources();
            QSTileItemIconView.updateIcon$default(getIcon(), null, false, this.added, true, configUtils.uiModeChanged(i2) && ((SecondaryPanelRouter) getMainPanelController().getSecondaryPanelRouter().get()).getInMainPanel(), 1, null);
        }
        suppressLayout(false);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setClipChildren(false);
        setClipToOutline(false);
        setHapticFeedbackEnabled(!HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE());
        this.heightAnim = Folme.useAt(this).state();
        this.labelAnim = Folme.useAt(getBinding().tileLabel).state();
        this.markAnim = Folme.useAt(getBinding().mark).state();
        updateContainerHeight();
        updateTextSizeForKDDI();
        setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.qs.tileview.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QSTileItemView.onFinishInflate$lambda$0(this.f5489a, view);
            }
        });
        getBinding().mark.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.qs.tileview.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QSTileItemView.onFinishInflate$lambda$1(this.f5490a, view);
            }
        });
        setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.qs.tileview.f
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return QSTileItemView.onFinishInflate$lambda$2(this.f5491a, view);
            }
        });
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void onModeChanged(MainPanelController.Mode mode, boolean z2) {
        n.g(mode, "mode");
        if (this.mode == mode) {
            return;
        }
        this.mode = mode;
        onStateUpdated(false);
        changeExpand();
        showLabel(getShowLabel(), z2);
        updateMark(z2);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void onStateChanged(QSTile.State state) {
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void onStyleChanged(MainPanelController.Style style) {
        n.g(style, "style");
        this.style = style;
        changeExpand();
        showLabel(getShowLabel(), false);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void onTextModeChanged(QSListController.TextMode mode, boolean z2) {
        n.g(mode, "mode");
        this.textMode = mode;
        changeExpand();
        showLabel(getShowLabel(), z2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MainPanelItemViewHolder.TouchAnimator touchAnimator;
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            MainPanelItemViewHolder.TouchAnimator touchAnimator2 = getTouchAnimator();
            if (touchAnimator2 != null) {
                touchAnimator2.touchDown(motionEvent);
            }
        } else if (numValueOf != null && numValueOf.intValue() == 1) {
            MainPanelItemViewHolder.TouchAnimator touchAnimator3 = getTouchAnimator();
            if (touchAnimator3 != null) {
                touchAnimator3.touchRelease();
            }
        } else if (numValueOf != null && numValueOf.intValue() == 3 && (touchAnimator = getTouchAnimator()) != null) {
            touchAnimator.touchCancel();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        MainPanelItemViewHolder.TouchAnimator touchAnimator = getTouchAnimator();
        if (touchAnimator != null) {
            touchAnimator.touchCancel();
        }
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void recycle() {
        super.recycle();
        getIcon().recycle();
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void setCompatTile(boolean z2) {
        this.compatTile = z2;
    }

    @Override // miui.systemui.controlcenter.widget.ExpandableView
    public void setCornerRadius(float f2) {
    }

    public final void setMainPanelController(MainPanelController mainPanelController) {
        n.g(mainPanelController, "<set-?>");
        this.mainPanelController = mainPanelController;
    }

    public final void setNeedLongClickAction(boolean z2) {
        this.needLongClickAction = z2;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void setTouchAnimator(MainPanelItemViewHolder.TouchAnimator touchAnimator) {
        this.touchAnimator = touchAnimator;
    }

    public final View updateAccessibilityOrder(View previousView) {
        n.g(previousView, "previousView");
        setAccessibilityTraversalAfter(previousView.getId());
        return this;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void updateAdded(boolean z2, boolean z3) {
        setAdded(z2);
        updateAccessibilityClickOperation();
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void updateCustomizeState(QSTile.State state, boolean z2) {
        this.customizeState = state;
        if (this.mode == MainPanelController.Mode.NORMAL) {
            return;
        }
        onStateUpdated(z2);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void updateRemovable(boolean z2, boolean z3) {
        if (this.removable == z2) {
            return;
        }
        this.removable = z2;
        updateMark(z3);
    }

    public final void updateSize() {
        updateContainerHeight();
        changeExpand();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.qs_tile_item_icon_margin_top);
        miui.systemui.widget.FrameLayout frameLayout = getBinding().iconFrame;
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        n.d(frameLayout);
        CommonUtils.setMarginTop$default(commonUtils, frameLayout, dimensionPixelSize, false, 2, null);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.qs_tile_item_edit_mark_size);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.qs_tile_item_edit_margin);
        QSMarkImageView mark = getBinding().mark;
        n.f(mark, "mark");
        CommonUtils.setLayoutSize$default(commonUtils, mark, dimensionPixelSize2, dimensionPixelSize2, false, 4, null);
        QSMarkImageView mark2 = getBinding().mark;
        n.f(mark2, "mark");
        CommonUtils.setMargins$default(commonUtils, mark2, dimensionPixelSize3, false, 2, null);
        TextView textView = getBinding().tileLabel;
        n.d(textView);
        CommonUtils.setLayoutHeight$default(commonUtils, textView, this.labelHeight, false, 2, null);
        int dimensionPixelSize4 = textView.getResources().getDimensionPixelSize(R.dimen.qs_tile_item_label_margin_horizontal);
        CommonUtils.setMargins$default(commonUtils, textView, dimensionPixelSize4, 0, dimensionPixelSize4, dimensionPixelSize, false, 18, null);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void updateState(QSTile.State state, boolean z2, boolean z3) {
        this.state = state;
        if (this.mode != MainPanelController.Mode.NORMAL) {
            return;
        }
        onStateUpdated(z3);
    }

    public final void updateTextAppearance() {
        getBinding().tileLabel.setTextAppearance(R.style.TextAppearance_QS_TileLabel);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSTileItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSTileItemView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ QSTileItemView(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSTileItemView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
        this.binding$delegate = H0.e.b(new QSTileItemView$binding$2(this));
        this.tileState = 1;
        this.lastTriggeredTime = -1L;
        this.needLongClickAction = true;
        this.removable = true;
        this.style = MainPanelController.Style.VERTICAL;
        this.mode = MainPanelController.Mode.NORMAL;
        this.textMode = QSListController.TextMode.NO_TEXT;
    }
}
