package miui.systemui.notification.focus.template;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import c1.f;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import com.miui.circulate.device.api.Constant;
import java.util.ArrayList;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.template.FocusTemplate;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public abstract class FocusTemplate {
    private final String IS_FLIP_DEVICE;
    private Bundle actionBundle;
    private final String answer;
    private String aodPic;
    private String aodTitle;
    private Bundle bitmapBundle;
    private final String call;
    private final String close;
    private final Integer colorBg;
    private String content;
    private Integer contentColor;
    private Integer contentColorDark;
    private final String copy;
    private final String desc;
    private final String desc1;
    private final String desc2;
    private final Integer descColor;
    private final Integer descColorDark;
    private final String done;
    private final boolean enableFloat;
    private final String end;
    private final String hangup;
    private boolean haveSubTitle;
    private boolean isBgPicDownloadFail;
    private boolean isNotHaveContent;
    private boolean isOnlyActionButton;
    private boolean isSolidBackground;
    private final String later;
    private boolean padding;
    private final JSONObject param;
    private final String pause;
    private int protocol;
    private String reopen;
    private final String restart;
    private String scene;
    private final String speaker;
    private String ticker;
    private String tickerPic;
    private String tickerPicDark;
    private int timeoutMin;
    private String title;
    private final Integer titleColor;
    private final Integer titleColorDark;
    private final boolean updatable;

    public FocusTemplate(JSONObject param) throws JSONException, FocusParamsException {
        String str;
        Integer numValueOf;
        Integer numValueOf2;
        Integer numValueOf3;
        Integer numValueOf4;
        Integer numValueOf5;
        Integer numValueOf6;
        String strOptString;
        n.g(param, "param");
        this.param = param;
        Object obj = param.get("protocol");
        n.e(obj, "null cannot be cast to non-null type kotlin.Int");
        this.protocol = ((Integer) obj).intValue();
        Object obj2 = param.get(Const.Param.SCENE);
        n.e(obj2, "null cannot be cast to non-null type kotlin.String");
        this.scene = (String) obj2;
        this.title = param.optString("title").toString();
        this.ticker = param.optString(Const.Param.PARAM_TICKER, NotificationUtil.DEBUG ? "noTicker" : "");
        this.tickerPic = param.optString(Const.Param.PARAM_TICKER_PIC, "");
        this.tickerPicDark = param.optString(Const.Param.PARAM_TICKER_PIC_DARK, "");
        this.aodTitle = param.optString(Const.Param.PARAM_AOD_TITLE, "");
        this.aodPic = param.optString(Const.Param.PARAM_AOD_PIC, "");
        this.timeoutMin = param.optInt(Const.Param.TIMEOUT_MIN, 720);
        String strOptString2 = param.optString(Const.Param.DESC_1);
        n.f(strOptString2, "optString(...)");
        this.desc1 = strOptString2;
        String strOptString3 = param.optString(Const.Param.DESC_2);
        n.f(strOptString3, "optString(...)");
        this.desc2 = strOptString3;
        if (n.c(strOptString2, "") || n.c(strOptString3, "")) {
            str = strOptString2 + strOptString3;
        } else {
            str = strOptString2 + "丨" + strOptString3;
        }
        this.desc = str;
        Integer numValueOf7 = null;
        try {
            numValueOf = Integer.valueOf(Color.parseColor(param.optString(Const.Param.COLOR_BG)));
        } catch (Exception unused) {
            numValueOf = null;
        }
        this.colorBg = numValueOf;
        try {
            numValueOf2 = Integer.valueOf(Color.parseColor(this.param.optString(Const.Param.COLOR_TITLE)));
        } catch (Exception unused2) {
            numValueOf2 = null;
        }
        this.titleColor = numValueOf2;
        try {
            numValueOf3 = Integer.valueOf(Color.parseColor(this.param.optString(Const.Param.COLOR_TITLE_DARK)));
        } catch (Exception unused3) {
            numValueOf3 = null;
        }
        this.titleColorDark = numValueOf3;
        try {
            numValueOf4 = Integer.valueOf(Color.parseColor(this.param.optString(Const.Param.COLOR_CONTENT)));
        } catch (Exception unused4) {
            numValueOf4 = null;
        }
        this.contentColor = numValueOf4;
        try {
            numValueOf5 = Integer.valueOf(Color.parseColor(this.param.optString(Const.Param.COLOR_CONTENT_DARK)));
        } catch (Exception unused5) {
            numValueOf5 = null;
        }
        this.contentColorDark = numValueOf5;
        try {
            numValueOf6 = Integer.valueOf(Color.parseColor(this.param.optString(Const.Param.COLOR_DESC)));
        } catch (Exception unused6) {
            numValueOf6 = null;
        }
        this.descColor = numValueOf6;
        try {
            numValueOf7 = Integer.valueOf(Color.parseColor(this.param.optString(Const.Param.COLOR_DESC_DARK)));
        } catch (Exception unused7) {
        }
        this.descColorDark = numValueOf7;
        String strOptString4 = this.param.optString(Const.Param.CONTENT);
        n.f(strOptString4, "optString(...)");
        boolean z2 = strOptString4.length() == 0;
        this.isNotHaveContent = z2;
        if (!z2) {
            strOptString = this.param.optString(Const.Param.CONTENT);
            n.d(strOptString);
        } else {
            if (TextUtils.isEmpty(this.desc)) {
                throw new FocusParamsException("Both content and desc are null");
            }
            this.contentColor = this.descColor;
            this.contentColorDark = numValueOf7;
            strOptString = this.desc;
        }
        this.content = strOptString;
        this.updatable = (this.param.optBoolean("updatable", false) || n.c(this.scene, Const.Scene.FOOD_DELIVERY) || n.c(this.scene, Const.Scene.CAR_HAILING)) && hasPermission();
        this.enableFloat = this.param.optBoolean(Const.Param.ENABLE_FLOAT, false);
        this.padding = this.param.optBoolean(Const.Param.PADDING, false);
        this.reopen = this.param.optString("reopen", Const.Param.REOPEN_FALSE);
        if (TextUtils.isEmpty(this.title) && !(this instanceof TemplateRevert)) {
            throw new FocusParamsException("title is empty");
        }
        this.pause = Const.ACTIONS.ACTION_PAUSE;
        this.restart = Const.ACTIONS.ACTION_RESTART;
        this.done = Const.ACTIONS.ACTION_DONE;
        this.later = Const.ACTIONS.ACTION_LATER;
        this.close = Const.ACTIONS.ACTION_CLOSE;
        this.end = Const.ACTIONS.ACTION_END;
        this.copy = Const.ACTIONS.ACTION_COPY;
        this.answer = Const.ACTIONS.ACTION_ANSWER;
        this.hangup = Const.ACTIONS.ACTION_HANGUP;
        this.speaker = Const.ACTIONS.ACTION_SPEAKER;
        this.call = Const.ACTIONS.ACTION_CALL;
        this.IS_FLIP_DEVICE = "is_flip_device";
    }

    private final void buildRemoteViews(Context context, StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent) {
        buildNormalViews(context, statusBarNotification, focusNotificationContent);
        handleAodAndStatusBar(statusBarNotification, context);
        if (FocusTemplateKt.isFlipDevice) {
            buildTinyViews(context, statusBarNotification, focusNotificationContent);
            buildDecoPortViews(context, statusBarNotification, focusNotificationContent);
            buildDecoLandViews(context, statusBarNotification, focusNotificationContent);
        }
    }

    private final int getIconResourceId(String str, boolean z2, boolean z3) {
        if (n.c(str, this.pause)) {
            return z2 ? z3 ? R.drawable.pause_deco_port : R.drawable.pause_deco : R.drawable.pause;
        }
        if (n.c(str, this.restart)) {
            return z2 ? z3 ? R.drawable.restart_deco_port : R.drawable.restart_deco : R.drawable.restart;
        }
        if (n.c(str, this.done)) {
            return z2 ? z3 ? R.drawable.done_deco_port : R.drawable.done_deco : R.drawable.done;
        }
        if (n.c(str, this.end)) {
            return z2 ? z3 ? R.drawable.end_deco_port : R.drawable.end_deco : R.drawable.end;
        }
        if (n.c(str, this.copy)) {
            return z2 ? z3 ? R.drawable.copy_deco_port : R.drawable.copy_deco : R.drawable.copy;
        }
        if (n.c(str, this.close)) {
            return z2 ? z3 ? R.drawable.close_deco_port : R.drawable.close_deco : R.drawable.close;
        }
        if (n.c(str, this.later)) {
            return z2 ? z3 ? R.drawable.later_deco_port : R.drawable.later_deco : R.drawable.later;
        }
        if (n.c(str, this.answer)) {
            return z2 ? R.drawable.answer_deco : R.drawable.answer;
        }
        if (n.c(str, this.hangup)) {
            return z2 ? R.drawable.hangup_deco : R.drawable.hangup;
        }
        if (n.c(str, this.speaker)) {
            return z2 ? z3 ? R.drawable.speaker_deco_port : R.drawable.speaker_deco : R.drawable.speaker;
        }
        if (n.c(str, this.call)) {
            return R.drawable.call;
        }
        return -1;
    }

    public static /* synthetic */ void setActionData$default(FocusTemplate focusTemplate, RemoteViews remoteViews, int i2, Notification.Action action, boolean z2, boolean z3, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setActionData");
        }
        focusTemplate.setActionData(remoteViews, i2, action, (i3 & 8) != 0 ? false : z2, (i3 & 16) != 0 ? false : z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setActionData$lambda$1(Notification.Action action, View view) throws PendingIntent.CanceledException {
        PendingIntent pendingIntent = action.actionIntent;
        if (pendingIntent != null) {
            pendingIntent.send();
        }
    }

    public static /* synthetic */ void setRemoteViewsBackground$default(FocusTemplate focusTemplate, Context context, View view, StatusBarNotification statusBarNotification, boolean z2, boolean z3, int i2, Object obj) throws FocusParamsException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setRemoteViewsBackground");
        }
        if ((i2 & 16) != 0) {
            z3 = false;
        }
        focusTemplate.setRemoteViewsBackground(context, view, statusBarNotification, z2, z3);
    }

    public static /* synthetic */ void setRemoteViewsProgress$default(FocusTemplate focusTemplate, Context context, View view, int i2, int i3, boolean z2, boolean z3, boolean z4, boolean z5, int i4, StatusBarNotification statusBarNotification, int i5, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setRemoteViewsProgress");
        }
        focusTemplate.setRemoteViewsProgress(context, view, i2, i3, (i5 & 16) != 0 ? false : z2, (i5 & 32) != 0 ? false : z3, (i5 & 64) != 0 ? false : z4, (i5 & 128) != 0 ? false : z5, (i5 & 256) != 0 ? R.drawable.point_unselect : i4, statusBarNotification);
    }

    public final boolean bgPicDownloadFail(Bundle bundle) {
        return bundle != null && bundle.containsKey("miui.focus.pic_bg") && (bundle != null ? (Icon) bundle.getParcelable("miui.focus.pic_bg") : null) == null;
    }

    public abstract void buildDecoLandViews(Context context, StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent);

    public abstract void buildDecoPortViews(Context context, StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent);

    public abstract void buildNormalViews(Context context, StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent);

    public abstract void buildTinyViews(Context context, StatusBarNotification statusBarNotification, FocusNotificationContent focusNotificationContent);

    public void checkPermission() {
    }

    public final Bundle getActionBundle() {
        return this.actionBundle;
    }

    public final String getAodPic() {
        return this.aodPic;
    }

    public final String getAodTitle() {
        return this.aodTitle;
    }

    public final Bundle getBitmapBundle() {
        return this.bitmapBundle;
    }

    public final Integer getColorBg() {
        return this.colorBg;
    }

    public final String getContent() {
        return this.content;
    }

    public final Integer getContentColor() {
        return this.contentColor;
    }

    public final Integer getContentColorDark() {
        return this.contentColorDark;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getDesc1() {
        return this.desc1;
    }

    public final String getDesc2() {
        return this.desc2;
    }

    public final Integer getDescColor() {
        return this.descColor;
    }

    public final Integer getDescColorDark() {
        return this.descColorDark;
    }

    public final boolean getEnableFloat() {
        return this.enableFloat;
    }

    public final boolean getHaveSubTitle() {
        return this.haveSubTitle;
    }

    public final boolean getPadding() {
        return this.padding;
    }

    public final JSONObject getParam() {
        return this.param;
    }

    public final int getProtocol() {
        return this.protocol;
    }

    public final String getReopen() {
        return this.reopen;
    }

    public final String getScene() {
        return this.scene;
    }

    public final String getTicker() {
        return this.ticker;
    }

    public final String getTickerPic() {
        return this.tickerPic;
    }

    public final String getTickerPicDark() {
        return this.tickerPicDark;
    }

    public final int getTimeoutMin() {
        return this.timeoutMin;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getTitleColor() {
        return this.titleColor;
    }

    public final Integer getTitleColorDark() {
        return this.titleColorDark;
    }

    public final boolean getUpdatable() {
        return this.updatable;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleAodAndStatusBar(android.service.notification.StatusBarNotification r4, android.content.Context r5) {
        /*
            r3 = this;
            java.lang.String r0 = "sbn"
            kotlin.jvm.internal.n.g(r4, r0)
            java.lang.String r0 = "ctx"
            kotlin.jvm.internal.n.g(r5, r0)
            android.app.Notification r0 = r4.getNotification()
            android.os.Bundle r0 = r0.extras
            java.lang.String r1 = "miui.focus.ticker"
            java.lang.String r2 = r3.ticker
            r0.putString(r1, r2)
            android.app.Notification r0 = r4.getNotification()
            android.os.Bundle r0 = r0.extras
            java.lang.String r1 = "miui.focus.pic_ticker"
            java.lang.String r2 = r3.tickerPic
            r0.putString(r1, r2)
            android.app.Notification r0 = r4.getNotification()
            android.os.Bundle r0 = r0.extras
            java.lang.String r1 = "miui.focus.pic_ticker_dark"
            java.lang.String r2 = r3.tickerPicDark
            r0.putString(r1, r2)
            android.widget.RemoteViews r0 = new android.widget.RemoteViews
            java.lang.String r5 = r5.getPackageName()
            int r1 = com.android.systemui.miui.notification.R.layout.focus_notification_template_aod_v2
            r0.<init>(r5, r1)
            java.lang.String r5 = r3.aodTitle
            if (r5 == 0) goto L86
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L86
            java.lang.String r5 = r3.aodTitle
            r3.setAodTextVisibleAndText(r0, r5)
            int r5 = com.android.systemui.miui.notification.R.id.focus_aod_icon
            r1 = 0
            r0.setViewVisibility(r5, r1)
            java.lang.String r1 = r3.aodPic
            if (r1 == 0) goto L70
            kotlin.jvm.internal.n.d(r1)
            int r1 = r1.length()
            if (r1 <= 0) goto L70
            android.os.Bundle r1 = r3.bitmapBundle
            if (r1 == 0) goto L6b
            java.lang.String r3 = r3.aodPic
            android.os.Parcelable r3 = r1.getParcelable(r3)
            android.graphics.drawable.Icon r3 = (android.graphics.drawable.Icon) r3
            goto L6c
        L6b:
            r3 = 0
        L6c:
            r0.setImageViewIcon(r5, r3)
            goto L7b
        L70:
            android.app.Notification r3 = r4.getNotification()
            android.os.Bundle r3 = r3.extras
            java.lang.String r1 = "miui.focus.aodIconId"
            r3.putInt(r1, r5)
        L7b:
            android.app.Notification r3 = r4.getNotification()
            android.os.Bundle r3 = r3.extras
            java.lang.String r4 = "miui.focus.rvAod"
            r3.putParcelable(r4, r0)
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.notification.focus.template.FocusTemplate.handleAodAndStatusBar(android.service.notification.StatusBarNotification, android.content.Context):void");
    }

    public void handleTimeout(StatusBarNotification sbn, int i2) {
        n.g(sbn, "sbn");
        long j2 = i2 < 0 ? 5000L : 60000 * ((long) i2);
        NotificationUtil.debugLog(Const.TAG, "handleTimeout " + j2);
        sbn.getNotification().extras.putLong(Const.Param.EXTRA_MIUI_TIMEOUT, j2);
    }

    public final boolean hasPermission() {
        return true;
    }

    public final boolean isBgPicDownloadFail() {
        return this.isBgPicDownloadFail;
    }

    public final boolean isNotHaveContent() {
        return this.isNotHaveContent;
    }

    public final boolean isOnlyActionButton() {
        return this.isOnlyActionButton;
    }

    public final boolean isSolidBackground() {
        return this.isSolidBackground;
    }

    public final void resetTemplateState() {
        this.haveSubTitle = false;
        this.isOnlyActionButton = false;
        this.isSolidBackground = false;
        this.isBgPicDownloadFail = false;
    }

    public final void resetViewState(View v2) {
        n.g(v2, "v");
        TextView textView = (TextView) v2.findViewById(R.id.focus_title);
        if (textView != null) {
            textView.setText((CharSequence) null);
        }
        TextView textView2 = (TextView) v2.findViewById(R.id.focus_content);
        if (textView2 != null) {
            textView2.setText((CharSequence) null);
        }
        TextView textView3 = (TextView) v2.findViewById(R.id.focus_desc);
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
        View viewFindViewById = v2.findViewById(R.id.progressbar_container);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        View viewFindViewById2 = v2.findViewById(R.id.progress_point2);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(8);
        }
        View viewFindViewById3 = v2.findViewById(R.id.focus_progress_info2);
        if (viewFindViewById3 != null) {
            viewFindViewById3.setVisibility(8);
        }
        int i2 = R.id.focus_large_icon;
        View viewFindViewById4 = v2.findViewById(i2);
        if (viewFindViewById4 != null) {
            viewFindViewById4.setVisibility(8);
        }
        ImageView imageView = (ImageView) v2.findViewById(i2);
        if (imageView != null) {
            imageView.setImageBitmap(null);
        }
        int i3 = R.id.focus_small_icon;
        ImageView imageView2 = (ImageView) v2.findViewById(i3);
        if (imageView2 != null) {
            imageView2.setImageBitmap(null);
        }
        ImageView imageView3 = (ImageView) v2.findViewById(i3);
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        ImageView imageView4 = (ImageView) v2.findViewById(R.id.focus_button_icon1);
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        ImageView imageView5 = (ImageView) v2.findViewById(R.id.focus_button_icon2);
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        TextView textView4 = (TextView) v2.findViewById(R.id.focus_subtitle);
        if (textView4 == null) {
            return;
        }
        textView4.setVisibility(8);
    }

    public final void setActionBundle(Bundle bundle) {
        this.actionBundle = bundle;
    }

    public final void setActionData(RemoteViews v2, int i2, Notification.Action action, boolean z2, boolean z3) {
        String string;
        n.g(v2, "v");
        if (action == null || (string = action.getExtras().getString("icon_name")) == null) {
            return;
        }
        int iconResourceId = getIconResourceId(string, z2, z3);
        if (iconResourceId != -1) {
            v2.setImageViewResource(i2, iconResourceId);
        }
        v2.setContentDescription(i2, action.title);
        v2.setOnClickPendingIntent(i2, action.actionIntent);
        v2.setViewVisibility(i2, 0);
        this.isOnlyActionButton = true;
    }

    public final void setAodPic(String str) {
        this.aodPic = str;
    }

    public final void setAodTextVisibleAndText(RemoteViews v2, String str) {
        n.g(v2, "v");
        int i2 = R.id.focus_aod_title;
        v2.setViewVisibility(i2, 0);
        v2.setTextViewText(i2, str);
    }

    public final void setAodTitle(String str) {
        this.aodTitle = str;
    }

    public final void setBgPicDownloadFail(boolean z2) {
        this.isBgPicDownloadFail = z2;
    }

    public final void setBitmapBundle(Bundle bundle) {
        this.bitmapBundle = bundle;
    }

    public final void setContent(String str) {
        n.g(str, "<set-?>");
        this.content = str;
    }

    public final void setContentColor(Integer num) {
        this.contentColor = num;
    }

    public final void setContentColorDark(Integer num) {
        this.contentColorDark = num;
    }

    public final void setDescTextColor(View v2, int i2) {
        TextView textView;
        n.g(v2, "v");
        TextView textView2 = (TextView) v2.findViewById(R.id.focus_desc);
        if (textView2 != null) {
            textView2.setTextColor(i2);
        }
        if (this.isNotHaveContent && (textView = (TextView) v2.findViewById(R.id.focus_content)) != null) {
            textView.setTextColor(i2);
        }
        ProgressBar progressBar = (ProgressBar) v2.findViewById(R.id.focus_progress_info1);
        if (progressBar != null) {
            progressBar.setProgressTintList(ColorStateList.valueOf(i2));
        }
        ProgressBar progressBar2 = (ProgressBar) v2.findViewById(R.id.focus_progress_info2);
        if (progressBar2 != null) {
            progressBar2.setProgressTintList(ColorStateList.valueOf(i2));
        }
        ImageView imageView = (ImageView) v2.findViewById(R.id.progress_point1);
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(i2));
        }
        ImageView imageView2 = (ImageView) v2.findViewById(R.id.progress_point2);
        if (imageView2 == null) {
            return;
        }
        imageView2.setImageTintList(ColorStateList.valueOf(i2));
    }

    public final void setHaveSubTitle(boolean z2) {
        this.haveSubTitle = z2;
    }

    public final void setNotHaveContent(boolean z2) {
        this.isNotHaveContent = z2;
    }

    public final void setOnlyActionButton(boolean z2) {
        this.isOnlyActionButton = z2;
    }

    public final void setPadding(boolean z2) {
        this.padding = z2;
    }

    public final void setProtocol(int i2) {
        this.protocol = i2;
    }

    public final void setRemoteViewsBackground(Context ctx, View v2, StatusBarNotification sbn, boolean z2, boolean z3) throws FocusParamsException {
        Drawable drawableLoadDrawable;
        n.g(ctx, "ctx");
        n.g(v2, "v");
        n.g(sbn, "sbn");
        Bundle bundle = this.bitmapBundle;
        Icon icon = bundle != null ? (Icon) bundle.getParcelable("miui.focus.pic_bg") : null;
        Bundle bundle2 = this.bitmapBundle;
        this.isBgPicDownloadFail = bundle2 != null && bundle2.containsKey("miui.focus.pic_bg") && icon == null;
        ImageView imageView = (ImageView) v2.findViewById(R.id.focus_notify_bg_image);
        if (this.isBgPicDownloadFail || z3) {
            this.isSolidBackground = true;
        } else if (icon != null) {
            sbn.getNotification().extras.putBoolean(Const.Param.HAS_CUSTOM_BG, true);
            if (imageView != null) {
                imageView.setImageIcon(icon);
            }
        } else if (this.colorBg != null) {
            try {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
                new Canvas(bitmapCreateBitmap).drawColor(this.colorBg.intValue());
                sbn.getNotification().extras.putBoolean(Const.Param.HAS_CUSTOM_BG, true);
                if (imageView != null) {
                    imageView.setImageBitmap(bitmapCreateBitmap);
                }
            } catch (Error e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } else {
            this.isSolidBackground = true;
        }
        sbn.getNotification().extras.putBoolean(Const.Param.PARAM_IS_SOLID_BG, this.isSolidBackground);
        if (!this.isSolidBackground) {
            if (!TextUtils.isEmpty(this.title) && this.titleColor == null) {
                throw new FocusParamsException("The color of title is null");
            }
            if (!TextUtils.isEmpty(this.content) && this.contentColor == null) {
                throw new FocusParamsException("The color of content is null");
            }
            if (!TextUtils.isEmpty(this.desc) && this.descColor == null) {
                throw new FocusParamsException("The color of desc is null");
            }
            Integer num = this.titleColor;
            if (num != null) {
                int iIntValue = num.intValue();
                TextView textView = (TextView) v2.findViewById(R.id.focus_title);
                if (textView != null) {
                    textView.setTextColor(iIntValue);
                }
                TextView textView2 = (TextView) v2.findViewById(R.id.focus_subtitle);
                if (textView2 != null) {
                    textView2.setTextColor(iIntValue);
                }
            }
            Integer num2 = this.contentColor;
            if (num2 != null) {
                int iIntValue2 = num2.intValue();
                TextView textView3 = (TextView) v2.findViewById(R.id.focus_content);
                if (textView3 != null) {
                    textView3.setTextColor(iIntValue2);
                }
            }
            Integer num3 = this.descColor;
            if (num3 != null) {
                setDescTextColor(v2, num3.intValue());
            }
        }
        if (this.isOnlyActionButton) {
            return;
        }
        Bundle bundle3 = this.bitmapBundle;
        Icon icon2 = bundle3 != null ? (Icon) bundle3.getParcelable("miui.focus.pic_large") : null;
        ImageView imageView2 = (ImageView) v2.findViewById(R.id.focus_large_icon);
        ViewGroup.LayoutParams layoutParams = imageView2 != null ? imageView2.getLayoutParams() : null;
        if (layoutParams == null) {
            return;
        }
        if (z2) {
            float dimension = n.c(this.scene, Const.Scene.SOS) ? ctx.getResources().getDimension(R.dimen.focus_notify_tiny_large_pic_mini_height) : ctx.getResources().getDimension(R.dimen.focus_notify_tiny_large_pic_height);
            if (icon2 != null) {
                int i2 = (int) dimension;
                layoutParams.height = i2;
                layoutParams.width = i2;
                imageView2.setLayoutParams(layoutParams);
                imageView2.setImageIcon(icon2);
                imageView2.setVisibility(0);
            }
            if (this.haveSubTitle) {
                ((TextView) v2.findViewById(R.id.focus_title)).setMaxEms(4);
                return;
            }
            return;
        }
        float dimension2 = ctx.getResources().getDimension(R.dimen.focus_notify_large_pic_height);
        if (icon2 == null || (drawableLoadDrawable = icon2.loadDrawable(ctx)) == null) {
            return;
        }
        int intrinsicWidth = drawableLoadDrawable.getIntrinsicWidth();
        Drawable drawableLoadDrawable2 = icon2.loadDrawable(ctx);
        if (drawableLoadDrawable2 != null) {
            int intrinsicHeight = drawableLoadDrawable2.getIntrinsicHeight();
            float fH = f.h(intrinsicWidth / intrinsicHeight, 1.0f, 1.7f);
            NotificationUtil.debugLog(Const.TAG, "LargeIconData " + intrinsicWidth + " , " + intrinsicHeight + " , " + fH);
            if (this.padding) {
                dimension2 = ctx.getResources().getDimension(R.dimen.focus_notify_large_pic_padding_height);
                if (imageView2.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.setMarginEnd((int) ctx.getResources().getDimension(R.dimen.focus_notify_large_icon_margin_end));
                    layoutParams2.topMargin = (int) ctx.getResources().getDimension(R.dimen.focus_notify_large_icon_margin_top);
                }
                if (imageView2.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                    FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) layoutParams;
                    layoutParams3.setMarginEnd((int) ctx.getResources().getDimension(R.dimen.focus_notify_large_icon_margin_end));
                    layoutParams3.topMargin = (int) ctx.getResources().getDimension(R.dimen.focus_notify_large_icon_margin_top);
                }
                layoutParams.height = (int) dimension2;
            }
            layoutParams.width = (int) (dimension2 * fH);
            imageView2.setLayoutParams(layoutParams);
            imageView2.setImageIcon(icon2);
            imageView2.setVisibility(0);
            if (this.haveSubTitle) {
                ((TextView) v2.findViewById(R.id.focus_title)).setMaxEms(5);
            }
        }
    }

    public final void setRemoteViewsProgress(Context ctx, View v2, int i2, int i3, boolean z2, boolean z3, boolean z4, boolean z5, int i4, StatusBarNotification sbn) {
        ViewGroup.LayoutParams layoutParams;
        int i5;
        n.g(ctx, "ctx");
        n.g(v2, "v");
        n.g(sbn, "sbn");
        if (i2 < 0) {
            int dimensionPixelSize = ctx.getResources().getDimensionPixelSize(z5 ? R.dimen.dynamic_island_height : z3 ? R.dimen.focus_notify_deco_port_height : z4 ? R.dimen.focus_notify_deco_port_width : z2 ? R.dimen.focus_notify_normal_height_tiny : R.dimen.focus_notify_normal_height);
            View viewFindViewById = v2.findViewById(R.id.focus_container);
            if (viewFindViewById == null || (layoutParams = viewFindViewById.getLayoutParams()) == null || (i5 = layoutParams.height) == -1 || i5 == dimensionPixelSize) {
                return;
            }
            layoutParams.height = dimensionPixelSize;
            return;
        }
        View viewFindViewById2 = v2.findViewById(R.id.progressbar_container);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(0);
        }
        int i6 = R.dimen.focus_notify_extend_height;
        TextView textView = (TextView) v2.findViewById(R.id.focus_content);
        ViewGroup.LayoutParams layoutParams2 = textView != null ? textView.getLayoutParams() : null;
        LinearLayout.LayoutParams layoutParams3 = layoutParams2 instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams2 : null;
        if (z3) {
            i6 = R.dimen.focus_notify_deco_port_height;
        } else if (!z4) {
            if (z2) {
                if (layoutParams3 != null) {
                    layoutParams3.bottomMargin = ctx.getResources().getDimensionPixelSize(R.dimen.focus_notify_tiny_margin_gap_template_progress);
                }
                i6 = R.dimen.focus_notify_tiny_extend_height;
            } else {
                TextView textView2 = (TextView) v2.findViewById(R.id.focus_desc);
                if (textView2 != null) {
                    textView2.setVisibility(0);
                }
                if (textView2 != null) {
                    textView2.setText(this.desc);
                }
                if (layoutParams3 != null) {
                    layoutParams3.bottomMargin = ctx.getResources().getDimensionPixelSize(R.dimen.focus_notify_margin_bottom_normal_os2);
                }
            }
        }
        if (textView != null) {
            textView.setLayoutParams(layoutParams3);
        }
        View viewFindViewById3 = v2.findViewById(R.id.focus_container);
        ViewGroup.LayoutParams layoutParams4 = viewFindViewById3 != null ? viewFindViewById3.getLayoutParams() : null;
        if (layoutParams4 != null) {
            layoutParams4.height = ctx.getResources().getDimensionPixelSize(i6);
        }
        ProgressBar progressBar = (ProgressBar) v2.findViewById(R.id.focus_progress_info1);
        ImageView imageView = (ImageView) v2.findViewById(R.id.progress_point1);
        if (progressBar != null) {
            progressBar.setMax(100);
        }
        if (progressBar != null) {
            progressBar.setProgress(i2);
        }
        int i7 = R.drawable.point_select;
        int i8 = R.drawable.point_done;
        ProgressBar progressBar2 = (ProgressBar) v2.findViewById(R.id.focus_progress_info2);
        ImageView imageView2 = (ImageView) v2.findViewById(R.id.progress_point2);
        if (i2 == 100 && i3 == 1 && imageView != null) {
            imageView.setImageResource(i7);
        }
        if (i3 > 1) {
            if (progressBar2 != null) {
                progressBar2.setVisibility(0);
            }
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            if (progressBar != null) {
                progressBar.setMax(50);
            }
            if (progressBar != null) {
                progressBar.setProgress(i2);
            }
            if (progressBar2 != null) {
                progressBar2.setMax(50);
            }
            if (progressBar2 != null) {
                progressBar2.setProgress(i2 - 50);
            }
            if (i2 < 50) {
                if (imageView != null) {
                    imageView.setImageResource(i4);
                }
                if (imageView != null) {
                    imageView.setImageTintBlendMode(BlendMode.DST);
                }
            } else if (i2 == 50) {
                if (imageView != null) {
                    imageView.setImageResource(i7);
                }
                if (imageView != null) {
                    imageView.setImageTintBlendMode(BlendMode.SRC_IN);
                }
            } else if (i2 >= 51) {
                if (imageView != null) {
                    imageView.setImageResource(i8);
                }
                if (imageView != null) {
                    imageView.setImageTintBlendMode(BlendMode.SRC_IN);
                }
            }
            if (i2 < 99) {
                if (imageView2 != null) {
                    imageView2.setImageResource(i4);
                }
                if (imageView2 == null) {
                    return;
                }
                imageView2.setImageTintBlendMode(BlendMode.DST);
                return;
            }
            if (i2 >= 100) {
                if (imageView2 != null) {
                    imageView2.setImageResource(i7);
                }
                if (imageView2 == null) {
                    return;
                }
                imageView2.setImageTintBlendMode(BlendMode.SRC_IN);
            }
        }
    }

    public final void setReopen(String str) {
        this.reopen = str;
    }

    public final void setScene(String str) {
        n.g(str, "<set-?>");
        this.scene = str;
    }

    public final void setSolidBackground(boolean z2) {
        this.isSolidBackground = z2;
    }

    @SuppressLint({"CutPasteId"})
    public final void setTextVisibleAndText(View v2) {
        n.g(v2, "v");
        int i2 = R.id.focus_title;
        TextView textView = (TextView) v2.findViewById(i2);
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = (TextView) v2.findViewById(i2);
        if (textView2 != null) {
            textView2.setText(this.title);
        }
        int i3 = R.id.focus_content;
        TextView textView3 = (TextView) v2.findViewById(i3);
        if (textView3 != null) {
            textView3.setVisibility(0);
        }
        TextView textView4 = (TextView) v2.findViewById(i3);
        if (textView4 == null) {
            return;
        }
        textView4.setText(this.content);
    }

    public final void setTicker(String str) {
        this.ticker = str;
    }

    public final void setTickerPic(String str) {
        this.tickerPic = str;
    }

    public final void setTickerPicDark(String str) {
        this.tickerPicDark = str;
    }

    public final void setTimeoutMin(int i2) {
        this.timeoutMin = i2;
    }

    public final void setTitle(String str) {
        n.g(str, "<set-?>");
        this.title = str;
    }

    public final void showAppIcon(Context ctx, ImageView imageView, StatusBarNotification sbn) {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        String sbnTargetPkg = NotificationUtil.getSbnTargetPkg(ctx, sbn);
        if (imageView != null) {
            imageView.setImageDrawable(DynamicIslandUtils.INSTANCE.getAppIcon(ctx, sbnTargetPkg, Integer.valueOf(sbn.getUser().getIdentifier())));
        }
    }

    public String toString() {
        return Constant.KeyValue.VALUE_COLUMN + this.protocol + MethodCodeHelper.IDENTITY_INFO_SEPARATOR + this.scene + " t:" + this.title + " c:" + this.content + " des:" + this.desc;
    }

    public void wrapNotification(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        this.bitmapBundle = sbn.getNotification().extras.getBundle("miui.focus.pics");
        this.actionBundle = sbn.getNotification().extras.getBundle(Const.Param.PARAM_ACTION_BUNDLE);
        handleAodAndStatusBar(sbn, ctx);
        handleTimeout(sbn, this.timeoutMin);
        sbn.getNotification().extras.putBoolean(Const.Param.EXTRA_CUSTOM_HIDE_BORDER, true);
        sbn.getNotification().extras.putBoolean(Const.Param.EXTRA_ENABLE_FLOAT, this.enableFloat);
        sbn.getNotification().extras.putString(Const.Param.SCENE, this.scene);
        if (this.updatable) {
            sbn.getNotification().extras.putString(Const.Param.EXTRA_FOCUS_REOPEN, this.reopen);
            sbn.getNotification().extras.putBoolean(Const.Param.EXTRA_FOCUS_ENABLE_ALERT, this.enableFloat);
            sbn.getNotification().flags &= -17;
        } else {
            sbn.getNotification().extras.remove(Const.Param.EXTRA_FOCUS_REOPEN);
            sbn.getNotification().flags |= 16;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.valueOf(R.id.focus_title));
        arrayList.add(Integer.valueOf(R.id.focus_subtitle));
        arrayList.add(Integer.valueOf(R.id.focus_content));
        sbn.getNotification().extras.putIntegerArrayList(Const.Param.TEXT_VIds, arrayList);
        buildRemoteViews(ctx, sbn, focusContent);
    }

    public static /* synthetic */ void setActionData$default(FocusTemplate focusTemplate, View view, int i2, Notification.Action action, boolean z2, boolean z3, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setActionData");
        }
        focusTemplate.setActionData(view, i2, action, (i3 & 8) != 0 ? false : z2, (i3 & 16) != 0 ? false : z3);
    }

    public final void setActionData(View v2, int i2, final Notification.Action action, boolean z2, boolean z3) {
        String string;
        n.g(v2, "v");
        if (action == null || (string = action.getExtras().getString("icon_name")) == null) {
            return;
        }
        int iconResourceId = getIconResourceId(string, z2, z3);
        ImageView imageView = (ImageView) v2.findViewById(i2);
        if (iconResourceId != -1 && imageView != null) {
            imageView.setImageResource(iconResourceId);
        }
        if (imageView != null) {
            imageView.setContentDescription(action.title);
        }
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: A1.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws PendingIntent.CanceledException {
                    FocusTemplate.setActionData$lambda$1(action, view);
                }
            });
        }
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        this.isOnlyActionButton = true;
    }
}
