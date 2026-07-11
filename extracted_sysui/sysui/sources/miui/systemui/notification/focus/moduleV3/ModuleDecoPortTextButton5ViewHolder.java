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
public final class ModuleDecoPortTextButton5ViewHolder extends ModuleViewHolder {
    private View action;
    private TimerTextEffectView actionTitleView;
    private View container;
    private TimerTextEffectView primaryTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoPortTextButton5ViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
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
            TimerTextEffectView timerTextEffectView2 = this.primaryTextView;
            if (timerTextEffectView2 != null) {
                timerTextEffectView2.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView3 = this.primaryTextView;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setText(Html.fromHtml(primaryText), TextView.BufferType.SPANNABLE);
            }
            Integer primaryColor = getPrimaryColor();
            if (primaryColor != null) {
                int iIntValue = primaryColor.intValue();
                TimerTextEffectView timerTextEffectView4 = this.primaryTextView;
                if (timerTextEffectView4 != null) {
                    timerTextEffectView4.updateTextWithNewAppearance(Html.fromHtml(primaryText), Integer.valueOf(iIntValue));
                }
            }
        }
        HighlightInfoV3 highlightInfoV3 = template.getHighlightInfoV3();
        if (highlightInfoV3 == null || (actionInfo = highlightInfoV3.getActionInfo()) == null || TextUtils.isEmpty(actionInfo.getAction())) {
            return;
        }
        TimerTextEffectView timerTextEffectView5 = this.actionTitleView;
        if (timerTextEffectView5 != null) {
            timerTextEffectView5.setVisibility(0);
        }
        TimerTextEffectView timerTextEffectView6 = this.actionTitleView;
        if (timerTextEffectView6 != null) {
            timerTextEffectView6.setText(Html.fromHtml(actionInfo.getActionTitle()), TextView.BufferType.SPANNABLE);
        }
        String actionTitleColor = actionInfo.getActionTitleColor();
        if (actionTitleColor != null && (timerTextEffectView = this.actionTitleView) != null) {
            timerTextEffectView.updateTextWithNewAppearance(Html.fromHtml(actionInfo.getActionTitle()), Integer.valueOf(Color.parseColor(actionTitleColor)));
        }
        String actionBgColor = actionInfo.getActionBgColor();
        if (actionBgColor != null) {
            Drawable drawable = getCtx().getResources().getDrawable(R.drawable.focus_button_background);
            kotlin.jvm.internal.n.e(drawable, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            gradientDrawable.setColor(Color.parseColor(actionBgColor));
            View view2 = this.action;
            kotlin.jvm.internal.n.d(view2);
            view2.setBackground(gradientDrawable);
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_button_5, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_button_5) : null;
        View view2 = getView();
        this.primaryTextView = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.focus_primary_text) : null;
        View view3 = getView();
        View viewFindViewById = view3 != null ? view3.findViewById(R.id.focus_button_container_action) : null;
        this.action = viewFindViewById;
        this.actionTitleView = viewFindViewById != null ? (TimerTextEffectView) viewFindViewById.findViewById(R.id.focus_button_title) : null;
        TimerTextEffectView timerTextEffectView = this.primaryTextView;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.actionTitleView;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView3 = this.primaryTextView;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView4 = this.actionTitleView;
        if (timerTextEffectView4 == null) {
            return;
        }
        timerTextEffectView4.setEnableEffectWithInit(false);
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
