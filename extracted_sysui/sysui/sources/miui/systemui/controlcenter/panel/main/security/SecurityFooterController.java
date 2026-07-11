package miui.systemui.controlcenter.panel.main.security;

import H0.s;
import I0.m;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.statusbar.MiuiSecurityController;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.x;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.SecurityFooterBinding;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.MainPanelStyleController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.policy.SecurityController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.OnClickListenerEx;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.widget.LinearLayout;
import miuix.animation.utils.EaseManager;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class SecurityFooterController extends MainPanelListItem.Controller<ControlCenterWindowViewImpl> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG = false;
    private static final int MSG_CLICK = 0;
    private static final int MSG_REFRESH_STATE = 1;
    private static final String TAG = "SecurityFooterController";
    private static final int TYPE_SECURITY_FOOTER = 732873;
    private boolean _listening;
    private final MiuiSecurityController dialog;
    private final E0.a distributor;
    private final SecurityFooterController$handler$1 handler;
    private boolean isVisible;
    private final ArrayList<SecurityFooterController> listItems;
    private final E0.a mainPanelModeController;
    private final E0.a mainPanelStyleController;
    private final int priority;
    private final boolean rightOrLeft;
    private final SecurityFooterController$securityCallback$1 securityCallback;
    private final SecurityController securityController;
    private Drawable securityIcon;
    private CharSequence securityMsg;
    private final int spanSize;
    private final SystemUIResourcesHelper systemUIResourcesHelper;
    private final int type;
    private final Handler uiHandler;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class SecurityFooterViewHolder extends MainPanelItemViewHolder {
        private final SecurityFooterBinding binding;

        /* JADX WARN: Illegal instructions before constructor call */
        public SecurityFooterViewHolder(SecurityFooterBinding binding) {
            n.g(binding, "binding");
            LinearLayout root = binding.getRoot();
            n.f(root, "getRoot(...)");
            super(root);
            this.binding = binding;
        }

        public final SecurityFooterBinding getBinding() {
            return this.binding;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            if (ConfigUtils.INSTANCE.textAppearanceChanged(i2)) {
                this.binding.securityMsg.setTextAppearance(R.style.TextAppearance_QS_SecurityFooter);
            }
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void updateBlendBlur() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.security.SecurityFooterController$createViewHolder$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((View) obj);
            return s.f314a;
        }

        public final void invoke(View view) {
            obtainMessage(0).sendToTarget();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.controlcenter.panel.main.security.SecurityFooterController$securityCallback$1] */
    /* JADX WARN: Type inference failed for: r2v2, types: [miui.systemui.controlcenter.panel.main.security.SecurityFooterController$handler$1] */
    public SecurityFooterController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Qualifiers.ControlCenter Lifecycle lifecycle, SecurityController securityController, @Main Handler uiHandler, @Background final Looper bgLooper, E0.a distributor, SystemUIResourcesHelper systemUIResourcesHelper, E0.a mainPanelStyleController, E0.a mainPanelModeController, MiuiSecurityController dialog) {
        super(windowView, lifecycle);
        n.g(windowView, "windowView");
        n.g(lifecycle, "lifecycle");
        n.g(securityController, "securityController");
        n.g(uiHandler, "uiHandler");
        n.g(bgLooper, "bgLooper");
        n.g(distributor, "distributor");
        n.g(systemUIResourcesHelper, "systemUIResourcesHelper");
        n.g(mainPanelStyleController, "mainPanelStyleController");
        n.g(mainPanelModeController, "mainPanelModeController");
        n.g(dialog, "dialog");
        this.securityController = securityController;
        this.uiHandler = uiHandler;
        this.distributor = distributor;
        this.systemUIResourcesHelper = systemUIResourcesHelper;
        this.mainPanelStyleController = mainPanelStyleController;
        this.mainPanelModeController = mainPanelModeController;
        this.dialog = dialog;
        this.securityCallback = new SecurityController.SecurityControllerCallback() { // from class: miui.systemui.controlcenter.panel.main.security.SecurityFooterController$securityCallback$1
            @Override // miui.systemui.controlcenter.policy.SecurityController.SecurityControllerCallback
            public void onStateChanged() {
                this.this$0.refreshState();
            }
        };
        this.handler = new Handler(bgLooper) { // from class: miui.systemui.controlcenter.panel.main.security.SecurityFooterController$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                String str = "unknown";
                try {
                    int i2 = msg.what;
                    if (i2 == 0) {
                        str = "handleClick";
                        this.handleClick();
                    } else if (i2 == 1) {
                        str = "handleRefreshState";
                        this.handleRefreshState();
                    }
                } catch (Throwable th) {
                    Log.e("SecurityFooterController", "error in " + str, th);
                }
            }
        };
        this.priority = EaseManager.EaseStyleDef.PERLIN;
        this.rightOrLeft = true;
        this.listItems = m.f(this);
        this.spanSize = 4;
        this.type = TYPE_SECURITY_FOOTER;
    }

    private final CharSequence getFooterText(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str, String str2, CharSequence charSequence, CharSequence charSequence2, boolean z7) {
        if (!z2) {
            if (z5) {
                return charSequence2 == null ? this.systemUIResourcesHelper.getString("quick_settings_disclosure_managed_profile_monitoring") : this.systemUIResourcesHelper.getString("quick_settings_disclosure_named_managed_profile_monitoring", charSequence2);
            }
            if (z4) {
                return this.systemUIResourcesHelper.getString("quick_settings_disclosure_monitoring");
            }
            if (str != null && str2 != null) {
                return this.systemUIResourcesHelper.getString("quick_settings_disclosure_vpns");
            }
            if (str2 != null) {
                return this.systemUIResourcesHelper.getString("quick_settings_disclosure_managed_profile_named_vpn", str2);
            }
            if (str != null) {
                return z3 ? this.systemUIResourcesHelper.getString("quick_settings_disclosure_personal_profile_named_vpn", str) : this.systemUIResourcesHelper.getString("quick_settings_disclosure_named_vpn", str);
            }
            if (z7) {
                return charSequence2 == null ? this.systemUIResourcesHelper.getString("quick_settings_disclosure_management") : this.systemUIResourcesHelper.getString("quick_settings_disclosure_named_management", charSequence2);
            }
            return null;
        }
        if (z4 || z5 || z6) {
            return charSequence == null ? this.systemUIResourcesHelper.getString("quick_settings_disclosure_management_monitoring") : this.systemUIResourcesHelper.getString("quick_settings_disclosure_named_management_monitoring", charSequence);
        }
        if (str != null && str2 != null) {
            return charSequence == null ? this.systemUIResourcesHelper.getString("quick_settings_disclosure_management_vpns") : this.systemUIResourcesHelper.getString("quick_settings_disclosure_named_management_vpns", charSequence);
        }
        if (str == null && str2 == null) {
            return charSequence == null ? this.systemUIResourcesHelper.getString("quick_settings_disclosure_management") : this.systemUIResourcesHelper.getString("quick_settings_disclosure_named_management", charSequence);
        }
        if (charSequence == null) {
            SystemUIResourcesHelper systemUIResourcesHelper = this.systemUIResourcesHelper;
            if (str == null) {
                str = str2;
            }
            return systemUIResourcesHelper.getString("quick_settings_disclosure_management_named_vpn", str);
        }
        SystemUIResourcesHelper systemUIResourcesHelper2 = this.systemUIResourcesHelper;
        if (str == null) {
            str = str2;
        }
        return systemUIResourcesHelper2.getString("quick_settings_disclosure_named_management_named_vpn", charSequence, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleClick() {
        this.dialog.showDeviceMonitoringDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleRefreshState() {
        final Drawable icon;
        boolean zIsDeviceManaged = this.securityController.isDeviceManaged();
        UserInfo currentUserInfo = this.securityController.getCurrentUserInfo();
        boolean z2 = UserManager.isDeviceInDemoMode(getContext()) && currentUserInfo != null && currentUserInfo.isDemo();
        boolean zHasWorkProfile = this.securityController.hasWorkProfile();
        boolean zHasCACertInCurrentUser = this.securityController.hasCACertInCurrentUser();
        boolean zHasCACertInWorkProfile = this.securityController.hasCACertInWorkProfile();
        boolean zIsNetworkLoggingEnabled = this.securityController.isNetworkLoggingEnabled();
        String primaryVpnName = this.securityController.getPrimaryVpnName();
        String workProfileVpnName = this.securityController.getWorkProfileVpnName();
        CharSequence deviceOwnerOrganizationName = this.securityController.getDeviceOwnerOrganizationName();
        CharSequence workProfileOrganizationName = this.securityController.getWorkProfileOrganizationName();
        boolean zIsProfileOwnerOfOrganizationOwnedDevice = this.securityController.isProfileOwnerOfOrganizationOwnedDevice();
        boolean zIsParentalControlsEnabled = this.securityController.isParentalControlsEnabled();
        boolean z3 = (zIsDeviceManaged && !z2) || zHasCACertInCurrentUser || zHasCACertInWorkProfile || workProfileVpnName != null || zIsProfileOwnerOfOrganizationOwnedDevice;
        final CharSequence footerText = z3 ? getFooterText(zIsDeviceManaged, zHasWorkProfile, zHasCACertInCurrentUser, zHasCACertInWorkProfile, zIsNetworkLoggingEnabled, primaryVpnName, workProfileVpnName, deviceOwnerOrganizationName, workProfileOrganizationName, zIsProfileOwnerOfOrganizationOwnedDevice) : null;
        final x xVar = new x();
        xVar.f5058a = R.drawable.ic_security_footer_icon;
        if (primaryVpnName != null || workProfileVpnName != null) {
            xVar.f5058a = this.securityController.isVpnBranded() ? R.drawable.ic_security_footer_vpn_branded : R.drawable.ic_security_footer_vpn;
        }
        if (zIsParentalControlsEnabled) {
            SecurityController securityController = this.securityController;
            icon = securityController.getIcon(securityController.getDeviceAdminInfo());
        } else {
            icon = null;
        }
        final boolean z4 = z3;
        this.uiHandler.post(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.security.a
            @Override // java.lang.Runnable
            public final void run() {
                SecurityFooterController.handleRefreshState$lambda$1(this.f5438a, footerText, icon, xVar, z4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleRefreshState$lambda$1(SecurityFooterController this$0, CharSequence charSequence, Drawable drawable, x iconId, boolean z2) {
        n.g(this$0, "this$0");
        n.g(iconId, "$iconId");
        this$0.securityMsg = charSequence;
        if (drawable == null) {
            drawable = this$0.getContext().getDrawable(iconId.f5058a);
        }
        this$0.securityIcon = drawable;
        this$0.updateState();
        this$0.setVisible(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshState() {
        sendEmptyMessage(1);
    }

    private final void setVisible(boolean z2) {
        if (this.isVisible == z2) {
            return;
        }
        this.isVisible = z2;
        MainPanelContentDistributor mainPanelContentDistributor = (MainPanelContentDistributor) this.distributor.get();
        n.d(mainPanelContentDistributor);
        MainPanelContentDistributor.distributePanels$default(mainPanelContentDistributor, false, 1, null);
        MainPanelContentDistributor.handleNotifyChanged$default(mainPanelContentDistributor, false, 1, null);
    }

    private final void set_listening(boolean z2) {
        if (this._listening == z2) {
            return;
        }
        this._listening = z2;
        if (!z2) {
            this.securityController.removeCallback(this.securityCallback);
        } else {
            this.securityController.addCallback(this.securityCallback);
            refreshState();
        }
    }

    private final void updateState() {
        MainPanelItemViewHolder holder = getHolder();
        SecurityFooterViewHolder securityFooterViewHolder = holder instanceof SecurityFooterViewHolder ? (SecurityFooterViewHolder) holder : null;
        if (securityFooterViewHolder != null) {
            CharSequence charSequence = this.securityMsg;
            if (charSequence != null) {
                securityFooterViewHolder.getBinding().securityMsg.setText(charSequence);
            }
            Drawable drawable = this.securityIcon;
            if (drawable != null) {
                securityFooterViewHolder.getBinding().icon.setImageDrawable(drawable);
            }
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return (((MainPanelStyleController) this.mainPanelStyleController.get()).getStyle() == MainPanelController.Style.COMPACT || ((MainPanelModeController) this.mainPanelModeController.get()).getMode() == MainPanelController.Mode.EDIT || !this.isVisible) ? false : true;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != TYPE_SECURITY_FOOTER) {
            return null;
        }
        SecurityFooterBinding securityFooterBindingInflate = SecurityFooterBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        n.f(securityFooterBindingInflate, "inflate(...)");
        OnClickListenerEx onClickListenerEx = OnClickListenerEx.INSTANCE;
        LinearLayout root = securityFooterBindingInflate.getRoot();
        n.f(root, "getRoot(...)");
        onClickListenerEx.setOnClickListenerEx(root, new AnonymousClass1());
        return new SecurityFooterViewHolder(securityFooterBindingInflate);
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
        updateState();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller, miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        super.onStart();
        set_listening(true);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller, miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        super.onStop();
        set_listening(false);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public ArrayList<SecurityFooterController> getListItems() {
        return this.listItems;
    }
}
