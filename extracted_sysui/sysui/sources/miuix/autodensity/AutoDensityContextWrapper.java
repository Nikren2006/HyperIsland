package miuix.autodensity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes3.dex */
public class AutoDensityContextWrapper extends ContextThemeWrapper {
    private Configuration mOriginConfiguration;

    public AutoDensityContextWrapper(Context context, int i2) {
        super(context, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.ContextThemeWrapper] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    public Configuration getNoOverrideConfiguration() {
        ?? r12 = this;
        while (r12.getBaseContext() instanceof ContextThemeWrapper) {
            r12 = (ContextThemeWrapper) r12.getBaseContext();
        }
        return r12.getResources().getConfiguration();
    }

    public Configuration getOriginConfiguration() {
        return this.mOriginConfiguration;
    }

    public void restoreOriginConfig() {
        getResources().getConfiguration().setTo(this.mOriginConfiguration);
        getResources().getDisplayMetrics().density = this.mOriginConfiguration.densityDpi / 160.0f;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Configuration configuration = this.mOriginConfiguration;
        displayMetrics.densityDpi = configuration.densityDpi;
        float f2 = configuration.fontScale;
        DisplayMetrics displayMetrics2 = getResources().getDisplayMetrics();
        float f3 = getResources().getDisplayMetrics().density;
        if (f2 == 0.0f) {
            f2 = 1.0f;
        }
        displayMetrics2.scaledDensity = f3 * f2;
    }

    public void setOriginConfiguration(Configuration configuration) {
        this.mOriginConfiguration = configuration;
    }

    @RequiresApi(api = 23)
    public AutoDensityContextWrapper(Context context, Resources.Theme theme) {
        super(context, theme);
    }
}
