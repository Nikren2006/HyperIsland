package miui.systemui.notification.focus.template;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.util.ContextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@SceneName(sceneNames = {Const.Scene.SMART_HOME_ALERT, Const.Scene.SOS, Const.Scene.MISSED_CALLS, Const.Template.TEMPLATE_BASE})
public final class TemplateBase extends FocusTemplate {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateBase(JSONObject param) {
        super(param);
        n.g(param, "param");
    }

    private final void normalViewsBuild(Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, boolean z4, boolean z5, View view) throws FocusParamsException {
        TextView textView;
        resetViewState(view);
        setTextVisibleAndText(view);
        if (z3 && z4) {
            String strOptString = getParam().optString(Const.Param.CONTENT);
            n.d(strOptString);
            if (strOptString.length() > 0) {
                TextView textView2 = (TextView) view.findViewById(R.id.focus_content_sos);
                if (textView2 != null) {
                    textView2.setVisibility(0);
                }
                if (textView2 != null) {
                    textView2.setText(strOptString);
                }
                Integer contentColor = getContentColor();
                if (contentColor != null) {
                    int iIntValue = contentColor.intValue();
                    if (textView2 != null) {
                        textView2.setTextColor(iIntValue);
                    }
                }
            }
            if (getDesc1().length() > 0) {
                TextView textView3 = (TextView) view.findViewById(R.id.focus_desc1);
                if (textView3 != null) {
                    textView3.setVisibility(0);
                }
                if (textView3 != null) {
                    textView3.setText(getDesc1());
                }
                Integer descColor = getDescColor();
                if (descColor != null) {
                    int iIntValue2 = descColor.intValue();
                    if (textView3 != null) {
                        textView3.setTextColor(iIntValue2);
                    }
                }
            }
            if (getDesc2().length() > 0) {
                TextView textView4 = (TextView) view.findViewById(R.id.focus_desc2);
                if (textView4 != null) {
                    textView4.setVisibility(0);
                }
                if (textView4 != null) {
                    textView4.setText(getDesc2());
                }
                Integer descColor2 = getDescColor();
                if (descColor2 != null) {
                    int iIntValue3 = descColor2.intValue();
                    if (textView4 != null) {
                        textView4.setTextColor(iIntValue3);
                    }
                }
            }
        }
        if (z3 && !z4 && (textView = (TextView) view.findViewById(R.id.focus_title)) != null) {
            textView.setMaxLines(1);
        }
        int i2 = R.id.focus_button_icon1;
        Bundle actionBundle = getActionBundle();
        setActionData(view, i2, actionBundle != null ? (Notification.Action) actionBundle.getParcelable(Const.Param.ACTION_VId_1) : null, z3, z4);
        int i3 = R.id.focus_button_icon2;
        Bundle actionBundle2 = getActionBundle();
        setActionData(view, i3, actionBundle2 != null ? (Notification.Action) actionBundle2.getParcelable(Const.Param.ACTION_VId_2) : null, z3, z4);
        setRemoteViewsBackground(context, view, statusBarNotification, z2, z5);
        if (isSolidBackground() && !isBgPicDownloadFail()) {
            TextView textView5 = (TextView) view.findViewById(R.id.focus_title);
            TextView textView6 = (TextView) view.findViewById(R.id.focus_content);
            TextView textView7 = (TextView) view.findViewById(R.id.focus_desc);
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue4 = titleColor.intValue();
                if (textView5 != null) {
                    textView5.setTextColor(iIntValue4);
                }
            }
            Integer contentColor2 = getContentColor();
            if (contentColor2 != null) {
                int iIntValue5 = contentColor2.intValue();
                if (textView6 != null) {
                    textView6.setTextColor(iIntValue5);
                }
            }
            Integer descColor3 = getDescColor();
            if (descColor3 != null) {
                int iIntValue6 = descColor3.intValue();
                if (textView7 != null) {
                    textView7.setTextColor(iIntValue6);
                }
                if (isNotHaveContent() && textView6 != null) {
                    textView6.setTextColor(iIntValue6);
                }
            }
        }
        resetTemplateState();
    }

    public static /* synthetic */ void normalViewsBuild$default(TemplateBase templateBase, Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, boolean z4, boolean z5, View view, int i2, Object obj) throws FocusParamsException {
        templateBase.normalViewsBuild(context, statusBarNotification, z2, (i2 & 8) != 0 ? false : z3, (i2 & 16) != 0 ? false : z4, (i2 & 32) != 0 ? false : z5, view);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildDecoLandViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) throws FocusParamsException {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        View decoLand = focusContent.getDecoLand();
        if (decoLand == null) {
            decoLand = LayoutInflater.from(ContextUtils.INSTANCE.getDayContext(ctx)).inflate(R.layout.focus_notification_template_base_deco_land, (ViewGroup) null);
        }
        View view = decoLand;
        View decoLandDark = focusContent.getDecoLandDark();
        if (decoLandDark == null) {
            decoLandDark = LayoutInflater.from(ContextUtils.getNightContext$default(ContextUtils.INSTANCE, ctx, false, 2, null)).inflate(R.layout.focus_notification_template_base_deco_land, (ViewGroup) null);
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
        View deco = focusContent.getDeco();
        if (deco == null) {
            deco = LayoutInflater.from(ContextUtils.INSTANCE.getDayContext(ctx)).inflate(R.layout.focus_notification_template_base_deco_prot, (ViewGroup) null);
        }
        View view = deco;
        View decoDark = focusContent.getDecoDark();
        if (decoDark == null) {
            decoDark = LayoutInflater.from(ContextUtils.getNightContext$default(ContextUtils.INSTANCE, ctx, false, 2, null)).inflate(R.layout.focus_notification_template_base_deco_prot, (ViewGroup) null);
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
        ContextUtils contextUtils = ContextUtils.INSTANCE;
        Context dayContext = contextUtils.getDayContext(ctx);
        Context nightContext$default = ContextUtils.getNightContext$default(contextUtils, ctx, false, 2, null);
        View focusNotification = focusContent.getFocusNotification();
        if (focusNotification == null) {
            focusNotification = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_base, (ViewGroup) null);
        }
        View view = focusNotification;
        View focusNotificationDark = focusContent.getFocusNotificationDark();
        if (focusNotificationDark == null) {
            focusNotificationDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_base, (ViewGroup) null);
        }
        View view2 = focusNotificationDark;
        View focusNotificationModal = focusContent.getFocusNotificationModal();
        if (focusNotificationModal == null) {
            focusNotificationModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_base, (ViewGroup) null);
            focusNotificationModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view3 = focusNotificationModal;
        View focusNotificationDarkModal = focusContent.getFocusNotificationDarkModal();
        if (focusNotificationDarkModal == null) {
            focusNotificationDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_base, (ViewGroup) null);
            focusNotificationDarkModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view4 = focusNotificationDarkModal;
        Context nightContext = contextUtils.getNightContext(ctx, true);
        View islandExpandedView = focusContent.getIslandExpandedView();
        if (islandExpandedView == null) {
            islandExpandedView = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_base, (ViewGroup) null);
        }
        View view5 = islandExpandedView;
        View islandExpandedViewFake = focusContent.getIslandExpandedViewFake();
        if (islandExpandedViewFake == null) {
            islandExpandedViewFake = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_base, (ViewGroup) null);
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
        ContextUtils contextUtils = ContextUtils.INSTANCE;
        Context dayContext = contextUtils.getDayContext(ctx);
        Context nightContext$default = ContextUtils.getNightContext$default(contextUtils, ctx, false, 2, null);
        View tinyView = focusContent.getTinyView();
        if (tinyView == null) {
            tinyView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_base_tiny, (ViewGroup) null);
        }
        View view = tinyView;
        View tinyKeyguardView = focusContent.getTinyKeyguardView();
        if (tinyKeyguardView == null) {
            tinyKeyguardView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_base_tiny, (ViewGroup) null);
        }
        View view2 = tinyKeyguardView;
        View tinyViewDark = focusContent.getTinyViewDark();
        if (tinyViewDark == null) {
            tinyViewDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_base_tiny, (ViewGroup) null);
        }
        View view3 = tinyViewDark;
        View tinyViewModal = focusContent.getTinyViewModal();
        if (tinyViewModal == null) {
            tinyViewModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_base_tiny, (ViewGroup) null);
        }
        View view4 = tinyViewModal;
        View tinyViewDarkModal = focusContent.getTinyViewDarkModal();
        if (tinyViewDarkModal == null) {
            tinyViewDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_base_tiny, (ViewGroup) null);
        }
        View view5 = tinyViewDarkModal;
        View tinyViewKeyguardDark = focusContent.getTinyViewKeyguardDark();
        if (tinyViewKeyguardDark == null) {
            tinyViewKeyguardDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_base_tiny, (ViewGroup) null);
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

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public String toString() {
        return TemplateBase.class.getSimpleName() + " " + super.toString();
    }
}
