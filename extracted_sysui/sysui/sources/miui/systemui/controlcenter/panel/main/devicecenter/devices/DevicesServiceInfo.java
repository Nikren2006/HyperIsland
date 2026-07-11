package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.IconDrawableFactory;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.appcompat.content.res.AppCompatResources;
import miui.systemui.controlcenter.R;
import miui.systemui.util.ControlsUtils;

/* JADX INFO: loaded from: classes.dex */
public class DevicesServiceInfo {
    public final ComponentName componentName;
    private final Context mContext;
    protected final PackageManager mPm;
    public final String summary;
    public final int userId;

    public DevicesServiceInfo(Context context, PackageManager packageManager, int i2, ComponentName componentName) {
        this(context, packageManager, i2, componentName, null, true);
    }

    public Boolean isContentTheSame(Object obj) {
        if (!(obj instanceof DevicesServiceInfo)) {
            return Boolean.FALSE;
        }
        DevicesServiceInfo devicesServiceInfo = (DevicesServiceInfo) obj;
        if (this.componentName.equals(devicesServiceInfo.componentName)) {
            return Boolean.valueOf(this.userId == devicesServiceInfo.userId);
        }
        return Boolean.FALSE;
    }

    public Boolean isWrapperTheSame(Object obj) {
        return obj instanceof DevicesServiceInfo ? Boolean.valueOf(this.componentName.equals(((DevicesServiceInfo) obj).componentName)) : Boolean.FALSE;
    }

    @WorkerThread
    public CharSequence loadLabel() {
        ComponentName componentName = this.componentName;
        if (componentName != null) {
            if (TextUtils.equals(componentName.getPackageName(), ControlsUtils.GOOGLE_HOME_PACKAGE)) {
                return this.mContext.getString(R.string.google_home_hub_entry_title);
            }
            try {
                return this.mPm.getApplicationInfo(this.componentName.getPackageName(), 128).loadLabel(this.mPm);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }

    @Nullable
    @WorkerThread
    public Drawable loadNormalIcon() {
        ComponentName componentName = this.componentName;
        if (componentName == null) {
            return null;
        }
        if (TextUtils.equals(componentName.getPackageName(), ControlsUtils.GOOGLE_HOME_PACKAGE)) {
            return AppCompatResources.getDrawable(this.mContext, R.drawable.google_home_hub_icon);
        }
        try {
            IconDrawableFactory iconDrawableFactoryNewInstance = IconDrawableFactory.newInstance(this.mContext);
            ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(this.componentName.getPackageName(), 128);
            return applicationInfo != null ? applicationInfo.loadIcon(this.mPm) : iconDrawableFactoryNewInstance.getBadgedIcon(applicationInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public DevicesServiceInfo(Context context, PackageManager packageManager, int i2, ComponentName componentName, String str, boolean z2) {
        this.mContext = context;
        this.mPm = packageManager;
        this.userId = i2;
        this.componentName = componentName;
        this.summary = str;
    }
}
