package miui.systemui.controlcenter.panel.main.header;

import H0.s;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.shade.PanelExpandController;
import com.android.systemui.plugins.miui.shade.ShadeHeader;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.MainPanelMsgHeaderBinding;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.header.MessageHeaderController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.devicecontrols.ui.TouchBehavior;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ThreadUtilsExt;
import miui.systemui.widget.FrameLayout;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
@SuppressLint({"InflateParams"})
public final class MessageHeaderController extends ControlCenterViewController<View> implements ShadeHeader {
    public static final Companion Companion = new Companion(null);
    private static final long HIDE_DELAY = 3000;
    private static final EaseManager.EaseStyle HIDE_EASE;
    private static final EaseManager.EaseStyle SHOW_EASE;
    private static final String TAG = "MessageHeaderController";
    private final MainPanelMsgHeaderBinding binding;
    private final E0.a expandController;
    private final E0.a headerController;
    private final View headerView;
    private final ArrayList<Msg> hiddenMsg;
    private final Lifecycle lifecycle;
    private final E0.a mainPanelController;
    private final Msg msgA;
    private final Msg msgB;
    private CharSequence pendingMsg;
    private final E0.a secondaryPanelRouter;
    private final ShadeHeaderController shadeHeaderController;
    private boolean showing;
    private final ArrayList<Msg> showingOrAnimating;
    private final int type;
    private final Handler uiHandler;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final class Msg {
        private final IStateStyle animator;
        private final AnimConfig hideConfig;
        private Runnable hideRunnable;
        private final AnimConfig showConfig;
        final /* synthetic */ MessageHeaderController this$0;
        private final TextView view;

        public Msg(final MessageHeaderController messageHeaderController, TextView view) {
            n.g(view, "view");
            this.this$0 = messageHeaderController;
            this.view = view;
            this.hideRunnable = new Runnable() { // from class: miui.systemui.controlcenter.panel.main.header.c
                @Override // java.lang.Runnable
                public final void run() {
                    MessageHeaderController.Msg.hideRunnable$lambda$0(this.f5406a);
                }
            };
            this.animator = Folme.useAt(view).state().setTo(ViewProperty.ALPHA, Float.valueOf(0.0f));
            this.showConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.controlcenter.panel.main.header.MessageHeaderController$Msg$showConfig$1
                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    messageHeaderController.uiHandler.postDelayed(this.hideRunnable, TouchBehavior.STATELESS_ENABLE_TIMEOUT_IN_MILLIS);
                }
            }).setEase(MessageHeaderController.SHOW_EASE);
            this.hideConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.controlcenter.panel.main.header.MessageHeaderController$Msg$hideConfig$1
                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    this.this$0.getView().setVisibility(4);
                    messageHeaderController.showingOrAnimating.remove(this.this$0);
                    messageHeaderController.hiddenMsg.add(this.this$0);
                    if (messageHeaderController.showing) {
                        if (messageHeaderController.pendingMsg != null) {
                            messageHeaderController.findAvailableAndShow();
                        } else if (messageHeaderController.showingOrAnimating.size() == 0 && ((SecondaryPanelRouter) messageHeaderController.secondaryPanelRouter.get()).getInMainPanel()) {
                            ShadeHeaderController.transitionTo$default(messageHeaderController.shadeHeaderController, 1, false, 2, (Object) null);
                        }
                    }
                }
            }).setEase(MessageHeaderController.HIDE_EASE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void hideRunnable$lambda$0(Msg this$0) {
            n.g(this$0, "this$0");
            this$0.hide();
        }

        public final TextView getView() {
            return this.view;
        }

        public final void hide() {
            Log.i(MessageHeaderController.TAG, "hid msg");
            this.this$0.uiHandler.removeCallbacks(this.hideRunnable);
            if (this.this$0.showing && this.this$0.pendingMsg == null && this.this$0.showingOrAnimating.size() == 1 && this.this$0.showingOrAnimating.get(0) == this && ((SecondaryPanelRouter) this.this$0.secondaryPanelRouter.get()).getInMainPanel()) {
                ShadeHeaderController.transitionTo$default(this.this$0.shadeHeaderController, 1, false, 2, (Object) null);
            }
            this.animator.cancel();
            this.animator.to(ViewProperty.ALPHA, Float.valueOf(0.0f), this.hideConfig);
        }

        public final void show() {
            if (this.this$0.pendingMsg == null || !((ControlCenterExpandController) this.this$0.expandController.get()).getAppearance()) {
                return;
            }
            Log.i(MessageHeaderController.TAG, "show msg");
            this.view.setText(this.this$0.pendingMsg);
            this.this$0.pendingMsg = null;
            this.this$0.hiddenMsg.remove(this);
            this.this$0.showingOrAnimating.add(this);
            this.this$0.uiHandler.removeCallbacks(this.hideRunnable);
            this.view.setVisibility(0);
            this.animator.cancel();
            this.animator.to(ViewProperty.ALPHA, Float.valueOf(1.0f), this.showConfig);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.header.MessageHeaderController$showMsg$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ CharSequence $msg;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CharSequence charSequence) {
            super(0);
            this.$msg = charSequence;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m89invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m89invoke() {
            if (MessageHeaderController.this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED) && ((MainPanelController) MessageHeaderController.this.mainPanelController.get()).getModeController().getMode() == MainPanelController.Mode.NORMAL && ((SecondaryPanelRouter) MessageHeaderController.this.secondaryPanelRouter.get()).getInMainPanel() && ((SecondaryPanelRouter) MessageHeaderController.this.secondaryPanelRouter.get()).getRoutingState() != 0 && ((SecondaryPanelRouter) MessageHeaderController.this.secondaryPanelRouter.get()).getRoutingState() != 1) {
                MessageHeaderController.this.pendingMsg = this.$msg;
                if (!MessageHeaderController.this.showing) {
                    ShadeHeaderController.transitionTo$default(MessageHeaderController.this.shadeHeaderController, MessageHeaderController.this.getType(), false, 2, (Object) null);
                    return;
                }
                Iterator it = MessageHeaderController.this.showingOrAnimating.iterator();
                while (it.hasNext()) {
                    ((Msg) it.next()).hide();
                }
                MessageHeaderController.this.findAvailableAndShow();
            }
        }
    }

    static {
        EaseManager.EaseStyle style = EaseManager.getStyle(-2, 0.95f, 0.35f);
        n.f(style, "getStyle(...)");
        SHOW_EASE = style;
        EaseManager.EaseStyle style2 = EaseManager.getStyle(-2, 0.95f, 0.18f);
        n.f(style2, "getStyle(...)");
        HIDE_EASE = style2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.View] */
    public MessageHeaderController(MainPanelMsgHeaderBinding binding, ShadeHeaderController shadeHeaderController, @Main Handler uiHandler, E0.a headerController, E0.a expandController, E0.a mainPanelController, @Qualifiers.ControlCenter Lifecycle lifecycle, E0.a secondaryPanelRouter) {
        n.g(binding, "binding");
        n.g(shadeHeaderController, "shadeHeaderController");
        n.g(uiHandler, "uiHandler");
        n.g(headerController, "headerController");
        n.g(expandController, "expandController");
        n.g(mainPanelController, "mainPanelController");
        n.g(lifecycle, "lifecycle");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        FrameLayout root = binding.getRoot();
        n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.shadeHeaderController = shadeHeaderController;
        this.uiHandler = uiHandler;
        this.headerController = headerController;
        this.expandController = expandController;
        this.mainPanelController = mainPanelController;
        this.lifecycle = lifecycle;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.headerView = getView();
        this.type = MainPanelHeaderController.HEADER_TYPE_MSG;
        miui.systemui.widget.TextView headerMsgA = binding.headerMsgA;
        n.f(headerMsgA, "headerMsgA");
        this.msgA = new Msg(this, headerMsgA);
        miui.systemui.widget.TextView headerMsgB = binding.headerMsgB;
        n.f(headerMsgB, "headerMsgB");
        this.msgB = new Msg(this, headerMsgB);
        this.hiddenMsg = new ArrayList<>();
        this.showingOrAnimating = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void findAvailableAndShow() {
        if (this.hiddenMsg.isEmpty()) {
            return;
        }
        this.hiddenMsg.get(0).show();
    }

    private final void updateMaxLines() {
        int i2 = CommonUtils.INSTANCE.getInVerticalMode(getContext()) ? 2 : 1;
        this.binding.headerMsgA.setMaxLines(i2);
        this.binding.headerMsgB.setMaxLines(i2);
    }

    private final void updateSize() {
        this.binding.msgHeader.setLayoutParams(new FrameLayout.LayoutParams(((MainPanelHeaderController) this.headerController.get()).getLayoutParams()));
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        Space referenceSpace = this.binding.referenceSpace;
        n.f(referenceSpace, "referenceSpace");
        CommonUtils.setLayoutHeight$default(commonUtils, referenceSpace, getContext().getResources().getDimensionPixelSize(commonUtils.getForceVertical() ? R.dimen.header_qs_message_reference_height_force_vertical : R.dimen.header_qs_message_reference_height), false, 2, null);
    }

    private final void updateTextAppearance() {
        miui.systemui.widget.TextView textView = this.binding.headerMsgA;
        int i2 = R.style.TextAppearance_QS_HeaderMessage;
        textView.setTextAppearance(i2);
        this.binding.headerMsgB.setTextAppearance(i2);
    }

    public View getHeaderView() {
        return this.headerView;
    }

    public float getHeight() {
        return this.binding.msgHeader.getHeight();
    }

    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onApplyWindowInsets(WindowInsets insets) {
        n.g(insets, "insets");
        if (((MainPanelController) this.mainPanelController.get()).getStyle() == MainPanelController.Style.COMPACT) {
            updateSize();
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zOrientationChanged = configUtils.orientationChanged(i2);
        if (zOrientationChanged) {
            updateMaxLines();
        }
        if (zOrientationChanged || configUtils.dimensionsChanged(i2)) {
            updateSize();
        }
        if (configUtils.textAppearanceChanged(i2)) {
            updateTextAppearance();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        ArrayList<Msg> arrayList = this.hiddenMsg;
        arrayList.add(this.msgA);
        arrayList.add(this.msgB);
        this.shadeHeaderController.registerHeader(this);
        ((ControlCenterExpandController) this.expandController.get()).addCallback(new PanelExpandController.Callback() { // from class: miui.systemui.controlcenter.panel.main.header.MessageHeaderController.onCreate.2
            public void onAppearanceChanged(boolean z2, boolean z3) {
                super.onAppearanceChanged(z2, z3);
                if (z2) {
                    return;
                }
                Iterator it = MessageHeaderController.this.showingOrAnimating.iterator();
                while (it.hasNext()) {
                    ((Msg) it.next()).hide();
                }
            }

            public void onStretchHeightChanged(float f2) {
                MessageHeaderController.this.getView().setTranslationY(f2);
            }
        });
        updateMaxLines();
        updateSize();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        this.hiddenMsg.clear();
        this.showingOrAnimating.clear();
        this.shadeHeaderController.unregisterHeader(this);
    }

    public final void showMsg(CharSequence msg) {
        n.g(msg, "msg");
        ThreadUtilsExt.INSTANCE.runOrPost(this.uiHandler, new AnonymousClass1(msg));
    }

    public void transitionFrom(ShadeHeader from, boolean z2) {
        n.g(from, "from");
        Log.i(TAG, "transitionFrom " + from.getType());
        this.showing = true;
        findAvailableAndShow();
    }

    public void transitionTo(ShadeHeader to, boolean z2) {
        n.g(to, "to");
        Log.i(TAG, "transitionTo " + to.getType());
        this.showing = false;
        Iterator<T> it = this.showingOrAnimating.iterator();
        while (it.hasNext()) {
            ((Msg) it.next()).hide();
        }
    }
}
