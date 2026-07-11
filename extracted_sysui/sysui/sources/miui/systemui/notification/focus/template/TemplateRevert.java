package miui.systemui.notification.focus.template;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.util.ContextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@SceneName(sceneNames = {Const.Scene.VERIFY_CODE, Const.Scene.RECORDER, "alarm", Const.Scene.TIMER, Const.Template.TEMPLATE_BASE_REVERT})
public final class TemplateRevert extends FocusTemplate {
    private final String TAG;
    private final int TYPE_HIDE_TIMER;
    private long timerCurrent;
    private long timerSystemCurrent;
    private long timerTotal;
    private final int timerType;
    private long timerWhen;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateRevert(JSONObject param) throws FocusParamsException {
        super(param);
        n.g(param, "param");
        this.TAG = "FocusPlugin_TemplateRevert";
        int iOptInt = param.optInt("timerType", this.TYPE_HIDE_TIMER);
        this.timerType = iOptInt;
        this.timerWhen = param.optLong("timerWhen", System.currentTimeMillis());
        this.timerTotal = param.optLong("timerTotal", 0L);
        long jOptLong = param.optLong("timerSystemCurrent", System.currentTimeMillis());
        this.timerSystemCurrent = jOptLong;
        this.timerCurrent = iOptInt > 0 ? jOptLong - this.timerWhen : this.timerWhen - jOptLong;
        if (TextUtils.isEmpty(getTitle()) && iOptInt == this.TYPE_HIDE_TIMER) {
            throw new FocusParamsException("title is empty");
        }
    }

    private final void adaptTimerDelay() {
        Bundle actionBundle = getActionBundle();
        Notification.Action action = actionBundle != null ? (Notification.Action) actionBundle.getParcelable(Const.Param.ACTION_VId_1) : null;
        if (action == null || !n.c(action.getExtras().getString("icon_name"), Const.ACTIONS.ACTION_PAUSE)) {
            return;
        }
        calculateTimer();
    }

    private final void calculateTimer() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j2 = this.timerSystemCurrent;
        if (jCurrentTimeMillis - j2 > 1000) {
            Log.d(this.TAG, "Timer delay too large, current:" + jCurrentTimeMillis + " timerSystemCurrent:" + j2);
            this.timerSystemCurrent = jCurrentTimeMillis;
            long j3 = this.timerType > 0 ? jCurrentTimeMillis - this.timerWhen : this.timerWhen - jCurrentTimeMillis;
            this.timerCurrent = j3;
            Log.d(this.TAG, "normalRemoteViewsBuild: calculateTimer :timerCurrent:" + j3);
        }
    }

    private final void normalViewsBuild(Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, boolean z4, boolean z5, View view) throws FocusParamsException {
        resetViewState(view);
        setTextVisibleAndText(view);
        int i2 = R.id.chronometer;
        Chronometer chronometer = (Chronometer) view.findViewById(i2);
        TextView textView = (TextView) view.findViewById(R.id.focus_title);
        TextView textView2 = (TextView) view.findViewById(R.id.focus_content);
        TextView textView3 = (TextView) view.findViewById(R.id.focus_desc);
        if (n.c(getTitle(), "")) {
            if (chronometer != null) {
                chronometer.setVisibility(0);
            }
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            if (chronometer != null) {
                chronometer.setVisibility(8);
            }
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
        Log.d(this.TAG, "normalRemoteViewsBuild: :timerCurrent:" + this.timerCurrent);
        if (this.timerType != this.TYPE_HIDE_TIMER) {
            statusBarNotification.getNotification().extras.putInt("timerType", this.timerType);
            statusBarNotification.getNotification().extras.putLong("timerWhen", this.timerWhen);
            statusBarNotification.getNotification().extras.putLong("timerCurrent", this.timerCurrent);
            statusBarNotification.getNotification().extras.putLong("timerTotal", this.timerTotal);
            statusBarNotification.getNotification().extras.putLong("timerSystemCurrent", this.timerSystemCurrent);
            if (chronometer != null) {
                chronometer.setCountDown(this.timerType < 0);
            }
            if (chronometer != null) {
                chronometer.setBase((SystemClock.elapsedRealtime() + this.timerWhen) - this.timerSystemCurrent);
            }
            statusBarNotification.getNotification().extras.putBoolean(Const.Param.PROGRESS_BACK_ENABLE, false);
            statusBarNotification.getNotification().extras.putInt("miui.focus.chronometerId", i2);
            statusBarNotification.getNotification().extras.putInt(Const.Param.ACTION_VId_2, R.id.focus_button_icon2);
            statusBarNotification.getNotification().extras.putInt(Const.Param.ACTION_VId_1, R.id.focus_button_icon1);
        }
        int i3 = R.id.focus_button_icon1;
        Bundle actionBundle = getActionBundle();
        setActionData(view, i3, actionBundle != null ? (Notification.Action) actionBundle.getParcelable(Const.Param.ACTION_VId_1) : null, z3, z4);
        int i4 = R.id.focus_button_icon2;
        Bundle actionBundle2 = getActionBundle();
        setActionData(view, i4, actionBundle2 != null ? (Notification.Action) actionBundle2.getParcelable(Const.Param.ACTION_VId_2) : null, z3, z4);
        setRemoteViewsBackground(context, view, statusBarNotification, z2, z5);
        if (isSolidBackground() && !isBgPicDownloadFail()) {
            if (getTitleColor() != null && textView != null) {
                textView.setTextColor(getTitleColor().intValue());
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue = contentColor.intValue();
                if (textView2 != null) {
                    textView2.setTextColor(iIntValue);
                }
            }
            Integer descColor = getDescColor();
            if (descColor != null) {
                int iIntValue2 = descColor.intValue();
                if (textView3 != null) {
                    textView3.setTextColor(iIntValue2);
                }
                if (isNotHaveContent() && textView2 != null) {
                    textView2.setTextColor(iIntValue2);
                }
            }
        }
        resetTemplateState();
    }

    public static /* synthetic */ void normalViewsBuild$default(TemplateRevert templateRevert, Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, boolean z4, boolean z5, View view, int i2, Object obj) throws FocusParamsException {
        templateRevert.normalViewsBuild(context, statusBarNotification, z2, (i2 & 8) != 0 ? false : z3, (i2 & 16) != 0 ? false : z4, (i2 & 32) != 0 ? false : z5, view);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildDecoLandViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) throws FocusParamsException {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        adaptTimerDelay();
        View decoLand = focusContent.getDecoLand();
        if (decoLand == null) {
            decoLand = LayoutInflater.from(ContextUtils.INSTANCE.getDayContext(ctx)).inflate(R.layout.focus_notification_template_revert_deco_land, (ViewGroup) null);
        }
        View view = decoLand;
        View decoLandDark = focusContent.getDecoLandDark();
        if (decoLandDark == null) {
            decoLandDark = LayoutInflater.from(ContextUtils.getNightContext$default(ContextUtils.INSTANCE, ctx, false, 2, null)).inflate(R.layout.focus_notification_template_revert_deco_land, (ViewGroup) null);
        }
        View view2 = decoLandDark;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, true, true, false, false, view, 48, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, true, true, false, false, view2, 48, null);
        focusContent.setDecoLand(view);
        focusContent.setDecoLandDark(view2);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildDecoPortViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) throws FocusParamsException {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        adaptTimerDelay();
        View deco = focusContent.getDeco();
        if (deco == null) {
            deco = LayoutInflater.from(ContextUtils.INSTANCE.getDayContext(ctx)).inflate(R.layout.focus_notification_template_revert_deco_port, (ViewGroup) null);
        }
        View view = deco;
        View decoDark = focusContent.getDecoDark();
        if (decoDark == null) {
            decoDark = LayoutInflater.from(ContextUtils.getNightContext$default(ContextUtils.INSTANCE, ctx, false, 2, null)).inflate(R.layout.focus_notification_template_revert_deco_port, (ViewGroup) null);
        }
        View view2 = decoDark;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, true, true, true, false, view, 32, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, true, true, true, false, view2, 32, null);
        focusContent.setDeco(view);
        focusContent.setDecoDark(view2);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildNormalViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) throws FocusParamsException {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        adaptTimerDelay();
        ContextUtils contextUtils = ContextUtils.INSTANCE;
        Context dayContext = contextUtils.getDayContext(ctx);
        Context nightContext$default = ContextUtils.getNightContext$default(contextUtils, ctx, false, 2, null);
        View focusNotification = focusContent.getFocusNotification();
        if (focusNotification == null) {
            focusNotification = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert, (ViewGroup) null);
        }
        View view = focusNotification;
        View focusNotificationDark = focusContent.getFocusNotificationDark();
        if (focusNotificationDark == null) {
            focusNotificationDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert, (ViewGroup) null);
        }
        View view2 = focusNotificationDark;
        View focusNotificationModal = focusContent.getFocusNotificationModal();
        if (focusNotificationModal == null) {
            focusNotificationModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert, (ViewGroup) null);
            focusNotificationModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view3 = focusNotificationModal;
        View focusNotificationDarkModal = focusContent.getFocusNotificationDarkModal();
        if (focusNotificationDarkModal == null) {
            focusNotificationDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert, (ViewGroup) null);
            focusNotificationDarkModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view4 = focusNotificationDarkModal;
        Context nightContext = contextUtils.getNightContext(ctx, true);
        View islandExpandedView = focusContent.getIslandExpandedView();
        if (islandExpandedView == null) {
            islandExpandedView = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_revert, (ViewGroup) null);
        }
        View view5 = islandExpandedView;
        View islandExpandedViewFake = focusContent.getIslandExpandedViewFake();
        if (islandExpandedViewFake == null) {
            islandExpandedViewFake = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_revert, (ViewGroup) null);
        }
        View view6 = islandExpandedViewFake;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, false, false, false, false, view, 56, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, false, false, false, false, view2, 56, null);
        n.d(view3);
        normalViewsBuild$default(this, ctx, sbn, false, false, false, false, view3, 56, null);
        n.d(view4);
        normalViewsBuild$default(this, ctx, sbn, false, false, false, false, view4, 56, null);
        n.d(view5);
        normalViewsBuild$default(this, ctx, sbn, false, false, false, true, view5, 24, null);
        n.d(view6);
        normalViewsBuild$default(this, ctx, sbn, false, false, false, true, view6, 24, null);
        focusContent.setFocusNotification(view);
        focusContent.setFocusNotificationDark(view2);
        focusContent.setFocusNotificationModal(view3);
        focusContent.setFocusNotificationDarkModal(view4);
        focusContent.setIslandExpandedView(view5);
        focusContent.setIslandExpandedViewFake(view6);
        sbn.getNotification().extras.putBoolean(Const.Param.IS_FOCUS_LAYOUT, true);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildTinyViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) throws FocusParamsException {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        adaptTimerDelay();
        ContextUtils contextUtils = ContextUtils.INSTANCE;
        Context dayContext = contextUtils.getDayContext(ctx);
        Context nightContext$default = ContextUtils.getNightContext$default(contextUtils, ctx, false, 2, null);
        View tinyView = focusContent.getTinyView();
        if (tinyView == null) {
            tinyView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_tiny, (ViewGroup) null);
        }
        View view = tinyView;
        View tinyKeyguardView = focusContent.getTinyKeyguardView();
        if (tinyKeyguardView == null) {
            tinyKeyguardView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_tiny, (ViewGroup) null);
        }
        View view2 = tinyKeyguardView;
        View tinyViewDark = focusContent.getTinyViewDark();
        if (tinyViewDark == null) {
            tinyViewDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_tiny, (ViewGroup) null);
        }
        View view3 = tinyViewDark;
        View tinyViewModal = focusContent.getTinyViewModal();
        if (tinyViewModal == null) {
            tinyViewModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_tiny, (ViewGroup) null);
        }
        View view4 = tinyViewModal;
        View tinyViewDarkModal = focusContent.getTinyViewDarkModal();
        if (tinyViewDarkModal == null) {
            tinyViewDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_tiny, (ViewGroup) null);
        }
        View view5 = tinyViewDarkModal;
        View tinyViewKeyguardDark = focusContent.getTinyViewKeyguardDark();
        if (tinyViewKeyguardDark == null) {
            tinyViewKeyguardDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_tiny, (ViewGroup) null);
        }
        View view6 = tinyViewKeyguardDark;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, true, false, false, false, view, 56, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, true, false, false, false, view2, 56, null);
        n.d(view3);
        normalViewsBuild$default(this, ctx, sbn, true, false, false, false, view3, 56, null);
        n.d(view4);
        normalViewsBuild$default(this, ctx, sbn, true, false, false, false, view4, 56, null);
        n.d(view5);
        normalViewsBuild$default(this, ctx, sbn, true, false, false, false, view5, 56, null);
        n.d(view6);
        normalViewsBuild$default(this, ctx, sbn, true, false, false, false, view6, 56, null);
        focusContent.setTinyView(view);
        focusContent.setTinyKeyguardView(view2);
        focusContent.setTinyViewDark(view3);
        focusContent.setTinyViewModal(view4);
        focusContent.setTinyViewDarkModal(view5);
        focusContent.setTinyViewKeyguardDark(view6);
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getTYPE_HIDE_TIMER() {
        return this.TYPE_HIDE_TIMER;
    }

    public final long getTimerCurrent() {
        return this.timerCurrent;
    }

    public final long getTimerSystemCurrent() {
        return this.timerSystemCurrent;
    }

    public final long getTimerTotal() {
        return this.timerTotal;
    }

    public final int getTimerType() {
        return this.timerType;
    }

    public final long getTimerWhen() {
        return this.timerWhen;
    }

    public final void setTimerCurrent(long j2) {
        this.timerCurrent = j2;
    }

    public final void setTimerSystemCurrent(long j2) {
        this.timerSystemCurrent = j2;
    }

    public final void setTimerTotal(long j2) {
        this.timerTotal = j2;
    }

    public final void setTimerWhen(long j2) {
        this.timerWhen = j2;
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public String toString() {
        return TemplateRevert.class.getSimpleName() + " " + super.toString();
    }
}
