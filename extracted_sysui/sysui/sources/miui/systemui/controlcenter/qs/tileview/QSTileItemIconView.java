package miui.systemui.controlcenter.qs.tileview;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.VectorDrawable;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.systemui.plugins.qs.QSTile;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.Interpolators;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.widget.ExpandableView;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DrawableUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.ThemeUtils;
import miui.systemui.widget.ImageView;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.property.FloatProperty;
import miuix.animation.utils.EaseManager;
import miuix.theme.token.ColorBlendToken;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UseCompatLoadingForDrawables"})
public final class QSTileItemIconView extends LinearLayout implements ExpandableView, QSItemIconView {
    public static final long QS_ANIM_LENGTH = 300;
    private static final String TAG = "QSTileItemIconView";
    private IStateStyle _backgroundAnimator;
    private float _cornerRadius;
    private boolean animationEnabled;
    private ObjectAnimator animator;
    private int autoBrightnessTileIconColor;
    private final boolean card;
    private float customBitmapTileSize;
    private float customVectorTileSize;
    private int defaultIconColor;
    private int defaultIconColorOff;
    private int defaultIconColorRestrict;
    private int defaultIconColorUnavailable;
    private Drawable disabledBg;
    private Drawable enabledBg;
    private final ImageView icon;
    private AnimatedVectorDrawable iconDrawable;
    private float iconProgress;
    private boolean iconUpdatePending;
    private boolean isDetailTile;
    private boolean lastRestrictedState;
    private int miuiCellularTileIconColor;
    private int miuiFlashlightTileIconColor;
    private int muteTileIconColor;
    private int paperModeTileIconColor;
    private final Context pluginContext;
    private int powerSaferTileIconColor;
    private int quietModeTileIconColor;
    private QSTile.State state;
    private float tileSize;
    public static final Companion Companion = new Companion(null);
    private static final Choreographer choreographer = Choreographer.getInstance();
    private static final CopyOnWriteArrayList<AnimatedVectorDrawable> runningDrawables = new CopyOnWriteArrayList<>();
    private static final QSTileItemIconView$Companion$animationCallback$1 animationCallback = new Animatable2.AnimationCallback() { // from class: miui.systemui.controlcenter.qs.tileview.QSTileItemIconView$Companion$animationCallback$1
        @Override // android.graphics.drawable.Animatable2.AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            QSTileItemIconView.Companion.endAnim(drawable);
        }

        @Override // android.graphics.drawable.Animatable2.AnimationCallback
        public void onAnimationStart(Drawable drawable) {
            AnimatedVectorDrawable animatedVectorDrawable = drawable instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) drawable : null;
            if (animatedVectorDrawable == null) {
                return;
            }
            QSTileItemIconView.runningDrawables.add(animatedVectorDrawable);
            QSTileItemIconView.choreographer.postFrameCallback(QSTileItemIconView.frameCallback);
        }
    };
    private static final QSTileItemIconView$Companion$frameCallback$1 frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.controlcenter.qs.tileview.QSTileItemIconView$Companion$frameCallback$1
        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j2) {
            if (QSTileItemIconView.runningDrawables.isEmpty()) {
                return;
            }
            QSTileItemIconView.choreographer.postFrameCallback(this);
            Iterator it = QSTileItemIconView.runningDrawables.iterator();
            while (it.hasNext()) {
                ((AnimatedVectorDrawable) it.next()).invalidateSelf();
            }
        }
    };
    private static final QSTileItemIconView$Companion$ICON_PROGRESS$1 ICON_PROGRESS = new FloatProperty<QSTileItemIconView>() { // from class: miui.systemui.controlcenter.qs.tileview.QSTileItemIconView$Companion$ICON_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(QSTileItemIconView qSTileItemIconView) {
            if (qSTileItemIconView != null) {
                return qSTileItemIconView.iconProgress;
            }
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(QSTileItemIconView qSTileItemIconView, float f2) {
            if (qSTileItemIconView == null) {
                return;
            }
            qSTileItemIconView.setIconProgress(f2);
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void endAnim(Drawable drawable) {
            AnimatedVectorDrawable animatedVectorDrawable = drawable instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) drawable : null;
            if (animatedVectorDrawable == null) {
                return;
            }
            QSTileItemIconView.runningDrawables.remove(animatedVectorDrawable);
            animatedVectorDrawable.unregisterAnimationCallback(QSTileItemIconView.animationCallback);
        }

        public final void onStop() {
            if (!QSTileItemIconView.runningDrawables.isEmpty()) {
                Iterator it = QSTileItemIconView.runningDrawables.iterator();
                while (it.hasNext()) {
                    QSTileItemIconView.Companion.endAnim((AnimatedVectorDrawable) it.next());
                }
                QSTileItemIconView.runningDrawables.clear();
            }
            Folme.clean(this);
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSTileItemIconView(Context pluginContext) {
        this(pluginContext, null, false, 6, null);
        n.g(pluginContext, "pluginContext");
    }

    private final void applyBlendColor(QSTile.State state, boolean z2) {
        ColorBlendToken cc_tile_on_blend_colors = state.state == 2 ? MiuiColorBlendToken.INSTANCE.getCC_TILE_ON_BLEND_COLORS() : (n.c(state.spec, "bt") && QSItemView.Companion.isRestrictedCompat(state)) ? MiuiColorBlendToken.INSTANCE.getCC_TILE_RESTRICTED_BLEND_COLORS() : this.isDetailTile ? MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_TILE_DEFAULT_BLEND_COLORS() : MiuiColorBlendToken.INSTANCE.getCC_TILE_DEFAULT_BLEND_COLORS();
        MiBlurCompat.setMiViewBlurModeCompat(this.icon, 1);
        MiBlurCompat.setMiBackgroundBlendColors(this.icon, cc_tile_on_blend_colors);
        boolean z3 = state.state != 2;
        if (z2) {
            getBackgroundAnimator().to(ICON_PROGRESS, Float.valueOf(z3 ? 0.0f : 1.0f));
        } else {
            getBackgroundAnimator().setTo(ICON_PROGRESS, Float.valueOf(z3 ? 0.0f : 1.0f));
        }
    }

    private final void cancelAlphaAnimation() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator.removeAllListeners();
            objectAnimator.removeAllUpdateListeners();
        }
        this.animator = null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void drawableTint(QSTile.State state, Drawable drawable) {
        drawable.mutate();
        drawable.setAutoMirrored(false);
        int i2 = state.state;
        if (i2 == 0) {
            drawable.setTint(this.defaultIconColorUnavailable);
            return;
        }
        if (i2 == 1) {
            drawable.setTint(QSItemView.Companion.isRestrictedCompat(state) ? this.defaultIconColorRestrict : this.defaultIconColorOff);
            return;
        }
        if (i2 != 2) {
            return;
        }
        String str = state.spec;
        if (str != null) {
            switch (str.hashCode()) {
                case -1672707928:
                    if (str.equals("batterysaver")) {
                        drawable.setTint(this.powerSaferTileIconColor);
                        return;
                    }
                    break;
                case -1183073498:
                    if (str.equals("flashlight")) {
                        drawable.setTint(this.miuiFlashlightTileIconColor);
                        return;
                    }
                    break;
                case -919209152:
                    if (str.equals("autobrightness")) {
                        drawable.setTint(this.autoBrightnessTileIconColor);
                        return;
                    }
                    break;
                case 3049826:
                    if (str.equals("cell")) {
                        drawable.setTint(this.miuiCellularTileIconColor);
                        return;
                    }
                    break;
                case 3363353:
                    if (str.equals("mute")) {
                        drawable.setTint(this.muteTileIconColor);
                        return;
                    }
                    break;
                case 298820911:
                    if (str.equals("papermode")) {
                        drawable.setTint(this.paperModeTileIconColor);
                        return;
                    }
                    break;
                case 1367090647:
                    if (str.equals("quietmode")) {
                        drawable.setTint(this.quietModeTileIconColor);
                        return;
                    }
                    break;
            }
        }
        drawable.setTint(this.defaultIconColor);
    }

    private final Drawable getActiveBackgroundDrawable(QSTile.State state) {
        return this.pluginContext.getTheme().getDrawable(this.card ? R.drawable.qs_card_background_enabled : state.activeBgColor == 1 ? this.isDetailTile ? R.drawable.qs_detail_background_warning : R.drawable.qs_background_warning : this.isDetailTile ? R.drawable.qs_detail_background_enabled : R.drawable.qs_background_enabled);
    }

    private final IStateStyle getBackgroundAnimator() {
        IStateStyle iStateStyle = this._backgroundAnimator;
        if (iStateStyle != null) {
            return iStateStyle;
        }
        IStateStyle iStateStyleUseValue = Folme.useValue(this);
        iStateStyleUseValue.setEase(EaseManager.getStyle(-2, 0.95f, 0.35f), ICON_PROGRESS);
        this._backgroundAnimator = iStateStyleUseValue;
        return iStateStyleUseValue;
    }

    private final Drawable getBackgroundDrawable(QSTile.State state) {
        Drawable drawable;
        int i2 = state.state;
        if (i2 == 0) {
            if (this.card) {
                drawable = this.pluginContext.getTheme().getDrawable(R.drawable.qs_card_background_unavailable);
            } else {
                drawable = this.pluginContext.getTheme().getDrawable(this.isDetailTile ? R.drawable.qs_detail_background_unavailable : R.drawable.qs_background_unavailable);
            }
            n.d(drawable);
            return drawable;
        }
        if (i2 != 2) {
            Drawable disabledBackgroundDrawable = getDisabledBackgroundDrawable(state);
            n.f(disabledBackgroundDrawable, "getDisabledBackgroundDrawable(...)");
            return disabledBackgroundDrawable;
        }
        Drawable activeBackgroundDrawable = getActiveBackgroundDrawable(state);
        n.f(activeBackgroundDrawable, "getActiveBackgroundDrawable(...)");
        return activeBackgroundDrawable;
    }

    private final Drawable getDisabledBackgroundDrawable(QSTile.State state) {
        return this.pluginContext.getTheme().getDrawable((this.card && isRestrictedState()) ? R.drawable.qs_card_background_restricted : this.card ? R.drawable.qs_card_background_disabled : isRestrictedState() ? this.isDetailTile ? R.drawable.qs_detail_background_restricted : R.drawable.qs_background_restricted : this.isDetailTile ? R.drawable.qs_detail_background_disabled : R.drawable.qs_background_disabled);
    }

    private final int getProperIconSize(Drawable drawable) {
        float f2 = drawable instanceof AnimatedVectorDrawable ? this.tileSize : (!(drawable instanceof VectorDrawable) && (drawable instanceof BitmapDrawable)) ? this.customBitmapTileSize : this.customVectorTileSize;
        return (int) f2;
    }

    private final boolean isRestrictedState() {
        return ControlCenterUtils.INSTANCE.getRestrictedState(this.state, this.lastRestrictedState);
    }

    private final void liteIconUpdate(QSTile.State state, Drawable drawable) {
        drawableTint(state, drawable);
        setEnabledBg(getBackgroundDrawable(state));
        int properIconSize = getProperIconSize(drawable);
        LayerDrawable layerDrawableCombine = DrawableUtils.combine(this.enabledBg, drawable, 17);
        n.f(layerDrawableCombine, "combine(...)");
        layerDrawableCombine.setLayerSize(1, properIconSize, properIconSize);
        this.icon.setImageDrawable(layerDrawableCombine);
    }

    private final void setDisabledBg(Drawable drawable) {
        this.disabledBg = drawable;
        this.icon.invalidateOutline();
    }

    private final void setEnabledBg(Drawable drawable) {
        this.enabledBg = drawable;
        this.icon.invalidateOutline();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setIconProgress(float f2) {
        this.iconProgress = f2;
        MiBlurCompat.setMiViewBlurModeCompat(this.icon, 1);
        MiBlurCompat.setMiBackgroundBlendColors((View) this.icon, isRestrictedState() ? MiuiColorBlendToken.INSTANCE.getCC_TILE_RESTRICTED_BLEND_COLORS() : this.isDetailTile ? MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_TILE_DEFAULT_BLEND_COLORS() : MiuiColorBlendToken.INSTANCE.getCC_TILE_DEFAULT_BLEND_COLORS(), MiuiColorBlendToken.INSTANCE.getCC_TILE_ON_BLEND_COLORS(), this.iconProgress, true);
    }

    private final boolean shouldAnimate(int i2, int i3, int i4, int i5, String str, String str2, Boolean bool) {
        if ((i4 == i2 && i5 == i3) || !this.animationEnabled || i2 == 0) {
            return false;
        }
        if (i2 == 1 && (i4 == 0 || i4 == -1 || !n.c(str2, str))) {
            return false;
        }
        return n.c(bool, Boolean.TRUE);
    }

    private final void startAlphaAnimation(Drawable drawable, int i2) {
        cancelAlphaAnimation();
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, "alpha", 255 - i2, i2);
        objectAnimatorOfInt.setDuration(300L);
        objectAnimatorOfInt.setInterpolator(Interpolators.INSTANCE.getCUBIC_EASE_OUT());
        objectAnimatorOfInt.start();
        this.animator = objectAnimatorOfInt;
    }

    private final void updateCornerRadius() {
        this._cornerRadius = this.pluginContext.getResources().getDimensionPixelSize(R.dimen.control_center_universal_corner_radius);
    }

    public static /* synthetic */ void updateIcon$default(QSTileItemIconView qSTileItemIconView, QSTile.State state, boolean z2, boolean z3, boolean z4, boolean z5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            state = qSTileItemIconView.state;
        }
        QSTile.State state2 = state;
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        qSTileItemIconView.updateIcon(state2, z2, z3, (i2 & 8) != 0 ? false : z4, (i2 & 16) != 0 ? false : z5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateIcon$lambda$3(QSTileItemIconView this$0, boolean z2, boolean z3, boolean z4) {
        n.g(this$0, "this$0");
        this$0.iconUpdatePending = false;
        this$0.updateIconInternal(this$0.state, z2, z3, z4, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x012a A[PHI: r14 r15 r18 r19 r20 r21
      0x012a: PHI (r14v6 int) = (r14v2 int), (r14v8 int) binds: [B:62:0x0128, B:57:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x012a: PHI (r15v4 java.lang.Object) = (r15v2 java.lang.Object), (r15v5 java.lang.Object) binds: [B:62:0x0128, B:57:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x012a: PHI (r18v3 int) = (r18v1 int), (r18v5 int) binds: [B:62:0x0128, B:57:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x012a: PHI (r19v3 int) = (r19v1 int), (r19v4 int) binds: [B:62:0x0128, B:57:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x012a: PHI (r20v3 int) = (r20v1 int), (r20v4 int) binds: [B:62:0x0128, B:57:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x012a: PHI (r21v3 int) = (r21v1 int), (r21v4 int) binds: [B:62:0x0128, B:57:0x0113] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateIconInternal(com.android.systemui.plugins.qs.QSTile.State r23, boolean r24, boolean r25, boolean r26, boolean r27) {
        /*
            Method dump skipped, instruction units count: 559
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.qs.tileview.QSTileItemIconView.updateIconInternal(com.android.systemui.plugins.qs.QSTile$State, boolean, boolean, boolean, boolean):void");
    }

    public static /* synthetic */ void updateIconInternal$default(QSTileItemIconView qSTileItemIconView, QSTile.State state, boolean z2, boolean z3, boolean z4, boolean z5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            state = qSTileItemIconView.state;
        }
        qSTileItemIconView.updateIconInternal(state, z2, (i2 & 4) != 0 ? false : z3, z4, (i2 & 16) != 0 ? false : z5);
    }

    private final void updateIconSize() {
        int dimensionPixelSize = this.pluginContext.getResources().getDimensionPixelSize(this.card ? R.dimen.control_center_universal_1_row_size : R.dimen.qs_tile_item_icon_size);
        CommonUtils.setLayoutSize$default(CommonUtils.INSTANCE, this.icon, dimensionPixelSize, dimensionPixelSize, false, 4, null);
    }

    @Override // miui.systemui.controlcenter.qs.tileview.QSItemIconView
    public void forceStopIconAnimate() {
        AnimatedVectorDrawable animatedVectorDrawable = this.iconDrawable;
        if (animatedVectorDrawable != null) {
            animatedVectorDrawable.stop();
        }
    }

    @Override // miui.systemui.controlcenter.widget.ExpandableView
    public float getCornerRadius() {
        return this.card ? this._cornerRadius : this.tileSize / 2;
    }

    public final ImageView getIcon() {
        return this.icon;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final boolean isDetailTile() {
        return this.isDetailTile;
    }

    public final void recycle() {
        if (this._backgroundAnimator != null) {
            Folme.clean(this);
            this._backgroundAnimator = null;
        }
    }

    @Override // miui.systemui.controlcenter.widget.ExpandableView
    public void setCornerRadius(float f2) {
        Drawable drawable = this.enabledBg;
        GradientDrawable gradientDrawable = drawable instanceof GradientDrawable ? (GradientDrawable) drawable : null;
        if (gradientDrawable != null) {
            gradientDrawable.setCornerRadius(f2);
        }
        Drawable drawable2 = this.disabledBg;
        GradientDrawable gradientDrawable2 = drawable2 instanceof GradientDrawable ? (GradientDrawable) drawable2 : null;
        if (gradientDrawable2 == null) {
            return;
        }
        gradientDrawable2.setCornerRadius(f2);
    }

    public final void setDetailTile(boolean z2) {
        this.isDetailTile = z2;
    }

    public final void updateIcon(QSTile.State state, final boolean z2, final boolean z3, final boolean z4, boolean z5) {
        this.state = state;
        if (state == null) {
            return;
        }
        if (z5 && n.c(state.spec, "night")) {
            this.iconUpdatePending = true;
            this.icon.post(new Runnable() { // from class: miui.systemui.controlcenter.qs.tileview.c
                @Override // java.lang.Runnable
                public final void run() {
                    QSTileItemIconView.updateIcon$lambda$3(this.f5485a, z2, z4, z3);
                }
            });
        } else {
            if (this.iconUpdatePending) {
                return;
            }
            updateIconInternal(state, z2, z4, z3, z5);
        }
    }

    public final void updateResources() {
        updateCornerRadius();
        updateIconSize();
        QSTile.State state = this.state;
        if (state != null) {
            Context context = getContext();
            n.f(context, "getContext(...)");
            if (ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context)) {
                applyBlendColor(state, false);
            } else {
                CommonUtils.INSTANCE.clearMiBlurBlendEffect(this.icon);
            }
        }
        this.defaultIconColor = this.pluginContext.getColor(R.color.qs_icon_enabled_color);
        this.defaultIconColorOff = this.pluginContext.getColor(R.color.qs_icon_disabled_color);
        this.defaultIconColorUnavailable = this.pluginContext.getColor(R.color.qs_icon_unavailable_color);
        this.defaultIconColorRestrict = this.pluginContext.getColor(R.color.qs_icon_restrict_color);
        this.customVectorTileSize = this.pluginContext.getResources().getDimension(R.dimen.qs_tile_item_icon_inner_size);
        this.customBitmapTileSize = this.pluginContext.getResources().getDimension(R.dimen.qs_tile_item_icon_bitmap_size);
        this.tileSize = this.pluginContext.getResources().getDimension(R.dimen.control_center_universal_1_row_size);
        setCornerRadius(getCornerRadius());
        this.paperModeTileIconColor = this.pluginContext.getColor(R.color.qs_paper_mode_icon_enabled_color);
        this.miuiFlashlightTileIconColor = this.pluginContext.getColor(R.color.qs_miui_flashlight_icon_enabled_color);
        this.powerSaferTileIconColor = this.pluginContext.getColor(R.color.qs_power_safer_icon_enabled_color);
        this.quietModeTileIconColor = this.pluginContext.getColor(R.color.qs_quiet_mode_icon_enabled_color);
        this.autoBrightnessTileIconColor = this.pluginContext.getColor(R.color.qs_auto_brightness_icon_enabled_color);
        this.miuiCellularTileIconColor = this.pluginContext.getColor(R.color.qs_miui_cellular_icon_enabled_color);
        this.muteTileIconColor = this.pluginContext.getColor(R.color.qs_mute_icon_enabled_color);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSTileItemIconView(Context pluginContext, Context sysUIContext) {
        this(pluginContext, sysUIContext, false, 4, null);
        n.g(pluginContext, "pluginContext");
        n.g(sysUIContext, "sysUIContext");
    }

    public /* synthetic */ QSTileItemIconView(Context context, Context context2, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? context : context2, (i2 & 4) != 0 ? false : z2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSTileItemIconView(Context pluginContext, Context sysUIContext, boolean z2) {
        super(sysUIContext);
        n.g(pluginContext, "pluginContext");
        n.g(sysUIContext, "sysUIContext");
        this.pluginContext = pluginContext;
        this.card = z2;
        Context context = getContext();
        n.f(context, "getContext(...)");
        ImageView imageView = new ImageView(context, null, 0, 0, 14, null);
        imageView.setId(R.id.icon);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(imageView);
        imageView.setClipToOutline(true);
        imageView.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.controlcenter.qs.tileview.QSTileItemIconView$icon$1$1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                float f2;
                float cornerRadius;
                n.g(view, "view");
                n.g(outline, "outline");
                Drawable drawable = this.this$0.enabledBg;
                GradientDrawable gradientDrawable = null;
                GradientDrawable gradientDrawable2 = drawable instanceof GradientDrawable ? (GradientDrawable) drawable : null;
                if (gradientDrawable2 == null) {
                    Drawable drawable2 = this.this$0.disabledBg;
                    if (drawable2 instanceof GradientDrawable) {
                        gradientDrawable = (GradientDrawable) drawable2;
                    }
                } else {
                    gradientDrawable = gradientDrawable2;
                }
                if (gradientDrawable != null) {
                    cornerRadius = gradientDrawable.getCornerRadius();
                } else {
                    if (!ThemeUtils.INSTANCE.getDefaultPluginTheme()) {
                        f2 = 0.0f;
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), f2);
                        outline.setAlpha(0.0f);
                    }
                    cornerRadius = this.this$0.getCornerRadius();
                }
                f2 = cornerRadius;
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), f2);
                outline.setAlpha(0.0f);
            }
        });
        this.icon = imageView;
        QSTile.State state = new QSTile.State();
        state.state = 1;
        this.state = state;
        this.animationEnabled = true;
        updateResources();
        setClipToOutline(false);
        setClipChildren(false);
    }
}
