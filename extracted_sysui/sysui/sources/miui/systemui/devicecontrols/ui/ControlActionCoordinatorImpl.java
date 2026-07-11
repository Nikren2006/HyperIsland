package miui.systemui.devicecontrols.ui;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.service.controls.Control;
import android.service.controls.actions.BooleanAction;
import android.service.controls.actions.CommandAction;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.eventtracking.DeviceControlsEventTracker;
import miui.systemui.devicecontrols.util.ControlsUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class ControlActionCoordinatorImpl implements ControlActionCoordinator {
    public static final Companion Companion = new Companion(null);
    private static final long RESPONSE_TIMEOUT_IN_MILLIS = 3000;
    private Set<String> actionsInProgress;
    private final ActivityStarter activityStarter;
    private final DelayableExecutor bgExecutor;
    private final BroadcastDispatcher broadcastDispatcher;
    private final Context context;
    private Dialog dialog;
    private final HapticFeedback hapticFeedback;
    private Action pendingAction;
    private final StatusBarStateController statusBarStateController;
    private final DelayableExecutor uiExecutor;
    private final Vibrator vibrator;

    public final class Action {
        private final boolean blockable;
        private final String controlId;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final Function0 f5641f;
        final /* synthetic */ ControlActionCoordinatorImpl this$0;

        public Action(ControlActionCoordinatorImpl controlActionCoordinatorImpl, String controlId, Function0 f2, boolean z2) {
            kotlin.jvm.internal.n.g(controlId, "controlId");
            kotlin.jvm.internal.n.g(f2, "f");
            this.this$0 = controlActionCoordinatorImpl;
            this.controlId = controlId;
            this.f5641f = f2;
            this.blockable = z2;
        }

        public final boolean getBlockable() {
            return this.blockable;
        }

        public final String getControlId() {
            return this.controlId;
        }

        public final Function0 getF() {
            return this.f5641f;
        }

        public final void invoke() {
            if (!this.blockable || this.this$0.shouldRunAction(this.controlId)) {
                this.f5641f.invoke();
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.ui.ControlActionCoordinatorImpl$longPress$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ ControlViewHolder $cvh;
        final /* synthetic */ ControlActionCoordinatorImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ControlViewHolder controlViewHolder, ControlActionCoordinatorImpl controlActionCoordinatorImpl) {
            super(0);
            this.$cvh = controlViewHolder;
            this.this$0 = controlActionCoordinatorImpl;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() throws IOException {
            m119invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m119invoke() throws IOException {
            Control control = this.$cvh.getCws().getControl();
            if (control != null) {
                ControlActionCoordinatorImpl controlActionCoordinatorImpl = this.this$0;
                ControlViewHolder controlViewHolder = this.$cvh;
                if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
                    HapticFeedback hapticFeedback = controlActionCoordinatorImpl.hapticFeedback;
                    if (hapticFeedback != null) {
                        hapticFeedback.postLongClick();
                    }
                } else {
                    controlViewHolder.getLayout().performHapticFeedback(0);
                }
                Intent intent = control.getAppIntent().getIntent();
                kotlin.jvm.internal.n.f(intent, "getIntent(...)");
                controlActionCoordinatorImpl.showDetail(controlViewHolder, intent);
                String strFlattenToShortString = controlViewHolder.getCws().getComponentName().flattenToShortString();
                DeviceControlsEventTracker.INSTANCE.trackEquipmentLongClickEvent(strFlattenToShortString, strFlattenToShortString, ControlsUtils.INSTANCE.checkSenseType(controlViewHolder.getCws().getCi().getControlId()) ? DeviceControlsEventTracker.EQUIPMENT_STYLE_SCENE_ITEM : DeviceControlsEventTracker.EQUIPMENT_STYLE_ITEM, controlActionCoordinatorImpl.isLocked());
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.ui.ControlActionCoordinatorImpl$setValue$1, reason: invalid class name and case insensitive filesystem */
    public static final class C05031 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ ControlViewHolder $cvh;
        final /* synthetic */ float $newValue;
        final /* synthetic */ String $templateId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05031(ControlViewHolder controlViewHolder, String str, float f2) {
            super(0);
            this.$cvh = controlViewHolder;
            this.$templateId = str;
            this.$newValue = f2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m120invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m120invoke() {
            this.$cvh.action(new FloatAction(this.$templateId, this.$newValue));
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.ui.ControlActionCoordinatorImpl$toggle$1, reason: invalid class name and case insensitive filesystem */
    public static final class C05041 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ ControlViewHolder $cvh;
        final /* synthetic */ boolean $isChecked;
        final /* synthetic */ String $templateId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05041(ControlViewHolder controlViewHolder, String str, boolean z2) {
            super(0);
            this.$cvh = controlViewHolder;
            this.$templateId = str;
            this.$isChecked = z2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m121invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m121invoke() {
            if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
                HapticFeedback hapticFeedback = ControlActionCoordinatorImpl.this.hapticFeedback;
                if (hapticFeedback != null) {
                    hapticFeedback.postClick();
                }
            } else {
                this.$cvh.getLayout().performHapticFeedback(1);
            }
            this.$cvh.action(new BooleanAction(this.$templateId, true ^ this.$isChecked));
            if (this.$cvh.getCws().getControl() != null) {
                ControlViewHolder controlViewHolder = this.$cvh;
                ControlActionCoordinatorImpl controlActionCoordinatorImpl = ControlActionCoordinatorImpl.this;
                String strFlattenToShortString = controlViewHolder.getCws().getComponentName().flattenToShortString();
                DeviceControlsEventTracker deviceControlsEventTracker = DeviceControlsEventTracker.INSTANCE;
                ControlsUtils controlsUtils = ControlsUtils.INSTANCE;
                deviceControlsEventTracker.trackEquipmentClickEvent(strFlattenToShortString, strFlattenToShortString, controlsUtils.checkSenseType(controlViewHolder.getCws().getCi().getControlId()) ? DeviceControlsEventTracker.DEVICE_STATUS_UNCHANGING : controlViewHolder.getEnabledForState$miui_devicecontrols_release() ? DeviceControlsEventTracker.DEVICE_STATUS_OFF : DeviceControlsEventTracker.DEVICE_STATUS_ON, controlsUtils.checkSenseType(controlViewHolder.getCws().getCi().getControlId()) ? DeviceControlsEventTracker.EQUIPMENT_STYLE_SCENE_ITEM : DeviceControlsEventTracker.EQUIPMENT_STYLE_ITEM, controlActionCoordinatorImpl.isLocked());
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.ui.ControlActionCoordinatorImpl$touch$1, reason: invalid class name and case insensitive filesystem */
    public static final class C05051 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ Control $control;
        final /* synthetic */ ControlViewHolder $cvh;
        final /* synthetic */ String $templateId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05051(ControlViewHolder controlViewHolder, Control control, String str) {
            super(0);
            this.$cvh = controlViewHolder;
            this.$control = control;
            this.$templateId = str;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() throws IOException {
            m122invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m122invoke() throws IOException {
            if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
                HapticFeedback hapticFeedback = ControlActionCoordinatorImpl.this.hapticFeedback;
                if (hapticFeedback != null) {
                    hapticFeedback.postClick();
                }
            } else {
                this.$cvh.getLayout().performHapticFeedback(1);
            }
            if (!this.$cvh.usePanel()) {
                this.$cvh.action(new CommandAction(this.$templateId));
                return;
            }
            ControlActionCoordinatorImpl controlActionCoordinatorImpl = ControlActionCoordinatorImpl.this;
            ControlViewHolder controlViewHolder = this.$cvh;
            Intent intent = this.$control.getAppIntent().getIntent();
            kotlin.jvm.internal.n.f(intent, "getIntent(...)");
            controlActionCoordinatorImpl.showDetail(controlViewHolder, intent);
        }
    }

    public ControlActionCoordinatorImpl(Context context, DelayableExecutor bgExecutor, @Main DelayableExecutor uiExecutor, ActivityStarter activityStarter, StatusBarStateController statusBarStateController, BroadcastDispatcher broadcastDispatcher, HapticFeedback hapticFeedback) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(activityStarter, "activityStarter");
        kotlin.jvm.internal.n.g(statusBarStateController, "statusBarStateController");
        kotlin.jvm.internal.n.g(broadcastDispatcher, "broadcastDispatcher");
        this.context = context;
        this.bgExecutor = bgExecutor;
        this.uiExecutor = uiExecutor;
        this.activityStarter = activityStarter;
        this.statusBarStateController = statusBarStateController;
        this.broadcastDispatcher = broadcastDispatcher;
        this.hapticFeedback = hapticFeedback;
        Object systemService = context.getSystemService("vibrator");
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type android.os.Vibrator");
        this.vibrator = (Vibrator) systemService;
        this.actionsInProgress = new LinkedHashSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bouncerOrRun$lambda$1(Action action) {
        kotlin.jvm.internal.n.g(action, "$action");
        Log.d("ControlsUiController", "Device unlocked, invoking controls action");
        action.invoke();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bouncerOrRun$lambda$2(ControlActionCoordinatorImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.pendingAction = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLocked() {
        return CommonUtils.isLocked(this.statusBarStateController);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldRunAction(final String str) {
        if (!this.actionsInProgress.add(str)) {
            return false;
        }
        this.uiExecutor.executeDelayed(new Runnable() { // from class: miui.systemui.devicecontrols.ui.j
            @Override // java.lang.Runnable
            public final void run() {
                ControlActionCoordinatorImpl.shouldRunAction$lambda$0(this.f5679a, str);
            }
        }, 3000L);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void shouldRunAction$lambda$0(ControlActionCoordinatorImpl this$0, String controlId) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(controlId, "$controlId");
        this$0.actionsInProgress.remove(controlId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDetail(ControlViewHolder controlViewHolder, Intent intent) throws IOException {
        Control control = controlViewHolder.getCws().getControl();
        if (control != null) {
            Runtime.getRuntime().exec("cmd statusbar collapse");
            CommonUtils.INSTANCE.callDismissKeyGuard(this.context);
            intent.putExtra("device_id", control.getControlId());
            try {
                ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
                activityOptionsMakeBasic.setPendingIntentBackgroundActivityLaunchAllowed(true);
                control.getAppIntent().send(this.context, 0, null, null, null, null, activityOptionsMakeBasic.toBundle());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private final void vibrate(final VibrationEffect vibrationEffect) {
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.g
            @Override // java.lang.Runnable
            public final void run() {
                ControlActionCoordinatorImpl.vibrate$lambda$3(this.f5675a, vibrationEffect);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void vibrate$lambda$3(ControlActionCoordinatorImpl this$0, VibrationEffect effect) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(effect, "$effect");
        this$0.vibrator.vibrate(effect);
    }

    @VisibleForTesting
    public final void bouncerOrRun(final Action action) {
        kotlin.jvm.internal.n.g(action, "action");
        if (!isLocked()) {
            action.invoke();
            return;
        }
        if (isLocked()) {
            this.context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            this.pendingAction = action;
        }
        this.activityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: miui.systemui.devicecontrols.ui.h
            public final boolean onDismiss() {
                return ControlActionCoordinatorImpl.bouncerOrRun$lambda$1(action);
            }
        }, new Runnable() { // from class: miui.systemui.devicecontrols.ui.i
            @Override // java.lang.Runnable
            public final void run() {
                ControlActionCoordinatorImpl.bouncerOrRun$lambda$2(this.f5678a);
            }
        }, true);
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void closeDialogs() {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.dialog = null;
    }

    @VisibleForTesting
    public final Action createAction(String controlId, Function0 f2, boolean z2) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
        kotlin.jvm.internal.n.g(f2, "f");
        return new Action(this, controlId, f2, z2);
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void drag(boolean z2) {
        if (z2) {
            vibrate(Vibrations.INSTANCE.getRangeEdgeEffect());
        } else {
            vibrate(Vibrations.INSTANCE.getRangeMiddleEffect());
        }
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void enableActionOnTouch(String controlId) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
        this.actionsInProgress.remove(controlId);
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void longPress(ControlViewHolder cvh) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        bouncerOrRun(createAction(cvh.getCws().getCi().getControlId(), new AnonymousClass1(cvh, this), false));
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void runPendingAction(String controlId) {
        kotlin.jvm.internal.n.g(controlId, "controlId");
        if (isLocked()) {
            return;
        }
        Action action = this.pendingAction;
        if (kotlin.jvm.internal.n.c(action != null ? action.getControlId() : null, controlId)) {
            Action action2 = this.pendingAction;
            if (action2 != null) {
                action2.invoke();
            }
            this.pendingAction = null;
        }
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void setValue(ControlViewHolder cvh, String templateId, float f2) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        kotlin.jvm.internal.n.g(templateId, "templateId");
        bouncerOrRun(createAction(cvh.getCws().getCi().getControlId(), new C05031(cvh, templateId, f2), false));
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void toggle(ControlViewHolder cvh, String templateId, boolean z2) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        kotlin.jvm.internal.n.g(templateId, "templateId");
        bouncerOrRun(createAction(cvh.getCws().getCi().getControlId(), new C05041(cvh, templateId, z2), true));
    }

    @Override // miui.systemui.devicecontrols.ui.ControlActionCoordinator
    public void touch(ControlViewHolder cvh, String templateId, Control control) {
        kotlin.jvm.internal.n.g(cvh, "cvh");
        kotlin.jvm.internal.n.g(templateId, "templateId");
        kotlin.jvm.internal.n.g(control, "control");
        bouncerOrRun(createAction(cvh.getCws().getCi().getControlId(), new C05051(cvh, control, templateId), cvh.usePanel()));
    }

    public /* synthetic */ ControlActionCoordinatorImpl(Context context, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, ActivityStarter activityStarter, StatusBarStateController statusBarStateController, BroadcastDispatcher broadcastDispatcher, HapticFeedback hapticFeedback, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, delayableExecutor, delayableExecutor2, activityStarter, statusBarStateController, broadcastDispatcher, (i2 & 64) != 0 ? null : hapticFeedback);
    }
}
