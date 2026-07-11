package miui.systemui.devicecontrols.management;

import android.app.AppGlobals;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.IconDrawableFactory;
import androidx.annotation.NonNull;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultAppInfo extends CandidateInfo {
    public final ComponentName componentName;
    private final Context mContext;
    protected final PackageManager mPm;
    public final PackageItemInfo packageItemInfo;
    public final String summary;
    public final int userId;

    public DefaultAppInfo(Context context, PackageManager packageManager, int i2, ComponentName componentName) {
        this(context, packageManager, i2, componentName, (String) null, true);
    }

    private ComponentInfo getComponentInfo() {
        return getComponentInfoAfterT();
    }

    private ComponentInfo getComponentInfoAfterT() {
        try {
            Class<?> cls = AppGlobals.getPackageManager().getClass();
            Class cls2 = Long.TYPE;
            Class cls3 = Integer.TYPE;
            ComponentInfo componentInfo = (ComponentInfo) cls.getMethod("getActivityInfo", ComponentName.class, cls2, cls3).invoke(AppGlobals.getPackageManager(), this.componentName, 0, Integer.valueOf(this.userId));
            return componentInfo == null ? (ComponentInfo) AppGlobals.getPackageManager().getClass().getMethod("getServiceInfo", ComponentName.class, cls2, cls3).invoke(AppGlobals.getPackageManager(), this.componentName, 0, Integer.valueOf(this.userId)) : componentInfo;
        } catch (Throwable unused) {
            return null;
        }
    }

    private ComponentInfo getComponentInfoBeforeT() {
        try {
            Class<?> cls = AppGlobals.getPackageManager().getClass();
            Class cls2 = Integer.TYPE;
            ComponentInfo componentInfo = (ComponentInfo) cls.getMethod("getActivityInfo", ComponentName.class, cls2, cls2).invoke(AppGlobals.getPackageManager(), this.componentName, 0, Integer.valueOf(this.userId));
            return componentInfo == null ? (ComponentInfo) AppGlobals.getPackageManager().getClass().getMethod("getServiceInfo", ComponentName.class, cls2, cls2).invoke(AppGlobals.getPackageManager(), this.componentName, 0, Integer.valueOf(this.userId)) : componentInfo;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // miui.systemui.devicecontrols.management.CandidateInfo
    public String getKey() {
        ComponentName componentName = this.componentName;
        if (componentName != null) {
            return componentName.flattenToString();
        }
        PackageItemInfo packageItemInfo = this.packageItemInfo;
        if (packageItemInfo != null) {
            return packageItemInfo.packageName;
        }
        return null;
    }

    @Override // miui.systemui.devicecontrols.management.CandidateInfo
    public Drawable loadIcon() {
        IconDrawableFactory iconDrawableFactoryNewInstance = IconDrawableFactory.newInstance(this.mContext);
        if (this.componentName != null) {
            try {
                ComponentInfo componentInfo = getComponentInfo();
                ApplicationInfo applicationInfoAsUser = this.mPm.getApplicationInfoAsUser(this.componentName.getPackageName(), 0, this.userId);
                return componentInfo != null ? applicationInfoAsUser.loadIcon(this.mPm) : iconDrawableFactoryNewInstance.getBadgedIcon(applicationInfoAsUser);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        PackageItemInfo packageItemInfo = this.packageItemInfo;
        if (packageItemInfo != null) {
            try {
                return iconDrawableFactoryNewInstance.getBadgedIcon(this.packageItemInfo, this.mPm.getApplicationInfoAsUser(packageItemInfo.packageName, 0, this.userId), this.userId);
            } catch (PackageManager.NameNotFoundException unused2) {
            }
        }
        return null;
    }

    @Override // miui.systemui.devicecontrols.management.CandidateInfo
    public CharSequence loadLabel() {
        if (this.componentName != null) {
            try {
                ComponentInfo componentInfo = getComponentInfo();
                return componentInfo != null ? componentInfo.loadLabel(this.mPm) : this.mPm.getApplicationInfoAsUser(this.componentName.getPackageName(), 0, this.userId).loadLabel(this.mPm);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        PackageItemInfo packageItemInfo = this.packageItemInfo;
        if (packageItemInfo != null) {
            return packageItemInfo.loadLabel(this.mPm);
        }
        return null;
    }

    @Override // miui.systemui.devicecontrols.management.CandidateInfo
    @NonNull
    public Drawable loadNormalIcon() {
        Drawable drawableLoadIcon = null;
        if (this.componentName != null) {
            try {
                ComponentInfo componentInfo = getComponentInfo();
                ApplicationInfo applicationInfoAsUser = this.mPm.getApplicationInfoAsUser(this.componentName.getPackageName(), 0, this.userId);
                if (componentInfo != null) {
                    drawableLoadIcon = componentInfo.loadIcon(this.mPm);
                } else if (applicationInfoAsUser != null) {
                    drawableLoadIcon = applicationInfoAsUser.loadIcon(this.mPm);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return drawableLoadIcon == null ? this.mContext.getDrawable(R.drawable.f5520android) : drawableLoadIcon;
    }

    public DefaultAppInfo(Context context, PackageManager packageManager, int i2, PackageItemInfo packageItemInfo) {
        this(context, packageManager, i2, packageItemInfo, (String) null, true);
    }

    public DefaultAppInfo(Context context, PackageManager packageManager, int i2, ComponentName componentName, String str, boolean z2) {
        super(z2);
        this.mContext = context;
        this.mPm = packageManager;
        this.packageItemInfo = null;
        this.userId = i2;
        this.componentName = componentName;
        this.summary = str;
    }

    public DefaultAppInfo(Context context, PackageManager packageManager, int i2, PackageItemInfo packageItemInfo, String str, boolean z2) {
        super(z2);
        this.mContext = context;
        this.mPm = packageManager;
        this.userId = i2;
        this.packageItemInfo = packageItemInfo;
        this.componentName = null;
        this.summary = str;
    }
}
