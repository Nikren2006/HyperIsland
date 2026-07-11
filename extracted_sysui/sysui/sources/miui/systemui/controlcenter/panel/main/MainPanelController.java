package miui.systemui.controlcenter.panel.main;

import I0.m;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SpringRecyclerView;
import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.K;
import j1.u;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.events.ControlCenterScenarioTracker;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.volume.VolumeSliderController;
import miui.systemui.controlcenter.panel.secondary.SecondaryContainerController;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.widget.MainPanelRecyclerView;
import miui.systemui.controlcenter.widget.VerticalSeekBar;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.controlcenter.windowview.GestureDispatcher;
import miui.systemui.util.BlurUtilsExt;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.SystemUIResourcesHelper;
import miuix.spring.view.SpringStateListener;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelController extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "MainPanelController";
    private static boolean lowEndAnim;
    private final BlurUtilsExt blurUtilsExt;
    private final BrightnessSliderController brightnessSliderController;
    private final I clipFooter;
    private final I clipHeader;
    private final MainPanelContentDistributor distributor;
    private final InterfaceC0418f dragging;
    private final ControlCenterExpandController expandController;
    private final GestureDispatcher gestureDispatcher;
    private final MainPanelHeaderController headerController2;
    private final RecyclerView leftMainPanel;
    private final u leftNotOnBottom;
    private final u leftNotOnTop;
    private final u leftSpringBottom;
    private final u leftSpringTop;
    private final Lifecycle lifecycle;
    private final MainPanelAnimController mainPanelAnimController;
    private final LinearLayout mainPanelContainer;
    private final MainPanelModeController modeController;
    private final InterfaceC0418f notOnBottom;
    private final InterfaceC0418f notOnTop;
    private int panelMargin;
    private int panelWidth;
    private final QSController qsController;
    private final RecyclerView rightMainPanel;
    private final u rightNotOnBottom;
    private final u rightNotOnTop;
    private final u rightSpringBottom;
    private final u rightSpringTop;
    private Integer rotation;
    private final E scope;
    private final SecondaryContainerController secondaryContainerController;
    private final E0.a secondaryPanelRouter;
    private final SpreadRowsAnimator spreadRowsAnimator;
    private final InterfaceC0418f springBottom;
    private final InterfaceC0418f springTop;
    private final MainPanelStyleController styleController;
    private final SystemUIResourcesHelper systemUIResourcesHelper;
    private final MainPanelTouchController touchController;
    private Boolean useSeparatedPanels;
    private final VolumeSliderController volumeSliderController;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getLowEndAnim() {
            return MainPanelController.lowEndAnim || DeviceUtils.isLowEndDevice() || DeviceUtils.isLowLevel() || DeviceUtils.isMidLowLevel() || DeviceUtils.isSubMidLevel();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Mode {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ Mode[] $VALUES;
        public static final Mode NORMAL = new Mode("NORMAL", 0);
        public static final Mode EDIT = new Mode("EDIT", 1);

        private static final /* synthetic */ Mode[] $values() {
            return new Mode[]{NORMAL, EDIT};
        }

        static {
            Mode[] modeArr$values = $values();
            $VALUES = modeArr$values;
            $ENTRIES = O0.b.a(modeArr$values);
        }

        private Mode(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static Mode valueOf(String str) {
            return (Mode) Enum.valueOf(Mode.class, str);
        }

        public static Mode[] values() {
            return (Mode[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Style {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ Style[] $VALUES;
        public static final Style VERTICAL = new Style("VERTICAL", 0);
        public static final Style WIDE_VERTICAL = new Style("WIDE_VERTICAL", 1);
        public static final Style HORIZONTAL = new Style("HORIZONTAL", 2);
        public static final Style COMPACT = new Style("COMPACT", 3);

        private static final /* synthetic */ Style[] $values() {
            return new Style[]{VERTICAL, WIDE_VERTICAL, HORIZONTAL, COMPACT};
        }

        static {
            Style[] styleArr$values = $values();
            $VALUES = styleArr$values;
            $ENTRIES = O0.b.a(styleArr$values);
        }

        private Style(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static Style valueOf(String str) {
            return (Style) Enum.valueOf(Style.class, str);
        }

        public static Style[] values() {
            return (Style[]) $VALUES.clone();
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Style.values().length];
            try {
                iArr[Style.COMPACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Style.WIDE_VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Mode.values().length];
            try {
                iArr2[Mode.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[Mode.EDIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainPanelController(@Qualifiers.ControlCenter E scope, @Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Qualifiers.RightMainPanel RecyclerView rightMainPanel, @Qualifiers.LeftMainPanel RecyclerView leftMainPanel, @Qualifiers.MainPanelContainer LinearLayout mainPanelContainer, @Qualifiers.ControlCenter Lifecycle lifecycle, MainPanelStyleController styleController, MainPanelModeController modeController, MainPanelAnimController mainPanelAnimController, MainPanelTouchController touchController, ControlCenterExpandController expandController, VolumeSliderController volumeSliderController, E0.a windowViewController, SpreadRowsAnimator spreadRowsAnimator, GestureDispatcher gestureDispatcher, QSController qsController, MainPanelContentDistributor distributor, E0.a secondaryPanelRouter, MainPanelHeaderController headerController2, SystemUIResourcesHelper systemUIResourcesHelper, BlurUtilsExt blurUtilsExt, SecondaryContainerController secondaryContainerController, BrightnessSliderController brightnessSliderController) {
        super(windowView);
        n.g(scope, "scope");
        n.g(windowView, "windowView");
        n.g(rightMainPanel, "rightMainPanel");
        n.g(leftMainPanel, "leftMainPanel");
        n.g(mainPanelContainer, "mainPanelContainer");
        n.g(lifecycle, "lifecycle");
        n.g(styleController, "styleController");
        n.g(modeController, "modeController");
        n.g(mainPanelAnimController, "mainPanelAnimController");
        n.g(touchController, "touchController");
        n.g(expandController, "expandController");
        n.g(volumeSliderController, "volumeSliderController");
        n.g(windowViewController, "windowViewController");
        n.g(spreadRowsAnimator, "spreadRowsAnimator");
        n.g(gestureDispatcher, "gestureDispatcher");
        n.g(qsController, "qsController");
        n.g(distributor, "distributor");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(headerController2, "headerController2");
        n.g(systemUIResourcesHelper, "systemUIResourcesHelper");
        n.g(blurUtilsExt, "blurUtilsExt");
        n.g(secondaryContainerController, "secondaryContainerController");
        n.g(brightnessSliderController, "brightnessSliderController");
        this.scope = scope;
        this.rightMainPanel = rightMainPanel;
        this.leftMainPanel = leftMainPanel;
        this.mainPanelContainer = mainPanelContainer;
        this.lifecycle = lifecycle;
        this.styleController = styleController;
        this.modeController = modeController;
        this.mainPanelAnimController = mainPanelAnimController;
        this.touchController = touchController;
        this.expandController = expandController;
        this.volumeSliderController = volumeSliderController;
        this.windowViewController = windowViewController;
        this.spreadRowsAnimator = spreadRowsAnimator;
        this.gestureDispatcher = gestureDispatcher;
        this.qsController = qsController;
        this.distributor = distributor;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.headerController2 = headerController2;
        this.systemUIResourcesHelper = systemUIResourcesHelper;
        this.blurUtilsExt = blurUtilsExt;
        this.secondaryContainerController = secondaryContainerController;
        this.brightnessSliderController = brightnessSliderController;
        Display display = getContext().getDisplay();
        this.rotation = display != null ? Integer.valueOf(display.getRotation()) : null;
        Boolean bool = Boolean.FALSE;
        u uVarA = K.a(bool);
        this.leftNotOnTop = uVarA;
        u uVarA2 = K.a(bool);
        this.rightNotOnTop = uVarA2;
        InterfaceC0418f interfaceC0418fL = AbstractC0420h.l(uVarA, uVarA2, new MainPanelController$notOnTop$1(null));
        this.notOnTop = interfaceC0418fL;
        u uVarA3 = K.a(bool);
        this.leftNotOnBottom = uVarA3;
        u uVarA4 = K.a(bool);
        this.rightNotOnBottom = uVarA4;
        InterfaceC0418f interfaceC0418fL2 = AbstractC0420h.l(uVarA3, uVarA4, new MainPanelController$notOnBottom$1(null));
        this.notOnBottom = interfaceC0418fL2;
        InterfaceC0418f interfaceC0418fL3 = AbstractC0420h.l(distributor.getLeftAdapter().getDragging(), distributor.getRightAdapter().getDragging(), new MainPanelController$dragging$1(null));
        this.dragging = interfaceC0418fL3;
        u uVarA5 = K.a(bool);
        this.leftSpringTop = uVarA5;
        u uVarA6 = K.a(bool);
        this.rightSpringTop = uVarA6;
        InterfaceC0418f interfaceC0418fL4 = AbstractC0420h.l(uVarA5, uVarA6, new MainPanelController$springTop$1(null));
        this.springTop = interfaceC0418fL4;
        u uVarA7 = K.a(bool);
        this.leftSpringBottom = uVarA7;
        u uVarA8 = K.a(bool);
        this.rightSpringBottom = uVarA8;
        InterfaceC0418f interfaceC0418fL5 = AbstractC0420h.l(uVarA7, uVarA8, new MainPanelController$springBottom$1(null));
        this.springBottom = interfaceC0418fL5;
        InterfaceC0418f interfaceC0418fI = AbstractC0420h.i(interfaceC0418fL, interfaceC0418fL3, interfaceC0418fL4, new MainPanelController$clipHeader$1(null));
        E.a aVar = j1.E.f4648a;
        this.clipHeader = AbstractC0420h.B(interfaceC0418fI, scope, aVar.c(), bool);
        this.clipFooter = AbstractC0420h.B(AbstractC0420h.i(interfaceC0418fL2, interfaceC0418fL3, interfaceC0418fL5, new MainPanelController$clipFooter$1(null)), scope, aVar.c(), bool);
    }

    public static /* synthetic */ void changeItemVisible$default(MainPanelController mainPanelController, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z4 = false;
        }
        mainPanelController.changeItemVisible(z2, z3, z4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkLeftScroll() {
        checkScroll(this.leftMainPanel, this.leftNotOnTop, this.leftNotOnBottom, this.leftSpringTop, this.leftSpringBottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkRightScroll() {
        checkScroll(this.rightMainPanel, this.rightNotOnTop, this.rightNotOnBottom, this.rightSpringTop, this.rightSpringBottom);
    }

    private final void checkScroll(final RecyclerView recyclerView, final u uVar, final u uVar2, final u uVar3, final u uVar4) {
        if (!recyclerView.isAttachedToWindow() || recyclerView.getItemAnimator() == null) {
            Boolean bool = Boolean.FALSE;
            uVar.setValue(bool);
            uVar2.setValue(bool);
            uVar3.setValue(bool);
            uVar4.setValue(bool);
            return;
        }
        if (((Boolean) uVar.getValue()).booleanValue()) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator != null) {
                itemAnimator.isRunning(new RecyclerView.ItemAnimator.ItemAnimatorFinishedListener() { // from class: miui.systemui.controlcenter.panel.main.g
                    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener
                    public final void onAnimationsFinished() {
                        MainPanelController.checkScroll$lambda$12(uVar, recyclerView, uVar3);
                    }
                });
            }
        } else {
            uVar.setValue(Boolean.valueOf(checkScroll$notOnTop(recyclerView)));
            uVar3.setValue(Boolean.valueOf(checkScroll$onSpring(recyclerView)));
        }
        if (!((Boolean) uVar2.getValue()).booleanValue()) {
            uVar2.setValue(Boolean.valueOf(checkScroll$notOnBottom(recyclerView)));
            uVar4.setValue(Boolean.valueOf(checkScroll$onSpring(recyclerView)));
        } else {
            RecyclerView.ItemAnimator itemAnimator2 = recyclerView.getItemAnimator();
            if (itemAnimator2 != null) {
                itemAnimator2.isRunning(new RecyclerView.ItemAnimator.ItemAnimatorFinishedListener() { // from class: miui.systemui.controlcenter.panel.main.h
                    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener
                    public final void onAnimationsFinished() {
                        MainPanelController.checkScroll$lambda$13(uVar2, recyclerView, uVar4);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkScroll$lambda$12(u top, RecyclerView view, u springTop) {
        n.g(top, "$top");
        n.g(view, "$view");
        n.g(springTop, "$springTop");
        top.setValue(Boolean.valueOf(checkScroll$notOnTop(view)));
        springTop.setValue(Boolean.valueOf(checkScroll$onSpring(view)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkScroll$lambda$13(u bottom, RecyclerView view, u springBottom) {
        n.g(bottom, "$bottom");
        n.g(view, "$view");
        n.g(springBottom, "$springBottom");
        bottom.setValue(Boolean.valueOf(checkScroll$notOnBottom(view)));
        springBottom.setValue(Boolean.valueOf(checkScroll$onSpring(view)));
    }

    private static final boolean checkScroll$notOnBottom(RecyclerView recyclerView) {
        return recyclerView.isAttachedToWindow() && recyclerView.canScrollVertically(1);
    }

    private static final boolean checkScroll$notOnTop(RecyclerView recyclerView) {
        return recyclerView.isAttachedToWindow() && recyclerView.canScrollVertically(-1);
    }

    private static final boolean checkScroll$onSpring(RecyclerView recyclerView) {
        SpringRecyclerView springRecyclerView = recyclerView instanceof SpringRecyclerView ? (SpringRecyclerView) recyclerView : null;
        return recyclerView.isAttachedToWindow() && (springRecyclerView != null ? springRecyclerView.getSpringY() : 0.0f) > 0.0f;
    }

    private final boolean getAnySeekBarTracking() {
        VerticalSeekBar slider = this.brightnessSliderController.getSlider();
        if (slider != null && slider.getTracking()) {
            return true;
        }
        VerticalSeekBar slider2 = this.volumeSliderController.getSlider();
        return slider2 != null && slider2.getTracking();
    }

    public static /* synthetic */ Pair getPanelBorder$default(MainPanelController mainPanelController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        return mainPanelController.getPanelBorder(z2);
    }

    private final void observeScroll() {
        RecyclerView recyclerView = this.leftMainPanel;
        recyclerView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miui.systemui.controlcenter.panel.main.MainPanelController$observeScroll$1$1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v2) {
                n.g(v2, "v");
                this.this$0.checkLeftScroll();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v2) {
                n.g(v2, "v");
                this.this$0.checkLeftScroll();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: miui.systemui.controlcenter.panel.main.MainPanelController$observeScroll$1$2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i2) {
                n.g(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, i2);
                if (i2 == 1) {
                    BoostHelper.getInstance().boostWithCpuFreq(5000L, recyclerView2);
                }
                if (i2 == 0) {
                    this.this$0.distributor.getLeftAdapter().setPendingUpdateHolderLocation(true);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i2, int i3) {
                n.g(recyclerView2, "recyclerView");
                this.this$0.checkLeftScroll();
            }
        });
        RecyclerView recyclerView2 = this.leftMainPanel;
        if (recyclerView2 instanceof SpringRecyclerView) {
            ((SpringRecyclerView) recyclerView2).addSpringStateListener(new SpringStateListener() { // from class: miui.systemui.controlcenter.panel.main.c
                @Override // miuix.spring.view.SpringStateListener
                public final void onSpringDistanceChanged(float f2, float f3) {
                    MainPanelController.observeScroll$lambda$8$lambda$6(this.f5360a, f2, f3);
                }
            });
        }
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miui.systemui.controlcenter.panel.main.d
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                MainPanelController.observeScroll$lambda$8$lambda$7(this.f5361a, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        });
        RecyclerView recyclerView3 = this.rightMainPanel;
        recyclerView3.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miui.systemui.controlcenter.panel.main.MainPanelController$observeScroll$2$1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v2) {
                n.g(v2, "v");
                this.this$0.checkRightScroll();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v2) {
                n.g(v2, "v");
                this.this$0.checkRightScroll();
            }
        });
        recyclerView3.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: miui.systemui.controlcenter.panel.main.MainPanelController$observeScroll$2$2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView4, int i2) {
                n.g(recyclerView4, "recyclerView");
                super.onScrollStateChanged(recyclerView4, i2);
                if (i2 == 1) {
                    BoostHelper.getInstance().boostWithCpuFreq(5000L, recyclerView4);
                }
                if (i2 == 0) {
                    this.this$0.distributor.getRightAdapter().setPendingUpdateHolderLocation(true);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView4, int i2, int i3) {
                n.g(recyclerView4, "recyclerView");
                this.this$0.checkRightScroll();
            }
        });
        RecyclerView recyclerView4 = this.rightMainPanel;
        if (recyclerView4 instanceof SpringRecyclerView) {
            ((SpringRecyclerView) recyclerView4).addSpringStateListener(new SpringStateListener() { // from class: miui.systemui.controlcenter.panel.main.e
                @Override // miuix.spring.view.SpringStateListener
                public final void onSpringDistanceChanged(float f2, float f3) {
                    MainPanelController.observeScroll$lambda$11$lambda$9(this.f5396a, f2, f3);
                }
            });
        }
        recyclerView3.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miui.systemui.controlcenter.panel.main.f
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                MainPanelController.observeScroll$lambda$11$lambda$10(this.f5397a, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observeScroll$lambda$11$lambda$10(MainPanelController this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        this$0.checkRightScroll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observeScroll$lambda$11$lambda$9(MainPanelController this$0, float f2, float f3) {
        n.g(this$0, "this$0");
        this$0.checkRightScroll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observeScroll$lambda$8$lambda$6(MainPanelController this$0, float f2, float f3) {
        n.g(this$0, "this$0");
        this$0.checkLeftScroll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observeScroll$lambda$8$lambda$7(MainPanelController this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        this$0.checkLeftScroll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1$lambda$0(MainPanelController this$0, View view) {
        n.g(this$0, "this$0");
        this$0.exit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$2(MainPanelController this$0, View view) {
        n.g(this$0, "this$0");
        this$0.exit();
    }

    private final void setUseSeparatedPanels(Boolean bool) {
        if (bool == null || n.c(bool, this.useSeparatedPanels)) {
            return;
        }
        this.useSeparatedPanels = bool;
        if (!bool.booleanValue()) {
            this.mainPanelContainer.removeView(this.leftMainPanel);
            return;
        }
        this.mainPanelContainer.addView(this.leftMainPanel, 0);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.control_center_horizontal_margin_center);
        this.panelMargin = dimensionPixelSize;
        CommonUtils.setMarginEnd$default(CommonUtils.INSTANCE, this.leftMainPanel, dimensionPixelSize, false, 2, null);
    }

    private final void updatePanelSize() {
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        CommonUtils.setLayoutSize$default(commonUtils, this.leftMainPanel, this.panelWidth, -1, false, 4, null);
        CommonUtils.setLayoutSize$default(commonUtils, this.rightMainPanel, this.panelWidth, -1, false, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void updatePanelStyle() {
        int i2;
        int dimensionPixelOffset;
        int i3 = WhenMappings.$EnumSwitchMapping$0[getStyle().ordinal()];
        int i4 = GravityCompat.END;
        if (i3 == 1) {
            int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.control_center_compact_margin_end);
            boolean z2 = FlipUtils.isCutoutRight(getContext().getDisplay()) == CommonUtils.isLayoutRtl(getContext());
            i2 = z2 ? 0 : dimensionPixelOffset2;
            int i5 = z2 ? dimensionPixelOffset2 : 0;
            if (!z2) {
                i4 = 8388611;
            }
            dimensionPixelOffset = i5;
        } else if (i3 != 2) {
            i4 = 17;
            i2 = 0;
            dimensionPixelOffset = 0;
        } else {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.control_center_force_vertical_margin_end);
            i2 = 0;
        }
        CommonUtils.setMargins$default(CommonUtils.INSTANCE, this.rightMainPanel, i2, 0, dimensionPixelOffset, 0, true, 10, null);
        ViewGroup.LayoutParams layoutParams = this.mainPanelContainer.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.gravity = i4;
            ((ControlCenterWindowViewImpl) getView()).updateViewLayout(this.mainPanelContainer, layoutParams2);
        }
    }

    private final void updatePanelWidth() {
        this.panelWidth = this.styleController.getStyle() == Style.COMPACT ? getResources().getDimensionPixelSize(R.dimen.control_center_universal_3_rows_with_margin_size) : getResources().getDimensionPixelSize(R.dimen.control_center_universal_4_rows_with_margin_size);
    }

    public final void changeItemVisible(boolean z2, boolean z3, boolean z4) {
        this.distributor.getRightAdapter().changeItemVisible(z2, z3, z4);
        this.distributor.getLeftAdapter().changeItemVisible(z2, z3, z4);
    }

    public final void changePanelExpand(boolean z2, boolean z3, float f2, float f3, float f4) {
        this.distributor.getRightAdapter().changePanelExpand(z2, z3, f2, f3, f4);
        this.distributor.getLeftAdapter().changePanelExpand(z2, z3, f2, f3, f4);
    }

    public final void exit() {
        if (getMode() == Mode.NORMAL) {
            this.expandController.hidePanel(true, true);
            ControlCenterScenarioTracker.INSTANCE.reportCollapseEvents(true);
        }
    }

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
    public final void exitOrHide() {
        int i2 = WhenMappings.$EnumSwitchMapping$1[this.modeController.getMode().ordinal()];
        if (i2 == 1) {
            if (getAnySeekBarTracking()) {
                return;
            }
            this.expandController.hidePanel(true, true);
            ControlCenterScenarioTracker.INSTANCE.reportCollapseEvents(true);
            return;
        }
        if (i2 != 2) {
            return;
        }
        if (((Boolean) this.distributor.getRightAdapter().getDragging().getValue()).booleanValue()) {
            Log.e(TAG, "exitOrHide: Can not exit, still dragging tiles.");
            return;
        }
        if (!CommonUtils.isTinyScreen(getContext())) {
            ControlCenterEventTracker.Companion.trackEditPanelQuitEvent(EventTracker.Companion.getScreenType(getContext()), ((QSListController) this.qsController.getQsListController().get()).getTileCustomized(), 0);
        }
        Log.e(TAG, "exitOrHide: " + ((QSListController) this.qsController.getQsListController().get()).getTileCustomized());
        if (((Boolean) this.distributor.getRightAdapter().getDragging().getValue()).booleanValue()) {
            return;
        }
        this.modeController.changeMode(Mode.NORMAL, ((QSListController) this.qsController.getQsListController().get()).getTileCustomized() ? 1 : 0, true);
    }

    public final BlurUtilsExt getBlurUtilsExt() {
        return this.blurUtilsExt;
    }

    public final I getClipFooter() {
        return this.clipFooter;
    }

    public final I getClipHeader() {
        return this.clipHeader;
    }

    public final ControlCenterExpandController getExpandController() {
        return this.expandController;
    }

    public final MainPanelHeaderController getHeaderController2() {
        return this.headerController2;
    }

    public final boolean getMainPanelScrolling() {
        return (this.rightMainPanel.getScrollState() == 0 && this.leftMainPanel.getScrollState() == 0) ? false : true;
    }

    public final Mode getMode() {
        return this.modeController.getMode();
    }

    public final MainPanelModeController getModeController() {
        return this.modeController;
    }

    public final Pair<Float, Float> getPanelBorder(boolean z2) {
        if (z2) {
            updateResources();
        }
        if (!CommonUtils.INSTANCE.getForceVertical()) {
            return new Pair<>(Float.valueOf(0.0f), Float.valueOf(((ControlCenterWindowViewController) this.windowViewController.get()).getScreenWidth()));
        }
        ViewGroup.LayoutParams layoutParams = this.rightMainPanel.getLayoutParams();
        int marginEnd = layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginEnd() : 0;
        if (CommonUtils.isLayoutRtl(getContext())) {
            return new Pair<>(Float.valueOf(marginEnd), Float.valueOf(this.panelWidth));
        }
        float screenWidth = ((ControlCenterWindowViewController) this.windowViewController.get()).getScreenWidth() - marginEnd;
        return new Pair<>(Float.valueOf(screenWidth - this.panelWidth), Float.valueOf(screenWidth));
    }

    public final int getPanelContainerWidth() {
        return n.c(this.useSeparatedPanels, Boolean.TRUE) ? (this.panelWidth * 2) + this.panelMargin : this.panelWidth;
    }

    public final int getPanelMargin() {
        return this.panelMargin;
    }

    public final int getPanelWidth() {
        return this.panelWidth;
    }

    public final QSController getQsController() {
        return this.qsController;
    }

    public final SecondaryContainerController getSecondaryContainerController() {
        return this.secondaryContainerController;
    }

    public final E0.a getSecondaryPanelRouter() {
        return this.secondaryPanelRouter;
    }

    public final SpreadRowsAnimator getSpreadRowsAnimator() {
        return this.spreadRowsAnimator;
    }

    public final Style getStyle() {
        return this.styleController.getStyle();
    }

    public final MainPanelStyleController getStyleController() {
        return this.styleController;
    }

    public final MainPanelTouchController getTouchController() {
        return this.touchController;
    }

    public final Boolean getUseSeparatedPanels() {
        return this.useSeparatedPanels;
    }

    public final E0.a getWindowViewController() {
        return this.windowViewController;
    }

    public final void handleMainPanelShown() {
        this.volumeSliderController.handleMainPanelShown();
    }

    public final void handlePanelTouchEvent(MotionEvent ev) {
        n.g(ev, "ev");
        this.volumeSliderController.handlePanelTouchEvent(ev);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onApplyWindowInsets(WindowInsets insets) {
        n.g(insets, "insets");
        if (getStyle() == Style.COMPACT) {
            this.mainPanelContainer.setPadding(insets.getSystemWindowInsetLeft(), 0, insets.getSystemWindowInsetRight(), 0);
        } else {
            this.mainPanelContainer.setPadding(0, 0, 0, 0);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onChildrenCreated() {
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        Display display = getContext().getDisplay();
        Integer numValueOf = display != null ? Integer.valueOf(display.getRotation()) : null;
        if (!n.c(this.rotation, numValueOf)) {
            this.rotation = numValueOf;
            updatePanelStyle();
        }
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zOrientationChanged = configUtils.orientationChanged(i2);
        if (zOrientationChanged || configUtils.dimensionsChanged(i2)) {
            updateResources();
            if (!CommonUtils.INSTANCE.getForceVertical()) {
                MainPanelModeController.changeMode$default(this.modeController, Mode.NORMAL, 2, false, 4, null);
            }
            getContext().getResources().getConfiguration();
        }
        if (zOrientationChanged) {
            scrollToTop();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        Boolean bool = this.systemUIResourcesHelper.getBoolean("force_shade_low_end_anim");
        lowEndAnim = bool != null ? bool.booleanValue() : false;
        MainPanelContentDistributor.distributePanels$default(this.distributor, false, 1, null);
        RecyclerView recyclerView = this.rightMainPanel;
        recyclerView.setItemViewCacheSize(8);
        recyclerView.setAdapter(this.distributor.getRightAdapter());
        MainPanelRecyclerView mainPanelRecyclerView = recyclerView instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) recyclerView : null;
        if (mainPanelRecyclerView != null) {
            mainPanelRecyclerView.createGestureHelper(this.gestureDispatcher);
        }
        recyclerView.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainPanelController.onCreate$lambda$1$lambda$0(this.f5352a, view);
            }
        });
        RecyclerView recyclerView2 = this.leftMainPanel;
        recyclerView2.setItemViewCacheSize(8);
        recyclerView2.invalidateOutline();
        recyclerView2.setAdapter(this.distributor.getLeftAdapter());
        MainPanelRecyclerView mainPanelRecyclerView2 = recyclerView2 instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) recyclerView2 : null;
        if (mainPanelRecyclerView2 != null) {
            mainPanelRecyclerView2.createGestureHelper(this.gestureDispatcher);
        }
        recyclerView2.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainPanelController.onCreate$lambda$3$lambda$2(this.f5355a, view);
            }
        });
        observeScroll();
        this.mainPanelContainer.addView(this.rightMainPanel);
        updateResources();
    }

    public final Boolean onKeyEvent(KeyEvent event) {
        n.g(event, "event");
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                int keyCode = event.getKeyCode();
                if (keyCode == 4 || keyCode == 82) {
                    if (event.isCanceled()) {
                        return null;
                    }
                    exitOrHide();
                    return Boolean.TRUE;
                }
                if (keyCode == 24 || keyCode == 25) {
                    this.volumeSliderController.volumeKeyUp();
                    if (getMode() != Mode.NORMAL) {
                        return null;
                    }
                    return Boolean.TRUE;
                }
            }
        } else {
            if (getMode() != Mode.NORMAL) {
                return null;
            }
            int keyCode2 = event.getKeyCode();
            if (keyCode2 == 24) {
                this.volumeSliderController.changeVolume(true);
                return Boolean.TRUE;
            }
            if (keyCode2 == 25) {
                this.volumeSliderController.changeVolume(false);
                return Boolean.TRUE;
            }
            if (keyCode2 == 164) {
                if (event.getRepeatCount() == 0) {
                    this.volumeSliderController.changeMuteMode();
                }
                return Boolean.TRUE;
            }
        }
        return null;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onResume() {
        if (this.rightMainPanel.getVisibility() != 0) {
            Log.w(TAG, "panel is not visible when expanded.");
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        scrollToTop();
        if (this.modeController.getMode() == Mode.EDIT && !CommonUtils.isTinyScreen(getContext())) {
            ControlCenterEventTracker.Companion.trackEditPanelQuitEvent(EventTracker.Companion.getScreenType(getContext()), ((QSListController) this.qsController.getQsListController().get()).getTileCustomized(), 1);
        }
        MainPanelModeController.changeMode$default(this.modeController, Mode.NORMAL, 2, false, 4, null);
    }

    public final void overrideHeaderLayoutParams(FrameLayout.LayoutParams lp) {
        n.g(lp, "lp");
        int i2 = WhenMappings.$EnumSwitchMapping$0[getStyle().ordinal()];
        if (i2 == 1) {
            lp.gravity = FlipUtils.isCutoutRight(getContext().getDisplay()) != CommonUtils.isLayoutRtl(getContext()) ? 8388659 : 8388661;
        } else if (i2 != 2) {
            lp.gravity = 49;
        } else {
            lp.setMarginEnd(getResources().getDimensionPixelOffset(R.dimen.control_center_force_vertical_margin_end));
            lp.gravity = 8388661;
        }
        lp.width = getPanelContainerWidth();
    }

    public final void scrollToTop() {
        RecyclerView recyclerView = this.rightMainPanel;
        n.e(recyclerView, "null cannot be cast to non-null type miui.systemui.controlcenter.widget.MainPanelRecyclerView");
        ((MainPanelRecyclerView) recyclerView).scrollToTop();
        RecyclerView recyclerView2 = this.leftMainPanel;
        n.e(recyclerView2, "null cannot be cast to non-null type miui.systemui.controlcenter.widget.MainPanelRecyclerView");
        ((MainPanelRecyclerView) recyclerView2).scrollToTop();
    }

    public final boolean smoothScrollToTop() {
        RecyclerView recyclerView = this.rightMainPanel;
        n.e(recyclerView, "null cannot be cast to non-null type miui.systemui.controlcenter.widget.MainPanelRecyclerView");
        if (((MainPanelRecyclerView) recyclerView).smoothScrollToTop()) {
            RecyclerView recyclerView2 = this.leftMainPanel;
            n.e(recyclerView2, "null cannot be cast to non-null type miui.systemui.controlcenter.widget.MainPanelRecyclerView");
            if (((MainPanelRecyclerView) recyclerView2).smoothScrollToTop()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateResources() {
        ((ControlCenterWindowViewImpl) getView()).suppressLayout(true);
        updatePanelWidth();
        Log.i(TAG, "update panel width to " + this.panelWidth);
        updateUseSeparatedPanels();
        updatePanelStyle();
        updatePanelSize();
        ((ControlCenterWindowViewImpl) getView()).suppressLayout(false);
    }

    public final void updateUseSeparatedPanels() {
        setUseSeparatedPanels(Boolean.valueOf(!CommonUtils.INSTANCE.getInVerticalMode(getContext())));
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<ControlCenterViewController<? extends ViewGroup>> getChildControllers() {
        return m.f(this.styleController, this.modeController, this.qsController, this.distributor, this.mainPanelAnimController, this.spreadRowsAnimator, this.touchController, this.headerController2, this.secondaryContainerController);
    }
}
