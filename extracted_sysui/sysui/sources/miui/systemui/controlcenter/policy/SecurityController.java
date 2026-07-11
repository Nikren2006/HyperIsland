package miui.systemui.controlcenter.policy;

import H0.s;
import android.app.ActivityManager;
import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.net.VpnManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.net.LegacyVpnInfo;
import com.android.internal.net.VpnConfig;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.settings.CurrentUserTracker;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.SystemUIResourcesHelper;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class SecurityController extends CurrentUserTracker {
    private static final int CA_CERT_LOADING_RETRY_TIME_IN_MS = 30000;
    private static final int NO_NETWORK = -1;
    private static final String TAG = "PluginSecurityController";
    private static final String VPN_BRANDED_META_DATA = "com.android.systemui.IS_BRANDED";
    private final Executor bgExecutor;
    private final Handler bgHandler;
    private final BroadcastDispatcher broadcastDispatcher;
    private final BroadcastReceiver broadcastReceiver;
    private final ArrayList<SecurityControllerCallback> callbacks;
    private final ConnectivityManager connectivityManager;
    private final Context context;
    private int currentUserID;
    private SparseArray<VpnConfig> currentVpns;
    private final DevicePolicyManager devicePolicyManager;
    private final ArrayMap<Integer, Boolean> hasCACerts;
    private final ConnectivityManager.NetworkCallback networkCallback;
    private final PackageManager packageManager;
    private final SystemUIResourcesHelper systemUIResourcesHelper;
    private final UserManager userManager;
    private final VpnManager vpnManager;
    private int vpnUserId;
    public static final Companion Companion = new Companion(null);
    private static final NetworkRequest REQUEST = new NetworkRequest.Builder().clearCapabilities().build();
    private static final Method REGISTER_NETWORK_CALLBACK_METHOD = ConnectivityManager.class.getDeclaredMethod("registerNetworkCallback", NetworkRequest.class, ConnectivityManager.NetworkCallback.class);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface SecurityControllerCallback {
        void onStateChanged();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecurityController(@SystemUI Context context, @Background Handler bgHandler, @Background Executor bgExecutor, BroadcastDispatcher broadcastDispatcher, SystemUIResourcesHelper systemUIResourcesHelper) {
        super(broadcastDispatcher);
        n.g(context, "context");
        n.g(bgHandler, "bgHandler");
        n.g(bgExecutor, "bgExecutor");
        n.g(broadcastDispatcher, "broadcastDispatcher");
        n.g(systemUIResourcesHelper, "systemUIResourcesHelper");
        this.context = context;
        this.bgHandler = bgHandler;
        this.bgExecutor = bgExecutor;
        this.broadcastDispatcher = broadcastDispatcher;
        this.systemUIResourcesHelper = systemUIResourcesHelper;
        Object systemService = context.getSystemService("device_policy");
        n.e(systemService, "null cannot be cast to non-null type android.app.admin.DevicePolicyManager");
        this.devicePolicyManager = (DevicePolicyManager) systemService;
        Object systemService2 = context.getSystemService("connectivity");
        n.e(systemService2, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.connectivityManager = (ConnectivityManager) systemService2;
        this.vpnManager = (VpnManager) context.getSystemService(VpnManager.class);
        this.packageManager = context.getPackageManager();
        Object systemService3 = context.getSystemService("user");
        n.e(systemService3, "null cannot be cast to non-null type android.os.UserManager");
        this.userManager = (UserManager) systemService3;
        this.broadcastReceiver = new BroadcastReceiver() { // from class: miui.systemui.controlcenter.policy.SecurityController$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                int intExtra;
                n.g(context2, "context");
                n.g(intent, "intent");
                if (n.c("android.security.action.TRUST_STORE_CHANGED", intent.getAction())) {
                    this.this$0.refreshCACerts(getSendingUserId());
                } else {
                    if (!n.c("android.intent.action.USER_UNLOCKED", intent.getAction()) || (intExtra = intent.getIntExtra("android.intent.extra.user_handle", -10000)) == -10000) {
                        return;
                    }
                    this.this$0.refreshCACerts(intExtra);
                }
            }
        };
        this.networkCallback = new ConnectivityManager.NetworkCallback() { // from class: miui.systemui.controlcenter.policy.SecurityController$networkCallback$1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                n.g(network, "network");
                CommonUtils.debugLog$default(CommonUtils.INSTANCE, "PluginSecurityController", "onAvailable " + network.getNetId(), null, 4, null);
                this.this$0.updateState();
                this.this$0.fireCallbacks();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                n.g(network, "network");
                CommonUtils.debugLog$default(CommonUtils.INSTANCE, "PluginSecurityController", "onLost " + network.getNetId(), null, 4, null);
                this.this$0.updateState();
                this.this$0.fireCallbacks();
            }
        };
        this.callbacks = new ArrayList<>();
        this.currentVpns = new SparseArray<>();
        this.hasCACerts = new ArrayMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fireCallbacks() {
        synchronized (this.callbacks) {
            try {
                Iterator<T> it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((SecurityControllerCallback) it.next()).onStateChanged();
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final String getNameForVpnConfig(VpnConfig vpnConfig, UserHandle userHandle) {
        if (vpnConfig.legacy) {
            return this.systemUIResourcesHelper.getString("legacy_vpn_name");
        }
        String str = vpnConfig.user;
        try {
            Context context = this.context;
            Context contextCreatePackageContextAsUser = context.createPackageContextAsUser(context.getPackageName(), 0, userHandle);
            n.f(contextCreatePackageContextAsUser, "createPackageContextAsUser(...)");
            return VpnConfig.getVpnLabel(contextCreatePackageContextAsUser, str).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "Package " + str + " is not present", e2);
            return null;
        }
    }

    private final String getPackageNameForVpnConfig(VpnConfig vpnConfig) {
        if (vpnConfig.legacy) {
            return null;
        }
        return vpnConfig.user;
    }

    private final ComponentName getProfileOwnerOrDeviceOwnerComponent() {
        return getProfileOwnerOrDeviceOwnerSupervisionComponent();
    }

    private final ComponentName getProfileOwnerOrDeviceOwnerSupervisionComponent() {
        try {
            return (ComponentName) this.devicePolicyManager.getClass().getMethod("getProfileOwnerOrDeviceOwnerSupervisionComponent", UserHandle.class).invoke(this.devicePolicyManager, new UserHandle(this.currentUserID));
        } catch (Error unused) {
            return null;
        }
    }

    private final int getWorkProfileUserId(int i2) {
        List<UserInfo> profiles = this.userManager.getProfiles(i2);
        n.f(profiles, "getProfiles(...)");
        for (UserInfo userInfo : profiles) {
            if (userInfo.isManagedProfile()) {
                return userInfo.id;
            }
        }
        return -10000;
    }

    private final boolean isVpnPackageBranded(String str) {
        try {
            ApplicationInfo applicationInfo = this.packageManager.getApplicationInfo(str, 128);
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null && (applicationInfo.flags & 1) != 0) {
                return bundle.getBoolean(VPN_BRANDED_META_DATA, false);
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshCACerts(final int i2) {
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.policy.a
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                SecurityController.refreshCACerts$lambda$10(this.f5476a, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void refreshCACerts$lambda$10(miui.systemui.controlcenter.policy.SecurityController r13, int r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.policy.SecurityController.refreshCACerts$lambda$10(miui.systemui.controlcenter.policy.SecurityController, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateState() {
        LegacyVpnInfo legacyVpnInfo;
        SparseArray<VpnConfig> sparseArray = new SparseArray<>();
        for (UserInfo userInfo : this.userManager.getUsers()) {
            Class cls = Integer.TYPE;
            VpnConfig vpnConfig = (VpnConfig) VpnManager.class.getDeclaredMethod("getVpnConfig", cls).invoke(this.vpnManager, Integer.valueOf(userInfo.id));
            if (vpnConfig != null && !n.c(vpnConfig.user, "com.miui.vpnsdkmanager") && (!vpnConfig.legacy || ((legacyVpnInfo = (LegacyVpnInfo) VpnManager.class.getDeclaredMethod("getLegacyVpnInfo", cls).invoke(this.vpnManager, Integer.valueOf(userInfo.id))) != null && legacyVpnInfo.state == 3))) {
                sparseArray.put(userInfo.id, vpnConfig);
            }
        }
        this.currentVpns = sparseArray;
    }

    public final void addCallback(SecurityControllerCallback callback) {
        n.g(callback, "callback");
        synchronized (this.callbacks) {
            if (this.callbacks.contains(callback)) {
                return;
            }
            this.callbacks.add(callback);
        }
    }

    public final UserInfo getCurrentUserInfo() {
        return this.userManager.getUserInfo(ActivityManager.getCurrentUser());
    }

    public final DeviceAdminInfo getDeviceAdminInfo() {
        ComponentName profileOwnerOrDeviceOwnerComponent = getProfileOwnerOrDeviceOwnerComponent();
        n.d(profileOwnerOrDeviceOwnerComponent);
        return getDeviceAdminInfo(profileOwnerOrDeviceOwnerComponent);
    }

    public final ComponentName getDeviceOwnerComponentOnAnyUser() {
        return this.devicePolicyManager.getDeviceOwnerComponentOnAnyUser();
    }

    public final String getDeviceOwnerName() {
        return this.devicePolicyManager.getDeviceOwnerNameOnAnyUser();
    }

    public final CharSequence getDeviceOwnerOrganizationName() {
        return this.devicePolicyManager.getDeviceOwnerOrganizationName();
    }

    public final int getDeviceOwnerType(ComponentName admin) throws IllegalAccessException, InvocationTargetException {
        n.g(admin, "admin");
        Object objInvoke = DevicePolicyManager.class.getDeclaredMethod("getDeviceOwnerType", ComponentName.class).invoke(this.devicePolicyManager, admin);
        n.e(objInvoke, "null cannot be cast to non-null type kotlin.Int");
        return ((Integer) objInvoke).intValue();
    }

    public final Drawable getIcon(DeviceAdminInfo deviceAdminInfo) {
        if (deviceAdminInfo != null) {
            return deviceAdminInfo.loadIcon(this.packageManager);
        }
        return null;
    }

    public final CharSequence getLabel(DeviceAdminInfo deviceAdminInfo) {
        if (deviceAdminInfo != null) {
            return deviceAdminInfo.loadLabel(this.packageManager);
        }
        return null;
    }

    public final String getPrimaryVpnName() {
        VpnConfig vpnConfig = this.currentVpns.get(this.vpnUserId);
        if (vpnConfig != null) {
            return getNameForVpnConfig(vpnConfig, new UserHandle(this.vpnUserId));
        }
        return null;
    }

    public final String getProfileOwnerName() {
        int[] profileIdsWithDisabled = this.userManager.getProfileIdsWithDisabled(this.currentUserID);
        n.f(profileIdsWithDisabled, "getProfileIdsWithDisabled(...)");
        for (int i2 : profileIdsWithDisabled) {
            String profileOwnerNameAsUser = this.devicePolicyManager.getProfileOwnerNameAsUser(i2);
            if (profileOwnerNameAsUser != null) {
                n.d(profileOwnerNameAsUser);
                return profileOwnerNameAsUser;
            }
        }
        return null;
    }

    public final CharSequence getWorkProfileOrganizationName() {
        int workProfileUserId = getWorkProfileUserId(this.currentUserID);
        if (workProfileUserId == -10000) {
            return null;
        }
        return this.devicePolicyManager.getOrganizationNameForUser(workProfileUserId);
    }

    public final String getWorkProfileVpnName() {
        VpnConfig vpnConfig;
        int workProfileUserId = getWorkProfileUserId(this.vpnUserId);
        if (workProfileUserId == -10000 || (vpnConfig = this.currentVpns.get(workProfileUserId)) == null) {
            return null;
        }
        UserHandle userHandleOf = UserHandle.of(workProfileUserId);
        n.f(userHandleOf, "of(...)");
        return getNameForVpnConfig(vpnConfig, userHandleOf);
    }

    public final boolean hasCACertInCurrentUser() {
        return n.c(this.hasCACerts.get(Integer.valueOf(this.currentUserID)), Boolean.TRUE);
    }

    public final boolean hasCACertInWorkProfile() {
        int workProfileUserId = getWorkProfileUserId(this.currentUserID);
        if (workProfileUserId == -10000) {
            return false;
        }
        return n.c(this.hasCACerts.get(Integer.valueOf(workProfileUserId)), Boolean.TRUE);
    }

    public final boolean hasProfileOwner() {
        return this.devicePolicyManager.getProfileOwnerAsUser(this.currentUserID) != null;
    }

    public final boolean hasWorkProfile() {
        return getWorkProfileUserId(this.currentUserID) != -10000;
    }

    public final boolean isDeviceManaged() {
        return this.devicePolicyManager.isDeviceManaged();
    }

    public final boolean isNetworkLoggingEnabled() {
        return this.devicePolicyManager.isNetworkLoggingEnabled(null);
    }

    public final boolean isParentalControlsEnabled() {
        return getProfileOwnerOrDeviceOwnerSupervisionComponent() != null;
    }

    public final boolean isProfileOwnerOfOrganizationOwnedDevice() {
        return this.devicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile();
    }

    public final boolean isVpnBranded() {
        String packageNameForVpnConfig;
        VpnConfig vpnConfig = this.currentVpns.get(this.vpnUserId);
        if (vpnConfig == null || (packageNameForVpnConfig = getPackageNameForVpnConfig(vpnConfig)) == null) {
            return false;
        }
        return isVpnPackageBranded(packageNameForVpnConfig);
    }

    public final boolean isVpnEnabled() {
        int[] profileIdsWithDisabled = this.userManager.getProfileIdsWithDisabled(this.vpnUserId);
        n.f(profileIdsWithDisabled, "getProfileIdsWithDisabled(...)");
        for (int i2 : profileIdsWithDisabled) {
            VpnConfig vpnConfig = this.currentVpns.get(i2);
            if (vpnConfig != null) {
                n.d(vpnConfig);
                return true;
            }
        }
        return false;
    }

    public final boolean isVpnRestricted() {
        return this.userManager.getUserInfo(this.currentUserID).isRestricted() || this.userManager.hasUserRestriction("no_config_vpn", new UserHandle(this.currentUserID));
    }

    public final boolean isWorkProfileOn() {
        UserHandle userHandleOf = UserHandle.of(getWorkProfileUserId(this.currentUserID));
        return (userHandleOf == null || this.userManager.isQuietModeEnabled(userHandleOf)) ? false : true;
    }

    public final void onPluginCreated() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.security.action.TRUST_STORE_CHANGED");
        intentFilter.addAction("android.intent.action.USER_UNLOCKED");
        BroadcastDispatcher broadcastDispatcher = this.broadcastDispatcher;
        BroadcastReceiver broadcastReceiver = this.broadcastReceiver;
        Handler handler = this.bgHandler;
        UserHandle ALL = UserHandle.ALL;
        n.f(ALL, "ALL");
        broadcastDispatcher.registerReceiverWithHandler(broadcastReceiver, intentFilter, handler, ALL);
        REGISTER_NETWORK_CALLBACK_METHOD.invoke(this.connectivityManager, REQUEST, this.networkCallback);
        onUserSwitched(ActivityManager.getCurrentUser());
        startTracking();
    }

    public final void onPluginDestroyed() {
        stopTracking();
        this.connectivityManager.unregisterNetworkCallback(this.networkCallback);
        this.broadcastDispatcher.unregisterReceiver(this.broadcastReceiver);
    }

    @Override // miui.systemui.settings.CurrentUserTracker
    public void onUserSwitched(int i2) {
        this.currentUserID = i2;
        UserInfo userInfo = this.userManager.getUserInfo(i2);
        this.vpnUserId = userInfo.isRestricted() ? userInfo.restrictedProfileParentId : this.currentUserID;
        fireCallbacks();
    }

    public final void removeCallback(SecurityControllerCallback callback) {
        n.g(callback, "callback");
        synchronized (this.callbacks) {
            this.callbacks.remove(callback);
        }
    }

    private final DeviceAdminInfo getDeviceAdminInfo(ComponentName componentName) {
        try {
            ResolveInfo resolveInfo = new ResolveInfo();
            resolveInfo.activityInfo = this.packageManager.getReceiverInfo(componentName, 128);
            return new DeviceAdminInfo(this.context, resolveInfo);
        } catch (PackageManager.NameNotFoundException | IOException | XmlPullParserException unused) {
            return null;
        }
    }
}
