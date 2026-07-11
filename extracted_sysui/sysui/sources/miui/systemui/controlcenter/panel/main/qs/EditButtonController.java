package miui.systemui.controlcenter.panel.main.qs;

import I0.m;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.QsCustomizeButtonBinding;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.anim.PressEffectAnimator;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.OnClickListenerEx;
import miui.systemui.util.concurrency.DelayableExecutor;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class EditButtonController extends MainPanelListItem.SimpleController<ControlCenterWindowViewImpl> implements MainPanelContent, MainPanelItemViewHolder.TouchAnimator {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "EditButtonController";
    public static final int TYPE_EDIT = 3348;
    private final /* synthetic */ PressEffectAnimator $$delegate_0;
    private final Handler bgHandler;
    private final E0.a distributor;
    private final ArrayList<EditButtonController> listItems;
    private final E0.a mainPanelController;
    private final int priority;
    private final E0.a qsListController;
    private final boolean rightOrLeft;
    private final E0.a secondaryPanelRouter;
    private final E0.a shadeSwitchController;
    private final int spanSize;
    private final SuperSaveModeController superSaveModeController;
    private final int type;
    private final DelayableExecutor uiExecutor;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class EditButtonViewHolder extends MainPanelItemViewHolder {
        private final QsCustomizeButtonBinding binding;

        /* JADX WARN: Illegal instructions before constructor call */
        public EditButtonViewHolder(QsCustomizeButtonBinding binding) {
            n.g(binding, "binding");
            LinearLayout root = binding.getRoot();
            n.f(root, "getRoot(...)");
            super(root);
            this.binding = binding;
        }

        public final QsCustomizeButtonBinding getBinding() {
            return this.binding;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            ViewCompat.setAccessibilityDelegate(this.binding.touchContainer, new AccessibilityDelegateCompat() { // from class: miui.systemui.controlcenter.panel.main.qs.EditButtonController$EditButtonViewHolder$onConfigurationChanged$1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setContentDescription(this.this$0.getBinding().text.getText());
                    info.setClassName(Button.class.getName());
                }
            });
            ConfigUtils configUtils = ConfigUtils.INSTANCE;
            if (configUtils.dimensionsChanged(i2)) {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                View itemView = this.itemView;
                n.f(itemView, "itemView");
                CommonUtils.setMarginTop$default(commonUtils, itemView, this.itemView.getResources().getDimensionPixelSize(R.dimen.qs_customize_button_margin_top), false, 2, null);
                int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.qs_customize_button_touch_padding);
                miui.systemui.widget.LinearLayout linearLayout = this.binding.touchContainer;
                linearLayout.setPadding(dimensionPixelSize, linearLayout.getPaddingTop(), dimensionPixelSize, linearLayout.getPaddingBottom());
                int dimensionPixelSize2 = this.itemView.getResources().getDimensionPixelSize(R.dimen.qs_customize_button_padding_horizontal);
                int dimensionPixelSize3 = this.itemView.getResources().getDimensionPixelSize(R.dimen.qs_customize_button_padding_vertical);
                this.binding.text.setPadding(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize2, dimensionPixelSize3);
            }
            if (configUtils.textAppearanceChanged(i2)) {
                this.binding.text.setTextAppearance(R.style.TextAppearance_QS_CustomizeButton);
            }
            if (configUtils.textsChanged(i2)) {
                this.binding.text.setText(R.string.qs_customize_entry_button_text);
            }
            if (configUtils.colorsChanged(i2) || configUtils.blurChanged(i2)) {
                CommonUtils commonUtils2 = CommonUtils.INSTANCE;
                TextView text = this.binding.text;
                n.f(text, "text");
                CommonUtils.setBackgroundResourceEx$default(commonUtils2, text, R.drawable.qs_customize_button_background, false, 2, null);
            }
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
            return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void updateBlendBlur() {
            if (!ControlCenterUtils.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                TextView text = this.binding.text;
                n.f(text, "text");
                commonUtils.clearMiBlurBlendEffect(text);
                return;
            }
            TextView text2 = this.binding.text;
            n.f(text2, "text");
            MiBlurCompat.setMiViewBlurModeCompat(text2, 1);
            TextView text3 = this.binding.text;
            n.f(text3, "text");
            MiBlurCompat.setMiBackgroundBlendColors(text3, MiuiColorBlendToken.INSTANCE.getCC_EDIT_BUTTON_BLEND_COLORS());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EditButtonController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, E0.a distributor, @Background Handler bgHandler, @Main DelayableExecutor uiExecutor, E0.a qsListController, E0.a mainPanelController, SuperSaveModeController superSaveModeController, E0.a secondaryPanelRouter, E0.a shadeSwitchController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(distributor, "distributor");
        n.g(bgHandler, "bgHandler");
        n.g(uiExecutor, "uiExecutor");
        n.g(qsListController, "qsListController");
        n.g(mainPanelController, "mainPanelController");
        n.g(superSaveModeController, "superSaveModeController");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(shadeSwitchController, "shadeSwitchController");
        this.distributor = distributor;
        this.bgHandler = bgHandler;
        this.uiExecutor = uiExecutor;
        this.qsListController = qsListController;
        this.mainPanelController = mainPanelController;
        this.superSaveModeController = superSaveModeController;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.shadeSwitchController = shadeSwitchController;
        this.$$delegate_0 = new PressEffectAnimator(null, 1, 0 == true ? 1 : 0);
        this.priority = 200;
        this.rightOrLeft = true;
        this.listItems = m.f(this);
        this.type = TYPE_EDIT;
        this.spanSize = 4;
    }

    private final miui.systemui.widget.LinearLayout getEditButton() {
        QsCustomizeButtonBinding binding;
        MainPanelItemViewHolder holder = getHolder();
        EditButtonViewHolder editButtonViewHolder = holder instanceof EditButtonViewHolder ? (EditButtonViewHolder) holder : null;
        if (editButtonViewHolder == null || (binding = editButtonViewHolder.getBinding()) == null) {
            return null;
        }
        return binding.touchContainer;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void attachTouchTarget(View view) {
        n.g(view, "view");
        this.$$delegate_0.attachTouchTarget(view);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return (((MainPanelController) this.mainPanelController.get()).getStyle() == MainPanelController.Style.COMPACT || ((MainPanelController) this.mainPanelController.get()).getStyle() == MainPanelController.Style.HORIZONTAL || ((MainPanelController) this.mainPanelController.get()).getMode() != MainPanelController.Mode.NORMAL || this.superSaveModeController.isActive()) ? false : true;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 3348) {
            return null;
        }
        QsCustomizeButtonBinding qsCustomizeButtonBindingInflate = QsCustomizeButtonBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        n.f(qsCustomizeButtonBindingInflate, "inflate(...)");
        return new EditButtonViewHolder(qsCustomizeButtonBindingInflate);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void detachTouchTarget() {
        this.$$delegate_0.detachTouchTarget();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getSpanSize() {
        return this.spanSize;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onBindViewHolder() {
        miui.systemui.widget.LinearLayout editButton = getEditButton();
        if (editButton != null) {
            OnClickListenerEx.INSTANCE.setOnClickListenerEx(editButton, new EditButtonController$onBindViewHolder$1$1(this, editButton));
            editButton.setOnLongClickListener(null);
            attachTouchTarget(editButton);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        super.onStop();
        MainPanelItemViewHolder.Companion.releaseTouchNow();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.$$delegate_0.onTouch(view, motionEvent);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        miui.systemui.widget.LinearLayout editButton = getEditButton();
        if (editButton != null) {
            editButton.setOnClickListener(null);
            editButton.setOnLongClickListener(null);
        }
        detachTouchTarget();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchCancel() {
        this.$$delegate_0.touchCancel();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchDown(MotionEvent ev) {
        n.g(ev, "ev");
        this.$$delegate_0.touchDown(ev);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchRelease() {
        this.$$delegate_0.touchRelease();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchReleaseNow() {
        this.$$delegate_0.touchReleaseNow();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder.TouchAnimator
    public void touchTrigger() {
        this.$$delegate_0.touchTrigger();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public ArrayList<EditButtonController> getListItems() {
        return this.listItems;
    }
}
