package miui.systemui.notification.focus.template;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import com.android.systemui.plugins.miui.notification.FocusNotificationContent;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.Const;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.util.ContextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@SceneName(sceneNames = {Const.Scene.FOOD_DELIVERY, Const.Template.TEMPLATE_BASE_REVERT_PROGRESS})
public final class TemplateRevertProgress extends FocusTemplate {
    private final int progress;
    private final int progressCount;
    private final boolean showSmallIcon;
    private final String subtitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateRevertProgress(JSONObject param) {
        super(param);
        n.g(param, "param");
        this.subtitle = param.optString(Const.Param.SUB_TITLE);
        this.progress = param.optInt("progress", -1);
        this.progressCount = param.optInt(Const.Param.PROGRESS_COUNT, 0);
        this.showSmallIcon = param.optBoolean(Const.Param.SHOW_SMALL_ICON, true);
    }

    private final void normalViewsBuild(Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, boolean z4, boolean z5, View view) throws FocusParamsException {
        resetViewState(view);
        TextView textView = (TextView) view.findViewById(R.id.focus_title);
        TextView textView2 = (TextView) view.findViewById(R.id.focus_subtitle);
        TextView textView3 = (TextView) view.findViewById(R.id.focus_content);
        if (textView != null) {
            textView.setMaxEms(10);
        }
        setTextVisibleAndText(view);
        String str = this.subtitle;
        if (str != null && str.length() > 0 && z4 && textView != null) {
            textView.setSingleLine();
        }
        String str2 = this.subtitle;
        if (str2 != null && !n.c(str2, "")) {
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            if (textView2 != null) {
                textView2.setText(str2);
            }
            setHaveSubTitle(true);
        }
        if (z3) {
            if (textView != null) {
                textView.setMaxLines(3);
            }
            if (textView2 != null) {
                textView2.setSingleLine();
            }
            if (getTitle().length() > 10) {
                String str3 = this.subtitle;
                n.d(str3);
                if (str3.length() == 0 && textView3 != null) {
                    textView3.setMaxLines(2);
                }
            }
            if (getTitle().length() > 10) {
                String str4 = this.subtitle;
                n.d(str4);
                if (str4.length() > 0 && textView3 != null) {
                    textView3.setMaxLines(1);
                }
            }
            int length = getTitle().length();
            if (5 <= length && length < 11) {
                String str5 = this.subtitle;
                n.d(str5);
                if (str5.length() == 0 && textView3 != null) {
                    textView3.setMaxLines(3);
                }
            }
            int length2 = getTitle().length();
            if (5 <= length2 && length2 < 11) {
                String str6 = this.subtitle;
                n.d(str6);
                if (str6.length() > 0 && textView3 != null) {
                    textView3.setMaxLines(2);
                }
            }
            int length3 = getTitle().length();
            if (1 <= length3 && length3 < 6) {
                String str7 = this.subtitle;
                n.d(str7);
                if (str7.length() == 0 && textView3 != null) {
                    textView3.setMaxLines(4);
                }
            }
            int length4 = getTitle().length();
            if (1 <= length4 && length4 < 6) {
                String str8 = this.subtitle;
                n.d(str8);
                if (str8.length() > 0 && textView3 != null) {
                    textView3.setMaxLines(3);
                }
            }
        }
        setRemoteViewsBackground(context, view, statusBarNotification, z2, z5);
        if (isSolidBackground() && !isBgPicDownloadFail()) {
            if (getTitleColor() != null) {
                if (textView != null) {
                    textView.setTextColor(getTitleColor().intValue());
                }
                if (textView2 != null) {
                    textView2.setTextColor(getTitleColor().intValue());
                }
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue = contentColor.intValue();
                if (textView3 != null) {
                    textView3.setTextColor(iIntValue);
                }
            }
            if (getDescColor() != null) {
                setDescTextColor(view, getDescColor().intValue());
            }
        }
        FocusTemplate.setRemoteViewsProgress$default(this, context, view, this.progress, this.progressCount, z2, z3, z4, z5, 0, statusBarNotification, 256, null);
        if (this.showSmallIcon) {
            ImageView imageView = (ImageView) view.findViewById(R.id.focus_small_icon);
            if (imageView != null) {
                imageView.setVisibility(0);
                showAppIcon(context, imageView, statusBarNotification);
            }
        } else {
            ImageView imageView2 = (ImageView) view.findViewById(R.id.focus_small_icon);
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
        }
        resetTemplateState();
    }

    public static /* synthetic */ void normalViewsBuild$default(TemplateRevertProgress templateRevertProgress, Context context, StatusBarNotification statusBarNotification, boolean z2, boolean z3, boolean z4, boolean z5, View view, int i2, Object obj) throws FocusParamsException {
        templateRevertProgress.normalViewsBuild(context, statusBarNotification, z2, (i2 & 8) != 0 ? false : z3, (i2 & 16) != 0 ? false : z4, (i2 & 32) != 0 ? false : z5, view);
    }

    @Override // miui.systemui.notification.focus.template.FocusTemplate
    public void buildDecoLandViews(Context ctx, StatusBarNotification sbn, FocusNotificationContent focusContent) throws FocusParamsException {
        n.g(ctx, "ctx");
        n.g(sbn, "sbn");
        n.g(focusContent, "focusContent");
        View decoLand = focusContent.getDecoLand();
        if (decoLand == null) {
            decoLand = LayoutInflater.from(ContextUtils.INSTANCE.getDayContext(ctx)).inflate(R.layout.focus_notification_template_revert_progress_deco_land, (ViewGroup) null);
        }
        View view = decoLand;
        View decoLandDark = focusContent.getDecoLandDark();
        if (decoLandDark == null) {
            decoLandDark = LayoutInflater.from(ContextUtils.getNightContext$default(ContextUtils.INSTANCE, ctx, false, 2, null)).inflate(R.layout.focus_notification_template_revert_progress_deco_land, (ViewGroup) null);
        }
        View view2 = decoLandDark;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, false, false, true, false, view, 40, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, false, false, true, false, view2, 40, null);
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
            deco = LayoutInflater.from(ContextUtils.INSTANCE.getDayContext(ctx)).inflate(R.layout.focus_notification_template_revert_progress_deco_port, (ViewGroup) null);
        }
        View view = deco;
        View decoDark = focusContent.getDecoDark();
        if (decoDark == null) {
            decoDark = LayoutInflater.from(ContextUtils.getNightContext$default(ContextUtils.INSTANCE, ctx, false, 2, null)).inflate(R.layout.focus_notification_template_revert_progress_deco_port, (ViewGroup) null);
        }
        View view2 = decoDark;
        n.d(view);
        normalViewsBuild$default(this, ctx, sbn, false, true, false, false, view, 48, null);
        n.d(view2);
        normalViewsBuild$default(this, ctx, sbn, false, true, false, false, view2, 48, null);
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
            focusNotification = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_progress, (ViewGroup) null);
        }
        View view = focusNotification;
        View focusNotificationDark = focusContent.getFocusNotificationDark();
        if (focusNotificationDark == null) {
            focusNotificationDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_progress, (ViewGroup) null);
        }
        View view2 = focusNotificationDark;
        View focusNotificationModal = focusContent.getFocusNotificationModal();
        if (focusNotificationModal == null) {
            focusNotificationModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_progress, (ViewGroup) null);
            focusNotificationModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view3 = focusNotificationModal;
        View focusNotificationDarkModal = focusContent.getFocusNotificationDarkModal();
        if (focusNotificationDarkModal == null) {
            focusNotificationDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_progress, (ViewGroup) null);
            focusNotificationDarkModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view4 = focusNotificationDarkModal;
        Context nightContext = contextUtils.getNightContext(ctx, true);
        View islandExpandedView = focusContent.getIslandExpandedView();
        if (islandExpandedView == null) {
            islandExpandedView = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_revert_progress, (ViewGroup) null);
        }
        View view5 = islandExpandedView;
        View islandExpandedViewFake = focusContent.getIslandExpandedViewFake();
        if (islandExpandedViewFake == null) {
            islandExpandedViewFake = LayoutInflater.from(nightContext).inflate(R.layout.focus_notification_template_revert_progress, (ViewGroup) null);
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
            tinyView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_progress_tiny, (ViewGroup) null);
        }
        View view = tinyView;
        View tinyKeyguardView = focusContent.getTinyKeyguardView();
        if (tinyKeyguardView == null) {
            tinyKeyguardView = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_progress_tiny, (ViewGroup) null);
        }
        View view2 = tinyKeyguardView;
        View tinyViewDark = focusContent.getTinyViewDark();
        if (tinyViewDark == null) {
            tinyViewDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_progress_tiny, (ViewGroup) null);
        }
        View view3 = tinyViewDark;
        View tinyViewModal = focusContent.getTinyViewModal();
        if (tinyViewModal == null) {
            tinyViewModal = LayoutInflater.from(dayContext).inflate(R.layout.focus_notification_template_revert_progress_tiny, (ViewGroup) null);
            tinyViewModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view4 = tinyViewModal;
        View tinyViewDarkModal = focusContent.getTinyViewDarkModal();
        if (tinyViewDarkModal == null) {
            tinyViewDarkModal = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_progress_tiny, (ViewGroup) null);
            tinyViewDarkModal.setTag(R.id.dynamic_island_modal_tag, Boolean.TRUE);
        }
        View view5 = tinyViewDarkModal;
        View tinyViewKeyguardDark = focusContent.getTinyViewKeyguardDark();
        if (tinyViewKeyguardDark == null) {
            tinyViewKeyguardDark = LayoutInflater.from(nightContext$default).inflate(R.layout.focus_notification_template_revert_progress_tiny, (ViewGroup) null);
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
        return TemplateRevertProgress.class.getSimpleName() + " " + super.toString();
    }
}
