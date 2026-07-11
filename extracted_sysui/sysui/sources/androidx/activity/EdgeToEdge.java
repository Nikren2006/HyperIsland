package androidx.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import androidx.activity.SystemBarStyle;
import androidx.annotation.VisibleForTesting;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class EdgeToEdge {
    private static EdgeToEdgeImpl Impl;
    private static final int DefaultLightScrim = Color.argb(230, 255, 255, 255);
    private static final int DefaultDarkScrim = Color.argb(128, 27, 27, 27);

    public static final void enable(ComponentActivity componentActivity) {
        n.g(componentActivity, "<this>");
        enable$default(componentActivity, null, null, 3, null);
    }

    public static /* synthetic */ void enable$default(ComponentActivity componentActivity, SystemBarStyle systemBarStyle, SystemBarStyle systemBarStyle2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            systemBarStyle = SystemBarStyle.Companion.auto$default(SystemBarStyle.Companion, 0, 0, null, 4, null);
        }
        if ((i2 & 2) != 0) {
            systemBarStyle2 = SystemBarStyle.Companion.auto$default(SystemBarStyle.Companion, DefaultLightScrim, DefaultDarkScrim, null, 4, null);
        }
        enable(componentActivity, systemBarStyle, systemBarStyle2);
    }

    public static final int getDefaultDarkScrim() {
        return DefaultDarkScrim;
    }

    @VisibleForTesting
    public static /* synthetic */ void getDefaultDarkScrim$annotations() {
    }

    public static final int getDefaultLightScrim() {
        return DefaultLightScrim;
    }

    @VisibleForTesting
    public static /* synthetic */ void getDefaultLightScrim$annotations() {
    }

    public static final void enable(ComponentActivity componentActivity, SystemBarStyle statusBarStyle) {
        n.g(componentActivity, "<this>");
        n.g(statusBarStyle, "statusBarStyle");
        enable$default(componentActivity, statusBarStyle, null, 2, null);
    }

    public static final void enable(ComponentActivity componentActivity, SystemBarStyle statusBarStyle, SystemBarStyle navigationBarStyle) {
        n.g(componentActivity, "<this>");
        n.g(statusBarStyle, "statusBarStyle");
        n.g(navigationBarStyle, "navigationBarStyle");
        View decorView = componentActivity.getWindow().getDecorView();
        n.f(decorView, "window.decorView");
        Function1 detectDarkMode$activity_release = statusBarStyle.getDetectDarkMode$activity_release();
        Resources resources = decorView.getResources();
        n.f(resources, "view.resources");
        boolean zBooleanValue = ((Boolean) detectDarkMode$activity_release.invoke(resources)).booleanValue();
        Function1 detectDarkMode$activity_release2 = navigationBarStyle.getDetectDarkMode$activity_release();
        Resources resources2 = decorView.getResources();
        n.f(resources2, "view.resources");
        boolean zBooleanValue2 = ((Boolean) detectDarkMode$activity_release2.invoke(resources2)).booleanValue();
        EdgeToEdgeImpl edgeToEdgeApi29 = Impl;
        if (edgeToEdgeApi29 == null) {
            edgeToEdgeApi29 = new EdgeToEdgeApi29();
        }
        Window window = componentActivity.getWindow();
        n.f(window, "window");
        edgeToEdgeApi29.setUp(statusBarStyle, navigationBarStyle, window, decorView, zBooleanValue, zBooleanValue2);
    }
}
