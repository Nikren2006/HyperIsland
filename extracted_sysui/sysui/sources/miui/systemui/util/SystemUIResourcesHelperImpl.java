package miui.systemui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.SystemUI;

/* JADX INFO: loaded from: classes4.dex */
public final class SystemUIResourcesHelperImpl implements SystemUIResourcesHelper {
    public static final Companion Companion = new Companion(null);
    private static final String SYSTEM_UI_PKG = "com.android.systemui";
    private static final String TAG = "SystemUIResourcesHelper";
    private final Context context;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public SystemUIResourcesHelperImpl(@SystemUI Context context) {
        this.context = context;
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    public Boolean getBoolean(String resName) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return Boolean.valueOf(this.context.getResources().getBoolean(context.getResources().getIdentifier(resName, "bool", SYSTEM_UI_PKG)));
        } catch (Throwable th) {
            Log.e(TAG, "get boolean from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    @ColorInt
    public Integer getColor(String resName) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return Integer.valueOf(this.context.getColor(context.getResources().getIdentifier(resName, TypedValues.Custom.S_COLOR, SYSTEM_UI_PKG)));
        } catch (Throwable th) {
            Log.e(TAG, "get color from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    @Dimension
    public Integer getDimensionPixelSize(String resName) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return Integer.valueOf(this.context.getResources().getDimensionPixelSize(context.getResources().getIdentifier(resName, "dimen", SYSTEM_UI_PKG)));
        } catch (Throwable th) {
            Log.e(TAG, "get dimension from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public Drawable getDrawable(String resName) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return this.context.getDrawable(context.getResources().getIdentifier(resName, "drawable", SYSTEM_UI_PKG));
        } catch (Throwable th) {
            Log.e(TAG, "get drawable from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    public Float getFraction(String resName, int i2, int i3) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return Float.valueOf(this.context.getResources().getFraction(context.getResources().getIdentifier(resName, "fraction", SYSTEM_UI_PKG), i2, i3));
        } catch (Throwable th) {
            Log.e(TAG, "get fraction from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    public Integer getInteger(String resName) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return Integer.valueOf(this.context.getResources().getInteger(context.getResources().getIdentifier(resName, TypedValues.Custom.S_INT, SYSTEM_UI_PKG)));
        } catch (Throwable th) {
            Log.e(TAG, "get boolean from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    public int getResId(String type, String resName) {
        kotlin.jvm.internal.n.g(type, "type");
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return context.getResources().getIdentifier(resName, type, SYSTEM_UI_PKG);
        } catch (Throwable th) {
            Log.e(TAG, "get string from systemUI failed", th);
            return 0;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    public String getString(String resName) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return this.context.getString(context.getResources().getIdentifier(resName, TypedValues.Custom.S_STRING, SYSTEM_UI_PKG));
        } catch (Throwable th) {
            Log.e(TAG, "get string from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    public View inflateLayout(String resName) {
        kotlin.jvm.internal.n.g(resName, "resName");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return LayoutInflater.from(this.context).inflate(context.getResources().getIdentifier(resName, "layout", SYSTEM_UI_PKG), (ViewGroup) null);
        } catch (Throwable th) {
            Log.e(TAG, "inflate layout from systemUI failed", th);
            return null;
        }
    }

    @Override // miui.systemui.util.SystemUIResourcesHelper
    public String getString(String resName, Object... formatArgs) {
        kotlin.jvm.internal.n.g(resName, "resName");
        kotlin.jvm.internal.n.g(formatArgs, "formatArgs");
        try {
            Context context = this.context;
            kotlin.jvm.internal.n.d(context);
            return this.context.getString(context.getResources().getIdentifier(resName, TypedValues.Custom.S_STRING, SYSTEM_UI_PKG), Arrays.copyOf(formatArgs, formatArgs.length));
        } catch (Throwable th) {
            Log.e(TAG, "get string from systemUI failed", th);
            return null;
        }
    }
}
