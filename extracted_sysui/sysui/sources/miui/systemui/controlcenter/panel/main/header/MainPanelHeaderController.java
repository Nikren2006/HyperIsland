package miui.systemui.controlcenter.panel.main.header;

import I0.m;
import android.util.Log;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterHeader;
import com.android.systemui.plugins.miui.shade.PanelExpandController;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import java.util.Collection;
import java.util.function.Consumer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelHeaderController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements MainPanelModeController.OnModeChangedListener {
    public static final Companion Companion = new Companion(null);
    public static final int HEADER_TYPE_EDIT = 23333;
    public static final int HEADER_TYPE_EMPTY = 233;
    public static final int HEADER_TYPE_EMPTY_MIRROR = 23;
    public static final int HEADER_TYPE_MSG = 2333;
    private static final String TAG = "MainPanelHeaderController";
    private final ControlCenterHeader controlCenterHeader;
    private final CustomizeHeaderController customizeHeader;
    private final EmptyHeaderController detailHeaderController;
    private final EmptyHeaderMirrorController emptyHeaderController;
    private final MainPanelHeaderController$expandCallback$1 expandCallback;
    private final E0.a expandController;
    private FrameLayout.LayoutParams layoutParams;
    private final Consumer<FrameLayout.LayoutParams> layoutParamsCallback;
    private final E0.a mainPanelController;
    private final E0.a mainPanelModeController;
    private boolean mainPanelVisible;
    private final MessageHeaderController msgHeader;
    private final E0.a panelContentDistributor;
    private final SecondaryPanelRouter secondaryPanelRouter;
    private final ShadeHeaderController shadeHeaderController;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v2, types: [miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController$expandCallback$1] */
    public MainPanelHeaderController(ControlCenterWindowViewImpl windowView, ShadeHeaderController shadeHeaderController, MessageHeaderController msgHeader, CustomizeHeaderController customizeHeader, E0.a mainPanelController, E0.a expandController, E0.a panelContentDistributor, ControlCenterHeader controlCenterHeader, EmptyHeaderController detailHeaderController, EmptyHeaderMirrorController emptyHeaderController, SecondaryPanelRouter secondaryPanelRouter, E0.a mainPanelModeController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(shadeHeaderController, "shadeHeaderController");
        n.g(msgHeader, "msgHeader");
        n.g(customizeHeader, "customizeHeader");
        n.g(mainPanelController, "mainPanelController");
        n.g(expandController, "expandController");
        n.g(panelContentDistributor, "panelContentDistributor");
        n.g(controlCenterHeader, "controlCenterHeader");
        n.g(detailHeaderController, "detailHeaderController");
        n.g(emptyHeaderController, "emptyHeaderController");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(mainPanelModeController, "mainPanelModeController");
        this.shadeHeaderController = shadeHeaderController;
        this.msgHeader = msgHeader;
        this.customizeHeader = customizeHeader;
        this.mainPanelController = mainPanelController;
        this.expandController = expandController;
        this.panelContentDistributor = panelContentDistributor;
        this.controlCenterHeader = controlCenterHeader;
        this.detailHeaderController = detailHeaderController;
        this.emptyHeaderController = emptyHeaderController;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.mainPanelModeController = mainPanelModeController;
        this.mainPanelVisible = true;
        this.expandCallback = new PanelExpandController.Callback() { // from class: miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController$expandCallback$1
            public void onAppearanceChanged(boolean z2, boolean z3) {
                super.onAppearanceChanged(z2, z3);
                if (!z2) {
                    this.this$0.shadeHeaderController.transitionTo(1, z3);
                } else if (((MainPanelController) this.this$0.mainPanelController.get()).getModeController().getMode() == MainPanelController.Mode.EDIT) {
                    ShadeHeaderController.transitionTo$default(this.this$0.shadeHeaderController, MainPanelHeaderController.HEADER_TYPE_EDIT, false, 2, (Object) null);
                } else {
                    ShadeHeaderController.transitionTo$default(this.this$0.shadeHeaderController, 1, false, 2, (Object) null);
                }
            }
        };
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.layoutParamsCallback = new Consumer() { // from class: miui.systemui.controlcenter.panel.main.header.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MainPanelHeaderController.layoutParamsCallback$lambda$0(this.f5405a, (FrameLayout.LayoutParams) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void layoutParamsCallback$lambda$0(MainPanelHeaderController this$0, FrameLayout.LayoutParams layoutParams) {
        n.g(this$0, "this$0");
        MainPanelController mainPanelController = (MainPanelController) this$0.mainPanelController.get();
        n.d(layoutParams);
        mainPanelController.overrideHeaderLayoutParams(layoutParams);
        this$0.layoutParams = new FrameLayout.LayoutParams(layoutParams);
        ((MainPanelContentDistributor) this$0.panelContentDistributor.get()).updateHeaderSpaceHeight();
    }

    private final void updateResources() {
        this.controlCenterHeader.setLayoutParams(this.layoutParamsCallback);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public Collection<ControlCenterViewController<?>> getChildControllers() {
        return m.j(this.msgHeader, this.customizeHeader, this.detailHeaderController, this.emptyHeaderController);
    }

    public final FrameLayout.LayoutParams getLayoutParams() {
        return this.layoutParams;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onApplyWindowInsets(WindowInsets insets) {
        n.g(insets, "insets");
        if (((MainPanelController) this.mainPanelController.get()).getStyle() == MainPanelController.Style.COMPACT) {
            FrameLayout.LayoutParams layoutParams = this.layoutParams;
            layoutParams.leftMargin = insets.getSystemWindowInsetLeft();
            layoutParams.rightMargin = insets.getSystemWindowInsetRight();
        } else {
            FrameLayout.LayoutParams layoutParams2 = this.layoutParams;
            layoutParams2.leftMargin = 0;
            layoutParams2.rightMargin = 0;
        }
        updateResources();
    }

    public final void onBrightnessMirrorChanged(boolean z2, boolean z3) {
        Log.i(TAG, "onBrightnessMirrorChanged " + z2 + " " + z3);
        if (z2) {
            this.shadeHeaderController.transitionTo(23, z3);
        } else if (((MainPanelController) this.mainPanelController.get()).getModeController().getMode() == MainPanelController.Mode.NORMAL && this.secondaryPanelRouter.getInIdleState() && this.secondaryPanelRouter.getInMainPanel()) {
            this.shadeHeaderController.transitionTo(1, z3);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        if (configUtils.dimensionsChanged(i2) || configUtils.layoutDirectionChanged(i2) || configUtils.orientationChanged(i2)) {
            updateResources();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        updateResources();
        ((MainPanelController) this.mainPanelController.get()).getModeController().addOnModeChangedListener(this);
        ((ControlCenterExpandController) this.expandController.get()).addCallback(this.expandCallback);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        if (this.controlCenterHeader.getLayoutParams() == this.layoutParamsCallback) {
            this.controlCenterHeader.setLayoutParams((Consumer) null);
        }
    }

    public final void onMainPanelVisibleChanged(boolean z2, boolean z3) {
        Log.i(TAG, "onMainPanelVisibleChanged " + z2 + " " + z3);
        this.mainPanelVisible = z2;
        MainPanelController.Mode mode = ((MainPanelController) this.mainPanelController.get()).getModeController().getMode();
        if (!z2) {
            this.shadeHeaderController.transitionTo(HEADER_TYPE_EMPTY, z3);
        } else if (mode == MainPanelController.Mode.NORMAL) {
            this.shadeHeaderController.transitionTo(1, z3);
        } else if (mode == MainPanelController.Mode.EDIT) {
            ShadeHeaderController.transitionTo$default(this.shadeHeaderController, HEADER_TYPE_EDIT, false, 2, (Object) null);
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelModeController.OnModeChangedListener
    public void onModeChanged() {
        if (!this.mainPanelVisible) {
            ShadeHeaderController.transitionTo$default(this.shadeHeaderController, HEADER_TYPE_EMPTY, false, 2, (Object) null);
            return;
        }
        if (WhenMappings.$EnumSwitchMapping$0[((MainPanelController) this.mainPanelController.get()).getModeController().getMode().ordinal()] == 1) {
            this.shadeHeaderController.transitionTo(1, ((MainPanelModeController) this.mainPanelModeController.get()).getChangeModeLevel() != 2);
        } else {
            ShadeHeaderController.transitionTo$default(this.shadeHeaderController, HEADER_TYPE_EDIT, false, 2, (Object) null);
        }
    }
}
