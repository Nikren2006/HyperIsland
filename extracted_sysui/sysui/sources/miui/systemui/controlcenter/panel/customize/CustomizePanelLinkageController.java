package miui.systemui.controlcenter.panel.customize;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.CustomizerPanelBinding;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.LinearLayout;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.utils.EaseManager;
import miuix.animation.utils.VelocityMonitor;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizePanelLinkageController extends ControlCenterViewController<CustomizePanel> {
    private static final String ANIM_SETUP = "qs_customizer_setup";
    private static final String ANIM_TAG = "qs_customizer_tag";
    private static final String ANIM_TARGET = "qs_customizer_target";
    public static final Companion Companion = new Companion(null);
    private static final int MAX_ROWS = 4;
    private static final int MIN_ROWS = 1;
    private static final String TAG = "CustomizePanelLinkageController";
    private static final int VELOCITY_THRESHOLD = 1000;
    private final View.OnLayoutChangeListener addedContainerLayoutListener;
    private final AnimConfig animConfig;
    private final CustomizePanelLinkageController$animListener$1 animListener;
    private IStateStyle animator;
    private final CustomizerPanelBinding binding;
    private float downY;
    private int itemHeight;
    private float maxWeight;
    private float minWeight;
    private final View.OnLayoutChangeListener notAddedContainerLayoutListener;
    private boolean toTop;
    private final CustomizePanelLinkageController$touchListener$1 touchListener;
    private VelocityMonitor velocityMonitor;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r3v2, types: [miui.systemui.controlcenter.panel.customize.CustomizePanelLinkageController$animListener$1] */
    /* JADX WARN: Type inference failed for: r3v7, types: [miui.systemui.controlcenter.panel.customize.CustomizePanelLinkageController$touchListener$1] */
    public CustomizePanelLinkageController(CustomizerPanelBinding binding) {
        n.g(binding, "binding");
        CustomizePanel root = binding.getRoot();
        n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.animConfig = new AnimConfig();
        this.animListener = new TransitionListener() { // from class: miui.systemui.controlcenter.panel.customize.CustomizePanelLinkageController$animListener$1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "qs_customizer_tag");
                if (updateInfoFindByName == null) {
                    return;
                }
                this.this$0.setWeight((int) updateInfoFindByName.getFloatValue());
            }
        };
        this.itemHeight = getResources().getDimensionPixelSize(R.dimen.qs_cell_height);
        this.notAddedContainerLayoutListener = new View.OnLayoutChangeListener() { // from class: miui.systemui.controlcenter.panel.customize.a
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                CustomizePanelLinkageController.notAddedContainerLayoutListener$lambda$0(this.f5344a, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        };
        this.addedContainerLayoutListener = new View.OnLayoutChangeListener() { // from class: miui.systemui.controlcenter.panel.customize.b
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                CustomizePanelLinkageController.addedContainerLayoutListener$lambda$1(this.f5345a, view, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        };
        this.touchListener = new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.customize.CustomizePanelLinkageController$touchListener$1
            private float topMarginOnStart;

            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                IStateStyle upVar;
                IStateStyle upVar2;
                boolean z2 = false;
                VelocityMonitor velocityMonitor = null;
                Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null;
                if (numValueOf != null && numValueOf.intValue() == 0) {
                    VelocityMonitor velocityMonitor2 = this.this$0.velocityMonitor;
                    if (velocityMonitor2 == null) {
                        n.w("velocityMonitor");
                    } else {
                        velocityMonitor = velocityMonitor2;
                    }
                    velocityMonitor.clear();
                    this.this$0.downY = motionEvent.getRawY();
                    this.topMarginOnStart = this.this$0.getWeight();
                } else if (numValueOf != null && numValueOf.intValue() == 2) {
                    float rawY = motionEvent.getRawY() - this.this$0.downY;
                    float fAfterFriction = this.topMarginOnStart + rawY;
                    if (fAfterFriction > this.this$0.maxWeight) {
                        fAfterFriction = ControlCenterUtils.INSTANCE.afterFriction(fAfterFriction - this.this$0.maxWeight, this.this$0.minWeight) + this.this$0.maxWeight;
                    } else if (fAfterFriction < this.this$0.minWeight) {
                        fAfterFriction = this.this$0.minWeight - ControlCenterUtils.INSTANCE.afterFriction(this.this$0.minWeight - fAfterFriction, this.this$0.minWeight);
                    }
                    IStateStyle iStateStyle = this.this$0.animator;
                    if (iStateStyle != null && (upVar2 = iStateStyle.setup("qs_customizer_setup")) != null) {
                        upVar2.setTo("qs_customizer_tag", Float.valueOf(fAfterFriction));
                    }
                    this.this$0.setWeight((int) fAfterFriction);
                    VelocityMonitor velocityMonitor3 = this.this$0.velocityMonitor;
                    if (velocityMonitor3 == null) {
                        n.w("velocityMonitor");
                    } else {
                        velocityMonitor = velocityMonitor3;
                    }
                    velocityMonitor.update(0.0f, this.topMarginOnStart + rawY);
                } else if ((numValueOf != null && numValueOf.intValue() == 1) || (numValueOf != null && numValueOf.intValue() == 3)) {
                    VelocityMonitor velocityMonitor4 = this.this$0.velocityMonitor;
                    if (velocityMonitor4 == null) {
                        n.w("velocityMonitor");
                    } else {
                        velocityMonitor = velocityMonitor4;
                    }
                    float velocity = velocityMonitor.getVelocity(1);
                    CustomizePanelLinkageController customizePanelLinkageController = this.this$0;
                    if (velocity <= 1000.0f && (velocity < -1000.0f || customizePanelLinkageController.getWeight() <= (this.this$0.minWeight + this.this$0.maxWeight) / 2)) {
                        z2 = true;
                    }
                    customizePanelLinkageController.toTop = z2;
                    IStateStyle iStateStyle2 = this.this$0.animator;
                    if (iStateStyle2 != null && (upVar = iStateStyle2.setup("qs_customizer_setup")) != null) {
                        upVar.to("qs_customizer_tag", Float.valueOf(this.this$0.toTop ? this.this$0.minWeight : this.this$0.maxWeight), this.this$0.animConfig);
                    }
                }
                return true;
            }
        };
        this.downY = -1.0f;
        this.maxWeight = 2000.0f;
        this.minWeight = 500.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addedContainerLayoutListener$lambda$1(CustomizePanelLinkageController this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        this$0.calculateWeightBorders();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void calculateWeightBorders() {
        int[] iArr = new int[2];
        this.binding.addedList.getLocationOnScreen(iArr);
        int i2 = iArr[1];
        ((CustomizePanel) getView()).getLocationOnScreen(iArr);
        int i3 = i2 - iArr[1];
        int i4 = this.itemHeight;
        float f2 = i3 + i4;
        float f3 = i3 + (i4 * 4);
        if (f2 == this.minWeight && f3 == this.maxWeight) {
            return;
        }
        this.minWeight = f2;
        this.maxWeight = f3;
        if (this.toTop) {
            return;
        }
        setWeight((int) f3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notAddedContainerLayoutListener$lambda$0(CustomizePanelLinkageController this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        if (this$0.binding.addedContainer.getPaddingBottom() != this$0.binding.notAddedContainer.getHeight()) {
            this$0.updateHeaderLayoutMargin();
        }
    }

    private final void updateHeaderLayoutMargin() {
        ConstraintLayout constraintLayout = this.binding.addedContainer;
        constraintLayout.setPadding(constraintLayout.getPaddingLeft(), constraintLayout.getPaddingTop(), constraintLayout.getPaddingRight(), this.binding.notAddedContainer.getHeight());
        constraintLayout.requestLayout();
    }

    public final int getItemHeight() {
        return this.itemHeight;
    }

    public final int getWeight() {
        ViewGroup.LayoutParams layoutParams = this.binding.notAddedContainer.getLayoutParams();
        n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        return ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.dimensionsChanged(i2)) {
            setItemHeight(getResources().getDimensionPixelSize(R.dimen.qs_cell_height));
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        this.velocityMonitor = new VelocityMonitor();
        this.animator = Folme.useValue(ANIM_TARGET).setup(ANIM_SETUP);
        this.animConfig.setEase(EaseManager.getStyle(-2, 0.8f, 0.4f));
        this.animConfig.addListeners(this.animListener);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        this.animConfig.clear();
        this.animator = null;
        Folme.clean(ANIM_TARGET);
    }

    @Override // miui.systemui.util.ViewController
    public void onViewAttached() {
        LinearLayout linearLayout = this.binding.notAddedContainer;
        linearLayout.addOnLayoutChangeListener(this.notAddedContainerLayoutListener);
        linearLayout.setOnTouchListener(this.touchListener);
        this.binding.addedContainer.addOnLayoutChangeListener(this.addedContainerLayoutListener);
    }

    @Override // miui.systemui.util.ViewController
    public void onViewDetached() {
        LinearLayout linearLayout = this.binding.notAddedContainer;
        linearLayout.removeOnLayoutChangeListener(this.notAddedContainerLayoutListener);
        linearLayout.setOnTouchListener(null);
        this.binding.addedContainer.removeOnLayoutChangeListener(this.addedContainerLayoutListener);
    }

    public final void reset() {
        IStateStyle upVar;
        IStateStyle iStateStyle = this.animator;
        if (iStateStyle != null && (upVar = iStateStyle.setup(ANIM_SETUP)) != null) {
            upVar.setTo(ANIM_TAG, Float.valueOf(this.maxWeight), this.animConfig);
        }
        setWeight((int) this.maxWeight);
    }

    public final void setItemHeight(int i2) {
        this.itemHeight = i2;
        calculateWeightBorders();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setWeight(int i2) {
        ViewGroup.LayoutParams layoutParams = this.binding.notAddedContainer.getLayoutParams();
        n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (marginLayoutParams.topMargin == i2) {
            return;
        }
        marginLayoutParams.topMargin = i2;
        this.binding.notAddedContainer.setLayoutParams(marginLayoutParams);
        CustomizerPanelBinding customizerPanelBinding = this.binding;
        customizerPanelBinding.addedContainer.setBottom(customizerPanelBinding.notAddedContainer.getTop());
        ((CustomizePanel) getView()).requestLayout();
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void updateResources() {
        this.binding.indicator.setImageDrawable(getContext().getDrawable(R.drawable.customize_panel_not_added_indicator));
    }
}
