package miui.systemui.notification.auth;

import H0.d;
import H0.e;
import H0.k;
import H0.s;
import M0.c;
import N0.f;
import N0.l;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.xms.auth.a;
import com.xiaomi.xms.auth.b;
import g1.AbstractC0369g;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicFeatureConfig;
import miui.systemui.notification.focus.InflateAndAuthCallBack;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"StaticFieldLeak"})
public final class AuthManager implements IAuthManager {
    private static final String ACTION = "com.xiaomi.xms.auth.BIND_AUTH_SERVICE";
    private static final String KEY_PKG = "package_name";
    private static final String KEY_RESULT_CODE = "result_code";
    private static final String KEY_RESULT_MSG = "result_msg";
    private static final String KEY_SCOPE = "scope";
    private static final String NOTIFICATION_KEY = "notification_key";
    private static final int NO_NETWORK_CODE = -400;
    private static final long RELEASE_SCOPE = 20032;
    private static final String RESULT_CALLING_BUNDLE = "result_auth_params";
    private static long SCOPE = 0;
    private static final int SUCCESS_CODE = 0;
    private static final String TAG = "AuthManager";
    private static final long TEST_SCOPE = 22624;
    private static final String XMSF_PKG = "com.xiaomi.xmsf";
    private static final u _connectionFlow;
    private static com.xiaomi.xms.auth.a authService;
    private static AuthServiceCallback authServiceCallback;
    private static final ServiceConnection connection;
    private static final I connectionFlow;
    private static final IBinder.DeathRecipient mDeathRecipient;
    private static Context sysuiContext;
    public static final AuthManager INSTANCE = new AuthManager();
    private static final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private static boolean isLowVersion = true;
    private static final d uiScope$delegate = e.b(AuthManager$uiScope$2.INSTANCE);

    public static final class AuthServiceCallback extends b.a {
        private final InflateAndAuthCallBack inflateCallBack;

        public AuthServiceCallback(InflateAndAuthCallBack inflateCallBack) {
            n.g(inflateCallBack, "inflateCallBack");
            this.inflateCallBack = inflateCallBack;
        }

        @Override // com.xiaomi.xms.auth.b
        public void onAuthResult(Bundle authBundle) {
            n.g(authBundle, "authBundle");
            AbstractC0369g.b(AuthManager.INSTANCE.getUiScope(), null, null, new AuthManager$AuthServiceCallback$onAuthResult$1(authBundle, this, null), 3, null);
        }
    }

    public static final class LowVersionAuthServiceCallback extends b.a {
        private final InflateAndAuthCallBack inflateCallBack;
        private final String key;
        private final String packageName;

        public LowVersionAuthServiceCallback(String str, String str2, InflateAndAuthCallBack inflateCallBack) {
            n.g(inflateCallBack, "inflateCallBack");
            this.key = str;
            this.packageName = str2;
            this.inflateCallBack = inflateCallBack;
        }

        @Override // com.xiaomi.xms.auth.b
        public void onAuthResult(Bundle authBundle) {
            n.g(authBundle, "authBundle");
            AbstractC0369g.b(AuthManager.INSTANCE.getUiScope(), null, null, new AuthManager$LowVersionAuthServiceCallback$onAuthResult$1(authBundle, this, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.notification.auth.AuthManager$auth$1, reason: invalid class name */
    @f(c = "miui.systemui.notification.auth.AuthManager$auth$1", f = "AuthManager.kt", l = {119}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Context $context;
        final /* synthetic */ InflateAndAuthCallBack $inflateCallBack;
        final /* synthetic */ String $key;
        final /* synthetic */ String $packageName;
        int label;

        /* JADX INFO: renamed from: miui.systemui.notification.auth.AuthManager$auth$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.notification.auth.AuthManager$auth$1$1", f = "AuthManager.kt", l = {}, m = "invokeSuspend")
        public static final class C01491 extends l implements Function2 {
            /* synthetic */ boolean Z$0;
            int label;

            public C01491(L0.d dVar) {
                super(2, dVar);
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                C01491 c01491 = new C01491(dVar);
                c01491.Z$0 = ((Boolean) obj).booleanValue();
                return c01491;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke(((Boolean) obj).booleanValue(), (L0.d) obj2);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                return N0.b.a(this.Z$0);
            }

            public final Object invoke(boolean z2, L0.d dVar) {
                return ((C01491) create(Boolean.valueOf(z2), dVar)).invokeSuspend(s.f314a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Context context, String str, String str2, InflateAndAuthCallBack inflateAndAuthCallBack, L0.d dVar) {
            super(2, dVar);
            this.$context = context;
            this.$key = str;
            this.$packageName = str2;
            this.$inflateCallBack = inflateAndAuthCallBack;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass1(this.$context, this.$key, this.$packageName, this.$inflateCallBack, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I connectionFlow = AuthManager.INSTANCE.getConnectionFlow();
                C01491 c01491 = new C01491(null);
                this.label = 1;
                if (AbstractC0420h.t(connectionFlow, c01491, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            AuthManager.INSTANCE.auth(this.$context, this.$key, this.$packageName, this.$inflateCallBack);
            return s.f314a;
        }
    }

    static {
        u uVarA = K.a(Boolean.FALSE);
        _connectionFlow = uVarA;
        connectionFlow = uVarA;
        SCOPE = DynamicFeatureConfig.INSTANCE.getISLAND_XMS_RELEASE() ? RELEASE_SCOPE : TEST_SCOPE;
        connection = new ServiceConnection() { // from class: miui.systemui.notification.auth.AuthManager$connection$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                n.g(name, "name");
                n.g(service, "service");
                Log.d("AuthManager", "onServiceConnected");
                AuthManager authManager = AuthManager.INSTANCE;
                AuthManager.authService = a.AbstractBinderC0076a.Z0(service);
                AuthManager._connectionFlow.setValue(Boolean.TRUE);
                try {
                    service.linkToDeath(AuthManager.mDeathRecipient, 0);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                n.g(name, "name");
                Log.d("AuthManager", "onServiceDisconnected");
                AuthManager.authService = null;
                AuthManager._connectionFlow.setValue(Boolean.FALSE);
            }
        };
        mDeathRecipient = new AuthManager$mDeathRecipient$1();
    }

    private AuthManager() {
    }

    private final boolean checkStatusAvailable(String str, Context context) {
        IBinder iBinderAsBinder;
        com.xiaomi.xms.auth.a aVar = authService;
        if (aVar != null && (aVar == null || (iBinderAsBinder = aVar.asBinder()) == null || !iBinderAsBinder.isBinderAlive())) {
            Log.e(TAG, "remote binder dead");
            return false;
        }
        if (!isConnected()) {
            connect(context);
            Log.e(TAG, "Auth service is unavailable");
            return false;
        }
        if (str != null) {
            return true;
        }
        Log.e(TAG, "Sync auth packageName are unavailable");
        return false;
    }

    private final boolean getAuthStatus(Bundle bundle) {
        if (bundle == null) {
            Log.d(TAG, "Auth  result bundle is null");
            return false;
        }
        int i2 = bundle.getInt("result_code");
        Log.d(TAG, "Auth  result code: " + i2);
        if (i2 == 0) {
            Log.d(TAG, "Auth success.");
            return true;
        }
        Log.d(TAG, "Auth error message: " + bundle.getString(KEY_RESULT_MSG));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final E getUiScope() {
        return (E) uiScope$delegate.getValue();
    }

    private final void postAuthResult(String str, String str2, Boolean bool, InflateAndAuthCallBack inflateAndAuthCallBack) {
        if (str == null || str2 == null) {
            Log.d(TAG, "postAuthResult: flow is null, xms failed");
        } else if (n.c(bool, Boolean.TRUE)) {
            inflateAndAuthCallBack.onAuthSuccess(str, str2);
        } else {
            inflateAndAuthCallBack.onAuthFailed(str, str2);
        }
    }

    private final Bundle requestAuth(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_SCOPE, SCOPE);
        bundle.putString("package_name", str);
        com.xiaomi.xms.auth.a aVar = authService;
        n.d(aVar);
        Bundle bundleJ0 = aVar.j0(bundle);
        n.f(bundleJ0, "syncAuth(...)");
        return bundleJ0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle syncAuthTimeout$lambda$0(String str) {
        return INSTANCE.requestAuth(str);
    }

    @Override // miui.systemui.notification.auth.IAuthManager
    public void auth(Context context, String str, String str2, InflateAndAuthCallBack inflateCallBack) {
        n.g(context, "context");
        n.g(inflateCallBack, "inflateCallBack");
        Log.i(TAG, "request auth: " + str2 + ", isLowVersion: " + isLowVersion);
        if (!checkStatusAvailable(str2, context)) {
            if (isConnected()) {
                postAuthResult(str, str2, Boolean.FALSE, inflateCallBack);
                return;
            } else {
                AbstractC0369g.b(getUiScope(), null, null, new AnonymousClass1(context, str, str2, inflateCallBack, null), 3, null);
                return;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_SCOPE, SCOPE);
        bundle.putString("package_name", str2);
        bundle.putString(NOTIFICATION_KEY, str);
        try {
            if (isLowVersion) {
                com.xiaomi.xms.auth.a aVar = authService;
                if (aVar != null) {
                    aVar.U0(bundle, new LowVersionAuthServiceCallback(str, str2, inflateCallBack));
                    return;
                }
                return;
            }
            if (authServiceCallback == null) {
                authServiceCallback = new AuthServiceCallback(inflateCallBack);
            }
            com.xiaomi.xms.auth.a aVar2 = authService;
            if (aVar2 != null) {
                aVar2.U0(bundle, authServiceCallback);
            }
        } catch (Exception e2) {
            Log.e(TAG, "Auth error", e2);
            postAuthResult(str, str2, Boolean.FALSE, inflateCallBack);
        }
    }

    @Override // miui.systemui.notification.auth.IAuthManager
    public void connect(Context context) {
        n.g(context, "context");
        try {
            sysuiContext = context;
            Intent intent = new Intent(ACTION);
            intent.setPackage("com.xiaomi.xmsf");
            context.bindService(intent, connection, 1);
        } catch (Exception e2) {
            Log.e(TAG, "call bindService error", e2);
        }
    }

    @Override // miui.systemui.notification.auth.IAuthManager
    public void disConnect(Context context) {
        n.g(context, "context");
        if (isConnected()) {
            context.unbindService(connection);
            authService = null;
            _connectionFlow.setValue(Boolean.FALSE);
        }
    }

    public final I getConnectionFlow() {
        return connectionFlow;
    }

    @Override // miui.systemui.notification.auth.IAuthManager
    public boolean isConnected() {
        return authService != null;
    }

    @Override // miui.systemui.notification.auth.IAuthManager
    public boolean syncAuth(Context context, String str) {
        n.g(context, "context");
        if (checkStatusAvailable(str, context)) {
            return getAuthStatus(requestAuth(str));
        }
        return false;
    }

    @Override // miui.systemui.notification.auth.IAuthManager
    public boolean syncAuthTimeout(Context context, final String str, long j2) {
        Bundle bundle;
        n.g(context, "context");
        if (!checkStatusAvailable(str, context)) {
            return false;
        }
        CompletableFuture completableFutureSupplyAsync = CompletableFuture.supplyAsync(new Supplier() { // from class: miui.systemui.notification.auth.a
            @Override // java.util.function.Supplier
            public final Object get() {
                return AuthManager.syncAuthTimeout$lambda$0(str);
            }
        });
        try {
            bundle = j2 > 0 ? (Bundle) completableFutureSupplyAsync.get(j2, TimeUnit.MILLISECONDS) : (Bundle) completableFutureSupplyAsync.get();
        } catch (TimeoutException unused) {
            Log.e(TAG, "Sync auth time > " + j2);
            bundle = null;
        } catch (Exception e2) {
            Log.e(TAG, "Sync auth error", e2);
            bundle = null;
        }
        completableFutureSupplyAsync.cancel(true);
        return getAuthStatus(bundle);
    }

    private final void getAuthStatus(String str, Bundle bundle, IAuthResultCallback iAuthResultCallback) {
        if (bundle == null) {
            Log.d(TAG, "Auth  result bundle is null");
            iAuthResultCallback.onAuthResult(false);
            return;
        }
        int i2 = bundle.getInt("result_code");
        Log.d(TAG, str + " auth result code: " + i2);
        if (i2 == NO_NETWORK_CODE || i2 == 0) {
            Log.d(TAG, "Auth success.");
            iAuthResultCallback.onAuthResult(true);
            return;
        }
        Log.d(TAG, "Auth error message: " + bundle.getString(KEY_RESULT_MSG));
        iAuthResultCallback.onAuthResult(false);
    }
}
