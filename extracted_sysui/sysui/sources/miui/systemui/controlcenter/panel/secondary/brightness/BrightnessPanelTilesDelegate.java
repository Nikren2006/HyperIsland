package miui.systemui.controlcenter.panel.secondary.brightness;

import H0.d;
import H0.e;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.qs.QSFactory;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.y;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.BrightnessPanelBinding;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.secondary.BrightnessPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.OnClickListenerEx;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class BrightnessPanelTilesDelegate extends SecondaryPanelDelegateBase<BrightnessPanelParams> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "BrightnessPanelTilesDelegate";
    private final BrightnessPanelAnimator animator;
    private final BrightnessPanelBinding binding;
    private final d callback$delegate;
    private final HapticFeedback hapticFeedback;
    private final MiuiQSHost host;
    private boolean listening;
    private final Handler mainHandler;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private final Context sysUIContext;
    private final d tileSpecs$delegate;
    private final d tileViews$delegate;
    private final d tiles$delegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelTilesDelegate(@SystemUI Context sysUIContext, @Main Handler mainHandler, ControlCenterSecondaryBinding secondaryBinding, BrightnessPanelBinding binding, MiuiQSHost host, HapticFeedback hapticFeedback, BrightnessPanelAnimator animator) {
        super(secondaryBinding);
        n.g(sysUIContext, "sysUIContext");
        n.g(mainHandler, "mainHandler");
        n.g(secondaryBinding, "secondaryBinding");
        n.g(binding, "binding");
        n.g(host, "host");
        n.g(hapticFeedback, "hapticFeedback");
        n.g(animator, "animator");
        this.sysUIContext = sysUIContext;
        this.mainHandler = mainHandler;
        this.secondaryBinding = secondaryBinding;
        this.binding = binding;
        this.host = host;
        this.hapticFeedback = hapticFeedback;
        this.animator = animator;
        this.tileSpecs$delegate = e.b(BrightnessPanelTilesDelegate$tileSpecs$2.INSTANCE);
        this.tiles$delegate = e.b(BrightnessPanelTilesDelegate$tiles$2.INSTANCE);
        this.tileViews$delegate = e.b(BrightnessPanelTilesDelegate$tileViews$2.INSTANCE);
        this.callback$delegate = e.b(new BrightnessPanelTilesDelegate$callback$2(this));
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private final void addQSTileItemView(String str) {
        int i2 = 0;
        if (getTileViews().get(str) != null) {
            QSTileItemView qSTileItemView = getTileViews().get(str);
            if (qSTileItemView == null) {
                return;
            }
            QSTile qSTile = getTiles().get(str);
            if (qSTile != null && !qSTile.isAvailable()) {
                i2 = 8;
            }
            qSTileItemView.setVisibility(i2);
            return;
        }
        QSTile qSTile2 = getQSTile(str);
        if (qSTile2 == null) {
            return;
        }
        final QSTileItemView qSTileItemView2 = getQSTileItemView(str);
        ViewParent parent = qSTileItemView2.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(qSTileItemView2);
        }
        updateBlendBlur(qSTileItemView2, qSTile2);
        ViewGroup.LayoutParams layoutParams = qSTileItemView2.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        }
        layoutParams2.weight = 1.0f;
        getVTilesContainer().addView(qSTileItemView2, layoutParams2);
        qSTileItemView2.setTag(qSTile2);
        qSTileItemView2.onTextModeChanged(QSListController.TextMode.TEXT, false);
        qSTileItemView2.updateState(qSTile2.getState(), false, false);
        qSTileItemView2.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.secondary.brightness.a
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return BrightnessPanelTilesDelegate.addQSTileItemView$lambda$8$lambda$7(qSTileItemView2, view, motionEvent);
            }
        });
        OnClickListenerEx onClickListenerEx = OnClickListenerEx.INSTANCE;
        onClickListenerEx.setOnClickListenerEx(qSTileItemView2, new BrightnessPanelTilesDelegate$addQSTileItemView$1$2(this, qSTile2, qSTileItemView2));
        onClickListenerEx.setOnLongClickListenerEx(qSTileItemView2, BrightnessPanelTilesDelegate$addQSTileItemView$1$3.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addQSTileItemView$lambda$8$lambda$7(QSTileItemView this_apply, View view, MotionEvent motionEvent) {
        n.g(this_apply, "$this_apply");
        Folme.useAt(view).touch().setTint(0.0f, 0.0f, 0.0f, 0.0f).onMotionEventEx(this_apply, motionEvent, new AnimConfig());
        return false;
    }

    private final QSTile.Callback getCallback() {
        return (QSTile.Callback) this.callback$delegate.getValue();
    }

    private final QSTile getQSTile(String str) {
        y yVar = new y();
        QSTile qSTile = getTiles().get(str);
        yVar.f5059a = qSTile;
        if (qSTile != null) {
            return qSTile;
        }
        ArrayList qsFactories = this.host.getQsFactories();
        if (qsFactories != null) {
            Iterator it = qsFactories.iterator();
            while (it.hasNext()) {
                QSTile qSTileCreateTile = ((QSFactory) it.next()).createTile(str, true);
                if (qSTileCreateTile != null) {
                    n.d(qSTileCreateTile);
                    qSTileCreateTile.setTileSpec(str);
                    qSTileCreateTile.userSwitch(ActivityManager.getCurrentUser());
                    qSTileCreateTile.refreshState();
                } else {
                    qSTileCreateTile = null;
                }
                yVar.f5059a = qSTileCreateTile;
                if (qSTileCreateTile != null) {
                    HashMap<String, QSTile> tiles = getTiles();
                    Object obj = yVar.f5059a;
                    n.e(obj, "null cannot be cast to non-null type com.android.systemui.plugins.qs.QSTile");
                    tiles.put(str, (QSTile) obj);
                    return (QSTile) yVar.f5059a;
                }
            }
        }
        return null;
    }

    private final QSTileItemView getQSTileItemView(String str) {
        QSTileItemView qSTileItemView = getTileViews().get(str);
        if (qSTileItemView != null) {
            return qSTileItemView;
        }
        View viewInflate = LayoutInflater.from(getVTilesContainer().getContext()).inflate(R.layout.qs_tile_item_view, (ViewGroup) getVTilesContainer(), false);
        n.e(viewInflate, "null cannot be cast to non-null type miui.systemui.controlcenter.qs.tileview.QSTileItemView");
        QSTileItemView qSTileItemView2 = (QSTileItemView) viewInflate;
        QSTileItemIconView qSTileItemIconView = new QSTileItemIconView(getContext(), this.sysUIContext, false, 4, null);
        qSTileItemIconView.setDetailTile(true);
        qSTileItemView2.setFocusable(true);
        qSTileItemView2.setNeedLongClickAction(false);
        qSTileItemView2.init(qSTileItemIconView);
        qSTileItemView2.updateAdded(true, false);
        getTileViews().put(str, qSTileItemView2);
        return qSTileItemView2;
    }

    private final List<String> getTileSpecs() {
        return (List) this.tileSpecs$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HashMap<String, QSTileItemView> getTileViews() {
        return (HashMap) this.tileViews$delegate.getValue();
    }

    private final HashMap<String, QSTile> getTiles() {
        return (HashMap) this.tiles$delegate.getValue();
    }

    private final miui.systemui.widget.LinearLayout getVTilesContainer() {
        miui.systemui.widget.LinearLayout tilesContainer = this.binding.tilesContainer;
        n.f(tilesContainer, "tilesContainer");
        return tilesContainer;
    }

    private final miui.systemui.widget.LinearLayout getVTilesContent() {
        miui.systemui.widget.LinearLayout tilesContent = this.binding.tilesContent;
        n.f(tilesContent, "tilesContent");
        return tilesContent;
    }

    private final void loadTileViews() {
        Iterator<T> it = getTileSpecs().iterator();
        while (it.hasNext()) {
            addQSTileItemView((String) it.next());
        }
        Collection<QSTile> collectionValues = getTiles().values();
        n.f(collectionValues, "<get-values>(...)");
        Iterator<T> it2 = collectionValues.iterator();
        while (it2.hasNext()) {
            ((QSTile) it2.next()).addCallback(getCallback());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClickInternal(QSTile qSTile, QSTileItemView qSTileItemView) {
        ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
        String screenType = EventTracker.Companion.getScreenType(getContext());
        int i2 = getContext().getResources().getConfiguration().orientation;
        String tileSpec = qSTile.getTileSpec();
        n.f(tileSpec, "getTileSpec(...)");
        int iIndexOf = getTileSpecs().indexOf(qSTile.getTileSpec());
        QSTile.State state = qSTile.getState();
        companion.trackSecondaryQuickSettingsClickEvent(screenType, true, i2, tileSpec, iIndexOf, "二级页更多按钮", 1, state != null ? state.state : 2);
        if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
            this.hapticFeedback.postClick();
        } else {
            qSTileItemView.performHapticFeedback(1);
        }
        qSTile.click();
    }

    private final void refreshTiles() {
        Collection<QSTile> collectionValues = getTiles().values();
        n.f(collectionValues, "<get-values>(...)");
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            ((QSTile) it.next()).refreshState();
        }
    }

    private final void unloadTileViews() {
        Collection<QSTile> collectionValues = getTiles().values();
        n.f(collectionValues, "<get-values>(...)");
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            ((QSTile) it.next()).removeCallback(getCallback());
        }
        Collection<QSTileItemView> collectionValues2 = getTileViews().values();
        n.f(collectionValues2, "<get-values>(...)");
        for (QSTileItemView qSTileItemView : collectionValues2) {
            ViewParent parent = qSTileItemView.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(qSTileItemView);
            }
        }
        getVTilesContainer().removeAllViews();
        getTileViews().clear();
    }

    private final void updateBlendBlur(QSTileItemView qSTileItemView, QSTile qSTile) {
        View blendTarget = qSTileItemView.getBlendTarget();
        Context context = blendTarget.getContext();
        n.f(context, "getContext(...)");
        if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context)) {
            CommonUtils.INSTANCE.clearMiBlurBlendEffect(blendTarget);
        } else {
            MiBlurCompat.setMiViewBlurModeCompat(blendTarget, 1);
            MiBlurCompat.setMiBackgroundBlendColors(blendTarget, qSTile.getState().state == 2 ? MiuiColorBlendToken.INSTANCE.getCC_TILE_ON_BLEND_COLORS() : MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_TILE_DEFAULT_BLEND_COLORS());
        }
    }

    private final void updateSize(int i2) {
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        CommonUtils.setMarginBottom$default(commonUtils, getVTilesContent(), getContext().getResources().getDimensionPixelSize(commonUtils.getInVerticalMode(getContext()) ? R.dimen.brightness_panel_qs_container_margin_bottom_large : R.dimen.brightness_panel_qs_container_margin_bottom_small), false, 2, null);
        Collection<QSTileItemView> collectionValues = getTileViews().values();
        n.f(collectionValues, "<get-values>(...)");
        for (QSTileItemView qSTileItemView : collectionValues) {
            qSTileItemView.updateSize();
            qSTileItemView.updateTextAppearance();
            qSTileItemView.getIcon().updateResources();
            QSTileItemIconView.updateIcon$default(qSTileItemView.getIcon(), null, this.animator.isExpanded(), false, true, ConfigUtils.INSTANCE.uiModeChanged(i2), 5, null);
        }
        miui.systemui.widget.LinearLayout vTilesContainer = getVTilesContainer();
        ViewGroup.LayoutParams layoutParams = getVTilesContainer().getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        }
        vTilesContainer.setLayoutParams(layoutParams2);
    }

    public static /* synthetic */ void updateSize$default(BrightnessPanelTilesDelegate brightnessPanelTilesDelegate, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        brightnessPanelTilesDelegate.updateSize(i2);
    }

    public final boolean getListening() {
        return this.listening;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zOrientationChanged = configUtils.orientationChanged(i2);
        if (configUtils.dimensionsChanged(i2) || zOrientationChanged || configUtils.uiModeChanged(i2)) {
            updateSize(i2);
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        loadTileViews();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        unloadTileViews();
    }

    public final void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
        Collection<QSTile> collectionValues = getTiles().values();
        n.f(collectionValues, "<get-values>(...)");
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            ((QSTile) it.next()).setListening(this, z2);
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareShow(BrightnessPanelParams brightnessPanelParams) {
        super.prepareShow(brightnessPanelParams);
        updateSize$default(this, 0, 1, null);
        loadTileViews();
        refreshTiles();
    }
}
