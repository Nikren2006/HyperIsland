package miui.systemui.dynamicisland.window;

import H0.j;
import H0.s;
import I0.K;
import I0.u;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.settings.UserTracker;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.security.SecurityManager;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.window.AppLockController;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class AppLockController {
    private static final Uri ACCESS_CONTROL_LOCK_ENABLED;
    private static final Uri ACCESS_CONTROL_LOCK_MASK;
    public static final Companion Companion = new Companion(null);
    public static final long DELAY = 1000;
    public static final String TAG = "AppLockController";
    private final Executor bgExecutor;
    private final Handler bgHandler;
    private final Context context;
    private volatile boolean lockEnable;
    private final AppLockController$lockStateObserver$1 lockStateObserver;
    private final AppLockProvider provider;
    private final E scope;
    private final SecurityManager securityManager;
    private final AppLockController$userCallback$1 userCallback;
    private final UserTracker userTracker;

    public final class AppLockProvider {
        private final Handler bgHandler;
        private final Context context;
        private final Runnable fetchMaskListRunnable;
        private final ConcurrentHashMap<Integer, Set<String>> maskMap;
        private final AppLockController$AppLockProvider$maskStateObserver$1 maskStateObserver;
        private final H0.d method$delegate;
        private final SecurityManager securityManager;
        final /* synthetic */ AppLockController this$0;
        private final UserTracker userTracker;

        /* JADX WARN: Type inference failed for: r3v4, types: [miui.systemui.dynamicisland.window.AppLockController$AppLockProvider$maskStateObserver$1] */
        public AppLockProvider(final AppLockController appLockController, SecurityManager securityManager, Context context, final Handler bgHandler, UserTracker userTracker) {
            kotlin.jvm.internal.n.g(securityManager, "securityManager");
            kotlin.jvm.internal.n.g(context, "context");
            kotlin.jvm.internal.n.g(bgHandler, "bgHandler");
            kotlin.jvm.internal.n.g(userTracker, "userTracker");
            this.this$0 = appLockController;
            this.securityManager = securityManager;
            this.context = context;
            this.bgHandler = bgHandler;
            this.userTracker = userTracker;
            this.maskMap = new ConcurrentHashMap<>();
            this.method$delegate = H0.e.b(new AppLockController$AppLockProvider$method$2(this));
            this.maskStateObserver = new ContentObserver(bgHandler) { // from class: miui.systemui.dynamicisland.window.AppLockController$AppLockProvider$maskStateObserver$1
                @Override // android.database.ContentObserver
                public void onChange(boolean z2) {
                    if (this.this$0.getMethod() == null || !appLockController.lockEnable) {
                        return;
                    }
                    AppLockController.AppLockProvider.refreshMaskMap$default(this.this$0, false, 1, null);
                }
            };
            this.fetchMaskListRunnable = new Runnable() { // from class: miui.systemui.dynamicisland.window.b
                @Override // java.lang.Runnable
                public final void run() {
                    AppLockController.AppLockProvider.fetchMaskListRunnable$lambda$2(this.f5734a);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void fetchMaskListRunnable$lambda$2(AppLockProvider this$0) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            int userId = this$0.userTracker.getUserId();
            Set<String> maskList = this$0.getMaskList(userId);
            if (maskList != null) {
                this$0.maskMap.put(Integer.valueOf(userId), maskList);
            }
            Log.d(AppLockController.TAG, "refresh mask value = " + maskList + "  id = " + userId);
            if (userId == 0) {
                Set<String> maskList2 = this$0.getMaskList(DynamicIslandConstants.USER_XSPACE);
                if (maskList2 != null) {
                    this$0.maskMap.put(Integer.valueOf(DynamicIslandConstants.USER_XSPACE), maskList2);
                }
                Log.d(AppLockController.TAG, "refresh mask value = " + maskList2 + " id = 999");
            }
        }

        private final Set<String> getMaskList(int i2) {
            Object objA;
            Set setB;
            try {
                j.a aVar = H0.j.f299a;
                Method method = getMethod();
                Object objInvoke = method != null ? method.invoke(this.securityManager, Integer.valueOf(i2)) : null;
                List list = objInvoke instanceof List ? (List) objInvoke : null;
                if (list == null || (setB = u.o0(list)) == null) {
                    setB = K.b();
                }
                objA = H0.j.a(setB);
            } catch (Throwable th) {
                j.a aVar2 = H0.j.f299a;
                objA = H0.j.a(H0.k.a(th));
            }
            return (Set) (H0.j.b(objA) == null ? objA : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Method getMethod() {
            return (Method) this.method$delegate.getValue();
        }

        private final boolean isInLockV1(String str, int i2) {
            Object objA;
            try {
                j.a aVar = H0.j.f299a;
                this.securityManager.getApplicationAccessControlEnabledAsUser(str, i2);
                objA = H0.j.a(Boolean.valueOf(!this.securityManager.checkAccessControlPassAsUser(str, i2)));
            } catch (Throwable th) {
                j.a aVar2 = H0.j.f299a;
                objA = H0.j.a(H0.k.a(th));
            }
            if (H0.j.b(objA) != null) {
                objA = Boolean.FALSE;
            }
            return ((Boolean) objA).booleanValue();
        }

        private final boolean isInLockedV2(String str, int i2) {
            Set<String> set = this.maskMap.get(Integer.valueOf(i2));
            return set != null ? set.contains(str) : isInLockV1(str, i2);
        }

        private final void refreshMaskMap(boolean z2) {
            if (getMethod() == null || this.bgHandler.hasCallbacks(this.fetchMaskListRunnable)) {
                return;
            }
            this.maskMap.clear();
            this.bgHandler.postDelayed(this.fetchMaskListRunnable, z2 ? 0L : 1000L);
        }

        public static /* synthetic */ void refreshMaskMap$default(AppLockProvider appLockProvider, boolean z2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z2 = false;
            }
            appLockProvider.refreshMaskMap(z2);
        }

        public final void clearCache() {
            this.maskMap.clear();
        }

        public final void close() {
            if (getMethod() == null) {
                Log.d(AppLockController.TAG, "start: method is null block close");
            } else {
                this.context.getContentResolver().unregisterContentObserver(this.maskStateObserver);
            }
        }

        public final Handler getBgHandler() {
            return this.bgHandler;
        }

        public final Context getContext() {
            return this.context;
        }

        public final SecurityManager getSecurityManager() {
            return this.securityManager;
        }

        public final UserTracker getUserTracker() {
            return this.userTracker;
        }

        public final boolean isInLocked(String packName, int i2) {
            kotlin.jvm.internal.n.g(packName, "packName");
            return getMethod() == null ? isInLockV1(packName, i2) : isInLockedV2(packName, i2);
        }

        public final void start() {
            if (getMethod() == null) {
                Log.d(AppLockController.TAG, "start: method is null block start");
            } else {
                this.context.getContentResolver().registerContentObserver(AppLockController.Companion.getACCESS_CONTROL_LOCK_MASK(), false, this.maskStateObserver, -1);
                Log.d(AppLockController.TAG, "start: method not null ");
            }
        }

        public final void updateUser() {
            refreshMaskMap(true);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Uri getACCESS_CONTROL_LOCK_ENABLED() {
            return AppLockController.ACCESS_CONTROL_LOCK_ENABLED;
        }

        public final Uri getACCESS_CONTROL_LOCK_MASK() {
            return AppLockController.ACCESS_CONTROL_LOCK_MASK;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.AppLockController$init$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.AppLockController$init$1", f = "AppLockController.kt", l = {69}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return AppLockController.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                AppLockController.this.start();
                AppLockController.this.provider.start();
                this.label = 1;
                if (M.a(this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.AppLockController$init$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            AppLockController.this.close();
            AppLockController.this.provider.close();
        }
    }

    static {
        Uri uriFor = Settings.Secure.getUriFor("access_control_lock_enabled");
        kotlin.jvm.internal.n.f(uriFor, "getUriFor(...)");
        ACCESS_CONTROL_LOCK_ENABLED = uriFor;
        Uri uriFor2 = Settings.Secure.getUriFor("applock_mask_notify");
        kotlin.jvm.internal.n.f(uriFor2, "getUriFor(...)");
        ACCESS_CONTROL_LOCK_MASK = uriFor2;
    }

    /* JADX WARN: Type inference failed for: r7v4, types: [miui.systemui.dynamicisland.window.AppLockController$userCallback$1] */
    public AppLockController(@DynamicIsland E scope, Context context, UserTracker userTracker, @Background Executor bgExecutor, @Background Handler bgHandler) {
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(userTracker, "userTracker");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(bgHandler, "bgHandler");
        this.scope = scope;
        this.context = context;
        this.userTracker = userTracker;
        this.bgExecutor = bgExecutor;
        this.bgHandler = bgHandler;
        Object systemService = context.getSystemService("security");
        kotlin.jvm.internal.n.e(systemService, "null cannot be cast to non-null type miui.security.SecurityManager");
        SecurityManager securityManager = (SecurityManager) systemService;
        this.securityManager = securityManager;
        this.lockStateObserver = new AppLockController$lockStateObserver$1(this, bgHandler);
        this.userCallback = new UserTracker.Callback() { // from class: miui.systemui.dynamicisland.window.AppLockController$userCallback$1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public void onUserChanged(int i2, Context userContext) {
                kotlin.jvm.internal.n.g(userContext, "userContext");
                this.this$0.lockStateObserver.onChange(false);
            }
        };
        this.provider = new AppLockProvider(this, securityManager, context, bgHandler, userTracker);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void close() {
        this.context.getContentResolver().unregisterContentObserver(this.lockStateObserver);
        this.userTracker.removeCallback(this.userCallback);
    }

    private final void registerLockStatus() {
        this.context.getContentResolver().registerContentObserver(ACCESS_CONTROL_LOCK_ENABLED, false, this.lockStateObserver, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void start() {
        this.userTracker.addCallback(this.userCallback, this.bgExecutor);
        registerLockStatus();
        this.bgHandler.post(new Runnable() { // from class: miui.systemui.dynamicisland.window.a
            @Override // java.lang.Runnable
            public final void run() {
                AppLockController.start$lambda$0(this.f5733a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(AppLockController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.lockStateObserver.onChange(false);
    }

    public final void init() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null).l(new AnonymousClass2());
    }

    public final boolean isInLockState(String packName, int i2) {
        kotlin.jvm.internal.n.g(packName, "packName");
        if (this.lockEnable) {
            return this.provider.isInLocked(packName, i2);
        }
        return false;
    }
}
