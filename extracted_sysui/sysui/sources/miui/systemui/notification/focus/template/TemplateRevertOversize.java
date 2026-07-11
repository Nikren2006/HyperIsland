package miui.systemui.notification.focus.template;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.util.ContextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@SceneName(sceneNames = {Const.Scene.ANNIVERSARY, Const.Template.TEMPLATE_BASE_REVERT_OVERSIZE})
public final class TemplateRevertOversize extends FocusTemplate {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateRevertOversize(JSONObject param) {
        super(param);
        n.g(param, "param");
    }

    private final void normalViewsBuild(Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, View view) throws FocusParamsException {
        setTextVisibleAndText(view);
        setRemoteViewsBackground(context, view, statusBarNotification, z2, z3);
    }

    public static /* synthetic */ void normalViewsBuild$default(TemplateRevertOversize templateRevertOversize, Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, View view, int i2, Object obj) throws FocusParamsException {
        if ((i2 & 8) != 0) {
            z3 = false;
        }
        templateRevertOversize.normalViewsBuild(context, statusBarNotification, z2, z3, view);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildDecoLandViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildDecoPortViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
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
            focusNotification = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view = focusNotification;
        View focusNotificationDark = focusContent.getFocusNotificationDark();
        if (focusNotificationDark == null) {
            focusNotificationDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view2 = focusNotificationDark;
        View focusNotificationModal = focusContent.getFocusNotificationModal();
        if (focusNotificationModal == null) {
            focusNotificationModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
            focusNotificationModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view3 = focusNotificationModal;
        View focusNotificationDarkModal = focusContent.getFocusNotificationDarkModal();
        if (focusNotificationDarkModal == null) {
            focusNotificationDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
            focusNotificationDarkModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view4 = focusNotificationDarkModal;
        Context nightContext = contextUtils.getNightContext(ctx, true);
        View islandExpandedView = focusContent.getIslandExpandedView();
        if (islandExpandedView == null) {
            islandExpandedView = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view5 = islandExpandedView;
        View islandExpandedViewFake = focusContent.getIslandExpandedViewFake();
        if (islandExpandedViewFake == null) {
            islandExpandedViewFake = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view6 = islandExpandedViewFake;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, false, false, view, 8, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, false, false, view2, 8, null);
        n.d(view3);
        normalViewsBuild$default(this, ctx, sbn, false, false, view3, 8, null);
        n.d(view4);
        normalViewsBuild$default(this, ctx, sbn, false, false, view4, 8, null);
        n.d(view5);
        normalViewsBuild(ctx, sbn, false, true, view5);
        n.d(view6);
        normalViewsBuild(ctx, sbn, false, true, view6);
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
            tinyView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view = tinyView;
        View tinyKeyguardView = focusContent.getTinyKeyguardView();
        if (tinyKeyguardView == null) {
            tinyKeyguardView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view2 = tinyKeyguardView;
        View tinyViewDark = focusContent.getTinyViewDark();
        if (tinyViewDark == null) {
            tinyViewDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view3 = tinyViewDark;
        View tinyViewModal = focusContent.getTinyViewModal();
        if (tinyViewModal == null) {
            tinyViewModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view4 = tinyViewModal;
        View tinyViewDarkModal = focusContent.getTinyViewDarkModal();
        if (tinyViewDarkModal == null) {
            tinyViewDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view5 = tinyViewDarkModal;
        View tinyViewKeyguardDark = focusContent.getTinyViewKeyguardDark();
        if (tinyViewKeyguardDark == null) {
            tinyViewKeyguardDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_oversize, (ViewGroup) null);
        }
        View view6 = tinyViewKeyguardDark;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, true, false, view, 8, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, true, false, view2, 8, null);
        n.d(view3);
        normalViewsBuild$default(this, ctx, sbn, true, false, view3, 8, null);
        n.d(view4);
        normalViewsBuild$default(this, ctx, sbn, true, false, view4, 8, null);
        n.d(view5);
        normalViewsBuild$default(this, ctx, sbn, true, false, view5, 8, null);
        n.d(view6);
        normalViewsBuild$default(this, ctx, sbn, true, false, view6, 8, null);
        focusContent.setTinyView(view);
        focusContent.setTinyKeyguardView(view2);
        focusContent.setTinyViewDark(view3);
        focusContent.setTinyViewModal(view4);
        focusContent.setTinyViewDarkModal(view5);
        focusContent.setTinyViewKeyguardDark(view6);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public String toString() {
        return TemplateRevertOversize.class.getSimpleName() + " " + super.toString();
    }
}
