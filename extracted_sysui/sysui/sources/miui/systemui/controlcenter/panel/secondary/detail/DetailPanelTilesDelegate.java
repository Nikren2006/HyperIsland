package miui.systemui.controlcenter.panel.secondary.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.QsCardItemViewBinding;
import miui.systemui.controlcenter.panel.secondary.DetailFromView;
import miui.systemui.controlcenter.panel.secondary.DetailPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelAnimatorBase;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelAnimator;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.qs.tileview.QSCardItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSCardItemView;
import miui.systemui.controlcenter.qs.tileview.QSItemView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.widget.NonTouchableFrameLayout;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class DetailPanelTilesDelegate extends SecondaryPanelDelegateBase<DetailPanelParams> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DetailPanelTilesDelegate";
    private QSTile fakeTile;
    private ViewGroup fakeView;
    private View fakeViewScaleContent;
    private final E0.a qsControllerLazy;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private final Context sysUIContext;
    private final H0.d tiles$delegate;
    private final H0.d views$delegate;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailPanelTilesDelegate(@SystemUI Context sysUIContext, ControlCenterSecondaryBinding secondaryBinding, E0.a qsControllerLazy, E0.a windowViewController) {
        super(secondaryBinding);
        n.g(sysUIContext, "sysUIContext");
        n.g(secondaryBinding, "secondaryBinding");
        n.g(qsControllerLazy, "qsControllerLazy");
        n.g(windowViewController, "windowViewController");
        this.sysUIContext = sysUIContext;
        this.secondaryBinding = secondaryBinding;
        this.qsControllerLazy = qsControllerLazy;
        this.windowViewController = windowViewController;
        this.tiles$delegate = H0.e.b(DetailPanelTilesDelegate$tiles$2.INSTANCE);
        this.views$delegate = H0.e.b(DetailPanelTilesDelegate$views$2.INSTANCE);
    }

    private final void clearTiles() {
        getTiles().clear();
        Collection<ViewGroup> collectionValues = getViews().values();
        n.f(collectionValues, "<get-values>(...)");
        for (ViewGroup viewGroup : collectionValues) {
            ViewParent parent = viewGroup.getParent();
            ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup2 != null) {
                viewGroup2.removeView(viewGroup);
            }
        }
        getViews().clear();
    }

    private final void createTiles() {
        QSTile qSTileCreateTile;
        clearTiles();
        QSController qSController = (QSController) this.qsControllerLazy.get();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        for (String str : qSController.getCardStyleTileSpecs()) {
            n.d(str);
            QSTile qSTileCreateTile2 = qSController.createTile(str);
            if (qSTileCreateTile2 != null) {
                HashMap<String, QSTile> tiles = getTiles();
                String tileSpec = qSTileCreateTile2.getTileSpec();
                n.f(tileSpec, "getTileSpec(...)");
                tiles.put(tileSpec, qSTileCreateTile2);
                HashMap<String, ViewGroup> views = getViews();
                String tileSpec2 = qSTileCreateTile2.getTileSpec();
                n.f(tileSpec2, "getTileSpec(...)");
                QSCardItemView root = QsCardItemViewBinding.inflate(layoutInflaterFrom).getRoot();
                Context context = root.getContext();
                n.f(context, "getContext(...)");
                root.init(new QSCardItemIconView(context, this.sysUIContext, null, 4, null));
                updateViewBlur(qSTileCreateTile2, root);
                root.getBinding().title.setMarqueeRepeatLimit(0);
                root.getBinding().status.setMarqueeRepeatLimit(0);
                n.f(root, "apply(...)");
                views.put(tileSpec2, root);
            }
        }
        if (!qSController.supportMobileTilesStyle() || (qSTileCreateTile = qSController.createTile("bt")) == null) {
            return;
        }
        HashMap<String, QSTile> tiles2 = getTiles();
        String tileSpec3 = qSTileCreateTile.getTileSpec();
        n.f(tileSpec3, "getTileSpec(...)");
        tiles2.put(tileSpec3, qSTileCreateTile);
        HashMap<String, ViewGroup> views2 = getViews();
        String tileSpec4 = qSTileCreateTile.getTileSpec();
        n.f(tileSpec4, "getTileSpec(...)");
        ViewGroup qSTileItemIconView = new QSTileItemIconView(getContext(), this.sysUIContext, false, 4, null);
        updateViewBlur(qSTileCreateTile, qSTileItemIconView);
        views2.put(tileSpec4, qSTileItemIconView);
    }

    private final HashMap<String, QSTile> getTiles() {
        return (HashMap) this.tiles$delegate.getValue();
    }

    private final HashMap<String, ViewGroup> getViews() {
        return (HashMap) this.views$delegate.getValue();
    }

    private final void updateView(QSTile qSTile) {
        ViewGroup viewGroup;
        if (qSTile == null || (viewGroup = this.fakeView) == null) {
            return;
        }
        if (viewGroup instanceof QSCardItemView) {
            ((QSCardItemView) viewGroup).updateState(qSTile.getState(), qSTile.isConnected(), false);
        } else if (viewGroup instanceof QSTileItemIconView) {
            QSTileItemIconView.updateIcon$default((QSTileItemIconView) viewGroup, qSTile.getState(), false, false, false, false, 28, null);
        }
    }

    private final void updateViewBlur(QSTile qSTile, View view) {
        QSTile.State state;
        View blendTarget = view instanceof QSCardItemView ? ((QSCardItemView) view).getBlendTarget() : view instanceof QSTileItemIconView ? ((QSTileItemIconView) view).getIcon() : null;
        if (blendTarget != null) {
            Context context = blendTarget.getContext();
            n.f(context, "getContext(...)");
            if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(context)) {
                CommonUtils.INSTANCE.clearMiBlurBlendEffect(blendTarget);
                return;
            }
            MiBlurCompat.setMiViewBlurModeCompat(blendTarget, 1);
            if (n.c(qSTile != null ? qSTile.getTileSpec() : null, "bt") && (state = qSTile.getState()) != null) {
                QSItemView.Companion companion = QSItemView.Companion;
                n.d(state);
                if (companion.isRestrictedCompat(state)) {
                    MiBlurCompat.setMiBackgroundBlendColors(blendTarget, MiuiColorBlendToken.INSTANCE.getCC_TILE_RESTRICTED_BLEND_COLORS());
                    return;
                }
            }
            MiBlurCompat.setMiBackgroundBlendColors(blendTarget, MiuiColorBlendToken.INSTANCE.getCC_TILE_DEFAULT_BLEND_COLORS());
        }
    }

    public final ViewGroup getFakeView() {
        return this.fakeView;
    }

    public final View getFakeViewScaleContent() {
        return this.fakeViewScaleContent;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zColorsChanged = configUtils.colorsChanged(i2);
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        boolean zTextAppearanceChanged = configUtils.textAppearanceChanged(i2);
        boolean zTextsChanged = configUtils.textsChanged(i2);
        boolean zBlurChanged = configUtils.blurChanged(i2);
        if (zColorsChanged || zDimensionsChanged || zTextAppearanceChanged || zTextsChanged || zBlurChanged) {
            Collection<ViewGroup> collectionValues = getViews().values();
            n.f(collectionValues, "<get-values>(...)");
            for (ViewGroup viewGroup : collectionValues) {
                if (viewGroup instanceof QSCardItemView) {
                    QSCardItemView qSCardItemView = (QSCardItemView) viewGroup;
                    qSCardItemView.updateSize();
                    qSCardItemView.updateTextAppearance();
                    qSCardItemView.updateResources();
                } else if (viewGroup instanceof QSTileItemIconView) {
                    QSTileItemIconView qSTileItemIconView = (QSTileItemIconView) viewGroup;
                    qSTileItemIconView.updateResources();
                    QSTileItemIconView.updateIcon$default(qSTileItemIconView, null, false, false, true, false, 21, null);
                }
            }
        }
        if (zBlurChanged) {
            for (Map.Entry<String, ViewGroup> entry : getViews().entrySet()) {
                String key = entry.getKey();
                updateViewBlur(getTiles().get(key), entry.getValue());
            }
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        createTiles();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        clearTiles();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void onHidden() {
        super.onHidden();
        NonTouchableFrameLayout nonTouchableFrameLayout = this.secondaryBinding.fakeContainer;
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        n.d(nonTouchableFrameLayout);
        commonUtils.setGone(nonTouchableFrameLayout);
        nonTouchableFrameLayout.removeAllViews();
        this.fakeTile = null;
        ViewGroup viewGroup = this.fakeView;
        if (viewGroup != null) {
            ViewParent parent = viewGroup.getParent();
            ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup2 != null) {
                viewGroup2.removeView(viewGroup);
            }
            commonUtils.setGone(viewGroup);
        }
        this.fakeView = null;
        this.fakeViewScaleContent = null;
    }

    public final void setFakeView(ViewGroup viewGroup) {
        this.fakeView = viewGroup;
    }

    public final void setFakeViewScaleContent(View view) {
        this.fakeViewScaleContent = view;
    }

    public final void updateLayout(DetailFromView detailFromView, DetailPanelAnimator.AnimValue animValue) {
        ViewGroup viewGroup;
        ViewGroup itemFrame;
        ViewGroup itemFrame2;
        ViewGroup itemFrame3;
        ViewGroup itemFrame4;
        ViewGroup itemFrame5;
        ViewGroup itemFrame6;
        if (animValue == null || (viewGroup = this.fakeView) == null) {
            return;
        }
        SecondaryPanelAnimatorBase.ViewLocValue fromIcon = animValue.getFromIcon();
        int screenWidth = ((ControlCenterWindowViewController) this.windowViewController.get()).getScreenWidth();
        if (fromIcon != null) {
            float scaleY = 1.0f;
            float scaleX = (detailFromView == null || (itemFrame6 = detailFromView.getItemFrame()) == null) ? 1.0f : itemFrame6.getScaleX();
            if (detailFromView != null && (itemFrame5 = detailFromView.getItemFrame()) != null) {
                scaleY = itemFrame5.getScaleY();
            }
            float locLeft = fromIcon.getLocLeft() + ((fromIcon.getWidth() - (fromIcon.getWidth() * scaleX)) / 2.0f);
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            CommonUtils.setMarginLeft$default(commonUtils, viewGroup, Y0.b.b(locLeft), false, 2, null);
            CommonUtils.setMarginRight$default(commonUtils, viewGroup, Y0.b.b((screenWidth - locLeft) - fromIcon.getWidth()), false, 2, null);
            CommonUtils.setMarginTop$default(commonUtils, viewGroup, Y0.b.b(fromIcon.getLocTop() + ((fromIcon.getHeight() - (fromIcon.getHeight() * scaleY)) / 2.0f)), false, 2, null);
            commonUtils.setLayoutSize(viewGroup, fromIcon.getWidth(), fromIcon.getHeight(), true);
        } else {
            float pivotY = 0.0f;
            viewGroup.setRotationX((detailFromView == null || (itemFrame4 = detailFromView.getItemFrame()) == null) ? 0.0f : itemFrame4.getRotationX());
            viewGroup.setRotationY((detailFromView == null || (itemFrame3 = detailFromView.getItemFrame()) == null) ? 0.0f : itemFrame3.getRotationY());
            viewGroup.setPivotX((detailFromView == null || (itemFrame2 = detailFromView.getItemFrame()) == null) ? 0.0f : itemFrame2.getPivotX());
            if (detailFromView != null && (itemFrame = detailFromView.getItemFrame()) != null) {
                pivotY = itemFrame.getPivotY();
            }
            viewGroup.setPivotY(pivotY);
            CommonUtils commonUtils2 = CommonUtils.INSTANCE;
            CommonUtils.setMarginLeft$default(commonUtils2, viewGroup, animValue.getFromFrame().getLocLeft(), false, 2, null);
            CommonUtils.setMarginRight$default(commonUtils2, viewGroup, (screenWidth - animValue.getFromFrame().getLocLeft()) - animValue.getFromFrame().getWidth(), false, 2, null);
            CommonUtils.setMarginTop$default(commonUtils2, viewGroup, animValue.getFromFrame().getLocTop(), false, 2, null);
            commonUtils2.setLayoutSize(viewGroup, animValue.getFromFrame().getWidth(), animValue.getFromFrame().getHeight(), true);
        }
        CommonUtils commonUtils3 = CommonUtils.INSTANCE;
        NonTouchableFrameLayout fakeContainer = this.secondaryBinding.fakeContainer;
        n.f(fakeContainer, "fakeContainer");
        commonUtils3.setVisible(fakeContainer);
    }

    public final void updateTile(QSTile qSTile) {
        if (qSTile == null) {
            return;
        }
        if ((!n.c(qSTile.getTileSpec(), "cell") || qSTile.getState().dualTarget) && getTiles().containsKey(qSTile.getTileSpec())) {
            HashMap<String, QSTile> tiles = getTiles();
            String tileSpec = qSTile.getTileSpec();
            n.f(tileSpec, "getTileSpec(...)");
            tiles.put(tileSpec, qSTile);
            QSTile qSTile2 = this.fakeTile;
            if (n.c(qSTile2 != null ? qSTile2.getTileSpec() : null, qSTile.getTileSpec())) {
                this.fakeTile = qSTile;
                updateView(qSTile);
            }
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareShow(DetailPanelParams detailPanelParams) {
        DetailAdapter adapter;
        String strFrom;
        QSTile qSTile;
        QsCardItemViewBinding binding;
        super.prepareShow(detailPanelParams);
        DetailPanelParams panelParams = getPanelParams();
        if (panelParams == null || (adapter = panelParams.getAdapter()) == null || (strFrom = SecondaryParamsKt.from(adapter)) == null || (qSTile = getTiles().get(strFrom)) == null) {
            return;
        }
        this.fakeTile = qSTile;
        ViewGroup viewGroup = getViews().get(strFrom);
        if (viewGroup == null) {
            return;
        }
        this.fakeView = viewGroup;
        LinearLayout linearLayout = null;
        QSCardItemView qSCardItemView = viewGroup instanceof QSCardItemView ? (QSCardItemView) viewGroup : null;
        if (qSCardItemView != null && (binding = qSCardItemView.getBinding()) != null) {
            linearLayout = binding.scaleContent;
        }
        this.fakeViewScaleContent = linearLayout;
        NonTouchableFrameLayout nonTouchableFrameLayout = this.secondaryBinding.fakeContainer;
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        n.d(nonTouchableFrameLayout);
        commonUtils.setInvisible(nonTouchableFrameLayout);
        nonTouchableFrameLayout.removeAllViews();
        nonTouchableFrameLayout.addView(this.fakeView);
        updateView(this.fakeTile);
    }
}
