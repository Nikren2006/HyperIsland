package miui.systemui.notification.focus.moduleV3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import java.net.URISyntaxException;
import miui.systemui.notification.focus.model.ActionInfo;
import miui.systemui.notification.focus.model.HighlightInfoV3;
import miui.systemui.notification.focus.model.Template;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyTextButton5ViewHolder extends ModuleViewHolder {
    private View action;
    private TimerTextEffectView actionTitleView;
    private View container;
    private View focusSubTextContainer;
    private TimerTextEffectView highLightTextView;
    private TimerTextEffectView primaryTextView;
    private TimerTextEffectView secondaryTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyTextButton5ViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void bind(Template template, StatusBarNotification sbn) throws URISyntaxException {
        ActionInfo actionInfo;
        TimerTextEffectView timerTextEffectView;
        TimerTextEffectView timerTextEffectView2;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getHighlightInfoV3());
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        ModuleViewHolder.setActionData$default(this, template, sbn, false, 4, null);
        String primaryText = getPrimaryText();
        if (primaryText != null && !TextUtils.isEmpty(primaryText)) {
            TimerTextEffectView timerTextEffectView3 = this.primaryTextView;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView4 = this.primaryTextView;
            if (timerTextEffectView4 != null) {
                timerTextEffectView4.setText(Html.fromHtml(primaryText), TextView.BufferType.SPANNABLE);
            }
            Integer primaryColor = getPrimaryColor();
            if (primaryColor != null) {
                int iIntValue = primaryColor.intValue();
                TimerTextEffectView timerTextEffectView5 = this.primaryTextView;
                if (timerTextEffectView5 != null) {
                    timerTextEffectView5.updateTextWithNewAppearance(Html.fromHtml(primaryText), Integer.valueOf(iIntValue));
                }
            }
        }
        String secondaryText = getSecondaryText();
        if (secondaryText != null && !TextUtils.isEmpty(secondaryText)) {
            TimerTextEffectView timerTextEffectView6 = this.secondaryTextView;
            if (timerTextEffectView6 != null) {
                timerTextEffectView6.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView7 = this.secondaryTextView;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.setText(Html.fromHtml(secondaryText), TextView.BufferType.SPANNABLE);
            }
            if (kotlin.jvm.internal.n.c(getShowSecondaryLine(), Boolean.TRUE) && (timerTextEffectView2 = this.secondaryTextView) != null) {
                timerTextEffectView2.setPaintFlags(timerTextEffectView2 != null ? timerTextEffectView2.getPaintFlags() | 16 : 0);
            }
            Integer secondaryColor = getSecondaryColor();
            if (secondaryColor != null) {
                int iIntValue2 = secondaryColor.intValue();
                TimerTextEffectView timerTextEffectView8 = this.secondaryTextView;
                if (timerTextEffectView8 != null) {
                    timerTextEffectView8.updateTextWithNewAppearance(Html.fromHtml(secondaryText), Integer.valueOf(iIntValue2));
                }
            }
        }
        String highLightText = getHighLightText();
        if (highLightText != null && !TextUtils.isEmpty(highLightText)) {
            if (TextUtils.isEmpty(getSecondaryText())) {
                TimerTextEffectView timerTextEffectView9 = this.highLightTextView;
                if (timerTextEffectView9 != null) {
                    timerTextEffectView9.setVisibility(0);
                }
                TimerTextEffectView timerTextEffectView10 = this.highLightTextView;
                if (timerTextEffectView10 != null) {
                    timerTextEffectView10.setText(Html.fromHtml(highLightText), TextView.BufferType.SPANNABLE);
                }
                Integer highLightTextColor = getHighLightTextColor();
                if (highLightTextColor != null) {
                    int iIntValue3 = highLightTextColor.intValue();
                    TimerTextEffectView timerTextEffectView11 = this.highLightTextView;
                    if (timerTextEffectView11 != null) {
                        timerTextEffectView11.updateTextWithNewAppearance(Html.fromHtml(highLightText), Integer.valueOf(iIntValue3));
                    }
                }
                Integer highLightbgColor = getHighLightbgColor();
                if (highLightbgColor != null) {
                    int iIntValue4 = highLightbgColor.intValue();
                    Drawable drawable = getCtx().getResources().getDrawable(R.drawable.focus_tiny_text_background);
                    kotlin.jvm.internal.n.e(drawable, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
                    GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                    gradientDrawable.setColor(iIntValue4);
                    View view2 = this.focusSubTextContainer;
                    kotlin.jvm.internal.n.d(view2);
                    view2.setBackground(gradientDrawable);
                }
            } else {
                TimerTextEffectView timerTextEffectView12 = this.highLightTextView;
                if (timerTextEffectView12 != null) {
                    timerTextEffectView12.setVisibility(8);
                }
            }
        }
        HighlightInfoV3 highlightInfoV3 = template.getHighlightInfoV3();
        if (highlightInfoV3 == null || (actionInfo = highlightInfoV3.getActionInfo()) == null || TextUtils.isEmpty(actionInfo.getAction())) {
            return;
        }
        TimerTextEffectView timerTextEffectView13 = this.actionTitleView;
        if (timerTextEffectView13 != null) {
            timerTextEffectView13.setVisibility(0);
        }
        TimerTextEffectView timerTextEffectView14 = this.actionTitleView;
        if (timerTextEffectView14 != null) {
            timerTextEffectView14.setText(Html.fromHtml(actionInfo.getActionTitle()), TextView.BufferType.SPANNABLE);
        }
        String actionTitleColor = actionInfo.getActionTitleColor();
        if (actionTitleColor != null && (timerTextEffectView = this.actionTitleView) != null) {
            timerTextEffectView.updateTextWithNewAppearance(Html.fromHtml(actionInfo.getActionTitle()), Integer.valueOf(Color.parseColor(actionTitleColor)));
        }
        String actionBgColor = actionInfo.getActionBgColor();
        if (actionBgColor != null) {
            Drawable drawable2 = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable2 = (GradientDrawable) drawable2;
            gradientDrawable2.setColor(Color.parseColor(actionBgColor));
            View view3 = this.action;
            kotlin.jvm.internal.n.d(view3);
            view3.setBackground(gradientDrawable2);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_button_5, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_button_5) : null;
        View view2 = getView();
        this.primaryTextView = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.focus_primary_text) : null;
        View view3 = getView();
        this.secondaryTextView = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.focus_secondary_text) : null;
        View view4 = getView();
        this.highLightTextView = view4 != null ? (TimerTextEffectView) view4.findViewById(R.id.focus_highLight_text) : null;
        View view5 = getView();
        this.focusSubTextContainer = view5 != null ? view5.findViewById(R.id.focus_sub_text_container) : null;
        View view6 = getView();
        View viewFindViewById = view6 != null ? view6.findViewById(R.id.focus_button_container_action) : null;
        this.action = viewFindViewById;
        this.actionTitleView = viewFindViewById != null ? (TimerTextEffectView) viewFindViewById.findViewById(R.id.focus_button_title) : null;
        TimerTextEffectView timerTextEffectView = this.primaryTextView;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.secondaryTextView;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView3 = this.highLightTextView;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView4 = this.actionTitleView;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView5 = this.primaryTextView;
        if (timerTextEffectView5 != null) {
            timerTextEffectView5.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView6 = this.secondaryTextView;
        if (timerTextEffectView6 != null) {
            timerTextEffectView6.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView7 = this.highLightTextView;
        if (timerTextEffectView7 != null) {
            timerTextEffectView7.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView8 = this.actionTitleView;
        if (timerTextEffectView8 == null) {
            return;
        }
        timerTextEffectView8.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) throws URISyntaxException {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getHighlightInfoV3());
        bind(template, sbn);
    }
}
