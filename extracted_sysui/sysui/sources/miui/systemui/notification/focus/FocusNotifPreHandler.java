package miui.systemui.notification.focus;

import android.animation.Animator;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.NotificationListenerController;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import d.AbstractC0315p;
import d.C0307h;
import d.H;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.notification.NotificationSettingsManager;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt;
import miui.systemui.notification.focus.template.FocusTemplate;
import miui.systemui.notification.focus.template.FocusTemplateKt;
import miui.systemui.notification.focus.templateV3.TemplateFactoryV3;
import miui.systemui.util.LottieResUtils;
import miui.systemui.util.ReflectUtil;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class FocusNotifPreHandler {
    private static final String TAG = "FocusNotifPreHandler";
    private final ActivityStarter mActivityStarter;
    private final ControlCenterExpandRepository mCenterExpandRepository;
    private final FocusNotifUtils mFocusNotifUtils;
    private NotificationSettingsManager mNotificationSettingsManager;
    private final TemplateFactoryV3 mTemplateFactoryV3;
    private Context pluginContext;

    public interface ClickHandler {
        boolean handleClick();
    }

    public FocusNotifPreHandler(NotificationSettingsManager notificationSettingsManager, Context context, ActivityStarter activityStarter, TemplateFactoryV3 templateFactoryV3, FocusNotifUtils focusNotifUtils, ControlCenterExpandRepository controlCenterExpandRepository) {
        this.mNotificationSettingsManager = notificationSettingsManager;
        this.pluginContext = context;
        this.mActivityStarter = activityStarter;
        this.mTemplateFactoryV3 = templateFactoryV3;
        this.mFocusNotifUtils = focusNotifUtils;
        this.mCenterExpandRepository = controlCenterExpandRepository;
    }

    private void addFaceRecognitionLottie(Context context, StatusBarNotification statusBarNotification, View view, int i2, String str, NotificationListenerController.NotificationProvider notificationProvider) {
        ViewGroup viewGroup;
        if (view == null || !(view.findViewById(i2) instanceof ViewGroup) || (viewGroup = (ViewGroup) view.findViewById(i2)) == null) {
            return;
        }
        viewGroup.removeAllViews();
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.focus_notification_module_face_recognition, (ViewGroup) null);
        Resources resources = context.getResources();
        int i3 = R.dimen.focus_notify_face_recognition_width;
        viewGroup.addView(viewInflate, new ViewGroup.LayoutParams(resources.getDimensionPixelSize(i3), context.getResources().getDimensionPixelSize(i3)));
        playAnimation(context, statusBarNotification, str, (LottieAnimationView) viewInflate.findViewById(R.id.focus_animation), notificationProvider);
    }

    private View applyInteractionHandler(RemoteViews remoteViews, Context context, ViewGroup viewGroup) {
        try {
            return (View) ReflectUtil.callObjectMethod(remoteViews, View.class, "apply", new Class[]{Context.class, ViewGroup.class, RemoteViews.InteractionHandler.class}, context, viewGroup, createInteractionHandler(RemoteViews.InteractionHandler.class));
        } catch (Exception unused) {
            Log.e(TAG, "error get method RemoteViews.apply");
            return null;
        }
    }

    private boolean buildNoParamsFocusNotification(StatusBarNotification statusBarNotification, String str, Context context, Context context2, FocusNotificationContent focusNotificationContent, InflateAndAuthCallBack inflateAndAuthCallBack, NotificationListenerController.NotificationProvider notificationProvider) {
        if (!this.mFocusNotifUtils.hasCustomFocusView(statusBarNotification)) {
            return false;
        }
        Notification notification = statusBarNotification.getNotification();
        Bundle bundle = notification.extras;
        JSONObject focusParam = this.mFocusNotifUtils.parseFocusParam(statusBarNotification, "miui.focus.param.custom", focusNotificationContent);
        if (canRemoveFocusNotification(focusParam, statusBarNotification, notificationProvider, "remove custom focus notification")) {
            return true;
        }
        boolean z2 = focusParam != null && focusParam.optBoolean(Const.Param.FILTER_WHEN_NO_PERMISSION, false);
        NotificationUtil.debugLog(TAG, "RemoteViews isSkipWhenNoFocusPermission: " + z2);
        if (!this.mFocusNotifUtils.canShowFocus(context, str, statusBarNotification.getUser().getIdentifier())) {
            Log.e(TAG, "the app can not show focus notification");
            this.mFocusNotifUtils.resetAllParam(bundle);
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.FILTER_WHEN_NO_PERMISSION, z2);
            return z2;
        }
        try {
            createCustomView(statusBarNotification, context2, context, focusNotificationContent, focusParam, notificationProvider);
            bundle.putBoolean(Const.Param.EXTRA_CUSTOM_HIDE_BORDER, true);
            fillCustomViewNotifiParams(context, focusParam, notification, bundle);
            inflateAndAuthCallBack.onInflateSuccess(statusBarNotification.getKey());
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "createCustomView has some exception: " + e2.getMessage());
            inflateAndAuthCallBack.onInflateFailed(statusBarNotification.getKey());
            return false;
        }
    }

    private boolean buildParamsFocusNotification(StatusBarNotification statusBarNotification, JSONObject jSONObject, Context context, Context context2, String str, boolean z2, FocusNotificationContent focusNotificationContent, InflateAndAuthCallBack inflateAndAuthCallBack, NotificationListenerController.NotificationProvider notificationProvider) {
        boolean zOptBoolean = jSONObject.optBoolean(Const.Param.FILTER_WHEN_NO_PERMISSION, false);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("param_v2");
        boolean zOptBoolean2 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optBoolean(Const.Param.FILTER_WHEN_NO_PERMISSION, false) : zOptBoolean;
        if (!isNoPermission(context2, statusBarNotification.getNotification().extras, jSONObject, str, statusBarNotification.getUser().getIdentifier())) {
            return fillParamsFocusNotification(context2, context, statusBarNotification, jSONObject, z2, zOptBoolean2, focusNotificationContent, inflateAndAuthCallBack, notificationProvider);
        }
        statusBarNotification.getNotification().extras.putBoolean(Const.Param.FILTER_WHEN_NO_PERMISSION, zOptBoolean2);
        return zOptBoolean2;
    }

    private boolean canRemoveFocusNotification(JSONObject jSONObject, StatusBarNotification statusBarNotification, NotificationListenerController.NotificationProvider notificationProvider, String str) {
        if (jSONObject == null || !jSONObject.optBoolean(Const.Param.NOTIFICATION_CANCEL, false)) {
            return false;
        }
        Log.e(TAG, str);
        notificationProvider.removeNotification(statusBarNotification);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFaceRecognition(StatusBarNotification statusBarNotification, String str, LottieAnimationView lottieAnimationView, NotificationListenerController.NotificationProvider notificationProvider) {
        if (str.equals(Const.FACE_RECOGNITION.FACE_RECOGNITION_SUCCESS) || str.equals(Const.FACE_RECOGNITION.FACE_RECOGNITION_FAILED)) {
            LottieResUtils.INSTANCE.cancelLottieAnimate(lottieAnimationView, false);
            notificationProvider.removeNotification(statusBarNotification);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void createCustomView(android.service.notification.StatusBarNotification r21, android.content.Context r22, android.content.Context r23, com.android.systemui.plugins.miui.notification.FocusNotificationContent r24, org.json.JSONObject r25, com.android.systemui.plugins.NotificationListenerController.NotificationProvider r26) throws miui.systemui.notification.focus.FocusParamsException {
        /*
            Method dump skipped, instruction units count: 801
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.FocusNotifPreHandler.createCustomView(android.service.notification.StatusBarNotification, android.content.Context, android.content.Context, com.android.systemui.plugins.miui.notification.FocusNotificationContent, org.json.JSONObject, com.android.systemui.plugins.NotificationListenerController$NotificationProvider):void");
    }

    private Object createInteractionHandler(Class<?> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: miui.systemui.notification.focus.a
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return this.f5797a.lambda$createInteractionHandler$1(obj, method, objArr);
            }
        });
    }

    private void fillCustomViewNotifiParams(Context context, JSONObject jSONObject, Notification notification, Bundle bundle) {
        if (jSONObject == null) {
            return;
        }
        handleAodAndStatusBar(notification, context, jSONObject);
        int iOptInt = jSONObject.optInt(Const.Param.TIMEOUT_MIN, 720);
        long j2 = iOptInt < 0 ? 5000L : 60000 * ((long) iOptInt);
        boolean zOptBoolean = jSONObject.optBoolean(Const.Param.ENABLE_FLOAT, false);
        boolean zOptBoolean2 = jSONObject.optBoolean(Const.Param.IS_FIRST_FLOAT, true);
        boolean zOptBoolean3 = jSONObject.optBoolean("updatable", false);
        String strOptString = jSONObject.optString(Const.Param.OUT_EFFECT_SRC);
        String strOptString2 = jSONObject.optString("reopen", Const.Param.REOPEN_FALSE);
        bundle.putBoolean(Const.Param.EXTRA_ENABLE_FLOAT, zOptBoolean);
        bundle.putBoolean(Const.Param.EXTRA_ISLAND_FIRST_FLOAT, zOptBoolean2);
        NotificationUtil.debugLog(TAG, "timeoutMs: " + iOptInt + " " + j2 + "ms, ,enableFloat: " + zOptBoolean + ", updatable: " + zOptBoolean3 + ",reopen: " + strOptString2 + ",islandFirstFloat:" + zOptBoolean2);
        bundle.putLong(Const.Param.EXTRA_MIUI_TIMEOUT, j2);
        bundle.putString("miui.effect.src", strOptString);
        bundle.putBoolean(Const.Param.SHOW_NOTIFICATION, jSONObject.optBoolean("isShowNotification", true));
        if (!zOptBoolean3) {
            bundle.remove(Const.Param.EXTRA_FOCUS_REOPEN);
            notification.flags |= 16;
        } else {
            bundle.putString(Const.Param.EXTRA_FOCUS_REOPEN, strOptString2);
            bundle.putBoolean(Const.Param.EXTRA_FOCUS_ENABLE_ALERT, zOptBoolean);
            notification.flags &= -17;
        }
    }

    private boolean fillParamsFocusNotification(Context context, Context context2, StatusBarNotification statusBarNotification, JSONObject jSONObject, boolean z2, boolean z3, FocusNotificationContent focusNotificationContent, InflateAndAuthCallBack inflateAndAuthCallBack, NotificationListenerController.NotificationProvider notificationProvider) {
        if (jSONObject == null) {
            return true;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("param_v2");
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(Const.Param.PARAM_VOIP_V2);
        JSONObject jSONObject2 = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject : jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2 : jSONObject;
        if (jSONObject2.has(Const.Param.SEQUENCE)) {
            Long orDefault = this.mFocusNotifUtils.getMaxSeq().getOrDefault(statusBarNotification.getKey(), Long.MIN_VALUE);
            long jOptLong = jSONObject2.optLong(Const.Param.SEQUENCE, Long.MIN_VALUE);
            if (jOptLong <= orDefault.longValue()) {
                Log.w(TAG, "filterOut old " + statusBarNotification.getKey() + ", " + jOptLong + "<" + orDefault);
                return true;
            }
            this.mFocusNotifUtils.getMaxSeq().put(statusBarNotification.getKey(), Long.valueOf(jOptLong));
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(TAG, "error focusScene=", e2);
        }
        if (jSONObjectOptJSONObject != null || jSONObjectOptJSONObject2 != null) {
            if (canRemoveFocusNotification(jSONObject2, statusBarNotification, notificationProvider, "remove focus notification v2")) {
                return true;
            }
            this.mTemplateFactoryV3.createTemplate(context, context2, jSONObject2, statusBarNotification, z2, focusNotificationContent, inflateAndAuthCallBack);
            return false;
        }
        if (canRemoveFocusNotification(jSONObject2, statusBarNotification, notificationProvider, "remove focus notification v1")) {
            return true;
        }
        FocusTemplate focusTemplateCovert = FocusTemplateKt.covert(jSONObject2, z2);
        if (this.mFocusNotifUtils.getOS1Template().containsKey(statusBarNotification.getKey())) {
            FocusTemplate focusTemplate = this.mFocusNotifUtils.getOS1Template().get(statusBarNotification.getKey());
            if (focusTemplateCovert != null && focusTemplate != null && focusTemplateCovert.getClass() != focusTemplate.getClass()) {
                focusNotificationContent.reset();
            }
        } else {
            focusNotificationContent.reset();
        }
        this.mFocusNotifUtils.getOS1Template().put(statusBarNotification.getKey(), focusTemplateCovert);
        if (focusTemplateCovert != null) {
            NotificationUtil.debugLog(TAG, "focusScene=" + focusTemplateCovert);
            if (focusTemplateCovert.bgPicDownloadFail(statusBarNotification.getNotification().extras.getBundle("miui.focus.pics"))) {
                Log.e(TAG, "bgPicDownloadFail filterOut");
                return true;
            }
            focusTemplateCovert.wrapNotification(context, statusBarNotification, focusNotificationContent);
            inflateAndAuthCallBack.onInflateSuccess(statusBarNotification.getKey());
            inflateAndAuthCallBack.onInflateFinish(statusBarNotification.getKey());
            return false;
        }
        return false;
    }

    private Context getRemoteViewsContext(Context context, RemoteViews remoteViews) throws FocusParamsException {
        ApplicationInfo applicationInfo = remoteViews.mApplication;
        if (applicationInfo == null) {
            throw new FocusParamsException("cann`t get application info");
        }
        String packageName = context.getPackageName();
        String str = applicationInfo.packageName;
        int userId = context.getUserId();
        int userId2 = UserHandle.getUserId(applicationInfo.uid);
        Log.i(TAG, "packageName: " + packageName + ", applicationPackageName: " + str + ", packageUserId: " + userId + ", applicationUserId: " + userId2);
        if (userId == userId2 && TextUtils.equals(packageName, str)) {
            return context;
        }
        try {
            return context.createPackageContextAsUser(applicationInfo.packageName, 4, new UserHandle(userId2));
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "Package name " + applicationInfo.packageName + " not found");
            throw new FocusParamsException("cann`t create package context");
        }
    }

    private void handleAodAndStatusBar(Notification notification, Context context, JSONObject jSONObject) {
        String strOptString = jSONObject.optString(Const.Param.PARAM_TICKER);
        String strOptString2 = jSONObject.optString(Const.Param.PARAM_TICKER_PIC);
        String strOptString3 = jSONObject.optString(Const.Param.PARAM_TICKER_PIC_DARK);
        String strOptString4 = jSONObject.optString(Const.Param.PARAM_AOD_TITLE);
        notification.extras.putString(Const.Param.TICKER, strOptString);
        notification.extras.putString(Const.Param.TICKER_PIC, strOptString2);
        notification.extras.putString(Const.Param.TICKER_PIC_DARK, strOptString3);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.focus_notification_template_aod_v2);
        if (strOptString4 == null || strOptString4.isEmpty()) {
            return;
        }
        int i2 = R.id.focus_aod_title;
        remoteViews.setViewVisibility(i2, 0);
        remoteViews.setTextViewText(i2, strOptString4);
        int i3 = R.id.focus_aod_icon;
        remoteViews.setViewVisibility(i3, 0);
        Bundle bundle = notification.extras.getBundle("miui.focus.pics");
        String strOptString5 = jSONObject.optString(Const.Param.PARAM_AOD_PIC);
        if (bundle == null || strOptString5 == null || strOptString5.isEmpty()) {
            notification.extras.putInt(Const.Param.AOD_ICON_VId, i3);
        } else {
            remoteViews.setImageViewIcon(i3, (Icon) bundle.getParcelable(strOptString5));
        }
        notification.extras.putParcelable(Const.Param.LAYOUT_AOD, remoteViews);
    }

    private void handleNullParam(StatusBarNotification statusBarNotification, String str, boolean z2) {
        Bundle bundle = statusBarNotification.getNotification().extras;
        if (!this.mNotificationSettingsManager.canCustomFocus(str)) {
            this.mFocusNotifUtils.resetAllParam(bundle);
        } else if (this.mFocusNotifUtils.hasCustomFocusView(statusBarNotification)) {
            if (z2) {
                bundle.putBoolean(Const.Param.EXTRA_CUSTOM_HIDE_BORDER, true);
            } else {
                this.mFocusNotifUtils.resetAllParam(bundle);
            }
        }
    }

    private void handleProgressStyle(Context context, Bundle bundle, StatusBarNotification statusBarNotification, boolean z2) {
    }

    private boolean handleRemoteViewClick(View view, PendingIntent pendingIntent, ClickHandler clickHandler) {
        if (!pendingIntent.isActivity()) {
            return clickHandler.handleClick();
        }
        this.mActivityStarter.postStartActivityDismissingKeyguard(pendingIntent.getIntent(), 0);
        ModuleViewHolderExtKt.sendCollapseBroadcast(view.getContext());
        return true;
    }

    private boolean isNoPermission(Context context, Bundle bundle, JSONObject jSONObject, String str, int i2) {
        if (this.mFocusNotifUtils.canShowFocus(context, str, i2)) {
            return false;
        }
        this.mFocusNotifUtils.resetAllParam(bundle);
        if (!jSONObject.optBoolean("updatable", false) && !jSONObject.optString(Const.Param.SCENE, "").equals(Const.Scene.FOOD_DELIVERY) && !jSONObject.optString(Const.Param.SCENE, "").equals(Const.Scene.CAR_HAILING)) {
            return true;
        }
        bundle.putBoolean(Const.Param.EXTRA_FOCUS_ENABLE_ALERT, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createInteractionHandler$0(View view, PendingIntent pendingIntent, RemoteViews.RemoteResponse remoteResponse) {
        boolean zStartPendingIntent = startPendingIntent(view, pendingIntent, remoteResponse);
        boolean zBooleanValue = ((Boolean) this.mCenterExpandRepository.getAppearance().getValue()).booleanValue();
        Log.d(TAG, "Focus notification remote view PendingIntent started: " + zStartPendingIntent + " ,appearance: " + zBooleanValue + ", clicked view: " + view);
        if (zBooleanValue) {
            try {
                Runtime.getRuntime().exec("cmd statusbar collapse");
            } catch (Exception e2) {
                Log.e(TAG, "createInteractionHandler: ", e2);
            }
        }
        return zStartPendingIntent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$createInteractionHandler$1(Object obj, Method method, Object[] objArr) {
        if (!"onInteraction".equals(method.getName())) {
            throw new UnsupportedOperationException("Unsupported method creating interaction handler: " + method.getName());
        }
        final View view = (View) objArr[0];
        final PendingIntent pendingIntent = (PendingIntent) objArr[1];
        final RemoteViews.RemoteResponse remoteResponse = (RemoteViews.RemoteResponse) objArr[2];
        Log.i(TAG, "focus remoteview handles interaction");
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException unused) {
        }
        boolean zHandleRemoteViewClick = handleRemoteViewClick(view, pendingIntent, new ClickHandler() { // from class: miui.systemui.notification.focus.b
            @Override // miui.systemui.notification.focus.FocusNotifPreHandler.ClickHandler
            public final boolean handleClick() {
                return this.f5798a.lambda$createInteractionHandler$0(view, pendingIntent, remoteResponse);
            }
        });
        Log.d(TAG, "Focus notification remote view interaction handled: " + zHandleRemoteViewClick);
        return Boolean.valueOf(zHandleRemoteViewClick);
    }

    private void playAnimation(Context context, final StatusBarNotification statusBarNotification, final String str, final LottieAnimationView lottieAnimationView, final NotificationListenerController.NotificationProvider notificationProvider) {
        if (lottieAnimationView == null) {
            return;
        }
        AbstractC0315p.s(context, LottieResUtils.INSTANCE.getLottieRes(str, -1)).d(new H(this) { // from class: miui.systemui.notification.focus.FocusNotifPreHandler.1
            @Override // d.H
            public void onResult(C0307h c0307h) {
                lottieAnimationView.setComposition(c0307h);
                if (str.equals("face_recognition")) {
                    lottieAnimationView.setRepeatCount(-1);
                    lottieAnimationView.setRepeatMode(1);
                } else {
                    lottieAnimationView.setRepeatCount(0);
                }
                lottieAnimationView.setProgress(0.0f);
            }
        });
        lottieAnimationView.playAnimation();
        if (str.equals(Const.FACE_RECOGNITION.FACE_RECOGNITION_SUCCESS) || str.equals(Const.FACE_RECOGNITION.FACE_RECOGNITION_FAILED)) {
            lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: miui.systemui.notification.focus.FocusNotifPreHandler.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(@NonNull Animator animator) {
                    FocusNotifPreHandler.this.clearFaceRecognition(statusBarNotification, str, lottieAnimationView, notificationProvider);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(@NonNull Animator animator) {
                    FocusNotifPreHandler.this.clearFaceRecognition(statusBarNotification, str, lottieAnimationView, notificationProvider);
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(@NonNull Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(@NonNull Animator animator) {
                }
            });
        }
    }

    private boolean startPendingIntent(View view, PendingIntent pendingIntent, RemoteViews.RemoteResponse remoteResponse) {
        try {
            return ((Boolean) ReflectUtil.callStaticObjectMethod(Class.forName("android.widget.RemoteViews"), Boolean.class, "startPendingIntent", new Class[]{View.class, PendingIntent.class, Pair.class}, view, pendingIntent, (Pair) ReflectUtil.callObjectMethod(remoteResponse, Pair.class, "getLaunchOptions", new Class[]{View.class}, view))).booleanValue();
        } catch (Exception e2) {
            Log.e(TAG, "error FocusNotif startPendingIntent ");
            e2.printStackTrace();
            return false;
        }
    }

    public boolean onNotificationPosted(StatusBarNotification statusBarNotification, boolean z2, Context context, Context context2, FocusNotificationContent focusNotificationContent, InflateAndAuthCallBack inflateAndAuthCallBack, NotificationListenerController.NotificationProvider notificationProvider) {
        NotificationUtil.debugLog(TAG, "plugin preHandleFocusNotification " + statusBarNotification.getKey());
        if (!this.mFocusNotifUtils.isSupportFocusNotification(context)) {
            return false;
        }
        String sbnTargetPkg = NotificationUtil.getSbnTargetPkg(context2, statusBarNotification);
        Bundle bundle = statusBarNotification.getNotification().extras;
        if (statusBarNotification.getNotification().extras.containsKey(Const.Param.EXTRA_PROGRESS_SEGMENTS)) {
            handleProgressStyle(context, bundle, statusBarNotification, z2);
            return false;
        }
        this.mFocusNotifUtils.resetParam(bundle, Const.Param.EXTRA_FOCUS_ENABLE_ALERT);
        if (this.mFocusNotifUtils.hasCustomFocusView(statusBarNotification)) {
            return buildNoParamsFocusNotification(statusBarNotification, sbnTargetPkg, context, context2, focusNotificationContent, inflateAndAuthCallBack, notificationProvider);
        }
        JSONObject focusParam = this.mFocusNotifUtils.parseFocusParam(statusBarNotification, "miui.focus.param", focusNotificationContent);
        if (focusParam == null) {
            return false;
        }
        return buildParamsFocusNotification(statusBarNotification, focusParam, context2, context, sbnTargetPkg, z2, focusNotificationContent, inflateAndAuthCallBack, notificationProvider);
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent) {
        this.mTemplateFactoryV3.removeTemplate(statusBarNotification, focusNotificationContent);
    }
}
