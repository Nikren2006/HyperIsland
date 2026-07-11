package miui.systemui.autodensity;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.annotation.SuppressLint;
import android.app.ActivityThread;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.ThemeUtils;
import miui.systemui.widget.SmoothRoundDrawable;
import miuix.autodensity.AutoDensityConfig;
import miuix.autodensity.DensityConfig;
import miuix.autodensity.DensityConfigManager;
import miuix.autodensity.DensityUtil;

/* JADX INFO: loaded from: classes2.dex */
public final class AutoDensityControllerImpl implements AutoDensityController {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_SCREEN_ZOOM_LEVEL = "key_screen_zoom_level";
    private static final int SCREEN_ZOOM_NORMAL = 1;
    private static final String TAG = "AutoDensityController";
    private ActivityThread activityThread;
    private ArrayList<Application> applicationList;
    private final AutoDensityApplication autoDensityApplication;
    private final AutoDensityControllerImpl$displayListener$1 displayListener;
    private DisplayManager displayManager;
    private final DummyApplication dummyApplication;
    private final CopyOnWriteArrayList<AutoDensityController.OnDensityChangeListener> listeners;
    private final Context pluginContext;
    private Object resManager;
    private final E scope;
    private final Optional<Context> sysuiContext;
    private final Handler uiHandler;

    /* JADX INFO: renamed from: miui.systemui.autodensity.AutoDensityControllerImpl$1, reason: invalid class name */
    @f(c = "miui.systemui.autodensity.AutoDensityControllerImpl$1", f = "AutoDensityControllerImpl.kt", l = {76}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return AutoDensityControllerImpl.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                AutoDensityController.Companion.setImpl(AutoDensityControllerImpl.this);
                AutoDensityControllerImpl.this.onCreate();
                this.label = 1;
                if (M.a(this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.autodensity.AutoDensityControllerImpl$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            AutoDensityControllerImpl.this.onDestroy();
            AutoDensityController.Companion.setImpl(null);
        }
    }

    public final class AutoDensityApplication extends Application {
        public AutoDensityApplication() {
        }

        @Override // android.app.Application, android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration newConfig) {
            n.g(newConfig, "newConfig");
            int i2 = AutoDensityControllerImpl.this.pluginContext.getResources().getConfiguration().densityDpi;
            AutoDensityControllerImpl.this.updateAutoDensity();
            SmoothRoundDrawable.Companion.clearCache();
            int i3 = AutoDensityControllerImpl.this.pluginContext.getResources().getConfiguration().densityDpi;
            DensityConfig originConfig = DensityConfigManager.getInstance().getOriginConfig();
            Integer numValueOf = originConfig != null ? Integer.valueOf(originConfig.densityDpi) : null;
            DensityConfig targetConfig = DensityConfigManager.getInstance().getTargetConfig();
            Log.d(AutoDensityControllerImpl.TAG, "custom density updated context dpi from " + i2 + " to " + i3 + " origin dpi:" + numValueOf + " target dpi:" + (targetConfig != null ? Integer.valueOf(targetConfig.densityDpi) : null));
            super.onConfigurationChanged(newConfig);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final class DummyApplication extends Application {
        public DummyApplication() {
        }

        private final boolean boundsNotMatch(Rect rect, Rect rect2) {
            if (rect == null || rect2 == null || rect.width() == rect2.width()) {
                return false;
            }
            Log.e(AutoDensityControllerImpl.TAG, "bounds width not match new " + rect + " != old " + rect2);
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x00f7  */
        @Override // android.app.Application, android.content.ComponentCallbacks
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onConfigurationChanged(android.content.res.Configuration r7) throws java.lang.IllegalAccessException {
            /*
                Method dump skipped, instruction units count: 326
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.autodensity.AutoDensityControllerImpl.DummyApplication.onConfigurationChanged(android.content.res.Configuration):void");
        }
    }

    /* JADX WARN: Type inference failed for: r7v4, types: [miui.systemui.autodensity.AutoDensityControllerImpl$displayListener$1] */
    public AutoDensityControllerImpl(@Plugin Context pluginContext, @SystemUI Optional<Context> sysuiContext, @Main Handler uiHandler, @Plugin E scope) {
        n.g(pluginContext, "pluginContext");
        n.g(sysuiContext, "sysuiContext");
        n.g(uiHandler, "uiHandler");
        n.g(scope, "scope");
        this.pluginContext = pluginContext;
        this.sysuiContext = sysuiContext;
        this.uiHandler = uiHandler;
        this.scope = scope;
        this.autoDensityApplication = new AutoDensityApplication();
        this.dummyApplication = new DummyApplication();
        this.listeners = new CopyOnWriteArrayList<>();
        this.displayListener = new DisplayManager.DisplayListener() { // from class: miui.systemui.autodensity.AutoDensityControllerImpl$displayListener$1
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int i2) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int i2) {
                int i3 = this.this$0.pluginContext.getResources().getConfiguration().densityDpi;
                DensityConfig originConfig = DensityConfigManager.getInstance().getOriginConfig();
                Integer numValueOf = originConfig != null ? Integer.valueOf(originConfig.densityDpi) : null;
                DensityConfig targetConfig = DensityConfigManager.getInstance().getTargetConfig();
                Log.v("AutoDensityController", "on display changed " + i2 + " context dpi:" + i3 + " origin dpi:" + numValueOf + " target dpi:" + (targetConfig != null ? Integer.valueOf(targetConfig.densityDpi) : null));
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int i2) {
            }
        };
        AbstractC0369g.b(scope, null, null, new AnonymousClass1(null), 3, null).l(new AnonymousClass2());
    }

    private final int getForcedDensity(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "display_density_forced");
        if (TextUtils.isEmpty(string)) {
            return context.getResources().getConfiguration().densityDpi;
        }
        n.d(string);
        return Integer.parseInt(string);
    }

    private final float getZoomLevel(Context context) {
        return Settings.System.getInt(context.getContentResolver(), KEY_SCREEN_ZOOM_LEVEL, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"SoonBlockedPrivateApi", "DiscouragedPrivateApi"})
    public final void onCreate() throws IllegalAccessException {
        ThemeUtils themeUtils = ThemeUtils.INSTANCE;
        themeUtils.fixResourcesPackage(this.pluginContext);
        themeUtils.updateDefaultPluginTheme();
        DensityConfigManager.getInstance().init(this.pluginContext);
        AutoDensityConfig.setUpdateSystemRes(false);
        DensityUtil.updateCustomDensity(this.pluginContext);
        Object systemService = this.pluginContext.getSystemService("display");
        n.e(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        DisplayManager displayManager = (DisplayManager) systemService;
        this.displayManager = displayManager;
        ArrayList<Application> arrayList = null;
        if (displayManager == null) {
            n.w("displayManager");
            displayManager = null;
        }
        displayManager.registerDisplayListener(this.displayListener, this.uiHandler);
        ActivityThread activityThreadCurrentActivityThread = ActivityThread.currentActivityThread();
        n.f(activityThreadCurrentActivityThread, "currentActivityThread(...)");
        this.activityThread = activityThreadCurrentActivityThread;
        try {
            Field declaredField = ActivityThread.class.getDeclaredField("mResourcesManager");
            declaredField.setAccessible(true);
            ActivityThread activityThread = this.activityThread;
            if (activityThread == null) {
                n.w("activityThread");
                activityThread = null;
            }
            Object obj = declaredField.get(activityThread);
            n.f(obj, "get(...)");
            this.resManager = obj;
            Field declaredField2 = ActivityThread.class.getDeclaredField("mAllApplications");
            declaredField2.setAccessible(true);
            ActivityThread activityThread2 = this.activityThread;
            if (activityThread2 == null) {
                n.w("activityThread");
                activityThread2 = null;
            }
            Object obj2 = declaredField2.get(activityThread2);
            n.e(obj2, "null cannot be cast to non-null type java.util.ArrayList<android.app.Application>{ kotlin.collections.TypeAliasesKt.ArrayList<android.app.Application> }");
            this.applicationList = (ArrayList) obj2;
            Object obj3 = this.resManager;
            if (obj3 == null) {
                n.w("resManager");
                obj3 = s.f314a;
            }
            synchronized (obj3) {
                try {
                    ArrayList<Application> arrayList2 = this.applicationList;
                    if (arrayList2 == null) {
                        n.w("applicationList");
                        arrayList2 = null;
                    }
                    arrayList2.add(0, this.autoDensityApplication);
                    ArrayList<Application> arrayList3 = this.applicationList;
                    if (arrayList3 == null) {
                        n.w("applicationList");
                    } else {
                        arrayList = arrayList3;
                    }
                    arrayList.add(this.dummyApplication);
                } finally {
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "register dummy application failed.", e2);
        }
        Log.d(TAG, "created.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDestroy() {
        this.listeners.clear();
        DisplayManager displayManager = this.displayManager;
        ArrayList<Application> arrayList = null;
        if (displayManager == null) {
            n.w("displayManager");
            displayManager = null;
        }
        displayManager.unregisterDisplayListener(this.displayListener);
        try {
            Object obj = this.resManager;
            if (obj == null) {
                n.w("resManager");
                obj = s.f314a;
            }
            synchronized (obj) {
                try {
                    ArrayList<Application> arrayList2 = this.applicationList;
                    if (arrayList2 == null) {
                        n.w("applicationList");
                        arrayList2 = null;
                    }
                    arrayList2.remove(this.dummyApplication);
                    ArrayList<Application> arrayList3 = this.applicationList;
                    if (arrayList3 == null) {
                        n.w("applicationList");
                    } else {
                        arrayList = arrayList3;
                    }
                    arrayList.remove(this.autoDensityApplication);
                } finally {
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "unregister dummy application failed.", e2);
        }
        Log.d(TAG, "destroyed.");
    }

    private static final Context replacePluginContextResources$getBaseContextRoot(Context context) {
        Context baseContext;
        Context contextReplacePluginContextResources$getBaseContextRoot;
        ContextWrapper contextWrapper = context instanceof ContextWrapper ? (ContextWrapper) context : null;
        return (contextWrapper == null || (baseContext = contextWrapper.getBaseContext()) == null || (contextReplacePluginContextResources$getBaseContextRoot = replacePluginContextResources$getBaseContextRoot(baseContext)) == null) ? context : contextReplacePluginContextResources$getBaseContextRoot;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAutoDensity() {
        DensityConfigManager densityConfigManager = DensityConfigManager.getInstance();
        Context context = this.pluginContext;
        Optional<Context> optional = this.sysuiContext;
        densityConfigManager.tryUpdateConfig(context, (optional.isPresent() ? optional.get() : this.pluginContext).getResources().getConfiguration());
        DensityUtil.updateCustomDensity(this.pluginContext);
    }

    private final void updateDpiInfo(int i2, Context context) {
        Log.d(TAG, "updateDpiInfo");
        boolean z2 = i2 == 0;
        DensityConfigManager.getInstance().setUserAccessibilityDpiDelta(z2 ? 0.0f : getZoomLevel(context));
        DensityConfigManager.getInstance().setUserForcedDpi(z2 ? 0 : getForcedDensity(context));
    }

    @Override // miui.systemui.autodensity.AutoDensityController
    public void addOnDensityChangeListener(AutoDensityController.OnDensityChangeListener listener) {
        n.g(listener, "listener");
        this.listeners.add(listener);
    }

    @Override // miui.systemui.autodensity.AutoDensityController
    public void onUserChanged(int i2, Context userContext) {
        n.g(userContext, "userContext");
        updateDpiInfo(i2, userContext);
    }

    @Override // miui.systemui.autodensity.AutoDensityController
    public void removeOnDensityChangeListener(AutoDensityController.OnDensityChangeListener listener) {
        n.g(listener, "listener");
        this.listeners.remove(listener);
    }

    @SuppressLint({"DiscouragedPrivateApi"})
    public final void replacePluginContextResources(Context context) {
        n.g(context, "context");
        if (context.getResources() == this.pluginContext.getResources()) {
            return;
        }
        try {
            Field declaredField = Class.forName("android.app.ContextImpl").getDeclaredField("mResources");
            declaredField.setAccessible(true);
            declaredField.set(replacePluginContextResources$getBaseContextRoot(context), this.pluginContext.getResources());
        } catch (Throwable unused) {
            Log.e(TAG, "replace context resources failed.");
        }
    }
}
