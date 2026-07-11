package miui.systemui.devicecontrols.management;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.devicecontrols.ControlsServiceInfo;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.eventtracking.DeviceControlsEventTracker;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.SmartDeviceUtils;

/* JADX INFO: loaded from: classes3.dex */
final class EditEmptyGuideHolder extends BaseHolder {
    private final ActivityStarter activityStarter;
    private final TextView appGuide;
    private final RelativeLayout emptyGuide;
    private final boolean isLocked;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditEmptyGuideHolder(View view, ActivityStarter starter, StatusBarStateController statusBarStateController) {
        super(view);
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(starter, "starter");
        kotlin.jvm.internal.n.g(statusBarStateController, "statusBarStateController");
        this.appGuide = (TextView) this.itemView.requireViewById(R.id.tv_go);
        this.emptyGuide = (RelativeLayout) this.itemView.requireViewById(R.id.rl_edit_empty_guide);
        this.activityStarter = starter;
        this.isLocked = CommonUtils.isLocked(statusBarStateController);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$4$lambda$3(ComponentName target, ElementWrapper wrapper, EditEmptyGuideHolder this$0, View view) {
        Intent launchIntentForPackage;
        kotlin.jvm.internal.n.g(target, "$target");
        kotlin.jvm.internal.n.g(wrapper, "$wrapper");
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (kotlin.jvm.internal.n.c(target.getPackageName(), "com.xiaomi.smarthome")) {
            launchIntentForPackage = new Intent();
            launchIntentForPackage.setComponent(ComponentName.unflattenFromString("com.xiaomi.smarthome/com.xiaomi.smarthome.SmartHomeMainActivity"));
            launchIntentForPackage.setData(Uri.parse("mihome://home.mi.com/main/login"));
        } else {
            EditEmptyGuideWrapper editEmptyGuideWrapper = (EditEmptyGuideWrapper) wrapper;
            if (editEmptyGuideWrapper.getPanelActivity() != null) {
                SmartDeviceUtils smartDeviceUtils = SmartDeviceUtils.INSTANCE;
                Context context = this$0.itemView.getContext();
                kotlin.jvm.internal.n.f(context, "getContext(...)");
                boolean zIsAllowTrivialControlsUnderLockscreen = smartDeviceUtils.isAllowTrivialControlsUnderLockscreen(context);
                Intent intent = new Intent();
                intent.setComponent(editEmptyGuideWrapper.getPanelActivity());
                intent.putExtra(ControlsServiceInfo.EXTRA_LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS, zIsAllowTrivialControlsUnderLockscreen);
                launchIntentForPackage = intent;
            } else {
                launchIntentForPackage = this$0.itemView.getContext().getPackageManager().getLaunchIntentForPackage(target.getPackageName());
            }
        }
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(335544320);
            this$0.activityStarter.postStartActivityDismissingKeyguard(launchIntentForPackage, 0);
        }
        DeviceControlsEventTracker deviceControlsEventTracker = DeviceControlsEventTracker.INSTANCE;
        EditEmptyGuideWrapper editEmptyGuideWrapper2 = (EditEmptyGuideWrapper) wrapper;
        String string = editEmptyGuideWrapper2.getApp().toString();
        ComponentName component = editEmptyGuideWrapper2.getComponent();
        deviceControlsEventTracker.trackControlsEditAppJumpClickEvent(string, component != null ? component.flattenToString() : null, this$0.isLocked);
    }

    @Override // miui.systemui.devicecontrols.management.BaseHolder
    public void bindData(final ElementWrapper wrapper) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        EditEmptyGuideWrapper editEmptyGuideWrapper = (EditEmptyGuideWrapper) wrapper;
        this.emptyGuide.setVisibility(editEmptyGuideWrapper.getVisibility() ? 0 : 8);
        if (editEmptyGuideWrapper.getVisibility()) {
            TextView textView = this.appGuide;
            textView.setText(textView.getContext().getString(R.string.controls_edit_go_to_app_guide, editEmptyGuideWrapper.getApp()));
            final ComponentName component = editEmptyGuideWrapper.getComponent();
            if (component != null) {
                this.emptyGuide.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.management.m
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EditEmptyGuideHolder.bindData$lambda$4$lambda$3(component, wrapper, this, view);
                    }
                });
            }
            super.bindData(wrapper);
        }
    }

    public final ActivityStarter getActivityStarter() {
        return this.activityStarter;
    }

    public final TextView getAppGuide() {
        return this.appGuide;
    }

    public final RelativeLayout getEmptyGuide() {
        return this.emptyGuide;
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
    }
}
