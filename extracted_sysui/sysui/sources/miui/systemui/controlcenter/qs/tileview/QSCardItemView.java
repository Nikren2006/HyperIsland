package miui.systemui.controlcenter.qs.tileview;

import H0.s;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.miui.circulate.device.api.Column;
import com.xiaomi.onetrack.util.aa;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.animation.FolmeKt;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.Interpolators;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.QsCardItemViewBinding;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.qs.TileSpecsKt;
import miui.systemui.controlcenter.qs.tileview.QSItemView;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.widget.FocusedTextView;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.OnClickListenerEx;
import miui.systemui.util.ThemeUtils;
import miui.systemui.util.ViewOutlineProviderExt;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.FloatProperty;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UseCompatLoadingForDrawables", "ClickableViewAccessibility"})
public final class QSCardItemView extends LinearLayout implements QSItemView {
    private static final String TAG = "QSCardItemView";
    private float _cornerRadius;
    private String accessibilityClass;
    private final QSCardItemView$backgroundOutlineProvider$1 backgroundOutlineProvider;
    private float backgroundProgress;
    private final H0.d binding$delegate;
    private final AnimConfig blendBackgroundAnimConfig;
    private Function1 clickAction;
    private boolean clicked;
    private boolean compatTile;
    private boolean connected;
    private Drawable disabledBg;
    private Drawable enabledBg;
    private ColorBlendToken fromToken;
    private QSCardItemIconView icon;
    private boolean lastRestrictedState;
    private Function1 longClickAction;
    private final Handler mainHandler;
    private boolean marqueeLock;
    private final Runnable marqueeRunnable;
    private ObjectAnimator normalBackgroundAnimator;
    private QSTile.State state;
    private boolean tileState;
    private ColorBlendToken toToken;
    private MainPanelItemViewHolder.TouchAnimator touchAnimator;
    public static final Companion Companion = new Companion(null);
    private static final QSCardItemView$Companion$BLEND_BACKGROUND_PROGRESS$1 BLEND_BACKGROUND_PROGRESS = new FloatProperty<QSCardItemView>() { // from class: miui.systemui.controlcenter.qs.tileview.QSCardItemView$Companion$BLEND_BACKGROUND_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(QSCardItemView card) {
            n.g(card, "card");
            return card.getBackgroundProgress();
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(QSCardItemView card, float f2) {
            n.g(card, "card");
            card.setBackgroundProgress(f2);
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.qs.tileview.QSCardItemView$onFinishInflate$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((View) obj);
            return s.f314a;
        }

        public final void invoke(View view) {
            Function1 function1 = QSCardItemView.this.clickAction;
            if (function1 != null) {
                function1.invoke(view);
            }
        }
    }

    public /* synthetic */ QSCardItemView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    private final void cancelAlphaAnimation() {
        ObjectAnimator objectAnimator = this.normalBackgroundAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator.removeAllListeners();
            objectAnimator.removeAllUpdateListeners();
        }
        this.normalBackgroundAnimator = null;
    }

    private final void cleanBlendAnimation() {
        Folme.clean(this);
    }

    private final boolean getBackgroundBlendEnabled() {
        Context context = getContext();
        n.f(context, "getContext(...)");
        return ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context);
    }

    private final String getClosingString() {
        return getResources().getString(R.string.qs_control_big_tile_state_closing);
    }

    private final String getConnectedString() {
        return getResources().getString(R.string.qs_control_big_tile_state_connected);
    }

    private final String getDisabledString() {
        return getResources().getString(R.string.qs_control_big_tile_state_closed);
    }

    private final String getEnabledString() {
        return getResources().getString(R.string.qs_control_big_tile_state_opened);
    }

    private final String getOpeningString() {
        return getResources().getString(R.string.qs_control_big_tile_state_opening);
    }

    private final String getRestrictedString() {
        return getResources().getString(R.string.qs_control_big_tile_state_disconnected);
    }

    private final String getUnavailableString() {
        return getResources().getString(R.string.qs_control_big_tile_state_unavailable);
    }

    private final boolean isRestrictedState() {
        return ControlCenterUtils.INSTANCE.getRestrictedState(this.state, this.lastRestrictedState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void marqueeRunnable$lambda$3(QSCardItemView this$0) {
        n.g(this$0, "this$0");
        this$0.getBinding().title.startMarqueeLocal();
        this$0.getBinding().status.startMarqueeLocal();
        this$0.marqueeLock = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onFinishInflate$lambda$2(QSCardItemView this$0, View view) {
        n.g(this$0, "this$0");
        Function1 function1 = this$0.longClickAction;
        if (function1 != null) {
            return ((Boolean) function1.invoke(view)).booleanValue();
        }
        return false;
    }

    private final void setDisabledBg(Drawable drawable) {
        if (n.c(this.disabledBg, drawable)) {
            return;
        }
        this.disabledBg = drawable;
        invalidateOutline();
    }

    private final void setEnabledBg(Drawable drawable) {
        if (n.c(this.enabledBg, drawable)) {
            return;
        }
        this.enabledBg = drawable;
        invalidateOutline();
    }

    private final void startAlphaAnimation(Drawable drawable, int i2) {
        cancelAlphaAnimation();
        if (drawable == null) {
            return;
        }
        if (getVisibility() != 0) {
            drawable.setAlpha(i2);
        }
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, "alpha", 255 - i2, i2);
        objectAnimatorOfInt.setDuration(300L);
        objectAnimatorOfInt.setInterpolator(Interpolators.INSTANCE.getCUBIC_EASE_OUT());
        objectAnimatorOfInt.start();
        this.normalBackgroundAnimator = objectAnimatorOfInt;
    }

    private final void updateBackground(boolean z2) {
        if (getBackgroundBlendEnabled()) {
            updateBlurBlendBackground(z2);
            return;
        }
        updateDrawableBackground(z2);
        this.fromToken = null;
        this.toToken = null;
    }

    private final void updateBackgroundBlendModeAndColors(float f2) {
        ColorBlendToken colorBlendToken;
        ColorBlendToken colorBlendToken2 = this.fromToken;
        if (colorBlendToken2 == null || (colorBlendToken = this.toToken) == null || !getBackgroundBlendEnabled()) {
            return;
        }
        MiBlurCompat.setMiViewBlurModeCompat(this, 1);
        MiBlurCompat.setMiBackgroundBlendColors((View) this, colorBlendToken2, colorBlendToken, f2, false);
    }

    public static /* synthetic */ void updateBackgroundBlendModeAndColors$default(QSCardItemView qSCardItemView, float f2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 1.0f;
        }
        qSCardItemView.updateBackgroundBlendModeAndColors(f2);
    }

    private final void updateBlurBlendBackground(boolean z2) {
        QSTile.State state = this.state;
        if (state == null) {
            return;
        }
        int i2 = state.state;
        if (i2 == 0) {
            i2 = 1;
        }
        setBackground(null);
        setOutlineProvider(this.backgroundOutlineProvider);
        boolean z3 = this.fromToken == null;
        ColorBlendToken cc_tile_default_blend_colors = this.toToken;
        if (cc_tile_default_blend_colors == null) {
            cc_tile_default_blend_colors = MiuiColorBlendToken.INSTANCE.getCC_TILE_DEFAULT_BLEND_COLORS();
        }
        this.fromToken = cc_tile_default_blend_colors;
        ColorBlendToken cc_tile_on_blend_colors = i2 == 2 ? MiuiColorBlendToken.INSTANCE.getCC_TILE_ON_BLEND_COLORS() : QSItemView.Companion.isRestrictedCompat(state) ? MiuiColorBlendToken.INSTANCE.getCC_TILE_RESTRICTED_BLEND_COLORS() : MiuiColorBlendToken.INSTANCE.getCC_TILE_DEFAULT_BLEND_COLORS();
        this.toToken = cc_tile_on_blend_colors;
        Log.i(TAG, "updateBlendBackground: firstSet=" + z3 + ", changed=" + (!n.c(this.fromToken, cc_tile_on_blend_colors)) + ", isVisible=" + (getVisibility() == 0));
        if (z3 || getVisibility() != 0 || !z2) {
            ColorBlendToken colorBlendToken = this.toToken;
            if (colorBlendToken != null) {
                MiBlurCompat.setMiViewBlurModeCompat(this, 1);
                MiBlurCompat.setMiBackgroundBlendColors(this, colorBlendToken);
                return;
            }
            return;
        }
        if (n.c(this.fromToken, this.toToken)) {
            return;
        }
        IStateStyle iStateStyleState = FolmeKt.getFolme(this).state();
        QSCardItemView$Companion$BLEND_BACKGROUND_PROGRESS$1 qSCardItemView$Companion$BLEND_BACKGROUND_PROGRESS$1 = BLEND_BACKGROUND_PROGRESS;
        iStateStyleState.setTo(qSCardItemView$Companion$BLEND_BACKGROUND_PROGRESS$1, Float.valueOf(0.0f));
        iStateStyleState.to(qSCardItemView$Companion$BLEND_BACKGROUND_PROGRESS$1, Float.valueOf(1.0f), this.blendBackgroundAnimConfig);
    }

    private final void updateCornerRadius() {
        this._cornerRadius = getResources().getDimensionPixelSize(R.dimen.control_center_universal_corner_radius);
    }

    private final void updateDrawableBackground(boolean z2) {
        QSTile.State state = this.state;
        if (state == null) {
            return;
        }
        int i2 = state.state;
        if (i2 == 0) {
            i2 = 1;
        }
        CommonUtils.INSTANCE.clearMiBlurBlendEffect(this);
        invalidateOutline();
        setDisabledBg(getContext().getTheme().getDrawable(isRestrictedState() ? R.drawable.qs_card_background_restricted : R.drawable.qs_card_background_disabled).mutate());
        setEnabledBg(getContext().getTheme().getDrawable(R.drawable.qs_card_background_enabled).mutate());
        if (!ThemeUtils.INSTANCE.getDefaultPluginTheme() || DeviceUtils.isLowEndDevice()) {
            setBackground(i2 == 2 ? this.enabledBg : this.disabledBg);
            setTag(R.id.qs_icon_state_tag, Integer.valueOf(i2));
            return;
        }
        setBackground(new LayerDrawable(new Drawable[]{this.disabledBg, this.enabledBg}));
        setCornerRadius(this._cornerRadius);
        int i3 = i2 == 2 ? 255 : 0;
        int i4 = R.id.qs_icon_state_tag;
        Object tag = getTag(i4);
        setTag(i4, Integer.valueOf(i2));
        Log.i(TAG, "updateDrawableBackground: targetAlpha=" + i3 + ". " + tag + " " + i2);
        if (tag != null && !n.c(tag, Integer.valueOf(i2)) && z2) {
            startAlphaAnimation(this.enabledBg, i3);
            return;
        }
        Drawable drawable = this.enabledBg;
        if (drawable == null) {
            return;
        }
        drawable.setAlpha(i3);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public QSCardItemView asView() {
        return this;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void attachListeners(Function1 function1, Function1 function12, Function1 function13, Function1 function14) {
        this.clickAction = function1;
        this.longClickAction = function14;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void detachListeners() {
        this.clickAction = null;
        this.longClickAction = null;
    }

    public final float getBackgroundProgress() {
        return this.backgroundProgress;
    }

    public final QsCardItemViewBinding getBinding() {
        return (QsCardItemViewBinding) this.binding$delegate.getValue();
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public View getBlendTarget() {
        return this;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public boolean getCompatTile() {
        return this.compatTile;
    }

    @Override // miui.systemui.controlcenter.widget.ExpandableView
    public float getCornerRadius() {
        return this._cornerRadius;
    }

    public final QSCardItemIconView getIcon() {
        QSCardItemIconView qSCardItemIconView = this.icon;
        if (qSCardItemIconView != null) {
            return qSCardItemIconView;
        }
        n.w(Column.ICON);
        return null;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public MainPanelItemViewHolder.TouchAnimator getTouchAnimator() {
        return this.touchAnimator;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void init(QSCardItemIconView icon) {
        n.g(icon, "icon");
        this.icon = icon;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.qs_tile_item_icon_size);
        miui.systemui.widget.LinearLayout linearLayout = getBinding().scaleContent;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        layoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen.qs_card_item_icon_margin_start));
        layoutParams.gravity = 16;
        s sVar = s.f314a;
        linearLayout.addView((View) icon, 0, (ViewGroup.LayoutParams) layoutParams);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: miui.systemui.controlcenter.qs.tileview.QSCardItemView.init.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                n.g(host, "host");
                n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (TextUtils.isEmpty(QSCardItemView.this.accessibilityClass)) {
                    return;
                }
                info.setClassName(QSCardItemView.this.accessibilityClass);
                if (n.c(Switch.class.getName(), QSCardItemView.this.accessibilityClass)) {
                    boolean z2 = QSCardItemView.this.clicked;
                    boolean z3 = QSCardItemView.this.tileState;
                    if (z2) {
                        z3 = !z3;
                    }
                    info.setChecked(z3);
                    info.setCheckable(true);
                }
            }
        });
        icon.setFocusable(false);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zColorsChanged = configUtils.colorsChanged(i2);
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        boolean zTextAppearanceChanged = configUtils.textAppearanceChanged(i2);
        boolean zTextsChanged = configUtils.textsChanged(i2);
        boolean zBlurChanged = configUtils.blurChanged(i2);
        boolean zLayoutDirectionChanged = configUtils.layoutDirectionChanged(i2);
        if (zTextAppearanceChanged || zTextsChanged) {
            updateTextAppearance();
        }
        if (zDimensionsChanged) {
            updateSize();
        }
        if (zColorsChanged || zDimensionsChanged || zTextAppearanceChanged || zBlurChanged || zLayoutDirectionChanged) {
            updateResources();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        getBinding().title.setImportantForAccessibility(2);
        setHapticFeedbackEnabled(!HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE());
        OnClickListenerEx.INSTANCE.setOnClickListenerEx(this, new AnonymousClass1());
        setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.qs.tileview.b
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return QSCardItemView.onFinishInflate$lambda$2(this.f5484a, view);
            }
        });
        updateCornerRadius();
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void onStateChanged(QSTile.State state) {
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

    @Override // android.view.View
    public boolean performClick() {
        this.clicked = true;
        return super.performClick();
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void recycle() {
        super.recycle();
        cleanBlendAnimation();
        cancelAlphaAnimation();
    }

    public final void setBackgroundProgress(float f2) {
        if (this.backgroundProgress == f2) {
            return;
        }
        this.backgroundProgress = f2;
        updateBackgroundBlendModeAndColors(f2);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void setCompatTile(boolean z2) {
        this.compatTile = z2;
    }

    @Override // miui.systemui.controlcenter.widget.ExpandableView
    public void setCornerRadius(float f2) {
        if (getBackground() != null) {
            Drawable drawable = this.enabledBg;
            GradientDrawable gradientDrawable = drawable instanceof GradientDrawable ? (GradientDrawable) drawable : null;
            if (gradientDrawable != null) {
                gradientDrawable.setCornerRadius(f2);
            }
            Drawable drawable2 = this.disabledBg;
            GradientDrawable gradientDrawable2 = drawable2 instanceof GradientDrawable ? (GradientDrawable) drawable2 : null;
            if (gradientDrawable2 != null) {
                gradientDrawable2.setCornerRadius(f2);
            }
        }
        this._cornerRadius = f2;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void setTouchAnimator(MainPanelItemViewHolder.TouchAnimator touchAnimator) {
        this.touchAnimator = touchAnimator;
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void startMarquee() {
        if (this.marqueeLock) {
            return;
        }
        this.marqueeLock = true;
        this.mainHandler.postDelayed(this.marqueeRunnable, 1000L);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void stopMarquee() {
        if (this.marqueeLock) {
            this.mainHandler.removeCallbacks(this.marqueeRunnable);
            this.marqueeLock = false;
        }
        getBinding().title.stopMarqueeLocal();
        getBinding().status.stopMarqueeLocal();
    }

    public final void updateResources() {
        getIcon().updateResources();
        QSTile.State state = this.state;
        if (state != null) {
            updateState(state, this.connected, false);
        }
        updateBackground(false);
    }

    public final void updateSize() {
        updateCornerRadius();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        CommonUtils.setLayoutHeight$default(commonUtils, this, getResources().getDimensionPixelSize(R.dimen.qs_card_item_height), false, 2, null);
        CommonUtils.setMargins$default(commonUtils, this, getResources().getDimensionPixelSize(R.dimen.control_center_universal_margin), false, 2, null);
        QSIconView icon = getIcon();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.qs_tile_item_icon_size);
        CommonUtils.setLayoutSize$default(commonUtils, icon, dimensionPixelSize, dimensionPixelSize, false, 4, null);
        CommonUtils.setMarginStart$default(commonUtils, icon, getResources().getDimensionPixelSize(R.dimen.qs_card_item_icon_margin_start), false, 2, null);
        miui.systemui.widget.LinearLayout labelContainer = getBinding().labelContainer;
        n.f(labelContainer, "labelContainer");
        CommonUtils.setMargins$default(commonUtils, labelContainer, getResources().getDimensionPixelSize(R.dimen.qs_card_item_label_container_margin_start), 0, getResources().getDimensionPixelSize(R.dimen.qs_card_item_label_container_margin_end), 0, true, 10, null);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemView
    public void updateState(QSTile.State state, boolean z2, boolean z3) {
        CharSequence enabledString;
        QSTile.BooleanState booleanState;
        boolean z4;
        this.connected = z2;
        if (state == null) {
            return;
        }
        setClickable(state.state != 0);
        if ((n.c(state.spec, TileSpecsKt.TILE_SPEC_VOWIFI1) || n.c(state.spec, TileSpecsKt.TILE_SPEC_VOWIFI2)) && !isClickable()) {
            CommonUtils.removeAccessibilityClick(this, true);
        }
        getIcon().setIcon(state, z3);
        setContentDescription(state.contentDescription);
        this.accessibilityClass = state.expandedAccessibilityClassName;
        if ((state instanceof QSTile.BooleanState) && this.tileState != (z4 = (booleanState = (QSTile.BooleanState) state).value)) {
            this.tileState = z4;
            this.clicked = false;
            CharSequence charSequence = ((QSTile.State) booleanState).label;
            announceForAccessibility(((Object) charSequence) + aa.f3429b + getContext().getString(z4 ? R.string.switch_bar_on : R.string.switch_bar_off));
        }
        if (!n.c(state.spec, "cell") || (enabledString = state.secondaryLabel) == null) {
            boolean z5 = state.isTransient;
            if (z5 && state.state == 2) {
                enabledString = getOpeningString();
            } else if (z5 && state.state == 1) {
                enabledString = getClosingString();
            } else if (z2 && state.state == 2) {
                enabledString = getConnectedString();
            } else if (QSItemView.Companion.isRestrictedCompat(state) && state.state == 1) {
                enabledString = getRestrictedString();
            } else {
                int i2 = state.state;
                enabledString = i2 == 2 ? getEnabledString() : i2 == 1 ? getDisabledString() : getUnavailableString();
            }
        }
        getBinding().title.setTextColor(getContext().getColor(state.state == 2 ? R.color.qs_card_primary_text_enabled_color : (QSItemView.Companion.isRestrictedCompat(state) && state.state == 1) ? R.color.qs_card_primary_text_restricted_color : state.state == 1 ? R.color.qs_card_primary_text_disabled_color : R.color.qs_card_primary_text_unavailable_color));
        QSItemView.Companion companion = QSItemView.Companion;
        if (companion.isTrafficWarning(state)) {
            getBinding().status.setTextAppearance(R.style.TextAppearance_QS_CardSubTitle_Warning);
        } else {
            getBinding().status.setTextAppearance(R.style.TextAppearance_QS_CardSubTitle);
            getBinding().status.setTextColor(getContext().getColor(state.state == 2 ? R.color.qs_card_secondary_text_enabled_color : (companion.isRestrictedCompat(state) && state.state == 1) ? R.color.qs_card_secondary_text_restricted_color : state.state == 1 ? R.color.qs_card_secondary_text_disabled_color : R.color.qs_card_secondary_text_unavailable_color));
        }
        getBinding().title.setEnabled(true ^ state.disabledByPolicy);
        stopMarquee();
        FocusedTextView focusedTextView = getBinding().title;
        CharSequence charSequence2 = state.label;
        focusedTextView.setText(charSequence2 != null ? ControlCenterUtils.INSTANCE.getUnicodeWrapped(charSequence2) : null);
        getBinding().status.setText(enabledString != null ? ControlCenterUtils.INSTANCE.getUnicodeWrapped(enabledString) : null);
        startMarquee();
        this.state = state;
        updateBackground(z3);
        this.lastRestrictedState = companion.isRestrictedCompat(state);
    }

    public final void updateTextAppearance() {
        getBinding().title.setTextAppearance(R.style.TextAppearance_QS_CardTitle);
        getBinding().status.setTextAppearance(R.style.TextAppearance_QS_CardSubTitle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v5, types: [miui.systemui.controlcenter.qs.tileview.QSCardItemView$backgroundOutlineProvider$1] */
    public QSCardItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        this.binding$delegate = H0.e.b(new QSCardItemView$binding$2(this));
        this.mainHandler = new Handler(Looper.getMainLooper());
        AnimConfig animConfig = new AnimConfig();
        animConfig.setEase(FolmeEase.spring(0.95f, 0.35f));
        this.blendBackgroundAnimConfig = animConfig;
        this.backgroundOutlineProvider = new ViewOutlineProvider() { // from class: miui.systemui.controlcenter.qs.tileview.QSCardItemView$backgroundOutlineProvider$1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                n.g(view, "view");
                n.g(outline, "outline");
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.this$0._cornerRadius);
            }
        };
        setFocusable(true);
        setClipChildren(false);
        setClipToPadding(false);
        setClipToOutline(false);
        setOutlineProvider(ViewOutlineProviderExt.INSTANCE.getSOLID_BACKGROUND());
        this.marqueeRunnable = new Runnable() { // from class: miui.systemui.controlcenter.qs.tileview.a
            @Override // java.lang.Runnable
            public final void run() {
                QSCardItemView.marqueeRunnable$lambda$3(this.f5483a);
            }
        };
    }
}
