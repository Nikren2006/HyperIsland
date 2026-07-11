package miui.systemui.notification.focus;

import H0.d;
import H0.k;
import H0.s;
import M0.c;
import N0.f;
import N0.l;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.NotificationListenerController;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandData;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import com.android.systemui.plugins.miui.notification.NotificationDynamicIslandPlugin;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.M;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dynamicisland.DynamicFeatureConfig;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewCreator;
import miui.systemui.notification.LottieProgressManager;
import miui.systemui.notification.NotificationChronometerManager;
import miui.systemui.notification.NotificationSettingsManager;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.auth.AuthManager;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.templateV3.TemplateBuilderV3;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ConvenienceExtensionsKt;
import miui.systemui.util.concurrency.ConcurrencyModule;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotificationController {
    private boolean addCallback;
    private Map<String, Boolean> authResult;
    private Map<String, NotificationDynamicIslandPlugin.FocusInflationCallback> callbackMap;
    private final Context context;
    private DynamicIslandContent dynamicIslandContent;
    private final FocusNotifPreHandler focusNotifPreHandler;
    private final FocusNotifUtils focusNotifUtils;
    private final HideDeletedFocusController hideDeletedFocusController;
    private final E immediateUiScope;
    private final FocusNotificationController$inflateCallBack$1 inflateCallBack;
    private Map<String, Boolean> inflateResult;
    private boolean isFlipDevice;
    private Map<String, DynamicIslandData> islandShowingMap;
    private final ArrayList<String> islandTimeoutRemovedList;
    private final E0.a lottieProgressManager;
    private NotificationListenerController.NotificationProvider mProvider;
    private final Handler mainHandler;
    private final d notificationChronometerManager$delegate;
    private Map<String, FocusNotificationContent> notificationMap;
    private final NotificationSettingsManager notificationSettingsManager;
    private Map<String, StatusBarNotification> sbnMap;
    private final E scope;
    private final SignatureChecker signatureChecker;
    private Context sysuiCtx;
    private final E0.a windowViewCreator;

    /* JADX INFO: renamed from: miui.systemui.notification.focus.FocusNotificationController$register$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.notification.focus.FocusNotificationController$register$1", f = "FocusNotificationController.kt", l = {126}, m = "invokeSuspend")
    public static final class C06721 extends l implements Function2 {
        final /* synthetic */ FocusNotificationController$register$observer$1 $observer;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06721(FocusNotificationController$register$observer$1 focusNotificationController$register$observer$1, L0.d dVar) {
            super(2, dVar);
            this.$observer = focusNotificationController$register$observer$1;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return FocusNotificationController.this.new C06721(this.$observer, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06721) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                FocusNotificationController.this.context.getContentResolver().registerContentObserver(Const.Provider.URI_CAN_SHOW_FOCUS, false, this.$observer, -1);
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

    /* JADX INFO: renamed from: miui.systemui.notification.focus.FocusNotificationController$register$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        final /* synthetic */ ContentResolver $contentResolver;
        final /* synthetic */ FocusNotificationController$register$observer$1 $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ContentResolver contentResolver, FocusNotificationController$register$observer$1 focusNotificationController$register$observer$1) {
            super(1);
            this.$contentResolver = contentResolver;
            this.$observer = focusNotificationController$register$observer$1;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            this.$contentResolver.unregisterContentObserver(this.$observer);
        }
    }

    /* JADX WARN: Type inference failed for: r2v13, types: [miui.systemui.notification.focus.FocusNotificationController$inflateCallBack$1] */
    public FocusNotificationController(@Plugin E scope, Context context, Handler mainHandler, E0.a windowViewCreator, HideDeletedFocusController hideDeletedFocusController, E0.a notificationChronometerManager, NotificationSettingsManager notificationSettingsManager, FocusNotifPreHandler focusNotifPreHandler, E0.a lottieProgressManager, FocusNotifUtils focusNotifUtils, SignatureChecker signatureChecker) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(mainHandler, "mainHandler");
        n.g(windowViewCreator, "windowViewCreator");
        n.g(hideDeletedFocusController, "hideDeletedFocusController");
        n.g(notificationChronometerManager, "notificationChronometerManager");
        n.g(notificationSettingsManager, "notificationSettingsManager");
        n.g(focusNotifPreHandler, "focusNotifPreHandler");
        n.g(lottieProgressManager, "lottieProgressManager");
        n.g(focusNotifUtils, "focusNotifUtils");
        n.g(signatureChecker, "signatureChecker");
        this.scope = scope;
        this.context = context;
        this.mainHandler = mainHandler;
        this.windowViewCreator = windowViewCreator;
        this.hideDeletedFocusController = hideDeletedFocusController;
        this.notificationSettingsManager = notificationSettingsManager;
        this.focusNotifPreHandler = focusNotifPreHandler;
        this.lottieProgressManager = lottieProgressManager;
        this.focusNotifUtils = focusNotifUtils;
        this.signatureChecker = signatureChecker;
        this.notificationChronometerManager$delegate = ConvenienceExtensionsKt.getKotlinLazy(notificationChronometerManager);
        this.notificationMap = new LinkedHashMap();
        this.islandShowingMap = new LinkedHashMap();
        this.islandTimeoutRemovedList = new ArrayList<>();
        this.sbnMap = new LinkedHashMap();
        this.callbackMap = new LinkedHashMap();
        this.immediateUiScope = F.g(ConcurrencyModule.INSTANCE.getUiScope(), Dispatchers.INSTANCE.getMain().z());
        this.inflateResult = new LinkedHashMap();
        this.authResult = new LinkedHashMap();
        this.isFlipDevice = CommonUtils.isFlipDevice();
        register();
        this.inflateCallBack = new InflateAndAuthCallBack() { // from class: miui.systemui.notification.focus.FocusNotificationController$inflateCallBack$1
            @Override // miui.systemui.notification.focus.InflateAndAuthCallBack
            public void onAuthFailed(String key, String packageName) {
                n.g(key, "key");
                n.g(packageName, "packageName");
                AbstractC0369g.b(this.this$0.scope, null, null, new FocusNotificationController$inflateCallBack$1$onAuthFailed$1(key, packageName, this.this$0, null), 3, null);
            }

            @Override // miui.systemui.notification.focus.InflateAndAuthCallBack
            public void onAuthFinish(String key, String packageName) {
                n.g(key, "key");
                n.g(packageName, "packageName");
                Log.e(Const.TAG, "onAuthFinish " + key + " " + packageName);
            }

            @Override // miui.systemui.notification.focus.InflateAndAuthCallBack
            public void onAuthSuccess(String key, String packageName) {
                n.g(key, "key");
                n.g(packageName, "packageName");
                AbstractC0369g.b(this.this$0.scope, null, null, new FocusNotificationController$inflateCallBack$1$onAuthSuccess$1(key, packageName, this.this$0, null), 3, null);
            }

            @Override // miui.systemui.notification.focus.InflateAndAuthCallBack
            public void onInflateFailed(String key) {
                n.g(key, "key");
                AbstractC0369g.b(this.this$0.scope, null, null, new FocusNotificationController$inflateCallBack$1$onInflateFailed$1(key, this.this$0, null), 3, null);
            }

            @Override // miui.systemui.notification.focus.InflateAndAuthCallBack
            public void onInflateFinish(String key) {
                n.g(key, "key");
                Log.e(Const.TAG, "onInflateFinish " + key);
                this.this$0.onInflateEnd(key);
            }

            @Override // miui.systemui.notification.focus.InflateAndAuthCallBack
            public void onInflateSuccess(String key) {
                n.g(key, "key");
                AbstractC0369g.b(this.this$0.scope, null, null, new FocusNotificationController$inflateCallBack$1$onInflateSuccess$1(key, this.this$0, null), 3, null);
            }
        };
    }

    public static final /* synthetic */ Map access$getInflateResult$p(FocusNotificationController focusNotificationController) {
        return focusNotificationController.inflateResult;
    }

    public static final /* synthetic */ void access$inflateFinishCallback(FocusNotificationController focusNotificationController, String str) {
        focusNotificationController.inflateFinishCallback(str);
    }

    private final void addDynamicIslandView(FocusNotificationContent focusNotificationContent, StatusBarNotification statusBarNotification) {
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandWindowViewController windowViewController2;
        String tickerData;
        boolean z2 = statusBarNotification.getNotification().extras.getBoolean(Const.Param.IS_FOCUS_LAYOUT);
        boolean zSuppressDeletedFocus = this.hideDeletedFocusController.suppressDeletedFocus(statusBarNotification);
        boolean zCanShowFocus = this.focusNotifUtils.canShowFocus(this.context, statusBarNotification.getPackageName(), statusBarNotification.getUserId());
        View islandExpandedView = focusNotificationContent != null ? focusNotificationContent.getIslandExpandedView() : null;
        StringBuilder sb = new StringBuilder();
        sb.append("addDynamicIslandView  ");
        sb.append(islandExpandedView);
        sb.append(", (");
        sb.append(z2);
        sb.append(" && ");
        sb.append(zSuppressDeletedFocus);
        sb.append(") || ");
        sb.append(!zCanShowFocus);
        Log.d(Const.TAG, sb.toString());
        if (!(z2 && zSuppressDeletedFocus) && zCanShowFocus) {
            DynamicIslandData dynamicIslandData = new DynamicIslandData();
            dynamicIslandData.setKey(statusBarNotification.getKey());
            dynamicIslandData.setView(focusNotificationContent != null ? focusNotificationContent.getIslandExpandedView() : null);
            dynamicIslandData.setFakeView(focusNotificationContent != null ? focusNotificationContent.getIslandExpandedViewFake() : null);
            dynamicIslandData.setTickerData(parseIslandData(statusBarNotification, focusNotificationContent));
            if (dynamicIslandData.getView() == null && ((tickerData = dynamicIslandData.getTickerData()) == null || tickerData.length() == 0)) {
                String key = statusBarNotification.getKey();
                n.f(key, "getKey(...)");
                removeByKey(key);
                return;
            }
            dynamicIslandData.setExtras(setUpDynamicIslandDataBundle(statusBarNotification));
            Map<String, DynamicIslandData> map = this.islandShowingMap;
            String key2 = statusBarNotification.getKey();
            n.f(key2, "getKey(...)");
            map.put(key2, dynamicIslandData);
            if (((DynamicIslandWindowViewCreator) this.windowViewCreator.get()).getWindowView() == null) {
                this.dynamicIslandContent = ((DynamicIslandWindowViewCreator) this.windowViewCreator.get()).createView();
            }
            boolean z3 = statusBarNotification.getNotification().extras.getBoolean(Const.Param.EXTRA_ISLAND_FIRST_FLOAT, true) && !statusBarNotification.getNotification().extras.getBoolean(Const.Param.EXTRA_ISLAND_UPDATE_NO_FLOAT, false);
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_ISLAND_UPDATE_NO_FLOAT, false);
            DynamicIslandWindowView windowView2 = ((DynamicIslandWindowViewCreator) this.windowViewCreator.get()).getWindowView();
            if (windowView2 != null && (windowViewController2 = windowView2.getWindowViewController()) != null) {
                windowViewController2.addDynamicIslandView(dynamicIslandData, z3);
            }
            if (this.addCallback) {
                return;
            }
            this.addCallback = true;
            DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator = (DynamicIslandWindowViewCreator) this.windowViewCreator.get();
            if (dynamicIslandWindowViewCreator == null || (windowView = dynamicIslandWindowViewCreator.getWindowView()) == null || (windowViewController = windowView.getWindowViewController()) == null) {
                return;
            }
            windowViewController.addDynamicIslandTimeoutRemovedCallback(new DynamicIslandWindowViewController.DynamicIslandCallback() { // from class: miui.systemui.notification.focus.FocusNotificationController.addDynamicIslandView.1
                @Override // miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.DynamicIslandCallback
                public void onDynamicIslandConfigChange() {
                    String key3;
                    Notification notification;
                    Bundle bundle;
                    HashMap map2 = new HashMap(FocusNotificationController.this.islandShowingMap);
                    FocusNotificationController focusNotificationController = FocusNotificationController.this;
                    for (Map.Entry entry : map2.entrySet()) {
                        if (!focusNotificationController.islandTimeoutRemovedList.contains(entry.getKey())) {
                            FocusNotificationContent focusNotificationContent2 = (FocusNotificationContent) focusNotificationController.notificationMap.get(entry.getKey());
                            StatusBarNotification sbn = focusNotificationContent2 != null ? focusNotificationContent2.getSbn() : null;
                            if (sbn != null && (notification = sbn.getNotification()) != null && (bundle = notification.extras) != null) {
                                bundle.putBoolean(Const.Param.EXTRA_ISLAND_UPDATE_NO_FLOAT, true);
                            }
                            NotificationDynamicIslandPlugin.FocusInflationCallback focusInflationCallback = (NotificationDynamicIslandPlugin.FocusInflationCallback) focusNotificationController.callbackMap.get(entry.getKey());
                            focusNotificationController.getNotificationChronometerManager().saveTempTimeKeeperStatus(sbn);
                            FocusNotificationController.onNotificationRemoved$default(focusNotificationController, sbn, false, 2, null);
                            if (sbn != null && (key3 = sbn.getKey()) != null) {
                                n.d(key3);
                                focusNotificationController.focusNotifUtils.getMaxSeq().remove(key3);
                            }
                            if (focusInflationCallback != null) {
                                Map map3 = focusNotificationController.callbackMap;
                                Object key4 = entry.getKey();
                                n.f(key4, "<get-key>(...)");
                            }
                            if (sbn != null) {
                                Context context = focusNotificationController.context;
                                Context context2 = focusNotificationController.sysuiCtx;
                                n.d(context2);
                                NotificationListenerController.NotificationProvider notificationProvider = focusNotificationController.mProvider;
                                n.d(notificationProvider);
                                focusNotificationController.onNotificationPosted(sbn, context, context2, notificationProvider);
                            }
                        }
                    }
                }

                @Override // miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.DynamicIslandCallback
                public void onDynamicIslandTimeoutRemoved(String key3) {
                    n.g(key3, "key");
                    boolean z4 = FocusNotificationController.this.notificationMap.get(key3) != null;
                    Log.e(Const.TAG, "onDynamicIslandTimeoutRemoved, key=" + key3 + ", hasFocusNotification=" + z4);
                    if (z4) {
                        FocusNotificationController.this.islandTimeoutRemovedList.add(key3);
                    } else {
                        FocusNotificationController.this.removeByKey(key3);
                    }
                }

                @Override // miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.DynamicIslandCallback
                public void removeNotification(StatusBarNotification sbn) {
                    n.g(sbn, "sbn");
                    NotificationListenerController.NotificationProvider notificationProvider = FocusNotificationController.this.mProvider;
                    if (notificationProvider != null) {
                        notificationProvider.removeNotification(sbn);
                    }
                }
            });
        }
    }

    private final void fetchAuthResult(Context context, StatusBarNotification statusBarNotification, String str, Bundle bundle, InflateAndAuthCallBack inflateAndAuthCallBack) {
        if (DynamicFeatureConfig.INSTANCE.getISLAND_XMS_SWITCHER() || this.notificationSettingsManager.canPassXMSPermission(str)) {
            Log.e(Const.TAG, "pass xms permission " + str);
            String key = statusBarNotification.getKey();
            n.f(key, "getKey(...)");
            inflateAndAuthCallBack.onAuthSuccess(key, str);
            return;
        }
        if (NotificationUtil.IS_INTERNATIONAL_BUILD) {
            Log.i(Const.TAG, "is international build");
            String key2 = statusBarNotification.getKey();
            n.f(key2, "getKey(...)");
            inflateAndAuthCallBack.onAuthSuccess(key2, str);
            return;
        }
        if (!this.focusNotifUtils.hasCustomFocusView(statusBarNotification)) {
            if (!this.signatureChecker.checkSignatures(str)) {
                AuthManager.INSTANCE.auth(context, statusBarNotification.getKey(), str, inflateAndAuthCallBack);
                return;
            }
            String key3 = statusBarNotification.getKey();
            n.f(key3, "getKey(...)");
            inflateAndAuthCallBack.onAuthSuccess(key3, str);
            return;
        }
        if (this.notificationSettingsManager.canCustomFocus(statusBarNotification.getPackageName())) {
            String key4 = statusBarNotification.getKey();
            n.f(key4, "getKey(...)");
            inflateAndAuthCallBack.onAuthSuccess(key4, str);
        } else {
            this.focusNotifUtils.resetAllParam(bundle);
            String key5 = statusBarNotification.getKey();
            n.f(key5, "getKey(...)");
            inflateAndAuthCallBack.onAuthFailed(key5, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationChronometerManager getNotificationChronometerManager() {
        return (NotificationChronometerManager) this.notificationChronometerManager$delegate.getValue();
    }

    private final boolean hasIslandData(StatusBarNotification statusBarNotification) {
        Bundle bundle;
        String string;
        if (statusBarNotification == null) {
            return false;
        }
        try {
            Notification notification = statusBarNotification.getNotification();
            JSONObject jSONObject = (notification == null || (bundle = notification.extras) == null || (string = bundle.getString("miui.focus.param")) == null) ? null : new JSONObject(string);
            JSONObject jSONObjectOptJSONObject = jSONObject != null ? jSONObject.optJSONObject("param_v2") : null;
            if (jSONObjectOptJSONObject == null && this.focusNotifUtils.hasCustomFocusView(statusBarNotification)) {
                String string2 = statusBarNotification.getNotification().extras.getString("miui.focus.param.custom");
                jSONObject = string2 != null ? new JSONObject(string2) : null;
            }
            if (jSONObjectOptJSONObject == null) {
                jSONObjectOptJSONObject = jSONObject;
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optJSONObject("param_island") : null;
            if (jSONObjectOptJSONObject2 == null) {
                return false;
            }
            String string3 = jSONObjectOptJSONObject2.toString();
            n.f(string3, "toString(...)");
            return string3.length() > 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void inflateFinishCallback(String str) {
        NotificationDynamicIslandPlugin.FocusInflationCallback focusInflationCallback;
        Notification notification;
        Bundle bundle;
        if (shouldSkipInflateAuth(str)) {
            return;
        }
        FocusNotificationContent focusNotificationContent = this.notificationMap.get(str);
        Log.d(TemplateBuilderV3.DEBUG_TAG, "inflateFinishCallback: " + (focusNotificationContent != null ? focusNotificationContent.getFocusNotification() : null) + "  " + (focusNotificationContent != null ? focusNotificationContent.getKey() : null));
        if (n.c(this.authResult.get(str), Boolean.FALSE)) {
            StatusBarNotification statusBarNotification = this.sbnMap.get(str);
            if (statusBarNotification != null && (notification = statusBarNotification.getNotification()) != null && (bundle = notification.extras) != null) {
                this.focusNotifUtils.resetAllParam(bundle);
            }
            if (focusNotificationContent != null) {
                focusNotificationContent.reset();
                NotificationDynamicIslandPlugin.FocusInflationCallback focusInflationCallback2 = this.callbackMap.get(str);
                if (focusInflationCallback2 != null) {
                    focusInflationCallback2.onFocusInflationFinished(focusNotificationContent);
                    return;
                }
                return;
            }
        }
        if ((focusNotificationContent != null ? focusNotificationContent.getFocusNotification() : null) == null) {
            if ((focusNotificationContent != null ? focusNotificationContent.getIslandExpandedView() : null) == null && !hasIslandData(this.sbnMap.get(str))) {
                removeByKey(str);
                return;
            }
        }
        if (focusNotificationContent != null && (focusInflationCallback = this.callbackMap.get(str)) != null) {
            focusInflationCallback.onFocusInflationFinished(focusNotificationContent);
        }
        if (!this.islandShowingMap.containsKey(str)) {
            StatusBarNotification statusBarNotification2 = this.sbnMap.get(str);
            if (statusBarNotification2 != null) {
                addDynamicIslandView(focusNotificationContent, statusBarNotification2);
                return;
            }
            return;
        }
        Log.e(Const.TAG, "updateDynamicIslandView");
        StatusBarNotification statusBarNotification3 = this.sbnMap.get(str);
        if (statusBarNotification3 != null) {
            updateDynamicIslandView(focusNotificationContent, statusBarNotification3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onInflateEnd(String str) {
        StatusBarNotification statusBarNotification;
        Log.d(Const.TAG, "inflation Ended");
        FocusNotificationContent focusNotificationContent = this.notificationMap.get(str);
        if (focusNotificationContent == null || focusNotificationContent.getFocusNotification() == null || (statusBarNotification = this.sbnMap.get(str)) == null) {
            return;
        }
        getNotificationChronometerManager().updateTimeKeeper(statusBarNotification, focusNotificationContent);
        ((LottieProgressManager) this.lottieProgressManager.get()).addLottie(str, focusNotificationContent, R.id.focus_animation);
    }

    public static /* synthetic */ boolean onNotificationRemoved$default(FocusNotificationController focusNotificationController, StatusBarNotification statusBarNotification, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        return focusNotificationController.onNotificationRemoved(statusBarNotification, z2);
    }

    private final String parseCustomIslandData(StatusBarNotification statusBarNotification) throws JSONException {
        Log.d(Const.TAG, "parseCustomIslandData");
        String string = statusBarNotification.getNotification().extras.getString("miui.focus.param.custom");
        JSONObject jSONObject = string != null ? new JSONObject(string) : null;
        if (jSONObject != null && jSONObject.has("param_island")) {
            return String.valueOf(jSONObject != null ? jSONObject.getJSONObject("param_island") : null);
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("islandProperty", 1);
        jSONObject2.put("islandPriority", 1);
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("picInfo", new JSONObject());
        jSONObject4.put("type", 1);
        jSONObject3.put("imageTextInfoLeft", jSONObject4);
        jSONObject2.put("bigIslandArea", jSONObject3);
        String string2 = jSONObject2.toString();
        n.f(string2, "toString(...)");
        return string2;
    }

    private final String parseIslandData(StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent) {
        try {
            if (this.focusNotifUtils.hasCustomFocusView(statusBarNotification)) {
                return parseCustomIslandData(statusBarNotification);
            }
            String string = statusBarNotification.getNotification().extras.getString("miui.focus.param");
            JSONObject jSONObject = string != null ? new JSONObject(string) : null;
            if (jSONObject != null) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("param_v2");
                return jSONObjectOptJSONObject != null ? parseOS2DefaultIslandData(jSONObjectOptJSONObject, statusBarNotification) : parseOS1DefaultIslandData(jSONObject, statusBarNotification);
            }
            if (focusNotificationContent != null) {
                focusNotificationContent.reset();
            }
            String key = statusBarNotification.getKey();
            n.f(key, "getKey(...)");
            removeByKey(key);
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            if (focusNotificationContent != null) {
                focusNotificationContent.reset();
            }
            String key2 = statusBarNotification.getKey();
            n.f(key2, "getKey(...)");
            removeByKey(key2);
            return "";
        }
    }

    private final String parseOS1DefaultIslandData(JSONObject jSONObject, StatusBarNotification statusBarNotification) throws JSONException {
        Log.d(Const.TAG, "parseOS1DefaultIslandData");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("param_island");
        if (jSONObjectOptJSONObject != null) {
            String string = jSONObjectOptJSONObject.toString();
            n.d(string);
            return string;
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("islandProperty", 1);
        jSONObject2.put("islandPriority", 1);
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("pic", statusBarNotification.getNotification().extras.getString(Const.Param.TICKER_PIC));
        jSONObject5.put("type", 1);
        jSONObject4.put("picInfo", jSONObject5);
        jSONObject4.put("type", 1);
        jSONObject3.put("imageTextInfoLeft", jSONObject4);
        if (jSONObject.has("timerType") && jSONObject.has("timerWhen")) {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("timerType", jSONObject.optInt("timerType"));
            jSONObject6.put("timerWhen", jSONObject.optLong("timerWhen"));
            jSONObject6.put("timerTotal", jSONObject.optLong("timerTotal"));
            jSONObject6.put("timerSystemCurrent", jSONObject.optLong("timerSystemCurrent"));
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("timerInfo", jSONObject6);
            jSONObject3.put("sameWidthDigitInfo", jSONObject7);
        } else {
            JSONObject jSONObject8 = new JSONObject();
            JSONObject jSONObject9 = new JSONObject();
            String strOptString = jSONObject.optString(Const.Param.PARAM_TICKER);
            if (TextUtils.isEmpty(strOptString)) {
                strOptString = jSONObject.optString("title");
            }
            jSONObject9.put("title", strOptString);
            jSONObject8.put("textInfo", jSONObject9);
            jSONObject8.put("type", 2);
            jSONObject3.put("imageTextInfoRight", jSONObject8);
        }
        jSONObject2.put("bigIslandArea", jSONObject3);
        String string2 = jSONObject2.toString();
        n.d(string2);
        return string2;
    }

    private final String parseOS2DefaultIslandData(JSONObject jSONObject, StatusBarNotification statusBarNotification) throws JSONException {
        Log.d(Const.TAG, "parseOS2DefaultIslandData");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("param_island");
        if (jSONObjectOptJSONObject != null) {
            String string = jSONObjectOptJSONObject.toString();
            n.d(string);
            return string;
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("type", 1);
        jSONObject5.put("pic", statusBarNotification.getNotification().extras.getString(Const.Param.TICKER_PIC));
        jSONObject4.put("picInfo", jSONObject5);
        jSONObject4.put("type", 1);
        jSONObject3.put("imageTextInfoLeft", jSONObject4);
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("highlightInfo");
        JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optJSONObject("timerInfo") : null;
        if (jSONObjectOptJSONObject3 != null) {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("timerInfo", jSONObjectOptJSONObject3);
            jSONObject3.put("sameWidthDigitInfo", jSONObject6);
        } else {
            JSONObject jSONObject7 = new JSONObject();
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("title", statusBarNotification.getNotification().extras.getString(Const.Param.TICKER));
            jSONObject7.put("textInfo", jSONObject8);
            jSONObject7.put("type", 2);
            jSONObject3.put("imageTextInfoRight", jSONObject7);
        }
        jSONObject2.put("islandProperty", 1);
        jSONObject2.put("islandPriority", 1);
        jSONObject2.put("bigIslandArea", jSONObject3);
        String string2 = jSONObject2.toString();
        n.d(string2);
        return string2;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [miui.systemui.notification.focus.FocusNotificationController$register$observer$1] */
    private final void register() {
        ContentResolver contentResolver = this.context.getContentResolver();
        final Handler handler = this.mainHandler;
        ?? r2 = new ContentObserver(handler) { // from class: miui.systemui.notification.focus.FocusNotificationController$register$observer$1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2, Uri uri) {
                n.g(uri, "uri");
                try {
                    String queryParameter = uri.getQueryParameter("package");
                    boolean z3 = Boolean.parseBoolean(uri.getQueryParameter("canShowFocus"));
                    Log.e(Const.TAG, "mCanShowFocusObserver onChange pkg=" + queryParameter + ", canShowFocus=" + z3);
                    if (z3) {
                        return;
                    }
                    this.this$0.removeByPkg(queryParameter);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
        AbstractC0369g.b(this.scope, null, null, new C06721(r2, null), 3, null).l(new AnonymousClass2(contentResolver, r2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeByKey(String str) {
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        Notification notification;
        Bundle bundle;
        Log.i(Const.TAG, "removeByKey " + str);
        if (this.notificationMap.containsKey(str)) {
            this.notificationMap.remove(str);
        }
        if (this.islandShowingMap.containsKey(str)) {
            this.islandShowingMap.remove(str);
            DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator = (DynamicIslandWindowViewCreator) this.windowViewCreator.get();
            if ((dynamicIslandWindowViewCreator != null ? dynamicIslandWindowViewCreator.getWindowView() : null) != null) {
                StatusBarNotification statusBarNotification = this.sbnMap.get(str);
                boolean z2 = false;
                if (statusBarNotification != null && (notification = statusBarNotification.getNotification()) != null && (bundle = notification.extras) != null) {
                    z2 = bundle.getBoolean(Const.Param.EXTRA_ISLAND_UPDATE_NO_FLOAT, false);
                }
                DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator2 = (DynamicIslandWindowViewCreator) this.windowViewCreator.get();
                if (dynamicIslandWindowViewCreator2 != null && (windowView = dynamicIslandWindowViewCreator2.getWindowView()) != null && (windowViewController = windowView.getWindowViewController()) != null) {
                    windowViewController.removeDynamicIslandView(str, z2);
                }
            }
        }
        if (this.sbnMap.containsKey(str)) {
            this.sbnMap.remove(str);
            getNotificationChronometerManager().removeTimeKeeper(str);
            ((LottieProgressManager) this.lottieProgressManager.get()).removeLottie(str);
        }
        if (this.callbackMap.containsKey(str)) {
            this.callbackMap.remove(str);
        }
        if (this.inflateResult.containsKey(str)) {
            this.inflateResult.remove(str);
        }
        if (this.authResult.containsKey(str)) {
            this.authResult.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeByPkg(String str) {
        Collection<FocusNotificationContent> collectionValues = this.notificationMap.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            StatusBarNotification sbn = ((FocusNotificationContent) obj).getSbn();
            if (n.c(sbn != null ? sbn.getPackageName() : null, str)) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String key = ((FocusNotificationContent) it.next()).getKey();
            if (key != null) {
                removeByKey(key);
            }
        }
    }

    private final Bundle setUpDynamicIslandDataBundle(StatusBarNotification statusBarNotification) {
        Bundle bundle = new Bundle();
        bundle.putString("miui.pkg.name", NotificationUtil.getSbnTargetPkg(this.sysuiCtx, statusBarNotification));
        bundle.putInt("miui.user.id", statusBarNotification.getUser().getIdentifier());
        bundle.putParcelable("miui.sbn", statusBarNotification);
        bundle.putString("miui.effect.src", statusBarNotification.getNotification().extras.getString("miui.effect.src"));
        bundle.putString("miui.effect.color", statusBarNotification.getNotification().extras.getString("miui.effect.color"));
        bundle.putBundle("miui.focus.pics", statusBarNotification.getNotification().extras.getBundle("miui.focus.pics"));
        bundle.putInt("miui.focus.lottieView.id", statusBarNotification.getNotification().extras.getInt("miui.focus.lottieView.id"));
        bundle.putInt("miui.focus.lottieView.fake.id", statusBarNotification.getNotification().extras.getInt("miui.focus.lottieView.fake.id"));
        bundle.putBoolean("miui.focus.lottieView.isPlay", statusBarNotification.getNotification().extras.getBoolean("miui.focus.lottieView.isPlay"));
        if (statusBarNotification.getNotification().extras.containsKey("miui.exitFloating")) {
            Parcelable parcelable = statusBarNotification.getNotification().extras.getParcelable("miui.exitFloating");
            if (parcelable != null && (parcelable instanceof PendingIntent)) {
                bundle.putParcelable("miui.exitFloating", parcelable);
            }
        } else {
            bundle.putParcelable("miui.exitFloating", statusBarNotification.getNotification().extraNotification.getExitFloatingIntent());
        }
        return bundle;
    }

    private final boolean shouldSkipInflateAuth(String str) {
        Log.d(Const.TAG, "inflateFinishCallback " + str + " " + this.authResult + " " + this.inflateResult);
        return this.authResult.get(str) == null || this.inflateResult.get(str) == null || n.c(this.inflateResult.get(str), Boolean.FALSE);
    }

    private final void updateDynamicIslandView(FocusNotificationContent focusNotificationContent, StatusBarNotification statusBarNotification) {
        DynamicIslandWindowViewController windowViewController;
        View islandExpandedView = focusNotificationContent != null ? focusNotificationContent.getIslandExpandedView() : null;
        Log.e(Const.TAG, "updateDynamicIslandView: islandExpandedView" + islandExpandedView + " , key: " + statusBarNotification.getKey());
        if (statusBarNotification.getNotification().extras.getBoolean(Const.Param.IS_FOCUS_LAYOUT) && this.hideDeletedFocusController.suppressDeletedFocus(statusBarNotification)) {
            return;
        }
        this.islandTimeoutRemovedList.remove(statusBarNotification.getKey());
        DynamicIslandData dynamicIslandData = new DynamicIslandData();
        dynamicIslandData.setKey(focusNotificationContent != null ? focusNotificationContent.getKey() : null);
        dynamicIslandData.setView(focusNotificationContent != null ? focusNotificationContent.getIslandExpandedView() : null);
        dynamicIslandData.setFakeView(focusNotificationContent != null ? focusNotificationContent.getIslandExpandedViewFake() : null);
        dynamicIslandData.setTickerData(parseIslandData(statusBarNotification, focusNotificationContent));
        dynamicIslandData.setExtras(setUpDynamicIslandDataBundle(statusBarNotification));
        boolean z2 = statusBarNotification.getNotification().extras.getBoolean(Const.Param.EXTRA_ENABLE_FLOAT, false) && !statusBarNotification.getNotification().extras.getBoolean(Const.Param.EXTRA_ISLAND_UPDATE_NO_FLOAT, false);
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.EXTRA_ISLAND_UPDATE_NO_FLOAT, false);
        DynamicIslandWindowView windowView = ((DynamicIslandWindowViewCreator) this.windowViewCreator.get()).getWindowView();
        if (windowView == null || (windowViewController = windowView.getWindowViewController()) == null) {
            return;
        }
        windowViewController.updateDynamicIslandView(dynamicIslandData, z2);
    }

    public final void getFocusNotificationContent(StatusBarNotification sbn, NotificationDynamicIslandPlugin.FocusInflationCallback focusInflationCallback) {
        n.g(sbn, "sbn");
        n.g(focusInflationCallback, "focusInflationCallback");
        Log.d(Const.TAG, "getFocusNotificationContent inflateFinish value: " + this.inflateResult + "  authResult:" + this.authResult);
        Map<String, NotificationDynamicIslandPlugin.FocusInflationCallback> map = this.callbackMap;
        String key = sbn.getKey();
        n.f(key, "getKey(...)");
        map.put(key, focusInflationCallback);
        String key2 = sbn.getKey();
        n.f(key2, "getKey(...)");
        inflateFinishCallback(key2);
    }

    public final boolean needExtendLifetime(String key) {
        n.g(key, "key");
        DynamicIslandWindowView windowView = ((DynamicIslandWindowViewCreator) this.windowViewCreator.get()).getWindowView();
        if (windowView != null) {
            return windowView.needExtendLifetime(key);
        }
        return false;
    }

    public final boolean onNotificationPosted(StatusBarNotification sbn, Context pluginCtx, Context sysuiCtx, NotificationListenerController.NotificationProvider mProvider) {
        FocusNotificationContent focusNotificationContent;
        DynamicIslandWindowView windowView;
        DynamicIslandWindowViewController windowViewController;
        Bundle bundle;
        n.g(sbn, "sbn");
        n.g(pluginCtx, "pluginCtx");
        n.g(sysuiCtx, "sysuiCtx");
        n.g(mProvider, "mProvider");
        this.sysuiCtx = sysuiCtx;
        this.mProvider = mProvider;
        String key = sbn.getKey();
        String sbnTargetPkg = NotificationUtil.getSbnTargetPkg(sysuiCtx, sbn);
        NotificationUtil.debugLog(Const.TAG, "plugin onNotificationPosted: " + key + " " + sbnTargetPkg);
        Bundle bundle2 = sbn.getNotification().extras;
        if (!this.focusNotifUtils.hasCustomFocusView(sbn) && !bundle2.containsKey("miui.focus.param")) {
            boolean zIsMediaNotification = sbn.getNotification().isMediaNotification();
            NotificationUtil.debugLog(Const.TAG, key + " is not focus notification, isMedia: " + zIsMediaNotification);
            StatusBarNotification statusBarNotification = this.sbnMap.get(key);
            if (statusBarNotification != null) {
                FocusNotifUtils focusNotifUtils = this.focusNotifUtils;
                n.d(bundle2);
                focusNotifUtils.resetAllParam(bundle2);
                FocusNotificationContent focusNotificationContent2 = this.notificationMap.get(key);
                if (focusNotificationContent2 != null) {
                    focusNotificationContent2.reset();
                    this.notificationMap.remove(key);
                }
                if (!zIsMediaNotification) {
                    DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator = (DynamicIslandWindowViewCreator) this.windowViewCreator.get();
                    if ((dynamicIslandWindowViewCreator != null ? dynamicIslandWindowViewCreator.getWindowView() : null) != null) {
                        Notification notification = statusBarNotification.getNotification();
                        boolean z2 = (notification == null || (bundle = notification.extras) == null) ? false : bundle.getBoolean(Const.Param.EXTRA_ISLAND_UPDATE_NO_FLOAT, false);
                        DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator2 = (DynamicIslandWindowViewCreator) this.windowViewCreator.get();
                        if (dynamicIslandWindowViewCreator2 != null && (windowView = dynamicIslandWindowViewCreator2.getWindowView()) != null && (windowViewController = windowView.getWindowViewController()) != null) {
                            n.d(key);
                            windowViewController.removeDynamicIslandView(key, z2);
                        }
                    }
                }
            }
            return false;
        }
        if (this.notificationMap.containsKey(key)) {
            FocusNotificationContent focusNotificationContent3 = this.notificationMap.get(key);
            if (focusNotificationContent3 != null) {
                focusNotificationContent3.setSbn(sbn);
            }
            focusNotificationContent = focusNotificationContent3;
        } else {
            FocusNotificationContentImpl focusNotificationContentImpl = new FocusNotificationContentImpl();
            focusNotificationContentImpl.setKey(key);
            focusNotificationContentImpl.setSbn(sbn);
            Map<String, FocusNotificationContent> map = this.notificationMap;
            n.d(key);
            map.put(key, focusNotificationContentImpl);
            focusNotificationContent = focusNotificationContentImpl;
        }
        Map<String, StatusBarNotification> map2 = this.sbnMap;
        String key2 = sbn.getKey();
        n.f(key2, "getKey(...)");
        map2.put(key2, sbn);
        boolean zOnNotificationPosted = this.focusNotifPreHandler.onNotificationPosted(sbn, this.isFlipDevice, pluginCtx, sysuiCtx, focusNotificationContent, this.inflateCallBack, mProvider);
        n.d(sbnTargetPkg);
        n.d(bundle2);
        fetchAuthResult(sysuiCtx, sbn, sbnTargetPkg, bundle2, this.inflateCallBack);
        if ((sbn.getNotification().extras.getBoolean(Const.Param.IS_FOCUS_LAYOUT) && this.hideDeletedFocusController.suppressDeletedFocus(sbn)) || !sbn.getNotification().extras.getBoolean(Const.Param.SHOW_NOTIFICATION, true)) {
            return true;
        }
        Log.e(Const.TAG, "onNotificationPosted " + this.inflateResult + " " + sbnTargetPkg);
        return zOnNotificationPosted;
    }

    public final boolean onNotificationRemoved(StatusBarNotification statusBarNotification, boolean z2) {
        Notification notification;
        Bundle bundle;
        String key;
        FocusNotificationContent focusNotificationContent;
        String key2 = statusBarNotification != null ? statusBarNotification.getKey() : null;
        Log.i(Const.TAG, "onNotificationRemoved " + key2 + ", content: " + this.notificationMap.get(statusBarNotification != null ? statusBarNotification.getKey() : null));
        if (statusBarNotification != null && (key = statusBarNotification.getKey()) != null && (focusNotificationContent = this.notificationMap.get(key)) != null) {
            this.focusNotifPreHandler.onNotificationRemoved(statusBarNotification, focusNotificationContent);
            this.focusNotifUtils.onNotificationRemoved(statusBarNotification);
        }
        Integer numValueOf = (statusBarNotification == null || (notification = statusBarNotification.getNotification()) == null || (bundle = notification.extras) == null) ? null : Integer.valueOf(bundle.getInt(Const.Param.REMOVE_REASON));
        String key3 = statusBarNotification != null ? statusBarNotification.getKey() : null;
        if (key3 == null) {
            key3 = "";
        }
        removeByKey(key3);
        if (z2) {
            D.a(this.islandTimeoutRemovedList).remove(statusBarNotification != null ? statusBarNotification.getKey() : null);
        }
        this.hideDeletedFocusController.onNotificationRemoved(statusBarNotification, numValueOf);
        return false;
    }

    public final void removeExtendLifetime(String key) {
        n.g(key, "key");
        DynamicIslandWindowView windowView = ((DynamicIslandWindowViewCreator) this.windowViewCreator.get()).getWindowView();
        if (windowView != null) {
            windowView.removeExtendLifetime(key);
        }
    }
}
