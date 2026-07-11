package miui.systemui.controlcenter.panel.secondary.detail;

import H0.s;
import I0.m;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.MainThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.miui.shade.PanelExpandController;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.DetailHeaderBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;
import miui.systemui.controlcenter.panel.secondary.DetailCallback;
import miui.systemui.controlcenter.panel.secondary.DetailPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.DetailAdapterCompat;
import miui.systemui.controlcenter.widget.DetailPanelMoreButtonView;
import miui.systemui.controlcenter.widget.SlidingButton;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelDelegate extends SecondaryPanelDelegateBase<DetailPanelParams> implements StatusBarStateController.StateListener, PanelExpandController.Callback {
    public static final Companion Companion = new Companion(null);
    private static final String MIPLAY_REF = "controlcenter";
    private static final String TAG = "DetailPanelDelegate";
    private final ActivityStarter activityStarter;
    private final DetailPanelBinding binding;
    private final DetailPanelCellAnimator cellAnimator;
    private final ArrayList<SecondaryPanelDelegateBase<DetailPanelParams>> childControllers;
    private final ArrayList<SecondaryPanelDelegateBase<DetailPanelParams>> childDelegates;
    private DetailAdapter detailAdapter;
    private DetailCallback detailCallback;
    private final SparseArray<View> detailViews;
    private final ControlCenterExpandController expandController;
    private final Handler mainHandler;
    private boolean pendingUpdateItems;
    private boolean scanState;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private final StatusBarStateController statusBarStateController;
    private boolean switchEnabled;
    private boolean switchState;
    private final Context sysUIContext;
    private final DetailPanelTilesDelegate tilesDelegate;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getToggleVisibleCompat(DetailAdapter detailAdapter) {
            if (detailAdapter == null) {
                return true;
            }
            try {
                return detailAdapter.getToggleVisible();
            } catch (Throwable unused) {
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void notifyDataSetChangedCompat(View view) {
            try {
                Method declaredMethod = view.getClass().getDeclaredMethod("notifyDataSetChanged", null);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, null);
            } catch (Throwable th) {
                Log.e(DetailPanelDelegate.TAG, "Invoke notifyDataSetChanged failed.", th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setDetailShowingCompat(View view, boolean z2) {
            try {
                Method declaredMethod = view.getClass().getDeclaredMethod("setDetailShowing", Boolean.TYPE);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(view, Boolean.valueOf(z2));
            } catch (Throwable th) {
                Log.e(DetailPanelDelegate.TAG, "Invoke setDetailShowing failed", th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateItemsCompat(DetailAdapter detailAdapter) {
            try {
                Method declaredMethod = detailAdapter.getClass().getDeclaredMethod("updateItems", null);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(detailAdapter, null);
            } catch (Throwable th) {
                Log.e(DetailPanelDelegate.TAG, "Invoke updateItems failed.", th);
            }
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailPanelDelegate(@SystemUI Context context, @Main Handler mainHandler, ControlCenterSecondaryBinding secondaryBinding, DetailPanelBinding binding, ActivityStarter activityStarter, StatusBarStateController statusBarStateController, ControlCenterExpandController expandController, DetailPanelTilesDelegate tilesDelegate, DetailPanelCellAnimator cellAnimator) {
        super(secondaryBinding);
        n.g(mainHandler, "mainHandler");
        n.g(secondaryBinding, "secondaryBinding");
        n.g(binding, "binding");
        n.g(activityStarter, "activityStarter");
        n.g(statusBarStateController, "statusBarStateController");
        n.g(expandController, "expandController");
        n.g(tilesDelegate, "tilesDelegate");
        n.g(cellAnimator, "cellAnimator");
        this.sysUIContext = context;
        this.mainHandler = mainHandler;
        this.secondaryBinding = secondaryBinding;
        this.binding = binding;
        this.activityStarter = activityStarter;
        this.statusBarStateController = statusBarStateController;
        this.expandController = expandController;
        this.tilesDelegate = tilesDelegate;
        this.cellAnimator = cellAnimator;
        this.childControllers = m.f(tilesDelegate, cellAnimator);
        this.childDelegates = m.f(tilesDelegate, cellAnimator);
        this.detailViews = new SparseArray<>();
    }

    private final DetailHeaderBinding getHeaderBinding() {
        DetailHeaderBinding detailHeader = this.binding.detailHeader;
        n.f(detailHeader, "detailHeader");
        return detailHeader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SlidingButton getToggle() {
        SlidingButton toggle = getHeaderBinding().toggle;
        n.f(toggle, "toggle");
        return toggle;
    }

    private final void handleScanStateChanged(boolean z2) {
        if (this.scanState != z2) {
            this.scanState = z2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void handleShowDetail() {
        DetailAdapter detailAdapter = this.detailAdapter;
        if (detailAdapter != null) {
            setupDetailHeader(detailAdapter);
            setupDetailFooter(detailAdapter);
            int metricsCategory = detailAdapter.getMetricsCategory();
            View viewCreateDetailView = detailAdapter.createDetailView(this.sysUIContext, this.detailViews.get(metricsCategory), this.binding.content);
            if (viewCreateDetailView == null) {
                throw new IllegalStateException("Must return detail view");
            }
            this.binding.content.removeAllViews();
            this.binding.content.addView(viewCreateDetailView);
            viewCreateDetailView.setTag(detailAdapter);
            this.detailViews.put(metricsCategory, viewCreateDetailView);
            ((ConstraintLayout) getView()).announceForAccessibility("");
        }
    }

    private final void handleToggleStateChanged(boolean z2, boolean z3) {
        if (this.detailAdapter != null) {
            if (this.switchState == z2 && this.switchEnabled == z3) {
                return;
            }
            this.switchState = z2;
            this.switchEnabled = z3;
            LinearLayout root = getHeaderBinding().getRoot();
            if (z3) {
                getToggle().setChecked(z2);
                root.setEnabled(true);
            }
        }
    }

    private final void notifyDetailViewShowing(boolean z2) {
        View childAt = this.binding.content.getChildAt(0);
        if (childAt == null) {
            return;
        }
        if (childAt instanceof QSControlMiPlayDetailContent) {
            ((QSControlMiPlayDetailContent) childAt).setDetailShowing(z2, MIPLAY_REF, false);
            return;
        }
        Companion companion = Companion;
        companion.setDetailShowingCompat(childAt, z2);
        if (z2) {
            companion.notifyDataSetChangedCompat(childAt);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(DetailPanelDelegate this$0, View view) {
        n.g(this$0, "this$0");
        if (this$0.getToggle().getVisibility() == 0) {
            this$0.getToggle().toggle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onScanStateChanged$lambda$7(DetailPanelDelegate this$0, boolean z2) {
        n.g(this$0, "this$0");
        this$0.handleScanStateChanged(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onToggleStateChanged$lambda$6(DetailPanelDelegate this$0, boolean z2) {
        n.g(this$0, "this$0");
        DetailAdapter detailAdapter = this$0.detailAdapter;
        this$0.handleToggleStateChanged(z2, detailAdapter != null ? detailAdapter.getToggleEnabled() : false);
    }

    private final void setupDetailFooter(DetailAdapter detailAdapter) {
        final Intent settingsIntent = detailAdapter.getSettingsIntent();
        DetailPanelMoreButtonView detailPanelMoreButtonView = this.binding.moreButton;
        if (settingsIntent == null) {
            detailPanelMoreButtonView.setVisibility(8);
            detailPanelMoreButtonView.setOnClickListener(null);
        } else {
            detailPanelMoreButtonView.setVisibility(0);
            detailPanelMoreButtonView.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.secondary.detail.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DetailPanelDelegate.setupDetailFooter$lambda$15$lambda$14(this.f5470a, settingsIntent, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupDetailFooter$lambda$15$lambda$14(DetailPanelDelegate this$0, Intent intent, View view) {
        n.g(this$0, "this$0");
        DetailCallback detailCallback = this$0.detailCallback;
        if (detailCallback != null) {
            detailCallback.onMoreButtonClicked();
        }
        this$0.activityStarter.postStartActivityDismissingKeyguard(intent, 350);
    }

    private final void setupDetailHeader(final DetailAdapter detailAdapter) {
        if (!detailAdapter.hasHeader()) {
            getToggle().setOnPerformCheckedChangeListener(null);
            getHeaderBinding().getRoot().setVisibility(8);
            return;
        }
        LinearLayout root = getHeaderBinding().getRoot();
        root.setVisibility(0);
        getHeaderBinding().title.setText(detailAdapter.getTitle());
        root.setContentDescription(detailAdapter.getTitle());
        Boolean toggleState = detailAdapter.getToggleState();
        boolean toggleVisibleCompat = Companion.getToggleVisibleCompat(detailAdapter);
        if (toggleState == null) {
            SlidingButton toggle = getToggle();
            toggle.setVisibility(8);
            toggle.setClickable(false);
            toggle.setOnPerformCheckedChangeListener(null);
            return;
        }
        SlidingButton toggle2 = getToggle();
        toggle2.setVisibility(toggleVisibleCompat ? 0 : 8);
        toggle2.setClickable(toggleVisibleCompat);
        handleToggleStateChanged(toggleState.booleanValue(), detailAdapter.getToggleEnabled());
        toggle2.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: miui.systemui.controlcenter.panel.secondary.detail.d
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                DetailPanelDelegate.setupDetailHeader$lambda$13$lambda$12(this.f5463a, detailAdapter, compoundButton, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupDetailHeader$lambda$13$lambda$12(DetailPanelDelegate this$0, DetailAdapter adapter, CompoundButton compoundButton, boolean z2) {
        n.g(this$0, "this$0");
        n.g(adapter, "$adapter");
        DetailCallback detailCallback = this$0.detailCallback;
        if (detailCallback != null) {
            detailCallback.onToggleClicked(z2);
        }
        this$0.switchState = z2;
        adapter.setToggleState(z2);
    }

    private final void updateResources() {
        DetailAdapter detailAdapter = this.detailAdapter;
        if (detailAdapter == null) {
            return;
        }
        getToggle().updateResources();
        TextView textView = getHeaderBinding().title;
        DetailAdapterCompat detailAdapterCompat = DetailAdapterCompat.INSTANCE;
        textView.setTextColor(detailAdapterCompat.getTitleTextColorCompat(detailAdapter, getContext()));
        DetailPanelMoreButtonView detailPanelMoreButtonView = this.binding.moreButton;
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        n.d(detailPanelMoreButtonView);
        CommonUtils.setBackgroundResourceEx$default(commonUtils, detailPanelMoreButtonView, R.drawable.detail_panel_button, false, 2, null);
        Context context = detailPanelMoreButtonView.getContext();
        n.f(context, "getContext(...)");
        detailPanelMoreButtonView.setTextColor(detailAdapterCompat.getButtonTextColorCompat(detailAdapter, context));
        if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
            updateResources$applyMoreButtonColor(this, detailAdapter);
            return;
        }
        DetailPanelMoreButtonView detailPanelMoreButtonView2 = this.binding.moreButton;
        Drawable background = detailPanelMoreButtonView2.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable == null) {
            updateResources$applyMoreButtonColor(this, detailAdapter);
            n.f(detailPanelMoreButtonView2, "apply(...)");
            return;
        }
        gradientDrawable.setColor(detailPanelMoreButtonView2.getContext().getColor(R.color.transparent));
        n.d(detailPanelMoreButtonView2);
        MiBlurCompat.setMiViewBlurModeCompat(detailPanelMoreButtonView2, 1);
        MiBlurCompat.setMiBackgroundBlendColors(detailPanelMoreButtonView2, MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS());
        s sVar = s.f314a;
    }

    private static final void updateResources$applyMoreButtonColor(DetailPanelDelegate detailPanelDelegate, DetailAdapter detailAdapter) {
        Drawable background = detailPanelDelegate.binding.moreButton.getBackground();
        GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
        if (gradientDrawable == null) {
            return;
        }
        gradientDrawable.setColor(DetailAdapterCompat.INSTANCE.getButtonBackgroundColorCompat(detailAdapter, detailPanelDelegate.getContext()));
    }

    private final void updateSize() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.detail_panel_padding_vertical);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        LinearLayout root = getHeaderBinding().getRoot();
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.qs_detail_header_margin_bottom);
        n.d(root);
        CommonUtils.setMargins$default(commonUtils, root, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, false, 16, null);
        DetailPanelMoreButtonView detailPanelMoreButtonView = this.binding.moreButton;
        n.d(detailPanelMoreButtonView);
        CommonUtils.setLayoutHeight$default(commonUtils, detailPanelMoreButtonView, detailPanelMoreButtonView.getResources().getDimensionPixelSize(R.dimen.detail_panel_more_button_height), false, 2, null);
        CommonUtils.setMargins$default(commonUtils, detailPanelMoreButtonView, dimensionPixelSize, 0, dimensionPixelSize, 0, false, 26, null);
    }

    private final void updateTextAppearance() {
        getHeaderBinding().title.setTextAppearance(R.style.TextAppearance_Detail_Header);
        this.binding.moreButton.setTextAppearance(R.style.TextAppearance_Detail_Button);
    }

    private final void updateTexts() {
        this.binding.moreButton.setText(R.string.quick_settings_more_settings);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void cancelPrepare() {
        super.cancelPrepare();
        DetailCallback detailCallback = this.detailCallback;
        if (detailCallback != null) {
            detailCallback.onDetailHidden();
        }
    }

    public final DetailPanelTilesDelegate getTilesDelegate() {
        return this.tilesDelegate;
    }

    public void onAppearanceChanged(boolean z2, boolean z3) {
        if (this.pendingUpdateItems && z2) {
            this.pendingUpdateItems = false;
            View view = this.detailViews.get(117);
            Object tag = view != null ? view.getTag() : null;
            DetailAdapter detailAdapter = tag instanceof DetailAdapter ? (DetailAdapter) tag : null;
            if (detailAdapter != null) {
                Companion.updateItemsCompat(detailAdapter);
            }
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zOrientationChanged = configUtils.orientationChanged(i2);
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        boolean zColorsChanged = configUtils.colorsChanged(i2);
        boolean zTextsChanged = configUtils.textsChanged(i2);
        if (zDimensionsChanged || zColorsChanged || zTextsChanged || configUtils.fontSizeChanged(i2) || zOrientationChanged) {
            updateTextAppearance();
            this.detailViews.clear();
        }
        if (zTextsChanged) {
            updateTexts();
        }
        if (zColorsChanged || zDimensionsChanged) {
            updateResources();
        }
        if (zDimensionsChanged || zOrientationChanged) {
            updateSize();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        getHeaderBinding().getRoot().setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.secondary.detail.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DetailPanelDelegate.onCreate$lambda$0(this.f5469a, view);
            }
        });
        ViewCompat.setAccessibilityDelegate(getHeaderBinding().getRoot(), new AccessibilityDelegateCompat() { // from class: miui.systemui.controlcenter.panel.secondary.detail.DetailPanelDelegate.onCreate.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                n.g(host, "host");
                n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (DetailPanelDelegate.this.getToggle().getVisibility() == 0) {
                    info.setClassName(Switch.class.getName());
                    info.setChecked(DetailPanelDelegate.this.getToggle().isChecked());
                    info.setCheckable(DetailPanelDelegate.this.switchEnabled);
                } else {
                    info.setClassName(LinearLayout.class.getName());
                    info.setCheckable(false);
                    info.setClickable(false);
                    info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                }
            }
        });
        this.statusBarStateController.addCallback(this);
        this.expandController.addCallback(this);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        this.binding.moreButton.setOnClickListener(null);
        this.statusBarStateController.removeCallback(this);
        this.expandController.removeCallback(this);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void onHidden() {
        super.onHidden();
        notifyDetailViewShowing(false);
        DetailCallback detailCallback = this.detailCallback;
        if (detailCallback != null) {
            detailCallback.onDetailHidden();
        }
        this.detailAdapter = null;
    }

    @MainThread
    public final void onScanStateChanged(final boolean z2) {
        this.mainHandler.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.secondary.detail.f
            @Override // java.lang.Runnable
            public final void run() {
                DetailPanelDelegate.onScanStateChanged$lambda$7(this.f5467a, z2);
            }
        });
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void onShown() {
        super.onShown();
        notifyDetailViewShowing(true);
    }

    public void onStateChanged(int i2) {
        this.pendingUpdateItems = true;
    }

    @MainThread
    public final void onToggleStateChanged(final boolean z2) {
        this.mainHandler.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.secondary.detail.e
            @Override // java.lang.Runnable
            public final void run() {
                DetailPanelDelegate.onToggleStateChanged$lambda$6(this.f5465a, z2);
            }
        });
    }

    public final void setDetailCallBack(DetailCallback detailCallback) {
        this.detailCallback = detailCallback;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<SecondaryPanelDelegateBase<DetailPanelParams>> getChildControllers() {
        return this.childControllers;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public ArrayList<SecondaryPanelDelegateBase<DetailPanelParams>> getChildDelegates() {
        return this.childDelegates;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareShow(DetailPanelParams detailPanelParams) {
        if (detailPanelParams == null || detailPanelParams.getAdapter() == null) {
            return;
        }
        super.prepareShow(detailPanelParams);
        this.detailAdapter = detailPanelParams.getAdapter();
        updateResources();
        handleShowDetail();
    }
}
