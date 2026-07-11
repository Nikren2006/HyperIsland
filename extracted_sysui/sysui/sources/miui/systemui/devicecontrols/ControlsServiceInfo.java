package miui.systemui.devicecontrols;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.IconDrawableFactory;
import android.util.Log;
import androidx.annotation.WorkerThread;
import f1.g;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.devicecontrols.management.DefaultAppInfo;

/* JADX INFO: loaded from: classes3.dex */
public class ControlsServiceInfo extends DefaultAppInfo {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS = "android.service.controls.extra.LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS";
    public static final String META_DATA_PANEL_ACTIVITY = "android.service.controls.META_DATA_PANEL_ACTIVITY";
    private static final String TAG = "ControlsServiceInfo";
    private final ComponentName _panelActivity;
    private final Context context;
    private ComponentName panelActivity;
    private boolean resolved;
    private final ServiceInfo serviceInfo;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlsServiceInfo(Context context, ServiceInfo serviceInfo) {
        super(context, context.getPackageManager(), context.getUserId(), serviceInfo.getComponentName());
        n.g(context, "context");
        n.g(serviceInfo, "serviceInfo");
        this.context = context;
        this.serviceInfo = serviceInfo;
        Bundle bundle = serviceInfo.metaData;
        String string = bundle != null ? bundle.getString(META_DATA_PANEL_ACTIVITY) : null;
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(string == null ? "" : string);
        if (componentNameUnflattenFromString == null || !n.c(componentNameUnflattenFromString.getPackageName(), this.componentName.getPackageName())) {
            this._panelActivity = null;
        } else {
            this._panelActivity = componentNameUnflattenFromString;
        }
    }

    @WorkerThread
    private final boolean isComponentActuallyEnabled(ActivityInfo activityInfo) {
        int componentEnabledSetting = this.mPm.getComponentEnabledSetting(activityInfo.getComponentName());
        return componentEnabledSetting != 0 ? componentEnabledSetting == 1 : activityInfo.enabled;
    }

    public static /* synthetic */ void resolvePanelActivity$default(ControlsServiceInfo controlsServiceInfo, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resolvePanelActivity");
        }
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        controlsServiceInfo.resolvePanelActivity(z2);
    }

    @WorkerThread
    private final boolean verifyResolveInfo(ResolveInfo resolveInfo) {
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        return activityInfo != null && n.c(activityInfo.permission, "android.permission.BIND_CONTROLS") && activityInfo.exported && isComponentActuallyEnabled(activityInfo);
    }

    public final ControlsServiceInfo copy() {
        ControlsServiceInfo controlsServiceInfo = new ControlsServiceInfo(this.context, this.serviceInfo);
        controlsServiceInfo.panelActivity = this.panelActivity;
        return controlsServiceInfo;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ControlsServiceInfo) {
            ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) obj;
            if (this.userId == controlsServiceInfo.userId && n.c(this.componentName, controlsServiceInfo.componentName) && n.c(this.panelActivity, controlsServiceInfo.panelActivity)) {
                return true;
            }
        }
        return false;
    }

    public final ComponentName getPanelActivity() {
        return this.panelActivity;
    }

    public final ServiceInfo getServiceInfo() {
        return this.serviceInfo;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.userId), this.componentName, this.panelActivity);
    }

    @Override // miui.systemui.devicecontrols.management.DefaultAppInfo, miui.systemui.devicecontrols.management.CandidateInfo
    @WorkerThread
    public Drawable loadIcon() {
        String packageName;
        try {
            ComponentName componentName = this.componentName;
            if (componentName == null || (packageName = componentName.getPackageName()) == null) {
                PackageItemInfo packageItemInfo = this.packageItemInfo;
                packageName = packageItemInfo != null ? packageItemInfo.packageName : null;
                if (packageName == null) {
                    return null;
                }
            }
            return IconDrawableFactory.newInstance(this.context).getBadgedIcon(this.mPm.getApplicationInfoAsUser(packageName, 0, this.userId));
        } catch (Throwable th) {
            Log.e(TAG, "load icon error", th);
            return null;
        }
    }

    @Override // miui.systemui.devicecontrols.management.DefaultAppInfo, miui.systemui.devicecontrols.management.CandidateInfo
    @WorkerThread
    public CharSequence loadLabel() {
        CharSequence charSequenceLoadLabel;
        try {
            ComponentName componentName = this.componentName;
            if (componentName != null && (charSequenceLoadLabel = this.mPm.getApplicationInfoAsUser(componentName.getPackageName(), 0, this.userId).loadLabel(this.mPm)) != null) {
                return charSequenceLoadLabel;
            }
            PackageItemInfo packageItemInfo = this.packageItemInfo;
            if (packageItemInfo != null) {
                return packageItemInfo.loadLabel(this.mPm);
            }
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "load label error", th);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005e  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void resolvePanelActivity(boolean r6) {
        /*
            r5 = this;
            boolean r0 = r5.resolved
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r5.resolved = r0
            android.content.Context r0 = r5.context
            android.content.res.Resources r0 = r0.getResources()
            int r1 = miui.systemui.devicecontrols.R.array.config_controlsPreferredPackages
            java.lang.String[] r0 = r0.getStringArray(r1)
            kotlin.jvm.internal.n.d(r0)
            android.content.ComponentName r1 = r5.componentName
            java.lang.String r1 = r1.getPackageName()
            boolean r0 = I0.AbstractC0181i.w(r0, r1)
            if (r0 != 0) goto L26
            if (r6 != 0) goto L26
            return
        L26:
            android.content.ComponentName r6 = r5._panelActivity
            r0 = 0
            if (r6 == 0) goto L60
            android.content.pm.PackageManager r1 = r5.mPm
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            android.content.Intent r2 = r2.setComponent(r6)
            int r3 = r5.userId
            android.os.UserHandle r3 = android.os.UserHandle.of(r3)
            r4 = 786432(0xc0000, float:1.102026E-39)
            java.util.List r1 = r1.queryIntentActivitiesAsUser(r2, r4, r3)
            kotlin.jvm.internal.n.d(r1)
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L5e
            r2 = 0
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r2 = "get(...)"
            kotlin.jvm.internal.n.f(r1, r2)
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            boolean r1 = r5.verifyResolveInfo(r1)
            if (r1 == 0) goto L5e
            goto L5f
        L5e:
            r6 = r0
        L5f:
            r0 = r6
        L60:
            r5.panelActivity = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecontrols.ControlsServiceInfo.resolvePanelActivity(boolean):void");
    }

    public final void setPanelActivity(ComponentName componentName) {
        this.panelActivity = componentName;
    }

    public String toString() {
        return g.e("\n            ControlsServiceInfo(serviceInfo=" + this.serviceInfo + ", panelActivity=" + this.panelActivity + ", resolved=" + this.resolved + ")\n        ");
    }
}
